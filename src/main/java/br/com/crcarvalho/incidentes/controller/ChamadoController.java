package br.com.crcarvalho.incidentes.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import br.com.crcarvalho.incidentes.model.repository.ChamadoRepository;

@Controller
@RequestMapping("chamado")
public class ChamadoController {
	
	@Autowired
	private ChamadoRepository chamadoRepository;
	
	@GetMapping
	public ModelAndView index() {
		
		return new ModelAndView("chamado/list", "chamados", chamadoRepository.findAll());
	}
	
}
