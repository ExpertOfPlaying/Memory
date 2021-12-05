package memory;

import java.util.List;
import java.util.Scanner;

public class MemoryMain {

	public static void main(String[] args) throws PlayerNumberTakenException, StateException, IllegalArgumentException,
			KeyOutOfBoundsException, CardAlreadyPickedException, GameOverException {
		Gamefield gamefield = new Gamefield();
		Scanner sc = new Scanner(System.in);
		System.out.println("Input your name!");
		String name1 = sc.next();
		System.out.println("Input your name!");
		String name2 = sc.next();
		MemoryImplementation mimp = new MemoryImplementation(name1, name2);
		List<Player> playerList = mimp.getPlayer();
		Player user1 = playerList.get(0);
		Player user2 = playerList.get(1);
		System.out.println(user1.getUser() + ", pick your Playernumber! 1/2");
		int chosenNumber;
		while (true) {
			try {
				chosenNumber = Integer.parseInt(sc.next());
			} catch (NumberFormatException e) {
				System.out.println("This value in invalid! Please enter either 1 or 2!");
				continue;
			}
			if (chosenNumber < 1 || chosenNumber > 2) {
				System.out.println("This value in invalid! Please enter either 1 or 2!");
			} else {
				mimp.choosePlayerNumber(user1, PlayerNumber.values()[chosenNumber - 1]);
				break;
			}
		}
		System.out.println(user2.getUser() + ", pick your Playernumber! 1/2");
		while (true) {
			try {
				chosenNumber = Integer.parseInt(sc.next());
			} catch (NumberFormatException e) {
				System.out.println("This value in invalid! Please enter either 1 or 2!");
				continue;
			}
			if (chosenNumber < 1 || chosenNumber > 2) {
				System.out.println("This value in invalid! Please enter either 1 or 2!");
			} else {
				mimp.choosePlayerNumber(user2, PlayerNumber.values()[chosenNumber - 1]);
				break;
			}
		}
		System.out.println("Set the number of card-pairs you want to play with!");
		mimp.setNumberOfCardPairs(sc.nextInt());
		int pickedCardNumber;
		while (true) {
			try {
				gamefield.createGamefield(mimp.getTotalCards());
				System.out.println(user1.getUser() + ", choose a card!");
				pickedCardNumber = sc.nextInt();
				mimp.pickCard(pickedCardNumber, user1);
				System.out.println(user1.getUser() + ", choose another card!");
				pickedCardNumber = sc.nextInt();
				mimp.pickCard(pickedCardNumber, user1);
				gamefield.createGamefield(mimp.getTotalCards());
				System.out.println(user2.getUser() + ", choose a card!");
				pickedCardNumber = sc.nextInt();
				mimp.pickCard(pickedCardNumber, user2);
				System.out.println(user2.getUser() + ", choose another card!");
				pickedCardNumber = sc.nextInt();
				mimp.pickCard(pickedCardNumber, user2);
			} catch (GameOverException e) {
				break;
			}
		}
		sc.close();
	}

}
