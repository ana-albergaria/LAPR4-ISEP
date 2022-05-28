package eapli.base.persistence.impl.jpa;

import eapli.base.surveymanagement.domain.Questionnaire;
import eapli.base.surveymanagement.repositories.SurveyQuestionnareRepository;
import eapli.base.warehousemanagement.domain.AGV;

public class JpaSurveyQuestionnaireRepository extends BaseJpaRepositoryBase<Questionnaire, String, String> implements SurveyQuestionnareRepository {
    JpaSurveyQuestionnaireRepository(){
        super("Questionnaire ID");
    }
}
