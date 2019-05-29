package br.com.uploadarquivo.model;

import java.io.Serializable;
import java.util.Date;

public class ArquivoImportado implements Serializable {
	
	static final long serialVersionUID = -1702020379527059728L;
	
	String nomeArquivo;
	String nomeArquivoOriginal;
	String contentTypeArquivo;
	Date dataImportacao;
	Date dataCompetencia;
	Long quantidadeRegistros;
	Boolean erro = false;
	
	
	public ArquivoImportado() {

	}



	//GETTERS E SETTERS
	public String getNomeArquivo() {
		return nomeArquivo;
	}

	public void setNomeArquivo(String nomeArquivo) {
		this.nomeArquivo = nomeArquivo;
	}
	
	public String getNomeArquivoOriginal() {
		return nomeArquivoOriginal;
	}
	
	public void setNomeArquivoOriginal(String nomeArquivoOriginal) {
		this.nomeArquivoOriginal = nomeArquivoOriginal;
	}

	public String getContentTypeArquivo() {
		return contentTypeArquivo;
	}

	public void setContentTypeArquivo(String contentTypeArquivo) {
		this.contentTypeArquivo = contentTypeArquivo;
	}

	public Date getDataImportacao() {
		return dataImportacao;
	}

	public void setDataImportacao(Date dataImportacao) {
		this.dataImportacao = dataImportacao;
	}
	
	public Date getDataCompetencia() {
		return dataCompetencia;
	}
	
	public void setDataCompetencia(Date dataCompetencia) {
		this.dataCompetencia = dataCompetencia;
	}
	
	public Long getQuantidadeRegistros() {
		return quantidadeRegistros;
	}
	
	public void setQuantidadeRegistros(Long quantidadeRegistros) {
		this.quantidadeRegistros = quantidadeRegistros;
	}
	
	public Boolean getErro() {
		return erro;
	}
	
	public void setErro(Boolean erro) {
		this.erro = erro;
	}
	
}
