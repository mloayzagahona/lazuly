package net.sf.mp.demo.conference.domain.conference;

import java.sql.*;
import java.util.Date;
import java.util.List;
import java.util.ArrayList;
import java.util.Set;
import java.util.HashSet;

import javax.persistence.*;
import org.openxava.annotations.*;

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
@Views({
	@View(
		name="base",
		members=
        "" 	
        + "comment  ; "
        + "feedbackDate  ; "
	    + "conferenceMemberId;"  
	    + "conferenceId;"  
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
	),
	@View(extendsView="base",
        members=
          "" 	
	),
    @View(name="conferenceFeedbackDEFAULT_VIEW", 
	   members=
          " id ;" 	
        + "comment  ; "
        + "feedbackDate  ; "
        + "conferenceMemberId ;"
	)
})

@Tabs({
@Tab(
properties=
     " comment "
    +",  feedbackDate "
    +",  conferenceMemberId.firstName "
)
,
@Tab(
name = "ConferenceFeedbackTab",
properties=
     " comment "
    +",  feedbackDate "
)
,
@Tab(
name = "ConferenceFeedbackTabWithRef",
properties=
     " comment "
    +",  feedbackDate "
    +",  conferenceMemberId.firstName "
)
})

public class ConferenceFeedback {

    @Hidden @Id @Column(name="id" )
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id; 

    @Column(name="comment",   nullable=false,  unique=false)
    @Required
    private String comment; 
    @Column(name="feedback_date",   nullable=false,  unique=false)
    @Required
    private Timestamp feedbackDate; 

    @ManyToOne (fetch=FetchType.LAZY ,optional=false) //remove optional=false to aggragate but leads to a side effect when going directly to the entity: required check is not performed=> if no set DB check constraint is raised...
    @JoinColumn(name="conference_id", nullable=false,  unique=false  )
    @ReferenceView ("conferenceDEFAULT_VIEW")  
    private Conference conferenceId; 
    
    @ManyToOne (fetch=FetchType.LAZY ,optional=false) //remove optional=false to aggragate but leads to a side effect when going directly to the entity: required check is not performed=> if no set DB check constraint is raised...
    @JoinColumn(name="conference_member_id", nullable=false,  unique=false  )
    @ReferenceView ("conferenceMemberDEFAULT_VIEW")  
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

    public Conference getConferenceId () {  //
    	return conferenceId;
    }
	
    public void setConferenceId (Conference conferenceId) {
    	this.conferenceId = conferenceId;//this.conferenceId = conference;
    }
    public ConferenceMember getConferenceMemberId () {  //
    	return conferenceMemberId;
    }
	
    public void setConferenceMemberId (ConferenceMember conferenceMemberId) {
    	this.conferenceMemberId = conferenceMemberId;//this.conferenceMemberId = conferenceMember;
    }


}
