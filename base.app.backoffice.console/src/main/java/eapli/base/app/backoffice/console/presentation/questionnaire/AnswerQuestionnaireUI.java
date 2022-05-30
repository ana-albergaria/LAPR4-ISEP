package eapli.base.app.backoffice.console.presentation.questionnaire;

import eapli.base.surveymanagement.application.AnswerQuestionnaireController;
import eapli.framework.presentation.console.AbstractUI;

public class AnswerQuestionnaireUI extends AbstractUI {
    private AnswerQuestionnaireController controller = new AnswerQuestionnaireController();

    @Override
    protected boolean doShow() {


        return false;
    }

    @Override
    public String headline() {
        return "Answer Questionnaire.";
    }
}
