/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package citra;

import java.awt.Dimension;
import java.awt.Toolkit;

/**
 *
 * @author Ryan Fadholi
 */
public class SaveDialog_GUI extends javax.swing.JFrame {

    private String DialogTitle;
    private String imageType;
    private boolean isSavingProcessed;
    /**
     * Creates new form SaveDialog
     */
     /**
     * @param saveDir Direktori Penyimpanan
     * @param savingProcessed Image mana yang disimpan
     * @param imageType apakah BMP, JPG, atau PNG.
     */
    public SaveDialog_GUI(String saveDir, boolean savingProcessed, String imageType) {
        initComponents();
        
        this.isSavingProcessed = savingProcessed;
        this.imageType = imageType;
        
        tSaveDir.setText(saveDir + '\\');
        if(savingProcessed){
            DialogTitle = "Save Processed Image As " + imageType;
        }
        else{
            DialogTitle = "Save Source Image As " + imageType;
        }
        labelTitle.setText(DialogTitle);
        
        setDefaultCloseOperation(SaveDialog_GUI.HIDE_ON_CLOSE);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        labelDirectory = new javax.swing.JLabel();
        tSaveDir = new javax.swing.JTextField();
        labelTitle = new javax.swing.JLabel();
        labelFileName = new javax.swing.JLabel();
        tSaveName = new javax.swing.JTextField();
        btnSave = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowActivated(java.awt.event.WindowEvent evt) {
                formWindowActivated(evt);
            }
        });

        labelDirectory.setText("Directory:");

        tSaveDir.setEditable(false);
        tSaveDir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tSaveDirActionPerformed(evt);
            }
        });

        labelTitle.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        labelTitle.setText("Save File Title");

        labelFileName.setText("File Name:");

        tSaveName.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tSaveNameActionPerformed(evt);
            }
        });

        btnSave.setLabel("Save");
        btnSave.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSaveActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(labelTitle)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(labelFileName)
                            .addComponent(labelDirectory))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(tSaveDir, javax.swing.GroupLayout.DEFAULT_SIZE, 293, Short.MAX_VALUE)
                            .addComponent(tSaveName)))
                    .addComponent(btnSave))
                .addContainerGap(29, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(labelTitle)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(labelDirectory)
                    .addComponent(tSaveDir, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(labelFileName)
                    .addComponent(tSaveName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnSave)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void tSaveDirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tSaveDirActionPerformed

    }//GEN-LAST:event_tSaveDirActionPerformed

    private void tSaveNameActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tSaveNameActionPerformed
 
    }//GEN-LAST:event_tSaveNameActionPerformed

    private void btnSaveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSaveActionPerformed
        String saveImageDir;
        if(tSaveName == null){
        }
        else{
            saveImageDir = tSaveDir.getText() + tSaveName.getText() + '.' + imageType;
            if(isSavingProcessed){
                OlahCitra.saveProcessedImage(saveImageDir, imageType);
            }
            else{
                OlahCitra.saveSourceImage(saveImageDir, imageType);
            }
        }
        this.dispose();
    }//GEN-LAST:event_btnSaveActionPerformed

    private void formWindowActivated(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowActivated
        Dimension screensize = Toolkit.getDefaultToolkit().getScreenSize();
        Dimension framesize = getSize();
        setLocation((screensize.width - framesize.width)/2, (screensize.height - framesize.height)/2);
    }//GEN-LAST:event_formWindowActivated

   
//    public static void main(String args[]) {
//        /* Set the Nimbus look and feel */
//        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
//        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
//         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
//         */
//        try {
//            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
//                if ("Nimbus".equals(info.getName())) {
//                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
//                    break;
//                }
//            }
//        } catch (ClassNotFoundException ex) {
//            java.util.logging.Logger.getLogger(SaveDialog_GUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        } catch (InstantiationException ex) {
//            java.util.logging.Logger.getLogger(SaveDialog_GUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        } catch (IllegalAccessException ex) {
//            java.util.logging.Logger.getLogger(SaveDialog_GUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
//            java.util.logging.Logger.getLogger(SaveDialog_GUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        }
//        //</editor-fold>
//
//        /* Create and display the form */
//        java.awt.EventQueue.invokeLater(new Runnable() {
//            public void run() {
//                new SaveDialog_GUI().setVisible(true);
//            }
//        });
//    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnSave;
    private javax.swing.JLabel labelDirectory;
    private javax.swing.JLabel labelFileName;
    private javax.swing.JLabel labelTitle;
    private javax.swing.JTextField tSaveDir;
    private javax.swing.JTextField tSaveName;
    // End of variables declaration//GEN-END:variables
}
