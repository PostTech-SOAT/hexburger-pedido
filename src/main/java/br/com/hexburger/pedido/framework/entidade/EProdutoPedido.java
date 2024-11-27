package br.com.hexburger.pedido.framework.entidade;

import br.com.hexburger.pedido.interfaceadapters.entidadeadaptador.EProdutoPedidoInterface;
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
@Table(name = "ProdutoPedido")
public class EProdutoPedido implements EProdutoPedidoInterface {

    @Id
    private String id;

    private String nome;

    private String descricao;

    private BigDecimal valor;

    private String categoria;

}
