package org.lanadvice.repository;

import org.lanadvice.model.Survey;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.time.LocalDateTime;
import java.util.List;

public interface SurveyRepository extends PagingAndSortingRepository<Survey, Long> {
    List<Survey> findByCreatedBetween(LocalDateTime periodStart, LocalDateTime periodEnd);
}
