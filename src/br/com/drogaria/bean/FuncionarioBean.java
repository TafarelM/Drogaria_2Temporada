package br.com.drogaria.bean;

import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import org.apache.commons.codec.digest.DigestUtils;

import br.com.drogaria.dao.FuncionarioDAO;
import br.com.drogaria.domain.Funcionario;
import br.com.drogaria.util.FacesUtil;

@ManagedBean
@ViewScoped
public class FuncionarioBean {

	private Funcionario funcionarioMB;
	private List<Funcionario> listaFuncionarios;
	private List<Funcionario> listaFuncionariosFiltrados;
	private String acao;
	private int id;

	public Funcionario getFuncionarioMB() {
		return funcionarioMB;
	}

	public void setFuncionarioMB(Funcionario funcionarioMB) {
		funcionarioMB.setSenha(DigestUtils.md5Hex(funcionarioMB.getSenha()));
		this.funcionarioMB = funcionarioMB;
	}

	public List<Funcionario> getListaFuncionarios() {
		return listaFuncionarios;
	}

	public void setListaFuncionarios(List<Funcionario> listaFuncionarios) {
		this.listaFuncionarios = listaFuncionarios;
	}

	public List<Funcionario> getListaFuncionariosFiltrados() {
		return listaFuncionariosFiltrados;
	}

	public void setListaFuncionariosFiltrados(List<Funcionario> listaFuncionariosFiltrados) {
		this.listaFuncionariosFiltrados = listaFuncionariosFiltrados;
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

	public void novo() {
		funcionarioMB = new Funcionario();
	}

	public void salvar() {
		try {
			FuncionarioDAO funcionarioDAO = new FuncionarioDAO();
			
			funcionarioDAO.salvar(funcionarioMB);

			funcionarioMB = new Funcionario();

			FacesUtil.adicionarMgsInfo("Funcionário salvo com sucesso.");
		} catch (RuntimeException ex) {
			FacesUtil.adicionarMgsErro("Erro ao tentar salvar Funcionário. Detalhes: " + ex.getMessage());
		}
	}

	public void carregarPesquisa() {
		try {
			FuncionarioDAO funcionarioDAO = new FuncionarioDAO();
			listaFuncionarios = funcionarioDAO.listar();
		} catch (RuntimeException ex) {
			FacesUtil.adicionarMgsErro("Erro ao tentar listar os funcionários. Detalhes: "+ ex.getMessage());
		}
	}
	
	public void carregarCadastro(){
		try {
			if(id != 0){
				FuncionarioDAO funcionarioDAO = new FuncionarioDAO();
				funcionarioMB = funcionarioDAO.buscarPorId(id);
			}else{
				funcionarioMB = new Funcionario();
			}
		} catch (RuntimeException ex) {
			FacesUtil.adicionarMgsErro("Erro ao tentar obter os dados do funcionário. Detalhes: "+ ex.getMessage());
		}
	}
	
	public void excluir(){
		try {
			FuncionarioDAO funcionarioDAO = new FuncionarioDAO();
			funcionarioDAO.excluir(funcionarioMB);
			
			FacesUtil.adicionarMgsInfo("Funcionário Excluido com sucesso.");
		} catch (RuntimeException ex) {
			FacesUtil.adicionarMgsErro("Erro ao tentar excluir o funcionário. Detalhes: "+ ex.getMessage());
		}
	}
	
	public void editar(){
		try {
			FuncionarioDAO funcionarioDAO = new FuncionarioDAO();
			funcionarioMB.setSenha(DigestUtils.md5Hex(funcionarioMB.getSenha()));
			funcionarioDAO.editar(funcionarioMB);
			
			FacesUtil.adicionarMgsInfo("Funcionário editado com sucesso.");
		} catch (RuntimeException ex) {
			FacesUtil.adicionarMgsErro("Erro ao tentar editar os dados do funcionário. Detalhes: "+ ex.getMessage());
		}
	}

}
