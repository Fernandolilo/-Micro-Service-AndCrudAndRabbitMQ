package com.systempro.pagament.controller;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.web.PagedResourcesAssembler;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.PagedModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.systempro.pagament.data.vo.VendaVO;
import com.systempro.pagament.services.VendaService;


@RestController
@RequestMapping(value ="/venda")
public class VendaController {
	
	private final VendaService produtoService;
	private final PagedResourcesAssembler<VendaVO> assebler;
	
	@Autowired
	public VendaController(VendaService produtoService, PagedResourcesAssembler<VendaVO> assebler) {
		this.produtoService = produtoService;
		this.assebler = assebler;
	}
	
	@GetMapping(value ="/{id}", produces = {"application/json", "application/xml", "application/yaml"})
	public VendaVO findById(@PathVariable ("id") Long id) {
		VendaVO produtoVO = produtoService.findById(id);
		produtoVO.add(linkTo(methodOn(VendaController.class).findById(id)).withSelfRel());
		return produtoVO;
	}
	
	@GetMapping(produces = {"application/json", "application/xml", "application/yaml"})
	public ResponseEntity<?> findAll(@RequestParam(value ="page", defaultValue = "0")int page,
			@RequestParam(value = "limit", defaultValue = "12") int limit,
			@RequestParam(value = "derect", defaultValue = "0") String direct){
		
		var sorDirection = "desc".equals(direct)? Direction.DESC: Direction.ASC;
		
		Pageable pageable = PageRequest.of(page, limit, Sort.by(sorDirection, "instante"));
		Page<VendaVO> produtos = produtoService.findAll(pageable);
		produtos.stream().forEach(p -> p.add(linkTo(methodOn(VendaController.class).findById(p.getId())).withSelfRel()));
		PagedModel<EntityModel<VendaVO>> pageModel = assebler.toModel(produtos);
		return new ResponseEntity<>(pageModel, HttpStatus.OK);
	}
	
	@PostMapping(produces = {"application/json", "application/xml", "application/yaml"},
			consumes = {"application/json", "application/xml", "application/yaml"})
	public VendaVO create(@RequestBody VendaVO produtoVO) {
		VendaVO prodVO = produtoService.create(produtoVO);
		prodVO.add(linkTo(methodOn(VendaController.class).findById(prodVO.getId())).withSelfRel());
		return prodVO;
	}


}
