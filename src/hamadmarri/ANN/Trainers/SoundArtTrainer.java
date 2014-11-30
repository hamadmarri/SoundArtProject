package hamadmarri.ANN.Trainers;

import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.Scanner;

import hamadmarri.ANN.NeuralNetwork;
import hamadmarri.soundArt.InputStructure;

public class SoundArtTrainer extends Trainer {

	private String inputFilePath;
	private InputStructure is = new InputStructure();
	private String trainingInput;



	public SoundArtTrainer(String inputFileName, String outputFileName,
			NeuralNetwork neuralNetwork, int numberOfPasses,
			boolean printOutput, boolean readFromInputfile) {

		super(inputFileName, outputFileName, neuralNetwork, numberOfPasses,
				printOutput, readFromInputfile);

		this.trainingInput = inputFileName;
		this.inputFilePath = inputFileName;
	}



	@Override
	public void generateTest() {
		String a = "Hamad";
		String b = "Salim";
		String c = "Almarri";
		String d = "perfect";
		PrintWriter pw = null;

		try {
			pw = new PrintWriter(this.inputFilePath, "UTF-8");
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}

		for (int i = 0; i < this.numberOfPasses; i++)
			pw.println(a + " C\n" + b + " D4w\n" + c + " A#7s\n" + d + " F#5q");

		pw.close();
	}



	@Override
	public void train() {
		String input;
		String expectedOutput;

		inputFile = new Scanner(trainingInput);

		for (int i = 0; i < this.numberOfPasses; i++) {
			input = this.inputFile.next();
			expectedOutput = this.inputFile.next();

			is.setAll(expectedOutput);

			double inputArray[] = new double[10];
			double outputArray[] = new double[5];

			// intput text
			for (int j = 0; j < input.length(); j++) {
				byte b = input.substring(j, j + 1).getBytes()[0];
				inputArray[j] = Double.parseDouble(Byte.toString(b))
						/ (double) 255;
			}

			// expected key
			outputArray[0] = is.normalizeKey(is.getKey());

			// expected chord
			outputArray[1] = is.normalizeChord(is.getChord());

			// expected octave
			outputArray[2] = is.normalizeOctave(is.getOctave());

			// expected duration
			outputArray[3] = is.normalizeDuration(is.getDuration());

			// extra node for ANN
			outputArray[4] = 1;

			this.neuralNetwork.setInputValues(inputArray);
			this.neuralNetwork.feedForward();
			this.neuralNetwork.backPropagate(outputArray);

			if (printOutput) {
				this.outputFile.println("\npass: " + (i + 1));
				this.neuralNetwork.printResult(this.outputFile);
			}

			if (!inputFile.hasNext())
				inputFile = new Scanner(trainingInput);
		}

		endTraining();
	}
}
