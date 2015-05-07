package TransactionalBank;

import com.mysema.query.annotations.QueryEntity;

import java.util.Date;

/**
 * Created by bumax_000 on 5/4/2015.
 */
@QueryEntity
public class Operation {
    private String accountId;
    private int delta;
    private Date operationTime;
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

    public Date getOperationTime() {
        return operationTime;
    }

    public void setOperationTime(Date operationTime) {
        this.operationTime = operationTime;
    }
    @Override
    public String toString()
    {
        return "AccountId:\t" + this.accountId +"\n" + "Delta:\t" + this.delta +"\n" + "AcountId:\t" + operationTime.toString() +"\n";
    }
}
