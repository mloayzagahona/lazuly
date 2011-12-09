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
import net.sf.mp.demo.conference.domain.conference.Speaker;
import net.sf.mp.demo.conference.domain.conference.Address;
import net.sf.mp.demo.conference.domain.conference.Conference;

/**
 *
 * <p>Title: Sponsor</p>
 *
 * <p>Description: Domain Object describing a Sponsor entity</p>
 *
 */
@Entity (name="Sponsor")
@Table (name="sponsor")
@NamedQueries({
	 @NamedQuery(name="Sponsor.findAll", query="SELECT sponsor FROM Sponsor sponsor")
	,@NamedQuery(name="Sponsor.findByName", query="SELECT sponsor FROM Sponsor sponsor WHERE sponsor.name = :name")
	,@NamedQuery(name="Sponsor.findByNameContaining", query="SELECT sponsor FROM Sponsor sponsor WHERE sponsor.name like :name")
	,@NamedQuery(name="Sponsor.findByPrivilegeType", query="SELECT sponsor FROM Sponsor sponsor WHERE sponsor.privilegeType = :privilegeType")
	,@NamedQuery(name="Sponsor.findByPrivilegeTypeContaining", query="SELECT sponsor FROM Sponsor sponsor WHERE sponsor.privilegeType like :privilegeType")
	,@NamedQuery(name="Sponsor.findByStatus", query="SELECT sponsor FROM Sponsor sponsor WHERE sponsor.status = :status")
	,@NamedQuery(name="Sponsor.findByStatusContaining", query="SELECT sponsor FROM Sponsor sponsor WHERE sponsor.status like :status")
})
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(namespace="net.sf.mp.demo.conference.domain.conference", name = "Sponsor")
@XmlRootElement(namespace="net.sf.mp.demo.conference.domain.conference")
public class Sponsor extends AbstractDomainObject {
    private static final long serialVersionUID = 1L;
	
    public  static final String FIND_ALL = "Sponsor.findAll";
    public static final String FIND_BY_NAME = "Sponsor.findByName";
    public static final String FIND_BY_NAME_CONTAINING ="Sponsor.findByNameContaining";
    public static final String FIND_BY_PRIVILEGETYPE = "Sponsor.findByPrivilegeType";
    public static final String FIND_BY_PRIVILEGETYPE_CONTAINING ="Sponsor.findByPrivilegeTypeContaining";
    public static final String FIND_BY_STATUS = "Sponsor.findByStatus";
    public static final String FIND_BY_STATUS_CONTAINING ="Sponsor.findByStatusContaining";
	
    @Id @Column(name="id" )
    @GeneratedValue(strategy = GenerationType.AUTO)
    @XmlElement (name="id")
    private Long id;

//MP-MANAGED-ADDED-AREA-BEGINNING @name-field-annotation@
//MP-MANAGED-ADDED-AREA-ENDING @name-field-annotation@
//MP-MANAGED-UPDATABLE-BEGINNING-DISABLE @ATTRIBUTE-name@
    @Column(name="name",  length=45, nullable=false,  unique=false)
    @XmlElement (name="name")
    private String name; 
//MP-MANAGED-UPDATABLE-ENDING

//MP-MANAGED-ADDED-AREA-BEGINNING @privilege_type-field-annotation@
//MP-MANAGED-ADDED-AREA-ENDING @privilege_type-field-annotation@
//MP-MANAGED-UPDATABLE-BEGINNING-DISABLE @ATTRIBUTE-privilege_type@
    @Column(name="privilege_type",  length=45, nullable=false,  unique=false)
    @XmlElement (name="privilege-type")
    private String privilegeType; 
//MP-MANAGED-UPDATABLE-ENDING

//MP-MANAGED-ADDED-AREA-BEGINNING @status-field-annotation@
//MP-MANAGED-ADDED-AREA-ENDING @status-field-annotation@
//MP-MANAGED-UPDATABLE-BEGINNING-DISABLE @ATTRIBUTE-status@
    @Column(name="status",  length=45, nullable=false,  unique=false)
    @XmlElement (name="status")
    private String status; 
//MP-MANAGED-UPDATABLE-ENDING

    @ManyToOne (fetch=FetchType.LAZY , optional=false)
    @JoinColumn(name="address_id", referencedColumnName = "id", nullable=false,  unique=false ) 
    private Address addressId;  

    @XmlAttribute (name="address-id")
    @Column(name="address_id",  nullable=false,  unique=false, insertable=false, updatable=false)
    private Long addressId_;
	
    @ManyToOne (fetch=FetchType.LAZY , optional=false)
    @JoinColumn(name="conference_id", referencedColumnName = "id", nullable=false,  unique=false ) 
    private Conference conferenceId;  

