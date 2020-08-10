package br.com.peladas.portalEventos.model;

public class Convidado {
	
	private String nomeEvento;
	private String emailUsuario;
	
	public Convidado () {}
	
	public Convidado(String nomeEvento, String emailUsuario) {
		this.nomeEvento = nomeEvento;
		this.emailUsuario = emailUsuario;
	}
	
	public String getNomeEvento() {
		return nomeEvento;
	}
	public void setNomeEvento(String nomeEvento) {
		this.nomeEvento = nomeEvento;
	}
	public String getEmailUsuario() {
		return emailUsuario;
	}
	public void setEmailUsuario(String emailUsuario) {
		this.emailUsuario = emailUsuario;
	}
	
}
