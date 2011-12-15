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
import net.sf.mp.demo.conference.domain.conference.Evaluation;
import net.sf.mp.demo.conference.domain.conference.Speaker;
import net.sf.mp.demo.conference.domain.conference.Conference;
import net.sf.mp.demo.conference.domain.conference.Address;
import net.sf.mp.demo.conference.domain.admin.Role;

/**
 *
 * <p>Title: ConferenceMember</p>
 *
 * <p>Description: Domain Object describing a ConferenceMember entity</p>
 *
 */
@Entity (name="ConferenceMember")
@Table (name="conference_member")
@Views({
	@View(
		name="base",
		members=
        "" 	
        + "id  ; "
        + "conferenceId  ; "
        + "firstName  ; "
        + "lastName  ; "
        + "email  ; "
        + "addressId  ; "
        + "status  ; "
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
	    + "evaluations { evaluations };" //+ "${link}${columnclass}s { ${link}${columnclass}s };" 
	    + "speakers { speakers };" //+ "${link}${columnclass}s { ${link}${columnclass}s };" 
	    + "roles;"
	),
	@View(extendsView="base",
        members=
          "" 	
	    + "conferenceFeedbacks { conferenceFeedbacks };" //+ "${link}${columnclass}s { ${link}${columnclass}s };" 
	    + "evaluations { evaluations };" //+ "${link}${columnclass}s { ${link}${columnclass}s };" 
	    + "speakers { speakers };" //+ "${link}${columnclass}s { ${link}${columnclass}s };" 
	    + "roles;"
	),
    @View(name="conferenceMemberDEFAULT_VIEW", 
	   members=
          " id ;" 	
        + "firstName  ; "
        + "lastName  ; "
        + "email  ; "
        + "status  ; "
	)
})

@Tabs({
@Tab(
properties=
     " firstName "
    +",  lastName "
    +",  email "
    +",  status "
)
,
@Tab(
name = "ConferenceMemberTab",
properties=
     " firstName "
    +",  lastName "
    +",  email "
    +",  status "
)
,
@Tab(
name = "ConferenceMemberTabWithRef",
properties=
     " firstName "
    +",  lastName "
    +",  email "
    +",  status "
)
})
//MP-MANAGED-ADDED-AREA-BEGINNING @class-annotation@
//MP-MANAGED-ADDED-AREA-ENDING @class-annotation@
public class ConferenceMember {

    @Hidden @Id @Column(name="id" )
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id; 

//MP-MANAGED-ADDED-AREA-BEGINNING @first_name-field-annotation@
//MP-MANAGED-ADDED-AREA-ENDING @first_name-field-annotation@

//MP-MANAGED-UPDATABLE-BEGINNING-DISABLE @ATTRIBUTE-first_name@
    @Column(name="first_name",  length=255, nullable=false,  unique=false)
    @Required
    private String firstName;	
//MP-MANAGED-UPDATABLE-ENDING

//MP-MANAGED-ADDED-AREA-BEGINNING @last_name-field-annotation@
//MP-MANAGED-ADDED-AREA-ENDING @last_name-field-annotation@

//MP-MANAGED-UPDATABLE-BEGINNING-DISABLE @ATTRIBUTE-last_name@
    @Column(name="last_name",  length=255, nullable=false,  unique=false)
    @Required
    private String lastName;	
//MP-MANAGED-UPDATABLE-ENDING

//MP-MANAGED-ADDED-AREA-BEGINNING @email-field-annotation@
//MP-MANAGED-ADDED-AREA-ENDING @email-field-annotation@

//MP-MANAGED-UPDATABLE-BEGINNING-DISABLE @ATTRIBUTE-email@
    @Column(name="email",  length=255, nullable=false,  unique=false)
    @Required
    @Stereotype ("EMAIL")
    private String email;	
//MP-MANAGED-UPDATABLE-ENDING

//MP-MANAGED-ADDED-AREA-BEGINNING @status-field-annotation@
//MP-MANAGED-ADDED-AREA-ENDING @status-field-annotation@

//MP-MANAGED-UPDATABLE-BEGINNING-DISABLE @ATTRIBUTE-status@
    @Column(name="status",  length=45, nullable=false,  unique=false)
    @Required
    @Editor(forViews="base,Create,Update,DEFAULT,conferenceMemberDEFAULT_VIEW", value="ConferenceMemberStatusComboEditor")
    private String status;	
//MP-MANAGED-UPDATABLE-ENDING


