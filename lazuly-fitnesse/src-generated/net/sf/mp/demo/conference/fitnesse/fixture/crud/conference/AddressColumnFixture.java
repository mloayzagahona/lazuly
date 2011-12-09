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


public class AddressColumnFixture extends DbInsertUpdateDeleteFixture{

   public String id, WHERE_id; //id; // 
   public String street1, WHERE_street1; //street1; // 
   public String street2, WHERE_street2; //street2; // 
   public String country_id, WHERE_country_id; //countryId; // 

	 public static int NB_COLUMN = 4;
	
	 protected int getNumberOfColumn() {
		  return NB_COLUMN;
	 }
	 
	 protected String getTable() {
		  return "address";
	 }
	
	 protected Map<Integer, String> getColumnIndex () {
	   Map<Integer, String> columnIndex = new HashMap<Integer, String>();
	   int i = 0;
	   columnIndex.put(i++, "id"); 
	   columnIndex.put(i++, "street1"); 
	   columnIndex.put(i++, "street2"); 
	   columnIndex.put(i++, "country_id"); 
	   return columnIndex;
	 }
	
	 protected Map<String, String> getColumnValue () {
	   Map<String, String> columnValue = new HashMap<String, String>();
	   columnValue.put("id", id); 
	   columnValue.put("street1", street1); 
	   columnValue.put("street2", street2); 
	   columnValue.put("country_id", country_id); 
	   return columnValue;
	 }	
	
	 protected Map<Integer, String> getColumnWhereIndex () {
	   Map<Integer, String> columnIndex = new HashMap<Integer, String>();
	   int i = 0;
	   columnIndex.put(i++, "id"); 
	   columnIndex.put(i++, "street1"); 
	   columnIndex.put(i++, "street2"); 
	   columnIndex.put(i++, "country_id"); 
	   return columnIndex;
	 }
	
	 protected Map<String, String> getColumnWhereValue () {
	   Map<String, String> columnValue = new HashMap<String, String>();
	   columnValue.put("id", WHERE_id); 
	   columnValue.put("street1", WHERE_street1); 
	   columnValue.put("street2", WHERE_street2); 
	   columnValue.put("country_id", WHERE_country_id); 
	   return columnValue;
	 }		

	protected Map<Integer, Column> getColumns() {
	   Map<Integer, Column> columnIndex = new HashMap<Integer, Column>();
	   int i = 0;
	   columnIndex.put(i++, new Column("id", FieldType.BIGINT, true, 0)); //java.lang.Long
	   columnIndex.put(i++, new Column("street1", FieldType.VARCHAR, false, 255)); //java.lang.String
	   columnIndex.put(i++, new Column("street2", FieldType.VARCHAR, false, 255)); //java.lang.String
	   columnIndex.put(i++, new Column("country_id", FieldType.INTEGER, true, 0)); //java.lang.Integer
	   return columnIndex;
	}		 
	
}
