package br.gov.serratec.grupo05api.dto;

import java.time.LocalDate;

import br.gov.serratec.grupo05api.model.Produto;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;

public record ProdutoDto(
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
        String dataCadastro,
        
        @NotNull(message = "Valor unitário não pode ser nulo")
        @Positive(message = "Valor unitário deve ser positivo")
        Double valorUnitario,
        String imagem,
        @NotNull(message = "Categoria não pode ser nula")
        CategoriaDto categoria) {
	
	public Produto toEntity() {
		Produto produto = new Produto();
        produto.setId(id);
        produto.setNome(nome);
        produto.setDescricao(descricao);
        produto.setQtdEstoque(qtdEstoque);
        produto.setDataCadastro(LocalDate.parse(this.dataCadastro));
        produto.setValorUnitario(this.valorUnitario);
        produto.setImagem(this.imagem);
        if (this.categoria != null) {
            produto.setCategoria(this.categoria.toEntity());
        }
        return produto;
        
    }

	public static ProdutoDto toDto(Produto produtoEntity) {
        return new ProdutoDto(
                produtoEntity.getId(),
                produtoEntity.getNome(),
                produtoEntity.getDescricao(),
                produtoEntity.getQtdEstoque(),
                produtoEntity.getDataCadastro().toString(),
                produtoEntity.getValorUnitario(),
                produtoEntity.getImagem(),
                CategoriaDto.toDto(produtoEntity.getCategoria())
        );
    }

}
