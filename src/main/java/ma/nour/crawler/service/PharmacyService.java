package ma.nour.crawler.service;

import ma.nour.crawler.entity.Pharmacy;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Component
public class PharmacyService {

	// @Autowired
	// CrawlerAll crawlerAll;
	//
	// @Autowired
	// CrawlerGarde garde;

	// @Scheduled
	// public void reload(){
	// crawlerAll.crawle();
	// garde.crawle();
	// }

	public void sendToApi(Pharmacy pharmacy) {
		RestTemplate rest = new RestTemplate();
		String url = "http://127.0.0.1:8282/api/pharmacies/create";
		rest.postForObject(url, pharmacy, Pharmacy.class);
	}

	public boolean available(String pharmacyName) {
		RestTemplate rest = new RestTemplate();

		String url = "http://localhost:8282/api/pharmacies/{name}";
		Pharmacy pharResponse = rest.postForObject(url, pharmacyName,
				Pharmacy.class);
		return pharResponse != null;
	}

}
