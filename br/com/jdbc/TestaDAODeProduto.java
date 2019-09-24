package br.com.jdbc;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import br.com.jdbc.DAO.ProdutosDAO;
import br.com.jdbc.modelo.Produto;

public class TestaDAODeProduto {
/*abre uma conexao
 * cria o DAO, objeto que acessa os dados
 * salva e busca*/
	
	public static void main(String[] args) throws SQLException {
	
		Produto mesa = new Produto("mesa Vermelha", "mesa com 4 cadeiras");

		try (Connection con = new ConnectionPool().getConnection()) {
			ProdutosDAO dao = new ProdutosDAO(con);
			dao.salva(mesa);
			List<Produto> produtos = dao.lista();
			
			for (Produto produto : produtos) {
				System.out.println(produto);
			}
			
		}
		

	}
	

}
