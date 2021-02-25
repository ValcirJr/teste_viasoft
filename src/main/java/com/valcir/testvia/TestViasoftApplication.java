package com.valcir.testvia;

import java.text.SimpleDateFormat;
import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.valcir.testvia.domain.Autorizador;
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
import com.valcir.testvia.repositories.AutorizadorRepository;
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
	@Autowired
	private AutorizadorRepository autoRepo;
	
	
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
		
		Autorizador aut1 = new Autorizador(null, "AM");
		Autorizador aut2 = new Autorizador(null, "BA");
		Autorizador aut3 = new Autorizador(null, "CE");
		Autorizador aut4 = new Autorizador(null, "GO");
		Autorizador aut5 = new Autorizador(null, "MG");
		Autorizador aut6 = new Autorizador(null, "MS");
		Autorizador aut7 = new Autorizador(null, "MT");
		Autorizador aut8 = new Autorizador(null, "PE");
		Autorizador aut9 = new Autorizador(null, "PR");
		Autorizador aut10 = new Autorizador(null, "RS");
		Autorizador aut11 = new Autorizador(null, "SP");
		Autorizador aut12 = new Autorizador(null, "SVAN");
		Autorizador aut13 = new Autorizador(null, "SVRS");
		Autorizador aut14 = new Autorizador(null, "SVC-AN");
		Autorizador aut15 = new Autorizador(null, "SVC-RS");
		
		est1.getAutorizadores().addAll(Arrays.asList(aut14, aut13));
		est2.getAutorizadores().addAll(Arrays.asList(aut14, aut13));
		est3.getAutorizadores().addAll(Arrays.asList(aut14, aut13));
		est4.getAutorizadores().addAll(Arrays.asList(aut1, aut15));
		est5.getAutorizadores().addAll(Arrays.asList(aut2,aut15));
		est6.getAutorizadores().addAll(Arrays.asList(aut3,aut15));
		est7.getAutorizadores().addAll(Arrays.asList(aut14, aut13));
		est8.getAutorizadores().addAll(Arrays.asList(aut13, aut14));
		est9.getAutorizadores().addAll(Arrays.asList(aut4, aut15));
		est10.getAutorizadores().addAll(Arrays.asList(aut12, aut15));
		est11.getAutorizadores().addAll(Arrays.asList(aut7, aut15));
		est12.getAutorizadores().addAll(Arrays.asList(aut6, aut15));
		est13.getAutorizadores().addAll(Arrays.asList(aut5, aut14));
		est14.getAutorizadores().addAll(Arrays.asList(aut13, aut14));
		est15.getAutorizadores().addAll(Arrays.asList(aut13, aut14));
		est16.getAutorizadores().addAll(Arrays.asList(aut9, aut15));
		est17.getAutorizadores().addAll(Arrays.asList(aut8,aut15));
		est18.getAutorizadores().addAll(Arrays.asList(aut13, aut14));
		est19.getAutorizadores().addAll(Arrays.asList(aut13, aut14));
		est20.getAutorizadores().addAll(Arrays.asList(aut13, aut14));
		est21.getAutorizadores().addAll(Arrays.asList(aut10, aut13));
		est22.getAutorizadores().addAll(Arrays.asList(aut13, aut14));
		est23.getAutorizadores().addAll(Arrays.asList(aut13, aut14));
		est24.getAutorizadores().addAll(Arrays.asList(aut13, aut14));
		est25.getAutorizadores().addAll(Arrays.asList(aut11, aut14));
		est26.getAutorizadores().addAll(Arrays.asList(aut13, aut14));
		est27.getAutorizadores().addAll(Arrays.asList(aut13, aut14));
		
		aut1.getEstados().addAll(Arrays.asList(est4));
		aut2.getEstados().addAll(Arrays.asList(est5));
		aut3.getEstados().addAll(Arrays.asList(est6));
		aut4.getEstados().addAll(Arrays.asList(est9));
		aut5.getEstados().addAll(Arrays.asList(est13));
		aut6.getEstados().addAll(Arrays.asList(est12));
		aut7.getEstados().addAll(Arrays.asList(est11));
		aut8.getEstados().addAll(Arrays.asList(est17));
		aut9.getEstados().addAll(Arrays.asList(est16));
		aut10.getEstados().addAll(Arrays.asList(est21));
		aut11.getEstados().addAll(Arrays.asList(est25));
		aut12.getEstados().addAll(Arrays.asList(est10));
		aut13.getEstados().addAll(Arrays.asList(est1, est2, est3, est7, est8, est14, est15, est18, est19,
				est20, est21, est22, est23, est24, est26, est27));
		aut14.getEstados().addAll(Arrays.asList(est1, est2, est3, est7, est8, est13, est14, est15, est18, 
				est19, est20, est22, est23, est24, est25, est26, est27));
		aut15.getEstados().addAll(Arrays.asList(est4, est5, est6, est9, est10, est11, est12, est16, est17));
		
		
		
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
		autoRepo.saveAll(Arrays.asList(aut1, aut2, aut3, aut4, aut5, aut6, aut7, aut8, aut9, aut10,
				aut11, aut12, aut13, aut14, aut15));
		cidRepo.saveAll(Arrays.asList(c1,c2,c3));
		cliRepo.saveAll(Arrays.asList(cli1));
		endRepo.saveAll(Arrays.asList(e1, e2));
		pedRepo.saveAll(Arrays.asList(ped1, ped2));
		pgmtoRepo.saveAll(Arrays.asList(pgmto1, pgmto2));
		ipRepo.saveAll(Arrays.asList(ip1,ip2,ip3));
	}

}
