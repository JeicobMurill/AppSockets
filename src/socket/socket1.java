/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package socket;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

public class socket1 extends javax.swing.JFrame {

    public socket1() {
        initComponents();
        this.setVisible(true);
    }

    private void initComponents() {
        texto = new javax.swing.JTextField();
        boton = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTextArea1 = new javax.swing.JTextArea();
        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        boton.setText("Enviar");
        boton.addActionListener(evt -> botonActionPerformed());
        jTextArea1.setColumns(20);
        jTextArea1.setRows(5);
        jScrollPane1.setViewportView(jTextArea1);
        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING).addGroup(layout.createSequentialGroup().addContainerGap().addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING).addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 380, Short.MAX_VALUE).addGroup(layout.createSequentialGroup().addComponent(texto).addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED).addComponent(boton))).addContainerGap()));
        layout.setVerticalGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING).addGroup(layout.createSequentialGroup().addContainerGap().addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE).addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED).addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE).addComponent(texto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE).addComponent(boton)).addContainerGap(30, Short.MAX_VALUE)));
        pack();
    }

    private void botonActionPerformed() {
        try {
            Socket conexion = new Socket("127.0.0.1", 9999);
            DataOutputStream salida = new DataOutputStream(conexion.getOutputStream());
            salida.writeUTF(texto.getText());
            // Recibir respuesta del servidor
            DataInputStream entrada = new DataInputStream(conexion.getInputStream());
            String respuesta = entrada.readUTF();
            jTextArea1.append("Servidor: " + respuesta + "\n");
            conexion.close();
        } catch (IOException ex) {
            Logger.getLogger(socket1.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(() -> new socket1().setVisible(true));
    }

    private javax.swing.JButton boton;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextArea jTextArea1;
    private javax.swing.JTextField texto;
}
