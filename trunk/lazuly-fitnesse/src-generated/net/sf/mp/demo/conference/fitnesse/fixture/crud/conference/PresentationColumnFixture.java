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


public class PresentationColumnFixture extends DbInsertUpdateDeleteFixture{

   public String ID, WHERE_id; //id; // 
   public String START_TIME, WHERE_start_time; //startTime; // 
   public String END_TIME, WHERE_end_time; //endTime; // 
   public String ABSTRACT, WHERE_abstract; //abstractName; // 
   public String TITLE, WHERE_title; //title; // 
   public String STATUS, WHERE_status; //status; // 
   public String PRESENTATION_PLACE_ID, WHERE_presentation_place_id; //presentationPlaceId; // 
   public String PROPOSAL_TIME, WHERE_proposal_time; //proposalTime; // 

	public static int NB_COLUMN = 8;
	
	protected int getNumberOfColumn() {
		return NB_COLUMN;
	}
	 
	protected String getTable() {
		return "presentation";
	}
	
	protected Map<Integer, String> getColumnIndex () {
		Map<Integer, String> columnIndex = new HashMap<Integer, String>();
		int i = 0;
	    columnIndex.put(i++, "ID"); 
	    columnIndex.put(i++, "START_TIME"); 
	    columnIndex.put(i++, "END_TIME"); 
	    columnIndex.put(i++, "ABSTRACT"); 
	    columnIndex.put(i++, "TITLE"); 
	    columnIndex.put(i++, "STATUS"); 
	    columnIndex.put(i++, "PRESENTATION_PLACE_ID"); 
	    columnIndex.put(i++, "PROPOSAL_TIME"); 
	    return columnIndex;
	}
	
	protected Map<String, String> getColumnValue () {
	   Map<String, String> columnValue = new HashMap<String, String>();
	   columnValue.put("ID", ID); 
	   columnValue.put("START_TIME", START_TIME); 
	   columnValue.put("END_TIME", END_TIME); 
	   columnValue.put("ABSTRACT", ABSTRACT); 
	   columnValue.put("TITLE", TITLE); 
	   columnValue.put("STATUS", STATUS); 
	   columnValue.put("PRESENTATION_PLACE_ID", PRESENTATION_PLACE_ID); 
	   columnValue.put("PROPOSAL_TIME", PROPOSAL_TIME); 
	   return columnValue;
	 }	
	
	protected Map<Integer, String> getColumnWhereIndex () {
	   Map<Integer, String> columnIndex = new HashMap<Integer, String>();
	   int i = 0;
	   columnIndex.put(i++, "ID"); 
	   columnIndex.put(i++, "START_TIME"); 
	   columnIndex.put(i++, "END_TIME"); 
	   columnIndex.put(i++, "ABSTRACT"); 
	   columnIndex.put(i++, "TITLE"); 
	   columnIndex.put(i++, "STATUS"); 
	   columnIndex.put(i++, "PRESENTATION_PLACE_ID"); 
	   columnIndex.put(i++, "PROPOSAL_TIME"); 
	   return columnIndex;
	 }
	
	protected Map<String, String> getColumnWhereValue () {
	   Map<String, String> columnValue = new HashMap<String, String>();
	   columnValue.put("ID", WHERE_id); 
	   columnValue.put("START_TIME", WHERE_start_time); 
	   columnValue.put("END_TIME", WHERE_end_time); 
	   columnValue.put("ABSTRACT", WHERE_abstract); 
	   columnValue.put("TITLE", WHERE_title); 
	   columnValue.put("STATUS", WHERE_status); 
	   columnValue.put("PRESENTATION_PLACE_ID", WHERE_presentation_place_id); 
	   columnValue.put("PROPOSAL_TIME", WHERE_proposal_time); 
	   return columnValue;
	}		

	protected Map<Integer, Column> getColumns() {
	   Map<Integer, Column> columnIndex = new HashMap<Integer, Column>();
	   int i = 0;
	   columnIndex.put(i++, new Column("ID", FieldType.BIGINT, true, 0)); //java.lang.Long
	   columnIndex.put(i++, new Column("START_TIME", FieldType.TIMESTAMP, true, 0)); //java.lang.Timestamp
	   columnIndex.put(i++, new Column("END_TIME", FieldType.TIMESTAMP, true, 0)); //java.lang.Timestamp
	   columnIndex.put(i++, new Column("ABSTRACT", FieldType.VARCHAR, true, 500)); //java.lang.String
	   columnIndex.put(i++, new Column("TITLE", FieldType.VARCHAR, true, 255)); //java.lang.String
	   columnIndex.put(i++, new Column("STATUS", FieldType.VARCHAR, true, 15)); //java.lang.String
	   columnIndex.put(i++, new Column("PRESENTATION_PLACE_ID", FieldType.BIGINT, false, 0)); //java.lang.Long
	   columnIndex.put(i++, new Column("PROPOSAL_TIME", FieldType.TIMESTAMP, false, 0)); //java.lang.Timestamp
	   return columnIndex;
	}		 
	
}
