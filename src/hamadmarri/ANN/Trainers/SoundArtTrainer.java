package hamadmarri.ANN.Trainers;

import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;

import hamadmarri.ANN.NeuralNetwork;

public class SoundArtTrainer extends Trainer {

	private String inputFilePath;



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
			pw.println(a + " Chn\n" + b + " Dhn\n" + c + " Ehn");

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

			double inputArray[] = new double[10];
			double outputArray[] = new double[4];

			for (int j = 0; j < input.length(); j++) {
				byte b = input.substring(j, j + 1).getBytes()[0];
				inputArray[j] = Double.parseDouble(Byte.toString(b))
						/ (double) 255;
			}

			if (output.substring(0, 1).equals("C"))
				outputArray[0] = (double) 36 / (double) 60;
			else if (output.substring(0, 1).equals("D"))
				outputArray[0] = (double) 37 / (double) 60;
			else if (output.substring(0, 1).equals("E"))
				outputArray[0] = (double) 38 / (double) 60;

			outputArray[1] = (double) 1 / (double) 8;
			outputArray[2] = 0;
			outputArray[3] = 1;

			this.neuralNetwork.setInputValues(inputArray);
			this.neuralNetwork.feedForward();
			this.neuralNetwork.backPropagate(outputArray);
			this.neuralNetwork.printResult(this.outputFile);
		}

		endTraining();
	}
}
