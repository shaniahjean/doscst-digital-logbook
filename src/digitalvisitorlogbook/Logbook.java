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
import javax.swing.Box;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.Timer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableColumn;
import utils.DB;

/**
 *
 * @author Krispy Kreme
 */
public class Logbook extends javax.swing.JFrame {

    /**
     * Creates new form Logbook
     */
    public Logbook() {
        initComponents();
        setLocationRelativeTo(null);
        showDate();
        showTime();
        itemTable();
        displayData();
        jButton_timeOut.setEnabled(false);
        jTable_logbook.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                try {

                    if (e.getButton() == MouseEvent.BUTTON1) {
                        if (e.getButton() == 1) {
                            int row = jTable_logbook.getSelectedRow();
                            if (row == -1) {
                            } else {
                                jTable_logbook.getValueAt(row, 0).toString();
                                jTextField_id.setText((jTable_logbook.getValueAt(row, 1).toString()));
                                jTextField_reason.setText((jTable_logbook.getValueAt(row, 2).toString()));
                                jTextField_host_visited.setText((jTable_logbook.getValueAt(row, 3).toString()));
                                jTable_logbook.getValueAt(row, 4).toString();
                                jTable_logbook.getValueAt(row, 5).toString();
                                jTable_logbook.getValueAt(row, 6).toString();

                                String query = "SELECT VIS_FIRSTNAME, VIS_LASTNAME FROM visitor WHERE VIS_ID=\"" + jTextField_id.getText() + "\"";
                                try {
                                    ResultSet rs = DB.query(query);
                                    if (rs.next()) {
                                        String lname = rs.getString("VIS_LASTNAME");
                                        String fname = rs.getString("VIS_FIRSTNAME");
                                        jTextField_name.setText(fname + " " + lname);
                                    }
                                } catch (SQLException ex) {
                                    Logger.getLogger(Visitor.class.getName()).log(Level.SEVERE, null, ex);
                                }
                                jTextField_id.setEnabled(false);
                                jTextField_name.setEnabled(false);
                                jTextField_reason.setEnabled(false);
                                jTextField_host_visited.setEnabled(false);
                                jButton_timeOut.setEnabled(true);
                                jButton_timeIn.setEnabled(false);
                            }
                        }
                    }
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(null, "Table column Visitor ID is empty. Please make sure to register the visitor first", "ERROR", JOptionPane.ERROR_MESSAGE);
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
        String[] columnNames = {"LOG NO.", "VISITOR ID", "REASON", "HOST VISITED", "TIME IN", "TIME OUT", "DATE"};
        jTable_logbook = new javax.swing.JTable(tableModel);
        jTable_logbook = new javax.swing.JTable(tableModel);
        jTable_logbook.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
        jTable_logbook.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        jTable_logbook.setShowGrid(true);
        jTable_logbook.setFillsViewportHeight(true);
        jTable_logbook.getTableHeader().setReorderingAllowed(false);
        jTable_logbook.setRowSelectionAllowed(true);
        jTable_logbook.setBackground(Color.getHSBColor(180, 244, 217));
        jTable_logbook.setFont(new java.awt.Font("Tahoma", 0, 12));
        jTable_logbook.setRowHeight(25);
        jScrollPane1.setViewportView(jTable_logbook);
        JTableHeader header = jTable_logbook.getTableHeader();
        header.setFont(new java.awt.Font("Century Gothic", 4, 11));
        header.setBackground(Color.cyan);
        header.setResizingAllowed(true);
        header.setPreferredSize(new Dimension(header.WIDTH, 23));
        for (int i = 0; i < columnNames.length;) {
            tableModel.addColumn(columnNames[i]);
            i++;
        }
        TableColumn[] column = new TableColumn[7];
        column[0] = jTable_logbook.getColumnModel().getColumn(0);
        column[0].setPreferredWidth(10);
        column[1] = jTable_logbook.getColumnModel().getColumn(1);
        column[1].setPreferredWidth(10);
        column[2] = jTable_logbook.getColumnModel().getColumn(2);
        column[2].setPreferredWidth(30);
        column[3] = jTable_logbook.getColumnModel().getColumn(3);
        column[3].setPreferredWidth(25);
        column[4] = jTable_logbook.getColumnModel().getColumn(4);
        column[4].setPreferredWidth(35);
        column[5] = jTable_logbook.getColumnModel().getColumn(5);
        column[5].setPreferredWidth(35);
        column[6] = jTable_logbook.getColumnModel().getColumn(6);
        column[6].setPreferredWidth(30);
    }

    private void displayData() {

        try {
            String displayQuery = "SELECT * FROM visitor_logbook";
            ResultSet rs = DB.query(displayQuery);

            Vector row = new Vector();
            while (rs.next()) {
                Vector column = new Vector();
                column.add(rs.getString("LOG_NO"));
                column.add(rs.getString("VIS_ID"));
                column.add(rs.getString("LOG_REASON"));
                column.add(rs.getString("LOG_HOST"));
                column.add(rs.getString("LOG_TIME_IN"));
                column.add(rs.getString("LOG_TIME_OUT"));
                column.add(rs.getString("LOG_DATE"));
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
        SimpleDateFormat s = new SimpleDateFormat("MM-dd-yyyy");
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

        jMenu3 = new javax.swing.JMenu();
        jMenu4 = new javax.swing.JMenu();
        jMenuItem1 = new javax.swing.JMenuItem();
        jPanel1 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable_logbook = new javax.swing.JTable();
        jLabel8 = new javax.swing.JLabel();
        jButton_timeOut1 = new javax.swing.JButton();
        jTextField_tablesearch = new javax.swing.JTextField();
        jLabel11 = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        jPanel4 = new javax.swing.JPanel();
        jLabel9 = new javax.swing.JLabel();
        jLabel_admin = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jButton_timeIn = new javax.swing.JButton();
        jTextField_search = new javax.swing.JTextField();
        jTextField_id = new javax.swing.JTextField();
        jTextField_reason = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        jTextField_host_visited = new javax.swing.JTextField();
        jLabel10 = new javax.swing.JLabel();
        jTextField_name = new javax.swing.JTextField();
        jButton_timeOut = new javax.swing.JButton();
        jLabel13 = new javax.swing.JLabel();
        menubar = new javax.swing.JMenuBar();
        jMenu2 = new javax.swing.JMenu();
        jMenu_date = new javax.swing.JMenu();
        jMenu_time = new javax.swing.JMenu();
        jMenu6 = new javax.swing.JMenu();

        jMenu3.setText("jMenu3");

        jMenu4.setText("jMenu4");

        jMenuItem1.setText("jMenuItem1");

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setName("DigiLog"); // NOI18N
        setUndecorated(true);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 255), 2));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel2.setBackground(new java.awt.Color(0, 51, 102));
        jPanel2.setForeground(new java.awt.Color(0, 51, 102));
        jPanel2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jTable_logbook.setModel(new javax.swing.table.DefaultTableModel(
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
        jScrollPane1.setViewportView(jTable_logbook);

        jPanel2.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 100, 620, 250));

        jLabel8.setBackground(new java.awt.Color(0, 51, 102));
        jLabel8.setFont(new java.awt.Font("Ink Free", 1, 24)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(255, 219, 77));
        jLabel8.setText("Visitor Log App");
        jPanel2.add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 50, 300, 40));

        jButton_timeOut1.setBackground(new java.awt.Color(0, 51, 102));
        jButton_timeOut1.setFont(new java.awt.Font("Century Gothic", 0, 15)); // NOI18N
        jButton_timeOut1.setForeground(new java.awt.Color(255, 255, 255));
        jButton_timeOut1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/Delete_30px.png"))); // NOI18N
        jButton_timeOut1.setText("CANCEL");
        jButton_timeOut1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton_timeOut1ActionPerformed(evt);
            }
        });
        jPanel2.add(jButton_timeOut1, new org.netbeans.lib.awtextra.AbsoluteConstraints(510, 360, -1, 40));

        jTextField_tablesearch.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField_tablesearchActionPerformed(evt);
            }
        });
        jTextField_tablesearch.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jTextField_tablesearchKeyReleased(evt);
            }
        });
        jPanel2.add(jTextField_tablesearch, new org.netbeans.lib.awtextra.AbsoluteConstraints(460, 10, 150, 30));

        jLabel11.setBackground(new java.awt.Color(0, 51, 102));
        jLabel11.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N
        jLabel11.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/Search_30px.png"))); // NOI18N
        jLabel11.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel11MouseClicked(evt);
            }
        });
        jPanel2.add(jLabel11, new org.netbeans.lib.awtextra.AbsoluteConstraints(620, 10, 30, -1));
        jPanel2.add(jPanel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(380, -10, -1, -1));

        jPanel4.setBackground(new java.awt.Color(255, 255, 255));
        jPanel2.add(jPanel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(460, 10, 190, 30));

        jLabel9.setBackground(new java.awt.Color(0, 51, 102));
        jLabel9.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N
        jLabel9.setForeground(new java.awt.Color(255, 255, 255));
        jLabel9.setText("Visitor I.D.:");
        jPanel2.add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(380, 10, 80, 30));

        jLabel_admin.setForeground(new java.awt.Color(0, 51, 102));
        jLabel_admin.setText("id");
        jPanel2.add(jLabel_admin, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 370, 50, 30));

        jPanel1.add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 0, 660, 420));

        jLabel7.setBackground(new java.awt.Color(0, 51, 102));
        jLabel7.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N
        jLabel7.setText("Reason:");
        jPanel1.add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 200, 70, -1));

        jButton_timeIn.setBackground(new java.awt.Color(0, 51, 102));
        jButton_timeIn.setFont(new java.awt.Font("Century Gothic", 0, 15)); // NOI18N
        jButton_timeIn.setForeground(new java.awt.Color(255, 255, 255));
        jButton_timeIn.setText("LOG IN");
        jButton_timeIn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton_timeInActionPerformed(evt);
            }
        });
        jPanel1.add(jButton_timeIn, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 350, 90, 40));

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
        jPanel1.add(jTextField_search, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 20, 170, 30));
        jPanel1.add(jTextField_id, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 90, 90, 30));
        jPanel1.add(jTextField_reason, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 230, 200, 30));

        jLabel6.setBackground(new java.awt.Color(0, 51, 102));
        jLabel6.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N
        jLabel6.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/Search_30px.png"))); // NOI18N
        jLabel6.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel6MouseClicked(evt);
            }
        });
        jPanel1.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 20, 40, -1));

        jLabel12.setBackground(new java.awt.Color(0, 51, 102));
        jLabel12.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N
        jLabel12.setText("Host to be visited:");
        jPanel1.add(jLabel12, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 270, 160, -1));
        jPanel1.add(jTextField_host_visited, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 300, 200, 30));

        jLabel10.setBackground(new java.awt.Color(0, 51, 102));
        jLabel10.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N
        jLabel10.setText("Visitor Name:");
        jPanel1.add(jLabel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 130, 120, -1));
        jPanel1.add(jTextField_name, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 160, 200, 30));

        jButton_timeOut.setBackground(new java.awt.Color(0, 51, 102));
        jButton_timeOut.setFont(new java.awt.Font("Century Gothic", 0, 15)); // NOI18N
        jButton_timeOut.setForeground(new java.awt.Color(255, 255, 255));
        jButton_timeOut.setText("LOG OUT");
        jButton_timeOut.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton_timeOutActionPerformed(evt);
            }
        });
        jPanel1.add(jButton_timeOut, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 350, -1, 40));

        jLabel13.setBackground(new java.awt.Color(0, 51, 102));
        jLabel13.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N
        jLabel13.setText("Visitor I.D. No:");
        jPanel1.add(jLabel13, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 70, 120, -1));

        getContentPane().add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, 420));

        menubar.setBackground(new java.awt.Color(255, 255, 255));
        menubar.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N
        menubar.setName("DigiLog"); // NOI18N
        menubar.setPreferredSize(new java.awt.Dimension(275, 40));

        jMenu2.setForeground(new java.awt.Color(0, 51, 102));
        jMenu2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/Home_32px.png"))); // NOI18N
        jMenu2.setFocusable(false);
        jMenu2.setFont(new java.awt.Font("Century Gothic", 0, 13)); // NOI18N
        jMenu2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jMenu2MouseClicked(evt);
            }
        });
        menubar.add(jMenu2);

        jMenu_date.setBackground(new java.awt.Color(0, 51, 102));
        jMenu_date.setForeground(new java.awt.Color(0, 51, 102));
        jMenu_date.setText("Date");
        jMenu_date.setEnabled(false);
        jMenu_date.setFont(new java.awt.Font("Century Gothic", 0, 13)); // NOI18N
        jMenu_date.setMargin(new java.awt.Insets(0, 10, 0, 0));
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

    private void jLabel6MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel6MouseClicked
        search();        // TODO add your handling code here:
    }//GEN-LAST:event_jLabel6MouseClicked

    private void jTextField_searchKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextField_searchKeyReleased
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            search();
        }        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField_searchKeyReleased

    private void jTextField_searchActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField_searchActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField_searchActionPerformed

    private void jButton_timeInActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton_timeInActionPerformed
        timeIn();        // TODO add your handling code here:
    }//GEN-LAST:event_jButton_timeInActionPerformed

    private void jButton_timeOutActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton_timeOutActionPerformed
        timeOut();        // TODO add your handling code here:
    }//GEN-LAST:event_jButton_timeOutActionPerformed

    private void jMenu6MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jMenu6MouseClicked
        int reply = JOptionPane.showConfirmDialog(null, "Sign out from your account?", "Digital Visitor Logbook v.1", JOptionPane.YES_NO_OPTION);
        if (reply == JOptionPane.YES_OPTION) {
            Login log = new Login();
            log.setVisible(true);
            dispose();
        }
        // TODO add your handling code here:
    }//GEN-LAST:event_jMenu6MouseClicked

    private void jButton_timeOut1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton_timeOut1ActionPerformed
        cancel();
