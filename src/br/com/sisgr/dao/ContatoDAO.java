package br.com.sisgr.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import br.com.sisgr.model.Contato;
import br.com.sisgr.model.Usuario;

@Repository
public class ContatoDAO {

	private SessionFactory factory;
	private Session session;
	private Transaction transaction;
	
	@Autowired
	public ContatoDAO(SessionFactory sessionFactory) {
		this.factory = sessionFactory;
	}
	
	public void salvar(Contato contato){
		session = factory.openSession();
		transaction = session.beginTransaction();
		session.save(contato);
		transaction.commit();
		session.close();
	}
	
	public void atualizar(Contato contato){
		session = factory.openSession();
		transaction = session.beginTransaction();
		session.update(contato);
		transaction.commit();
		session.close();
	}
	
	public void remover(Contato contato){
		session = factory.openSession();
		transaction = session.beginTransaction();
		session.delete(contato);
		transaction.commit();
		session.close();
	}
	
	public Contato carregar(Integer id){
		session = factory.openSession();
		transaction = session.beginTransaction();
		Contato contato = (Contato) session.get(Contato.class, id);
		transaction.commit();
		session.close();
		return contato;
	}
	
	@SuppressWarnings("unchecked")
	public List<Contato> listar(Usuario usuario){
		session = factory.openSession();
		transaction.begin();
		Criteria criteria = session.createCriteria(Contato.class);
		criteria.add(Restrictions.eq("usuarioId", usuario.getId()));
		List<Contato> contatos = criteria.list();
		transaction.commit();
		session.close();
		return contatos;
	}
}
