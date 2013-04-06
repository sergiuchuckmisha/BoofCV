/*
 * Copyright (c) 2011-2013, Peter Abeles. All Rights Reserved.
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

package boofcv.alg.filter.misc;

import boofcv.struct.image.*;

/**
 * <p>Implementation of {@link AverageDownSampleOps} specialized for square regions of width N.</p>
 *
 * <p>
 * DO NOT MODIFY: This class was automatically generated by {@link boofcv.alg.filter.misc.GenerateImplAverageDownSampleN}.
 * </p>
 *
 * @author Peter Abeles
 */
public class ImplAverageDownSampleN {
	public static void down( ImageUInt8 input , int sampleWidth , ImageInt8 output ) {
		int maxY = input.height - input.height%sampleWidth;
		int maxX = input.width - input.width%sampleWidth;

		int N = sampleWidth*sampleWidth;
		int N_half = N/2;

		for( int y = 0, outY = 0; y < maxY; y += sampleWidth, outY++  ) {
			int indexOut = output.startIndex + outY*output.stride;
			int endBoxY = y + sampleWidth;
			for( int x = 0; x < maxX; x += sampleWidth ) {
				int endBoxX = x + sampleWidth;

				int total = 0;
				for( int yy = y; yy < endBoxY; yy++ ) {
					int indexIn = input.startIndex + yy*input.stride + x;
					for( int xx = x; xx < endBoxX; xx++ ) {
						total += input.data[indexIn++]& 0xFF;
					}
				}

				output.data[ indexOut++ ] = (byte)((total+N_half)/N);
			}
		}

		// handle the right side
		if( maxX != input.width ) {
			N = sampleWidth*(input.width-maxX);
			N_half = N/2;
			for( int y = 0, outY = 0; y < maxY; y += sampleWidth, outY++  ) {
				int indexOut = output.startIndex + outY*output.stride + output.width-1;
				int endBoxY = y + sampleWidth;

				int total = 0;
				for( int yy = y; yy < endBoxY; yy++ ) {
					int indexIn = input.startIndex + yy*input.stride + maxX;
					for( int xx = maxX; xx < input.width; xx++ ) {
						total += input.data[indexIn++]& 0xFF;
					}
				}

				output.data[ indexOut ] = (byte)((total+N_half)/N);
			}
		}

		// handle the bottom
		if( maxY != input.height ) {
			N = (input.height-maxY)*sampleWidth;
			N_half = N/2;
			int indexOut = output.startIndex + (output.height-1)*output.stride;

			for( int x = 0; x < maxX; x += sampleWidth ) {
				int endBoxX = x + sampleWidth;

				int total = 0;
				for( int yy = maxY; yy < input.height; yy++ ) {
					int indexIn = input.startIndex + yy*input.stride + x;
					for( int xx = x; xx < endBoxX; xx++ ) {
						total += input.data[indexIn++]& 0xFF;
					}
				}

				output.data[ indexOut++ ] = (byte)((total+N_half)/N);
			}
		}

		// handle the bottom right
		if( maxX != input.width && maxY != input.height ) {
			N = (input.height-maxY)*(input.width-maxX);
			N_half = N/2;
			int indexOut = output.startIndex + (output.height-1)*output.stride + output.width-1;

			int total = 0;
			for( int yy = maxY; yy < input.height; yy++ ) {
				int indexIn = input.startIndex + yy*input.stride + maxX;
				for( int xx = maxX; xx < input.width; xx++ ) {
					total += input.data[indexIn++]& 0xFF;
				}
			}

			output.data[ indexOut ] = (byte)((total+N_half)/N);
		}
	}

