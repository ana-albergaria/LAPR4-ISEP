package eapli.base.surveymanagement.repositories;

import eapli.base.surveymanagement.domain.Answer;
import eapli.framework.domain.repositories.DomainRepository;

public interface AnswerQuestionnaireRepository extends DomainRepository<String, Answer> {
    @Override
    Iterable<Answer> findAll();
}
