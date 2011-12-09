package net.sf.mp.demo.conference.fitnesse.fixture.crud.statistics;

import java.sql.*;
import java.util.Date;
import java.util.List;
import java.util.ArrayList;
import java.util.Set;
import java.util.HashMap;
import java.util.Map;

import net.sf.minuteproject.fitnesse.fixture.DbInsertUpdateDeleteFixture;
import net.sf.minuteproject.model.db.Column;
import net.sf.minuteproject.model.db.type.FieldType;
import fit.ColumnFixture;


public class StatMbByRoleColumnFixture extends DbInsertUpdateDeleteFixture{

   public String id, WHERE_id; //id; // 
   public String stat_mb_per_ctry_conf_id, WHERE_stat_mb_per_ctry_conf_id; //statMbPerCtryConfId; // 
   public String role_name, WHERE_role_name; //roleName; // 
   public String number, WHERE_number; //number; // 

	 public static int NB_COLUMN = 4;
	
	 protected int getNumberOfColumn() {
		  return NB_COLUMN;
	 }
	 
	 protected String getTable() {
		  return "stat_mb_by_role";
	 }
	
	 protected Map<Integer, String> getColumnIndex () {
	   Map<Integer, String> columnIndex = new HashMap<Integer, String>();
	   int i = 0;
	   columnIndex.put(i++, "id"); 
	   columnIndex.put(i++, "stat_mb_per_ctry_conf_id"); 
	   columnIndex.put(i++, "role_name"); 
	   columnIndex.put(i++, "number"); 
	   return columnIndex;
	 }
	
	 protected Map<String, String> getColumnValue () {
	   Map<String, String> columnValue = new HashMap<String, String>();
	   columnValue.put("id", id); 
	   columnValue.put("stat_mb_per_ctry_conf_id", stat_mb_per_ctry_conf_id); 
	   columnValue.put("role_name", role_name); 
	   columnValue.put("number", number); 
	   return columnValue;
	 }	
	
	 protected Map<Integer, String> getColumnWhereIndex () {
	   Map<Integer, String> columnIndex = new HashMap<Integer, String>();
	   int i = 0;
	   columnIndex.put(i++, "id"); 
	   columnIndex.put(i++, "stat_mb_per_ctry_conf_id"); 
	   columnIndex.put(i++, "role_name"); 
	   columnIndex.put(i++, "number"); 
	   return columnIndex;
	 }
	
	 protected Map<String, String> getColumnWhereValue () {
	   Map<String, String> columnValue = new HashMap<String, String>();
	   columnValue.put("id", WHERE_id); 
	   columnValue.put("stat_mb_per_ctry_conf_id", WHERE_stat_mb_per_ctry_conf_id); 
	   columnValue.put("role_name", WHERE_role_name); 
	   columnValue.put("number", WHERE_number); 
	   return columnValue;
	 }		

	protected Map<Integer, Column> getColumns() {
	   Map<Integer, Column> columnIndex = new HashMap<Integer, Column>();
	   int i = 0;
	   columnIndex.put(i++, new Column("id", FieldType.VARCHAR, false, 345)); //java.lang.String
	   columnIndex.put(i++, new Column("stat_mb_per_ctry_conf_id", FieldType.VARCHAR, false, 300)); //java.lang.String
	   columnIndex.put(i++, new Column("role_name", FieldType.VARCHAR, true, 45)); //java.lang.String
	   columnIndex.put(i++, new Column("number", FieldType.BIGINT, true, 0)); //java.lang.Long
	   return columnIndex;
	}		 
	
}
