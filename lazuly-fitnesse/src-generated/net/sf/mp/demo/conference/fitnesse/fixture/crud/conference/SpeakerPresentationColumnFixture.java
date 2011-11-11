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


public class SpeakerPresentationColumnFixture extends DbInsertUpdateDeleteFixture{

   public String SPEAKER_ID, WHERE_speaker_id; //speakerId; // 
   public String PRESENTATION_ID, WHERE_presentation_id; //presentationId; // 

	public static int NB_COLUMN = 2;
	
	protected int getNumberOfColumn() {
		return NB_COLUMN;
	}
	 
	protected String getTable() {
		return "speaker_presentation";
	}
	
	protected Map<Integer, String> getColumnIndex () {
		Map<Integer, String> columnIndex = new HashMap<Integer, String>();
		int i = 0;
	    columnIndex.put(i++, "SPEAKER_ID"); 
	    columnIndex.put(i++, "PRESENTATION_ID"); 
	    return columnIndex;
	}
	
	protected Map<String, String> getColumnValue () {
	   Map<String, String> columnValue = new HashMap<String, String>();
	   columnValue.put("SPEAKER_ID", SPEAKER_ID); 
	   columnValue.put("PRESENTATION_ID", PRESENTATION_ID); 
	   return columnValue;
	 }	
	
	protected Map<Integer, String> getColumnWhereIndex () {
	   Map<Integer, String> columnIndex = new HashMap<Integer, String>();
	   int i = 0;
	   columnIndex.put(i++, "SPEAKER_ID"); 
	   columnIndex.put(i++, "PRESENTATION_ID"); 
	   return columnIndex;
	 }
	
	protected Map<String, String> getColumnWhereValue () {
	   Map<String, String> columnValue = new HashMap<String, String>();
	   columnValue.put("SPEAKER_ID", WHERE_speaker_id); 
	   columnValue.put("PRESENTATION_ID", WHERE_presentation_id); 
	   return columnValue;
	}		

	protected Map<Integer, Column> getColumns() {
	   Map<Integer, Column> columnIndex = new HashMap<Integer, Column>();
	   int i = 0;
	   columnIndex.put(i++, new Column("SPEAKER_ID", FieldType.BIGINT, true, 0)); //java.lang.Long
	   columnIndex.put(i++, new Column("PRESENTATION_ID", FieldType.BIGINT, true, 0)); //java.lang.Long
	   return columnIndex;
	}		 
	
}
