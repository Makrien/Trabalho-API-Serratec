package br.gov.serratec.grupo05api.dto;

import java.time.LocalDate;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import br.gov.serratec.grupo05api.model.Cliente;
import br.gov.serratec.grupo05api.model.Endereco;

@JsonIgnoreProperties(ignoreUnknown = true)
public record ClienteDto(
        Long id,
        @NotBlank(message = "O email não deve ser nulo")
        @Email(message = "O email deve ser válido")
        String email,
        @NotBlank(message = "O nome completo não deve estar em branco")
        String nomeCompleto,
        @NotNull(message = "O CPF não deve ser nulo")
        @Pattern(regexp = "\\d{11}", message = "O CPF deve conter exatamente 11 números")
        String cpf,
        @NotNull(message = "O telefone não deve ser nulo")
        String telefone,
        @NotNull(message = "A data de nascimento não deve ser nula")
        LocalDate dataNascimento,
        @NotNull(message = "O endereço não deve ser nulo")
        Endereco endereco) {

    public Cliente toEntity() {
        Cliente cliente = new Cliente();
        cliente.setId(this.id);
        cliente.setEmail(this.email);
        cliente.setNomeCompleto(this.nomeCompleto);
        cliente.setCpf(this.cpf);
        cliente.setTelefone(this.telefone);
        cliente.setDataNascimento(dataNascimento);
        cliente.setEndereco(this.endereco);
        return cliente;
    }
}
