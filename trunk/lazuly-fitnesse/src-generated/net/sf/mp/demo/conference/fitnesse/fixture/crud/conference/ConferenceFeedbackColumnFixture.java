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

   public String ID, WHERE_id; //id; // 
   public String COMMENT, WHERE_comment; //comment; // 
   public String FEEDBACK_DATE, WHERE_feedback_date; //feedbackDate; // 
   public String CONFERENCE_ID, WHERE_conference_id; //conferenceId; // 
   public String CONFERENCE_MEMBER_ID, WHERE_conference_member_id; //conferenceMemberId; // 

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
	    columnIndex.put(i++, "ID"); 
	    columnIndex.put(i++, "COMMENT"); 
	    columnIndex.put(i++, "FEEDBACK_DATE"); 
	    columnIndex.put(i++, "CONFERENCE_ID"); 
	    columnIndex.put(i++, "CONFERENCE_MEMBER_ID"); 
	    return columnIndex;
	}
	
	protected Map<String, String> getColumnValue () {
	   Map<String, String> columnValue = new HashMap<String, String>();
	   columnValue.put("ID", ID); 
	   columnValue.put("COMMENT", COMMENT); 
	   columnValue.put("FEEDBACK_DATE", FEEDBACK_DATE); 
	   columnValue.put("CONFERENCE_ID", CONFERENCE_ID); 
	   columnValue.put("CONFERENCE_MEMBER_ID", CONFERENCE_MEMBER_ID); 
	   return columnValue;
	 }	
	
	protected Map<Integer, String> getColumnWhereIndex () {
	   Map<Integer, String> columnIndex = new HashMap<Integer, String>();
	   int i = 0;
	   columnIndex.put(i++, "ID"); 
	   columnIndex.put(i++, "COMMENT"); 
	   columnIndex.put(i++, "FEEDBACK_DATE"); 
	   columnIndex.put(i++, "CONFERENCE_ID"); 
	   columnIndex.put(i++, "CONFERENCE_MEMBER_ID"); 
	   return columnIndex;
	 }
	
	protected Map<String, String> getColumnWhereValue () {
	   Map<String, String> columnValue = new HashMap<String, String>();
	   columnValue.put("ID", WHERE_id); 
	   columnValue.put("COMMENT", WHERE_comment); 
	   columnValue.put("FEEDBACK_DATE", WHERE_feedback_date); 
	   columnValue.put("CONFERENCE_ID", WHERE_conference_id); 
	   columnValue.put("CONFERENCE_MEMBER_ID", WHERE_conference_member_id); 
	   return columnValue;
	}		

	protected Map<Integer, Column> getColumns() {
	   Map<Integer, Column> columnIndex = new HashMap<Integer, Column>();
	   int i = 0;
	   columnIndex.put(i++, new Column("ID", FieldType.INTEGER, true, 0)); //java.lang.Integer
	   columnIndex.put(i++, new Column("COMMENT", FieldType.LONGVARCHAR, true, 0)); //java.lang.String
	   columnIndex.put(i++, new Column("FEEDBACK_DATE", FieldType.TIMESTAMP, true, 0)); //java.lang.Timestamp
	   columnIndex.put(i++, new Column("CONFERENCE_ID", FieldType.BIGINT, true, 0)); //java.lang.Long
	   columnIndex.put(i++, new Column("CONFERENCE_MEMBER_ID", FieldType.BIGINT, true, 0)); //java.lang.Long
	   return columnIndex;
	}		 
	
}
