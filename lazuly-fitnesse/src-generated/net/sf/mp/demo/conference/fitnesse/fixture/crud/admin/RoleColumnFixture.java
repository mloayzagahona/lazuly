package net.sf.mp.demo.conference.fitnesse.fixture.crud.admin;

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


public class RoleColumnFixture extends DbInsertUpdateDeleteFixture{

   public String id, WHERE_id; //id; // 
   public String name, WHERE_name; //name; // 

	 public static int NB_COLUMN = 2;
	
	 protected int getNumberOfColumn() {
		  return NB_COLUMN;
	 }
	 
	 protected String getTable() {
		  return "role";
	 }
	
	 protected Map<Integer, String> getColumnIndex () {
	   Map<Integer, String> columnIndex = new HashMap<Integer, String>();
	   int i = 0;
	   columnIndex.put(i++, "id"); 
	   columnIndex.put(i++, "name"); 
	   return columnIndex;
	 }
	
	 protected Map<String, String> getColumnValue () {
	   Map<String, String> columnValue = new HashMap<String, String>();
	   columnValue.put("id", id); 
	   columnValue.put("name", name); 
	   return columnValue;
	 }	
	
	 protected Map<Integer, String> getColumnWhereIndex () {
	   Map<Integer, String> columnIndex = new HashMap<Integer, String>();
	   int i = 0;
	   columnIndex.put(i++, "id"); 
	   columnIndex.put(i++, "name"); 
	   return columnIndex;
	 }
	
	 protected Map<String, String> getColumnWhereValue () {
	   Map<String, String> columnValue = new HashMap<String, String>();
	   columnValue.put("id", WHERE_id); 
	   columnValue.put("name", WHERE_name); 
	   return columnValue;
	 }		

	protected Map<Integer, Column> getColumns() {
	   Map<Integer, Column> columnIndex = new HashMap<Integer, Column>();
	   int i = 0;
	   columnIndex.put(i++, new Column("id", FieldType.INTEGER, true, 0)); //java.lang.Integer
	   columnIndex.put(i++, new Column("name", FieldType.VARCHAR, true, 45)); //java.lang.String
	   return columnIndex;
	}		 
	
}
