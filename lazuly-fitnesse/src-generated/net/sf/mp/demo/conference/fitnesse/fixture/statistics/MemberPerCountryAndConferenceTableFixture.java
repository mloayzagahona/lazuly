package net.sf.mp.demo.conference.fitnesse.fixture.statistics;

import java.sql.*;
import java.util.Date;
import java.util.List;
import java.util.ArrayList;
import java.util.Set;
import java.util.HashSet;

import net.sf.minuteproject.fitnesse.fixture.DbTableFixture;
import fit.ColumnFixture;
import fitnesse.fixtures.TableFixture;

public class MemberPerCountryAndConferenceTableFixture extends DbTableFixture{

	public String ID; //id; // 
	public String COUNTRY; //country; // 
	public String CONFERENCE_NAME; //conferenceName; // 
	public Long NUMBER; //number; // 

	public static int NB_COLUMN = 4;
	
	protected int getNumberOfColumn() {
		return NB_COLUMN;
	}
	protected String getTable() {
		return "stat_mb_per_ctry_conf";
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
