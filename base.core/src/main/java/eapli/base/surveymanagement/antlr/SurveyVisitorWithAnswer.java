package eapli.base.surveymanagement.antlr;

import eapli.framework.io.util.Console;
import org.apache.commons.lang3.math.NumberUtils;

import java.util.*;

public class SurveyVisitorWithAnswer extends questionnaireBaseVisitor {
    private final Map<String, List<String>> answers= new HashMap<>();
    private final Scanner scan = new Scanner(System.in);
    private boolean isMandatory = false;

    @Override
    public Boolean visitQuestion(questionnaireParser.QuestionContext ctx) {
        visitChildren(ctx);

        return true;
    }

    @Override
    public Boolean visitLengthTitle(questionnaireParser.LengthTitleContext ctx) {
        String title = ctx.getText();

        if (title.length()>50) System.out.println("Title length is too long!");

        return true;
    }


    @Override
    public Boolean visitFree_text(questionnaireParser.Free_textContext ctx) {
        String question =ctx.getText();
        System.out.println(question);


        List<String> free_text = new ArrayList<>();

        if(ctx.obligatoriness().getText().equals("mandatory")){
            while(free_text.isEmpty()){
                System.out.println("This question is mandatory, so you need to provide an answer");
                free_text=readMultipleLines();
            }
        }else {
            free_text=readMultipleLines();
        }

        answers.put(ctx.numeric_id().getText(), free_text);

        return true;
    }



    @Override
    public Boolean visitNumeric(questionnaireParser.NumericContext ctx) {
        String question =ctx.getText();
        System.out.println(question);

        List<String> numeric = new ArrayList<>();
        String answer = "";
        boolean validAnswer=false;

        if(ctx.obligatoriness().getText().equals("mandatory")){
            isMandatory=true;
        }else {
            String isAnswering = Console.readLine("This question is optional, do you wish to answer it?(YES/NO)");
            isMandatory= isAnswering.equalsIgnoreCase("yes");
        }

        if(isMandatory ){
            while(answer.isEmpty() || !validAnswer){
                try {
                    answer=Console.readLine("Answer: ");

                    if(answer.isEmpty()) {
                        throw new UnsupportedOperationException("Your answer can't be empty!");
                    }
                    if (!NumberUtils.isParsable(answer)){
                        throw new UnsupportedOperationException("In a question with type: numeric, you must provide a numeric value as an answer.");
                    }

                    if(ctx.DECIMALS_ALLOWED() == null && Double.parseDouble(answer)%1!=0){
                        throw new UnsupportedOperationException("This numeric question does not allow decimal values.");
                    }
                }catch (UnsupportedOperationException ex ){
                    System.out.println(ex.getMessage());
                    continue;
                }
                validAnswer=true;
            }
        }
        numeric.add(answer);

        answers.put(ctx.numeric_id().getText(), numeric);


        return true;
    }

    @Override
    public Boolean visitSingle_choice_with_input(questionnaireParser.Single_choice_with_inputContext ctx) {
        String question= ctx.getText();
        System.out.println(question);

        List<String> singleChoiceInput = new ArrayList<>();
        String answer = "";
        boolean validAnswer=false;

        List<String> options = new ArrayList<>();

        for (questionnaireParser.OptionContext id: ctx.option()) {
            options.add(id.numeric_id().getText());
        }

        if(ctx.obligatoriness().getText().equals("mandatory")){
            isMandatory=true;
        }else {
            String isAnswering = Console.readLine("This question is optional, do you wish to answer it?(YES/NO)");
            isMandatory= isAnswering.equalsIgnoreCase("yes");
        }

        if(isMandatory){
            while(answer.isEmpty() || !validAnswer){
                try {
                    answer=Console.readLine("Answer: ");

                    if(answer.isEmpty()) {
                        throw new UnsupportedOperationException("Your answer can't be empty!");
                    }
                    if (!NumberUtils.isParsable(String.valueOf(answer.charAt(0)))){
                        throw new UnsupportedOperationException("In a question with type: single choice with input, you must provide a numeric value as an answer.");
                    }

                     if (!options.contains(answer.charAt(0))){
                        throw new UnsupportedOperationException("You must choose one of the given options!");
                    }

                    String lastOptionId = ctx.option().get(ctx.option().size()-1).numeric_id().getText();



                    if(lastOptionId.equals(String.valueOf(answer.charAt(0))) && answer.length()==1){
                        throw new UnsupportedOperationException("In a question with type: single choice with input, if you select the last option then you have to write another option.");
                    }
                }catch (UnsupportedOperationException ex ){
                    System.out.println(ex.getMessage());
                    continue;
                }
                validAnswer=true;
            }
        }
        singleChoiceInput.add(answer);

        answers.put(ctx.numeric_id().getText(), singleChoiceInput);

        return true;
    }

