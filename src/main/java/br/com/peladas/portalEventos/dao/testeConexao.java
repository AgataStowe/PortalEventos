package br.com.peladas.portalEventos.dao;

import java.sql.Connection;
import java.sql.DriverManager;

public class testeConexao {

	public static void main(String[] args) {
			Connection conexao = null;
			String usuario = "postgres";
			String senha = "postgres";
			String nomeBancoDados = "dbeventos";

			try {
				
				conexao = DriverManager.getConnection("jdbc:postgresql://localhost:5432/" + nomeBancoDados,
						 usuario, senha);
				System.out.println("conected");
				
				conexao.close();
			} catch (Exception e) {
				e.printStackTrace();
				
			}

	}

}
