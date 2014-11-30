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
	public void noChord1() {
		String testInput = "C7w";
		InputStructure is = new InputStructure(testInput);

		assertTrue(is.getKey(), is.getKey().equals("C"));
		assertTrue(is.getChord(), is.getChord().equals("n"));
		assertTrue(is.getOctave(), is.getOctave().equals("7"));
		assertTrue(is.getDuration(), is.getDuration().equals("w"));
	}



	@Test
	public void noChord2() {
		String testInput = "F#5q";
		InputStructure is = new InputStructure(testInput);

		assertTrue(is.getKey(), is.getKey().equals("F#"));
		assertTrue(is.getChord(), is.getChord().equals("n"));
		assertTrue(is.getOctave(), is.getOctave().equals("5"));
		assertTrue(is.getDuration(), is.getDuration().equals("q"));
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



	@Test
	public void normalizeOctave4() {
		String testInput = "4";
		double delta = 1e-5d;
		double expected = 1d / 4d;
		InputStructure is = new InputStructure();

		assertEquals(expected, is.normalizeOctave(testInput), delta);
	}



	@Test
	public void normalizeDurationW() {
		String testInput = "w";
		double delta = 1e-5d;
		double expected = 0d / 7d;
		InputStructure is = new InputStructure();

		assertEquals(expected, is.normalizeDuration(testInput), delta);
	}



	@Test
	public void normalizeDurationH() {
		String testInput = "h";
		double delta = 1e-5d;
		double expected = 1d / 7d;
		InputStructure is = new InputStructure();

		assertEquals(expected, is.normalizeDuration(testInput), delta);
	}



	@Test
	public void denormalizeKeyCSharp() {
		double testInput = 1d / 11d;
		String expected = "C#";
		InputStructure is = new InputStructure();

		assertTrue(is.denormalizeKey(testInput), is.denormalizeKey(testInput)
				.equals(expected));
	}



	@Test
	public void denormalizeKeyASharp() {
		double testInput = 10d / 11d;
		String expected = "A#";
		InputStructure is = new InputStructure();

		assertTrue(is.denormalizeKey(testInput), is.denormalizeKey(testInput)
				.equals(expected));
	}



	@Test
	public void denormalizeKeyG() {
		double testInput = 7d / 11d;
		String expected = "G";
		InputStructure is = new InputStructure();

		assertTrue(is.denormalizeKey(testInput), is.denormalizeKey(testInput)
				.equals(expected));
	}



	@Test
	public void denormalizeChord1() {
		double testInput = 0.03323756378673251d;
		String expected = "maj";
		InputStructure is = new InputStructure();

		assertTrue(is.denormalizeChord(testInput),
				is.denormalizeChord(testInput).equals(expected));
	}



	@Test
	public void denormalizeChordDom7Greater5() {
		double testInput = 23d / 30d;
		String expected = "dom7>5";
		InputStructure is = new InputStructure();

		assertTrue(is.denormalizeChord(testInput),
				is.denormalizeChord(testInput).equals(expected));
	}



	@Test
	public void denormalizeOctave6() {
		double testInput = 3d / 4d;
		String expected = "6";
		InputStructure is = new InputStructure();

		assertTrue(is.denormalizeOctave(testInput), is
				.denormalizeOctave(testInput).equals(expected));
	}



	@Test
	public void denormalizeOctave5() {
		double testInput = 2d / 4d;
		String expected = "5";
		InputStructure is = new InputStructure();

		assertTrue(is.denormalizeOctave(testInput), is
				.denormalizeOctave(testInput).equals(expected));
	}



	@Test
	public void denormalizeDurationS() {
		double testInput = 4d / 7d;
		String expected = "s";
		InputStructure is = new InputStructure();

		assertTrue(is.denormalizeDuration(testInput),
				is.denormalizeDuration(testInput).equals(expected));
	}

}
