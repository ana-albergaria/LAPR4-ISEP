package eapli.base.surveymanagement.repositories;

import eapli.base.clientmanagement.domain.Client;
import eapli.base.surveymanagement.domain.Answer;
import eapli.base.surveymanagement.domain.Questionnaire;
import eapli.base.surveymanagement.dto.QuestionnaireDTO;
import eapli.framework.domain.repositories.DomainRepository;

public interface AnswerQuestionnaireRepository extends DomainRepository<Long, Answer> {
    @Override
    Iterable<Answer> findAll();

    Iterable<Answer> findAnswersByClient(Client client, Questionnaire survey);

    Iterable<QuestionnaireDTO> findAnsweredQuestionnaires();

    int findNumberOfQuestionnaireResponses(Questionnaire survey);
}
