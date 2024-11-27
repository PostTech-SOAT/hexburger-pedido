package br.com.hexburger.pedido.application.usecase.produto;

import br.com.hexburger.pedido.application.interfacegateway.ProdutoGateway;
import br.com.hexburger.pedido.dominio.entidade.Categoria;
import br.com.hexburger.pedido.dominio.entidade.Produto;
import br.com.hexburger.pedido.framework.repository.ProdutoRepositorioImpl;
import br.com.hexburger.pedido.framework.repository.ProdutoRepository;
import br.com.hexburger.pedido.interfaceadapters.gateway.ProdutoGatewayJPA;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.notNullValue;

@SpringBootTest
@ActiveProfiles("test")
class BuscarProdutosPorCategoriaUseCaseIT {

    private BuscarProdutosPorCategoriaUseCase useCase;

    @Autowired
    private ProdutoRepository repository;

    @BeforeEach
    void setup() {

        ProdutoGateway produtoGateway = new ProdutoGatewayJPA(new ProdutoRepositorioImpl(repository));
        useCase = new BuscarProdutosPorCategoriaUseCase(produtoGateway);

    }

    @Test
    void deveBuscarProdutoPorCategoria() {

        List<Produto> produtos = useCase.buscarProdutosPorCategoria(Categoria.SOBREMESA);

        assertThat(produtos, is(notNullValue()));
        assertThat(produtos, hasSize(2));

    }

}
