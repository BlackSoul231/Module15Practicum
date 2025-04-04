import java.util.Random;

public class Player extends Creature {
    private Backpack inventory;

    class Backpack {
        int healingPotion;

        Backpack() {
            healingPotion = 0;
        }
    }

    public Player(String name) {
        super(name);
        this.inventory = new Backpack();
    }

    @Override
    public String toString() {
        return "Name: " + getName() + "; " + " Health: " + getHealth() + "; " + " Current level: " + getLevel() + "; " + " Gold: " + getGold() + ";";
    }

    public boolean checkExperienceForLevelUp(int currentExperience) {
        switch (currentExperience) {
            case 200: {
                System.out.println("You reached level 2! You have unspent stats point.");
                return true;
            }
            case 500: {
                System.out.println("You have reached level 3! You have unspent stats point.");
                return true;
            }
            case 800: {
                System.out.println("You have reached level 4! You have two unspent stats points.");
                return true;
            }
            case 1200: {
                System.out.println("You have reached highest possible level! You have two unspent stats points.");
                return true;
            }
            default:
                return false;
        }
    }

    public void getInfo() {
        System.out.println("Your experience is " + getExperience() + "." + ". Your level is " + getLevel() + "\n Your gold is " + getGold() + ". Your health is " + getHealth() + " . You have " + inventory.healingPotion + " healing potions.");
        switch (getLevel()) {
            case 1:
                System.out.println("To get a new level, you need " + (200 - getExperience()) + " more XP.");
                break;
            case 2:
                System.out.println("To get a new level, you need " + (500 - getExperience()) + " more XP.");
                break;
            case 3:
                System.out.println("To get a new level, you need " + (800 - getExperience()) + " more XP.");
                break;
            case 4:
                System.out.println("To get a new level, you need " + (1200 - getExperience()) + " more XP.");
                break;
        }

    }

    public void usePotion() {
        if (inventory.healingPotion > 0) {
            inventory.healingPotion -= 1;
            addHealth();
            System.out.println("+40 HP");
        } else System.out.println("You have no potions.");
    }

    public void buyPotion(Trader name, int cost) {
        takeGold(cost);
        inventory.healingPotion += 1;
    }

    @Override
    public void attack(Creature target) {
        Random successOfAttack = new Random();
        if (successOfAttack.nextInt(0, 101) < (getAgility() * 3)) {
            if (successOfAttack.nextInt(1, 6) > 3) {
                target.receiveDamage(getStrength() * 2);
                System.out.println("Critical strike from " + getName() + "!");
            }
            else {
                System.out.println("The " + getName() + " has slashed " + target.getName() + " with power of " + getStrength());
                target.receiveDamage(getStrength());
            }
            if (!target.isDead())
                System.out.println("The " + target.getName() + " health left: " + target.getHealth() + ".");
            else {
                addExperience(50);
                addGold(20);
                System.out.println("The " + target.getName() + " has been killed by " + getName() + "! +50 XP +20 GOLD.");

            }
        }
        else System.out.println("The " + getName() + " attacked and missed!");
    }
}
