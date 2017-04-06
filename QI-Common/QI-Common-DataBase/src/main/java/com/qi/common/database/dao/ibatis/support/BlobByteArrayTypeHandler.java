package com.qi.common.database.dao.ibatis.support;

import org.springframework.jdbc.support.lob.LobCreator;
import org.springframework.jdbc.support.lob.LobHandler;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Class BlobByteArrayTypeHandler
 *
 * @author 张麒 2016/5/13.
 * @version Description:
 */
public class BlobByteArrayTypeHandler extends AbstractLobTypeHandler {

    /**
     * Constructor used by iBATIS: fetches config-time LobHandler from
     * SqlMapClientFactoryBean.
     *
     * @see com.qi.common.database.dao.ibatis.SqlMapClientFactoryBean#getConfigTimeLobHandler
     */
    public BlobByteArrayTypeHandler() {
        super();
    }

    /**
     * Constructor used for testing: takes an explicit LobHandler.
     */
    protected BlobByteArrayTypeHandler(LobHandler lobHandler) {
        super(lobHandler);
    }

    @Override
    protected void setParameterInternal(
            PreparedStatement ps, int index, Object value, String jdbcType, LobCreator lobCreator)
            throws SQLException {
        lobCreator.setBlobAsBytes(ps, index, (byte[]) value);
    }

    @Override
    protected Object getResultInternal(ResultSet rs, int index, LobHandler lobHandler)
            throws SQLException {
        return lobHandler.getBlobAsBytes(rs, index);
    }

    public Object valueOf(String s) {
        return s.getBytes();
    }
}
