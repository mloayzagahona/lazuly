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


public class MemberRoleColumnFixture extends DbInsertUpdateDeleteFixture{

   public String CONFERENCE_MEMBER_ID, WHERE_conference_member_id; //conferenceMemberId; // 
   public String ROLE_ID, WHERE_role_id; //roleId; // 

	public static int NB_COLUMN = 2;
	
	protected int getNumberOfColumn() {
		return NB_COLUMN;
	}
	 
	protected String getTable() {
		return "member_role";
	}
	
	protected Map<Integer, String> getColumnIndex () {
		Map<Integer, String> columnIndex = new HashMap<Integer, String>();
		int i = 0;
	    columnIndex.put(i++, "CONFERENCE_MEMBER_ID"); 
	    columnIndex.put(i++, "ROLE_ID"); 
	    return columnIndex;
	}
	
	protected Map<String, String> getColumnValue () {
	   Map<String, String> columnValue = new HashMap<String, String>();
	   columnValue.put("CONFERENCE_MEMBER_ID", CONFERENCE_MEMBER_ID); 
	   columnValue.put("ROLE_ID", ROLE_ID); 
	   return columnValue;
	 }	
	
	protected Map<Integer, String> getColumnWhereIndex () {
	   Map<Integer, String> columnIndex = new HashMap<Integer, String>();
	   int i = 0;
	   columnIndex.put(i++, "CONFERENCE_MEMBER_ID"); 
	   columnIndex.put(i++, "ROLE_ID"); 
	   return columnIndex;
	 }
	
	protected Map<String, String> getColumnWhereValue () {
	   Map<String, String> columnValue = new HashMap<String, String>();
	   columnValue.put("CONFERENCE_MEMBER_ID", WHERE_conference_member_id); 
	   columnValue.put("ROLE_ID", WHERE_role_id); 
	   return columnValue;
	}		

	protected Map<Integer, Column> getColumns() {
	   Map<Integer, Column> columnIndex = new HashMap<Integer, Column>();
	   int i = 0;
	   columnIndex.put(i++, new Column("CONFERENCE_MEMBER_ID", FieldType.BIGINT, true, 0)); //java.lang.Long
	   columnIndex.put(i++, new Column("ROLE_ID", FieldType.INTEGER, true, 0)); //java.lang.Integer
	   return columnIndex;
	}		 
	
}
