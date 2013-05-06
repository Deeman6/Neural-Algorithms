package com.npk.neural.unsupervised;

import java.io.File;


import net.sf.javaml.clustering.*;
import net.sf.javaml.core.Dataset;
import net.sf.javaml.tools.data.FileHandler;

public class MLSOM {

	public static void main(String[] args) throws Exception{
		
		SOM som = new SOM(4,3, SOM.GridType.RECTANGLES,1000, 0.6, 8, SOM.LearningType.EXPONENTIAL, SOM.NeighbourhoodFunction.GAUSSIAN);
		Dataset data = FileHandler.loadDataset(new File("dataset/iris.data"), 4, " ");
		
		Dataset[] dataset = som.cluster(data);
		System.out.println(dataset.length);
		for (Dataset d : dataset){
			System.out.println(d);
			System.out.println(d.size());
		}

	}
}
