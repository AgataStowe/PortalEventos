package br.com.peladas.portalEventos.controller;

import java.util.logging.Logger;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;

import br.com.peladas.portalEventos.model.Singleton;
import br.com.peladas.portalEventos.model.Usuario;
import br.com.peladas.portalEventos.service.UsuarioService;
@ManagedBean (name = "usuarioBean")
@RequestScoped
public class UsuarioBean{

	private Usuario usuario;
	
	public UsuarioBean() {
		usuario = new Usuario();
	}
	
	UsuarioService usuarioService = new UsuarioService();

	public String login(){
		
		if(!existe(usuario)) {
			FacesContext.getCurrentInstance().addMessage(
					null,
					new FacesMessage(FacesMessage.SEVERITY_WARN,"Atenção",
							"Usuario não cadastrado!"));
			return null;
		}
		
		if(usuarioService.login(usuario) != null) {
			Usuario user = usuarioService.login(usuario);
			salvarUsuario(user);
			
			try {
				FacesContext.getCurrentInstance().getExternalContext().redirect("Home.xhtml");
			} catch (Exception e) {
				Logger.getLogger(Usuario.class.getName());
			}
			
			return "Home.xhtml";
		}
		
		FacesContext.getCurrentInstance().addMessage(
				null,
				new FacesMessage(FacesMessage.SEVERITY_WARN,"Atenção",
						"Email ou senha incorreta!"));
		return null;
    }
	
	public String cadastrar() {
		String retorno = "";
		
		if(existe(usuario)) {
			FacesContext.getCurrentInstance().addMessage(
					null,
					new FacesMessage(FacesMessage.SEVERITY_WARN,"Atenção",
							"Email já cadastrado!"));
			
			try {
				FacesContext.getCurrentInstance().getExternalContext().redirect("Cadastro.xhtml");
			} catch (Exception e) {
				Logger.getLogger(Usuario.class.getName());
			}
			retorno = "Cadastro.xhtml";
			
		}else if (usuarioService.cadastrar(usuario)) {
			FacesContext.getCurrentInstance().addMessage(
					null,
					new FacesMessage(FacesMessage.SEVERITY_INFO,null,
							"Usuario incluido com sucesso!"));
			
			try {
				FacesContext.getCurrentInstance().getExternalContext().redirect("login.xhtml");
			} catch (Exception e) {
				Logger.getLogger(Usuario.class.getName());
			}
			retorno = "login.xhtml";
		}
		
		return retorno;
	}
	
	public void salvarUsuario(Usuario user) {
		Singleton.getInstance().usuario = user;
	}
	
	public boolean existe(Usuario usuario){
		return usuarioService.existe(usuario);
    }
	
	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}
	
	
}
