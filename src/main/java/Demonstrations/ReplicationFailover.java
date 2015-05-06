package Demonstrations;

import net.ravendb.client.IDocumentSession;
import net.ravendb.client.document.DocumentQueryCustomizationFactory;
import net.ravendb.client.document.DocumentStore;
import net.ravendb.client.document.FailoverBehavior;
import net.ravendb.client.document.FailoverBehaviorSet;
import org.apache.commons.lang.time.StopWatch;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Random;

/**
 * Created by bumax_000 on 5/3/2015.
 */
public class ReplicationFailover {

    public static void InsertDataWithReplication(){
        try(DocumentStore store = new DocumentStore("http://localhost:8080", "MyNewDB")){

            // initialization
            store.getConventions().
                    setFailoverBehavior(
                            FailoverBehaviorSet.of(
                                    FailoverBehavior.ALLOW_READS_FROM_SECONDARIES_AND_WRITES_TO_SECONDARIES));
            store.initialize();
        }

    }
}
