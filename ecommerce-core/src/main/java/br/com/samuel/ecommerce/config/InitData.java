package br.com.samuel.ecommerce.config;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import br.com.samuel.ecommerce.models.Cliente;
import br.com.samuel.ecommerce.models.Endereco;
import br.com.samuel.ecommerce.models.Perfil;
import br.com.samuel.ecommerce.models.Produto;
import br.com.samuel.ecommerce.models.Usuario;
import br.com.samuel.ecommerce.models.Vendedor;
import br.com.samuel.ecommerce.repository.RepositorioCliente;
import br.com.samuel.ecommerce.repository.RepositorioPerfil;
import br.com.samuel.ecommerce.repository.RepositorioProduto;
import br.com.samuel.ecommerce.repository.RepositorioVendedor;
import br.com.samuel.ecommerce.security.models.Perfis;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Component
public class InitData implements ApplicationListener<ContextRefreshedEvent> {

    @Autowired
    private RepositorioPerfil repositorioPerfil;

    @Autowired
    private RepositorioVendedor repositorioVendedor;

    @Autowired
    private RepositorioCliente repositorioCliente;

    @Autowired
    private RepositorioProduto repositorioProduto;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public void onApplicationEvent(ContextRefreshedEvent arg0) {
        List<Perfil> perfis = Arrays.asList();
        perfis = repositorioPerfil.findAll();
        Perfil perfilVendedor = new Perfil(Perfis.ROLE_ADMIN);
        Perfil perfilCliente = new Perfil(Perfis.ROLE_CLIENT);

        if(perfis.isEmpty()) {   
            perfis = Arrays.asList(perfilVendedor, perfilCliente);
            perfis = repositorioPerfil.saveAll(perfis);
            perfilVendedor = perfis.get(0);
            perfilCliente = perfis.get(1);
        }

        List<Vendedor> vendedores = Arrays.asList();
        vendedores = repositorioVendedor.findAll();
        Integer vendedorId = null;
        if(vendedores.isEmpty()) {
            Set<Perfil> perfils = new HashSet<Perfil>();
            perfils.add(perfilVendedor);
            vendedorId = criarVendedor(perfils);
        }
        
        List<Cliente> clientes = Arrays.asList();
        clientes = repositorioCliente.findAll();
        if(clientes.isEmpty()) {
            Set<Perfil> perfils = new HashSet<Perfil>();
            perfils.add(perfilCliente);
            criarClientes(perfils, vendedorId);
        }

        List<Produto> produtos = Arrays.asList();
        produtos = repositorioProduto.findAll();
        if(produtos.isEmpty()) {
            criarProdutos(vendedorId);
        }
    }

    public Integer criarVendedor(Set<Perfil> perfis) {
        Endereco endereco = new Endereco();
        endereco.setRua("Vicente Carlos Araújo");
        endereco.setNumero(413);
        endereco.setCep("61898000");
        endereco.setReferencia("Próximo ao posto de saúde");
        endereco.setBairro("Água Verde");
        endereco.setMunicipio("Guaiúba");

        Usuario usuario = new Usuario();
        usuario.setNome("Madalena");
        usuario.setSobrenome("Taveira");
        usuario.setTelefone("85988180673");
        usuario.setEmail("madalenataveira@hotmail.com");
        usuario.setSenha(passwordEncoder.encode("123456"));
        usuario.setPerfis(perfis);
        usuario.setEstaAtivo(true);
        usuario.setCodigoVerificacao("465961");
        usuario.setEndereco(endereco);

        Vendedor vendedor = new Vendedor();
        vendedor.setNomeFantasia("Lanchanote do &Povo");
        vendedor.setDescricao("Somos a lanchonete mais popular do centro de Água Verde. Nossa especialidade é: salgados, massas, caldos, saladas, vitamnas, sucos e refrigerantes. Nosso foco é a satisfação do cliente!");
        vendedor.setUsuario(usuario);
        vendedor.setReceita(0);
        vendedor.setPedidosConcluidos(0);
        vendedor.setPedidosCancelados(0);
        vendedor.setTotalClientes(2);
        vendedor.setTotalProdutosVendidos(0);

        Integer vendedorId = repositorioVendedor.save(vendedor).getId();
        return vendedorId;
    }