    @Override
    public Boolean visitSingle_choice(questionnaireParser.Single_choiceContext ctx) {
        String question= ctx.getText();
        System.out.println(question);

        List<String> singleChoice = new ArrayList<>();
        String answer = "";
        boolean validAnswer=false;

        List<String> options = new ArrayList<>();

        for (questionnaireParser.OptionContext id: ctx.option()) {
            options.add(id.numeric_id().getText());
        }

        if(ctx.obligatoriness().getText().equals("mandatory")){
            isMandatory=true;
        }else {
            String isAnswering = Console.readLine("This question is optional, do you wish to answer it?(YES/NO)");
            isMandatory= isAnswering.equalsIgnoreCase("yes");
        }

        if(isMandatory){
            while(answer.isEmpty() || !validAnswer){
                try {
                    answer=Console.readLine("Answer: ");

                    if(answer.isEmpty()) {
                        throw new UnsupportedOperationException("Your answer can't be empty!");
                    }
                    if (!NumberUtils.isParsable(answer)){
                        throw new UnsupportedOperationException("In a question with type: single choice, you must provide a numeric value as an answer.");
                    }

                    if (!options.contains(answer)){
                        throw new UnsupportedOperationException("You must choose one of the given options!");
                    }
                }catch (UnsupportedOperationException ex ){
                    System.out.println(ex.getMessage());
                    continue;
                }
                validAnswer=true;
            }
        }
        singleChoice.add(answer);

        answers.put(ctx.numeric_id().getText(), singleChoice);

        return true;
    }

    @Override
    public Boolean visitMultiple_choice(questionnaireParser.Multiple_choiceContext ctx) {
        String question= ctx.getText();
        System.out.println(question);

        List<String> multipleChoice = new ArrayList<>();
        String answer = "";
        boolean validAnswer=false;
        boolean noMoreOptions = false;
        int optionCounter=1;

        List<String> options = new ArrayList<>();

        for (questionnaireParser.OptionContext id: ctx.option()) {
            options.add(id.numeric_id().getText());
        }

        if(ctx.obligatoriness().getText().equals("mandatory")){
            isMandatory=true;
        }else {
            String isAnswering = Console.readLine("This question is optional, do you wish to answer it?(YES/NO)");
            isMandatory= isAnswering.equalsIgnoreCase("yes");
        }

        if(isMandatory){
            while(!noMoreOptions){

                while(answer.isEmpty() || !validAnswer){
                    try {
                        answer=Console.readLine("Option: ");

                        if(answer.isEmpty()) {
                            throw new UnsupportedOperationException("Your answer can't be empty!");
                        }

                        if (!NumberUtils.isParsable(answer)){
                            throw new UnsupportedOperationException("In a question with type: multiple choice, you must provide a numeric value as an answer.");
                        }

                        if (!options.contains(answer)){
                            throw new UnsupportedOperationException("You must choose one of the given options!");
                        }

                    }catch (UnsupportedOperationException ex ){
                        System.out.println(ex.getMessage());
                        continue;
                    }
                    validAnswer=true;
                }
                validAnswer=false;
                answer="";
                optionCounter++;
                String addMore = Console.readLine("Do you wish to add more options?(YES/NO)");
                if (!addMore.equalsIgnoreCase("yes")){
                    noMoreOptions=true;
                }

                try{
                    if(ctx.LIMIT() != null) {
                       String maxOptions = ctx.DIGITO().getText();
                     if(optionCounter > Integer.parseInt(maxOptions)) {
                           multipleChoice= new ArrayList<>();
                           optionCounter=0;
                          throw new UnsupportedOperationException("In a question with type: multiple choice with a limit, you must respect the max options limit. Please choose again!");
                        }
                    } else if(optionCounter == ctx.option().size()){
                        noMoreOptions=true;
                        throw new UnsupportedOperationException("You can't choose more options");
                    }
                }catch (UnsupportedOperationException ex) {
                  System.out.println(ex.getMessage());
                    continue;
                }


                multipleChoice.add(answer);
            }
        }

        answers.put(ctx.numeric_id().getText(), multipleChoice);
        return true;
    }


