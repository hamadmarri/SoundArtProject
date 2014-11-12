package hamadmarri.ANN.layers;

import hamadmarri.ANN.Edge;
import hamadmarri.ANN.Neuron;

public class HiddenLayer extends Layer {

	private static final long serialVersionUID = -3223830649940262543L;
	private Layer previousLayer;
	private int numberOfOutputEdges;



	public HiddenLayer(int numberOfNodes, Layer previousLayer, int numberOfOutputEdges) {
		super(numberOfNodes);
		this.numberOfOutputEdges = numberOfOutputEdges + 1; // bais
		this.previousLayer = previousLayer;

		for (Neuron n : this.neurons)
			n.setOutputEdges(new Edge[this.numberOfOutputEdges]);

		Layer.connectTwoLayers(this.previousLayer, this);
	}



	public void backPropagate(double learningRate, double momentum) {
		double sum = 0;

		// Calculate all output error, update threshold, and update weights
		for (Neuron n : this.neurons) {
			sum = 0;
			for (Edge oe : n.getOutputEdges()) {
				sum += oe.getWeight() * oe.getRightNeuron().getError();
			}

			// update error
			n.setError(sum * n.getOutput() * (1 - n.getOutput()));

			// update delta threshold
			n.updateThreshold(learningRate, momentum);

			// update input edges weights
			for (Edge e : n.getInputEdges())
				e.updateWeight(learningRate, momentum);
		}
	}

}
