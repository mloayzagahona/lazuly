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

   public String ID, WHERE_id; //id; // 
   public String LOCATION, WHERE_location; //location; // 
   public String NUMBER_OF_SEATS, WHERE_number_of_seats; //numberOfSeats; // 

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
	    columnIndex.put(i++, "ID"); 
	    columnIndex.put(i++, "LOCATION"); 
	    columnIndex.put(i++, "NUMBER_OF_SEATS"); 
	    return columnIndex;
	}
	
	protected Map<String, String> getColumnValue () {
	   Map<String, String> columnValue = new HashMap<String, String>();
	   columnValue.put("ID", ID); 
	   columnValue.put("LOCATION", LOCATION); 
	   columnValue.put("NUMBER_OF_SEATS", NUMBER_OF_SEATS); 
	   return columnValue;
	 }	
	
	protected Map<Integer, String> getColumnWhereIndex () {
	   Map<Integer, String> columnIndex = new HashMap<Integer, String>();
	   int i = 0;
	   columnIndex.put(i++, "ID"); 
	   columnIndex.put(i++, "LOCATION"); 
	   columnIndex.put(i++, "NUMBER_OF_SEATS"); 
	   return columnIndex;
	 }
	
	protected Map<String, String> getColumnWhereValue () {
	   Map<String, String> columnValue = new HashMap<String, String>();
	   columnValue.put("ID", WHERE_id); 
	   columnValue.put("LOCATION", WHERE_location); 
	   columnValue.put("NUMBER_OF_SEATS", WHERE_number_of_seats); 
	   return columnValue;
	}		

	protected Map<Integer, Column> getColumns() {
	   Map<Integer, Column> columnIndex = new HashMap<Integer, Column>();
	   int i = 0;
	   columnIndex.put(i++, new Column("ID", FieldType.BIGINT, true, 0)); //java.lang.Long
	   columnIndex.put(i++, new Column("LOCATION", FieldType.VARCHAR, false, 45)); //java.lang.String
	   columnIndex.put(i++, new Column("NUMBER_OF_SEATS", FieldType.INTEGER, false, 0)); //java.lang.Integer
	   return columnIndex;
	}		 
	
}
