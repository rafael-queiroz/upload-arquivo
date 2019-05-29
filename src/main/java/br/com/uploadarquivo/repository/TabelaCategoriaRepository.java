package br.com.uploadarquivo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.uploadarquivo.model.TabelaCategoria;

@Repository
public interface TabelaCategoriaRepository extends JpaRepository<TabelaCategoria, Long>{

}
