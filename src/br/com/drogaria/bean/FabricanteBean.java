package br.com.drogaria.bean;

import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import br.com.drogaria.dao.FabricanteDAO;
import br.com.drogaria.domain.Fabricante;
import br.com.drogaria.util.FacesUtil;

@ManagedBean
@ViewScoped
public class FabricanteBean {

	private Fabricante fabricanteMB;
	private List<Fabricante> listaFabricantes;
	private List<Fabricante> listaFabricantesFiltrados;

	public Fabricante getFabricanteMB() {
		if (fabricanteMB == null) {
			fabricanteMB = new Fabricante();
		}
		return fabricanteMB;
	}

	public void setFabricanteMB(Fabricante fabricanteMB) {
		this.fabricanteMB = fabricanteMB;
	}

	public List<Fabricante> getListaFabricantes() {
		return listaFabricantes;
	}

	public void setListaFabricantes(List<Fabricante> listaFabricantes) {
		this.listaFabricantes = listaFabricantes;
	}

	public List<Fabricante> getListaFabricantesFiltrados() {
		return listaFabricantesFiltrados;
	}

	public void setListaFabricantesFiltrados(List<Fabricante> listaFabricantesFiltrados) {
		this.listaFabricantesFiltrados = listaFabricantesFiltrados;
	}

	public void novo() {
		fabricanteMB = new Fabricante();
	}

	public void salvar() {

		try {
			FabricanteDAO fabricanteDAO = new FabricanteDAO();
			fabricanteDAO.salvar(fabricanteMB);

			fabricanteMB = new Fabricante();

			FacesUtil.adicionarMgsInfo("Fabricante Salvo com Sucesso.");
		} catch (RuntimeException ex) {
			ex.printStackTrace();
			FacesUtil.adicionarMgsErro("Erro ao tentar Salvar um Fabricante. Detalhes: " + ex.getMessage());
		}
	}
	
	public void carregar(){
		
		try {
			FabricanteDAO fabricanteDAO = new FabricanteDAO();
			listaFabricantes = fabricanteDAO.listar();
		} catch (RuntimeException ex) {
			FacesUtil.adicionarMgsErro("Erro ao tentar pesquisar. Detalhes: "+ ex.getMessage());
		}
	}

}
