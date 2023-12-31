/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package hr.algebra.view;

import hr.algebra.dal.Repository;
import hr.algebra.dal.RepositoryFactory;
import hr.algebra.enums.Role;
import hr.algebra.model.Person;
import hr.algebra.utilities.MessageUtils;
import hr.algebra.view.model.PersonTableModel;
import java.util.Optional;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ListSelectionModel;

/**
 *
 * @author matej.galic
 */
public class CRUDPeoplePanel extends javax.swing.JPanel {

    private static final String CRUD_PEOPLE_PANEL = "People panel";

    /**
     * Creates new form EditPeoplePanel
     */
    public CRUDPeoplePanel() {
        initComponents();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jlPersonName = new javax.swing.JLabel();
        tfPersonName = new javax.swing.JTextField();
        btnAddPerson = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        cbPersonRole = new javax.swing.JComboBox<>();
        jScrollPane1 = new javax.swing.JScrollPane();
        tbPeople = new javax.swing.JTable();
        btnUpdatePerson = new javax.swing.JButton();
        btnDeletePerson = new javax.swing.JButton();
        jlPeopleInDatabase = new javax.swing.JLabel();

        setForeground(new java.awt.Color(255, 255, 255));
        addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                formMouseClicked(evt);
            }
        });
        addComponentListener(new java.awt.event.ComponentAdapter() {
            public void componentShown(java.awt.event.ComponentEvent evt) {
                formComponentShown(evt);
            }
        });

        jlPersonName.setText("Full name");

        btnAddPerson.setBackground(new java.awt.Color(0, 204, 0));
        btnAddPerson.setForeground(new java.awt.Color(255, 255, 255));
        btnAddPerson.setText("Add person");
        btnAddPerson.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAddPersonActionPerformed(evt);
            }
        });

        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("Role in movie");

        tbPeople.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        tbPeople.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbPeopleMouseClicked(evt);
            }
        });
        tbPeople.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                tbPeopleKeyReleased(evt);
            }
        });
        jScrollPane1.setViewportView(tbPeople);

        btnUpdatePerson.setBackground(new java.awt.Color(51, 153, 255));
        btnUpdatePerson.setForeground(new java.awt.Color(255, 255, 255));
        btnUpdatePerson.setText("Update person");
        btnUpdatePerson.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnUpdatePersonActionPerformed(evt);
            }
        });

        btnDeletePerson.setBackground(new java.awt.Color(255, 0, 0));
        btnDeletePerson.setForeground(new java.awt.Color(255, 255, 255));
        btnDeletePerson.setText("Delete person");
        btnDeletePerson.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDeletePersonActionPerformed(evt);
            }
        });

        jlPeopleInDatabase.setText("People in database:");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(147, 147, 147)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(btnAddPerson, javax.swing.GroupLayout.PREFERRED_SIZE, 158, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(btnUpdatePerson, javax.swing.GroupLayout.PREFERRED_SIZE, 158, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jlPersonName))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(tfPersonName)
                            .addComponent(cbPersonRole, javax.swing.GroupLayout.PREFERRED_SIZE, 161, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addComponent(btnDeletePerson, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(136, 136, 136)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jlPeopleInDatabase)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(378, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(69, 69, 69)
                .addComponent(jlPeopleInDatabase)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(155, 155, 155)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jlPersonName)
                            .addComponent(tfPersonName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel1)
                            .addComponent(cbPersonRole, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(btnAddPerson)
                            .addComponent(btnUpdatePerson))
                        .addGap(18, 18, 18)
                        .addComponent(btnDeletePerson)))
                .addContainerGap(336, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void btnAddPersonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAddPersonActionPerformed
        if (!formValid()) {
            return;
        }

        try {
            Person person = new Person(
                    tfPersonName.getText().trim(),
                    Role.fromString((String)cbPersonRole.getSelectedItem()));

            int createdPerson = repository.createPerson(person);

            if (createdPerson > 0) {
                MessageUtils.showInformationMessage(CRUD_PEOPLE_PANEL, "Successfully created person!");
            } else {
                MessageUtils.showErrorMessage(CRUD_PEOPLE_PANEL, "Some Error!");
            }

            personTableModel.setPeople(repository.selectPeople());
            clearFields();
            
        } catch (Exception ex) {
            Logger.getLogger(CRUDPeoplePanel.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_btnAddPersonActionPerformed

    private void formComponentShown(java.awt.event.ComponentEvent evt) {//GEN-FIRST:event_formComponentShown
        init();
        clearFields();
    }//GEN-LAST:event_formComponentShown

    private void tbPeopleMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbPeopleMouseClicked
        showPerson();
    }//GEN-LAST:event_tbPeopleMouseClicked

    private void tbPeopleKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tbPeopleKeyReleased
        showPerson();
    }//GEN-LAST:event_tbPeopleKeyReleased

    private void btnUpdatePersonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnUpdatePersonActionPerformed
        if (selectedPerson == null) {
            MessageUtils.showErrorMessage(CRUD_PEOPLE_PANEL, "Select person!");
            return;
        }
        if (!formValid()) {
            return;
        }

        updatePerson();
    }//GEN-LAST:event_btnUpdatePersonActionPerformed

    private void updatePerson() {
        try {
            selectedPerson.setName(tfPersonName.getText().trim());
            selectedPerson.setRole(Role.fromString((String) cbPersonRole.getSelectedItem()));
            
            repository.updatePerson(selectedPerson.getId(), selectedPerson);
            personTableModel.setPeople(repository.selectPeople());
            clearFields();
            
            MessageUtils.showInformationMessage(CRUD_PEOPLE_PANEL, "Person updated!");
        } catch (Exception ex) {
            Logger.getLogger(CRUDPeoplePanel.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void btnDeletePersonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDeletePersonActionPerformed
        if (selectedPerson == null) {
            MessageUtils.showErrorMessage(CRUD_PEOPLE_PANEL, "Select person!");
            return;
        }

        if (MessageUtils.showConfirmDialog("Delete person?", "Do you want to delete this person?")) {
            try {
                repository.deletePerson(selectedPerson.getId());
                personTableModel.setPeople(repository.selectPeople());
                clearFields();
            } catch (Exception ex) {
                Logger.getLogger(CRUDPeoplePanel.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

    }//GEN-LAST:event_btnDeletePersonActionPerformed

    private void formMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_formMouseClicked
        selectedPerson = null;
        clearFields();
    }//GEN-LAST:event_formMouseClicked


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAddPerson;
    private javax.swing.JButton btnDeletePerson;
    private javax.swing.JButton btnUpdatePerson;
    private javax.swing.JComboBox<String> cbPersonRole;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel jlPeopleInDatabase;
    private javax.swing.JLabel jlPersonName;
    private javax.swing.JTable tbPeople;
    private javax.swing.JTextField tfPersonName;
    // End of variables declaration//GEN-END:variables

    private Repository repository;

    private PersonTableModel personTableModel;

    private Person selectedPerson;

    private void init() {
        try {
            initRepository();
            initRolesInMovie();
            initTable();
        } catch (Exception ex) {
            Logger.getLogger(CRUDPeoplePanel.class.getName()).log(Level.SEVERE, null, ex);
            MessageUtils.showErrorMessage(CRUD_PEOPLE_PANEL, "Cannot initiate the form");
            System.exit(1);
        }
    }

    private void initRepository() {
        repository = RepositoryFactory.getRepository();
    }

    private void initRolesInMovie() {
        cbPersonRole.removeAllItems();
        cbPersonRole.addItem(Role.ACTOR.name());
        cbPersonRole.addItem(Role.DIRECTOR.name());
    }

    private void clearFields() {
        tfPersonName.setText("");
        cbPersonRole.setSelectedIndex(-1);
        selectedPerson = null;
    }

    private boolean formValid() {
        if (tfPersonName.getText().trim().isEmpty()) {
            MessageUtils.showInformationMessage(CRUD_PEOPLE_PANEL, "Enter username!");
            return false;
        }

        if (cbPersonRole.getSelectedIndex() == -1) {
            MessageUtils.showInformationMessage(CRUD_PEOPLE_PANEL, "Choose role!");
            return false;
        }

        return true;
    }

    private void initTable() throws Exception {
        tbPeople.setRowHeight(25);
        tbPeople.setAutoCreateRowSorter(true);
        tbPeople.setSelectionMode(
                ListSelectionModel.SINGLE_SELECTION);

        personTableModel = new PersonTableModel(
                repository.selectPeople());
        tbPeople.setModel(personTableModel);
    }

    private void showPerson() {
        clearFields();

        try {
            int selectedRow = tbPeople.getSelectedRow();
            int rowIndex = tbPeople.convertRowIndexToModel(selectedRow);
            int id = (int) personTableModel.getValueAt(rowIndex, 0);

            Optional<Person> optPerson = repository.selectPerson(id);
            if (optPerson.isPresent()) {
                selectedPerson = optPerson.get();
                fillForm(selectedPerson);
            }
        } catch (Exception ex) {
            MessageUtils.showErrorMessage(CRUD_PEOPLE_PANEL, "Some error!");
            Logger.getLogger(CRUDPeoplePanel.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void fillForm(Person person) {
        tfPersonName.setText(person.getName());
        cbPersonRole.setSelectedItem(person.getRole().name());
    }

}
