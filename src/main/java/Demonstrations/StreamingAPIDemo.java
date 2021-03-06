package Demonstrations;

import TransactionalBank.Account;
import TransactionalBank.QAccount;
import net.ravendb.abstractions.LinqOps;
import net.ravendb.abstractions.basic.CloseableIterator;
import net.ravendb.abstractions.data.Constants;
import net.ravendb.abstractions.data.StreamResult;
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
        store = new DocumentStore("http://localhost:8080", "TreasuryDB").initialize();
        //useFiddler(store);
    }

    @Override
    public void execute(){
        System.out.println("Press any key to start printing all accounts");

        try(IDocumentSession session = store.openSession()){
            QAccount account = QAccount.account;
            IRavenQueryable accountsQuery = session.query(Account.class)
                    .where(account.owner.eq("foo"));
            CloseableIterator<StreamResult> accountsIteration = session.advanced().stream(accountsQuery);
            while(accountsIteration.hasNext()){
                Account curAccount = (Account)accountsIteration.next().getDocument();
                System.out.println(String.format("Owner: %1$s Balance: %2$s", curAccount.getOwner(), curAccount.getBalance()));
            }
        }
    }

    @Override
    public void close(){
        store.close();
    }

}