    @XmlAttribute (name="conference-id")
    @Column(name="conference_id",  nullable=false,  unique=false, insertable=false, updatable=false)
    private Long conferenceId_;
	
//MP-MANAGED-UPDATABLE-BEGINNING-DISABLE @speakerSponsorViaSponsorId-field-sponsor@
    @XmlElement (name="speakerSponsorViaSponsorId")
    @OneToMany (targetEntity=net.sf.mp.demo.conference.domain.conference.Speaker.class, fetch=FetchType.LAZY, mappedBy="sponsorId", cascade=CascadeType.REMOVE)//, cascade=CascadeType.ALL)
    private Set <Speaker> speakerSponsorViaSponsorId = new HashSet<Speaker>(); 
   
//MP-MANAGED-UPDATABLE-ENDING
    /**
    * Default constructor
    */
    public Sponsor() {
    }

	/**
	* All field constructor 
	*/
    public Sponsor(
       Long id,
       String name,
       String privilegeType,
       String status,
       Long conferenceId,
       Long addressId) {
       //primary keys
       setId (id);
       //attributes
       setName (name);
       setPrivilegeType (privilegeType);
       setStatus (status);
       //parents
       this.addressId = new Address();
       this.addressId.setId(addressId); //Address Column [name=id; type=BIGINT] - local Sponsor Column [name=address_id; type=BIGINT]
       this.conferenceId = new Conference();
       this.conferenceId.setId(conferenceId); //Conference Column [name=id; type=BIGINT] - local Sponsor Column [name=conference_id; type=BIGINT]
    }

	public Sponsor flat() {
	   return new Sponsor(
          getId(),
          getName(),
          getPrivilegeType(),
          getStatus(),
          getConferenceId_(),
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
            .append("privilegeType", privilegeType)
            .append("status", status)
            .append("conferenceId", conferenceId)
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
        if (conferenceId!=null)
            toStringBuilder.append("conferenceId", conferenceId.toStringWithParents());
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
		if (!(object instanceof Sponsor)) return false;
		Sponsor sponsor = (Sponsor) object;
		if (sponsor.id==null || !sponsor.id.equals(id)) return false;
		return true;
	}    

	public Sponsor clone() {
        Sponsor sponsor = flat();
        if (getAddressId()!=null) 
            sponsor.setAddressId (getAddressId().clone());   
        if (getConferenceId()!=null) 
            sponsor.setConferenceId (getConferenceId().clone());   
        return sponsor;
	}
	
	public void copy (Sponsor sponsor) {
		if (sponsor!=null) {
			setId (sponsor.getId());
			setName (sponsor.getName());
			setPrivilegeType (sponsor.getPrivilegeType());
			setStatus (sponsor.getStatus());
			setConferenceId (sponsor.getConferenceId());
			setAddressId (sponsor.getAddressId());
		}
	}
	
	public static Sponsor fullMask() {
		return new Sponsor(
			longMask__ ,
			stringMask__ ,
			stringMask__ ,
			stringMask__ ,
			longMask__ ,
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

//MP-MANAGED-UPDATABLE-BEGINNING-DISABLE @GETTER-SETTER-privilege_type@
    public String getPrivilegeType() {
        return privilegeType;
    }
	
    public void setPrivilegeType (String privilegeType) {
        this.privilegeType =  privilegeType;
    }    
//MP-MANAGED-UPDATABLE-ENDING

//MP-MANAGED-UPDATABLE-BEGINNING-DISABLE @GETTER-SETTER-status@
    public String getStatus() {
        return status;
    }
	
    public void setStatus (String status) {
        this.status =  status;
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
	
    public Conference getConferenceId () {
    	return conferenceId;
    }
	
    public void setConferenceId (Conference conferenceId) {
    	this.conferenceId = conferenceId;
    }

    public Long getConferenceId_() {
        return conferenceId_;
    }
	
    public void setConferenceId_ (Long conferenceId) {
        this.conferenceId_ =  conferenceId;
    }
	

//MP-MANAGED-UPDATABLE-BEGINNING-DISABLE @speakerSponsorViaSponsorId-getter-sponsor@
    public Set<Speaker> getSpeakerSponsorViaSponsorId() {
        if (speakerSponsorViaSponsorId == null){
            speakerSponsorViaSponsorId = new HashSet<Speaker>();
        }
        return speakerSponsorViaSponsorId;
    }

    public void setSpeakerSponsorViaSponsorId (Set<Speaker> speakerSponsorViaSponsorId) {
        this.speakerSponsorViaSponsorId = speakerSponsorViaSponsorId;
    }	
    
    public void addSpeakerSponsorViaSponsorId (Speaker speaker) {
    	    getSpeakerSponsorViaSponsorId().add(speaker);
    }
    
//MP-MANAGED-UPDATABLE-ENDING


//MP-MANAGED-ADDED-AREA-BEGINNING @implementation@
//MP-MANAGED-ADDED-AREA-ENDING @implementation@

}
