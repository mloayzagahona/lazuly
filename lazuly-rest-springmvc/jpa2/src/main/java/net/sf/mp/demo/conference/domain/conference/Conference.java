package net.sf.mp.demo.conference.domain.conference;

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
import net.sf.mp.demo.conference.domain.conference.ConferenceFeedback;
import net.sf.mp.demo.conference.domain.conference.ConferenceMember;
import net.sf.mp.demo.conference.domain.conference.Sponsor;
import net.sf.mp.demo.conference.domain.conference.Address;

/**
 *
 * <p>Title: Conference</p>
 *
 * <p>Description: Domain Object describing a Conference entity</p>
 *
 */
@Entity (name="Conference")
@Table (name="conference")
@NamedQueries({
	 @NamedQuery(name="Conference.findAll", query="SELECT conference FROM Conference conference")
	,@NamedQuery(name="Conference.findByName", query="SELECT conference FROM Conference conference WHERE conference.name = :name")
	,@NamedQuery(name="Conference.findByNameContaining", query="SELECT conference FROM Conference conference WHERE conference.name like :name")
	,@NamedQuery(name="Conference.findByStartDate", query="SELECT conference FROM Conference conference WHERE conference.startDate = :startDate")
	,@NamedQuery(name="Conference.findByEndDate", query="SELECT conference FROM Conference conference WHERE conference.endDate = :endDate")
})
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(namespace="net.sf.mp.demo.conference.domain.conference", name = "Conference")
@XmlRootElement(namespace="net.sf.mp.demo.conference.domain.conference")
public class Conference extends AbstractDomainObject {
    private static final long serialVersionUID = 1L;
	
    public  static final String FIND_ALL = "Conference.findAll";
    public static final String FIND_BY_NAME = "Conference.findByName";
    public static final String FIND_BY_NAME_CONTAINING ="Conference.findByNameContaining";
    public static final String FIND_BY_STARTDATE = "Conference.findByStartDate";
    public static final String FIND_BY_ENDDATE = "Conference.findByEndDate";
	
    @Id @Column(name="id" )
    @GeneratedValue(strategy = GenerationType.AUTO)
    @XmlElement (name="id")
    private Long id;

//MP-MANAGED-ADDED-AREA-BEGINNING @name-field-annotation@
//MP-MANAGED-ADDED-AREA-ENDING @name-field-annotation@
//MP-MANAGED-UPDATABLE-BEGINNING-DISABLE @ATTRIBUTE-name@
    @Column(name="name",  length=255, nullable=false,  unique=false)
    @XmlElement (name="name")
    private String name; 
//MP-MANAGED-UPDATABLE-ENDING

//MP-MANAGED-ADDED-AREA-BEGINNING @start_date-field-annotation@
//MP-MANAGED-ADDED-AREA-ENDING @start_date-field-annotation@
//MP-MANAGED-UPDATABLE-BEGINNING-DISABLE @ATTRIBUTE-start_date@
    @Column(name="start_date",    nullable=true,  unique=false)
    @XmlElement (name="start-date")
    @Temporal(TemporalType.DATE)
    private java.util.Date startDate; 
//MP-MANAGED-UPDATABLE-ENDING

//MP-MANAGED-ADDED-AREA-BEGINNING @end_date-field-annotation@
//MP-MANAGED-ADDED-AREA-ENDING @end_date-field-annotation@
//MP-MANAGED-UPDATABLE-BEGINNING-DISABLE @ATTRIBUTE-end_date@
    @Column(name="end_date",    nullable=true,  unique=false)
    @XmlElement (name="end-date")
    @Temporal(TemporalType.DATE)
    private java.util.Date endDate; 
//MP-MANAGED-UPDATABLE-ENDING

    @ManyToOne (fetch=FetchType.LAZY , optional=false)
    @JoinColumn(name="address_id", referencedColumnName = "id", nullable=false,  unique=false ) 
    private Address addressId;  

