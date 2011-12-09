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
import net.sf.mp.demo.conference.domain.conference.Conference;
import net.sf.mp.demo.conference.domain.conference.ConferenceMember;
import net.sf.mp.demo.conference.domain.conference.Sponsor;
import net.sf.mp.demo.conference.domain.admin.Country;

/**
 *
 * <p>Title: Address</p>
 *
 * <p>Description: Domain Object describing a Address entity</p>
 *
 */
@Entity (name="Address")
@Table (name="address")
@NamedQueries({
	 @NamedQuery(name="Address.findAll", query="SELECT address FROM Address address")
	,@NamedQuery(name="Address.findByStreet1", query="SELECT address FROM Address address WHERE address.street1 = :street1")
	,@NamedQuery(name="Address.findByStreet1Containing", query="SELECT address FROM Address address WHERE address.street1 like :street1")
	,@NamedQuery(name="Address.findByStreet2", query="SELECT address FROM Address address WHERE address.street2 = :street2")
	,@NamedQuery(name="Address.findByStreet2Containing", query="SELECT address FROM Address address WHERE address.street2 like :street2")
})
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(namespace="net.sf.mp.demo.conference.domain.conference", name = "Address")
@XmlRootElement(namespace="net.sf.mp.demo.conference.domain.conference")
public class Address extends AbstractDomainObject {
    private static final long serialVersionUID = 1L;
	
    public  static final String FIND_ALL = "Address.findAll";
    public static final String FIND_BY_STREET1 = "Address.findByStreet1";
    public static final String FIND_BY_STREET1_CONTAINING ="Address.findByStreet1Containing";
    public static final String FIND_BY_STREET2 = "Address.findByStreet2";
    public static final String FIND_BY_STREET2_CONTAINING ="Address.findByStreet2Containing";
	
    @Id @Column(name="id" )
    @GeneratedValue(strategy = GenerationType.AUTO)
    @XmlElement (name="id")
    private Long id;

//MP-MANAGED-ADDED-AREA-BEGINNING @street1-field-annotation@
//MP-MANAGED-ADDED-AREA-ENDING @street1-field-annotation@
//MP-MANAGED-UPDATABLE-BEGINNING-DISABLE @ATTRIBUTE-street1@
    @Column(name="street1",  length=255,  nullable=true,  unique=false)
    @XmlElement (name="street1")
    private String street1; 
//MP-MANAGED-UPDATABLE-ENDING

//MP-MANAGED-ADDED-AREA-BEGINNING @street2-field-annotation@
//MP-MANAGED-ADDED-AREA-ENDING @street2-field-annotation@
//MP-MANAGED-UPDATABLE-BEGINNING-DISABLE @ATTRIBUTE-street2@
    @Column(name="street2",  length=255,  nullable=true,  unique=false)
    @XmlElement (name="street2")
    private String street2; 
//MP-MANAGED-UPDATABLE-ENDING

    @ManyToOne (fetch=FetchType.LAZY , optional=false)
    @JoinColumn(name="country_id", referencedColumnName = "id", nullable=false,  unique=false ) 
    private Country countryId;  

    @XmlAttribute (name="country-id")
    @Column(name="country_id",  nullable=false,  unique=false, insertable=false, updatable=false)
    private Integer countryId_;
	
//MP-MANAGED-UPDATABLE-BEGINNING-DISABLE @conferenceAddressViaAddressId-field-address@
    @XmlElement (name="conferenceAddressViaAddressId")
    @OneToMany (targetEntity=net.sf.mp.demo.conference.domain.conference.Conference.class, fetch=FetchType.LAZY, mappedBy="addressId", cascade=CascadeType.REMOVE)//, cascade=CascadeType.ALL)
    private Set <Conference> conferenceAddressViaAddressId = new HashSet<Conference>(); 
   
//MP-MANAGED-UPDATABLE-ENDING
//MP-MANAGED-UPDATABLE-BEGINNING-DISABLE @conferenceMemberAddressViaAddressId-field-address@
    @XmlElement (name="conferenceMemberAddressViaAddressId")
    @OneToMany (targetEntity=net.sf.mp.demo.conference.domain.conference.ConferenceMember.class, fetch=FetchType.LAZY, mappedBy="addressId", cascade=CascadeType.REMOVE)//, cascade=CascadeType.ALL)
    private Set <ConferenceMember> conferenceMemberAddressViaAddressId = new HashSet<ConferenceMember>(); 
   
//MP-MANAGED-UPDATABLE-ENDING
//MP-MANAGED-UPDATABLE-BEGINNING-DISABLE @sponsorAddressViaAddressId-field-address@
    @XmlElement (name="sponsorAddressViaAddressId")
    @OneToMany (targetEntity=net.sf.mp.demo.conference.domain.conference.Sponsor.class, fetch=FetchType.LAZY, mappedBy="addressId", cascade=CascadeType.REMOVE)//, cascade=CascadeType.ALL)
    private Set <Sponsor> sponsorAddressViaAddressId = new HashSet<Sponsor>(); 
   
//MP-MANAGED-UPDATABLE-ENDING
    /**
    * Default constructor
    */
    public Address() {
    }

