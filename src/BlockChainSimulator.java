import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import java.util.Scanner;

public class BlockChainSimulator {
    public static void main(String[] args) {
        // Genesis Block
        Block genesisBlock = new Block("0");
        BlockChain.blockchain.add(genesisBlock);

        Scanner scanner = new Scanner(System.in);
        boolean running = true;

        while (running) {
            System.out.println("\n--- Blockchain Menu ---");
            System.out.println("1. Create Transaction");
            System.out.println("2. Mine Pending Transactions");
            System.out.println("3. Show Blockchain");
            System.out.println("4. Validate Blockchain");
            System.out.println("5. Exit");
            System.out.print("Choose: ");
            int choice = scanner.nextInt();
            scanner.nextLine(); // consume newline

            switch (choice) {
                case 1:
                    System.out.print("Sender: ");
                    String sender = scanner.nextLine();
                    System.out.print("Receiver: ");
                    String receiver = scanner.nextLine();
                    System.out.print("Amount: ");
                    double amount = scanner.nextDouble();
                    scanner.nextLine();

                    Transaction tx = new Transaction(sender, receiver, amount);
                    BlockChain.addTransaction(tx);
                    System.out.println("Transaction added to pool.");
                    break;

                case 2:
                    System.out.print("Enter miner name: ");
                    String miner = scanner.nextLine();
                    BlockChain.minePendingTransactions(miner);
                    break;

                case 3:
                    Gson gson = new GsonBuilder().setPrettyPrinting().create();
                    String blockchainJson = gson.toJson(BlockChain.blockchain);
                    System.out.println("\nBlockchain:\n" + blockchainJson);
                    break;

                case 4:
                    System.out.println("\nBlockchain valid? " + BlockChain.isChainValid());
                    break;

                case 5:
                    running = false;
                    break;

                default:
                    System.out.println("Invalid choice.");
            }
        }

        scanner.close();
    }
}
