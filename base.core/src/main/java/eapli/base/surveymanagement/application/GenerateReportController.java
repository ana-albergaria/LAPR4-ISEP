package eapli.base.surveymanagement.application;

import eapli.base.infrastructure.persistence.PersistenceContext;
import eapli.base.surveymanagement.domain.Questionnaire;
import eapli.base.surveymanagement.dto.QuestionnaireDTO;
import eapli.base.surveymanagement.repositories.AnswerQuestionnaireRepository;
import eapli.base.surveymanagement.repositories.SurveyQuestionnareRepository;
import eapli.framework.infrastructure.authz.application.AuthorizationService;
import eapli.framework.infrastructure.authz.application.AuthzRegistry;

import java.util.ArrayList;
import java.util.List;

public class GenerateReportController {

    private final SurveyQuestionnareRepository questionnairesRepository = PersistenceContext.repositories().questionnaries();
    private final AnswerQuestionnaireRepository answersRepository = PersistenceContext.repositories().answers();

    private final AuthorizationService authz= AuthzRegistry.authorizationService();

    public Iterable<QuestionnaireDTO> answeredQuestionnaires(){
        /*final Iterable<Questionnaire> questionnaires = this.questionnairesRepository.findAnsweredQuestionnaires();

        final List<QuestionnaireDTO> ret = new ArrayList<>();
        questionnaires.forEach(e -> ret.add(e.toDTO()));
        return ret;*/
        throw new IllegalArgumentException("to develop");
    }

    public boolean verifyIfQuestionnaireHasAnswers(QuestionnaireDTO survey){
        throw new IllegalArgumentException("to develop");
    }

}
