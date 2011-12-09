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
import net.sf.mp.demo.conference.domain.conference.Conference;
import net.sf.mp.demo.conference.domain.conference.ConferenceMember;

/**
 *
 * <p>Title: ConferenceFeedback</p>
 *
 * <p>Description: Domain Object describing a ConferenceFeedback entity</p>
 *
 */
@Entity (name="ConferenceFeedback")
@Table (name="conference_feedback")
@NamedQueries({
	 @NamedQuery(name="ConferenceFeedback.findAll", query="SELECT conferenceFeedback FROM ConferenceFeedback conferenceFeedback")
	,@NamedQuery(name="ConferenceFeedback.findByComment", query="SELECT conferenceFeedback FROM ConferenceFeedback conferenceFeedback WHERE conferenceFeedback.comment = :comment")
	,@NamedQuery(name="ConferenceFeedback.findByCommentContaining", query="SELECT conferenceFeedback FROM ConferenceFeedback conferenceFeedback WHERE conferenceFeedback.comment like :comment")
	,@NamedQuery(name="ConferenceFeedback.findByFeedbackDate", query="SELECT conferenceFeedback FROM ConferenceFeedback conferenceFeedback WHERE conferenceFeedback.feedbackDate = :feedbackDate")
})
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(namespace="net.sf.mp.demo.conference.domain.conference", name = "ConferenceFeedback")
@XmlRootElement(namespace="net.sf.mp.demo.conference.domain.conference")
public class ConferenceFeedback extends AbstractDomainObject {
    private static final long serialVersionUID = 1L;
	
    public  static final String FIND_ALL = "ConferenceFeedback.findAll";
    public static final String FIND_BY_COMMENT = "ConferenceFeedback.findByComment";
    public static final String FIND_BY_COMMENT_CONTAINING ="ConferenceFeedback.findByCommentContaining";
    public static final String FIND_BY_FEEDBACKDATE = "ConferenceFeedback.findByFeedbackDate";
	
    @Id @Column(name="id" )
    @GeneratedValue(strategy = GenerationType.AUTO)
    @XmlElement (name="id")
    private Integer id;

//MP-MANAGED-ADDED-AREA-BEGINNING @comment-field-annotation@
//MP-MANAGED-ADDED-AREA-ENDING @comment-field-annotation@
//MP-MANAGED-UPDATABLE-BEGINNING-DISABLE @ATTRIBUTE-comment@
    @Column(name="comment",   nullable=false,  unique=false)
    @XmlElement (name="comment")
    private String comment; 
//MP-MANAGED-UPDATABLE-ENDING

//MP-MANAGED-ADDED-AREA-BEGINNING @feedback_date-field-annotation@
//MP-MANAGED-ADDED-AREA-ENDING @feedback_date-field-annotation@
//MP-MANAGED-UPDATABLE-BEGINNING-DISABLE @ATTRIBUTE-feedback_date@
    @Column(name="feedback_date",   nullable=false,  unique=false)
    @XmlElement (name="feedback-date")
    @Temporal(TemporalType.TIMESTAMP)
    private java.util.Date feedbackDate; 
//MP-MANAGED-UPDATABLE-ENDING

    @ManyToOne (fetch=FetchType.LAZY , optional=false)
    @JoinColumn(name="conference_id", referencedColumnName = "id", nullable=false,  unique=false ) 
    private Conference conferenceId;  

    @XmlAttribute (name="conference-id")
    @Column(name="conference_id",  nullable=false,  unique=false, insertable=false, updatable=false)
    private Long conferenceId_;
	
    @ManyToOne (fetch=FetchType.LAZY , optional=false)
    @JoinColumn(name="conference_member_id", referencedColumnName = "id", nullable=false,  unique=false ) 
    private ConferenceMember conferenceMemberId;  

    @XmlAttribute (name="conference-member-id")
    @Column(name="conference_member_id",  nullable=false,  unique=false, insertable=false, updatable=false)
    private Long conferenceMemberId_;
	
    /**
    * Default constructor
    */
    public ConferenceFeedback() {
    }

