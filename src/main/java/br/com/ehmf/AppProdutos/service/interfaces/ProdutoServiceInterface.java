package br.com.ehmf.AppProdutos.service.interfaces;

import java.util.List;
import java.util.Optional;

import br.com.ehmf.AppProdutos.model.Produto;

public interface ProdutoServiceInterface {
	Produto save(Produto produto);
	Optional<Produto> getById(Long id);
	List<Produto> getAll();
	Produto update(Long id, Produto produto);
	void delete(Long id);
}
