package br.com.drogaria.dao;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import br.com.drogaria.domain.Venda;
import br.com.drogaria.filter.VendaFilter;
import br.com.drogaria.util.HibernateUtil;

public class VendaDAO {

	public int salvar(Venda venda) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction transaction = null;
		int id = 0;

		try {
			transaction = session.beginTransaction();
			id = (int) session.save(venda);
			transaction.commit();
		} catch (RuntimeException ex) {
			if (transaction != null) {
				transaction.rollback();
			}
			throw ex;
		} finally {
			session.close();
		}

		return id;
	}

	@SuppressWarnings("unchecked")
	public List<Venda> listar() {
		Session session = HibernateUtil.getSessionFactory().openSession();
		List<Venda> vendas = null;

		try {
			Query query = session.getNamedQuery("Venda.listar");
			vendas = query.list();
		} catch (RuntimeException ex) {
			throw ex;
		} finally {
			session.close();
		}

		return vendas;
	}

	public Venda buscarPorId(int id) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		Venda venda = null;

		try {
			Query query = session.getNamedQuery("Venda.buscarPorId");
			query.setInteger("id", id);

			venda = (Venda) query.uniqueResult();
		} catch (RuntimeException ex) {
			throw ex;
		} finally {
			session.close();
		}

		return venda;
	}

	public void excluir(Venda venda) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction transaction = null;

		try {
			transaction = session.beginTransaction();
			session.delete(venda);
			transaction.commit();
		} catch (RuntimeException ex) {
			if (transaction != null) {
				transaction.rollback();
			}
			throw ex;
		} finally {
			session.close();
		}
	}

	public void editar(Venda venda) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction transaction = null;

		try {
			transaction = session.beginTransaction();
			session.update(venda);
			transaction.commit();
		} catch (RuntimeException ex) {
			if (transaction != null) {
				transaction.rollback();
			}
			throw ex;
		} finally {
			session.close();
		}
	}

	@SuppressWarnings("unchecked")
	public List<Venda> buscar(VendaFilter filtro) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		List<Venda> vendas = null;

		StringBuilder sql = new StringBuilder();
		sql.append("SELECT venda FROM Venda venda ");

		if (filtro.getDataInicial() != null && filtro.getDatafinal() != null) {
			sql.append("WHERE venda.horario BETWEEN :dataInicial AND :dataFinal ");
		}
		sql.append("ORDER BY venda.horario ");

		try {
			Query query = session.createQuery(sql.toString());
			if (filtro.getDataInicial() != null && filtro.getDatafinal() != null) {
				query.setDate("dataInicial", filtro.getDataInicial());
				query.setDate("dataFinal", filtro.getDatafinal());
			}
			
			vendas = query.list();
		} catch (RuntimeException ex) {
			throw ex;
		} finally {
			session.close();
		}

		return vendas;
	}
}
