package hamadmarri.ANN.layers;

import hamadmarri.ANN.Edge;
import hamadmarri.ANN.Neuron;

public class InputLayer extends Layer {

	private static final long serialVersionUID = -4116376177027912450L;
	private int numberOfOutputEdges;



	public InputLayer(int numberOfNodes, int numberOfOutputEdges) {
		super(numberOfNodes);
		this.numberOfOutputEdges = numberOfOutputEdges + 1; // bias

		for (Neuron n : this.neurons)
			n.setOutputEdges(new Edge[this.numberOfOutputEdges]);

	}



	@Override
	public void feedForward() {
		// just do nothing since input layer
		// doesn't have to feed from previous layer
	}

}
