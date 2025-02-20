

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
        this.setVisible(true);
        new Thread(this).start();
    }

    private void initComponents() {
        jScrollPane1 = new javax.swing.JScrollPane();
        jTextArea1 = new javax.swing.JTextArea();
        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        jTextArea1.setColumns(20);
        jTextArea1.setRows(5);
        jScrollPane1.setViewportView(jTextArea1);
        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING).addGroup(layout.createSequentialGroup().addContainerGap().addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 380, Short.MAX_VALUE).addContainerGap()));
        layout.setVerticalGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING).addGroup(layout.createSequentialGroup().addContainerGap().addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 280, Short.MAX_VALUE).addContainerGap()));
        pack();
    }

    public void run() {
        try {
            ServerSocket servidor = new ServerSocket(9999);
            jTextArea1.append("Servidor esperando conexiones...\n");
            while (true) {
                Socket socket = servidor.accept();
                DataInputStream entrada = new DataInputStream(socket.getInputStream());
                String mensaje = entrada.readUTF();
                jTextArea1.append("Cliente: " + mensaje + "\n");

                DataOutputStream salida = new DataOutputStream(socket.getOutputStream());
                salida.writeUTF("Mensaje recibido: " + mensaje);
                socket.close();
            }
        } catch (IOException ex) {
            Logger.getLogger(socket2.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextArea jTextArea1;
}
