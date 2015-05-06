package Demonstrations;

import TransactionalBank.Account;
import TransactionalBank.Operation;
import net.ravendb.client.IDocumentSession;
import net.ravendb.client.IDocumentStore;
import net.ravendb.client.document.DocumentSession;
import net.ravendb.client.document.DocumentStore;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * Created by bumax_000 on 5/3/2015.
 */
public class TransactionDemostration implements IDemonstrationBase{
    public void Execute() throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        try(IDocumentStore store = new DocumentStore("http://localhost:8080", "Transaction").initialize()){
            int input = 0;
            IDocumentSession session = store.openSession();
            try {
                while(input != -1) {

                    try {
                        System.out.println("Enter Your Operation:");
                        System.out.println("1) Create Account");
                        System.out.println("2) Deposit/Withdraw");
                        System.out.println("3) Delete Account");
                        System.out.println("4) Submit Transaction");
                        System.out.println("5) Cancel Transaction");
                        System.out.println("6) Quit");

                        input = Integer.parseInt(br.readLine());
                        switch (input) {
                            case 1:
                                createAccount(br, session);
                                break;
                            case 2:
                                if (Deposit(br, session)) continue;
                                break;
                            case 3:
                                System.out.println("<accountId>");
                                session.delete(br.readLine());
                                break;
                            case 4:
                                session.saveChanges();
                                session.close();
                                session = store.openSession();
                                break;
                            case 5:
                                session.close();
                                session = store.openSession();
                                break;
                            case 6:
                                return;
                        }
                    }
                    catch (Exception ex){
                        System.out.print("Error occured" + ex.getMessage());
                        session.close();
                        session = store.openSession();
                    }

                }
            } finally {
                session.close();
            }
        }
    }

    private boolean Deposit(BufferedReader br, IDocumentSession session) throws IOException {
        System.out.println("<accountId> <delta>");
        String[] params = br.readLine().split("\\s");
        //QAddress a;
        Account existingAccount = session.load(Account.class, params[0]);
        if (existingAccount == null){
            System.out.println("No such account id: " + params[0]);
            return true;
        }
        Operation newOperation = new Operation();
        newOperation.setAccountId(params[0]);
        int delta = Integer.parseInt(params[1]);
        newOperation.setDelta(delta);
        existingAccount.setBalance(existingAccount.getBalance() + delta);
        session.store(existingAccount);
        return false;
    }

    private void createAccount(BufferedReader br, IDocumentSession session) throws IOException {
        System.out.println("<owner> <initial balance>");
        String[] params = br.readLine().split("\\s");
        Account newAccount = new Account();
        newAccount.setOwner(params[0]);
        newAccount.setBalance(Integer.parseInt(params[1]));
        session.store(newAccount);
        System.out.println(String.format("new account id: %1$s", session.advanced().getDocumentId(newAccount)));
    }
}
