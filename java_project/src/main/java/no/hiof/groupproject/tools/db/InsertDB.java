package no.hiof.groupproject.tools.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/*
A loose coupled adaptor which inserts data into the database.
Create this class and then call the insert() method along with the following arguments:
    InsertDB db = new InsertDB();
    db.insert(String table, String col1, int col2);
The code is -LOOSELY BASED- on the code from https://www.sqlitetutorial.net/sqlite-java/insert/ taken at 13:00
on the 14/10/2022.
@author sqlitetutorial.net
 */
public class InsertDB {

    public void insert(String table, String nm, int ag) {

        //eg INSERT INTO people(name,age) VALUES('sam',29)
        String sql = "INSERT INTO " + table + "(name,age) VALUES(?,?)";

        try (Connection conn = ConnectDB.connect();
             PreparedStatement str = conn.prepareStatement(sql)) {
            str.setString(1, nm);
            str.setInt(2, ag);
            str.executeUpdate();
            //conn.close();   //not necessary - try catch closes the resource after being used
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
}