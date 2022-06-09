import java.util.Scanner;

import static java.lang.Integer.parseInt;

public class CoffeeMachine {
    private static int M_waterSupply = 400; //ml
    private static int M_milkSupply = 540; //ml
    private static int M_coffeeBeansSupply = 120; //g
    private static int M_disposableCups = 9; //pieces
    private static int M_money = 550; //dollars
    private static Scanner M_scanner = new Scanner(System.in);
    private static int M_refillCounter=0;
    private static boolean M_quit=false;

    enum States{READY, ORDERING, SELECTING, REFILLING }
    private static States M_currentState = States.READY;

    public static void main(String[] args) {
        handleCommand("reset");
        while(!M_quit) {handleCommand(M_scanner.nextLine());}
    }

    private static void start(){
        System.out.println("Starting to make a coffee...");
        System.out.println("Grinding coffee beans...");
        System.out.println("Boiling water...");
        System.out.println("Mixing boiled water with crushed coffee beans...");
        System.out.println("Pouring coffee into the cup...");
        System.out.println("Pouring some milk into the cup...");
        System.out.println("Coffee is ready!");
    }

    private static void printCurrentInventory(){
        System.out.println("The coffee machine has:");
        System.out.println(M_waterSupply + " ml of water");
        System.out.println(M_milkSupply +" ml of milk");
        System.out.println(M_coffeeBeansSupply + " g of coffee beans");
        System.out.println(M_disposableCups + " disposable cups");
        System.out.println("$"+ M_money + " of money");
    }

    private static void handleCommand(String command) {
        switch (M_currentState) {
            case READY:
                if(command.equalsIgnoreCase("reset")) {
                    resetScreen();
                }
                else if(command.equalsIgnoreCase("buy")) {
                    System.out.println("What do you want to buy? 1 - espresso, 2 - latte, 3 - cappuccino, back - to main menu: ");
                    M_currentState = States.ORDERING;
                }
                else if(command.equalsIgnoreCase("fill")) {
                    M_currentState = States.REFILLING;
                    M_refillCounter = 0;
                    System.out.println("Write how many ml of water you want to add: ");
                }
                else if(command.equalsIgnoreCase("take")) {
                    emptyCashContainer();
                    resetScreen();
                }
                else if(command.equalsIgnoreCase("remaining")) {
                    printCurrentInventory();
                    resetScreen();
                }
                else if(command.equalsIgnoreCase("exit")) {
                    M_quit=true;
                }
                break;
            case ORDERING:
                if(command.equalsIgnoreCase("back")) {
                    M_currentState = States.READY;
                }
                else {
                    int coffeeType = parseInt(command);
                    makeCoffee(coffeeType);
                    M_currentState = States.READY;
                    resetScreen();
                }
                break;
            case REFILLING:
                switch (M_refillCounter){
                    case 0: //water
                        M_waterSupply += parseInt(command);
                        M_refillCounter ++;
                        System.out.println("Write how many ml of milk you want to add:");
                        break;
                    case 1: //milk
                        M_milkSupply += parseInt(command);
                        M_refillCounter ++;
                        System.out.println("Write how many grams of coffee beans you want to add: ");
                        break;
                    case 2: //coffeBeans
                        M_coffeeBeansSupply += parseInt(command);
                        M_refillCounter ++;
                        System.out.println("Write how many disposable cups of coffee you want to add: ");
                        break;
                    case 3: //disposableCups
                        M_disposableCups += parseInt(command);
                        //Refill finished. Resetting...
                        M_refillCounter =0;
                        M_currentState = States.READY;
                        resetScreen();
                        break;
                }
                break;
            default:
                throw new IllegalStateException("Unexpected value: " + command);
        }
    }

    private static void emptyCashContainer() {
        System.out.println("I gave you "+ M_money);
        M_money = 0;
    }

    private static void makeCoffee(int coffeType) {
        int water=0,milk=0,coffeeBeans=0,money=0; int cup =1;
        switch(coffeType){
            case 1: //espresso
                water = 250;
                milk = 0;
                coffeeBeans = 16;
                money = 4;
                break;
            case 2: //latte
                water = 350;
                milk = 75;
                coffeeBeans = 20;
                money = 7;
                break;
            case 3: //cappuccino
                water = 200;
                milk = 100;
                coffeeBeans = 12;
                money = 6;
        }
        if(water>M_waterSupply) System.out.println("Sorry, not enough water!");
        else if(milk>M_milkSupply) System.out.println("Sorry, not enough milk!");
        else if(coffeeBeans>M_coffeeBeansSupply) System.out.println("Sorry, not enough coffee beans");
        else if(cup>M_disposableCups) System.out.println("Sorry, not enough cups!");
        else {
            System.out.println("I have enough resources, making you a coffee!\n");
            M_waterSupply -= water;
            M_milkSupply -= milk;
            M_coffeeBeansSupply -= coffeeBeans;
            M_disposableCups -= cup;
            M_money += money;
        }
    }

    private static void resetScreen() {
        System.out.println("\nWrite action (buy, fill, take, remaining, exit)");
    }
}
