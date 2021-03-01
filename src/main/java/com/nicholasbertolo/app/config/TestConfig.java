package com.nicholasbertolo.app.config;

import java.time.Instant;
import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import com.nicholasbertolo.app.entities.Categoria;
import com.nicholasbertolo.app.entities.Cidade;
import com.nicholasbertolo.app.entities.Endereco;
import com.nicholasbertolo.app.entities.ItemPedido;
import com.nicholasbertolo.app.entities.Pagamento;
import com.nicholasbertolo.app.entities.Pedido;
import com.nicholasbertolo.app.entities.Produto;
import com.nicholasbertolo.app.entities.Usuario;
import com.nicholasbertolo.app.entities.enums.EstadoEnum;
import com.nicholasbertolo.app.entities.enums.PedidoStatus;
import com.nicholasbertolo.app.repositories.CategoriaRepository;
import com.nicholasbertolo.app.repositories.CidadeRepository;
import com.nicholasbertolo.app.repositories.EnderecoRepository;
import com.nicholasbertolo.app.repositories.ItemPedidoRepository;
import com.nicholasbertolo.app.repositories.PedidoRepository;
import com.nicholasbertolo.app.repositories.ProdutoRepository;
import com.nicholasbertolo.app.repositories.UserRepository;

@Configuration
@Profile("test")
public class TestConfig implements CommandLineRunner{
	
	@Autowired
	private UserRepository userRepository;

	@Autowired
	private PedidoRepository pedidoRepository;
	
	@Autowired
	private CategoriaRepository categRepository;
	
	@Autowired
	private ProdutoRepository produtoRepository;
	
	@Autowired
	private ItemPedidoRepository itemPedidoRepository;
	
	@Autowired
	private CidadeRepository cidadeRepository;
	
	@Autowired
	private EnderecoRepository enderecoRepository;
	
