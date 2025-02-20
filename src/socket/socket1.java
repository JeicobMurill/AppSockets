

package socket;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

public class socket1 extends javax.swing.JFrame implements Runnable {

    public socket1() {
        initComponents();
        this.setTitle("Persona 1");
        this.setVisible(true);

        new Thread(this).start();
    }

    public void run() {
        try {
            ServerSocket servidor = new ServerSocket(8888);
            jTextArea1.append("Escuchando en puerto 8888...\n");
            while (true) {
                Socket socket = servidor.accept();
                DataInputStream entrada = new DataInputStream(socket.getInputStream());
                String mensaje = entrada.readUTF();
                jTextArea1.append("Persona 2: " + mensaje + "\n");
                socket.close();
            }
        } catch (IOException ex) {
            Logger.getLogger(socket1.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void botonActionPerformed(java.awt.event.ActionEvent evt) {                                         
        try {
            Socket conexion = new Socket("127.0.0.1", 9999);
            DataOutputStream salida = new DataOutputStream(conexion.getOutputStream());
            String msg = texto.getText();
            salida.writeUTF(msg);
            jTextArea1.append("Yo: " + msg + "\n");
            conexion.close();
        } catch (IOException ex) {
            Logger.getLogger(socket1.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
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
     
    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(() -> new socket1().setVisible(true));
    }
}
