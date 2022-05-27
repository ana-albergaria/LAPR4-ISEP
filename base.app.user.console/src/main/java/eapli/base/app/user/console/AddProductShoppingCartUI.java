package eapli.base.app.user.console;

import eapli.base.app.user.console.presentation.ClientUserBaseUI;
import eapli.base.app.user.console.tcp.TcpCliOrder;
import eapli.base.clientmanagement.domain.Client;
import eapli.base.clientmanagement.domain.Email;
import eapli.base.clientmanagement.repositories.ClientRepository;
import eapli.base.infrastructure.persistence.PersistenceContext;
import eapli.framework.io.util.Console;

import java.util.Optional;


public class AddProductShoppingCartUI extends ClientUserBaseUI {
    @Override
    protected boolean doShow() {
        ClientRepository clientRepository = PersistenceContext.repositories().clients();
        Optional<Client> client = clientRepository.findByEmail(Email.valueOf("1201518@isep.ipp.pt"));
        System.out.println(client);


        String address = Console.readLine("IPv4 Address: ");
        try {
            new TcpCliOrder(address);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }
}
