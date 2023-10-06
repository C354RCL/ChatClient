
import java.io.*;
import java.net.*;
public class Prueba extends javax.swing.JFrame {
    
    //Se declaran variables que se usarán a lo largo del programa
    public Socket socket;
    public OutputStream outputstream;
    public String message;
    public byte[] msg;
    public boolean firstTime = true;
    public String username;
    public String command;
    public String[] userlist;
    
    
    
    
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
            message = msg;
            //System.out.println(message);
            commandInterpreter(commandReader(message));//Se interpreta el comando que se recibe del servidor y se procesa
            
            
            
        } catch (Exception e) {
            System.out.println("Error: " + e);
        }
    }
    
    //Funcion para leer el comando
    public String[] commandReader(String message){
        String[] commandText = message.split("\\^"); //Separa el comando del resto del texto
        
        return commandText;
        
    }
    //Funcion para ejecutar una accion dependiendo del comando leido
    public void commandInterpreter(String[] commandText){
        
        //System.out.println(commandText[0]);
        switch(commandText[0]){
            case "m": //Si el comando es m significa que es un mensaje al chat grupal
                txtChat.append(commandText[1] + "\n"); //Se agrega el mensaje a la interfaz del chat
            break;
            case "lu": //Si el comando es lu significa que es una lista de los usuarios conectados
                updateUserList(userListGenerator(commandText[1])); //Actualiza la lista de usuarios
            break;
            case "nc": //Si el comando es nc significa que se tiene que abrir una nueva ventana para un chat privado
                openPrivateChat(commandText);//Se ejecuta la clase de chat privado
            break;
        }
    }
    
    //Funcion para abrir un chat privado
    public void openPrivateChat(String[] commandText){
        String privateUser = commandText[1]; //Se define con que usuario se hara la conexion privada
        int privatePort = Integer.parseInt(commandText[2]); //Se guarda el puerto en el cual se encuentra el usuario con el cual se va a conectar
        //System.out.println(privateUser);
        //System.out.println(privatePort);
        
        PrivateChat privateChat = new PrivateChat(username, privateUser, privatePort); //Se crea el nuevo chat privado con los parametros de nombre de usuario, con quien se va a conectar y que puerto tiene
        privateChat.setVisible(true); 
        
    }
    
    //Funcion para separar los usuarios por tokens
    public String[] userListGenerator(String usuarios) {
        userlist = usuarios.split(";");
    return userlist;
}
    //Funcion para actualizar la lista de usuarios disponibles
    public void updateUserList(String[] userlist){
        listUsernames.setListData(userlist);    
    }
    
    //Funcion principal
    public Prueba() {
        initComponents();
        
        try{
            
            conectarServer("localhost", 2099); //Se establece conexion con el servidor
            
            //Se crea un hilo para recibir flujos de entrada desde el servidor
            Thread recibir = new Thread(){
                @Override
                public void run() {
                    try {
                        while (socket.isConnected()) {
                            //System.out.println("Loop started");
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
        btnSend = new javax.swing.JButton();
        txtMsg = new javax.swing.JTextField();
        jScrollPane2 = new javax.swing.JScrollPane();
        listUsernames = new javax.swing.JList<>();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        txtChat.setEditable(false);
        txtChat.setColumns(20);
        txtChat.setRows(5);
        jScrollPane1.setViewportView(txtChat);

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 607, Short.MAX_VALUE)
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 381, Short.MAX_VALUE)
        );

        btnSend.setText("Enviar");
        btnSend.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSendActionPerformed(evt);
            }
        });

        txtMsg.addHierarchyListener(new java.awt.event.HierarchyListener() {
            public void hierarchyChanged(java.awt.event.HierarchyEvent evt) {
                txtMsgHierarchyChanged(evt);
            }
        });

        listUsernames.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        listUsernames.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                listUsernamesMouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(listUsernames);

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(txtMsg))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 151, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnSend))
                .addContainerGap(175, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jScrollPane2))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 48, Short.MAX_VALUE)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnSend)
                    .addComponent(txtMsg, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(32, 32, 32))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 24, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
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
                //System.out.println("Bytes mandados");
                firstTime = false; //Se actualiza la variable que establece si es la primera vez que se envía algo desde el cliente

                txtChat.append("Tu nombre de usuario es " + username + "\n"); //Se le muestra al usuario cual es su nombre
            }
            else{
                message = "m^" + txtMsg.getText(); //Se guarda en una variable el comando y el mensaje
                msg = message.getBytes(); //Se pasa a bytes el mensaje completo
                outputstream.write(msg); //Se envia por el flujo de salida

                txtMsg.setText("");  //Se limpia el campo de texto para que se escriba otro mensaje sin problemas
                //System.out.println("Bytes mandados");
            }
        }catch (IOException er){
            System.out.println("Error " + er);
        }
    }//GEN-LAST:event_btnSendActionPerformed

    private void listUsernamesMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_listUsernamesMouseClicked
        try{
            
            message = "nc^" + username + "^" + listUsernames.getSelectedValue(); //Se guarda en una variable el comando y el mensaje
            msg = message.getBytes(); //Se pasa a bytes el mensaje completo
            outputstream.write(msg); //Se envia por el flujo de salida
            txtMsg.setText("");  //Se limpia el campo de texto para que se escriba otro mensaje sin problemas
            
            //System.out.println(message);
            //Abrir ventana de chat privado
            String privateUser = listUsernames.getSelectedValue(); //Se guarda el usuario de la persona a la que se selecciono
            int privatePort = 0; 
            PrivateChat privateChat = new PrivateChat(username, privateUser, privatePort); //Se crea una clase de chat privado con los parametros necesarios al presionar uno de la lista de usuarios
            privateChat.setVisible(true);
        }  
        catch(IOException err){
            System.out.println("Error: " + err);
        }
                
    }//GEN-LAST:event_listUsernamesMouseClicked

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
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JList<String> listUsernames;
    private javax.swing.JTextArea txtChat;
    private javax.swing.JTextField txtMsg;
    // End of variables declaration//GEN-END:variables
}
