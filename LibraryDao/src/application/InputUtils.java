package application;

import java.util.Scanner;

import model.entities.User;

public class InputUtils {

	public static Scanner sc = new Scanner(System.in);

	public static int readInt(String msg) {
		System.out.print(msg);
		int value = sc.nextInt();
		sc.nextLine();
		return value;

	}

	public static String readLine(String msg) {
		System.out.print(msg);
		return sc.nextLine();
	}

	public static void pause() {
		System.out.println("\nPress enter to continue...");
		sc.nextLine();
	}

	public static boolean readConfirmation() {
		char enter;
		do {
			System.out.println("Enter the option: (Y)DELETE  (N)CANCEL");
			enter = sc.nextLine().toUpperCase().charAt(0);

			if (enter != 'Y' && enter != 'N') {
				System.out.println("Invalid entered! Try again");
			}

		}

		while (enter != 'Y' && enter != 'N');

		if (enter == 'Y') {
			return true;
		}
		return false;
	}
}
