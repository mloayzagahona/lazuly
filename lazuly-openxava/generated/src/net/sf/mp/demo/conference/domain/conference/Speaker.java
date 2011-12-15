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
import net.sf.mp.demo.conference.domain.conference.Sponsor;
import net.sf.mp.demo.conference.domain.conference.Presentation;

/**
 *
 * <p>Title: Speaker</p>
 *
 * <p>Description: Domain Object describing a Speaker entity</p>
 *
 */
@Entity (name="Speaker")
@Table (name="speaker")
@Views({
	@View(
		name="base",
		members=
        "" 	
        + "id  ; "
        + "conferenceMemberId  ; "
        + "bio  ; "
        + "photo  ; "
        + "webSiteUrl  ; "
        + "sponsorId  ; "
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
	    + "presentations;"
	),
	@View(extendsView="base",
        members=
          "" 	
	    + "presentations;"
	),
    @View(name="speakerDEFAULT_VIEW", 
	   members=
          " id ;" 	
        + "bio  ; "
        + "photo  ; "
        + "webSiteUrl  ; "
        + "conferenceMemberId ;"
	)
})

@Tabs({
@Tab(
properties=
     " bio "
    +",  photo "
    +",  webSiteUrl "
    +",  conferenceMemberId.firstName "
)
,
@Tab(
name = "SpeakerTab",
properties=
     " bio "
    +",  photo "
    +",  webSiteUrl "
)
,
@Tab(
name = "SpeakerTabWithRef",
properties=
     " bio "
    +",  photo "
    +",  webSiteUrl "
    +",  conferenceMemberId.firstName "
)
})
//MP-MANAGED-ADDED-AREA-BEGINNING @class-annotation@
//MP-MANAGED-ADDED-AREA-ENDING @class-annotation@
public class Speaker {

    @Hidden @Id @Column(name="id" )
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id; 

//MP-MANAGED-ADDED-AREA-BEGINNING @bio-field-annotation@
//MP-MANAGED-ADDED-AREA-ENDING @bio-field-annotation@

//MP-MANAGED-UPDATABLE-BEGINNING-DISABLE @ATTRIBUTE-bio@
    @Column(name="bio",   nullable=false,  unique=false)
    @Required
    @Stereotype ("HTML_TEXT")
    private String bio;	
//MP-MANAGED-UPDATABLE-ENDING

//MP-MANAGED-ADDED-AREA-BEGINNING @photo-field-annotation@
//MP-MANAGED-ADDED-AREA-ENDING @photo-field-annotation@

//MP-MANAGED-UPDATABLE-BEGINNING-DISABLE @ATTRIBUTE-photo@
    @Column(name="photo",    nullable=true,  unique=false)
    @Stereotype ("PHOTO")
    private String photo;	
//MP-MANAGED-UPDATABLE-ENDING

//MP-MANAGED-ADDED-AREA-BEGINNING @web_site_url-field-annotation@
//MP-MANAGED-ADDED-AREA-ENDING @web_site_url-field-annotation@

//MP-MANAGED-UPDATABLE-BEGINNING-DISABLE @ATTRIBUTE-web_site_url@
    @Column(name="web_site_url",  length=255,  nullable=true,  unique=false)
    @Stereotype ("WEBURL")
    private String webSiteUrl;	
//MP-MANAGED-UPDATABLE-ENDING


    @ManyToOne (fetch=FetchType.LAZY ,optional=false) //remove optional=false to aggragate but leads to a side effect when going directly to the entity: required check is not performed=> if no set DB check constraint is raised...
    @JoinColumn(name="conference_member_id", nullable=false,  unique=false  )
    @ReferenceView ("conferenceMemberDEFAULT_VIEW")  
    private ConferenceMember conferenceMemberId; 
    
    @ManyToOne (fetch=FetchType.LAZY ) //remove optional=false to aggragate but leads to a side effect when going directly to the entity: required check is not performed=> if no set DB check constraint is raised...
    @JoinColumn(name="sponsor_id",  nullable=true,  unique=false  )
    @ReferenceView ("sponsorDEFAULT_VIEW")  
    private Sponsor sponsorId; 
    


    @ManyToMany
    @JoinTable(name="SPEAKER_PRESENTATION",
        joinColumns=@JoinColumn(name="speaker_id"), 
        inverseJoinColumns=@JoinColumn(name="presentation_id") 
    )
    private Set <Presentation> presentations = new HashSet <Presentation> ();


    /**
    * Default constructor
    */
    public Speaker() {
    }


    public Long getId() {
        return id;
    }
	
    public void setId (Long id) {
        this.id =  id;
    }
    

//MP-MANAGED-UPDATABLE-BEGINNING-DISABLE @GETTER-SETTER-bio@
    public String getBio() {
        return bio;
    }
	
    public void setBio (String bio) {
        this.bio =  bio;
    } 
//MP-MANAGED-UPDATABLE-ENDING

//MP-MANAGED-UPDATABLE-BEGINNING-DISABLE @GETTER-SETTER-photo@
    public String getPhoto() {
        return photo;
    }
	
    public void setPhoto (String photo) {
        this.photo =  photo;
    } 
//MP-MANAGED-UPDATABLE-ENDING

//MP-MANAGED-UPDATABLE-BEGINNING-DISABLE @GETTER-SETTER-web_site_url@
    public String getWebSiteUrl() {
        return webSiteUrl;
    }
	
    public void setWebSiteUrl (String webSiteUrl) {
        this.webSiteUrl =  webSiteUrl;
    } 
//MP-MANAGED-UPDATABLE-ENDING


    public ConferenceMember getConferenceMemberId () {  //
    	return conferenceMemberId;
    }
	
    public void setConferenceMemberId (ConferenceMember conferenceMemberId) {
    	this.conferenceMemberId = conferenceMemberId;//this.conferenceMemberId = conferenceMember;
    }
    public Sponsor getSponsorId () {  //
    	return sponsorId;
    }
	
    public void setSponsorId (Sponsor sponsorId) {
    	this.sponsorId = sponsorId;//this.sponsorId = sponsor;
    }

    public Set<Presentation> getPresentations() {
        if (presentations == null){
            presentations = new HashSet<Presentation>();
        }
        return presentations;
    }

    public void setPresentations (Set<Presentation> presentations) {
        this.presentations = presentations;
    }
    	
    public void addPresentations (Presentation presentations) {
        getPresentations().add(presentations);
    }	



//MP-MANAGED-ADDED-AREA-BEGINNING @implementation@
//MP-MANAGED-ADDED-AREA-ENDING @implementation@

}
