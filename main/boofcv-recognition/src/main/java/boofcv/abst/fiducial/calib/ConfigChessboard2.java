/*
 * Copyright (c) 2011-2019, Peter Abeles. All Rights Reserved.
 *
 * This file is part of BoofCV (http://boofcv.org).
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package boofcv.abst.fiducial.calib;

import boofcv.alg.feature.detect.chess.DetectChessboardCorners;
import boofcv.alg.fiducial.calib.chess.DetectChessboardFiducial;
import boofcv.factory.filter.binary.ConfigThreshold;
import boofcv.factory.filter.binary.ThresholdType;
import boofcv.struct.Configuration;

/**
 * Calibration parameters for chessboard style calibration grid.
 *
 * @see DetectChessboardFiducial
 *
 * @author Peter Abeles
 */
public class ConfigChessboard2 implements Configuration {

	/**
	 * Size of a corner in the corner detector. For very small targets 1 is required. Otherwise 2 provides
	 * much more stable results.
	 */
	public int cornerRadius = 2;

	/**
	 * Second threshold on corner intensity. This is applied after orientation has been estimated and is used
	 * to remove false positives, like corners on a box.
	 */
	public double cornerThreshold = 1.0;

	/**
	 * The minimum allowed size for the top most layer in the pyramid. size = min(width,height). To have
	 * only one layer in the pyramid at the same resolution as the input set this to a value of &le; 0
	 */
	public int pyramidTopSize = 100;

	/**
	 * How similar the direction of two corners relative to each other need to be.
	 */
	public double directionTol = 0.6;

	/**
	 * How similar two corner orientations need to be
	 */
	public double orientaitonTol = 0.45;

	/**
	 * Ratio used to decide if two corners are spatially close enough to each other to be considered
	 * as the same corner.
	 */
	public double ambiguousTol = 0.25;

	/**
	 * Maximum number of neighbors returned by nearest neighbor search
	 */
	public int maxNeighbors = 20;

	/**
	 * Maximum search distance for nearest neighbor search. Units = pixels.
	 */
	public double maxNeighborDistance = Double.MAX_VALUE;

	/**
	 * Selection of threshold for binary image. Intensity image is the input.
	 */
	public ConfigThreshold threshold = ConfigThreshold.global(ThresholdType.GLOBAL_OTSU);

	{
		threshold.maxPixelValue = DetectChessboardCorners.GRAY_LEVELS;
		threshold.scale = 1.0;
		threshold.down = false;
	}

	@Override
	public void checkValidity() {}
}
