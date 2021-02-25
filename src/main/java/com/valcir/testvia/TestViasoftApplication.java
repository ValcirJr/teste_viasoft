package com.valcir.testvia;

import java.text.SimpleDateFormat;
import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.valcir.testvia.domain.Categoria;
import com.valcir.testvia.domain.Cidade;
import com.valcir.testvia.domain.Cliente;
import com.valcir.testvia.domain.Endereco;
import com.valcir.testvia.domain.Estado;
import com.valcir.testvia.domain.ItemPedido;
import com.valcir.testvia.domain.Pagamento;
import com.valcir.testvia.domain.PagamentoComBoleto;
import com.valcir.testvia.domain.PagamentoComCartao;
import com.valcir.testvia.domain.Pedido;
import com.valcir.testvia.domain.Produto;
import com.valcir.testvia.domain.enums.EstadoPagamento;
import com.valcir.testvia.domain.enums.TipoCliente;
import com.valcir.testvia.repositories.CategoriaRepository;
import com.valcir.testvia.repositories.CidadeRepository;
import com.valcir.testvia.repositories.ClienteRepository;
import com.valcir.testvia.repositories.EnderecoRepository;
import com.valcir.testvia.repositories.EstadoRepository;
import com.valcir.testvia.repositories.ItemPedidoRepository;
import com.valcir.testvia.repositories.PagamentoRepository;
import com.valcir.testvia.repositories.PedidoRepository;
import com.valcir.testvia.repositories.ProdutoRepository;
 
