import java.util.Scanner;

public class Lab17App {

	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		
		CountriesTextFile fileHandler = new CountriesTextFile();
		fileHandler.makeFolder();
		fileHandler.createFile();
		boolean keepGoing = true;
		
		while (keepGoing) {
			int choice = handleMenu(scan);
			keepGoing = handleSelection(choice, scan, fileHandler);
		}
	}
	
	public static int handleMenu(Scanner scan) {
		System.out.println("Welcome to the country list. Please choose an option below:");
		System.out.println("1. Display country list");
		System.out.println("2. Enter a new country");
		System.out.println("3. Quit");
		return Validator.getInt(scan, "What would you like to do? ", 1, 3);
	}
	
	public static boolean handleSelection(int selection, Scanner scan, CountriesTextFile fileHandler) {
		switch(selection) {
		case 1:		fileHandler.printCountryList(fileHandler.getCountryList());
					return true;
					
		case 2:		addCountry(scan, fileHandler);
					return true;
					
		default:	return false;
		}
	}
	
	public static void addCountry(Scanner Scan, CountriesTextFile fileHandler) {
		String countryName = Validator.getString(Scan, "What country would you like to add? ");
		int countryPopulation = Validator.getInt(Scan, "What is the population? ");
		
		Country newCountry = new Country(countryName, countryPopulation);
		fileHandler.writeToFile(newCountry);
	}
}
