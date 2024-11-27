package br.com.hexburger.pedido.application.usecase.produto;

import br.com.hexburger.pedido.application.interfacegateway.ProdutoGateway;
import br.com.hexburger.pedido.application.util.exception.ConflictException;
import br.com.hexburger.pedido.dominio.entidade.Produto;
import br.com.hexburger.pedido.framework.repository.ProdutoRepositorioImpl;
import br.com.hexburger.pedido.framework.repository.ProdutoRepository;
import br.com.hexburger.pedido.interfaceadapters.gateway.ProdutoGatewayJPA;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.math.BigDecimal;
import java.util.UUID;

import static br.com.hexburger.pedido.dominio.entidade.Categoria.ACOMPANHAMENTO;
import static br.com.hexburger.pedido.dominio.entidade.Categoria.LANCHE;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.notNullValue;
import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest
@ActiveProfiles("test")
class CriarProdutoUseCaseIT {

    private CriarProdutoUseCase useCase;

    @Autowired
    private ProdutoRepository repository;

    @BeforeEach
    void setup() {

        ProdutoGateway produtoGateway = new ProdutoGatewayJPA(new ProdutoRepositorioImpl(repository));
        useCase = new CriarProdutoUseCase(produtoGateway);

    }

    @Test
    void deveCriarProduto() {

        Produto produto = criarProduto();

        Produto produtoCriado = useCase.criarProduto(produto);

        assertThat(produtoCriado, is(notNullValue()));
        assertThat(produto.getNome(), is(equalTo(produtoCriado.getNome())));
        assertThat(produto.getDescricao(), is(equalTo(produtoCriado.getDescricao())));
        assertThat(produto.getValor(), is(equalTo(produtoCriado.getValor())));
        assertThat(produto.getCategoria(), is(equalTo(produtoCriado.getCategoria())));

    }

    @Test
    void deveLancarExcecaoAoCriarProdutoComNomeExistente() {

        Produto produto = new Produto(UUID.randomUUID().toString(), "Hex Fries",
                "Batatas fritas crocantes com nome repetido", BigDecimal.valueOf(10.00), ACOMPANHAMENTO);

        assertThrows(ConflictException.class, () -> useCase.criarProduto(produto));

    }

    private Produto criarProduto() {

        return new Produto(UUID.randomUUID().toString(), "Hex Burger Para Testes do UseCase",
                "Pão e Hambuguer no formato hexagonal (Para Testes)", BigDecimal.valueOf(15.00), LANCHE);

    }

}
