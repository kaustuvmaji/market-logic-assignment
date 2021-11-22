package com.ml.survey.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ml.survey.entity.Question;

@Repository
public interface QuestionRepository extends JpaRepository<Question, Long> {
}
