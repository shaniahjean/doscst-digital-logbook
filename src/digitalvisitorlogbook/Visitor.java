/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package digitalvisitorlogbook;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.MessageFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableColumn;
import utils.DB;
import javax.swing.Box;
import javax.swing.Timer;

/**
 *
 * @author Krispy Kreme
 */
public class Visitor extends javax.swing.JFrame {

    /**
     * Creates new form AddVisitor
     */
    public Visitor() {
        initComponents();
        itemTable();
        displayData();
        showDate();
        showTime();
        setLocationRelativeTo(null);
        jTextField_fdoID.setEnabled(false);
        jTable_visitors.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (e.getButton() == MouseEvent.BUTTON1) {
                    if (e.getButton() == 1) {
                        int row = jTable_visitors.getSelectedRow();
                        if (row == -1) {
                        } else {
                            jTextField_ID.setText((jTable_visitors.getValueAt(row, 0)).toString());
                            jTextField_lname.setText((jTable_visitors.getValueAt(row, 1).toString()));
                            jTextField_fname.setText((jTable_visitors.getValueAt(row, 2).toString()));
                            jTextField_mname.setText((jTable_visitors.getValueAt(row, 3).toString()));
                            jTextField_email.setText((jTable_visitors.getValueAt(row, 4).toString()));
                            jTextField_phone.setText((jTable_visitors.getValueAt(row, 5).toString()));
                            String query = "SELECT FDO_ACC_ID FROM visitor WHERE VIS_ID=\"" + jTextField_ID.getText() + "\"";
                            try {
                                ResultSet rs = DB.query(query);
                                if (rs.next()) {
                                    int id = rs.getInt("FDO_ACC_ID");
                                    jTextField_fdoID.setText(String.valueOf(id));
                                }
                            } catch (SQLException ex) {
                                Logger.getLogger(Visitor.class.getName()).log(Level.SEVERE, null, ex);
                            }

                            jTextField_ID.setEnabled(false);
                        }
                    }
                }
            }

            @Override
            public void mousePressed(MouseEvent e) {

            }

            @Override
            public void mouseReleased(MouseEvent e) {

            }

            @Override
            public void mouseEntered(MouseEvent e) {

            }

