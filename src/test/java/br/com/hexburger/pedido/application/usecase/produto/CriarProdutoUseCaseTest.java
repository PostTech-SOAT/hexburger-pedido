package br.com.hexburger.pedido.application.usecase.produto;

import br.com.hexburger.pedido.application.interfacegateway.ProdutoGateway;
import br.com.hexburger.pedido.application.util.exception.ConflictException;
import br.com.hexburger.pedido.dominio.entidade.Produto;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.math.BigDecimal;
import java.util.Optional;
import java.util.UUID;

import static br.com.hexburger.pedido.dominio.entidade.Categoria.LANCHE;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.notNullValue;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

class CriarProdutoUseCaseTest {

    private CriarProdutoUseCase useCase;

    @Mock
    private ProdutoGateway produtoGateway;

    AutoCloseable openMocks;

    @BeforeEach
    void setUp() {

        openMocks = MockitoAnnotations.openMocks(this);
        useCase = new CriarProdutoUseCase(produtoGateway);

    }

    @Test
    void deveCriarProduto() {

        Produto produto = criarProduto();

        when(produtoGateway.criarProduto(any(Produto.class))).thenReturn(produto);

        Produto produtoCriado = useCase.criarProduto(produto);

        assertThat(produtoCriado, is(notNullValue()));
        assertThat(produto.getNome(), is(equalTo(produtoCriado.getNome())));
        assertThat(produto.getDescricao(), is(equalTo(produtoCriado.getDescricao())));
        assertThat(produto.getValor(), is(equalTo(produtoCriado.getValor())));
        assertThat(produto.getCategoria(), is(equalTo(produtoCriado.getCategoria())));

    }

    @Test
    void deveLancarExcecaoAoCriarProdutoComNomeExistente() {

        Produto produto = criarProduto();

        when(produtoGateway.buscarProdutoPorNome(any(String.class))).thenReturn(Optional.of(produto));

        assertThrows(ConflictException.class, () -> useCase.criarProduto(produto));

    }

    private Produto criarProduto() {

        return new Produto(UUID.randomUUID().toString(), "Hex Burger Para Testes do UseCase",
                "Pão e Hambuguer no formato hexagonal (Para Testes)", BigDecimal.valueOf(15.00), LANCHE);

    }

}
