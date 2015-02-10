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
public class CrawlerGarde {

	@Autowired
	PharmacyService pharService;

	@PostConstruct
	public void crawle() {

		String url = "http://www.laboelallali.com/Pharmacie-de-garde.html";
		try {
			Document document = Jsoup.connect(url).get();
			// get the element which contains all pharmacies
			Element sectionElements = document.getElementsByClass("itemList")
					.first();
			// get all pharmacies' list
			Elements childElements = sectionElements
					.getElementsByClass("catItemView");

			// passing by each one to get details

			for (Element pharmarcieModel : childElements) {
				Pharmacy pharmacy = new Pharmacy();
				// get name element
				Element pharNom = pharmarcieModel.getElementsByTag("h3")
						.first();
				pharmacy.setName("Pharmacie "+pharNom.text());
				// get adress element
				Element pharAdress = pharmarcieModel.getElementsByTag("li")
						.get(1);
				pharmacy.setAdress(pharAdress.text().substring(7));
				// get numbre element
				Element pharTele = pharmarcieModel.getElementsByTag("li")
						.get(0);
				pharmacy.setTele(pharTele.text().substring(3));
				pharmacy.setGarde(true);
				// testing

				
					pharService.sendToApi(pharmacy);
				

			System.out.println("********************garde**************");
//				System.out.println("nom : " + pharmacy.getName());
//				System.out.println("adress : " + pharmacy.getAdress());
//				System.out.println("tele : " + pharmacy.getTele());
			}

		} catch (IOException e) {
			System.out.println("IOException in Job: " + e);
		}

	}
}
