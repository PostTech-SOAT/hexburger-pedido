package br.com.hexburger.pedido.framework.repository;

import br.com.hexburger.pedido.framework.entidade.ECombo;
import br.com.hexburger.pedido.framework.entidade.EPedido;
import br.com.hexburger.pedido.framework.entidade.EProdutoPedido;
import br.com.hexburger.pedido.interfaceadapters.entidadeadaptador.EPedidoInterface;
import br.com.hexburger.pedido.interfaceadapters.gateway.repositorydto.PedidoRepositoryDTO;
import br.com.hexburger.pedido.interfaceadapters.repositorioadaptador.PedidoRepositorioAdaptador;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Component
@Transactional
@AllArgsConstructor
public class PedidoRepositorioImpl implements PedidoRepositorioAdaptador {

    private final PedidoRepository repository;

    @Override
    public EPedidoInterface criarPedido(PedidoRepositoryDTO pedidoRepositoryDTO) {
        List<ECombo> eCombos = pedidoRepositoryDTO.getCombos().stream().map(combo -> new ECombo(combo.getId(), combo.getProdutos().stream().map(p ->
                new EProdutoPedido(p.getId(), p.getNome(), p.getDescricao(), p.getValor(), p.getCategoria().name())).toList(),
                combo.getValorTotal())).toList();
        EPedido ePedido = new EPedido(pedidoRepositoryDTO.getId(), eCombos, pedidoRepositoryDTO.getValorTotal(), pedidoRepositoryDTO.getCpfCliente(), pedidoRepositoryDTO.getStatus().name(),
                pedidoRepositoryDTO.getDataPedido(), pedidoRepositoryDTO.getQrCode(), pedidoRepositoryDTO.getIdExternoPagamento());
        return repository.save(ePedido);
    }

    @Override
    public List<? extends EPedidoInterface> buscarPedidos() {
        return repository.findPedidosNaoFinalizados();
    }

    @Override
    public void atualizarStatusPedido(String idPedido, String statusPedido) {
        repository.updateStatusPedido(idPedido, statusPedido);
    }

}
