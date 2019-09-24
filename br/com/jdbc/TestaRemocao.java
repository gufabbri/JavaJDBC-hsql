package br.com.jdbc;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class TestaRemocao {

	public static void main(String[] args) throws SQLException {
		
		Connection connection = new ConnectionPool().getConnection();
		Statement statement = connection.createStatement();
		
		statement.execute("delete from Produto where id>3");
		int count = statement.getUpdateCount();/*quantas linhas foram atualizadas*/
		System.out.println(count);
		
		statement.close();
		connection.close();
	}

}
