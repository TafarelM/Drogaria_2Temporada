package br.com.drogaria.domain;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

@Entity
@Table(name = "tbl_ItensVenda")
@NamedQueries({
	@NamedQuery(name = "ItemVenda.listar", query = "SELECT itemVenda FROM ItemVenda itemVenda"),
	@NamedQuery(name = "ItemVenda.buscarPorId", query = "SELECT itemVenda FROM ItemVenda itemVenda WHERE id = :id")
})
public class ItemVenda {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id")
	private int id;
	
	@Column(name = "valor_parcial", precision = 7, scale = 2, nullable = false)
	private BigDecimal valor;
	
	@Column(name = "quantidade", nullable = false)
	private int quantidade;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "tbl_vendas_id", referencedColumnName = "id", nullable = false)
	private Venda venda;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "tbl_produtos_id", referencedColumnName = "id", nullable = false)
	private Produto produto;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public BigDecimal getValor() {
		return valor;
	}

	public void setValor(BigDecimal valor) {
		this.valor = valor;
	}

	public int getQuantidade() {
		return quantidade;
	}

	public void setQuantidade(int quantidade) {
		this.quantidade = quantidade;
	}

	public Venda getVenda() {
		return venda;
	}

	public void setVenda(Venda venda) {
		this.venda = venda;
	}

	public Produto getProduto() {
		return produto;
	}

	public void setProduto(Produto produto) {
		this.produto = produto;
	}

	@Override
	public String toString() {
		return "ItemVenda [id=" + id + ", valor=" + valor + ", quantidade=" + quantidade + ", venda=" + venda
				+ ", produto=" + produto + "]";
	}	
	
}
