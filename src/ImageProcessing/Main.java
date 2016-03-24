/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ImageProcessing;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;

/**
 *
 * @author Ryan Fadholi
 */
public class Main {
    private static BufferedImage sourceImage;
    private static BufferedImage tempImage;
    private static BufferedImage processedImage;

    public static BufferedImage getSourceImage() {
        return sourceImage;
    }

    public static BufferedImage getProcessedImage() {
        return processedImage;
    }
    
    public static void saveSourceImage(String fileLocation, String imageType){
        
        File temp = new File(fileLocation);
        
        try {
            ImageIO.write(sourceImage, imageType, temp);
        } catch (IOException ex) {
            Logger.getLogger(ImageProcessing_GUI.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
     public static void saveProcessedImage(String fileLocation, String imageType){
        
        File temp = new File(fileLocation);
        
        try {
            ImageIO.write(processedImage, imageType, temp);
        } catch (IOException ex) {
            Logger.getLogger(ImageProcessing_GUI.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
     
    public static boolean setImage(String fileDir){
         try {
            sourceImage = ImageIO.read(new File(fileDir));
            tempImage = ImageIO.read(new File(fileDir));
            processedImage = ImageIO.read(new File(fileDir));
        } catch (IOException ex) {
            Logger.getLogger(ImageProcessing_GUI.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
        return true;
    }
    
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(ImageProcessing_GUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(ImageProcessing_GUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(ImageProcessing_GUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(ImageProcessing_GUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new ImageProcessing_GUI().setVisible(true);
            }
        });
    }
}
