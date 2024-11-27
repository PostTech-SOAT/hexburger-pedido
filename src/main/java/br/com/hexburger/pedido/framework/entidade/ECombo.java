package br.com.hexburger.pedido.framework.entidade;

import br.com.hexburger.pedido.interfaceadapters.entidadeadaptador.EComboInterface;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.List;

@Getter
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "Combo")
public class ECombo implements EComboInterface {

    @Id
    private String id;

    @OneToMany(cascade = CascadeType.ALL)
    private List<EProdutoPedido> produtosPedido;

    private BigDecimal valorTotal;

}
