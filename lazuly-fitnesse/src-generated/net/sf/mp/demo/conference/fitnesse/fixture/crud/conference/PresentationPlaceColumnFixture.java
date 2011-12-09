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


public class PresentationPlaceColumnFixture extends DbInsertUpdateDeleteFixture{

   public String id, WHERE_id; //id; // 
   public String location, WHERE_location; //location; // 
   public String number_of_seats, WHERE_number_of_seats; //numberOfSeats; // 

	 public static int NB_COLUMN = 3;
	
	 protected int getNumberOfColumn() {
		  return NB_COLUMN;
	 }
	 
	 protected String getTable() {
		  return "presentation_place";
	 }
	
	 protected Map<Integer, String> getColumnIndex () {
	   Map<Integer, String> columnIndex = new HashMap<Integer, String>();
	   int i = 0;
	   columnIndex.put(i++, "id"); 
	   columnIndex.put(i++, "location"); 
	   columnIndex.put(i++, "number_of_seats"); 
	   return columnIndex;
	 }
	
	 protected Map<String, String> getColumnValue () {
	   Map<String, String> columnValue = new HashMap<String, String>();
	   columnValue.put("id", id); 
	   columnValue.put("location", location); 
	   columnValue.put("number_of_seats", number_of_seats); 
	   return columnValue;
	 }	
	
	 protected Map<Integer, String> getColumnWhereIndex () {
	   Map<Integer, String> columnIndex = new HashMap<Integer, String>();
	   int i = 0;
	   columnIndex.put(i++, "id"); 
	   columnIndex.put(i++, "location"); 
	   columnIndex.put(i++, "number_of_seats"); 
	   return columnIndex;
	 }
	
	 protected Map<String, String> getColumnWhereValue () {
	   Map<String, String> columnValue = new HashMap<String, String>();
	   columnValue.put("id", WHERE_id); 
	   columnValue.put("location", WHERE_location); 
	   columnValue.put("number_of_seats", WHERE_number_of_seats); 
	   return columnValue;
	 }		

	protected Map<Integer, Column> getColumns() {
	   Map<Integer, Column> columnIndex = new HashMap<Integer, Column>();
	   int i = 0;
	   columnIndex.put(i++, new Column("id", FieldType.BIGINT, true, 0)); //java.lang.Long
	   columnIndex.put(i++, new Column("location", FieldType.VARCHAR, false, 45)); //java.lang.String
	   columnIndex.put(i++, new Column("number_of_seats", FieldType.INTEGER, false, 0)); //java.lang.Integer
	   return columnIndex;
	}		 
	
}
