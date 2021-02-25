package com.valcir.testvia.services;

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
	
	public String atualPorEstado(String UF) {
		String ret = "";
		
		try {			
			final Document document = Jsoup.connect(url).get();
			List<Estado> estados = new ArrayList<>();
			Estado est = new Estado();
			
			estados = estadoRepo.findBySigla(UF);
			
			// Coleta do estado
			if(estados.size() > 0) {
				est = estados.get(0);
				
			}else {
				return "Estado não encontrado";
			}
			
			
			//Percorrendo tabela na URL
			for(Element row : document.select("table.tabelaListagemDados tr")) {				
				
				if(row.select("td:nth-of-type(1)").text().equals("")) {
					continue;
				}else {
					
					//Percorrendo autorizadores do estado
					for(Autorizador a : est.getAutorizadores()) {
						System.out.println("ASDIOJAASOIDIOASDJOIASJ >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>"+ row.select("td:nth-of-type(1)").text() +
						"<<<<<<<<<<<<<<<" + a.getNome());
						if(row.select("td:nth-of-type(1)").text().equals(a.getNome())) {						
							
							if(row.select("td:nth-of-type(6)").outerHtml().contains("verde")) {
								ret = ret + "Disponível em " + a.getNome() + "\n";
							}else if(row.select("td:nth-of-type(6)").outerHtml().contains("amarela")) {
								ret = ret +"Instável em " + a.getNome() + "\n";
							}else {
								ret = ret + "Indisponível em " + a.getNome() + "\n";
							}
						}	
					}
				}
			}
			
		}catch (Exception e) {
			e.printStackTrace();
		}
		
		
		System.out.println("ASDIOJAASOIDIOASDJOIASJ >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>"+ ret);
		return ret;
	}
	

}
