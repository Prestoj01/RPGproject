/*
Name: Preston Johnson
RPG Project
Section 005
https://github.com/Prestoj01/RPGproject
*/
import java.util.Scanner;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.FileOutputStream;
import java.io.PrintWriter;

public class Game {
    public static void main (String [] args) throws IOException {
        Scanner scnr = new Scanner(System.in);
        Character user = new Character();
        Character Thors = new Character(15, 10, 5);
        Character Throk = new Character(10, 3, 5);
        Character Roger = new Character(5, 10, 15);
        Character Shanks = new Character (6, 7, 10);
        Character Levi = new Character(10, 15, 5);
        Character Quinn = new Character(5, 15, 4);
        Map gamemap = new Map();
        Place PirateShip = new Place("Pirate Ship");
            PirateShip.addOpponent("King Roger", Roger);
            PirateShip.addOpponent("Shanks", Shanks);
        Place VikingVillage = new Place("Viking Village");
            VikingVillage.addOpponent("Leader Thors", Thors);
            VikingVillage.addOpponent("Throk", Throk);
        Place NinjaGarden = new Place ("Ninja Garden");
            NinjaGarden.addOpponent("Master Levi", Levi);
            NinjaGarden.addOpponent("Quinn", Quinn);
        gamemap.addPlace(PirateShip);
        gamemap.addPlace(VikingVillage);
        gamemap.addPlace(NinjaGarden);
        String name = ""; 
        String userClass = "";
        String specialAbility;
        String guideName;

        System.out.println("Do you have a saved character? Y or N");
        String savedchar = scnr.nextLine();
        FileInputStream fileByteStream = null;
        Scanner inFS = null;
        if (savedchar.equalsIgnoreCase("y")) {
            fileByteStream = new FileInputStream("Character.txt");
            inFS = new Scanner(fileByteStream);
            name = inFS.nextLine();
            userClass = inFS.nextLine();
            int userStrength = inFS.nextInt();
            int userSpeed = inFS.nextInt();
            int userDefence = inFS.nextInt();
            fileByteStream.close();
            user.addStrength(userStrength - 5);
            user.addSpeed(userSpeed - 5);
            user.addDefence(userDefence - 5);
        }
        else {
            System.out.println("Please create your character:");
            System.out.println("What is your characters name: ");
            name = scnr.nextLine();
            System.out.println("What class would you like to be: (Options include: viking, pirate, or ninja)");
            System.out.println("**This will affect who you ally with and some stats");
            userClass = scnr.nextLine();
            while (!userClass.equalsIgnoreCase("viking") && !userClass.equalsIgnoreCase("pirate") && !userClass.equalsIgnoreCase("ninja")) {
                if (!userClass.equalsIgnoreCase("viking") && !userClass.equalsIgnoreCase("pirate") && !userClass.equalsIgnoreCase("ninja")) {
                    System.out.println("Please input a valid class option: viking, pirate, or ninja");
                    userClass = scnr.nextLine();
                }
                else {
                    break;
                }
            }

        }

        System.out.println("");
        printRules();
        System.out.println("");
        printIntro();
        
        if (userClass.equalsIgnoreCase("viking")) {
            guideName = "Thors";
            specialAbility = "Super Strength";
            user.addStrength(5);
            gamemap.removePlace(VikingVillage);
        }
        else if (userClass.equalsIgnoreCase("pirate")) {
            guideName = "Roger";
            specialAbility = "Extreme durability";
            user.addDefence(5);
            gamemap.removePlace(PirateShip);
        }
        else {
            guideName = "Levi";
            specialAbility = "Super Speed";
            user.addSpeed(5);
            gamemap.removePlace(NinjaGarden);
        }
        System.out.println(guideName + ": By the way, my name  is " + guideName + ", what is yours?\n");
        System.out.println("You give him your name\n");
        System.out.println(guideName + ": Nice to meet you " + name);
        System.out.println(guideName + ": Everyone that has woken up has gained some sort of ability, lets go see what yours is!\n");
        System.out.println("After some test, you discover your special ability is " + specialAbility + "! (This raises that respected stat by 5)");

        boolean userquit = false;
        int daycounter = 1;
        while (userquit == false) {
            System.out.println("Day " + daycounter + "\n");
            System.out.println("Current Stats:");
            user.printStats();
            System.out.println(guideName + ": What would you like to do next? Train, travel, or rest");
            String userDecision = scnr.nextLine();
                while(true) {
                    if (userDecision.equalsIgnoreCase("train")) {
                        break;
                    }
                    else if (userDecision.equalsIgnoreCase("travel")) {
                        break;
                    }
                    else if (userDecision.equalsIgnoreCase("rest")) {
                        break;
                    }
                    else {
                        System.out.println("Please input a valid option: Train, Travel, or Rest");
                        userDecision = scnr.nextLine();
                    }
                }
            if (userDecision.equalsIgnoreCase("train")) {
                System.out.println(guideName + ": Training is a great choice for today, what would you like to train?");
                System.out.println("Whatever you train (Strength, Speed, or Defence) will increase the chosen stat by 5");
                String trainingSelection = scnr.next();
                while (true) {
                    if (trainingSelection.equalsIgnoreCase("Strength")) {
                        user.addStrength(5);
                        break;
                    }
                    else if (trainingSelection.equalsIgnoreCase("Speed")) {
                        user.addSpeed(5);
                        break;
                    }
                    else if (trainingSelection.equalsIgnoreCase("Defence")){
                        user.addDefence(5);
                        break;
                    }
                    else {
                        System.out.println("Please input a valid option");
                    }
                }
            } 
            else if (userDecision.equalsIgnoreCase("travel")) {
                gamemap.listPlaces();
                System.out.println(guideName + ": Where would you like to go?");
                String userPlace = scnr.nextLine();
                if (gamemap.hasPlace(userPlace)) {
                    System.out.println("Here are you opponents");
                    gamemap.getPlace(userPlace).listOpponents();
                    System.out.println("Who would you like to Challenge?");
                    String userOpponent = scnr.nextLine();
                    if (gamemap.getPlace(userPlace).hasOpponent(userOpponent)) {
                        boolean winner = battle(user, gamemap.getPlace(userPlace).getCharacter(userOpponent));
                        if (winner) {
                            gamemap.getPlace(userPlace).removeOpponent(userOpponent, gamemap.getPlace(userPlace).getCharacter(userOpponent));
                            if (gamemap.getPlace(userPlace).placeEmpty()) {
                                gamemap.removePlace(gamemap.getPlace(userPlace));
                            }
                            System.out.println("You have defeated " + userOpponent);
                        }
                        else {
                            System.out.println("You have been defeated and your character has been lost");
                            System.exit(0);
                        }
                    }
                }
                else if (gamemap.mapEmpty()) {
                    System.out.println("You have nothing left to travel to, and have completed the game! Congratulations!");
                    userquit = true;
                }
                else {
                    System.out.println("That place does not exist, you may try again tomorrow.");
                }
            }
            else {
                System.out.println("Resting is a great way to spend any day, enjoy your time.");
            }
            daycounter = daycounter + 1;
            System.out.println("Would you like to stop playing? Y or N");
            userDecision = scnr.nextLine();
            if (userDecision.equalsIgnoreCase("Y")) {
                userquit = true;
                System.out.println("Would you like to save you character? Type Y if you would like to.");
                String saveCharacter = scnr.nextLine();
                if (saveCharacter.equalsIgnoreCase("y")) {
                    FileOutputStream fileStream = null;
                    PrintWriter outFS = null;

                    fileStream = new FileOutputStream("Character.txt");
                    outFS = new PrintWriter(fileStream);

                    outFS.println(name);
                    outFS.println(userClass);
                    outFS.println(user.getStrength());
                    outFS.println(user.getSpeed());
                    outFS.println(user.getDefence());
                    outFS.close();
                }
            }    
            else {
                userquit = false;
            }
        }
    }

