/*
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.boonya.spark.examples.ml;

import org.apache.spark.sql.SparkSession;

// $example on$
import org.apache.spark.ml.feature.RobustScaler;
import org.apache.spark.ml.feature.RobustScalerModel;
import org.apache.spark.sql.Dataset;
import org.apache.spark.sql.Row;
// $example off$

public class JavaRobustScalerExample {
  public static void main(String[] args) {
    SparkSession spark = SparkSession
      .builder()
      .appName("JavaRobustScalerExample")
      .getOrCreate();

    // $example on$
    Dataset<Row> dataFrame =
      spark.read().format("libsvm").load("data/mllib/sample_libsvm_data.txt");

    RobustScaler scaler = new RobustScaler()
      .setInputCol("features")
      .setOutputCol("scaledFeatures")
      .setWithScaling(true)
      .setWithCentering(false)
      .setLower(0.25)
      .setUpper(0.75);

    // Compute summary statistics by fitting the RobustScaler
    RobustScalerModel scalerModel = scaler.fit(dataFrame);

    // Transform each feature to have unit quantile range.
    Dataset<Row> scaledData = scalerModel.transform(dataFrame);
    scaledData.show();
    // $example off$
    spark.stop();
  }
}