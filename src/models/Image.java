package models;

import global.Constants;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;

import javax.imageio.ImageIO;

/**
 * Image class to serve as a model of the desired attributes and stores bytepixels of image in byteImage
 * @author mdatla
 *
 */
public class Image {

	public int[][] byteImage;
	protected int[][] byteImage2;

	protected String name;
	protected boolean containsPoem;
	protected boolean checkValue;
	protected int vertical;
	protected int horizontal;

	protected double stanzaMean;
	protected double stanzaStandardDeviation;
	protected double stanzaMin;
	protected double stanzaMax;
	protected double stanzaRange;

	protected double jaggedLineMean;
	protected double jaggedLineStandardDeviation;
	protected double jaggedMin;
	protected double jaggedMax;
	protected double jaggedRange;

	protected double marginMean;
	protected double marginStdDev;
	protected double marginMin;
	protected double marginMax;
	protected double marginRange;

	protected double lengthMean;
	protected double lengthStdDev;
	protected double lengthMin;
	protected double lengthMax;
	protected double lengthRange;
	
	protected ArrayList<Integer> columnBreaks;



	public double getLengthMean() {
		return lengthMean;
	}

	public void setLengthMean(double lengthMean) {
		this.lengthMean = lengthMean;
	}

	public double getLengthStdDev() {
		return lengthStdDev;
	}

	public void setLengthStdDev(double lengthStdDev) {
		this.lengthStdDev = lengthStdDev;
	}

	public double getLengthMin() {
		return lengthMin;
	}

	public void setLengthMin(double lengthMin) {
		this.lengthMin = lengthMin;
	}

	public double getLengthMax() {
		return lengthMax;
	}

	public void setLengthMax(double lengthMax) {
		this.lengthMax = lengthMax;
	}

	public double getLengthRange() {
		return lengthRange;
	}

	public void setLengthRange(double lengthRange) {
		this.lengthRange = lengthRange;
	}
	protected int blurLevel;

	public double getStanzaMin() {
		return stanzaMin;
	}

	public void setStanzaMin(double stanzaMin) {
		this.stanzaMin = stanzaMin;
	}

	public double getStanzaMax() {
		return stanzaMax;
	}

	public void setStanzaMax(double stanzaMax) {
		this.stanzaMax = stanzaMax;
	}

	public double getStanzaRange() {
		return stanzaRange;
	}

	public void setStanzaRange(double stanzaRange) {
		this.stanzaRange = stanzaRange;
	}

	public double getJaggedMin() {
		return jaggedMin;
	}

	public void setJaggedMin(double jaggedMin) {
		this.jaggedMin = jaggedMin;
	}

	public double getJaggedMax() {
		return jaggedMax;
	}

	public void setJaggedMax(double jaggedMax) {
		this.jaggedMax = jaggedMax;
	}

	public double getJaggedRange() {
		return jaggedRange;
	}

	public void setJaggedRange(double jaggedRange) {
		this.jaggedRange = jaggedRange;
	}

	public double getMarginMin() {
		return marginMin;
	}

	public void setMarginMin(double marginMin) {
		this.marginMin = marginMin;
	}

	public double getMarginMax() {
		return marginMax;
	}

	public void setMarginMax(double marginMax) {
		this.marginMax = marginMax;
	}

	public double getMarginRange() {
		return marginRange;
	}

	public void setMarginRange(double marginRange) {
		this.marginRange = marginRange;
	}

	public double getMarginMean() {
		return marginMean;
	}

	public void setMarginMean(double marginMean) {
		this.marginMean = marginMean;
	}

	public double getMarginStdDev() {
		return marginStdDev;
	}

	public void setMarginStdDev(double marginStdDev) {
		this.marginStdDev = marginStdDev;
	}
	protected double leftMarginSize;
	protected double rightMarginSize;

	public Image(){
		this.name = "";
		this.containsPoem = false;
		this.checkValue = false;
		this.vertical = 0;
		this.horizontal = 0;
		this.stanzaMean = 0;
		this.stanzaStandardDeviation = 0;
		this.blurLevel = 3;
		this.jaggedLineMean = 0;
		this.jaggedLineStandardDeviation = 0;
		this.leftMarginSize = 0;
		this.rightMarginSize = 0;

	}
	
