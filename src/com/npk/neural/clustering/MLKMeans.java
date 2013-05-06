package com.npk.neural.clustering;

import java.io.File;

import net.sf.javaml.clustering.Clusterer;
import net.sf.javaml.clustering.KMeans;
import net.sf.javaml.core.Dataset;
import net.sf.javaml.distance.NormalizedEuclideanSimilarity;
import net.sf.javaml.tools.data.FileHandler;

public class MLKMeans {

	public static void main(String[] args) throws Exception {

        Dataset data = FileHandler.loadDataset(new File("dataset/iris.data"), 4, " ");
        
        /*Default cluster size of 4, 100 iterations, euclidean distance, also a default random generator.
         * There are lots of Distance measures to choose from.
         * KMeans(int clusters, int iterations, DistanceMeasure dm)
         * Create a new K-means clusterer with the given number of clusters and iterations.
         */
        Clusterer km = new KMeans(5,100, new NormalizedEuclideanSimilarity(data));
        Dataset[] clusters = km.cluster(data);
        
        System.out.println("Cluster count: " + clusters.length);
        
        for (Dataset d : clusters){
			System.out.println(d);
			System.out.println(d.size());
		}

    }

}
