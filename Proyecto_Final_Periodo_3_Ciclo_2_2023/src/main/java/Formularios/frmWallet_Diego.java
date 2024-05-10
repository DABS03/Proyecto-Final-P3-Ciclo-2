package Formularios;

import blockchain.*;
import Clases.*;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Calendar;
import java.util.Date;
import javax.swing.JOptionPane;

public class frmWallet_Diego extends javax.swing.JFrame implements Runnable
{
    
    private BlockChain bc;
    private double dCurrentBalance;
    private ServerSocket clientSocket;
    private Thread tListener;
    private Conexion oCon;
    private Cifrado oCifrado;
    public Cliente oCliente;
    private Double dAmount;

    public frmWallet_Diego() 
    {
        initComponents();
        this.setResizable(false);
        oCifrado = new Cifrado("ñ3YeQdvKAq9iGvqñ");
        oCon = new Conexion("localhost", "xe", "system", "AlavadoseaDios1");
        oCliente = new Cliente("Diego", "127.0.0.1", 7001);
        bc = new BlockChain(3,"0");
        bc.createGenesis();
        this.txtContrasena.setText("");
        this.dCurrentBalance = 1500;
        this.labelBalance.setText("₿ :"+dCurrentBalance);
        dAmount = 0.0;
        this.startClient();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel2 = new javax.swing.JLabel();
        labelBalance = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        cmbHabitacion = new javax.swing.JComboBox<>();
        btnEnviar = new javax.swing.JButton();
        jdcCheckIn = new com.toedter.calendar.JDateChooser();
        jLabel5 = new javax.swing.JLabel();
        spnEstancia = new javax.swing.JSpinner();
        jLabel3 = new javax.swing.JLabel();
        txtContrasena = new javax.swing.JPasswordField();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel2.setText("Wallet de: Diego");

        labelBalance.setText("₿ :");

        jLabel1.setText("Balance:");

        jLabel6.setText("Contraseña:");

        jLabel4.setText("Habitación:");

        cmbHabitacion.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "#10", "#11", "#12", "#13" }));

        btnEnviar.setText("Enviar");
        btnEnviar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEnviarActionPerformed(evt);
            }
        });

        jLabel5.setText("Check In:");

        spnEstancia.setModel(new javax.swing.SpinnerNumberModel(1, 1, 356, 1));

        jLabel3.setText("Días de estancia:");

        txtContrasena.setText("jPasswordField1");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btnEnviar, javax.swing.GroupLayout.PREFERRED_SIZE, 76, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(128, 128, 128))
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(43, 43, 43)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel3)
                                .addGap(15, 15, 15))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)))
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jdcCheckIn, javax.swing.GroupLayout.DEFAULT_SIZE, 106, Short.MAX_VALUE)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addComponent(spnEstancia, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(27, 27, 27))))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(66, 66, 66)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 72, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED))
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 54, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 66, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(18, 18, 18)))
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(cmbHabitacion, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 104, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addComponent(labelBalance, javax.swing.GroupLayout.PREFERRED_SIZE, 51, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(23, 23, 23))
                            .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 96, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtContrasena, javax.swing.GroupLayout.PREFERRED_SIZE, 106, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(113, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(26, 26, 26)
                .addComponent(jLabel2)
                .addGap(32, 32, 32)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(labelBalance)
                            .addComponent(jLabel1))
                        .addGap(42, 42, 42))
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel6)
                        .addComponent(txtContrasena, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(cmbHabitacion, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel3)
                            .addComponent(spnEstancia, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addComponent(jdcCheckIn, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jLabel5, javax.swing.GroupLayout.Alignment.TRAILING))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 54, Short.MAX_VALUE)
                .addComponent(btnEnviar)
                .addGap(50, 50, 50))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void startClient()
    {
        try
        {
            InetAddress iAddress= InetAddress.getByName(this.oCliente.getIp()); //127.0.0.1
            InetSocketAddress sNetSckt = new InetSocketAddress(iAddress, this.oCliente.getPuerto());
            this.clientSocket = new ServerSocket();
            this.clientSocket.bind(sNetSckt);
            this.tListener = new Thread(this);
            this.tListener.start();
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
    }
    
    private boolean sendTransaction() 
    {
        String sNode = this.oCliente.getNombre();
        String password = this.txtContrasena.getText();
        String sReceiver = "Hotel";
        
        if(this.autenticarReceptor(sReceiver)==true)
        {
            dAmount = 0.0;
            String hSeleccionada = (String) cmbHabitacion.getSelectedItem();

        // Asigna el monto según la habitación seleccionada
        switch (hSeleccionada) 
        {
            case "#10":
                dAmount = 150.00;
                break;
            case "#11":
                dAmount = 250.00;
                break;
            case "#12":
                dAmount = 350.00;
                break;
            case "#13":
                dAmount = 450.00;
                break;
            default:
                break;
        }
            if (this.autenticarUsuario(sNode, password)) 
            {
                if (dAmount <= this.dCurrentBalance) 
                {
                    try 
                    {
                        sNode = this.oCifrado.encriptar(sNode);
                        sReceiver = this.oCifrado.encriptar(sReceiver);

                        String numeroCuarto = hSeleccionada;
                        int duracion = (int) spnEstancia.getValue();
                        Date checkIn = jdcCheckIn.getDate();
                        Date checkOut = calcularCheckOut(checkIn, duracion);
                        
                        Transaction tTran = new Transaction(0, sNode, sReceiver, dAmount, numeroCuarto, duracion, checkIn, checkOut);
                        Block blk = new Block();
                        blk.setTransaction(tTran);
                        
                        // Enviar la reservación al hotel
                        Socket socket = new Socket("127.0.0.1", 7000);
                        ObjectOutputStream oos = new ObjectOutputStream(socket.getOutputStream());
                        oos.writeObject(blk);

                        this.dCurrentBalance -= dAmount;
                        this.labelBalance.setText("₿ " + Double.toString(this.dCurrentBalance));

                        oos.close();
                        socket.close();
                        return true;
                    } 
                    catch (Exception e) 
                    {
                        e.printStackTrace();
                        return false;
                    }
                }
                else
                {
                    JOptionPane.showMessageDialog(rootPane, "Fondos insifucientes.");
                }
            }
        }
        return false;
    }
    
    private boolean autenticarReceptor(String receptor) 
    {
        String query = "SELECT * FROM Usuario WHERE username = ?";
        try (PreparedStatement pst = oCon.getConnection().prepareStatement(query)) 
        {
            pst.setString(1, this.oCifrado.encriptar(receptor));
            try (ResultSet rs = pst.executeQuery()) 
            {
                return rs.next(); // Si hay un resultado, significa que el usuario existe
            }
        } 
        catch (Exception e) 
        {
            e.printStackTrace();
            return false;
        }
    }
    
    private Date calcularCheckOut(Date checkIn, int duracion) 
    {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(checkIn);
        calendar.add(Calendar.DAY_OF_YEAR, duracion);
        return calendar.getTime();
    }

    private boolean autenticarUsuario(String username, String password) 
    {
        String query = "SELECT * FROM Usuario WHERE username = ?";

        try (PreparedStatement pst = oCon.getConnection().prepareStatement(query)) 
        {
            pst.setString(1, this.oCifrado.encriptar(username));

            try (ResultSet rs = pst.executeQuery()) 
            {
                if (rs.next()) {
                    // Username found, now check the password
                    String storedEncryptedPassword = rs.getString("contrasenaHash");

                    // Compare the entered password with the stored encrypted password
                    if (this.oCifrado.encriptar(password).equals(storedEncryptedPassword)) 
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
                else 
                {
                    // Username not found
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
    
    private void btnEnviarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEnviarActionPerformed
        this.sendTransaction();
        this.txtContrasena.setText("");
    }//GEN-LAST:event_btnEnviarActionPerformed

    public static void main(String args[]) {
        
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
            java.util.logging.Logger.getLogger(frmWallet_Diego.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(frmWallet_Diego.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(frmWallet_Diego.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(frmWallet_Diego.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>


        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new frmWallet_Diego().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnEnviar;
    private javax.swing.JComboBox<String> cmbHabitacion;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private com.toedter.calendar.JDateChooser jdcCheckIn;
    private javax.swing.JLabel labelBalance;
    private javax.swing.JSpinner spnEstancia;
    private javax.swing.JPasswordField txtContrasena;
    // End of variables declaration//GEN-END:variables

    @Override
    public void run() 
    {
        while (true) 
        {
            try 
            {
                Socket socket = this.clientSocket.accept();

                InputStream is = socket.getInputStream();
                ObjectInputStream ois = new ObjectInputStream(is);
                {
                    boolean habitacionDisponible = ois.readBoolean();
                    if(habitacionDisponible==false)
                    {
                        //Habitacion NO disponible
                        this.dCurrentBalance+= dAmount;
                        this.labelBalance.setText("₿ " + Double.toString(this.dCurrentBalance));
                        JOptionPane.showMessageDialog(rootPane, "Habitación no disponible");
                    }
                    else
                    {
                        JOptionPane.showMessageDialog(rootPane, "Habitación reservada exitosamente.");
                    }
                }

                ois.close();
                socket.close();
            } 
            catch (Exception e) 
            {
                e.printStackTrace();
            }
        }
    }
}
