package com.laba.solvd.databases.myBatis;

import java.io.InputStream;
import java.io.Reader;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

public class MyBatisObject {
  // Create a MyBatis configuration object
  private static SqlSessionFactory sqlSessionFactory;

  public static SqlSessionFactory getSqlSessionFactory() {
    if (sqlSessionFactory == null) {
      try {
        // Specify the path to your MyBatis configuration file
        String resource = "mybatis-config.xml";
        //InputStream inputStream = Resources.getResourceAsStream(resource);
        Reader reader = Resources.getResourceAsReader(resource);

        // Create the SqlSessionFactory
        sqlSessionFactory = new SqlSessionFactoryBuilder().build(reader);
      } catch (Exception e) {
        e.printStackTrace();
      }
    }
    return sqlSessionFactory;
  }

  public static SqlSession getSqlSession() {
    return sqlSessionFactory.openSession();
  }

}
