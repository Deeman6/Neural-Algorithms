/*
 * SOM Example using the IRIS data set for training.
 * Encog java libraries used.
 * https://github.com/encog
 * 
 * 
 * getnpk@gmail.com
 * 
 */

package com.npk.neural.unsupervised;

import java.util.HashMap;
import java.util.Map;

import org.encog.Encog;
import org.encog.ml.data.MLData;
import org.encog.ml.data.MLDataSet;
import org.encog.ml.data.basic.BasicMLData;
import org.encog.ml.data.basic.BasicMLDataSet;
import org.encog.neural.som.SOM;
import org.encog.neural.som.training.basic.BasicTrainSOM;
import org.encog.neural.som.training.basic.neighborhood.NeighborhoodSingle;


import com.npk.neural.data.IrisData;

/**
 * Implement a simple SOM using Iris data set. It learns to recognize three patterns.
 * @author nitin 
 *
 */

public class SOMIris {
	
	
	public static double SOM_INPUT[][] = { 
		{ -1.0, -1.0, 1.0, 1.0 }, 
		{ 6.3, 3.3, 4.7, 1.6 }, 
		{1,0,1.4,0} };
	
	public static void main(String args[])
	{	
		/* 
		 *  create the training set 
		 *  5th part of data-set is classification number
		 */
		
		double[][] irisdata = new IrisData().getDataArray("full",4);
		
		MLDataSet training = new BasicMLDataSet(irisdata,null);
		
		
		/**
		 * Create the neural network.
		 *  
		 * @param inputCount
		 *            Number of input neurons
		 * @param outputCount
		 *            Number of output neurons
		 * 
		 * Randomize weights between -1 and 1 on reset
		 */
		
		SOM network = new SOM(4,3);
		network.reset(); 
		
		/**
		 * Create an instance of competitive training.
		 * 
		 * @param network
		 *            The SOM network to train.
		 * @param learningRate
		 *            The learning rate, how much to apply per iteration.
		 * @param training
		 *            The training set (unsupervised).
		 * @param neighborhood
		 *            The neighborhood function to use.
		
		 * A very simple neighborhood function that will return 1.0 (full effect) for
		 * the winning neuron, and 0.0 (no change) for everything else.
		 * 
		 */
		BasicTrainSOM train = new BasicTrainSOM(
				network,
				0.3,
				training,
				new NeighborhoodSingle());
				
		int iteration = 0;
		
		for(iteration = 0;iteration<=1000;iteration++)
		{
			train.iteration();
			System.out.println("Iteration: " + iteration + ", Error:" + train.getError());
		}
		
		/*
		 * Basic implementation of the MLData interface
		 * that stores the data in an array.
		*/
		
		MLData data1 = new BasicMLData(SOM_INPUT[1]);
		MLData data2 = new BasicMLData(SOM_INPUT[2]);
		System.out.println(data1);
		System.out.println(data2);
		
		System.out.println("Pattern 1 winner: " + network.classify(data1));
		System.out.println("Pattern 2 winner: " + network.classify(data2));
		
		Map<String,String> map = network.getProperties();
		
		System.out.println(network.getWeights());
		for (String s: map.keySet()){
			System.out.println(s + " " +map.get(s));
		}
		
		Encog.getInstance().shutdown();
		
		
	}
}
