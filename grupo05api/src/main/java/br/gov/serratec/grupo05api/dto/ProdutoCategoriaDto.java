package br.gov.serratec.grupo05api.dto;

import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import br.gov.serratec.grupo05api.model.Categoria;
import br.gov.serratec.grupo05api.model.Produto;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;

@JsonIgnoreProperties(ignoreUnknown = true)
public record ProdutoCategoriaDto(
		Long id,
		@NotNull(message = "Nome não pode ser nulo")
	    @NotEmpty(message = "Nome não pode ser vazio")
	    @Size(min = 2, max = 100, message = "Nome deve ter entre 2 e 100 caracteres")
	    String nome,	    
	    @NotNull(message = "Descrição não pode ser nula")
	    @NotEmpty(message = "Descrição não pode ser vazia")
	    @Size(min = 2, max = 255, message = "Descrição deve ter entre 2 e 255 caracteres")
	    String descricao,	    
	    @NotNull(message = "Quantidade em estoque não pode ser nula")
	    @Positive(message = "Quantidade em estoque deve ser positiva")
	    Long qtdEstoque,	    
	    @NotNull(message = "Data de cadastro não pode ser nula")
	    LocalDate dataCadastro,	    
	    @NotNull(message = "Valor unitário não pode ser nulo")
	    @Positive(message = "Valor unitário deve ser positivo")
	    Double valorUnitario,	    
	    String imagem,	    
	    @NotNull(message = "Categoria não pode ser nula")
	    Long categoriaId) {
	
	public Produto toEntity(Categoria categoria) {
        Produto produto = new Produto();
        produto.setNome(this.nome);
        produto.setDescricao(this.descricao);
        produto.setQtdEstoque(this.qtdEstoque);
        produto.setDataCadastro(this.dataCadastro);
        produto.setValorUnitario(this.valorUnitario);
        produto.setImagem(this.imagem);
        produto.setCategoria(categoria);
        return produto;
    }
}
