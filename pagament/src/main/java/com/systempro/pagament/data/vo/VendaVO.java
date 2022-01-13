package com.systempro.pagament.data.vo;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.hateoas.RepresentationModel;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.systempro.pagament.entity.Venda;

@JsonPropertyOrder({ "id", "instante", "produtos", "valorTotal" })
public class VendaVO  extends RepresentationModel<VendaVO> implements Serializable {
	private static final long serialVersionUID = 1L;
	
	
	@JsonProperty("id")
	private Long id;
	
	@JsonProperty("produtos")
	private List<ProdutoVendaVO> produtos;
	
	@JsonProperty("instante")
	private LocalDate instante;
	
	@JsonProperty("valorTotal")
	private Double valorTotal;

	public static VendaVO create(Venda venda) {
		return new ModelMapper().map(venda, VendaVO.class);
	}
	
	public VendaVO() {
	}

	public VendaVO(Long id, LocalDate instante, Double valorTotal) {
		this.id = id;
		this.instante = instante;
		this.valorTotal = valorTotal;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public List<ProdutoVendaVO> getProdutos() {
		return produtos;
	}

	public void setProdutos(List<ProdutoVendaVO> produtos) {
		this.produtos = produtos;
	}

	public LocalDate getInstante() {
		return instante;
	}

	public void setInstante(LocalDate instante) {
		this.instante = instante;
	}

	public Double getValorTotal() {
		return valorTotal;
	}

	public void setValorTotal(Double valorTotal) {
		this.valorTotal = valorTotal;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!super.equals(obj))
			return false;
		if (getClass() != obj.getClass())
			return false;
		VendaVO other = (VendaVO) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder
		.append("VendaVO [id=")
		.append(id)
		.append(", produtos=")
		.append(produtos)
		.append(", instante=")
		.append(instante)
		.append(", valorTotal=")
		.append(valorTotal)
		.append("]");
		return builder.toString();
	}
}
