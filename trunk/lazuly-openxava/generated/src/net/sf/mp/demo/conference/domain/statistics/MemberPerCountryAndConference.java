package net.sf.mp.demo.conference.domain.statistics;

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
	    + "memberPerRoleCountryAndConferences { memberPerRoleCountryAndConferences };" //+ "statMbByRoleStatMbPerCtryConfIds { statMbByRoleStatMbPerCtryConfIds };" 
	),
	@View(extendsView="base",
        members=
          "" 	
	    + "memberPerRoleCountryAndConferences { memberPerRoleCountryAndConferences };" //+ "statMbByRoleStatMbPerCtryConfIds { statMbByRoleStatMbPerCtryConfIds };" 
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
     " country "
    +",  conferenceName "
    +",  number "
)
,
@Tab(
name = "MemberPerCountryAndConferenceTab",
properties=
     " country "
    +",  conferenceName "
    +",  number "
)
,
@Tab(
name = "MemberPerCountryAndConferenceTabWithRef",
properties=
     " country "
    +",  conferenceName "
    +",  number "
)
})

public class MemberPerCountryAndConference {

    @Id @Column(name="id" ,length=300)
    private String id; 

    @Column(name="country",  length=45, nullable=false,  unique=false)
    @Required
    private String country; 
    @Column(name="conference_name",  length=255, nullable=false,  unique=false)
    @Required
    private String conferenceName; 
    @Column(name="number",   nullable=false,  unique=false)
    @Required
    private Long number; 


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
    

    public String getCountry() {
        return country;
    }
	
    public void setCountry (String country) {
        this.country =  country;
    }
    public String getConferenceName() {
        return conferenceName;
    }
	
    public void setConferenceName (String conferenceName) {
        this.conferenceName =  conferenceName;
    }
    public Long getNumber() {
        return number;
    }
	
    public void setNumber (Long number) {
        this.number =  number;
    }


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
    

}
