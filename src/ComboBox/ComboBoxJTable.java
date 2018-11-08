/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ComboBox;

import java.awt.event.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
/**
 *
 * @author Luis Fuentes <luisfuau10@gmail.com>
 */
public class ComboBoxJTable extends javax.swing.JFrame {

    /**
     * Creates new form ComboBox
     */
    public ComboBoxJTable() {
        initComponents();
        this.setLocationRelativeTo(null);
        
        Estado vista = new Estado();
        DefaultComboBoxModel modeloEstados = new DefaultComboBoxModel(vista.mostrarEstados());
        estados_cb.setModel(modeloEstados);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        estados_cb = new javax.swing.JComboBox<>();
        jScrollPane1 = new javax.swing.JScrollPane();
        municipios_jt = new javax.swing.JTable();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel1.setText("Estado");

        estados_cb.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                estados_cbItemStateChanged(evt);
            }
        });

        municipios_jt.setModel(new javax.swing.table.DefaultTableModel(
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
        jScrollPane1.setViewportView(municipios_jt);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(24, 24, 24)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 424, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(estados_cb, javax.swing.GroupLayout.PREFERRED_SIZE, 212, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(25, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(38, 38, 38)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel1)
                    .addComponent(estados_cb, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 219, Short.MAX_VALUE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void estados_cbItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_estados_cbItemStateChanged
        
        if(evt.getStateChange() == ItemEvent.SELECTED)
        {
            Estado estados = (Estado) estados_cb.getSelectedItem();
            
            try
        {
            DefaultTableModel modelo = new DefaultTableModel();
            municipios_jt.setModel(modelo);
            
            PreparedStatement ps;
            ResultSet rs;
            ConexionGeneral conexion = new ConexionGeneral("localhost", "3306", "mexico", "root", "1234");
            Connection con = conexion.getConnection();
        
            String sql = "SELECT id_municipio, municipio FROM t_municipio WHERE id_estado= " + estados.getId();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            
            ResultSetMetaData rsMd = rs.getMetaData();
            int cantidadColumnas = rsMd.getColumnCount();
            
            modelo.addColumn("ID");
            modelo.addColumn("Municipio");
            
            int[] ancho = {50, 100};
            
            for(int x=0; x<cantidadColumnas; x++)
            {
                municipios_jt.getColumnModel().getColumn(x).setPreferredWidth(ancho[x]);
            }
            
            while(rs.next())
            {
                Object[] filas = new Object[cantidadColumnas];
                
                for(int i=0; i<cantidadColumnas; i++)
                {
                    filas[i] = rs.getObject(i + 1);
                }
                
                modelo.addRow(filas);
            }
            
        }
        catch(SQLException ex)
        {
            System.err.println(ex.toString());
        }
        }
    }//GEN-LAST:event_estados_cbItemStateChanged

    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Windows".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        }
        catch (ClassNotFoundException | InstantiationException | IllegalAccessException | javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(ComboBoxJTable.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new ComboBoxJTable().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JComboBox<String> estados_cb;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable municipios_jt;
    // End of variables declaration//GEN-END:variables
}
