package eapli.base.surveymanagement.antlr;

public class SurveyVisitor extends questionnaireBaseVisitor {
    @Override
    public Boolean visitLengthTitle(questionnaireParser.LengthTitleContext ctx) {
        String title = ctx.getText();

        if (title.length()>50) System.out.println("Title length is too long!");

        return true;
    }



}
