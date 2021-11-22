package com.ml.survey.service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import javax.transaction.Transactional;
import javax.transaction.Transactional.TxType;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.ml.survey.common.ApplicationErrorCode;
import com.ml.survey.common.LogMethodExecution;
import com.ml.survey.controller.qa.vo.AnswerIO;
import com.ml.survey.controller.qa.vo.QuestionIO;
import com.ml.survey.entity.Answer;
import com.ml.survey.entity.Question;
import com.ml.survey.exception.ApplicationException;
import com.ml.survey.repository.AnswerRepository;
import com.ml.survey.repository.QuestionRepository;

/**
 * This Class is implements business functionalities of Question Answer Services
 * 
 * @author Kaustuv Maji
 *
 */
@Service
public class QAService {

	private static Logger LOGGER = LoggerFactory.getLogger(QAService.class);

	@Autowired
	QuestionRepository questionRepository;

	@Autowired
	AnswerRepository answerRepository;

	@Transactional(value = TxType.REQUIRED)
	@LogMethodExecution
	public QuestionIO addQuestion(QuestionIO questionIO) throws ApplicationException {
		Question question = new Question(questionIO.getContent());
		if (null != questionIO.getAnswers()) {
			for (AnswerIO answerIO : questionIO.getAnswers()) {
				Answer answer = new Answer(answerIO.getContent());
				question.addAnswer(answer);
			}
		}
		Question savedQuestion = questionRepository.save(question);
		if (null == savedQuestion || null == savedQuestion.getQuestionId()) {
			throw new ApplicationException(ApplicationErrorCode.SYSTEM_EXCEPTION_CODE.label,
					"Unable to save given auestion. Please try after some time.");
		}

		questionIO.setQuestionId(savedQuestion.getQuestionId());
		LOGGER.info("New Question added");
		return questionIO;
	}

	@Transactional(value = TxType.REQUIRED)
	@LogMethodExecution
	public QuestionIO updateQuestion(QuestionIO questionIO) throws ApplicationException {
		Question question = questionRepository.findById(questionIO.getQuestionId()).orElse(null);
		if (null == question) {
			this.addQuestion(questionIO);
		} else {
			if (StringUtils.hasLength(questionIO.getContent())) {
				question.setContent(questionIO.getContent());
			}
			questionIO.getAnswers().forEach(e -> {
				if (StringUtils.hasLength(e.getContent())) {
					if (!question.isAnswerPresent(e.getContent())) {
						question.addAnswer(new Answer(e.getContent()));
					}
				}
			});
		}
		Question updateQuestion = questionRepository.save(question);
		LOGGER.info("Question hasbeen updated ", updateQuestion);
		return questionIO;
	}

	@Transactional(value = TxType.REQUIRED)
	@LogMethodExecution
	public QuestionIO getQuestion(Long questionId) throws ApplicationException {
		Question question = questionRepository.findById(questionId).orElse(null);
		if (null == question) {
			throw new ApplicationException(ApplicationErrorCode.RESOURCE_NOTFOUND_EXCEPTION_CODE.label,
					"Question Not Found");
		}

		QuestionIO questionIO = new QuestionIO(question.getQuestionId(), question.getContent());
		LOGGER.info("Question hasbeen updated ", question.getAnswers());
		questionIO.setAnswers(question.getAnswers().stream().map(e -> new AnswerIO(e.getAnswerId(), e.getContent()))
				.collect(Collectors.toList()));
		return questionIO;
	}

	@LogMethodExecution
	public List<QuestionIO> findQuestions() {
		List<Question> questions = (ArrayList<Question>) questionRepository.findAll();
		List<QuestionIO> questionIOs = new ArrayList<QuestionIO>();
		for (Question q : questions) {
			QuestionIO questionIO = new QuestionIO();
			questionIO.setQuestionId(q.getQuestionId());
			questionIO.setContent(q.getContent());
			for (Answer a : q.getAnswers()) {
				AnswerIO answer = new AnswerIO(a.getAnswerId(), a.getContent());
				questionIO.addAnswer(answer);
			}
			questionIOs.add(questionIO);
		}
		return questionIOs;
	}

	@Transactional(value = TxType.REQUIRED)
	@LogMethodExecution
	public void deleteQuestion(Long questionId) {
		questionRepository.deleteById(questionId);
		LOGGER.info("Question deleted from database");
	}

	@Transactional(value = TxType.REQUIRED)
	@LogMethodExecution
	public AnswerIO updateAnswer(Long questionId, AnswerIO answerIO) throws ApplicationException {
		LOGGER.info("executing SurveyService.update(...)");
		Question question = questionRepository.findById(questionId).orElse(null);
		if (null != question) {
			question.getAnswers().stream().forEach(e -> {
				if (e.getAnswerId().equals(answerIO.getId())) {
					e.setContent(answerIO.getContent());
					answerRepository.save(e);
				}
			});
		} else {
			throw new ApplicationException(ApplicationErrorCode.RESOURCE_NOTFOUND_EXCEPTION_CODE.label,
					"Question Not Found");
		}
		return answerIO;
	}

	@Transactional(value = TxType.REQUIRED)
	@LogMethodExecution
	public AnswerIO getAnswer(Long answerId) throws ApplicationException {
		Answer answer = answerRepository.findById(answerId).get();
		if (null == answer) {
			throw new ApplicationException(ApplicationErrorCode.RESOURCE_NOTFOUND_EXCEPTION_CODE.label,
					"Answer Not Found");
		}
		return new AnswerIO(answer.getAnswerId(), answer.getContent());
	}

	@LogMethodExecution
	public List<AnswerIO> findAnswers() {
		List<Answer> answers = (ArrayList<Answer>) answerRepository.findAll();
		List<AnswerIO> answerIOs = answers.stream().map(a -> new AnswerIO(a.getAnswerId(), a.getContent()))
				.collect(Collectors.toList());
		return answerIOs;
	}

	@Transactional(value = TxType.REQUIRED)
	@LogMethodExecution
	public void deleteAnswerByQuestionId(Long questionId, Long answerId) throws ApplicationException {
		Question question = questionRepository.findById(questionId).get();
		if (question == null) {
			throw new ApplicationException(ApplicationErrorCode.RESOURCE_NOTFOUND_EXCEPTION_CODE.label,
					"Question Not Found");
		}
		boolean isMatch = false;
		for (Answer answer : question.getAnswers()) {
			if (answer.getAnswerId().longValue() == answerId.longValue()) {
				question.removeAnswer(answer);
				isMatch = true;
				break;
			}
		}
		if (!isMatch) {
			throw new ApplicationException(ApplicationErrorCode.RESOURCE_NOTFOUND_EXCEPTION_CODE.label,
					"Answer Not Found");
		}
		questionRepository.save(question);
		LOGGER.info("deleted successfully");
	}

}
