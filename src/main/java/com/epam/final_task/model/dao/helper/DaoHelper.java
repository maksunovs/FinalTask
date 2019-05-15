package com.epam.final_task.model.dao.helper;

import java.util.Map;

public class DaoHelper {
    public String makeInsertQuery(Map<String, Object> map, String tableName){
        StringBuilder builder = new StringBuilder();
        builder.append("INSERT INTO ");
        builder.append(tableName);
        builder.append("  (");
        int counter=0;
        for (Map.Entry<String, Object> pair : map.entrySet()) {
            builder.append(pair.getKey());
            if(counter<map.entrySet().size()-1) {
                builder.append(",");
            }
            counter++;
        }
        builder.append(") ");
        builder.append("VALUES ");
        builder.append("(");
        for (int i =0; i < map.entrySet().size(); i++ ){
            builder.append("?");
            if(i<map.entrySet().size()-1) {
                builder.append(",");
            }
        }
        builder.append(");");
        return builder.toString();
    }

    public String makeDeleteQuery(String tableName){
        StringBuilder builder = new StringBuilder();
        builder.append("DELETE FROM ");
        builder.append(tableName);
        builder.append(" WHERE id=?;");
        return  builder.toString();
    }
}
