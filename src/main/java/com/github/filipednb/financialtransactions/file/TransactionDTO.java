package com.github.filipednb.financialtransactions.file;

import com.ancientprogramming.fixedformat4j.annotation.Field;
import com.ancientprogramming.fixedformat4j.annotation.Record;

@Record
public class TransactionDTO {

    private Integer tipo;
    private String data;
    private Integer valor;
    private String cpf;
    private String cartao;
    private String hora;
    private String donoLoja;
    private String nomeLoja;

    @Field(offset = 1, length = 1)
    public Integer getTipo() {
        return tipo;
    }

    public void setTipo(Integer tipo) {
        this.tipo = tipo;
    }

    @Field(offset = 2, length = 8)
    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    @Field(offset = 10, length = 10)
    public Integer getValor() {
        return valor;
    }

    public void setValor(Integer valor) {
        this.valor = valor;
    }

    @Field(offset = 20, length = 11)
    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    @Field(offset = 31, length = 12)
    public String getCartao() {
        return cartao;
    }

    public void setCartao(String cartao) {
        this.cartao = cartao;
    }

    @Field(offset = 43, length = 6)
    public String getHora() {
        return hora;
    }

    public void setHora(String hora) {
        this.hora = hora;
    }

    @Field(offset = 49, length = 14)
    public String getDonoLoja() {
        return donoLoja;
    }

    public void setDonoLoja(String donoLoja) {
        this.donoLoja = donoLoja;
    }

    @Field(offset = 43, length = 19)
    public String getNomeLoja() {
        return nomeLoja;
    }

    public void setNomeLoja(String nomeLoja) {
        this.nomeLoja = nomeLoja;
    }
}
