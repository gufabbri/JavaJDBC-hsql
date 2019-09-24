package br.com.jdbc.modelo;

import java.util.ArrayList;
import java.util.List;

public class Categoria {

	private Integer id;
	private String nome;
	private List<Produto> produtos = new ArrayList<>();

	public Categoria(int id, String nome) {
		this.id = id;
		this.nome = nome;
	}

	public String getNome() {
		return this.nome;
	}
	
	public List<Produto> getProdutos() {
		return produtos;
	}

	public Integer getId() {
		return id;
	}

	public void adiciona(Produto p) {	
		produtos.add(p);
	}

}
