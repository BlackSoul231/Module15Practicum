public class Trader extends Creature {
    private int potions = 5;
    public static final int POTION_COST = 20;


    public Trader(String name) {
        super(name);
    }

    public void addPotion() {
        ++potions;
    }

    public void sellPotion() {
        if (potions > 0) {
            addGold(POTION_COST);
            System.out.println("Trader has " + --potions + " left.");
        }
        else System.out.println("Trader has  no potions for you. Come back later.");
    }
}
