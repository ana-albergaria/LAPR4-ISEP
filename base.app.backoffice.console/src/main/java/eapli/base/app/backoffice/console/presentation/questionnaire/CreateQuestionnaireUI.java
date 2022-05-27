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
        int k=0, n=0, numSections, sectionID, numRepeats, numQuestionsPerSection, questionID;
        List<String> welcomeMessage = new ArrayList<>();
        String questionnaireID, questionnaireTitle, title, welcomeMessageOption, welcomeMessageNewLine, sectionHeader, sectionTitle, sectionMessageOption, sectionMessageNewLine = "",
                sectionObligatorinessOption, condition, repetability, questionHeader, questionText, questionObligatorinessOption, questionObligatorinessHeader, finalMessage, filePath;

        questionnaireID = Console.readLine("What is the questionnaire ID?");

        questionnaireTitle = Console.readLine("What is the questionnaire title?");

        title = questionnaireID + " " + questionnaireTitle + "\n" + "\n";

        filePath = controller.createQuestionnaireTextFile(questionnaireTitle);

        controller.writeQuestionnaireTextFile(title, filePath);

        System.out.println("Do you want to add an welcome message?");
        welcomeMessageOption = yesOrNo();

        if(welcomeMessageOption.equalsIgnoreCase("Yes") || welcomeMessageOption.equalsIgnoreCase("Y")){
            while (input.hasNextLine()) {
                welcomeMessageNewLine = input.nextLine();
                if (welcomeMessageNewLine.isEmpty()) {
                    break;
                }
                welcomeMessage.add(welcomeMessageNewLine);
            }

            for(String welMes : welcomeMessage){
                controller.writeQuestionnaireTextFile(welMes + "\n", filePath);
            }
        }

        System.out.println("How many sections do you want to have?");
        do {
            if(k > 0){
                System.out.println(VALID_ANSWER_MESSAGE);
            }

            numSections = Console.readInteger("Number os Sections");

            if (numSections >= 1){
                isValidNumSections = true;
            }

            k++;
        }while (!isValidNumSections);

        for(int i=0; i<numSections; i++){
            int r=0;
            List<String> sectionMessage = new ArrayList<>();

            sectionID = i+1;

            sectionTitle = Console.readLine("Enter the section title.");

            sectionHeader = sectionID + ". " + sectionTitle;

            controller.writeQuestionnaireTextFile(sectionHeader + "\n", filePath);

            System.out.println("Do you want to add a message to this section?");
            sectionMessageOption = yesOrNo();

            if(sectionMessageOption.equalsIgnoreCase("Yes") || sectionMessageOption.equalsIgnoreCase("Y")){
                while (input.hasNextLine()) {
                    sectionMessageNewLine = input.nextLine();
                    if (sectionMessageNewLine.isEmpty()) {
                        break;
                    }

                    sectionMessage.add(sectionMessageNewLine);
                }

                for(String secMes : sectionMessage){
                    controller.writeQuestionnaireTextFile(secMes + "\n", filePath);
                }
            }

            System.out.println("What is the obligatoriness of this section? (Mandatory | Optional | Condition Dependent)");
            sectionObligatorinessOption = defineSectionObligatoriness();

            if(sectionObligatorinessOption.equalsIgnoreCase("Condition Dependent")){
                condition = Console.readLine("What is the condition?");
                //Escrever no ficheiro .txt SECTION_OBLIGATORINESS + obligatoriness + ":" + condition
                controller.writeQuestionnaireTextFile(SECTION_OBLIGATORINESS + sectionObligatorinessOption + ":" + condition + "\n", filePath);
            }else {
                //Escrever no ficheiro .txt SECTION_OBLIGATORINESS + obligatoriness
                controller.writeQuestionnaireTextFile(SECTION_OBLIGATORINESS + sectionObligatorinessOption + "\n", filePath);
            }

            System.out.println("Is this section repeatable?");
            repetability = yesOrNo();

            if(repetability.equalsIgnoreCase("Yes") || repetability.equalsIgnoreCase("Y")){
                System.out.println("How many times can it be repeated?");
                do{
                    if(n > 0){
                        System.out.println(VALID_ANSWER_MESSAGE);
                    }

                    numRepeats = Console.readInteger("Repeatability");

                    if(numRepeats >= 1){
                        isValidRepeatability = true;
                    }

                    n++;
                }while(!isValidRepeatability);

                //Escrever no ficheiro .txt SECTION_REPEATABILITY + numRepeats
                controller.writeQuestionnaireTextFile(SECTION_REPEATABILITY + numRepeats + "\n", filePath);
            }

            System.out.println("How many questions do you want this section to have?");
            do{
                if(r > 0){
                    System.out.println(VALID_ANSWER_MESSAGE);
                }

                numQuestionsPerSection = Console.readInteger("(You need, at least, one)");

                if(numQuestionsPerSection >= 1){
                    isValidNumQuestions = true;
                }

                r++;
            }while(!isValidNumQuestions);

            for(int l=0; l<numQuestionsPerSection; l++){
                questionID = l+1;

                questionText = Console.readLine("What is the question text?");

                System.out.println("What is the obligatoriness of this question?");
                questionObligatorinessOption = defineSectionObligatoriness();

                if(questionObligatorinessOption.equalsIgnoreCase("Condition Dependent")){
                    System.out.println("What is the condition?");
                    condition = Console.readLine("");
                    questionObligatorinessHeader = sectionObligatorinessOption + ":" + condition;
                }else {
                    questionObligatorinessHeader = sectionObligatorinessOption;
                }

                if(questionText.contains("?")){
                    questionHeader = questionID + ". " + questionText + LEFT_PARETENSIS + questionObligatorinessHeader + RIGHT_PARETENSIS + "\n";
                }else{
                    questionHeader = questionID + ". " + questionText + INTERROGATION_POINT + LEFT_PARETENSIS + questionObligatorinessHeader + RIGHT_PARETENSIS + "\n";
                }

                controller.writeQuestionnaireTextFile(questionHeader, filePath);



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

            option = Console.readLine("Yes (Y) | No (N)");

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

            sectionObligatoriness = Console.readLine("(Mandatory | Optional | Condition Dependent)");

            if(sectionObligatoriness.equalsIgnoreCase("Mandatory") || sectionObligatoriness.equalsIgnoreCase("Optional") || sectionObligatoriness.equalsIgnoreCase("Condition Dependent")){
                isValidObligatoriness = true;
            }

            m++;
        }while(!isValidObligatoriness);

        m = 0;

        return sectionObligatoriness;
    }
}
