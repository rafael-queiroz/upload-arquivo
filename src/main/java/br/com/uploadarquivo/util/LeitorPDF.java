package br.com.uploadarquivo.util;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.encryption.InvalidPasswordException;
import org.apache.pdfbox.text.PDFTextStripper;
import org.apache.pdfbox.text.PDFTextStripperByArea;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.uploadarquivo.model.TabelaCategoria;

@Component
public class LeitorPDF {

	@Autowired
	IntegerUtil integerUtil;
	
	
	public List<TabelaCategoria> lerPDF(File file) {
		try (PDDocument document = PDDocument.load(file)) {

			if (!document.isEncrypted()) {

				PDFTextStripperByArea stripper = new PDFTextStripperByArea();
				stripper.setSortByPosition(true);

				PDFTextStripper tStripper = new PDFTextStripper();

				String pdfFileInText = tStripper.getText(document);

				String lines[] = pdfFileInText.split("\\r?\\n");
				
				List<TabelaCategoria> linhas = new ArrayList<>();
				boolean exibir = false;

				for (String line : lines) {
					if (line.equals("Tabela de Categoria do Padrão TISS "))
						exibir = true;

					if (line.equals("Fonte: Elaborado pelos autores. "))
						exibir = false;

					if (exibir && !line.trim().equals("") && !line.trim().equals("91") && !line.trim().equals("92")
							&& !line.trim().equals("93") && !line.trim().equals("94") && !line.trim().equals("95")
							&& !line.trim().equals("Padrão TISS - Componente Organizacional – fevereiro de 2019")) {
						String linhaStr = line.substring(0, line.indexOf(" "));

						if (integerUtil.ehInteiro(linhaStr) || linhas.size() < 2) {
							linhas.add(new TabelaCategoria(line.substring(0, line.indexOf(" ")),
									line.substring(line.indexOf(" "))));
						} else {
							TabelaCategoria linha = linhas.get(linhas.size() - 1);
							linha.setDescricao(linha.getDescricao().concat(line));
						}
					}
				}
				return linhas;
			}
		} catch (InvalidPasswordException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
}