	public static void down( ImageSInt8 input , int sampleWidth , ImageInt8 output ) {
		int maxY = input.height - input.height%sampleWidth;
		int maxX = input.width - input.width%sampleWidth;

		int N = sampleWidth*sampleWidth;
		int N_half = N/2;

		for( int y = 0, outY = 0; y < maxY; y += sampleWidth, outY++  ) {
			int indexOut = output.startIndex + outY*output.stride;
			int endBoxY = y + sampleWidth;
			for( int x = 0; x < maxX; x += sampleWidth ) {
				int endBoxX = x + sampleWidth;

				int total = 0;
				for( int yy = y; yy < endBoxY; yy++ ) {
					int indexIn = input.startIndex + yy*input.stride + x;
					for( int xx = x; xx < endBoxX; xx++ ) {
						total += input.data[indexIn++];
					}
				}

				output.data[ indexOut++ ] = total >= 0 ? (byte)((total+N_half)/N) : (byte)((total-N_half)/N);
			}
		}

		// handle the right side
		if( maxX != input.width ) {
			N = sampleWidth*(input.width-maxX);
			N_half = N/2;
			for( int y = 0, outY = 0; y < maxY; y += sampleWidth, outY++  ) {
				int indexOut = output.startIndex + outY*output.stride + output.width-1;
				int endBoxY = y + sampleWidth;

				int total = 0;
				for( int yy = y; yy < endBoxY; yy++ ) {
					int indexIn = input.startIndex + yy*input.stride + maxX;
					for( int xx = maxX; xx < input.width; xx++ ) {
						total += input.data[indexIn++];
					}
				}

				output.data[ indexOut ] = total >= 0 ? (byte)((total+N_half)/N) : (byte)((total-N_half)/N);
			}
		}

		// handle the bottom
		if( maxY != input.height ) {
			N = (input.height-maxY)*sampleWidth;
			N_half = N/2;
			int indexOut = output.startIndex + (output.height-1)*output.stride;

			for( int x = 0; x < maxX; x += sampleWidth ) {
				int endBoxX = x + sampleWidth;

				int total = 0;
				for( int yy = maxY; yy < input.height; yy++ ) {
					int indexIn = input.startIndex + yy*input.stride + x;
					for( int xx = x; xx < endBoxX; xx++ ) {
						total += input.data[indexIn++];
					}
				}

				output.data[ indexOut++ ] = total >= 0 ? (byte)((total+N_half)/N) : (byte)((total-N_half)/N);
			}
		}

		// handle the bottom right
		if( maxX != input.width && maxY != input.height ) {
			N = (input.height-maxY)*(input.width-maxX);
			N_half = N/2;
			int indexOut = output.startIndex + (output.height-1)*output.stride + output.width-1;

			int total = 0;
			for( int yy = maxY; yy < input.height; yy++ ) {
				int indexIn = input.startIndex + yy*input.stride + maxX;
				for( int xx = maxX; xx < input.width; xx++ ) {
					total += input.data[indexIn++];
				}
			}

			output.data[ indexOut ] = total >= 0 ? (byte)((total+N_half)/N) : (byte)((total-N_half)/N);
		}
	}

	public static void down( ImageUInt16 input , int sampleWidth , ImageInt16 output ) {
		int maxY = input.height - input.height%sampleWidth;
		int maxX = input.width - input.width%sampleWidth;

		int N = sampleWidth*sampleWidth;
		int N_half = N/2;

		for( int y = 0, outY = 0; y < maxY; y += sampleWidth, outY++  ) {
			int indexOut = output.startIndex + outY*output.stride;
			int endBoxY = y + sampleWidth;
			for( int x = 0; x < maxX; x += sampleWidth ) {
				int endBoxX = x + sampleWidth;

				int total = 0;
				for( int yy = y; yy < endBoxY; yy++ ) {
					int indexIn = input.startIndex + yy*input.stride + x;
					for( int xx = x; xx < endBoxX; xx++ ) {
						total += input.data[indexIn++]& 0xFFFF;
					}
				}

				output.data[ indexOut++ ] = (short)((total+N_half)/N);
			}
		}

		// handle the right side
		if( maxX != input.width ) {
			N = sampleWidth*(input.width-maxX);
			N_half = N/2;
			for( int y = 0, outY = 0; y < maxY; y += sampleWidth, outY++  ) {
				int indexOut = output.startIndex + outY*output.stride + output.width-1;
				int endBoxY = y + sampleWidth;

				int total = 0;
				for( int yy = y; yy < endBoxY; yy++ ) {
					int indexIn = input.startIndex + yy*input.stride + maxX;
					for( int xx = maxX; xx < input.width; xx++ ) {
						total += input.data[indexIn++]& 0xFFFF;
					}
				}

				output.data[ indexOut ] = (short)((total+N_half)/N);
			}
		}

		// handle the bottom
		if( maxY != input.height ) {
			N = (input.height-maxY)*sampleWidth;
			N_half = N/2;
			int indexOut = output.startIndex + (output.height-1)*output.stride;

			for( int x = 0; x < maxX; x += sampleWidth ) {
				int endBoxX = x + sampleWidth;

				int total = 0;
				for( int yy = maxY; yy < input.height; yy++ ) {
					int indexIn = input.startIndex + yy*input.stride + x;
					for( int xx = x; xx < endBoxX; xx++ ) {
						total += input.data[indexIn++]& 0xFFFF;
					}
				}

				output.data[ indexOut++ ] = (short)((total+N_half)/N);
			}
		}

		// handle the bottom right
		if( maxX != input.width && maxY != input.height ) {
			N = (input.height-maxY)*(input.width-maxX);
			N_half = N/2;
			int indexOut = output.startIndex + (output.height-1)*output.stride + output.width-1;

			int total = 0;
			for( int yy = maxY; yy < input.height; yy++ ) {
				int indexIn = input.startIndex + yy*input.stride + maxX;
				for( int xx = maxX; xx < input.width; xx++ ) {
					total += input.data[indexIn++]& 0xFFFF;
				}
			}

			output.data[ indexOut ] = (short)((total+N_half)/N);
		}
	}

