package net.sf.mp.demo.conference.domain.conference;

import java.sql.*;
import java.util.Date;
import java.util.List;
import java.util.ArrayList;
import java.util.Set;
import java.util.HashSet;

import javax.persistence.*;
import org.openxava.annotations.*;

import net.sf.mp.demo.conference.domain.conference.Evaluation;
import net.sf.mp.demo.conference.domain.conference.PresentationPlace;
import net.sf.mp.demo.conference.domain.conference.Speaker;

/**
 *
 * <p>Title: Presentation</p>
 *
 * <p>Description: Domain Object describing a Presentation entity</p>
 *
 */
@Entity (name="Presentation")
@Table (name="presentation")
@Views({
	@View(
		name="base",
		members=
        "" 	
        + "startTime  ; "
        + "endTime  ; "
        + "abstractName  ; "
        + "title  ; "
        + "status  ; "
        + "proposalTime  ; "
	    + "presentationPlaceId;"  
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
	    + "evaluations { evaluations };" //+ "speakerPresentationPresentationIds { speakerPresentationPresentationIds };" 
	    + "speakers;"
	),
	@View(extendsView="base",
        members=
          "" 	
	    + "evaluations { evaluations };" //+ "speakerPresentationPresentationIds { speakerPresentationPresentationIds };" 
	    + "speakers;"
	),
    @View(name="presentationDEFAULT_VIEW", 
	   members=
          " id ;" 	
        + "startTime  ; "
        + "endTime  ; "
        + "abstractName  ; "
        + "title  ; "
        + "status  ; "
        + "proposalTime  ; "
	)
})

@Tabs({
@Tab(
properties=
     " startTime "
    +",  endTime "
    +",  abstractName "
    +",  title "
    +",  status "
    +",  proposalTime "
)
,
@Tab(
name = "PresentationTab",
properties=
     " startTime "
    +",  endTime "
    +",  abstractName "
    +",  title "
    +",  status "
    +",  proposalTime "
)
,
@Tab(
name = "PresentationTabWithRef",
properties=
     " startTime "
    +",  endTime "
    +",  abstractName "
    +",  title "
    +",  status "
    +",  proposalTime "
)
})

public class Presentation {

    @Hidden @Id @Column(name="id" )
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id; 

    @Column(name="start_time",   nullable=false,  unique=false)
    @Required
    private Timestamp startTime; 
    @Column(name="end_time",   nullable=false,  unique=false)
    @Required
    private Timestamp endTime; 
    @Column(name="abstract",  length=500, nullable=false,  unique=false)
    @Required
    private String abstractName; 
    @Column(name="title",  length=255, nullable=false,  unique=false)
    @Required
    private String title; 
    @Column(name="status",  length=15, nullable=false,  unique=false)
    @Required
    @Editor(forViews="base,Create,Update,DEFAULT,presentationDEFAULT_VIEW", value="PresentationStatusComboEditor")
    private String status; 
    @Column(name="proposal_time",    nullable=true,  unique=false)
    private Timestamp proposalTime; 

    @ManyToOne (fetch=FetchType.LAZY ) //remove optional=false to aggragate but leads to a side effect when going directly to the entity: required check is not performed=> if no set DB check constraint is raised...
    @JoinColumn(name="presentation_place_id",  nullable=true,  unique=false  )
    @ReferenceView ("presentationPlaceDEFAULT_VIEW")  
    private PresentationPlace presentationPlaceId; 
    

    @OneToMany (targetEntity=net.sf.mp.demo.conference.domain.conference.Evaluation.class, fetch=FetchType.LAZY, mappedBy="presentationId", cascade=CascadeType.REMOVE)//, cascade=CascadeType.ALL)
    @ListProperties(""+
       "star ,comment ,evaluationDate ,conferenceMemberId.firstName   ")
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
    public String getStatus() {
        return status;
    }
	
    public void setStatus (String status) {
        this.status =  status;
    }
    public Timestamp getProposalTime() {
        return proposalTime;
    }
	
    public void setProposalTime (Timestamp proposalTime) {
        this.proposalTime =  proposalTime;
    }

    public PresentationPlace getPresentationPlaceId () {  //
    	return presentationPlaceId;
    }
	
    public void setPresentationPlaceId (PresentationPlace presentationPlaceId) {
    	this.presentationPlaceId = presentationPlaceId;//this.presentationPlaceId = presentationPlace;
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
