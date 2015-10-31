package Demonstrations;

import TransactionalBank.Account;
import com.google.common.base.Stopwatch;
import net.ravendb.client.IDocumentSession;
import net.ravendb.client.IDocumentStore;
import net.ravendb.client.document.BulkInsertOperation;
import net.ravendb.client.document.DocumentStore;

import java.io.BufferedReader;
import java.io.Closeable;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Random;

/**
 * Created by bumax_000 on 5/6/2015.
 */
public class BulkInsertDemo extends DemonstrationBase {

    IDocumentStore store;

    public BulkInsertDemo() {
        store = new DocumentStore("http://localhost:8080", "TreasuryDB").initialize();

    }

    @Override
    public void execute() throws Exception {
        System.out.println("Inserting amount of 20K accounts");

        Random random = new Random();
        Stopwatch sp = new Stopwatch();
        sp.start();


        //useFiddler(store);
        try (BulkInsertOperation insert = store.bulkInsert()) {

            for (int i = 0; i < 20000; i++) {

                Account newPerson = new Account();
                newPerson.setOwner("Johnny Doe" + i);
                newPerson.setBalance(random.nextInt(400000));
                insert.store(newPerson);
            }
        }

        System.out.println(String.format("Opertion finished in %1$s miliseconds", sp.elapsedMillis()));
        System.out.println("Press any key");
        new BufferedReader(new InputStreamReader(System.in)).readLine();

    }

    @Override
    public void close() throws IOException {
        store.close();
    }
}
