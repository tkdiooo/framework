package com.qi.common.dao.jdbc;

import com.qi.common.constants.JdbcConstants.Driver;
import com.qi.common.util.ListUtil;

import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Class AbstractJdbcDao
 *
 * @author 张麒 2016/9/28.
 * @version Description:
 */
public abstract class AbstractJdbcDao {

    private Connection con = null;
    private PreparedStatement ps = null;
    private ResultSet rs = null;

    private void setParams(Object[] params) throws SQLException {
        for (int i = 0; i < params.length; i++) {
            if (params[i] instanceof Short) {
                ps.setShort(i + 1, (Short) params[i]);
            } else if (params[i] instanceof Integer) {
                ps.setInt(i + 1, (Integer) params[i]);
            } else if (params[i] instanceof Long) {
                ps.setLong(i + 1, (Long) params[i]);
            } else if (params[i] instanceof Float) {
                ps.setFloat(i + 1, (Float) params[i]);
            } else if (params[i] instanceof Double) {
                ps.setDouble(i + 1, (Double) params[i]);
            } else if (params[i] instanceof Byte) {
                ps.setByte(i + 1, (Byte) params[i]);
            } else if (params[i] instanceof String) {
                ps.setString(i + 1, (String) params[i]);
            } else if (params[i] instanceof Date) {
                SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss SSS");
                ps.setString(i + 1, format.format((Date) params[i]));
            }
        }
    }

    /**
     * 实例化PreparedStatement对象
     *
     * @param sql sql语句
     * @throws SQLException
     */
    private void InstancePreparedStatement(String sql) throws SQLException {
        ps = con.prepareStatement(sql);
    }

    /**
     * 实例化Connection连接对象
     *
     * @param url    数据库连接路径
     * @param dbUser 登录用户
     * @param dbPwd  登录密码
     * @param driver 数据库连接驱动枚举
     * @throws ClassNotFoundException
     * @throws SQLException
     */
    private void InstanceConnection(String url, String dbUser, String dbPwd, Driver driver) throws ClassNotFoundException, SQLException {
        Class.forName(driver.get());
        con = DriverManager.getConnection(url, dbUser, dbPwd);
    }

    /**
     * 执行不带参数查询方法
     *
     * @param url    数据库连接路径
     * @param dbUser 登录用户
     * @param dbPwd  登录密码
     * @param driver 数据库连接驱动枚举
     * @param sql    查询语句
     * @throws SQLException
     * @throws ClassNotFoundException
     */
    protected final void ExecuteQuery(String url, String dbUser, String dbPwd, Driver driver, String sql) throws ClassNotFoundException,
            SQLException {
        InstanceConnection(url, dbUser, dbPwd, driver);
        InstancePreparedStatement(sql);
        rs = ps.executeQuery();
    }

    /**
     * 执行带参数查询方法
     *
     * @param url    数据库连接路径
     * @param dbUser 登录用户
     * @param dbPwd  登录密码
     * @param driver 数据库连接驱动枚举
     * @param sql    查询语句
     * @param params 查询参数
     * @throws SQLException
     * @throws ClassNotFoundException
     */
    protected final void ExecuteQuery(String url, String dbUser, String dbPwd, Driver driver, String sql, Object[] params)
            throws ClassNotFoundException, SQLException {
        InstanceConnection(url, dbUser, dbPwd, driver);
        InstancePreparedStatement(sql);
        setParams(params);
        rs = ps.executeQuery();
    }

    /**
     * 执行持久化操作
     *
     * @param sql
     * @param params
     * @return int
     * @throws SQLException
     */
    protected final int ExecuteUpdate(String sql, Object[] params) throws SQLException {
        setParams(params);
        return ps.executeUpdate(sql);
    }

    @SuppressWarnings("unchecked")
    protected final <T> List<T> getResultSet(Class<T> cls) throws SQLException {
        if (cls.equals(Short.class)) {
            List<Short> dataSet = new ArrayList<>();
            while (rs.next()) {
                dataSet.add(rs.getShort(1));
            }
            return (List<T>) dataSet;
        } else if (cls.equals(Integer.class)) {
            List<Integer> dataSet = new ArrayList<>();
            while (rs.next()) {
                dataSet.add(rs.getInt(1));
            }
            return (List<T>) dataSet;
        } else if (cls.equals(Long.class)) {
            List<Long> dataSet = new ArrayList<>();
            while (rs.next()) {
                dataSet.add(rs.getLong(1));
            }
            return (List<T>) dataSet;
        } else if (cls.equals(Float.class)) {
            List<Float> dataSet = new ArrayList<>();
            while (rs.next()) {
                dataSet.add(rs.getFloat(1));
            }
            return (List<T>) dataSet;
        } else if (cls.equals(Double.class)) {
            List<Double> dataSet = new ArrayList<>();
            while (rs.next()) {
                dataSet.add(rs.getDouble(1));
            }
            return (List<T>) dataSet;
        } else if (cls.equals(Byte.class)) {
            List<Byte> dataSet = new ArrayList<>();
            while (rs.next()) {
                dataSet.add(rs.getByte(1));
            }
            return (List<T>) dataSet;
        } else if (cls.equals(String.class)) {
            List<String> dataSet = new ArrayList<>();
            while (rs.next()) {
                dataSet.add(rs.getString(1));
            }
            return (List<T>) dataSet;
        } else if (cls.equals(Date.class)) {
            List<Date> dataSet = new ArrayList<>();
            while (rs.next()) {
                dataSet.add(rs.getDate(1));
            }
            return (List<T>) dataSet;
        } else {
            return null;
        }
    }

    /**
     * 逐次关闭数据库连接
     *
     * @param rs  true：关闭ResultSet链接
     * @param ps  true：关闭PreparedStatement链接
     * @param con true：关闭Connection链接
     */
    protected void Close(boolean rs, boolean ps, boolean con) {
        try {
            if (rs && this.rs != null && !this.rs.isClosed())
                this.rs.close();
            if (ps && this.ps != null && !this.ps.isClosed())
                this.ps.close();
            if (con && this.con != null && !this.con.isClosed())
                this.con.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * 开启事务
     *
     * @throws SQLException
     */
    protected void BeginTransaction() throws SQLException {
        con.setAutoCommit(false);
    }

    /**
     * 提交事务
     *
     * @throws SQLException
     */
    protected void CommitTransaction() throws SQLException {
        con.commit();
    }

    /**
     * 事务回滚
     *
     * @throws SQLException
     */
    protected void RollbackTransaction() throws SQLException {
        con.rollback();
    }
}
