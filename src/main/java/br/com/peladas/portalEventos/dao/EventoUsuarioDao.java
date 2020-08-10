package br.com.peladas.portalEventos.dao;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;

import br.com.peladas.portalEventos.hibernateUtils.HibernateUtil;
import br.com.peladas.portalEventos.model.EventoUsuario;

public class EventoUsuarioDao {
	
	private Session session;

	public boolean add(EventoUsuario evento) {
		boolean retorno = true;
		try {
			session = HibernateUtil.getSession();
			session.beginTransaction();
			session.update(evento);
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
	public List<EventoUsuario> listar() {
		List<EventoUsuario> lista = new ArrayList<>();
		try {
			session = HibernateUtil.getSession();
			session.beginTransaction();
			lista = (List<EventoUsuario>) session.createCriteria(EventoUsuario.class).list();
			session.getTransaction().commit();
		} catch (Exception e) {
			session.getTransaction().rollback();
			e.printStackTrace();
		} finally {
			session.close();
		}
		return lista;
	}
 
}
