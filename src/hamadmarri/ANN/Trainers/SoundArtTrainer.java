package hamadmarri.ANN.Trainers;

import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;

import hamadmarri.ANN.NeuralNetwork;
import hamadmarri.soundArt.InputStructure;

public class SoundArtTrainer extends Trainer {

	private String inputFilePath;
	private InputStructure is = new InputStructure();



	public SoundArtTrainer(String inputFileName, String outputFileName,
			NeuralNetwork neuralNetwork, int numberOfPasses) {

		super(inputFileName, outputFileName, neuralNetwork, numberOfPasses);
		this.inputFilePath = inputFileName;
	}



	@Override
	public void generateTest() {
		String a = "Hamad";
		String b = "Salim";
		String c = "Almarri";
		PrintWriter pw = null;

		try {
			pw = new PrintWriter(this.inputFilePath, "UTF-8");
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}

		for (int i = 0; i < this.numberOfPasses; i++)
			pw.println(a + " Cmaj7w\n" + b + " Dmaj7w\n" + c + " Emaj7wn");

		pw.close();
	}



	@Override
	public void train() {
		String input;
		String output;

		for (int i = 0; i < this.numberOfPasses; i++) {
			this.outputFile.println("\npass: " + (i + 1));

			input = this.inputFile.next();
			output = this.inputFile.next();
			is.setAll(output);

			double inputArray[] = new double[10];
			double outputArray[] = new double[4];

			// intput text
			for (int j = 0; j < input.length(); j++) {
				byte b = input.substring(j, j + 1).getBytes()[0];
				inputArray[j] = Double.parseDouble(Byte.toString(b))
						/ (double) 255;
			}

			// expected key
			outputArray[0] = is.normalizeKey(is.getKey());

			// expected chord
			outputArray[1] = (double) 1 / (double) 8;

			// third expected output
			outputArray[2] = 0;

			// extra node for ANN
			outputArray[3] = 1;

			this.neuralNetwork.setInputValues(inputArray);
			this.neuralNetwork.feedForward();
			this.neuralNetwork.backPropagate(outputArray);
			this.neuralNetwork.printResult(this.outputFile);
		}

		endTraining();
	}
}
