package net.sf.mp.demo.conference.fitnesse.fixture.conference;

import java.sql.*;
import java.util.Date;
import java.util.List;
import java.util.ArrayList;
import java.util.Set;
import java.util.HashSet;

import net.sf.minuteproject.fitnesse.fixture.DbTableFixture;
import fit.ColumnFixture;
import fitnesse.fixtures.TableFixture;

public class ConferenceMemberTableFixture extends DbTableFixture{

	public Long ID; //id; // 
	public Long CONFERENCE_ID; //conferenceId; // 
	public String FIRST_NAME; //firstName; // 
	public String LAST_NAME; //lastName; // 
	public String EMAIL; //email; // 
	public Long ADDRESS_ID; //addressId; // 
	public String STATUS; //status; // 

	public static int NB_COLUMN = 7;
	
	protected int getNumberOfColumn() {
		return NB_COLUMN;
	}
	protected String getTable() {
		return "conference_member";
	}
	
	@Override
	protected void doStaticTable(int arg0) {
		try {
			resultSet = executeQuery(getColumnIndex(), getColumnExpressionValue(), getColumnValue(),getColumnOrderValue() );
		} catch (SQLException e) {
			e.printStackTrace();
		}
		checkCount(resultSet.length+"");
		checkResultSet();
	}

	
	private void convertSnippet() {
		convertGeneralValue();
		convertFieldValue();
	}
	
	private void convertGeneralValue() {
//		int rowGeneralValueIndex =  getGeneralValueRowIndex();
	}
	
	private void convertFieldValue() {

	}
}
