package br.com.peladas.portalEventos.service;

import java.util.List;

import javax.persistence.NoResultException;

import br.com.peladas.portalEventos.dao.EventoUsuarioDao;
import br.com.peladas.portalEventos.model.EventoUsuario;

public class EventoUsuarioService {
	
	private EventoUsuarioDao eventoUsuarioDao = new EventoUsuarioDao();
	
	public boolean participar(EventoUsuario evento) {
		return eventoUsuarioDao.add(evento);
	}
	
	/*public boolean existe(Usuario usuario) {
		
		Usuario usuarioBd = usuarioDao.consultar(usuario);
		return (usuarioBd.getEmail() != null);
	}*/
	
	public List<EventoUsuario> listar(){
		try {
			
			return eventoUsuarioDao.listar();
		} catch (NoResultException e) {
			e.printStackTrace();
			return null;
		}
	}
}
