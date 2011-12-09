package net.sf.mp.demo.conference.domain.conference;

import java.sql.*;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import javax.persistence.metamodel.SetAttribute;

import net.sf.mp.demo.conference.domain.conference.Speaker;
import net.sf.mp.demo.conference.domain.conference.Address;
import net.sf.mp.demo.conference.domain.conference.Conference;

@StaticMetamodel(Sponsor.class)
public class Sponsor_ {

    public static volatile SingularAttribute<Sponsor, Long> id;

    public static volatile SingularAttribute<Sponsor, String> name;
    public static volatile SingularAttribute<Sponsor, String> privilegeType;
    public static volatile SingularAttribute<Sponsor, String> status;

    public static volatile SingularAttribute<Sponsor, Address> addressId;
    public static volatile SingularAttribute<Sponsor, Conference> conferenceId;

    public static volatile SetAttribute<Sponsor, Speaker> speakerSponsorViaSponsorId;


}
