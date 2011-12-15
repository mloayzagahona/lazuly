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
@Views({
	@View(
		name="base",
		members=
        "" 	
        + "id  ; "
        + "name  ; "
        + "startDate  ; "
        + "endDate  ; "
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
	    + "conferenceFeedbacks { conferenceFeedbacks };" //+ "${link}${columnclass}s { ${link}${columnclass}s };" 
	    + "conferenceMembers { conferenceMembers };" //+ "${link}${columnclass}s { ${link}${columnclass}s };" 
	    + "sponsors { sponsors };" //+ "${link}${columnclass}s { ${link}${columnclass}s };" 
	),
	@View(extendsView="base",
        members=
          "" 	
	    + "conferenceFeedbacks { conferenceFeedbacks };" //+ "${link}${columnclass}s { ${link}${columnclass}s };" 
	    + "conferenceMembers { conferenceMembers };" //+ "${link}${columnclass}s { ${link}${columnclass}s };" 
	    + "sponsors { sponsors };" //+ "${link}${columnclass}s { ${link}${columnclass}s };" 
	),
    @View(name="conferenceDEFAULT_VIEW", 
	   members=
          " id ;" 	
        + "name  ; "
        + "startDate  ; "
        + "endDate  ; "
	)
})

@Tabs({
@Tab(
properties=
     " name "
    +",  startDate "
    +",  endDate "
)
,
@Tab(
name = "ConferenceTab",
properties=
     " name "
    +",  startDate "
    +",  endDate "
)
,
@Tab(
name = "ConferenceTabWithRef",
properties=
     " name "
    +",  startDate "
    +",  endDate "
)
})
//MP-MANAGED-ADDED-AREA-BEGINNING @class-annotation@
//MP-MANAGED-ADDED-AREA-ENDING @class-annotation@
public class Conference {

    @Hidden @Id @Column(name="id" )
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id; 

//MP-MANAGED-ADDED-AREA-BEGINNING @name-field-annotation@
//MP-MANAGED-ADDED-AREA-ENDING @name-field-annotation@

//MP-MANAGED-UPDATABLE-BEGINNING-DISABLE @ATTRIBUTE-name@
    @Column(name="name",  length=255, nullable=false,  unique=false)
    @Required
    private String name;	
//MP-MANAGED-UPDATABLE-ENDING

//MP-MANAGED-ADDED-AREA-BEGINNING @start_date-field-annotation@
//MP-MANAGED-ADDED-AREA-ENDING @start_date-field-annotation@

//MP-MANAGED-UPDATABLE-BEGINNING-DISABLE @ATTRIBUTE-start_date@
    @Column(name="start_date",    nullable=true,  unique=false)
    private Date startDate;	
//MP-MANAGED-UPDATABLE-ENDING

//MP-MANAGED-ADDED-AREA-BEGINNING @end_date-field-annotation@
//MP-MANAGED-ADDED-AREA-ENDING @end_date-field-annotation@

//MP-MANAGED-UPDATABLE-BEGINNING-DISABLE @ATTRIBUTE-end_date@
    @Column(name="end_date",    nullable=true,  unique=false)
    private Date endDate;	
//MP-MANAGED-UPDATABLE-ENDING


    @ManyToOne (fetch=FetchType.LAZY ,optional=false) //remove optional=false to aggragate but leads to a side effect when going directly to the entity: required check is not performed=> if no set DB check constraint is raised...
    @JoinColumn(name="address_id", nullable=false,  unique=false  )
    @ReferenceView ("addressDEFAULT_VIEW")  
    private Address addressId; 
    

    @OneToMany (targetEntity=net.sf.mp.demo.conference.domain.conference.ConferenceFeedback.class, fetch=FetchType.LAZY, mappedBy="conferenceId", cascade=CascadeType.REMOVE)//, cascade=CascadeType.ALL)
    @ListProperties(""+
       "comment ,feedbackDate ,conferenceMemberId.firstName   ")
    private Set <ConferenceFeedback> conferenceFeedbacks = new HashSet<ConferenceFeedback>(); 
   
    @OneToMany (targetEntity=net.sf.mp.demo.conference.domain.conference.ConferenceMember.class, fetch=FetchType.LAZY, mappedBy="conferenceId", cascade=CascadeType.REMOVE)//, cascade=CascadeType.ALL)
    private Set <ConferenceMember> conferenceMembers = new HashSet<ConferenceMember>(); 
   
    @OneToMany (targetEntity=net.sf.mp.demo.conference.domain.conference.Sponsor.class, fetch=FetchType.LAZY, mappedBy="conferenceId", cascade=CascadeType.REMOVE)//, cascade=CascadeType.ALL)
    private Set <Sponsor> sponsors = new HashSet<Sponsor>(); 
   


    /**
    * Default constructor
    */
    public Conference() {
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
    public Date getStartDate() {
        return startDate;
    }
	
    public void setStartDate (Date startDate) {
        this.startDate =  startDate;
    } 
//MP-MANAGED-UPDATABLE-ENDING

//MP-MANAGED-UPDATABLE-BEGINNING-DISABLE @GETTER-SETTER-end_date@
    public Date getEndDate() {
        return endDate;
    }
	
    public void setEndDate (Date endDate) {
        this.endDate =  endDate;
    } 
//MP-MANAGED-UPDATABLE-ENDING


    public Address getAddressId () {  //
    	return addressId;
    }
	
    public void setAddressId (Address addressId) {
    	this.addressId = addressId;//this.addressId = address;
    }

    public Set<ConferenceFeedback> getConferenceFeedbacks() {
        if (conferenceFeedbacks == null){
            conferenceFeedbacks = new HashSet<ConferenceFeedback>();
        }
        return conferenceFeedbacks;
    }

    public void setConferenceFeedbacks (Set<ConferenceFeedback> conferenceFeedbacks) {
        this.conferenceFeedbacks = conferenceFeedbacks;
    }	
    
    public void addConferenceFeedbacks (ConferenceFeedback conferenceFeedback) {
    	    getConferenceFeedbacks().add(conferenceFeedback);
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
