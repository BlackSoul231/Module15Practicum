import java.util.ArrayList;
import java.util.Random;

public abstract class Creature implements Fighter {
    private String name;
    private int health;
    private int agility;
    private int strength;
    private int gold;
    private int experience;
    private int level;
    private static int counterOfCreatures = 0;
    static ArrayList<Creature> creaturesList = new ArrayList<>();
    static Creature playerRef;

    public Creature() {
        this("Unknown creature " + ++counterOfCreatures);
    }

    public Creature(String name) {
        this.name = name;
        this.health = 100; // Health for every single creature is 100
        this.level = 1; // Initial level is 1
        this.experience = 0; // Initial experience
        this.gold = 0; // No gold at the start
        this.strength = 20; // Initial strength
        this.agility = 25; // Initial agility
        creaturesList.add(this);
        ++counterOfCreatures; // Counting amount of created creatures.
    }

    public void receiveDamage(int damage) {
        health = -damage;
    }

    public String getName() {
        return name;
    }

    public int getExperience() {
        return experience;
    }

    public void addExperience(int newExp) {
        experience += newExp;
    }

    public void addAgility(int newAgility) {
        agility += newAgility;
    }

    public int getAgility() {
        return agility;
    }

    public void addStrength(int newStrength) {
        strength += newStrength;
    }

    public int getStrength() {
        return strength;
    }

    public int getGold() {
        return gold;
    }

    public void addGold(int amount) {
        gold += amount;
    }

    public void takeGold(int amount) {
        gold -= amount;
    }

    public int getLevel() {
        return level;
    }

    public void levelUp() {
        level++;
        System.out.println("Level up!");
    }

    public int getHealth() {
        return health;
    }

    public void addHealth() {
        health += 40;
    }

    public boolean isDead() {
        return health <= 0;
    }

    @Override
    public void attack(Creature target) {
        Random successOfAttack = new Random();
        if (successOfAttack.nextInt(0,101) < (getAgility() * 3)) {
            if (successOfAttack.nextInt(1,6) > 3) target.receiveDamage(strength * 2);
            else target.receiveDamage(strength);
            if (!target.isDead())  System.out.println("The " + getName() + " has attacked " + target.getName() + ". Health left: " + target.getHealth() + ".");
            else System.out.println("The " + target.getName() + " has been killed by " + getName() + "!");
        }
        else System.out.println("The " + getName() + " attacked and missed!");
    }

}
