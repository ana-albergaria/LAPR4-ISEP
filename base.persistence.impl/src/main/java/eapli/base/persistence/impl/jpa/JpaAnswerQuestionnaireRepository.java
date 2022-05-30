package eapli.base.persistence.impl.jpa;

import eapli.base.surveymanagement.domain.Answer;
import eapli.base.surveymanagement.repositories.AnswerQuestionnaireRepository;

public class JpaAnswerQuestionnaireRepository extends BaseJpaRepositoryBase<Answer, String, String> implements AnswerQuestionnaireRepository {
    JpaAnswerQuestionnaireRepository(){
        super("Answer ID");
    }
}
