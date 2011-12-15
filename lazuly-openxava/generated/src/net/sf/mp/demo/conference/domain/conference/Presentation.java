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
        + "id  ; "
        + "startTime  ; "
        + "endTime  ; "
        + "abstract_Name  ; "
        + "title  ; "
        + "status  ; "
        + "presentationPlaceId  ; "
        + "proposalTime  ; "
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
	    + "evaluations { evaluations };" //+ "${link}${columnclass}s { ${link}${columnclass}s };" 
	    + "speakers;"
	),
	@View(extendsView="base",
        members=
          "" 	
	    + "evaluations { evaluations };" //+ "${link}${columnclass}s { ${link}${columnclass}s };" 
	    + "speakers;"
	),
    @View(name="presentationDEFAULT_VIEW", 
	   members=
          " id ;" 	
        + "startTime  ; "
        + "endTime  ; "
        + "abstract_Name  ; "
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
//MP-MANAGED-ADDED-AREA-BEGINNING @class-annotation@
//MP-MANAGED-ADDED-AREA-ENDING @class-annotation@
public class Presentation {

    @Hidden @Id @Column(name="id" )
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id; 

//MP-MANAGED-ADDED-AREA-BEGINNING @start_time-field-annotation@
//MP-MANAGED-ADDED-AREA-ENDING @start_time-field-annotation@

//MP-MANAGED-UPDATABLE-BEGINNING-DISABLE @ATTRIBUTE-start_time@
    @Column(name="start_time",   nullable=false,  unique=false)
    @Required
    private Timestamp startTime;	
//MP-MANAGED-UPDATABLE-ENDING

//MP-MANAGED-ADDED-AREA-BEGINNING @end_time-field-annotation@
//MP-MANAGED-ADDED-AREA-ENDING @end_time-field-annotation@

//MP-MANAGED-UPDATABLE-BEGINNING-DISABLE @ATTRIBUTE-end_time@
    @Column(name="end_time",   nullable=false,  unique=false)
    @Required
    private Timestamp endTime;	
//MP-MANAGED-UPDATABLE-ENDING

//MP-MANAGED-ADDED-AREA-BEGINNING @abstract-field-annotation@
//MP-MANAGED-ADDED-AREA-ENDING @abstract-field-annotation@

//MP-MANAGED-UPDATABLE-BEGINNING-DISABLE @ATTRIBUTE-abstract@
    @Column(name="abstract",  length=500, nullable=false,  unique=false)
    @Required
    private String abstract_Name;	
//MP-MANAGED-UPDATABLE-ENDING

//MP-MANAGED-ADDED-AREA-BEGINNING @title-field-annotation@
//MP-MANAGED-ADDED-AREA-ENDING @title-field-annotation@

//MP-MANAGED-UPDATABLE-BEGINNING-DISABLE @ATTRIBUTE-title@
    @Column(name="title",  length=255, nullable=false,  unique=false)
    @Required
    private String title;	
//MP-MANAGED-UPDATABLE-ENDING

//MP-MANAGED-ADDED-AREA-BEGINNING @status-field-annotation@
//MP-MANAGED-ADDED-AREA-ENDING @status-field-annotation@

//MP-MANAGED-UPDATABLE-BEGINNING-DISABLE @ATTRIBUTE-status@
    @Column(name="status",  length=15, nullable=false,  unique=false)
    @Required
    @Editor(forViews="base,Create,Update,DEFAULT,presentationDEFAULT_VIEW", value="PresentationStatusComboEditor")
    private String status;	
//MP-MANAGED-UPDATABLE-ENDING

//MP-MANAGED-ADDED-AREA-BEGINNING @proposal_time-field-annotation@
//MP-MANAGED-ADDED-AREA-ENDING @proposal_time-field-annotation@

//MP-MANAGED-UPDATABLE-BEGINNING-DISABLE @ATTRIBUTE-proposal_time@
    @Column(name="proposal_time",    nullable=true,  unique=false)
    private Timestamp proposalTime;	
//MP-MANAGED-UPDATABLE-ENDING


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
    

//MP-MANAGED-UPDATABLE-BEGINNING-DISABLE @GETTER-SETTER-start_time@
    public Timestamp getStartTime() {
        return startTime;
    }
	
    public void setStartTime (Timestamp startTime) {
        this.startTime =  startTime;
    } 
//MP-MANAGED-UPDATABLE-ENDING

//MP-MANAGED-UPDATABLE-BEGINNING-DISABLE @GETTER-SETTER-end_time@
    public Timestamp getEndTime() {
        return endTime;
    }
	
    public void setEndTime (Timestamp endTime) {
        this.endTime =  endTime;
    } 
//MP-MANAGED-UPDATABLE-ENDING

//MP-MANAGED-UPDATABLE-BEGINNING-DISABLE @GETTER-SETTER-abstract@
    public String getAbstract_Name() {
        return abstract_Name;
    }
	
    public void setAbstract_Name (String abstract_Name) {
        this.abstract_Name =  abstract_Name;
    } 
//MP-MANAGED-UPDATABLE-ENDING

//MP-MANAGED-UPDATABLE-BEGINNING-DISABLE @GETTER-SETTER-title@
    public String getTitle() {
        return title;
    }
	
    public void setTitle (String title) {
        this.title =  title;
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

//MP-MANAGED-UPDATABLE-BEGINNING-DISABLE @GETTER-SETTER-proposal_time@
    public Timestamp getProposalTime() {
        return proposalTime;
    }
	
    public void setProposalTime (Timestamp proposalTime) {
        this.proposalTime =  proposalTime;
    } 
//MP-MANAGED-UPDATABLE-ENDING


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



//MP-MANAGED-ADDED-AREA-BEGINNING @implementation@
//MP-MANAGED-ADDED-AREA-ENDING @implementation@

}