    @XmlAttribute (name="address-id")
    @Column(name="address_id",  nullable=false,  unique=false, insertable=false, updatable=false)
    private Long addressId_;
	
//MP-MANAGED-UPDATABLE-BEGINNING-DISABLE @conferenceFeedbackConferenceViaConferenceId-field-conference@
    @XmlElement (name="conferenceFeedbackConferenceViaConferenceId")
    @OneToMany (targetEntity=net.sf.mp.demo.conference.domain.conference.ConferenceFeedback.class, fetch=FetchType.LAZY, mappedBy="conferenceId", cascade=CascadeType.REMOVE)//, cascade=CascadeType.ALL)
    private Set <ConferenceFeedback> conferenceFeedbackConferenceViaConferenceId = new HashSet<ConferenceFeedback>(); 
   
//MP-MANAGED-UPDATABLE-ENDING
//MP-MANAGED-UPDATABLE-BEGINNING-DISABLE @conferenceMemberConferenceViaConferenceId-field-conference@
    @XmlElement (name="conferenceMemberConferenceViaConferenceId")
    @OneToMany (targetEntity=net.sf.mp.demo.conference.domain.conference.ConferenceMember.class, fetch=FetchType.LAZY, mappedBy="conferenceId", cascade=CascadeType.REMOVE)//, cascade=CascadeType.ALL)
    private Set <ConferenceMember> conferenceMemberConferenceViaConferenceId = new HashSet<ConferenceMember>(); 
   
//MP-MANAGED-UPDATABLE-ENDING
//MP-MANAGED-UPDATABLE-BEGINNING-DISABLE @sponsorConferenceViaConferenceId-field-conference@
    @XmlElement (name="sponsorConferenceViaConferenceId")
    @OneToMany (targetEntity=net.sf.mp.demo.conference.domain.conference.Sponsor.class, fetch=FetchType.LAZY, mappedBy="conferenceId", cascade=CascadeType.REMOVE)//, cascade=CascadeType.ALL)
    private Set <Sponsor> sponsorConferenceViaConferenceId = new HashSet<Sponsor>(); 
   
//MP-MANAGED-UPDATABLE-ENDING
    /**
    * Default constructor
    */
    public Conference() {
    }

	/**
	* All field constructor 
	*/
    public Conference(
       Long id,
       String name,
       java.util.Date startDate,
       java.util.Date endDate,
       Long addressId) {
       //primary keys
       setId (id);
       //attributes
       setName (name);
       setStartDate (startDate);
       setEndDate (endDate);
       //parents
       this.addressId = new Address();
       this.addressId.setId(addressId); //Address Column [name=id; type=BIGINT] - local Conference Column [name=address_id; type=BIGINT]
    }

