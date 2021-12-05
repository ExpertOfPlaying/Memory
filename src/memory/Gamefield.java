package memory;

import java.util.List;

public class Gamefield {

	public void createGamefield(List<Character> cards) {
		for (int i = 0; i < cards.size(); i++) {
			if (cards.size() == 4) {
				if (cards.get(i) == '?') {
					if (i % 2 == 0 && i != 0)
						System.out.println();
					System.out.print("[x]");
				} else {
					if (i % 2 == 0 && i != 0) {
						System.out.println();
					}
					System.out.print("[" + i + "]");
				}
			} else {
				if (cards.get(i) == '?') {
					System.out.print("[x]");
				} else {
					if (i % 4 == 0 && i != 0) {
						System.out.println();
					}
					System.out.print("[" + i + "]");
				}
			}
		}
		System.out.println();
	}

}
