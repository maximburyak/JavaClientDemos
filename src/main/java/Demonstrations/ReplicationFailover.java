package Demonstrations;

import net.ravendb.client.IDocumentSession;
import net.ravendb.client.IDocumentStore;
import net.ravendb.client.document.DocumentStore;
import net.ravendb.client.document.FailoverBehavior;
import net.ravendb.client.document.FailoverBehaviorSet;

import java.io.BufferedReader;
import java.io.InputStreamReader;

/**
 * Created by bumax_000 on 5/3/2015.
 */
public class ReplicationFailover extends DemonstrationBase {
    private final TransactionDemo accountApi;

    IDocumentStore store;

    public ReplicationFailover() {
        accountApi = new TransactionDemo();
        store = new DocumentStore("http://localhost:8080", "MyNewDB");
        // initialization
        store.getConventions().
                setFailoverBehavior(
                        FailoverBehaviorSet.of(
                                FailoverBehavior.ALLOW_READS_FROM_SECONDARIES_AND_WRITES_TO_SECONDARIES));
        store.initialize();
    }

    @Override
    public void close() {

    }

    @Override
    public void execute() throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int input = 0;
        try {
            while (input != 4) {
                System.out.println("Enter Your Operation:");
                System.out.println("1) Create Account");
                System.out.println("2) Deposit/Withdraw");
                System.out.println("3) Delete Account");
                System.out.println("4) Quit");
                input = Integer.parseInt(br.readLine());
                try (IDocumentSession session = store.openSession()) {
                    switch (input) {
                        case 1:
                            accountApi.createAccount(br, session);
                            break;
                        case 2:
                            if (accountApi.deposit(br, session)) continue;
                            break;
                        case 3:
                            System.out.println("<accountId>");
                            accountApi.DeleteAcount(br, session);
                            break;
                        case 4:
                            return;
                    }
                }
            }

        } finally {

        }
    }
}
