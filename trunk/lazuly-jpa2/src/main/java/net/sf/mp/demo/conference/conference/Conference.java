package net.sf.mp.demo.conference.conference;

import java.sql.*;
import java.util.Date;
import java.util.List;
import java.util.ArrayList;
import java.util.Set;
import java.util.HashSet;

import javax.persistence.*;

import net.sf.mp.demo.conference.conference.ConferenceFeedback;
import net.sf.mp.demo.conference.conference.ConferenceMember;
import net.sf.mp.demo.conference.conference.Sponsor;
import net.sf.mp.demo.conference.conference.Address;

/**
 *
 * <p>Title: Conference</p>
 *
 * <p>Description: Domain Object describing a Conference entity</p>
 *
 */
@Entity (name="Conference")
@Table (name="conference")
public class Conference {

    @Id @Column(name="id" )
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name="name",  length=255, nullable=false,  unique=false)
    private String name; 
    @Column(name="start_date",    nullable=true,  unique=false)
    private Date startDate; 
    @Column(name="end_date",    nullable=true,  unique=false)
    private Date endDate; 

    @ManyToOne (fetch=FetchType.LAZY , optional=false)
    @JoinColumn(name="address_id", referencedColumnName = "id", nullable=false,  unique=false ) 
    private Address addressId;
    
    @OneToMany (targetEntity=net.sf.mp.demo.conference.conference.ConferenceFeedback.class, fetch=FetchType.LAZY, mappedBy="conferenceId", cascade=CascadeType.REMOVE)//, cascade=CascadeType.ALL)
    private Set <ConferenceFeedback> conferenceFeedbacks = new HashSet<ConferenceFeedback>(); 
   
    @OneToMany (targetEntity=net.sf.mp.demo.conference.conference.ConferenceMember.class, fetch=FetchType.LAZY, mappedBy="conferenceId", cascade=CascadeType.REMOVE)//, cascade=CascadeType.ALL)
    private Set <ConferenceMember> conferenceMembers = new HashSet<ConferenceMember>(); 
   
    @OneToMany (targetEntity=net.sf.mp.demo.conference.conference.Sponsor.class, fetch=FetchType.LAZY, mappedBy="conferenceId", cascade=CascadeType.REMOVE)//, cascade=CascadeType.ALL)
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
    
    public String getName() {
        return name;
    }
	
    public void setName (String name) {
        this.name =  name;
    }
    
    public Date getStartDate() {
        return startDate;
    }
	
    public void setStartDate (Date startDate) {
        this.startDate =  startDate;
    }
    
    public Date getEndDate() {
        return endDate;
    }
	
    public void setEndDate (Date endDate) {
        this.endDate =  endDate;
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
    
}
