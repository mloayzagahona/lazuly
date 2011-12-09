package net.sf.mp.demo.conference.domain.admin;

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
import net.sf.mp.demo.conference.domain.conference.Address;

/**
 *
 * <p>Title: Country</p>
 *
 * <p>Description: Domain Object describing a Country entity</p>
 *
 */
@Entity (name="Country")
@Table (name="country")
@NamedQueries({
	 @NamedQuery(name="Country.findAll", query="SELECT country FROM Country country")
	,@NamedQuery(name="Country.findByName", query="SELECT country FROM Country country WHERE country.name = :name")
	,@NamedQuery(name="Country.findByNameContaining", query="SELECT country FROM Country country WHERE country.name like :name")
	,@NamedQuery(name="Country.findByIsoName", query="SELECT country FROM Country country WHERE country.isoName = :isoName")
	,@NamedQuery(name="Country.findByIsoNameContaining", query="SELECT country FROM Country country WHERE country.isoName like :isoName")
})
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(namespace="net.sf.mp.demo.conference.domain.admin", name = "Country")
@XmlRootElement(namespace="net.sf.mp.demo.conference.domain.admin")
@org.hibernate.annotations.Cache(usage = org.hibernate.annotations.CacheConcurrencyStrategy.READ_WRITE)
public class Country extends AbstractDomainObject {
    private static final long serialVersionUID = 1L;
	
    public  static final String FIND_ALL = "Country.findAll";
    public static final String FIND_BY_NAME = "Country.findByName";
    public static final String FIND_BY_NAME_CONTAINING ="Country.findByNameContaining";
    public static final String FIND_BY_ISONAME = "Country.findByIsoName";
    public static final String FIND_BY_ISONAME_CONTAINING ="Country.findByIsoNameContaining";
	
    @Id @Column(name="id" )
    @GeneratedValue(strategy = GenerationType.AUTO)
    @XmlElement (name="id")
    private Integer id;

//MP-MANAGED-ADDED-AREA-BEGINNING @name-field-annotation@
//MP-MANAGED-ADDED-AREA-ENDING @name-field-annotation@
//MP-MANAGED-UPDATABLE-BEGINNING-DISABLE @ATTRIBUTE-name@
    @Column(name="name",  length=45, nullable=false,  unique=false)
    @XmlElement (name="name")
    private String name; 
//MP-MANAGED-UPDATABLE-ENDING

//MP-MANAGED-ADDED-AREA-BEGINNING @iso_name-field-annotation@
//MP-MANAGED-ADDED-AREA-ENDING @iso_name-field-annotation@
//MP-MANAGED-UPDATABLE-BEGINNING-DISABLE @ATTRIBUTE-iso_name@
    @Column(name="iso_name",  length=45, nullable=false,  unique=false)
    @XmlElement (name="iso-name")
    private String isoName; 
//MP-MANAGED-UPDATABLE-ENDING

//MP-MANAGED-UPDATABLE-BEGINNING-DISABLE @addressCountryViaCountryId-field-country@
    @XmlElement (name="addressCountryViaCountryId")
    @OneToMany (targetEntity=net.sf.mp.demo.conference.domain.conference.Address.class, fetch=FetchType.LAZY, mappedBy="countryId", cascade=CascadeType.REMOVE)//, cascade=CascadeType.ALL)
    private Set <Address> addressCountryViaCountryId = new HashSet<Address>(); 
   
//MP-MANAGED-UPDATABLE-ENDING
    /**
    * Default constructor
    */
    public Country() {
    }

	/**
	* All field constructor 
	*/
    public Country(
       Integer id,
       String name,
       String isoName) {
       //primary keys
       setId (id);
       //attributes
       setName (name);
       setIsoName (isoName);
       //parents
    }

	public Country flat() {
	   return new Country(
          getId(),
          getName(),
          getIsoName()
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
            .append("isoName", isoName)
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
		if (!(object instanceof Country)) return false;
		Country country = (Country) object;
		if (country.id==null || !country.id.equals(id)) return false;
		return true;
	}    

	public Country clone() {
        Country country = flat();
        return country;
	}
	
	public void copy (Country country) {
		if (country!=null) {
			setId (country.getId());
			setName (country.getName());
			setIsoName (country.getIsoName());
		}
	}
	
	public static Country fullMask() {
		return new Country(
			integerMask__ ,
			stringMask__ ,
			stringMask__ 
		);	    
	}

    public Integer getId() {
        return id;
    }
	
    public void setId (Integer id) {
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

//MP-MANAGED-UPDATABLE-BEGINNING-DISABLE @GETTER-SETTER-iso_name@
    public String getIsoName() {
        return isoName;
    }
	
    public void setIsoName (String isoName) {
        this.isoName =  isoName;
    }    
//MP-MANAGED-UPDATABLE-ENDING



//MP-MANAGED-UPDATABLE-BEGINNING-DISABLE @addressCountryViaCountryId-getter-country@
    public Set<Address> getAddressCountryViaCountryId() {
        if (addressCountryViaCountryId == null){
            addressCountryViaCountryId = new HashSet<Address>();
        }
        return addressCountryViaCountryId;
    }

    public void setAddressCountryViaCountryId (Set<Address> addressCountryViaCountryId) {
        this.addressCountryViaCountryId = addressCountryViaCountryId;
    }	
    
    public void addAddressCountryViaCountryId (Address address) {
    	    getAddressCountryViaCountryId().add(address);
    }
    
//MP-MANAGED-UPDATABLE-ENDING


//MP-MANAGED-ADDED-AREA-BEGINNING @implementation@
//MP-MANAGED-ADDED-AREA-ENDING @implementation@

}
