package br.com.ehmf.AppProdutos.service;

import java.util.List;
import java.util.Optional;


import org.springframework.stereotype.Service;

import br.com.ehmf.AppProdutos.model.Produto;
import br.com.ehmf.AppProdutos.repository.ProdutoRepository;
import br.com.ehmf.AppProdutos.service.interfaces.ProdutoServiceInterface;

@Service
public class ProdutoService implements ProdutoServiceInterface {

	private ProdutoRepository produtoRepository;
	
	
	public ProdutoService(ProdutoRepository produtoRepository) {
		this.produtoRepository = produtoRepository;
	}
	
	@Override
	public Produto save(Produto produto) {
		return produtoRepository.save(produto);
	}

	@Override
	public Optional<Produto> getById(Long id) {
		return produtoRepository.findById(id);
	}

	@Override
	public List<Produto> getAll() {
		return produtoRepository.findAll();
	}

	@Override
	public Produto update(Long id, Produto produto) {
		//encontrei o produto
		Optional<Produto> upProduto = produtoRepository.findById(id);
		
		//se ele existir, atualizar:
		if(upProduto.isPresent()) {
			Produto newProduto = upProduto.get();
			newProduto.setCodigoBarras(produto.getCodigoBarras());
			newProduto.setNome(produto.getNome());
			newProduto.setPreco(produto.getPreco());
			return produtoRepository.save(newProduto);
		}
		return produto;
	}

	@Override
	public void delete(Long id) {
		produtoRepository.deleteById(id);
	}

}
