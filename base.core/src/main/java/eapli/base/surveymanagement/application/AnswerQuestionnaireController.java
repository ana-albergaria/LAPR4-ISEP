package eapli.base.surveymanagement.application;

import eapli.base.infrastructure.persistence.PersistenceContext;
import eapli.base.surveymanagement.domain.Answer;
import eapli.base.surveymanagement.domain.Questionnaire;
import eapli.base.surveymanagement.repositories.AnswerQuestionnaireRepository;
import eapli.base.surveymanagement.repositories.SurveyQuestionnareRepository;
import eapli.framework.infrastructure.authz.application.AuthorizationService;
import eapli.framework.infrastructure.authz.application.AuthzRegistry;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

public class AnswerQuestionnaireController {
    private final SurveyQuestionnareRepository questionnairesRepository = PersistenceContext.repositories().questionnaries();
    private final AnswerQuestionnaireRepository answersRepository = PersistenceContext.repositories().answers();

    private final AuthorizationService authz= AuthzRegistry.authorizationService();

    private final String FILE_PATH = "base.core/src/main/java/eapli/base/surveymanagement/antlr/";
    private final String FILE_EXTENSION = ".txt";

    public List<Questionnaire> allQuestionnairesInTheSystem(){
        return (List<Questionnaire>) questionnairesRepository.findAll();
    }

    public Answer registerAnswer(String answerID){ //TODO put all the Answers' constructor parameter here!
        final var newAnswer = new Answer(answerID);

        return answersRepository.save(newAnswer);
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
}
