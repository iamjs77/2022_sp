package card.validator.server;

import java.util.Scanner;

public class CardServerLauncher {
    public static void main(String[] args)  {

        CardServer server = new CardServer();
        Thread th = new Thread(server);
        th.start();

        Scanner scanner = new Scanner(System.in);

        String input;
        while((input = scanner.nextLine()) != null) {

            if (input.equalsIgnoreCase("QUIT")) {
                server.close();
                break;
            }
        }
    }
}

