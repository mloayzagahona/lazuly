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
        + "id  ; "
        + "statMbPerCtryConfId  ; "
        + "roleName  ; "
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
     " id "
    +",  roleName "
    +",  number "
)
,
@Tab(
name = "MemberPerRoleCountryAndConferenceTab",
properties=
     " id "
    +",  roleName "
    +",  number "
)
,
@Tab(
name = "MemberPerRoleCountryAndConferenceTabWithRef",
properties=
     " id "
    +",  roleName "
    +",  number "
)
})
//MP-MANAGED-ADDED-AREA-BEGINNING @class-annotation@
//MP-MANAGED-ADDED-AREA-ENDING @class-annotation@
public class MemberPerRoleCountryAndConference {

    @Id @Column(name="id" ,length=345)
    private String id; 

//MP-MANAGED-ADDED-AREA-BEGINNING @role_name-field-annotation@
//MP-MANAGED-ADDED-AREA-ENDING @role_name-field-annotation@

//MP-MANAGED-UPDATABLE-BEGINNING-DISABLE @ATTRIBUTE-role_name@
    @Column(name="role_name",  length=45, nullable=false,  unique=false)
    @Required
    private String roleName;	
//MP-MANAGED-UPDATABLE-ENDING

//MP-MANAGED-ADDED-AREA-BEGINNING @number-field-annotation@
//MP-MANAGED-ADDED-AREA-ENDING @number-field-annotation@

//MP-MANAGED-UPDATABLE-BEGINNING-DISABLE @ATTRIBUTE-number@
    @Column(name="number",   nullable=false,  unique=false)
    @Required
    private Long number;	
//MP-MANAGED-UPDATABLE-ENDING


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
    

//MP-MANAGED-UPDATABLE-BEGINNING-DISABLE @GETTER-SETTER-role_name@
    public String getRoleName() {
        return roleName;
    }
	
    public void setRoleName (String roleName) {
        this.roleName =  roleName;
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


    public MemberPerCountryAndConference getStatMbPerCtryConfId () {  //
    	return statMbPerCtryConfId;
    }
	
    public void setStatMbPerCtryConfId (MemberPerCountryAndConference statMbPerCtryConfId) {
    	this.statMbPerCtryConfId = statMbPerCtryConfId;//this.statMbPerCtryConfId = memberPerCountryAndConference;
    }



//MP-MANAGED-ADDED-AREA-BEGINNING @implementation@
//MP-MANAGED-ADDED-AREA-ENDING @implementation@

}
