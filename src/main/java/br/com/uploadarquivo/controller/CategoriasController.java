package br.com.uploadarquivo.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import br.com.uploadarquivo.controller.page.PageWrapper;
import br.com.uploadarquivo.model.TabelaCategoria;
import br.com.uploadarquivo.service.TabelaCategoriaService;

@Controller
@RequestMapping("/categorias")
public class CategoriasController {
	
	@Autowired
	TabelaCategoriaService tabelaCategoriaService;
	
	@GetMapping
	public ModelAndView consultar(@RequestParam(value="page", defaultValue="0") Integer page, 
			@RequestParam(value="linesPerPage", defaultValue="10") Integer linesPerPage, 
			@RequestParam(value="orderBy", defaultValue="id") String orderBy, 
			@RequestParam(value="direction", defaultValue="ASC") String direction,
			HttpServletRequest httpServletRequest) {
		ModelAndView mv = new ModelAndView("ListaTabelaCategoria");
		
		PageWrapper<TabelaCategoria> paginaWrapper = new PageWrapper<>(tabelaCategoriaService.findPage(page, linesPerPage, orderBy, direction) , httpServletRequest);
		mv.addObject("pagina", paginaWrapper);
		
		return mv;
	}

}
