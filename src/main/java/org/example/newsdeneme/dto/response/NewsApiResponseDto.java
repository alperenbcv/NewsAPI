package org.example.newsdeneme.dto.response;


import org.example.newsdeneme.entities.News;

import java.util.List;

//NewsApiResponseDto, API’nin döndürdüğü yanıt yapısına uymak için oluşturulmuş bir DTO (Data Transfer Object)’dur.
//Eğer API yanıtında değişiklik olursa (örneğin, yeni bir alan eklenirse),
//NewsApiResponseDto içinde bu alanı eklemek daha kolay olur. Böylece, News sınıfını değiştirmeden API yanıtında
// değişiklik yapabilirsiniz.
public record NewsApiResponseDto(
		List<News> articles
) {
}