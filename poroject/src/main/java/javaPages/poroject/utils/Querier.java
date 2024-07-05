package javaPages.poroject.utils;

import java.lang.reflect.Field;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.HashMap;
import java.util.LinkedList;

import javaPages.poroject.conf.DbConnect;

public class Querier {
    public static LinkedList<HashMap<String,Object>> query(String sql,LinkedList<Object> values){
        try (Connection conn=DbConnect.connection()){
            PreparedStatement ps=conn.prepareStatement(sql);
            for(int i=0;i<values.size();i++){
                ps.setObject(i+1, values.get(i));
            }
            
            ResultSet rs=ps.executeQuery();
            LinkedList<HashMap<String,Object>> results=new LinkedList<>();        
            
            while (rs.next()) {
                HashMap<String,Object> row=new HashMap<>();            
                for (int i = 1; i <= rs.getMetaData().getColumnCount(); i++) {                   
                    row.put(rs.getMetaData().getColumnName(i), rs.getObject(i));                    
                }
                results.add(row);
            }
            ps.close();
            conn.close();
            rs.close();
            return results;            
        } catch (Exception e) {
            System.err.println("Error at querying: "+e);
            return null;
        } 
    }
    public static <T> LinkedList<T> queryModel(String sql,LinkedList<Object> values,Class<T> class1){
        try (Connection conn=DbConnect.connection()){
            PreparedStatement ps=conn.prepareStatement(sql);
            for(int i=0;i<values.size();i++){
                ps.setObject(i+1, values.get(i));
            }            
            ResultSet rs=ps.executeQuery();
            LinkedList<T> results=new LinkedList<>(); 
            while(rs.next()){
                T obj = class1.getDeclaredConstructor().newInstance();
                Field[] fields=class1.getDeclaredFields();
                for (Field field:fields){
                    String fieldName=field.getName();
                    Object fieldValue=rs.getObject(fieldName);
                    field.setAccessible(true);
                    field.set(obj, fieldValue);
                }
                results.add(obj);
            }
            ps.close();
            conn.close();
            rs.close();
            return results;            
        } catch (Exception e) {
            System.err.println("Error at querying: "+e);
            return null;
        } 
    }
    

}
