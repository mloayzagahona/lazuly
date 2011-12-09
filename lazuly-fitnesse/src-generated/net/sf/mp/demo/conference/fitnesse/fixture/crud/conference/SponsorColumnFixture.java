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

   public String id, WHERE_id; //id; // 
   public String name, WHERE_name; //name; // 
   public String privilege_type, WHERE_privilege_type; //privilegeType; // 
   public String status, WHERE_status; //status; // 
   public String conference_id, WHERE_conference_id; //conferenceId; // 
   public String address_id, WHERE_address_id; //addressId; // 

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
	   columnIndex.put(i++, "id"); 
	   columnIndex.put(i++, "name"); 
	   columnIndex.put(i++, "privilege_type"); 
	   columnIndex.put(i++, "status"); 
	   columnIndex.put(i++, "conference_id"); 
	   columnIndex.put(i++, "address_id"); 
	   return columnIndex;
	 }
	
	 protected Map<String, String> getColumnValue () {
	   Map<String, String> columnValue = new HashMap<String, String>();
	   columnValue.put("id", id); 
	   columnValue.put("name", name); 
	   columnValue.put("privilege_type", privilege_type); 
	   columnValue.put("status", status); 
	   columnValue.put("conference_id", conference_id); 
	   columnValue.put("address_id", address_id); 
	   return columnValue;
	 }	
	
	 protected Map<Integer, String> getColumnWhereIndex () {
	   Map<Integer, String> columnIndex = new HashMap<Integer, String>();
	   int i = 0;
	   columnIndex.put(i++, "id"); 
	   columnIndex.put(i++, "name"); 
	   columnIndex.put(i++, "privilege_type"); 
	   columnIndex.put(i++, "status"); 
	   columnIndex.put(i++, "conference_id"); 
	   columnIndex.put(i++, "address_id"); 
	   return columnIndex;
	 }
	
	 protected Map<String, String> getColumnWhereValue () {
	   Map<String, String> columnValue = new HashMap<String, String>();
	   columnValue.put("id", WHERE_id); 
	   columnValue.put("name", WHERE_name); 
	   columnValue.put("privilege_type", WHERE_privilege_type); 
	   columnValue.put("status", WHERE_status); 
	   columnValue.put("conference_id", WHERE_conference_id); 
	   columnValue.put("address_id", WHERE_address_id); 
	   return columnValue;
	 }		

	protected Map<Integer, Column> getColumns() {
	   Map<Integer, Column> columnIndex = new HashMap<Integer, Column>();
	   int i = 0;
	   columnIndex.put(i++, new Column("id", FieldType.BIGINT, true, 0)); //java.lang.Long
	   columnIndex.put(i++, new Column("name", FieldType.VARCHAR, true, 45)); //java.lang.String
	   columnIndex.put(i++, new Column("privilege_type", FieldType.VARCHAR, true, 45)); //java.lang.String
	   columnIndex.put(i++, new Column("status", FieldType.VARCHAR, true, 45)); //java.lang.String
	   columnIndex.put(i++, new Column("conference_id", FieldType.BIGINT, true, 0)); //java.lang.Long
	   columnIndex.put(i++, new Column("address_id", FieldType.BIGINT, true, 0)); //java.lang.Long
	   return columnIndex;
	}		 
	
}
