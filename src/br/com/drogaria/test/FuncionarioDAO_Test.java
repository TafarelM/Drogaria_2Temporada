package br.com.drogaria.test;

import java.util.List;

import org.junit.Ignore;
import org.junit.Test;

import br.com.drogaria.dao.FuncionarioDAO;
import br.com.drogaria.domain.Funcionario;

public class FuncionarioDAO_Test {
	
	@Test
	@Ignore
	public void salvar(){
		Funcionario funcionario = new Funcionario();
		FuncionarioDAO dao = new FuncionarioDAO();
		
		funcionario.setNome("salvar teste");
		funcionario.setCpf("12365478911");
		funcionario.setSenha("2134da");
		funcionario.setFuncao("tester");
		
		dao.salvar(funcionario);
	}
	
	@Test
	@Ignore
	public void listar(){
		FuncionarioDAO dao = new FuncionarioDAO();
		List<Funcionario> listaFuncionario = dao.listar();
		
		System.out.println(listaFuncionario);
			
	}
	
	@Test
	@Ignore
	public void buscarPorId(){
		FuncionarioDAO dao = new FuncionarioDAO();
		
		Funcionario funcionario = dao.buscarPorId(1);
		
		System.out.println(funcionario);
	}
	
	@Test
	@Ignore
	public void excluir(){
		FuncionarioDAO dao = new FuncionarioDAO();
		
		Funcionario funcionario = dao.buscarPorId(1);
		dao.excluir(funcionario);
	}
	

	@Test
	@Ignore
	public void excluirPorId(){
		FuncionarioDAO dao = new FuncionarioDAO();
		
		dao.excluirPorId(1);
	}
	
	@Test
	@Ignore
	public void editar(){
		FuncionarioDAO dao = new FuncionarioDAO();
		Funcionario funcionario = dao.buscarPorId(3);
		funcionario.setNome("testedsd");
		funcionario.setCpf("12365479822");
		funcionario.setSenha("12dd");
		funcionario.setFuncao("testerrrr");
		
		dao.editar(funcionario);
	}
}
