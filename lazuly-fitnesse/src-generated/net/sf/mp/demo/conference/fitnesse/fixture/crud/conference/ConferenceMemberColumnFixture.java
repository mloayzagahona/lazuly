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

   public String id, WHERE_id; //id; // 
   public String conference_id, WHERE_conference_id; //conferenceId; // 
   public String first_name, WHERE_first_name; //firstName; // 
   public String last_name, WHERE_last_name; //lastName; // 
   public String email, WHERE_email; //email; // 
   public String address_id, WHERE_address_id; //addressId; // 
   public String status, WHERE_status; //status; // 

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
	   columnIndex.put(i++, "id"); 
	   columnIndex.put(i++, "conference_id"); 
	   columnIndex.put(i++, "first_name"); 
	   columnIndex.put(i++, "last_name"); 
	   columnIndex.put(i++, "email"); 
	   columnIndex.put(i++, "address_id"); 
	   columnIndex.put(i++, "status"); 
	   return columnIndex;
	 }
	
	 protected Map<String, String> getColumnValue () {
	   Map<String, String> columnValue = new HashMap<String, String>();
	   columnValue.put("id", id); 
	   columnValue.put("conference_id", conference_id); 
	   columnValue.put("first_name", first_name); 
	   columnValue.put("last_name", last_name); 
	   columnValue.put("email", email); 
	   columnValue.put("address_id", address_id); 
	   columnValue.put("status", status); 
	   return columnValue;
	 }	
	
	 protected Map<Integer, String> getColumnWhereIndex () {
	   Map<Integer, String> columnIndex = new HashMap<Integer, String>();
	   int i = 0;
	   columnIndex.put(i++, "id"); 
	   columnIndex.put(i++, "conference_id"); 
	   columnIndex.put(i++, "first_name"); 
	   columnIndex.put(i++, "last_name"); 
	   columnIndex.put(i++, "email"); 
	   columnIndex.put(i++, "address_id"); 
	   columnIndex.put(i++, "status"); 
	   return columnIndex;
	 }
	
	 protected Map<String, String> getColumnWhereValue () {
	   Map<String, String> columnValue = new HashMap<String, String>();
	   columnValue.put("id", WHERE_id); 
	   columnValue.put("conference_id", WHERE_conference_id); 
	   columnValue.put("first_name", WHERE_first_name); 
	   columnValue.put("last_name", WHERE_last_name); 
	   columnValue.put("email", WHERE_email); 
	   columnValue.put("address_id", WHERE_address_id); 
	   columnValue.put("status", WHERE_status); 
	   return columnValue;
	 }		

	protected Map<Integer, Column> getColumns() {
	   Map<Integer, Column> columnIndex = new HashMap<Integer, Column>();
	   int i = 0;
	   columnIndex.put(i++, new Column("id", FieldType.BIGINT, true, 0)); //java.lang.Long
	   columnIndex.put(i++, new Column("conference_id", FieldType.BIGINT, true, 0)); //java.lang.Long
	   columnIndex.put(i++, new Column("first_name", FieldType.VARCHAR, true, 255)); //java.lang.String
	   columnIndex.put(i++, new Column("last_name", FieldType.VARCHAR, true, 255)); //java.lang.String
	   columnIndex.put(i++, new Column("email", FieldType.VARCHAR, true, 255)); //java.lang.String
	   columnIndex.put(i++, new Column("address_id", FieldType.BIGINT, true, 0)); //java.lang.Long
	   columnIndex.put(i++, new Column("status", FieldType.VARCHAR, true, 45)); //java.lang.String
	   return columnIndex;
	}		 
	
}
