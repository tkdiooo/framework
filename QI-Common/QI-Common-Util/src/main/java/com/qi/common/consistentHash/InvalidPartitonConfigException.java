package com.qi.common.consistentHash;

/**
 * Class InvalidPartitonConfigException
 *
 * @author 张麒 2016/5/16.
 * @version Description:
 */
public class InvalidPartitonConfigException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    public InvalidPartitonConfigException(String msg) {
        super(msg);
    }
}
