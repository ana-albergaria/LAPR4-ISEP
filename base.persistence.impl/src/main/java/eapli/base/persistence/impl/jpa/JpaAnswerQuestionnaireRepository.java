package eapli.base.persistence.impl.jpa;

import eapli.base.clientmanagement.domain.Client;
import eapli.base.surveymanagement.domain.Answer;
import eapli.base.surveymanagement.domain.Questionnaire;
import eapli.base.surveymanagement.dto.QuestionnaireDTO;
import eapli.base.surveymanagement.repositories.AnswerQuestionnaireRepository;

import java.util.HashMap;
import java.util.Map;

public class JpaAnswerQuestionnaireRepository extends BaseJpaRepositoryBase<Answer, Long, Long> implements AnswerQuestionnaireRepository {
    JpaAnswerQuestionnaireRepository(){
        super("answerID");
    }

    @Override
    public Iterable<Answer> findAnswersByClient(Client client, Questionnaire questionnaire) {
        final Map<String, Object> params = new HashMap<>();
        params.put("client", client);
        params.put("questionnaire", questionnaire);
        return match("e.client=:client AND e.questionnaire=:questionnaire", params);
    }
}
