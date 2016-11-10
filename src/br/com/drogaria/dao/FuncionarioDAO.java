package br.com.drogaria.dao;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import br.com.drogaria.domain.Funcionario;
import br.com.drogaria.util.HibernateUtil;

public class FuncionarioDAO {
	
	public void salvar(Funcionario funcionario){
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction transaction = null;
		
		try{
			transaction = session.beginTransaction();
			session.save(funcionario);
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
	public List<Funcionario> listar(){
		Session session = HibernateUtil.getSessionFactory().openSession();
		List<Funcionario> funcionarios = null;
		
		try {
			Query query = session.getNamedQuery("Funcionario.listar");
			funcionarios = query.list();
		} catch (RuntimeException ex) {
			throw ex;
		}finally {
			session.close();
		}
		return funcionarios;
	}
	
	public Funcionario buscarPorId(int id){
		Session session = HibernateUtil.getSessionFactory().openSession();
		Funcionario funcionario = null;
		
		try {
			Query query = session.getNamedQuery("Funcionario.buscarPorId");
			query.setInteger("id", id);
			
			funcionario = (Funcionario) query.uniqueResult();
		} catch (RuntimeException ex) {
			throw ex;
		}finally {
			session.close();
		}
		return funcionario;
	}
	
	public void excluir(Funcionario funcionario){
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction transaction = null;
		
		try{
			transaction = session.beginTransaction();
			session.delete(funcionario);
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
	
	public void excluirPorId(int id){
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction transaction = null;
		
		try{
			transaction = session.beginTransaction();
			Funcionario funcionario = buscarPorId(id);
			session.delete(funcionario);
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
	
	public void editar(Funcionario funcionario){
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction transaction = null;
		
		try{
			transaction = session.beginTransaction();			
			session.update(funcionario);
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
	
	public Funcionario autenticar(String cpf, String senha){
		Session session = HibernateUtil.getSessionFactory().openSession();
		Funcionario funcionario = null;
		
		try {
			Query query = session.getNamedQuery("Funcionario.autenticar");
			query.setString("cpf", cpf);
			query.setString("senha", senha);
			
			funcionario = (Funcionario) query.uniqueResult();
		} catch (RuntimeException ex) {
			throw ex;
		}finally {
			session.close();
		}
		
		return funcionario;
	}
	
	
}
