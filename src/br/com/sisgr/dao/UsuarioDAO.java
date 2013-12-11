package br.com.sisgr.dao;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import br.com.sisgr.model.Usuario;

@Repository
public class UsuarioDAO {

	private SessionFactory factory;
	private Session session;
	private Transaction transaction;

	@Autowired
	public UsuarioDAO(SessionFactory factory){
		this.factory = factory;
	}

	public void adicionar(Usuario usuario) {
		session = factory.openSession();
		transaction = session.beginTransaction();
		session.save(usuario);
		transaction.commit();
		session.close();
	}

	public void atualizar(Usuario usuario){
		session = factory.openSession();
		transaction = session.beginTransaction();
		session.update(usuario);
		transaction.commit();
		session.close();
	}
	
	public void remover(Usuario usuario) {
		session = factory.openSession();
		transaction = session.beginTransaction();
		session.delete(usuario);
		transaction.commit();
		session.close();
	}

	public Usuario carregar(Integer id){
		session = factory.openSession();
		transaction = session.beginTransaction();
		Usuario usuario = (Usuario) session.get(Usuario.class, id);
		transaction.commit();
		session.close();
		return usuario;
	} 
	
	public Usuario buscarPorLogin(String email, String senha) {
		session = factory.openSession();
		transaction = session.beginTransaction();
		Criteria criteria = session.createCriteria(Usuario.class);
		criteria.add(Restrictions.eq("email", email));
		criteria.add(Restrictions.eq("senha", senha));
		Usuario usuario = (Usuario) criteria.uniqueResult();
		transaction.commit();
		session.close();
		return usuario;
	}

	public Usuario buscarPorEmail(String email) {
		session = factory.openSession();
		transaction = session.beginTransaction();
		Criteria criteria = session.createCriteria(Usuario.class);
		criteria.add(Restrictions.ilike("email", email));	
		Usuario usuario = (Usuario) criteria.uniqueResult();
		transaction.commit();
		session.close();
		return usuario;
	}

}
