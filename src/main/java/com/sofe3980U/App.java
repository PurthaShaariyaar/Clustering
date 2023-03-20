package com.sofe3980U;

import net.sf.javaml.clustering.Clusterer;
import net.sf.javaml.clustering.DensityBasedSpatialClustering;
import net.sf.javaml.clustering.FarthestFirst;
import net.sf.javaml.clustering.KMeans;
import net.sf.javaml.core.Dataset;
import net.sf.javaml.tools.data.FileHandler;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;


public class App {

  private static Map<Integer, Dataset> runClusterer(Clusterer clusterer, Dataset data) {
    Map<Integer, Dataset> clusters = new HashMap<Integer, Dataset>();
    Dataset[] clusterArray = clusterer.cluster(data);
    for (int i = 0; i < clusterArray.length; i++) {
        clusters.put(i, clusterArray[i]);
    }
    return clusters;
  }
  

  private static void printClusters(Map<Integer, Dataset> clusters) {
    for (Integer i : clusters.keySet()) {
      System.out.println("Cluster " + i + ":");
      Dataset data = clusters.get(i);
      for (int j = 0; j < data.size(); j++) {
        System.out.println(data.get(j));
      }
    }
  }

  public static void main(String[] args) {
    try {
      
      Dataset data = FileHandler.loadDataset(new File("iris.data"), 4, ",");

      Clusterer kmeans = new KMeans();

      Clusterer farthestFirst = new FarthestFirst();

      Clusterer densityBased = new DensityBasedSpatialClustering();

      Map<Integer, Dataset> kmeansClusters = runClusterer(kmeans, data);
      System.out.println("K-means Clusters:");
      printClusters(kmeansClusters);
      System.out.println();

      Map<Integer, Dataset> farthestFirstClusters = runClusterer(farthestFirst, data);
      System.out.println("Farthest First Clusters:");
      printClusters(farthestFirstClusters);
      System.out.println();

      Map<Integer, Dataset> densityBasedClusters = runClusterer(densityBased, data);
      System.out.println("Density Based Clusters: ");
      printClusters(densityBasedClusters);
      System.out.println();

    } catch (IOException e) {
      e.printStackTrace();
    }
  }
}
