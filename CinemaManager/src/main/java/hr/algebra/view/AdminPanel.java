/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package hr.algebra.view;

import hr.algebra.dal.Repository;
import hr.algebra.dal.RepositoryFactory;
import hr.algebra.model.Movie;
import hr.algebra.parsers.rss.MovieParser;
import hr.algebra.utilities.MessageUtils;
import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author matej.galic
 */
public class AdminPanel extends javax.swing.JPanel {

    private static final String ADMIN_PANEL = "Admin panel";
    private static final String ASSETS_PATH = "assets";

    /**
     * Creates new form AdminPanel
     */
    public AdminPanel() {
        initComponents();
        init();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        btnDeleteAllData = new javax.swing.JButton();
        btnUploadMovies = new javax.swing.JButton();

        btnDeleteAllData.setBackground(new java.awt.Color(255, 51, 51));
        btnDeleteAllData.setForeground(new java.awt.Color(255, 255, 255));
        btnDeleteAllData.setText("Delete all data");
        btnDeleteAllData.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDeleteAllDataActionPerformed(evt);
            }
        });

        btnUploadMovies.setBackground(new java.awt.Color(51, 153, 255));
        btnUploadMovies.setForeground(new java.awt.Color(255, 255, 255));
        btnUploadMovies.setText("Upload movies");
        btnUploadMovies.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnUploadMoviesActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(939, Short.MAX_VALUE)
                .addComponent(btnDeleteAllData, javax.swing.GroupLayout.PREFERRED_SIZE, 153, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(363, 363, 363))
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addGap(331, 331, 331)
                    .addComponent(btnUploadMovies, javax.swing.GroupLayout.PREFERRED_SIZE, 153, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(971, Short.MAX_VALUE)))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(461, Short.MAX_VALUE)
                .addComponent(btnDeleteAllData, javax.swing.GroupLayout.PREFERRED_SIZE, 57, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(342, 342, 342))
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                    .addContainerGap(458, Short.MAX_VALUE)
                    .addComponent(btnUploadMovies, javax.swing.GroupLayout.PREFERRED_SIZE, 57, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(345, 345, 345)))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void btnUploadMoviesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnUploadMoviesActionPerformed
        try {
            MovieParser.parse();
        } catch (Exception ex) {
            Logger.getLogger(AdminPanel.class.getName()).log(Level.SEVERE, null, ex);
            MessageUtils.showErrorMessage(ADMIN_PANEL, "Cannot upload movies!");
        }
    }//GEN-LAST:event_btnUploadMoviesActionPerformed

    private void btnDeleteAllDataActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDeleteAllDataActionPerformed
        if (MessageUtils.showConfirmDialog("Delete all data?", "Do you want to delete all data?")) {
            try {
                deleteAllData();
            } catch (Exception ex) {
                Logger.getLogger(AdminPanel.class.getName()).log(Level.SEVERE, null, ex);
                MessageUtils.showErrorMessage(ADMIN_PANEL, "Cannot delete all data!");
            }
        }
    }//GEN-LAST:event_btnDeleteAllDataActionPerformed

    private Repository repository;

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnDeleteAllData;
    private javax.swing.JButton btnUploadMovies;
    // End of variables declaration//GEN-END:variables

    private void init() {
        try {
            initRepository();
        } catch (Exception ex) {
            Logger.getLogger(AdminPanel.class.getName()).log(Level.SEVERE, null, ex);
            MessageUtils.showErrorMessage(ADMIN_PANEL, "Cannot initiate the form");
            System.exit(1);
        }
    }

    private void initRepository() {
        repository = RepositoryFactory.getRepository();
    }

    private void deleteAllData() throws Exception {
        repository.deleteAllData();
        deleteAssets(ASSETS_PATH);
    }

    private void deleteAssets(String path) {
        try {
            Path dir = Paths.get(path);

            if (Files.exists(dir) && Files.isDirectory(dir)) {
                File[] files = dir.toFile().listFiles();

                if (files != null) {
                    for (File file : files) {
                        if (file.isFile()) {
                            file.delete();
                        }
                    }
                }
            }
        } catch (Exception ex) {
            Logger.getLogger(AdminPanel.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
