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


public class EvaluationColumnFixture extends DbInsertUpdateDeleteFixture{

   public String ID, WHERE_id; //id; // 
   public String CONFERENCE_MEMBER_ID, WHERE_conference_member_id; //conferenceMemberId; // 
   public String PRESENTATION_ID, WHERE_presentation_id; //presentationId; // 
   public String STAR, WHERE_star; //star; // 
   public String COMMENT, WHERE_comment; //comment; // 
   public String EVALUATION_DATE, WHERE_evaluation_date; //evaluationDate; // 

	public static int NB_COLUMN = 6;
	
	protected int getNumberOfColumn() {
		return NB_COLUMN;
	}
	 
	protected String getTable() {
		return "evaluation";
	}
	
	protected Map<Integer, String> getColumnIndex () {
		Map<Integer, String> columnIndex = new HashMap<Integer, String>();
		int i = 0;
	    columnIndex.put(i++, "ID"); 
	    columnIndex.put(i++, "CONFERENCE_MEMBER_ID"); 
	    columnIndex.put(i++, "PRESENTATION_ID"); 
	    columnIndex.put(i++, "STAR"); 
	    columnIndex.put(i++, "COMMENT"); 
	    columnIndex.put(i++, "EVALUATION_DATE"); 
	    return columnIndex;
	}
	
	protected Map<String, String> getColumnValue () {
	   Map<String, String> columnValue = new HashMap<String, String>();
	   columnValue.put("ID", ID); 
	   columnValue.put("CONFERENCE_MEMBER_ID", CONFERENCE_MEMBER_ID); 
	   columnValue.put("PRESENTATION_ID", PRESENTATION_ID); 
	   columnValue.put("STAR", STAR); 
	   columnValue.put("COMMENT", COMMENT); 
	   columnValue.put("EVALUATION_DATE", EVALUATION_DATE); 
	   return columnValue;
	 }	
	
	protected Map<Integer, String> getColumnWhereIndex () {
	   Map<Integer, String> columnIndex = new HashMap<Integer, String>();
	   int i = 0;
	   columnIndex.put(i++, "ID"); 
	   columnIndex.put(i++, "CONFERENCE_MEMBER_ID"); 
	   columnIndex.put(i++, "PRESENTATION_ID"); 
	   columnIndex.put(i++, "STAR"); 
	   columnIndex.put(i++, "COMMENT"); 
	   columnIndex.put(i++, "EVALUATION_DATE"); 
	   return columnIndex;
	 }
	
	protected Map<String, String> getColumnWhereValue () {
	   Map<String, String> columnValue = new HashMap<String, String>();
	   columnValue.put("ID", WHERE_id); 
	   columnValue.put("CONFERENCE_MEMBER_ID", WHERE_conference_member_id); 
	   columnValue.put("PRESENTATION_ID", WHERE_presentation_id); 
	   columnValue.put("STAR", WHERE_star); 
	   columnValue.put("COMMENT", WHERE_comment); 
	   columnValue.put("EVALUATION_DATE", WHERE_evaluation_date); 
	   return columnValue;
	}		

	protected Map<Integer, Column> getColumns() {
	   Map<Integer, Column> columnIndex = new HashMap<Integer, Column>();
	   int i = 0;
	   columnIndex.put(i++, new Column("ID", FieldType.BIGINT, true, 0)); //java.lang.Long
	   columnIndex.put(i++, new Column("CONFERENCE_MEMBER_ID", FieldType.BIGINT, true, 0)); //java.lang.Long
	   columnIndex.put(i++, new Column("PRESENTATION_ID", FieldType.BIGINT, true, 0)); //java.lang.Long
	   columnIndex.put(i++, new Column("STAR", FieldType.INTEGER, true, 0)); //java.lang.Integer
	   columnIndex.put(i++, new Column("COMMENT", FieldType.VARCHAR, false, 500)); //java.lang.String
	   columnIndex.put(i++, new Column("EVALUATION_DATE", FieldType.TIMESTAMP, false, 0)); //java.lang.Timestamp
	   return columnIndex;
	}		 
	
}
