package br.com.hexburger.pedido.framework.api;

import br.com.hexburger.pedido.framework.repository.ProdutoRepositorioImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@ActiveProfiles("test")
class ProdutoAPIIT {

    private MockMvc mockMvc;

    @Autowired
    private ProdutoRepositorioImpl repositorio;

    @BeforeEach
    void setUp() {

        ProdutoAPI produtoAPI = new ProdutoAPI(repositorio);
        mockMvc = MockMvcBuilders.standaloneSetup(produtoAPI)
                .addFilter((request, response, chain) -> {
                    response.setCharacterEncoding("UTF-8");
                    chain.doFilter(request, response);
                }, "/*")
                .build();

    }


    @Test
    void deverCriarProduto() throws Exception {

        mockMvc.perform(post("/v1/produto")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"nome\": \"Hex Burger API\", \"descricao\": \"Pão e Hambuguer no formato hexagonal para teste API\", \"valor\": 20, \"categoria\": \"LANCHE\"}"))
                .andExpect(status().isOk());

    }

    @Test
    void deveLancarExcecaoAoCriarProdutoInvalido() throws Exception {

        mockMvc.perform(post("/v1/produto")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"nome\": \"Hex Burger API\", \"descricao\": \"Pão e Hambuguer no formato hexagonal para teste API\", \"valor\": 0, \"categoria\": \"LANCHE\"}"))
                .andExpect(status().isBadRequest());

    }

    @Test
    void deveLancarExcecaoAoCriarProdutoJaExistente() throws Exception {

        mockMvc.perform(post("/v1/produto")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"nome\": \"Hex Burger Para Editar 2\", \"descricao\": \"Pão e dois Hambugueres no formato hexagonal\", \"valor\": 27, \"categoria\": \"LANCHE\"}"))
                .andExpect(status().isConflict());

    }

    @Test
    void deveEditarProduto() throws Exception {

        mockMvc.perform(put("/v1/produto/{id}", "56d4e6b1-b1bd-44e6-9818-e459f230e3e4")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"nome\": \"Hex Burger Nova Edição\", \"descricao\": \"Pão e Hambuguer no formato hexagonal\", \"valor\": 200, \"categoria\": \"LANCHE\"}"))
                .andExpect(status().isOk());

    }

    @Test
    void deveLancarExcecaoAoEditarProdutoInvalido() throws Exception {

        mockMvc.perform(put("/v1/produto/{id}", "5c4c83cb-f1e8-4182-8601-281323f00111")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"nome\": \"Hex Burger Nova Edição\", \"descricao\": \"Pão e Hambuguer no formato hexagonal\", \"valor\": 0, \"categoria\": \"LANCHE\"}"))
                .andExpect(status().isBadRequest());

    }

    @Test
    void deveLancarExcecaoAoEditarProdutoInexistente() throws Exception {

        mockMvc.perform(put("/v1/produto/{id}", "51ff3042-ae28-4bbd-a49f-468ef898b6e2")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"nome\": \"Hex Burger Nova Edição\", \"descricao\": \"Pão e Hambuguer no formato hexagonal\", \"valor\": 20, \"categoria\": \"LANCHE\"}"))
                .andExpect(status().isNotFound());

    }

    @Test
    void deveRemoverProduto() throws Exception {

        mockMvc.perform(delete("/v1/produto/{id}", "41980234-5f97-4443-9c2e-9afa3600bcfb"))
                .andExpect(status().isOk());

    }

    @Test
    void deveLancarExcecaoAoRemoverProdutoInexistente() throws Exception {

        mockMvc.perform(delete("/v1/produto/{id}", "30f5df5f-ff52-43a0-8637-d354d7c468b2"))
                .andExpect(status().isNotFound());

    }

    @Test
    void deveBuscarProdutosPorCategoria() throws Exception {

        mockMvc.perform(get("/v1/produto/{categoria}", "LANCHE"))
                .andExpect(status().isOk());

    }

    @Test
    void deveLancarExcecaoAoBuscarProdutosPorCategoriaInexistente() throws Exception {

        mockMvc.perform(get("/v1/produto/{categoria}", "ERRO"))
                .andExpect(status().isBadRequest());

    }

}
