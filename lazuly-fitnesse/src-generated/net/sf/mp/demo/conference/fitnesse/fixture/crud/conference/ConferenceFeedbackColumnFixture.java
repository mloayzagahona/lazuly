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


public class ConferenceFeedbackColumnFixture extends DbInsertUpdateDeleteFixture{

   public String id, WHERE_id; //id; // 
   public String comment, WHERE_comment; //comment; // 
   public String feedback_date, WHERE_feedback_date; //feedbackDate; // 
   public String conference_id, WHERE_conference_id; //conferenceId; // 
   public String conference_member_id, WHERE_conference_member_id; //conferenceMemberId; // 

	 public static int NB_COLUMN = 5;
	
	 protected int getNumberOfColumn() {
		  return NB_COLUMN;
	 }
	 
	 protected String getTable() {
		  return "conference_feedback";
	 }
	
	 protected Map<Integer, String> getColumnIndex () {
	   Map<Integer, String> columnIndex = new HashMap<Integer, String>();
	   int i = 0;
	   columnIndex.put(i++, "id"); 
	   columnIndex.put(i++, "comment"); 
	   columnIndex.put(i++, "feedback_date"); 
	   columnIndex.put(i++, "conference_id"); 
	   columnIndex.put(i++, "conference_member_id"); 
	   return columnIndex;
	 }
	
	 protected Map<String, String> getColumnValue () {
	   Map<String, String> columnValue = new HashMap<String, String>();
	   columnValue.put("id", id); 
	   columnValue.put("comment", comment); 
	   columnValue.put("feedback_date", feedback_date); 
	   columnValue.put("conference_id", conference_id); 
	   columnValue.put("conference_member_id", conference_member_id); 
	   return columnValue;
	 }	
	
	 protected Map<Integer, String> getColumnWhereIndex () {
	   Map<Integer, String> columnIndex = new HashMap<Integer, String>();
	   int i = 0;
	   columnIndex.put(i++, "id"); 
	   columnIndex.put(i++, "comment"); 
	   columnIndex.put(i++, "feedback_date"); 
	   columnIndex.put(i++, "conference_id"); 
	   columnIndex.put(i++, "conference_member_id"); 
	   return columnIndex;
	 }
	
	 protected Map<String, String> getColumnWhereValue () {
	   Map<String, String> columnValue = new HashMap<String, String>();
	   columnValue.put("id", WHERE_id); 
	   columnValue.put("comment", WHERE_comment); 
	   columnValue.put("feedback_date", WHERE_feedback_date); 
	   columnValue.put("conference_id", WHERE_conference_id); 
	   columnValue.put("conference_member_id", WHERE_conference_member_id); 
	   return columnValue;
	 }		

	protected Map<Integer, Column> getColumns() {
	   Map<Integer, Column> columnIndex = new HashMap<Integer, Column>();
	   int i = 0;
	   columnIndex.put(i++, new Column("id", FieldType.INTEGER, true, 0)); //java.lang.Integer
	   columnIndex.put(i++, new Column("comment", FieldType.LONGVARCHAR, true, 0)); //java.lang.String
	   columnIndex.put(i++, new Column("feedback_date", FieldType.TIMESTAMP, true, 0)); //java.lang.Timestamp
	   columnIndex.put(i++, new Column("conference_id", FieldType.BIGINT, true, 0)); //java.lang.Long
	   columnIndex.put(i++, new Column("conference_member_id", FieldType.BIGINT, true, 0)); //java.lang.Long
	   return columnIndex;
	}		 
	
}
