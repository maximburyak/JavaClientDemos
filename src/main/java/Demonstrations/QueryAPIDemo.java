package Demonstrations;

import TransactionalBank.Account;
import TransactionalBank.QAccount;
import net.ravendb.abstractions.basic.CloseableIterator;
import net.ravendb.abstractions.data.StreamResult;
import net.ravendb.client.IDocumentSession;
import net.ravendb.client.IDocumentStore;
import net.ravendb.client.document.DocumentStore;
import net.ravendb.client.linq.IRavenQueryable;

import java.util.List;

/**
 * Created by bumax_000 on 5/6/2015.
 */
public class QueryAPIDemo extends DemonstrationBase {

    IDocumentStore store;
    public QueryAPIDemo(){
        store = new DocumentStore("http://localhost:8080", "TreasuryDB").initialize();
        //useFiddler(store);
    }

    @Override
    public void execute(){

        try(IDocumentSession session = store.openSession()){
            QAccount account = QAccount.account;
            Account johnAcc = session.query(Account.class)
                    .first(account.owner.eq("John"));

            // do something with this account
        }
    }

    @Override
    public void close(){
        store.close();
    }

}
