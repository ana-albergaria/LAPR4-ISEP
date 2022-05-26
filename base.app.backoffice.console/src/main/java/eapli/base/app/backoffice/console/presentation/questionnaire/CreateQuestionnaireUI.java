package eapli.base.app.backoffice.console.presentation.questionnaire;

import eapli.base.productmanagement.application.CreateCategoryController;
import eapli.framework.io.util.Console;
import eapli.framework.presentation.console.AbstractUI;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class CreateQuestionnaireUI extends AbstractUI {
    private final CreateCategoryController controller = new CreateCategoryController();
    private final String SECTION_OBLIGATORINESS = "Section Obligatoriness: ";
    private final String SECTION_REPEATABILITY = "Repeatability: ";
    private final String VALID_ANSWER_MESSAGE = "Please enter a valid answer.";
    private String option;
    private boolean isValidYesOrNo = false;
    private int j=0;
    private final Scanner input = new Scanner(System.in);
    @Override
    protected boolean doShow() {
        boolean isValidNumSections = false;
        int k=0, m=0, numSections, sectionID;
        List<String> welcomeMessage = new ArrayList<>();
        String questionnaireID, questionnaireTitle, title, welcomeMessageOption, welcomeMessageNewLine, sectionTitle, sectionMessageOption, sectionMessageNewLine, finalMessage;

        questionnaireID = Console.readLine("What is the questionnaire ID?");

        questionnaireTitle = Console.readLine("What is the questionnaire title?");

        title = questionnaireID + " " + questionnaireTitle + "\n";

        System.out.println("Do you want to add an welcome message? Yes (Y) | No (N)");
        welcomeMessageOption = yesOrNo();

        if(welcomeMessageOption.equalsIgnoreCase("Yes") || welcomeMessageOption.equalsIgnoreCase("Y")){
            while (input.hasNextLine()) {
                welcomeMessageNewLine = input.nextLine();
                if (welcomeMessageNewLine.isEmpty()) {
                    break;
                }
                welcomeMessage.add(welcomeMessageNewLine);
            }

            input.close();
        }

        System.out.println("How many sections do you want to have?");
        do {
            if(k > 0){
                System.out.println(VALID_ANSWER_MESSAGE);
            }

            numSections = Console.readInteger("");

            if (numSections >= 1){
                isValidNumSections = true;
            }

            k++;
        }while (!isValidNumSections);

        for(int i=0; i<numSections; i++){
            List<String> sectionMessage = new ArrayList<>();

            sectionID = i+1;

            sectionTitle = Console.readLine("Enter the section title.");

            System.out.println("Do you want to add a message to this section? Yes (Y) | No (N)");
            sectionMessageOption = yesOrNo();

            if(sectionMessageOption.equalsIgnoreCase("Yes") || sectionMessageOption.equalsIgnoreCase("Y")){
                while (input.hasNextLine()) {
                    sectionMessageNewLine = input.nextLine();
                    if (sectionMessageNewLine.isEmpty()) {
                        break;
                    }
                    sectionMessage.add(sectionMessageNewLine);
                }

                input.close();
            }



        }



        return false;
    }

    @Override
    public String headline() {
        return "Create Questionnaire.";
    }

    private String yesOrNo(){
        do{
            if(j > 0){
                System.out.println(VALID_ANSWER_MESSAGE);
            }

            option = Console.readLine("");

            if(option.equalsIgnoreCase("Yes") || option.equalsIgnoreCase("Y") || option.equalsIgnoreCase("No") || option.equalsIgnoreCase("N")){
                isValidYesOrNo = true;
            }

            j++;
        }while(!isValidYesOrNo);

        j = 0;

        return option;
    }
}
