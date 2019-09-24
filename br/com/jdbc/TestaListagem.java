package br.com.jdbc;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class TestaListagem {

	public static void main(String[] args) throws SQLException {
		Connection connection = new ConnectionPool().getConnection();
		/*connection na url especificada no driver*/
		
		Statement statement = connection.createStatement();
		boolean resultado = statement.execute("select * from Produto");
		/*connection, executa um statement (select)
		 *devolve truecaso retorne uma lista de dados */
		
		ResultSet resultSet = statement.getResultSet(); /*devolve o conjunto de resultados*/
		
		while(resultSet.next()) {/*para cada uma das linhas: pegue o id, nome e descrição*/
		
		int id = resultSet.getInt("id");		
		String nome = resultSet.getString("nome");		
		String descricao = resultSet.getString("descricao");
		
		System.out.println(id);
		System.out.println(nome);
		System.out.println(descricao);
		
		
		}
		
		
		resultSet.close();
		statement.close();
		connection.close();
	}

	

}
