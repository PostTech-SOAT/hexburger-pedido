package br.com.hexburger.pedido.framework.entidade;

import br.com.hexburger.pedido.interfaceadapters.entidadeadaptador.EProdutoInterface;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Getter
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "Produto")
public class EProduto implements EProdutoInterface {

    @Id
    private String id;

    @Column(unique = true)
    private String nome;

    private String descricao;

    private BigDecimal valor;

    private String categoria;

}
