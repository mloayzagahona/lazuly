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


public class SponsorColumnFixture extends DbInsertUpdateDeleteFixture{

   public String ID, WHERE_id; //id; // 
   public String NAME, WHERE_name; //name; // 
   public String PRIVILEGE_TYPE, WHERE_privilege_type; //privilegeType; // 
   public String STATUS, WHERE_status; //status; // 
   public String CONFERENCE_ID, WHERE_conference_id; //conferenceId; // 
   public String ADDRESS_ID, WHERE_address_id; //addressId; // 

	public static int NB_COLUMN = 6;
	
	protected int getNumberOfColumn() {
		return NB_COLUMN;
	}
	 
	protected String getTable() {
		return "sponsor";
	}
	
	protected Map<Integer, String> getColumnIndex () {
		Map<Integer, String> columnIndex = new HashMap<Integer, String>();
		int i = 0;
	    columnIndex.put(i++, "ID"); 
	    columnIndex.put(i++, "NAME"); 
	    columnIndex.put(i++, "PRIVILEGE_TYPE"); 
	    columnIndex.put(i++, "STATUS"); 
	    columnIndex.put(i++, "CONFERENCE_ID"); 
	    columnIndex.put(i++, "ADDRESS_ID"); 
	    return columnIndex;
	}
	
	protected Map<String, String> getColumnValue () {
	   Map<String, String> columnValue = new HashMap<String, String>();
	   columnValue.put("ID", ID); 
	   columnValue.put("NAME", NAME); 
	   columnValue.put("PRIVILEGE_TYPE", PRIVILEGE_TYPE); 
	   columnValue.put("STATUS", STATUS); 
	   columnValue.put("CONFERENCE_ID", CONFERENCE_ID); 
	   columnValue.put("ADDRESS_ID", ADDRESS_ID); 
	   return columnValue;
	 }	
	
	protected Map<Integer, String> getColumnWhereIndex () {
	   Map<Integer, String> columnIndex = new HashMap<Integer, String>();
	   int i = 0;
	   columnIndex.put(i++, "ID"); 
	   columnIndex.put(i++, "NAME"); 
	   columnIndex.put(i++, "PRIVILEGE_TYPE"); 
	   columnIndex.put(i++, "STATUS"); 
	   columnIndex.put(i++, "CONFERENCE_ID"); 
	   columnIndex.put(i++, "ADDRESS_ID"); 
	   return columnIndex;
	 }
	
	protected Map<String, String> getColumnWhereValue () {
	   Map<String, String> columnValue = new HashMap<String, String>();
	   columnValue.put("ID", WHERE_id); 
	   columnValue.put("NAME", WHERE_name); 
	   columnValue.put("PRIVILEGE_TYPE", WHERE_privilege_type); 
	   columnValue.put("STATUS", WHERE_status); 
	   columnValue.put("CONFERENCE_ID", WHERE_conference_id); 
	   columnValue.put("ADDRESS_ID", WHERE_address_id); 
	   return columnValue;
	}		

	protected Map<Integer, Column> getColumns() {
	   Map<Integer, Column> columnIndex = new HashMap<Integer, Column>();
	   int i = 0;
	   columnIndex.put(i++, new Column("ID", FieldType.BIGINT, true, 0)); //java.lang.Long
	   columnIndex.put(i++, new Column("NAME", FieldType.VARCHAR, true, 45)); //java.lang.String
	   columnIndex.put(i++, new Column("PRIVILEGE_TYPE", FieldType.VARCHAR, true, 45)); //java.lang.String
	   columnIndex.put(i++, new Column("STATUS", FieldType.VARCHAR, true, 45)); //java.lang.String
	   columnIndex.put(i++, new Column("CONFERENCE_ID", FieldType.BIGINT, true, 0)); //java.lang.Long
	   columnIndex.put(i++, new Column("ADDRESS_ID", FieldType.BIGINT, true, 0)); //java.lang.Long
	   return columnIndex;
	}		 
	
}
