package br.com.hexburger.pedido.framework.repository;

import br.com.hexburger.pedido.framework.entidade.EProduto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ProdutoRepository extends JpaRepository<EProduto, String> {

    List<EProduto> findByCategoria(String categoria);
    Optional<EProduto> findByNome(String nome);

}
