package com.systempro.pagament.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.systempro.pagament.data.vo.VendaVO;
import com.systempro.pagament.entity.ProdutoVenda;
import com.systempro.pagament.entity.Venda;
import com.systempro.pagament.exceptions.NotFoundException;
import com.systempro.pagament.repository.ProdutoVendaRepository;
import com.systempro.pagament.repository.VendaRepository;

@Service
public class VendaService {

private final VendaRepository repository;
private final ProdutoVendaRepository produtoVendaRepository;
	
	@Autowired
	public VendaService(VendaRepository repository, ProdutoVendaRepository produtoVendaRepository) {
		this.repository = repository;
		this.produtoVendaRepository = produtoVendaRepository;
	}
		
	public VendaVO create(VendaVO vendaVO) {	
		Venda venda = repository.save(Venda.create(vendaVO));
		List<ProdutoVenda> produtosSalvos = new ArrayList<>();
		vendaVO.getProdutos().forEach(
				p ->{ ProdutoVenda pv = ProdutoVenda.create(p);
				pv.setVenda(venda);
				produtosSalvos.add(produtoVendaRepository.save(pv));
				});
		venda.setProdutos(produtosSalvos);
		return  VendaVO.create(venda);
	}
	
	public Page<VendaVO> findAll(Pageable pageable){
		var page = repository.findAll(pageable);
		return page.map(this::convertToVendaVO);
	}
	private VendaVO convertToVendaVO(Venda venda) {
		return VendaVO.create(venda);
	}
	
	public VendaVO findById(Long id) {
		var entity = repository.findById(id)
				.orElseThrow(() -> new NotFoundException("No records found for this ID "));
		return VendaVO.create(entity);
	}
	
}
