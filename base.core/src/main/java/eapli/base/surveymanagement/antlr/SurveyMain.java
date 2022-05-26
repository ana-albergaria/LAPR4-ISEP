package eapli.base.surveymanagement.antlr;

import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.tree.ParseTree;

import java.io.IOException;

public class SurveyMain {
    public static void main(String[] args) throws IOException {
        parseWithVisitor();
    }


    public static void parseWithVisitor() {
        try {
            questionnaireLexer lexer = new questionnaireLexer(CharStreams.fromFileName("base.core/src/main/java/eapli/base/surveymanagement/antlr/teste.txt"));
            CommonTokenStream tokens = new CommonTokenStream(lexer);
            questionnaireParser parser = new questionnaireParser(tokens);
            ParseTree tree = parser.survey();
            SurveyVisitor eval = new SurveyVisitor();
            eval.visit(tree);
            System.out.println("Survey has a valid format!");
        } catch(IOException e) {
            System.out.println("The Survey does not follow the required format.");
        }
    }
}