	public Image(int h, int w){
		this.name = "";
		this.containsPoem = false;
		this.checkValue = false;
		this.vertical = h;
		this.horizontal = w;
		this.stanzaMean = 0;
		this.stanzaStandardDeviation = 0;
		this.blurLevel = 3;
		this.jaggedLineMean = 0;
		this.jaggedLineStandardDeviation = 0;
		this.leftMarginSize = 0;
		this.rightMarginSize = 0;
		this.byteImage = new int[h][w];
		this.byteImage2 = new int[h][w];
	}

	public int[][] getByteImage2() {
		return byteImage2;
	}

	public void setByteImage2(int[][] byteImage2) {
		this.byteImage2 = byteImage2;
	}

	public double getLeftMarginSize() {
		return leftMarginSize;
	}

	public void setLeftMarginSize(double leftMarginSize) {
		this.leftMarginSize = leftMarginSize;
	}

	public double getRightMarginSize() {
		return rightMarginSize;
	}

	public void setRightMarginSize(double rightMarginSize) {
		this.rightMarginSize = rightMarginSize;
	}

	public double getJaggedLineMean() {
		return jaggedLineMean;
	}

	public void setJaggedLineMean(double jaggedLineMean) {
		this.jaggedLineMean = jaggedLineMean;
	}

	public double getJaggedLineStandardDeviation() {
		return jaggedLineStandardDeviation;
	}

	public void setJaggedLineStandardDeviation(double jaggedLineStandardDeviation) {
		this.jaggedLineStandardDeviation = jaggedLineStandardDeviation;
	}

	public int getVertical() {
		return vertical;
	}

	public void setVertical(int vertical) {
		this.vertical = vertical;
	}

	public int getHorizontal() {
		return horizontal;
	}

	public void setHorizontal(int horizontal) {
		this.horizontal = horizontal;
	}



	public int getBlurLevel() {
		return blurLevel;
	}

	public void setBlurLevel(int blurLevel) {
		this.blurLevel = blurLevel;
	}

	public double getStanzaMean() {
		return stanzaMean;
	}

	public void setStanzaMean(double stanzaMean) {
		this.stanzaMean = stanzaMean;
	}

	public double getStanzaStdDev() {
		return stanzaStandardDeviation;
	}

	public void setStanzaStandardDeviation(double stanzaStandardDeviation) {
		this.stanzaStandardDeviation = stanzaStandardDeviation;
	}

	public Image(String fileName, boolean checkFigure){
		this.name = fileName;
		this.checkValue = checkFigure;

	}

