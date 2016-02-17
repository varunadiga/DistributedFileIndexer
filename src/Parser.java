
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.Scanner;

public class Parser
{
	// Fields
	private static int pointer;
	private static Indexer index;

	// Constructor
	public Parser()
	{
		pointer = 0;
		index = new Indexer();
	}

	// Methods

	/**
	 * Parses through the files which are passed in as a parameter. Outputs
	 * a list of the top ten words found in all the files in ascending
	 * order (by frequency).
	 * 
	 * @param args
	 *            An array of filenames to be read
	 */
	public static void main(String[] args)
	{
		if (args.length == 0)
		{
			System.err.println(
					"Please pass at least one filename as an argument.");
			return;
		}
		String text = "";
		StringBuilder tempText = new StringBuilder();
		Parser parse = new Parser();

		// Copy all the text from the input files into the StringBuilder
		// tempText
		for (int i = 0; i < args.length; i++)
		{
			String inputFilename = args[i];
			File inputFile = new File(inputFilename);
			Scanner sc;
			try
			{
				sc = new Scanner(inputFile);
			}
			catch (FileNotFoundException e)
			{
				System.err.println("The input file cannot be found.");
				return;
			}
			while (sc.hasNextLine())
			{
				tempText.append(sc.nextLine() + "\n");
			}
			sc.close();
		}
		text = tempText.toString().toLowerCase();
		while (pointer < text.length())
		{
			parse.consume(text);
		}

		System.out.println("Top Ten Words (in ascending order):");

		// Print out the top ten list in ascending order
		Object[] topTen = index.getTopTen().toArray();
		Arrays.sort(topTen);
		for (Object word : topTen)
		{
			System.out.println(((Word) word).getWord() + " : "
					+ ((Word) word).getFrequency());
		}
		return;
	}

	/**
	 * Finds and adds the next word in text to the index. After finding a
	 * word, it consumes all non-alphanumeric characters, moving the
	 * pointer to the next alphanumeric character.
	 * 
	 * @param text
	 *            A string containing the text from all the files
	 */
	public void consume(String text)
	{
		StringBuilder tempWord = new StringBuilder();
		String word = "";
		while (pointer < text.length())
		{
			char letter = text.charAt(pointer);
			// Check to see if the character is a number or letter (ASCII
			// values)
			if ((letter > 96 && letter < 123)
					|| (letter > 47 && letter < 58))
			{
				tempWord.append(letter);
				pointer++;
			}
			else
			{
				// Consume the non-alphanumeric characters
				do
				{
					pointer++;
					if (pointer >= text.length())
					{
						break;
					}
					letter = text.charAt(pointer);
				}
				while (!(letter > 96 && letter < 123)
						&& !(letter > 47 && letter < 58));
				break;
			}
		}
		word = tempWord.toString();
		if (word.length() > 0)
		{
			index.addWord(word);
		}
		return;
	}

	/**
	 * 
	 * @return Returns pointer (used only for tests)
	 */
	public int getPointer()
	{
		return pointer;
	}
}
