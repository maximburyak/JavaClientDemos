package TransactionalBank;

import com.mysema.query.annotations.QueryEntity;

/**
 * Created by bumax_000 on 5/4/2015.
 */
@QueryEntity
public class Account {
    private String owner;
    private int balance;

    public String getOwner() {
        return owner;
    }

    public void setOwner(String owner) {
        this.owner = owner;
    }

    public int getBalance() {
        return balance;
    }

    public void setBalance(int balance) {
        this.balance = balance;
    }
}
