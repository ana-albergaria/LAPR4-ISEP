package eapli.base.surveymanagement.application;

import eapli.base.infrastructure.persistence.PersistenceContext;
import eapli.base.surveymanagement.domain.Questionnaire;
import eapli.base.surveymanagement.repositories.SurveyQuestionnareRepository;
import eapli.base.usermanagement.domain.BaseRoles;
import eapli.framework.infrastructure.authz.application.AuthorizationService;
import eapli.framework.infrastructure.authz.application.AuthzRegistry;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
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

    private final String FILE_PATH = "base.core/src/main/java/eapli/base/surveymanagement/antlr";
    private final String FILE_EXTENSION = ".txt";

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

    public String createQuestionnaireTextFile(String questionnaireName){
        File questionnaire = null;
        try{
            questionnaire = new File(FILE_PATH + questionnaireName + FILE_EXTENSION);
            questionnaire.createNewFile();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        return questionnaire.getPath();
    }
    public void writeQuestionnaireTextFile(String information, String questionnaireName, String filePath){
        if(!filePath.isEmpty()) {
            try {
                FileWriter questionnaireWriter = new FileWriter(filePath);
                questionnaireWriter.write(information);
                questionnaireWriter.close();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
