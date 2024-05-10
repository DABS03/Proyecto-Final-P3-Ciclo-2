package blockchain;

import blockchain.*;
import java.security.MessageDigest; 
import java.util.ArrayList;
import java.util.List;

public class BlockChain 
{
    private ArrayList<Block> blockchain;
    private int complexity;
    private String proofOfWork;
    
    public BlockChain(int iComplexity, String proofChar)
    {
        this.blockchain = new ArrayList<>();
        this.complexity=iComplexity;
        this.proofOfWork="";
        for (int i=0; i<this.complexity; i++) this.proofOfWork+= proofChar;
    }
    
    public List<Block> getBlockChain() 
    {
        return this.blockchain;
    }
    
    public boolean blockExist(Block blk)
    {
        for(int i=0; i<this.blockchain.size(); i++)
        {
            if(this.blockchain.get(i).getId()==blk.getId()) return true;
        }
        return false;
    }
    
    public Block getBlock(int index)
    {
        return this.blockchain.get(index);
    }
    
    public Block getLastBlock()
    {
        return this.blockchain.get(this.blockchain.size()-1);
    }
    
    public int size() 
    {
        return this.blockchain.size();
    }
        
    public boolean createGenesis(double pInitialBalance, String pClient) 
    {
        if (this.size() < 1) {
            Block tmpBlock = new Block(0, "0000000000000000000000000000000000000000000000000000000000000000");
            if (pInitialBalance > 0) {
                Transaction genesisTransaction = new Transaction(0, "0000GeNeSiS", pClient, pInitialBalance, "", 0, null, null);
                tmpBlock.setTransaction(genesisTransaction);
            }
            this.blockchain.add(tmpBlock);
            this.mineBlock();
            return true;
        }
        return false;
    }



    public boolean createGenesis()
    {
        if(this.size()<1)
        {
            Block tmpBlock= new Block(0,"0000000000000000000000000000000000000000000000000000000000000000");
            this.blockchain.add(tmpBlock);
            this.mineBlock();
            return true;
        }
        return false;
    }
    
    public void createBlock()
    {
        
        String prevHash= this.blockchain.get(this.blockchain.size()-1).getHash(); 
        this.blockchain.add(new Block(this.blockchain.size(), prevHash));
    }

    public double getBalance(String pSender)
    {
        double positiveAmount=0;
        double negativeAmount=0;
        for(int i=0; i<this.size(); i++)
        {
            for(int j=0; j<this.getBlock(i).countTransactions(); j++)
            {
                if (this.getBlock(i).getTransaction(j).getReceiver().equals(pSender))
                    positiveAmount+= this.getBlock(i).getTransaction(j).getAmount(); 
                else if (this.getBlock(i).getTransaction(j).getSender().equals(pSender)) negativeAmount+= this.getBlock(i).getTransaction(j).getAmount();
            }                
        }
        return positiveAmount-negativeAmount;
    }


    public boolean getProofOfWork_overBlock(Block blk)
    {
        String cad= blk.toString();
        int nonce= blk.getNonce();
        String sHash = "";
        sHash=this.generateHash(cad+Integer.toString(nonce));
        if (sHash.equals(blk.getHash())) return true;
        else return false;
    }
    
    public boolean addProvedBlock(Block blk)
    {
       if(!this.blockExist(blk))
        {
            if(this.getProofOfWork_overBlock(blk))
            {
                this.blockchain.add(blk);
                return true;
            }
        }
        return false; 
    }
    
    public void mineBlock()
    {
        String cad= this.blockchain.get(this.blockchain.size()-1).toString();
        int nonce=0;
        String sHash="";
        while(true)
        {
            sHash=this.generateHash(cad+Integer.toString(nonce));
            if (sHash.subSequence(0, complexity).equals(this.proofOfWork))
            {
                this.blockchain.get(this.blockchain.size()-1).register(nonce, sHash);
                break;
            }      
        nonce++;
        }
    }
    
    private String generateHash(String pCad)
    {
        try
        {
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            byte[] hash = digest.digest(pCad.getBytes("UTF-8"));
            StringBuffer hexadecimalString = new StringBuffer();
            for (int i = 0; i < hash.length; i++)
            {
                String hexadecimal = Integer.toHexString(0xff & hash[i]);
                if (hexadecimal.length()==1) hexadecimalString.append('0');
                hexadecimalString.append(hexadecimal);
            }
            return hexadecimalString.toString();
        }
        catch(Exception ee)
        {
            return null;
        }
    }
    
    //→
    public String transactionReport(int nBlock)
    {
        String sCad="";
        Block blk= this.blockchain.get(nBlock);
        for (int i=0; i<blk.countTransactions(); i++)
        {
            sCad+= "\tTransacion #"+ Integer.toString(blk.getTransaction(i).getId())+
            ": $"+Double.toString(blk.getTransaction(i).getAmount()) + ".\t("+
            blk.getTransaction(i).getSender()+" ---> "+
            blk.getTransaction(i).getReceiver()+")\n";
        }
        return sCad;
    }
    
    @Override
    public String toString() 
    {
        String blockChain = "";
        for(Block block : this.blockchain)
        blockChain+=block.toString()+"\n";
        return blockChain;
    }
}