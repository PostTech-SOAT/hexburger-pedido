package br.com.hexburger.pedido.framework.repository;

import br.com.hexburger.pedido.framework.entidade.EPedido;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PedidoRepository extends JpaRepository<EPedido, String> {

    @Query(value = "SELECT * FROM PEDIDO WHERE status NOT IN ('FINALIZADO', 'CANCELADO') ORDER BY " +
            "CASE status WHEN 'PRONTO' THEN 1 " +
            "WHEN 'EM_PREPARACAO' THEN 2 " +
            "WHEN 'RECEBIDO' THEN 3 " +
            "ELSE 4 END, data_pedido ASC", nativeQuery = true)
    List<EPedido> findPedidosNaoFinalizados();

    @Modifying
    @Query(value = "UPDATE Pedido SET status = :statusPedido WHERE id = :idPedido", nativeQuery = true)
    void updateStatusPedido(String idPedido, String statusPedido);

}
