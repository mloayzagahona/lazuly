package net.sf.mp.demo.conference.domain.statistics;

//MP-MANAGED-ADDED-AREA-BEGINNING @import@
//MP-MANAGED-ADDED-AREA-ENDING @import@
import java.sql.*;
import java.util.Date;
import java.util.List;
import java.util.ArrayList;
import java.util.Set;
import java.util.HashSet;

import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;
import net.sf.minuteProject.architecture.bsla.domain.AbstractDomainObject;
import java.io.Serializable;
import javax.persistence.*;
import javax.xml.bind.annotation.*;

/**
 *
 * <p>Title: StatMbPerCtryConf</p>
 *
 * <p>Description: Domain Object describing a StatMbPerCtryConf entity</p>
 *
 */
@Entity (name="StatMbPerCtryConf")
@Table (name="stat_mb_per_ctry_conf")
@NamedQueries({
	 @NamedQuery(name="StatMbPerCtryConf.findAll", query="SELECT statMbPerCtryConf FROM StatMbPerCtryConf statMbPerCtryConf")
	,@NamedQuery(name="StatMbPerCtryConf.findByCountry", query="SELECT statMbPerCtryConf FROM StatMbPerCtryConf statMbPerCtryConf WHERE statMbPerCtryConf.country = :country")
	,@NamedQuery(name="StatMbPerCtryConf.findByCountryContaining", query="SELECT statMbPerCtryConf FROM StatMbPerCtryConf statMbPerCtryConf WHERE statMbPerCtryConf.country like :country")
	,@NamedQuery(name="StatMbPerCtryConf.findByConferenceName", query="SELECT statMbPerCtryConf FROM StatMbPerCtryConf statMbPerCtryConf WHERE statMbPerCtryConf.conferenceName = :conferenceName")
	,@NamedQuery(name="StatMbPerCtryConf.findByConferenceNameContaining", query="SELECT statMbPerCtryConf FROM StatMbPerCtryConf statMbPerCtryConf WHERE statMbPerCtryConf.conferenceName like :conferenceName")
	,@NamedQuery(name="StatMbPerCtryConf.findByNumber", query="SELECT statMbPerCtryConf FROM StatMbPerCtryConf statMbPerCtryConf WHERE statMbPerCtryConf.number = :number")
})
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(namespace="net.sf.mp.demo.conference.domain.statistics", name = "StatMbPerCtryConf")
@XmlRootElement(namespace="net.sf.mp.demo.conference.domain.statistics")
public class StatMbPerCtryConf extends AbstractDomainObject {
    private static final long serialVersionUID = 1L;
	
    public  static final String FIND_ALL = "StatMbPerCtryConf.findAll";
    public static final String FIND_BY_COUNTRY = "StatMbPerCtryConf.findByCountry";
    public static final String FIND_BY_COUNTRY_CONTAINING ="StatMbPerCtryConf.findByCountryContaining";
    public static final String FIND_BY_CONFERENCENAME = "StatMbPerCtryConf.findByConferenceName";
    public static final String FIND_BY_CONFERENCENAME_CONTAINING ="StatMbPerCtryConf.findByConferenceNameContaining";
    public static final String FIND_BY_NUMBER = "StatMbPerCtryConf.findByNumber";
	
    @Id @Column(name="id" ,length=300)
    @XmlElement (name="id")
    private String id;

//MP-MANAGED-ADDED-AREA-BEGINNING @country-field-annotation@
//MP-MANAGED-ADDED-AREA-ENDING @country-field-annotation@
//MP-MANAGED-UPDATABLE-BEGINNING-DISABLE @ATTRIBUTE-country@
    @Column(name="country",  length=45, nullable=false,  unique=false)
    @XmlElement (name="country")
    private String country; 
//MP-MANAGED-UPDATABLE-ENDING

//MP-MANAGED-ADDED-AREA-BEGINNING @conference_name-field-annotation@
//MP-MANAGED-ADDED-AREA-ENDING @conference_name-field-annotation@
//MP-MANAGED-UPDATABLE-BEGINNING-DISABLE @ATTRIBUTE-conference_name@
    @Column(name="conference_name",  length=255, nullable=false,  unique=false)
    @XmlElement (name="conference-name")
    private String conferenceName; 
//MP-MANAGED-UPDATABLE-ENDING

//MP-MANAGED-ADDED-AREA-BEGINNING @number-field-annotation@
//MP-MANAGED-ADDED-AREA-ENDING @number-field-annotation@
//MP-MANAGED-UPDATABLE-BEGINNING-DISABLE @ATTRIBUTE-number@
    @Column(name="number",   nullable=false,  unique=false)
    @XmlElement (name="number")
    private Long number; 
//MP-MANAGED-UPDATABLE-ENDING

