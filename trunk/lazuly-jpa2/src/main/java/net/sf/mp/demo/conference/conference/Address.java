package net.sf.mp.demo.conference.conference;

import java.sql.*;
import java.util.Date;
import java.util.List;
import java.util.ArrayList;
import java.util.Set;
import java.util.HashSet;

import javax.persistence.*;

import net.sf.mp.demo.conference.conference.Conference;
import net.sf.mp.demo.conference.conference.ConferenceMember;
import net.sf.mp.demo.conference.conference.Sponsor;
import net.sf.mp.demo.conference.admin.Country;

/**
 *
 * <p>Title: Address</p>
 *
 * <p>Description: Domain Object describing a Address entity</p>
 *
 */
@Entity (name="Address")
@Table (name="address")
public class Address {

    @Id @Column(name="id" )
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name="street1",  length=255,  nullable=true,  unique=false)
    private String street1; 
    @Column(name="street2",  length=255,  nullable=true,  unique=false)
    private String street2; 

    @ManyToOne (fetch=FetchType.LAZY , optional=false)
    @JoinColumn(name="country_id", referencedColumnName = "id", nullable=false,  unique=false ) 
    private Country countryId;
    
    @OneToMany (targetEntity=net.sf.mp.demo.conference.conference.Conference.class, fetch=FetchType.LAZY, mappedBy="addressId", cascade=CascadeType.REMOVE)//, cascade=CascadeType.ALL)
    private Set <Conference> conferences = new HashSet<Conference>(); 
   
    @OneToMany (targetEntity=net.sf.mp.demo.conference.conference.ConferenceMember.class, fetch=FetchType.LAZY, mappedBy="addressId", cascade=CascadeType.REMOVE)//, cascade=CascadeType.ALL)
    private Set <ConferenceMember> conferenceMembers = new HashSet<ConferenceMember>(); 
   
    @OneToMany (targetEntity=net.sf.mp.demo.conference.conference.Sponsor.class, fetch=FetchType.LAZY, mappedBy="addressId", cascade=CascadeType.REMOVE)//, cascade=CascadeType.ALL)
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
    
    public String getStreet1() {
        return street1;
    }
	
    public void setStreet1 (String street1) {
        this.street1 =  street1;
    }
    
    public String getStreet2() {
        return street2;
    }
	
    public void setStreet2 (String street2) {
        this.street2 =  street2;
    }
    
    public Country getCountryId () {
    	return countryId;
    }
	
    public void setCountryId (Country countryId) {
    	this.countryId = countryId;
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
    
}
