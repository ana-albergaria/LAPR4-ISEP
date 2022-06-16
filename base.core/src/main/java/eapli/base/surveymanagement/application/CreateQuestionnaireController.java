package eapli.base.surveymanagement.application;

import eapli.base.clientmanagement.domain.Client;
import eapli.base.clientmanagement.repositories.ClientRepository;
import eapli.base.infrastructure.persistence.PersistenceContext;
import eapli.base.productmanagement.domain.AlphaNumericCode;
import eapli.base.productmanagement.domain.Code;
import eapli.base.productmanagement.repositories.ProductCategoryRepository;
import eapli.base.productmanagement.repositories.ProductRepository;
import eapli.base.surveymanagement.antlr.SurveyMain;
import eapli.base.surveymanagement.domain.Questionnaire;
import eapli.base.surveymanagement.domain.TheRule;
import eapli.base.surveymanagement.repositories.SurveyQuestionnareRepository;
import eapli.base.usermanagement.domain.BaseRoles;
import eapli.framework.infrastructure.authz.application.AuthorizationService;
import eapli.framework.infrastructure.authz.application.AuthzRegistry;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.*;

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
    private final SurveyQuestionnareRepository repository = PersistenceContext.repositories().questionnaries();
    private final ClientRepository clientRepository = PersistenceContext.repositories().clients();
    private final ProductRepository productRepository = PersistenceContext.repositories().products();
    private final ProductCategoryRepository productCategoryRepository = PersistenceContext.repositories().productCategories();

    private final String FILE_PATH = "base.core/src/main/java/eapli/base/surveymanagement/antlr/surveys/";
    private final String FILE_EXTENSION = ".txt";

    /**
     *
     * @param code
     * @param title
     * @param welcomeMessage
     * @param questionnaireContent
     * @param finalMessage
     * @param rules
     */
    public void registerQuestionnaire(String code, String title, String welcomeMessage, String questionnaireContent, String finalMessage, Set<TheRule> rules){

        authz.ensureAuthenticatedUserHasAnyOf(BaseRoles.POWER_USER, BaseRoles.SALES_MANAGER);

        Iterator<Client> targetAudience;
        List<Client> list = new ArrayList<>();

        if (rules.isEmpty()) {
            targetAudience = clientRepository.findAll().iterator();
            targetAudience.forEachRemaining(list::add);
        } /*else {

            Criteria criteria = null;
            for (TheRule theRule:
                 rules) {
                if (theRule.rule().equals(TheRule.Rule.GENDER))
                    criteria = new GenderCriteria(Criteria);

                            boolean condigiton = criteria.verofyu(client);



            }
        }*/


        final var questionnaire = new Questionnaire(code, title, welcomeMessage, questionnaireContent, finalMessage, list, rules);

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
    public void writeQuestionnaireTextFile(String information, String filePath){
        if(!filePath.isEmpty()) {
            try {
                FileWriter fileWriter = new FileWriter(filePath, true);
                PrintWriter questionnaireWriter = new PrintWriter(fileWriter);
                questionnaireWriter.append(information);
                questionnaireWriter.close();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }

    public boolean isQuestionnaireValid(String filePath) {
        return SurveyMain.parseWithVisitor(filePath);
    }

    public boolean productExists(String id){
        if (productRepository.ofIdentity(Code.valueOf(id)).isPresent()){
            return true;
        }
        return false;
    }

    public boolean productCategoryExists(String id){
        if (productCategoryRepository.ofIdentity(AlphaNumericCode.valueOf(id)).isPresent()){
            return true;
        }
        return false;
    }

}
