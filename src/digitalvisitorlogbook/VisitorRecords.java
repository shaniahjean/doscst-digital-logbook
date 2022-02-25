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
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.Timer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableColumn;
import utils.DB;
import javax.swing.Box;
import javax.swing.JOptionPane;

/**
 *
 * @author Krispy Kreme
 */
public class VisitorRecords extends javax.swing.JFrame {

    /**
     * Creates new form VisitorRecords
     */
    public VisitorRecords() {
        initComponents();
        setLocationRelativeTo(null);
        showDate();
        showTime();
        itemTable();
        displayData();
        jTable_visitors.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (e.getButton() == MouseEvent.BUTTON1) {
                    if (e.getButton() == 1) {
                        int row = jTable_visitors.getSelectedRow();
                        if (row == -1) {
                        } else {
                            jTable_visitors.getValueAt(row, 0).toString();
                            jTable_visitors.getValueAt(row, 1).toString();
                            jTable_visitors.getValueAt(row, 2).toString();
                            jTable_visitors.getValueAt(row, 3).toString();
                            jTable_visitors.getValueAt(row, 4).toString();
                            jTable_visitors.getValueAt(row, 5).toString();
                            jTable_visitors.getValueAt(row, 6).toString();
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
        String[] columnNames = {"VISITOR ID", "LAST NAME", "FIRST NAME", "MIDDLE NAME", "EMAIL", "PHONE NUMBER", "RECEPTIONIST ID"};
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
        TableColumn[] column = new TableColumn[7];
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
        column[6] = jTable_visitors.getColumnModel().getColumn(6);
        column[6].setPreferredWidth(20);
    }

    private void displayData() {

        try {
            String displayQuery = "SELECT * FROM visitor";
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
                column.add(rs.getString("FDO_ACC_ID"));
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

        jPanel2 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable_visitors = new javax.swing.JTable();
        jButton_print = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        jTextField_search = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        jButton_cancel = new javax.swing.JButton();
        jLabel_recepID = new javax.swing.JLabel();
        menubar = new javax.swing.JMenuBar();
        jMenu3 = new javax.swing.JMenu();
        jMenu_date = new javax.swing.JMenu();
        jMenu_time = new javax.swing.JMenu();
        jMenu6 = new javax.swing.JMenu();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setUndecorated(true);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

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

        jPanel2.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 140, 750, 350));

        jButton_print.setBackground(new java.awt.Color(0, 51, 102));
        jButton_print.setForeground(new java.awt.Color(255, 255, 255));
        jButton_print.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/Print_30px.png"))); // NOI18N
        jButton_print.setText("PRINT");
        jButton_print.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jButton_print.setContentAreaFilled(false);
        jButton_print.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton_printActionPerformed(evt);
            }
        });
        jPanel2.add(jButton_print, new org.netbeans.lib.awtextra.AbsoluteConstraints(660, 500, 110, 50));

        jLabel2.setFont(new java.awt.Font("Century Gothic", 0, 20)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel2.setText("LIST OF VISITORS");
        jPanel2.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(310, 100, 210, -1));

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 255), 2));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jTextField_search.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField_searchActionPerformed(evt);
            }
        });
        jTextField_search.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jTextField_searchKeyReleased(evt);
            }
        });
        jPanel1.add(jTextField_search, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 150, 30));

        jLabel1.setFont(new java.awt.Font("Century Gothic", 0, 20)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/Search_30px.png"))); // NOI18N
        jLabel1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel1MouseClicked(evt);
            }
        });
        jPanel1.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 0, 30, -1));

        jPanel2.add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 90, 180, 30));

        jButton_cancel.setBackground(new java.awt.Color(0, 51, 102));
        jButton_cancel.setForeground(new java.awt.Color(255, 255, 255));
        jButton_cancel.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/Delete_30px.png"))); // NOI18N
        jButton_cancel.setText("CANCEL");
        jButton_cancel.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jButton_cancel.setContentAreaFilled(false);
        jButton_cancel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton_cancelActionPerformed(evt);
            }
        });
        jPanel2.add(jButton_cancel, new org.netbeans.lib.awtextra.AbsoluteConstraints(540, 500, 110, 50));

        jLabel_recepID.setForeground(new java.awt.Color(0, 51, 102));
        jPanel2.add(jLabel_recepID, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 510, 120, 20));

        getContentPane().add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, -40, 790, 560));

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

    private void jButton_printActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton_printActionPerformed
        MessageFormat header = new MessageFormat("LIST OF VISITORS");
        MessageFormat footer = new MessageFormat("----Digital Visitor Logbook v.1----");
        try {
            jTable_visitors.print(JTable.PrintMode.FIT_WIDTH, header, footer);
        } catch (java.awt.print.PrinterException e) {
            System.err.format("Cannot Print %s%n", e.getMessage());
        }
    }//GEN-LAST:event_jButton_printActionPerformed

    private void jMenu6MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jMenu6MouseClicked
        Logbook log = new Logbook();
        log.getUser().setText(jLabel_recepID.getText());
        log.setVisible(true);
        dispose();// TODO add your handling code here:
    }//GEN-LAST:event_jMenu6MouseClicked

    private void jTextField_searchKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextField_searchKeyReleased
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            searchTable();
        }       // TODO add your handling code here:
    }//GEN-LAST:event_jTextField_searchKeyReleased

    private void jLabel1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel1MouseClicked
        searchTable();        // TODO add your handling code here:
    }//GEN-LAST:event_jLabel1MouseClicked

    private void jTextField_searchActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField_searchActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField_searchActionPerformed

    private void jButton_cancelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton_cancelActionPerformed
        cancel();
// TODO add your handling code here:
    }//GEN-LAST:event_jButton_cancelActionPerformed

    private void jMenu3MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jMenu3MouseClicked
        Homepage h = new Homepage();
        h.getUser().setText(jLabel_recepID.getText());
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
            java.util.logging.Logger.getLogger(VisitorRecords.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(VisitorRecords.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(VisitorRecords.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(VisitorRecords.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new VisitorRecords().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton_cancel;
    private javax.swing.JButton jButton_print;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel_recepID;
    private javax.swing.JMenu jMenu3;
    private javax.swing.JMenu jMenu6;
    private javax.swing.JMenu jMenu_date;
    private javax.swing.JMenu jMenu_time;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable_visitors;
    private javax.swing.JTextField jTextField_search;
    private javax.swing.JMenuBar menubar;
    // End of variables declaration//GEN-END:variables
  private void searchTable() {
        String searchTable = jTextField_search.getText();

        try {
            String query = "SELECT * FROM visitor WHERE VIS_ID LIKE '%" + searchTable + "%' OR VIS_LASTNAME LIKE '%" + searchTable + "%' OR VIS_FIRSTNAME LIKE '%" + searchTable + "%'";
            ResultSet rs = DB.query(query);

            Vector row = new Vector();
            while (rs.next()) {
                Vector column = new Vector();
                column.add(rs.getString("VIS_ID"));
                column.add(rs.getString("VIS_LASTNAME"));
                column.add(rs.getString("VIS_FIRSTNAME"));
                column.add(rs.getString("VIS_MIDDLENAME"));
                column.add(rs.getString("VIS_EMAIL"));
                column.add(rs.getString("VIS_PHONE"));
                column.add(rs.getString("FDO_ACC_ID"));
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

    private void cancel() {
        displayData();
    }
    //getter

    public javax.swing.JLabel getUser() {
        return jLabel_recepID;
    }

    //setter
    public void setUser(javax.swing.JLabel jlabel_recepID) {
        this.jLabel_recepID = jLabel_recepID;
    }
}
