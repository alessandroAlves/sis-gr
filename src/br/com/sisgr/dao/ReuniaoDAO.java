package br.com.sisgr.dao;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import br.com.sisgr.model.Reuniao;

@Repository
public class ReuniaoDAO {
	
	private SessionFactory factory;
	private Session session; 
	private Transaction transaction;
	
	@Autowired
	public void setSessionFactory(SessionFactory sessionFactory) {
		this.factory = sessionFactory;
	}

	public void adicionar(Reuniao reuniao) 
	{
		session = factory.openSession();
		transaction = session.beginTransaction();
		session.save(reuniao);
		transaction.commit();
		session.close();
	}
	
	public void atualizar(Reuniao reuniao)
	{
		session = factory.openSession();
		transaction = session.beginTransaction();
		session.update(reuniao);
		transaction.commit();
		session.close();
	}

	public void remover(Reuniao reuniao) {
		session = factory.openSession();
		transaction = session.beginTransaction();
		session.delete(reuniao);
		transaction.commit();
		session.close();
	}

	public Reuniao carregar(Integer id) {
		session = factory.openSession();
		Transaction transaction = session.beginTransaction();
		Reuniao reuniao = (Reuniao) session.get(Reuniao.class, id);
		transaction.commit();
		session.close();
		return reuniao;
	}
	
	@SuppressWarnings("unchecked")
	public List<Reuniao> listar(Integer id) {
		session = factory.openSession();
		Transaction transaction = session.beginTransaction();
		Query query = session.createQuery(
				"From Reuniao where usuarioId = " + id);
		List<Reuniao> reunioes = query.list();
		transaction.commit();
		session.close();
		return reunioes;
	}
	
}