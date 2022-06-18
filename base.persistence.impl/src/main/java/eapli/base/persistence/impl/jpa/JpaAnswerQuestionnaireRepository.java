package eapli.base.persistence.impl.jpa;

import eapli.base.clientmanagement.domain.Client;
import eapli.base.surveymanagement.domain.Answer;
import eapli.base.surveymanagement.domain.Questionnaire;
import eapli.base.surveymanagement.dto.QuestionnaireDTO;
import eapli.base.surveymanagement.repositories.AnswerQuestionnaireRepository;

import javax.persistence.TypedQuery;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
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

    @Override
    public Iterable<QuestionnaireDTO> findAnsweredQuestionnaires() {
        final TypedQuery<Answer> query = entityManager().createQuery(
                "SELECT a FROM Answer a",
                Answer.class);
        Iterable<Answer> answers = query.getResultList();
        List<QuestionnaireDTO> filteredByQuestionnaireList = new LinkedList<>();
        for (Answer a : answers){
            if (!filteredByQuestionnaireList.contains(a.questionnaire().toDTO())){
                filteredByQuestionnaireList.add(a.questionnaire().toDTO());
            }
        }
        return filteredByQuestionnaireList;
    }

    @Override
    public int findNumberOfQuestionnaireResponses(Questionnaire survey) {
        final TypedQuery<Answer> query = entityManager().createQuery(
                "SELECT a FROM Answer a",
                Answer.class);
        Iterable<Answer> answers = query.getResultList();
        List<Client> clientsWhoAnswered = new LinkedList<>();
        for (Answer a : answers){
            if (a.questionnaire()==survey && !clientsWhoAnswered.contains(a.client())){
                clientsWhoAnswered.add(a.client());
            }
        }
        return clientsWhoAnswered.size();
    }
}
