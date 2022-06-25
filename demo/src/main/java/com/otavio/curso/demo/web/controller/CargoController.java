package com.otavio.curso.demo.web.controller;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.otavio.curso.demo.domain.Cargo;
import com.otavio.curso.demo.domain.Departamento;
import com.otavio.curso.demo.service.CargoService;
import com.otavio.curso.demo.service.DepartamentoService;
import com.otavio.curso.demo.util.PaginacaoUtil;

@Controller
@RequestMapping("/cargos")
public class CargoController {
	
	@Autowired
	CargoService cargoService;
	
	@Autowired
	DepartamentoService departamentoService;
	
	@GetMapping("/cadastrar")
	public String cadastrar(Cargo cargo) {
		
		return "cargo/cadastro";
	}
	
	@GetMapping("/listar")
	public String listar(ModelMap model,
			@RequestParam("page") Optional<Integer> page,
			@RequestParam("dir") Optional<String> dir) {
		
		int paginalAtual = page.orElse(1);		// padrão da consulta é 1
		String ordem = dir.orElse("asc");		// padrão da consulta é asc
		
		PaginacaoUtil<Cargo> pageCargo = cargoService.buscaPorPagina(paginalAtual, ordem);
		
		model.addAttribute("pageCargo", pageCargo);
		
		return "cargo/lista";
	}
	
	@GetMapping("/excluir/{id}")
	public String excluir(@PathVariable("id") Long id, RedirectAttributes attr) {
		if (cargoService.cargoTemFuncionarios(id)) {
			attr.addFlashAttribute("fail", "Cargo não excluido. Tem funcionário(s) vinculado(s).");
		} else {
			cargoService.excluir(id);
			attr.addFlashAttribute("success", "Cargo excluido com sucesso.");
		}
		return "redirect:/cargos/listar";
	}
	
	@PostMapping("/salvar")
	public String salvar(@Valid Cargo cargo, BindingResult result, RedirectAttributes attr) {		// a anotação @Valid informa a necessidade da validação no atributo à frente. Já o atributo BindingResult verifica se houve algum problema referente às validações. 
		
		if(result.hasErrors()) {		// método que verifica se tem erro.
			return "cargo/cadastro";
		}
		
		cargoService.salvar(cargo);
		attr.addFlashAttribute("success", "Cargo inserido com sucesso.");
		return "redirect:/cargos/cadastrar";
	}
	
	@GetMapping("/editar/{id}")
	public String preEditar(@PathVariable("id") Long id, ModelMap model) {
		model.addAttribute("cargo", cargoService.buscarPorId(id));
		return "cargo/cadastro";
	}
	
	
	@PostMapping("editar")
	public String editar (@Valid Cargo cargo,BindingResult result, RedirectAttributes attr) {
		
		if(result.hasErrors()) {
			return "cargo/cadastro";
		}
		
		cargoService.editar(cargo);
		attr.addFlashAttribute("success", "Registro atualizado com sucesso.");
		return "redirect:/cargos/cadastrar";
		
	}
	
	@ModelAttribute("departamentos")		// método responsável em enviar para a página a lista de departamentos para a variável departamentos, podendo assim listar no combobox
	public List<Departamento> listaDeDepartamentos(){
		return departamentoService.buscarTodos();
	}
	
	

}
