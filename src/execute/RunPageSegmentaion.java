package execute;

import global.Constants;

import java.awt.image.BufferedImage;
import java.awt.image.Raster;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import javax.imageio.ImageIO;
import models.Image;
import blurring.ImageBlurrer;

public class RunPageSegmentaion {

	/**
	 * This main function is responsible for running the Full-page segmentation algorithm.
	 * It reads in either a single .jpg image or a text list of .jpg images and performs segmentation.
	 * @param args
	 */
	public static void main(String[] args) {

		if(args[0].contains(".txt")){
			String imageList = args[0];
			File inputImages = new File(Constants.imageLists,imageList);
			BufferedReader br = null;
			try {
				br = new BufferedReader(new FileReader(inputImages));
			} catch (FileNotFoundException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}

			try {
				String line = br.readLine();
				int i = 1;
				while(line != null){
					System.out.println("Image "+i);
					Image img = importImage(line);
					try{
						//img.scaleDown(5);
						segmentImage(img);
					}catch(RuntimeException r){
						r.printStackTrace();
						System.out.println("runtime");
						//System.out.println("ERROR: Unable to segment "+img.getName()+"\nPlease make sure that the image isn't rotated and has good contrast");
					}catch(Exception e){
						//System.out.println("ERROR: Unable to segment "+img.getName()+"\nPlease make sure that the page isn't rotated and has good contrast");
					}
					line = br.readLine();
					i++;
				}
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}else if(args[0].contains(".jpg")){
			Image img = importImage(args[0]);
			try{
				//img.scaleDown(5);
				segmentImage(img);	
			} catch(Exception e){
				e.printStackTrace();
				//System.out.println("ERROR: Unable to segment "+img.getName()+"\nPlease make sure that the page isn't rotated");
			}
		}
	}


	/**
	 * This method imports the image whose filepath is passed as a parameter and returns an models.Image object.
	 * @param inputFilename
	 * @return models.Image
	 */
	public static Image importImage(String inputFilename){
		BufferedImage inputImage = null;
		int w=0,h=0;
		try {
			System.out.println("Loading Image..");
			File inputImageFile = new File(Constants.fullPagePath, inputFilename);
			inputImage = ImageIO.read(inputImageFile);

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		Raster raster = inputImage.getData();
		w = raster.getWidth();
		h = raster.getHeight();
		Image img = new Image(h,w);
		int pixels[][] = new int[h][w];
		//		int pixels2[][] = new int[h][w];

		//read the pixels from the input image
		for (int i = 0; i < h; i++) {
			for (int j = 0; j < w; j++) {
				pixels[i][j] = raster.getSample(j, i, 0);
			}
		}
		//		
		//		for (int i = 0; i < h; i++) {
		//			for (int j = 0; j < w; j++) {
		//				pixels2[i][j] = raster.getSample(j, i, 0);
		//			}
		//		}


		img.setByteImage(pixels);
		img.setByteImage2(pixels);

		img.setName(inputFilename);

		return img;
	}

	/**
	 * A helper method for grouping together the function calls for image segmentation.
	 * @param img
	 */
	public static void segmentImage(Image img){
		ImageBlurrer imb = new ImageBlurrer();
		imb.binarizeSegment(img, true);
		System.out.println("Binarizing Passed\n\n\n\n");


		img.findColumnBreaks();
		System.out.println("Column Find Passed\n\n\n\n");
		System.out.println(img.getColumnBreaks());
		img.showColumnBreaks();
		System.out.println("Column Breaks Passed\n\n\n\n");

		img.convertPageToSnippets();
	}
}