	/**
	* All field constructor 
	*/
    public ConferenceFeedback(
       Integer id,
       String comment,
       java.util.Date feedbackDate,
       Long conferenceId,
       Long conferenceMemberId) {
       //primary keys
       setId (id);
       //attributes
       setComment (comment);
       setFeedbackDate (feedbackDate);
       //parents
       this.conferenceId = new Conference();
       this.conferenceId.setId(conferenceId); //Conference Column [name=id; type=BIGINT] - local ConferenceFeedback Column [name=conference_id; type=BIGINT]
       this.conferenceMemberId = new ConferenceMember();
       this.conferenceMemberId.setId(conferenceMemberId); //ConferenceMember Column [name=id; type=BIGINT] - local ConferenceFeedback Column [name=conference_member_id; type=BIGINT]
    }

	public ConferenceFeedback flat() {
	   return new ConferenceFeedback(
          getId(),
          getComment(),
          getFeedbackDate(),
          getConferenceId_(),
          getConferenceMemberId_()
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
            .append("comment", comment)
            .append("feedbackDate", feedbackDate)
            .append("conferenceId", conferenceId)
            .append("conferenceMemberId", conferenceMemberId)
	 	    ;
	} 
		
	public String toString(Object object) {
	 	return getToStringBuilder(object).toString();
	} 
	
	public String toStringWithParents() {
	    ToStringBuilder toStringBuilder = getToStringBuilder(this);
        if (conferenceId!=null)
            toStringBuilder.append("conferenceId", conferenceId.toStringWithParents());
        if (conferenceMemberId!=null)
            toStringBuilder.append("conferenceMemberId", conferenceMemberId.toStringWithParents());
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
		if (!(object instanceof ConferenceFeedback)) return false;
		ConferenceFeedback conferencefeedback = (ConferenceFeedback) object;
		if (conferencefeedback.id==null || !conferencefeedback.id.equals(id)) return false;
		return true;
	}    

	public ConferenceFeedback clone() {
        ConferenceFeedback conferencefeedback = flat();
        if (getConferenceId()!=null) 
            conferencefeedback.setConferenceId (getConferenceId().clone());   
        if (getConferenceMemberId()!=null) 
            conferencefeedback.setConferenceMemberId (getConferenceMemberId().clone());   
        return conferencefeedback;
	}
	
	public void copy (ConferenceFeedback conferencefeedback) {
		if (conferencefeedback!=null) {
			setId (conferencefeedback.getId());
			setComment (conferencefeedback.getComment());
			setFeedbackDate (conferencefeedback.getFeedbackDate());
			setConferenceId (conferencefeedback.getConferenceId());
			setConferenceMemberId (conferencefeedback.getConferenceMemberId());
		}
	}
	
	public static ConferenceFeedback fullMask() {
		return new ConferenceFeedback(
			integerMask__ ,
			stringMask__ ,
			timestampMask__ ,
			longMask__ ,
			longMask__ 
		);	    
	}

    public Integer getId() {
        return id;
    }
	
    public void setId (Integer id) {
        this.id =  id;
    }
    
//MP-MANAGED-UPDATABLE-BEGINNING-DISABLE @GETTER-SETTER-comment@
    public String getComment() {
        return comment;
    }
	
    public void setComment (String comment) {
        this.comment =  comment;
    }    
//MP-MANAGED-UPDATABLE-ENDING

//MP-MANAGED-UPDATABLE-BEGINNING-DISABLE @GETTER-SETTER-feedback_date@
    public java.util.Date getFeedbackDate() {
        return feedbackDate;
    }
	
    public void setFeedbackDate (java.util.Date feedbackDate) {
        this.feedbackDate =  feedbackDate;
    }    
//MP-MANAGED-UPDATABLE-ENDING


    public Conference getConferenceId () {
    	return conferenceId;
    }
	
    public void setConferenceId (Conference conferenceId) {
    	this.conferenceId = conferenceId;
    }

    public Long getConferenceId_() {
        return conferenceId_;
    }
	
    public void setConferenceId_ (Long conferenceId) {
        this.conferenceId_ =  conferenceId;
    }
	
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
	



//MP-MANAGED-ADDED-AREA-BEGINNING @implementation@
//MP-MANAGED-ADDED-AREA-ENDING @implementation@

}
