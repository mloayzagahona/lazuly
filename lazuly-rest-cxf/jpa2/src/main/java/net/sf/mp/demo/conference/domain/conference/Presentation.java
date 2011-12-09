package net.sf.mp.demo.conference.domain.conference;

//MP-MANAGED-ADDED-AREA-BEGINNING @import@
//MP-MANAGED-ADDED-AREA-ENDING @import@
import java.sql.*;
import java.util.Date;
import java.util.List;
import java.util.ArrayList;
import java.util.Set;
import java.util.HashSet;

import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;
import net.sf.minuteProject.architecture.bsla.domain.AbstractDomainObject;
import java.io.Serializable;
import javax.persistence.*;
import javax.xml.bind.annotation.*;
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
@NamedQueries({
	 @NamedQuery(name="Presentation.findAll", query="SELECT presentation FROM Presentation presentation")
	,@NamedQuery(name="Presentation.findByStartTime", query="SELECT presentation FROM Presentation presentation WHERE presentation.startTime = :startTime")
	,@NamedQuery(name="Presentation.findByEndTime", query="SELECT presentation FROM Presentation presentation WHERE presentation.endTime = :endTime")
	,@NamedQuery(name="Presentation.findByAbstractName", query="SELECT presentation FROM Presentation presentation WHERE presentation.abstractName = :abstractName")
	,@NamedQuery(name="Presentation.findByAbstractNameContaining", query="SELECT presentation FROM Presentation presentation WHERE presentation.abstractName like :abstractName")
	,@NamedQuery(name="Presentation.findByTitle", query="SELECT presentation FROM Presentation presentation WHERE presentation.title = :title")
	,@NamedQuery(name="Presentation.findByTitleContaining", query="SELECT presentation FROM Presentation presentation WHERE presentation.title like :title")
	,@NamedQuery(name="Presentation.findByStatus", query="SELECT presentation FROM Presentation presentation WHERE presentation.status = :status")
	,@NamedQuery(name="Presentation.findByStatusContaining", query="SELECT presentation FROM Presentation presentation WHERE presentation.status like :status")
	,@NamedQuery(name="Presentation.findByProposalTime", query="SELECT presentation FROM Presentation presentation WHERE presentation.proposalTime = :proposalTime")
})
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(namespace="net.sf.mp.demo.conference.domain.conference", name = "Presentation")
@XmlRootElement(namespace="net.sf.mp.demo.conference.domain.conference")
public class Presentation extends AbstractDomainObject {
    private static final long serialVersionUID = 1L;
	
    public  static final String FIND_ALL = "Presentation.findAll";
    public static final String FIND_BY_STARTTIME = "Presentation.findByStartTime";
    public static final String FIND_BY_ENDTIME = "Presentation.findByEndTime";
    public static final String FIND_BY_ABSTRACTNAME = "Presentation.findByAbstractName";
    public static final String FIND_BY_ABSTRACTNAME_CONTAINING ="Presentation.findByAbstractNameContaining";
    public static final String FIND_BY_TITLE = "Presentation.findByTitle";
    public static final String FIND_BY_TITLE_CONTAINING ="Presentation.findByTitleContaining";
    public static final String FIND_BY_STATUS = "Presentation.findByStatus";
    public static final String FIND_BY_STATUS_CONTAINING ="Presentation.findByStatusContaining";
    public static final String FIND_BY_PROPOSALTIME = "Presentation.findByProposalTime";
	