            @Override
            public void mouseExited(MouseEvent e) {

            }

        });
    }
    DefaultTableModel tableModel = new DefaultTableModel() {
        public boolean isCellEditable(int row, int col) {
            if (col == 1) {
                return false;
            } else {
                return false;
            }
        }
    };

    public void itemTable() {
        String[] columnNames = {"VISITOR ID", "LAST NAME", "FIRST NAME", "MIDDLE NAME", "EMAIL", "PHONE NUMBER"};
        jTable_visitors = new javax.swing.JTable(tableModel);
        jTable_visitors = new javax.swing.JTable(tableModel);
        jTable_visitors.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
        jTable_visitors.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        jTable_visitors.setShowGrid(true);
        jTable_visitors.setFillsViewportHeight(true);
        jTable_visitors.getTableHeader().setReorderingAllowed(false);
        jTable_visitors.setRowSelectionAllowed(true);
        jTable_visitors.setBackground(Color.getHSBColor(180, 244, 217));
        jTable_visitors.setFont(new java.awt.Font("Tahoma", 0, 12));
        jTable_visitors.setRowHeight(25);
        jScrollPane1.setViewportView(jTable_visitors);
        JTableHeader header = jTable_visitors.getTableHeader();
        header.setFont(new java.awt.Font("Century Gothic", 4, 11));
        header.setBackground(Color.cyan);
        header.setResizingAllowed(true);
        header.setPreferredSize(new Dimension(header.WIDTH, 23));
        for (int i = 0; i < columnNames.length;) {
            tableModel.addColumn(columnNames[i]);
            i++;
        }
        TableColumn[] column = new TableColumn[6];
        column[0] = jTable_visitors.getColumnModel().getColumn(0);
        column[0].setPreferredWidth(20);
        column[1] = jTable_visitors.getColumnModel().getColumn(1);
        column[1].setPreferredWidth(25);
        column[2] = jTable_visitors.getColumnModel().getColumn(2);
        column[2].setPreferredWidth(25);
        column[3] = jTable_visitors.getColumnModel().getColumn(3);
        column[3].setPreferredWidth(25);
        column[4] = jTable_visitors.getColumnModel().getColumn(4);
        column[4].setPreferredWidth(30);
        column[5] = jTable_visitors.getColumnModel().getColumn(5);
        column[5].setPreferredWidth(20);
    }

    private void displayData() {

        try {
            String displayQuery = "SELECT VIS_ID,VIS_LASTNAME, VIS_FIRSTNAME, VIS_MIDDLENAME, VIS_EMAIL, VIS_PHONE FROM visitor";
            ResultSet rs = DB.query(displayQuery);

            Vector row = new Vector();
            while (rs.next()) {
                Vector column = new Vector();
                column.add(rs.getString("VIS_ID"));
                column.add(rs.getString("VIS_LASTNAME"));
                column.add(rs.getString("VIS_FIRSTNAME"));
                column.add(rs.getString("VIS_MIDDLENAME"));
                column.add(rs.getString("VIS_EMAIL"));
                column.add(rs.getString("VIS_PHONE"));
                row.add(column);
                tableModel.setRowCount(0);
                for (int i = 0; i < row.size(); i++) {
                    tableModel.addRow((Vector) row.get(i));
                }
            }
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    void showDate() {
        Date d = new Date();
        SimpleDateFormat s = new SimpleDateFormat("dd-MM-yyyy");
        jMenu_date.setText("| Date: " + s.format(d));
    }

    void showTime() {
        new Timer(0, new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                Date d = new Date();
                SimpleDateFormat s = new SimpleDateFormat("hh:mm:ss a");
                jMenu_time.setText("Time: " + s.format(d));
            }

        }
        ).start();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel7 = new javax.swing.JLabel();
        jTextField_fdoID = new javax.swing.JTextField();
        jTextField_fname = new javax.swing.JTextField();
        jLabel9 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable_visitors = new javax.swing.JTable();
        jButton3 = new javax.swing.JButton();
        jButton_delete = new javax.swing.JButton();
        jButton_update = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jLabel8 = new javax.swing.JLabel();
        jTextField_lname = new javax.swing.JTextField();
        jLabel10 = new javax.swing.JLabel();
        jTextField_mname = new javax.swing.JTextField();
        jLabel11 = new javax.swing.JLabel();
        jTextField_email = new javax.swing.JTextField();
        jTextField_phone = new javax.swing.JTextField();
        jLabel12 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        jTextField_ID = new javax.swing.JTextField();
        menubar = new javax.swing.JMenuBar();
        jMenu3 = new javax.swing.JMenu();
        jMenu_date = new javax.swing.JMenu();
        jMenu_time = new javax.swing.JMenu();
        jMenu6 = new javax.swing.JMenu();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setUndecorated(true);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 255), 2));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel7.setBackground(new java.awt.Color(0, 51, 102));
        jLabel7.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N
        jLabel7.setText("First name:");
        jPanel1.add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 230, 90, -1));

        jTextField_fdoID.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jTextField_fdoID.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField_fdoIDActionPerformed(evt);
            }
        });
        jTextField_fdoID.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jTextField_fdoIDKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                jTextField_fdoIDKeyTyped(evt);
            }
        });
        jPanel1.add(jTextField_fdoID, new org.netbeans.lib.awtextra.AbsoluteConstraints(390, 60, 70, 30));

        jTextField_fname.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                jTextField_fnameKeyTyped(evt);
            }
        });
        jPanel1.add(jTextField_fname, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 250, 200, 30));

        jLabel9.setBackground(new java.awt.Color(0, 51, 102));
        jLabel9.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N
        jLabel9.setText("Front Desk Account ID:");
        jPanel1.add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(227, 60, -1, 30));

        jPanel2.setBackground(new java.awt.Color(0, 51, 102));
        jPanel2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jTable_visitors.setModel(new javax.swing.table.DefaultTableModel(
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
        jScrollPane1.setViewportView(jTable_visitors);

        jPanel2.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 90, 560, 210));

        jButton3.setBackground(new java.awt.Color(0, 51, 102));
        jButton3.setForeground(new java.awt.Color(255, 255, 255));
        jButton3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/Available Updates_30px.png"))); // NOI18N
        jButton3.setText("UPDATE");
        jButton3.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jButton3.setContentAreaFilled(false);
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });
        jPanel2.add(jButton3, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 320, 110, 50));

        jButton_delete.setBackground(new java.awt.Color(0, 51, 102));
        jButton_delete.setForeground(new java.awt.Color(255, 255, 255));
        jButton_delete.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/Delete_30px.png"))); // NOI18N
        jButton_delete.setText("DELETE");
        jButton_delete.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jButton_delete.setContentAreaFilled(false);
        jButton_delete.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton_deleteActionPerformed(evt);
            }
        });
        jPanel2.add(jButton_delete, new org.netbeans.lib.awtextra.AbsoluteConstraints(300, 320, 110, 50));

        jButton_update.setBackground(new java.awt.Color(0, 51, 102));
        jButton_update.setForeground(new java.awt.Color(255, 255, 255));
        jButton_update.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/Checkmark_40px.png"))); // NOI18N
        jButton_update.setText("ADD");
        jButton_update.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jButton_update.setContentAreaFilled(false);
        jButton_update.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton_updateActionPerformed(evt);
            }
        });
        jPanel2.add(jButton_update, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 320, 100, 50));

        jButton2.setBackground(new java.awt.Color(0, 51, 102));
        jButton2.setForeground(new java.awt.Color(255, 255, 255));
        jButton2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/Broom_40px.png"))); // NOI18N
        jButton2.setText("CLEAR");
        jButton2.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jButton2.setContentAreaFilled(false);
        jButton2.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });
        jPanel2.add(jButton2, new org.netbeans.lib.awtextra.AbsoluteConstraints(440, 320, 100, 50));

        jPanel1.add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(490, 0, 580, 400));

        jLabel8.setBackground(new java.awt.Color(0, 51, 102));
        jLabel8.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N
        jLabel8.setText("Last name:");
        jPanel1.add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 170, 90, -1));

        jTextField_lname.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                jTextField_lnameKeyTyped(evt);
            }
        });
        jPanel1.add(jTextField_lname, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 190, 200, 30));

        jLabel10.setBackground(new java.awt.Color(0, 51, 102));
        jLabel10.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N
        jLabel10.setText("Middle name:");
        jPanel1.add(jLabel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 290, 120, -1));

        jTextField_mname.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                jTextField_mnameKeyTyped(evt);
            }
        });
        jPanel1.add(jTextField_mname, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 310, 200, 30));

        jLabel11.setBackground(new java.awt.Color(0, 51, 102));
        jLabel11.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N
        jLabel11.setText("Email Address:");
        jPanel1.add(jLabel11, new org.netbeans.lib.awtextra.AbsoluteConstraints(260, 110, 120, 20));
        jPanel1.add(jTextField_email, new org.netbeans.lib.awtextra.AbsoluteConstraints(260, 130, 200, 30));
        jPanel1.add(jTextField_phone, new org.netbeans.lib.awtextra.AbsoluteConstraints(260, 190, 200, 30));

        jLabel12.setBackground(new java.awt.Color(0, 51, 102));
        jLabel12.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N
        jLabel12.setText("Phone Number:");
        jPanel1.add(jLabel12, new org.netbeans.lib.awtextra.AbsoluteConstraints(260, 170, 130, 20));

        jLabel13.setBackground(new java.awt.Color(0, 51, 102));
        jLabel13.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N
        jLabel13.setText("Visitor I.D. No.:");
        jPanel1.add(jLabel13, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 110, 120, -1));

        jTextField_ID.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                jTextField_IDKeyTyped(evt);
            }
        });
        jPanel1.add(jTextField_ID, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 130, 200, 30));

        getContentPane().add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, -40, 1070, 400));

        menubar.setBackground(new java.awt.Color(255, 255, 255));
        menubar.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N
        menubar.setName("DigiLog"); // NOI18N
        menubar.setPreferredSize(new java.awt.Dimension(275, 40));

        jMenu3.setForeground(new java.awt.Color(0, 51, 102));
        jMenu3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/Home_32px.png"))); // NOI18N
        jMenu3.setFont(new java.awt.Font("Century Gothic", 0, 13)); // NOI18N
        jMenu3.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jMenu3MouseClicked(evt);
            }
        });
        menubar.add(jMenu3);

        jMenu_date.setBackground(new java.awt.Color(0, 51, 102));
        jMenu_date.setForeground(new java.awt.Color(0, 51, 102));
        jMenu_date.setText("Date");
        jMenu_date.setEnabled(false);
        jMenu_date.setFont(new java.awt.Font("Century Gothic", 0, 13)); // NOI18N
        menubar.add(jMenu_date);

        jMenu_time.setBackground(new java.awt.Color(0, 51, 102));
        jMenu_time.setForeground(new java.awt.Color(0, 51, 102));
        jMenu_time.setText("Time");
        jMenu_time.setEnabled(false);
        jMenu_time.setFont(new java.awt.Font("Century Gothic", 0, 13)); // NOI18N
        menubar.add(jMenu_time);
        menubar.add(Box.createGlue());

        jMenu6.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/Cancel_32px.png"))); // NOI18N
        jMenu6.setContentAreaFilled(false);
        jMenu6.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jMenu6MouseClicked(evt);
            }
        });
        menubar.add(jMenu6);

        setJMenuBar(menubar);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton_updateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton_updateActionPerformed
        add();
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton_updateActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        clear();        // TODO add your handling code here:
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jTextField_fdoIDActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField_fdoIDActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField_fdoIDActionPerformed

    private void jTextField_fdoIDKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextField_fdoIDKeyReleased
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            add();
        }        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField_fdoIDKeyReleased

    private void jTextField_IDKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextField_IDKeyTyped
        char c = evt.getKeyChar();
        if (!(Character.isDigit(c) || c == KeyEvent.VK_BACK_SPACE || c == KeyEvent.VK_DELETE)) {
            getToolkit().beep();
            evt.consume();
        }        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField_IDKeyTyped

    private void jTextField_lnameKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextField_lnameKeyTyped
        jTextField_lname.setText(jTextField_lname.getText().toUpperCase());           // TODO add your handling code here:
    }//GEN-LAST:event_jTextField_lnameKeyTyped

    private void jTextField_fnameKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextField_fnameKeyTyped
        jTextField_fname.setText(jTextField_fname.getText().toUpperCase());             // TODO add your handling code here:
    }//GEN-LAST:event_jTextField_fnameKeyTyped

    private void jTextField_mnameKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextField_mnameKeyTyped
        jTextField_mname.setText(jTextField_mname.getText().toUpperCase());             // TODO add your handling code here:
    }//GEN-LAST:event_jTextField_mnameKeyTyped

    private void jTextField_fdoIDKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextField_fdoIDKeyTyped
        char c = evt.getKeyChar();
        if (!(Character.isDigit(c) || c == KeyEvent.VK_BACK_SPACE || c == KeyEvent.VK_DELETE)) {
            getToolkit().beep();
            evt.consume();
        }         // TODO add your handling code here:
    }//GEN-LAST:event_jTextField_fdoIDKeyTyped

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        update();        // TODO add your handling code here:
    }//GEN-LAST:event_jButton3ActionPerformed

    private void jButton_deleteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton_deleteActionPerformed
        delete();        // TODO add your handling code here:
    }//GEN-LAST:event_jButton_deleteActionPerformed

    private void jMenu6MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jMenu6MouseClicked
        Logbook log = new Logbook();
        log.getUser().setText(jTextField_fdoID.getText());
        log.setVisible(true);
        dispose();// TODO add your handling code here:
    }//GEN-LAST:event_jMenu6MouseClicked

    private void jMenu3MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jMenu3MouseClicked
        Homepage h = new Homepage();
        h.getUser().setText(jTextField_fdoID.getText());
        h.setVisible(true);
        dispose();// TODO add your handling code here:
    }//GEN-LAST:event_jMenu3MouseClicked

    /**
     * @param args the command line arguments
     */
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
            java.util.logging.Logger.getLogger(Visitor.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);

        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Visitor.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);

        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Visitor.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);

        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Visitor.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Visitor().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton_delete;
    private javax.swing.JButton jButton_update;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JMenu jMenu3;
    private javax.swing.JMenu jMenu6;
    private javax.swing.JMenu jMenu_date;
    private javax.swing.JMenu jMenu_time;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable_visitors;
    private javax.swing.JTextField jTextField_ID;
    private javax.swing.JTextField jTextField_email;
    private javax.swing.JTextField jTextField_fdoID;
    private javax.swing.JTextField jTextField_fname;
    private javax.swing.JTextField jTextField_lname;
    private javax.swing.JTextField jTextField_mname;
    private javax.swing.JTextField jTextField_phone;
    private javax.swing.JMenuBar menubar;
    // End of variables declaration//GEN-END:variables
