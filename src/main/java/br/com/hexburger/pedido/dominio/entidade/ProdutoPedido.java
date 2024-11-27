package br.com.hexburger.pedido.dominio.entidade;

import java.math.BigDecimal;

public class ProdutoPedido {

    private final String id;

    private String nome;

    private String descricao;

    private final BigDecimal valor;

    private Categoria categoria;

    public ProdutoPedido(String id) {
        this.id = id;
        this.valor = BigDecimal.ZERO;
    }

    public ProdutoPedido(String id, String nome, String descricao, BigDecimal valor, Categoria categoria) {
        this.id = id;
        this.nome = nome;
        this.descricao = descricao;
        this.valor = valor;
        this.categoria = categoria;
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

}
