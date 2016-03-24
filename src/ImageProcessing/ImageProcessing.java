/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ImageProcessing;

import java.awt.Color;
import java.awt.image.BufferedImage;
import static java.lang.Math.sqrt;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.renderer.xy.XYItemRenderer;
import org.jfree.data.statistics.HistogramDataset;

/**
 *
 * @author Ryan Fadholi
 */
//Test github syncing
public final class ImageProcessing {
    
//    private static int[] getSourceMatrix(int pixelWidth, int pixelHeight, double[] colorArray){
//        //[TIDAK UNTUK DIPANGGIL LANGSUNG, DIPANGGIL OLEH FUNGSI processFiltering().
//        //Mengambil sebuah matriks pixel berukuran 3x3 dengan pixel di parameter 
//        //pixelWidth dan pixelHeight sebagai elemen tengah.
//        //Pixel diambil dari sebuah objek BufferedImage. Khusus implementasi ini
//        //objek sumber adalah tempImage, yaitu sebuah kopi dari objek rightImage
//        //tepat sebelum operasi yang sedang dijalankan.
//        int[] source = new int[9];
//        int sourceArrIndex=0;
//       
//        
//        for (int i= pixelHeight-1; i <= pixelHeight+1; i++) {
//            for (int j = pixelWidth-1; j <= pixelWidth+1; j++) {
//                source[sourceArrIndex] = colorArray[(i*imageWidth)+j];
//                sourceArrIndex++;
//            }
//        }
//         return source;
//    }
    private static int normalizeColor(int colorValue){
        //Ensures colorValue value to always stay at the range (0-255)
        if(colorValue > 255){
            return 255;
        }
        if(colorValue < 0){
            return 0;
        }
        return colorValue;
    }
    
    private static int processFiltering(int pixelWidth, int pixelHeight, 
                                        int imageWidth, int imageHeight, 
                                        double[] colorArray, double[] filter){
        
        int[] sourceMatrix = new int[filter.length];
        int matrixRange = (int) sqrt(filter.length)/2;
        int sourceArrIndex=0;
        
        for (int i= pixelHeight - matrixRange; i <= pixelHeight + matrixRange; i++) {
            for (int j = pixelWidth - matrixRange; j <= pixelWidth + matrixRange; j++) {
                sourceMatrix[sourceArrIndex] = (int) colorArray[(i*imageWidth)+j];
                sourceArrIndex++;
            }
        }
        
        double sum = 0;
        int result;
        
        //Operasi aplikasi filter
        for(int i=0;i<sourceMatrix.length;i++){
            sum += sourceMatrix[i] * filter[i];
        }
        
        result = normalizeColor((int)sum);
        return result;
    }
        
    private ImageProcessing(){
        //Constructor private untuk mencegah instansiasi class ImageProcessing
        //Di Main/Form.
    }

    public static JFreeChart createHistogram(double[] dataset_values, Color barColor){
        //Creates a histogram based on passed values and bar colors.        
        HistogramDataset dataset = new HistogramDataset();
        dataset.addSeries("Values", dataset_values, 255, 0, 255);
        
        JFreeChart chart = ChartFactory.createHistogram( null, //Title
                                                         null, //X Label
                                                         null, //Y Label
                                                      dataset, //Dataset
                org.jfree.chart.plot.PlotOrientation.VERTICAL, //Plot orientation
                                          true, false, false); //Other details
        
        //Remove chart legends to save space, we don't really need them anyway
        chart.removeLegend();
        //Set bar colors according to the parameter passed.
        XYItemRenderer renderer = chart.getXYPlot().getRenderer();
        renderer.setSeriesPaint(0, barColor);
        //Set background to null (the background will be similar to frame color in display.
        chart.setBackgroundPaint(null);
        
        return chart;
    }
    
    public static double[] extractAlphaValue(BufferedImage source){
        //Extracts the Alpha value from the RGB value of the source pixels.
        int imageWidth = source.getWidth();
        int imageHeight = source.getHeight();
        double[] values = new double[imageWidth * imageHeight];
        
         for (int i = 0; i < imageHeight; i++) {
            for (int j = 0; j < imageWidth; j++) {
                int rgbValue = source.getRGB(j, i);
                Color currentPixel = new Color(rgbValue,true);
                int value = currentPixel.getAlpha();
                values[(i*imageWidth)+j] = value;
            }
        }
         
        return values;
    }
    
    public static double[] extractBlueColor(BufferedImage source){
        //Extracts the Blue value from the RGB value of the source pixels.
        int imageWidth = source.getWidth();
        int imageHeight = source.getHeight();
        double[] values = new double[imageWidth * imageHeight];
        
         for (int i = 0; i < imageHeight; i++) {
            for (int j = 0; j < imageWidth; j++) {
                int rgbValue = source.getRGB(j, i);
                Color currentPixel = new Color(rgbValue,true);
                int value = currentPixel.getBlue();
                values[(i*imageWidth)+j] = value;
            }
        }
         
        return values;
    }
    
