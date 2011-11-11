package net.sf.mp.demo.conference.fitnesse.fixture.crud.conference;

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


public class ConferenceMemberColumnFixture extends DbInsertUpdateDeleteFixture{

   public String ID, WHERE_id; //id; // 
   public String CONFERENCE_ID, WHERE_conference_id; //conferenceId; // 
   public String FIRST_NAME, WHERE_first_name; //firstName; // 
   public String LAST_NAME, WHERE_last_name; //lastName; // 
   public String EMAIL, WHERE_email; //email; // 
   public String ADDRESS_ID, WHERE_address_id; //addressId; // 
   public String STATUS, WHERE_status; //status; // 

	public static int NB_COLUMN = 7;
	
	protected int getNumberOfColumn() {
		return NB_COLUMN;
	}
	 
	protected String getTable() {
		return "conference_member";
	}
	
	protected Map<Integer, String> getColumnIndex () {
		Map<Integer, String> columnIndex = new HashMap<Integer, String>();
		int i = 0;
	    columnIndex.put(i++, "ID"); 
	    columnIndex.put(i++, "CONFERENCE_ID"); 
	    columnIndex.put(i++, "FIRST_NAME"); 
	    columnIndex.put(i++, "LAST_NAME"); 
	    columnIndex.put(i++, "EMAIL"); 
	    columnIndex.put(i++, "ADDRESS_ID"); 
	    columnIndex.put(i++, "STATUS"); 
	    return columnIndex;
	}
	
	protected Map<String, String> getColumnValue () {
	   Map<String, String> columnValue = new HashMap<String, String>();
	   columnValue.put("ID", ID); 
	   columnValue.put("CONFERENCE_ID", CONFERENCE_ID); 
	   columnValue.put("FIRST_NAME", FIRST_NAME); 
	   columnValue.put("LAST_NAME", LAST_NAME); 
	   columnValue.put("EMAIL", EMAIL); 
	   columnValue.put("ADDRESS_ID", ADDRESS_ID); 
	   columnValue.put("STATUS", STATUS); 
	   return columnValue;
	 }	
	
	protected Map<Integer, String> getColumnWhereIndex () {
	   Map<Integer, String> columnIndex = new HashMap<Integer, String>();
	   int i = 0;
	   columnIndex.put(i++, "ID"); 
	   columnIndex.put(i++, "CONFERENCE_ID"); 
	   columnIndex.put(i++, "FIRST_NAME"); 
	   columnIndex.put(i++, "LAST_NAME"); 
	   columnIndex.put(i++, "EMAIL"); 
	   columnIndex.put(i++, "ADDRESS_ID"); 
	   columnIndex.put(i++, "STATUS"); 
	   return columnIndex;
	 }
	
	protected Map<String, String> getColumnWhereValue () {
	   Map<String, String> columnValue = new HashMap<String, String>();
	   columnValue.put("ID", WHERE_id); 
	   columnValue.put("CONFERENCE_ID", WHERE_conference_id); 
	   columnValue.put("FIRST_NAME", WHERE_first_name); 
	   columnValue.put("LAST_NAME", WHERE_last_name); 
	   columnValue.put("EMAIL", WHERE_email); 
	   columnValue.put("ADDRESS_ID", WHERE_address_id); 
	   columnValue.put("STATUS", WHERE_status); 
	   return columnValue;
	}		

	protected Map<Integer, Column> getColumns() {
	   Map<Integer, Column> columnIndex = new HashMap<Integer, Column>();
	   int i = 0;
	   columnIndex.put(i++, new Column("ID", FieldType.BIGINT, true, 0)); //java.lang.Long
	   columnIndex.put(i++, new Column("CONFERENCE_ID", FieldType.BIGINT, true, 0)); //java.lang.Long
	   columnIndex.put(i++, new Column("FIRST_NAME", FieldType.VARCHAR, true, 255)); //java.lang.String
	   columnIndex.put(i++, new Column("LAST_NAME", FieldType.VARCHAR, true, 255)); //java.lang.String
	   columnIndex.put(i++, new Column("EMAIL", FieldType.VARCHAR, true, 255)); //java.lang.String
	   columnIndex.put(i++, new Column("ADDRESS_ID", FieldType.BIGINT, true, 0)); //java.lang.Long
	   columnIndex.put(i++, new Column("STATUS", FieldType.VARCHAR, true, 45)); //java.lang.String
	   return columnIndex;
	}		 
	
}
