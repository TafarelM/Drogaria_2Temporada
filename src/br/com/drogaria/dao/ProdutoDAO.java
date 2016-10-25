package br.com.drogaria.dao;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import br.com.drogaria.domain.Produto;
import br.com.drogaria.util.HibernateUtil;

public class ProdutoDAO {
	
	public void salvar(Produto produto){
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction transaction = null;
		
		try {
			transaction = session.beginTransaction();
			session.save(produto);
			transaction.commit();			
		} catch (RuntimeException ex) {
			if(transaction != null){
				transaction.rollback();
			}
			throw ex;
		}finally {
			session.close();
		}
	}
	
	@SuppressWarnings("unchecked")
	public List<Produto> listar(){
		Session session = HibernateUtil.getSessionFactory().openSession();
		List<Produto> produtos = null;
		
		try {
			Query query = session.getNamedQuery("Produto.listar");
			produtos = query.list();
		} catch (RuntimeException ex) {
			throw ex;
		}finally {
			session.close();
		}
		return produtos;
	}
	
	public Produto buscarPorId(int id){
		Session session = HibernateUtil.getSessionFactory().openSession();
		Produto produto = null;
		
		try {
			Query query = session.getNamedQuery("Produto.buscarPorId");
			query.setInteger("id", id);
			produto = (Produto) query.uniqueResult();
		} catch (RuntimeException ex) {
			throw ex;
		}finally {
			session.close();
		}
		return produto;
	}
	
}
