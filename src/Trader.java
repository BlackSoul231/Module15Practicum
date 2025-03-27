public class Trader extends Creature {
    private int potions = 5;
    public static final int POTION_COST = 20;


    public Trader(String name) {
        super(name);
    }

    public void addPotion() {
        ++potions;
    }

    public int getPotions() {
        return potions;
    }

    public boolean canSellPotion() {
        return potions > 0;
    }

    public void sellPotion(int cost) {
        if (canSellPotion()) {
            addGold(cost);
            --potions;
        } else System.out.println("Trader has no potions for you. Come back later.");

    }
}
