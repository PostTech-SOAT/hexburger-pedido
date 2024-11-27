package br.com.hexburger.pedido.application.usecase.produto;

import br.com.hexburger.pedido.application.interfacegateway.ProdutoGateway;
import br.com.hexburger.pedido.application.util.exception.ResourceNotFoundException;
import br.com.hexburger.pedido.dominio.entidade.Produto;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.math.BigDecimal;
import java.util.Optional;
import java.util.UUID;

import static br.com.hexburger.pedido.dominio.entidade.Categoria.LANCHE;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.notNullValue;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

class EditarProdutoUseCaseTest {

    private EditarProdutoUseCase useCase;

    @Mock
    private ProdutoGateway produtoGateway;

    AutoCloseable openMocks;

    @BeforeEach
    void setUp() {

        openMocks = MockitoAnnotations.openMocks(this);
        useCase = new EditarProdutoUseCase(produtoGateway);

    }


    @Test
    void deveEditarProduto() {

        String produtoId = UUID.randomUUID().toString();

        Produto produto = new Produto(produtoId, "Hex Burger Para Testes",
                "Pão e Hambuguer no formato hexagonal", BigDecimal.valueOf(15.00), LANCHE);

        Produto novoProduto = new Produto(produtoId, "Hex Burger Para Testes (Edição UseCase)",
                "Pão e Hambuguer no formato hexagonal (Para Testes)", BigDecimal.valueOf(150.00), LANCHE);

        when(produtoGateway.buscarProdutoPorId(any(String.class))).thenReturn(Optional.of(produto));
        when(produtoGateway.editarProduto(any(Produto.class))).thenReturn(novoProduto);

        Produto produtoEditado = useCase.editarProduto(produto);

        assertThat(produtoEditado, is(notNullValue()));
        assertThat(produtoEditado.getId(), is(equalTo(produto.getId())));
        assertThat(produtoEditado.getNome(), is(equalTo("Hex Burger Para Testes (Edição UseCase)")));
        assertThat(produtoEditado.getDescricao(), is(equalTo("Pão e Hambuguer no formato hexagonal (Para Testes)")));
        assertThat(produtoEditado.getValor(), is(equalTo(BigDecimal.valueOf(150.00))));
        assertThat(produtoEditado.getCategoria(), is(equalTo(LANCHE)));

    }

    @Test
    void deveLancarExcecaoAoTentarEditarProdutoInexistente() {

        Produto produto = new Produto(UUID.randomUUID().toString(), "Hex Burger Para Testes",
                "Pão e Hambuguer no formato hexagonal", BigDecimal.valueOf(15.00), LANCHE);

        when(produtoGateway.buscarProdutoPorId(any(String.class))).thenReturn(Optional.empty());

        assertThrows(ResourceNotFoundException.class, () -> useCase.editarProduto(produto));

    }

}
