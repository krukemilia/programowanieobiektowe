

import com.capgemini.programowanie.obiektowe.ClientManager;
import com.capgemini.programowanie.obiektowe.ClientNotFoundException;

public class TestClientManager {
    public static void main(String[] args) {
        ClientManager manager = new ClientManager();

        try {
            String clientId1 = manager.createNewClient("John", "Doe");
            System.out.println(clientId1);

            manager.activatePremiumAccount(clientId1);
            System.out.println(manager.getClientFullName(clientId1));
            System.out.println(manager.getClientCreationDate(clientId1));
            System.out.println(manager.isPremiumClient(clientId1));
            System.out.println(manager.getNumberOfClients());
            System.out.println(manager.getNumberOfPremiumClients());

            // Dodanie drugiego klienta.
        String clientId2 = manager.createNewClient("Jane", "Smith");
        System.out.println(clientId2);

        // Wyświetlenie informacji o drugim kliencie
        System.out.println(manager.getClientFullName(clientId2));
        System.out.println(manager.getClientCreationDate(clientId2));
        System.out.println(manager.isPremiumClient(clientId2));
        System.out.println(manager.getNumberOfClients());
        System.out.println(manager.getNumberOfPremiumClients());
        } catch (ClientNotFoundException e) {
            e.printStackTrace();
        }
    }
}
