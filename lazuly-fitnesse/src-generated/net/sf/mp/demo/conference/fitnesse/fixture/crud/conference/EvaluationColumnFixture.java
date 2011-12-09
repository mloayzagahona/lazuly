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

   public String id, WHERE_id; //id; // 
   public String conference_member_id, WHERE_conference_member_id; //conferenceMemberId; // 
   public String presentation_id, WHERE_presentation_id; //presentationId; // 
   public String star, WHERE_star; //star; // 
   public String comment, WHERE_comment; //comment; // 
   public String evaluation_date, WHERE_evaluation_date; //evaluationDate; // 

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
	   columnIndex.put(i++, "id"); 
	   columnIndex.put(i++, "conference_member_id"); 
	   columnIndex.put(i++, "presentation_id"); 
	   columnIndex.put(i++, "star"); 
	   columnIndex.put(i++, "comment"); 
	   columnIndex.put(i++, "evaluation_date"); 
	   return columnIndex;
	 }
	
	 protected Map<String, String> getColumnValue () {
	   Map<String, String> columnValue = new HashMap<String, String>();
	   columnValue.put("id", id); 
	   columnValue.put("conference_member_id", conference_member_id); 
	   columnValue.put("presentation_id", presentation_id); 
	   columnValue.put("star", star); 
	   columnValue.put("comment", comment); 
	   columnValue.put("evaluation_date", evaluation_date); 
	   return columnValue;
	 }	
	
	 protected Map<Integer, String> getColumnWhereIndex () {
	   Map<Integer, String> columnIndex = new HashMap<Integer, String>();
	   int i = 0;
	   columnIndex.put(i++, "id"); 
	   columnIndex.put(i++, "conference_member_id"); 
	   columnIndex.put(i++, "presentation_id"); 
	   columnIndex.put(i++, "star"); 
	   columnIndex.put(i++, "comment"); 
	   columnIndex.put(i++, "evaluation_date"); 
	   return columnIndex;
	 }
	
	 protected Map<String, String> getColumnWhereValue () {
	   Map<String, String> columnValue = new HashMap<String, String>();
	   columnValue.put("id", WHERE_id); 
	   columnValue.put("conference_member_id", WHERE_conference_member_id); 
	   columnValue.put("presentation_id", WHERE_presentation_id); 
	   columnValue.put("star", WHERE_star); 
	   columnValue.put("comment", WHERE_comment); 
	   columnValue.put("evaluation_date", WHERE_evaluation_date); 
	   return columnValue;
	 }		

	protected Map<Integer, Column> getColumns() {
	   Map<Integer, Column> columnIndex = new HashMap<Integer, Column>();
	   int i = 0;
	   columnIndex.put(i++, new Column("id", FieldType.BIGINT, true, 0)); //java.lang.Long
	   columnIndex.put(i++, new Column("conference_member_id", FieldType.BIGINT, true, 0)); //java.lang.Long
	   columnIndex.put(i++, new Column("presentation_id", FieldType.BIGINT, true, 0)); //java.lang.Long
	   columnIndex.put(i++, new Column("star", FieldType.INTEGER, true, 0)); //java.lang.Integer
	   columnIndex.put(i++, new Column("comment", FieldType.VARCHAR, false, 500)); //java.lang.String
	   columnIndex.put(i++, new Column("evaluation_date", FieldType.TIMESTAMP, false, 0)); //java.lang.Timestamp
	   return columnIndex;
	}		 
	
}