    @ManyToOne (fetch=FetchType.LAZY ,optional=false) //remove optional=false to aggragate but leads to a side effect when going directly to the entity: required check is not performed=> if no set DB check constraint is raised...
    @JoinColumn(name="conference_id", nullable=false,  unique=false  )
    @ReferenceView ("conferenceDEFAULT_VIEW")  
    private Conference conferenceId; 
    
    @ManyToOne (fetch=FetchType.LAZY ,optional=false) //remove optional=false to aggragate but leads to a side effect when going directly to the entity: required check is not performed=> if no set DB check constraint is raised...
    @JoinColumn(name="address_id", nullable=false,  unique=false  )
    @ReferenceView ("addressDEFAULT_VIEW")  
    private Address addressId; 
    

    @OneToMany (targetEntity=net.sf.mp.demo.conference.domain.conference.ConferenceFeedback.class, fetch=FetchType.LAZY, mappedBy="conferenceMemberId", cascade=CascadeType.REMOVE)//, cascade=CascadeType.ALL)
    private Set <ConferenceFeedback> conferenceFeedbacks = new HashSet<ConferenceFeedback>(); 
   
    @OneToMany (targetEntity=net.sf.mp.demo.conference.domain.conference.Evaluation.class, fetch=FetchType.LAZY, mappedBy="conferenceMemberId", cascade=CascadeType.REMOVE)//, cascade=CascadeType.ALL)
    private Set <Evaluation> evaluations = new HashSet<Evaluation>(); 
   
    @OneToMany (targetEntity=net.sf.mp.demo.conference.domain.conference.Speaker.class, fetch=FetchType.LAZY, mappedBy="conferenceMemberId", cascade=CascadeType.REMOVE)//, cascade=CascadeType.ALL)
    private Set <Speaker> speakers = new HashSet<Speaker>(); 
   

    @ManyToMany
    @JoinTable(name="MEMBER_ROLE",
        joinColumns=@JoinColumn(name="conference_member_id"), 
        inverseJoinColumns=@JoinColumn(name="role_id") 
    )
    private Set <Role> roles = new HashSet <Role> ();


    /**
    * Default constructor
    */
    public ConferenceMember() {
    }


    public Long getId() {
        return id;
    }
	
    public void setId (Long id) {
        this.id =  id;
    }
    

//MP-MANAGED-UPDATABLE-BEGINNING-DISABLE @GETTER-SETTER-first_name@
    public String getFirstName() {
        return firstName;
    }
	
    public void setFirstName (String firstName) {
        this.firstName =  firstName;
    } 
//MP-MANAGED-UPDATABLE-ENDING

//MP-MANAGED-UPDATABLE-BEGINNING-DISABLE @GETTER-SETTER-last_name@
    public String getLastName() {
        return lastName;
    }
	
    public void setLastName (String lastName) {
        this.lastName =  lastName;
    } 
//MP-MANAGED-UPDATABLE-ENDING

//MP-MANAGED-UPDATABLE-BEGINNING-DISABLE @GETTER-SETTER-email@
    public String getEmail() {
        return email;
    }
	
    public void setEmail (String email) {
        this.email =  email;
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


    public Conference getConferenceId () {  //
    	return conferenceId;
    }
	
    public void setConferenceId (Conference conferenceId) {
    	this.conferenceId = conferenceId;//this.conferenceId = conference;
    }
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
    
    public Set<Evaluation> getEvaluations() {
        if (evaluations == null){
            evaluations = new HashSet<Evaluation>();
        }
        return evaluations;
    }

    public void setEvaluations (Set<Evaluation> evaluations) {
        this.evaluations = evaluations;
    }	
    
    public void addEvaluations (Evaluation evaluation) {
    	    getEvaluations().add(evaluation);
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
    
    public Set<Role> getRoles() {
        if (roles == null){
            roles = new HashSet<Role>();
        }
        return roles;
    }

    public void setRoles (Set<Role> roles) {
        this.roles = roles;
    }
    	
    public void addRoles (Role roles) {
        getRoles().add(roles);
    }	



//MP-MANAGED-ADDED-AREA-BEGINNING @implementation@
//MP-MANAGED-ADDED-AREA-ENDING @implementation@

}
