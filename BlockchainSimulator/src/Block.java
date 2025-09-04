import java.security.MessageDigest;
import java.util.ArrayList;
import java.util.Date;

public class Block {
    public String hash;
    public String previousHash;
    private ArrayList<Transaction> transactions = new ArrayList<>();
    private long timeStamp;
    private int nonce;

    public Block(String previousHash) {
        this.previousHash = previousHash;
        this.timeStamp = new Date().getTime();
        this.hash = calculateHash();
    }

    public void addTransaction(Transaction transaction) {
        transactions.add(transaction);
    }

    public String calculateHash() {
        StringBuilder transactionData = new StringBuilder();
        for (Transaction t : transactions) {
            transactionData.append(t.toString());
        }

        String input = previousHash + Long.toString(timeStamp) + Integer.toString(nonce) + transactionData;
        return applySha256(input);
    }

    public void mineBlock(int difficulty){
        String target = new String(new char[difficulty]).replace('\0', '0');
        while(!hash.substring(0, difficulty).equals(target)){
            nonce++;
            hash = calculateHash();
        }
        System.out.println("Block mined: " + hash);
    }

    private String applySha256(String input){
        try {
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            byte[] hash = digest.digest(input.getBytes("UTF-8"));

            StringBuilder hexString = new StringBuilder();
            for(byte b : hash){
                String hex = Integer.toHexString(0xff & b);
                if(hex.length() == 1) hexString.append('0');
                hexString.append(hex);
            }
            return hexString.toString();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public ArrayList<Transaction> getTransactions() {
        return transactions;
    }
}
