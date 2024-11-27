package br.com.hexburger.pedido.dominio.entidade;

import java.math.BigDecimal;
import java.util.UUID;

public class Produto {

    private final String id;

    private final String nome;

    private final String descricao;

    private final BigDecimal valor;

    private final Categoria categoria;

    public Produto(String id, String nome, String descricao, BigDecimal valor, Categoria categoria) {
        this.id = id;
        this.nome = nome;
        this.descricao = descricao;
        this.valor = valor;
        this.categoria = categoria;
        validaProduto();
    }

    public Produto(String nome, String descricao, BigDecimal valor, Categoria categoria) {
        this.id = UUID.randomUUID().toString();
        this.nome = nome;
        this.descricao = descricao;
        this.valor = valor;
        this.categoria = categoria;
        validaProduto();
    }

    public String getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public String getDescricao() {
        return descricao;
    }

    public BigDecimal getValor() {
        return valor;
    }

    public Categoria getCategoria() {
        return categoria;
    }

    private void validaProduto() {
        if (!validaNome() || !validaDescricao() || !validaValor() || !validaCategoria()) {
            throw new IllegalArgumentException("Produto inválido");
        }
    }

    private boolean validaNome() {
        return nome != null && !nome.isEmpty();
    }

    private boolean validaDescricao() {
        return descricao != null && !descricao.isEmpty();
    }

    private boolean validaValor() {
        return valor != null && valor.compareTo(BigDecimal.ZERO) > 0;
    }

    private boolean validaCategoria() {
        return categoria != null;
    }

}
