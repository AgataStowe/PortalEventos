package br.com.peladas.portalEventos.service;

import java.util.List;

import javax.persistence.NoResultException;

import br.com.peladas.portalEventos.dao.UsuarioDao;
import br.com.peladas.portalEventos.model.Usuario;

public class UsuarioService {
	
	private UsuarioDao usuarioDao = new UsuarioDao();
	private Usuario usuarioBd = new Usuario();
	
	public boolean cadastrar(Usuario usuario) {
		return usuarioDao.salvar(usuario);
	}
	
	public Usuario login(Usuario usuario) {
		this.usuarioBd = usuarioDao.consultarPorEmail(usuario.getEmail());
			
		if(usuario.getEmail().equals(usuarioBd.getEmail()) && 
					usuario.getEmail().equals(usuarioBd.getEmail())) {
			return usuarioBd;
		}
		
		return null;
	}
	
	public boolean existe(Usuario usuario) {
		
		this.usuarioBd = usuarioDao.consultarPorEmail(usuario.getEmail());
		return (usuarioBd != null);
	}
	
	public List<Usuario> listar(){
		try {
			
			return usuarioDao.listar();
		} catch (NoResultException e) {
			e.printStackTrace();
			return null;
		}
	}
	
	public Usuario obterPorEmail(String email) {
			
			return usuarioDao.consultarPorEmail(email);
		}
}
