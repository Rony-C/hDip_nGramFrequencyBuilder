package ie.atu.sw;

public class MenuOptions {

	// ### show Comments ### //
	// Moved here from Menu class to save space
	// and to have dedicated space for menu options.
	// Gives the programmer a dedicated space to edit the menu options only.

	// Displays a menu option that corresponds to a switch statement in Menu class.
	public static void show() {
		System.out.println("************************************************************");
		System.out.println("*      ATU - Dept. Computer Science & Applied Physics     *");
		System.out.println("*                                                          *");
		System.out.println("*                  N-Gram Frequency Builder                *");
		System.out.println("*                                                          *");
		System.out.println("************************************************************");
		System.out.println("(1) Specify Text File Directory");
		System.out.println("(2) Specify n-Gram Size");
		System.out.println("(3) Set Output File Name");
		System.out.println("(4) Build n-Grams ");
		System.out.println("(5) Quit");
		System.out.print("Select an Option [1-5]>");
		System.out.println();
	}
}
