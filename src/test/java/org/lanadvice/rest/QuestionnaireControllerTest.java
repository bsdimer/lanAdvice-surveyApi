package org.lanadvice.rest;

import io.quarkus.test.junit.QuarkusTest;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.Test;
import org.lanadvice.model.Question;
import org.lanadvice.model.Questionnaire;
import org.lanadvice.model.Survey;
import org.lanadvice.service.QuestionnaireService;

import javax.inject.Inject;

import java.time.LocalDateTime;
import java.util.Map;

import static io.restassured.RestAssured.given;

@QuarkusTest
public class QuestionnaireControllerTest {

    @Inject
    QuestionnaireService questionnaireService;

    @Test
    public void findAllQuestionnairesTest() {
        prepareQuestion();
        given()
                .log().all()
                .when().get(QuestionnaireController.ROUTE_ROOT + QuestionnaireController.ROUTE_Q)
                .then()
                .log().all()
                .statusCode(200);
    }

    @Test
    public void addSurveyTest() {
        Question question = prepareQuestion();
        String questionId = question.getId().toString();
        given()
                .log().all()
                .when()
                .queryParams(Map.of("s", "0888878786", "a", "3", "q", questionId))
                .contentType(ContentType.JSON)
                .post(QuestionnaireController.ROUTE_ROOT + QuestionnaireController.ROUTE_S)
                .then()
                .log().all()
                .statusCode(200);
    }

    @Test
    public void searchTest() {
        Question q = prepareQuestion();
        prepareSurvey(q);
        given()
                .log().all()
                .when()
                .queryParams(Map.of("start", "2020-05-15T00:00:00", "end", "2020-05-19T00:00:00"))
                .get(QuestionnaireController.ROUTE_ROOT + QuestionnaireController.ROUTE_SEARCH)
                .then()
                .log().all()
                .statusCode(200);
    }

    private void prepareSurvey(Question q) {
        questionnaireService.addSurvey("02342424", "1", q.getId());
    }

    private Question prepareQuestion() {
        Questionnaire questionnaire = new Questionnaire();
        questionnaire.setName("Survey 1");
        Question question = new Question();
        question.setText("Question 1");
        questionnaire.getQuestions().add(question);
        question.setQuestionnaire(questionnaire);
        questionnaireService.create(questionnaire);
        return question;
    }

}