	@Override
	public void run(String... args) throws Exception {
		
		Cidade ci1 = new Cidade(null, "Limeira", EstadoEnum.SP);
		Cidade ci2 = new Cidade(null, "Americana", EstadoEnum.SP);
		Cidade ci3 = new Cidade(null, "Campinas", EstadoEnum.SP);
		Cidade ci4 = new Cidade(null, "São Paulo", EstadoEnum.SP);
		Cidade ci5 = new Cidade(null, "Belo Horizonte", EstadoEnum.MG);
		
		cidadeRepository.saveAll(Arrays.asList(ci1,ci2,ci3,ci4,ci5));
 		
		Usuario u1 = new Usuario(null, "Maria Brown", "maria@gmail.com", "(19) 98525-0326", "408.235.296-02", "123456");
		Usuario u2 = new Usuario(null, "Alex Green", "alex@gmail.com", "(19) 98356-3169", "543.274.396-09", "123456"); 
		Usuario u3 = new Usuario(null, "Marley Red", "marley@gmail.com", "(11) 92154-9526", "154.289.429-05", "654321");
		Usuario u4 = new Usuario(null, "Bob Gray", "bob@gmail.com", "(15) 98245-9316", "255.255.025-04", "542612");
		Usuario u5 = new Usuario(null, "John White", "johnw@gmail.com", "(11) 98215-2952",  "313.256.932-13", "572165");
		
		userRepository.saveAll(Arrays.asList(u1,u2,u3,u4,u5));
		
		Endereco e1 = new Endereco(null, "Rua Alferes Franco", "208", "nda", "Centro", "13480-050", ci1, u1);
		Endereco e2 = new Endereco(null, "Rua General Rondom", "915", "Sobreloja", "Jardim Boa Vista", "13486-154", ci1, u2);
		Endereco e3 = new Endereco(null, "Rua Sergipe", "1440", "", "Bairro Savassi", "30130-174", ci5, u3);
		Endereco e4 = new Endereco(null, "Av. Brasil Sul", "2000", "", "Parque Res. Nardini", "13465-770", ci2, u3);
		Endereco e5 = new Endereco(null, "Rua Prof. Luis Rosa", "121", "", "Botafogo", "13020-260", ci3, u4);
		Endereco e6 = new Endereco(null, "Av. Paulista", "1374", "","Bela Vista", "01310-916", ci4, u5);
		
		enderecoRepository.saveAll(Arrays.asList(e1,e2,e3,e4,e5,e6));
		
		Categoria c1 = new Categoria(null, "Hardware");
		Categoria c2 = new Categoria(null, "Televisões");
		Categoria c3 = new Categoria(null, "Celulares");
		Categoria c4 = new Categoria(null, "Notebooks");
		
		Produto pro1 = new Produto(null, "Intel Core i5 9th", "6 Núcleos, 6 Threads. 2.90GHz (Max 4.10).", 889.00, "");
		Produto pro2 = new Produto(null, "Smart TV 43´ Samsung", "2 HDMI, 1 USB, Wi-Fi", 1890.00, "");
		Produto pro3 = new Produto(null, "SSD Kingston A400", "480GB, SATA, Leitura 500MB/s, Gravação 450MB/s", 478.00, "");
		Produto pro4 = new Produto(null, "Notebook Acer Aspire 3 Intel Core i5-1035G1", "4GB, SSD 256GB, Endless OS, 15.6´ ", 3250.00, "");
		
		c1.getProduto().addAll(Arrays.asList(pro1,pro3));
		c2.getProduto().addAll(Arrays.asList(pro2));
		c4.getProduto().addAll(Arrays.asList(pro4));
		
		pro1.getCategoria().addAll(Arrays.asList(c1));
		pro2.getCategoria().addAll(Arrays.asList(c2));
		pro3.getCategoria().addAll(Arrays.asList(c1));
		pro4.getCategoria().addAll(Arrays.asList(c4));
		
		categRepository.saveAll(Arrays.asList(c1,c2,c3,c4));		
		produtoRepository.saveAll(Arrays.asList(pro1, pro2, pro3, pro4));
		
		Pedido p1 = new Pedido(null, Instant.parse("2021-02-01T13:30:05Z"), PedidoStatus.PAGO, u1, e1);
		Pedido p2 = new Pedido(null, Instant.parse("2021-02-04T19:32:05Z"), PedidoStatus.AGUARDANDO_PAGAMENTO,u2, e2);
		Pedido p3 = new Pedido(null, Instant.parse("2021-01-25T05:22:04Z"), PedidoStatus.ENTREGUE,u2, e2);
		Pedido p4 = new Pedido(null, Instant.parse("2021-02-04T19:30:03Z"), PedidoStatus.AGUARDANDO_PAGAMENTO,u3, e4);
		Pedido p5 = new Pedido(null, Instant.parse("2021-02-01T10:32:09Z"), PedidoStatus.EM_TRANSITO, u5, e6);
		
		pedidoRepository.saveAll(Arrays.asList(p1,p2,p3,p4,p5));
		
		ItemPedido ip1 = new ItemPedido(p1, pro1, 2, pro1.getPrice());
		ItemPedido ip2 = new ItemPedido(p2, pro2, 3, pro3.getPrice());
		ItemPedido ip3 = new ItemPedido(p3, pro4, 2, pro4.getPrice());
		ItemPedido ip4 = new ItemPedido(p2, pro3, 2, pro3.getPrice());
		ItemPedido ip5 = new ItemPedido(p4, pro1, 1, pro1.getPrice());
		ItemPedido ip6 = new ItemPedido(p4, pro3, 2, pro3.getPrice());
		ItemPedido ip7 = new ItemPedido(p5, pro1, 2, pro1.getPrice());
		ItemPedido ip8 = new ItemPedido(p5, pro2, 1, pro2.getPrice());
		
		itemPedidoRepository.saveAll(Arrays.asList(ip1,ip2,ip3,ip4,ip5,ip6,ip7,ip8));
		
		Pagamento pag1 = new Pagamento(null, Instant.parse("2020-02-01T13:30:05Z"), p1);
		Pagamento pag2 = new Pagamento(null, Instant.parse("2021-01-25T05:32:04Z"),p3);
		p1.setPagamento(pag1);
		p3.setPagamento(pag2);
		

		
	}
}
