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
        + "id  ; "
        + "conferenceMemberId  ; "
        + "presentationId  ; "
        + "star  ; "
        + "comment  ; "
        + "evaluationDate  ; "
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
//MP-MANAGED-ADDED-AREA-BEGINNING @class-annotation@
//MP-MANAGED-ADDED-AREA-ENDING @class-annotation@
public class Evaluation {

    @Hidden @Id @Column(name="id" )
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id; 

//MP-MANAGED-ADDED-AREA-BEGINNING @star-field-annotation@
//MP-MANAGED-ADDED-AREA-ENDING @star-field-annotation@

//MP-MANAGED-UPDATABLE-BEGINNING-DISABLE @ATTRIBUTE-star@
    @Column(name="star",   nullable=false,  unique=false)
    @Required
    private Integer star;	
//MP-MANAGED-UPDATABLE-ENDING

//MP-MANAGED-ADDED-AREA-BEGINNING @comment-field-annotation@
//MP-MANAGED-ADDED-AREA-ENDING @comment-field-annotation@

//MP-MANAGED-UPDATABLE-BEGINNING-DISABLE @ATTRIBUTE-comment@
    @Column(name="comment",  length=500,  nullable=true,  unique=false)
    private String comment;	
//MP-MANAGED-UPDATABLE-ENDING

//MP-MANAGED-ADDED-AREA-BEGINNING @evaluation_date-field-annotation@
//MP-MANAGED-ADDED-AREA-ENDING @evaluation_date-field-annotation@

//MP-MANAGED-UPDATABLE-BEGINNING-DISABLE @ATTRIBUTE-evaluation_date@
    @Column(name="evaluation_date",    nullable=true,  unique=false)
    private Timestamp evaluationDate;	
//MP-MANAGED-UPDATABLE-ENDING


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
    public Timestamp getEvaluationDate() {
        return evaluationDate;
    }
	
    public void setEvaluationDate (Timestamp evaluationDate) {
        this.evaluationDate =  evaluationDate;
    } 
//MP-MANAGED-UPDATABLE-ENDING


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



//MP-MANAGED-ADDED-AREA-BEGINNING @implementation@
//MP-MANAGED-ADDED-AREA-ENDING @implementation@

}
