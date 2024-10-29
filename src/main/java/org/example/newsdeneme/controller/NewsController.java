package org.example.newsdeneme.controller;

import lombok.RequiredArgsConstructor;
import org.example.newsdeneme.dto.response.BaseResponse;
import org.example.newsdeneme.dto.response.NewsApiResponseDto;
import org.example.newsdeneme.entities.News;
import org.example.newsdeneme.entities.URLCategoryParameters;
import org.example.newsdeneme.entities.URLCountryParameters;
import org.example.newsdeneme.service.NewsService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/news")
public class NewsController {
	private final NewsService newsService;

	@GetMapping("/get-news")
	public ResponseEntity<BaseResponse<List<News>>> getNews(
			@RequestParam URLCategoryParameters category,
			@RequestParam URLCountryParameters country,
			@RequestParam(required = false) String fromDate,
			@RequestParam(required = false) String toDate) {

		DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
		LocalDate from = null;
		LocalDate to = null;

		try {
			if (fromDate != null) {
				from = LocalDate.parse(fromDate, dateFormatter);
			}
			if (toDate != null) {
				to = LocalDate.parse(toDate, dateFormatter);
			}
		} catch (DateTimeParseException e) {
			return ResponseEntity.badRequest().body(BaseResponse.<List<News>>builder()
					.code(400)
					.message("Tarih formatı geçersiz. Doğru format: yyyy-MM-dd")
					.success(false)
					.build());
		}

		String apiUrl = "https://newsapi.org/v2/top-headlines?country=" + country + "&category=" + category + "&apiKey=e4cf8decc94b4eb19c65ad108eaf36ff";
		if (from != null && to != null) {
			apiUrl += "&from=" + fromDate + "&to=" + toDate;
		}

		return ResponseEntity.ok(BaseResponse.<List<News>>builder()
				.data(newsService.getNews(apiUrl))
				.code(200)
				.message("News brought successfully")
				.success(true)
				.build());
	}
}