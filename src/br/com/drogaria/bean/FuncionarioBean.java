package br.com.drogaria.bean;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import br.com.drogaria.domain.Funcionario;
import br.com.drogaria.util.FacesUtil;

@ManagedBean
@ViewScoped
public class FuncionarioBean {
	
	private Funcionario funcionarioMB;
	
	public Funcionario getFuncionarioMB() {
		if(funcionarioMB == null){
			funcionarioMB = new Funcionario();
		}
		return funcionarioMB;
	}
	
	public void setFuncionarioMB(Funcionario funcionarioMB) {
		this.funcionarioMB = funcionarioMB;
	}
	
	public void salvar(){
		FacesUtil.adicionarMgsInfo(funcionarioMB.toString());
		
		FacesUtil.adicionarMgsInfo("Funcionário salvo com Sucesso.");
	}
}
