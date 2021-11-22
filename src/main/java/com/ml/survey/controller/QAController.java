package com.ml.survey.controller;

import java.util.List;

import javax.validation.Valid;
import javax.validation.constraints.Min;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.ml.survey.common.LogMethodExecution;
import com.ml.survey.controller.qa.vo.AnswerIO;
import com.ml.survey.controller.qa.vo.QuestionIO;
import com.ml.survey.exception.ApplicationException;
import com.ml.survey.service.QAService;

/**
 * This Class enable REST Interface for Question & Answer services
 * 
 * @author Kaustuv Maji
 */
@RestController
@RequestMapping("/")
@Validated
public class QAController {

	@Autowired
	QAService qaService;

	@PostMapping(value = "/questions", produces = { MediaType.APPLICATION_JSON_VALUE }, consumes = {
			MediaType.APPLICATION_JSON_VALUE })
	@ResponseStatus(HttpStatus.CREATED)
	@LogMethodExecution
	public @ResponseBody QuestionIO addQuestion(@Valid @RequestBody QuestionIO question) throws ApplicationException {
		return qaService.addQuestion(question);
	}

	@PutMapping(value = "/questions", produces = { MediaType.APPLICATION_JSON_VALUE }, consumes = {
			MediaType.APPLICATION_JSON_VALUE })
	@LogMethodExecution
	@ResponseStatus(HttpStatus.OK)
	public @ResponseBody QuestionIO updateQuestion(@RequestBody QuestionIO question) throws ApplicationException {
		return qaService.updateQuestion(question);
	}

	@GetMapping(value = "/questions", produces = { MediaType.APPLICATION_JSON_VALUE })
	@ResponseStatus(HttpStatus.OK)
	@LogMethodExecution
	public @ResponseBody List<QuestionIO> findQuestions() {
		return qaService.findQuestions();
	}

	@GetMapping(value = "/questions/{questionId}", produces = { MediaType.APPLICATION_JSON_VALUE })
	@ResponseStatus(HttpStatus.OK)
	@LogMethodExecution
	public @ResponseBody QuestionIO getQuestion(@PathVariable Long questionId) throws ApplicationException {
		return qaService.getQuestion(questionId);
	}

	@DeleteMapping(value = "/questions/{questionId}", produces = { MediaType.APPLICATION_JSON_VALUE })
	@ResponseStatus(HttpStatus.NO_CONTENT)
	@LogMethodExecution
	public void deleteQuestion(@PathVariable @Min(1) Long questionId) {
		qaService.deleteQuestion(questionId);
	}

	@GetMapping(value = "/answers", produces = { MediaType.APPLICATION_JSON_VALUE })
	@ResponseStatus(HttpStatus.OK)
	@LogMethodExecution
	public @ResponseBody List<AnswerIO> findAnswers() {
		return qaService.findAnswers();
	}

	@GetMapping(value = "/answers/{answerId}", produces = { MediaType.APPLICATION_JSON_VALUE })
	@ResponseStatus(HttpStatus.OK)
	@LogMethodExecution
	public @ResponseBody AnswerIO getAnswer(@PathVariable @Min(1) Long answerId) throws ApplicationException {
		return qaService.getAnswer(answerId);
	}

	@PutMapping(value = "/questions/{questionId}/answers", produces = { MediaType.APPLICATION_JSON_VALUE })
	@ResponseStatus(HttpStatus.OK)
	@LogMethodExecution
	public @ResponseBody AnswerIO updateAnswer(@PathVariable @Min(1) Long questionId, @RequestBody AnswerIO answer)
			throws ApplicationException {
		return qaService.updateAnswer(questionId, answer);
	}

	@DeleteMapping(value = "/questions/{questionId}/answers/{answerId}", produces = {
			MediaType.APPLICATION_JSON_VALUE })
	@ResponseStatus(HttpStatus.NO_CONTENT)
	@LogMethodExecution
	public void deleteAnswerByQuestionId(@PathVariable @Min(1) Long questionId, @PathVariable @Min(1) Long answerId)
			throws ApplicationException {
		qaService.deleteAnswerByQuestionId(questionId, answerId);
	}

}
