import java.util.InputMismatchException;
import java.util.Random;
import java.util.Scanner;

public class GameWorld {
    static final String[] NAMES_FOR_MONSTERS = new String[]{"Zerkk", "Kotsh", "Oglak", "Joks", "Katar", "Hantork", "Horrot", "Ottukr"};
    static final String[] NAMES_FOR_TRADERS = new String[]{"Olaf Silvertongue", "Bjork the Peckish", "Jan Goldenberg", "Petro Barrigo", "Holz Geldmann", "Jouni the Finn", "Maurizio Legale", "John Johnson"};
    static Trader mapTrader;
    static int TURN_OFF = 1;

    public GameWorld() {
        Random randomiser = new Random();
        int randomNames = randomiser.nextInt(0, 8);
        mapTrader = new Trader(NAMES_FOR_TRADERS[randomNames]);
    }

    public static void main(String[] args) {
        new GameWorld();
        System.out.println("Welcome to my first RPG game.\n\nIn this game you play as stranger who was sent to the castle to clear it out of monsters.\n\nLet's create a character first. What's your name?");
        Scanner input = new Scanner(System.in);
        Player player = new Player(input.next());
        System.out.println("So, your name is " + player.getName() + ", right? Let's begin our journey.");
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        while (TURN_OFF == 1) {
            System.out.println();
            System.out.println("Where do you want to go? \n 1. To the Trader (" + mapTrader.getName() + "). \n 2. To the dark forest. \n 3. Exit \n To take a look at your stats - type 0.");
            System.out.println("Enter a number to proceed.");
            try {
                switch (input.nextInt()) {
                    case 1: {
                        System.out.println("You are in trader's den. Do you want to buy a healing potion? It costs " + Trader.POTION_COST + ". \n Type Y or N.");
                        switch (input.next()) {
                            case "Y": {
                                player.buyPotion(mapTrader);
                                System.out.println("You have bought one healing potion. Do you want to buy more?");
                                switch (input.next()) {
                                    case "Y": {
                                        player.buyPotion(mapTrader);
                                        System.out.println("You have bought one healing potion.");
                                        break;
                                    }
                                    case "N": {
                                        System.out.println("You are moving back.");
                                        break;
                                    }
                                }
                                break;
                            }
                            case "N": {
                                System.out.println("You are moving back.");
                                break;
                            }
                            default: {
                                System.out.println("Please, use Y or N to select an option.");
                                break;
                            }
                        }
                    }
                    case 2: {
                        Battle.mainCharacter = player;
                        Battle fight = new Battle();
                        fight.start();
                        try {
                            fight.join();
                        } catch (InterruptedException e) {
                            break;
                        }
                        fight.interrupt();
                        if (player.isDead()) {
                            System.out.println("Your character is dead. This is the place, where your journey ends.");
                            TURN_OFF = 0;
                        }
                        break;
                    }
                    case 3: {
                        System.out.println("Farewell!");
                        TURN_OFF = 0;
                        break;
                    }
                    case 0: {
                        System.out.println();
                        player.getInfo();
                        System.out.println();
                        try {
                            Thread.sleep(3000);
                        } catch (InterruptedException e) {
                            throw new RuntimeException(e);
                        }
                        break;
                    }
                    default: {
                        System.out.println("Please, follow the instructions.");
                        break;
                    }
                }
            }
            catch (InputMismatchException exception) {
                System.out.println("Please, follow the instructions");
                return;
            }
        }
    }

}
