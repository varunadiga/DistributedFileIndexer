import java.util.PriorityQueue;

import org.junit.Assert;
import org.junit.Test;

public class DFITest
{
	@Test
	public void addWordGeneralTest()
	{
		Indexer index = new Indexer();
		for (int i = 0; i < 50; i++)
		{
			index.addWord("myself");
		}
		index.addWord("yourself");

		PriorityQueue<Word> topTen = index.getTopTen();
		Assert.assertEquals(1, topTen.poll().getFrequency());
		Assert.assertEquals(50, topTen.poll().getFrequency());
	}

	@Test
	public void consumeGeneralTest()
	{
		Parser parse = new Parser();
		String text = "foo!! _#$ bar";
		parse.consume(text);
		Assert.assertEquals(10, parse.getPointer());
	}
}
