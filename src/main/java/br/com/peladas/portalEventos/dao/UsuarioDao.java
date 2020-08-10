package br.com.peladas.portalEventos.dao;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

import br.com.peladas.portalEventos.hibernateUtils.HibernateUtil;
import br.com.peladas.portalEventos.model.Usuario;

public class UsuarioDao {
	
	private Session session;
	
	public Usuario consultarPorEmail(String email) {
		Usuario retorno = new Usuario();
		try {
			session = HibernateUtil.getSession();
			session.beginTransaction();
			Criteria criteria = session.createCriteria(Usuario.class);
			criteria.add(Restrictions.eq("email", email));
			retorno = (Usuario) criteria.uniqueResult();
			session.getTransaction().commit();
		} catch (Exception e) {
			session.getTransaction().rollback();
			e.printStackTrace();
		} finally {
			session.close();
		}
		
		return retorno ;
	}
	
	public boolean salvar(Usuario usuario) {
		boolean retorno = true;
		try {
			session = HibernateUtil.getSession();
			session.beginTransaction();
			session.save(usuario);
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
	public List<Usuario> listar() {
		List<Usuario> lista = new ArrayList<>();
		try {
			session = HibernateUtil.getSession();
			session.beginTransaction();
			lista = (List<Usuario>) session.createCriteria(Usuario.class).list();
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
