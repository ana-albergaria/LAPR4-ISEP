package eapli.base.infrastructure.bootstrapers.demo;

import eapli.base.clientmanagement.domain.Client;
import eapli.base.clientmanagement.repositories.ClientRepository;
import eapli.base.infrastructure.persistence.PersistenceContext;
import eapli.base.surveymanagement.application.AnswerQuestionnaireController;
import eapli.base.surveymanagement.application.GenerateReportController;
import eapli.base.surveymanagement.domain.Answer;
import eapli.base.surveymanagement.domain.Questionnaire;
import eapli.base.surveymanagement.repositories.AnswerQuestionnaireRepository;
import eapli.base.surveymanagement.repositories.SurveyQuestionnareRepository;
import eapli.framework.actions.Action;
import eapli.framework.domain.repositories.ConcurrencyException;
import eapli.framework.domain.repositories.IntegrityViolationException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.parameters.P;
import org.springframework.transaction.TransactionSystemException;

import java.util.ArrayList;
import java.util.List;

public class AnswersBootstrapper implements Action {

    private static final Logger LOGGER = LoggerFactory.getLogger(AnswersBootstrapper.class);

    private SurveyQuestionnareRepository questionnaireRepository = PersistenceContext.repositories().questionnaries();
    private ClientRepository clientRepository = PersistenceContext.repositories().clients();
    private AnswerQuestionnaireRepository answerRepository = PersistenceContext.repositories().answers();

    private final AnswerQuestionnaireController controller = new AnswerQuestionnaireController();

    private Questionnaire getQuestionnaire(final String code){
        return questionnaireRepository.ofIdentity(code).orElseThrow(IllegalStateException::new);
    }

    private Client getClient(final String code){
        return clientRepository.ofIdentity(Long.valueOf(code)).orElseThrow(IllegalStateException::new);
    }

    @Override
    public boolean execute(){
        Questionnaire questionnaire = getQuestionnaire("BOOKS21-22");
        Client client1 = getClient("21");
        Client client2 = getClient("22");
        String question1 = "1", question2 = "2", question3 = "3";
        String answer1 = "Portugal", answer2a = "24", answer2b = "51", answer3a = "1", answer3b = "3";
        List<String> answers1 = new ArrayList<>();
        answers1.add(answer1);
        List<String> answers2a = new ArrayList<>();
        answers2a.add(answer2a);
        List<String> answers2b = new ArrayList<>();
        answers2b.add(answer2b);
        List<String> answers3a = new ArrayList<>();
        answers3a.add(answer3a);
        List<String> answers3b = new ArrayList<>();
        answers3b.add(answer3b);
        register(questionnaire,client1,question1,answers1);
        register(questionnaire,client2,question1,answers1);
        register(questionnaire,client1,question2,answers2a);
        register(questionnaire,client2,question2,answers2b);
        register(questionnaire,client1,question3,answers3a);
        register(questionnaire,client2,question3,answers3b);
        return true;
    }

    public void register(final Questionnaire questionnaire, final Client client, final String questionID, final List<String> answers){
        try {
            Answer answer = new Answer(questionnaire,client,questionID,answers);
            answerRepository.save(answer);
            LOGGER.debug(client.identity().toString(), questionID, questionnaire.identity());
        } catch (final IntegrityViolationException | ConcurrencyException | TransactionSystemException e){
            LOGGER.warn("Assuming client with id {} already answered question {} from questionnaire {} (see trace log for details on {} {})", client.identity().toString(), questionID, questionnaire.identity(),
                    e.getClass().getSimpleName(), e.getMessage());
            LOGGER.trace(String.valueOf(e));
        }
    }

}
