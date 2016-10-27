package br.com.drogaria.test;

import java.math.BigDecimal;
import java.util.List;

import org.junit.Ignore;
import org.junit.Test;

import br.com.drogaria.dao.FabricanteDAO;
import br.com.drogaria.dao.ProdutoDAO;
import br.com.drogaria.domain.Fabricante;
import br.com.drogaria.domain.Produto;

public class ProdutoDAO_Test {
	
	@Test
	@Ignore
	public void salvar(){
		Produto produto = new Produto();
		ProdutoDAO produtoDAO = new ProdutoDAO();
		
		Fabricante fabricante = new Fabricante();
		FabricanteDAO fabricanteDAO = new FabricanteDAO();
		
		fabricante = fabricanteDAO.buscarPorId(4);
		
		produto.setDescricao("teste Salvar");
		produto.setPreco(new BigDecimal("23.55"));
		produto.setQuantidade(12);
		produto.setFabricante(fabricante);
		
		produtoDAO.salvar(produto);
	}
	
	@Test
	@Ignore
	public void buscarPorId(){
		ProdutoDAO produtoDAO = new ProdutoDAO();
		
		Produto produto =  produtoDAO.buscarPorId(5);
		
		System.out.println(produto);
	}
	
	@Test
	@Ignore
	public void listar(){
		ProdutoDAO produtoDAO = new ProdutoDAO();
		
		List<Produto> produtos = produtoDAO.listar();
		
		for(Produto p :produtos){
			System.out.println(p);
		}
	}
	
	@Test
	@Ignore
	public void excluir(){
		ProdutoDAO dao = new ProdutoDAO();
		Produto produto = new Produto();
		
		produto = dao.buscarPorId(3);
		dao.excluir(produto);		
	}
	
	@Test
	@Ignore
	public void editar(){
		ProdutoDAO dao = new ProdutoDAO();
		Produto produto = new Produto();
		
		FabricanteDAO fabricanteDAO = new FabricanteDAO();
		Fabricante fabricante = new Fabricante();
		
		produto = dao.buscarPorId(2);
		produto.setDescricao("teste editar");
		produto.setPreco(new BigDecimal("32.20"));
		produto.setQuantidade(10);		
		fabricante = fabricanteDAO.buscarPorId(5);		
		produto.setFabricante(fabricante);
		
		dao.editar(produto);
	}
	
}
