package eapli.base.surveymanagement.antlr;

public class SurveyVisitor extends questionnaireBaseVisitor {
    @Override
    public Boolean visitLengthText(questionnaireParser.LengthTextContext ctx) {
        String title = ctx.getText();

        if (title.length()>20) System.out.println("teste");

        return true;
    }

}
