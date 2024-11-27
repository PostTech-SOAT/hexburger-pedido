package br.com.hexburger.pedido.application.usecase.produto;

import br.com.hexburger.pedido.application.interfacegateway.ProdutoGateway;
import br.com.hexburger.pedido.application.util.exception.ResourceNotFoundException;
import br.com.hexburger.pedido.dominio.entidade.Produto;
import br.com.hexburger.pedido.dominio.entidade.ProdutoPedido;
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

class BuscarProdutoPorIdUseCaseTest {

    private BuscarProdutoPorIdUseCase useCase;

    @Mock
    private ProdutoGateway produtoGateway;

    AutoCloseable openMocks;

    @BeforeEach
    void setUp() {

        openMocks = MockitoAnnotations.openMocks(this);
        useCase = new BuscarProdutoPorIdUseCase(produtoGateway);

    }

    @Test
    void deveBuscarProdutoPorId() {

        Produto produto = new Produto(UUID.randomUUID().toString(), "Hex Burger Para Testes",
                "Pão e Hambuguer no formato hexagonal", BigDecimal.valueOf(15.00), LANCHE);
        ProdutoPedido produtoPedido = new ProdutoPedido(produto.getId(), produto.getNome(),
                produto.getDescricao(), produto.getValor(), produto.getCategoria());

        when(produtoGateway.buscarProdutoPorId(any(String.class))).thenReturn(Optional.of(produto));

        ProdutoPedido produtoObtido = useCase.buscaProduto(produtoPedido);

        assertThat(produtoObtido, is(notNullValue()));
        assertThat(produtoObtido.getNome(), is(equalTo(produto.getNome())));
        assertThat(produto.getDescricao(), is(equalTo(produto.getDescricao())));
        assertThat(produto.getValor(), is(equalTo(produto.getValor())));
        assertThat(produto.getCategoria(), is(equalTo(produto.getCategoria())));

    }

    @Test
    void deveLancarExcecaoAoBuscarProdutoPorIdInexistente() {

        Produto produto = new Produto(UUID.randomUUID().toString(), "Hex Burger Para Testes",
                "Pão e Hambuguer no formato hexagonal", BigDecimal.valueOf(15.00), LANCHE);
        ProdutoPedido produtoPedido = new ProdutoPedido(produto.getId(), produto.getNome(),
                produto.getDescricao(), produto.getValor(), produto.getCategoria());

        when(produtoGateway.buscarProdutoPorId(any(String.class))).thenReturn(Optional.empty());

        assertThrows(ResourceNotFoundException.class, () -> useCase.buscaProduto(produtoPedido));

    }

 }