package com.valcir.testvia.services;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import com.valcir.testvia.domain.Autorizador;
import com.valcir.testvia.domain.DisponibilidadeServicoNF;
import com.valcir.testvia.domain.Estado;
import com.valcir.testvia.domain.EstadoDisponibilidade;
import com.valcir.testvia.repositories.AutorizadorRepository;
import com.valcir.testvia.repositories.DisponibilidadeServicoNFRepository;
import com.valcir.testvia.repositories.EstadoRepository;


@Component 
@EnableScheduling
@Service
public class ConsultaDisponibilidadeService {
	
	
	@Autowired
	private AutorizadorRepository autoRepo;
	@Autowired
	private DisponibilidadeServicoNFRepository dispoRepo;
	@Autowired
	private EstadoRepository estadoRepo;
	
	private Integer maiorIndisponibilidade = 0;
	private Integer indisponibilidade = 0;
	private EstadoDisponibilidade estdisp;
	private List<Estado> estados = new ArrayList<>();
	
	
	final String url = "http://www.nfe.fazenda.gov.br/portal/disponibilidade.aspx";
	
	private final long SEGUNDO = 1000; 
   private final long MINUTO = SEGUNDO * 60; 

	
    
	@Scheduled(fixedDelay = MINUTO * 5)
	public void verificarCadaCincoMinutos() {
		try {			
			final Document document = Jsoup.connect(url).get();

			for(Element row : document.select("table.tabelaListagemDados tr")) {
				
				Autorizador aut1 = new Autorizador();
				List<Autorizador> auts = new ArrayList<>();
				DisponibilidadeServicoNF dispoNF = new DisponibilidadeServicoNF();
			
				if(row.select("td:nth-of-type(1)").text().equals("")) {
					continue;
				
				}else {
					
					final String autorizador = row.select("td:nth-of-type(1)").text();
					
					auts = autoRepo.findByNome(autorizador);
					
					if(auts.size() > 0)
						aut1 = auts.get(0);
					
					dispoNF.setAutorizador(aut1);
					
					if(row.select("td:nth-of-type(6)").outerHtml().contains("verde")) {
						dispoNF.setDisponibilidade("Disponível");
					}else if(row.select("td:nth-of-type(6)").outerHtml().contains("amarela")) {
						dispoNF.setDisponibilidade("Instável");
					}else {
						dispoNF.setDisponibilidade("Indisponível");
					}
					
					dispoNF.setMomentoConsulta(new Date());
					dispoRepo.save(dispoNF);
				}
			}
			
		}catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
	public List<EstadoDisponibilidade> atualTodosEstados(){
		
		verificarCadaCincoMinutos();
		List<EstadoDisponibilidade> ret = new ArrayList<>();
		 
		
		estadoRepo.findAll().forEach(e -> {
			e.getAutorizadores().forEach(a -> {
					estdisp = new EstadoDisponibilidade();
					estdisp.setEstado(e);
					estdisp.setAutorizador(a);
					estdisp.setDisponibilidade(a.getDisponiblidadeServicos().get(a.getDisponiblidadeServicos().size()-1));
					System.out.println(estdisp.toString());
					
					ret.add(estdisp);
			});
		});
		
		return ret;
		
	}
	
	
	
	public List<EstadoDisponibilidade> atualPorEstado(String UF) {
		
		verificarCadaCincoMinutos();
		List<EstadoDisponibilidade> ret = new ArrayList<>();
		estados = estadoRepo.findBySigla(UF);
		System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>");
		// Coleta do estado
		if(estados.size() > 0) {
			System.out.println("<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<");	
			estados.get(0).getAutorizadores().forEach(a -> {
				estdisp = new EstadoDisponibilidade();
				estdisp.setEstado(estados.get(0));
				estdisp.setAutorizador(a);
				estdisp.setDisponibilidade(a.getDisponiblidadeServicos().get(a.getDisponiblidadeServicos().size()-1));
				ret.add(estdisp);
			});
		}
		
		return ret;
	}
	
	public List<String> diponibilidadeEntreDatas(String dataInicial, String dataFinal) {
		return dispoRepo.findDisponibilidadesEntreDatas(dataInicial, dataFinal);
	}
	
	public List<Estado> maiorIndisponibilidade() {
		List<String> disponibilidadeEstados = new ArrayList<>();
		List<Estado> retorno = new ArrayList<>();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		disponibilidadeEstados = diponibilidadeEntreDatas("2021-01-01",sdf.format(new Date()));
				
		disponibilidadeEstados.forEach((e) -> {
			if(e.contains("Indisponível")) {
				indisponibilidade++;
				if(maiorIndisponibilidade < indisponibilidade) {
					retorno.clear();
					retorno.add(estadoRepo.findByNome(e.split(",")[1]).get(0));
				}else if(maiorIndisponibilidade == indisponibilidade) {
					retorno.add(estadoRepo.findByNome(e).get(0));
				}
			}
		});
		return retorno;
	}
	

}
