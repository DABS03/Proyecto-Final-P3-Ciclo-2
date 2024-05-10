package Formularios;

import Clases.*;
import blockchain.*;
import java.io.EOFException;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

public class frmHotel extends javax.swing.JFrame implements Runnable{

    private BlockChain bc;
    private double dCurrentBalance;
    private ServerSocket clientSocket;
    private Thread tListener;
    private Conexion oCon;
    private Cifrado oCifrado;
    public Cliente oCliente;
    private String mensaje="";
    
    private Map<String, List<DateRange>> reservasPorHabitacion = new HashMap<>();

    public frmHotel() {
        initComponents();
        this.setResizable(false);
        oCifrado = new Cifrado("ñ3YeQdvKAq9iGvqñ");
        oCon = new Conexion("localhost", "xe", "system", "AlavadoseaDios1");
        oCliente = new Cliente("Hotel", "127.0.0.1", 7000);
        bc = new BlockChain(3, "0");  // Agrega esta línea para inicializar bc
        bc.createGenesis();  // Crea el bloque génesis

        this.dCurrentBalance = 0;
        this.labelBalance.setText("₿ :" + dCurrentBalance);

        reservasPorHabitacion = new HashMap<>();

        this.startClient();
    }

    private void startClient()
    {
        closeServerSocket();
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
    
    private void closeServerSocket() {
        try {
            if (this.clientSocket != null && !this.clientSocket.isClosed()) {
                this.clientSocket.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel2 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        txtServidor = new javax.swing.JTextArea();
        jLabel1 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        labelBalance = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel2.setText("HOTEL");

        txtServidor.setColumns(20);
        txtServidor.setRows(5);
        jScrollPane1.setViewportView(txtServidor);

        jLabel1.setText("Habitaciones reservadas:");

        jLabel3.setText("Balance del Hotel: ");

        labelBalance.setText("₿ :");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jScrollPane1))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(19, 19, 19)
                        .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 107, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(labelBalance, javax.swing.GroupLayout.PREFERRED_SIZE, 51, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(191, Short.MAX_VALUE)
                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(188, 188, 188))
            .addGroup(layout.createSequentialGroup()
                .addGap(35, 35, 35)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(29, 29, 29)
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 226, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(labelBalance))
                .addContainerGap(107, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents


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
            java.util.logging.Logger.getLogger(frmHotel.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(frmHotel.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(frmHotel.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(frmHotel.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new frmHotel().setVisible(true);
            }
        });
    }

    //Verificar si una habitación está disponible para las fechas especificadas
    private boolean habitacionDisponible(Transaction transaction) 
    {
        String numeroCuarto = transaction.getRoomNumber();
        if (reservasPorHabitacion.containsKey(numeroCuarto)) 
        {
            List<DateRange> reservas = reservasPorHabitacion.get(numeroCuarto);
            DateRange nuevaReserva = new DateRange(transaction.getCheckInDate(), transaction.getCheckOutDate());
            for (DateRange reservaExistente : reservas) 
            {
                if (reservaExistente.seSolapanCon(nuevaReserva)) 
                {// Hay solapamiento, entonces la habitación no está disponible
                    return false; 
                }
            }
        }
        return true; // La habitación está disponible
    }

    //Actualizar las fechas reservadas después de procesar una transacción
    private void actualizarFechasReservadas(Transaction transaction) 
    {
        String numeroCuarto = transaction.getRoomNumber();
        if (!reservasPorHabitacion.containsKey(numeroCuarto)) 
        {
            reservasPorHabitacion.put(numeroCuarto, new ArrayList<>());
        }
        reservasPorHabitacion.get(numeroCuarto).add(new DateRange(transaction.getCheckInDate(), transaction.getCheckOutDate()));
    }

    
    @Override
    public void run() 
    {
        while (true) 
        {
            try (
                 Socket socket = this.clientSocket.accept();
                 InputStream is = socket.getInputStream();
                 ObjectInputStream ois = new ObjectInputStream(is)
                 ) 
            {
                Block blk = (Block)ois.readObject();
                socket.close();
                
                if (habitacionDisponible(blk.getTransaction(0)))
                {
                    if(blk.getId()<0)
                    {
                        String sSender = this.oCifrado.desencriptar(blk.getTransaction(0).getSender());
                        String numeroCuarto = blk.getTransaction(0).getRoomNumber();
                        String sReceiver = this.oCifrado.desencriptar(blk.getTransaction(0).getReceiver());
                        double dAmount = blk.getTransaction(0).getAmount();
                        int duracion = blk.getTransaction(0).getDuration();
                        Date checkIn = blk.getTransaction(0).getCheckInDate();
                        Date checkOut =  blk.getTransaction(0).getCheckOutDate();
                        
                        Transaction tTran = new Transaction(0,sSender, sReceiver, dAmount, numeroCuarto, duracion, checkIn, checkOut);
                        
                        if(this.bc.getBalance(sSender)>=0)
                        {
                            this.bc.createBlock();
                            this.bc.getLastBlock().setTransaction(tTran);
                            this.bc.mineBlock();
                            
                        }
                                
                        // Actualizar txtServidor con la información de la transacción
                        mensaje += sSender + " reservó habitación " + numeroCuarto + " con: ₿ " + dAmount + "\n";
                        mensaje += "Duración: " + duracion + " días\n";
                        mensaje += "Check In: " + checkIn + "\n";
                        mensaje += "Check Out: " + checkOut + "\n";
                        mensaje += "---------------------------------------------------\n";
                        this.txtServidor.setText(mensaje);

                        // Actualizar labelBalance en función del monto de la transacción
                        this.dCurrentBalance += dAmount;
                        this.labelBalance.setText("₿ " + Double.toString(this.dCurrentBalance));

                        // Actualizar las fechas reservadas
                        actualizarFechasReservadas(blk.getTransaction(0));
                    }
                    
                }
                else
                {
                    String sSender = this.oCifrado.desencriptar(blk.getTransaction(0).getSender());
                    System.out.println("La habitación no está disponible para" + sSender);
                    // Enviar respuesta al cliente de que la habitación no está disponible
                    int puerto = 0;

                    if (sSender.equals("Diego")) 
                    {
                        puerto = 7001;
                    } 
                    else if (sSender.equals("Rodrigo")) 
                    {
                        puerto = 7002;
                    } 
                    else if (sSender.equals("Tania")) 
                    {
                        puerto = 7003;
                    }

                    try (Socket responseSocket = new Socket("127.0.0.1", puerto);
                         ObjectOutputStream responseOos = new ObjectOutputStream(responseSocket.getOutputStream())) 
                    {
                        responseOos.writeBoolean(false); // Habitación no disponible
                    } 
                    catch (IOException e) 
                    {
                        e.printStackTrace();
                    }
                }
            } 
            catch (EOFException e) 
            {
                System.out.println("EOFException: El flujo de entrada se cerró inesperadamente.");
                e.printStackTrace();
                // Realiza acciones adicionales si es necesario.
            } 
            catch (IOException | ClassNotFoundException e) 
            {
                e.printStackTrace();
            } 
            catch (Exception ex) 
            {
                Logger.getLogger(frmHotel.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel labelBalance;
    private javax.swing.JTextArea txtServidor;
    // End of variables declaration//GEN-END:variables
}
