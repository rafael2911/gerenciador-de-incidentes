package br.com.crcarvalho.incidentes.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import br.com.crcarvalho.incidentes.model.entity.Chamado;
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
	public ModelAndView index() {
		
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
}
