package com.ml.survey;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.apache.http.HttpHeaders;
import org.junit.Before;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ml.survey.controller.survey.vo.SurveyIO;
import com.ml.survey.controller.survey.vo.SurveyUserFeedIO;
import com.ml.survey.entity.Answer;
import com.ml.survey.entity.Question;
import com.ml.survey.entity.Survey;
import com.ml.survey.entity.SurveyQuestion;
import com.ml.survey.entity.SurveyResponseQA;
import com.ml.survey.entity.SurveyUserFeed;
import com.ml.survey.exception.ApplicationException;
import com.ml.survey.repository.AnswerRepository;
import com.ml.survey.repository.QuestionRepository;
import com.ml.survey.repository.SurveyRepository;
import com.ml.survey.repository.SurveyUserFeedRepository;
import com.ml.survey.service.SurveyService;

@RunWith(SpringRunner.class)
@SpringBootTest()
@AutoConfigureMockMvc
class SurveryControllerTests {

	@Test
	void contextLoads() {
	}
	
    private static final ObjectMapper om = new ObjectMapper();
    
	@Autowired
	private MockMvc mockMvc;

	@MockBean
	private SurveyRepository mockSurveyRepository; 
	
	@MockBean
	private SurveyService mockSurveyService; 
	
	@MockBean
	private SurveyUserFeedRepository mockSurveyUserFeedService; 
	
	
	@MockBean
	private AnswerRepository mockAnswerRepository;
	
	@MockBean
	private QuestionRepository mockQuestionRepository;

	@Before
	public void init() throws ApplicationException {
		
		Answer mockAnswer = new Answer();
		mockAnswer.setAnswerId(1L);
		mockAnswer.setContent("Delhi");
		Set<Answer> answers = new HashSet<Answer>();
		answers.add(mockAnswer);
		
		Question mockQuestion1 = new Question("What is india's capital?");
		mockQuestion1.setQuestionId(1L);
		mockQuestion1.setAnswers(answers);

		Question mockQuestion2 = new Question("What is india's capital?");
		mockQuestion2.setQuestionId(2L);
		mockQuestion2.setAnswers(answers);
		
		Question mockQuestion3 = new Question("What is india's capital?");
		mockQuestion3.setQuestionId(3L);
		mockQuestion3.setAnswers(answers);

		when(mockQuestionRepository.findById(1L)).thenReturn(Optional.of(mockQuestion1));
		when(mockQuestionRepository.findById(2L)).thenReturn(Optional.of(mockQuestion2));
		when(mockQuestionRepository.findById(3L)).thenReturn(Optional.of(mockQuestion3));
		
		Survey mockSurvey = new Survey();
		mockSurvey.setId(1L);
		mockSurvey.setCreatedBy("Nielson");
		mockSurvey.setCreatedOn(new Date());
		mockSurvey.setSurveyDescription("Mock survey for test");
		mockSurvey.setTopic("Test");
		
		SurveyQuestion combo1 = new SurveyQuestion();
		combo1.setId(1l);
		combo1.setQuestionid(1l);
		
		SurveyQuestion combo2 = new SurveyQuestion();
		combo2.setId(2l);
		combo2.setQuestionid(2l);
		
		SurveyQuestion combo3 = new SurveyQuestion();
		combo3.setId(3l);
		combo3.setQuestionid(3l);
		
		List<SurveyQuestion> sqs = new ArrayList<SurveyQuestion>();
		sqs.add(combo1);sqs.add(combo2);sqs.add(combo3);
		mockSurvey.setSurveyQuestions(sqs );
		when(mockSurveyRepository.findById(1L)).thenReturn(Optional.of(mockSurvey));
		when(mockSurveyRepository.save(mockSurvey)).thenReturn(mockSurvey);
		
		List<SurveyResponseQA> qabuckets = new ArrayList<SurveyResponseQA>();
		SurveyResponseQA surveyResponseQA1 = new SurveyResponseQA();
		surveyResponseQA1.setId(1l);
		surveyResponseQA1.setSurveyId(1l);
		surveyResponseQA1.setQuestionId(1l);
		surveyResponseQA1.setAnswerId(1l);
		qabuckets.add(surveyResponseQA1);
		
		SurveyUserFeed suf = new SurveyUserFeed();
		suf.setSurveyId(1l);
		suf.setUserInfo("kaustuv");
		suf.setSurveyAnswerList(qabuckets );
		mockAnswer.setAnswerId(5l);
		when(mockSurveyUserFeedService.save(suf)).thenReturn(suf);
	}
	
	
    @Test
    public void get_surveyBYId_OK() throws Exception {
		mockMvc.perform(get("/survey/1"))
        .andDo(print())
        .andExpect(status().isOk());
    }

    @Test
    public void post_survey_OK() throws Exception {
    	SurveyIO sio = new SurveyIO();
		mockMvc.perform(post("/survey/").content(om.writeValueAsString(sio)).header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON))
        .andDo(print())
        .andExpect(status().isCreated());
    }
    
    
    @Test
    public void submit_survey_OK() throws Exception {
    	SurveyUserFeedIO sio = new SurveyUserFeedIO();
		mockMvc.perform(post("/survey/submit").content(om.writeValueAsString(sio)).header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON))
        .andDo(print())
        .andExpect(status().isCreated());
    }
}
