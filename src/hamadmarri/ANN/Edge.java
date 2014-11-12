package hamadmarri.ANN;

import java.io.Serializable;

public class Edge implements Serializable {

	private static final long serialVersionUID = 7036430790105246433L;
	private Neuron leftNeuron;
	private Neuron rightNeuron;
	private double weight;
	private double DeltaWeight;



	public Edge(Neuron leftNode, Neuron rightNode) {
		this.leftNeuron = leftNode;
		this.rightNeuron = rightNode;

		// Initialise weight with a random
		// number between -1 and 1
		this.weight = -1 + (2 * Math.random());
	}



	public Neuron getLeftNeuron() {
		return leftNeuron;
	}



	public void updateWeight(double learningRate, double momentum) {

		// update delta weight
		this.DeltaWeight = learningRate * rightNeuron.getError() * leftNeuron.getOutput()
				+ (momentum * this.DeltaWeight);

		// update weight
		this.weight += this.DeltaWeight;
	}



	public void setLeftNeuron(Neuron leftNode) {
		this.leftNeuron = leftNode;
	}



	public Neuron getRightNeuron() {
		return rightNeuron;
	}



	public void setRightNeuron(Neuron rightNode) {
		this.rightNeuron = rightNode;
	}



	public double getWeight() {
		return weight;
	}



	public void setWeight(double weight) {
		this.weight = weight;
	}



	public double getDeltaWeight() {
		return DeltaWeight;
	}



	public void setDeltaWeight(double deltaWeight) {
		DeltaWeight = deltaWeight;
	}
}
