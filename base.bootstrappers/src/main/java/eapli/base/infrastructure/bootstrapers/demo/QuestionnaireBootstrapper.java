package eapli.base.infrastructure.bootstrapers.demo;

import eapli.base.surveymanagement.application.CreateQuestionnaireController;
import eapli.framework.actions.Action;
import eapli.framework.domain.repositories.ConcurrencyException;
import eapli.framework.domain.repositories.IntegrityViolationException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.transaction.TransactionSystemException;

public class QuestionnaireBootstrapper implements Action {

    private static final Logger LOGGER = LoggerFactory.getLogger(QuestionnaireBootstrapper.class);

    private final CreateQuestionnaireController controller = new CreateQuestionnaireController();

    @Override
    public boolean execute() {
        String booksContent = "1. About You\n" +
                "Section Obligatoriness: mandatory\n" +
                "Repeatability: 3\n" +
                "1. Where are you from?(mandatory)\n" +
                "Type: free text\n" +
                "\n" +
                "2. How old are you?(mandatory)\n" +
                "Type: numeric\n" +
                "\n" +
                "\n" +
                "2. Product feedback\n" +
                "Section Obligatoriness: mandatory\n" +
                "3. Do you like our products?(mandatory)\n" +
                "Type: single choice\n" +
                "1)Yes\n" +
                "2)No\n" +
                "3)Maybe";

        String drinksContent = "1. About you\n" +
                "Section Obligatoriness: mandatory\n" +
                "Repeatability: 3\n" +
                "1. How old are you?(mandatory)\n" +
                "Type: numeric\n" +
                "\n" +
                "2. Where are you from?(mandatory)\n" +
                "Type: free text\n" +
                "\n" +
                "3. What is your gender?(mandatory)\n" +
                "Type: single choice\n" +
                "1)Male\n" +
                "2)Female\n" +
                "3)Other\n" +
                "\n" +
                "\n" +
                "2. Product Feedback\n" +
                "Section Obligatoriness: mandatory\n" +
                "4. How do you rate our drinks?(mandatory)\n" +
                "Type: scaling option\n" +
                "Scale: Strongly agree, agree, neutral, disagree, strongly disagree\n" +
                "1)Our drinks are the best on the market\n" +
                "2)Our drinks are better than most on the market\n" +
                "3)Our drinks are as good as another in the market\n" +
                "4)Our drinks are worse than most on the market\n" +
                "5)Our drinks are the worst on the market";

        String sportsContent = "1. About you\n" +
                "Section Obligatoriness: mandatory\n" +
                "Repeatability: 3\n" +
                "1. How old are you?(mandatory)\n" +
                "Type: numeric\n" +
                "\n" +
                "2. Where are you from?(mandatory)\n" +
                "Type: free text\n" +
                "\n" +
                "\n" +
                "2. Product feedback\n" +
                "Section Obligatoriness: mandatory\n" +
                "3. Would you recommend our sports products to another person?(mandatory)\n" +
                "Type: single choice\n" +
                "1)Yes\n" +
                "2)No\n" +
                "3)Maybe";

        register("BOOKS21-22", "Books Questionnaire", "Hello, welcome to the Books Questionnaire", booksContent, "Thanks for answering the Books Questionnaire");
        register("DRI21-22", "Drinks Questionnaire", "Hello, welcome to the Drinks Questionnaire", drinksContent, "Thanks for answering the Drinks Questionnaire");
        register("SPO21-22", "Sports Questionnaire", "Hello, welcome to the Sports Questionnaire", sportsContent, "Thanks for answering the Sports Questionnaire");

        return true;
    }

    public void register(final String code, final String title, final String welcomeMessage, final String questionnaireContent, final String finalMessage){
        try{
            controller.registerQuestionnaire(code, title, welcomeMessage, questionnaireContent, finalMessage);
            LOGGER.debug(code);
        } catch (final IntegrityViolationException | ConcurrencyException | TransactionSystemException e){
            // ignoring exception. assuming it is just a primary key violation
            // due to the tentative of inserting a duplicated user
            LOGGER.warn("Assuming {} already exists (see trace log for details on {} {})", code,
                    e.getClass().getSimpleName(), e.getMessage());
            LOGGER.trace(String.valueOf(e));
        }
    }
}
