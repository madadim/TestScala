package com.test

import java.sql.SQLException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.DriverManager; 

object HiveJDBCconnection {
  def main(args: Array[String]): Unit = {
    println("TEst")
    val driver = "org.apache.hive.jdbc.HiveDriver"
    val url = "jdbc:hive2://192.168.59.137:10000/default"
    val username = "john"
    val password = "Home12#"

    // there's probably a better way to do this
    var connection: Connection = null

    try {
      // make the connection
      Class.forName(driver)
      connection = DriverManager.getConnection(url, username, password)

      // create the statement, and run the select query
      val statement = connection.createStatement()
      val resultSet = statement.executeQuery("select code, salary from sample_07 limit 10")
      while (resultSet.next()) {
        val code = resultSet.getString("code")
        val salary = resultSet.getString("salary")
        println("code, salary = " + code + ", " + salary)
      }
    } catch {
      case e => e.printStackTrace
    }
  }
}