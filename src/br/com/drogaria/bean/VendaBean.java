package br.com.drogaria.bean;

import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import br.com.drogaria.dao.ProdutoDAO;
import br.com.drogaria.domain.Produto;
import br.com.drogaria.util.FacesUtil;

@ManagedBean
@ViewScoped
public class VendaBean {
	
	private List<Produto> listaProdutos;
	private List<Produto> listaProdutosFiltrados;
	
	public List<Produto> getListaProdutos() {
		return listaProdutos;
	}
	
	public void setListaProdutosFiltrados(List<Produto> listaProdutosFiltrados) {
		this.listaProdutosFiltrados = listaProdutosFiltrados;
	}
	
	public List<Produto> getListaProdutosFiltrados() {
		return listaProdutosFiltrados;
	}
	
	public void setListaProdutos(List<Produto> listaProdutos) {
		this.listaProdutos = listaProdutos;
	}
	
	public void carregarProdutos() {
		try {
			ProdutoDAO produtoDAO = new ProdutoDAO();
			listaProdutos = produtoDAO.listar();
		} catch (RuntimeException ex) {
			FacesUtil.adicionarMgsErro("Erro ao tentar listar os Produtos. Detalhes: "+ ex.getMessage());
		}
	}
}
