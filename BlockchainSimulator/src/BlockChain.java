import java.util.ArrayList;

public class BlockChain {
    public static ArrayList<Block> blockchain = new ArrayList<>();
    public static int difficulty = 4;
    private static ArrayList<Transaction> pendingTransactions = new ArrayList<>();
    private static double miningReward = 10.0;

    public static void addTransaction(Transaction transaction) {
        pendingTransactions.add(transaction);
    }

    public static void minePendingTransactions(String minerAddress) {
        Block newBlock = new Block(blockchain.get(blockchain.size() - 1).hash);

        for (Transaction tx : pendingTransactions) {
            newBlock.addTransaction(tx);
        }

        // Reward transaction
        newBlock.addTransaction(new Transaction("System", minerAddress, miningReward));

        newBlock.mineBlock(difficulty);
        blockchain.add(newBlock);

        System.out.println("Block mined successfully! Reward sent to " + minerAddress);

        pendingTransactions.clear(); // Empty pool after mining
    }

    public static boolean isChainValid(){
        for (int i = 1; i < blockchain.size(); i++) {
            Block currentBlock = blockchain.get(i);
            Block previousBlock = blockchain.get(i - 1);

            if(!currentBlock.hash.equals(currentBlock.calculateHash())){
                System.out.println("Current Hashes not equal");
                return false;
            }

            if(!currentBlock.previousHash.equals(previousBlock.hash)) {
                System.out.println("Previous Hashes not equal");
                return false;
            }
        }
        return true;
    }
}