    /**
    * Default constructor
    */
    public StatMbPerCtryConf() {
    }

	/**
	* All field constructor 
	*/
    public StatMbPerCtryConf(
       String id,
       String country,
       String conferenceName,
       Long number) {
       //primary keys
       setId (id);
       //attributes
       setCountry (country);
       setConferenceName (conferenceName);
       setNumber (number);
       //parents
    }

	public StatMbPerCtryConf flat() {
	   return new StatMbPerCtryConf(
          getId(),
          getCountry(),
          getConferenceName(),
          getNumber()
	   );
	}

    /**
    * toString implementation
    */
	public String toString() {
		return toString(this);
	}

	public ToStringBuilder getToStringBuilder(Object object) {
	 	return new ToStringBuilder(this, ToStringStyle.SHORT_PREFIX_STYLE)
            .append("id", id)
            .append("country", country)
            .append("conferenceName", conferenceName)
            .append("number", number)
	 	    ;
	} 
		
	public String toString(Object object) {
	 	return getToStringBuilder(object).toString();
	} 
	
	public String toStringWithParents() {
	    ToStringBuilder toStringBuilder = getToStringBuilder(this);
	 	return toStringBuilder.toString();	
	}
	/**
    * hashCode implementation
    */
	public int hashCode() {
		return HashCodeBuilder.reflectionHashCode(flat());
	}
	
	/**
    * equals implementation

	public boolean equals(Object object) {
		return super.toEquals(flat(), object);
	}
    */
	public boolean equals(Object object) {
		if (object == null) return false;	
		if (object == this) return true;
		if (!(object instanceof StatMbPerCtryConf)) return false;
		StatMbPerCtryConf statmbperctryconf = (StatMbPerCtryConf) object;
		if (statmbperctryconf.id==null || !statmbperctryconf.id.equals(id)) return false;
		return true;
	}    

	public StatMbPerCtryConf clone() {
        StatMbPerCtryConf statmbperctryconf = flat();
        return statmbperctryconf;
	}
	
	public void copy (StatMbPerCtryConf statmbperctryconf) {
		if (statmbperctryconf!=null) {
			setId (statmbperctryconf.getId());
			setCountry (statmbperctryconf.getCountry());
			setConferenceName (statmbperctryconf.getConferenceName());
			setNumber (statmbperctryconf.getNumber());
		}
	}
	
	public static StatMbPerCtryConf fullMask() {
		return new StatMbPerCtryConf(
			stringMask__ ,
			stringMask__ ,
			stringMask__ ,
			longMask__ 
		);	    
	}

    public String getId() {
        return id;
    }
	
    public void setId (String id) {
        this.id =  id;
    }
    
//MP-MANAGED-UPDATABLE-BEGINNING-DISABLE @GETTER-SETTER-country@
    public String getCountry() {
        return country;
    }
	
    public void setCountry (String country) {
        this.country =  country;
    }    
//MP-MANAGED-UPDATABLE-ENDING

//MP-MANAGED-UPDATABLE-BEGINNING-DISABLE @GETTER-SETTER-conference_name@
    public String getConferenceName() {
        return conferenceName;
    }
	
    public void setConferenceName (String conferenceName) {
        this.conferenceName =  conferenceName;
    }    
//MP-MANAGED-UPDATABLE-ENDING

//MP-MANAGED-UPDATABLE-BEGINNING-DISABLE @GETTER-SETTER-number@
    public Long getNumber() {
        return number;
    }
	
    public void setNumber (Long number) {
        this.number =  number;
    }    
//MP-MANAGED-UPDATABLE-ENDING





//MP-MANAGED-ADDED-AREA-BEGINNING @implementation@
//MP-MANAGED-ADDED-AREA-ENDING @implementation@

}