    public void criarClientes(Set<Perfil> perfis, Integer vendedorId) {
        /* Cliente Samuel */
        Endereco enderecoSamuel = new Endereco();
        enderecoSamuel.setRua("Paulo Henrique Cavalcante");
        enderecoSamuel.setNumero(141);
        enderecoSamuel.setCep("61895000");
        enderecoSamuel.setReferencia("Próximo a Fazenda do Tabosa");
        enderecoSamuel.setBairro("Água Verde");
        enderecoSamuel.setMunicipio("Guaiúba");

        Usuario usuarioSamuel = new Usuario();
        usuarioSamuel.setNome("Samuel");
        usuarioSamuel.setSobrenome("Souza");
        usuarioSamuel.setTelefone("85989711010");
        usuarioSamuel.setEmail("samuelgsete@gmail.com");
        usuarioSamuel.setSenha(passwordEncoder.encode("layla"));
        usuarioSamuel.setPerfis(perfis);
        usuarioSamuel.setEstaAtivo(true);
        usuarioSamuel.setCodigoVerificacao("964359");
        usuarioSamuel.setEndereco(enderecoSamuel);

        Cliente clienteSamuel = new Cliente();
        clienteSamuel.setUsuario(usuarioSamuel);
        clienteSamuel.setVendedorId(vendedorId);
        clienteSamuel.setTotalPedidos(0);
        clienteSamuel.setTotalConsumido(0);
        clienteSamuel.setTotalGasto(0);

        /* Cliente Layla */
        Endereco enderecoLayla = new Endereco();
        enderecoLayla.setRua("Paulo Figueredo Sales");
        enderecoLayla.setNumero(215);
        enderecoLayla.setCep("61895000");
        enderecoLayla.setReferencia("Próximo a estação da OI");
        enderecoLayla.setBairro("Água Verde");
        enderecoLayla.setMunicipio("Guaiúba");

        Usuario usuarioLayla = new Usuario();
        usuarioLayla.setNome("Layla");
        usuarioLayla.setSobrenome("Duarte");
        usuarioLayla.setTelefone("85988922477");
        usuarioLayla.setEmail("layladuarte@hotmail.com");
        usuarioLayla.setSenha(passwordEncoder.encode("samuel"));
        usuarioLayla.setPerfis(perfis);
        usuarioLayla.setEstaAtivo(true);
        usuarioLayla.setCodigoVerificacao("316597");
        usuarioLayla.setEndereco(enderecoLayla);

        Cliente clienteLayla = new Cliente();
        clienteLayla.setUsuario(usuarioLayla);
        clienteLayla.setVendedorId(vendedorId);
        clienteLayla.setTotalPedidos(0);
        clienteLayla.setTotalConsumido(0);
        clienteLayla.setTotalGasto(0);

        /* Cliente Sharles */
        Endereco enderecoSharles = new Endereco();
        enderecoSharles.setRua("Capitão José Teixeira");
        enderecoSharles.setNumero(409);
        enderecoSharles.setCep("61895000");
        enderecoSharles.setReferencia("Próximo ao posto de Saúde");
        enderecoSharles.setBairro("Água Verde");
        enderecoSharles.setMunicipio("Guaiúba");

        Usuario usuarioSharles = new Usuario();
        usuarioSharles.setNome("Sharles");
        usuarioSharles.setSobrenome("Chagas");
        usuarioSharles.setTelefone("85983559221");
        usuarioSharles.setEmail("sharleschagas@yahoo.com");
        usuarioSharles.setSenha(passwordEncoder.encode("melissa"));
        usuarioSharles.setPerfis(perfis);
        usuarioSharles.setEstaAtivo(true);
        usuarioSharles.setCodigoVerificacao("312984");
        usuarioSharles.setEndereco(enderecoSharles);

        Cliente clienteSharles= new Cliente();
        clienteSharles.setUsuario(usuarioSharles);
        clienteSharles.setVendedorId(vendedorId);
        clienteSharles.setTotalPedidos(0);
        clienteSharles.setTotalConsumido(0);
        clienteSharles.setTotalGasto(0);

        repositorioCliente.saveAll(Arrays.asList(clienteSamuel, clienteLayla, clienteSharles));
    }

