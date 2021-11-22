package com.ml.survey;

import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import org.junit.Before;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ml.survey.common.ApplicationErrorCode;
import com.ml.survey.controller.qa.vo.QuestionIO;
import com.ml.survey.entity.Answer;
import com.ml.survey.entity.Question;
import com.ml.survey.exception.ApplicationException;
import com.ml.survey.repository.AnswerRepository;
import com.ml.survey.repository.QuestionRepository;
import com.ml.survey.service.QAService;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class QAControllerTest {

    private static final ObjectMapper om = new ObjectMapper();
    
	@Autowired
	private MockMvc mockMvc;

	@MockBean
	private AnswerRepository mockAnswerRepository;
	
	@MockBean
	private QuestionRepository mockQuestionRepository;
	
	@MockBean
	private QAService mockQAService; 

	@Before
	public void init() throws ApplicationException {
		Question question = new Question("What is your name?");
		question.setQuestionId(1L);
		Set<Answer> answers = new HashSet<Answer>();
		answers.add(new Answer("Kaustuv"));
		question.setAnswers(answers);
		Answer mockAnswer = new Answer();
		mockAnswer.setAnswerId(1L);
		mockAnswer.setContent("My Name is Kaustuv");
		question.addAnswer(mockAnswer);
		when(mockAnswerRepository.findById(1L)).thenReturn(Optional.of(mockAnswer));
		when(mockQuestionRepository.save(new Question("live"))).thenReturn(question);
		when(mockQuestionRepository.findById(4L)).thenReturn(Optional.of(question));
		when(mockQAService.getQuestion(2L)).thenThrow(new ApplicationException(ApplicationErrorCode.RESOURCE_NOTFOUND_EXCEPTION_CODE.label, "Question Not Found"));
		doNothing().when(mockQuestionRepository).deleteById(3L);
		doNothing().when(mockAnswerRepository).deleteById(3L);
	}
	
    @Test
    public void find_answerId_OK() throws Exception {
        mockMvc.perform(get("/answers/1"))
                .andDo(print())
                .andExpect(status().isOk());
    }
        
    @Test
    public void get_questions_OK() throws Exception {
		mockMvc.perform(get("/questions/")).andDo(print())
        .andExpect(status().isOk());
    }
    
    @Test
    public void get_questionBYId_OK() throws Exception {
		mockMvc.perform(get("/questions/4"))
        .andDo(print())
        .andExpect(status().isOk());
    }
      
    @Test
    public void add_question_OK() throws Exception {
        QuestionIO questionIO = new QuestionIO(1L, "what is my name");
		mockMvc.perform(post("/questions/").content(om.writeValueAsString(questionIO)).header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON))
        .andDo(print())
        .andExpect(status().isCreated());
    }

    
    @Test
    public void put_question_OK() throws Exception {
    	QuestionIO questionIO = new QuestionIO(1L, "what is my name");
		mockMvc.perform(put("/questions/").content(om.writeValueAsString(questionIO)).header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON))
        .andDo(print())
        .andExpect(status().isOk());
    }
    
    @Test
    public void delete_question_OK() throws Exception {
		mockMvc.perform(delete("/questions/3")).andDo(print())
        .andExpect(status().isNoContent());
    }
       

    @Test
    public void delete_answer_question_OK() throws Exception {
		mockMvc.perform(delete("/questions/3/answers/2")).andDo(print())
        .andExpect(status().isNoContent());
    }
    
    @Test
    public void find_answerId_NOTFOUND() throws Exception {
        mockMvc.perform(get("/answers/15"))
                .andDo(print())
                .andExpect(status().isOk());
    }
}
