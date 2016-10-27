package br.com.drogaria.test;

import java.math.BigDecimal;
import java.util.List;

import org.junit.Test;

import br.com.drogaria.dao.ItemVendaDAO;
import br.com.drogaria.dao.ProdutoDAO;
import br.com.drogaria.dao.VendaDAO;
import br.com.drogaria.domain.ItemVenda;
import br.com.drogaria.domain.Produto;
import br.com.drogaria.domain.Venda;

public class ItemVendaDAO_Test {
	
	@Test
	//@Ignore
	public void salvar(){
		ProdutoDAO produtoDAO = new ProdutoDAO();
		Produto produto = new Produto();
		produto = produtoDAO.buscarPorId(4);
		
		VendaDAO vendaDAO = new VendaDAO();
		Venda venda = new Venda();
		venda = vendaDAO.buscarPorId(2);
		
		ItemVendaDAO itemVendaDAO = new ItemVendaDAO();
		ItemVenda itemVenda = new ItemVenda();
		
		itemVenda.setProduto(produto);
		itemVenda.setQuantidade(22);
		itemVenda.setValor(new BigDecimal("66.33"));
		itemVenda.setVenda(venda);
		
		itemVendaDAO.salvar(itemVenda);
	}
	
	@Test
	//@Ignore
	public void listar(){
		ItemVendaDAO itemVendaDAO = new ItemVendaDAO();
		
		List<ItemVenda> itemVendas = itemVendaDAO.listar();
		
		for (ItemVenda itens : itemVendas) {
			System.out.println(itens);
		}
	}
	
	@Test
	//@Ignore
	public void buscarPorId(){
		ItemVendaDAO itemVendaDAO = new ItemVendaDAO();
		ItemVenda itemVenda = new ItemVenda();
		
		itemVenda = itemVendaDAO.buscarPorId(2);
		
		System.out.println(itemVenda);
	}
	
	@Test
	//@Ignore
	public void excluir(){
		ItemVendaDAO itemVendaDAO = new ItemVendaDAO();
		ItemVenda itemVenda = new ItemVenda();
		
		itemVenda = itemVendaDAO.buscarPorId(1);
		
		itemVendaDAO.excluir(itemVenda);
	}
	
	@Test
	//@Ignore
	public void editar(){		
		VendaDAO vendaDAO = new VendaDAO();
		Venda venda = new Venda();
		venda = vendaDAO.buscarPorId(2);
		
		ProdutoDAO produtoDAO = new ProdutoDAO();
		Produto produto = new Produto();
		produto = produtoDAO.buscarPorId(5);
		
		ItemVendaDAO itemVendaDAO = new ItemVendaDAO();
		ItemVenda itemVenda = new ItemVenda();
		itemVenda = itemVendaDAO.buscarPorId(2);
		
		itemVenda.setProduto(produto);
		itemVenda.setQuantidade(1);
		itemVenda.setValor(new BigDecimal("44.22"));
		itemVenda.setVenda(venda);
		
		itemVendaDAO.editar(itemVenda);
	}
}
