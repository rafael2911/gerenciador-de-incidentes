package br.com.crcarvalho.incidentes.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

import br.com.crcarvalho.incidentes.model.entity.Chamado;
import br.com.crcarvalho.incidentes.model.entity.Usuario;
import br.com.crcarvalho.incidentes.model.repository.ChamadoRepository;

@Controller
public class HomeController {
	
	@Autowired
	private ChamadoRepository chamadoRepository;
	
	@GetMapping("/")
	public ModelAndView index(@AuthenticationPrincipal Usuario usuario) {
		
		List<Chamado> chamados = chamadoRepository.findByRequerente(usuario);
		
		System.out.println(chamados);
		
		return new ModelAndView("chamado/list", "chamados", chamados);
	}
	
}
