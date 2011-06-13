package net.sf.mp.demo.conference.statistics;

import java.sql.*;
import java.util.Date;
import java.util.List;
import java.util.ArrayList;
import java.util.Set;
import java.util.HashSet;

import javax.persistence.*;

import net.sf.mp.demo.conference.statistics.MemberPerCountryAndConference;

/**
 *
 * <p>Title: MemberPerRoleCountryAndConference</p>
 *
 * <p>Description: Domain Object describing a MemberPerRoleCountryAndConference entity</p>
 *
 */
@Entity (name="MemberPerRoleCountryAndConference")
@Table (name="stat_mb_by_role")
public class MemberPerRoleCountryAndConference {

    @Id @Column(name="id" ,length=345)
    private String id;

    @Column(name="role_name",  length=45, nullable=false,  unique=false)
    private String roleName; 
    @Column(name="number",   nullable=false,  unique=false)
    private Long number; 

    @ManyToOne (fetch=FetchType.LAZY )
    @JoinColumn(name="stat_mb_per_ctry_conf_id", referencedColumnName = "id",  nullable=true,  unique=false ) 
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
    
    public MemberPerCountryAndConference getStatMbPerCtryConfId () {
    	return statMbPerCtryConfId;
    }
	
    public void setStatMbPerCtryConfId (MemberPerCountryAndConference statMbPerCtryConfId) {
    	this.statMbPerCtryConfId = statMbPerCtryConfId;
    }
    
}
