package br.com.hexburger.pedido.dominio.entidade;

import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.junit.jupiter.api.Assertions.assertThrows;

class ComboTest {

    @Test
    void deveCriarCombo() {

        Combo combo = new Combo(new ArrayList<>(criarProdutosPediodos()));

        combo.validaCombo();

        assertThat(combo, is(notNullValue()));
        assertThat(combo.getId(), is(notNullValue()));
        assertThat(combo.getProdutos(), hasSize(3));
        assertThat(combo.getValorTotal(), is(BigDecimal.valueOf(28.00)));

    }

    @Test
    void deveCriarComboParaExibir() {

        Combo combo = new Combo(UUID.randomUUID().toString(), new ArrayList<>(criarProdutosPediodos()), BigDecimal.valueOf(28.00));

        combo.validaCombo();

        assertThat(combo, is(notNullValue()));
        assertThat(combo.getId(), is(notNullValue()));
        assertThat(combo.getProdutos(), hasSize(3));
        assertThat(combo.getValorTotal(), is(BigDecimal.valueOf(28.00)));

    }

    @Test
    void deveLancarExcecaoAoCriarComboSemProdutos() {

        List<ProdutoPedido> produtoPedidos = new ArrayList<>();

        Combo combo = new Combo(produtoPedidos);

        assertThrows(IllegalArgumentException.class, combo::validaCombo);

    }

    @Test
    void deveLancarExcecaoAoCriarComboComListaDeProdutosNula() {

        Combo combo = new Combo(null);

        assertThrows(IllegalArgumentException.class, combo::validaCombo);

    }

    @Test
    void deveLancarExcecaoAoCriarComboComValorTotalZero() {

        Combo combo = new Combo(new ArrayList<>(List.of(new ProdutoPedido(UUID.randomUUID().toString(), "Hamburguer",
                "Hamburguer de carne", BigDecimal.ZERO, Categoria.LANCHE))));

        assertThrows(IllegalArgumentException.class, combo::validaCombo);

    }

    @Test
    void deveLancarExcecaoAoCriarComboComValorTotalNulo() {

        ProdutoPedido produtoPedido = new ProdutoPedido(UUID.randomUUID().toString(), "Hamburguer",
                "Hamburguer de carne", null, Categoria.LANCHE);
        ArrayList<ProdutoPedido> produtos = new ArrayList<>(List.of(produtoPedido));
        Combo combo = new Combo(UUID.randomUUID().toString(), produtos, null);

        assertThrows(IllegalArgumentException.class, combo::validaCombo);

    }

    @Test
    void deveLancarExcecaoAoCriarComboCasoHajaMaisDeUmProdutoDoMesmoTipo() {

        ProdutoPedido produtoPedido = new ProdutoPedido(UUID.randomUUID().toString(), "Hamburguer",
                "Hamburguer de carne", BigDecimal.valueOf(15.00), Categoria.LANCHE);
        ProdutoPedido produtoPedido2 = new ProdutoPedido(UUID.randomUUID().toString(), "Hamburguer 2",
                "Hamburguer de frango", BigDecimal.valueOf(12.00), Categoria.LANCHE);

        Combo combo = new Combo(new ArrayList<>(List.of(produtoPedido, produtoPedido2)));

        assertThrows(IllegalArgumentException.class, combo::validaCombo);

    }

    private List<ProdutoPedido> criarProdutosPediodos() {

        ProdutoPedido produtoPedido = new ProdutoPedido(UUID.randomUUID().toString(), "Hamburguer",
                "Hamburguer de carne", BigDecimal.valueOf(15.00), Categoria.LANCHE);
        ProdutoPedido produtoPedido2 = new ProdutoPedido(UUID.randomUUID().toString(), "Batata",
                "Batata Frita", BigDecimal.valueOf(8.00), Categoria.ACOMPANHAMENTO);
        ProdutoPedido produtoPedido3 = new ProdutoPedido(UUID.randomUUID().toString(), "Refrigerante",
                "Refrigerante com 2 cubos de Gelo", BigDecimal.valueOf(5.00), Categoria.BEBIDA);

        return List.of(produtoPedido, produtoPedido2, produtoPedido3);

    }

}