    @Override
    public Boolean visitMultiple_choice_with_input(questionnaireParser.Multiple_choice_with_inputContext ctx) {
        String question= ctx.getText();
        System.out.println(question);

        List<String> multipleChoiceInput = new ArrayList<>();
        String answer = "";
        boolean validAnswer=false;
        boolean noMoreOptions = false;
        int optionCounter=1;

        List<String> options = new ArrayList<>();

        for (questionnaireParser.OptionContext id: ctx.option()) {
            options.add(id.numeric_id().getText());
        }

        if(ctx.obligatoriness().getText().equals("mandatory")){
            isMandatory=true;
        }else {
            String isAnswering = Console.readLine("This question is optional, do you wish to answer it?(YES/NO)");
            isMandatory= isAnswering.equalsIgnoreCase("yes");
        }

        if(isMandatory){
            while(!noMoreOptions){

                while(answer.isEmpty() || !validAnswer){
                    try {
                        answer=Console.readLine("Option: ");

                        if(answer.isEmpty()) {
                            throw new UnsupportedOperationException("Your answer can't be empty!");
                        }

                        if (!NumberUtils.isParsable(String.valueOf(answer.charAt(0)))){
                            throw new UnsupportedOperationException("In a question with type: multiple choice, you must provide a numeric value corresponding to an option as an answer.");
                        }

                        if (!options.contains(String.valueOf(answer.charAt(0)))){
                            throw new UnsupportedOperationException("You must choose one of the given options!");
                        }
                        String lastOptionId = ctx.option().get(ctx.option().size()-1).numeric_id().getText();

                        if(lastOptionId.equals(String.valueOf(answer.charAt(0))) && answer.length()==1){
                            throw new UnsupportedOperationException("In a question with type: multiple choice with input, if you select the last option then you have to write another option.");
                        }

                    }catch (UnsupportedOperationException ex ){
                        System.out.println(ex.getMessage());
                        continue;
                    }
                    validAnswer=true;
                }
                validAnswer=false;
                answer="";
                optionCounter++;
                String addMore = Console.readLine("Do you wish to add more options?(YES/NO)");
                if (!addMore.equalsIgnoreCase("yes")){
                    noMoreOptions=true;
                }

                try{
                    if(optionCounter == ctx.option().size()){
                        noMoreOptions=true;
                        throw new UnsupportedOperationException("You can't choose more options");
                    }
                }catch (UnsupportedOperationException ex) {
                    System.out.println(ex.getMessage());
                    continue;
                }


                multipleChoiceInput.add(answer);
            }
        }

        answers.put(ctx.numeric_id().getText(), multipleChoiceInput);

        return true;
    }

