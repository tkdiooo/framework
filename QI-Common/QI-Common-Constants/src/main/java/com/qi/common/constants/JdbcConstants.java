package com.qi.common.constants;

/**
 * Class JdbcConstants
 *
 * @author 张麒 2016/9/28.
 * @version Description:
 */
public class JdbcConstants {

    public enum Driver {

        /**
         * MySQL驱动<br />
         * MySQL(http://www.mysql.com)mysql-connector-java-2.0.14-bin.jar<br />
         * Class.forName( "org.gjt.mm.mysql.Driver" );<br />
         * cn = DriverManager.getConnection("jdbc:mysql://MyDbComputerNameOrIP:3306/myDatabaseName", sUsr, sPwd );<br />
         */
        MySQL("com.mysql.jdbc.Driver"),

        /**
         * PostgreSQL驱动<br />
         * PostgreSQL(http://www.de.postgresql.org)pgjdbc2.jar<br />
         * Class.forName("org.postgresql.Driver" );<br />
         * cn = DriverManager.getConnection("jdbc:postgresql://MyDbComputerNameOrIP/myDatabaseName", sUsr, sPwd );
         */
        PostgreSQL("org.postgresql.Driver"),

        /**
         * Oracle驱动<br />
         * Oracle(http://www.oracle.com/ip/deploy/database/oracle9i/)classes12.zip<br />
         * Class.forName( "oracle.jdbc.driver.OracleDriver" );<br />
         * cn = DriverManager.getConnection("jdbc:oracle:thin:@MyDbComputerNameOrIP:1521:ORCL", sUsr, sPwd );
         */
        Oracle("oracle.jdbc.driver.OracleDriver"),

        /**
         * Sybase驱动<br />
         * Sybase(http://jtds.sourceforge.net)jconn2.jar<br />
         * Class.forName("com.sybase.jdbc2.jdbc.SybDriver" );<br />
         * cn = DriverManager.getConnection("jdbc:sybase:Tds:MyDbComputerNameOrIP:2638", sUsr, sPwd );
         */
        Sybase("com.sybase.jdbc2.jdbc.SybDriver"),

        /**
         * SQLServerJTDS驱动<br />
         * Microsoft SQLServer(http://jtds.sourceforge.net)<br />
         * Class.forName("net.sourceforge.jtds.jdbc.Driver" );<br />
         * cn = DriverManager.getConnection("jdbc:jtds:sqlserver://MyDbComputerNameOrIP:1433/master", sUsr, sPwd );
         */
        SQLServerJTDS("net.sourceforge.jtds.jdbc.Driver"),

        /**
         * SQLServerJDBC驱动<br />
         * Class.forName( "sun.jdbc.odbc.JdbcOdbcDriver" );<br />
         * Connection cn = DriverManager.getConnection("jdbc:odbc:" + sDsn, sUsr, sPwd );
         */
        SQLServerJDBC("com.microsoft.jdbc.sqlserver.SQLServerDriver"),

        /**
         * DB2驱动<br />
         * Class.forName("com.ibm.db2.jdbc.net.DB2Driver");<br />
         * cn = DriverManager.getConnection("jdbc:db2://192.9.200.108:6789/SAMPLE", sUsr, sPwd );
         */
        DB2("com.ibm.db2.jdbc.net.DB2Driver");

        private final String driver;

        Driver(String driver) {
            this.driver = driver;
        }

        public String get() {
            return this.driver;
        }
    }
}
