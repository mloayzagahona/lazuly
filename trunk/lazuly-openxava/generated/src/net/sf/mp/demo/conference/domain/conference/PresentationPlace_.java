package net.sf.mp.demo.conference.domain.conference;

import java.sql.*;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import javax.persistence.metamodel.SetAttribute;

import net.sf.mp.demo.conference.domain.conference.Presentation;

@StaticMetamodel(PresentationPlace.class)
public class PresentationPlace_ {

    public static volatile SingularAttribute<PresentationPlace, Long> id;

    public static volatile SingularAttribute<PresentationPlace, String> location;
    public static volatile SingularAttribute<PresentationPlace, Integer> numberOfSeats;


    public static volatile SetAttribute<PresentationPlace, Presentation> presentations;


}
