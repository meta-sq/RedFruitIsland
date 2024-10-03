import java.util.Scanner;
import java.util.ArrayList;
import java.util.Random;
import java.util.Collections;

/**
 * The TextManagementGame class represents a text-based management game where the player manages resources and resource generators.
 */
public class TextManagementGame {
    // Define game variables
    private int day;
    private int totalScore = 0;
    private ArrayList<Resource> resources = new ArrayList<Resource>();
    private ArrayList<Generator> generators = new ArrayList<Generator>();

    // Define a Scanner for user input
    private Scanner scanner;

    /**
     * Creates a new TextManagementGame instance with initial resource and time values.
     * TODO : Add starting resources
     */
    public TextManagementGame() {
        day = 1;       // Start at Day 1
        scanner = new Scanner(System.in);

        System.out.println("""
                You are an eccentric billionaire who was on a business trip but,
                perhaps by bad luck, has crashed on a remote island. You are the
                only survivor from the crew. You see no one. All you notice besides
                the forest are very few odd Red Fruits. That and the howl of distant
                beasts........
                
                You managed to fight and claw your way through the corporate world,
                but you cannot bribe the creatures of this forest
                """);
    }

    /**
     * Check if a method should run with a 1 in number chance.
     *
     * @return true if the method should run, false otherwise
     */
    public boolean haveEventThisTurn(int number) {
        Random random = new Random();
        int chance = random.nextInt(number); // Generates a random number between 0 (inclusive) and number (exclusive)
        return chance == 0; // Returns true with a 1 in number chance
    }

    /**
     * Prints the list of resources
     */
    public void viewResources(){
        sortResources();
        for(Resource r : resources){
            System.out.println(r.toString());
        }
    }

    /**
     * Prints the list of Generators
     */
    public void viewGenerators(){
        sortGenerators();
        for(Generator b : generators){
            System.out.println(b);
        }
    }


    /**
     * Increments the time counter and then adds more resources based on what generators are present
     * TODO : Add calculations to generate resources for the next turn
     */
    public void endDay(){
        day++;
    }

    /**
     * Adds a Generator object to the ArrayList of Generators.
     *
     * @param generator the Generator object to add
     */
    public void addGenerator(Generator generator) {
        generators.add(generator);
    }

    /**
     * Adds a Resource object to the ArrayList of resources.
     *
     * @param resource the Resource object to add
     */
    public void addResource(Resource resource) {
        resources.add(resource);
    }

    /**
     * Checks if we are out of any critical resources
     *
     * @return returns true if we are out of any critical resources returns false otherwise
     */
    public boolean isCriticalResourceEmpty(){
        for(Resource r : resources){
            if(r.isCritical() && r.getQuantity() == 0){
                return true;
            }
        }
        return false;
    }

    //Sorts the resources by quantity
    public void sortResources(){
        Collections.sort(resources);
    }

    //Sorts the generators by quantity
    public void sortGenerators(){
        Collections.sort(generators);
    }

    public void updateResources(Wood wood, RedFruit redFruit, RedFruitSeed redFruitSeed){
        //Remove resources
        for(int i = 0; i < resources.size(); i++){
            resources.remove(i);
            i--;
        }

        //Add updated resources and their updated totals
        resources.add(wood);
        resources.add(redFruit);
        resources.add(redFruitSeed);
    }

    public void updateGenerators(LumberMill lumberMill, RedFruitFarm redFruitFarm, MagicSeedBox seedBox, EnergyBox energyBox){
        //Remove generators
        for(int i = 0; i < generators.size(); i++){
            generators.remove(i);
            i--;
        }

        //Add updated resources and their updated totals
        generators.add(lumberMill);
        generators.add(redFruitFarm);
        generators.add(seedBox);
    }


