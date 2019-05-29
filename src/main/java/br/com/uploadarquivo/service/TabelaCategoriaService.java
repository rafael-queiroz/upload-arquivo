package br.com.uploadarquivo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import br.com.uploadarquivo.model.TabelaCategoria;
import br.com.uploadarquivo.repository.TabelaCategoriaRepository;

@Service
public class TabelaCategoriaService {
	
	@Autowired
	TabelaCategoriaRepository tabelaCategoriaRepository;
	
	public Page<TabelaCategoria> findPage(Integer page, Integer linesPerPage, String orderBy, String direction) {
		PageRequest pageRequest = new PageRequest(page, linesPerPage, Direction.valueOf(direction), orderBy);
		return tabelaCategoriaRepository.findAll(pageRequest);
	}

}
