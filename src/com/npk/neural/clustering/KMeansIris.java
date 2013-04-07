package com.npk.neural.clustering;

import java.util.Arrays;

import org.encog.ml.MLCluster;
import org.encog.ml.data.MLDataPair;
import org.encog.ml.data.MLDataSet;
import org.encog.ml.data.basic.BasicMLData;
import org.encog.ml.data.basic.BasicMLDataPair;
import org.encog.ml.data.basic.BasicMLDataSet;
import org.encog.ml.kmeans.KMeansClustering;

import com.npk.neural.data.IrisData;

/**
 * This example performs a simple KMeans cluster.  The numbers are clustered
 * into two groups.
 */

public class KMeansIris {
	
	/**
	 * The data to be clustered.
	 */
	public static final double[][] DATA = new IrisData().getDataArray("test");
	
	

	//public static final double[][] DATA = { { 5.1, 3.5, 1.4, 0.2,1 }, { 6.3, 3.3, 4.7, 1.6,2 }, { 5.9, 3.0, 5.1, 1.8,3 }, {5.5, 2.3, 4.0, 1.3, 2}};
	
	public static void main(final String args[]) { 
			
		/**
		 * BasicMLDataSet
		 * NOTE: Stores data in an ArrayList. This class is memory based, so large enough
		 * datasets could cause memory issues. I've used the test.data instead of original
		 * dataset as it caused memory problems. Many other dataset types extend this
		 * class.
		 */
		
		final BasicMLDataSet set = new BasicMLDataSet();

		for (final double[] element : KMeansIris.DATA) {
			
			set.add(new BasicMLData(element));
		}

		/**
		 * Construct the K-Means object.
		 * 
		 * @param k
		 *            The number of clusters to use.
		 * @param theSet
		 *            The dataset to cluster.
		 */

		final KMeansClustering kmeans = new KMeansClustering(3, set);

		kmeans.iteration(1000);
		
		// Display the cluster
		
		int i = 1;
		/**
		 * MLCluster
		 * Interface, Defines a cluster. Usually used with the MLClustering method to break input
		 * into clusters.
		 */
		for (final MLCluster cluster : kmeans.getClusters()) {
			System.out.println("*** Cluster " + (i++) + " ***");
			
			
			/**
			 * MLDataSet
			 * An interface designed to abstract classes that store machine learning data.
			 * This interface is designed to provide EngineDataSet objects. These can be
			 * used to train machine learning methods using both supervised and unsupervised
			 * training.
			 * 
			 * Some implementations of this interface are memory based. That is they store
			 * the entire contents of the dataset in memory.
			 * 
			 * Other implementations of this interface are not memory based. These
			 * implementations read in data as it is needed. This allows very large datasets
			 * to be used. Typically the add methods are not supported on non-memory based
			 * datasets.
			 * 
			 */
			final MLDataSet ds = cluster.createDataSet();
			
			/**
			 * BasicMLDataPair
			 * A basic implementation of the MLDataPair interface. This implementation
			 * simply holds and input and ideal MLData object.
			 * 
			 * NOTE: For supervised training both input and ideal should be specified.
			 * 
			 * For unsupervised training the input property should be valid, however the
			 * ideal property should contain null.
			 * 
			 * 
			 * BasicMLDataPair.createPair
			 * Create a new data pair object of the correct size for the machine
			 * learning method that is being trained. This object will be passed to the
			 * getPair method to allow the data pair objects to be copied to it.
			 * 
			 * @param inputSize
			 *            The size of the input data.
			 * @param idealSize
			 *            The size of the ideal data.
			 * @return A new data pair object.
			 */			
			final MLDataPair pair = BasicMLDataPair.createPair(ds.getInputSize(), ds.getIdealSize());
			
			/**
			 * getRecordCount()
			 * @return The total number of records in the set.
			 */
			for (int j = 0; j < ds.getRecordCount(); j++) {
				ds.getRecord(j, pair);
				System.out.println(Arrays.toString(pair.getInputArray()));

			}
		}
	}
}
