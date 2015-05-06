package Demonstrations;

import net.ravendb.abstractions.LinqOps;
import net.ravendb.client.IDocumentSession;
import net.ravendb.client.IDocumentStore;
import net.ravendb.client.document.DocumentStore;

/**
 * Created by bumax_000 on 5/6/2015.
 */
public class StreamingAPIDemo extends DemonstrationBase {

    IDocumentStore store;
    public StreamingAPIDemo(){
        store = new DocumentStore("http://localhost:8080", "ForgeryDB");
    }

    @Override
    public void Execute(){
        System.out.println("Press any key to start printing all accounts");
        try(IDocumentSession session = store.openSession()){
            
        }
    }

    @Override
    public void close(){
        store.close();
    }

}
