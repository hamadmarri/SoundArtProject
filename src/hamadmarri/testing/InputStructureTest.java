package hamadmarri.testing;

import static org.junit.Assert.*;
import hamadmarri.soundArt.InputStructure;

import org.junit.Test;

public class InputStructureTest {

	@Test
	public void normal() {
		String testInput = "Cmaj7w";
		InputStructure is = new InputStructure(testInput);

		assertTrue(is.getKey(), is.getKey().equals("C"));
		assertTrue(is.getChord(), is.getChord().equals("maj"));
		assertTrue(is.getOctave(), is.getOctave().equals("7"));
		assertTrue(is.getDuration(), is.getDuration().equals("w"));
	}



	@Test
	public void noChord() {
		String testInput = "C7w";
		InputStructure is = new InputStructure(testInput);

		assertTrue(is.getKey(), is.getKey().equals("C"));
		assertTrue(is.getChord(), is.getChord().equals("n"));
		assertTrue(is.getOctave(), is.getOctave().equals("7"));
		assertTrue(is.getDuration(), is.getDuration().equals("w"));
	}



	@Test
	public void noOctave() {
		String testInput = "Cmajw";
		InputStructure is = new InputStructure(testInput);

		assertTrue(is.getKey(), is.getKey().equals("C"));
		assertTrue(is.getChord(), is.getChord().equals("maj"));
		assertTrue(is.getOctave(), is.getOctave().equals("3"));
		assertTrue(is.getDuration(), is.getDuration().equals("w"));
	}



	@Test
	public void noDuration() {
		String testInput = "Cmaj7";
		InputStructure is = new InputStructure(testInput);

		assertTrue(is.getKey(), is.getKey().equals("C"));
		assertTrue(is.getChord(), is.getChord().equals("maj"));
		assertTrue(is.getOctave(), is.getOctave().equals("7"));
		assertTrue(is.getDuration(), is.getDuration().equals("q"));
	}



	@Test
	public void normalWithSharp() {
		String testInput = "C#maj7w";
		InputStructure is = new InputStructure(testInput);

		assertTrue(is.getKey(), is.getKey().equals("C#"));
		assertTrue(is.getChord(), is.getChord().equals("maj"));
		assertTrue(is.getOctave(), is.getOctave().equals("7"));
		assertTrue(is.getDuration(), is.getDuration().equals("w"));
	}



	@Test
	public void noDurationNorOctave() {
		String testInput = "Cmaj";
		InputStructure is = new InputStructure(testInput);

		assertTrue(is.getKey(), is.getKey().equals("C"));
		assertTrue(is.getChord(), is.getChord().equals("maj"));
		assertTrue(is.getOctave(), is.getOctave().equals("3"));
		assertTrue(is.getDuration(), is.getDuration().equals("q"));
	}



	@Test
	public void noDurationNorChord() {
		String testInput = "C7";
		InputStructure is = new InputStructure(testInput);

		assertTrue(is.getKey(), is.getKey().equals("C"));
		assertTrue(is.getChord(), is.getChord().equals("n"));
		assertTrue(is.getOctave(), is.getOctave().equals("7"));
		assertTrue(is.getDuration(), is.getDuration().equals("q"));
	}



	@Test
	public void noOctaveNorChord() {
		String testInput = "C#h";
		InputStructure is = new InputStructure(testInput);

		assertTrue(is.getKey(), is.getKey().equals("C#"));
		assertTrue(is.getChord(), is.getChord().equals("n"));
		assertTrue(is.getOctave(), is.getOctave().equals("3"));
		assertTrue(is.getDuration(), is.getDuration().equals("h"));
	}



	@Test
	public void randomTest1() {
		String testInput = "F#dom7<5>96";
		InputStructure is = new InputStructure(testInput);

		assertTrue(is.getKey(), is.getKey().equals("F#"));
		assertTrue(is.getChord(), is.getChord().equals("dom7<5>9"));
		assertTrue(is.getOctave(), is.getOctave().equals("6"));
		assertTrue(is.getDuration(), is.getDuration().equals("q"));
	}



	@Test
	public void normalizeKeyC() {
		String testInput = "C";
		double delta = 1e-5d;
		double expected = 0d / 11d;
		InputStructure is = new InputStructure();
		// System.out.println(is.normalizeKey(testInput));
		// System.out.println(delta);

		assertEquals(expected, is.normalizeKey(testInput), delta);
	}



	@Test
	public void normalizeKeyCSharp() {
		String testInput = "C#";
		double delta = 1e-5d;
		double expected = 1d / 11d;
		InputStructure is = new InputStructure();

		assertEquals(expected, is.normalizeKey(testInput), delta);
	}



	@Test
	public void normalizeKeyFSharp() {
		String testInput = "F#";
		double delta = 1e-5d;
		double expected = 6d / 11d;
		InputStructure is = new InputStructure();

		assertEquals(expected, is.normalizeKey(testInput), delta);
	}
	
	
	@Test
	public void normalizeKeyB() {
		String testInput = "B";
		double delta = 1e-5d;
		double expected = 11d / 11d;
		InputStructure is = new InputStructure();

		assertEquals(expected, is.normalizeKey(testInput), delta);
	}
	
	
	@Test
	public void normalizeChordSus2() {
		String testInput = "sus2";
		double delta = 1e-5d;
		double expected = 9d / 30d;
		InputStructure is = new InputStructure();

		assertEquals(expected, is.normalizeChord(testInput), delta);
	}

}
