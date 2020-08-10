package br.com.peladas.portalEventos.service;

import java.util.Date;
import java.util.List;

import javax.persistence.NoResultException;

import br.com.peladas.portalEventos.dao.NotificacaoDao;
import br.com.peladas.portalEventos.model.Convidado;
import br.com.peladas.portalEventos.model.Evento;
import br.com.peladas.portalEventos.model.Notificacao;
import br.com.peladas.portalEventos.model.Usuario;

public class NotificacaoService {
	
	private NotificacaoDao notificacaoDao = new NotificacaoDao();
	private EventoService eventoService = new EventoService();
	private UsuarioService usuarioService = new UsuarioService();
	
	public boolean salvar(Convidado convite) {
		
		// Recuperando o evento
		Evento evento = obterEvento(convite.getNomeEvento());
		
		// Criando uma notificacao
		Notificacao notificacao = new Notificacao(evento.getId(), new Date(), convite.getEmailUsuario());
		
		// Salvando
		return notificacaoDao.add(notificacao);
	}
	
	public List<Notificacao> listarPorEmail(Usuario usuario) {
		return notificacaoDao.listarPorEmail(usuario.getEmail());
	}
	
	public List<Notificacao> listar(){
		try {
			
			return notificacaoDao.listar();
		} catch (NoResultException e) {
			e.printStackTrace();
			return null;
		}
	}
	
	public boolean remover(Notificacao notificacao) {
		return notificacaoDao.remover(notificacao);
	}
	
	public boolean aceitar(Notificacao notificacao) {
			
			// Configurando o evento
			Evento evento = new Evento();
			evento.setId(notificacao.getId());
			
			// Relacionando o usuario ao evento
			evento.getUsuarios().add(usuarioService.obterPorEmail(notificacao.getEmail()));
			
			// Salvando
			if(eventoService.atualizar(evento)) {
				
				if(this.remover(notificacao)) return true;
			}
			
			return false;
		}
	
	
	public Evento obterEvento(String nomeEvento){
		return  eventoService.getEvento(nomeEvento);
	}
}
