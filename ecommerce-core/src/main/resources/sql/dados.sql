-- Produtos
INSERT INTO produto (nome, preco, estoque, detalhes, url_imagem) VALUES ('Pastel de Queijo', 2.99, 20, 'Pastel de queijo com catupiry assado no óleo', '');
INSERT INTO produto (nome, preco, estoque, detalhes, url_imagem) VALUES ('Coca cola 500 ml', 1.99, 10, 'Coca cola de 500 ml gelada', '');
INSERT INTO produto (nome, preco, estoque, detalhes, url_imagem) VALUES ('Coxinha de frango grande', 3.99, 15, 'Salgado com recheio de frango refogado', '');
INSERT INTO produto (nome, preco, estoque, detalhes, url_imagem) VALUES ('Cajuina de 1.5 litros', 2.99, 20, 'Cajuina de 1 litro e meio gelada', '');
INSERT INTO produto (nome, preco, estoque, detalhes, url_imagem) VALUES ('Enroladinho de salsicha', 1.99, 12, 'Salgado com recheio de salsiha perdigão', '');
INSERT INTO produto (nome, preco, estoque, detalhes, url_imagem) VALUES ('Risole de carne grande', 4.99, 8, 'Salgado de tamanho grande recheado de carne moída', '');
INSERT INTO produto (nome, preco, estoque, detalhes, url_imagem) VALUES ('Pão árabe tradicional', 5.99, 15, 'Pão com verduras, recheado com catupiry, queijo, presunto e grango', '');
INSERT INTO produto (nome, preco, estoque, detalhes, url_imagem) VALUES ('Pão árabe turbinado', 7.99, 20, 'Pão com verduras, recheado com catupiry, bacon, queijo, presunto, frango e carne', '');
INSERT INTO produto (nome, preco, estoque, detalhes, url_imagem) VALUES ('Batata frita tamanho normal', 2.99, 10, 'Porção de batata frita de tamanho normal', '');

-- Enderecos
INSERT INTO endereco (rua, numero, cep, bairro, municipio) VALUES ('Paulo Henrique Cavalcante', 141, '61895000', 'Água Verde', 'Guaiúba');
INSERT INTO endereco (rua, numero, cep, bairro, municipio) VALUES ('Capitão José Teixeira', 260, '61895000', 'Água Verde', 'Guaiúba');

-- Clientes
INSERT INTO cliente (nome, sobrenome, telefone, email, senha, endereco_id) VALUES ('Samuel', 'Taveira', '85989711010', 'samuelgsete@gmail.com', '123456', 1);
INSERT INTO cliente (nome, sobrenome, telefone, email, senha, endereco_id) VALUES ('Layla', 'Duarte', '85988922477', 'layladuarte@hotmail.com', 'davi', 2);

-- ItemPedido
INSERT INTO item_pedido (quantidade, produto_id) VALUES (1, 1);
INSERT INTO item_pedido (quantidade, produto_id) VALUES (1, 6);
INSERT INTO item_pedido (quantidade, produto_id) VALUES (1, 2);

INSERT INTO item_pedido (quantidade, produto_id) VALUES (1, 7);
INSERT INTO item_pedido (quantidade, produto_id) VALUES (2, 4);

-- Pedidos
INSERT INTO pedido (custo, feito_em, cliente_id, situacao) VALUES (9.97, now(), 1, 'PEDIDO_RECEBIDO');
INSERT INTO pedido (custo, feito_em, cliente_id, situacao) VALUES (11.97, now(), 2, 'PEDIDO_RECEBIDO');

-- Pedido_itens
INSERT INTO pedido_itens (pedido_id, itens_id) VALUES (1, 1);
INSERT INTO pedido_itens (pedido_id, itens_id) VALUES (1, 2);
INSERT INTO pedido_itens (pedido_id, itens_id) VALUES (1, 3);

INSERT INTO pedido_itens (pedido_id, itens_id) VALUES (2, 4);
INSERT INTO pedido_itens (pedido_id, itens_id) VALUES (2, 5);