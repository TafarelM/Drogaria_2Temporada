package br.com.drogaria.test;

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.junit.Ignore;
import org.junit.Test;

import br.com.drogaria.dao.FuncionarioDAO;
import br.com.drogaria.dao.VendaDAO;
import br.com.drogaria.domain.Funcionario;
import br.com.drogaria.domain.Venda;
import br.com.drogaria.filter.VendaFilter;

public class VendaDAO_Test {
	
	@Test
	@Ignore
	public void salvar(){
		FuncionarioDAO funcionarioDAO = new FuncionarioDAO();
		Funcionario funcionario = new Funcionario();
		
		funcionario = funcionarioDAO.buscarPorId(2);
		
		VendaDAO vendaDAO = new VendaDAO();
		Venda venda = new Venda();	
		
		venda.setFuncionario(funcionario);
		venda.setHorario(new Date());
		venda.setValorTotal(new BigDecimal("12.33"));
		
		vendaDAO.salvar(venda);
	}
	
	@Test
	@Ignore
	public void listar(){
		VendaDAO vendaDAO = new VendaDAO();
		
		List<Venda> vendas = vendaDAO.listar();
		
		System.out.println(vendas);
	}
	
	@Test
	public void buscarPorId(){
		VendaDAO vendaDAO = new VendaDAO();
		Venda venda = vendaDAO.buscarPorId(1);
		
		System.out.println(venda);
	}
	
	@Test
	public void excluir(){
		VendaDAO vendaDAO = new VendaDAO();
		Venda venda = new Venda();
		
		venda = vendaDAO.buscarPorId(1);
		
		vendaDAO.excluir(venda);
	}
	
	@Test
	public void editar(){
		VendaDAO vendaDAO = new VendaDAO();
		Venda venda = new Venda();
		
		FuncionarioDAO funcionarioDAO = new FuncionarioDAO();
		Funcionario funcionario = new Funcionario();
		
		funcionario = funcionarioDAO.buscarPorId(3);
		
		venda = vendaDAO.buscarPorId(2);
		venda.setFuncionario(funcionario);
		venda.setHorario(new Date());
		venda.setValorTotal(new BigDecimal("10.00"));
		
		vendaDAO.editar(venda);
	}
	
	@Test
	public void  buscar() throws ParseException{
		SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
		
		VendaFilter filtro = new VendaFilter();
		filtro.setDataInicial(format.parse("10/10/2016"));
		filtro.setDatafinal(format.parse("13/11/2016"));
		
		VendaDAO vendaDAO = new VendaDAO();
		List<Venda> vendas = vendaDAO.buscar(filtro);
		
		for(Venda venda : vendas){
			System.out.println(venda);
		}
	}
}
