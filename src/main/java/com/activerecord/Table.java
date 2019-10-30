package com.activerecord;

import java.io.Serializable;
import java.sql.ResultSet;
import java.util.LinkedList;
import java.util.List;

public class Table {

    private DataBase dataBase;

    private String tableName = "student";

    public Table(DataBase dataBase) {
        this.dataBase = dataBase;
    }

    List<Record> findById(Serializable id) {
        List<Record> records = new LinkedList<>();
        String sql = "select * from " + tableName + " where id = ?";
        ResultSet rs = dataBase.query(sql, id);
        try {

            while (rs.next()){
                System.out.println(rs.getString("name"));
            }
        }catch (Exception e){

        }
        return records;
    }


}
