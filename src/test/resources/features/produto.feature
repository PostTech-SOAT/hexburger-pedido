# language: pt

Funcionalidade: Gestão de Produtos
  Como administrador
  Quero criar, editar, remover e consultar produtos
  Para gerenciar o catálogo do sistema

  Cenário: Criar um produto válido
  Dado o payload de produto válido
  Quando o produto for criado
  Então a resposta deve ter o status 200 OK

  Cenário: Criar um produto inválido
  Dado o payload de produto com valor inválido
  Quando o produto for criado
  Então a resposta deve ter o status 400 Bad Request

  Cenário: Criar um produto já existente
  Dado o payload de produto já existente
  E o produto já existe no sistema
  Quando o produto for criado
  Então a resposta deve ter o status 409 Conflict

  Cenário: Editar um produto válido
  Dado o payload de produto válido para edição
  E o produto existe no sistema com ID "56d4e6b1-b1bd-44e6-9818-e459f230e3e4"
  Quando o produto for editado
  Então a resposta deve ter o status 200 OK

  Cenário: Editar um produto inválido
  Dado o payload de produto com valor inválido para edição
  E o produto existe no sistema com ID "5c4c83cb-f1e8-4182-8601-281323f00111"
  Quando o produto for editado
  Então a resposta deve ter o status 400 Bad Request

  Cenário: Editar um produto inexistente
  Dado o payload de produto válido para edição
  E o produto não existe no sistema com ID "51ff3042-ae28-4bbd-a49f-468ef898b6e2"
  Quando o produto for editado
  Então a resposta deve ter o status 404 produto Not Found

  Cenário: Remover um produto existente
  Dado o produto existe no sistema com ID "41980234-5f97-4443-9c2e-9afa3600bcfb"
  Quando o produto for removido
  Então a resposta deve ter o status 200 OK

  Cenário: Remover um produto inexistente
  Dado o produto não existe no sistema com ID "30f5df5f-ff52-43a0-8637-d354d7c468b2"
  Quando o produto for removido
  Então a resposta deve ter o status 404 produto Not Found

  Cenário: Buscar produtos por categoria válida
  Dado a categoria "LANCHE" existe no sistema
  Quando os produtos forem buscados pela categoria
  Então a resposta deve ter o status 200 OK

  Cenário: Buscar produtos por categoria inexistente
  Dado a categoria "ERRO" não existe no sistema
  Quando os produtos forem buscados pela categoria
  Então a resposta deve ter o status 400 Bad Request