    public static void printIntro() {
        System.out.println("In the year 2050 humanity was mysteriously frozen in time.");
        System.out.println("Farms animals, insects, and all other creatures remained unharmed, however, not humans.\n");
        System.out.println("One day you awake from your stone slumber");
        System.out.println("You did not awake on your own but you see someone infront of you that seems to have done something to wake you up.\n");
        System.out.println("Strange Man: Greetings, I know this may be a lot to take in, but the current year is 3019, society was set back hundreds of years because or the freeze.");
    }

    public static void printRules() {
        System.out.println("Welcome to Versus, a game in which you pick a class and fight off other clans throughout the land\n");
        System.out.println("The rules are simple and as follows:");
        System.out.println("Fights are based upon three stats (Strength, Speed, and Defence)");
        System.out.println("Strength serves as how much damage each of your attacks do");
        System.out.println("Speed serves as a measure of which fighter attacks first");
        System.out.println("In the event of a speed tie, the opponent will go first in attacking");
        System.out.println("And defence serves as a measure of how much damage a character can take before being defeated");
        System.out.println("PS* it is recommended to take on the leader of each land last as they are the toughest opponents");
        System.out.println("PS** Upon quitting the game, you are given the option to save your character");
    }

    public static boolean battle(Character user, Character opponent) {
        boolean finished = false;
        boolean winner = true;
        int userHealth = user.getDefence();
        int opponentHealth = opponent.getDefence();
        while (finished == false) {
            if (user.getSpeed() > opponent.getSpeed()) {
                opponentHealth = opponentHealth - user.getStrength();
                if (opponentHealth <= 0) {
                    finished = true;
                    winner = true;
                    return winner;
                }
                userHealth = userHealth - opponent.getStrength();
                if (userHealth <= 0) {
                    finished = true;
                    winner = false;
                    return winner;
                }
            }
            else {
                userHealth = userHealth - opponent.getStrength();
                if (userHealth <= 0) {
                    finished = true;
                    winner = false;
                    return winner;
                }
                opponentHealth = opponentHealth - user.getStrength();
                if (opponentHealth <= 0) {
                    finished = true;
                    winner = true;
                    return winner;
                }
            }
        }
        return winner;
    }
}