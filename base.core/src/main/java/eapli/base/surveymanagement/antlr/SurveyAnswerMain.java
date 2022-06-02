package eapli.base.surveymanagement.antlr;

import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.tree.ParseTree;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SurveyAnswerMain {
    public static void main(String[] args) {
        Map<String, List<String>> answers =  new HashMap<>();
        parseWithVisitor("base.core/src/main/java/eapli/base/surveymanagement/antlr/teste.txt", answers);
    }

    public static boolean parseWithVisitor(String filePath, Map<String, List<String>> answers) {
        try {
            questionnaireLexer lexer = new questionnaireLexer(CharStreams.fromFileName(filePath));
            CommonTokenStream tokens = new CommonTokenStream(lexer);
            questionnaireParser parser = new questionnaireParser(tokens);
            ParseTree tree = parser.survey();
            SurveyVisitorWithAnswer eval = new SurveyVisitorWithAnswer(answers);
            eval.visit(tree);
            return true;
        } catch(IOException e) {
            System.out.println("Make sure the file has the correct path");
            return false;
        } catch (Exception e) {
            System.out.println("The Survey does not follow the required format.");
            return false;
        }
    }
}
