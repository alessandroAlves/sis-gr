package br.com.sisgr.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import br.com.sisgr.model.Tarefa;

@Repository
public class TarefaDAO {

	private SessionFactory factory;
	private Session session;
	private Transaction transaction;
	
	@Autowired
	public TarefaDAO (SessionFactory sessionFactory){
		this.factory = sessionFactory;
	}
	
	public void adicionar(Tarefa tarefa){
		session = factory.openSession();
		transaction = session.beginTransaction();
		session.save(tarefa);
		transaction.commit();
		session.close();
	}
	
	public void atualizar(Tarefa tarefa){
		session = factory.openSession();
		transaction = session.beginTransaction();
		session.update(tarefa);
		transaction.commit();
		session.close();
	}
	
	public void remover(Tarefa tarefa) {
		session = factory.openSession();
		transaction = session.beginTransaction();
		session.delete(tarefa);
		transaction.commit();
		session.close();
	}	
	
	public Tarefa carregar(Integer id){
		session = factory.openSession();
		transaction = session.beginTransaction();
		Tarefa tarefa = (Tarefa) session.get(Tarefa.class, id); 
		transaction.commit();
		session.close();
		return tarefa;
	}
	
	@SuppressWarnings("unchecked")
	public List<Tarefa> listar(Integer id){
		session = factory.openSession();
		transaction = session.beginTransaction();
		Criteria criteria = session.createCriteria(Tarefa.class);
		criteria.add(Restrictions.eq("reuniaoId", id));
		List<Tarefa> tarefas = criteria.list(); 
		transaction.commit();
		session.close();
		return tarefas;
	}
}
