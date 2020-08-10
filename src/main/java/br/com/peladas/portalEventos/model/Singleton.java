package br.com.peladas.portalEventos.model;

public class Singleton {

	private static Singleton uniqueInstance = null;
	public Usuario usuario;

	private Singleton() {
	}

	public static synchronized Singleton getInstance() {
		if (uniqueInstance != null)
			return uniqueInstance;

		uniqueInstance = new Singleton();
		return uniqueInstance;
	}
}