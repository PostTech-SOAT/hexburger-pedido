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
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class RemoverProdutoUseCaseTest {

    private RemoverProdutoUseCase useCase;

    @Mock
    private ProdutoGateway produtoGateway;

    AutoCloseable openMocks;

    @BeforeEach
    void setUp() {

        openMocks = MockitoAnnotations.openMocks(this);
        useCase = new RemoverProdutoUseCase(produtoGateway);

    }

    @Test
    void deveRemoverProduto() {

        String produtoId = UUID.randomUUID().toString();

        Produto produto = new Produto(produtoId, "Hex Burger Para Testes",
                "Pão e Hambuguer no formato hexagonal", BigDecimal.valueOf(15.00), LANCHE);

        when(produtoGateway.buscarProdutoPorId(any(String.class))).thenReturn(Optional.of(produto));

        useCase.removerProduto(produtoId);

        verify(produtoGateway, times(1)).removerProduto(produtoId);

    }

    @Test
    void deveLancarExcecaoQuandoProdutoNaoEncontrado() {

        when(produtoGateway.buscarProdutoPorId(any(String.class))).thenReturn(Optional.empty());

        assertThrows(ResourceNotFoundException.class, () -> useCase.removerProduto(UUID.randomUUID().toString()));

    }

}