    public static double[] extractGrayColor(BufferedImage source){
        //Extracts the gray value from the pixels at the source by 
        //calculating the average of the RGB value at the given pixel.
        int imageWidth = source.getWidth();
        int imageHeight = source.getHeight();
        double[] values = new double[imageWidth * imageHeight];
        
         for (int i = 0; i < imageHeight; i++) {
            for (int j = 0; j < imageWidth; j++) {
                int rgbValue = source.getRGB(j, i);
                Color currentPixel = new Color(rgbValue,true);
                int value = (currentPixel.getRed() 
                                    + currentPixel.getGreen() 
                                    + currentPixel.getBlue()) / 3;
                values[(i*imageWidth)+j] = value;
            }
        }
         
        return values;
    }
    
    public static double[] extractGreenColor(BufferedImage source){
        //Extracts the Green value from the RGB value of the source pixels.
        int imageWidth = source.getWidth();
        int imageHeight = source.getHeight();
        double[] values = new double[imageWidth * imageHeight];
        
         for (int i = 0; i < imageHeight; i++) {
            for (int j = 0; j < imageWidth; j++) {
                int rgbValue = source.getRGB(j, i);
                Color currentPixel = new Color(rgbValue,true);
                int value = currentPixel.getGreen();
                values[(i*imageWidth)+j] = value;
            }
        }
         
        return values;
    }
    
    public static double[] extractRedColor(BufferedImage source){
        //Extracts the Red value from the RGB value of the source pixels.
        int imageWidth = source.getWidth();
        int imageHeight = source.getHeight();
        double[] values = new double[imageWidth * imageHeight];
        
         for (int i = 0; i < imageHeight; i++) {
            for (int j = 0; j < imageWidth; j++) {
                int rgbValue = source.getRGB(j, i);
                Color currentPixel = new Color(rgbValue,true);
                int value = currentPixel.getRed();
                values[(i*imageWidth)+j] = value;
            }
        }
         
        return values;
    }
    
    public static void processGrayscale(BufferedImage image){
        //Converts a color image to a Grayscale image.
        for (int i = 0; i < image.getHeight(); i++) {
            for (int j = 0; j < image.getWidth(); j++) {
                int rgb = image.getRGB(j, i);
                Color currentPixel = new Color(rgb,true);
                //Find the average from all the color components for the given pixel.
                int grayLevel = (currentPixel.getRed() 
                                    + currentPixel.getGreen() 
                                    + currentPixel.getBlue()) / 3;
                
                int gray = (grayLevel << 16) + (grayLevel << 8) + grayLevel; 
                image.setRGB(j,i, gray);
            }
        }
    }
    
    public static void processFlipHorizontal(BufferedImage image){
        //Flips image horizontally.
        for (int i=0; i < image.getHeight(); i++) {
        for (int j = 0; j < image.getWidth()/2; j++) {
                int left_rgb = image.getRGB(j, i);
                int right_rgb = image.getRGB(image.getWidth() - (j+1),i);
                
                image.setRGB(j,i, right_rgb);
                image.setRGB(image.getWidth() - (j+1),i, left_rgb);
            }
        }
    }
    
    public static void processFlipVertical(BufferedImage image){
        //Flips image vertically.
        for (int i=0; i < image.getHeight()/2; i++) {
        for (int j = 0; j < image.getWidth(); j++) {
                int upper_rgb = image.getRGB(j, i);
                int below_rgb = image.getRGB(j,image.getHeight() - (i+1));
                image.setRGB(j,i, below_rgb);
                image.setRGB(j,image.getHeight() - (i+1), upper_rgb);
            }
        }
    }
    
    public static void processConvolve(BufferedImage image, double[] filter){
        //Applies convolution operation using passed filter to the image.
        
        //Initialize values
        int alphaValue, filteredRed, filteredGreen, filteredBlue;
        int imageWidth  = image.getWidth();
        int imageHeight = image.getHeight();
        double[] temp_alpha = extractAlphaValue(image);
        double[] temp_red   = extractRedColor(image);
        double[] temp_green = extractGreenColor(image);
        double[] temp_blue  = extractBlueColor(image);
  
       //For every pixels (except top/bottom row & borderline left/right row,
       //Apply filter.
       for (int i = 1; i < imageHeight-1; i++) {
            for (int j = 1; j < imageWidth-1; j++) {
                alphaValue = (int) temp_alpha[(i*imageWidth)+j];
                //Apply filter to every color component (RGB)
                filteredRed = processFiltering(j, i, imageWidth, imageHeight, temp_red, filter);
                filteredGreen = processFiltering(j, i, imageWidth, imageHeight, temp_green, filter);
                filteredBlue = processFiltering(j, i, imageWidth, imageHeight, temp_blue, filter);
                //Copy the processed color values to a RGB integer using bitwise operator.
                int filteredRGB = (alphaValue << 24) + (filteredRed << 16) 
                        + (filteredGreen << 8) + filteredBlue;
                //Set the RGB back to the exact same pixel position. 
                image.setRGB(j,i, filteredRGB);
            }
        }  
    }
    
    public static void copyImage(BufferedImage source_image, BufferedImage target_image){
        //Copies pixel data from source image to target image.
        //The size will not be copied/adjusted, so keep in mind the size of both images.
     for (int i= 0; i < target_image.getHeight(); i++) {
            for (int j = 0; j < target_image.getWidth(); j++) {
                int rgb = source_image.getRGB(j, i);
                target_image.setRGB(j,i, rgb);
            }
        }
    }
    
}
    
   