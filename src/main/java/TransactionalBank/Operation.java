package TransactionalBank;

import com.mysema.query.annotations.QueryEntity;

/**
 * Created by bumax_000 on 5/4/2015.
 */
@QueryEntity
public class Operation {
    private String accountId;
    private int delta;
    public String getAccountId() {
        return accountId;
    }

    public void setAccountId(String accountId) {
        this.accountId = accountId;
    }

    public int getDelta() {
        return delta;
    }

    public void setDelta(int delta) {
        this.delta = delta;
    }
}