// TODO add your handling code here:
    }//GEN-LAST:event_jButton_timeOut1ActionPerformed

    private void jTextField_tablesearchActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField_tablesearchActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField_tablesearchActionPerformed

    private void jTextField_tablesearchKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextField_tablesearchKeyReleased
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            searchTable();
        }       // TODO add your handling code here:
    }//GEN-LAST:event_jTextField_tablesearchKeyReleased

    private void jLabel11MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel11MouseClicked
        searchTable();        // TODO add your handling code here:
    }//GEN-LAST:event_jLabel11MouseClicked

    private void jMenu2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jMenu2MouseClicked
        Homepage home = new Homepage();
        home.getUser().setText(jLabel_admin.getText());
        home.setVisible(true);
        dispose();// TODO add your handling code here:
    }//GEN-LAST:event_jMenu2MouseClicked

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
            java.util.logging.Logger.getLogger(Logbook.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Logbook.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Logbook.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Logbook.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Logbook().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton_timeIn;
    private javax.swing.JButton jButton_timeOut;
    private javax.swing.JButton jButton_timeOut1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JLabel jLabel_admin;
    private javax.swing.JMenu jMenu2;
    private javax.swing.JMenu jMenu3;
    private javax.swing.JMenu jMenu4;
    private javax.swing.JMenu jMenu6;
    private javax.swing.JMenuItem jMenuItem1;
    private javax.swing.JMenu jMenu_date;
    private javax.swing.JMenu jMenu_time;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable_logbook;
    private javax.swing.JTextField jTextField_host_visited;
    private javax.swing.JTextField jTextField_id;
    private javax.swing.JTextField jTextField_name;
    private javax.swing.JTextField jTextField_reason;
    private javax.swing.JTextField jTextField_search;
    private javax.swing.JTextField jTextField_tablesearch;
    private javax.swing.JMenuBar menubar;
    // End of variables declaration//GEN-END:variables
  private void search() {
        String search = jTextField_search.getText().toUpperCase();

        String searchQuery = "SELECT * FROM visitor WHERE VIS_ID like '%" + search + "%' OR VIS_LASTNAME like'%" + search + "%' OR VIS_FIRSTNAME like '%" + search + "%'";
        try {
            ResultSet rs = DB.query(searchQuery);

            if (rs.next()) {
                String lastname = rs.getString("VIS_LASTNAME");
                String firstname = rs.getString("VIS_FIRSTNAME");
                int id = rs.getInt("VIS_ID");
                jTextField_name.setText(firstname + " " + lastname);
                jTextField_id.setText(String.valueOf(id));
            } else {
                JOptionPane.showMessageDialog(null, "Visitor is unregistered", "ERROR", JOptionPane.ERROR_MESSAGE);
            }

        } catch (SQLException ex) {
            Logger.getLogger(Logbook.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void timeIn() {
        String id = jTextField_id.getText();
        String reason = jTextField_reason.getText();
        String host = jTextField_host_visited.getText();
        String name = jTextField_name.getText();

        Date date = new Date();
        SimpleDateFormat t = new SimpleDateFormat("hh:mm:ss a");

        date = new Date();
        SimpleDateFormat d = new SimpleDateFormat("MM-dd-yyyy");

        String timeIn = t.format(date);
        String dateIn = d.format(date);

        System.out.println(timeIn);
        System.out.println(dateIn);

        String timeInQuery = "INSERT INTO visitor_logbook VALUES(NULL,(SELECT VIS_ID FROM visitor WHERE VIS_ID=\"" + id + "\"),\"" + reason + "\",\"" + host + "\",\"" + timeIn + "\",\"-------\",\"" + dateIn + "\")";
        try {
            DB.query(timeInQuery, true);
            JOptionPane.showMessageDialog(null, name + " has logged in at " + timeIn + " " + dateIn, "Digital Visitor Logbook v.1", JOptionPane.INFORMATION_MESSAGE);
            displayData();
            clear();
        } catch (SQLException ex) {
            Logger.getLogger(Logbook.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void timeOut() {
        String id = jTextField_id.getText();
        String name = jTextField_name.getText();

        Date date = new Date();
        SimpleDateFormat t = new SimpleDateFormat("hh:mm:ss a");

        date = new Date();
        SimpleDateFormat d = new SimpleDateFormat("MM-dd-yyyy");

        String timeOut = t.format(date);
        String dateOut = d.format(date);

        System.out.println(timeOut);
        System.out.println(dateOut);

        int row = jTable_logbook.getSelectedRow();
        String currentTimeIn = jTable_logbook.getValueAt(row, 4).toString();
        System.out.println(currentTimeIn);
        String currentDate = jTable_logbook.getValueAt(row, 6).toString();
        System.out.println(currentDate);

        String timeOutQuery = "UPDATE visitor_logbook SET LOG_TIME_OUT=\"" + timeOut + "\" WHERE VIS_ID=\"" + id + "\" AND LOG_TIME_IN =\"" + currentTimeIn + "\" AND LOG_DATE=\"" + currentDate + "\"";
        try {
            DB.query(timeOutQuery, true);
            JOptionPane.showMessageDialog(null, name + " has logged out at " + timeOut + " " + dateOut, "Digital Visitor Logbook v.1", JOptionPane.INFORMATION_MESSAGE);
            displayData();
            clear();
            jTextField_id.setEnabled(true);
            jTextField_name.setEnabled(true);
            jTextField_reason.setEnabled(true);
            jTextField_host_visited.setEnabled(true);
            jButton_timeIn.setEnabled(true);
            jButton_timeOut.setEnabled(false);
        } catch (SQLException ex) {
            Logger.getLogger(Logbook.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    private void clear() {
        jTextField_id.setText("");
        jTextField_reason.setText("");
        jTextField_host_visited.setText("");
        jTextField_name.setText("");
        jTextField_search.setText("");
    }

    private void cancel() {
        jButton_timeIn.setEnabled(true);
        jButton_timeOut.setEnabled(false);
        jTextField_id.setEnabled(true);
        jTextField_name.setEnabled(true);
        jTextField_reason.setEnabled(true);
        jTextField_host_visited.setEnabled(true);
        jTextField_id.setText("");
        jTextField_reason.setText("");
        jTextField_host_visited.setText("");
        jTextField_name.setText("");
        jTextField_search.setText("");
        displayData();
    }

    private void searchTable() {
        String searchTable = jTextField_tablesearch.getText();
        String query = "SELECT * FROM visitor_logbook WHERE VIS_ID =\"" + searchTable + "\"";

        try {
            ResultSet rs = DB.query(query);
            Vector row = new Vector();
            while (rs.next()) {
                Vector column = new Vector();
                column.add(rs.getString("LOG_NO"));
                column.add(rs.getString("VIS_ID"));
                column.add(rs.getString("LOG_REASON"));
                column.add(rs.getString("LOG_HOST"));
                column.add(rs.getString("LOG_TIME_IN"));
                column.add(rs.getString("LOG_TIME_OUT"));
                column.add(rs.getString("LOG_DATE"));
                row.add(column);
                tableModel.setRowCount(0);
                for (int i = 0; i < row.size(); i++) {
                    tableModel.addRow((Vector) row.get(i));
                }
            }
        } catch (Exception e) {
            System.out.println(e);
        }
        try {
            ResultSet rs = DB.query(query);
            if (!rs.next()) {

                JOptionPane.showMessageDialog(null, "Record not found", "ERROR", JOptionPane.ERROR_MESSAGE);
                return;

            }
        } catch (SQLException ex) {
            Logger.getLogger(Logbook.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

//getter
    public javax.swing.JLabel getUser() {
        return jLabel_admin;
    }

    //setter
    public void setUser(javax.swing.JLabel jLabel_admin) {
        this.jLabel_admin = jLabel_admin;
    }

}
