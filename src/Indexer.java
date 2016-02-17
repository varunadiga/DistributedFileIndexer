
import java.util.HashMap;
import java.util.PriorityQueue;

public class Indexer
{
	// Fields
	private HashMap<String, Word> allWords;
	private PriorityQueue<Word> topTenWords;

	// Constructor
	public Indexer()
	{
		allWords = new HashMap<String, Word>();
		topTenWords = new PriorityQueue<Word>(10);
	}

	// Methods
	/**
	 * Adds a Word to allWords if it isn't already in the HashMap.
	 * Increments the frequency of the Word if it already exists in
	 * allWords. Adds the Word to topTenWords if its frequency is high
	 * enough.
	 * 
	 * @param currentWord
	 *            The String to be added to the index
	 */
	public void addWord(String currentWord)
	{
		if (allWords.containsKey(currentWord))
		{
			allWords.get(currentWord).incrementFrequency();
			// Remove and add the Word from topTenWords to rebalance the
			// PriorityQueue if topTenWords already contains the Word
			if (topTenWords.contains(allWords.get(currentWord)))
			{
				topTenWords.remove(allWords.get(currentWord));
				topTenWords.add(allWords.get(currentWord));
			}
			else
			{
				if (allWords.get(currentWord)
						.compareTo(topTenWords.peek()) > 0)
				{
					topTenWords.poll();
					topTenWords.add(allWords.get(currentWord));
				}
			}
		}
		else
		{
			Word newWord = new Word(currentWord);
			allWords.put(currentWord, newWord);
			if (topTenWords.size() < 10)
			{
				topTenWords.add(newWord);
			}
		}
	}

	public PriorityQueue<Word> getTopTen()
	{
		return topTenWords;
	}
}
