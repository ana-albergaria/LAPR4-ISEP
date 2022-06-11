package eapli.base.app.user.console.presentation.questionnaire;

import eapli.base.app.user.console.presentation.questionnaire.dto.QuestionnaireDTOPrinter;
import eapli.base.clientmanagement.domain.Email;
import eapli.base.clientmanagement.repositories.ClientRepository;
import eapli.base.infrastructure.persistence.PersistenceContext;
import eapli.base.surveymanagement.application.AnswerQuestionnaireController;
import eapli.base.surveymanagement.domain.Answer;
import eapli.base.surveymanagement.domain.Questionnaire;
import eapli.base.surveymanagement.dto.QuestionnaireDTO;
import eapli.base.surveymanagement.repositories.AnswerQuestionnaireRepository;
import eapli.base.surveymanagement.repositories.SurveyQuestionnareRepository;
import eapli.framework.io.util.Console;
import eapli.framework.presentation.console.AbstractUI;
import eapli.framework.presentation.console.SelectWidget;

import javax.crypto.spec.PSource;
import javax.persistence.Persistence;
import java.util.*;

public class AnswerQuestionnaireUI extends AbstractUI {
    private AnswerQuestionnaireController controller = new AnswerQuestionnaireController();

    @Override
    protected boolean doShow() {

        Iterable<QuestionnaireDTO> surveys = this.controller.allQuestionnairesInTheSystem();
        final SelectWidget<QuestionnaireDTO> selector = new SelectWidget<>("Questionnaires For Client:", surveys, new QuestionnaireDTOPrinter());
        selector.show();
        final QuestionnaireDTO survey = selector.selectedElement();

        //OBTER A STRING DO QUESTIONARIO


        Map<String, List<String>> answers = new HashMap<>();
        // CHAMAR O VISITOR E OBTER AS RESPOSTAS

        List<String> list = new ArrayList<>();
        //list.add("isto é um teste");
        answers.put("1", list);
        //list = new ArrayList<>();
        //list.add("lala");
        answers.put("2", list);


        System.out.println("Your answers will be now saved!");
        this.controller.sendAnswersToBeSaved(answers, survey);
        System.out.println("Answers successfuly saved!");

        return false;
    }

    @Override
    public String headline() {
        return "Answer Questionnaire.";
    }
}