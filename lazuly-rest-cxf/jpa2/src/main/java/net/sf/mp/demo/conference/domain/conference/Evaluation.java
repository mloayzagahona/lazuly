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
import net.sf.mp.demo.conference.domain.conference.ConferenceMember;
import net.sf.mp.demo.conference.domain.conference.Presentation;

/**
 *
 * <p>Title: Evaluation</p>
 *
 * <p>Description: Domain Object describing a Evaluation entity</p>
 *
 */
@Entity (name="Evaluation")
@Table (name="evaluation")
@NamedQueries({
	 @NamedQuery(name="Evaluation.findAll", query="SELECT evaluation FROM Evaluation evaluation")
	,@NamedQuery(name="Evaluation.findByStar", query="SELECT evaluation FROM Evaluation evaluation WHERE evaluation.star = :star")
	,@NamedQuery(name="Evaluation.findByComment", query="SELECT evaluation FROM Evaluation evaluation WHERE evaluation.comment = :comment")
	,@NamedQuery(name="Evaluation.findByCommentContaining", query="SELECT evaluation FROM Evaluation evaluation WHERE evaluation.comment like :comment")
	,@NamedQuery(name="Evaluation.findByEvaluationDate", query="SELECT evaluation FROM Evaluation evaluation WHERE evaluation.evaluationDate = :evaluationDate")
})
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(namespace="net.sf.mp.demo.conference.domain.conference", name = "Evaluation")
@XmlRootElement(namespace="net.sf.mp.demo.conference.domain.conference")
public class Evaluation extends AbstractDomainObject {
    private static final long serialVersionUID = 1L;
	
    public  static final String FIND_ALL = "Evaluation.findAll";
    public static final String FIND_BY_STAR = "Evaluation.findByStar";
    public static final String FIND_BY_COMMENT = "Evaluation.findByComment";
    public static final String FIND_BY_COMMENT_CONTAINING ="Evaluation.findByCommentContaining";
    public static final String FIND_BY_EVALUATIONDATE = "Evaluation.findByEvaluationDate";
	
    @Id @Column(name="id" )
    @GeneratedValue(strategy = GenerationType.AUTO)
    @XmlElement (name="id")
    private Long id;

//MP-MANAGED-ADDED-AREA-BEGINNING @star-field-annotation@
//MP-MANAGED-ADDED-AREA-ENDING @star-field-annotation@
//MP-MANAGED-UPDATABLE-BEGINNING-DISABLE @ATTRIBUTE-star@
    @Column(name="star",   nullable=false,  unique=false)
    @XmlElement (name="star")
    private Integer star; 
//MP-MANAGED-UPDATABLE-ENDING

//MP-MANAGED-ADDED-AREA-BEGINNING @comment-field-annotation@
//MP-MANAGED-ADDED-AREA-ENDING @comment-field-annotation@
//MP-MANAGED-UPDATABLE-BEGINNING-DISABLE @ATTRIBUTE-comment@
    @Column(name="comment",  length=500,  nullable=true,  unique=false)
    @XmlElement (name="comment")
    private String comment; 
//MP-MANAGED-UPDATABLE-ENDING

//MP-MANAGED-ADDED-AREA-BEGINNING @evaluation_date-field-annotation@
//MP-MANAGED-ADDED-AREA-ENDING @evaluation_date-field-annotation@
//MP-MANAGED-UPDATABLE-BEGINNING-DISABLE @ATTRIBUTE-evaluation_date@
    @Column(name="evaluation_date",    nullable=true,  unique=false)
    @XmlElement (name="evaluation-date")
    @Temporal(TemporalType.TIMESTAMP)
    private java.util.Date evaluationDate; 
//MP-MANAGED-UPDATABLE-ENDING

    @ManyToOne (fetch=FetchType.LAZY , optional=false)
    @JoinColumn(name="conference_member_id", referencedColumnName = "id", nullable=false,  unique=false ) 
    private ConferenceMember conferenceMemberId;  

    @XmlAttribute (name="conference-member-id")
    @Column(name="conference_member_id",  nullable=false,  unique=false, insertable=false, updatable=false)
    private Long conferenceMemberId_;
	
    @ManyToOne (fetch=FetchType.LAZY , optional=false)
    @JoinColumn(name="presentation_id", referencedColumnName = "id", nullable=false,  unique=false ) 
    private Presentation presentationId;  

    @XmlAttribute (name="presentation-id")
    @Column(name="presentation_id",  nullable=false,  unique=false, insertable=false, updatable=false)
    private Long presentationId_;
	
    /**
    * Default constructor
    */
    public Evaluation() {
    }

