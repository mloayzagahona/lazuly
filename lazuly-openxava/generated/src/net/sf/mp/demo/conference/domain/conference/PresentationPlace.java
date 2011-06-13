package net.sf.mp.demo.conference.domain.conference;

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
	    + "presentations { presentations };" //+ "presentationPresentationPlaceIds { presentationPresentationPlaceIds };" 
	),
	@View(extendsView="base",
        members=
          "" 	
	    + "presentations { presentations };" //+ "presentationPresentationPlaceIds { presentationPresentationPlaceIds };" 
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

public class PresentationPlace {

    @Hidden @Id @Column(name="id" )
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id; 

    @Column(name="location",  length=45,  nullable=true,  unique=false)
    private String location; 
    @Column(name="number_of_seats",    nullable=true,  unique=false)
    private Integer numberOfSeats; 


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
    

    public String getLocation() {
        return location;
    }
	
    public void setLocation (String location) {
        this.location =  location;
    }
    public Integer getNumberOfSeats() {
        return numberOfSeats;
    }
	
    public void setNumberOfSeats (Integer numberOfSeats) {
        this.numberOfSeats =  numberOfSeats;
    }


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
    

}
