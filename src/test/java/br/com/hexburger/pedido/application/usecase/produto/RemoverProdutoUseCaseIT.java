package br.com.hexburger.pedido.application.usecase.produto;

import br.com.hexburger.pedido.application.interfacegateway.ProdutoGateway;
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
import java.util.Optional;
import java.util.UUID;

import static br.com.hexburger.pedido.dominio.entidade.Categoria.LANCHE;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest
@ActiveProfiles("test")
class RemoverProdutoUseCaseIT {

    private RemoverProdutoUseCase useCase;

    private CriarProdutoUseCase criarProdutoUseCase;

    @Autowired
    private ProdutoRepository repository;

    @BeforeEach
    void setup() {

        ProdutoGateway produtoGateway = new ProdutoGatewayJPA(new ProdutoRepositorioImpl(repository));
        useCase = new RemoverProdutoUseCase(produtoGateway);
        criarProdutoUseCase = new CriarProdutoUseCase(produtoGateway);


    }

    @Test
    void deveRemoverProduto() {

        String produtoId = UUID.randomUUID().toString();
        criarProdutoUseCase.criarProduto(new Produto(produtoId, "Hex Burger Para Ser Removido",
                "Hex Burger que será apagado", BigDecimal.valueOf(15.00), LANCHE));

        useCase.removerProduto(produtoId);

        assertThat(repository.findById(produtoId), is(Optional.empty()));

    }

    @Test
    void deveLancarExcecaoAoRemoverProdutoInexistente() {

        String produtoId = "idInexistente";

        assertThrows(Exception.class, () -> useCase.removerProduto(produtoId));

    }

}
