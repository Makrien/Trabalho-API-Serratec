package br.gov.serratec.grupo05api.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.gov.serratec.grupo05api.dto.EnderecoCadastroDto;
import br.gov.serratec.grupo05api.dto.EnderecoDto;
import br.gov.serratec.grupo05api.model.Endereco;
import br.gov.serratec.grupo05api.repository.EnderecoRepository;

@Service
public class EnderecoService {
	
	@Autowired
	private EnderecoRepository repositorio;
	
	@Autowired
	private ConverteDados conversor;
	
	public List<EnderecoDto> obterTodos() {
		List<EnderecoDto> enderecos = new ArrayList<>();
		repositorio.findAll().forEach(e -> {
			enderecos.add(e.toDto());
		});
		return enderecos;
	}
	
	public EnderecoDto cadastrarEndereco(EnderecoCadastroDto enderecoCadastro) {
		String json = ConsumoApi.obterDados(enderecoCadastro.cep());
		EnderecoDto endereco = conversor.converter(json, EnderecoDto.class);
		
		EnderecoDto novoEndereco = new EnderecoDto(
				endereco.id(),
				endereco.cep(),
				endereco.rua(),
				endereco.bairro(), 
				endereco.cidade(), 
				enderecoCadastro.numero(), 
				enderecoCadastro.complemento(), 
				endereco.uf(), 
				endereco.cliente()); 
		
		Endereco enderecoSalvo = repositorio.save(novoEndereco.toEntity());
	    return enderecoSalvo.toDto();
	}
	
	public Optional<EnderecoDto> atualizarEndereco(Long id, EnderecoDto enderecoAtualizado) {
		 Endereco enderecoEntity = enderecoAtualizado.toEntity();

	        if (repositorio.existsById(id)) {
	            enderecoEntity.setId(id);
	            repositorio.save(enderecoEntity);
	            return Optional.of(enderecoEntity.toDto());
	        }
	        return Optional.empty();
	 }
	
	public boolean excluiEndereco(Long id) {
		if (repositorio.existsById(id)) {
			repositorio.deleteById(id);
			return true;
		}
		
		return false;
	}
	
	public Optional<EnderecoDto> obterEnderecoPorId(Long id) {
		Optional<Endereco> endereco = repositorio.findById(id);
		
		if (endereco.isPresent()) {
			return Optional.of(endereco.get().toDto());
		}
		
		return Optional.empty();
	}
}

