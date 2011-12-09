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

   public String id, WHERE_id; //id; // 
   public String start_time, WHERE_start_time; //startTime; // 
   public String end_time, WHERE_end_time; //endTime; // 
   public String abstract, WHERE_abstract; //abstract; // 
   public String title, WHERE_title; //title; // 
   public String status, WHERE_status; //status; // 
   public String presentation_place_id, WHERE_presentation_place_id; //presentationPlaceId; // 
   public String proposal_time, WHERE_proposal_time; //proposalTime; // 

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
	   columnIndex.put(i++, "id"); 
	   columnIndex.put(i++, "start_time"); 
	   columnIndex.put(i++, "end_time"); 
	   columnIndex.put(i++, "abstract"); 
	   columnIndex.put(i++, "title"); 
	   columnIndex.put(i++, "status"); 
	   columnIndex.put(i++, "presentation_place_id"); 
	   columnIndex.put(i++, "proposal_time"); 
	   return columnIndex;
	 }
	
	 protected Map<String, String> getColumnValue () {
	   Map<String, String> columnValue = new HashMap<String, String>();
	   columnValue.put("id", id); 
	   columnValue.put("start_time", start_time); 
	   columnValue.put("end_time", end_time); 
	   columnValue.put("abstract", abstract); 
	   columnValue.put("title", title); 
	   columnValue.put("status", status); 
	   columnValue.put("presentation_place_id", presentation_place_id); 
	   columnValue.put("proposal_time", proposal_time); 
	   return columnValue;
	 }	
	
	 protected Map<Integer, String> getColumnWhereIndex () {
	   Map<Integer, String> columnIndex = new HashMap<Integer, String>();
	   int i = 0;
	   columnIndex.put(i++, "id"); 
	   columnIndex.put(i++, "start_time"); 
	   columnIndex.put(i++, "end_time"); 
	   columnIndex.put(i++, "abstract"); 
	   columnIndex.put(i++, "title"); 
	   columnIndex.put(i++, "status"); 
	   columnIndex.put(i++, "presentation_place_id"); 
	   columnIndex.put(i++, "proposal_time"); 
	   return columnIndex;
	 }
	
	 protected Map<String, String> getColumnWhereValue () {
	   Map<String, String> columnValue = new HashMap<String, String>();
	   columnValue.put("id", WHERE_id); 
	   columnValue.put("start_time", WHERE_start_time); 
	   columnValue.put("end_time", WHERE_end_time); 
	   columnValue.put("abstract", WHERE_abstract); 
	   columnValue.put("title", WHERE_title); 
	   columnValue.put("status", WHERE_status); 
	   columnValue.put("presentation_place_id", WHERE_presentation_place_id); 
	   columnValue.put("proposal_time", WHERE_proposal_time); 
	   return columnValue;
	 }		

	protected Map<Integer, Column> getColumns() {
	   Map<Integer, Column> columnIndex = new HashMap<Integer, Column>();
	   int i = 0;
	   columnIndex.put(i++, new Column("id", FieldType.BIGINT, true, 0)); //java.lang.Long
	   columnIndex.put(i++, new Column("start_time", FieldType.TIMESTAMP, true, 0)); //java.lang.Timestamp
	   columnIndex.put(i++, new Column("end_time", FieldType.TIMESTAMP, true, 0)); //java.lang.Timestamp
	   columnIndex.put(i++, new Column("abstract", FieldType.VARCHAR, true, 500)); //java.lang.String
	   columnIndex.put(i++, new Column("title", FieldType.VARCHAR, true, 255)); //java.lang.String
	   columnIndex.put(i++, new Column("status", FieldType.VARCHAR, true, 15)); //java.lang.String
	   columnIndex.put(i++, new Column("presentation_place_id", FieldType.BIGINT, false, 0)); //java.lang.Long
	   columnIndex.put(i++, new Column("proposal_time", FieldType.TIMESTAMP, false, 0)); //java.lang.Timestamp
	   return columnIndex;
	}		 
	
}
