package br.com.hexburger.pedido.interfaceadapters.presenter;

import br.com.hexburger.pedido.dominio.entidade.Produto;
import br.com.hexburger.pedido.interfaceadapters.dto.ProdutoDTO;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

import static br.com.hexburger.pedido.dominio.entidade.Categoria.BEBIDA;
import static br.com.hexburger.pedido.dominio.entidade.Categoria.LANCHE;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;

class ProdutoPreseterTest {

    @Test
    void deveConverterDeEntidadeParaDTO() {

        Produto produto = new Produto(UUID.randomUUID().toString(), "Hamburguer", "Hamburguer de carne", BigDecimal.valueOf(15.00), LANCHE);
        ProdutoDTO produtoDTO = ProdutoPresenter.toDTO(produto);

        assertThat(produtoDTO.getId(), is(equalTo(produto.getId())));
        assertThat(produtoDTO.getNome(), is(equalTo(produto.getNome())));
        assertThat(produtoDTO.getDescricao(), is(equalTo(produto.getDescricao())));
        assertThat(produtoDTO.getValor(), is(equalTo(produto.getValor())));

    }

    @Test
    void deveConverterDeEntidadesParaDTOs() {

        Produto lanche = new Produto(UUID.randomUUID().toString(), "Hamburguer", "Hamburguer de carne", BigDecimal.valueOf(15.00), LANCHE);
        Produto bebida = new Produto(UUID.randomUUID().toString(), "Suco de laranja", "Suco de laranja", BigDecimal.valueOf(5.00), BEBIDA);
        List<ProdutoDTO> produtosDTO = ProdutoPresenter.toDTO(List.of(lanche, bebida));

        assertThat(produtosDTO, hasSize(2));
        assertThat(produtosDTO.get(0).getId(), is(equalTo(lanche.getId())));
        assertThat(produtosDTO.get(1).getId(), is(equalTo(bebida.getId())));

    }

}
