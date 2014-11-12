package hamadmarri.ANN;

import hamadmarri.ANN.layers.HiddenLayer;
import hamadmarri.ANN.layers.InputLayer;
import hamadmarri.ANN.layers.Layer;
import hamadmarri.ANN.layers.OutputLayer;

import java.io.PrintWriter;
import java.io.Serializable;

public class NeuralNetwork implements Serializable {

	private static final long serialVersionUID = 8273724046476369821L;
	private InputLayer inputLayer;
	private HiddenLayer[] hiddenLayers;
	private OutputLayer outputLayer;
	private double learningRate;
	private double momentum;



	public NeuralNetwork(int[] config, double learningRate, double momentum) {
		initializeInputLayer(config);
		initializeHidderLayers(config);
		initializeOutputLayer(config);

		this.learningRate = learningRate;
		this.momentum = momentum;
	}



	public void setInputValues(double values[]) {
		for (int i = 0; i < values.length; i++) {
			this.inputLayer.getNeurons()[i].setOutput(values[i]);
		}
	}



	public void feedForward() {

		// starting from hidden layers
		for (HiddenLayer hl : this.hiddenLayers)
			hl.feedForward();

		// and also the output layer
		this.outputLayer.feedForward();
	}



	public void printResult(PrintWriter pw) {
		for (int i = 0; i < this.outputLayer.getNeurons().length - 1; i++) {
			Neuron n = this.outputLayer.getNeurons()[i];
			pw.print("input: ");

			for (int j = 0; j < this.inputLayer.getNeurons().length - 1; j++) {
				Neuron en = this.inputLayer.getNeurons()[j];
				pw.print("(" + en.getOutput() + ")");
			}

			pw.println("\noutput: " + n.getOutput());
			pw.println("error: " + n.getError());
		}
	}



	public void backPropagate(double[] expectedValues) {
		this.outputLayer.backPropagate(expectedValues, this.learningRate, this.momentum);

		// Calculate error for all neurons in the hidden layers
		// (back propagate the errors
		for (HiddenLayer hl : this.hiddenLayers)
			hl.backPropagate(this.learningRate, this.momentum);

	}



	private void initializeOutputLayer(int[] config) {
		this.outputLayer = new OutputLayer(config[config.length - 1], this.hiddenLayers[hiddenLayers.length - 1]);
	}



	private void initializeHidderLayers(int[] config) {
		this.hiddenLayers = new HiddenLayer[config.length - 2];
		Layer prevLayer = this.inputLayer;

		for (int i = 0; i < this.hiddenLayers.length; i++)
			prevLayer = this.hiddenLayers[i] = new HiddenLayer(config[i + 1], prevLayer, config[i + 2]);
	}



	private void initializeInputLayer(int[] config) {
		this.inputLayer = new InputLayer(config[0], config[1]);
	}



	public InputLayer getInputLayer() {
		return inputLayer;
	}



	public HiddenLayer[] getHiddenLayers() {
		return hiddenLayers;
	}



	public OutputLayer getOutputLayer() {
		return outputLayer;
	}

}
