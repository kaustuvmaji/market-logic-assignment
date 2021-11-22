package com.ml.survey.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.ml.survey.entity.Answer;

@Repository
public interface AnswerRepository extends CrudRepository<Answer, Long> {
}
