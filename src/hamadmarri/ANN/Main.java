package hamadmarri.ANN;

import hamadmarri.ANN.Trainers.AndTrainer;
import hamadmarri.ANN.Trainers.SoundArtTrainer;
import hamadmarri.ANN.Trainers.Trainer;
import hamadmarri.ANN.Trainers.XorTrainer;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class Main {

	public static void main(String[] args) {
		soundArtTest();
	}



	private static void andTest() {

		String inputPath = "./input.txt";
		String outputPath = "./output.txt";
		String serPath = "./ANN_AND.ser";
		// int[] config = new int[] { 2, 2, 1 };
		// NeuralNetwork nw = new NeuralNetwork(config, 0.02, 0.15);

		NeuralNetwork nw = (NeuralNetwork) deserialize(serPath);
		Trainer trainer = new AndTrainer(inputPath, outputPath, nw, 10000);

		train(trainer);

		serialize(serPath, nw);
	}



	private static void xorTest() {

		String inputPath = "./input.txt";
		String outputPath = "./output.txt";
		String serPath = "./ANN_XOR.ser";
		int[] config = new int[] { 2, 4, 1 };
		NeuralNetwork nw = new NeuralNetwork(config, 0.02, 0.15);
		// NeuralNetwork nw = (NeuralNetwork) deserialize(serPath);

		Trainer trainer = new XorTrainer(inputPath, outputPath, nw, 10000);

		train(trainer);

		serialize(serPath, nw);
	}



	private static void soundArtTest() {

		String inputPath = "./input.txt";
		String outputPath = "./output.txt";
		 String serPath = "./ANN_SOUND_ART.ser";
		// int[] config = new int[] { 10, 15, 3 };
		// NeuralNetwork nw = new NeuralNetwork(config, 0.02, 0.15);
		// NeuralNetwork nw = new NeuralNetwork(config, 0.72, 0.25);
		NeuralNetwork nw = (NeuralNetwork) deserialize(serPath);

		Trainer trainer = new SoundArtTrainer(inputPath, outputPath, nw, 100000);

		train(trainer);

		serialize(serPath, nw);
	}



	private static void train(Trainer trainer) {
		long startTime;
		long endTime;

//		trainer.generateTest();
		startTime = System.currentTimeMillis();
		trainer.train();
		endTime = System.currentTimeMillis();
		System.out.println("took: " + (endTime - startTime) + "ms");
	}



	private static void serialize(String path, NeuralNetwork nw) {
		ObjectOutputStream oos = null;

		try {
			oos = new ObjectOutputStream(new FileOutputStream(path));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

		try {
			oos.writeObject(nw);
		} catch (IOException e) {
			e.printStackTrace();
		}

	}



	private static Object deserialize(String path) {
		ObjectInputStream oos = null;

		try {
			oos = new ObjectInputStream(new FileInputStream(path));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

		try {
			return oos.readObject();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}

		return null;
	}
}
