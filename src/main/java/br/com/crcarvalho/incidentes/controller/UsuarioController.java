package br.com.crcarvalho.incidentes.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import br.com.crcarvalho.incidentes.model.entity.Usuario;
import br.com.crcarvalho.incidentes.service.UsuarioService;

@Controller
@RequestMapping("/usuario")
public class UsuarioController {
	
	@Autowired
	private UsuarioService usuarioService;
	
	@GetMapping
	public ModelAndView index() {
		
		List<Usuario> usuarios = usuarioService.findAll();
		
		return new ModelAndView("usuario/list", "usuarios", usuarios);
	}
	
	@GetMapping("novo")
	public ModelAndView form(@ModelAttribute Usuario usuario) {
		
		return new ModelAndView("usuario/form");
	}
	
	@PostMapping(params = "form")
	public ModelAndView salvar(@Valid Usuario usuario, BindingResult result, RedirectAttributes attr) {
		
		if(result.hasErrors()) {
			return new ModelAndView("usuario/form");
		}
		
		usuarioService.save(usuario);
		
		attr.addFlashAttribute("message", "Usuário cadastrado com sucesso.");
		
		return new ModelAndView("redirect:/usuario");
	}
	
	@GetMapping("editar/{email}")
	public ModelAndView editar(@PathVariable("email") String email) {

		Usuario usuario = usuarioService.find(email);

		return new ModelAndView("usuario/form", "usuario", usuario);
	}
	
	@GetMapping("remover/{email}")
	public ModelAndView remover(@PathVariable("email") String email) {
		
		usuarioService.remove(usuarioService.find(email));
		
		return new ModelAndView("redirect:/usuario");
		
	}
	
}
