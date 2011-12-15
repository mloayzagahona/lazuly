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
@Views({
	@View(
		name="base",
		members=
        "" 	
        + "id  ; "
        + "name  ; "
        + "privilegeType  ; "
        + "status  ; "
        + "conferenceId  ; "
        + "addressId  ; "
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
	    + "speakers { speakers };" //+ "${link}${columnclass}s { ${link}${columnclass}s };" 
	),
	@View(extendsView="base",
        members=
          "" 	
	    + "speakers { speakers };" //+ "${link}${columnclass}s { ${link}${columnclass}s };" 
	),
    @View(name="sponsorDEFAULT_VIEW", 
	   members=
          " id ;" 	
        + "name  ; "
        + "privilegeType  ; "
        + "status  ; "
	)
})

@Tabs({
@Tab(
properties=
     " name "
    +",  privilegeType "
    +",  status "
)
,
@Tab(
name = "SponsorTab",
properties=
     " name "
    +",  privilegeType "
    +",  status "
)
,
@Tab(
name = "SponsorTabWithRef",
properties=
     " name "
    +",  privilegeType "
    +",  status "
)
})
//MP-MANAGED-ADDED-AREA-BEGINNING @class-annotation@
//MP-MANAGED-ADDED-AREA-ENDING @class-annotation@
public class Sponsor {

    @Hidden @Id @Column(name="id" )
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id; 

//MP-MANAGED-ADDED-AREA-BEGINNING @name-field-annotation@
//MP-MANAGED-ADDED-AREA-ENDING @name-field-annotation@

//MP-MANAGED-UPDATABLE-BEGINNING-DISABLE @ATTRIBUTE-name@
    @Column(name="name",  length=45, nullable=false,  unique=false)
    @Required
    private String name;	
//MP-MANAGED-UPDATABLE-ENDING

//MP-MANAGED-ADDED-AREA-BEGINNING @privilege_type-field-annotation@
//MP-MANAGED-ADDED-AREA-ENDING @privilege_type-field-annotation@

//MP-MANAGED-UPDATABLE-BEGINNING-DISABLE @ATTRIBUTE-privilege_type@
    @Column(name="privilege_type",  length=45, nullable=false,  unique=false)
    @Required
    @Editor(forViews="base,Create,Update,DEFAULT,sponsorDEFAULT_VIEW", value="SponsorPrivilegeTypeComboEditor")
    private String privilegeType;	
//MP-MANAGED-UPDATABLE-ENDING

//MP-MANAGED-ADDED-AREA-BEGINNING @status-field-annotation@
//MP-MANAGED-ADDED-AREA-ENDING @status-field-annotation@

//MP-MANAGED-UPDATABLE-BEGINNING-DISABLE @ATTRIBUTE-status@
    @Column(name="status",  length=45, nullable=false,  unique=false)
    @Required
    @Editor(forViews="base,Create,Update,DEFAULT,sponsorDEFAULT_VIEW", value="SponsorStatusComboEditor")
    private String status;	
//MP-MANAGED-UPDATABLE-ENDING


    @ManyToOne (fetch=FetchType.LAZY ,optional=false) //remove optional=false to aggragate but leads to a side effect when going directly to the entity: required check is not performed=> if no set DB check constraint is raised...
    @JoinColumn(name="address_id", nullable=false,  unique=false  )
    @ReferenceView ("addressDEFAULT_VIEW")  
    private Address addressId; 
    
    @ManyToOne (fetch=FetchType.LAZY ,optional=false) //remove optional=false to aggragate but leads to a side effect when going directly to the entity: required check is not performed=> if no set DB check constraint is raised...
    @JoinColumn(name="conference_id", nullable=false,  unique=false  )
    @ReferenceView ("conferenceDEFAULT_VIEW")  
    private Conference conferenceId; 
    

    @OneToMany (targetEntity=net.sf.mp.demo.conference.domain.conference.Speaker.class, fetch=FetchType.LAZY, mappedBy="sponsorId", cascade=CascadeType.REMOVE)//, cascade=CascadeType.ALL)
    @ListProperties(""+
       "bio ,photo ,webSiteUrl ,conferenceMemberId.firstName   ")
    private Set <Speaker> speakers = new HashSet<Speaker>(); 
   


    /**
    * Default constructor
    */
    public Sponsor() {
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


    public Address getAddressId () {  //
    	return addressId;
    }
	
    public void setAddressId (Address addressId) {
    	this.addressId = addressId;//this.addressId = address;
    }
    public Conference getConferenceId () {  //
    	return conferenceId;
    }
	
    public void setConferenceId (Conference conferenceId) {
    	this.conferenceId = conferenceId;//this.conferenceId = conference;
    }

    public Set<Speaker> getSpeakers() {
        if (speakers == null){
            speakers = new HashSet<Speaker>();
        }
        return speakers;
    }

    public void setSpeakers (Set<Speaker> speakers) {
        this.speakers = speakers;
    }	
    
    public void addSpeakers (Speaker speaker) {
    	    getSpeakers().add(speaker);
    }
    


//MP-MANAGED-ADDED-AREA-BEGINNING @implementation@
//MP-MANAGED-ADDED-AREA-ENDING @implementation@

}
