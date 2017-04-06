package com.qi.common.database.dao.ibatis.support;

import org.springframework.jdbc.support.lob.LobCreator;
import org.springframework.jdbc.support.lob.LobHandler;

import java.io.*;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Class BlobSerializableTypeHandler
 *
 * @author 张麒 2016/5/13.
 * @version Description:
 */
public class BlobSerializableTypeHandler extends AbstractLobTypeHandler {


    /**
     * Constructor used by iBATIS: fetches config-time LobHandler from
     * SqlMapClientFactoryBean.
     *
     * @see com.qi.common.database.dao.ibatis.SqlMapClientFactoryBean#getConfigTimeLobHandler
     */
    public BlobSerializableTypeHandler() {
        super();
    }

    /**
     * Constructor used for testing: takes an explicit LobHandler.
     */
    protected BlobSerializableTypeHandler(LobHandler lobHandler) {
        super(lobHandler);
    }

    @Override
    protected void setParameterInternal(
            PreparedStatement ps, int index, Object value, String jdbcType, LobCreator lobCreator)
            throws SQLException, IOException {

        if (value != null) {
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            ObjectOutputStream oos = new ObjectOutputStream(baos);
            try {
                oos.writeObject(value);
                oos.flush();
                lobCreator.setBlobAsBytes(ps, index, baos.toByteArray());
            } finally {
                oos.close();
            }
        } else {
            lobCreator.setBlobAsBytes(ps, index, null);
        }
    }

    @Override
    protected Object getResultInternal(ResultSet rs, int index, LobHandler lobHandler)
            throws SQLException, IOException {

        InputStream is = lobHandler.getBlobAsBinaryStream(rs, index);
        if (is != null) {
            ObjectInputStream ois = new ObjectInputStream(is);
            try {
                return ois.readObject();
            } catch (ClassNotFoundException ex) {
                throw new SQLException("Could not deserialize BLOB contents: " + ex.getMessage());
            } finally {
                ois.close();
            }
        } else {
            return null;
        }
    }

    public Object valueOf(String s) {
        return s;
    }
}
