package br.com.peladas.portalEventos.service;

import java.util.List;

import javax.persistence.NoResultException;

import br.com.peladas.portalEventos.dao.EventoDao;
import br.com.peladas.portalEventos.model.Evento;
import br.com.peladas.portalEventos.model.Usuario;

public class EventoService {
	
	private EventoDao eventoDao = new EventoDao();
	
	public boolean salvar(Evento dados) {
		return eventoDao.add(dados);
	}
	
	public boolean atualizar(Evento dados) {
		return eventoDao.atualizar(dados);
	}
	
	public boolean remover(Evento dados) {
		return eventoDao.remover(dados);
	}
	
	public List<Evento> listar(){
		try {
			
			return eventoDao.listar();
		} catch (NoResultException e) {
			e.printStackTrace();
			return null;
		}
	}
	
	public List<Evento> listarPorEmail(Usuario usuario){
		try {
			
			return eventoDao.listarPorEmail(usuario.getEmail());
		} catch (NoResultException e) {
			e.printStackTrace();
			return null;
		}
	}
	
	public Evento getEvento(String nome) {
		return eventoDao.getEvento(nome);
	}
}
