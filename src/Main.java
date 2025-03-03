//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.



import java.nio.file.LinkPermission;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Scanner;

public class Main {
    private static Scanner console = new Scanner(System.in);


    public static void main(String[] args) {
        byte counter = 1;
        while (true) {
            System.out.println("----------------\nRound " + counter + "\n----------------");
            CardDeck.Generate();
            oneTurn();
            counter++;
        }

    }

    public static void oneTurn() {
        String[] computerOutput = new String[10];
        String[] playerOutput = new String[10];
        ArrayList<Byte> playerUsed = new ArrayList<Byte>();
        ArrayList<Byte> computerUsed = new ArrayList<Byte>();
        HashMap<String, Byte>[] Deck = CardDeck.getCards();
        HashMap<String, Byte> computerCards = Deck[0];
        HashMap<String, Byte> playerCards = Deck[1];
        Arrays.fill(computerOutput, "[#]");
        Arrays.fill(playerOutput, "[#]");

        byte playerScore = 0;
        byte computerScore = 0;


        // PLAYER

        while (true) {
            System.out.println("\nYour board:\n" +
                    StrBuilder.builder(playerOutput) + " | " + playerScore);
            if (console.hasNextByte()) {
                byte input = console.nextByte();
                if (input > 0 && input < 11 && playerUsed.contains(input) == false) {
                    playerUsed.add(input);
                    String card = playerCards.keySet().toArray()[input - 1].toString();
                    playerOutput[input - 1] = "[" + card + "]";
                    playerScore += playerCards.get(card);
                    if (playerScore > 21) {
                        System.out.println("\nYour board:\n" +
                                StrBuilder.builder(playerOutput) + " | " + "Overdraft" + "(" + playerScore + ")");
                        break;
                    }
                    if (playerScore == 21) {
                        System.out.println("\nYour board:\n" +
                                StrBuilder.builder(playerOutput) + " | " + playerScore);
                        break;
                    }
                }

            } else if (console.hasNextLine()) {
                if (console.nextLine().equals("") || console.nextLine().equals("ok")) {
                    break;
                }
            }
        }

        System.out.println("\nYour cards");
        for (String i: playerOutput) {
            if (i != "[#]") {
                System.out.print(i);
            }
        }
        System.out.println(" | Score: " + playerScore);

        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        // COMPUTER

        while (true) {
            try {
                Thread.sleep(800);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            System.out.println("\nComputer board:\n" +
                    StrBuilder.builder(computerOutput) + " | " + computerScore);

            byte input = (byte) (Math.random() * 10);
            if (computerUsed.contains(input) == false) {

                String card = computerCards.keySet().toArray()[input].toString();
                computerOutput[input] = "[" + card + "]";
                computerScore += computerCards.get(card);
                if (computerScore >= playerScore) {
                    System.out.println("\nComputer board:\n" +
                            StrBuilder.builder(computerOutput) + " | " + computerScore);
                    break;
                }
                if (computerScore > 21) {
                    System.out.println("\nComputer board:\n" +
                            StrBuilder.builder(computerOutput) + " | " + "Computer Overdraft" + "(" + computerScore + ")");
                    break;
                }

                if (playerScore > 21 && computerScore < 21) {
                    System.out.println("\nComputer board:\n" +
                            StrBuilder.builder(computerOutput) + " | " + computerScore);
                    break;
                }

            }


        }

        System.out.println("\nComputer cards");
        for (String i : computerOutput) {
            if (i != "[#]") {
                System.out.print(i);
            }
        }

        System.out.println(" | Score: " + playerScore);
        // FINAL

        System.out.println("\nYour score: " + playerScore + "\nComputer score: " + computerScore);

        if (computerScore > playerScore && computerScore <= 21) {
            System.out.println("COMPUTER WON!");
        } else if (playerScore > computerScore && playerScore <= 21) {
            System.out.println("YOU WON!");
        } else if (playerScore == computerScore || (playerScore > 21 && computerScore > 21)) {
            System.out.println("DRAW!");
        } else if (computerScore > 21 && playerScore <= 21) {
            System.out.println("YOU WON!");
        } else if (playerScore > 21 && computerScore <= 21) {
            System.out.println("COMPUTER WON!");
        }
    }
}
