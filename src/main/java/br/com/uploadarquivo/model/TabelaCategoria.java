package br.com.uploadarquivo.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "tabela_categoria")
public class TabelaCategoria {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	Long id;
	String codigo;
	String descricao;
	
	public TabelaCategoria() {
	}
	
	public TabelaCategoria(String codigo, String descricao) {
		this.codigo = codigo;
		this.descricao = descricao;
	}
	

	public String getCodigo() {
		return codigo;
	}
	
	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}
	
	public String getDescricao() {
		return descricao;
	}
	
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

}
