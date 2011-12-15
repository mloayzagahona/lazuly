package net.sf.mp.demo.conference.domain.admin;

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

/**
 *
 * <p>Title: Role</p>
 *
 * <p>Description: Domain Object describing a Role entity</p>
 *
 */
@Entity (name="Role")
@Table (name="role")
@Views({
	@View(
		name="base",
		members=
        "" 	
        + "id  ; "
        + "name  ; "
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
	    + "conferenceMembers;"
	),
	@View(extendsView="base",
        members=
          "" 	
	    + "conferenceMembers;"
	),
    @View(name="roleDEFAULT_VIEW", 
	   members=
          " id ;" 	
        + "name  ; "
	)
})

@Tabs({
@Tab(
properties=
     " name "
)
,
@Tab(
name = "RoleTab",
properties=
     " name "
)
,
@Tab(
name = "RoleTabWithRef",
properties=
     " name "
)
})
//MP-MANAGED-ADDED-AREA-BEGINNING @class-annotation@
//MP-MANAGED-ADDED-AREA-ENDING @class-annotation@
public class Role {

    @Hidden @Id @Column(name="id" )
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id; 

//MP-MANAGED-ADDED-AREA-BEGINNING @name-field-annotation@
//MP-MANAGED-ADDED-AREA-ENDING @name-field-annotation@

//MP-MANAGED-UPDATABLE-BEGINNING-DISABLE @ATTRIBUTE-name@
    @Column(name="name",  length=45, nullable=false,  unique=false)
    @Required
    private String name;	
//MP-MANAGED-UPDATABLE-ENDING




    @ManyToMany
    @JoinTable(name="MEMBER_ROLE",
        joinColumns=@JoinColumn(name="role_id"), 
        inverseJoinColumns=@JoinColumn(name="conference_member_id") 
    )
    private Set <ConferenceMember> conferenceMembers = new HashSet <ConferenceMember> ();


    /**
    * Default constructor
    */
    public Role() {
    }


    public Integer getId() {
        return id;
    }
	
    public void setId (Integer id) {
        this.id =  id;
    }
    

//MP-MANAGED-UPDATABLE-BEGINNING-DISABLE @GETTER-SETTER-name@
    public String getName() {
        return name;
    }
	
    public void setName (String name) {
        this.name =  name;
    } 
//MP-MANAGED-UPDATABLE-ENDING



    public Set<ConferenceMember> getConferenceMembers() {
        if (conferenceMembers == null){
            conferenceMembers = new HashSet<ConferenceMember>();
        }
        return conferenceMembers;
    }

    public void setConferenceMembers (Set<ConferenceMember> conferenceMembers) {
        this.conferenceMembers = conferenceMembers;
    }
    	
    public void addConferenceMembers (ConferenceMember conferenceMembers) {
        getConferenceMembers().add(conferenceMembers);
    }	



//MP-MANAGED-ADDED-AREA-BEGINNING @implementation@
//MP-MANAGED-ADDED-AREA-ENDING @implementation@

}
