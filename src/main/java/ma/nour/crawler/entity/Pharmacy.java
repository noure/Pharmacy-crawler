package ma.nour.crawler.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import lombok.Data;
/**
 * 
 * @author noureddine
 *
 */

@Entity
@Data
public class Pharmacy {

	@Id
	@GeneratedValue
	private Integer id;

	private String name;

	private String adress;
	
	private String tele;
	
	private boolean garde;
}
