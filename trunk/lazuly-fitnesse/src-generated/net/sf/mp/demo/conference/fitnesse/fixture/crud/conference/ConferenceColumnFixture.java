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


public class ConferenceColumnFixture extends DbInsertUpdateDeleteFixture{

   public String ID, WHERE_id; //id; // 
   public String NAME, WHERE_name; //name; // 
   public String START_DATE, WHERE_start_date; //startDate; // 
   public String END_DATE, WHERE_end_date; //endDate; // 
   public String ADDRESS_ID, WHERE_address_id; //addressId; // 

	public static int NB_COLUMN = 5;
	
	protected int getNumberOfColumn() {
		return NB_COLUMN;
	}
	 
	protected String getTable() {
		return "conference";
	}
	
	protected Map<Integer, String> getColumnIndex () {
		Map<Integer, String> columnIndex = new HashMap<Integer, String>();
		int i = 0;
	    columnIndex.put(i++, "ID"); 
	    columnIndex.put(i++, "NAME"); 
	    columnIndex.put(i++, "START_DATE"); 
	    columnIndex.put(i++, "END_DATE"); 
	    columnIndex.put(i++, "ADDRESS_ID"); 
	    return columnIndex;
	}
	
	protected Map<String, String> getColumnValue () {
	   Map<String, String> columnValue = new HashMap<String, String>();
	   columnValue.put("ID", ID); 
	   columnValue.put("NAME", NAME); 
	   columnValue.put("START_DATE", START_DATE); 
	   columnValue.put("END_DATE", END_DATE); 
	   columnValue.put("ADDRESS_ID", ADDRESS_ID); 
	   return columnValue;
	 }	
	
	protected Map<Integer, String> getColumnWhereIndex () {
	   Map<Integer, String> columnIndex = new HashMap<Integer, String>();
	   int i = 0;
	   columnIndex.put(i++, "ID"); 
	   columnIndex.put(i++, "NAME"); 
	   columnIndex.put(i++, "START_DATE"); 
	   columnIndex.put(i++, "END_DATE"); 
	   columnIndex.put(i++, "ADDRESS_ID"); 
	   return columnIndex;
	 }
	
	protected Map<String, String> getColumnWhereValue () {
	   Map<String, String> columnValue = new HashMap<String, String>();
	   columnValue.put("ID", WHERE_id); 
	   columnValue.put("NAME", WHERE_name); 
	   columnValue.put("START_DATE", WHERE_start_date); 
	   columnValue.put("END_DATE", WHERE_end_date); 
	   columnValue.put("ADDRESS_ID", WHERE_address_id); 
	   return columnValue;
	}		

	protected Map<Integer, Column> getColumns() {
	   Map<Integer, Column> columnIndex = new HashMap<Integer, Column>();
	   int i = 0;
	   columnIndex.put(i++, new Column("ID", FieldType.BIGINT, true, 0)); //java.lang.Long
	   columnIndex.put(i++, new Column("NAME", FieldType.VARCHAR, true, 255)); //java.lang.String
	   columnIndex.put(i++, new Column("START_DATE", FieldType.DATE, false, 0)); //java.lang.Date
	   columnIndex.put(i++, new Column("END_DATE", FieldType.DATE, false, 0)); //java.lang.Date
	   columnIndex.put(i++, new Column("ADDRESS_ID", FieldType.BIGINT, true, 0)); //java.lang.Long
	   return columnIndex;
	}		 
	
}
