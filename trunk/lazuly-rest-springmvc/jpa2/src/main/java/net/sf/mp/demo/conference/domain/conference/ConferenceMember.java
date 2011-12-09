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
import net.sf.mp.demo.conference.domain.conference.ConferenceFeedback;
import net.sf.mp.demo.conference.domain.conference.Evaluation;
import net.sf.mp.demo.conference.domain.conference.Speaker;
import net.sf.mp.demo.conference.domain.conference.Conference;
import net.sf.mp.demo.conference.domain.conference.Address;
import net.sf.mp.demo.conference.domain.admin.Role;

/**
 *
 * <p>Title: ConferenceMember</p>
 *
 * <p>Description: Domain Object describing a ConferenceMember entity</p>
 *
 */
@Entity (name="ConferenceMember")
@Table (name="conference_member")
@NamedQueries({
	 @NamedQuery(name="ConferenceMember.findAll", query="SELECT conferenceMember FROM ConferenceMember conferenceMember")
	,@NamedQuery(name="ConferenceMember.findByFirstName", query="SELECT conferenceMember FROM ConferenceMember conferenceMember WHERE conferenceMember.firstName = :firstName")
	,@NamedQuery(name="ConferenceMember.findByFirstNameContaining", query="SELECT conferenceMember FROM ConferenceMember conferenceMember WHERE conferenceMember.firstName like :firstName")
	,@NamedQuery(name="ConferenceMember.findByLastName", query="SELECT conferenceMember FROM ConferenceMember conferenceMember WHERE conferenceMember.lastName = :lastName")
	,@NamedQuery(name="ConferenceMember.findByLastNameContaining", query="SELECT conferenceMember FROM ConferenceMember conferenceMember WHERE conferenceMember.lastName like :lastName")
	,@NamedQuery(name="ConferenceMember.findByEmail", query="SELECT conferenceMember FROM ConferenceMember conferenceMember WHERE conferenceMember.email = :email")
	,@NamedQuery(name="ConferenceMember.findByEmailContaining", query="SELECT conferenceMember FROM ConferenceMember conferenceMember WHERE conferenceMember.email like :email")
	,@NamedQuery(name="ConferenceMember.findByStatus", query="SELECT conferenceMember FROM ConferenceMember conferenceMember WHERE conferenceMember.status = :status")
	,@NamedQuery(name="ConferenceMember.findByStatusContaining", query="SELECT conferenceMember FROM ConferenceMember conferenceMember WHERE conferenceMember.status like :status")
})
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(namespace="net.sf.mp.demo.conference.domain.conference", name = "ConferenceMember")
@XmlRootElement(namespace="net.sf.mp.demo.conference.domain.conference")
public class ConferenceMember extends AbstractDomainObject {
    private static final long serialVersionUID = 1L;
	
    public  static final String FIND_ALL = "ConferenceMember.findAll";
    public static final String FIND_BY_FIRSTNAME = "ConferenceMember.findByFirstName";
    public static final String FIND_BY_FIRSTNAME_CONTAINING ="ConferenceMember.findByFirstNameContaining";
    public static final String FIND_BY_LASTNAME = "ConferenceMember.findByLastName";
    public static final String FIND_BY_LASTNAME_CONTAINING ="ConferenceMember.findByLastNameContaining";
    public static final String FIND_BY_EMAIL = "ConferenceMember.findByEmail";
    public static final String FIND_BY_EMAIL_CONTAINING ="ConferenceMember.findByEmailContaining";
    public static final String FIND_BY_STATUS = "ConferenceMember.findByStatus";
    public static final String FIND_BY_STATUS_CONTAINING ="ConferenceMember.findByStatusContaining";
	
