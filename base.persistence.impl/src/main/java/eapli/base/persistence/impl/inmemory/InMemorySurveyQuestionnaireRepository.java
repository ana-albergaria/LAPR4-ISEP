package eapli.base.persistence.impl.inmemory;

import eapli.base.surveymanagement.domain.Questionnaire;
import eapli.base.surveymanagement.repositories.SurveyQuestionnareRepository;
import eapli.framework.infrastructure.repositories.impl.inmemory.InMemoryDomainRepository;

public class InMemorySurveyQuestionnaireRepository extends InMemoryDomainRepository<Questionnaire, String> implements SurveyQuestionnareRepository {
    static {
        InMemoryInitializer.init();
    }
}
