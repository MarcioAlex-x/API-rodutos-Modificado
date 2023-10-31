package br.com.ehmf.AppProdutos.service;

import java.util.List;
import java.util.Optional;


import org.springframework.stereotype.Service;

import br.com.ehmf.AppProdutos.exception.ResourceNotFoundException;
import br.com.ehmf.AppProdutos.model.Estoque;
import br.com.ehmf.AppProdutos.repository.EstoqueRepository;
import br.com.ehmf.AppProdutos.service.interfaces.EstoqueServiceInterface;

@Service
public class EstoqueService implements EstoqueServiceInterface {
	
	private EstoqueRepository estoqueRepository;
	
	
	public EstoqueService(EstoqueRepository estoqueRepository) {
		this.estoqueRepository = estoqueRepository;
	}
	
	@Override
	public Estoque save(Estoque estoque) {
		return estoqueRepository.save(estoque);
	}

	@Override
	public Optional<Estoque> getById(Long id) {
		return estoqueRepository.findById(id);
	}

	@Override
	public List<Estoque> getAll() {
		return estoqueRepository.findAll();
	}

	@Override
	public Estoque update(Estoque estoque) {
		Optional<Estoque> upEstoque = estoqueRepository.findById(estoque.getId());
		
		if(upEstoque.isPresent()) {
			Estoque newEstoque = upEstoque.get();
			newEstoque.setProduto(estoque.getProduto());
			newEstoque.setQuantidade(estoque.getQuantidade());
			return estoqueRepository.save(newEstoque);
		}
		return estoque;
	}

	@Override
	public void delete(Long id) {
		estoqueRepository.deleteById(id);
	}

	@Override
	public Estoque addQuantidade(Long id, int quantidade) {
		return estoqueRepository.findById(id)
				.map(estoque -> {
					estoque.setQuantidade(estoque.getQuantidade() + quantidade);
					return estoqueRepository.save(estoque);
				}).orElseThrow(() -> new ResourceNotFoundException("Estoque não encontrado no ID: " + id));
	}

	@Override
	public Estoque delQuantidade(Long id, int quantidade) {
		return estoqueRepository.findById(id)
				.map(estoque -> {
					estoque.setQuantidade(estoque.getQuantidade() - quantidade);
					return estoqueRepository.save(estoque);
				}).orElseThrow(() -> new ResourceNotFoundException("Estoque não encontrado no ID: " + id));
	}

}
