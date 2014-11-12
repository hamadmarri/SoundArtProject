package hamadmarri.ANN.Trainers;

import hamadmarri.ANN.NeuralNetwork;

import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;

public class XorTrainer extends Trainer {

	private String inputFilePath;



	public XorTrainer(String inputFileName, String outputFileName, NeuralNetwork neuralNetwork, int numberOfPasses) {
		super(inputFileName, outputFileName, neuralNetwork, numberOfPasses);
		this.inputFilePath = inputFileName;
	}



	@Override
	public void generateTest() {
		int a, b, c;
		PrintWriter pw = null;

		try {
			pw = new PrintWriter(this.inputFilePath, "UTF-8");
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}

		for (int i = 0; i < this.numberOfPasses; i++) {
			a = (int) Math.round(Math.random());
			b = (int) Math.round(Math.random());
			c = a ^ b;

			pw.println(a + " " + b + " " + c);
		}

		pw.close();
	}



	@Override
	public void train() {
		int a, b, c;

		for (int i = 0; i < this.numberOfPasses; i++) {
			this.outputFile.println("\npass: " + (i + 1));

			a = this.inputFile.nextInt();
			b = this.inputFile.nextInt();
			c = this.inputFile.nextInt();

			this.neuralNetwork.setInputValues(new double[] { a, b });
			this.neuralNetwork.feedForward();
			this.neuralNetwork.backPropagate(new double[] { c, 1 });
			this.neuralNetwork.printResult(this.outputFile);
		}

		endTraining();
	}

}
