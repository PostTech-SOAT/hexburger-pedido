package br.com.hexburger.pedido.framework.api;

import br.com.hexburger.pedido.framework.repository.PedidoRepositorioImpl;
import br.com.hexburger.pedido.framework.repository.ProdutoRepositorioImpl;
import br.com.hexburger.pedido.interfaceadapters.controller.PedidoController;
import br.com.hexburger.pedido.interfaceadapters.dto.PedidoDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static org.springframework.http.HttpStatus.BAD_REQUEST;

@RestController
@Tag(name = "Pedido")
@RequiredArgsConstructor
@RequestMapping(value = "/v1/pedido")
public class PedidoAPI {

    private final PedidoRepositorioImpl pedidoRepositorio;
    private final ProdutoRepositorioImpl produtoRepositorio;

    @PostMapping
    @Operation(summary = "Criar um pedido")
    public ResponseEntity<Object> criarPedido(@RequestBody @io.swagger.v3.oas.annotations.parameters.RequestBody(
            description = "Objeto de exemplo para criar Pedido", required = true,
            content = @Content(mediaType = "application/json", examples =
            @ExampleObject(value = "{\"combos\":[{\"produtos\":[{\"id\":\"877e03ba-eef1-4c49-9dc5-d3cc480426c8\"}]}],\"cliente\":{\"cpf\":\"12345678911\",\"nome\":\"Teste\",\"email\":\"teste@hmail.com\"}}")))
                                              PedidoDTO pedidoDTO) {
        try {
            PedidoController controller = new PedidoController();
            return ResponseEntity.ok(controller.criarPedido(pedidoDTO, pedidoRepositorio, produtoRepositorio));
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>(e.getMessage(), BAD_REQUEST);
        }
    }

    @GetMapping
    @Operation(summary = "Buscar todos os pedidos não finalizados, ordenando pelo status do pedido e data do pedido (ambos decrescentes)")
    public ResponseEntity<Object> buscarPedidos() {
        PedidoController controller = new PedidoController();
        return ResponseEntity.ok(controller.buscarPedidos(pedidoRepositorio));
    }

}
