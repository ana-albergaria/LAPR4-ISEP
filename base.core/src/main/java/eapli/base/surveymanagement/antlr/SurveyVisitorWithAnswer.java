package eapli.base.surveymanagement.antlr;

import org.apache.commons.lang3.math.NumberUtils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

public class SurveyVisitorWithAnswer extends questionnaireBaseVisitor {
    private final Map<String, List<String>> answers;

    public SurveyVisitorWithAnswer (Map<String, List<String>> answers){
        this.answers=answers;
    }

    @Override
    public Boolean visitQuestion(questionnaireParser.QuestionContext ctx) {
        visitChildren(ctx);

        return true;
    }

    @Override
    public Boolean visitFree_text(questionnaireParser.Free_textContext ctx) {
        String questionID = ctx.numeric_id().getText();
        System.out.println(questionID);

        List<String> questionAnswers = answers.get(questionID);

        if(questionAnswers.size()>1){
            throw new UnsupportedOperationException("In a question with type: Free Text, you can only give one answer.");
        }

        //falta verificar o length da answer com o Nuno
        return true;
    }


    @Override
    public Boolean visitNumeric(questionnaireParser.NumericContext ctx) {
        String questionID = ctx.numeric_id().getText();
        System.out.println(questionID);

        List<String> questionAnswers = answers.get(questionID);


        if(questionAnswers.size()>1){
            throw new UnsupportedOperationException("In a question with type: numeric, you can only give one answer.");
        }
        String answer = questionAnswers.get(0);
        if (!NumberUtils.isParsable(answer)){
            throw new UnsupportedOperationException("In a question with type: numeric, you must provide a numeric value as an answer.");
        }

        if(ctx.DECIMALS_ALLOWED() == null && Double.parseDouble(answer)%1!=0){
            throw new UnsupportedOperationException("This numeric question does not allow decimal values.");
        }
        return true;
    }

    @Override
    public Boolean visitSingle_choice_with_input(questionnaireParser.Single_choice_with_inputContext ctx) {
        String questionID = ctx.numeric_id().getText();
        System.out.println(questionID);

        List<String> questionAnswers = answers.get(questionID);

        if(questionAnswers.size()>1){
            throw new UnsupportedOperationException("In a question with type: single choice with input, you can only give one answer.");
        }
        String answer = questionAnswers.get(0);
        if (!NumberUtils.isParsable(answer)){
            throw new UnsupportedOperationException("In a question with type: single choice with input, you must provide a numeric value as an answer.");
        }

        String lastOptionId = ctx.option().get(ctx.option().size()-1).numeric_id().getText();

        if(lastOptionId.equals(String.valueOf(answer.charAt(0))) && answer.length()==1){
            throw new UnsupportedOperationException("In a question with type: single choice with input, if you select the last option then you have to write another option.");
        }

        return true;
    }

    @Override
    public Boolean visitSingle_choice(questionnaireParser.Single_choiceContext ctx) {
        String questionID = ctx.numeric_id().getText();
        System.out.println(questionID);
        List<String> questionAnswers = answers.get(questionID);

        if(questionAnswers.size()>1){
            throw new UnsupportedOperationException("In a question with type: single choice, you can only give one answer.");
        }
        String answer = questionAnswers.get(0);
        if (!NumberUtils.isParsable(answer)){
            throw new UnsupportedOperationException("In a question with type: single choice, you must provide a numeric value as an answer.");
        }

        return true;
    }



    @Override
    public Boolean visitMultiple_choice(questionnaireParser.Multiple_choiceContext ctx) {
        String questionID = ctx.numeric_id().getText();
        System.out.println(questionID);
        //verificar a parte dos limites

        return true;
    }


    @Override
    public Boolean visitMultiple_choice_with_input(questionnaireParser.Multiple_choice_with_inputContext ctx) {
        String questionID = ctx.numeric_id().getText();
        System.out.println(questionID);
        List<String> questionAnswers = answers.get(questionID);

        String answer = questionAnswers.get(0);

        String lastOptionID = ctx.option().get(ctx.option().size()-1).numeric_id().getText();

        if(lastOptionID.equals(String.valueOf(answer.charAt(0))) && answer.length()==1){
            throw new UnsupportedOperationException("In a question with type: multiple choice with input, if you select the last option then you have to write another option.");
        }

        return true;
    }

    @Override
    public Boolean visitSorting_option(questionnaireParser.Sorting_optionContext ctx) {
        String questionID = ctx.numeric_id().getText();
        System.out.println(questionID);
        List<String> questionAnswers = answers.get(questionID);

        if(questionAnswers.size()>1){
            throw new UnsupportedOperationException("In a question with type: sorting, you can only give one answer.");
        }

        String answer = questionAnswers.get(0);
        int size = answer.split(", ").length;

        int optionSize = ctx.option().size();

        if(size!=optionSize){
            throw new UnsupportedOperationException("In a question with type: sorting, you have to sort ALL the options available.");
        }

        return true;
    }

    @Override
    public Boolean visitScaling_option(questionnaireParser.Scaling_optionContext ctx) {
        String questionID = ctx.numeric_id().getText();
        System.out.println(questionID);
        List<String> questionAnswers = answers.get(questionID);

        int answersSize = questionAnswers.size();
        int optionSize = ctx.option().size();

        if(answersSize != optionSize){
            throw new UnsupportedOperationException("In a question with type: scaling, you have to scale ALL the options available.");
        }

        String[] valuesScale = ctx.frase().getText().split(", ");
        List<String> values = Arrays.asList(valuesScale);

        for (String answer : questionAnswers) {
            if(!values.contains(answer)) {
                throw new UnsupportedOperationException("In a question with type: scaling, you have choose one value from the given Scale.");
            }
        }


        return true;
    }


}
