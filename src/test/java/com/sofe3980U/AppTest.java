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
    

}