    @Id @Column(name="id" )
    @GeneratedValue(strategy = GenerationType.AUTO)
    @XmlElement (name="id")
    private Long id;

//MP-MANAGED-ADDED-AREA-BEGINNING @first_name-field-annotation@
//MP-MANAGED-ADDED-AREA-ENDING @first_name-field-annotation@
//MP-MANAGED-UPDATABLE-BEGINNING-DISABLE @ATTRIBUTE-first_name@
    @Column(name="first_name",  length=255, nullable=false,  unique=false)
    @XmlElement (name="first-name")
    private String firstName; 
//MP-MANAGED-UPDATABLE-ENDING

//MP-MANAGED-ADDED-AREA-BEGINNING @last_name-field-annotation@
//MP-MANAGED-ADDED-AREA-ENDING @last_name-field-annotation@
//MP-MANAGED-UPDATABLE-BEGINNING-DISABLE @ATTRIBUTE-last_name@
    @Column(name="last_name",  length=255, nullable=false,  unique=false)
    @XmlElement (name="last-name")
    private String lastName; 
//MP-MANAGED-UPDATABLE-ENDING

//MP-MANAGED-ADDED-AREA-BEGINNING @email-field-annotation@
//MP-MANAGED-ADDED-AREA-ENDING @email-field-annotation@
//MP-MANAGED-UPDATABLE-BEGINNING-DISABLE @ATTRIBUTE-email@
    @Column(name="email",  length=255, nullable=false,  unique=false)
    @XmlElement (name="email")
    private String email; 
//MP-MANAGED-UPDATABLE-ENDING

//MP-MANAGED-ADDED-AREA-BEGINNING @status-field-annotation@
//MP-MANAGED-ADDED-AREA-ENDING @status-field-annotation@
//MP-MANAGED-UPDATABLE-BEGINNING-DISABLE @ATTRIBUTE-status@
    @Column(name="status",  length=45, nullable=false,  unique=false)
    @XmlElement (name="status")
    private String status; 
//MP-MANAGED-UPDATABLE-ENDING

    @ManyToOne (fetch=FetchType.LAZY , optional=false)
    @JoinColumn(name="conference_id", referencedColumnName = "id", nullable=false,  unique=false ) 
    private Conference conferenceId;  

    @XmlAttribute (name="conference-id")
    @Column(name="conference_id",  nullable=false,  unique=false, insertable=false, updatable=false)
    private Long conferenceId_;
	
    @ManyToOne (fetch=FetchType.LAZY , optional=false)
    @JoinColumn(name="address_id", referencedColumnName = "id", nullable=false,  unique=false ) 
    private Address addressId;  

    @XmlAttribute (name="address-id")
    @Column(name="address_id",  nullable=false,  unique=false, insertable=false, updatable=false)
    private Long addressId_;
	
//MP-MANAGED-UPDATABLE-BEGINNING-DISABLE @conferenceFeedbackConferenceMemberViaConferenceMemberId-field-conference_member@
    @XmlElement (name="conferenceFeedbackConferenceMemberViaConferenceMemberId")
    @OneToMany (targetEntity=net.sf.mp.demo.conference.domain.conference.ConferenceFeedback.class, fetch=FetchType.LAZY, mappedBy="conferenceMemberId", cascade=CascadeType.REMOVE)//, cascade=CascadeType.ALL)
    private Set <ConferenceFeedback> conferenceFeedbackConferenceMemberViaConferenceMemberId = new HashSet<ConferenceFeedback>(); 
   
//MP-MANAGED-UPDATABLE-ENDING
//MP-MANAGED-UPDATABLE-BEGINNING-DISABLE @evaluationConferenceMemberViaConferenceMemberId-field-conference_member@
    @XmlElement (name="evaluationConferenceMemberViaConferenceMemberId")
    @OneToMany (targetEntity=net.sf.mp.demo.conference.domain.conference.Evaluation.class, fetch=FetchType.LAZY, mappedBy="conferenceMemberId", cascade=CascadeType.REMOVE)//, cascade=CascadeType.ALL)
    private Set <Evaluation> evaluationConferenceMemberViaConferenceMemberId = new HashSet<Evaluation>(); 
   
//MP-MANAGED-UPDATABLE-ENDING
//MP-MANAGED-UPDATABLE-BEGINNING-DISABLE @memberRoleConferenceMemberViaConferenceMemberId-field-conference_member@
//MP-MANAGED-UPDATABLE-ENDING
//MP-MANAGED-UPDATABLE-BEGINNING-DISABLE @speakerConferenceMemberViaConferenceMemberId-field-conference_member@
    @XmlElement (name="speakerConferenceMemberViaConferenceMemberId")
    @OneToMany (targetEntity=net.sf.mp.demo.conference.domain.conference.Speaker.class, fetch=FetchType.LAZY, mappedBy="conferenceMemberId", cascade=CascadeType.REMOVE)//, cascade=CascadeType.ALL)
    private Set <Speaker> speakerConferenceMemberViaConferenceMemberId = new HashSet<Speaker>(); 
   
//MP-MANAGED-UPDATABLE-ENDING
    @XmlTransient
    @ManyToMany
    @JoinTable(name="MEMBER_ROLE", 
        joinColumns=@JoinColumn(name="conference_member_id"), 
        inverseJoinColumns=@JoinColumn(name="role_id") 
    )
    private Set <Role> roleMemberRoleViaId = new HashSet <Role> ();

