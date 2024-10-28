package org.example.newsdeneme.repository;

import org.example.newsdeneme.entities.News;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface NewsRepository extends JpaRepository<News, UUID> {
}