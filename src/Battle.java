import java.util.Random;
import java.util.Scanner;
import java.util.concurrent.ArrayBlockingQueue;

public class Battle extends Thread {

    public static Player mainCharacter;
    public static Monster enemy;

    @Override
    public void run() {
        while (!interrupted()) {
            System.out.println("The battle has began!");
            Random selectionOfEnemy = new Random();
            if (selectionOfEnemy.nextInt(1, 2) > 1) {
                enemy = new Skeleton(GameWorld.NAMES_FOR_MONSTERS[selectionOfEnemy.nextInt(1, 8)] + " the Skeleton");
            }
            else {
                enemy = new Goblin(GameWorld.NAMES_FOR_MONSTERS[selectionOfEnemy.nextInt(1, 8)] + " the Goblin");
            }
            try {
                sleep(2000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            while (!mainCharacter.isDead() && !enemy.isDead()) {
                try {
                    if (!mainCharacter.isDead()) mainCharacter.attack(enemy);
                    sleep(1500);
                    if (!enemy.isDead()) enemy.attack(mainCharacter);
                    sleep(1500);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
            System.out.println("The battle is over! The winner is " + (mainCharacter.isDead() ? enemy.getName() : mainCharacter.getName()) + ".");
            System.out.println();
            try {
                sleep(500);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            if (!mainCharacter.isDead()) {
                System.out.println("Do you want to proceed to another battle?\nType Y or N");
                Scanner input = new Scanner(System.in);
                switch (input.next()) {
                    case "Y": break;
                    case "N": {
                        interrupt();
                        break;
                    }
                }
            }
            else interrupt();
        }
    }
}