@SpringBootApplication
public class TestViasoftApplication implements CommandLineRunner{
	
	

	
	@Autowired
	private CategoriaRepository catRepo;	
	@Autowired
	private ProdutoRepository prodRepo;	
	@Autowired
	private EstadoRepository estRepo;	
	@Autowired 
	private CidadeRepository cidRepo;
	@Autowired
	private ClienteRepository cliRepo;	
	@Autowired
	private EnderecoRepository endRepo;	
	@Autowired
	private PedidoRepository pedRepo;	
	@Autowired
	private PagamentoRepository pgmtoRepo;	
	@Autowired
	private ItemPedidoRepository ipRepo;
	
	
	public static void main(String[] args) {
		SpringApplication.run(TestViasoftApplication.class, args);
	}

	
	
	
	@Override
	public void run(String... args) throws Exception {
		
	
	
		Categoria cat1 = new Categoria(null, "Informática");
		Categoria cat2 = new Categoria(null, "Escritório");
		
		Produto p1 = new Produto(null, "Comuptador", 2000.00);
		Produto p2 = new Produto(null, "Impressora", 800.00);
		Produto p3 = new Produto(null, "Mouse", 80.00);
		
		cat1.getProdutos().addAll(Arrays.asList(p1,p2,p3));
		cat2.getProdutos().addAll(Arrays.asList(p2));
		
		p1.getCategorias().addAll(Arrays.asList(cat1));
		p2.getCategorias().addAll(Arrays.asList(cat1, cat2));
		p3.getCategorias().addAll(Arrays.asList(cat1));
		
		
		Estado est1 = new Estado(null, "Acre", "AC");
		Estado est2 = new Estado(null, "Alagoas", "AL");
		Estado est3 = new Estado(null, "Amapá", "AP");
		Estado est4 = new Estado(null, "Amazonas", "AM");
		Estado est5 = new Estado(null, "Bahia", "BA");
		Estado est6 = new Estado(null, "Ceará", "CE");
		Estado est7 = new Estado(null, "Distrito Fedaral", "DF");
		Estado est8 = new Estado(null, "Espírito Santo", "ES");
		Estado est9 = new Estado(null, "Goiás", "GO");
		Estado est10 = new Estado(null, "Maranhão", "MA");
		Estado est11 = new Estado(null, "Mato Grosso", "MT");
		Estado est12 = new Estado(null, "Mato Grosso do Sul", "MS");
		Estado est13 = new Estado(null, "Minas Gerais", "MG");
		Estado est14 = new Estado(null, "Pará", "PA");
		Estado est15 = new Estado(null, "Paraíba", "PB");
		Estado est16 = new Estado(null, "Paraná", "PR");
		Estado est17 = new Estado(null, "Pernmbuco", "PE");
		Estado est18 = new Estado(null, "Piauí", "PI");
		Estado est19 = new Estado(null, "Rio de Janeiro", "RJ");
		Estado est20 = new Estado(null, "Rio Grande do Norte", "RN");
		Estado est21 = new Estado(null, "Rio Grande do Sul", "RS");
		Estado est22 = new Estado(null, "Rondônio", "RO");
		Estado est23 = new Estado(null, "Roraima", "RR");
		Estado est24 = new Estado(null, "Santa Catarina", "SC");
		Estado est25 = new Estado(null, "São Paulo", "SP");
		Estado est26 = new Estado(null, "Sergipe", "SE");
		Estado est27 = new Estado(null, "Tocantins", "TO");
		
		
		Cidade c1 = new Cidade(null, "Uberlândia", est1);
		Cidade c2 = new Cidade(null, "São Paulo", est2);
		Cidade c3 = new Cidade(null, "Campinas", est2);
		
		est1.getCidades().addAll(Arrays.asList(c1));
		est2.getCidades().addAll(Arrays.asList(c2,c3));
		
		Cliente cli1 = new Cliente(null, "Maria Silva", "maria@gmail.com", 
				"08398166906", TipoCliente.PESSOA_FISICA);
		cli1.getTelefones().addAll(Arrays.asList("999313231", "322421234"));
		
		Endereco e1 = new Endereco(null, "Rua Flores", "300", "apto 303", "Jardim", "38220834", cli1, c1);
		Endereco e2 = new Endereco(null, "Avenida Matos", "105", "Sala 800", "Centro", "38777012", cli1, c2);
		
		cli1.getEnderecos().addAll(Arrays.asList(e1,e2));
		
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm");
		Pedido ped1 = new Pedido(null, sdf.parse("30/09/2020 10:32"), cli1, e1);
		Pedido ped2 = new Pedido(null, sdf.parse("10/10/2020 23:01"), cli1, e2);
		
		Pagamento pgmto1 = new PagamentoComCartao(null, EstadoPagamento.QUITADO, ped1, 6);
		ped1.setPagamento(pgmto1);
		Pagamento pgmto2 = new PagamentoComBoleto(null, EstadoPagamento.PENDENTE, ped2,sdf.parse("20/10/2017 00:00"), null);
		ped2.setPagamento(pgmto2);		
		
		cli1.getPedidos().addAll(Arrays.asList(ped1,ped2));
		
		ItemPedido ip1 = new ItemPedido(ped1, p1, 0.00, 1, 2000.00);
		ItemPedido ip2 = new ItemPedido(ped1, p3, 0.00, 2, 80.00);
		ItemPedido ip3 = new ItemPedido(ped2, p2, 100.00, 1, 800.00);
		
		ped1.getItens().addAll(Arrays.asList(ip1, ip2));
		ped2.getItens().addAll(Arrays.asList(ip3));
		
		p1.getItens().addAll(Arrays.asList(ip1));
		p2.getItens().addAll(Arrays.asList(ip3));
		p3.getItens().addAll(Arrays.asList(ip2));
		
				
		catRepo.saveAll(Arrays.asList(cat1, cat2));
		prodRepo.saveAll(Arrays.asList(p1,p2,p3));
		estRepo.saveAll(Arrays.asList(est1, est2, est3, est4, est5, est6, est7, est8, est9, est10,
				est11, est12, est13, est14, est15, est16, est17, est18, est19, est20,
				est21, est22, est23, est24, est25, est26, est27));
		cidRepo.saveAll(Arrays.asList(c1,c2,c3));
		cliRepo.saveAll(Arrays.asList(cli1));
		endRepo.saveAll(Arrays.asList(e1, e2));
		pedRepo.saveAll(Arrays.asList(ped1, ped2));
		pgmtoRepo.saveAll(Arrays.asList(pgmto1, pgmto2));
		ipRepo.saveAll(Arrays.asList(ip1,ip2,ip3));
	}

}
