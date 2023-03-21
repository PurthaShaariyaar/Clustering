package com.sofe3980U;

import static org.junit.Assert.assertTrue;

import java.io.File;
import java.io.IOException;

import org.junit.Test;


import net.sf.javaml.clustering.evaluation.AICScore;
import net.sf.javaml.clustering.evaluation.BICScore;
import net.sf.javaml.clustering.evaluation.ClusterEvaluation;
import net.sf.javaml.clustering.evaluation.SumOfSquaredErrors;

import net.sf.javaml.clustering.Clusterer;
import net.sf.javaml.clustering.DensityBasedSpatialClustering;
import net.sf.javaml.clustering.FarthestFirst;
import net.sf.javaml.clustering.KMeans;
import net.sf.javaml.clustering.evaluation.ClusterEvaluation;
import net.sf.javaml.core.Dataset;
import net.sf.javaml.tools.data.FileHandler;


public class AppTest {
    
    @Test
    public void testClustering() {
        try {

            ClusterEvaluation aic = new AICScore();
            ClusterEvaluation bic = new BICScore();
            ClusterEvaluation sse = new SumOfSquaredErrors();
            
            Dataset data = FileHandler.loadDataset(new File("iris.data"), 4, ",");
            
            Clusterer kmeans = new net.sf.javaml.clustering.KMeans();
            Dataset[] kmeansClusters = kmeans.cluster(data);
            double aicKMeans = aic.score(kmeansClusters);
            double bicKmeans = bic.score(kmeansClusters);
            double sseKMeans = sse.score(kmeansClusters);
            
            Clusterer farthestFirst = new net.sf.javaml.clustering.FarthestFirst();
            Dataset[] farthestFirstClusters = farthestFirst.cluster(data);
            double aicFarthestFirst = aic.score(farthestFirstClusters);
            double bicFarthestFirst = bic.score(farthestFirstClusters);
            double sseFarthestFirst = sse.score(farthestFirstClusters);

            Clusterer densityBased = new net.sf.javaml.clustering.DensityBasedSpatialClustering();
            Dataset[] densityBasedClusters = densityBased.cluster(data);
            double aicDensityBased = aic.score(densityBasedClusters);
            double bicDensityBased = bic.score(densityBasedClusters);
            double sseDensityBased = sse.score(densityBasedClusters);

            System.out.println("AIC score: " + aicKMeans+"\t"+aicFarthestFirst+"\t"+aicDensityBased);
            System.out.println("BIC score: " + bicKmeans+"\t"+bicFarthestFirst+"\t"+bicDensityBased);
            System.out.println("Sum of squared errors: " + sseKMeans+"\t"+sseFarthestFirst+"\t"+sseDensityBased);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testKMeans() throws Exception {
        Dataset data = FileHandler.loadDataset(new File("iris.data"), 4, ",");
        
        Clusterer kmeans = new net.sf.javaml.clustering.KMeans(3);
        Dataset[] kmeansClusters = kmeans.cluster(data);
        double aic = new AICScore().score(kmeansClusters);
        double bic = new BICScore().score(kmeansClusters);
        double sse = new SumOfSquaredErrors().score(kmeansClusters);
        System.out.println(aic);
        System.out.println(bic);
        System.out.println(sse);
        assertTrue(aic > 0);
        assertTrue(bic > 0);
        assertTrue(sse < 1000);
    }
    
    @Test
    public void testFarthestFirst() throws Exception {
        Dataset data = FileHandler.loadDataset(new File("iris.data"), 4, ",");
        Clusterer farthestFirst = new net.sf.javaml.clustering.FarthestFirst();
        Dataset[] farthestFirstClusters = farthestFirst.cluster(data);
        double aic = new AICScore().score(farthestFirstClusters);
        double bic = new BICScore().score(farthestFirstClusters);
        double sse = new SumOfSquaredErrors().score(farthestFirstClusters);
        System.out.println(aic);
        System.out.println(bic);
        System.out.println(sse);
        assertTrue(aic > 0);
        assertTrue(bic > 0);
        assertTrue(sse < 1000);
    }
    
    @Test
    public void testDensityBased() throws Exception {
        Dataset data = FileHandler.loadDataset(new File("iris.data"), 4, ",");
        Clusterer densityBased = new DensityBasedSpatialClustering(0.1, 5);
        Dataset[] densityBasedClusters = densityBased.cluster(data);
        double aic = new AICScore().score(densityBasedClusters);
        double bic = new BICScore().score(densityBasedClusters);
        double sse = new SumOfSquaredErrors().score(densityBasedClusters);
        System.out.println(aic);
        System.out.println(bic);
        System.out.println(sse);
        assertTrue(aic > 0);
        assertTrue(bic > 0);
        assertTrue(sse < 1000);
    }
}