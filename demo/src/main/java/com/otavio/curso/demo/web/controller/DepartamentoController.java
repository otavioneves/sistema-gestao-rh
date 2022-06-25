package com.otavio.curso.demo.web.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.otavio.curso.demo.domain.Departamento;
import com.otavio.curso.demo.service.DepartamentoService;

@Controller
@RequestMapping("/departamentos")
public class DepartamentoController {

	@Autowired
	private DepartamentoService departamentoService;

	@GetMapping("/cadastrar")
	public String cadastrar(Departamento departamento) {

		return "departamento/cadastro";
	}

	@GetMapping("/listar")
	public String listar(ModelMap model) {
		model.addAttribute("departamentos", departamentoService.buscarTodos());
		return "departamento/lista";
	}

	@PostMapping("/salvar")
	public String salvar(@Valid Departamento departamento, BindingResult result, RedirectAttributes attr) {
		
		if(result.hasErrors()) {		// método que verifica se tem erro.
			return "departamento/cadastro";
		}
		
		departamentoService.salvar(departamento);
		attr.addFlashAttribute("success", "Departamento inserido com sucesso.");
		return "redirect:/departamentos/cadastrar";
	}

	@GetMapping("/editar/{id}") // receberá na URL o ID
	public String preEditar(@PathVariable("id") Long id, ModelMap model) { // a anotação PahtVariable faz a leitura do
																			// recebido na URL.
		model.addAttribute("departamento", departamentoService.buscarPorId(id));
		return "departamento/cadastro";
	}

	@PostMapping("/editar")
	public String editar(@Valid Departamento departamento, BindingResult result, RedirectAttributes attr) {
		
		if(result.hasErrors()) {		// método que verifica se tem erro.
			return "departamento/cadastro";
		}
		
		departamentoService.editar(departamento);
		attr.addFlashAttribute("success", "Departamento editado com sucesso.");
		return "redirect:/departamentos/cadastrar";
	}

	@GetMapping("excluir/{id}")
	public String excluir(@PathVariable("id") Long id, ModelMap model) {
		
		if (departamentoService.departamentoTemCargos(id)) {
			model.addAttribute("fail", "Departamento não removido. Possui cargo(s) vinculados.");
		} else {
			departamentoService.excluir(id);
			model.addAttribute("success", "Departamento excluído com sucesso.");
		}
		
		return listar(model);		// chama o método listar
	}
	
}
