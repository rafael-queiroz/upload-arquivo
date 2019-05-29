package br.com.uploadarquivo.thymeleaf;

import java.util.HashSet;
import java.util.Set;

import org.springframework.stereotype.Component;
import org.thymeleaf.dialect.AbstractProcessorDialect;
import org.thymeleaf.processor.IProcessor;
import org.thymeleaf.standard.StandardDialect;

import br.com.uploadarquivo.thymeleaf.processor.PaginationElementTagProcessor;



@Component
public class DialectUpload extends AbstractProcessorDialect {

	public DialectUpload() {
		super("DialectUpload", "upload-dialect", StandardDialect.PROCESSOR_PRECEDENCE);
	}
	
	@Override
	public Set<IProcessor> getProcessors(String dialectPrefix) {
		final Set<IProcessor> processadores = new HashSet<>();
		processadores.add(new PaginationElementTagProcessor(dialectPrefix));
		return processadores;
	}

}
