package br.com.jdbc;

import java.sql.Connection;
import java.sql.SQLException;

public class TestaConexao {
	public static void main(String[] args) throws SQLException {
		
		/*DriverManager escolhe pra gente o driver que vai permitir uma conexao com o banco*/
		Connection connection = new ConnectionPool().getConnection();
		System.out.println("Abrindo uma conexão com sucesso");
		connection.close();
	}
}
