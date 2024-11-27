package br.com.hexburger.pedido.framework.repository;

import br.com.hexburger.pedido.dominio.entidade.Categoria;
import br.com.hexburger.pedido.framework.entidade.EProduto;
import br.com.hexburger.pedido.interfaceadapters.entidadeadaptador.EProdutoInterface;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.hasSize;

@SpringBootTest
@ActiveProfiles("test")
class ProdutoRepositorioImplIT {

    @Autowired
    private ProdutoRepository repository;

    private ProdutoRepositorioImpl produtoRepositorio;

    @BeforeEach
    void setup() {

        produtoRepositorio = new ProdutoRepositorioImpl(repository);

    }

    @Test
    void deveCriarProduto() {

        EProduto produto = criarProduto();

        EProdutoInterface produtoCriado = produtoRepositorio.criarProduto(UUID.randomUUID().toString(), produto.getNome(),
                produto.getDescricao(), produto.getValor(), produto.getCategoria());

        assertThat(produtoCriado, is(notNullValue()));
        assertThat(produtoCriado.getId(), is(notNullValue()));
        assertThat(produtoCriado.getNome(), is(equalTo(produto.getNome())));
        assertThat(produtoCriado.getDescricao(), is(equalTo(produto.getDescricao())));
        assertThat(produtoCriado.getValor(), is(equalTo(produto.getValor())));
        assertThat(produtoCriado.getCategoria(), is(equalTo(produto.getCategoria())));

    }

    @Test
    void deveBuscarProdutoPorNome() {

        EProdutoInterface produtoObtido = produtoRepositorio.buscarProdutoPorNome("Hex Cola").orElse(null);

        assertThat(produtoObtido, is(notNullValue()));
        assertThat(produtoObtido.getId(), is(notNullValue()));
        assertThat(produtoObtido.getNome(), is(equalTo("Hex Cola")));
        assertThat(produtoObtido.getDescricao(), is(equalTo("Refrigerante sabor cola")));
        assertThat(produtoObtido.getValor(), is(equalTo(new BigDecimal("6.00"))));

    }

    @Test
    void deveBuscarProdutoPorId() {

        EProdutoInterface produtoObtido = produtoRepositorio.buscarProdutoPorId("8c722a6a-57d6-4ea1-9d98-79e78835837b").orElse(null);

        assertThat(produtoObtido, is(notNullValue()));
        assertThat(produtoObtido.getId(), is(equalTo("8c722a6a-57d6-4ea1-9d98-79e78835837b")));
        assertThat(produtoObtido.getNome(), is(equalTo("Hex Nuggets")));
        assertThat(produtoObtido.getDescricao(), is(equalTo("Nuggets no formato hexagonal")));
        assertThat(produtoObtido.getValor(), is(equalTo(new BigDecimal("12.00"))));

    }

    @Test
    void deveBuscarProdutosPorCategoria() {

        List<? extends EProdutoInterface> sobremesas = produtoRepositorio.buscarProdutosPorCategoria(Categoria.SOBREMESA.name());
        List<? extends EProdutoInterface> acompanhamentos = produtoRepositorio.buscarProdutosPorCategoria(Categoria.ACOMPANHAMENTO.name());

        assertThat(sobremesas, is(notNullValue()));
        assertThat(sobremesas, hasSize(2));
        assertThat(acompanhamentos, is(notNullValue()));
        assertThat(acompanhamentos, hasSize(2));

    }

    @Test
    void deveEditarProduto() {

        EProdutoInterface produtoObtido = produtoRepositorio.buscarProdutoPorId("8c722a6a-57d6-4ea1-9d98-79e78835837b").orElse(null);

        EProdutoInterface produtoEditado = produtoRepositorio.editarProduto(produtoObtido.getId(),
                produtoObtido.getNome() + " (testando edição)", produtoObtido.getDescricao(), new BigDecimal("200.00"), produtoObtido.getCategoria());

        assertThat(produtoEditado, is(notNullValue()));
        assertThat(produtoEditado.getId(), is(equalTo(produtoObtido.getId())));
        assertThat(produtoEditado.getNome(), is(equalTo(produtoObtido.getNome() + " (testando edição)")));
        assertThat(produtoEditado.getNome(), is(not(equalTo(produtoObtido.getNome()))));
        assertThat(produtoEditado.getDescricao(), is(equalTo(produtoObtido.getDescricao())));
        assertThat(produtoEditado.getValor(), is(equalTo(new BigDecimal("200.00"))));
        assertThat(produtoEditado.getValor(), is(not(equalTo(produtoObtido.getValor()))));
        assertThat(produtoEditado.getCategoria(), is(equalTo(produtoObtido.getCategoria())));

    }

    @Test
    void deveRemoverProduto() {

        EProduto produto = criarProduto();

        EProdutoInterface produtoCriado = produtoRepositorio.criarProduto(UUID.randomUUID().toString(), produto.getNome(),
                produto.getDescricao(), produto.getValor(), produto.getCategoria());

        produtoRepositorio.removerProduto(produtoCriado.getId());

        assertThat(produtoRepositorio.buscarProdutoPorId(produtoCriado.getId()), is(Optional.empty()));

    }

    private EProduto criarProduto() {

        return new EProduto(UUID.randomUUID().toString(), "Hex Burger Para Testes",
                "Pão e Hambuguer no formato hexagonal (Para Testes)", BigDecimal.valueOf(15.00), Categoria.LANCHE.name());

    }

}
