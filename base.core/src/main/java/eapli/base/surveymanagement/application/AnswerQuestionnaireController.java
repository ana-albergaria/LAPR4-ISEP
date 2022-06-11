package eapli.base.surveymanagement.application;

import eapli.base.infrastructure.persistence.PersistenceContext;
import eapli.base.surveymanagement.domain.Answer;
import eapli.base.surveymanagement.domain.Questionnaire;
import eapli.base.surveymanagement.dto.QuestionnaireDTO;
import eapli.base.surveymanagement.repositories.AnswerQuestionnaireRepository;
import eapli.base.surveymanagement.repositories.SurveyQuestionnareRepository;
import eapli.framework.infrastructure.authz.application.AuthorizationService;
import eapli.framework.infrastructure.authz.application.AuthzRegistry;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;

public class AnswerQuestionnaireController {
    private final SurveyQuestionnareRepository questionnairesRepository = PersistenceContext.repositories().questionnaries();
    private final AnswerQuestionnaireRepository answersRepository = PersistenceContext.repositories().answers();

    private final AuthorizationService authz= AuthzRegistry.authorizationService();

    private final AnswerQuestionnaireService service = new AnswerQuestionnaireService();

    private final String FILE_PATH = "base.core/src/main/java/eapli/base/surveymanagement/antlr/surveys/";
    private final String FILE_EXTENSION = ".txt";

    public Iterable<QuestionnaireDTO> allQuestionnairesInTheSystem(){
        return service.allSurveys();
    }


    public void sendAnswersToBeSaved(Map<String, List<String>> answers, QuestionnaireDTO survey) {
        service.sendAnswersToBeSaved(answers, survey, authz.session().get().authenticatedUser().email().toString());
    }


}
