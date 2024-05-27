package br.gov.serratec.grupo05api.model;

import java.time.LocalDate;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import br.gov.serratec.grupo05api.config.Mapper;
import br.gov.serratec.grupo05api.dto.ClienteDto;
import br.gov.serratec.grupo05api.dto.ClienteEnderecoDto;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "cliente")
public class Cliente {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String email;
	private String nomeCompleto;
	private String cpf;
	private String telefone;
	private LocalDate dataNascimento;	
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "endereco_id")
	private Endereco endereco;	
	@JsonIgnore
	@OneToMany(mappedBy = "cliente")
	private List<Pedido> pedidos;

	public Cliente() {
		super();
	}
	
	public Cliente(Long id, String email, String nomeCompleto, String cpf, String telefone, LocalDate dataNascimento,
			Endereco endereco, List<Pedido> pedidos) {
		super();
		this.id = id;
		this.email = email;
		this.nomeCompleto = nomeCompleto;
		this.cpf = cpf;
		this.telefone = telefone;
		this.dataNascimento = dataNascimento;
		this.endereco = endereco;
		this.pedidos = pedidos;
	}
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getNomeCompleto() {
		return nomeCompleto;
	}

	public void setNomeCompleto(String nomeCompleto) {
		this.nomeCompleto = nomeCompleto;
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public String getTelefone() {
		return telefone;
	}

	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}

	public LocalDate getDataNascimento() {
		return dataNascimento;
	}

	public void setDataNascimento(LocalDate dataNascimento) {
		this.dataNascimento = dataNascimento;
	}

	public Endereco getEndereco() {
		return endereco;
	}

	public void setEndereco(Endereco endereco) {
		this.endereco = endereco;
	}

	public List<Pedido> getPedidos() {
		return pedidos;
	}

	public void setPedidos(List<Pedido> pedidos) {
		this.pedidos = pedidos;
	}

	public static ClienteEnderecoDto enderecoToDto(Cliente clienteEntity) {
		return Mapper.getMapper().convertValue(clienteEntity, ClienteEnderecoDto.class);
	}
	
	public static ClienteDto toDto(Cliente clienteEntity) {
		return Mapper.getMapper().convertValue(clienteEntity, ClienteDto.class);
	}
	
}