	/**
	* All field constructor 
	*/
    public Address(
       Long id,
       String street1,
       String street2,
       Integer countryId) {
       //primary keys
       setId (id);
       //attributes
       setStreet1 (street1);
       setStreet2 (street2);
       //parents
       this.countryId = new Country();
       this.countryId.setId(countryId); //Country Column [name=id; type=INTEGER] - local Address Column [name=country_id; type=INTEGER]
    }

	public Address flat() {
	   return new Address(
          getId(),
          getStreet1(),
          getStreet2(),
          getCountryId_()
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
            .append("street1", street1)
            .append("street2", street2)
            .append("countryId", countryId)
	 	    ;
	} 
		
	public String toString(Object object) {
	 	return getToStringBuilder(object).toString();
	} 
	
	public String toStringWithParents() {
	    ToStringBuilder toStringBuilder = getToStringBuilder(this);
        if (countryId!=null)
            toStringBuilder.append("countryId", countryId.toStringWithParents());
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
		if (!(object instanceof Address)) return false;
		Address address = (Address) object;
		if (address.id==null || !address.id.equals(id)) return false;
		return true;
	}    

	public Address clone() {
        Address address = flat();
        if (getCountryId()!=null) 
            address.setCountryId (getCountryId().clone());   
        return address;
	}
	
	public void copy (Address address) {
		if (address!=null) {
			setId (address.getId());
			setStreet1 (address.getStreet1());
			setStreet2 (address.getStreet2());
			setCountryId (address.getCountryId());
		}
	}
	
	public static Address fullMask() {
		return new Address(
			longMask__ ,
			stringMask__ ,
			stringMask__ ,
			integerMask__ 
		);	    
	}

    public Long getId() {
        return id;
    }
	
    public void setId (Long id) {
        this.id =  id;
    }
    
//MP-MANAGED-UPDATABLE-BEGINNING-DISABLE @GETTER-SETTER-street1@
    public String getStreet1() {
        return street1;
    }
	
    public void setStreet1 (String street1) {
        this.street1 =  street1;
    }    
//MP-MANAGED-UPDATABLE-ENDING

//MP-MANAGED-UPDATABLE-BEGINNING-DISABLE @GETTER-SETTER-street2@
    public String getStreet2() {
        return street2;
    }
	
    public void setStreet2 (String street2) {
        this.street2 =  street2;
    }    
//MP-MANAGED-UPDATABLE-ENDING


    public Country getCountryId () {
    	return countryId;
    }
	
    public void setCountryId (Country countryId) {
    	this.countryId = countryId;
    }

    public Integer getCountryId_() {
        return countryId_;
    }
	
    public void setCountryId_ (Integer countryId) {
        this.countryId_ =  countryId;
    }
	

//MP-MANAGED-UPDATABLE-BEGINNING-DISABLE @conferenceAddressViaAddressId-getter-address@
    public Set<Conference> getConferenceAddressViaAddressId() {
        if (conferenceAddressViaAddressId == null){
            conferenceAddressViaAddressId = new HashSet<Conference>();
        }
        return conferenceAddressViaAddressId;
    }

    public void setConferenceAddressViaAddressId (Set<Conference> conferenceAddressViaAddressId) {
        this.conferenceAddressViaAddressId = conferenceAddressViaAddressId;
    }	
    
    public void addConferenceAddressViaAddressId (Conference conference) {
    	    getConferenceAddressViaAddressId().add(conference);
    }
    
//MP-MANAGED-UPDATABLE-ENDING
//MP-MANAGED-UPDATABLE-BEGINNING-DISABLE @conferenceMemberAddressViaAddressId-getter-address@
    public Set<ConferenceMember> getConferenceMemberAddressViaAddressId() {
        if (conferenceMemberAddressViaAddressId == null){
            conferenceMemberAddressViaAddressId = new HashSet<ConferenceMember>();
        }
        return conferenceMemberAddressViaAddressId;
    }

    public void setConferenceMemberAddressViaAddressId (Set<ConferenceMember> conferenceMemberAddressViaAddressId) {
        this.conferenceMemberAddressViaAddressId = conferenceMemberAddressViaAddressId;
    }	
    
    public void addConferenceMemberAddressViaAddressId (ConferenceMember conferenceMember) {
    	    getConferenceMemberAddressViaAddressId().add(conferenceMember);
    }
    
//MP-MANAGED-UPDATABLE-ENDING
//MP-MANAGED-UPDATABLE-BEGINNING-DISABLE @sponsorAddressViaAddressId-getter-address@
    public Set<Sponsor> getSponsorAddressViaAddressId() {
        if (sponsorAddressViaAddressId == null){
            sponsorAddressViaAddressId = new HashSet<Sponsor>();
        }
        return sponsorAddressViaAddressId;
    }

    public void setSponsorAddressViaAddressId (Set<Sponsor> sponsorAddressViaAddressId) {
        this.sponsorAddressViaAddressId = sponsorAddressViaAddressId;
    }	
    
    public void addSponsorAddressViaAddressId (Sponsor sponsor) {
    	    getSponsorAddressViaAddressId().add(sponsor);
    }
    
//MP-MANAGED-UPDATABLE-ENDING


//MP-MANAGED-ADDED-AREA-BEGINNING @implementation@
//MP-MANAGED-ADDED-AREA-ENDING @implementation@

}
