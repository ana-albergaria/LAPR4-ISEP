package eapli.base.surveymanagement.antlr;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.Assert.*;

public class SurveyAnswerMainTest {

    private Map<String, List<String>> map =  new HashMap<>();
    List<String> answers = new ArrayList<>();

    @Test
    public void ensureSingleChoiceWithInputWithOneOption() {

        //answers.add("1");
        //answers.add("2");
        //map.put("9", answers);

        String TRUSTED_STORE= System.getProperty("user.dir") + "/src/main/java/eapli/base/surveymanagement/antlr/questions/multiple_choice.txt";

        SurveyAnswerMain.parseWithVisitor(TRUSTED_STORE, map);




    }

}