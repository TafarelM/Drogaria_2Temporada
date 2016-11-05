package br.com.drogaria.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotEmpty;

@Entity
@Table(name = "tbl_fabricantes")
@NamedQueries({
	@NamedQuery(name = "Fabricante.listar", query = "SELECT fabricante FROM Fabricante fabricante"),
	@NamedQuery(name = "Fabricante.buscarPorId", query = "SELECT fabricante FROM Fabricante fabricante WHERE fabricante.id = :id")
})
public class Fabricante {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id")
	private int id;

	@NotEmpty(message = "O campo Descrição é obrigatório.")
	@Size(min = 5, max = 50, message = "Tamanho inválido para o campo descrição (5 - 50)")
	@Column(name = "descricao", length = 50, nullable = false)
	private String descricao;

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

	@Override
	public String toString() {
		return "Fabricante [id=" + id + ", descricao=" + descricao + "]";
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
		Fabricante other = (Fabricante) obj;
		if (id != other.id)
			return false;
		return true;
	}	

}