	/**
	* All field constructor 
	*/
    public Evaluation(
       Long id,
       Long conferenceMemberId,
       Long presentationId,
       Integer star,
       String comment,
       java.util.Date evaluationDate) {
       //primary keys
       setId (id);
       //attributes
       setStar (star);
       setComment (comment);
       setEvaluationDate (evaluationDate);
       //parents
       this.conferenceMemberId = new ConferenceMember();
       this.conferenceMemberId.setId(conferenceMemberId); //ConferenceMember Column [name=id; type=BIGINT] - local Evaluation Column [name=conference_member_id; type=BIGINT]
       this.presentationId = new Presentation();
       this.presentationId.setId(presentationId); //Presentation Column [name=id; type=BIGINT] - local Evaluation Column [name=presentation_id; type=BIGINT]
    }

	public Evaluation flat() {
	   return new Evaluation(
          getId(),
          getConferenceMemberId_(),
          getPresentationId_(),
          getStar(),
          getComment(),
          getEvaluationDate()
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
            .append("conferenceMemberId", conferenceMemberId)
            .append("presentationId", presentationId)
            .append("star", star)
            .append("comment", comment)
            .append("evaluationDate", evaluationDate)
	 	    ;
	} 
		
	public String toString(Object object) {
	 	return getToStringBuilder(object).toString();
	} 
	
	public String toStringWithParents() {
	    ToStringBuilder toStringBuilder = getToStringBuilder(this);
        if (conferenceMemberId!=null)
            toStringBuilder.append("conferenceMemberId", conferenceMemberId.toStringWithParents());
        if (presentationId!=null)
            toStringBuilder.append("presentationId", presentationId.toStringWithParents());
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
		if (!(object instanceof Evaluation)) return false;
		Evaluation evaluation = (Evaluation) object;
		if (evaluation.id==null || !evaluation.id.equals(id)) return false;
		return true;
	}    

	public Evaluation clone() {
        Evaluation evaluation = flat();
        if (getConferenceMemberId()!=null) 
            evaluation.setConferenceMemberId (getConferenceMemberId().clone());   
        if (getPresentationId()!=null) 
            evaluation.setPresentationId (getPresentationId().clone());   
        return evaluation;
	}
	
	public void copy (Evaluation evaluation) {
		if (evaluation!=null) {
			setId (evaluation.getId());
			setConferenceMemberId (evaluation.getConferenceMemberId());
			setPresentationId (evaluation.getPresentationId());
			setStar (evaluation.getStar());
			setComment (evaluation.getComment());
			setEvaluationDate (evaluation.getEvaluationDate());
		}
	}
	
	public static Evaluation fullMask() {
		return new Evaluation(
			longMask__ ,
			longMask__ ,
			longMask__ ,
			integerMask__ ,
			stringMask__ ,
			timestampMask__ 
		);	    
	}

    public Long getId() {
        return id;
    }
	
    public void setId (Long id) {
        this.id =  id;
    }
    
//MP-MANAGED-UPDATABLE-BEGINNING-DISABLE @GETTER-SETTER-star@
    public Integer getStar() {
        return star;
    }
	
    public void setStar (Integer star) {
        this.star =  star;
    }    
//MP-MANAGED-UPDATABLE-ENDING

//MP-MANAGED-UPDATABLE-BEGINNING-DISABLE @GETTER-SETTER-comment@
    public String getComment() {
        return comment;
    }
	
    public void setComment (String comment) {
        this.comment =  comment;
    }    
//MP-MANAGED-UPDATABLE-ENDING

//MP-MANAGED-UPDATABLE-BEGINNING-DISABLE @GETTER-SETTER-evaluation_date@
    public java.util.Date getEvaluationDate() {
        return evaluationDate;
    }
	
    public void setEvaluationDate (java.util.Date evaluationDate) {
        this.evaluationDate =  evaluationDate;
    }    
//MP-MANAGED-UPDATABLE-ENDING


    public ConferenceMember getConferenceMemberId () {
    	return conferenceMemberId;
    }
	
    public void setConferenceMemberId (ConferenceMember conferenceMemberId) {
    	this.conferenceMemberId = conferenceMemberId;
    }

    public Long getConferenceMemberId_() {
        return conferenceMemberId_;
    }
	
    public void setConferenceMemberId_ (Long conferenceMemberId) {
        this.conferenceMemberId_ =  conferenceMemberId;
    }
	
    public Presentation getPresentationId () {
    	return presentationId;
    }
	
    public void setPresentationId (Presentation presentationId) {
    	this.presentationId = presentationId;
    }

    public Long getPresentationId_() {
        return presentationId_;
    }
	
    public void setPresentationId_ (Long presentationId) {
        this.presentationId_ =  presentationId;
    }
	



//MP-MANAGED-ADDED-AREA-BEGINNING @implementation@
//MP-MANAGED-ADDED-AREA-ENDING @implementation@

}
