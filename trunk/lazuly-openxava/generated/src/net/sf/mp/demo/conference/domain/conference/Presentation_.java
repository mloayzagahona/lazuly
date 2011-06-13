package net.sf.mp.demo.conference.domain.conference;

import java.sql.*;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import javax.persistence.metamodel.SetAttribute;

import net.sf.mp.demo.conference.domain.conference.Evaluation;
import net.sf.mp.demo.conference.domain.conference.PresentationPlace;
import net.sf.mp.demo.conference.domain.conference.Speaker;

@StaticMetamodel(Presentation.class)
public class Presentation_ {

    public static volatile SingularAttribute<Presentation, Long> id;

    public static volatile SingularAttribute<Presentation, Timestamp> startTime;
    public static volatile SingularAttribute<Presentation, Timestamp> endTime;
    public static volatile SingularAttribute<Presentation, String> abstractName;
    public static volatile SingularAttribute<Presentation, String> title;
    public static volatile SingularAttribute<Presentation, String> status;
    public static volatile SingularAttribute<Presentation, Timestamp> proposalTime;

    public static volatile SingularAttribute<Presentation, PresentationPlace> presentationPlaceId;

    public static volatile SetAttribute<Presentation, Evaluation> evaluations;

    public static volatile SetAttribute<Presentation, Speaker> speakers;

}
