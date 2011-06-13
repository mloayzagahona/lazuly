package net.sf.mp.demo.conference.conference;

import java.sql.*;
import java.util.Date;
import java.util.List;
import java.util.ArrayList;
import java.util.Set;
import java.util.HashSet;

import javax.persistence.*;

import net.sf.mp.demo.conference.conference.ConferenceMember;
import net.sf.mp.demo.conference.conference.Sponsor;
import net.sf.mp.demo.conference.conference.Presentation;

/**
 *
 * <p>Title: Speaker</p>
 *
 * <p>Description: Domain Object describing a Speaker entity</p>
 *
 */
@Entity (name="Speaker")
@Table (name="speaker")
public class Speaker {

    @Id @Column(name="id" )
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name="bio",   nullable=false,  unique=false)
    private String bio; 
    @Column(name="photo",    nullable=true,  unique=false)
    private String photo; 
    @Column(name="web_site_url",  length=255,  nullable=true,  unique=false)
    private String webSiteUrl; 

    @ManyToOne (fetch=FetchType.LAZY , optional=false)
    @JoinColumn(name="conference_member_id", referencedColumnName = "id", nullable=false,  unique=false ) 
    private ConferenceMember conferenceMemberId;
    
    @ManyToOne (fetch=FetchType.LAZY )
    @JoinColumn(name="sponsor_id", referencedColumnName = "id",  nullable=true,  unique=false ) 
    private Sponsor sponsorId;
    
    @ManyToMany
    @JoinTable(name="SPEAKER_PRESENTATION", 
        joinColumns=@JoinColumn(name="speaker_id"), 
        inverseJoinColumns=@JoinColumn(name="presentation_id") 
    )
    private Set <Presentation> presentations = new HashSet <Presentation> ();

    /**
    * Default constructor
    */
    public Speaker() {
    }

    public Long getId() {
        return id;
    }
	
    public void setId (Long id) {
        this.id =  id;
    }
    
    public String getBio() {
        return bio;
    }
	
    public void setBio (String bio) {
        this.bio =  bio;
    }
    
    public String getPhoto() {
        return photo;
    }
	
    public void setPhoto (String photo) {
        this.photo =  photo;
    }
    
    public String getWebSiteUrl() {
        return webSiteUrl;
    }
	
    public void setWebSiteUrl (String webSiteUrl) {
        this.webSiteUrl =  webSiteUrl;
    }
    
    public ConferenceMember getConferenceMemberId () {
    	return conferenceMemberId;
    }
	
    public void setConferenceMemberId (ConferenceMember conferenceMemberId) {
    	this.conferenceMemberId = conferenceMemberId;
    }
    
    public Sponsor getSponsorId () {
    	return sponsorId;
    }
	
    public void setSponsorId (Sponsor sponsorId) {
    	this.sponsorId = sponsorId;
    }
    
    public Set<Presentation> getPresentations() {
        if (presentations == null){
            presentations = new HashSet<Presentation>();
        }
        return presentations;
    }

    public void setPresentations (Set<Presentation> presentations) {
        this.presentations = presentations;
    }
    	
    public void addPresentations (Presentation presentations) {
        getPresentations().add(presentations);
    }	    
}
