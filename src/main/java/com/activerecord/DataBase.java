package com.activerecord;

import com.activerecord.exception.ActiveRecordException;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DataBase {


    private Connection connection;

    private List<String> tables;

    private DataBase(Connection connection,List<String> tables) {
        this.connection = connection;
        this.tables=tables;
    }

    public static DataBase useDataBase(String url, String userName, String password){
        try {
            Connection connection= DriverManager.getConnection(url, userName,password);
            DatabaseMetaData db = connection.getMetaData();
            ResultSet rs = db.getTables(null, null, "%", new String[] {"TABLE"});
            List<String> tables=new ArrayList<>();
            while (rs.next()) {
                tables.add(rs.getString("table_name"));
            }
            return new DataBase(connection,tables);
        } catch (SQLException e) {
            throw  new RuntimeException(e);
        }
    }


    public List<String> showTables(){
      return this.tables;
    }

    public Table useTable(String tableName){
       if(this.tables.contains(tableName)){
         return new Table(this);
       }
       return null;
    }


    public PreparedStatement prepare(String sql, Object[] params, int[] types) {
        Connection c = this.connection;
        try {
            PreparedStatement call;
            if (sql.trim().toLowerCase().startsWith("insert")) {
                call = c.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            } else {
                call = c.prepareStatement(sql);
            }

            if (params != null && params.length > 0) {
                for (int i = 0; i < params.length; i++) {
                    if (params[i] != null) {
                        call.setObject(i + 1, params[i]);
                    } else {
                        call.setNull(i + 1, types[i]);
                    }
                }
            }
            return call;
        } catch (Exception e) {
            throw new ActiveRecordException();
        }
    }

    public ResultSet query(String sql, Object... params) {
        try {
            PreparedStatement call = prepare(sql, params, null);
            return call.executeQuery();
        } catch (SQLException e) {
            throw new ActiveRecordException();
        }
    }


}
