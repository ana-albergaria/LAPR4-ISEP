package eapli.base.app.server.order.requests;

import eapli.base.shoppingcartmanagement.application.OrderSrvController;
import eapli.base.surveymanagement.dto.QuestionnaireDTO;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;

public class GetQuestionnairesRequest extends OrderServerRequest{

    public GetQuestionnairesRequest(final OrderSrvController ctrl,
                                     final byte request,
                                     final ObjectOutputStream sOutObject,
                                     final DataInputStream sIn,
                                     final DataOutputStream sOut,
                                     final byte[] clientMessageUS) {
        super(ctrl, request, sOutObject, sIn, sOut, clientMessageUS);
    }
    @Override
    public void execute() {
        try {
            Iterable<QuestionnaireDTO> questionnaires = this.orderSrvController.allSurveys();
            sOutputObject.writeObject(questionnaires);
            sOutputObject.flush();
        } catch (IOException e) {
            System.out.println("[ERROR] An error because of the ObjectOutputStream has occured");
        }
    }
}
