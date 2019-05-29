package br.com.uploadarquivo.controller;

import java.io.File;
import java.io.IOException;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.async.DeferredResult;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import br.com.uploadarquivo.dto.ArquivoImportadoDTO;
import br.com.uploadarquivo.model.ArquivoImportado;
import br.com.uploadarquivo.model.TabelaCategoria;
import br.com.uploadarquivo.repository.TabelaCategoriaRepository;
import br.com.uploadarquivo.storage.ArquivoImportadoStorageRunnable;
import br.com.uploadarquivo.storage.local.ArquivoImportadoStorageLocal;
import br.com.uploadarquivo.util.GeradorCSV;
import br.com.uploadarquivo.util.LeitorPDF;


@RestController
@RequestMapping("/importacao-arquivo")
public class ImportacaoArquivoController {
	
	@Autowired
	ArquivoImportadoStorageLocal arquivoImportadoStorage;
	
	@Autowired
	LeitorPDF leitorPDF;
	
	@Autowired
	GeradorCSV geradorCSV;
	
	@Autowired
	TabelaCategoriaRepository tabelaCategoriaRepository;
	
	
	@GetMapping("/importar")
	public ModelAndView prepararImportar(ArquivoImportado arquivoImportado) {
		ModelAndView mv = new ModelAndView("ImportacaoArquivo");
		return mv;
	}
	
	
	@PostMapping("/importar")
	public DeferredResult<ArquivoImportadoDTO> importar(@RequestParam("files[]") MultipartFile[] files) {
		DeferredResult<ArquivoImportadoDTO> resultado = new DeferredResult<>();

		Thread thread = new Thread(new ArquivoImportadoStorageRunnable(files, resultado, arquivoImportadoStorage));
		thread.start();

		return resultado;
	}
	
	@PostMapping(value = { "/salvar", "{\\d+}" })
	public ModelAndView salvar(@Valid ArquivoImportado arquivoImportado, BindingResult result, Model model, RedirectAttributes attributes) {
		if (result.hasErrors()) {
			return prepararImportar(arquivoImportado);
		}
		
		File file = arquivoImportadoStorage.recarregarArquivoImportado(arquivoImportado.getNomeArquivo());
		
		try {
			List<TabelaCategoria> lista = leitorPDF.lerPDF(file);
			geradorCSV.gerarCsv(lista);
			lista.remove(0); // retirando cabecalho
			lista.remove(0); // retirando cabecalho
			tabelaCategoriaRepository.deleteAll();
			lista.forEach(tc -> tabelaCategoriaRepository.save(tc));
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return new ModelAndView("redirect:/categorias");
	}

}
