package eapli.base.app.backoffice.console.presentation.questionnaire;

import eapli.base.app.user.console.presentation.questionnaire.dto.QuestionnaireDTOPrinter;
import eapli.base.surveymanagement.application.GenerateReportController;
import eapli.base.surveymanagement.dto.QuestionnaireDTO;
import eapli.framework.presentation.console.AbstractUI;
import eapli.framework.presentation.console.SelectWidget;

public class GenerateReportUI extends AbstractUI {

    private GenerateReportController controller = new GenerateReportController();

    @Override
    protected boolean doShow(){

        QuestionnaireDTO survey = null;
        if (this.controller.answeredQuestionnaires().iterator().hasNext()) {
            Iterable<QuestionnaireDTO> surveys = this.controller.answeredQuestionnaires();
            final SelectWidget<QuestionnaireDTO> selector = new SelectWidget<>("Answered Questionnaires:", surveys, new QuestionnaireDTOPrinter());
            selector.show();
            survey = selector.selectedElement();

            

        } else {
            System.out.println("There are no answered questionnaires to analyse.");
        }
        return false;
    }

    @Override
    public String headline(){
        return "See Questionnaire's Report.";
    }

}
