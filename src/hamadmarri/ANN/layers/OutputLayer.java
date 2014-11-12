package hamadmarri.ANN.layers;

import hamadmarri.ANN.Edge;
import hamadmarri.ANN.Neuron;

public class OutputLayer extends Layer {

	private static final long serialVersionUID = -5257498365706472635L;
	private Layer previousLayer;



	public OutputLayer(int numberOfNodes, Layer previousLayer) {
		super(numberOfNodes);
		this.previousLayer = previousLayer;

		Layer.connectTwoLayers(this.previousLayer, this);
	}



	public void backPropagate(double[] expectedOutputs, double learningRate, double momentum) {
		Neuron outputNeuron;

		// Calculate all output error, update threshold, and update weights
		for (int i = 0; i < this.numberOfNeurons; i++) {
			outputNeuron = this.neurons[i];

			// update error
			outputNeuron.setError(OutputLayer.derivativeOfSigmoid(expectedOutputs[i], outputNeuron.getOutput()));

			// update delta threshold
			outputNeuron.updateThreshold(learningRate, momentum);

			// update input edges weights
			for (Edge e : outputNeuron.getInputEdges())
				e.updateWeight(learningRate, momentum);

		}

	}



	private static double derivativeOfSigmoid(double expectedOutput, double output) {
		return (expectedOutput - output) * output * (1 - output);
	}

}
