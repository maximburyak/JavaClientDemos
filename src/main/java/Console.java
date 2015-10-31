import Demonstrations.*;
import TransactionalBank.QAccount;

import java.io.BufferedReader;
import java.io.InputStreamReader;

/**
 * Created by HR on 4/30/2015.
 */
public class Console {
    public static void main(String[] args){
        System.setProperty("org.apache.commons.logging.Log",
                "org.apache.commons.logging.impl.NoOpLog");
        QAccount dd;
        int input = 0;
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        while(input  != -1) {
            System.out.println("Choose Your Demonstration:");
            System.out.println("1) Transactions");
            System.out.println("2) Replication Failover");
            System.out.println("3) Bulk Insert");
            System.out.println("4) StreamingAPI");
            System.out.println("5) Changes API");
            System.out.println("6) Aggressive Caching");
            System.out.println("7) Transformers");

            try {
                input  = Integer.parseInt(br.readLine());
            }
            catch (Exception ex){
                System.out.println(String.format("illegal input: %1$s", ex.getMessage()));
            }

            if (input >= 1 && input <= 7)
                try {
                    processCommand(input);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            else if (input != -1)
                System.out.println(String.format("illegal input: %1$d", input));
        }
    }

    public static DemonstrationBase GetDemonstration(int command){
        switch (command){
            case 1:
                return new TransactionDemo();
            case 2:
                return new ReplicationFailover();
            case 3:
                return new BulkInsertDemo();
            case 4:
                return new StreamingAPIDemo();
            case 5:
                return new ChangesApiDemo();
            case 6:
                return new AggressiveCachingDemo();
            case 7:
                return new TransformerDemo();
            default:
                break;
        }
        return null;
    }

    public static void processCommand(int command) throws  Exception{
        try( DemonstrationBase demo =  GetDemonstration(command)){
            demo.execute();
        }
    }
}
