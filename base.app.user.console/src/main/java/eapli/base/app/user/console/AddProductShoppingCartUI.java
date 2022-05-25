package eapli.base.app.user.console;

import eapli.base.app.user.console.presentation.ClientUserBaseUI;
import eapli.base.app.user.console.tcp.TcpCliOrder;
import eapli.framework.io.util.Console;




public class AddProductShoppingCartUI extends ClientUserBaseUI {
    @Override
    protected boolean doShow() {


        String address = Console.readLine("IPv4 Address: ");
        try {
            new TcpCliOrder(address);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }
}
