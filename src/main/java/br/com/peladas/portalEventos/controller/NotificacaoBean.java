package br.com.peladas.portalEventos.controller;

import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;

import br.com.peladas.portalEventos.model.Convidado;
import br.com.peladas.portalEventos.model.Evento;
import br.com.peladas.portalEventos.model.Notificacao;
import br.com.peladas.portalEventos.model.Singleton;
import br.com.peladas.portalEventos.model.Usuario;
import br.com.peladas.portalEventos.service.EventoService;
import br.com.peladas.portalEventos.service.NotificacaoService;

@ManagedBean (name = "notificacaoBean")
public class NotificacaoBean {
	private Notificacao notificacao;
	private NotificacaoService notificacaoService = new NotificacaoService();
	private EventoService eventoService = new EventoService();
	private List<Evento> eventos;
	private Usuario usuario = Singleton.getInstance().usuario;
	
	public NotificacaoBean() {
		listarPorId(this.usuario);
	}
	
	
	public void listarPorId(Usuario usuario) {
		eventos = eventoService.listarPorEmail(usuario);
	}
	
	public void aceitar(Convidado convite) {
		
		if(notificacaoService.salvar(convite)) {
			FacesContext.getCurrentInstance().addMessage(
					null,
					new FacesMessage(FacesMessage.SEVERITY_INFO,null,
							"Participação no evento realizada com sucesso!"));
		}else {
			FacesContext.getCurrentInstance().addMessage(
					null,
					new FacesMessage(FacesMessage.SEVERITY_ERROR,null,
							"Erro ao aceitar convite!"));
		}
	}
	
	public void recusar(Notificacao convite) {
		if(notificacaoService.remover(convite)) {
			FacesContext.getCurrentInstance().addMessage(
					null,
					new FacesMessage(FacesMessage.SEVERITY_INFO,null,
							"Convite recusado!"));
		}else {
			FacesContext.getCurrentInstance().addMessage(
					null,
					new FacesMessage(FacesMessage.SEVERITY_ERROR, null,
							"Erro ao recusar convite!"));
		}
	}

	public Notificacao getNotificacao() {
		return notificacao;
	}

	public void setNotificacao(Notificacao notificacao) {
		this.notificacao = notificacao;
	}
	
	public List<Evento> getEventos() {
		return eventos;
	}


	public void setEventos(List<Evento> eventos) {
		this.eventos = eventos;
	}

	
	
}
