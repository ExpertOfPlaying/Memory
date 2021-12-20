package memory;

import java.util.List;
import java.util.Scanner;


public class MemoryMain {

    private static MemoryImplementation mimp;

    public MemoryMain(String name1, String name2) {
        mimp = new MemoryImplementation(name1, name2);
    }

    public void getInteger(Player user, boolean choosePN) throws StateException {
        Scanner sc = new Scanner(System.in);
        boolean parameterNotFound = true;
        int chosenNumber;
        while (parameterNotFound) {
            try {
                chosenNumber = Integer.parseInt(sc.next());
                if (choosePN) {
                    if (chosenNumber < 1 || chosenNumber > 2) {
                        System.out.println("This value in invalid! Please enter either 1 or 2!");
                    } else {
                        mimp.choosePlayerNumber(user, PlayerNumber.values()[chosenNumber - 1]);
                        parameterNotFound = false;
                    }
                } else {
                    if (chosenNumber < 1 || chosenNumber > 18) {
                        System.out.println("This value in invalid! Please enter a value between 1 and 18!");
                    } else {
                        mimp.setNumberOfCardPairs(chosenNumber);
                        parameterNotFound = false;
                    }
                }
            } catch (NumberFormatException e) {
                if (choosePN) {
                    System.out.println("This value in invalid! Please enter either 1 or 2!");
                } else
                    System.out.println("This value in invalid! Please enter a value between 1 and 18!");
            } catch (PlayerNumberTakenException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    public void playTheGame(Player user, Gamefield gamefield) throws StateException, GameOverException {
        for(int i = 0; i < 2; i++) {
            playTheGameSupport(user, gamefield);
        }
    }

    public void playTheGameSupport(Player user, Gamefield gamefield) throws GameOverException, StateException {
        Scanner sc = new Scanner(System.in);
        int pickedCardNumber;
        boolean gameOn = true;
        while (gameOn) {
            try {
                if(mimp.getPickOne() == -1) {
                    gamefield.createGamefield(mimp.getTotalCards());
                    System.out.println(user.getUser() + ", choose a card!");
                }else{
                    System.out.println(user.getUser() + ", choose another card!");
                }
                pickedCardNumber = Integer.parseInt(sc.next());
                mimp.pickCard(pickedCardNumber, user);
                gameOn = false;
            } catch (NumberFormatException e) {
                System.out.println("This value in invalid! Please choose from the possible values!");
            } catch (IllegalArgumentException | CardAlreadyPickedException | KeyOutOfBoundsException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    public static void main(String[] args) throws StateException, IllegalArgumentException {
        Gamefield gamefield = new Gamefield();
        Scanner sc = new Scanner(System.in);
        System.out.println("Input your name!");
        String name1 = sc.next();
        System.out.println("Input your name!");
        String name2 = sc.next();
        MemoryMain mema = new MemoryMain(name1, name2);
        List<Player> playerList = mimp.getPlayer();
        Player user1 = playerList.get(0);
        Player user2 = playerList.get(1);
        System.out.println(user1.getUser() + ", pick your Playernumber! 1/2");
        mema.getInteger(user1, true);
        System.out.println(user2.getUser() + ", pick your Playernumber! 1/2");
        mema.getInteger(user2, true);
        System.out.println("Set the number of card-pairs you want to play with!");
        mema.getInteger(user1, false);
        int cnt = 1;
        boolean gameOn = true;
        while (gameOn) {
            try {
                if (cnt % 2 != 0) {
                    mema.playTheGame(user1, gamefield);
                } else {
                    mema.playTheGame(user2, gamefield);
                }
            } catch (GameOverException e) {
                System.out.println(e.getMessage());
                gameOn = false;
            }
            if (mimp.getTurn() != cnt) {
                cnt++;
            }
        }
    }

}
