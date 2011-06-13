package net.sf.mp.demo.conference.conference;

import java.sql.*;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import javax.persistence.metamodel.SetAttribute;

import net.sf.mp.demo.conference.conference.Evaluation;
import net.sf.mp.demo.conference.conference.PresentationPlace;
import net.sf.mp.demo.conference.conference.Speaker;
import net.sf.mp.demo.conference.enumeration.conference.PresentationStatusEnum;

@StaticMetamodel(Presentation.class)
public class Presentation_ {

    public static volatile SingularAttribute<Presentation, Long> id;

    public static volatile SingularAttribute<Presentation, Timestamp> startTime;
    public static volatile SingularAttribute<Presentation, Timestamp> endTime;
    public static volatile SingularAttribute<Presentation, String> abstractName;
    public static volatile SingularAttribute<Presentation, String> title;
    public static volatile SingularAttribute<Presentation, PresentationStatusEnum> status;
    public static volatile SingularAttribute<Presentation, Timestamp> proposalTime;

    public static volatile SingularAttribute<Presentation, PresentationPlace> presentationPlaceId;

    public static volatile SetAttribute<Presentation, Evaluation> evaluations;

    public static volatile SetAttribute<Presentation, Speaker> speakers;

}
