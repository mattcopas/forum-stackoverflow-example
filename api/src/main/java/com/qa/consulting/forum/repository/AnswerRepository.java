package com.qa.consulting.forum.repository;

import com.qa.consulting.forum.entities.Answer;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

/**
 * Created by Matt on 12/07/2017.
 */
@RepositoryRestResource
public interface AnswerRepository extends PagingAndSortingRepository<Answer, Long> {
}