    public void criarProdutos(Integer vendedorId) {

        Produto batataFrita = new Produto();
        batataFrita.setNome("Batata Frita grande");
        batataFrita.setPreco(5.99);
        batataFrita.setUnidadesVendidas(0);
        batataFrita.setEstoque(16);
        batataFrita.setDetalhes("Uma porção grande de batata frita crocrante e suculanta acompanhada de catchup");
        batataFrita.setUrlImagem("http://localhost:8080/upload/produtos/batata-frita.jpg");
        batataFrita.setVendedorId(vendedorId);

        Produto coxinhaGrande = new Produto();
        coxinhaGrande.setNome("Coxinha Grande");
        coxinhaGrande.setPreco(4.99);
        coxinhaGrande.setUnidadesVendidas(0);
        coxinhaGrande.setEstoque(20);
        coxinhaGrande.setDetalhes("Uma deliciosa coxinha tamanho grande de massa crocrante recheada com frango desfiado e catupiry");
        coxinhaGrande.setUrlImagem("http://localhost:8080/upload/produtos/coxinha-grande.png");
        coxinhaGrande.setVendedorId(vendedorId);

        Produto cocaCola500ml = new Produto();
        cocaCola500ml.setNome("Coca Cola 500 ml");
        cocaCola500ml.setPreco(2.99);
        cocaCola500ml.setUnidadesVendidas(0);
        cocaCola500ml.setEstoque(30);
        cocaCola500ml.setDetalhes("Um refrigerante original gelado da marca Coca Cola de 500 mililitros de volume");
        cocaCola500ml.setUrlImagem("http://localhost:8080/upload/produtos/coca-cola500ml.png");
        cocaCola500ml.setVendedorId(vendedorId);

        Produto cajuinaUmLitro = new Produto();
        cajuinaUmLitro.setNome("Cajuina 1 Litro");
        cajuinaUmLitro.setPreco(3.99);
        cajuinaUmLitro.setUnidadesVendidas(0);
        cajuinaUmLitro.setEstoque(20);
        cajuinaUmLitro.setDetalhes("Um refrigerante original gelado da marca São Gerardo 1 litro de volume");
        cajuinaUmLitro.setUrlImagem("http://localhost:8080/upload/produtos/cajuina-1litro.png");
        cajuinaUmLitro.setVendedorId(vendedorId);

        Produto enroladinhoSalsicha = new Produto();
        enroladinhoSalsicha.setNome("Enroladinho de Salsicha");
        enroladinhoSalsicha.setPreco(1.99);
        enroladinhoSalsicha.setUnidadesVendidas(0);
        enroladinhoSalsicha.setEstoque(30);
        enroladinhoSalsicha.setDetalhes("Um delicioso salgado tradicional de massa crocrante recheado com uma salsicha de excelente qualidade");
        enroladinhoSalsicha.setUrlImagem("http://localhost:8080/upload/produtos/enroladinho-salsicha.png");
        enroladinhoSalsicha.setVendedorId(vendedorId);

        Produto esfirraCarne = new Produto();
        esfirraCarne.setNome("Esfirra de Carne");
        esfirraCarne.setPreco(4.99);
        esfirraCarne.setUnidadesVendidas(0);
        esfirraCarne.setEstoque(18);
        esfirraCarne.setDetalhes("Uma deliciosa esfirra assada ao forno de massa crocrante recheada com carne moída e catupiry");
        esfirraCarne.setUrlImagem("http://localhost:8080/upload/produtos/esfirra-carne.png");
        esfirraCarne.setVendedorId(vendedorId);

        Produto fantaLaranja = new Produto();
        fantaLaranja.setNome("Fanta Laranja");
        fantaLaranja.setPreco(5.99);
        fantaLaranja.setUnidadesVendidas(0);
        fantaLaranja.setEstoque(18);
        fantaLaranja.setDetalhes("Um autêntico refrigerante gelado da marca Fanta sabor Laranja de 2 litros de volume");
        fantaLaranja.setUrlImagem("http://localhost:8080/upload/produtos/fanta-laranja.png");
        fantaLaranja.setVendedorId(vendedorId);

        Produto paoArabeEspecial = new Produto();
        paoArabeEspecial.setNome("Pão Árabe especial");
        paoArabeEspecial.setPreco(7.99);
        paoArabeEspecial.setUnidadesVendidas(0);
        paoArabeEspecial.setEstoque(10);
        paoArabeEspecial.setDetalhes("Um delisioso Pão Árabe tamanho grande de massa crocrante recheado com frango, queijo, presunto, verduras e catupiry");
        paoArabeEspecial.setUrlImagem("http://localhost:8080/upload/produtos/pao-arabe-especial.png");
        paoArabeEspecial.setVendedorId(vendedorId);

        Produto paoFocacia = new Produto();
        paoFocacia.setNome("Pão Focácia");
        paoFocacia.setPreco(3.49);
        paoFocacia.setUnidadesVendidas(0);
        paoFocacia.setEstoque(15);
        paoFocacia.setDetalhes("Um delisioso pedaço Pão Focácia estilo pizza de massa crocrante feita a mão recheado com frango, queijo, calabresa, verduras e catupiry");
        paoFocacia.setUrlImagem("http://localhost:8080/upload/produtos/pao-focacia.jpg");
        paoFocacia.setVendedorId(vendedorId);

        Produto pastelQueijo = new Produto();
        pastelQueijo.setNome("Pastel de Queijo");
        pastelQueijo.setPreco(2.49);
        pastelQueijo.setUnidadesVendidas(0);
        pastelQueijo.setEstoque(16);
        pastelQueijo.setDetalhes("Um delisioso pastel de queijo de massa crocante recheado com queijo de excelente qualidade e catupiry");
        pastelQueijo.setUrlImagem("http://localhost:8080/upload/produtos/pastel-queijo.jpg");
        pastelQueijo.setVendedorId(vendedorId);

        Produto pepsi = new Produto();
        pepsi.setNome("Pepsi grande");
        pepsi.setPreco(6.49);
        pepsi.setUnidadesVendidas(0);
        pepsi.setEstoque(24);
        pepsi.setDetalhes("Um autêntico refrigerante gelado da marca Pepsi de 2 litros de volume");
        pepsi.setUrlImagem("http://localhost:8080/upload/produtos/pepsi.png");
        pepsi.setVendedorId(vendedorId);

        Produto pizzaFrango = new Produto();
        pizzaFrango.setNome("Pizza de Frango");
        pizzaFrango.setPreco(17.99);
        pizzaFrango.setUnidadesVendidas(0);
        pizzaFrango.setEstoque(5);
        pizzaFrango.setDetalhes("Uma deliciosa pizza feita a mão tamanho grande de massa crocrante recheada com frango, queijo, calabresa, catupiry e verduras");
        pizzaFrango.setUrlImagem("http://localhost:8080/upload/produtos/pizza-de-frango.png");
        pizzaFrango.setVendedorId(vendedorId);

        repositorioProduto.saveAll(
            Arrays.asList(
                batataFrita,
                coxinhaGrande, 
                cocaCola500ml, 
                cajuinaUmLitro, 
                enroladinhoSalsicha, 
                esfirraCarne, 
                fantaLaranja,
                paoArabeEspecial,
                paoFocacia,
                pastelQueijo,
                pepsi,
                pizzaFrango
            )
        );
    }
}