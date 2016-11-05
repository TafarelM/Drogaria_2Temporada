package br.com.drogaria.bean;

import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import br.com.drogaria.dao.FabricanteDAO;
import br.com.drogaria.dao.ProdutoDAO;
import br.com.drogaria.domain.Fabricante;
import br.com.drogaria.domain.Produto;
import br.com.drogaria.util.FacesUtil;

@ManagedBean
@ViewScoped
public class ProdutoBean {
	
	private Produto produtoMB;
	private List<Produto> listaProdutos;
	private List<Produto> listaProdutosFiltrados;	
	private String acao;
	private int id;
	private List<Fabricante> listaFabricates;

	public Produto getProdutoMB() {
		return produtoMB;
	}

	public void setProdutoMB(Produto produtoMB) {
		this.produtoMB = produtoMB;
	}

	public List<Produto> getListaProdutos() {
		return listaProdutos;
	}

	public void setListaProdutos(List<Produto> listaProdutos) {
		this.listaProdutos = listaProdutos;
	}

	public List<Produto> getListaProdutosFiltrados() {
		return listaProdutosFiltrados;
	}

	public void setListaProdutosFiltrados(List<Produto> listaProdutosFiltrados) {
		this.listaProdutosFiltrados = listaProdutosFiltrados;
	}

	public String getAcao() {
		return acao;
	}

	public void setAcao(String acao) {
		this.acao = acao;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
	
	public List<Fabricante> getListaFabricates() {
		return listaFabricates;
	}
	
	public void setListaFabricates(List<Fabricante> listaFabricates) {
		this.listaFabricates = listaFabricates;
	}

	public void novo() {
		produtoMB = new Produto();
	}

	public void salvar() {
		try {
			ProdutoDAO produtoDAO = new ProdutoDAO();
			produtoDAO.salvar(produtoMB);

			produtoMB = new Produto();

			FacesUtil.adicionarMgsInfo("Produto salvo com sucesso.");
		} catch (RuntimeException ex) {
			FacesUtil.adicionarMgsErro("Erro ao tentar salvar o Produto. Detalhes: " + ex.getMessage());
		}
	}

	public void carregarPesquisa() {
		try {
			ProdutoDAO produtoDAO = new ProdutoDAO();
			listaProdutos = produtoDAO.listar();
		} catch (RuntimeException ex) {
			FacesUtil.adicionarMgsErro("Erro ao tentar listar os Produtos. Detalhes: "+ ex.getMessage());
		}
	}
	
	public void carregarCadastro(){
		try {
			if(id != 0){
				ProdutoDAO produtoDAO = new ProdutoDAO();
				produtoMB = produtoDAO.buscarPorId(id);
			}else{
				produtoMB = new Produto();
			}
			
			FabricanteDAO fabricanteDAO = new FabricanteDAO();
			listaFabricates = fabricanteDAO.listar();
		} catch (RuntimeException ex) {
			FacesUtil.adicionarMgsErro("Erro ao tentar obter os dados do Produto. Detalhes: "+ ex.getMessage());
		}
	}
	
	public void excluir(){
		try {
			ProdutoDAO produtoDAO = new ProdutoDAO();
			produtoDAO.excluir(produtoMB);
			
			FacesUtil.adicionarMgsInfo("Produto Excluido com sucesso.");
		} catch (RuntimeException ex) {
			FacesUtil.adicionarMgsErro("Erro ao tentar excluir o Produto. Detalhes: "+ ex.getMessage());
		}
	}
	
	public void editar(){
		try {
			ProdutoDAO produtoDAO = new ProdutoDAO();
			produtoDAO.editar(produtoMB);
			
			FacesUtil.adicionarMgsInfo("Produto editado com sucesso.");
		} catch (RuntimeException ex) {
			FacesUtil.adicionarMgsErro("Erro ao tentar editar os dados do Produto. Detalhes: "+ ex.getMessage());
		}
	}

}