	public static void down( ImageSInt16 input , int sampleWidth , ImageInt16 output ) {
		int maxY = input.height - input.height%sampleWidth;
		int maxX = input.width - input.width%sampleWidth;

		int N = sampleWidth*sampleWidth;
		int N_half = N/2;

		for( int y = 0, outY = 0; y < maxY; y += sampleWidth, outY++  ) {
			int indexOut = output.startIndex + outY*output.stride;
			int endBoxY = y + sampleWidth;
			for( int x = 0; x < maxX; x += sampleWidth ) {
				int endBoxX = x + sampleWidth;

				int total = 0;
				for( int yy = y; yy < endBoxY; yy++ ) {
					int indexIn = input.startIndex + yy*input.stride + x;
					for( int xx = x; xx < endBoxX; xx++ ) {
						total += input.data[indexIn++];
					}
				}

				output.data[ indexOut++ ] = total >= 0 ? (short)((total+N_half)/N) : (short)((total-N_half)/N);
			}
		}

		// handle the right side
		if( maxX != input.width ) {
			N = sampleWidth*(input.width-maxX);
			N_half = N/2;
			for( int y = 0, outY = 0; y < maxY; y += sampleWidth, outY++  ) {
				int indexOut = output.startIndex + outY*output.stride + output.width-1;
				int endBoxY = y + sampleWidth;

				int total = 0;
				for( int yy = y; yy < endBoxY; yy++ ) {
					int indexIn = input.startIndex + yy*input.stride + maxX;
					for( int xx = maxX; xx < input.width; xx++ ) {
						total += input.data[indexIn++];
					}
				}

				output.data[ indexOut ] = total >= 0 ? (short)((total+N_half)/N) : (short)((total-N_half)/N);
			}
		}

		// handle the bottom
		if( maxY != input.height ) {
			N = (input.height-maxY)*sampleWidth;
			N_half = N/2;
			int indexOut = output.startIndex + (output.height-1)*output.stride;

			for( int x = 0; x < maxX; x += sampleWidth ) {
				int endBoxX = x + sampleWidth;

				int total = 0;
				for( int yy = maxY; yy < input.height; yy++ ) {
					int indexIn = input.startIndex + yy*input.stride + x;
					for( int xx = x; xx < endBoxX; xx++ ) {
						total += input.data[indexIn++];
					}
				}

				output.data[ indexOut++ ] = total >= 0 ? (short)((total+N_half)/N) : (short)((total-N_half)/N);
			}
		}

		// handle the bottom right
		if( maxX != input.width && maxY != input.height ) {
			N = (input.height-maxY)*(input.width-maxX);
			N_half = N/2;
			int indexOut = output.startIndex + (output.height-1)*output.stride + output.width-1;

			int total = 0;
			for( int yy = maxY; yy < input.height; yy++ ) {
				int indexIn = input.startIndex + yy*input.stride + maxX;
				for( int xx = maxX; xx < input.width; xx++ ) {
					total += input.data[indexIn++];
				}
			}

			output.data[ indexOut ] = total >= 0 ? (short)((total+N_half)/N) : (short)((total-N_half)/N);
		}
	}

