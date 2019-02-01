package com.egtinteractive.db;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;
import javax.sql.DataSource;
import com.mysql.jdbc.jdbc2.optional.MysqlDataSource;

public class DBFactory {

  public static DataSource getDataSource() {
    final Properties p = new Properties();
    FileInputStream fis = null;
    MysqlDataSource mysqlDS = null;
    try {
      fis = new FileInputStream("src/main/resources/configuration.properties");
      p.load(fis);
      mysqlDS = new MysqlDataSource();
      mysqlDS.setURL(p.getProperty("url"));
      mysqlDS.setUser(p.getProperty("user"));
      mysqlDS.setPassword(p.getProperty("password"));
    } catch (IOException e) {
      e.printStackTrace();
    }
    return mysqlDS;
  }
}
