package br.com.hexburger.pedido.application.usecase.produto;

import br.com.hexburger.pedido.application.interfacegateway.ProdutoGateway;
import br.com.hexburger.pedido.application.util.exception.ResourceNotFoundException;
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

import static br.com.hexburger.pedido.dominio.entidade.Categoria.LANCHE;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.notNullValue;
import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest
@ActiveProfiles("test")
class EditarProdutoUseCaseIT {

    private EditarProdutoUseCase useCase;

    private BuscarProdutosPorCategoriaUseCase buscarProdutosPorCategoriaUseCase;

    @Autowired
    private ProdutoRepository repository;

    @BeforeEach
    void setup() {

        ProdutoGateway produtoGateway = new ProdutoGatewayJPA(new ProdutoRepositorioImpl(repository));
        useCase = new EditarProdutoUseCase(produtoGateway);
        buscarProdutosPorCategoriaUseCase = new BuscarProdutosPorCategoriaUseCase(produtoGateway);

    }

    @Test
    void deveEditarProduto() {

        Produto produtoObtido = buscarProdutosPorCategoriaUseCase.buscarProdutosPorCategoria(LANCHE).getFirst();

        produtoObtido = new Produto(produtoObtido.getId(), "Hex Burger Para Testes (Edição UseCase)",
                "Pão e Hambuguer no formato hexagonal (Para Testes)", BigDecimal.valueOf(150.00), LANCHE);

        Produto produtoEditado = useCase.editarProduto(produtoObtido);

        assertThat(produtoEditado, is(notNullValue()));
        assertThat(produtoEditado.getId(), is(equalTo(produtoObtido.getId())));
        assertThat(produtoEditado.getNome(), is(equalTo("Hex Burger Para Testes (Edição UseCase)")));
        assertThat(produtoEditado.getDescricao(), is(equalTo("Pão e Hambuguer no formato hexagonal (Para Testes)")));
        assertThat(produtoEditado.getValor(), is(equalTo(BigDecimal.valueOf(150.00))));
        assertThat(produtoEditado.getCategoria(), is(equalTo(LANCHE)));

    }

    @Test
    void deveLancarExcecaoAoTentarEditarProdutoInexistente() {

        assertThrows(ResourceNotFoundException.class, () -> useCase.editarProduto(new Produto(UUID.randomUUID().toString(), "Hex Burger Para Testes do UseCase",
                "Pão e Hambuguer no formato hexagonal (Para Testes)", BigDecimal.valueOf(15.00), LANCHE)));

    }

}
