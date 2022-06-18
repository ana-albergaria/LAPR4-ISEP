package eapli.base.app.backoffice.console.presentation.questionnaire;

import eapli.base.surveymanagement.application.GenerateReportController;
import eapli.framework.presentation.console.AbstractUI;

public class GenerateReportUI extends AbstractUI {

    private GenerateReportController controller = new GenerateReportController();

    @Override
    protected boolean doShow(){
        throw new IllegalArgumentException("to develop");
    }

    @Override
    public String headline(){
        return "See Questionnaire's Report.";
    }

}
