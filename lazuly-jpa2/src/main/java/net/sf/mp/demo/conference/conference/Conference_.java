package net.sf.mp.demo.conference.conference;

import java.sql.*;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import javax.persistence.metamodel.SetAttribute;

import net.sf.mp.demo.conference.conference.ConferenceFeedback;
import net.sf.mp.demo.conference.conference.ConferenceMember;
import net.sf.mp.demo.conference.conference.Sponsor;
import net.sf.mp.demo.conference.conference.Address;

@StaticMetamodel(Conference.class)
public class Conference_ {

    public static volatile SingularAttribute<Conference, Long> id;

    public static volatile SingularAttribute<Conference, String> name;
    public static volatile SingularAttribute<Conference, Date> startDate;
    public static volatile SingularAttribute<Conference, Date> endDate;

    public static volatile SingularAttribute<Conference, Address> addressId;

    public static volatile SetAttribute<Conference, ConferenceFeedback> conferenceFeedbacks;
    public static volatile SetAttribute<Conference, ConferenceMember> conferenceMembers;
    public static volatile SetAttribute<Conference, Sponsor> sponsors;


}
