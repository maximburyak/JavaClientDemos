package Demonstrations;

import net.ravendb.abstractions.basic.EventHandler;
import net.ravendb.abstractions.connection.WebRequestEventArgs;
import net.ravendb.client.IDocumentStore;
import org.apache.http.HttpHost;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.HttpRequestBase;

import java.io.Closeable;
import java.io.IOException;

/**
 * Created by bumax_000 on 5/3/2015.
 */
public class DemonstrationBase  implements Closeable {
    public void execute() throws Exception{

    }

    public static void useFiddler(IDocumentStore store) {
        store.getJsonRequestFactory().addConfigureRequestEventHandler(new FiddlerConfigureRequestHandler());
    }

    @Override
    public void close() throws IOException {

    }

    public static class FiddlerConfigureRequestHandler implements EventHandler<WebRequestEventArgs> {
        @Override
        public void handle(Object sender, WebRequestEventArgs event) {
            HttpRequestBase requestBase = (HttpRequestBase) event.getRequest();
            HttpHost proxy = new HttpHost("127.0.0.1", 8888, "http");
            RequestConfig requestConfig = requestBase.getConfig();
            if (requestConfig == null) {
                requestConfig = RequestConfig.DEFAULT;
            }
            requestConfig = RequestConfig.copy(requestConfig).setProxy(proxy).build();
            requestBase.setConfig(requestConfig);

        }
    }
}