	public static void down( ImageSInt32 input , int sampleWidth , ImageSInt32 output ) {
		int maxY = input.height - input.height%sampleWidth;
		int maxX = input.width - input.width%sampleWidth;

		int N = sampleWidth*sampleWidth;
		int N_half = N/2;

		for( int y = 0, outY = 0; y < maxY; y += sampleWidth, outY++  ) {
			int indexOut = output.startIndex + outY*output.stride;
			int endBoxY = y + sampleWidth;
			for( int x = 0; x < maxX; x += sampleWidth ) {
				int endBoxX = x + sampleWidth;

				int total = 0;
				for( int yy = y; yy < endBoxY; yy++ ) {
					int indexIn = input.startIndex + yy*input.stride + x;
					for( int xx = x; xx < endBoxX; xx++ ) {
						total += input.data[indexIn++];
					}
				}

				output.data[ indexOut++ ] = total >= 0 ? ((total+N_half)/N) : ((total-N_half)/N);
			}
		}

		// handle the right side
		if( maxX != input.width ) {
			N = sampleWidth*(input.width-maxX);
			N_half = N/2;
			for( int y = 0, outY = 0; y < maxY; y += sampleWidth, outY++  ) {
				int indexOut = output.startIndex + outY*output.stride + output.width-1;
				int endBoxY = y + sampleWidth;

				int total = 0;
				for( int yy = y; yy < endBoxY; yy++ ) {
					int indexIn = input.startIndex + yy*input.stride + maxX;
					for( int xx = maxX; xx < input.width; xx++ ) {
						total += input.data[indexIn++];
					}
				}

				output.data[ indexOut ] = total >= 0 ? ((total+N_half)/N) : ((total-N_half)/N);
			}
		}

		// handle the bottom
		if( maxY != input.height ) {
			N = (input.height-maxY)*sampleWidth;
			N_half = N/2;
			int indexOut = output.startIndex + (output.height-1)*output.stride;

			for( int x = 0; x < maxX; x += sampleWidth ) {
				int endBoxX = x + sampleWidth;

				int total = 0;
				for( int yy = maxY; yy < input.height; yy++ ) {
					int indexIn = input.startIndex + yy*input.stride + x;
					for( int xx = x; xx < endBoxX; xx++ ) {
						total += input.data[indexIn++];
					}
				}

				output.data[ indexOut++ ] = total >= 0 ? ((total+N_half)/N) : ((total-N_half)/N);
			}
		}

		// handle the bottom right
		if( maxX != input.width && maxY != input.height ) {
			N = (input.height-maxY)*(input.width-maxX);
			N_half = N/2;
			int indexOut = output.startIndex + (output.height-1)*output.stride + output.width-1;

			int total = 0;
			for( int yy = maxY; yy < input.height; yy++ ) {
				int indexIn = input.startIndex + yy*input.stride + maxX;
				for( int xx = maxX; xx < input.width; xx++ ) {
					total += input.data[indexIn++];
				}
			}

			output.data[ indexOut ] = total >= 0 ? ((total+N_half)/N) : ((total-N_half)/N);
		}
	}

