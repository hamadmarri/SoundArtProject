package hamadmarri.testing;

import static org.junit.Assert.assertTrue;
import hamadmarri.ANN.NeuralNetwork;
import hamadmarri.ANN.Trainers.SoundArtTrainer;
import hamadmarri.ANN.Trainers.Trainer;
import hamadmarri.soundArt.InputStructure;

import org.junit.BeforeClass;
import org.junit.Test;

public class ANN_ForSoundTest {

	static NeuralNetwork nw;
	InputStructure is = new InputStructure();



	@BeforeClass
	public static void test() {
		String inputPath = "./input.txt";
		String outputPath = "./output.txt";

		int[] config = new int[] { 10, 7, 4 };
		nw = new NeuralNetwork(config, 0.79, 0.15);

		Trainer trainer = new SoundArtTrainer(inputPath, outputPath, nw,
				100000, false);

		train(trainer);
	}



	private static void train(Trainer trainer) {
		long startTime;
		long endTime;

		trainer.generateTest();
		startTime = System.currentTimeMillis();
		trainer.train();
		endTime = System.currentTimeMillis();
		System.out.println("took: " + (endTime - startTime) + "ms");
	}



	@Test
	public void HamadTest() {
		String input = "Hamad";
		double inputArray[] = new double[10];
		double r[];

		// intput text
		for (int j = 0; j < input.length(); j++) {
			byte b = input.substring(j, j + 1).getBytes()[0];
			inputArray[j] = Double.parseDouble(Byte.toString(b)) / (double) 255;
		}

		nw.setInputValues(inputArray);
		nw.feedForward();
		r = nw.getResults();

		assertTrue(is.denormalizeKey(r[0]) + " " + r[0], is
				.denormalizeKey(r[0]).equals("C"));

		assertTrue(is.denormalizeChord(r[1]) + " " + r[1],
				is.denormalizeChord(r[1]).equals("n"));

		assertTrue(is.denormalizeOctave(r[2]) + " " + r[2], is
				.denormalizeOctave(r[2]).equals("3"));

		assertTrue(is.denormalizeDuration(r[3]) + " " + r[3], is
				.denormalizeDuration(r[3]).equals("q"));
	}



	@Test
	public void SalimTest() {
		String input = "Salim";
		double inputArray[] = new double[10];
		double r[];

		// intput text
		for (int j = 0; j < input.length(); j++) {
			byte b = input.substring(j, j + 1).getBytes()[0];
			inputArray[j] = Double.parseDouble(Byte.toString(b)) / (double) 255;
		}

		nw.setInputValues(inputArray);
		nw.feedForward();
		r = nw.getResults();

		assertTrue(is.denormalizeKey(r[0]) + " " + r[0], is
				.denormalizeKey(r[0]).equals("D"));
		
		assertTrue(is.denormalizeChord(r[1]) + " " + r[1],
				is.denormalizeChord(r[1]).equals("n"));

		assertTrue(is.denormalizeOctave(r[2]) + " " + r[2], is
				.denormalizeOctave(r[2]).equals("4"));

		assertTrue(is.denormalizeDuration(r[3]) + " " + r[3], is
				.denormalizeDuration(r[3]).equals("w"));
	}



	@Test
	public void AlmarriTest() {
		String input = "Almarri";
		double inputArray[] = new double[10];
		double r[];

		// intput text
		for (int j = 0; j < input.length(); j++) {
			byte b = input.substring(j, j + 1).getBytes()[0];
			inputArray[j] = Double.parseDouble(Byte.toString(b)) / (double) 255;
		}

		nw.setInputValues(inputArray);
		nw.feedForward();
		r = nw.getResults();

		assertTrue(is.denormalizeKey(r[0]) + " " + r[0], is
				.denormalizeKey(r[0]).equals("A#"));

		assertTrue(is.denormalizeChord(r[1]) + " " + r[1],
				is.denormalizeChord(r[1]).equals("n"));

		assertTrue(is.denormalizeOctave(r[2]) + " " + r[2], is
				.denormalizeOctave(r[2]).equals("7"));

		assertTrue(is.denormalizeDuration(r[3]) + " " + r[3], is
				.denormalizeDuration(r[3]).equals("s"));
	}



	@Test
	public void perfectTest() {
		String input = "perfect";
		double inputArray[] = new double[10];
		double r[];

		// intput text
		for (int j = 0; j < input.length(); j++) {
			byte b = input.substring(j, j + 1).getBytes()[0];
			inputArray[j] = Double.parseDouble(Byte.toString(b)) / (double) 255;
		}

		nw.setInputValues(inputArray);
		nw.feedForward();
		r = nw.getResults();

		assertTrue(is.denormalizeKey(r[0]) + " " + r[0], is
				.denormalizeKey(r[0]).equals("F#"));

		assertTrue(is.denormalizeChord(r[1]) + " " + r[1],
				is.denormalizeChord(r[1]).equals("n"));

		assertTrue(is.denormalizeOctave(r[2]) + " " + r[2], is
				.denormalizeOctave(r[2]).equals("5"));

		assertTrue(is.denormalizeDuration(r[3]) + " " + r[3], is
				.denormalizeDuration(r[3]).equals("q"));
	}
}
