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
import javax.validation.constraints.DecimalMax;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotEmpty;

@Entity
@Table(name = "tbl_produtos")
@NamedQueries({
	@NamedQuery(name = "Produto.listar", query = "SELECT produto FROM Produto produto"),
	@NamedQuery(name = "Produto.buscarPorId", query = "SELECT produto FROM Produto produto WHERE produto.id = :id")
})
public class Produto {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id")
	private int id;
	
	@NotEmpty(message = "O campo Descrição é obrigatorio.")
	@Size(min = 5, max = 50, message = "Tamanho inválido para o campo descrição (5 - 50).")
	@Column(name = "descricao", length = 50, nullable = false)
	private String descricao;
	
	@NotNull(message = "O campo preço é obrigatorio.")
	@DecimalMin(value = "0.00", message = "Informe um valor maior ou igual a zero para o campo preço.")
	@DecimalMax(value = "99999.99", message = "Informe um valor menor que 99999.99 para o campo preço.")
	@Column(name = "preco", precision = 7, scale = 2, nullable = false)
	private BigDecimal preco;
	
	@NotNull(message = "O campo quantidade é obrigatorio.")
	@Min(value = 0, message = "informe um valor maior ou igual a zero para o campo quantidade.")
	@Max(value = 9999, message = "Informe um valor menor que dez mil para o campo quantidade.")
	@Column(name = "quantidade", nullable = false)
	private int quantidade;
	
	@NotNull(message = "O campo fabricante é obrigatorio.")
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "tbl_fabricantes_id", referencedColumnName = "id", nullable = false)
	private Fabricante fabricante;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public BigDecimal getPreco() {
		return preco;
	}

	public void setPreco(BigDecimal preco) {
		this.preco = preco;
	}

	public int getQuantidade() {
		return quantidade;
	}

	public void setQuantidade(int quantidade) {
		this.quantidade = quantidade;
	}

	public Fabricante getFabricante() {
		return fabricante;
	}

	public void setFabricante(Fabricante fabricante) {
		this.fabricante = fabricante;
	}

	@Override
	public String toString() {
		return "Produto [id=" + id + ", descricao=" + descricao + ", preco=" + preco + ", quantidade=" + quantidade
				+ ", fabricante=" + fabricante + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + id;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Produto other = (Produto) obj;
		if (id != other.id)
			return false;
		return true;
	}	
	
}
