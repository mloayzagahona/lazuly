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


public class MemberPerRoleCountryAndConferenceColumnFixture extends DbInsertUpdateDeleteFixture{

   public String ID, WHERE_id; //id; // 
   public String STAT_MB_PER_CTRY_CONF_ID, WHERE_stat_mb_per_ctry_conf_id; //statMbPerCtryConfId; // 
   public String ROLE_NAME, WHERE_role_name; //roleName; // 
   public String NUMBER, WHERE_number; //number; // 

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
	    columnIndex.put(i++, "ID"); 
	    columnIndex.put(i++, "STAT_MB_PER_CTRY_CONF_ID"); 
	    columnIndex.put(i++, "ROLE_NAME"); 
	    columnIndex.put(i++, "NUMBER"); 
	    return columnIndex;
	}
	
	protected Map<String, String> getColumnValue () {
	   Map<String, String> columnValue = new HashMap<String, String>();
	   columnValue.put("ID", ID); 
	   columnValue.put("STAT_MB_PER_CTRY_CONF_ID", STAT_MB_PER_CTRY_CONF_ID); 
	   columnValue.put("ROLE_NAME", ROLE_NAME); 
	   columnValue.put("NUMBER", NUMBER); 
	   return columnValue;
	 }	
	
	protected Map<Integer, String> getColumnWhereIndex () {
	   Map<Integer, String> columnIndex = new HashMap<Integer, String>();
	   int i = 0;
	   columnIndex.put(i++, "ID"); 
	   columnIndex.put(i++, "STAT_MB_PER_CTRY_CONF_ID"); 
	   columnIndex.put(i++, "ROLE_NAME"); 
	   columnIndex.put(i++, "NUMBER"); 
	   return columnIndex;
	 }
	
	protected Map<String, String> getColumnWhereValue () {
	   Map<String, String> columnValue = new HashMap<String, String>();
	   columnValue.put("ID", WHERE_id); 
	   columnValue.put("STAT_MB_PER_CTRY_CONF_ID", WHERE_stat_mb_per_ctry_conf_id); 
	   columnValue.put("ROLE_NAME", WHERE_role_name); 
	   columnValue.put("NUMBER", WHERE_number); 
	   return columnValue;
	}		

	protected Map<Integer, Column> getColumns() {
	   Map<Integer, Column> columnIndex = new HashMap<Integer, Column>();
	   int i = 0;
	   columnIndex.put(i++, new Column("ID", FieldType.VARCHAR, false, 345)); //java.lang.String
	   columnIndex.put(i++, new Column("STAT_MB_PER_CTRY_CONF_ID", FieldType.VARCHAR, false, 300)); //java.lang.String
	   columnIndex.put(i++, new Column("ROLE_NAME", FieldType.VARCHAR, true, 45)); //java.lang.String
	   columnIndex.put(i++, new Column("NUMBER", FieldType.BIGINT, true, 0)); //java.lang.Long
	   return columnIndex;
	}		 
	
}
