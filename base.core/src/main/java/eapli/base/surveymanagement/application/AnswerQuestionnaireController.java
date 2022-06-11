package eapli.base.surveymanagement.application;

import eapli.base.infrastructure.persistence.PersistenceContext;
import eapli.base.surveymanagement.domain.Answer;
import eapli.base.surveymanagement.domain.Questionnaire;
import eapli.base.surveymanagement.dto.QuestionnaireDTO;
import eapli.base.surveymanagement.repositories.AnswerQuestionnaireRepository;
import eapli.base.surveymanagement.repositories.SurveyQuestionnareRepository;
import eapli.framework.infrastructure.authz.application.AuthorizationService;
import eapli.framework.infrastructure.authz.application.AuthzRegistry;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public class AnswerQuestionnaireController {
    private final SurveyQuestionnareRepository questionnairesRepository = PersistenceContext.repositories().questionnaries();
    private final AnswerQuestionnaireRepository answersRepository = PersistenceContext.repositories().answers();

    private final AuthorizationService authz= AuthzRegistry.authorizationService();

    private final AnswerQuestionnaireService service = new AnswerQuestionnaireService();

    private final String FILE_PATH = "base.core/src/main/java/eapli/base/surveymanagement/antlr/surveys/";
    private final String FILE_EXTENSION = ".txt";

    public Iterable<QuestionnaireDTO> allQuestionnairesInTheSystem(){
        return service.allSurveys();
    }

    /*public Answer registerAnswer(String answerID){ //TODO put all the Answers' constructor parameter here!
        final var newAnswer = new Answer(answerID);

        return answersRepository.save(newAnswer);
    }

    public List<String> allSectionsText(String questionnaireContent){
        List<String> sectionsText = new LinkedList<>();

        List<Object> lines = multipleLineStringToArray(questionnaireContent);

        for(Object line : lines){
            if(!line.toString().isEmpty() && Character.isDigit(line.toString().charAt(0)) && !line.toString().contains("?") && !line.toString().contains(")")){
                sectionsText.add(line.toString());
            }
        }

        return sectionsText;
    }

    public List<String> allQuestionsText(String questionnaireContent){
        List<String> questionsText = new LinkedList<>();

        List<Object> lines = multipleLineStringToArray(questionnaireContent);

        for(Object line : lines){
            if(!line.toString().isEmpty() && line.toString().contains("?")){
                questionsText.add(line.toString());
            }
        }

        return questionsText;
    }

    public String questionType(String questionnaireContent){
        List<Object> lines = multipleLineStringToArray(questionnaireContent);

        String questionType = "";

        for(Object line : lines){
            if(line.toString().contains("Type")){
                questionType = line.toString().replace("Type: ", "");
            }
        }

        return questionType;
    }

    public void printQuestionnaire(Questionnaire questionnaire) throws IOException {
        String completeFilePath = FILE_PATH + questionnaire.title() + FILE_EXTENSION;
        Path questionnaireFilePath = Paths.get(completeFilePath);

        FileInputStream questionnaireFileInput = new FileInputStream(completeFilePath);
        InputStream questionnaireInput = new BufferedInputStream(questionnaireFileInput);

        byte[] questionnaireFileSize = new byte[(int) Files.size(questionnaireFilePath)];

        try{
            for(int length = 0; (length = questionnaireInput.read(questionnaireFileSize)) != -1;){
                System.out.write(questionnaireFileSize, 0, length);
            }
        }finally {
            questionnaireInput.close();
        }
    }

    private List<Object> multipleLineStringToArray(String questionnaireContent){
        long numOfLines = questionnaireContent.lines().count();
        Object[] linesArray = questionnaireContent.lines().toArray();

        return new LinkedList<>(Arrays.asList(linesArray).subList(0, (int) numOfLines));
    }*/
}