    @Id @Column(name="id" )
    @GeneratedValue(strategy = GenerationType.AUTO)
    @XmlElement (name="id")
    private Long id;

//MP-MANAGED-ADDED-AREA-BEGINNING @start_time-field-annotation@
//MP-MANAGED-ADDED-AREA-ENDING @start_time-field-annotation@
//MP-MANAGED-UPDATABLE-BEGINNING-DISABLE @ATTRIBUTE-start_time@
    @Column(name="start_time",   nullable=false,  unique=false)
    @XmlElement (name="start-time")
    @Temporal(TemporalType.TIMESTAMP)
    private java.util.Date startTime; 
//MP-MANAGED-UPDATABLE-ENDING

//MP-MANAGED-ADDED-AREA-BEGINNING @end_time-field-annotation@
//MP-MANAGED-ADDED-AREA-ENDING @end_time-field-annotation@
//MP-MANAGED-UPDATABLE-BEGINNING-DISABLE @ATTRIBUTE-end_time@
    @Column(name="end_time",   nullable=false,  unique=false)
    @XmlElement (name="end-time")
    @Temporal(TemporalType.TIMESTAMP)
    private java.util.Date endTime; 
//MP-MANAGED-UPDATABLE-ENDING

//MP-MANAGED-ADDED-AREA-BEGINNING @abstract-field-annotation@
//MP-MANAGED-ADDED-AREA-ENDING @abstract-field-annotation@
//MP-MANAGED-UPDATABLE-BEGINNING-DISABLE @ATTRIBUTE-abstract@
    @Column(name="abstract",  length=500, nullable=false,  unique=false)
    @XmlElement (name="abstract")
    private String abstractName; 
//MP-MANAGED-UPDATABLE-ENDING

//MP-MANAGED-ADDED-AREA-BEGINNING @title-field-annotation@
//MP-MANAGED-ADDED-AREA-ENDING @title-field-annotation@
//MP-MANAGED-UPDATABLE-BEGINNING-DISABLE @ATTRIBUTE-title@
    @Column(name="title",  length=255, nullable=false,  unique=false)
    @XmlElement (name="title")
    private String title; 
//MP-MANAGED-UPDATABLE-ENDING

//MP-MANAGED-ADDED-AREA-BEGINNING @status-field-annotation@
//MP-MANAGED-ADDED-AREA-ENDING @status-field-annotation@
//MP-MANAGED-UPDATABLE-BEGINNING-DISABLE @ATTRIBUTE-status@
    @Column(name="status",  length=15, nullable=false,  unique=false)
    @XmlElement (name="status")
    private String status; 
//MP-MANAGED-UPDATABLE-ENDING

//MP-MANAGED-ADDED-AREA-BEGINNING @proposal_time-field-annotation@
//MP-MANAGED-ADDED-AREA-ENDING @proposal_time-field-annotation@
//MP-MANAGED-UPDATABLE-BEGINNING-DISABLE @ATTRIBUTE-proposal_time@
    @Column(name="proposal_time",    nullable=true,  unique=false)
    @XmlElement (name="proposal-time")
    @Temporal(TemporalType.TIMESTAMP)
    private java.util.Date proposalTime; 
//MP-MANAGED-UPDATABLE-ENDING

    @ManyToOne (fetch=FetchType.LAZY )
    @JoinColumn(name="presentation_place_id", referencedColumnName = "id",  nullable=true,  unique=false ) 
    private PresentationPlace presentationPlaceId;  

    @XmlAttribute (name="presentation-place-id")
    @Column(name="presentation_place_id",   nullable=true,  unique=false, insertable=false, updatable=false)
    private Long presentationPlaceId_;
	
//MP-MANAGED-UPDATABLE-BEGINNING-DISABLE @evaluationPresentationViaPresentationId-field-presentation@
    @XmlElement (name="evaluationPresentationViaPresentationId")
    @OneToMany (targetEntity=net.sf.mp.demo.conference.domain.conference.Evaluation.class, fetch=FetchType.LAZY, mappedBy="presentationId", cascade=CascadeType.REMOVE)//, cascade=CascadeType.ALL)
    private Set <Evaluation> evaluationPresentationViaPresentationId = new HashSet<Evaluation>(); 
   
//MP-MANAGED-UPDATABLE-ENDING
//MP-MANAGED-UPDATABLE-BEGINNING-DISABLE @speakerPresentationPresentationViaPresentationId-field-presentation@
//MP-MANAGED-UPDATABLE-ENDING
    @XmlTransient
    @ManyToMany
    @JoinTable(name="SPEAKER_PRESENTATION", 
        joinColumns=@JoinColumn(name="presentation_id"), 
        inverseJoinColumns=@JoinColumn(name="speaker_id") 
    )
    private Set <Speaker> speakerSpeakerPresentationViaId = new HashSet <Speaker> ();