	public Conference flat() {
	   return new Conference(
          getId(),
          getName(),
          getStartDate(),
          getEndDate(),
          getAddressId_()
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
            .append("name", name)
            .append("startDate", startDate)
            .append("endDate", endDate)
            .append("addressId", addressId)
	 	    ;
	} 
		
	public String toString(Object object) {
	 	return getToStringBuilder(object).toString();
	} 
	
	public String toStringWithParents() {
	    ToStringBuilder toStringBuilder = getToStringBuilder(this);
        if (addressId!=null)
            toStringBuilder.append("addressId", addressId.toStringWithParents());
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
		if (!(object instanceof Conference)) return false;
		Conference conference = (Conference) object;
		if (conference.id==null || !conference.id.equals(id)) return false;
		return true;
	}    

	public Conference clone() {
        Conference conference = flat();
        if (getAddressId()!=null) 
            conference.setAddressId (getAddressId().clone());   
        return conference;
	}
	
	public void copy (Conference conference) {
		if (conference!=null) {
			setId (conference.getId());
			setName (conference.getName());
			setStartDate (conference.getStartDate());
			setEndDate (conference.getEndDate());
			setAddressId (conference.getAddressId());
		}
	}
	
	public static Conference fullMask() {
		return new Conference(
			longMask__ ,
			stringMask__ ,
			new Date() ,
			new Date() ,
			longMask__ 
		);	    
	}

    public Long getId() {
        return id;
    }
	
    public void setId (Long id) {
        this.id =  id;
    }
    
//MP-MANAGED-UPDATABLE-BEGINNING-DISABLE @GETTER-SETTER-name@
    public String getName() {
        return name;
    }
	
    public void setName (String name) {
        this.name =  name;
    }    
//MP-MANAGED-UPDATABLE-ENDING

//MP-MANAGED-UPDATABLE-BEGINNING-DISABLE @GETTER-SETTER-start_date@
    public java.util.Date getStartDate() {
        return startDate;
    }
	
    public void setStartDate (java.util.Date startDate) {
        this.startDate =  startDate;
    }    
//MP-MANAGED-UPDATABLE-ENDING

//MP-MANAGED-UPDATABLE-BEGINNING-DISABLE @GETTER-SETTER-end_date@
    public java.util.Date getEndDate() {
        return endDate;
    }
	
    public void setEndDate (java.util.Date endDate) {
        this.endDate =  endDate;
    }    
//MP-MANAGED-UPDATABLE-ENDING


    public Address getAddressId () {
    	return addressId;
    }
	
    public void setAddressId (Address addressId) {
    	this.addressId = addressId;
    }

    public Long getAddressId_() {
        return addressId_;
    }
	
    public void setAddressId_ (Long addressId) {
        this.addressId_ =  addressId;
    }
	

//MP-MANAGED-UPDATABLE-BEGINNING-DISABLE @conferenceFeedbackConferenceViaConferenceId-getter-conference@
    public Set<ConferenceFeedback> getConferenceFeedbackConferenceViaConferenceId() {
        if (conferenceFeedbackConferenceViaConferenceId == null){
            conferenceFeedbackConferenceViaConferenceId = new HashSet<ConferenceFeedback>();
        }
        return conferenceFeedbackConferenceViaConferenceId;
    }

    public void setConferenceFeedbackConferenceViaConferenceId (Set<ConferenceFeedback> conferenceFeedbackConferenceViaConferenceId) {
        this.conferenceFeedbackConferenceViaConferenceId = conferenceFeedbackConferenceViaConferenceId;
    }	
    
    public void addConferenceFeedbackConferenceViaConferenceId (ConferenceFeedback conferenceFeedback) {
    	    getConferenceFeedbackConferenceViaConferenceId().add(conferenceFeedback);
    }
    
//MP-MANAGED-UPDATABLE-ENDING
//MP-MANAGED-UPDATABLE-BEGINNING-DISABLE @conferenceMemberConferenceViaConferenceId-getter-conference@
    public Set<ConferenceMember> getConferenceMemberConferenceViaConferenceId() {
        if (conferenceMemberConferenceViaConferenceId == null){
            conferenceMemberConferenceViaConferenceId = new HashSet<ConferenceMember>();
        }
        return conferenceMemberConferenceViaConferenceId;
    }

    public void setConferenceMemberConferenceViaConferenceId (Set<ConferenceMember> conferenceMemberConferenceViaConferenceId) {
        this.conferenceMemberConferenceViaConferenceId = conferenceMemberConferenceViaConferenceId;
    }	
    
    public void addConferenceMemberConferenceViaConferenceId (ConferenceMember conferenceMember) {
    	    getConferenceMemberConferenceViaConferenceId().add(conferenceMember);
    }
    
//MP-MANAGED-UPDATABLE-ENDING
//MP-MANAGED-UPDATABLE-BEGINNING-DISABLE @sponsorConferenceViaConferenceId-getter-conference@
    public Set<Sponsor> getSponsorConferenceViaConferenceId() {
        if (sponsorConferenceViaConferenceId == null){
            sponsorConferenceViaConferenceId = new HashSet<Sponsor>();
        }
        return sponsorConferenceViaConferenceId;
    }

    public void setSponsorConferenceViaConferenceId (Set<Sponsor> sponsorConferenceViaConferenceId) {
        this.sponsorConferenceViaConferenceId = sponsorConferenceViaConferenceId;
    }	
    
    public void addSponsorConferenceViaConferenceId (Sponsor sponsor) {
    	    getSponsorConferenceViaConferenceId().add(sponsor);
    }
    
//MP-MANAGED-UPDATABLE-ENDING


//MP-MANAGED-ADDED-AREA-BEGINNING @implementation@
//MP-MANAGED-ADDED-AREA-ENDING @implementation@

}
