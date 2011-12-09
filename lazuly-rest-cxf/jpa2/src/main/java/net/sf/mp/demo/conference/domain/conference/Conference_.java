package net.sf.mp.demo.conference.domain.conference;

import java.sql.*;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import javax.persistence.metamodel.SetAttribute;

import net.sf.mp.demo.conference.domain.conference.ConferenceFeedback;
import net.sf.mp.demo.conference.domain.conference.ConferenceMember;
import net.sf.mp.demo.conference.domain.conference.Sponsor;
import net.sf.mp.demo.conference.domain.conference.Address;

@StaticMetamodel(Conference.class)
public class Conference_ {

    public static volatile SingularAttribute<Conference, Long> id;

    public static volatile SingularAttribute<Conference, String> name;
    public static volatile SingularAttribute<Conference, Date> startDate;
    public static volatile SingularAttribute<Conference, Date> endDate;

    public static volatile SingularAttribute<Conference, Address> addressId;

    public static volatile SetAttribute<Conference, ConferenceFeedback> conferenceFeedbackConferenceViaConferenceId;
    public static volatile SetAttribute<Conference, ConferenceMember> conferenceMemberConferenceViaConferenceId;
    public static volatile SetAttribute<Conference, Sponsor> sponsorConferenceViaConferenceId;


}
