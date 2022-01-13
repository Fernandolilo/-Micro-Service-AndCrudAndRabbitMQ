package com.systempro.pagament.config;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

import com.systempro.pagament.data.vo.ProdutoVO;
import com.systempro.pagament.entity.Produto;
import com.systempro.pagament.repository.ProdutoRepository;

@Component
public class ProdutoReceiveMessege {
	
	private final ProdutoRepository produtoRepository;

	@Autowired
	public ProdutoReceiveMessege(ProdutoRepository produtoRepository) {
		this.produtoRepository = produtoRepository;
	}

	@RabbitListener(queues = {"crud.produto.queue"})
	public void receive(@Payload ProdutoVO produtoVO) {
		produtoRepository.save(Produto.create(produtoVO));
	}
}
