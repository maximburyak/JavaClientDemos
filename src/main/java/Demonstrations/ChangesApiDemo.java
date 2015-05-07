package Demonstrations;

import TransactionalBank.Operation;
import net.ravendb.abstractions.closure.Action1;
import net.ravendb.abstractions.data.DocumentChangeNotification;
import net.ravendb.client.IDocumentSession;
import net.ravendb.client.document.DocumentSession;
import net.ravendb.client.document.DocumentStore;
import net.ravendb.client.utils.Observers;

import java.io.BufferedReader;
import java.io.InputStreamReader;


/**
 * Created by Tal on 5/6/2015.
 */
public class ChangesApiDemo extends DemonstrationBase {

    @Override
    public void Execute() throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        try(DocumentStore store = new DocumentStore("http://localhost:8080", "Northwind")) {
            store.initialize();
            store.changes("Northwind").forDocumentsStartingWith("orders").subscribe(Observers.create(new Action1<DocumentChangeNotification>() {
                @Override
                public void apply(DocumentChangeNotification change) {
                    System.out.println(change.getType() + " on document " + change.getId());
                }
            }));

            System.out.println("Started to listen to Orders collection, press any key to stop demo.");
            System.in.read();
        }
        }
}
