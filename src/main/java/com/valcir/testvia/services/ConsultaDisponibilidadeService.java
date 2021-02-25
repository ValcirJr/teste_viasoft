package com.valcir.testvia.services;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component 
@EnableScheduling
public class ConsultaDisponibilidadeService {
	
	final String url = "http://www.nfe.fazenda.gov.br/portal/disponibilidade.aspx";
	
	private final long SEGUNDO = 1000; 
    private final long MINUTO = SEGUNDO * 60; 

	private String status = "";
	
    
	@Scheduled(fixedDelay = MINUTO * 5)
	public void verificarCadaCincoMinutos() {
		try {
			
			final Document document = Jsoup.connect(url).get();

			for(Element row : document.select("table.tabelaListagemDados tr")) {
				if(row.select("td:nth-of-type(1)").text().equals("")) {
					continue;
				}else {
					final String estado = row.select("td:nth-of-type(1)").outerHtml();
					
					if(row.select("td:nth-of-type(6)").outerHtml().contains("verde")) {
						status = "Disponível";
					}else if(row.select("td:nth-of-type(6)").outerHtml().contains("amarela")) {
						status = "Instável";
					}else {
						status = "Indisponível";
					}
				}
			}
			
		}catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	

}
