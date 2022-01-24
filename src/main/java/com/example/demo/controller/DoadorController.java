package com.example.demo.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DateUtil;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.filter.CommonsRequestLoggingFilter;

import com.example.demo.model.Doador;
import com.example.demo.repository.DoadorRepository;

import lombok.AllArgsConstructor;

@RestController
@AllArgsConstructor
public class DoadorController {
	@Bean
	public CommonsRequestLoggingFilter requestLoggingFilter() {
	    CommonsRequestLoggingFilter loggingFilter = new CommonsRequestLoggingFilter();
	    loggingFilter.setIncludeClientInfo(true);
	    loggingFilter.setIncludeQueryString(true);
	    loggingFilter.setIncludePayload(true);
	    loggingFilter.setIncludeHeaders(false);
	    return loggingFilter;
	}
	
	DoadorRepository repository;

	@GetMapping("/doador")
	public List<Doador> list() {
		return repository.findAll();
	}

	@PostMapping("/doador")
	public Doador register(@RequestBody Doador doador) {
		System.out.println(doador);
		return repository.save(doador);
	}

	@GetMapping("/readexcel")
	public String readExcel() {
		try {
			FileInputStream file;
			file = new FileInputStream(new File("C:/Users/osrovere/Documents/doadoresNOVO.xlsx"));
			Workbook workbook = new XSSFWorkbook(file);

			Sheet sheet = workbook.getSheetAt(0);

			Map<Integer, List<String>> data = new HashMap<>();
			int i = 0;
			for (Row row : sheet) {
				if(i != 0) {
				data.put(i, new ArrayList<String>());
				for (Cell cell : row) {
					switch (cell.getCellType()) {
					case STRING:
						data.get(Integer.valueOf(i)).add(cell.getRichStringCellValue().getString());
						break;
					case NUMERIC:
						if (DateUtil.isCellDateFormatted(cell)) {
							String dataNasc = new SimpleDateFormat("yyyy-MM-dd").format(cell.getDateCellValue());
							data.get(i).add(dataNasc);
						} else {
							int numeric = (int)cell.getNumericCellValue();
							data.get(i).add(String.valueOf(numeric));
						}
						break;
					case BOOLEAN:
						data.get(i).add(cell.getBooleanCellValue() + "");
						break;
					case FORMULA:
						data.get(i).add(cell.getCellFormula() + "");
						break;
					default:
						data.get(Integer.valueOf(i)).add(" ");
					}
				}
			}
				i++;
			}
			workbook.close();
			for (Map.Entry<Integer, List<String>> entry : data.entrySet()) {
				Doador doador = new Doador();
			    List<String> list = entry.getValue();
			    doador.setCpf(list.get(0));
			    doador.setNome(list.get(1));
			    doador.setData_nasc(list.get(2));
			    doador.setEmail(list.get(3));
			    doador.setTelefone(list.get(4));
			    doador.setCep(list.get(5));
			    doador.setLogradouro(list.get(6));
			    doador.setComplemento(list.get(7));
			    doador.setBairro(list.get(8));
			    doador.setCidade(list.get(9));
			    doador.setUf(list.get(10));
			    doador.setNumero(list.get(11));
			    doador.setSenha(list.get(12));
			    repository.save(doador);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}

		return "Sucesso";
	}
}
