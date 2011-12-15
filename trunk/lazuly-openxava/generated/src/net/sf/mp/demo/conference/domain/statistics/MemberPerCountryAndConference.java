package net.sf.mp.demo.conference.domain.statistics;

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

import net.sf.mp.demo.conference.domain.statistics.MemberPerRoleCountryAndConference;

/**
 *
 * <p>Title: MemberPerCountryAndConference</p>
 *
 * <p>Description: Domain Object describing a MemberPerCountryAndConference entity</p>
 *
 */
@Entity (name="MemberPerCountryAndConference")
@Table (name="stat_mb_per_ctry_conf")
@Views({
	@View(
		name="base",
		members=
        "" 	
        + "id  ; "
        + "country  ; "
        + "conferenceName  ; "
        + "number  ; "
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
	    + "memberPerRoleCountryAndConferences { memberPerRoleCountryAndConferences };" //+ "${link}${columnclass}s { ${link}${columnclass}s };" 
	),
	@View(extendsView="base",
        members=
          "" 	
	    + "memberPerRoleCountryAndConferences { memberPerRoleCountryAndConferences };" //+ "${link}${columnclass}s { ${link}${columnclass}s };" 
	),
    @View(name="memberPerCountryAndConferenceDEFAULT_VIEW", 
	   members=
          " id ;" 	
        + "country  ; "
        + "conferenceName  ; "
        + "number  ; "
	)
})

@Tabs({
@Tab(
properties=
     " id "
    +",  country "
    +",  conferenceName "
    +",  number "
)
,
@Tab(
name = "MemberPerCountryAndConferenceTab",
properties=
     " id "
    +",  country "
    +",  conferenceName "
    +",  number "
)
,
@Tab(
name = "MemberPerCountryAndConferenceTabWithRef",
properties=
     " id "
    +",  country "
    +",  conferenceName "
    +",  number "
)
})
//MP-MANAGED-ADDED-AREA-BEGINNING @class-annotation@
//MP-MANAGED-ADDED-AREA-ENDING @class-annotation@
public class MemberPerCountryAndConference {

    @Id @Column(name="id" ,length=300)
    private String id; 

//MP-MANAGED-ADDED-AREA-BEGINNING @country-field-annotation@
//MP-MANAGED-ADDED-AREA-ENDING @country-field-annotation@

//MP-MANAGED-UPDATABLE-BEGINNING-DISABLE @ATTRIBUTE-country@
    @Column(name="country",  length=45, nullable=false,  unique=false)
    @Required
    private String country;	
//MP-MANAGED-UPDATABLE-ENDING

//MP-MANAGED-ADDED-AREA-BEGINNING @conference_name-field-annotation@
//MP-MANAGED-ADDED-AREA-ENDING @conference_name-field-annotation@

//MP-MANAGED-UPDATABLE-BEGINNING-DISABLE @ATTRIBUTE-conference_name@
    @Column(name="conference_name",  length=255, nullable=false,  unique=false)
    @Required
    private String conferenceName;	
//MP-MANAGED-UPDATABLE-ENDING

//MP-MANAGED-ADDED-AREA-BEGINNING @number-field-annotation@
//MP-MANAGED-ADDED-AREA-ENDING @number-field-annotation@

//MP-MANAGED-UPDATABLE-BEGINNING-DISABLE @ATTRIBUTE-number@
    @Column(name="number",   nullable=false,  unique=false)
    @Required
    private Long number;	
//MP-MANAGED-UPDATABLE-ENDING



    @OneToMany (targetEntity=net.sf.mp.demo.conference.domain.statistics.MemberPerRoleCountryAndConference.class, fetch=FetchType.LAZY, mappedBy="statMbPerCtryConfId", cascade=CascadeType.REMOVE)//, cascade=CascadeType.ALL)
    private Set <MemberPerRoleCountryAndConference> memberPerRoleCountryAndConferences = new HashSet<MemberPerRoleCountryAndConference>(); 
   


    /**
    * Default constructor
    */
    public MemberPerCountryAndConference() {
    }


    public String getId() {
        return id;
    }
	
    public void setId (String id) {
        this.id =  id;
    }
    

//MP-MANAGED-UPDATABLE-BEGINNING-DISABLE @GETTER-SETTER-country@
    public String getCountry() {
        return country;
    }
	
    public void setCountry (String country) {
        this.country =  country;
    } 
//MP-MANAGED-UPDATABLE-ENDING

//MP-MANAGED-UPDATABLE-BEGINNING-DISABLE @GETTER-SETTER-conference_name@
    public String getConferenceName() {
        return conferenceName;
    }
	
    public void setConferenceName (String conferenceName) {
        this.conferenceName =  conferenceName;
    } 
//MP-MANAGED-UPDATABLE-ENDING

//MP-MANAGED-UPDATABLE-BEGINNING-DISABLE @GETTER-SETTER-number@
    public Long getNumber() {
        return number;
    }
	
    public void setNumber (Long number) {
        this.number =  number;
    } 
//MP-MANAGED-UPDATABLE-ENDING



    public Set<MemberPerRoleCountryAndConference> getMemberPerRoleCountryAndConferences() {
        if (memberPerRoleCountryAndConferences == null){
            memberPerRoleCountryAndConferences = new HashSet<MemberPerRoleCountryAndConference>();
        }
        return memberPerRoleCountryAndConferences;
    }

    public void setMemberPerRoleCountryAndConferences (Set<MemberPerRoleCountryAndConference> memberPerRoleCountryAndConferences) {
        this.memberPerRoleCountryAndConferences = memberPerRoleCountryAndConferences;
    }	
    
    public void addMemberPerRoleCountryAndConferences (MemberPerRoleCountryAndConference memberPerRoleCountryAndConference) {
    	    getMemberPerRoleCountryAndConferences().add(memberPerRoleCountryAndConference);
    }
    


//MP-MANAGED-ADDED-AREA-BEGINNING @implementation@
//MP-MANAGED-ADDED-AREA-ENDING @implementation@

}
