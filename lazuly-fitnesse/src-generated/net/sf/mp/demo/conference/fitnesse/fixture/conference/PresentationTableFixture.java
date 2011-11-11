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

public class PresentationTableFixture extends DbTableFixture{

	public Long ID; //id; // 
	public Timestamp START_TIME; //startTime; // 
	public Timestamp END_TIME; //endTime; // 
	public String ABSTRACT; //abstractName; // 
	public String TITLE; //title; // 
	public String STATUS; //status; // 
	public Long PRESENTATION_PLACE_ID; //presentationPlaceId; // 
	public Timestamp PROPOSAL_TIME; //proposalTime; // 

	public static int NB_COLUMN = 8;
	
	protected int getNumberOfColumn() {
		return NB_COLUMN;
	}
	protected String getTable() {
		return "presentation";
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