private void add() {
        try {
            String id = jTextField_ID.getText();
            String lname = jTextField_lname.getText();
            String fname = jTextField_fname.getText();
            String mname = jTextField_mname.getText();
            String email = jTextField_email.getText();
            String phone = jTextField_phone.getText();
            String fdo = jTextField_fdoID.getText();

            String checkId = "SELECT VIS_ID FROM visitor WHERE VIS_ID=\"" + id + "\"";
            ResultSet rs = DB.query(checkId);
            if (rs.next()) {
                JOptionPane.showMessageDialog(null, "Visitor ID has been taken", "Digital Visitor Logbook v.1", JOptionPane.ERROR_MESSAGE);
                jTextField_ID.setText("");
                return;
            }
            if (id.equals("") || lname.equals("") || fname.equals("") || mname.equals("") || email.equals("") || phone.equals("") || fdo.equals("")) {
                JOptionPane.showMessageDialog(null, "Fill in all the fields", "Digital Visitor Logbook v.1", JOptionPane.ERROR_MESSAGE);
                return;
            }
            try {
                String checkAdmin = "SELECT FDO_ACC_ID FROM front_desk_officer_account WHERE FDO_ACC_ID=\"" + fdo + "\"";
                ResultSet rs2 = DB.query(checkAdmin);
                if (!rs2.next()) {
                    JOptionPane.showMessageDialog(null, "Receptionist ID does not exist", "ERROR", JOptionPane.ERROR_MESSAGE);
                    jTextField_fdoID.setText("");
                    return;
                }
            } catch (Exception e) {
                System.out.println(e);
            }
            try {
                String insertQuery = "INSERT INTO visitor VALUES(\"" + id + "\",\"" + lname + "\",\"" + fname + "\",\"" + mname + "\",\"" + email + "\",\"" + phone + "\",(SELECT FDO_ACC_ID FROM front_desk_officer_account WHERE FDO_ACC_ID=\"" + fdo + "\"))";
                DB.query(insertQuery, true);
                JOptionPane.showMessageDialog(null, "Visitor successfully added!", "Digital Visitor Logbook v.1", JOptionPane.INFORMATION_MESSAGE);
                displayData();
                clear();
            } catch (Exception e) {
                System.out.println(e);

            }

        } catch (SQLException ex) {
            Logger.getLogger(Visitor.class
                    .getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void update() {
        try {

            String id = jTextField_ID.getText();
            String lname = jTextField_lname.getText();
            String fname = jTextField_fname.getText();
            String mname = jTextField_mname.getText();
            String email = jTextField_email.getText();
            String phone = jTextField_phone.getText();

            String updateQuery = "UPDATE visitor SET VIS_LASTNAME=\"" + lname + "\", VIS_FIRSTNAME=\"" + fname + "\",VIS_MIDDLENAME =\"" + mname + "\", VIS_EMAIL=\"" + email + "\", VIS_PHONE=\"" + phone + "\" WHERE VIS_ID=\"" + id + "\"";
            DB.query(updateQuery, true);
            JOptionPane.showMessageDialog(null, "Information updated", "Digital Visitor Logbook", JOptionPane.INFORMATION_MESSAGE);
            displayData();

        } catch (SQLException ex) {
            Logger.getLogger(Visitor.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void delete() {
        String id = jTextField_ID.getText();
        String q = "DELETE FROM visitor_logbook WHERE VIS_ID=\"" + id + "\"";

        try {
            int reply = JOptionPane.showConfirmDialog(null, "Are you sure to delete this visitor? If you continue all his logs will also be deleted.", "WARNING", JOptionPane.WARNING_MESSAGE);
            if (reply == JOptionPane.YES_OPTION) {
                DB.query(q, true);
                String delete = "DELETE FROM visitor WHERE VIS_ID = \"" + id + "\"";
                DB.query(delete, true);
                JOptionPane.showMessageDialog(null, "Successfully Deleted", "Visitor Log App", JOptionPane.INFORMATION_MESSAGE);
                displayData();
                clear();
            } else {

            }
        } catch (SQLException ex) {
            Logger.getLogger(Visitor.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    private void clear() {
        jTextField_ID.setText("");
        jTextField_lname.setText("");
        jTextField_fname.setText("");
        jTextField_mname.setText("");
        jTextField_email.setText("");
        jTextField_phone.setText("");
        jTextField_ID.setEnabled(true);
    }
    //getter

    public javax.swing.JTextField getUser() {
        return jTextField_fdoID;
    }

    //setter
    public void setUser(javax.swing.JTextField jTextField_fdoID) {
        this.jTextField_fdoID = jTextField_fdoID;
    }
}
