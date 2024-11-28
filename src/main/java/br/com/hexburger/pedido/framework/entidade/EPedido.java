package br.com.hexburger.pedido.framework.entidade;

import br.com.hexburger.pedido.interfaceadapters.entidadeadaptador.EPedidoInterface;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.Generated;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "Pedido")
public class EPedido implements EPedidoInterface {

    @Id
    private String id;

    @Generated
    @Column(columnDefinition = "serial", updatable = false)
    private Integer codigo;

    @OneToMany(cascade = CascadeType.ALL)
    private List<ECombo> combos;

    private BigDecimal valorTotal;

    @Column(name = "cpf_cliente")
    private String cpfCliente;

    private String status;

    private String statusPagamento;

    private LocalDateTime dataPedido;

    private String qrCode;

    private String idExternoPagamento;

    public EPedido(String id, List<ECombo> combos, BigDecimal valorTotal, String cpfCliente, String status,
                   LocalDateTime dataPedido, String qrCode, String idExternoPagamento) {
        this.id = id;
        this.combos = combos;
        this.valorTotal = valorTotal;
        this.cpfCliente = cpfCliente;
        this.status = status;
        this.dataPedido = dataPedido;
        this.qrCode = qrCode;
        this.idExternoPagamento = idExternoPagamento;
    }

}
