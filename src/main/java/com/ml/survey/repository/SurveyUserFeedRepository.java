package com.ml.survey.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.ml.survey.entity.SurveyUserFeed;

@Repository
public interface SurveyUserFeedRepository extends CrudRepository<SurveyUserFeed, Long> {
	List<SurveyUserFeed> findBySurveyId(Long surveyId);
	List<SurveyUserFeed> findByUserInfo(String userInfo);
}
