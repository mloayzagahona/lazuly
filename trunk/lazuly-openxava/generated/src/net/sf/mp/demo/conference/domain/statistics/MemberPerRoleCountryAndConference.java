package net.sf.mp.demo.conference.domain.statistics;

import java.sql.*;
import java.util.Date;
import java.util.List;
import java.util.ArrayList;
import java.util.Set;
import java.util.HashSet;

import javax.persistence.*;
import org.openxava.annotations.*;

import net.sf.mp.demo.conference.domain.statistics.MemberPerCountryAndConference;

/**
 *
 * <p>Title: MemberPerRoleCountryAndConference</p>
 *
 * <p>Description: Domain Object describing a MemberPerRoleCountryAndConference entity</p>
 *
 */
@Entity (name="MemberPerRoleCountryAndConference")
@Table (name="stat_mb_by_role")
@Views({
	@View(
		name="base",
		members=
        "" 	
        + "roleName  ; "
        + "number  ; "
	    + "statMbPerCtryConfId;"  
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
    @View(name="memberPerRoleCountryAndConferenceDEFAULT_VIEW", 
	   members=
          " id ;" 	
        + "roleName  ; "
        + "number  ; "
	)
})

@Tabs({
@Tab(
properties=
     " roleName "
    +",  number "
)
,
@Tab(
name = "MemberPerRoleCountryAndConferenceTab",
properties=
     " roleName "
    +",  number "
)
,
@Tab(
name = "MemberPerRoleCountryAndConferenceTabWithRef",
properties=
     " roleName "
    +",  number "
)
})

public class MemberPerRoleCountryAndConference {

    @Id @Column(name="id" ,length=345)
    private String id; 

    @Column(name="role_name",  length=45, nullable=false,  unique=false)
    @Required
    private String roleName; 
    @Column(name="number",   nullable=false,  unique=false)
    @Required
    private Long number; 

    @ManyToOne (fetch=FetchType.LAZY ) //remove optional=false to aggragate but leads to a side effect when going directly to the entity: required check is not performed=> if no set DB check constraint is raised...
    @JoinColumn(name="stat_mb_per_ctry_conf_id",  nullable=true,  unique=false  )
    @ReferenceView ("memberPerCountryAndConferenceDEFAULT_VIEW")  
    private MemberPerCountryAndConference statMbPerCtryConfId; 
    



    /**
    * Default constructor
    */
    public MemberPerRoleCountryAndConference() {
    }


    public String getId() {
        return id;
    }
	
    public void setId (String id) {
        this.id =  id;
    }
    

    public String getRoleName() {
        return roleName;
    }
	
    public void setRoleName (String roleName) {
        this.roleName =  roleName;
    }
    public Long getNumber() {
        return number;
    }
	
    public void setNumber (Long number) {
        this.number =  number;
    }

    public MemberPerCountryAndConference getStatMbPerCtryConfId () {  //
    	return statMbPerCtryConfId;
    }
	
    public void setStatMbPerCtryConfId (MemberPerCountryAndConference statMbPerCtryConfId) {
    	this.statMbPerCtryConfId = statMbPerCtryConfId;//this.statMbPerCtryConfId = memberPerCountryAndConference;
    }


}
