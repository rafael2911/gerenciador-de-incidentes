package br.com.crcarvalho.incidentes.controller;

import java.time.LocalDateTime;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import br.com.crcarvalho.incidentes.model.entity.Chamado;
import br.com.crcarvalho.incidentes.model.entity.Interacao;
import br.com.crcarvalho.incidentes.model.entity.Role;
import br.com.crcarvalho.incidentes.model.entity.StatusChamado;
import br.com.crcarvalho.incidentes.model.entity.TipoInteracao;
import br.com.crcarvalho.incidentes.model.entity.Usuario;
import br.com.crcarvalho.incidentes.model.repository.CategoriaRepository;
import br.com.crcarvalho.incidentes.model.repository.ChamadoRepository;
import br.com.crcarvalho.incidentes.model.repository.OrigemRepository;

@Controller
@RequestMapping("chamado")
public class ChamadoController {
	
	@Autowired
	private ChamadoRepository chamadoRepository;
	
	@Autowired
	private CategoriaRepository categoriaRepository;
	
	@Autowired
	private OrigemRepository origemRepository;
	
	@GetMapping
	public ModelAndView index(@AuthenticationPrincipal Usuario usuario) {
		
		if(usuario.getRoles().contains(new Role("ROLE_ADMIN")) || usuario.getRoles().contains(new Role("ROLE_TECNICO"))) {
			return new ModelAndView("chamado/list", "chamados", chamadoRepository.findAll());
		}
		
		return new ModelAndView("redirect:/");
	}
	
	@GetMapping("novo")
	public ModelAndView form(@ModelAttribute Chamado chamado) {
		
		ModelAndView modelAndView = new ModelAndView("chamado/form");
		
		modelAndView.addObject("categorias", categoriaRepository.findAll());
		modelAndView.addObject("origens", origemRepository.findAll());
		
		return modelAndView;
	}
	
	@PostMapping(params = "form")
	public ModelAndView salvar(@Valid Chamado chamado, BindingResult result, @AuthenticationPrincipal Usuario usuario, RedirectAttributes attr) {
		
		if(result.hasErrors()) {
			ModelAndView modelAndView = new ModelAndView("chamado/form");
			
			modelAndView.addObject("categorias", categoriaRepository.findAll());
			modelAndView.addObject("origens", origemRepository.findAll());
			
			return modelAndView;
		}
		
		chamado.setRequerente(usuario);
		
		chamadoRepository.save(chamado);
		
		attr.addFlashAttribute("message", "Chamado cadastrado com sucesso.");
		
		return new ModelAndView("redirect:/chamado");
		
	}
	
	@GetMapping("detalhe/{idChamado}")
	public ModelAndView detalhe(@PathVariable("idChamado") Long idChamado, RedirectAttributes attr, @AuthenticationPrincipal Usuario usuario, @ModelAttribute Interacao interacao) {
		
		Chamado chamado = chamadoRepository.findOne(idChamado);
		
		if(!usuarioPodeAcessar(chamado, usuario)) {
			attr.addFlashAttribute("erro", "Usuário não pode visualizar chamado!");
			return new ModelAndView("redirect:/chamado");
		}
		
		chamado.setDescricao(chamado.getDescricao().replace("\r\n", "<br />"));
		
		for (Interacao i : chamado.getInteracoes()) {
			i.setMensagem(i.getMensagem().replace("\r\n", "<br />"));
		}
		
		ModelAndView view = new ModelAndView("chamado/view");
		view.addObject("chamado", chamado);
		view.addObject("tipoInteracoes", TipoInteracao.values());
		
		return view;
	}
	
	@PostMapping(value = "{idChamado}/interagir", params = "form")
	public ModelAndView registraInteracao(@Valid Interacao interacao, BindingResult result, RedirectAttributes attr,@PathVariable("idChamado") Long idChamado, @AuthenticationPrincipal Usuario usuario) {
		
		Chamado chamado = chamadoRepository.findOne(idChamado);
		
		if(result.hasErrors()) {
			ModelAndView view = new ModelAndView("chamado/view");
			view.addObject("chamado", chamado);
			view.addObject("tipoInteracoes", TipoInteracao.values());
			
			return view;
		}		
		
		if(!usuarioPodeInteragir(chamado, usuario)) {
			attr.addFlashAttribute("erro", "Usuário não pode interagir no chamado!");
			return new ModelAndView("redirect:/chamado/detalhe/" + idChamado);
		}
		
		if(interacao.getTipoInteracao().equals(TipoInteracao.CANCELADO)) {
			chamado.setDataEncerramento(LocalDateTime.now());
			chamado.setStatus(StatusChamado.CANCELADO);	
		}
		
		if(interacao.getTipoInteracao().equals(TipoInteracao.CONCLUIDO)) {
			chamado.setDataEncerramento(LocalDateTime.now());
			chamado.setStatus(StatusChamado.CONCLUIDO);	
		}
		
		interacao.setUsuario(usuario);
		
		chamado.adicionaInteracao(interacao);
		
		chamadoRepository.save(chamado);
		
		return new ModelAndView("redirect:/chamado/detalhe/" + idChamado);
	}

	@GetMapping("{idChamado}/atender")
	public ModelAndView atenderChamado(@PathVariable("idChamado") Long idChamado, RedirectAttributes attr, @AuthenticationPrincipal Usuario usuario) {
		
		Chamado chamado = chamadoRepository.findOne(idChamado);
		
		if(chamado.getAtendente() != null) {
			attr.addFlashAttribute("erro", "Chamado já está sendo atendido.");
			
			return new ModelAndView("redirect:/chamado/detalhe/" + idChamado);
		}
		
		chamado.setAtendente(usuario);
		chamado.setStatus(StatusChamado.EM_ATENDIMENTO);
		chamadoRepository.save(chamado);
		
		attr.addFlashAttribute("message", "Chamado atualizado com sucesso.");
		
		return new ModelAndView("redirect:/chamado/detalhe/" + idChamado);
		
	}
	
	private boolean usuarioPodeInteragir(Chamado chamado, Usuario usuario) {
		if(chamado.getStatus().equals(StatusChamado.CANCELADO)) {
			return false;
		}
		
		if(chamado.getStatus().equals(StatusChamado.CONCLUIDO)) {
			return false;
		}
		
		if(usuario.getRoles().contains(new Role("ROLE_ADMIN"))) {
			return true;
		}
		
		if(chamado.getRequerente().equals(usuario)) {
			return true;
		}
		
		if(chamado.getAtendente() != null) {
			if(chamado.getAtendente().equals(usuario)) {
				return true;
			}
		}
		
		return false;
	}
	
	private boolean usuarioPodeAcessar(Chamado chamado, Usuario usuario) {
		if(usuario.getRoles().contains(new Role("ROLE_ADMIN")) || usuario.getRoles().contains(new Role("ROLE_TECNICO"))) {
			return true;
		}
		
		if(chamado.getRequerente().equals(usuario)) {
			return true;
		}
		
		return false;
	}
	
}
