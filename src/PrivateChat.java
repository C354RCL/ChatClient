
import java.io.*;
import java.net.*;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Cesar
 */
public class PrivateChat extends javax.swing.JFrame {

    /**
     * Creates new form Prueba
     */
    
    //Se declaran variables que se usarán a lo largo del programa
    public Socket socket;
    public OutputStream outputstream;
    public String message;
    public byte[] msg;
    public boolean firstTime = true;
    public String username;
    
    //Funcion para conectar el cliente con el servidor
    public void conectarServer(String host, int port) {
        System.out.println("Conectandose a " + host);
        try{
            socket = new Socket(host, port); //Se crea la conexion al socket
            System.out.println("Conectado a " + socket.getRemoteSocketAddress()); //Imprime en consola donde essta conectado
            outputstream = socket.getOutputStream(); //Se crea el flujo de salida
            
            txtChat.setText("Escribe tu nombre de usuario y da click en enviar \n");//Pide al usuario escribir su nombre
        }
        catch (Exception e){
            System.out.println("Error: " + e);
            
        }
    }
    
    //Funcion para recibir mensajes del servidor y mostrarlos en la interfaz
    public void recibirMensajes(Socket socket) {
        try {
            InputStream inputStream = socket.getInputStream(); //Se crea el flujo de entrada
            byte[] buffer = new byte[1024]; //Se crea un arreglo de bytes
            inputStream.read(buffer); //Se lee el flujo de entrada
            String msg = new String(buffer); //Se crea la variable para almacenar el mensaje recibido
            msg = msg.trim(); //Se eliminan los caracteres vacios
            //System.out.println(msg); 
            txtChat.append(msg + "\n"); //Se agrega el mensaje a la interfaz del chat
            
        } catch (Exception e) {
            System.out.println("Error: " + e);
        }
    }
    
    
    //Funcion principal
    public PrivateChat() {
        initComponents();
        try{
            
            conectarServer("localhost", 2099); //Se establece conexion con el servidor
            
            //Se crea un hilo para recibir flujos de entrada desde el servidor
            Thread recibir = new Thread(){
                @Override
                public void run() {
                    try {
                        while (socket.isConnected()) {
                            System.out.println("Loop started");
                            recibirMensajes(socket); //recibe mensajes del servidor mientras exista una conexion
                            
                        }
                    } catch (Exception e) {
                        System.out.println("Error: " + e);
                    }
                }
            };
            
            recibir.start(); //se inicia el hilo
            
           
            
            
        } catch (Exception e){
            System.out.println("Error" + e);
        }
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jFileChooser1 = new javax.swing.JFileChooser();
        jFileChooser2 = new javax.swing.JFileChooser();
        jPanel2 = new javax.swing.JPanel();
        jPanel3 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        txtChat = new javax.swing.JTextArea();
        txtMsg = new javax.swing.JTextField();
        btnSend = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        txtChat.setEditable(false);
        txtChat.setColumns(20);
        txtChat.setRows(5);
        jScrollPane1.setViewportView(txtChat);

        txtMsg.addHierarchyListener(new java.awt.event.HierarchyListener() {
            public void hierarchyChanged(java.awt.event.HierarchyEvent evt) {
                txtMsgHierarchyChanged(evt);
            }
        });

        btnSend.setText("Enviar");
        btnSend.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSendActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 434, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(txtMsg, javax.swing.GroupLayout.PREFERRED_SIZE, 335, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnSend)))
                .addGap(0, 173, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 213, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtMsg, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnSend))
                .addGap(0, 128, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, 491, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 10, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, 319, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 8, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void txtMsgHierarchyChanged(java.awt.event.HierarchyEvent evt) {//GEN-FIRST:event_txtMsgHierarchyChanged
        // TODO add your handling code here:
    }//GEN-LAST:event_txtMsgHierarchyChanged

    private void btnSendActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSendActionPerformed

        //Cuando se presiona el boton de enviar se ejecuta esta parte del codigo

        try{
            if(firstTime){
                //Si es la primera vez que envia un mensaje este se envia al servidor con un token marcando que lo que se envia es el nombre de usuario
                message = "u^" + txtMsg.getText(); //la variable mensaje empieza con el comando u^ para que el servidor lo interprete como nombre de usuario y además incluye el nombre de usuario después del caracter que utulizara par dividir tokens
                username = txtMsg.getText(); //Se guarda el nombre de usuario que se ingreso en una variable
                msg = message.getBytes(); //Se pasa el mensaje completo a bytes
                outputstream.write(msg); //Se envian por el flujo de salida los bytes

                txtMsg.setText(""); //Se limpia el campo de texto para que se escriba otro mensaje sin problemas
                System.out.println("Bytes mandados");
                firstTime = false; //Se actualiza la variable que establece si es la primera vez que se envía algo desde el cliente

                txtChat.append("Tu nombre de usuario es " + username + "\n"); //Se le muestra al usuario cual es su nombre
            }
            else{
                message = "m^" + txtMsg.getText(); //Se guarda en una variable el comando y el mensaje
                msg = message.getBytes(); //Se pasa a bytes el mensaje completo
                outputstream.write(msg); //Se envia por el flujo de salida

                txtMsg.setText("");  //Se limpia el campo de texto para que se escriba otro mensaje sin problemas
                System.out.println("Bytes mandados");
            }
        }catch (IOException er){
            System.out.println("Error " + er);
        }
    }//GEN-LAST:event_btnSendActionPerformed

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
            java.util.logging.Logger.getLogger(Prueba.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Prueba.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Prueba.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Prueba.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Prueba().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnSend;
    private javax.swing.JFileChooser jFileChooser1;
    private javax.swing.JFileChooser jFileChooser2;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextArea txtChat;
    private javax.swing.JTextField txtMsg;
    // End of variables declaration//GEN-END:variables
}
