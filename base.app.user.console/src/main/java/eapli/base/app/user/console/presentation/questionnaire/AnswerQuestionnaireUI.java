package eapli.base.app.user.console.presentation.questionnaire;

import eapli.base.app.user.console.presentation.questionnaire.dto.QuestionnaireDTOPrinter;
import eapli.base.clientmanagement.domain.Email;
import eapli.base.clientmanagement.repositories.ClientRepository;
import eapli.base.infrastructure.persistence.PersistenceContext;
import eapli.base.surveymanagement.application.AnswerQuestionnaireController;
import eapli.base.surveymanagement.domain.Answer;
import eapli.base.surveymanagement.domain.Questionnaire;
import eapli.base.surveymanagement.dto.QuestionnaireDTO;
import eapli.base.surveymanagement.repositories.AnswerQuestionnaireRepository;
import eapli.base.surveymanagement.repositories.SurveyQuestionnareRepository;
import eapli.framework.io.util.Console;
import eapli.framework.presentation.console.AbstractUI;
import eapli.framework.presentation.console.SelectWidget;

import javax.persistence.Persistence;
import java.util.*;

public class AnswerQuestionnaireUI extends AbstractUI {
    private AnswerQuestionnaireController controller = new AnswerQuestionnaireController();

    @Override
    protected boolean doShow() {

        Iterable<QuestionnaireDTO> surveys = this.controller.allQuestionnairesInTheSystem();
        final SelectWidget<QuestionnaireDTO> selector = new SelectWidget<>("Questionnaires For Client:", surveys, new QuestionnaireDTOPrinter());
        selector.show();
        final QuestionnaireDTO survey = selector.selectedElement();

        /*
        APENAS PARA TESTAR SE FAZIA SAVE DA ANSWER CORRETAMENTE
        SurveyQuestionnareRepository surveyRep = PersistenceContext.repositories().questionnaries();
        ClientRepository clientRepository = PersistenceContext.repositories().clients();

        AnswerQuestionnaireRepository answerQuestionnaireRepository = PersistenceContext.repositories().answers();
        answerQuestionnaireRepository.save(new Answer(surveyRep.ofIdentity("BOOKS21-22").get(),clientRepository.findByEmail(Email.valueOf("1201518@isep.ipp.pt")).get(),"1",new ArrayList<>()));*/


        /*int numOfQuestionnaires, n=1, selectedQuestionnaireNumber, numOfSections, numOfQuestions, numOfQuestionsPerSection;
        List<String> sections = new ArrayList<>();
        List<String> questions = new ArrayList<>();
        List<String> questionsPerSection = new LinkedList<>();
        Map<String, List<String>> sectionsAndQuestions = new HashMap<>();

        Questionnaire selectedQuestionnaire = null;

        List<Questionnaire> questionnaires = controller.allQuestionnairesInTheSystem();

        HashMap<Integer, Questionnaire> questionnaireOptions = new HashMap<>();

        numOfQuestionnaires = questionnaires.size();

        System.out.println("Which questionnaire would you like to answer?");

        for(Questionnaire questionnaire : questionnaires){
            System.out.printf("%d - %s\n", n, questionnaire.title());
            questionnaireOptions.put(n, questionnaire);
            n++;
        }

        selectedQuestionnaireNumber = Console.readOption(1, numOfQuestionnaires, 0);

        for(Integer selectedQuestionnaireOption : questionnaireOptions.keySet()){
            if(selectedQuestionnaireNumber == selectedQuestionnaireOption){
                selectedQuestionnaire = questionnaireOptions.get(selectedQuestionnaireOption);
            }
        }

        System.out.println("Questionnaire successfully selected. It's time to answer it!\n");


        sections = controller.allSectionsText(selectedQuestionnaire.content());
        questions = controller.allQuestionsText(selectedQuestionnaire.content());

        numOfSections = sections.size();
        numOfQuestions = questions.size();


        /*for (int m = 0; m < numOfQuestions; m++) {
            if ((int) questions.get(m).charAt(0) < (int) questions.get(m + 1).charAt(0)) {
                sectionsAndQuestions.put(, new LinkedList<>().add(questions.get(m)));
            }else{
                break;
            }
        }

        for(int j=0; j<numOfSections; j++){
            System.out.println(sections.get(j));

            int sIndex = questions.get(j).indexOf("(");
            System.out.println(questions.get(j).substring(0, sIndex));

        }*/


        return false;
    }

    @Override
    public String headline() {
        return "Answer Questionnaire.";
    }
}