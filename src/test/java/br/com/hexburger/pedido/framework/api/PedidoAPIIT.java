package br.com.hexburger.pedido.framework.api;

import br.com.hexburger.pedido.framework.rabbitmq.PedidoSenderService;
import br.com.hexburger.pedido.framework.repository.PedidoRepositorioImpl;
import br.com.hexburger.pedido.framework.repository.ProdutoRepositorioImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@ActiveProfiles("test")
class PedidoAPIIT {

    @Autowired
    private PedidoRepositorioImpl repositorio;

    @Autowired
    private ProdutoRepositorioImpl produtoRepositorio;

    @Autowired
    private PedidoSenderService pedidoSenderService;

    private MockMvc mockMvc;

    AutoCloseable openMocks;

    @BeforeEach
    void setUp() {

        openMocks = MockitoAnnotations.openMocks(this);
        PedidoAPI pedidoAPI = new PedidoAPI(pedidoSenderService, repositorio, produtoRepositorio);
        mockMvc = MockMvcBuilders.standaloneSetup(pedidoAPI)
                .addFilter((request, response, chain) -> {
                    response.setCharacterEncoding("UTF-8");
                    chain.doFilter(request, response);
                }, "/*")
                .build();

    }

    @Test
    void deveCriarPedido() throws Exception {

        mockMvc.perform(post("/v1/pedido")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"combos\":[{\"produtos\":[{\"id\":\"f55f733e-aeb3-4955-87d7-b521895cae80\"}]}],\"cliente\":{\"cpf\":\"98765432108\",\"nome\":\"Fernando\",\"email\":\"fernando@email.com\"}}"))
                .andExpect(status().isOk());

    }

    @Test
    void deveLancarExcecaoAoCriarPedidoComComboVazio() throws Exception {

        mockMvc.perform(post("/v1/pedido")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"combos\":[],\"cliente\":{\"cpf\":\"98765432108\",\"nome\":\"Fernando\",\"email\":\"fernando@email.com\"}}"))
                .andExpect(status().isBadRequest());

    }

}
