/**
 * 
 */
package com.ml.survey.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.ml.survey.entity.SurveyResponseQA;

public interface SurveyResponseQARepository extends CrudRepository<SurveyResponseQA, Long> {
	List<SurveyResponseQA> findBySurveyId(Long surveyId);
	List<SurveyResponseQA> findBySurveyIdAndQuestionId(Long surveyId, Long questionId);
	List<SurveyResponseQA> findBySurveyIdAndQuestionIdAndAnswerId(Long surveyId, Long questionId, Long answerId);
}
