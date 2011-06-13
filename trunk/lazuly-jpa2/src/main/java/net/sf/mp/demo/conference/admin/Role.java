package net.sf.mp.demo.conference.admin;

import java.sql.*;
import java.util.Date;
import java.util.List;
import java.util.ArrayList;
import java.util.Set;
import java.util.HashSet;

import javax.persistence.*;

import net.sf.mp.demo.conference.conference.ConferenceMember;

/**
 *
 * <p>Title: Role</p>
 *
 * <p>Description: Domain Object describing a Role entity</p>
 *
 */
@Entity (name="Role")
@Table (name="role")
public class Role {

    @Id @Column(name="id" )
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @Column(name="name",  length=45, nullable=false,  unique=false)
    private String name; 

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
    
    public String getName() {
        return name;
    }
	
    public void setName (String name) {
        this.name =  name;
    }
    
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
}
