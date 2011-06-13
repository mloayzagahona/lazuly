package net.sf.mp.demo.conference.conference;

import java.sql.*;
import java.util.Date;
import java.util.List;
import java.util.ArrayList;
import java.util.Set;
import java.util.HashSet;

import javax.persistence.*;

import net.sf.mp.demo.conference.conference.ConferenceMember;
import net.sf.mp.demo.conference.conference.Presentation;

/**
 *
 * <p>Title: Evaluation</p>
 *
 * <p>Description: Domain Object describing a Evaluation entity</p>
 *
 */
@Entity (name="Evaluation")
@Table (name="evaluation")
public class Evaluation {

    @Id @Column(name="id" )
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name="star",   nullable=false,  unique=false)
    private Integer star; 
    @Column(name="comment",  length=500,  nullable=true,  unique=false)
    private String comment; 
    @Column(name="evaluation_date",    nullable=true,  unique=false)
    private Timestamp evaluationDate; 

    @ManyToOne (fetch=FetchType.LAZY , optional=false)
    @JoinColumn(name="conference_member_id", referencedColumnName = "id", nullable=false,  unique=false ) 
    private ConferenceMember conferenceMemberId;
    
    @ManyToOne (fetch=FetchType.LAZY , optional=false)
    @JoinColumn(name="presentation_id", referencedColumnName = "id", nullable=false,  unique=false ) 
    private Presentation presentationId;
    
    /**
    * Default constructor
    */
    public Evaluation() {
    }

    public Long getId() {
        return id;
    }
	
    public void setId (Long id) {
        this.id =  id;
    }
    
    public Integer getStar() {
        return star;
    }
	
    public void setStar (Integer star) {
        this.star =  star;
    }
    
    public String getComment() {
        return comment;
    }
	
    public void setComment (String comment) {
        this.comment =  comment;
    }
    
    public Timestamp getEvaluationDate() {
        return evaluationDate;
    }
	
    public void setEvaluationDate (Timestamp evaluationDate) {
        this.evaluationDate =  evaluationDate;
    }
    
    public ConferenceMember getConferenceMemberId () {
    	return conferenceMemberId;
    }
	
    public void setConferenceMemberId (ConferenceMember conferenceMemberId) {
    	this.conferenceMemberId = conferenceMemberId;
    }
    
    public Presentation getPresentationId () {
    	return presentationId;
    }
	
    public void setPresentationId (Presentation presentationId) {
    	this.presentationId = presentationId;
    }
    
}
