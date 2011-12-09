package net.sf.mp.demo.conference.domain.admin;

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

/**
 *
 * <p>Title: Role</p>
 *
 * <p>Description: Domain Object describing a Role entity</p>
 *
 */
@Entity (name="Role")
@Table (name="role")
@NamedQueries({
	 @NamedQuery(name="Role.findAll", query="SELECT role FROM Role role")
	,@NamedQuery(name="Role.findByName", query="SELECT role FROM Role role WHERE role.name = :name")
	,@NamedQuery(name="Role.findByNameContaining", query="SELECT role FROM Role role WHERE role.name like :name")
})
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(namespace="net.sf.mp.demo.conference.domain.admin", name = "Role")
@XmlRootElement(namespace="net.sf.mp.demo.conference.domain.admin")
public class Role extends AbstractDomainObject {
    private static final long serialVersionUID = 1L;
	
    public  static final String FIND_ALL = "Role.findAll";
    public static final String FIND_BY_NAME = "Role.findByName";
    public static final String FIND_BY_NAME_CONTAINING ="Role.findByNameContaining";
	
    @Id @Column(name="id" )
    @GeneratedValue(strategy = GenerationType.AUTO)
    @XmlElement (name="id")
    private Integer id;

//MP-MANAGED-ADDED-AREA-BEGINNING @name-field-annotation@
//MP-MANAGED-ADDED-AREA-ENDING @name-field-annotation@
//MP-MANAGED-UPDATABLE-BEGINNING-DISABLE @ATTRIBUTE-name@
    @Column(name="name",  length=45, nullable=false,  unique=false)
    @XmlElement (name="name")
    private String name; 
//MP-MANAGED-UPDATABLE-ENDING

//MP-MANAGED-UPDATABLE-BEGINNING-DISABLE @memberRoleRoleViaRoleId-field-role@
//MP-MANAGED-UPDATABLE-ENDING
    @XmlTransient
    @ManyToMany
    @JoinTable(name="MEMBER_ROLE", 
        joinColumns=@JoinColumn(name="role_id"), 
        inverseJoinColumns=@JoinColumn(name="conference_member_id") 
    )
    private Set <ConferenceMember> conferenceMemberMemberRoleViaId = new HashSet <ConferenceMember> ();

    /**
    * Default constructor
    */
    public Role() {
    }

	/**
	* All field constructor 
	*/
    public Role(
       Integer id,
       String name) {
       //primary keys
       setId (id);
       //attributes
       setName (name);
       //parents
    }

	public Role flat() {
	   return new Role(
          getId(),
          getName()
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
            .append("name", name)
	 	    ;
	} 
		
	public String toString(Object object) {
	 	return getToStringBuilder(object).toString();
	} 
	
	public String toStringWithParents() {
	    ToStringBuilder toStringBuilder = getToStringBuilder(this);
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
		if (!(object instanceof Role)) return false;
		Role role = (Role) object;
		if (role.id==null || !role.id.equals(id)) return false;
		return true;
	}    

	public Role clone() {
        Role role = flat();
        return role;
	}
	
	public void copy (Role role) {
		if (role!=null) {
			setId (role.getId());
			setName (role.getName());
		}
	}
	
	public static Role fullMask() {
		return new Role(
			integerMask__ ,
			stringMask__ 
		);	    
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



//MP-MANAGED-UPDATABLE-BEGINNING-DISABLE @memberRoleRoleViaRoleId-getter-role@
//MP-MANAGED-UPDATABLE-ENDING

    public Set<ConferenceMember> getConferenceMemberMemberRoleViaId() {
        if (conferenceMemberMemberRoleViaId == null){
            conferenceMemberMemberRoleViaId = new HashSet<ConferenceMember>();
        }
        return conferenceMemberMemberRoleViaId;
    }

    public void setConferenceMemberMemberRoleViaId (Set<ConferenceMember> conferenceMemberMemberRoleViaId) {
        this.conferenceMemberMemberRoleViaId = conferenceMemberMemberRoleViaId;
    }
    	
    public void addConferenceMemberMemberRoleViaId (ConferenceMember conferenceMemberMemberRoleViaId) {
        getConferenceMemberMemberRoleViaId().add(conferenceMemberMemberRoleViaId);
    }	    

//MP-MANAGED-ADDED-AREA-BEGINNING @implementation@
//MP-MANAGED-ADDED-AREA-ENDING @implementation@

}
