/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ImageProcessing;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.HashSet;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;

/**
 *
 * @author Ryan Fadholi
 */
public final class OlahCitra {
    //Objek Gambar yang akan diolah
    private static BufferedImage leftImage = null;
    private static BufferedImage rightImage = null;
    private static BufferedImage tempImage = null;
    
    private static int[] getSourceMatrix(int pixelWidth, int pixelHeight, char color){
        //[TIDAK UNTUK DIPANGGIL LANGSUNG, DIPANGGIL OLEH FUNGSI processFiltering().
        //Mengambil sebuah matriks pixel berukuran 3x3 dengan pixel di parameter 
        //pixelWidth dan pixelHeight sebagai elemen tengah.
        //Pixel diambil dari sebuah objek BufferedImage. Khusus implementasi ini
        //objek sumber adalah tempImage, yaitu sebuah kopi dari objek rightImage
        //tepat sebelum operasi yang sedang dijalankan.
        int[] source = new int[9];
        int sourceArrIndex=0;
       
        
        for (int i= pixelHeight-1; i <= pixelHeight+1; i++) {
            for (int j = pixelWidth-1; j <= pixelWidth+1; j++) {
                Color currentPixel = new Color(tempImage.getRGB(j, i));
                switch(color){
                    case 'R':
                        source[sourceArrIndex] = currentPixel.getRed();
                        break;
                    case 'G':
                        source[sourceArrIndex] = currentPixel.getGreen();
                        break;
                    case 'B':
                        source[sourceArrIndex] = currentPixel.getBlue();
                        break;
                }
                sourceArrIndex++;
            }
        }
         return source;
    }
    private static int normalizeColor(int colorValue){
        //Normalisasi nilai sebuah integer, disini berupa sebuah nilai warna
        //dalam spektrum RGB dengan range 0-255.
        if(colorValue > 255){
            return 255;
        }
        if(colorValue < 0){
            return 0;
        }
        return colorValue;
    }
    private static int processFiltering(int pixelWidth, int pixelHeight, char color, double[] filter){
        int[] sourceMatrix = getSourceMatrix(pixelWidth, pixelHeight, color);
        double sum = 0;
        int result;
        
        //Operasi aplikasi filter
        for(int i=0;i<sourceMatrix.length;i++){
            sum += sourceMatrix[i] * filter[i];
        }
        
        result = normalizeColor((int)sum);
        return result;
    }
        
    private OlahCitra(){
        //Constructor private untuk mencegah instansiasi class OlahCitra
        //Di Main/Form.
    }

    //Getter dan Setter
    public static BufferedImage getLeftImage() {
        return leftImage;
    }

    public static BufferedImage getRightImage() {
        return rightImage;
    }

    public static boolean setImage(String fileDir){
        try {
            leftImage = ImageIO.read(new File(fileDir));
            tempImage = ImageIO.read(new File(fileDir));
            rightImage = ImageIO.read(new File(fileDir));
        } catch (IOException ex) {
            Logger.getLogger(OlahCitra_GUI.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
        return true;
    }
    
    public static void processGrayscale(){
        for (int i = 0; i < rightImage.getHeight(); i++) {
            for (int j = 0; j < rightImage.getWidth(); j++) {
                int rgb = rightImage.getRGB(j, i);
                Color currentPixel = new Color(rgb,true);
                int grayLevel = (currentPixel.getRed() 
                                    + currentPixel.getGreen() 
                                    + currentPixel.getBlue()) / 3;
                int gray = (grayLevel << 16) + (grayLevel << 8) + grayLevel; 
                rightImage.setRGB(j,i, gray);
            }
        }
    }
    
    public static void processFlipHorizontal(){
        for (int i=0; i < rightImage.getHeight(); i++) {
        for (int j = 0; j < rightImage.getWidth()/2; j++) {
                int left_rgb = rightImage.getRGB(j, i);
                int right_rgb = rightImage.getRGB(rightImage.getWidth() - (j+1),i);
                
                rightImage.setRGB(j,i, right_rgb);
                rightImage.setRGB(rightImage.getWidth() - (j+1),i, left_rgb);
            }
        }
    }
    
    public static void processFlipVertical(){
        for (int i=0; i < rightImage.getHeight()/2; i++) {
        for (int j = 0; j < rightImage.getWidth(); j++) {
                int upper_rgb = rightImage.getRGB(j, i);
                int below_rgb = rightImage.getRGB(j,rightImage.getHeight() - (i+1));
                rightImage.setRGB(j,i, below_rgb);
                rightImage.setRGB(j,rightImage.getHeight() - (i+1), upper_rgb);
            }
        }
    }
    
    public static void switchImage(){
     for (int i= 0; i < leftImage.getHeight(); i++) {
            for (int j = 0; j < leftImage.getWidth(); j++) {
                int right_rgb = rightImage.getRGB(j, i);
                leftImage.setRGB(j,i, right_rgb);
            }
        }
    }
    
     
    public static void processConvolve(double[] filter){
        int filteredRed, filteredGreen, filteredBlue;
        setTempImage();
//        for(int i=0;i<filter.length;i++){
//            System.out.println(filter[i]);
//        }
  
       for (int i = 1; i < rightImage.getHeight()-1; i++) {
            for (int j = 1; j < rightImage.getWidth()-1; j++) {
                int rgb = rightImage.getRGB(j, i);
                Color currentPixel = new Color(rgb,true);
                filteredRed = processFiltering(j, i,'R', filter);
                filteredGreen = processFiltering(j, i, 'G', filter);
                filteredBlue = processFiltering(j, i, 'B', filter);
                int filteredRGB = (currentPixel.getAlpha() << 24) + (filteredRed << 16) 
                        + (filteredGreen << 8) + filteredBlue;
                rightImage.setRGB(j,i, filteredRGB);
            }
        }  
    }
    
    
    
    public static void resetImage(){
     for (int i= 0; i < rightImage.getHeight(); i++) {
            for (int j = 0; j < rightImage.getWidth(); j++) {
                int right_rgb = leftImage.getRGB(j, i);
                rightImage.setRGB(j,i, right_rgb);
            }
        }
    }
    
    public static void setTempImage(){
     for (int i= 0; i < rightImage.getHeight(); i++) {
            for (int j = 0; j < rightImage.getWidth(); j++) {
                int right_rgb = rightImage.getRGB(j, i);
                tempImage.setRGB(j,i, right_rgb);
            }
        }
    }
    
    public static void saveSourceImage(String fileLocation, String imageType){
        
        File temp = new File(fileLocation);
        
        try {
            ImageIO.write(leftImage, imageType, temp);
        } catch (IOException ex) {
            Logger.getLogger(OlahCitra_GUI.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
     public static void saveProcessedImage(String fileLocation, String imageType){
        
        File temp = new File(fileLocation);
        
        try {
            ImageIO.write(rightImage, imageType, temp);
        } catch (IOException ex) {
            Logger.getLogger(OlahCitra_GUI.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
