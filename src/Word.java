
public class Word implements Comparable<Word>
{
	// Fields
	private final String word;
	private int frequency;

	// Constructor
	public Word(String input)
	{
		word = input;
		frequency = 1;
	}

	// Methods
	public int getFrequency()
	{
		return frequency;
	}

	public int incrementFrequency()
	{
		return ++frequency;
	}

	public String getWord()
	{
		return word;
	}

	public int compareTo(Word otherWord)
	{
		return this.getFrequency() - otherWord.getFrequency();
	}
}
