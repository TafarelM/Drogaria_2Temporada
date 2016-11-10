package br.com.drogaria.bean;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import br.com.drogaria.dao.FuncionarioDAO;
import br.com.drogaria.domain.Funcionario;
import br.com.drogaria.util.FacesUtil;

@ManagedBean
@SessionScoped
public class AutenticacaoBean {
	private Funcionario funcionarioLogado;

	public Funcionario getFuncionarioLogado() {
		if(funcionarioLogado == null){
			funcionarioLogado = new Funcionario();
		}
		return funcionarioLogado;
	}

	public void setFuncionarioLogado(Funcionario funcionarioLogado) {
		this.funcionarioLogado = funcionarioLogado;
	}
	
	public void autenticar(){
		
		try {
			FuncionarioDAO funcionarioDAO = new FuncionarioDAO();
			funcionarioLogado = funcionarioDAO.autenticar(funcionarioLogado.getCpf(),funcionarioLogado.getSenha());
			if(funcionarioLogado == null){
				FacesUtil.adicionarMgsErro("CPF e/ou Senha inválidos.");
			}else{
				FacesUtil.adicionarMgsInfo("Funcionário autenticado com sucesso.");
			}
			
		} catch (RuntimeException ex) {
			FacesUtil.adicionarMgsErro("Erro ao tentar autenticar no sistema:" + ex.getMessage());
		}
	}
}
