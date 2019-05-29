package br.com.uploadarquivo.util;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.opencsv.CSVWriter;

import br.com.uploadarquivo.model.TabelaCategoria;
import br.com.uploadarquivo.storage.local.ArquivoImportadoStorageLocal;

@Component
public class GeradorCSV {
	
	@Autowired
	ArquivoImportadoStorageLocal arquivoImportadoStorage;
	

	@SuppressWarnings("resource")
	public void gerarCsv(List<TabelaCategoria> linhas) throws IOException {

		List<String[]> linhasArray = new ArrayList<>();

		linhas.forEach(t -> linhasArray.add(new String[] { t.getCodigo(), t.getDescricao() }));
		
		File arq = new File(arquivoImportadoStorage.getAbsolutePath().toString(), "teste.csv");
		Writer writer = new BufferedWriter(new FileWriter(arq));
		CSVWriter csvWriter = new CSVWriter(writer);

		csvWriter.writeAll(linhasArray);

		csvWriter.flush();
		writer.close();

	}
}
