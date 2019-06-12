package br.com.crcarvalho.incidentes.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

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
		
		return new ModelAndView("usuario/index", "usuarios", usuarios);
	}
	
}
