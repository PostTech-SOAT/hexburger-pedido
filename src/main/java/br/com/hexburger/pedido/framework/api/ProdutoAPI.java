package br.com.hexburger.pedido.framework.api;

import br.com.hexburger.pedido.application.util.exception.ConflictException;
import br.com.hexburger.pedido.application.util.exception.ResourceNotFoundException;
import br.com.hexburger.pedido.framework.repository.ProdutoRepositorioImpl;
import br.com.hexburger.pedido.interfaceadapters.controller.ProdutoController;
import br.com.hexburger.pedido.interfaceadapters.dto.ProdutoDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static org.springframework.http.HttpStatus.*;

@RestController
@Tag(name = "Produto")
@RequiredArgsConstructor
@RequestMapping(value = "/v1/produto")
public class ProdutoAPI {

    private final ProdutoRepositorioImpl repositorio;

    @PostMapping
    @Operation(summary = "Criar um produto")
    public ResponseEntity<Object> criarProduto(@RequestBody @io.swagger.v3.oas.annotations.parameters.RequestBody(
            description = "Objeto de exemplo para criar Produto", required = true,
            content = @Content(mediaType = "application/json", examples =
            @ExampleObject(value = "{\"nome\": \"Hex Burger\", \"descricao\": \"Pão e Hambuguer no formato hexagonal\", \"valor\": 20, \"categoria\": \"LANCHE\"}")))
                                               ProdutoDTO produtoDTO) {
        try {
            ProdutoController controller = new ProdutoController();
            return ResponseEntity.ok(controller.criarProduto(produtoDTO, repositorio));
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>(e.getMessage(), BAD_REQUEST);
        } catch (ConflictException e) {
            return new ResponseEntity<>(e.getMessage(), CONFLICT);
        }
    }

    @PutMapping("/{id}")
    @Operation(summary = "Editar um produto")
    public ResponseEntity<Object> editarProduto(@PathVariable @Parameter(description = "ID do produto", required = true, schema = @Schema(type = "string", example = "877e03ba-eef1-4c49-9dc5-d3cc480426c8")) String id,
                                                @RequestBody @io.swagger.v3.oas.annotations.parameters.RequestBody(
            description = "Objeto de exemplo para editar Produto", required = true,
            content = @Content(mediaType = "application/json", examples =
            @ExampleObject(value = "{\"nome\": \"Hex Burger\", \"descricao\": \"Pão e Hambuguer no formato hexagonal\", \"valor\": 20, \"categoria\": \"LANCHE\"}")))
    ProdutoDTO produtoDTO) {
        try {
            ProdutoController controller = new ProdutoController();
            return ResponseEntity.ok(controller.editarProduto(id, produtoDTO, repositorio));
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>(e.getMessage(), BAD_REQUEST);
        } catch (ResourceNotFoundException e) {
            return new ResponseEntity<>(e.getMessage(), NOT_FOUND);
        }
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Remover um produto")
    public ResponseEntity<Object> removerProduto(@PathVariable @Parameter(description = "ID do produto", required = true, schema = @Schema(type = "string", example = "877e03ba-eef1-4c49-9dc5-d3cc480426c8")) String id) {
        try {
            ProdutoController controller = new ProdutoController();
            controller.removerProduto(id, repositorio);
            return ResponseEntity.ok().build();
        } catch (ResourceNotFoundException e) {
            return new ResponseEntity<>(e.getMessage(), NOT_FOUND);
        }
    }

    @GetMapping("/{categoria}")
    @Operation(summary = "Buscar produtos por categoria")
    public ResponseEntity<Object> buscarProdutosPorCategoria(@PathVariable @Parameter(description = "Categoria do Produto", required = true, schema = @Schema(type = "string", allowableValues = {"LANCHE", "BEBIDA", "ACOMPANHAMENTO", "SOBREMESA"})) String categoria) {
        try {
            ProdutoController controller = new ProdutoController();
            return ResponseEntity.ok(controller.buscarProdutosPorCategoria(categoria.toUpperCase(), repositorio));
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>(e.getMessage(), BAD_REQUEST);
        } catch (ResourceNotFoundException e) {
            return new ResponseEntity<>(e.getMessage(), NOT_FOUND);
        }
    }
}
