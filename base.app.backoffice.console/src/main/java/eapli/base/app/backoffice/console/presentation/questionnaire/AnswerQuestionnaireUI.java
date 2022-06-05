package eapli.base.app.backoffice.console.presentation.questionnaire;

import eapli.base.surveymanagement.application.AnswerQuestionnaireController;
import eapli.base.surveymanagement.domain.Questionnaire;
import eapli.framework.io.util.Console;
import eapli.framework.presentation.console.AbstractUI;

import java.util.HashMap;
import java.util.List;

public class AnswerQuestionnaireUI extends AbstractUI {
    private AnswerQuestionnaireController controller = new AnswerQuestionnaireController();

    @Override
    protected boolean doShow() {
        int numOfQuestionnaires, n=1, selectedQuestionnaireNumber;

        Questionnaire selectedQuestionnaire = null;

        List<Questionnaire> questionnaires = controller.allQuestionnairesInTheSystem();

        HashMap<Integer, Questionnaire> questionnaireOptions = new HashMap<>();

        numOfQuestionnaires = questionnaires.size();

        System.out.println("Which questionnaire would you like to answer?");

        for(Questionnaire questionnaire : questionnaires){
            System.out.printf("%d - %s\n", n, questionnaire.title());
            questionnaireOptions.put(n, questionnaire);
            n++;
        }

        selectedQuestionnaireNumber = Console.readOption(1, numOfQuestionnaires, 0);

        for(Integer selectedQuestionnaireOption : questionnaireOptions.keySet()){
            if(selectedQuestionnaireNumber == selectedQuestionnaireOption){
                selectedQuestionnaire = questionnaireOptions.get(selectedQuestionnaireOption);
            }
        }

        System.out.println("Questionnaire successfully selected. It's time to answer it!");

        /*System.out.println(selectedQuestionnaire.identity() + " " + selectedQuestionnaire.title());

        if(!selectedQuestionnaire.welcomeMessage().isEmpty()){
            System.out.println(selectedQuestionnaire.welcomeMessage() + "\n");
        }else{
            System.out.println();
        }*/

        

        //TODO for each section, print section and each question's information.



        return false;
    }

    @Override
    public String headline() {
        return "Answer Questionnaire.";
    }
}