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

   public String ID, WHERE_id; //id; // 
   public String STREET1, WHERE_street1; //street1; // 
   public String STREET2, WHERE_street2; //street2; // 
   public String COUNTRY_ID, WHERE_country_id; //countryId; // 

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
	    columnIndex.put(i++, "ID"); 
	    columnIndex.put(i++, "STREET1"); 
	    columnIndex.put(i++, "STREET2"); 
	    columnIndex.put(i++, "COUNTRY_ID"); 
	    return columnIndex;
	}
	
	protected Map<String, String> getColumnValue () {
	   Map<String, String> columnValue = new HashMap<String, String>();
	   columnValue.put("ID", ID); 
	   columnValue.put("STREET1", STREET1); 
	   columnValue.put("STREET2", STREET2); 
	   columnValue.put("COUNTRY_ID", COUNTRY_ID); 
	   return columnValue;
	 }	
	
	protected Map<Integer, String> getColumnWhereIndex () {
	   Map<Integer, String> columnIndex = new HashMap<Integer, String>();
	   int i = 0;
	   columnIndex.put(i++, "ID"); 
	   columnIndex.put(i++, "STREET1"); 
	   columnIndex.put(i++, "STREET2"); 
	   columnIndex.put(i++, "COUNTRY_ID"); 
	   return columnIndex;
	 }
	
	protected Map<String, String> getColumnWhereValue () {
	   Map<String, String> columnValue = new HashMap<String, String>();
	   columnValue.put("ID", WHERE_id); 
	   columnValue.put("STREET1", WHERE_street1); 
	   columnValue.put("STREET2", WHERE_street2); 
	   columnValue.put("COUNTRY_ID", WHERE_country_id); 
	   return columnValue;
	}		

	protected Map<Integer, Column> getColumns() {
	   Map<Integer, Column> columnIndex = new HashMap<Integer, Column>();
	   int i = 0;
	   columnIndex.put(i++, new Column("ID", FieldType.BIGINT, true, 0)); //java.lang.Long
	   columnIndex.put(i++, new Column("STREET1", FieldType.VARCHAR, false, 255)); //java.lang.String
	   columnIndex.put(i++, new Column("STREET2", FieldType.VARCHAR, false, 255)); //java.lang.String
	   columnIndex.put(i++, new Column("COUNTRY_ID", FieldType.INTEGER, true, 0)); //java.lang.Integer
	   return columnIndex;
	}		 
	
}
