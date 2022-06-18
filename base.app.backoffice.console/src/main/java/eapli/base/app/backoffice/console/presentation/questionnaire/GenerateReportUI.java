package eapli.base.app.backoffice.console.presentation.questionnaire;

import eapli.base.app.user.console.presentation.questionnaire.dto.QuestionnaireDTOPrinter;
import eapli.base.surveymanagement.application.GenerateReportController;
import eapli.base.surveymanagement.dto.QuestionnaireDTO;
import eapli.framework.presentation.console.AbstractUI;
import eapli.framework.presentation.console.SelectWidget;

import java.util.ArrayList;
import java.util.List;

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
            String report = generateReport(survey);



        } else {
            System.out.println("There are no answered questionnaires to analyse.");
        }
        return false;
    }

    private String generateReport(QuestionnaireDTO survey){
        StringBuilder text = new StringBuilder();
        text.append(survey.code() + " " + survey.title() + "\n");
        int targetNum = survey.targetAudience().size();
        text.append("Target Audience: " + targetNum + "\n");
        int answerNum = controller.numberOfQuestionnaireResponses(survey);
        text.append("Questionnaire's Answers: " + answerNum + "\n");
        double answerPercentage = (double) answerNum/targetNum;
        text.append("Answering Percentage: " + answerPercentage + "\n");


        //para cada question do questionnaire
            //ver o tipo de question
                //se for single-choice, multiple-choice, sorting-options ou scaling-options
                    //if single-choice: % de cada resposta
                    //else if multiple-choice: % de cada resposta + % de cada uma das combinações possíveis
                    //else if sorting-options: para cada posição, a % de cada opção
                    //else if scaling-options: para cada opção, a % de cada scale level
                //se não for
                    //printar also a dizer que o tipo de pergunta não é compatível com análise estatística


        return text.toString();
    }

    @Override
    public String headline(){
        return "See Questionnaire's Report.";
    }

}
