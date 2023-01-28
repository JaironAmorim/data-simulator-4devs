package com.jaironamorim.datasimulator4devs.model.dto.people;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.jaironamorim.datasimulator4devs.model.dto.creditcard.CreditCardDto;
import lombok.Data;
@Data
public class PeopleDto {
    @JsonProperty("nome")
    private String nome;
    @JsonProperty("idade")
    private Integer idade;
    @JsonProperty("cpf")
    private String cpf;
    @JsonProperty("rg")
    private String rg;
    @JsonProperty("data_nasc")
    private String dataNasc;
    @JsonProperty("sexo")
    private String sexo;
    @JsonProperty("signo")
    private String signo;
    @JsonProperty("mae")
    private String mae;
    @JsonProperty("pai")
    private String pai;
    @JsonProperty("email")
    private String email;
    @JsonProperty("senha")
    private String senha;
    @JsonProperty("cep")
    private String cep;
    @JsonProperty("endereco")
    private String endereco;
    @JsonProperty("numero")
    private Integer numero;
    @JsonProperty("bairro")
    private String bairro;
    @JsonProperty("cidade")
    private String cidade;
    @JsonProperty("estado")
    private String estado;
    @JsonProperty("telefone_fixo")
    private String telefoneFixo;
    @JsonProperty("celular")
    private String celular;
    @JsonProperty("altura")
    private String altura;
    @JsonProperty("peso")
    private Integer peso;
    @JsonProperty("tipo_sanguineo")
    private String tipoSanguineo;
    @JsonProperty("cor")
    private String cor;
    @JsonProperty("cartaoDeCredito")
    private CreditCardDto cartaoDeCredito;
}
