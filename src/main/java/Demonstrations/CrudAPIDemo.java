package Demonstrations;

import TransactionalBank.Account;
import TransactionalBank.QAccount;
import net.ravendb.client.IDocumentSession;
import net.ravendb.client.IDocumentStore;
import net.ravendb.client.document.DocumentStore;
import net.ravendb.client.linq.IRavenQueryable;

/**
 * Created by bumax_000 on 5/6/2015.
 */
public class CrudAPIDemo extends DemonstrationBase {

    IDocumentStore store;
    public CrudAPIDemo(){
        store = new DocumentStore("http://localhost:8080", "TreasuryDB").initialize();
        //useFiddler(store);
    }

    @Override
    public void execute(){

        try(IDocumentSession session = store.openSession()){

            Account acc = new Account();
            acc.setOwner("John");
            session.store(acc);

            session.saveChanges();
        }

        try(IDocumentSession session = store.openSession()){

            Account acc = session.load(Account.class, "accounts/1");

            acc.setBalance(10);

            session.saveChanges();
        }
    }

    @Override
    public void close(){
        store.close();
    }

}
