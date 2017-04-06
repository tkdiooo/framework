package com.qi.common.database.datasource;

import com.qi.common.database.datasource.dynamic.DbTypeHolder;

/**
 * Class ReadWritExcepiton
 *
 * @author 张麒 2016/5/13.
 * @version Description:
 */
public class ReadWritExcepiton extends RuntimeException {


    /**
     *
     */
    private static final long serialVersionUID = 1L;

    public ReadWritExcepiton(String message, Exception cause) {
        super("DataSource[" + DbTypeHolder.getDbType() + "]  target Exception message: " + cause.getMessage(), cause);
    }

    public ReadWritExcepiton(String message) {
        super("DataSource[" + DbTypeHolder.getDbType() + "]" + message);
    }

    public ReadWritExcepiton(Exception cause) {
        super(cause);
    }
}
