package com.ml.survey;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertThrows;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.Set;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.AutoConfigurationPackage;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import com.ml.survey.controller.qa.vo.AnswerIO;
import com.ml.survey.controller.qa.vo.QuestionIO;
import com.ml.survey.entity.Answer;
import com.ml.survey.entity.Question;
import com.ml.survey.exception.ApplicationException;
import com.ml.survey.repository.AnswerRepository;
import com.ml.survey.repository.QuestionRepository;
import com.ml.survey.service.QAService;
import com.ml.survey.service.SurveyService;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigurationPackage
public class SurveyServiceTest {

	@MockBean
	private AnswerRepository mockAnswerRepository;
	
	@MockBean
	private QuestionRepository mockQuestionRepository;
	
	@MockBean
	private SurveyService mockSurveyService; 
	
	@Autowired
	private QAService qaService;

	private Answer mockAnswer() {
		Answer mockAnswer = new Answer();
		mockAnswer.setAnswerId(1L);
		mockAnswer.setContent("My Name is Kaustuv");
		return mockAnswer;
	}
	
	private Question mockQuestion() {
		Question question = new Question("What is your name?");
		question.setQuestionId(1L);
		Set<Answer> answers = new HashSet<Answer>();
		answers.add(new Answer("Kaustuv"));
		question.setAnswers(answers);
		Answer mockAnswer = mockAnswer();
		question.addAnswer(mockAnswer);
		return question;
	}
	
	@BeforeClass
	@Before
	public void setUp() {
		System.out.println("-----> SETUP <-----");
		Question mockQuestion = mockQuestion();
		when(mockAnswerRepository.findById(1L)).thenReturn(Optional.of(mockAnswer()));
		when(mockQuestionRepository.save(new Question("live"))).thenReturn(mockQuestion);
//		when(mockQAService.getQuestion(2L)).thenThrow(new ApplicationException(ApplicationErrorCode.RESOURCE_NOTFOUND_EXCEPTION_CODE.label, "Question Not Found"));
		doNothing().when(mockQuestionRepository).deleteById(3L);
		doNothing().when(mockAnswerRepository).deleteById(3L);
	}


	
    @Test
    public void find_answerId() throws Exception {
    	Answer mockAnswer = mockAnswer();
    	when(mockAnswerRepository.findById(1L)).thenReturn(Optional.of(mockAnswer));
    	AnswerIO answer = qaService.getAnswer(1L);
    	assertEquals(answer.getContent(),"My Name is Kaustuv");
    }


    @Test
    public void find_questionId() throws Exception {
		when(mockQuestionRepository.findById(1L)).thenReturn(Optional.of(mockQuestion()));
    	QuestionIO question = qaService.getQuestion(1L);
    	assertEquals(question.getContent(),"What is your name?");
    }
    
    @Test //(expected = ApplicationException.class)
    public void find_questionId_Error() throws Exception {
    	assertThrows(ApplicationException.class, () -> {
        	qaService.getQuestion(15L);
        });
    }
    
    @Test //(expected = ApplicationException.class)
    public void find_answerId_Error() throws Exception {
    	assertThrows(NoSuchElementException.class, () -> {
        	qaService.getAnswer(16L);
        });
    }
	
    @Test
    public void find_questions() throws Exception {
    	List<Question> mockQuestions = new ArrayList<Question>();
    	mockQuestions.add(mockQuestion());
		when(mockQuestionRepository.findAll()).thenReturn(mockQuestions);
    	assertEquals(1,qaService.findQuestions().size());
    }
	
//    @Test
    public void save_questions() throws Exception {
		Question question = new Question("What is your name?");
		question.setQuestionId(1L);
		Set<Answer> answers = new HashSet<Answer>();
		answers.add(new Answer("Kaustuv"));
		question.setAnswers(answers);
		Answer mockAnswer = mockAnswer();
		question.addAnswer(mockAnswer);
		when(mockQuestionRepository.save(new Question("live"))).thenReturn(mockQuestion());
    	assertNotNull(qaService.addQuestion(new QuestionIO(null,"What is your name?")));
    }
}
