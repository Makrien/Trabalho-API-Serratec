package br.gov.serratec.grupo05api.model;


import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name="item_pedido")
public class ItemPedido {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private int quantidade;
	private Double precoVenda;
	private Double percentualDesconto;
	private Double valorBruto;
	private Double valorLiquido;
	@ManyToOne
    @JoinColumn(name = "produto_id")
	private Produto produto;
	@ManyToOne
    @JoinColumn(name = "pedido_id")
	private Pedido pedido;
	
	public ItemPedido() {
		super();
	}

	public ItemPedido(Long id, int quantidade, Double precoVenda, Double percentualDesconto, Double valorBruto,
			Double valorLiquido, Produto produto, Pedido pedido) {
		super();
		this.id = id;
		this.quantidade = quantidade;
		this.precoVenda = precoVenda;
		this.percentualDesconto = percentualDesconto;
		this.valorBruto = valorBruto;
		this.valorLiquido = valorLiquido;
		this.produto = produto;
		this.pedido = pedido;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public int getQuantidade() {
		return quantidade;
	}

	public void setQuantidade(int quantidade) {
		this.quantidade = quantidade;
	}

	public Double getPrecoVenda() {
		return precoVenda;
	}

	public void setPrecoVenda(Double precoVenda) {
		this.precoVenda = precoVenda;
	}

	public Double getPercentualDesconto() {
		return percentualDesconto;
	}

	public void setPercentualDesconto(Double percentualDesconto) {
		this.percentualDesconto = percentualDesconto;
	}

	public Double getValorBruto() {
		return valorBruto;
	}

	public void setValorBruto(Double valorUnitario) {
		this.valorBruto = valorUnitario * getQuantidade();
	}

	public Double getValorLiquido() {
		return valorLiquido;
	}

	public void setValorLiquido(Double valorLiquido) {
		this.valorLiquido = getValorBruto() * ( 1 - (valorLiquido/100));
	}

	public Produto getProduto() {
		return produto;
	}

	public void setProduto(Produto produto) {
		this.produto = produto;
	}

	public Pedido getPedido() {
		return pedido;
	}

	public void setPedido(Pedido pedido) {
		this.pedido = pedido;
	}	

}
