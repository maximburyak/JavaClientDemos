package Northwind;

import com.mysema.query.annotations.QueryEntity;

/**
 * Created by Maxim on 01/05/2015.
 */
@QueryEntity
public class Territory {
    private String code ;
    private String name ;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
