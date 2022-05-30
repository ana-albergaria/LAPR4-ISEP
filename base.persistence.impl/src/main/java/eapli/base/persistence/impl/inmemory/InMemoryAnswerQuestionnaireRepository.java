package eapli.base.persistence.impl.inmemory;

import eapli.base.surveymanagement.domain.Answer;
import eapli.base.surveymanagement.repositories.AnswerQuestionnaireRepository;
import eapli.framework.infrastructure.repositories.impl.inmemory.InMemoryDomainRepository;

public class InMemoryAnswerQuestionnaireRepository extends InMemoryDomainRepository<Answer, String> implements AnswerQuestionnaireRepository {
    static {
        InMemoryInitializer.init();
    }
}
