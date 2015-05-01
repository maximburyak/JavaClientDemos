package Northwind;

import com.mysema.query.annotations.QueryEntity;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;


/**
 * Created by Maxim on 30/04/2015.
 */
@QueryEntity
public class Order {

    private String company;

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    private String employee;

    public String getEmployee() {
        return employee;
    }

    public void setEmployee(String employee) {
        this.employee = employee;
    }

    private Date orderedAt;

    public Date getOrderedAt() {
        return orderedAt;
    }

    public void setOrderedAt(Date orderedAt) {
        this.orderedAt = orderedAt;
    }

    private Date requiredAt;

    public Date getRequiredAt() {
        return requiredAt;
    }

    public void setRequiredAt(Date requiredAt) {
        this.requiredAt = requiredAt;
    }

    private Date shippedAt;

    public Date getShippedAt() {
        return shippedAt;
    }

    public void setShippedAt(Date shippedAt) {
        this.shippedAt = shippedAt;
    }


    private Address shipTo;

    public Address getShipTo() {
        return shipTo;
    }

    public void setShipTo(Address shipTo) {
        this.shipTo = shipTo;
    }

    private String shipVia;

    public String getShipVia() {
        return shipVia;
    }

    public void setShipVia(String shipVia) {
        this.shipVia = shipVia;
    }

    private BigDecimal freight;

    public BigDecimal getFreight() {
        return freight;
    }

    public void setFreight(BigDecimal freight) {
        this.freight = freight;
    }

    private List<OrderLine> lines;

    public List<OrderLine> getLines() {
        return lines;
    }

    public void setLines(List<OrderLine> lines) {
        this.lines = lines;
    }

}
