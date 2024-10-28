package org.example.newsdeneme.controller;

import lombok.RequiredArgsConstructor;
import org.example.newsdeneme.dto.response.BaseResponse;
import org.example.newsdeneme.dto.response.NewsApiResponseDto;
import org.example.newsdeneme.entities.News;
import org.example.newsdeneme.service.NewsService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/news")
public class NewsController {
	private final NewsService newsService;
	
	@GetMapping("/get-news")
	public ResponseEntity<BaseResponse<List<News>>> getNews(@RequestParam String apiUrl) {
		return ResponseEntity.ok(BaseResponse.<List<News>>builder()
		                                 .data(newsService.getNews(apiUrl))
				                         .code(200)
				                         .message("News brought successfully")
				                         .success(true)
				                         .build());
	}
}