	public static void down( ImageFloat32 input , int sampleWidth , ImageFloat32 output ) {
		int maxY = input.height - input.height%sampleWidth;
		int maxX = input.width - input.width%sampleWidth;

		float N = sampleWidth*sampleWidth;

		for( int y = 0, outY = 0; y < maxY; y += sampleWidth, outY++  ) {
			int indexOut = output.startIndex + outY*output.stride;
			int endBoxY = y + sampleWidth;
			for( int x = 0; x < maxX; x += sampleWidth ) {
				int endBoxX = x + sampleWidth;

				float total = 0;
				for( int yy = y; yy < endBoxY; yy++ ) {
					int indexIn = input.startIndex + yy*input.stride + x;
					for( int xx = x; xx < endBoxX; xx++ ) {
						total += input.data[indexIn++];
					}
				}

				output.data[ indexOut++ ] = (total/N);
			}
		}

		// handle the right side
		if( maxX != input.width ) {
			N = sampleWidth*(input.width-maxX);
			for( int y = 0, outY = 0; y < maxY; y += sampleWidth, outY++  ) {
				int indexOut = output.startIndex + outY*output.stride + output.width-1;
				int endBoxY = y + sampleWidth;

				float total = 0;
				for( int yy = y; yy < endBoxY; yy++ ) {
					int indexIn = input.startIndex + yy*input.stride + maxX;
					for( int xx = maxX; xx < input.width; xx++ ) {
						total += input.data[indexIn++];
					}
				}

				output.data[ indexOut ] = (total/N);
			}
		}

		// handle the bottom
		if( maxY != input.height ) {
			N = (input.height-maxY)*sampleWidth;
			int indexOut = output.startIndex + (output.height-1)*output.stride;

			for( int x = 0; x < maxX; x += sampleWidth ) {
				int endBoxX = x + sampleWidth;

				float total = 0;
				for( int yy = maxY; yy < input.height; yy++ ) {
					int indexIn = input.startIndex + yy*input.stride + x;
					for( int xx = x; xx < endBoxX; xx++ ) {
						total += input.data[indexIn++];
					}
				}

				output.data[ indexOut++ ] = (total/N);
			}
		}

		// handle the bottom right
		if( maxX != input.width && maxY != input.height ) {
			N = (input.height-maxY)*(input.width-maxX);
			int indexOut = output.startIndex + (output.height-1)*output.stride + output.width-1;

			float total = 0;
			for( int yy = maxY; yy < input.height; yy++ ) {
				int indexIn = input.startIndex + yy*input.stride + maxX;
				for( int xx = maxX; xx < input.width; xx++ ) {
					total += input.data[indexIn++];
				}
			}

			output.data[ indexOut ] = (total/N);
		}
	}

	public static void down( ImageFloat64 input , int sampleWidth , ImageFloat64 output ) {
		int maxY = input.height - input.height%sampleWidth;
		int maxX = input.width - input.width%sampleWidth;

		double N = sampleWidth*sampleWidth;

		for( int y = 0, outY = 0; y < maxY; y += sampleWidth, outY++  ) {
			int indexOut = output.startIndex + outY*output.stride;
			int endBoxY = y + sampleWidth;
			for( int x = 0; x < maxX; x += sampleWidth ) {
				int endBoxX = x + sampleWidth;

				double total = 0;
				for( int yy = y; yy < endBoxY; yy++ ) {
					int indexIn = input.startIndex + yy*input.stride + x;
					for( int xx = x; xx < endBoxX; xx++ ) {
						total += input.data[indexIn++];
					}
				}

				output.data[ indexOut++ ] = (total/N);
			}
		}

		// handle the right side
		if( maxX != input.width ) {
			N = sampleWidth*(input.width-maxX);
			for( int y = 0, outY = 0; y < maxY; y += sampleWidth, outY++  ) {
				int indexOut = output.startIndex + outY*output.stride + output.width-1;
				int endBoxY = y + sampleWidth;

				double total = 0;
				for( int yy = y; yy < endBoxY; yy++ ) {
					int indexIn = input.startIndex + yy*input.stride + maxX;
					for( int xx = maxX; xx < input.width; xx++ ) {
						total += input.data[indexIn++];
					}
				}

				output.data[ indexOut ] = (total/N);
			}
		}

		// handle the bottom
		if( maxY != input.height ) {
			N = (input.height-maxY)*sampleWidth;
			int indexOut = output.startIndex + (output.height-1)*output.stride;

			for( int x = 0; x < maxX; x += sampleWidth ) {
				int endBoxX = x + sampleWidth;

				double total = 0;
				for( int yy = maxY; yy < input.height; yy++ ) {
					int indexIn = input.startIndex + yy*input.stride + x;
					for( int xx = x; xx < endBoxX; xx++ ) {
						total += input.data[indexIn++];
					}
				}

				output.data[ indexOut++ ] = (total/N);
			}
		}

		// handle the bottom right
		if( maxX != input.width && maxY != input.height ) {
			N = (input.height-maxY)*(input.width-maxX);
			int indexOut = output.startIndex + (output.height-1)*output.stride + output.width-1;

			double total = 0;
			for( int yy = maxY; yy < input.height; yy++ ) {
				int indexIn = input.startIndex + yy*input.stride + maxX;
				for( int xx = maxX; xx < input.width; xx++ ) {
					total += input.data[indexIn++];
				}
			}

			output.data[ indexOut ] = (total/N);
		}
	}


}
