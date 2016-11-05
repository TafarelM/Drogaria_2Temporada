package br.com.drogaria.domain;

import java.math.BigDecimal;
import java.util.Date;

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
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name = "tbl_Vendas")
@NamedQueries({
	@NamedQuery(name = "Venda.listar", query = "SELECT venda FROM Venda venda"),
	@NamedQuery(name = "Venda.buscarPorId", query = "SELECT venda FROM Venda venda WHERE id = :id")
})
public class Venda {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id")
	private int id;
	
	@Temporal(value = TemporalType.TIMESTAMP)
	@Column(name = "horario", nullable = false)
	private Date horario;
	
	@Column(name = "valorTotal", precision = 7, scale = 2, nullable = false)
	private BigDecimal valorTotal;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "tbl_funcionarios_id", referencedColumnName = "id", nullable = false)
	private Funcionario funcionario;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Date getHorario() {
		return horario;
	}

	public void setHorario(Date horario) {
		this.horario = horario;
	}

	public BigDecimal getValorTotal() {
		return valorTotal;
	}

	public void setValorTotal(BigDecimal valorTotal) {
		this.valorTotal = valorTotal;
	}

	public Funcionario getFuncionario() {
		return funcionario;
	}

	public void setFuncionario(Funcionario funcionario) {
		this.funcionario = funcionario;
	}

	@Override
	public String toString() {
		return "Venda [id=" + id + ", horario=" + horario + ", valorTotal=" + valorTotal + ", funcionario="
				+ funcionario + "]";
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
		Venda other = (Venda) obj;
		if (id != other.id)
			return false;
		return true;
	}	
			
}
