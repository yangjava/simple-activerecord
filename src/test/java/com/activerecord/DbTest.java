package com.activerecord;

import java.util.List;

public class DbTest {

    public static void main(String[] args) {
        DataBase dataBase=DataBase.useDataBase("jdbc:mysql://localhost:3306/active-record?useUnicode=true&characterEncoding=UTF-8&zeroDateTimeBehavior=convertToNull&allowMultiQueries=true&useSSL=false", "root", "root");
        List<String> strings = dataBase.showTables();
        strings.stream().forEach(e-> System.out.println(e));
        Table student = dataBase.useTable("student");
        student.findById(1);
    }
}
