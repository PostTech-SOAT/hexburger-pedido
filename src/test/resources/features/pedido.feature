# language: pt

Funcionalidade: : Pedido API -
Como cliente, eu quero criar pedidos para combos com produtos, garantindo que o pedido seja validado corretamente.

  Cenário: Criar pedido válido
    Dado o payload de pedido válido
    Quando o pedido for criado
    Então a resposta deve ter o status 200 OK

  Cenário:  Criar pedido com combo vazio
    Dado o payload de pedido com combo vazio
    Quando o pedido for criado
    Então a resposta deve ter o status 400 Bad Request

  Cenario: Criar pedido com campos obrigatórios ausentes
    Dado o payload de pedido com campos obrigatórios ausentes
    Quando o pedido for criado
    Então a resposta deve ter o status 400 Bad Request
