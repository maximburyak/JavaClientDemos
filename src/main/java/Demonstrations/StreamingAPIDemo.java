package Demonstrations;

import TransactionalBank.Account;
import net.ravendb.abstractions.LinqOps;
import net.ravendb.abstractions.basic.CloseableIterator;
import net.ravendb.abstractions.data.Constants;
import net.ravendb.client.IDocumentQuery;
import net.ravendb.client.IDocumentSession;
import net.ravendb.client.IDocumentStore;
import net.ravendb.client.document.DocumentStore;
import net.ravendb.client.linq.IRavenQueryable;

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
            IRavenQueryable accountsQuery = session.query(Account.class, "Raven/DocumentsByEntityName");
            CloseableIterator<Account> accountsIteration = session.advanced().stream(accountsQuery);
            while(accountsIteration.hasNext()){
                Account curAccount = accountsIteration.next();
                System.out.println(String.format("Owner: %1$s10 Balance: %2s10"));
            }
        }
    }

    @Override
    public void close(){
        store.close();
    }

}
