package net.sf.mp.demo.conference.conference;

import java.sql.*;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import javax.persistence.metamodel.SetAttribute;

import net.sf.mp.demo.conference.conference.Conference;
import net.sf.mp.demo.conference.conference.ConferenceMember;

@StaticMetamodel(ConferenceFeedback.class)
public class ConferenceFeedback_ {

    public static volatile SingularAttribute<ConferenceFeedback, Integer> id;

    public static volatile SingularAttribute<ConferenceFeedback, String> comment;
    public static volatile SingularAttribute<ConferenceFeedback, Timestamp> feedbackDate;

    public static volatile SingularAttribute<ConferenceFeedback, Conference> conferenceId;
    public static volatile SingularAttribute<ConferenceFeedback, ConferenceMember> conferenceMemberId;



}
