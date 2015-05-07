package Demonstrations;

import TransactionalBank.Operation;
import TransactionalBank.QOperation;
import net.ravendb.client.IDocumentSession;
import net.ravendb.client.document.DocumentStore;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Iterator;
import java.util.List;

/**
 * Created by Tal on 5/6/2015.
 */
public class TransformerDemo extends DemonstrationBase {
    @Override
    public void Execute() throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        try(DocumentStore store = new DocumentStore("http://localhost:8080","Transaction"))
        {
            store.initialize();
            try {
                store.executeTransformer(new OperationTransformer());
            } catch (Exception e)
            {
                System.out.println(e.getMessage());
            }
            int input = 0;
            while (input != 2){

                System.out.println("Enter Your Operation:");
                System.out.println("1) Query Operation by account Id");
                System.out.println("2) Quit");
                input = Integer.parseInt(br.readLine());
                switch (input) {
                    case 1:
                        System.out.println("<account id> <timezone>");
                        System.out.println("time zone option are:");
                        System.out.println("1 - Dateline Standard Time(GMT-12:00)");
                        System.out.println("2 - Pacific Time          (GMT-08:00)");
                        System.out.println("3 - Caracas, La Paz       (GMT-04:00)");
                        System.out.println("4 - Jerusalem             (GMT+12:00)");
                        try{
                            String[] params = br.readLine().split("\\s");
                            int timeZone = Integer.parseInt(params[1]);
                            String timeZoneStr = "";
                            try (IDocumentSession session = store.openSession();) {
                                switch (timeZone) {
                                    case 1:
                                        timeZoneStr ="Dateline%20Standard%20Time";
                                        break;
                                    case 2:
                                        timeZoneStr ="Pacific%20Time";
                                        break;
                                    case 3:
                                        timeZoneStr ="Caracas,%20La%20Paz";
                                        break;
                                    case 4:
                                        timeZoneStr ="Jerusalem";
                                        break;
                                }
                                List<Operation> res = session.query(Operation.class)
                                        .where(QOperation.operation.accountId.eq(params[0]))
                                        .transformWith(OperationTransformer.class, Operation.class)
                                        .addTransformerParameter("timeZone",  timeZoneStr)
                                        .toList();
                                for (Iterator<Operation> it = res.iterator(); it.hasNext();)
                                {
                                    Operation op = it.next();
                                    System.out.println(op.toString());
                                }
                            }
                        } catch (Exception e)
                        {
                            System.out.println("Please provide the input in the correct format.");
                            return;
                        }

                        break;
                    case 2:
                        return;
                }
            }
        }
        }
}
