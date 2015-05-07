package Demonstrations;

import TransactionalBank.Account;
import net.ravendb.client.IDocumentSession;
import net.ravendb.client.IDocumentStore;
import net.ravendb.client.delegates.RequestCachePolicy;
import net.ravendb.client.document.DocumentStore;

import java.io.BufferedReader;
import java.io.InputStreamReader;

/**
 * Created by bumax_000 on 5/6/2015.
 */
public class AggressiveCachingDemo extends DemonstrationBase {

    IDocumentStore store;

    public AggressiveCachingDemo(){
        store = new DocumentStore("http://localhost:8080", "ForgeryDB").initialize();
        store.getConventions().setShouldCacheRequest(new RequestCachePolicy() {
            @SuppressWarnings("boxing")
            @Override
            public Boolean shouldCacheRequest(String url) {
                return true;
            }
        });
        useFiddler(store);
    }

    @Override
    public void execute() throws  Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        System.out.println("Enter Enter To Get Recent Account Amount, press -1 to quit:");

        try (AutoCloseable scope = store.aggressivelyCache()) {
            while (true){
                String input = br.readLine();
                if (input.contentEquals("-1"))
                    return;
                try(IDocumentSession session = store.openSession()){
                    int accountAmount = session.query(Account.class,  "Raven/DocumentsByEntityName").count();
                    System.out.println(accountAmount);
                }
            }
        }


    }
    @Override
    public void close(){
        store.close();
    }
}
