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
import net.sf.mp.demo.conference.domain.conference.Presentation;

/**
 *
 * <p>Title: PresentationPlace</p>
 *
 * <p>Description: Domain Object describing a PresentationPlace entity</p>
 *
 */
@Entity (name="PresentationPlace")
@Table (name="presentation_place")
@NamedQueries({
	 @NamedQuery(name="PresentationPlace.findAll", query="SELECT presentationPlace FROM PresentationPlace presentationPlace")
	,@NamedQuery(name="PresentationPlace.findByLocation", query="SELECT presentationPlace FROM PresentationPlace presentationPlace WHERE presentationPlace.location = :location")
	,@NamedQuery(name="PresentationPlace.findByLocationContaining", query="SELECT presentationPlace FROM PresentationPlace presentationPlace WHERE presentationPlace.location like :location")
	,@NamedQuery(name="PresentationPlace.findByNumberOfSeats", query="SELECT presentationPlace FROM PresentationPlace presentationPlace WHERE presentationPlace.numberOfSeats = :numberOfSeats")
})
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(namespace="net.sf.mp.demo.conference.domain.conference", name = "PresentationPlace")
@XmlRootElement(namespace="net.sf.mp.demo.conference.domain.conference")
public class PresentationPlace extends AbstractDomainObject {
    private static final long serialVersionUID = 1L;
	
    public  static final String FIND_ALL = "PresentationPlace.findAll";
    public static final String FIND_BY_LOCATION = "PresentationPlace.findByLocation";
    public static final String FIND_BY_LOCATION_CONTAINING ="PresentationPlace.findByLocationContaining";
    public static final String FIND_BY_NUMBEROFSEATS = "PresentationPlace.findByNumberOfSeats";
	
    @Id @Column(name="id" )
    @GeneratedValue(strategy = GenerationType.AUTO)
    @XmlElement (name="id")
    private Long id;

//MP-MANAGED-ADDED-AREA-BEGINNING @location-field-annotation@
//MP-MANAGED-ADDED-AREA-ENDING @location-field-annotation@
//MP-MANAGED-UPDATABLE-BEGINNING-DISABLE @ATTRIBUTE-location@
    @Column(name="location",  length=45,  nullable=true,  unique=false)
    @XmlElement (name="location")
    private String location; 
//MP-MANAGED-UPDATABLE-ENDING

//MP-MANAGED-ADDED-AREA-BEGINNING @number_of_seats-field-annotation@
//MP-MANAGED-ADDED-AREA-ENDING @number_of_seats-field-annotation@
//MP-MANAGED-UPDATABLE-BEGINNING-DISABLE @ATTRIBUTE-number_of_seats@
    @Column(name="number_of_seats",    nullable=true,  unique=false)
    @XmlElement (name="number-of-seats")
    private Integer numberOfSeats; 
//MP-MANAGED-UPDATABLE-ENDING

//MP-MANAGED-UPDATABLE-BEGINNING-DISABLE @presentationPresentationPlaceViaPresentationPlaceId-field-presentation_place@
    @XmlElement (name="presentationPresentationPlaceViaPresentationPlaceId")
    @OneToMany (targetEntity=net.sf.mp.demo.conference.domain.conference.Presentation.class, fetch=FetchType.LAZY, mappedBy="presentationPlaceId", cascade=CascadeType.REMOVE)//, cascade=CascadeType.ALL)
    private Set <Presentation> presentationPresentationPlaceViaPresentationPlaceId = new HashSet<Presentation>(); 
   
//MP-MANAGED-UPDATABLE-ENDING
    /**
    * Default constructor
    */
    public PresentationPlace() {
    }

	/**
	* All field constructor 
	*/
    public PresentationPlace(
       Long id,
       String location,
       Integer numberOfSeats) {
       //primary keys
       setId (id);
       //attributes
       setLocation (location);
       setNumberOfSeats (numberOfSeats);
       //parents
    }

	public PresentationPlace flat() {
	   return new PresentationPlace(
          getId(),
          getLocation(),
          getNumberOfSeats()
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
            .append("location", location)
            .append("numberOfSeats", numberOfSeats)
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
		if (!(object instanceof PresentationPlace)) return false;
		PresentationPlace presentationplace = (PresentationPlace) object;
		if (presentationplace.id==null || !presentationplace.id.equals(id)) return false;
		return true;
	}    

	public PresentationPlace clone() {
        PresentationPlace presentationplace = flat();
        return presentationplace;
	}
	
	public void copy (PresentationPlace presentationplace) {
		if (presentationplace!=null) {
			setId (presentationplace.getId());
			setLocation (presentationplace.getLocation());
			setNumberOfSeats (presentationplace.getNumberOfSeats());
		}
	}
	
	public static PresentationPlace fullMask() {
		return new PresentationPlace(
			longMask__ ,
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
    
//MP-MANAGED-UPDATABLE-BEGINNING-DISABLE @GETTER-SETTER-location@
    public String getLocation() {
        return location;
    }
	
    public void setLocation (String location) {
        this.location =  location;
    }    
//MP-MANAGED-UPDATABLE-ENDING

//MP-MANAGED-UPDATABLE-BEGINNING-DISABLE @GETTER-SETTER-number_of_seats@
    public Integer getNumberOfSeats() {
        return numberOfSeats;
    }
	
    public void setNumberOfSeats (Integer numberOfSeats) {
        this.numberOfSeats =  numberOfSeats;
    }    
//MP-MANAGED-UPDATABLE-ENDING



//MP-MANAGED-UPDATABLE-BEGINNING-DISABLE @presentationPresentationPlaceViaPresentationPlaceId-getter-presentation_place@
    public Set<Presentation> getPresentationPresentationPlaceViaPresentationPlaceId() {
        if (presentationPresentationPlaceViaPresentationPlaceId == null){
            presentationPresentationPlaceViaPresentationPlaceId = new HashSet<Presentation>();
        }
        return presentationPresentationPlaceViaPresentationPlaceId;
    }

    public void setPresentationPresentationPlaceViaPresentationPlaceId (Set<Presentation> presentationPresentationPlaceViaPresentationPlaceId) {
        this.presentationPresentationPlaceViaPresentationPlaceId = presentationPresentationPlaceViaPresentationPlaceId;
    }	
    
    public void addPresentationPresentationPlaceViaPresentationPlaceId (Presentation presentation) {
    	    getPresentationPresentationPlaceViaPresentationPlaceId().add(presentation);
    }
    
//MP-MANAGED-UPDATABLE-ENDING


//MP-MANAGED-ADDED-AREA-BEGINNING @implementation@
//MP-MANAGED-ADDED-AREA-ENDING @implementation@

}
