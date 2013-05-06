package com.npk.neural.unsupervised;

import java.io.File;


import net.sf.javaml.clustering.*;
import net.sf.javaml.core.Dataset;
import net.sf.javaml.tools.data.FileHandler;

public class MLSOM {

	public static void main(String[] args) throws Exception{
		/*
		 * public SOM(int xdim,
   			int ydim,
   			SOM.GridType grid,
   			int iterations,
   			double learningRate,
   			int initialRadius,
   			SOM.LearningType learning,
   			SOM.NeighbourhoodFunction nbf)
		 * */
		SOM som = new SOM(3,4, SOM.GridType.RECTANGLES,100, 0.6, 8, SOM.LearningType.EXPONENTIAL, SOM.NeighbourhoodFunction.GAUSSIAN);
		Dataset data = FileHandler.loadDataset(new File("dataset/model.data"), 0, ",");
		
		Dataset[] dataset = som.cluster(data);
		System.out.println(dataset.length);
		for (Dataset d : dataset){
			System.out.println(d.size());
		}

	}
}
