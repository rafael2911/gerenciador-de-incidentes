package br.com.crcarvalho.incidentes.controller;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

@ControllerAdvice
public class ExceptionHandlerController {
	
	@ExceptionHandler(Exception.class)
	public ModelAndView tratarErrosNaAplicacao(Exception ex) {

		ex.printStackTrace();
		
		return new ModelAndView("error/generico", "exception", ex);
	}

}
