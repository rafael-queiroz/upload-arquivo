package br.com.uploadarquivo.storage;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

public interface ArquivoImportadoStorage {
	
	public List<String> salvar(MultipartFile[] files);
	
}