    /**
    * Default constructor
    */
    public Presentation() {
    }

	/**
	* All field constructor 
	*/
    public Presentation(
       Long id,
       java.util.Date startTime,
       java.util.Date endTime,
       String abstractName,
       String title,
       String status,
       Long presentationPlaceId,
       java.util.Date proposalTime) {
       //primary keys
       setId (id);
       //attributes
       setStartTime (startTime);
       setEndTime (endTime);
       setAbstractName (abstractName);
       setTitle (title);
       setStatus (status);
       setProposalTime (proposalTime);
       //parents
       this.presentationPlaceId = new PresentationPlace();
       this.presentationPlaceId.setId(presentationPlaceId); //PresentationPlace Column [name=id; type=BIGINT] - local Presentation Column [name=presentation_place_id; type=BIGINT]
    }

	public Presentation flat() {
	   return new Presentation(
          getId(),
          getStartTime(),
          getEndTime(),
          getAbstractName(),
          getTitle(),
          getStatus(),
          getPresentationPlaceId_(),
          getProposalTime()
	   );
	}

    /**
    * toString implementation
    */
	public String toString() {
		return toString(this);
	}

	public ToStringBuilder getToStringBuilder(Object object) {
	 	return new ToStringBuilder(this, ToStringStyle.SHORT_PREFIX_STYLE)
            .append("id", id)
            .append("startTime", startTime)
            .append("endTime", endTime)
            .append("abstractName", abstractName)
            .append("title", title)
            .append("status", status)
            .append("presentationPlaceId", presentationPlaceId)
            .append("proposalTime", proposalTime)
	 	    ;
	} 
		
	public String toString(Object object) {
	 	return getToStringBuilder(object).toString();
	} 
	
	public String toStringWithParents() {
	    ToStringBuilder toStringBuilder = getToStringBuilder(this);
        if (presentationPlaceId!=null)
            toStringBuilder.append("presentationPlaceId", presentationPlaceId.toStringWithParents());
	 	return toStringBuilder.toString();	
	}
	/**
    * hashCode implementation
    */
	public int hashCode() {
		return HashCodeBuilder.reflectionHashCode(flat());
	}
	
	/**
    * equals implementation

	public boolean equals(Object object) {
		return super.toEquals(flat(), object);
	}
    */
	public boolean equals(Object object) {
		if (object == null) return false;	
		if (object == this) return true;
		if (!(object instanceof Presentation)) return false;
		Presentation presentation = (Presentation) object;
		if (presentation.id==null || !presentation.id.equals(id)) return false;
		return true;
	}    

	public Presentation clone() {
        Presentation presentation = flat();
        if (getPresentationPlaceId()!=null) 
            presentation.setPresentationPlaceId (getPresentationPlaceId().clone());   
        return presentation;
	}
	
	public void copy (Presentation presentation) {
		if (presentation!=null) {
			setId (presentation.getId());
			setStartTime (presentation.getStartTime());
			setEndTime (presentation.getEndTime());
			setAbstractName (presentation.getAbstractName());
			setTitle (presentation.getTitle());
			setStatus (presentation.getStatus());
			setPresentationPlaceId (presentation.getPresentationPlaceId());
			setProposalTime (presentation.getProposalTime());
		}
	}
	
