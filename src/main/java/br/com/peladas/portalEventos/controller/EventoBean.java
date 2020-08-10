package br.com.peladas.portalEventos.controller;

import java.util.ArrayList;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;

import br.com.peladas.portalEventos.model.Convidado;
import br.com.peladas.portalEventos.model.Evento;
import br.com.peladas.portalEventos.model.Notificacao;
import br.com.peladas.portalEventos.model.Singleton;
import br.com.peladas.portalEventos.model.Usuario;
import br.com.peladas.portalEventos.service.EventoService;
import br.com.peladas.portalEventos.service.NotificacaoService;
import br.com.peladas.portalEventos.service.UsuarioService;


@ManagedBean (name = "eventoBean")
@RequestScoped
public class EventoBean{
	
	private Evento dados;
	private Usuario usuario;
	private List<Usuario> listaUsuario;
	private List<Evento> listaEventos;
	private UsuarioService usuarioService;
	private EventoService eventoService;
	private Notificacao notificacao;
	private Convidado convidado;
	
	public EventoBean() {
		this.listaUsuario = new ArrayList<>();
		this.dados = new Evento();
		this.usuarioService = new UsuarioService();
		this.eventoService = new EventoService();
		this.usuario = new Usuario();
		this.notificacao = new Notificacao();
		this.convidado = new Convidado();
		listar();
	}
	
	// TAB Feed e Adicionar
	public String salvar(){
		
		if(eventoService.salvar(dados)) {
			return "Home?faces-redirect=true";
		}
		
		FacesContext.getCurrentInstance().addMessage(
				null,
				new FacesMessage(FacesMessage.SEVERITY_ERROR,"Evento",
						"Erro ao cadastrar evento!"));
		
		return null;
    }
	
	
	public void listar(){
		listaEventos = eventoService.listar();
    }
	
	public void addParticipante(){
		
		if(validarEmail()) {
			NotificacaoService notificacaoService = new NotificacaoService();
			
			if(notificacaoService.salvar(this.convidado)){
				FacesContext.getCurrentInstance().addMessage(
						null,
						new FacesMessage(FacesMessage.SEVERITY_INFO,null,
								"Convite enviado!"));
			}else {
				FacesContext.getCurrentInstance().addMessage(
						null,
						new FacesMessage(FacesMessage.SEVERITY_ERROR,null,
								"Erro ao convidar usuario!"));
			}
		}
		
    }
	
	private boolean validarEmail() {
		
		if(convidado.getEmailUsuario() == null) {
			FacesContext.getCurrentInstance().addMessage(
					null,
					new FacesMessage(FacesMessage.SEVERITY_WARN,null,
							"Informe um email!"));
			return false;
		}
		
		if(usuarioService.obterPorEmail(convidado.getEmailUsuario()) == null) {
			FacesContext.getCurrentInstance().addMessage(
					null,
					new FacesMessage(FacesMessage.SEVERITY_ERROR,null,
							"Email não encontrado!"));
			return false;
		}
		
		return true;
	}

	public void participar(Evento event){
		this.usuario = Singleton.getInstance().usuario;
		event.getUsuarios().add(usuario);
		
		if(eventoService.atualizar(event)) {
			FacesContext.getCurrentInstance().addMessage(
					null,
					new FacesMessage(FacesMessage.SEVERITY_INFO,null,
							"Participação confirmada!"));
		}else {
			FacesContext.getCurrentInstance().addMessage(
					null,
					new FacesMessage(FacesMessage.SEVERITY_ERROR,null,
							"Erro ao participar!"));

		}
    }
	
	public void atualizar(Evento event){
		if(eventoService.atualizar(event)) {
			FacesContext.getCurrentInstance().addMessage(
					null,
					new FacesMessage(FacesMessage.SEVERITY_INFO,null,
							"Evento atualizado!"));
		}else {
			FacesContext.getCurrentInstance().addMessage(
					null,
					new FacesMessage(FacesMessage.SEVERITY_ERROR,null,
							"Erro ao atualizar o evento!"));
		}
    }
	
	public void remover(Evento event){
		if(eventoService.remover(event)) {
			FacesContext.getCurrentInstance().addMessage(
					null,
					new FacesMessage(FacesMessage.SEVERITY_INFO,null,
							"Evento removido com sucesso!"));
		}else {
			FacesContext.getCurrentInstance().addMessage(
					null,
					new FacesMessage(FacesMessage.SEVERITY_ERROR,null,
							"Erro ao remover evento!"));
		}
    }
	
	// TAB Notificações
	private NotificacaoService notificacaoService = new NotificacaoService();
	private List<Evento> eventos;
	
	public void listarPorId(Usuario usuario) {
		eventos = eventoService.listarPorEmail(usuario);
	}
	
	public void aceitar(Notificacao convite) {
		
		if(notificacaoService.aceitar(convite)) {
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
	
	public void setListaUsuario(List<Usuario> listaUsuario) {
		this.listaUsuario = listaUsuario;
	}

	public List<Evento> getListaEventos() {
		return listaEventos;
	}

	public void setListaEventos(List<Evento> listaEventos) {
		this.listaEventos = listaEventos;
	}
	
	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public List<Usuario> getListaUsuario() {
		return listaUsuario;
	}

	public Evento getDados() {
		return dados;
	}

	public void setDados(Evento dados) {
		this.dados = dados;
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

	public Convidado getConvidado() {
		return convidado;
	}

	public void setConvidado(Convidado convidado) {
		this.convidado = convidado;
	}

	
	
	
}