    @Override
    public Boolean visitSorting_option(questionnaireParser.Sorting_optionContext ctx) {
        String question= ctx.getText();
        System.out.println(question);

        List<String> sortingOption = new ArrayList<>();
        String answer = "";
        boolean validAnswer=false;

        List<String> options = new ArrayList<>();

        for (questionnaireParser.OptionContext id: ctx.option()) {
            options.add(id.numeric_id().getText());
        }

        if(ctx.obligatoriness().getText().equals("mandatory")){
            isMandatory=true;
        }else {
            String isAnswering = Console.readLine("This question is optional, do you wish to answer it?(YES/NO)");
            isMandatory= isAnswering.equalsIgnoreCase("yes");
        }

        if(isMandatory){
            while(answer.isEmpty() || !validAnswer){
                try {
                    answer=Console.readLine("Answer: ");

                    if(answer.isEmpty()) {
                        throw new UnsupportedOperationException("Your answer can't be empty!");
                    }

                    String[] arrayAnswer = answer.split(", ");
                    int size = arrayAnswer.length;
                    int optionSize = ctx.option().size();

                    if(size!=optionSize){
                        throw new UnsupportedOperationException("In a question with type: sorting, you have to sort ALL the options available and ONLY those.");
                    }

                    if (!options.containsAll(Arrays.asList(arrayAnswer))){
                        throw new UnsupportedOperationException("In a question with type: sorting, you must only add valid options.");
                    }
                }catch (UnsupportedOperationException ex ){
                    System.out.println(ex.getMessage());
                    continue;
                }
                validAnswer=true;
            }
        }
        sortingOption.add(answer);

        answers.put(ctx.numeric_id().getText(), sortingOption);

        return true;
    }

    @Override
    public Boolean visitScaling_option(questionnaireParser.Scaling_optionContext ctx) {
        System.out.println(ctx.numeric_id().getText() + ". " + ctx.question_text().getText() + ctx.PARENTESIS_ESQUERDO().getText()
        + ctx.obligatoriness().getText() + ctx.PARENTESIS_DIREITO().getText());
        if(ctx.message(0)!=null) System.out.println(ctx.message(0));
        System.out.println("Type: " + ctx.SCALING_OPTION().getText());
        System.out.println("Scale: " + ctx.frase().getText());

        List<String> scalingOption = new ArrayList<>();
        String answer = "";
        boolean validAnswer=false;

        List<String> options = new ArrayList<>();

        for (questionnaireParser.OptionContext id: ctx.option()) {
            options.add(id.numeric_id().getText());
        }

        String[] scale= ctx.frase().getText().split(", ");

        if(ctx.obligatoriness().getText().equals("mandatory")){
            isMandatory=true;
        }else {
            String isAnswering = Console.readLine("This question is optional, do you wish to answer it?(YES/NO)");
            isMandatory= isAnswering.equalsIgnoreCase("yes");
        }

        if(isMandatory){
            for (questionnaireParser.OptionContext optionText: ctx.option()) {
                System.out.println(optionText.getText());
                while(answer.isEmpty() || !validAnswer){
                    try {
                        for (int i=0; i<scale.length; i++) {
                            System.out.println((i+1) + ". " + scale[i]);
                        }
                        answer=Console.readLine("Scaling option: ");

                        if(answer.isEmpty()) {
                            throw new UnsupportedOperationException("Your answer can't be empty!");
                        }

                        if (!NumberUtils.isParsable(answer)){
                            throw new UnsupportedOperationException("In a question with type: scaling choice, you must provide a numeric value corresponding to scaling option.");
                        }

                        if (Integer.parseInt(answer)<1 || Integer.parseInt(answer) > scale.length){
                            throw new UnsupportedOperationException("You must choose one of the given scaling options!");
                        }

                    }catch (UnsupportedOperationException ex ){
                        System.out.println(ex.getMessage());
                        continue;
                    }
                    validAnswer=true;
                }
                validAnswer=false;
                scalingOption.add(scale[Integer.parseInt(answer)]);
                answer="";
            }

        }

        answers.put(ctx.numeric_id().getText(), scalingOption);


        return true;
    }

    private List<String> readMultipleLines(){
        List<String> free_text = new ArrayList<>();

        while(scan.hasNextLine()){
            String answer = scan.nextLine();
            try{
                if(answer.isEmpty()){
                    break;
                }

                if(answer.length()>100){
                    //por a exceção na gramatica
                    throw new UnsupportedOperationException("In a question with type: Free Text, you can only write 100 characters per line. Please type another answer");
                }

            }catch (UnsupportedOperationException ex) {
                System.out.println(ex.getMessage());
                continue;
            }

            free_text.add(answer);
        }

        return free_text;
    }
}
