package net.sf.mp.demo.conference.conference;

import java.sql.*;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import javax.persistence.metamodel.SetAttribute;

import net.sf.mp.demo.conference.conference.Speaker;
import net.sf.mp.demo.conference.conference.Address;
import net.sf.mp.demo.conference.conference.Conference;
import net.sf.mp.demo.conference.enumeration.conference.SponsorPrivilegeTypeEnum;
import net.sf.mp.demo.conference.enumeration.conference.SponsorStatusEnum;

@StaticMetamodel(Sponsor.class)
public class Sponsor_ {

    public static volatile SingularAttribute<Sponsor, Long> id;

    public static volatile SingularAttribute<Sponsor, String> name;
    public static volatile SingularAttribute<Sponsor, SponsorPrivilegeTypeEnum> privilegeType;
    public static volatile SingularAttribute<Sponsor, SponsorStatusEnum> status;

    public static volatile SingularAttribute<Sponsor, Address> addressId;
    public static volatile SingularAttribute<Sponsor, Conference> conferenceId;

    public static volatile SetAttribute<Sponsor, Speaker> speakers;


}
