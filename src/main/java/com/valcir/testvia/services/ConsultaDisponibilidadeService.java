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
import com.valcir.testvia.repositories.AutorizadorRepository;
import com.valcir.testvia.repositories.DisponibilidadeServicoNFRepository;


@Component 
@EnableScheduling
@Service
public class ConsultaDisponibilidadeService {
	
	
	@Autowired
	private AutorizadorRepository autoRepo;
	@Autowired
	private DisponibilidadeServicoNFRepository dispoRepo;
	
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
	

}
