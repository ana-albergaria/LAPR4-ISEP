package eapli.base.persistence.impl.jpa;

import eapli.base.surveymanagement.domain.Answer;
import eapli.base.surveymanagement.repositories.AnswerQuestionnaireRepository;

public class JpaAnswerQuestionnaireRepository extends BaseJpaRepositoryBase<Answer, Long, Long> implements AnswerQuestionnaireRepository {
    JpaAnswerQuestionnaireRepository(){
        super("answerID");
    }
}
