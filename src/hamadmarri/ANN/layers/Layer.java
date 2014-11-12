package hamadmarri.ANN.layers;

import hamadmarri.ANN.Edge;
import hamadmarri.ANN.Neuron;

import java.io.Serializable;

public class Layer implements Serializable {

	private static final long serialVersionUID = -6231886071576530407L;
	protected int numberOfNeurons = 0;
	protected Neuron neurons[];



	public Layer(int numberOfNeurons) {
		// + 1 is for bias neuron
		this.numberOfNeurons = numberOfNeurons + 1;
		this.neurons = new Neuron[this.numberOfNeurons];

		// initialize nodes
		for (int i = 0; i < this.neurons.length; i++)
			this.neurons[i] = new Neuron();

		// make the bias neuron's output = 1
		this.neurons[this.numberOfNeurons - 1].setOutput(1);

	}



	public void feedForward() {
		// - 1 so bias neuron doesn't need to calculate its output
		for (int i = 0; i < this.neurons.length - 1; i++)
			this.neurons[i].feedForward();

	}



	public static void connectTwoLayers(Layer previous, Layer next) {
		// set previous layer's nodes to connect to next
		// layer's nodes
		for (Neuron pn : previous.neurons) {
			Edge[] edgesPerNode = pn.getOutputEdges();
			for (int j = 0; j < edgesPerNode.length; j++) {
				edgesPerNode[j].setRightNeuron(next.neurons[j]);
			}
		}

		// set next layer's nodes to connct to
		// previous layer's nodes
		for (int i = 0; i < next.numberOfNeurons; i++) {
			Edge[] inputEdges = new Edge[previous.neurons.length];
			for (int j = 0; j < inputEdges.length; j++) {
				inputEdges[j] = previous.neurons[j].getOutputEdges()[i];
			}
			next.neurons[i].setInputEdges(inputEdges);
		}

	}



	public Neuron[] getNeurons() {
		return neurons;
	}

}
