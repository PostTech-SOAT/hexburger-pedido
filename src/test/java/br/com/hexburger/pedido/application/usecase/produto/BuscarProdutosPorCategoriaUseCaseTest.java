package br.com.hexburger.pedido.application.usecase.produto;

import br.com.hexburger.pedido.application.interfacegateway.ProdutoGateway;
import br.com.hexburger.pedido.dominio.entidade.Categoria;
import br.com.hexburger.pedido.dominio.entidade.Produto;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

import static br.com.hexburger.pedido.dominio.entidade.Categoria.LANCHE;
import static br.com.hexburger.pedido.dominio.entidade.Categoria.SOBREMESA;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.not;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.empty;
import static org.hamcrest.Matchers.hasSize;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

class BuscarProdutosPorCategoriaUseCaseTest {

    private BuscarProdutosPorCategoriaUseCase useCase;

    @Mock
    private ProdutoGateway produtoGateway;

    AutoCloseable openMocks;

    @BeforeEach
    void setUp() {

        openMocks = MockitoAnnotations.openMocks(this);
        useCase = new BuscarProdutosPorCategoriaUseCase(produtoGateway);

    }

    @Test
    void deveBuscarProdutosPorCategoria() {

        when(produtoGateway.buscarProdutosPorCategoria(any(Categoria.class))).thenReturn(criaListaDeLanches());

        List<Produto> produtosBuscados = useCase.buscarProdutosPorCategoria(LANCHE);

        assertThat(produtosBuscados, is(not(empty())));
        assertThat(produtosBuscados, hasSize(2));
        assertThat(produtosBuscados.stream().map(Produto::getCategoria).distinct().toList(), hasSize(1));

    }

    @Test
    void deveBuscarProdutosPorCategoriaSemProdutos() {

        when(produtoGateway.buscarProdutosPorCategoria(any(Categoria.class))).thenReturn(List.of());

        List<Produto> produtosBuscados = useCase.buscarProdutosPorCategoria(SOBREMESA);

        assertThat(produtosBuscados, is(empty()));

    }

    private List<Produto> criaListaDeLanches() {

        Produto produto = new Produto(UUID.randomUUID().toString(), "Hex Burger",
                "Pão e Hambuguer no formato hexagonal", BigDecimal.valueOf(15.00), LANCHE);

        Produto produto2 = new Produto(UUID.randomUUID().toString(), "Hex Burger Duplo",
                "Pão e dois Hambugueres no formato hexagonal", BigDecimal.valueOf(25.00), LANCHE);

        return List.of(produto, produto2);

    }
}
