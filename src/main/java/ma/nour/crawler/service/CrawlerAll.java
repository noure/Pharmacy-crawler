package ma.nour.crawler.service;

import java.io.IOException;

import javax.annotation.PostConstruct;

import ma.nour.crawler.entity.Pharmacy;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class CrawlerAll {

	@Autowired
	PharmacyService pharService;

	//@PostConstruct
	public void crawle() { 

		String url = "http://www.anahna.com/pharmacies-agadir-ca7-qa0.html";
		try {
			Document document = Jsoup.connect(url).get();
			// get the element which contains all pharmacies
			Element sectionElements = document.getElementsByTag("section")
					.first();
			// get all pharmacies' list
			Elements childElements = sectionElements
					.getElementsByTag("article");

			// extracting information now

			for (Element pharmarcieModel : childElements) {

				Pharmacy pharmacie = new Pharmacy();

				// get name element
				Element pharNom = pharmarcieModel.getElementsByTag("h1")
						.first();
				pharmacie.setName(pharNom.text());
				// get adress element
				Element pharAdress = pharmarcieModel.getElementsByTag("p").get(
						1);
				pharmacie.setAdress(pharAdress.text());
				// get phone number element
				Element pharTele = pharmarcieModel.getElementsByTag("p").get(2);
				
				pharmacie.setTele(pharTele.text().substring(5));
				pharmacie.setGarde(false);
				

				//send to api
				pharService.sendToApi(pharmacie);
				
//				
//				System.out.println( "**********************************");
//				System.out.println("nom : " + pharmacie.getName());
//				System.out.println("adress : " + pharmacie.getAdress());
//				System.out.println("tele : " + pharmacie.getTele());

			}
		} catch (IOException e) {
			System.out.println("IOException in Job: " + e);
		}
	}

}
