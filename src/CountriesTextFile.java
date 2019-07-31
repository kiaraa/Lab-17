import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;

public class CountriesTextFile {

	public void makeFolder() {
		String dirPath = "resources";
		
		Path folder = Paths.get(dirPath);
		
		if(Files.notExists(folder)) {
			
			try {
				Files.createDirectories(folder);
				System.out.println("The file was created succesfully");
			} catch (IOException e) {
				System.out.println("Something went wrong with the folder creation.");
				//e.printStackTrace();
			}
		}
		else {
			System.out.println("The folder already exists.");
		}
	}
	
	public void createFile() {
		String fileName = "countries.txt";
		Path path = Paths.get("resources", fileName);
		
		if(Files.notExists(path)) {
			try {
				Files.createFile(path);
				System.out.println("The file was created successfully.");
			} catch (IOException e) {
				System.out.println("Oops, something went terribly wrong!");
				//e.printStackTrace();
			}
		}
		else {
			System.out.println("The file already exists.");
		}
	}
	
	public void writeToFile(Country newCountry) {
		String fileName = "countries.txt";
		Path path = Paths.get("resources", fileName);
		
		File file = path.toFile();
		PrintWriter output = null;
		
		try {
			output = new PrintWriter(new FileOutputStream(file, true));
			output.println(newCountry);
			
		} catch (FileNotFoundException e) {
			System.out.println("Hey, contact customer service!");
		} finally {
			output.close();
		}
	}
	
	public ArrayList<Country> getCountryList(){
		ArrayList<Country> countryList = new ArrayList<Country>();
		
		String fileName = "countries.txt";
		Path path = Paths.get("resources", fileName);
		
		File file = path.toFile();
		
		try {
			BufferedReader br = new BufferedReader(new FileReader(file));
			
			String countryString = br.readLine();
			
			while (countryString != null) {
				String [] countryInfo = countryString.split(",");
				int population = Integer.parseInt(countryInfo[1]);
				Country country = new Country(countryInfo[0], population);
				countryList.add(country);
				
				countryString = br.readLine();
			}
			br.close();
			return countryList;
			
		} catch (FileNotFoundException e) {
			System.out.println("Something happened with the file...");
		} catch (IOException e) {
			System.out.println("Something happened when attempting to read from the file...");
		}
		return null;
	}
	
	public void printCountryList(ArrayList<Country> countryList) {
		for (Country country : countryList) {
			System.out.println(country);
		}
	}
}
