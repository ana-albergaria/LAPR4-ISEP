package eapli.base.surveymanagement.application;

import eapli.base.infrastructure.persistence.PersistenceContext;
import eapli.base.surveymanagement.domain.Questionnaire;
import eapli.base.surveymanagement.repositories.SurveyQuestionnareRepository;
import eapli.base.usermanagement.domain.BaseRoles;
import eapli.framework.infrastructure.authz.application.AuthorizationService;
import eapli.framework.infrastructure.authz.application.AuthzRegistry;

import java.util.HashMap;

/**
 * Controloller of Questionnaire
 *
 * @author Marcio Oliveira 1181472
 */
public class CreateQuestionnaireController {
    /**
     *
     */
    private final AuthorizationService authz= AuthzRegistry.authorizationService();
    /**
     *
     */
    private final SurveyQuestionnareRepository repository = PersistenceContext.repositories().questionnarie();

    /**
     *
     * @param code
     * @param title
     * @param welcomeMessage
     * @param sectionsAndQuestions
     * @param finalMessage
     */
    public void createQuestionnaire(String code,String title,String welcomeMessage, HashMap<String,String> sectionsAndQuestions,String finalMessage){

        authz.ensureAuthenticatedUserHasAnyOf(BaseRoles.SALES_MANAGER);

        final var questionnaire = new Questionnaire(code, title, welcomeMessage, sectionsAndQuestions,finalMessage);

        repository.save(questionnaire);


    }
}
