


import java.io.File; 
import java.io.IOException;
import java.util.ArrayList;
import java.awt.image.BufferedImage; 
import java.awt.image.Raster;

import javax.imageio.ImageIO; 


public class RunLengthCode {


	
	
	private static String ComputeCode(int[][] imgArr) {
		boolean flag = false;

		ArrayList<Integer> values = new ArrayList<>();
		
		String RLC = "";
		for (int row = 0; row < imgArr.length; row++) {
			
			for (int col = 0; col < imgArr[0].length; col++) {
				// this is used to get the headers
				if (imgArr[row][col] == 1 && flag == false) {
					RLC = RLC +"(" +row+" ";
					flag = true;
				}
				// this to add the one locations in an araylist
				if (imgArr[row][col] == 1 && flag == true) {
					values.add(col);
				}
			}
			// thi is used to check if the ones present in that row is consecutive or not
			if (!isConsecutive(values)) {
				
				// case they are not consecutive
				// smth is missing here
			
				RLC=RLC+values.get(0)+" ";
				for(int i=2;i<values.size();i++) {
					if(values.get(i-1)==(values.get(i))-1) {
						continue;
					}else {
						RLC=RLC+values.get(i-1)+" ";	
						RLC=RLC+values.get(i)+" ";
						
					}
				}
				RLC=RLC+values.get(values.size()-1)+")"+" ";
				
				
				
				// case they are conseutive numbers
			} else if(!values.isEmpty() && isConsecutive(values)) {
				RLC=RLC+values.get(0)+" ";
				int x = values.get(0);
				RLC=RLC+values.get(values.size() - 1)+")"+" ";
				int y = values.get(values.size() - 1);
			}else if(values.isEmpty()) {	
			
			}
			
			values.clear();
			flag = false;
		}
		return RLC;

	}
	
	
	
	
	// this method is used to check the consecutive numbers and return true or false
	public static boolean isConsecutive(ArrayList<Integer> x) {
		boolean flag = true;
		for (int i = 1; i < x.size(); i++) {
			if (x.get(i - 1) == (x.get(i)) - 1) {
				flag = true;
			} else {
				return false;
			}
		}
		return flag;
	}
	
	public static void main(String [] args) {
		//write image path 
		String path = "/Users/mohamedashraf/Documents/Projects/MediaRunLengthEncoding/binary_line.jpg";
		
		BufferedImage image = null;
		
		try {
			
			File input_image = new File(path);
			// Reading input image 
		    image = ImageIO.read(input_image);
		    System.out.println("Reading complete."); 
		} 
		
		catch (IOException e) {
		}
		
		// convert image to 2D array 
		int width = image.getWidth();
	    int height = image.getHeight();
	    int[][] imgArr = new int[height][width];
	    Raster raster = image.getData();
	    for (int i = 0; i < height; i++) {
	        for (int j = 0; j < width; j++) {
	            imgArr[i][j] = raster.getSample(j, i, 0);

	    }
	   }
		
	    
		// print run length Code 
		System.out.println(ComputeCode(imgArr));

	}//main ends here
	
}// class ends here 


