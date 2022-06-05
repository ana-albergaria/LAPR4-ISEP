package eapli.base.surveymanagement.antlr;

import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.tree.ParseTree;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SurveyAnswerMain {
    public static void main(String[] args) {
        Map<String, List<String>> map =  new HashMap<>();
        List<String> answers = new ArrayList<>();
        /*SINGLE CHOICE WITH INPUT*/
        //-> More than one option error
        /*answers.add("1");
        /answers.add("2"); -> more than option error*/
        //-> numeric value
        //answers.add("word");
        //->last option without input
        /*answers.add("3");
        map.put("1", answers);*/

        /*SINGLE CHOICE*/
        //-> More than one option error
        /*answers.add("1");
        answers.add("2");
        //-> numeric value

        map.put("2", answers);*/

        //NUMERIC
        /*answers.add("2.3");
        map.put("4", answers);*/

        /*answers.add("1, 3, 5");
        map.put("10", answers);*/

        /*answers.add("Strongly Agree");
        answers.add("Agree");
        map.put("6", answers);*/

        /*answers.add("4");
        answers.add("3");
        answers.add("2");
        answers.add("1");
        map.put("9", answers);*/

        parseWithVisitor("base.core/src/main/java/eapli/base/surveymanagement/antlr/questions/multiple_choice.txt", map);
    }

    public static boolean parseWithVisitor(String filePath, Map<String, List<String>> answers) {

        try {

            questionnaireLexer lexer = new questionnaireLexer(CharStreams.fromFileName(filePath));
            CommonTokenStream tokens = new CommonTokenStream(lexer);
            questionnaireParser parser = new questionnaireParser(tokens);
            ParseTree tree = parser.question();
            SurveyVisitorWithAnswer eval = new SurveyVisitorWithAnswer(answers);
            eval.visit(tree);
            return true;
        } catch(IOException e) {
            System.out.println("Make sure the file has the correct path");
            return false;
        } catch (Exception e) {
            System.out.println("[ERROR] " + e.getMessage());
            return false;
        }
    }
}
