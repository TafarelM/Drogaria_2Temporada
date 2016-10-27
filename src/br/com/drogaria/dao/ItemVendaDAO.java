package br.com.drogaria.dao;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import br.com.drogaria.domain.ItemVenda;
import br.com.drogaria.util.HibernateUtil;

public class ItemVendaDAO {
	
	public void salvar(ItemVenda itemVenda){
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction transaction = null;
		
		try {
			transaction = session.beginTransaction();
			session.save(itemVenda);
			transaction.commit();
		} catch (RuntimeException ex) {
			if (transaction != null) {
				transaction.rollback();
			}
			throw ex;
		}finally {
			session.close();
		}
	}
	
	@SuppressWarnings("unchecked")
	public List<ItemVenda> listar(){
		Session session = HibernateUtil.getSessionFactory().openSession();
		List<ItemVenda> itemVendas = null;
		
		try {
			Query query = session.getNamedQuery("ItemVenda.listar");
			itemVendas = query.list();
		} catch (RuntimeException ex) {
			throw ex;
		}finally {
			session.close();
		}
		
		return itemVendas;
	}
	
	public ItemVenda buscarPorId(int id){
		Session session = HibernateUtil.getSessionFactory().openSession();
		ItemVenda itemVenda = null;
		
		try {
			Query query = session.getNamedQuery("ItemVenda.buscarPorId");
			query.setInteger("id", id);
			
			itemVenda =  (ItemVenda) query.uniqueResult();
		} catch (RuntimeException ex) {
			throw ex;
		}finally {
			session.close();
		}
		return itemVenda;
	}
	
	public void excluir(ItemVenda itemVenda){
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction transaction = null;
		
		try {
			transaction = session.beginTransaction();
			session.delete(itemVenda);
			transaction.commit();
		} catch (RuntimeException ex) {
			if (transaction != null) {
				transaction.rollback();
			}
			throw ex;
		}finally {
			session.close();
		}
	}
	
	public void editar(ItemVenda itemVenda){
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction transaction = null;
		
		try {
			transaction = session.beginTransaction();
			session.update(itemVenda);
			transaction.commit();
		} catch (RuntimeException ex) {
			if (transaction != null) {
				transaction.rollback();
			}
			throw ex;
		}finally {
			session.close();
		}
	}
}
