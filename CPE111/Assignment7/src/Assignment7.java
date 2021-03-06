import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.Scanner;

public class Assignment7 {

	static Dictionary dictionary;

	public static void main(String[] args) {
		dictionary = new Dictionary();
		
		// Read file
		ReadFile("utf8lexitron.csv");
		
		// Sort
		dictionary.Sort();
		System.out.println("Total: " + dictionary.list.size());
		// Remove duplicate
		dictionary.RemoveDuplicate();
		System.out.println("Duplicate: " + dictionary.duplicateCount);
		System.out.println("Remaining: " + dictionary.list.size());
		// Find maximum word
		dictionary.FindMaximumWord();
		System.out.println("Maximumn meaning word " + dictionary.Get(dictionary.indexMaxWord).word + " have " + dictionary.maxWordCount + " meaning.");
		dictionary.PrintMaxWordList();		
		
		// Scanner
		Scanner sc = new Scanner(System.in);
		
		String input;
		boolean endProgram = false;
		boolean command;
		do
		{
			command = false;
			// Recieve input
			System.out.print("Enter word: ");
			input = sc.nextLine();
			// Check input is not end program command
			endProgram = (input.trim().compareToIgnoreCase("/end") == 0)? true : false;
			if (!endProgram || !command)
			{
				// Set start and end index to null
				int start[] = {-1};
				int end [] = {-1};
				// Search
				dictionary.SearchWord(input, start, end);
				if (start[0] >= 0) // Found word
				{
					System.out.println("found " + dictionary.list.get(start[0]).word + " " + (end[0] - start[0] + 1) +  " word at " + start[0] + " - " + end[0]);
					dictionary.Print(start[0], end[0]);
				}
				else // Not found
				{
					System.out.println(input + " not found");
				}
			}
		} while (endProgram == false);
		sc.close();
		System.out.println("End Program");
	}
	
	public static void ReadFile (String filePath)
	{
		FileInputStream in = null;
		try {
			in = new FileInputStream(filePath);
		} catch (Exception e) {
		 
		}
		InputStreamReader fr = null;
		try {
			fr = new InputStreamReader(in, "UTF-8");
		} catch (Exception e){
			
		}
		if (in != null && fr != null) {
			Scanner sc = new Scanner(fr);
			// Read each line
			while (sc.hasNext()) {
				// Trim, remove uFEFF and remove space each line before add to dictionary
				dictionary.Add(new DNode(sc.nextLine().trim().replace("\uFEFF","").replaceAll("\\s+", " ")));
			}
			sc.close();
		}
	}

}
