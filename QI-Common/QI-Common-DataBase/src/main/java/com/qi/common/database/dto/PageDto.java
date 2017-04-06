package com.qi.common.database.dto;

/**
 * Class PageDto
 *
 * @author 张麒 2016/5/16.
 * @version Description:
 */
public class PageDto<T extends BaseDtoImpl> extends BaseListDto {

    private static final long serialVersionUID = -2762689922407395921L;

    private T dto;

    public void setDto(T dto) {
        this.dto = dto;
    }

    public T getDto() {
        return dto;
    }
}
