package net.sf.mp.demo.conference.conference;

import java.sql.*;
import java.util.Date;
import java.util.List;
import java.util.ArrayList;
import java.util.Set;
import java.util.HashSet;

import javax.persistence.*;

import net.sf.mp.demo.conference.conference.ConferenceFeedback;
import net.sf.mp.demo.conference.conference.Evaluation;
import net.sf.mp.demo.conference.conference.Speaker;
import net.sf.mp.demo.conference.conference.Conference;
import net.sf.mp.demo.conference.conference.Address;
import net.sf.mp.demo.conference.admin.Role;

/**
 *
 * <p>Title: ConferenceMember</p>
 *
 * <p>Description: Domain Object describing a ConferenceMember entity</p>
 *
 */
@Entity (name="ConferenceMember")
@Table (name="conference_member")
public class ConferenceMember {

    @Id @Column(name="id" )
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name="first_name",  length=255, nullable=false,  unique=false)
    private String firstName; 
    @Column(name="last_name",  length=255, nullable=false,  unique=false)
    private String lastName; 
    @Column(name="email",  length=255, nullable=false,  unique=false)
    private String email; 
    @Column(name="status",  length=45, nullable=false,  unique=false)
    private String status; 

    @ManyToOne (fetch=FetchType.LAZY , optional=false)
    @JoinColumn(name="conference_id", referencedColumnName = "id", nullable=false,  unique=false ) 
    private Conference conferenceId;
    
    @ManyToOne (fetch=FetchType.LAZY , optional=false)
    @JoinColumn(name="address_id", referencedColumnName = "id", nullable=false,  unique=false ) 
    private Address addressId;
    
    @OneToMany (targetEntity=net.sf.mp.demo.conference.conference.ConferenceFeedback.class, fetch=FetchType.LAZY, mappedBy="conferenceMemberId", cascade=CascadeType.REMOVE)//, cascade=CascadeType.ALL)
    private Set <ConferenceFeedback> conferenceFeedbacks = new HashSet<ConferenceFeedback>(); 
   
    @OneToMany (targetEntity=net.sf.mp.demo.conference.conference.Evaluation.class, fetch=FetchType.LAZY, mappedBy="conferenceMemberId", cascade=CascadeType.REMOVE)//, cascade=CascadeType.ALL)
    private Set <Evaluation> evaluations = new HashSet<Evaluation>(); 
   
    @OneToMany (targetEntity=net.sf.mp.demo.conference.conference.Speaker.class, fetch=FetchType.LAZY, mappedBy="conferenceMemberId", cascade=CascadeType.REMOVE)//, cascade=CascadeType.ALL)
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
    
    public String getFirstName() {
        return firstName;
    }
	
    public void setFirstName (String firstName) {
        this.firstName =  firstName;
    }
    
    public String getLastName() {
        return lastName;
    }
	
    public void setLastName (String lastName) {
        this.lastName =  lastName;
    }
    
    public String getEmail() {
        return email;
    }
	
    public void setEmail (String email) {
        this.email =  email;
    }
    
    public String getStatus() {
        return status;
    }
	
    public void setStatus (String status) {
        this.status =  status;
    }
    
    public Conference getConferenceId () {
    	return conferenceId;
    }
	
    public void setConferenceId (Conference conferenceId) {
    	this.conferenceId = conferenceId;
    }
    
    public Address getAddressId () {
    	return addressId;
    }
	
    public void setAddressId (Address addressId) {
    	this.addressId = addressId;
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
}
