package br.com.peladas.portalEventos.dao;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

import br.com.peladas.portalEventos.hibernateUtils.HibernateUtil;
import br.com.peladas.portalEventos.model.Evento;
import br.com.peladas.portalEventos.model.Notificacao;

public class NotificacaoDao {
	
	private Session session;

	@SuppressWarnings("unchecked")
	public List<Notificacao> listarPorEmail(String email) {
		List<Notificacao> notificacoes = new ArrayList<Notificacao>();
		try {
			session = HibernateUtil.getSession();
			session.beginTransaction();
			Criteria criteria = session.createCriteria(Notificacao.class);
			criteria.add(Restrictions.eq("email", email));
			notificacoes = (List<Notificacao>) criteria.list();
			session.getTransaction().commit();
		} catch (Exception e) {
			session.getTransaction().rollback();
			e.printStackTrace();
		} finally {
			session.close();
		}
		
		return notificacoes ;
	}
	
	public boolean add(Notificacao convite) {
		boolean retorno = true;
		try {
			session = HibernateUtil.getSession();
			session.beginTransaction();
			session.save(convite);
			session.getTransaction().commit();
		} catch (Exception e) {
			session.getTransaction().rollback();
			e.printStackTrace();
			retorno = false;
		} finally {
			session.close();
		}
		
		return retorno;
	}
	
	public boolean participar(Notificacao convite) {
		boolean retorno = true;
		try {
			session = HibernateUtil.getSession();
			session.beginTransaction();
			session.update(convite);
			session.getTransaction().commit();
		} catch (Exception e) {
			session.getTransaction().rollback();
			e.printStackTrace();
			retorno = false;
		} finally {
			session.close();
		}
		
		return retorno;
	}
	
	@SuppressWarnings("unchecked")
	public List<Notificacao> listar() {
		List<Notificacao> notificacoes = new ArrayList<>();
		try {
			session = HibernateUtil.getSession();
			session.beginTransaction();
			notificacoes = (List<Notificacao>) session.createCriteria(Evento.class).list();
			session.getTransaction().commit();
		} catch (Exception e) {
			session.getTransaction().rollback();
			e.printStackTrace();
		} finally {
			session.close();
		}
		return notificacoes;
	}
	
	public boolean remover(Notificacao notificacao) {
		boolean retorno = true;
		try {
			session = HibernateUtil.getSession();
			session.beginTransaction();
			Evento evento = (Evento) session.createCriteria(Evento.class)
					.add(Restrictions.eq("id_notificacao", notificacao.getId())).uniqueResult();
			session.delete(evento);
			session.getTransaction().commit();
		} catch (Exception e) {
			session.getTransaction().rollback();
			e.printStackTrace();
			retorno = false;
		} finally {
			session.close();
		}
		
		return retorno;
	}
 
}
