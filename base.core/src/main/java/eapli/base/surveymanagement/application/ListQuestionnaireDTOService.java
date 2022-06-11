package eapli.base.surveymanagement.application;

import eapli.base.infrastructure.persistence.PersistenceContext;
import eapli.base.productmanagement.dto.ProductDTO;
import eapli.base.surveymanagement.domain.Questionnaire;
import eapli.base.surveymanagement.dto.QuestionnaireDTO;
import eapli.base.surveymanagement.repositories.SurveyQuestionnareRepository;

import java.util.ArrayList;
import java.util.List;

public class ListQuestionnaireDTOService {
    private final SurveyQuestionnareRepository questionnareRepository = PersistenceContext.repositories().questionnaries();

    public Iterable<QuestionnaireDTO> allSurveys(){
        final Iterable<Questionnaire> questionnaires = this.questionnareRepository.findAll();

        final List<QuestionnaireDTO> ret = new ArrayList<>();
        questionnaires.forEach(e -> ret.add(e.toDTO()));
        return ret;
    }
}
