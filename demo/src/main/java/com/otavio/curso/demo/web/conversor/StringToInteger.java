package com.otavio.curso.demo.web.conversor;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class StringToInteger implements Converter<String, Integer> {

	@Override
	public Integer convert(String text) {

		text=text.trim();		// método que remove espaços em brancon o começo ou final da string
		
		if(text.matches("[0-9]+")) {		// método pra verificar se só possuí números na string text
		
			return Integer.valueOf(text);		// caso tenha apenas números, retorna o integer
			
		} 
		return null;		// caso não seja apenas números, vai retornar null, que cairá na validação e exibiremos a mensagem.
	}

	
	
}
