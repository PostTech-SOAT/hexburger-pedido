package br.com.hexburger.pedido.application.usecase.produto;

import br.com.hexburger.pedido.application.interfacegateway.ProdutoGateway;
import br.com.hexburger.pedido.application.util.exception.ResourceNotFoundException;
import br.com.hexburger.pedido.dominio.entidade.Categoria;
import br.com.hexburger.pedido.dominio.entidade.ProdutoPedido;
import br.com.hexburger.pedido.framework.repository.ProdutoRepositorioImpl;
import br.com.hexburger.pedido.framework.repository.ProdutoRepository;
import br.com.hexburger.pedido.interfaceadapters.gateway.ProdutoGatewayJPA;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.math.BigDecimal;

import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest
@ActiveProfiles("test")
class BuscarProdutoPorIdUseCaseIT {

    private BuscarProdutoPorIdUseCase useCase;

    @Autowired
    private ProdutoRepository repository;

    @BeforeEach
    void setup() {

        ProdutoGateway produtoGateway = new ProdutoGatewayJPA(new ProdutoRepositorioImpl(repository));
        useCase = new BuscarProdutoPorIdUseCase(produtoGateway);

    }


    @Test
    void deveBuscarProdutoPorId() {

        ProdutoPedido produtoPedido = new ProdutoPedido("f55f733e-aeb3-4955-87d7-b521895cae80", "Hex Fries",
                "Batatas fritas crocantes", new BigDecimal("8.00"), Categoria.ACOMPANHAMENTO);

        ProdutoPedido produtoBuscado = useCase.buscaProduto(produtoPedido);

        assertThat(produtoBuscado.getId(), is(not(equalTo(produtoPedido.getId()))));
        assertThat(produtoBuscado.getNome(), is(equalTo(produtoPedido.getNome())));
        assertThat(produtoBuscado.getDescricao(), is(equalTo(produtoPedido.getDescricao())));
        assertThat(produtoBuscado.getValor(), is(equalTo(produtoPedido.getValor())));
        assertThat(produtoBuscado.getCategoria(), is(equalTo(produtoPedido.getCategoria())));

    }

    @Test
    void deveLancarExcecaoAoBuscarProdutoInexistente() {

        ProdutoPedido produtoPedido = new ProdutoPedido("idInexitente", "Hex Fries",
                "Batatas fritas crocantes", new BigDecimal("8.00"), Categoria.ACOMPANHAMENTO);

        ResourceNotFoundException exception = assertThrows(ResourceNotFoundException.class, () -> useCase.buscaProduto(produtoPedido));

        assertThat(exception.getMessage(), is(equalTo("Produto não existente")));

    }

}
