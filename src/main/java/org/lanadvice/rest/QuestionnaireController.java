package org.lanadvice.rest;

import org.lanadvice.model.Questionnaire;
import org.lanadvice.model.Survey;
import org.lanadvice.serializers.DateFormat;
import org.lanadvice.serializers.DateTimeFormat;
import org.lanadvice.service.QuestionnaireService;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.Response;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.List;

@Path(QuestionnaireController.ROUTE_ROOT)
public class QuestionnaireController {

    public static final String ROUTE_ROOT = "/api";
    public static final String ROUTE_Q = "/questionnaire";
    public static final String ROUTE_S = "/survey";
    public static final String ROUTE_SEARCH = "/search";
    public static final String SOURCE_PARAM = "s";
    public static final String ANSWER_PARAM = "a";
    public static final String QUESTION_PARAM = "q";

    @Inject
    QuestionnaireService questionnaireService;

    @GET
    @Path(QuestionnaireController.ROUTE_Q)
    @Produces("application/json")
    public List<Questionnaire> findAll() {
        return questionnaireService.findAll();
    }

    @GET
    @Path(QuestionnaireController.ROUTE_SEARCH)
    @Produces("application/vnd.ms-excel")
    public Response getAsCsv(@QueryParam("start") @DateFormat(DateTimeFormat.DEFAULT_DATE_TIME) final LocalDateTime start,
                         @QueryParam("end") @DateFormat(DateTimeFormat.DEFAULT_DATE_TIME) final LocalDateTime end) {
        try {
            return Response.ok(questionnaireService.getAsCsv(start, end))
                    .header("Cache-Control", "must-revalidate")
                    .header("Pragma", "must-revalidate")
                    .header("Content-Disposition", "attachment;filename=report.csv")
                    .build();
        } catch (IOException e) {
            return Response.serverError().build();
        }
    }

    @POST
    @Path(QuestionnaireController.ROUTE_Q)
    @Consumes("application/json")
    @Produces("application/json")
    public Questionnaire create(Questionnaire questionnaire) {
        return questionnaireService.create(questionnaire);
    }

    @POST
    @Path(QuestionnaireController.ROUTE_S)
    @Consumes("application/json")
    @Produces("application/json")
    public Survey survey(@QueryParam(SOURCE_PARAM) String source,
                         @QueryParam(ANSWER_PARAM) String answer,
                         @QueryParam(QUESTION_PARAM) Long question) {
        return questionnaireService.addSurvey(source, answer, question);
    }
}
