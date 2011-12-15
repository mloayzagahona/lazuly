package net.sf.mp.demo.conference.domain.conference;

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

import net.sf.mp.demo.conference.domain.conference.Presentation;

/**
 *
 * <p>Title: PresentationPlace</p>
 *
 * <p>Description: Domain Object describing a PresentationPlace entity</p>
 *
 */
@Entity (name="PresentationPlace")
@Table (name="presentation_place")
@Views({
	@View(
		name="base",
		members=
        "" 	
        + "id  ; "
        + "location  ; "
        + "numberOfSeats  ; "
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
	    + "presentations { presentations };" //+ "${link}${columnclass}s { ${link}${columnclass}s };" 
	),
	@View(extendsView="base",
        members=
          "" 	
	    + "presentations { presentations };" //+ "${link}${columnclass}s { ${link}${columnclass}s };" 
	),
    @View(name="presentationPlaceDEFAULT_VIEW", 
	   members=
          " id ;" 	
        + "location  ; "
        + "numberOfSeats  ; "
	)
})

@Tabs({
@Tab(
properties=
     " location "
    +",  numberOfSeats "
)
,
@Tab(
name = "PresentationPlaceTab",
properties=
     " location "
    +",  numberOfSeats "
)
,
@Tab(
name = "PresentationPlaceTabWithRef",
properties=
     " location "
    +",  numberOfSeats "
)
})
//MP-MANAGED-ADDED-AREA-BEGINNING @class-annotation@
//MP-MANAGED-ADDED-AREA-ENDING @class-annotation@
public class PresentationPlace {

    @Hidden @Id @Column(name="id" )
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id; 

//MP-MANAGED-ADDED-AREA-BEGINNING @location-field-annotation@
//MP-MANAGED-ADDED-AREA-ENDING @location-field-annotation@

//MP-MANAGED-UPDATABLE-BEGINNING-DISABLE @ATTRIBUTE-location@
    @Column(name="location",  length=45,  nullable=true,  unique=false)
    private String location;	
//MP-MANAGED-UPDATABLE-ENDING

//MP-MANAGED-ADDED-AREA-BEGINNING @number_of_seats-field-annotation@
//MP-MANAGED-ADDED-AREA-ENDING @number_of_seats-field-annotation@

//MP-MANAGED-UPDATABLE-BEGINNING-DISABLE @ATTRIBUTE-number_of_seats@
    @Column(name="number_of_seats",    nullable=true,  unique=false)
    private Integer numberOfSeats;	
//MP-MANAGED-UPDATABLE-ENDING



    @OneToMany (targetEntity=net.sf.mp.demo.conference.domain.conference.Presentation.class, fetch=FetchType.LAZY, mappedBy="presentationPlaceId", cascade=CascadeType.REMOVE)//, cascade=CascadeType.ALL)
    private Set <Presentation> presentations = new HashSet<Presentation>(); 
   


    /**
    * Default constructor
    */
    public PresentationPlace() {
    }


    public Long getId() {
        return id;
    }
	
    public void setId (Long id) {
        this.id =  id;
    }
    

//MP-MANAGED-UPDATABLE-BEGINNING-DISABLE @GETTER-SETTER-location@
    public String getLocation() {
        return location;
    }
	
    public void setLocation (String location) {
        this.location =  location;
    } 
//MP-MANAGED-UPDATABLE-ENDING

//MP-MANAGED-UPDATABLE-BEGINNING-DISABLE @GETTER-SETTER-number_of_seats@
    public Integer getNumberOfSeats() {
        return numberOfSeats;
    }
	
    public void setNumberOfSeats (Integer numberOfSeats) {
        this.numberOfSeats =  numberOfSeats;
    } 
//MP-MANAGED-UPDATABLE-ENDING



    public Set<Presentation> getPresentations() {
        if (presentations == null){
            presentations = new HashSet<Presentation>();
        }
        return presentations;
    }

    public void setPresentations (Set<Presentation> presentations) {
        this.presentations = presentations;
    }	
    
    public void addPresentations (Presentation presentation) {
    	    getPresentations().add(presentation);
    }
    


//MP-MANAGED-ADDED-AREA-BEGINNING @implementation@
//MP-MANAGED-ADDED-AREA-ENDING @implementation@

}