    /**
     * Starts the game and manages the game loop.
     */
    public void start() {
        //Game Title
        System.out.println("Welcome to RED FRUIT ISLAND: $URVIVAL!"); //TODO: Change Text

        //Starting Inventory
        Wood totalWood = new Wood(); //CRITICAL RESOURCE
        totalWood.add(10);
        addResource(totalWood);
        //Add points for wood
        totalScore+=(10 * totalWood.scoreImpact());

        RedFruit totalRedFruit = new RedFruit(); //CRITICAL RESOURCE
        totalRedFruit.add(5);
        addResource(totalRedFruit);
        //Add points for red fruit
        totalScore+=(5 * totalRedFruit.scoreImpact());

        RedFruitSeed totalSeed = new RedFruitSeed();
        addResource(totalSeed);


        //Create Event instances
        Forage forage = new Forage();
        BeastAttack beastAttack = new BeastAttack();


        //Display starting inventory and day
        System.out.println("\n----Starting Inventory----");
        System.out.println("DAY: " + day);
        System.out.println("Wood: " + totalWood.getQuantity());
        System.out.println("Red Fruit: " + totalRedFruit.getQuantity());
        System.out.println("*** 2 Red Fruit Per Day to Survive");
        System.out.println("*** 4 Wood Per Day to Survive");
        System.out.println(""" 
            ~~~You realize you can repair the airplane and run it off of Red
                Fruit Seeds. But you will need to construct several Magical
                Seed Boxes in order to do so. But, can you survive till then?
                """);
        System.out.println("-------------------------\n");

        //Instantiate Generators and add to Generator ArrayList
        LumberMill lumberMill = new LumberMill();
        addGenerator(lumberMill);

        RedFruitFarm redFruitFarm = new RedFruitFarm();
        addGenerator(redFruitFarm);

        MagicSeedBox magicSeedBox = new MagicSeedBox();
        addGenerator(magicSeedBox);

        EnergyBox energyBox = new EnergyBox();
        addGenerator(energyBox);


        // Main game loop
        boolean continueGame = true;
        while (continueGame) {


            System.out.println("\nOptions:");
            System.out.println("\t1. Forage for Resources");
            System.out.println("\t2. Add a new Generator");
            System.out.println("\t3. Get Red Fruit Seed From Fruit");
            System.out.println("\t4. View Resources and Day");
            System.out.println("\t5. View Generators and Day");
            System.out.println("\t6. End day");
            System.out.println("\t7. Check Score");
            System.out.println("\t8. Quit game");
            System.out.print("Choose an option: ");
            int choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    //Determine random resources from forage
                    int addWood = forage.addWood();
                    int addRedFruit = forage.addRedFruit();

                    //Display what resources found
                    System.out.println("\n You managed to find the following: ");
                    System.out.println("Wood: " + addWood + "\n" + "Red Fruit: " + addRedFruit);

                    //Add resources found to total
                    totalWood.add(addWood);
                    totalRedFruit.add(addRedFruit);

                    //Add points
                    totalScore+=(addWood * totalWood.scoreImpact());
                    totalScore+=(addRedFruit * totalRedFruit.scoreImpact());

                    //Update resources
                    updateResources(totalWood, totalRedFruit, totalSeed);

                    //Add time to Forage
                    forage.addTimes();
                    break;
                case 2:
                    System.out.println("\nSelect which generator you would like to add:");
                    System.out.println("\t1. Lumber Mill    -> COST: 200 Wood");
                    System.out.println("\t2. Red Fruit Farm -> COST: 40 Red Fruit, 10 Red Fruit Seed");
                    System.out.println("\t3. Magic Seed Box -> COST: 100 Red Fruit Seed, 200 Red Fruit, 700 Wood");
                    choice = scanner.nextInt();

                    switch(choice){
                        case 1:
                            if(totalWood.getQuantity() < 200){
                                //Not Enough wood to build lumber mill
                                System.out.println("\nNot enough wood");
                            }
                            else{
                                //Built a LumberMill in exchange for 200 wood
                                lumberMill.addNumberConstructed(1);
                                totalWood.consume(200);

                                //Add points from generator build
                                totalScore+=lumberMill.scoreImpact();

                                //Update resources and generators
                                updateResources(totalWood, totalRedFruit, totalSeed);
                                updateGenerators(lumberMill, redFruitFarm, magicSeedBox, energyBox);
                            }
                            break;
                        case 2:
                            if(totalSeed.getQuantity() < 10 && totalRedFruit.getQuantity() < 40){
                                System.out.println("\nNot enough Red Fruit and not enough Red Fruit Seed");
                            }
                            else if(totalSeed.getQuantity() < 10 && totalRedFruit.getQuantity() >= 40){
                                System.out.println("\nNot enough Red Fruit Seed");
                            }
                            else if(totalSeed.getQuantity() >= 10 && totalRedFruit.getQuantity() < 40){
                                System.out.println("\nNot enough Red Fruit");
                            }
                            else{
                                //Built a Red Fruit Farm in exchange for 10 Red Fruit seeds and 40 Red Fruit
                                redFruitFarm.addNumberConstructed(1);
                                totalSeed.consume(10);
                                totalRedFruit.consume(40);

                                //Add points for building farm
                                totalScore+=redFruitFarm.scoreImpact();

                                //Update the resources and generators
                                updateResources(totalWood, totalRedFruit, totalSeed);
                                updateGenerators(lumberMill, redFruitFarm, magicSeedBox, energyBox);
                            }
                            break;
                        case 3:
                            //Initiate transfer for red fruit, seeds, and wood for Magic Seed Box
                            System.out.println("\nHow many Magic Seed Boxes would you like");
                            System.out.println("***HINT: You need 100 Red Fruit Seeds, 200 Red Fruit, and 700 Wood for 1 Magic Seed Box");

                            int transferForBox = scanner.nextInt();
                            if( (transferForBox * 100 > (totalSeed.getQuantity()) ) || (transferForBox * 200 > (totalRedFruit.getQuantity())) || (transferForBox * 700 > (totalWood.getQuantity()))){
                                System.out.println("Not enough resources");
                            }
                            else{
                                //Consume materials to produce specified amount of Magic Seed Boxes
                                magicSeedBox.addNumberConstructed(transferForBox);
                                totalWood.consume(700*transferForBox);
                                totalRedFruit.consume(200 * transferForBox);
                                totalSeed.consume(100 * transferForBox);

                                //Add points for building Magic Seed Boxes
                                totalScore += (transferForBox * magicSeedBox.scoreImpact());

                                //Update Resources and Generators
                                updateResources(totalWood, totalRedFruit, totalSeed);
                                updateGenerators(lumberMill, redFruitFarm, magicSeedBox, energyBox);
                            }
                            break;
                        default:
                            System.out.println("\nInvalid choice. Please try again.");
                            break;

                    }
                    viewGenerators();
                    break;
                case 3:
                    //NOTE: GETTING SEEDS FROM FRUIT DOES NOT ADD TO TOTALSCORE
                    System.out.println("\nHow many Red Fruit Seeds would you like?");
                    System.out.println("*HINT* 1 Red Fruit Seed costs 5 Red Fruit");
                    System.out.println("*HINT* DON'T USE ALL YOUR RED FRUIT! You might not like what happens if you do...");

                    int transferForSeed = scanner.nextInt();
                    if( transferForSeed * 5 > (totalRedFruit.getQuantity()) ){
                        System.out.println("Not enough Red Fruit for " + transferForSeed + "Red Fruit Seeds");
                    }
                    else{
                        //Exchange Red Fruit for Red Fruit Seeds
                        totalSeed.add(transferForSeed);
                        totalRedFruit.consume((5 * transferForSeed));

                        //Update resource list
                        updateResources(totalWood, totalRedFruit, totalSeed);
                    }
                    break;
                case 4:
                    System.out.println("\nDay: " + day);
                    System.out.println("\n---Here are the resources you currently have---");
                    viewResources();
                    break;
                case 5:
                    System.out.println("\nDay: " + day);
                    System.out.println("\n---Here are the generators you currently have---");
                    viewGenerators();
                    break;
                case 6:
                    //End the day and consume needed resources
                    endDay();
                    totalWood.consume(4);
                    totalRedFruit.consume(2);

                    //Add resources produced from generators to the total
                    totalWood.add(lumberMill.collectResources());
                    totalRedFruit.add(redFruitFarm.collectResources());
                    totalSeed.add(magicSeedBox.collectResources());

                    //Add points from resources generated
                    totalScore+=lumberMill.collectResources();
                    totalScore+=redFruitFarm.collectResources();
                    totalScore+=magicSeedBox.collectResources();

                    //Update resource list
                    updateResources(totalWood, totalRedFruit, totalSeed);

                    System.out.println("\nDay " + day + " has started");
                    forage.resetTimes();
                    break;
                case 7:
                    System.out.println("\nSCORE: " + totalScore);
                case 8:
                    System.out.println("\n Thank you for playing!");
                    System.exit(0);
                    break;
                default:
                    System.out.println("\nInvalid choice. Please try again.");
            }

            //Check if you have foraged 3 times. You can only forage 3 times a day
            if(forage.getTimes() == 3){
                //End the day and consume needed resources
                endDay();
                totalWood.consume(4);
                totalRedFruit.consume(2);

                //Add resources produced from generators to the total
                totalWood.add(lumberMill.collectResources());
                totalRedFruit.add(redFruitFarm.collectResources());
                totalSeed.add(magicSeedBox.collectResources());

                //Update resource list
                updateResources(totalWood, totalRedFruit, totalSeed);

                System.out.println("\n You have foraged 3 times");
                System.out.println("Day " + day + " has started");
                forage.resetTimes();
            }

            //Check if critical resources are empty
            if(isCriticalResourceEmpty()){
                System.out.println("Game Over! You ran out of critical resources.");
                System.out.println("You survived for " + day + " days");
                continueGame = false;
            }

            //Check if an Energy Box has been created
            if(energyBox.getNumberConstructed() > 0){
                System.out.println("Congratulations. You built an Energy Box. An Energy Box is an ancient relic");
                System.out.println("lost to time. It's workings are a mystery. But it has restructured both the");
                System.out.println(" airplane's body and engine. You can now leave the island.");
                System.out.println("You managed to survive this wild world . I hope you have learned");
                System.out.println("there are things money can never replace. Such as your life. I hope");
                System.out.println("you never come back here. Have a happy life!");
                continueGame = false;
            }

            //Check if a wild beast attack should occur(25% chance)
            if(haveEventThisTurn(4)){
                Random random = new Random();
                int consume = beastAttack.attackSeverity(random.nextInt(5));

                //Alert of the wild attack
                System.out.println("\nA wild beast attacks!!");
                System.out.println("You lose " + consume + " wood and " + consume + " Red Fruit");
                //Consume resources
                totalWood.consume(consume);
                totalRedFruit.consume(consume);
            }

        }

    }

    /**
     * Main method to run the game
     *
     * @param args the command-line arguments (not used in this game)
     */
    public static void main(String[] args) {
        TextManagementGame game = new TextManagementGame();
        game.start();
    }
}