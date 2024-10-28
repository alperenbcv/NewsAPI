package org.example.newsdeneme.service;

import lombok.RequiredArgsConstructor;
import org.example.newsdeneme.dto.response.NewsApiResponseDto;
import org.example.newsdeneme.entities.News;
import org.example.newsdeneme.repository.NewsRepository;
import org.springframework.boot.autoconfigure.web.reactive.function.client.WebClientAutoConfiguration;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.util.List;

@Service
@RequiredArgsConstructor
public class NewsService {
	public final NewsRepository newsRepository;
	private final WebClient.Builder webClientBuilder;
	
	
	/**
	 * Fetches news data from the specified API endpoint.
	 *
	 * @param apiUrl The URL of the API endpoint
	 * @get Specifies the request type as HTTP GET
	 * @uri Specifies the API endpoint to be called
	 * @retrieve Initializes the HTTP request to the API and prepares to receive a response
	 * @bodyToMono Converts the response to an asynchronous Mono<T> object
	 * @block Converts the asynchronous Mono<T> object into a synchronous T object by waiting for the response
	 * @return List of news fetched from the API
	 *
	 * Synchronous data -> Wait until the response is received
	 * Asynchronous data -> Receive the response as soon as it's available meanwhile process' other tasks
	 */
	public List<News> getNews(String apiUrl) {
		NewsApiResponseDto newsMono = webClientBuilder
                .build()
                .get()
                .uri(apiUrl)
                .retrieve()
                .bodyToMono(NewsApiResponseDto.class)
				.block();
		return newsMono.articles();
	}
	/*
	bodyToMono(NewsApiResponseDto.class):
		Burada asıl çevirme işlemi gerçekleşir.
		Alınan JSON verisi, NewsApiResponseDto sınıfına dönüştürülür. Bu işlem Jackson tarafından yapılır.
		Jackson, NewsApiResponseDto sınıfında articles alanını görür ve JSON yanıtındaki "articles" listesini
		List<News> türüne otomatik olarak deserialize eder.
		News sınıfı ile JSON’daki her bir article objesini News nesnesine çevirir ve articles listesine ekler.
		Dönüşteki JSON verisindeki dizinin (array) adı, DTO sınıfınızdaki liste alanının adı ile eşleşmelidir.
		
		Java Sınıfı ve JSON Alanlarının Eşleşmesi: Jackson, JSON’daki her bir alanı
		Java sınıfındaki ilgili alana eşlemek için refleksiyon (reflection) kullanır.
		JSON alan adları ve Java sınıfındaki alan adları uyumluysa,
		Jackson JSON verisindeki değerleri Java sınıfındaki alanlara kopyalar.

	 */
}