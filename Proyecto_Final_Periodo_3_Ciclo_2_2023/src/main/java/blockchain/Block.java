package blockchain;

import blockchain.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;

public class Block implements Serializable
{
    private int id;
    private int nonce;
    private long timeStamp;
    private String hash; // según Nakamoto (2008) es el Root Hash
    private String previousHash;
    private ArrayList<Transaction> aTransactions;
 

    public Block(int pId, String pPrevHash) 
    {
        this.id = pId;
        this.timeStamp = new Date().getTime();
        this.previousHash = pPrevHash;
        this.aTransactions = new ArrayList<>();
        this.nonce = -1;
        this.hash = null;
    }
    
    public Block() 
    {
        this.timeStamp = new Date().getTime();
        this.aTransactions = new ArrayList<>();
        this.nonce = -1;
        this.hash = null;
        this.id = -1;
    }
    
    public boolean register(int pNonce, String pHash)
    {
        if ((this.id > -1) && (this.nonce < 0) && (this.hash == null))
        {
            this.nonce = pNonce;
            this.hash = pHash;           
            return true;
        }
        else return false;
    }
    
    public void setTransaction(Transaction pTran) 
    {
        this.aTransactions.add(pTran);
    }

    
    public Transaction getTransaction(int pId)
    {
        return this.aTransactions.get(pId);
    }
    
    public int countTransactions()
    {
        return this.aTransactions.size();
    }

    public int getId() {
        return id;
    }

    public int getNonce() {
        return nonce;
    }

    public long getTimeStamp() {
        return timeStamp;
    }

    public String getHash() {
        return hash;
    }

    public String getPreviousHash() {
        return previousHash;
    }

    @Override
    public String toString() {
        String sCad = Integer.toString(id) + Long.toString(timeStamp) + this.previousHash;
        for (int i = 0; i < this.aTransactions.size(); i++)
        {
            sCad = sCad + this.aTransactions.get(i).toString();
        }
        return sCad;
    }
}
