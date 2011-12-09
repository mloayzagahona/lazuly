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
@NamedQueries({
	 @NamedQuery(name="Speaker.findAll", query="SELECT speaker FROM Speaker speaker")
	,@NamedQuery(name="Speaker.findByBio", query="SELECT speaker FROM Speaker speaker WHERE speaker.bio = :bio")
	,@NamedQuery(name="Speaker.findByBioContaining", query="SELECT speaker FROM Speaker speaker WHERE speaker.bio like :bio")
	,@NamedQuery(name="Speaker.findByPhoto", query="SELECT speaker FROM Speaker speaker WHERE speaker.photo = :photo")
	,@NamedQuery(name="Speaker.findByPhotoContaining", query="SELECT speaker FROM Speaker speaker WHERE speaker.photo like :photo")
	,@NamedQuery(name="Speaker.findByWebSiteUrl", query="SELECT speaker FROM Speaker speaker WHERE speaker.webSiteUrl = :webSiteUrl")
	,@NamedQuery(name="Speaker.findByWebSiteUrlContaining", query="SELECT speaker FROM Speaker speaker WHERE speaker.webSiteUrl like :webSiteUrl")
})
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(namespace="net.sf.mp.demo.conference.domain.conference", name = "Speaker")
@XmlRootElement(namespace="net.sf.mp.demo.conference.domain.conference")
public class Speaker extends AbstractDomainObject {
    private static final long serialVersionUID = 1L;
	
    public  static final String FIND_ALL = "Speaker.findAll";
    public static final String FIND_BY_BIO = "Speaker.findByBio";
    public static final String FIND_BY_BIO_CONTAINING ="Speaker.findByBioContaining";
    public static final String FIND_BY_PHOTO = "Speaker.findByPhoto";
    public static final String FIND_BY_PHOTO_CONTAINING ="Speaker.findByPhotoContaining";
    public static final String FIND_BY_WEBSITEURL = "Speaker.findByWebSiteUrl";
    public static final String FIND_BY_WEBSITEURL_CONTAINING ="Speaker.findByWebSiteUrlContaining";
	
    @Id @Column(name="id" )
    @GeneratedValue(strategy = GenerationType.AUTO)
    @XmlElement (name="id")
    private Long id;

//MP-MANAGED-ADDED-AREA-BEGINNING @bio-field-annotation@
//MP-MANAGED-ADDED-AREA-ENDING @bio-field-annotation@
//MP-MANAGED-UPDATABLE-BEGINNING-DISABLE @ATTRIBUTE-bio@
    @Column(name="bio",   nullable=false,  unique=false)
    @XmlElement (name="bio")
    private String bio; 
//MP-MANAGED-UPDATABLE-ENDING

//MP-MANAGED-ADDED-AREA-BEGINNING @photo-field-annotation@
//MP-MANAGED-ADDED-AREA-ENDING @photo-field-annotation@
//MP-MANAGED-UPDATABLE-BEGINNING-DISABLE @ATTRIBUTE-photo@
    @Column(name="photo",    nullable=true,  unique=false)
    @XmlElement (name="photo")
    private String photo; 
//MP-MANAGED-UPDATABLE-ENDING

//MP-MANAGED-ADDED-AREA-BEGINNING @web_site_url-field-annotation@
//MP-MANAGED-ADDED-AREA-ENDING @web_site_url-field-annotation@
//MP-MANAGED-UPDATABLE-BEGINNING-DISABLE @ATTRIBUTE-web_site_url@
    @Column(name="web_site_url",  length=255,  nullable=true,  unique=false)
    @XmlElement (name="web-site-url")
    private String webSiteUrl; 
//MP-MANAGED-UPDATABLE-ENDING

    @ManyToOne (fetch=FetchType.LAZY , optional=false)
    @JoinColumn(name="conference_member_id", referencedColumnName = "id", nullable=false,  unique=false ) 
    private ConferenceMember conferenceMemberId;  

    @XmlAttribute (name="conference-member-id")
    @Column(name="conference_member_id",  nullable=false,  unique=false, insertable=false, updatable=false)
    private Long conferenceMemberId_;
	
    @ManyToOne (fetch=FetchType.LAZY )
    @JoinColumn(name="sponsor_id", referencedColumnName = "id",  nullable=true,  unique=false ) 
    private Sponsor sponsorId;  

