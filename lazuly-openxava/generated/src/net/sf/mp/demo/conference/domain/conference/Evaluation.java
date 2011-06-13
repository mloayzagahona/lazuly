package net.sf.mp.demo.conference.domain.conference;

import java.sql.*;
import java.util.Date;
import java.util.List;
import java.util.ArrayList;
import java.util.Set;
import java.util.HashSet;

import javax.persistence.*;
import org.openxava.annotations.*;

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
@Views({
	@View(
		name="base",
		members=
        "" 	
        + "star  ; "
        + "comment  ; "
        + "evaluationDate  ; "
	    + "conferenceMemberId;"  
	    + "presentationId;"  
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
    @View(name="evaluationDEFAULT_VIEW", 
	   members=
          " id ;" 	
        + "star  ; "
        + "comment  ; "
        + "evaluationDate  ; "
        + "conferenceMemberId ;"
	)
})

@Tabs({
@Tab(
properties=
     " star "
    +",  comment "
    +",  evaluationDate "
    +",  conferenceMemberId.firstName "
)
,
@Tab(
name = "EvaluationTab",
properties=
     " star "
    +",  comment "
    +",  evaluationDate "
)
,
@Tab(
name = "EvaluationTabWithRef",
properties=
     " star "
    +",  comment "
    +",  evaluationDate "
    +",  conferenceMemberId.firstName "
)
})

public class Evaluation {

    @Hidden @Id @Column(name="id" )
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id; 

    @Column(name="star",   nullable=false,  unique=false)
    @Required
    private Integer star; 
    @Column(name="comment",  length=500,  nullable=true,  unique=false)
    private String comment; 
    @Column(name="evaluation_date",    nullable=true,  unique=false)
    private Timestamp evaluationDate; 

    @ManyToOne (fetch=FetchType.LAZY ,optional=false) //remove optional=false to aggragate but leads to a side effect when going directly to the entity: required check is not performed=> if no set DB check constraint is raised...
    @JoinColumn(name="conference_member_id", nullable=false,  unique=false  )
    @ReferenceView ("conferenceMemberDEFAULT_VIEW")  
    private ConferenceMember conferenceMemberId; 
    
    @ManyToOne (fetch=FetchType.LAZY ,optional=false) //remove optional=false to aggragate but leads to a side effect when going directly to the entity: required check is not performed=> if no set DB check constraint is raised...
    @JoinColumn(name="presentation_id", nullable=false,  unique=false  )
    @ReferenceView ("presentationDEFAULT_VIEW")  
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

    public ConferenceMember getConferenceMemberId () {  //
    	return conferenceMemberId;
    }
	
    public void setConferenceMemberId (ConferenceMember conferenceMemberId) {
    	this.conferenceMemberId = conferenceMemberId;//this.conferenceMemberId = conferenceMember;
    }
    public Presentation getPresentationId () {  //
    	return presentationId;
    }
	
    public void setPresentationId (Presentation presentationId) {
    	this.presentationId = presentationId;//this.presentationId = presentation;
    }


}
