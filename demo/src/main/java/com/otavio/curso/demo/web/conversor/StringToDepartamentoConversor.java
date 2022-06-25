package com.otavio.curso.demo.web.conversor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import com.otavio.curso.demo.domain.Departamento;
import com.otavio.curso.demo.service.DepartamentoService;


@Component
public class StringToDepartamentoConversor implements Converter<String, Departamento> {

	@Autowired
	private DepartamentoService departamentoService;
	
	@Override
	public Departamento convert(String text) {
		if (text.isEmpty()) {
			return null;
		}
		Long id = Long.valueOf(text);
		return departamentoService.buscarPorId(id);
	}
	
}
