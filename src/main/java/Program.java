import Chess.ChessGame;
import Chess.Tuple;
import Console.InputHandler;
import Console.BoardDisplay;
import Players.PlayerTask;

import java.util.Arrays;
import java.util.Scanner;
import java.util.concurrent.*;


public class Program {

    public static void main(String args[]) throws Exception {
        InputHandler handler = new InputHandler();
        Scanner scanner = new Scanner(System.in);

        ChessGame game = new ChessGame();

        game.addPlayer(new Players.Player1());
        game.addPlayer(new Players.Player1());

        BoardDisplay.clearConsole();
        BoardDisplay.printBoard(game.getBoard());
        while (!game.isFinished()) {
//            System.out.println("Enter move (eg. A2-A3): ");
//            String input = scanner.nextLine();

            try {
                ExecutorService executor = Executors.newSingleThreadExecutor();
                Future<String> future = executor.submit(new PlayerTask(game.getTurn(), game.getBoard()));
                String input = future.get(1, TimeUnit.SECONDS);
                executor.shutdown();
                System.out.println(input);
//                String input = game.getTurn().getNextMove(game.getBoard());
                if (!handler.isValid(input)) {
                    System.out.println("Invalid input!");
                    System.out.println("Valid input is in form: A2-A3");
                } else {
                    Tuple from = handler.getFrom(input);
                    Tuple to = handler.getTo(input);

                    boolean movePlayed = game.playMove(from, to);
                    if (!movePlayed)
                        System.out.println("Illegal move!");
                    else {
                        BoardDisplay.clearConsole();
                        BoardDisplay.printBoard(game.getBoard());
                    }
                }
            } catch (TimeoutException timeout) {
                System.out.println("Invalid activity!");
                System.out.println("Too many time has been elapsed!");
                break;
            } catch (Exception ex) {
                System.out.println("Invalid activity!");
                System.out.println("Do NOT raise any Exception!");
            }
        }
        scanner.close();
        System.out.println("\n" + game.getTurn().getName() + " has lost!\n");
        System.out.println("Game has finished. Thanks for playing.\n");

        System.exit(0);
    }
}
