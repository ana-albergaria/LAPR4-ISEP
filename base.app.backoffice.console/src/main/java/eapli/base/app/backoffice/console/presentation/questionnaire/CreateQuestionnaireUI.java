package eapli.base.app.backoffice.console.presentation.questionnaire;

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
    private final String SCALING_OPTION = "Scale: ";
    private final String WITH_INPUT = " with input";
    private final String DECIMALS_ARE_ALLOWED = "Decimal numbers are allowed!";
    private final String VALID_ANSWER_MESSAGE = "Please enter a valid answer.";
    private final String INTERROGATION_POINT = "?";
    private final String LEFT_PARETENSIS = "(";
    private final String RIGHT_PARETENSIS = ")";
    private String option;
    private String obligatoriness;
    private String questionType;
    private boolean isValidYesOrNo = false;
    private boolean isValidObligatoriness = false;
    private boolean isValidQuestionType = false;
    private boolean isValidNumberOfOptions = false;
    private int j=0;
    private  int m=0;
    private int z=0;
    private int a=0;
    private int numOfChoices;
    private final Scanner input = new Scanner(System.in);
    @Override
    protected boolean doShow() {
        boolean isValidNumSections = false, isValidRepeatability = false, isValidNumQuestions = false;
        int k=0, n=0, numSections, sectionID, numRepeats, numQuestionsPerSection, questionID, numOfChoicesOption, numOfSortingOptions, numOfScaleOptions;
        List<String> welcomeMessage = new ArrayList<>();
        String questionnaireID, questionnaireTitle, title, welcomeMessageOption, welcomeMessageNewLine, sectionHeader, sectionTitle, sectionMessageOption, sectionMessageNewLine = "",
                sectionObligatorinessOption, condition, repetability, questionHeader, questionText, questionObligatorinessOption, questionMessageOption,
                questionMessageNewLine, questionObligatorinessHeader, questionTypeOption, questionInputChoice, questionTypeInput, questionTypeOptionDescription,
                questionSortingOptionDescription, questionScale, questionScalingOptionDescription, areDecimalsAllowed, questionFinalMessageOption, questionFinalMessage,
                filePath, finalMessage;

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

            sectionTitle = Console.readLine("Enter the section number " + i+1 + " title.");

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
            sectionObligatorinessOption = defineObligatoriness();

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
                List<String> questionMessage = new ArrayList<>();

                questionID = l+1;

                questionText = Console.readLine("What is the question number " + l+1 +" text?");

                System.out.println("What is the obligatoriness of this question?");
                questionObligatorinessOption = defineObligatoriness();

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

                System.out.println("Do you want to add a message to this question?");
                questionMessageOption = yesOrNo();

                if(questionMessageOption.equalsIgnoreCase("Yes") || questionMessageOption.equalsIgnoreCase("Y")){
                    while (input.hasNextLine()) {
                        questionMessageNewLine = input.nextLine();
                        if (questionMessageNewLine.isEmpty()) {
                            break;
                        }

                        questionMessage.add(questionMessageNewLine);
                    }

                    for(String queMes : questionMessage){
                        controller.writeQuestionnaireTextFile(queMes + "\n", filePath);
                    }
                }

                System.out.println("What is the question type?");
                questionTypeOption = defineQuestionType();

                if(questionTypeOption.equalsIgnoreCase("Single Choice") || questionTypeOption.equalsIgnoreCase("Multiple Choice")){
                    numOfChoicesOption = defineNumberOfOptions();
                    System.out.println("Will you like to introduce the input?");
                    questionInputChoice = yesOrNo();

                    if(questionInputChoice.equalsIgnoreCase("Yes") || questionInputChoice.equalsIgnoreCase("Y")){
                        for(int h=0; h<numOfChoicesOption; h++){
                            questionTypeInput = Console.readLine("Input for this option: ");

                            controller.writeQuestionnaireTextFile( QUESTION_TYPE + questionTypeOption + WITH_INPUT + "\n", filePath);

                            controller.writeQuestionnaireTextFile(writeOptions(h+1, questionTypeInput), filePath);
                        }
                    }else{
                        for(int h=0; h<numOfChoicesOption; h++){
                            questionTypeOptionDescription = Console.readLine("Option description: ");

                            controller.writeQuestionnaireTextFile( QUESTION_TYPE + questionTypeOption + "\n", filePath);

                            controller.writeQuestionnaireTextFile(writeOptions(h+1, questionTypeOptionDescription), filePath);
                        }
                    }
                }else if(questionTypeOption.equalsIgnoreCase("Sorting Option")){
                    controller.writeQuestionnaireTextFile( QUESTION_TYPE + questionTypeOption + "\n", filePath);

                    numOfSortingOptions = defineNumberOfOptions();

                    for(int e=0; e<numOfSortingOptions; e++){
                        questionSortingOptionDescription = Console.readLine("Option description: ");
                        controller.writeQuestionnaireTextFile(writeOptions(e+1, questionSortingOptionDescription), filePath);
                    }
                }else if(questionTypeOption.equalsIgnoreCase("Scaling Option")){
                    controller.writeQuestionnaireTextFile( QUESTION_TYPE + questionTypeOption + "\n", filePath);

                    questionScale = Console.readLine("What is the scale?");

                    controller.writeQuestionnaireTextFile(SCALING_OPTION + questionScale + "\n", filePath);

                    numOfScaleOptions = defineNumberOfOptions();

                    for(int u=0; u<numOfScaleOptions; u++){
                        questionScalingOptionDescription = Console.readLine("Option description: ");
                        controller.writeQuestionnaireTextFile(writeOptions(u+1, questionScalingOptionDescription), filePath);
                    }
                }else if(questionTypeOption.equalsIgnoreCase("Numeric")){
                    areDecimalsAllowed = yesOrNo();

                    if(areDecimalsAllowed.equalsIgnoreCase("Yes") || areDecimalsAllowed.equalsIgnoreCase("Y")){
                        controller.writeQuestionnaireTextFile( QUESTION_TYPE + questionTypeOption + " " + LEFT_PARETENSIS +
                                DECIMALS_ARE_ALLOWED + RIGHT_PARETENSIS + "\n", filePath);
                    }else{
                        controller.writeQuestionnaireTextFile( QUESTION_TYPE + questionTypeOption + "\n", filePath);
                    }
                }else if(questionTypeOption.equalsIgnoreCase("Free Text")){
                    controller.writeQuestionnaireTextFile( QUESTION_TYPE + questionTypeOption + "\n", filePath);
                }

                System.out.println("Would you like to enter a final message for this question?");

                questionFinalMessageOption = yesOrNo();

                if(questionFinalMessageOption.equalsIgnoreCase("Yes") || questionFinalMessageOption.equalsIgnoreCase("Y")){
                    questionFinalMessage = Console.readLine("Enter the message:");
                    controller.writeQuestionnaireTextFile(questionFinalMessage, filePath);
                }
            }
        }

        finalMessage = Console.readLine("Final survey message:");

        controller.writeQuestionnaireTextFile("\n\n" + finalMessage, filePath);

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

    private String defineObligatoriness(){
        do{
            if(m > 0){
                System.out.println(VALID_ANSWER_MESSAGE);
            }

            obligatoriness = Console.readLine("(Mandatory | Optional | Condition Dependent)");

            if(obligatoriness.equalsIgnoreCase("Mandatory") || obligatoriness.equalsIgnoreCase("Optional") || obligatoriness.equalsIgnoreCase("Condition Dependent")){
                isValidObligatoriness = true;
            }

            m++;
        }while(!isValidObligatoriness);

        m = 0;

        return obligatoriness;
    }

    private String defineQuestionType(){
        do{
            if(z > 0){
                System.out.println(VALID_ANSWER_MESSAGE);
            }

            questionType = Console.readLine("(Free Text | Numeric | Single Choice | Multiple Choice | Sorting Option | Scaling Option)");

            if(questionType.equalsIgnoreCase("Free Text") || questionType.equalsIgnoreCase("Numeric") || questionType.equalsIgnoreCase("Single Choice")
                || questionType.equalsIgnoreCase("Multiple Choice") || questionType.equalsIgnoreCase("Sorting Option") || questionType.equalsIgnoreCase("Scaling Option")){
                isValidQuestionType = true;
            }

            z++;
        }while(!isValidQuestionType);

        z = 0;

        return questionType;
    }

    private Integer defineNumberOfOptions(){
        do{
            if(a > 0){
                System.out.println(VALID_ANSWER_MESSAGE);
            }

            numOfChoices = Console.readInteger("How many options would you like to have? (You need, at least, one)");

            if(numOfChoices >= 1){
                isValidNumberOfOptions = true;
            }

            a++;
        }while(!isValidNumberOfOptions);

        a = 0;

        return numOfChoices;
    }

    private String writeOptions(int numericID, String optionDescription){
        return numericID + RIGHT_PARETENSIS + optionDescription + "\n";
    }
}
