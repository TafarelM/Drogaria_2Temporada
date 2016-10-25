package br.com.drogaria.test;

import java.util.List;

import org.junit.Ignore;
import org.junit.Test;

import br.com.drogaria.dao.FabricanteDAO;
import br.com.drogaria.domain.Fabricante;

public class FabricanteDAO_Test {
	
	@Test
	@Ignore
	public void salvar(){
		Fabricante f1 = new Fabricante();
		f1.setDescricao("descrição a");
		
		Fabricante f2 = new Fabricante();
		f2.setDescricao("descrição b");
		
		FabricanteDAO dao = new FabricanteDAO();
		dao.salvar(f1);
		dao.salvar(f2);
	}
	
	@Test
	@Ignore
	public void listar(){
		FabricanteDAO dao = new FabricanteDAO();
		List<Fabricante> fabricantes = dao.listar();		

			System.out.println(fabricantes);		
	}
	
	@Test
	@Ignore
	public void buscarPorId(){
		FabricanteDAO dao = new FabricanteDAO();
		
		Fabricante f1 = dao.buscarPorId(3);
		Fabricante f2 = dao.buscarPorId(2);
		
		System.out.println(f1);
		System.out.println(f2);
	}
	
	@Test
	@Ignore
	public void exlcuir(){
		FabricanteDAO dao = new FabricanteDAO();
		
		Fabricante f1 = dao.buscarPorId(2);
		dao.excluir(f1);
	}
	
	@Test
	@Ignore
	public void exlcuirPorId(){
		FabricanteDAO dao = new FabricanteDAO();
		
		dao.excluirPorId(3);
	}
	
	@Test
	@Ignore
	public void editar(){		
		FabricanteDAO dao = new FabricanteDAO();
		
		Fabricante fabricante = dao.buscarPorId(5);
		
		fabricante.setDescricao("teste editar");
		
		dao.editar(fabricante);
	}
	
}
