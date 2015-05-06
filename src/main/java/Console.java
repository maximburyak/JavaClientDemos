import Demonstrations.BulkInsertDemo;
import Demonstrations.DemonstrationBase;
import Demonstrations.StreamingAPIDemo;
import Demonstrations.TransactionDemostration;
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
            System.out.println("3) StreamingAPI");
            System.out.println("4) Lazy Queries");
            System.out.println("5) Changes API");
            System.out.println("6) Aggressive Caching");
            System.out.println("7) Transformers (With Parameters) - mauro serviente");
            System.out.println("8) Streaming - demonstrations with fiddler and continuous query");

            System.out.println("20) Clear Screen");

            try {
                input  = Integer.parseInt(br.readLine());
            }
            catch (Exception ex){
                System.out.println(String.format("illegal input: %1$s", ex.getMessage()));
                continue;
            }

            if (input >= 1 && input <= 20)
                try {
                    processCommand(input);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            else if (input != -1)
                System.out.println(String.format("illegal input: %1$d", input));

            /*
            * WOW Features:
            * 1) Query Streaming
            * 2) Full Text Search (also, define indexes)
            * 3) Map Reduce
            * 4) Multi Map Reduce
            * 5) Map Reduce visualizer
            * 6) 
            * */
        }
    }

    public static DemonstrationBase GetDemonstration(int command){
        switch (command){
            case 1:
                return new TransactionDemostration();
            case 2:
                break;
            case 3:
                return new BulkInsertDemo();
            case 4:
                return new StreamingAPIDemo();
            default:
                break;
        }

        return null;
    }
    public static void processCommand(int command) throws  Exception{
        try( DemonstrationBase demo =  GetDemonstration(command)){
            demo.Execute();
        }
    }
}
