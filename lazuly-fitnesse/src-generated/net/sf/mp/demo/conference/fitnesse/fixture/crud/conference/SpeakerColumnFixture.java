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

   public String id, WHERE_id; //id; // 
   public String conference_member_id, WHERE_conference_member_id; //conferenceMemberId; // 
   public String bio, WHERE_bio; //bio; // 
   public String photo, WHERE_photo; //photo; // 
   public String web_site_url, WHERE_web_site_url; //webSiteUrl; // 
   public String sponsor_id, WHERE_sponsor_id; //sponsorId; // 

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
	   columnIndex.put(i++, "id"); 
	   columnIndex.put(i++, "conference_member_id"); 
	   columnIndex.put(i++, "bio"); 
	   columnIndex.put(i++, "photo"); 
	   columnIndex.put(i++, "web_site_url"); 
	   columnIndex.put(i++, "sponsor_id"); 
	   return columnIndex;
	 }
	
	 protected Map<String, String> getColumnValue () {
	   Map<String, String> columnValue = new HashMap<String, String>();
	   columnValue.put("id", id); 
	   columnValue.put("conference_member_id", conference_member_id); 
	   columnValue.put("bio", bio); 
	   columnValue.put("photo", photo); 
	   columnValue.put("web_site_url", web_site_url); 
	   columnValue.put("sponsor_id", sponsor_id); 
	   return columnValue;
	 }	
	
	 protected Map<Integer, String> getColumnWhereIndex () {
	   Map<Integer, String> columnIndex = new HashMap<Integer, String>();
	   int i = 0;
	   columnIndex.put(i++, "id"); 
	   columnIndex.put(i++, "conference_member_id"); 
	   columnIndex.put(i++, "bio"); 
	   columnIndex.put(i++, "photo"); 
	   columnIndex.put(i++, "web_site_url"); 
	   columnIndex.put(i++, "sponsor_id"); 
	   return columnIndex;
	 }
	
	 protected Map<String, String> getColumnWhereValue () {
	   Map<String, String> columnValue = new HashMap<String, String>();
	   columnValue.put("id", WHERE_id); 
	   columnValue.put("conference_member_id", WHERE_conference_member_id); 
	   columnValue.put("bio", WHERE_bio); 
	   columnValue.put("photo", WHERE_photo); 
	   columnValue.put("web_site_url", WHERE_web_site_url); 
	   columnValue.put("sponsor_id", WHERE_sponsor_id); 
	   return columnValue;
	 }		

	protected Map<Integer, Column> getColumns() {
	   Map<Integer, Column> columnIndex = new HashMap<Integer, Column>();
	   int i = 0;
	   columnIndex.put(i++, new Column("id", FieldType.BIGINT, true, 0)); //java.lang.Long
	   columnIndex.put(i++, new Column("conference_member_id", FieldType.BIGINT, true, 0)); //java.lang.Long
	   columnIndex.put(i++, new Column("bio", FieldType.LONGVARCHAR, true, 0)); //java.lang.String
	   columnIndex.put(i++, new Column("photo", FieldType.LONGVARBINARY, false, 0)); //java.lang.String
	   columnIndex.put(i++, new Column("web_site_url", FieldType.VARCHAR, false, 255)); //java.lang.String
	   columnIndex.put(i++, new Column("sponsor_id", FieldType.BIGINT, false, 0)); //java.lang.Long
	   return columnIndex;
	}		 
	
}
