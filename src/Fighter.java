

public interface Fighter {
    void attack(Creature creatureName);

    default void standardAttack(Creature creatureName) {
        creatureName.receiveDamage(1);
        System.out.println("You are just poking it. ");
    }

}
