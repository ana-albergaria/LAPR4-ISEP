package eapli.base.app.user.console.presentation.questionnaire;

import eapli.base.app.user.console.presentation.questionnaire.dto.QuestionnaireDTOPrinter;
import eapli.base.app.user.console.presentation.route_planner.AgvRouteUtils;
import eapli.base.infrastructure.persistence.PersistenceContext;
import eapli.base.surveymanagement.antlr.SurveyVisitorWithAnswer;
import eapli.base.surveymanagement.antlr.questionnaireLexer;
import eapli.base.surveymanagement.antlr.questionnaireParser;
import eapli.base.surveymanagement.application.AnswerQuestionnaireController;
import eapli.base.surveymanagement.dto.QuestionnaireDTO;
import eapli.base.warehousemanagement.domain.Bin;
import eapli.base.warehousemanagement.repositories.BinRepository;
import eapli.framework.presentation.console.AbstractUI;
import eapli.framework.presentation.console.SelectWidget;
import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.tree.ParseTree;

import java.awt.geom.Point2D;
import java.util.*;

public class AnswerQuestionnaireUI extends AbstractUI {
    private AnswerQuestionnaireController controller = new AnswerQuestionnaireController();

    @Override
    protected boolean doShow() {
        BinRepository binRepository = PersistenceContext.repositories().bins();
        Iterator<Bin> bins = binRepository.findAll().iterator();
        List<Bin> list = new ArrayList<>();
        list.add(bins.next());
        list.add(bins.next());

        String[][] matrix =
                {
                        { "1", "X", "6", "5", "5", "1", "1", "1", "7", "X" },
                        { "3", "6", "2", "X", "6", "5", "7", "2", "6", "6" },
                        { "1", "3", "6", "1", "1", "1", "7", "1", "X", "5" },
                        { "7", "5", "6", "3", "1", "3", "3", "1", "1", "7" },
                        { "3", "X", "6", "X", "7", "2", "6", "5", "X", "X" },
                        { "3", "2", "5", "1", "2", "5", "1", "2", "3", "X" },
                        { "4", "2", "2", "2", "5", "2", "3", "7", "7", "3" },
                        { "7", "2", "X", "3", "5", "2", "2", "3", "6", "3" },
                        { "5", "1", "X", "2", "6", "X", "6", "7", "3", "7" },
                        { "1", "X", "1", "7", "5", "3", "6", "5", "3", "9" }
                };

        LinkedList<Point2D> finalRoute = AgvRouteUtils.computeFinalRoute(matrix,0,0,list);
        System.out.println(finalRoute);

        QuestionnaireDTO survey = null;

        boolean hasNotAnsweredYet = false;

        while(!hasNotAnsweredYet) {
            Iterable<QuestionnaireDTO> surveys = this.controller.questionnairesForClient();
            final SelectWidget<QuestionnaireDTO> selector = new SelectWidget<>("Questionnaires For Client:", surveys, new QuestionnaireDTOPrinter());
            selector.show();
            survey = selector.selectedElement();
            if(survey == null)
                break;
            hasNotAnsweredYet = controller.verifyIfClientAnswered(survey);
            if(!hasNotAnsweredYet)
                System.out.println("You have already answered that survey! Please, choose another one.");

        }




        if(survey != null) { //the client doesn't want to exit
            String surveyString = extractSurvey(survey);

            Map<String, List<String>> answers = new HashMap<>();

            boolean validAnswers = parseSurveyAnswersWithVisitor(surveyString, answers);

            if(validAnswers) {
                System.out.println("Your answers will be now saved!");
                this.controller.sendAnswersToBeSaved(answers, survey);
                System.out.println("Answers successfuly saved!");
            }
        }



        return false;
    }

    private String extractSurvey(QuestionnaireDTO survey) {
        StringBuilder text = new StringBuilder();
        text.append(survey.code() + " " + survey.title() + "\n");
        if(!survey.welcomeMessage().isEmpty())
            text.append(survey.welcomeMessage() + "\n\n");
        text.append(survey.questionnaireContent() + "\n\n\n\n");
        text.append(survey.finalMessage());
        return text.toString();
    }

    private boolean parseSurveyAnswersWithVisitor(String survey, Map<String, List<String>> answers) {

        try {

            questionnaireLexer lexer = new questionnaireLexer(CharStreams.fromString(survey));
            CommonTokenStream tokens = new CommonTokenStream(lexer);
            questionnaireParser parser = new questionnaireParser(tokens);
            ParseTree tree = parser.survey();
            SurveyVisitorWithAnswer eval = new SurveyVisitorWithAnswer(answers);
            eval.visit(tree);
            return true;
        } catch (Exception e) {
            System.out.println("[ERROR] " + e.getMessage());
            return false;
        }
    }

    @Override
    public String headline() {
        return "Answer Questionnaire.";
    }
}