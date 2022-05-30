package eapli.base.surveymanagement.application;

import eapli.base.infrastructure.persistence.PersistenceContext;
import eapli.base.surveymanagement.domain.Answer;
import eapli.base.surveymanagement.domain.Questionnaire;
import eapli.base.surveymanagement.repositories.AnswerQuestionnaireRepository;
import eapli.base.surveymanagement.repositories.SurveyQuestionnareRepository;
import eapli.framework.infrastructure.authz.application.AuthorizationService;
import eapli.framework.infrastructure.authz.application.AuthzRegistry;

public class AnswerQuestionnaireController {
    private final SurveyQuestionnareRepository questionnairesRepository = PersistenceContext.repositories().questionnaries();

    private final AnswerQuestionnaireRepository answersRepository = PersistenceContext.repositories().answers();

    private final AuthorizationService authz= AuthzRegistry.authorizationService();

    public Iterable<Questionnaire> allQuestionnairesInTheSystem(){
        return questionnairesRepository.findAll();
    }

    public Answer registerAnswer(String answerID){ //TODO put all the Answers' constructor parameter here!
        final var newAnswer = new Answer(answerID);

        return answersRepository.save(newAnswer);
    }
}
