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

/**
 *
 * <p>Title: ConferenceFeedback</p>
 *
 * <p>Description: Domain Object describing a ConferenceFeedback entity</p>
 *
 */
@Entity (name="ConferenceFeedback")
@Table (name="conference_feedback")
public class ConferenceFeedback {

    @Id @Column(name="id" )
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @Column(name="comment",   nullable=false,  unique=false)
    private String comment; 
    @Column(name="feedback_date",   nullable=false,  unique=false)
    private Timestamp feedbackDate; 

    @ManyToOne (fetch=FetchType.LAZY , optional=false)
    @JoinColumn(name="conference_id", referencedColumnName = "id", nullable=false,  unique=false ) 
    private Conference conferenceId;
    
    @ManyToOne (fetch=FetchType.LAZY , optional=false)
    @JoinColumn(name="conference_member_id", referencedColumnName = "id", nullable=false,  unique=false ) 
    private ConferenceMember conferenceMemberId;
    
    /**
    * Default constructor
    */
    public ConferenceFeedback() {
    }

    public Integer getId() {
        return id;
    }
	
    public void setId (Integer id) {
        this.id =  id;
    }
    
    public String getComment() {
        return comment;
    }
	
    public void setComment (String comment) {
        this.comment =  comment;
    }
    
    public Timestamp getFeedbackDate() {
        return feedbackDate;
    }
	
    public void setFeedbackDate (Timestamp feedbackDate) {
        this.feedbackDate =  feedbackDate;
    }
    
    public Conference getConferenceId () {
    	return conferenceId;
    }
	
    public void setConferenceId (Conference conferenceId) {
    	this.conferenceId = conferenceId;
    }
    
    public ConferenceMember getConferenceMemberId () {
    	return conferenceMemberId;
    }
	
    public void setConferenceMemberId (ConferenceMember conferenceMemberId) {
    	this.conferenceMemberId = conferenceMemberId;
    }
    
}
