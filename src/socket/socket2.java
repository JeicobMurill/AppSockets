

package socket;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

public class socket2 extends javax.swing.JFrame implements Runnable {

    public socket2() {
        initComponents();
        this.setTitle("Persona 2");
        this.setVisible(true);
        new Thread(this).start();
    }
    
    public void run() {
        try {
            ServerSocket servidor = new ServerSocket(9999);
            jTextArea1.append("Escuchando en puerto 9999...\n");
            while (true) {
                Socket socket = servidor.accept();
                DataInputStream entrada = new DataInputStream(socket.getInputStream());
                String mensaje = entrada.readUTF();
                jTextArea1.append("Persona 1: " + mensaje + "\n");
                socket.close();
            }
        } catch (IOException ex) {
            Logger.getLogger(socket2.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void botonActionPerformed(java.awt.event.ActionEvent evt) {                                         
        try {
            Socket conexion = new Socket("127.0.0.1", 8888); // Se conecta al puerto de Persona 1
            DataOutputStream salida = new DataOutputStream(conexion.getOutputStream());
            String msg = texto.getText();
            salida.writeUTF(msg);
            jTextArea1.append("Yo: " + msg + "\n");
            conexion.close();
        } catch (IOException ex) {
            Logger.getLogger(socket2.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    
    // Código generado por NetBeans (puedes modificarlo con el GUI Builder o personalizarlo)
    @SuppressWarnings("unchecked")
    private void initComponents() {
        texto = new javax.swing.JTextField();
        boton = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTextArea1 = new javax.swing.JTextArea();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);


        boton.setText("Enviar");
        boton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botonActionPerformed(evt);
            }
        });

        jTextArea1.setColumns(20);
        jTextArea1.setRows(5);
        jScrollPane1.setViewportView(jTextArea1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 380, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(texto)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(boton)))
                .addGap(19, 19, 19))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(texto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(boton))
                .addContainerGap(30, Short.MAX_VALUE))
        );

        pack();
    }
    private javax.swing.JButton boton;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextArea jTextArea1;
    private javax.swing.JTextField texto;
}
