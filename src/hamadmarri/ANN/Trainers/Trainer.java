package hamadmarri.ANN.Trainers;

import hamadmarri.ANN.NeuralNetwork;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.Scanner;

public abstract class Trainer {
	protected Scanner inputFile;
	protected PrintWriter outputFile;
	protected NeuralNetwork neuralNetwork;
	protected int numberOfPasses = 2000;
	protected boolean readFromInputfile = false;
	protected boolean printOutput = false;



	public Trainer(String inputFileName, String outputFileName,
			NeuralNetwork neuralNetwork, int numberOfPasses) {
		this.neuralNetwork = neuralNetwork;
		initializeInputFile(inputFileName);
		initializeOutputFile(outputFileName);
		this.numberOfPasses = numberOfPasses;
	}



	public Trainer(String inputFileName, String outputFileName,
			NeuralNetwork neuralNetwork, int numberOfPasses, boolean printOutput, boolean readFromInputfile) {
		this.neuralNetwork = neuralNetwork;
		
		if (readFromInputfile)
			initializeInputFile(inputFileName);

		if (printOutput)
			initializeOutputFile(outputFileName);

		this.numberOfPasses = numberOfPasses;
		
		this.readFromInputfile = readFromInputfile;
		this.printOutput = printOutput;
	}



	private void initializeInputFile(String inputFileName) {
		File f = new File(inputFileName);
		if (!f.exists()) {
			try {
				f.createNewFile();
			} catch (IOException e1) {
				e1.printStackTrace();
			}
		}

		try {
			this.inputFile = new Scanner(new FileInputStream(inputFileName));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}



	private void initializeOutputFile(String outputFileName) {
		try {
			this.outputFile = new PrintWriter(outputFileName, "UTF-8");
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			return;
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
			return;
		}
	}



	public void endTraining() {
		this.inputFile.close();

		if (printOutput)
			this.outputFile.close();
	}



	public abstract void generateTest();



	public abstract void train();

}
