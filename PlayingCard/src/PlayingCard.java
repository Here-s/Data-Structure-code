import java.util.ArrayList;
import java.util.List;
import java.util.Random;


class Card {
    //数字
    private int rank;
    //花色
    private String suit;

    public Card(int rank, String suit) {
        this.rank = rank;
        this.suit = suit;
    }

    @Override
    public String toString() {
        return "[" + this.suit + "："+ this.rank  +
                ']';
    }
}
public class PlayingCard {

    public static final String[] suits = {"♥","♠","♣","♦"};

    public static List<Card> buyCard() {
        ArrayList<Card> cards = new ArrayList<>();
        for (int i = 0; i < 4; i++) {
            for (int j = 1; j <= 13; j++) {
                /*String suit = suits[i];
                int rank = j;
                Card card = new Card(rank,suit);*/
                cards.add(new Card(j,suits[i]));
            }
        }
        return cards;
    }

    private static void swap(List<Card> cards, int i, int j) {
        Card tmp = cards.get(i);
        cards.set(i,cards.get(j));
        cards.set(j,tmp);
    }

    //洗牌  用数组下标和后面随机一个下标交换
    private static void shuffle(List<Card> cards) {
        int size = cards.size();
        for (int i = size - 1; i > 0; i--) {
            Random random = new Random();
            int rand = random.nextInt(i);
            swap(cards,i,rand);
        }
    }

    public static void main(String[] args) {
        List<Card> cards = buyCard();
        System.out.println("买牌 "+cards);
        shuffle(cards);
        System.out.println("洗牌 "+cards);

        System.out.println("揭牌：3个人每个人轮流揭 5 张牌");

        //当作一个 二维数组 每个元素又是一个 List
        ArrayList<List<Card>> hand = new ArrayList<>();

        List<Card> hand1 = new ArrayList<>();
        List<Card> hand2 = new ArrayList<>();
        List<Card> hand3 = new ArrayList<>();

        //这样就知道揭的牌在哪个 head 里面了
        hand.add(hand1);
        hand.add(hand2);
        hand.add(hand3);

        //每个人轮流揭牌 一次揭三张 一共 5 此
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 3; j++) {
                //每次揭完一张牌 后面的就揭不到了，所以直接删掉这张牌就行了
                //每次都是拿的第一张牌
                Card card = cards.remove(0);
                hand.get(j).add(card);
            }
        }
        System.out.println("第一个人的牌："+hand1);
        System.out.println("第二个人的牌："+hand2);
        System.out.println("第三个人的牌："+hand3);
        System.out.println("剩下的牌："+cards);
    }

    public static void main1(String[] args) {
        Card card = new Card(3,"♥");
        System.out.println(card);
    }
}