    @XmlAttribute (name="sponsor-id")
    @Column(name="sponsor_id",   nullable=true,  unique=false, insertable=false, updatable=false)
    private Long sponsorId_;
	
//MP-MANAGED-UPDATABLE-BEGINNING-DISABLE @speakerPresentationSpeakerViaSpeakerId-field-speaker@
//MP-MANAGED-UPDATABLE-ENDING
    @XmlTransient
    @ManyToMany
    @JoinTable(name="SPEAKER_PRESENTATION", 
        joinColumns=@JoinColumn(name="speaker_id"), 
        inverseJoinColumns=@JoinColumn(name="presentation_id") 
    )
    private Set <Presentation> presentationSpeakerPresentationViaId = new HashSet <Presentation> ();

    /**
    * Default constructor
    */
    public Speaker() {
    }

	/**
	* All field constructor 
	*/
    public Speaker(
       Long id,
       Long conferenceMemberId,
       String bio,
       String photo,
       String webSiteUrl,
       Long sponsorId) {
       //primary keys
       setId (id);
       //attributes
       setBio (bio);
       setPhoto (photo);
       setWebSiteUrl (webSiteUrl);
       //parents
       this.conferenceMemberId = new ConferenceMember();
       this.conferenceMemberId.setId(conferenceMemberId); //ConferenceMember Column [name=id; type=BIGINT] - local Speaker Column [name=conference_member_id; type=BIGINT]
       this.sponsorId = new Sponsor();
       this.sponsorId.setId(sponsorId); //Sponsor Column [name=id; type=BIGINT] - local Speaker Column [name=sponsor_id; type=BIGINT]
    }

	public Speaker flat() {
	   return new Speaker(
          getId(),
          getConferenceMemberId_(),
          getBio(),
          getPhoto(),
          getWebSiteUrl(),
          getSponsorId_()
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
            .append("bio", bio)
            .append("photo", photo)
            .append("webSiteUrl", webSiteUrl)
            .append("sponsorId", sponsorId)
	 	    ;
	} 
		
	public String toString(Object object) {
	 	return getToStringBuilder(object).toString();
	} 
	
	public String toStringWithParents() {
	    ToStringBuilder toStringBuilder = getToStringBuilder(this);
        if (conferenceMemberId!=null)
            toStringBuilder.append("conferenceMemberId", conferenceMemberId.toStringWithParents());
        if (sponsorId!=null)
            toStringBuilder.append("sponsorId", sponsorId.toStringWithParents());
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
		if (!(object instanceof Speaker)) return false;
		Speaker speaker = (Speaker) object;
		if (speaker.id==null || !speaker.id.equals(id)) return false;
		return true;
	}    

	public Speaker clone() {
        Speaker speaker = flat();
        if (getConferenceMemberId()!=null) 
            speaker.setConferenceMemberId (getConferenceMemberId().clone());   
        if (getSponsorId()!=null) 
            speaker.setSponsorId (getSponsorId().clone());   
        return speaker;
	}
	
	public void copy (Speaker speaker) {
		if (speaker!=null) {
			setId (speaker.getId());
			setConferenceMemberId (speaker.getConferenceMemberId());
			setBio (speaker.getBio());
			setPhoto (speaker.getPhoto());
			setWebSiteUrl (speaker.getWebSiteUrl());
			setSponsorId (speaker.getSponsorId());
		}
	}
	
	public static Speaker fullMask() {
		return new Speaker(
			longMask__ ,
			longMask__ ,
			stringMask__ ,
			null ,
			stringMask__ ,
			longMask__ 
		);	    
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
	
    public Sponsor getSponsorId () {
    	return sponsorId;
    }
	
    public void setSponsorId (Sponsor sponsorId) {
    	this.sponsorId = sponsorId;
    }

    public Long getSponsorId_() {
        return sponsorId_;
    }
	
    public void setSponsorId_ (Long sponsorId) {
        this.sponsorId_ =  sponsorId;
    }
	

//MP-MANAGED-UPDATABLE-BEGINNING-DISABLE @speakerPresentationSpeakerViaSpeakerId-getter-speaker@
//MP-MANAGED-UPDATABLE-ENDING

    public Set<Presentation> getPresentationSpeakerPresentationViaId() {
        if (presentationSpeakerPresentationViaId == null){
            presentationSpeakerPresentationViaId = new HashSet<Presentation>();
        }
        return presentationSpeakerPresentationViaId;
    }

    public void setPresentationSpeakerPresentationViaId (Set<Presentation> presentationSpeakerPresentationViaId) {
        this.presentationSpeakerPresentationViaId = presentationSpeakerPresentationViaId;
    }
    	
    public void addPresentationSpeakerPresentationViaId (Presentation presentationSpeakerPresentationViaId) {
        getPresentationSpeakerPresentationViaId().add(presentationSpeakerPresentationViaId);
    }	    

//MP-MANAGED-ADDED-AREA-BEGINNING @implementation@
//MP-MANAGED-ADDED-AREA-ENDING @implementation@

}