    /**
    * Default constructor
    */
    public ConferenceMember() {
    }

	/**
	* All field constructor 
	*/
    public ConferenceMember(
       Long id,
       Long conferenceId,
       String firstName,
       String lastName,
       String email,
       Long addressId,
       String status) {
       //primary keys
       setId (id);
       //attributes
       setFirstName (firstName);
       setLastName (lastName);
       setEmail (email);
       setStatus (status);
       //parents
       this.conferenceId = new Conference();
       this.conferenceId.setId(conferenceId); //Conference Column [name=id; type=BIGINT] - local ConferenceMember Column [name=conference_id; type=BIGINT]
       this.addressId = new Address();
       this.addressId.setId(addressId); //Address Column [name=id; type=BIGINT] - local ConferenceMember Column [name=address_id; type=BIGINT]
    }

	public ConferenceMember flat() {
	   return new ConferenceMember(
          getId(),
          getConferenceId_(),
          getFirstName(),
          getLastName(),
          getEmail(),
          getAddressId_(),
          getStatus()
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
            .append("conferenceId", conferenceId)
            .append("firstName", firstName)
            .append("lastName", lastName)
            .append("email", email)
            .append("addressId", addressId)
            .append("status", status)
	 	    ;
	} 
		
	public String toString(Object object) {
	 	return getToStringBuilder(object).toString();
	} 
	
	public String toStringWithParents() {
	    ToStringBuilder toStringBuilder = getToStringBuilder(this);
        if (conferenceId!=null)
            toStringBuilder.append("conferenceId", conferenceId.toStringWithParents());
        if (addressId!=null)
            toStringBuilder.append("addressId", addressId.toStringWithParents());
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
		if (!(object instanceof ConferenceMember)) return false;
		ConferenceMember conferencemember = (ConferenceMember) object;
		if (conferencemember.id==null || !conferencemember.id.equals(id)) return false;
		return true;
	}    

	public ConferenceMember clone() {
        ConferenceMember conferencemember = flat();
        if (getConferenceId()!=null) 
            conferencemember.setConferenceId (getConferenceId().clone());   
        if (getAddressId()!=null) 
            conferencemember.setAddressId (getAddressId().clone());   
        return conferencemember;
	}
	
	public void copy (ConferenceMember conferencemember) {
		if (conferencemember!=null) {
			setId (conferencemember.getId());
			setConferenceId (conferencemember.getConferenceId());
			setFirstName (conferencemember.getFirstName());
			setLastName (conferencemember.getLastName());
			setEmail (conferencemember.getEmail());
			setAddressId (conferencemember.getAddressId());
			setStatus (conferencemember.getStatus());
		}
	}
	
	public static ConferenceMember fullMask() {
		return new ConferenceMember(
			longMask__ ,
			longMask__ ,
			stringMask__ ,
			stringMask__ ,
			stringMask__ ,
			longMask__ ,
			stringMask__ 
		);	    
	}

    public Long getId() {
        return id;
    }
	
    public void setId (Long id) {
        this.id =  id;
    }
    
//MP-MANAGED-UPDATABLE-BEGINNING-DISABLE @GETTER-SETTER-first_name@
    public String getFirstName() {
        return firstName;
    }
	
    public void setFirstName (String firstName) {
        this.firstName =  firstName;
    }    
//MP-MANAGED-UPDATABLE-ENDING

//MP-MANAGED-UPDATABLE-BEGINNING-DISABLE @GETTER-SETTER-last_name@
    public String getLastName() {
        return lastName;
    }
	
    public void setLastName (String lastName) {
        this.lastName =  lastName;
    }    
//MP-MANAGED-UPDATABLE-ENDING

//MP-MANAGED-UPDATABLE-BEGINNING-DISABLE @GETTER-SETTER-email@
    public String getEmail() {
        return email;
    }
	
    public void setEmail (String email) {
        this.email =  email;
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
	
    public Address getAddressId () {
    	return addressId;
    }
	
    public void setAddressId (Address addressId) {
    	this.addressId = addressId;
    }

    public Long getAddressId_() {
        return addressId_;
    }
	
    public void setAddressId_ (Long addressId) {
        this.addressId_ =  addressId;
    }
	

//MP-MANAGED-UPDATABLE-BEGINNING-DISABLE @conferenceFeedbackConferenceMemberViaConferenceMemberId-getter-conference_member@
    public Set<ConferenceFeedback> getConferenceFeedbackConferenceMemberViaConferenceMemberId() {
        if (conferenceFeedbackConferenceMemberViaConferenceMemberId == null){
            conferenceFeedbackConferenceMemberViaConferenceMemberId = new HashSet<ConferenceFeedback>();
        }
        return conferenceFeedbackConferenceMemberViaConferenceMemberId;
    }

    public void setConferenceFeedbackConferenceMemberViaConferenceMemberId (Set<ConferenceFeedback> conferenceFeedbackConferenceMemberViaConferenceMemberId) {
        this.conferenceFeedbackConferenceMemberViaConferenceMemberId = conferenceFeedbackConferenceMemberViaConferenceMemberId;
    }	
    
    public void addConferenceFeedbackConferenceMemberViaConferenceMemberId (ConferenceFeedback conferenceFeedback) {
    	    getConferenceFeedbackConferenceMemberViaConferenceMemberId().add(conferenceFeedback);
    }
    
//MP-MANAGED-UPDATABLE-ENDING
//MP-MANAGED-UPDATABLE-BEGINNING-DISABLE @evaluationConferenceMemberViaConferenceMemberId-getter-conference_member@
    public Set<Evaluation> getEvaluationConferenceMemberViaConferenceMemberId() {
        if (evaluationConferenceMemberViaConferenceMemberId == null){
            evaluationConferenceMemberViaConferenceMemberId = new HashSet<Evaluation>();
        }
        return evaluationConferenceMemberViaConferenceMemberId;
    }

    public void setEvaluationConferenceMemberViaConferenceMemberId (Set<Evaluation> evaluationConferenceMemberViaConferenceMemberId) {
        this.evaluationConferenceMemberViaConferenceMemberId = evaluationConferenceMemberViaConferenceMemberId;
    }	
    
    public void addEvaluationConferenceMemberViaConferenceMemberId (Evaluation evaluation) {
    	    getEvaluationConferenceMemberViaConferenceMemberId().add(evaluation);
    }
    
//MP-MANAGED-UPDATABLE-ENDING
//MP-MANAGED-UPDATABLE-BEGINNING-DISABLE @memberRoleConferenceMemberViaConferenceMemberId-getter-conference_member@
//MP-MANAGED-UPDATABLE-ENDING
//MP-MANAGED-UPDATABLE-BEGINNING-DISABLE @speakerConferenceMemberViaConferenceMemberId-getter-conference_member@
    public Set<Speaker> getSpeakerConferenceMemberViaConferenceMemberId() {
        if (speakerConferenceMemberViaConferenceMemberId == null){
            speakerConferenceMemberViaConferenceMemberId = new HashSet<Speaker>();
        }
        return speakerConferenceMemberViaConferenceMemberId;
    }

    public void setSpeakerConferenceMemberViaConferenceMemberId (Set<Speaker> speakerConferenceMemberViaConferenceMemberId) {
        this.speakerConferenceMemberViaConferenceMemberId = speakerConferenceMemberViaConferenceMemberId;
    }	
    
    public void addSpeakerConferenceMemberViaConferenceMemberId (Speaker speaker) {
    	    getSpeakerConferenceMemberViaConferenceMemberId().add(speaker);
    }
    
//MP-MANAGED-UPDATABLE-ENDING

    public Set<Role> getRoleMemberRoleViaId() {
        if (roleMemberRoleViaId == null){
            roleMemberRoleViaId = new HashSet<Role>();
        }
        return roleMemberRoleViaId;
    }

    public void setRoleMemberRoleViaId (Set<Role> roleMemberRoleViaId) {
        this.roleMemberRoleViaId = roleMemberRoleViaId;
    }
    	
    public void addRoleMemberRoleViaId (Role roleMemberRoleViaId) {
        getRoleMemberRoleViaId().add(roleMemberRoleViaId);
    }	    

//MP-MANAGED-ADDED-AREA-BEGINNING @implementation@
//MP-MANAGED-ADDED-AREA-ENDING @implementation@

}
