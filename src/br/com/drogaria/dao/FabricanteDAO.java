package br.com.drogaria.dao;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import br.com.drogaria.domain.Fabricante;
import br.com.drogaria.util.HibernateUtil;

public class FabricanteDAO {
	
	public void salvar(Fabricante fabricante) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction transaction = null;
		
		try{
			transaction = session.beginTransaction();
			session.save(fabricante);
			transaction.commit();
		}catch(RuntimeException ex){
			if(transaction != null){
				transaction.rollback();
			}
			throw ex;
		}finally {
			session.close();
		}
	}
	
	@SuppressWarnings("unchecked")
	public List<Fabricante> listar(){
		Session session = HibernateUtil.getSessionFactory().openSession();
		List<Fabricante> fabricantes = null;
		
		try {
			Query consulta = session.getNamedQuery("Fabricante.listar");
			fabricantes = consulta.list();
		} catch (RuntimeException ex) {
			throw ex;
		}finally {
			session.close();
		}
		return fabricantes;
	}
	
	public Fabricante buscarPorId(int id){
		Session session = HibernateUtil.getSessionFactory().openSession();
		Fabricante fabricante = null;
		
		try {
			Query consulta = session.getNamedQuery("Fabricante.buscarPorId");
			consulta.setInteger("id", id);
			
			fabricante = (Fabricante) consulta.uniqueResult();
		} catch (RuntimeException ex) {
			throw ex;
		}finally {
			session.close();
		}
		return fabricante;
	}
	
	public void excluir(Fabricante fabricante) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction transaction = null;
		
		try{
			transaction = session.beginTransaction();
			session.delete(fabricante);
			transaction.commit();
		}catch(RuntimeException ex){
			if(transaction != null){
				transaction.rollback();
			}
			throw ex;
		}finally {
			session.close();
		}
	}
	
	public void excluirPorId(int id) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction transaction = null;
		
		try{
			transaction = session.beginTransaction();
			Fabricante fabricante = buscarPorId(id);
			session.delete(fabricante);
			transaction.commit();
		}catch(RuntimeException ex){
			if(transaction != null){
				transaction.rollback();
			}
			throw ex;
		}finally {
			session.close();
		}
	}
	
	public void editar(Fabricante fabricante) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction transaction = null;
		
		try{
			transaction = session.beginTransaction();			
			session.update(fabricante);
			transaction.commit();
		}catch(RuntimeException ex){
			if(transaction != null){
				transaction.rollback();
			}
			throw ex;
		}finally {
			session.close();
		}
	}
	
}
