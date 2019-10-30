package com.activerecord;

import java.io.Serializable;
import java.math.BigInteger;
import java.util.Map;

/**
 * 信息记录
 */
public class Record implements Serializable {

    private Map<String, Object> columns;

    public Record(Map<String, Object> columns) {
        this.columns = columns;
    }

    public Map<String, Object> getColumns() {
        return columns;
    }

    public void setColumns(Map<String, Object> columns) {
        this.columns = columns;
    }

    public Record set(String column, Object value) {
        getColumns().put(column, value);
        return this;
    }

    public <T> T get(String column) {
        return (T)getColumns().get(column);
    }

    public <T> T get(String column, Object defaultValue) {
        Object result = getColumns().get(column);
        return (T)(result != null ? result : defaultValue);
    }

    public Object getObject(String column) {
        return getColumns().get(column);
    }

    public Object getObject(String column, Object defaultValue) {
        Object result = getColumns().get(column);
        return result != null ? result : defaultValue;
    }
    public String getStr(String column) {
        Object s = getColumns().get(column);
        return s != null ? s.toString() : null;
    }

    public Integer getInt(String column) {
        Number n = getNumber(column);
        return n != null ? n.intValue() : null;
    }

    public Number getNumber(String column) {
        return (Number)getColumns().get(column);
    }

    public Long getLong(String column) {
        Number n = getNumber(column);
        return n != null ? n.longValue() : null;
    }
    public BigInteger getBigInteger(String column) {
        return (BigInteger)getColumns().get(column);
    }

    public java.util.Date getDate(String column) {
        return (java.util.Date)getColumns().get(column);
    }

    public java.sql.Time getTime(String column) {
        return (java.sql.Time)getColumns().get(column);
    }

    public java.sql.Timestamp getTimestamp(String column) {
        return (java.sql.Timestamp)getColumns().get(column);
    }

    public Double getDouble(String column) {
        Number n = getNumber(column);
        return n != null ? n.doubleValue() : null;
    }

    public Float getFloat(String column) {
        Number n = getNumber(column);
        return n != null ? n.floatValue() : null;
    }

    public Short getShort(String column) {
        Number n = getNumber(column);
        return n != null ? n.shortValue() : null;
    }

    public Byte getByte(String column) {
        Number n = getNumber(column);
        return n != null ? n.byteValue() : null;
    }


    /**
     * Get column of mysql type: bit, tinyint(1)
     */
    public Boolean getBoolean(String column) {
        return (Boolean)getColumns().get(column);
    }

    /**
     * Get column of mysql type: decimal, numeric
     */
    public java.math.BigDecimal getBigDecimal(String column) {
        return (java.math.BigDecimal)getColumns().get(column);
    }


    public byte[] getBytes(String column) {
        return (byte[])getColumns().get(column);
    }

}
