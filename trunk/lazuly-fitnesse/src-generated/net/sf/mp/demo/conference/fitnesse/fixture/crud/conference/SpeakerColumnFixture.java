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


public class SpeakerColumnFixture extends DbInsertUpdateDeleteFixture{

   public String ID, WHERE_id; //id; // 
   public String CONFERENCE_MEMBER_ID, WHERE_conference_member_id; //conferenceMemberId; // 
   public String BIO, WHERE_bio; //bio; // 
   public String PHOTO, WHERE_photo; //photo; // 
   public String WEB_SITE_URL, WHERE_web_site_url; //webSiteUrl; // 
   public String SPONSOR_ID, WHERE_sponsor_id; //sponsorId; // 

	public static int NB_COLUMN = 6;
	
	protected int getNumberOfColumn() {
		return NB_COLUMN;
	}
	 
	protected String getTable() {
		return "speaker";
	}
	
	protected Map<Integer, String> getColumnIndex () {
		Map<Integer, String> columnIndex = new HashMap<Integer, String>();
		int i = 0;
	    columnIndex.put(i++, "ID"); 
	    columnIndex.put(i++, "CONFERENCE_MEMBER_ID"); 
	    columnIndex.put(i++, "BIO"); 
	    columnIndex.put(i++, "PHOTO"); 
	    columnIndex.put(i++, "WEB_SITE_URL"); 
	    columnIndex.put(i++, "SPONSOR_ID"); 
	    return columnIndex;
	}
	
	protected Map<String, String> getColumnValue () {
	   Map<String, String> columnValue = new HashMap<String, String>();
	   columnValue.put("ID", ID); 
	   columnValue.put("CONFERENCE_MEMBER_ID", CONFERENCE_MEMBER_ID); 
	   columnValue.put("BIO", BIO); 
	   columnValue.put("PHOTO", PHOTO); 
	   columnValue.put("WEB_SITE_URL", WEB_SITE_URL); 
	   columnValue.put("SPONSOR_ID", SPONSOR_ID); 
	   return columnValue;
	 }	
	
	protected Map<Integer, String> getColumnWhereIndex () {
	   Map<Integer, String> columnIndex = new HashMap<Integer, String>();
	   int i = 0;
	   columnIndex.put(i++, "ID"); 
	   columnIndex.put(i++, "CONFERENCE_MEMBER_ID"); 
	   columnIndex.put(i++, "BIO"); 
	   columnIndex.put(i++, "PHOTO"); 
	   columnIndex.put(i++, "WEB_SITE_URL"); 
	   columnIndex.put(i++, "SPONSOR_ID"); 
	   return columnIndex;
	 }
	
	protected Map<String, String> getColumnWhereValue () {
	   Map<String, String> columnValue = new HashMap<String, String>();
	   columnValue.put("ID", WHERE_id); 
	   columnValue.put("CONFERENCE_MEMBER_ID", WHERE_conference_member_id); 
	   columnValue.put("BIO", WHERE_bio); 
	   columnValue.put("PHOTO", WHERE_photo); 
	   columnValue.put("WEB_SITE_URL", WHERE_web_site_url); 
	   columnValue.put("SPONSOR_ID", WHERE_sponsor_id); 
	   return columnValue;
	}		

	protected Map<Integer, Column> getColumns() {
	   Map<Integer, Column> columnIndex = new HashMap<Integer, Column>();
	   int i = 0;
	   columnIndex.put(i++, new Column("ID", FieldType.BIGINT, true, 0)); //java.lang.Long
	   columnIndex.put(i++, new Column("CONFERENCE_MEMBER_ID", FieldType.BIGINT, true, 0)); //java.lang.Long
	   columnIndex.put(i++, new Column("BIO", FieldType.LONGVARCHAR, true, 0)); //java.lang.String
	   columnIndex.put(i++, new Column("PHOTO", FieldType.LONGVARBINARY, false, 0)); //java.lang.String
	   columnIndex.put(i++, new Column("WEB_SITE_URL", FieldType.VARCHAR, false, 255)); //java.lang.String
	   columnIndex.put(i++, new Column("SPONSOR_ID", FieldType.BIGINT, false, 0)); //java.lang.Long
	   return columnIndex;
	}		 
	
}
