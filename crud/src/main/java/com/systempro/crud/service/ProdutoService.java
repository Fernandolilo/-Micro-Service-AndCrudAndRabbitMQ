package com.systempro.crud.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.systempro.crud.data.vo.ProdutoVO;
import com.systempro.crud.entity.Produto;
import com.systempro.crud.exceptions.NotFoundException;
import com.systempro.crud.message.ProdutoSendMessage;
import com.systempro.crud.repository.ProdutoRepository;


@Service
public class ProdutoService {

	private final ProdutoRepository repository;
	private final ProdutoSendMessage produtoSenMessage;

	
	@Autowired
	public ProdutoService(ProdutoRepository repository,ProdutoSendMessage produtoSenMessage ) {
		this.repository = repository;
		this.produtoSenMessage = produtoSenMessage;
	
	}

	public ProdutoVO create(ProdutoVO produtoVO) {
		ProdutoVO prodVoReturn = ProdutoVO.create(repository.save(Produto.create(produtoVO)));
		produtoSenMessage.sendMessage(prodVoReturn);
		return prodVoReturn;
	}

	public Page<ProdutoVO> findAll(Pageable pageable) {
		var page = repository.findAll(pageable);
		return page.map(this::convertToProdutoVO);
	}

	private ProdutoVO convertToProdutoVO(Produto produto) {
		return ProdutoVO.create(produto);
	}

	public ProdutoVO findById(Long id) {
		var entity = repository.findById(id).orElseThrow(() -> new NotFoundException("No records found for this ID "));
		return ProdutoVO.create(entity);
	}

	public ProdutoVO update(ProdutoVO produtoVO) {
		final Optional<Produto> optionalProduto = repository.findById(produtoVO.getId());

		if (!optionalProduto.isPresent()) {
			throw new NotFoundException("No records found for this ID ");
		}
		return ProdutoVO.create(repository.save(Produto.create(produtoVO)));
	}

	public void delete(Long id) {
		var entity = repository.findById(id).orElseThrow(() -> new NotFoundException("No records found for this ID "));
		repository.delete(entity);
	}

}
