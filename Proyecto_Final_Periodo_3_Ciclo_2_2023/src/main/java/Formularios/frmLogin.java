package Formularios;

import Clases.Conexion;
import blockchain.Cifrado;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.HashMap;
import java.util.Map;
import javax.swing.JOptionPane;

public class frmLogin extends javax.swing.JFrame {

    private Conexion oCon;
    private int iServer = 0;
    private Map<String, javax.swing.JFrame> userForms = new HashMap<>();
    private Cifrado cifrador;//Solo usado para cifrar los usuario estaticos de la BD
    
    public frmLogin() 
    {
        initComponents();
         oCon = new Conexion("localhost", "xe", "system", "AlavadoseaDios1");
         this.btnCifrador.setVisible(false); //boton auxiliar apra cifrar
         this.cifrador = new Cifrado("ñ3YeQdvKAq9iGvqñ");
         this.txtContrasena.setText("");
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        jList1 = new javax.swing.JList<>();
        jLabel1 = new javax.swing.JLabel();
        btnLogin = new javax.swing.JButton();
        txtUsuario = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        btnCifrador = new javax.swing.JButton();
        txtContrasena = new javax.swing.JPasswordField();

        jList1.setModel(new javax.swing.AbstractListModel<String>() {
            String[] strings = { "Item 1", "Item 2", "Item 3", "Item 4", "Item 5" };
            public int getSize() { return strings.length; }
            public String getElementAt(int i) { return strings[i]; }
        });
        jScrollPane1.setViewportView(jList1);

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel1.setText("LOGIN");

        btnLogin.setText("Entrar");
        btnLogin.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLoginActionPerformed(evt);
            }
        });

        jLabel2.setText("Usuario:");

        jLabel3.setText("Contraseña:");

        btnCifrador.setText("Cifrador");
        btnCifrador.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCifradorActionPerformed(evt);
            }
        });

        txtContrasena.setText("jPasswordField1");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(36, 36, 36)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(btnCifrador, javax.swing.GroupLayout.PREFERRED_SIZE, 57, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3)
                    .addComponent(jLabel2))
                .addGap(71, 71, 71)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(txtUsuario, javax.swing.GroupLayout.DEFAULT_SIZE, 122, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(11, 11, 11)
                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(txtContrasena))
                .addContainerGap(107, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btnLogin, javax.swing.GroupLayout.PREFERRED_SIZE, 96, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(28, 28, 28))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(33, 33, 33)
                .addComponent(jLabel1)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(50, 50, 50)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel2)
                            .addComponent(txtUsuario, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 95, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel3)
                            .addComponent(txtContrasena, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(66, 66, 66)))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnLogin)
                    .addComponent(btnCifrador))
                .addGap(22, 22, 22))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnLoginActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLoginActionPerformed
        // usuario y contraseña
        String username = txtUsuario.getText().trim();
        String password = txtContrasena.getText().trim();

        if (!username.isEmpty() && !password.isEmpty()) 
        {
            if (autenticarUsuario(username, password)) 
            {
                System.out.println("Autenticación exitosa");
                abrirFrmUsuario(username);//Abrir la wallet del usuario ingresado
                iServer++;
            } 
            else 
            {
                JOptionPane.showMessageDialog(rootPane, "Usuario o Contraseña equivocados.");
                System.out.println("Autenticación fallida");
            }
        } 
        else 
        {
            System.out.println("Por favor, ingrese usuario y contraseña");
        }
        this.txtContrasena.setText("");
    }//GEN-LAST:event_btnLoginActionPerformed

    // CIFRADOR
    // Usado una vez
    private void btnCifradorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCifradorActionPerformed
        try 
        {
            // Supongamos que tienes una conexión a la base de datos y un Statement para ejecutar consultas
            Statement stmt = oCon.getConnection().createStatement();
            ResultSet resultSet = stmt.executeQuery("SELECT * FROM system.Usuario");

            while (resultSet.next()) 
            {
                String usuario = resultSet.getString("username");
                String contrasena = resultSet.getString("contrasenaHash");

                System.out.println("Usuario antes de encriptar: " + usuario);
                System.out.println("Contraseña antes de encriptar: " + contrasena);

                String usuarioEncriptado = cifrador.encriptar(usuario);
                String contrasenaEncriptada = cifrador.encriptar(contrasena);

                System.out.println("Usuario encriptado: " + usuarioEncriptado);
                System.out.println("Contraseña encriptada: " + contrasenaEncriptada);

                String updateQuery = "UPDATE system.Usuario SET username = ?, contrasenaHash = ? WHERE username = ?";
                try (PreparedStatement pstUpdate = oCon.getConnection().prepareStatement(updateQuery)) 
                {
                    pstUpdate.setString(1, usuarioEncriptado);
                    pstUpdate.setString(2, contrasenaEncriptada);
                    pstUpdate.setString(3, usuario);
                    pstUpdate.executeUpdate();
                }
            }
           
            System.out.println("Cifrado de usuarios y contraseñas completado");
        } 
        catch (Exception e) 
        {
            e.printStackTrace();
            System.out.println("Error al cifrar usuarios y contraseñas");
        }
    }//GEN-LAST:event_btnCifradorActionPerformed

    
    private boolean autenticarUsuario(String username, String password) 
    {
        String query = "SELECT * FROM Usuario WHERE username = ? AND contrasenaHash = ?";
        
        try (PreparedStatement pst = oCon.getConnection().prepareStatement(query)) 
        {
            pst.setString(1, cifrador.encriptar(username));
            pst.setString(2, cifrador.encriptar(password));

            try (ResultSet rs = pst.executeQuery()) 
            {
                if (rs.next()) 
                {
                    System.out.println("Usuario autenticado: " + username);
                    return true;
                } 
                else 
                {
                    System.out.println("Nombre de usuario o contraseña incorrectos");
                    return false;
                }
            }
        } 
        catch (Exception e) 
        {
            e.printStackTrace();
            return false;
        }
    }


    private void abrirFrmUsuario(String username) 
    {
        if (!userForms.containsKey(username)) 
        {
            javax.swing.JFrame userForm = obtenerFrmUsuario(username);
            userForm.setVisible(true);

            userForms.put(username, userForm);
        }
    }

    private javax.swing.JFrame obtenerFrmUsuario(String username) 
    {
        // Para abir el usuario correspondiente
        switch (username) {
            case "Diego":
                return new frmWallet_Diego();
            case "Rodrigo":
                return new frmWalletRodrigo();
            case "Tania":
                return new frmWalletTania();
            case "Hotel":
                return new frmHotel();
            default:
                System.out.println("Usuario no reconocido");
        return new javax.swing.JFrame(); 
        }
    }
    
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
            java.util.logging.Logger.getLogger(frmLogin.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(frmLogin.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(frmLogin.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(frmLogin.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new frmLogin().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnCifrador;
    private javax.swing.JButton btnLogin;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JList<String> jList1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JPasswordField txtContrasena;
    private javax.swing.JTextField txtUsuario;
    // End of variables declaration//GEN-END:variables
}
