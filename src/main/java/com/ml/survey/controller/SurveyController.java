package com.ml.survey.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.ml.survey.common.LogMethodExecution;
import com.ml.survey.controller.survey.vo.SurveyIO;
import com.ml.survey.controller.survey.vo.SurveyReportIO;
import com.ml.survey.controller.survey.vo.SurveyUserFeedIO;
import com.ml.survey.exception.ApplicationException;
import com.ml.survey.service.SurveyService;

/**
 * This Class enable REST Interface for Survey services.
 * 
 * @author Kaustuv Maji
 */
@RestController
@RequestMapping("/survey")
@Validated
public class SurveyController {

	@Autowired
	SurveyService surveyService;

	@PostMapping("/")
	@ResponseStatus(HttpStatus.CREATED)
	@LogMethodExecution
	public SurveyIO addSurvey(@Valid @RequestBody SurveyIO survey) throws ApplicationException {
		return surveyService.save(survey);
	}

	@GetMapping("/{surveyId}")
	@LogMethodExecution
	public SurveyIO getSurvey(@PathVariable Long surveyId) throws ApplicationException {
		return surveyService.get(surveyId);
	}

	@PostMapping("/submit")
	@ResponseStatus(HttpStatus.CREATED)
	@LogMethodExecution
	public SurveyUserFeedIO submit(@Valid @RequestBody SurveyUserFeedIO surveyUserFeedIO) throws ApplicationException {
		return surveyService.submit(surveyUserFeedIO);
	}

	@GetMapping("/{surveyId}/statistics")
	@LogMethodExecution
	public SurveyReportIO getSurveyStaticticsBySurveyId(@PathVariable Long surveyId) throws ApplicationException {
		return surveyService.getSurveyStatictics(surveyId);
	}

}
