package blockchain;

import blockchain.*;
import java.io.Serializable;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Transaction implements Serializable
{
    private int id;
    private long timeStamp;
    private String sender;
    private String receiver;//El hotel sera el que recibe
    private double amount;
    private String numeroCuarto;
    private int duracion;
    private Date checkIn;
    private Date checkOut;
    
    private transient Cifrado oCifrado = new Cifrado("ñ3YeQdvKAq9iGvqñ");
    
    public Transaction(int pId, String pSender, String pReceiver, double pAmount,
                   String pRoomNumber, int pDuration, Date pCheckInDate, Date pCheckOutDate) 
    {
        this.id = pId;
        this.timeStamp = new Date().getTime();
        this.sender = pSender;
        this.receiver = pReceiver;
        this.amount = pAmount;
        this.numeroCuarto = pRoomNumber;
        this.duracion = pDuration;
        this.checkIn = (pCheckInDate != null) ? new Date(pCheckInDate.getTime()) : null;
        this.checkOut = (pCheckOutDate != null) ? new Date(pCheckOutDate.getTime()) : null;
    }


    @Override
    public String toString() 
    {
        StringBuilder stringBuilder = new StringBuilder();
        String sSenderDecrypted = "";
        String receiverDecrypted = "";

        try 
        {
            sSenderDecrypted = oCifrado.desencriptar(sender);
            receiverDecrypted = oCifrado.desencriptar(receiver);
        } 
        catch (Exception ex) 
        {
            Logger.getLogger(Transaction.class.getName()).log(Level.SEVERE, null, ex);
        }

        stringBuilder.append(sSenderDecrypted)
                .append(" reservó habitación ")
                .append(numeroCuarto)
                .append(" con: ₿ ")
                .append(amount)
                .append("\nDuración: ")
                .append(duracion)
                .append(" días\nCheck In: ")
                .append((checkIn != null) ? checkIn.toString() : "null")
                .append("\nCheck Out: ")
                .append((checkOut != null) ? checkOut.toString() : "null")
                .append("\n---------------------------------------------------\n");

        return stringBuilder.toString();
    }

    public boolean reserSolapada(Transaction other) 
    {
        return !(this.checkOut.before(other.getCheckInDate()) || this.checkIn.after(other.getCheckOutDate()));
    }

    
    public int getId() {
        return id;
    }

    public long getTimeStamp() {
        return timeStamp;
    }

    public String getSender() {
        return sender;
    }

    public String getReceiver() {
        return receiver;
    }

    public double getAmount() {
        return amount;
    }

    public String getRoomNumber() {
        return numeroCuarto;
    }

    public int getDuration() {
        return duracion;
    }

    public Date getCheckInDate() {
        return checkIn;
    }

    public Date getCheckOutDate() {
        return checkOut;
    }
}