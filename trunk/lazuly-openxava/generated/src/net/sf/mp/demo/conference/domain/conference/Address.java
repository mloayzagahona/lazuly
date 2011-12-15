package net.sf.mp.demo.conference.domain.conference;

//MP-MANAGED-ADDED-AREA-BEGINNING @import@
//MP-MANAGED-ADDED-AREA-ENDING @import@
import java.sql.*;
import java.util.Date;
import java.util.List;
import java.util.ArrayList;
import java.util.Set;
import java.util.HashSet;

import javax.persistence.*;
import org.openxava.annotations.*;

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
@Views({
	@View(
		name="base",
		members=
        "" 	
        + "id  ; "
        + "street1  ; "
        + "street2  ; "
        + "countryId  ; "
		),
	@View(
		name="Create", 
		extendsView="base"
	),
	@View(
		name="Update", 
		extendsView="base",
        members=
          "" 	
	    + "conferences { conferences };" //+ "${link}${columnclass}s { ${link}${columnclass}s };" 
	    + "conferenceMembers { conferenceMembers };" //+ "${link}${columnclass}s { ${link}${columnclass}s };" 
	    + "sponsors { sponsors };" //+ "${link}${columnclass}s { ${link}${columnclass}s };" 
	),
	@View(extendsView="base",
        members=
          "" 	
	    + "conferences { conferences };" //+ "${link}${columnclass}s { ${link}${columnclass}s };" 
	    + "conferenceMembers { conferenceMembers };" //+ "${link}${columnclass}s { ${link}${columnclass}s };" 
	    + "sponsors { sponsors };" //+ "${link}${columnclass}s { ${link}${columnclass}s };" 
	),
    @View(name="addressDEFAULT_VIEW", 
	   members=
          " id ;" 	
        + "street1  ; "
        + "street2  ; "
        + "countryId ;"
	)
})

@Tabs({
@Tab(
properties=
     " street1 "
    +",  street2 "
    +",  countryId.name "
)
,
@Tab(
name = "AddressTab",
properties=
     " street1 "
    +",  street2 "
)
,
@Tab(
name = "AddressTabWithRef",
properties=
     " street1 "
    +",  street2 "
    +",  countryId.name "
)
})
//MP-MANAGED-ADDED-AREA-BEGINNING @class-annotation@
//MP-MANAGED-ADDED-AREA-ENDING @class-annotation@
public class Address {

    @Hidden @Id @Column(name="id" )
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id; 

//MP-MANAGED-ADDED-AREA-BEGINNING @street1-field-annotation@
//MP-MANAGED-ADDED-AREA-ENDING @street1-field-annotation@

//MP-MANAGED-UPDATABLE-BEGINNING-DISABLE @ATTRIBUTE-street1@
    @Column(name="street1",  length=255,  nullable=true,  unique=false)
    private String street1;	
//MP-MANAGED-UPDATABLE-ENDING

//MP-MANAGED-ADDED-AREA-BEGINNING @street2-field-annotation@
//MP-MANAGED-ADDED-AREA-ENDING @street2-field-annotation@

//MP-MANAGED-UPDATABLE-BEGINNING-DISABLE @ATTRIBUTE-street2@
    @Column(name="street2",  length=255,  nullable=true,  unique=false)
    private String street2;	
//MP-MANAGED-UPDATABLE-ENDING


    @ManyToOne (fetch=FetchType.LAZY ,optional=false) //remove optional=false to aggragate but leads to a side effect when going directly to the entity: required check is not performed=> if no set DB check constraint is raised...
    @JoinColumn(name="country_id", nullable=false,  unique=false  )
    @ReferenceView ("countryDEFAULT_VIEW")  
    @DescriptionsList(
       descriptionProperties=
       "name "
       ,order=
       "NAME desc  "
    )
    @NoCreate
    @NoModify	
    private Country countryId; 
    

    @OneToMany (targetEntity=net.sf.mp.demo.conference.domain.conference.Conference.class, fetch=FetchType.LAZY, mappedBy="addressId", cascade=CascadeType.REMOVE)//, cascade=CascadeType.ALL)
    private Set <Conference> conferences = new HashSet<Conference>(); 
   
    @OneToMany (targetEntity=net.sf.mp.demo.conference.domain.conference.ConferenceMember.class, fetch=FetchType.LAZY, mappedBy="addressId", cascade=CascadeType.REMOVE)//, cascade=CascadeType.ALL)
    private Set <ConferenceMember> conferenceMembers = new HashSet<ConferenceMember>(); 
   
    @OneToMany (targetEntity=net.sf.mp.demo.conference.domain.conference.Sponsor.class, fetch=FetchType.LAZY, mappedBy="addressId", cascade=CascadeType.REMOVE)//, cascade=CascadeType.ALL)
    private Set <Sponsor> sponsors = new HashSet<Sponsor>(); 
   


    /**
    * Default constructor
    */
    public Address() {
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


    public Country getCountryId () {  //
    	return countryId;
    }
	
    public void setCountryId (Country countryId) {
    	this.countryId = countryId;//this.countryId = country;
    }

    public Set<Conference> getConferences() {
        if (conferences == null){
            conferences = new HashSet<Conference>();
        }
        return conferences;
    }

    public void setConferences (Set<Conference> conferences) {
        this.conferences = conferences;
    }	
    
    public void addConferences (Conference conference) {
    	    getConferences().add(conference);
    }
    
    public Set<ConferenceMember> getConferenceMembers() {
        if (conferenceMembers == null){
            conferenceMembers = new HashSet<ConferenceMember>();
        }
        return conferenceMembers;
    }

    public void setConferenceMembers (Set<ConferenceMember> conferenceMembers) {
        this.conferenceMembers = conferenceMembers;
    }	
    
    public void addConferenceMembers (ConferenceMember conferenceMember) {
    	    getConferenceMembers().add(conferenceMember);
    }
    
    public Set<Sponsor> getSponsors() {
        if (sponsors == null){
            sponsors = new HashSet<Sponsor>();
        }
        return sponsors;
    }

    public void setSponsors (Set<Sponsor> sponsors) {
        this.sponsors = sponsors;
    }	
    
    public void addSponsors (Sponsor sponsor) {
    	    getSponsors().add(sponsor);
    }
    


//MP-MANAGED-ADDED-AREA-BEGINNING @implementation@
//MP-MANAGED-ADDED-AREA-ENDING @implementation@

}
