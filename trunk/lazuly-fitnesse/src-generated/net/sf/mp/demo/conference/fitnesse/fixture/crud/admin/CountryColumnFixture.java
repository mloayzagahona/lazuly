package net.sf.mp.demo.conference.fitnesse.fixture.crud.admin;

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


public class CountryColumnFixture extends DbInsertUpdateDeleteFixture{

   public String ID, WHERE_id; //id; // 
   public String NAME, WHERE_name; //name; // 
   public String ISO_NAME, WHERE_iso_name; //isoName; // 

	public static int NB_COLUMN = 3;
	
	protected int getNumberOfColumn() {
		return NB_COLUMN;
	}
	 
	protected String getTable() {
		return "country";
	}
	
	protected Map<Integer, String> getColumnIndex () {
		Map<Integer, String> columnIndex = new HashMap<Integer, String>();
		int i = 0;
	    columnIndex.put(i++, "ID"); 
	    columnIndex.put(i++, "NAME"); 
	    columnIndex.put(i++, "ISO_NAME"); 
	    return columnIndex;
	}
	
	protected Map<String, String> getColumnValue () {
	   Map<String, String> columnValue = new HashMap<String, String>();
	   columnValue.put("ID", ID); 
	   columnValue.put("NAME", NAME); 
	   columnValue.put("ISO_NAME", ISO_NAME); 
	   return columnValue;
	 }	
	
	protected Map<Integer, String> getColumnWhereIndex () {
	   Map<Integer, String> columnIndex = new HashMap<Integer, String>();
	   int i = 0;
	   columnIndex.put(i++, "ID"); 
	   columnIndex.put(i++, "NAME"); 
	   columnIndex.put(i++, "ISO_NAME"); 
	   return columnIndex;
	 }
	
	protected Map<String, String> getColumnWhereValue () {
	   Map<String, String> columnValue = new HashMap<String, String>();
	   columnValue.put("ID", WHERE_id); 
	   columnValue.put("NAME", WHERE_name); 
	   columnValue.put("ISO_NAME", WHERE_iso_name); 
	   return columnValue;
	}		

	protected Map<Integer, Column> getColumns() {
	   Map<Integer, Column> columnIndex = new HashMap<Integer, Column>();
	   int i = 0;
	   columnIndex.put(i++, new Column("ID", FieldType.INTEGER, true, 0)); //java.lang.Integer
	   columnIndex.put(i++, new Column("NAME", FieldType.VARCHAR, true, 45)); //java.lang.String
	   columnIndex.put(i++, new Column("ISO_NAME", FieldType.VARCHAR, true, 45)); //java.lang.String
	   return columnIndex;
	}		 
	
}
