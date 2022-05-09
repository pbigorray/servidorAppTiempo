package model;

import com.mysql.cj.jdbc.MysqlDataSource;
import properties.MyConfig;

import javax.sql.DataSource;


public class MyDataSource {
    public static DataSource getMyMariaDBDataSource() {
        MysqlDataSource myDataSource = new MysqlDataSource();
        //jdbc:mysql://<host>:<port>/<schema>
        String host = MyConfig.getInstance().getDBHost();
        String port = MyConfig.getInstance().getDBPort();
        String schema = MyConfig.getInstance().getDBSchema();
        String user = MyConfig.getInstance().getUsername();
        String pass = MyConfig.getInstance().getPassword();

        myDataSource.setURL("jdbc:mysql://" + host + ":" + port + "/" + schema);
        myDataSource.setUser(user);
        myDataSource.setPassword(pass);

        return myDataSource;
    }
}
