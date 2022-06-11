package eapli.base.surveymanagement.dto;

import java.io.Serializable;

public class QuestionnaireDTO implements Serializable {
    private String code;

    private String title;

    private String welcomeMessage;

    private String questionnaireContent;

    private String finalMessage;

    public QuestionnaireDTO(final String code, final String title, final String welcomeMessage, final String questionnaireContent, final String finalMessage){
        this.code = code;
        this.title = title;
        this.welcomeMessage = welcomeMessage;
        this.questionnaireContent = questionnaireContent;
        this.finalMessage = finalMessage;
    }

    @Override
    public String toString(){
        return String.format("Questionnaire: %s %s \n", code, title);
    }
}
