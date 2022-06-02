package eapli.base.surveymanagement.antlr;

import org.apache.commons.lang3.math.NumberUtils;

import java.util.List;
import java.util.Map;

public class SurveyVisitorWithAnswer extends questionnaireBaseVisitor {
    private final Map<String, List<String>> answers;

    public SurveyVisitorWithAnswer (Map<String, List<String>> answers){
        this.answers=answers;
    }

    @Override
    public Boolean visitQuestion(questionnaireParser.QuestionContext ctx) {
        String questionID = ctx.numeric_id().getText();

        List<String> questionAnswers = answers.get(questionID);
        String questionObligatoriness = ctx.obligatoriness().getText();

        if(questionObligatoriness.equals("mandatory") && questionAnswers==null){
            System.out.println("Question " + questionID +  "must be answered, because it is of type: mandatory!");
        }

        String questionType = ctx.type().getText();

        String lastOptionId = ctx.type().option().get(ctx.type().option().size()-1).getText();

        return true;
    }

    private void isFreeText(List<String> questionAnswers){
        if(questionAnswers.size()>1){
            System.out.println("In a question with type: Free Text, you can only give one answer.");
        }

        //falta verificar o length da answer com o Nuno
    }

    private void isNumeric(List<String> questionAnswers, questionnaireParser.TypeContext typeContext){
        if(questionAnswers.size()>1){
            System.out.println("In a question with type: numeric, you can only give one answer.");
        }
        String answer = questionAnswers.get(0);
        if (!NumberUtils.isParsable(answer)){
            System.out.println("In a question with type: numeric, you must provide a numeric value as an answer.");
        }
        if(typeContext.DECIMALS_ALLOWED().getText()== null && Double.parseDouble(answer)%1!=0){
            System.out.println("This numeric question does not allow decimal values.");
        }
    }

    private void isSingleChoiceWithInput(List<String> questionAnswers, String lastOptionID){
        if(questionAnswers.size()>1){
            System.out.println("In a question with type: single choice, you can only give one answer.");
        }
        String answer = questionAnswers.get(0);
        if (!NumberUtils.isParsable(answer)){
            System.out.println("In a question with type: single choice, you must provide a numeric value as an answer.");
        }
        if(lastOptionID.equals(String.valueOf(answer.charAt(0))) && answer.length()==1){
            System.out.println("In a question with type: single choice, if you select the last option then you have.");
        }
    }

    private void isSingleChoice(List<String> questionAnswers){
        if(questionAnswers.size()>1){
            System.out.println("In a question with type: single choice, you can only give one answer.");
        }
        String answer = questionAnswers.get(0);
        if (!NumberUtils.isParsable(answer)){
            System.out.println("In a question with type: single choice, you must provide a numeric value as an answer.");
        }
    }

    private void isMultipleChoice(List<String> questionAnswers){
        //verificar a parte dos limites
    }

    private  void isMultipleChoiceWithInput(List<String> questionAnswers, String lastOptionID){
        String answer = questionAnswers.get(0);
        if(lastOptionID.equals(String.valueOf(answer.charAt(0))) && answer.length()==1){
            System.out.println("In a question with type: single choice, if you select the last option then you have.");
        }
    }

    private void isSorting(List<String> questionAnswers, int optionSize){
        if(questionAnswers.size()>1){
            System.out.println("In a question with type: sorting, you can only give one answer.");
        }

        String answer = questionAnswers.get(0);
        int size = answer.split(",").length;

        if(size!=optionSize){
            System.out.println("In a question with type: sorting, you have to sort ALL the options available.");
        }
    }
}
