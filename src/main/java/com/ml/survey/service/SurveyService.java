package com.ml.survey.service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Map.Entry;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import javax.transaction.Transactional;
import javax.transaction.Transactional.TxType;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ml.survey.common.ApplicationErrorCode;
import com.ml.survey.common.LogMethodExecution;
import com.ml.survey.controller.qa.vo.AnswerIO;
import com.ml.survey.controller.qa.vo.QuestionIO;
import com.ml.survey.controller.survey.vo.AnswerStatIO;
import com.ml.survey.controller.survey.vo.QuestionStatIO;
import com.ml.survey.controller.survey.vo.SurveyIO;
import com.ml.survey.controller.survey.vo.SurveyReportIO;
import com.ml.survey.controller.survey.vo.SurveyUserFeedIO;
import com.ml.survey.entity.Survey;
import com.ml.survey.entity.SurveyQuestion;
import com.ml.survey.entity.SurveyResponseQA;
import com.ml.survey.entity.SurveyUserFeed;
import com.ml.survey.exception.ApplicationException;
import com.ml.survey.repository.SurveyRepository;
import com.ml.survey.repository.SurveyResponseQARepository;
import com.ml.survey.repository.SurveyUserFeedRepository;

/**
 * This Class is implements business functionalities of Survey Services
 * @author Kaustuv Maji
 *
 */
@Service
public class SurveyService {

	@Autowired
	SurveyRepository surveyRepository;

	@Autowired
	SurveyUserFeedRepository surveyUserFeedRepository;

	@Autowired
	SurveyResponseQARepository surveyResponseQARepository;

	@Autowired
	QAService qaService;

	@Transactional(value = TxType.REQUIRED)
	@LogMethodExecution
	public SurveyIO save(SurveyIO surveyIO) throws ApplicationException {
		Survey survey = new Survey();
		survey.setSurveyDescription(surveyIO.getSurveyDescriptions());
		survey.setTopic(surveyIO.getTopic());
		if(null != surveyIO.getQuestions()) {
			List<SurveyQuestion> surveyQuestions = surveyIO.getQuestions().stream().map(e -> new SurveyQuestion(e.getQuestionId().longValue())).collect(Collectors.toList());
			survey.setSurveyQuestions(surveyQuestions);
		}
		Survey savedEntity = surveyRepository.save(survey);
		surveyIO.setSurveyId(savedEntity.getId());
		return surveyIO;
	}

	@LogMethodExecution
	public SurveyIO get(Long id) throws ApplicationException {
		Optional<Survey> surveyOptional = surveyRepository.findById(id);
		Survey survey = surveyOptional.get();
		if (survey == null) {
			throw new ApplicationException(ApplicationErrorCode.RESOURCE_NOTFOUND_EXCEPTION_CODE.label, "Survey Not Found");
		}
		SurveyIO surveyIO = new SurveyIO();
		surveyIO.setSurveyId(survey.getId());
		surveyIO.setCreatedBy(null);
		surveyIO.setCreatedOn(null);
		surveyIO.setSurveyDescriptions(survey.getSurveyDescription());
		surveyIO.setTopic(survey.getTopic());
		Set<QuestionIO> questions = new HashSet<QuestionIO>();
		for (SurveyQuestion sq : survey.getSurveyQuestions()) {
			questions.add(qaService.getQuestion(sq.getQuestionid()));
		}
		surveyIO.setQuestions(questions);
		return surveyIO;
	}

	@Transactional(value = TxType.REQUIRED)
	@LogMethodExecution
	public SurveyUserFeedIO submit(SurveyUserFeedIO surveyUserFeedIO) throws ApplicationException {
		Survey survey = surveyRepository.findById(surveyUserFeedIO.getSurveyId()).get();
		if (survey == null) {
			throw new ApplicationException(ApplicationErrorCode.RESOURCE_NOTFOUND_EXCEPTION_CODE.label, "Survey " + surveyUserFeedIO.getSurveyId() + " Not Found");
		}
		SurveyUserFeed suf = new SurveyUserFeed();
		List<SurveyResponseQA> surveyAnswerList = new ArrayList<SurveyResponseQA>();
		for (Entry<Long, Long> qaref : surveyUserFeedIO.getQuestionAndAnswerRef().entrySet()) {
			surveyAnswerList.add(new SurveyResponseQA(qaref.getKey(), qaref.getValue(), survey.getId()));
		}
		suf.setSurveyAnswerList(surveyAnswerList);
		suf.setUserInfo(surveyUserFeedIO.getUserInfo());
		suf.setSurveyId(survey.getId());
		SurveyUserFeed savedEntity = surveyUserFeedRepository.save(suf);
		surveyUserFeedIO.setId(savedEntity.getId());
		return surveyUserFeedIO;

	}

	@LogMethodExecution
	public SurveyReportIO getSurveyStatictics(Long surveyId) throws ApplicationException {
		SurveyIO survey = this.get(surveyId);
		if (survey == null) {
			throw new ApplicationException(ApplicationErrorCode.RESOURCE_NOTFOUND_EXCEPTION_CODE.label, "Survey " + surveyId + " Not Found");
		}
		SurveyReportIO surveyReportIO = new SurveyReportIO();
		surveyReportIO.setSurveyId(survey.getSurveyId());
		surveyReportIO.setSurveyDescriptions(survey.getSurveyDescriptions());
		surveyReportIO.setTopic(survey.getTopic());
		surveyReportIO.setParticipationCounter(Long.valueOf(surveyUserFeedRepository.findBySurveyId(surveyId).size()));
		surveyReportIO.setCreatedBy(survey.getCreatedBy());
		surveyReportIO.setCreatedOn(survey.getCreatedOn());
		List<QuestionStatIO> questionStatistics = new ArrayList<QuestionStatIO>();
		for (QuestionIO question : survey.getQuestions()) {
			QuestionStatIO qStatIO = new QuestionStatIO();
			qStatIO.setContent(question.getContent());
			qStatIO.setQuestionId(question.getQuestionId());
			qStatIO.setCounter(Long.valueOf(surveyResponseQARepository.findBySurveyIdAndQuestionId(surveyId, question.getQuestionId()).size()));
			List<AnswerStatIO> answersStat = new ArrayList<AnswerStatIO>();
			for (AnswerIO answerIO : question.getAnswers()) {
				AnswerStatIO answerStatIO = new AnswerStatIO();
				answerStatIO.setAnswerId(answerIO.getId());
				answerStatIO.setContent(answerIO.getContent());
				answerStatIO.setCounter(Long.valueOf(surveyResponseQARepository.findBySurveyIdAndQuestionIdAndAnswerId(surveyId, question.getQuestionId(), answerIO.getId()).size()));
				answersStat.add(answerStatIO);
			}
			Collections.sort(answersStat);
			qStatIO.setAnswerStatistics(answersStat);
			questionStatistics.add(qStatIO);
		}
		Collections.sort(questionStatistics);
		surveyReportIO.setQuestionStatistics(questionStatistics);
		return surveyReportIO;
	}

}
