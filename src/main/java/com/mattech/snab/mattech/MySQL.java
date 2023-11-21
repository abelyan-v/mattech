package com.mattech.snab.mattech;

import java.sql.*;
import java.util.ArrayList;

public class MySQL {
    Statement statement;

    public MySQL() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection connection = DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/com_mattech_snab", "root", ""
            );
            Statement statement = connection.createStatement();
            this.statement = statement;
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public ArrayList<String> SelectQuery(String sql) {
        try {
            ArrayList<String> output_list = new ArrayList<String>();
            int i = 0;
            ResultSet resultSet = this.statement.executeQuery(sql);
            while (resultSet.next())
                output_list.add(resultSet.getString(2));
            return output_list;
        } catch (Exception e) {
            ArrayList<String> output_list = new ArrayList<String>();
            output_list.add("mistake");
            System.out.println(e);
            return output_list;
        }
    }

    public void InsertQuery(String sql) {
        try {
            this.statement.execute(sql);
        }
        catch (Exception e){
            System.out.println(e);
        }
    }

    public ArrayList<ArrayList<String>> ApplicationSelectQuery(String sql) {
        try {
            ArrayList<ArrayList<String>> output_list = new ArrayList<ArrayList<String>>();
            ResultSet resultSet = this.statement.executeQuery(sql);
            while (resultSet.next()) {
                ArrayList<String> data = new ArrayList<String>();
                data.add(resultSet.getString(2));
                data.add(resultSet.getString(3));
                data.add(resultSet.getString(4));
                data.add(resultSet.getString(5));
                output_list.add(data);
            }
            return output_list;
        }
        catch (Exception e) {
            ArrayList<ArrayList<String>> output_list = null;
            System.out.println(e);
            return output_list;
        }
    }

    void ApplicationInsertQuery(String sql) {
        try {
            statement.execute(sql);
        } catch (Exception e) {
            System.out.println(e);
        }
    }
}
