package com.ml.survey.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ml.survey.entity.Survey;


@Repository
public interface SurveyRepository  extends JpaRepository <Survey,Long>{ 
}
