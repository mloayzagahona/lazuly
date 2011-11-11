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


public class MemberPerCountryAndConferenceColumnFixture extends DbInsertUpdateDeleteFixture{

   public String ID, WHERE_id; //id; // 
   public String COUNTRY, WHERE_country; //country; // 
   public String CONFERENCE_NAME, WHERE_conference_name; //conferenceName; // 
   public String NUMBER, WHERE_number; //number; // 

	public static int NB_COLUMN = 4;
	
	protected int getNumberOfColumn() {
		return NB_COLUMN;
	}
	 
	protected String getTable() {
		return "stat_mb_per_ctry_conf";
	}
	
	protected Map<Integer, String> getColumnIndex () {
		Map<Integer, String> columnIndex = new HashMap<Integer, String>();
		int i = 0;
	    columnIndex.put(i++, "ID"); 
	    columnIndex.put(i++, "COUNTRY"); 
	    columnIndex.put(i++, "CONFERENCE_NAME"); 
	    columnIndex.put(i++, "NUMBER"); 
	    return columnIndex;
	}
	
	protected Map<String, String> getColumnValue () {
	   Map<String, String> columnValue = new HashMap<String, String>();
	   columnValue.put("ID", ID); 
	   columnValue.put("COUNTRY", COUNTRY); 
	   columnValue.put("CONFERENCE_NAME", CONFERENCE_NAME); 
	   columnValue.put("NUMBER", NUMBER); 
	   return columnValue;
	 }	
	
	protected Map<Integer, String> getColumnWhereIndex () {
	   Map<Integer, String> columnIndex = new HashMap<Integer, String>();
	   int i = 0;
	   columnIndex.put(i++, "ID"); 
	   columnIndex.put(i++, "COUNTRY"); 
	   columnIndex.put(i++, "CONFERENCE_NAME"); 
	   columnIndex.put(i++, "NUMBER"); 
	   return columnIndex;
	 }
	
	protected Map<String, String> getColumnWhereValue () {
	   Map<String, String> columnValue = new HashMap<String, String>();
	   columnValue.put("ID", WHERE_id); 
	   columnValue.put("COUNTRY", WHERE_country); 
	   columnValue.put("CONFERENCE_NAME", WHERE_conference_name); 
	   columnValue.put("NUMBER", WHERE_number); 
	   return columnValue;
	}		

	protected Map<Integer, Column> getColumns() {
	   Map<Integer, Column> columnIndex = new HashMap<Integer, Column>();
	   int i = 0;
	   columnIndex.put(i++, new Column("ID", FieldType.VARCHAR, false, 300)); //java.lang.String
	   columnIndex.put(i++, new Column("COUNTRY", FieldType.VARCHAR, true, 45)); //java.lang.String
	   columnIndex.put(i++, new Column("CONFERENCE_NAME", FieldType.VARCHAR, true, 255)); //java.lang.String
	   columnIndex.put(i++, new Column("NUMBER", FieldType.BIGINT, true, 0)); //java.lang.Long
	   return columnIndex;
	}		 
	
}