	public boolean getCheckValue() {
		return checkValue;
	}
	public void setCheckValue(boolean checkValue) {
		this.checkValue = checkValue;
	}
	public int[][] getByteImage() {
		return byteImage;
	}
	public void setByteImage(int[][] byteImage) {
		this.byteImage = byteImage;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public boolean isContainsPoem() {
		return containsPoem;
	}
	public void setContainsPoem(boolean containsPoem) {
		this.containsPoem = containsPoem;
	}public ArrayList<Integer> getColumnBreaks(){
		return columnBreaks;
	}
	public void setColumnBreaks(ArrayList<Integer> columns){
		this.columnBreaks = columns;
	}
	/**
	 * Method used to find the separation columns of a newspaper.
	 * These columns can be represented visually by either whitespace separating text columns or
	 * by continuous, straight black lines.
	 */
	public void findColumnBreaks(){
		
		ArrayList<Integer> whiteColumns = new ArrayList<Integer>();
		int whiteCount;
		int columnCount = 0;
		for(int j = 0; j < this.horizontal; j++){
			whiteCount = 0;
			for(int i = 0; i < this.vertical; i++){
				if(this.byteImage[i][j] == 255){
					whiteCount++;
				}
			}
			if(whiteCount >= (this.vertical*.9)){
				columnCount++;
				if(columnCount < 50){
					whiteColumns.add(j);
				}
			}else if(whiteCount < (this.vertical*.9)){
				if(whiteColumns.contains(j-1)){
					columnCount = 0;
				}else if(!whiteColumns.contains(j-1) && columnCount >= 50){
					columnCount = 0;
				}
			}
		}
		
		
		System.out.println(whiteColumns);
		ArrayList<Integer> columns = new ArrayList<Integer>();
		int marker = 0;
		/*Search for the part of the array that makes a large jump, this indicates the end of
		 *a section of white columns and the beginning of a new section. Once found we find the 
		 *middle index of the section of white columns and then set the marker to be the first 
		 *white column of the next section of white columns.
		 */
		for(int k = 0; k < whiteColumns.size()-1; k++){
			if(whiteColumns.get(k+1) - whiteColumns.get(k) > 150){
				int index = k-((k - marker)/2);
				columns.add(whiteColumns.get(index));
				marker = k+1;
			}
			//Special condition used to find middle point of the final batch of white columns
			//(the far right hand side of the newspaper)
			if(k+1 == whiteColumns.size()-1){
				int index = (k+1)-(((k+1) - marker)/2);
				columns.add(whiteColumns.get(index));
			}
		}
		
		int count = 0;
		int mean = 0;
		for(int p = 0; p < columns.size()-1; p++){
			mean += (columns.get(p+1)-columns.get(p));
			count++;
		}
		mean = mean/count;
		System.out.println("Mean: "+mean);
		int vari = 0;
		for(int p = 0; p < columns.size()-1; p++){
			vari += Math.pow((columns.get(p+1)-columns.get(p))-mean, 2);
		}
		System.out.println("Vari: "+vari);
		System.out.println("Count: "+count);
		double stdDev = Math.ceil(Math.sqrt(vari/count));
		System.out.println("StdDev: "+stdDev);
		
		int check = 0;
		for(int l = 0; l < columns.size()-1; l++){
			int dist = columns.get(l+1)-columns.get(l);
			if(dist < mean-stdDev || dist > mean+stdDev){
				check++;
			}
		}
//		if(check > 0){
			ArrayList<Integer> blackColumns = new ArrayList<Integer>();
			for(int m = 0; m < this.horizontal; m++){
				int blackContinuous = 0;
				int maxBlack = 0;
				int prev = 255;
				for(int n = 0; n < this.vertical; n++){
					if(this.byteImage[n][m] == 0 && prev == 0){
						blackContinuous++;
					}else if(this.byteImage[n][m] != 0 && prev == 0){
						if(maxBlack < blackContinuous){
							maxBlack = blackContinuous;
						}
						blackContinuous = 0;
						prev = 255;
					}else if(this.byteImage[n][m] == 0 && prev == 255){
						blackContinuous++;
						prev = 0;
					}
				}
				if(maxBlack >= (this.vertical * .1) && m > columns.get(0)){
					blackColumns.add(m);
				}
			}
			marker = 0;
			for(int k = 0; k < blackColumns.size()-1; k++){
				if(blackColumns.get(k+1) - blackColumns.get(k) > 125){
					int index = k-((k - marker)/2);
					columns.add(blackColumns.get(index));
					marker = k+1;
				}
			}
			System.out.println("Black Columns "+blackColumns);
			Collections.sort(columns);
//		}
		
		ArrayList<Integer> columnsToAdd = new ArrayList<Integer>();
		ArrayList<Integer> columnsToRemove = new ArrayList<Integer>();
		
		int index = 0;
		while(columns.get(index) < 100){
			columnsToRemove.add(columns.get(index));
			index++;
		}
		index = 1;
		while(this.horizontal-columns.get(columns.size()-index) < 100){
			columnsToRemove.add(columns.get(columns.size()-index));
			index++;
		}
		columns.removeAll(columnsToRemove);
		columnsToRemove.clear();
		for(int p = 0; p < columns.size()-1; p++){
			if(columns.get(p+1)-columns.get(p) < 75){
				columnsToRemove.add(columns.get(p));
				columnsToRemove.add(columns.get(p+1));
				int middle = columns.get(p+1) - ((columns.get(p+1)-columns.get(p))/2);
				columnsToAdd.add(middle);
			}
		}
		
		columns.removeAll(columnsToRemove);
		columns.addAll(columnsToAdd);
		Collections.sort(columns);
		//System.out.println(columns);
		columnsToRemove.clear();
		
		count = 0;
		mean = 0;
		for(int p = 0; p < columns.size()-1; p++){
			mean += (columns.get(p+1)-columns.get(p));
			count++;
		}
		mean = mean/count;
		System.out.println("Mean: "+mean);
		vari = 0;
		for(int p = 0; p < columns.size()-1; p++){
			vari += Math.pow((columns.get(p+1)-columns.get(p))-mean, 2);
			
		}
		stdDev = Math.ceil(Math.sqrt(vari/count));
		System.out.println("StdDev: "+stdDev);
		System.out.println(columns);
		for(int p = 1; p < columns.size()-1; p++){
			if(columns.get(p)-columns.get(p-1) < mean-10){
				if(columns.get(p+1)-columns.get(p-1) < mean+(stdDev) && columns.get(p+1)-columns.get(p-1) > mean-stdDev){
//					columnsToRemove.add(columns.get(p));
					columns.remove(p);
					p--;
				}
			}
		}
		System.out.println(columns);
		ArrayList<Integer> columnWidth = new ArrayList<Integer>();
		for(int p = 0; p < columns.size()-1; p++){
			columnWidth.add(columns.get(p+1)-columns.get(p));
		}
		Collections.sort(columnWidth);
		System.out.println(columnWidth);
		int averageWidth = columnWidth.get((int) Math.floor(columnWidth.size()/2));
		System.out.println("Average Width: "+averageWidth);
		for(int p = columns.size()-1; p >= 1; p--){
			if(columns.get(p-1) < columns.get(p)-averageWidth-75){
				columns.add(p, columns.get(p)-averageWidth);
				p++;
			}else if(columns.get(p-1) > columns.get(p)-averageWidth+75){
				columns.remove(p-1);
				if(columns.get(p-1)-averageWidth > 0){
					columns.add(p-1, columns.get(p-1)-averageWidth);
				}
			}
		}
		
		System.out.println(columns);
		if(columns.size()<3){
			throw new RuntimeException();
		}
		if(columns.get(0)>this.vertical/2){
			throw new RuntimeException();
		}
//		columns.removeAll(columnsToRemove);
		
		//stores the column breaks list in the class Image
		this.setColumnBreaks(columns);
	}
	/**
	 * Method to output an image with red lines indicating the column breaks
	 */
	public void showColumnBreaks(){
		BufferedImage OutputImage = new BufferedImage(this.horizontal,this.vertical,BufferedImage.TYPE_INT_RGB);
		int i = 1;
		int marker = this.columnBreaks.get(0);
		int value = 0;
		for (int x = 0; x < this.getHorizontal(); x++) {
			//iterate and acquire column break marker at the correct time
			if(i < this.columnBreaks.size()){
				if(x > marker){
					marker = this.columnBreaks.get(i);
					i++;
				}
			}
			for (int y = 0; y < this.getVertical(); y++) {
				if(x == marker){
					value = 0xFF0000;//hexadecimal code for the color red
				}else{
					//The following line offsets the pixels' values to fix the 'blue problem'
					value = this.byteImage2[y][x] << 16 | this.byteImage2[y][x] << 8 | this.byteImage2[y][x];
				}
				OutputImage.setRGB(x, y, value);
			}
		}
		//Output the image to a file of our choosing
		File outputFile = new File(Constants.binaryOutput,this.name);
		try {
			ImageIO.write(OutputImage, "jpg", outputFile);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	/**
	 * Helper method that will compare matricies to ensure that they are the same.
	 * Used for debugging purposes.
	 * @param A matrix
	 * @param B matrix
	 * @return number of different pixels
	 */
	public int compareMatrices(int[][] A, int[][] B){
		int difference = 0;
		for(int i = 0; i < A.length; i++){
			for(int j = 0; j < A[0].length; j++){
				if(A[i][j] != B[i][j]){
					difference++;
				}
			}
		}
		return difference;
	}
	
	private int snippetHeight(){
		int sum = 0;
		for(int i = 0; i < this.columnBreaks.size(); i++){
			sum += this.columnBreaks.get(i);
		}
		int avgWidth = sum/this.columnBreaks.size();
		int avgHeight = (int) ((14.0/9.0)*avgWidth);
		return avgHeight;
	}
	
	/**
	 * Final step in segmentation algorithm. Once column breaks have been found this method
	 * will use those breakpoints to dynamically create snippets of varying width and constant height.
	 * Snippets are outputted to the directory noted by Constants.Snippets and are grouped together by the full page they came from.
	 */
	public void convertPageToSnippets(){
		int height = snippetHeight();
		int nextBegin = 0;
		int nextEnd = height;
		int snippetRow = 0;
		int snippetColumn = 0;
		for(int i = 0; i < columnBreaks.size()-1; i++){
			int[][] snippet = new int[height][columnBreaks.get(i+1) - columnBreaks.get(i)];
			int c = 0;
			for(int j = columnBreaks.get(i); j < columnBreaks.get(i+1); j++){
				int r = 0;
				for(int k = nextBegin; k < nextEnd; k++){
					snippet[r][c] = this.byteImage2[k][j];
					r++;
				}
				c++;
			}
			
			String snippetSubName = this.getName().substring(0, this.getName().lastIndexOf('.'));
			String snippetName = snippetSubName+"_"+snippetRow+"_"+snippetColumn+".jpg";
			
			BufferedImage OutputImage = new BufferedImage(columnBreaks.get(i+1) - columnBreaks.get(i), height, BufferedImage.TYPE_INT_RGB);
			for (int y = 0; y < height; y++) {
				for (int x = 0; x < columnBreaks.get(i+1) - columnBreaks.get(i); x++) {
					//The following line offsets the pixels' values to fix the 'blue problem'
					int value = snippet[y][x] << 16 | snippet[y][x] << 8 | snippet[y][x];
					OutputImage.setRGB(x, y, value);
				}
			}
			
			//Output the snippet to a file of our choosing
			File outputFile = new File(Constants.Snippets,snippetName);
			outputFile.mkdirs();
			try {
				ImageIO.write(OutputImage, "jpg", outputFile);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			snippetColumn++;
			
			if(i == columnBreaks.size() - 2){
				nextBegin = nextEnd - (height/2);
				nextEnd = nextBegin + height;
				if(nextEnd <= this.getVertical()){
					i = -1;
					snippetRow++;
					snippetColumn = 0;
				}
			}
		}
	}
	
	public void scaleDown(int scale){
		int r = 0,s = 0;
		int[][] scaledImage = new int[this.vertical/scale][this.horizontal/scale];
		for(int i = (int)Math.ceil(scale/2); i < this.vertical - (int)Math.ceil(scale/2); i = i+scale){
			s = 0;
			for(int j = (int)Math.ceil(scale/2); j < this.horizontal - (int)Math.ceil(scale/2); j = j+scale){
				scaledImage[r][s] = average(scale, i, j);
				s++;
			}
			r++;
		}
		this.setByteImage(scaledImage);
		System.out.println("SET");
		this.setByteImage2(scaledImage);
		this.setVertical(r);
		this.setHorizontal(s);
	}
	private int average(int scale, int i, int j){
		int sum = 0;
		for(int a = i-(scale/2); a < i+(scale/2); a++){
			for(int b = j-(scale/2); b < j+(scale/2); b++){
				sum+=this.byteImage[i][j];
			}
		}
		return sum/(scale*scale);
	}

	public void printImage(String filePath){
		int w = this.getHorizontal(),h = this.getVertical();
		BufferedImage OutputImage = new BufferedImage(w,h,BufferedImage.TYPE_INT_RGB);
		int[][] pixels3 = this.getByteImage();
		for (int y = 0; y < this.getVertical(); y++) {
			for (int x = 0; x < this.getHorizontal(); x++) {
				//The following line offsets the pixels' values to fix the 'blue problem'
				int value = pixels3[y][x] << 16 | pixels3[y][x] << 8 | pixels3[y][x];
				OutputImage.setRGB(x, y, value);
			}
		} 
		
		File outputFile = new File(filePath,this.name);
		try {
			ImageIO.write(OutputImage, "jpg", outputFile);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
