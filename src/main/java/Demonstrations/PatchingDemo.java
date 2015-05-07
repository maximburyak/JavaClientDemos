package Demonstrations;

import net.ravendb.client.IDocumentSession;
import net.ravendb.client.IDocumentStore;
import net.ravendb.client.document.DocumentStore;

/**
 * Created by bumax_000 on 5/7/2015.
 */
public class PatchingDemo extends DemonstrationBase {
    IDocumentStore store;
    public PatchingDemo(){
        store = new DocumentStore("http://localhost:8080","ForgeryDB" ).initialize();
    }
    @Override
    public void execute(){
        store.getDatabaseCommands().patch()
    }

    @Override
    public void close(){

    }
}
