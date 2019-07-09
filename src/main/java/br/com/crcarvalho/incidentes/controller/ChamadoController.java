package br.com.crcarvalho.incidentes.controller;

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
	public ModelAndView chamadosDoUsuario(@AuthenticationPrincipal Usuario usuario) {
		
		return new ModelAndView("chamado/list", "chamados", chamadoRepository.findByRequerente(usuario));
	}
	
	@GetMapping("todos")
	public ModelAndView todosOsChamados() {
		return new ModelAndView("chamado/list", "chamados", chamadoRepository.findAll());
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
		
		interacao.setUsuario(usuario);
		
		chamado.adicionaInteracao(interacao);
		
		chamadoRepository.save(chamado);
		
		return new ModelAndView("redirect:/chamado/detalhe/" + idChamado);
	}

	@GetMapping("{idChamado}/atender")
	public ModelAndView atenderChamado(@PathVariable("idChamado") Long idChamado, RedirectAttributes attr, @AuthenticationPrincipal Usuario usuario) {
		
		Chamado chamado = chamadoRepository.findOne(idChamado);
		
		try {
			chamado.setAtendente(usuario);
			
			chamadoRepository.save(chamado);
			
		}catch (RuntimeException ex) {
			attr.addFlashAttribute("erro", ex.getMessage());
			
			return new ModelAndView("redirect:/chamado/detalhe/" + idChamado);
		}
		
		attr.addFlashAttribute("message", "Chamado atualizado com sucesso.");
		
		return new ModelAndView("redirect:/chamado/detalhe/" + idChamado);
		
	}
	
	private boolean usuarioPodeAcessar(Chamado chamado, Usuario usuario) {
		if(usuario.possuiRole("ROLE_ADMIN") || usuario.possuiRole("ROLE_TECNICO")) {
			return true;
		}
		
		if(chamado.getRequerente().equals(usuario)) {
			return true;
		}
		
		return false;
	}
	
}
