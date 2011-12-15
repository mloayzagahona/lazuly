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

import net.sf.mp.demo.conference.domain.conference.Address;

/**
 *
 * <p>Title: Country</p>
 *
 * <p>Description: Domain Object describing a Country entity</p>
 *
 */
@Entity (name="Country")
@Table (name="country")
@Views({
	@View(
		name="base",
		members=
        "" 	
        + "id  ; "
        + "name  ; "
        + "isoName  ; "
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
    @View(name="countryDEFAULT_VIEW", 
	   members=
          " id ;" 	
        + "name  ; "
        + "isoName  ; "
	)
})

@Tabs({
@Tab(
properties=
     " name "
    +",  isoName "
)
,
@Tab(
name = "CountryTab",
properties=
     " name "
    +",  isoName "
)
,
@Tab(
name = "CountryTabWithRef",
properties=
     " name "
    +",  isoName "
)
})
@org.hibernate.annotations.Cache(usage = org.hibernate.annotations.CacheConcurrencyStrategy.READ_WRITE)
//MP-MANAGED-ADDED-AREA-BEGINNING @class-annotation@
//MP-MANAGED-ADDED-AREA-ENDING @class-annotation@
public class Country {

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

//MP-MANAGED-ADDED-AREA-BEGINNING @iso_name-field-annotation@
//MP-MANAGED-ADDED-AREA-ENDING @iso_name-field-annotation@

//MP-MANAGED-UPDATABLE-BEGINNING-DISABLE @ATTRIBUTE-iso_name@
    @Column(name="iso_name",  length=45, nullable=false,  unique=false)
    @Required
    private String isoName;	
//MP-MANAGED-UPDATABLE-ENDING



    @OneToMany (targetEntity=net.sf.mp.demo.conference.domain.conference.Address.class, fetch=FetchType.LAZY, mappedBy="countryId", cascade=CascadeType.REMOVE)//, cascade=CascadeType.ALL)
    private Set <Address> addresses = new HashSet<Address>(); 
   


    /**
    * Default constructor
    */
    public Country() {
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

//MP-MANAGED-UPDATABLE-BEGINNING-DISABLE @GETTER-SETTER-iso_name@
    public String getIsoName() {
        return isoName;
    }
	
    public void setIsoName (String isoName) {
        this.isoName =  isoName;
    } 
//MP-MANAGED-UPDATABLE-ENDING



    public Set<Address> getAddresses() {
        if (addresses == null){
            addresses = new HashSet<Address>();
        }
        return addresses;
    }

    public void setAddresses (Set<Address> addresses) {
        this.addresses = addresses;
    }	
    
    public void addAddresses (Address address) {
    	    getAddresses().add(address);
    }
    


//MP-MANAGED-ADDED-AREA-BEGINNING @implementation@
//MP-MANAGED-ADDED-AREA-ENDING @implementation@

}
