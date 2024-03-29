package net.sf.mp.demo.conference.dao.face;

import java.io.InputStream;
import java.io.OutputStream;
import java.io.Reader;
import java.io.Writer;
import java.math.BigDecimal;
import java.sql.Clob;
import java.sql.Blob;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.sql.DataSource;

import org.apache.commons.lang.StringUtils;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.simple.SimpleJdbcTemplate;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractTransactionalJUnit4SpringContextTests;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.transaction.annotation.Transactional;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={
     "classpath:net/sf/mp/demo/conference/factory/spring/spring-config-Conference-BE-main.xml"
})
@TransactionConfiguration(transactionManager = "conferenceTransactionManager") 
@Transactional
public class AdapterConferenceTestDao extends AbstractTransactionalJUnit4SpringContextTests { 

	@Override
	@Autowired
	public void setDataSource(@Qualifier(value = "conferenceDataSource") DataSource dataSource) {
	   this.simpleJdbcTemplate = new SimpleJdbcTemplate(dataSource);
	}
        
    protected String getString1 (int length) {
        return StringUtils.substring ("This is a test string",0,length);
    }

    protected Integer getInteger1 () {
        return new Integer (100);
    }

    protected Long getLong (long l) {
        return new Long (l);
     }
         
    protected Long getLong1 () {
        return getLong (100);
    }

    protected Boolean getBoolean1 () {
        return new Boolean (false);
    }

    protected Long getDecimal1() {
        return getLong1();
    }

    protected BigDecimal getBigDecimal1() {
        return BigDecimal.valueOf(getLong1());
    }
    
    protected String getString2 (int length) {
        return StringUtils.substring ("that is a second test string",0,length);
    }

    protected Integer getInteger2 () {
        return new Integer (200);
    }
    
    protected Long getLong2 () {
       return getLong (200);
    }

    protected Boolean getBoolean2 () {
       return new Boolean (true);
    }

    protected Long getDecimal2() {
        return getLong2();
    }

    protected BigDecimal getBigDecimal2() {
        return BigDecimal.valueOf(getLong2());
    }
    
    protected Date getDate () {
       return new Date();
    }

    protected Timestamp getTimestamp () {
       return new Timestamp(getDate().getTime());
    }
        
    protected String getBlobString(int size) {
       return new String (new String[size].toString());
    }
    
    protected String getClobString(int size) {
       return new String (new String[size].toString());    
    }
    
    protected Long convertStringToLong (String s) {
       return Long.valueOf(s);
    }
    
    protected Long convertIntegerToLong (Integer i) {
       return convertStringToLong(i+"");
    }

    protected BigDecimal convertIntegerToBigDecimal (Integer i) {
       return BigDecimal.valueOf(convertIntegerToLong(i));
    }
   
    protected String convertBigDecimalToString (BigDecimal i) {
       return i+"";
    }

    protected Long convertBigDecimalToLong (BigDecimal i) {
       return Long.valueOf(convertBigDecimalToString(i));
    }
         
    protected Integer convertLongToInteger (Long l) {
    	return Integer.valueOf(l+"");
    }
                      
    protected Clob getClob(int size) {
    	Clob c = new Clob() {
			
			public void truncate(long len) throws SQLException {
				// TODO Auto-generated method stub
				
			}
			
			public int setString(long pos, String str, int offset, int len)
					throws SQLException {
				// TODO Auto-generated method stub
				return 0;
			}
			
			public int setString(long pos, String str) throws SQLException {
				// TODO Auto-generated method stub
				return 0;
			}
			
			public Writer setCharacterStream(long pos) throws SQLException {
				// TODO Auto-generated method stub
				return null;
			}
			
			public OutputStream setAsciiStream(long pos) throws SQLException {
				// TODO Auto-generated method stub
				return null;
			}
			
			public long position(Clob searchstr, long start) throws SQLException {
				// TODO Auto-generated method stub
				return 0;
			}
			
			public long position(String searchstr, long start) throws SQLException {
				// TODO Auto-generated method stub
				return 0;
			}
			
			public long length() throws SQLException {
				// TODO Auto-generated method stub
				return 0;
			}
			
			public String getSubString(long pos, int length) throws SQLException {
				// TODO Auto-generated method stub
				return null;
			}
			
			public Reader getCharacterStream(long pos, long length) throws SQLException {
				// TODO Auto-generated method stub
				return null;
			}
			
			public Reader getCharacterStream() throws SQLException {
				// TODO Auto-generated method stub
				return null;
			}
			
			public InputStream getAsciiStream() throws SQLException {
				// TODO Auto-generated method stub
				return null;
			}
			
			public void free() throws SQLException {
				// TODO Auto-generated method stub
				
			}
		};
        return c;    
     }
     
    protected Blob getBlob(int size) {
    	Blob b = new Blob() {
			
			byte[] bytes = null;

			public java.io.InputStream getBinaryStream()
			   throws java.sql.SQLException
			{
			   return new java.io.ByteArrayInputStream(bytes);
			}

			public long length() throws SQLException {
				// TODO Auto-generated method stub
				return 0;
			}

			public byte[] getBytes(long pos, int length) throws SQLException {
				// TODO Auto-generated method stub
				return null;
			}

			public long position(byte[] pattern, long start)
					throws SQLException {
				// TODO Auto-generated method stub
				return 0;
			}

			public long position(Blob pattern, long start) throws SQLException {
				// TODO Auto-generated method stub
				return 0;
			}

			public int setBytes(long pos, byte[] bytes) throws SQLException {
				// TODO Auto-generated method stub
				return 0;
			}

			public int setBytes(long pos, byte[] bytes, int offset, int len)
					throws SQLException {
				// TODO Auto-generated method stub
				return 0;
			}

			public OutputStream setBinaryStream(long pos) throws SQLException {
				// TODO Auto-generated method stub
				return null;
			}

			public void truncate(long len) throws SQLException {
				// TODO Auto-generated method stub
				
			}

			public void free() throws SQLException {
				// TODO Auto-generated method stub
				
			}

			public InputStream getBinaryStream(long pos, long length)
					throws SQLException {
				// TODO Auto-generated method stub
				return null;
			}
    			

		};
        return b;    
     }    

     
}