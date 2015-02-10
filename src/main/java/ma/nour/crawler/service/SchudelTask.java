package ma.nour.crawler.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;


@Service
@EnableScheduling
public class SchudelTask {

	@Autowired
	CrawlerGarde crawlerGarde;
	/**
	 * Schedul the call to crawle all pharmacies garde to every 24hours 
	 * 
	 * 24*60*60*1000=86400000
	 * we can use cron as in linux or ... fixedDelay to stay proper 
	 */
	@Scheduled(fixedDelay=86400000)
	public void reloadPharmaciesGarde(){
		crawlerGarde.crawle();
		System.out.println("inside schedul");
	}
}
