package eapli.base.surveymanagement.repositories;

import eapli.base.surveymanagement.domain.Questionnaire;
import eapli.framework.domain.repositories.DomainRepository;

public interface SurveyQuestionnareRepository extends DomainRepository<String, Questionnaire> {

    Iterable<Questionnaire>findALl();
}
