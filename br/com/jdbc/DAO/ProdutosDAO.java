package br.com.jdbc.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import br.com.jdbc.modelo.Categoria;
import br.com.jdbc.modelo.Produto;

public class ProdutosDAO {

	private Connection con;

	public ProdutosDAO(Connection con) {
		this.con = con;
	}

	public void salva(Produto produto) throws SQLException {
		String sql = "insert into Produto (nome, descricao) values (?, ?)";

		try (PreparedStatement stmt = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

			stmt.setString(1, produto.getNome());
			stmt.setString(2, produto.getDescricao());
			stmt.execute();

			try (ResultSet rs = stmt.getGeneratedKeys()) {
				if (rs.next()) {
					int id = rs.getInt("id");
					produto.setId(id);
				}
			}
		}
	}
	/*cria uma lista
	 * prepara o sql
	 * busca
	 * para cada dos produtos, recebe os parametroe, cria o produto e adiciona na lista
	 * retorna a lista*/

	public List<Produto> lista() throws SQLException {
		
		List<Produto> produtos = new ArrayList<>();
		String sql = "select * from Produto";
		try(PreparedStatement stmt = con.prepareStatement(sql)){
			stmt.execute();			
			tranformaResultadosEmProdutos(produtos, stmt);
		}
		return produtos;
	}

	private void tranformaResultadosEmProdutos(List<Produto> produtos, PreparedStatement stmt) throws SQLException {
		try(ResultSet rs = stmt.getResultSet()){
			while(rs.next()) {
				int id = rs.getInt("id");
				String nome = rs.getString("nome");
				String descricao = rs.getString("descricao");
				Produto produto = new Produto(nome, descricao);
				produto.setId(id);
				produtos.add(produto);
			}
		}
	}

	public List<Produto> busca(Categoria categoria) throws SQLException {
		
		List<Produto> produtos = new ArrayList<>();
		String sql = "select * from Produto where categoria_id = ?";
		try(PreparedStatement stmt = con.prepareStatement(sql)){
			stmt.setInt(1, categoria.getId());
			stmt.execute();			
			tranformaResultadosEmProdutos(produtos, stmt);
		}
		return produtos;
	}
}
