package net.sf.mp.demo.conference.conference;

import java.sql.*;
import java.util.Date;
import java.util.List;
import java.util.ArrayList;
import java.util.Set;
import java.util.HashSet;

import javax.persistence.*;

import net.sf.mp.demo.conference.conference.Evaluation;
import net.sf.mp.demo.conference.conference.PresentationPlace;
import net.sf.mp.demo.conference.conference.Speaker;
import net.sf.mp.demo.conference.enumeration.conference.PresentationStatusEnum;

/**
 *
 * <p>Title: Presentation</p>
 *
 * <p>Description: Domain Object describing a Presentation entity</p>
 *
 */
@Entity (name="Presentation")
@Table (name="presentation")
public class Presentation {

    @Id @Column(name="id" )
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name="start_time",   nullable=false,  unique=false)
    private Timestamp startTime; 
    @Column(name="end_time",   nullable=false,  unique=false)
    private Timestamp endTime; 
    @Column(name="abstract",  length=500, nullable=false,  unique=false)
    private String abstractName; 
    @Column(name="title",  length=255, nullable=false,  unique=false)
    private String title; 
    @Enumerated
    private PresentationStatusEnum status; 
    @Column(name="proposal_time",    nullable=true,  unique=false)
    private Timestamp proposalTime; 

    @ManyToOne (fetch=FetchType.LAZY )
    @JoinColumn(name="presentation_place_id", referencedColumnName = "id",  nullable=true,  unique=false ) 
    private PresentationPlace presentationPlaceId;
    
    @OneToMany (targetEntity=net.sf.mp.demo.conference.conference.Evaluation.class, fetch=FetchType.LAZY, mappedBy="presentationId", cascade=CascadeType.REMOVE)//, cascade=CascadeType.ALL)
    private Set <Evaluation> evaluations = new HashSet<Evaluation>(); 
   
    @ManyToMany
    @JoinTable(name="SPEAKER_PRESENTATION", 
        joinColumns=@JoinColumn(name="presentation_id"), 
        inverseJoinColumns=@JoinColumn(name="speaker_id") 
    )
    private Set <Speaker> speakers = new HashSet <Speaker> ();

    /**
    * Default constructor
    */
    public Presentation() {
    }

    public Long getId() {
        return id;
    }
	
    public void setId (Long id) {
        this.id =  id;
    }
    
    public Timestamp getStartTime() {
        return startTime;
    }
	
    public void setStartTime (Timestamp startTime) {
        this.startTime =  startTime;
    }
    
    public Timestamp getEndTime() {
        return endTime;
    }
	
    public void setEndTime (Timestamp endTime) {
        this.endTime =  endTime;
    }
    
    public String getAbstractName() {
        return abstractName;
    }
	
    public void setAbstractName (String abstractName) {
        this.abstractName =  abstractName;
    }
    
    public String getTitle() {
        return title;
    }
	
    public void setTitle (String title) {
        this.title =  title;
    }
    
    public PresentationStatusEnum getStatus() {
        return status;
    }
	
    public void setStatus (PresentationStatusEnum status) {
        this.status =  status;
    }
    
    public Timestamp getProposalTime() {
        return proposalTime;
    }
	
    public void setProposalTime (Timestamp proposalTime) {
        this.proposalTime =  proposalTime;
    }
    
    public PresentationPlace getPresentationPlaceId () {
    	return presentationPlaceId;
    }
	
    public void setPresentationPlaceId (PresentationPlace presentationPlaceId) {
    	this.presentationPlaceId = presentationPlaceId;
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
    	
    public void addSpeakers (Speaker speakers) {
        getSpeakers().add(speakers);
    }	    
}