	public static Presentation fullMask() {
		return new Presentation(
			longMask__ ,
			timestampMask__ ,
			timestampMask__ ,
			stringMask__ ,
			stringMask__ ,
			stringMask__ ,
			longMask__ ,
			timestampMask__ 
		);	    
	}

    public Long getId() {
        return id;
    }
	
    public void setId (Long id) {
        this.id =  id;
    }
    
//MP-MANAGED-UPDATABLE-BEGINNING-DISABLE @GETTER-SETTER-start_time@
    public java.util.Date getStartTime() {
        return startTime;
    }
	
    public void setStartTime (java.util.Date startTime) {
        this.startTime =  startTime;
    }    
//MP-MANAGED-UPDATABLE-ENDING

//MP-MANAGED-UPDATABLE-BEGINNING-DISABLE @GETTER-SETTER-end_time@
    public java.util.Date getEndTime() {
        return endTime;
    }
	
    public void setEndTime (java.util.Date endTime) {
        this.endTime =  endTime;
    }    
//MP-MANAGED-UPDATABLE-ENDING

//MP-MANAGED-UPDATABLE-BEGINNING-DISABLE @GETTER-SETTER-abstract@
    public String getAbstractName() {
        return abstractName;
    }
	
    public void setAbstractName (String abstractName) {
        this.abstractName =  abstractName;
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
    public java.util.Date getProposalTime() {
        return proposalTime;
    }
	
    public void setProposalTime (java.util.Date proposalTime) {
        this.proposalTime =  proposalTime;
    }    
//MP-MANAGED-UPDATABLE-ENDING


    public PresentationPlace getPresentationPlaceId () {
    	return presentationPlaceId;
    }
	
    public void setPresentationPlaceId (PresentationPlace presentationPlaceId) {
    	this.presentationPlaceId = presentationPlaceId;
    }

    public Long getPresentationPlaceId_() {
        return presentationPlaceId_;
    }
	
    public void setPresentationPlaceId_ (Long presentationPlaceId) {
        this.presentationPlaceId_ =  presentationPlaceId;
    }
	

//MP-MANAGED-UPDATABLE-BEGINNING-DISABLE @evaluationPresentationViaPresentationId-getter-presentation@
    public Set<Evaluation> getEvaluationPresentationViaPresentationId() {
        if (evaluationPresentationViaPresentationId == null){
            evaluationPresentationViaPresentationId = new HashSet<Evaluation>();
        }
        return evaluationPresentationViaPresentationId;
    }

    public void setEvaluationPresentationViaPresentationId (Set<Evaluation> evaluationPresentationViaPresentationId) {
        this.evaluationPresentationViaPresentationId = evaluationPresentationViaPresentationId;
    }	
    
    public void addEvaluationPresentationViaPresentationId (Evaluation evaluation) {
    	    getEvaluationPresentationViaPresentationId().add(evaluation);
    }
    
//MP-MANAGED-UPDATABLE-ENDING
//MP-MANAGED-UPDATABLE-BEGINNING-DISABLE @speakerPresentationPresentationViaPresentationId-getter-presentation@
//MP-MANAGED-UPDATABLE-ENDING

    public Set<Speaker> getSpeakerSpeakerPresentationViaId() {
        if (speakerSpeakerPresentationViaId == null){
            speakerSpeakerPresentationViaId = new HashSet<Speaker>();
        }
        return speakerSpeakerPresentationViaId;
    }

    public void setSpeakerSpeakerPresentationViaId (Set<Speaker> speakerSpeakerPresentationViaId) {
        this.speakerSpeakerPresentationViaId = speakerSpeakerPresentationViaId;
    }
    	
    public void addSpeakerSpeakerPresentationViaId (Speaker speakerSpeakerPresentationViaId) {
        getSpeakerSpeakerPresentationViaId().add(speakerSpeakerPresentationViaId);
    }	    

//MP-MANAGED-ADDED-AREA-BEGINNING @implementation@
//MP-MANAGED-ADDED-AREA-ENDING @implementation@

}
