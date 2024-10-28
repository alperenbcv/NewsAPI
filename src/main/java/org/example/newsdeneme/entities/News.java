package org.example.newsdeneme.entities;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
@Entity
@Table(name = "tblnews")
//@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class) //ya da doğrudan bu anotasyonu kullanarak field
// isimlerimizi snakecase'e cevirerek json fieldlari ile eslestirebiliriz!
//@JsonIgnoreProperties(ignoreUnknown = true) json verisindeki fazladan alanlari yok sayar, tum fieldlari tutmaya
// gerek kalmaz! Fakat varsayilan davranis da bunu saglar yani default olarak tum alanlari tutmamiza gerek yoktur!
public class News extends BaseEntity{
//	@JsonProperty("author") //Json Objesindeki author field'i ile bizim fieldimizi eslestirir fakat field isimleri
			//ayni ise bu anotasyona gerek kalmaz!
	String author;
	String title;
	String description;
	String url;
	String urlToImage;
	String publishedAt;
	String content;
	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "source_id", referencedColumnName = "id")
	Source source;
}