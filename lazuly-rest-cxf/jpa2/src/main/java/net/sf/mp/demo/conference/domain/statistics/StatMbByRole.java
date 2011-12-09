package net.sf.mp.demo.conference.domain.statistics;

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

/**
 *
 * <p>Title: StatMbByRole</p>
 *
 * <p>Description: Domain Object describing a StatMbByRole entity</p>
 *
 */
@Entity (name="StatMbByRole")
@Table (name="stat_mb_by_role")
@NamedQueries({
	 @NamedQuery(name="StatMbByRole.findAll", query="SELECT statMbByRole FROM StatMbByRole statMbByRole")
	,@NamedQuery(name="StatMbByRole.findByStatMbPerCtryConfId", query="SELECT statMbByRole FROM StatMbByRole statMbByRole WHERE statMbByRole.statMbPerCtryConfId = :statMbPerCtryConfId")
	,@NamedQuery(name="StatMbByRole.findByStatMbPerCtryConfIdContaining", query="SELECT statMbByRole FROM StatMbByRole statMbByRole WHERE statMbByRole.statMbPerCtryConfId like :statMbPerCtryConfId")
	,@NamedQuery(name="StatMbByRole.findByRoleName", query="SELECT statMbByRole FROM StatMbByRole statMbByRole WHERE statMbByRole.roleName = :roleName")
	,@NamedQuery(name="StatMbByRole.findByRoleNameContaining", query="SELECT statMbByRole FROM StatMbByRole statMbByRole WHERE statMbByRole.roleName like :roleName")
	,@NamedQuery(name="StatMbByRole.findByNumber", query="SELECT statMbByRole FROM StatMbByRole statMbByRole WHERE statMbByRole.number = :number")
})
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(namespace="net.sf.mp.demo.conference.domain.statistics", name = "StatMbByRole")
@XmlRootElement(namespace="net.sf.mp.demo.conference.domain.statistics")
public class StatMbByRole extends AbstractDomainObject {
    private static final long serialVersionUID = 1L;
	
    public  static final String FIND_ALL = "StatMbByRole.findAll";
    public static final String FIND_BY_STATMBPERCTRYCONFID = "StatMbByRole.findByStatMbPerCtryConfId";
    public static final String FIND_BY_STATMBPERCTRYCONFID_CONTAINING ="StatMbByRole.findByStatMbPerCtryConfIdContaining";
    public static final String FIND_BY_ROLENAME = "StatMbByRole.findByRoleName";
    public static final String FIND_BY_ROLENAME_CONTAINING ="StatMbByRole.findByRoleNameContaining";
    public static final String FIND_BY_NUMBER = "StatMbByRole.findByNumber";
	
    @Id @Column(name="id" ,length=345)
    @XmlElement (name="id")
    private String id;

//MP-MANAGED-ADDED-AREA-BEGINNING @stat_mb_per_ctry_conf_id-field-annotation@
//MP-MANAGED-ADDED-AREA-ENDING @stat_mb_per_ctry_conf_id-field-annotation@
//MP-MANAGED-UPDATABLE-BEGINNING-DISABLE @ATTRIBUTE-stat_mb_per_ctry_conf_id@
    @Column(name="stat_mb_per_ctry_conf_id",  length=300,  nullable=true,  unique=false)
    @XmlElement (name="stat-mb-per-ctry-conf-id")
    private String statMbPerCtryConfId; 
//MP-MANAGED-UPDATABLE-ENDING

//MP-MANAGED-ADDED-AREA-BEGINNING @role_name-field-annotation@
//MP-MANAGED-ADDED-AREA-ENDING @role_name-field-annotation@
//MP-MANAGED-UPDATABLE-BEGINNING-DISABLE @ATTRIBUTE-role_name@
    @Column(name="role_name",  length=45, nullable=false,  unique=false)
    @XmlElement (name="role-name")
    private String roleName; 
//MP-MANAGED-UPDATABLE-ENDING

//MP-MANAGED-ADDED-AREA-BEGINNING @number-field-annotation@
//MP-MANAGED-ADDED-AREA-ENDING @number-field-annotation@
//MP-MANAGED-UPDATABLE-BEGINNING-DISABLE @ATTRIBUTE-number@
    @Column(name="number",   nullable=false,  unique=false)
    @XmlElement (name="number")
    private Long number; 
//MP-MANAGED-UPDATABLE-ENDING

    /**
    * Default constructor
    */
    public StatMbByRole() {
    }

	/**
	* All field constructor 
	*/
    public StatMbByRole(
       String id,
       String statMbPerCtryConfId,
       String roleName,
       Long number) {
       //primary keys
       setId (id);
       //attributes
       setStatMbPerCtryConfId (statMbPerCtryConfId);
       setRoleName (roleName);
       setNumber (number);
       //parents
    }

	public StatMbByRole flat() {
	   return new StatMbByRole(
          getId(),
          getStatMbPerCtryConfId(),
          getRoleName(),
          getNumber()
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
            .append("statMbPerCtryConfId", statMbPerCtryConfId)
            .append("roleName", roleName)
            .append("number", number)
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
		if (!(object instanceof StatMbByRole)) return false;
		StatMbByRole statmbbyrole = (StatMbByRole) object;
		if (statmbbyrole.id==null || !statmbbyrole.id.equals(id)) return false;
		return true;
	}    

	public StatMbByRole clone() {
        StatMbByRole statmbbyrole = flat();
        return statmbbyrole;
	}
	
	public void copy (StatMbByRole statmbbyrole) {
		if (statmbbyrole!=null) {
			setId (statmbbyrole.getId());
			setStatMbPerCtryConfId (statmbbyrole.getStatMbPerCtryConfId());
			setRoleName (statmbbyrole.getRoleName());
			setNumber (statmbbyrole.getNumber());
		}
	}
	
	public static StatMbByRole fullMask() {
		return new StatMbByRole(
			stringMask__ ,
			stringMask__ ,
			stringMask__ ,
			longMask__ 
		);	    
	}

    public String getId() {
        return id;
    }
	
    public void setId (String id) {
        this.id =  id;
    }
    
//MP-MANAGED-UPDATABLE-BEGINNING-DISABLE @GETTER-SETTER-stat_mb_per_ctry_conf_id@
    public String getStatMbPerCtryConfId() {
        return statMbPerCtryConfId;
    }
	
    public void setStatMbPerCtryConfId (String statMbPerCtryConfId) {
        this.statMbPerCtryConfId =  statMbPerCtryConfId;
    }    
//MP-MANAGED-UPDATABLE-ENDING

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





//MP-MANAGED-ADDED-AREA-BEGINNING @implementation@
//MP-MANAGED-ADDED-AREA-ENDING @implementation@

}
