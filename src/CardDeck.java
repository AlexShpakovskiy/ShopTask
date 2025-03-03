import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

public class CardDeck {

    private static HashMap<String, Byte> cardDeck = new HashMap<String, Byte>();
    private static HashMap<String, Byte> computerCards = new HashMap<String, Byte>();
    private static HashMap<String, Byte> playerCards = new HashMap<String, Byte>();
    //private static ArrayList<String> keyArray = new ArrayList<String>();
    private static char[] suits = {'♠', '♥', '♣', '♦'};
    private static char[] highRanks = {'J', 'Q', 'K', 'A'};

    public static void Generate () {
        for (byte i = 6; i < 11; i++) {
            for (byte j = 0; j < 4; j++) {
                String builder = Integer.toString(i) + suits[j];
                //keyArray.add(builder);
                cardDeck.put(builder, i);
            }
        }

        for (byte i = 0; i < 4; i++) {
            for (char suit: suits) {
                String builder = Character.toString(highRanks[i]) + suit;
                //keyArray.add(builder);
                cardDeck.put(builder, highRanks[i] == 'A' ? (byte) 11 : (byte) (i + 2));
            }
        }
        System.out.println();

    }

    public static HashMap<String, Byte>[] getCards() {
        HashMap<String, Byte>[] array = new HashMap[2];

        for (byte j = 0; j < 10; j++) {
            byte random = (byte) Math.round(Math.random() * (cardDeck.keySet().toArray().length - 1));
            String randomKey = cardDeck.keySet().toArray()[random].toString();

            computerCards.put(randomKey, cardDeck.get(randomKey));
            cardDeck.remove(randomKey);

        }

        for (byte j = 0; j < 10; j++) {
            byte random = (byte) Math.round(Math.random() * ( cardDeck.keySet().toArray().length - 1));
            String randomKey = cardDeck.keySet().toArray()[random].toString();

            playerCards.put(randomKey, cardDeck.get(randomKey));
            cardDeck.remove(randomKey);

        }

        array[0] = computerCards;
        array[1] = playerCards;
        //System.out.println(cardDeck);
        return array;
    }
}
