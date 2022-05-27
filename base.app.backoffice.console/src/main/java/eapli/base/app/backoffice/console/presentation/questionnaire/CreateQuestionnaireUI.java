package eapli.base.app.backoffice.console.presentation.questionnaire;

import eapli.base.productmanagement.application.CreateCategoryController;
import eapli.base.surveymanagement.application.CreateQuestionnaireController;
import eapli.framework.io.util.Console;
import eapli.framework.presentation.console.AbstractUI;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class CreateQuestionnaireUI extends AbstractUI {
    private final CreateQuestionnaireController controller = new CreateQuestionnaireController();
    private final String SECTION_OBLIGATORINESS = "Section Obligatoriness: ";
    private final String SECTION_REPEATABILITY = "Repeatability: ";
    private final String QUESTION_TYPE = "Type: ";
    private final String VALID_ANSWER_MESSAGE = "Please enter a valid answer.";
    private final String INTERROGATION_POINT = "?";
    private final String LEFT_PARETENSIS = "(";
    private final String RIGHT_PARETENSIS = ")";
    private String option;
    private String sectionObligatoriness;
    private boolean isValidYesOrNo = false;
    private boolean isValidObligatoriness = false;
    private int j=0;
    private  int m=0;
    private final Scanner input = new Scanner(System.in);
    @Override
    protected boolean doShow() {
        boolean isValidNumSections = false, isValidRepeatability = false, isValidNumQuestions = false;
        int k=0, n=0, r=0, numSections, sectionID, numRepeats, numQuestionsPerSection, questionID;
        List<String> welcomeMessage = new ArrayList<>();
        String questionnaireID, questionnaireTitle, title, welcomeMessageOption, welcomeMessageNewLine, sectionHeader, sectionTitle, sectionMessageOption, sectionMessageNewLine,
                sectionObligatorinessOption, condition, repetability, questionHeader, questionTitle, questionObligatorinessOption, questionObligatorinessHeader, finalMessage, filePath;

        questionnaireID = Console.readLine("What is the questionnaire ID?");

        questionnaireTitle = Console.readLine("What is the questionnaire title?");

        title = questionnaireID + " " + questionnaireTitle + "\n";

        filePath = controller.createQuestionnaireTextFile(questionnaireTitle);

        controller.writeQuestionnaireTextFile(title, questionnaireTitle, filePath);

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

            for(String welMes : welcomeMessage){
                controller.writeQuestionnaireTextFile(welMes, questionnaireTitle, filePath);
            }
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

            sectionHeader = sectionID + sectionTitle;

            controller.writeQuestionnaireTextFile(sectionHeader, questionnaireTitle, filePath);

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

                for(String secMes : sectionMessage){
                    controller.writeQuestionnaireTextFile(secMes, questionnaireTitle, filePath);
                }
            }

            System.out.println("What is the obligatoriness of this section? (Mandatory | Optional | Condition Dependent)");
            sectionObligatorinessOption = defineSectionObligatoriness();

            if(sectionObligatorinessOption.equalsIgnoreCase("Condition Dependent")){
                System.out.println("What is the condition?");
                condition = Console.readLine("");
                //Escrever no ficheiro .txt SECTION_OBLIGATORINESS + obligatoriness + ":" + condition
                controller.writeQuestionnaireTextFile(SECTION_OBLIGATORINESS + sectionObligatorinessOption + ":" + condition, questionnaireTitle, filePath);
            }else {
                //Escrever no ficheiro .txt SECTION_OBLIGATORINESS + obligatoriness
                controller.writeQuestionnaireTextFile(SECTION_OBLIGATORINESS + sectionObligatorinessOption, questionnaireTitle, filePath);
            }

            System.out.println("Is this question repetible?");
            repetability = yesOrNo();

            if(repetability.equalsIgnoreCase("Yes") || repetability.equalsIgnoreCase("Y")){
                System.out.println("How many times can it be repeated?");
                do{
                    if(n > 0){
                        System.out.println(VALID_ANSWER_MESSAGE);
                    }

                    numRepeats = Console.readInteger("");

                    if(numRepeats >= 1){
                        isValidRepeatability = true;
                    }

                    n++;
                }while(!isValidRepeatability);

                //Escrever no ficheiro .txt SECTION_REPEATABILITY + numRepeats
                controller.writeQuestionnaireTextFile(SECTION_REPEATABILITY + numRepeats, questionnaireTitle, filePath);
            }

            System.out.println("How many questions do you want this section to have? (You need, at least, one)");
            do{
                if(r > 0){
                    System.out.println(VALID_ANSWER_MESSAGE);
                }

                numQuestionsPerSection = Console.readInteger("");

                if(numQuestionsPerSection >= 1){
                    isValidNumQuestions = true;
                }

                r++;
            }while(!isValidNumQuestions);

            for(int l=0; l<numQuestionsPerSection; l++){
                questionID = l+1;

                questionTitle = Console.readLine("What is the question text?");

                System.out.println("What is the obligatoriness of this question? (Mandatory | Optional | Condition Dependent)");
                questionObligatorinessOption = defineSectionObligatoriness();

                if(questionObligatorinessOption.equalsIgnoreCase("Condition Dependent")){
                    System.out.println("What is the condition?");
                    condition = Console.readLine("");
                    questionObligatorinessHeader = SECTION_OBLIGATORINESS + sectionObligatorinessOption + ":" + condition;
                }else {
                    questionObligatorinessHeader = SECTION_OBLIGATORINESS + sectionObligatorinessOption;
                }

                questionHeader = questionID + questionTitle + INTERROGATION_POINT + LEFT_PARETENSIS + questionObligatorinessHeader + RIGHT_PARETENSIS;

                controller.writeQuestionnaireTextFile(questionHeader, questionnaireTitle, filePath);


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

    private String defineSectionObligatoriness(){
        do{
            if(m > 0){
                System.out.println(VALID_ANSWER_MESSAGE);
            }

            sectionObligatoriness = Console.readLine("");

            if(sectionObligatoriness.equalsIgnoreCase("Mandatory") || sectionObligatoriness.equalsIgnoreCase("Optional") || sectionObligatoriness.equalsIgnoreCase("Condition Dependent")){
                isValidObligatoriness = true;
            }

            m++;
        }while(!isValidObligatoriness);

        m = 0;

        return sectionObligatoriness;
    }
}
