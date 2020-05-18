package org.lanadvice.service;

import io.quarkus.test.junit.QuarkusTest;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.lanadvice.model.Question;
import org.lanadvice.model.Questionnaire;
import org.lanadvice.model.Survey;

import javax.inject.Inject;
import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.List;

@QuarkusTest
@Slf4j
public class QuestionnaireServiceTest {

    @Inject
    QuestionnaireService questionnaireService;

    @Test
    @Transactional
    public void testHelloEndpointTest() {
        Questionnaire questionnaire = new Questionnaire();
        questionnaire.setName("Survey 1");
        Question question = new Question();
        question.setText("Question 1");
        questionnaire.getQuestions().add(question);
        log.info("Creating questionnaire");
        questionnaireService.create(questionnaire);
        log.info("Creating survey");
        questionnaireService.addSurvey("0883488344", "1", question.getId());
        log.info("Fetching saved surveys for today");
        List<Survey> result1 = questionnaireService.findByCreatedBetween(LocalDateTime.now().minusDays(1L), LocalDateTime.now().plusDays(1L));
        log.info("Expecting size of 1, receiving size -> {}", result1.size());
        Assertions.assertEquals(result1.size(), 1);
    }

}
