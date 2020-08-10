package br.com.peladas.portalEventos.dao;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

import br.com.peladas.portalEventos.hibernateUtils.HibernateUtil;
import br.com.peladas.portalEventos.model.Evento;
import br.com.peladas.portalEventos.model.Notificacao;

public class EventoDao {
	
	private Session session;
	
//	public void cadastrar(Usuario usuario) {
//		EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("eventoUnit");
//		EntityManager manager = entityManagerFactory.createEntityManager();
//		manager.getTransaction().begin();
//		
//		manager.persist(usuario);
//		manager.getTransaction().commit();
//	}
	
	/*public Usuario consultar(DadosEvento dados) {
		Usuario retorno = new Usuario();
		try {
			session = HibernateUtil.getSession();
			session.beginTransaction();
			Criteria criteria = session.createCriteria(Usuario.class);
			criteria.add(Restrictions.eq("email", dados.getEmail()));
			retorno = (Usuario) criteria.uniqueResult();
			session.getTransaction().commit();
		} catch (Exception e) {
			session.getTransaction().rollback();
			e.printStackTrace();
		} finally {
			session.close();
		}
		
		return retorno ;
	}*/
	
	
	/*public boolean existe(DadosEvento dados) {
		 
		Usuario usuario = consultar(dados);
		return (usuario.getEmail() != null);
	}
	*/
	public boolean add(Evento dados) {
		boolean retorno = true;
		try {
			session = HibernateUtil.getSession();
			session.beginTransaction();
			session.save(dados);
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
	
	public boolean atualizar(Evento dados) {
		boolean retorno = true;
		try {
			session = HibernateUtil.getSession();
			session.beginTransaction();
			session.update(dados);
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
	
	public boolean remover(Evento dados) {
		boolean retorno = true;
		try {
			session = HibernateUtil.getSession();
			session.beginTransaction();
			Evento evento = (Evento) session.createCriteria(Evento.class)
					.add(Restrictions.eq("id_evento", dados.getId())).uniqueResult();
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
	
	@SuppressWarnings("unchecked")
	public List<Evento> listar() {
		List<Evento> lista = new ArrayList<>();
		try {
			session = HibernateUtil.getSession();
			session.beginTransaction();
			lista = (List<Evento>) session.createCriteria(Evento.class).list();
			session.getTransaction().commit();
		} catch (Exception e) {
			session.getTransaction().rollback();
			e.printStackTrace();
		} finally {
			session.close();
		}
		return lista;
	}
	
	@SuppressWarnings("unchecked")
	public List<Evento> listarPorEmail(String email) {
		List<Evento> eventos = new ArrayList<Evento>();
		try {
			session = HibernateUtil.getSession();
			session.beginTransaction();
			Criteria criteria = session.createCriteria(Notificacao.class);
			criteria.add(Restrictions.eq("email", email));
			eventos = (List<Evento>) criteria.list();
			session.getTransaction().commit();
		} catch (Exception e) {
			session.getTransaction().rollback();
			e.printStackTrace();
		} finally {
			session.close();
		}
		
		return eventos ;
	}
	
	
	public Evento getEvento(String nome) {
		Evento evento = new Evento();
		try {
			session = HibernateUtil.getSession();
			session.beginTransaction();
			Criteria criteria = session.createCriteria(Notificacao.class);
			criteria.add(Restrictions.eq("nome", nome));
			evento = (Evento) criteria.uniqueResult();
			session.getTransaction().commit();
		} catch (Exception e) {
			session.getTransaction().rollback();
			e.printStackTrace();
		} finally {
			session.close();
		}
		
		return evento ;
	}
 
}
