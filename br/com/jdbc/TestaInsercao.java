package br.com.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class TestaInsercao {

	public static void main(String[] args) throws SQLException {
		
		
		try (Connection connection = new ConnectionPool().getConnection()) {
		connection.setAutoCommit(false);
		String sql = "insert into Produto (nome, descricao) values (?, ?)";

		
		try (PreparedStatement statement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)){
		adiciona("TV ", "42 polegadas", statement);
		adiciona("blueray ", "hdmi", statement);
		connection.commit();
		//statement.close();/*try fecha automaticamente*/
		} catch (Exception e){
			e.printStackTrace();
			connection.rollback();
		}

		//connection.close(); /*nao precisamos utilizar o close com o try*/
		/*autoclosable*/
		}
	}

	private static void adiciona(String nome, String descricao, PreparedStatement statement) throws SQLException {
		statement.setString(1, nome);
		statement.setString(2, descricao);
		
		boolean resultado = statement.execute();
		
		System.out.println(resultado);
		ResultSet resultSet = statement.getGeneratedKeys(); /*extrair as chaves(ids)  gerados automaticamente*/
		while(resultSet.next()) {
			String id = resultSet.getString("id");
			System.out.println(id);
		}
		
		resultSet.close();
	}

}
