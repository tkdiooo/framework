package com.qi.common.database.dto;

import java.io.Serializable;

/**
 * Class IBaseDto
 *
 * @author 张麒 2016/5/13.
 * @version Description:
 */
public interface IBaseDto extends Serializable {

    String toString();

    boolean equals(Object o);

    int hashCode();
}
