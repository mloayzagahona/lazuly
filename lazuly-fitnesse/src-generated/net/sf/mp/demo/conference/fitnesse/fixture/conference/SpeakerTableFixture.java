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

public class SpeakerTableFixture extends DbTableFixture{

	public Long ID; //id; // 
	public Long CONFERENCE_MEMBER_ID; //conferenceMemberId; // 
	public String BIO; //bio; // 
	public String PHOTO; //photo; // 
	public String WEB_SITE_URL; //webSiteUrl; // 
	public Long SPONSOR_ID; //sponsorId; // 

	public static int NB_COLUMN = 6;
	
	protected int getNumberOfColumn() {
		return NB_COLUMN;
	}
	protected String getTable() {
		return "speaker";
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
