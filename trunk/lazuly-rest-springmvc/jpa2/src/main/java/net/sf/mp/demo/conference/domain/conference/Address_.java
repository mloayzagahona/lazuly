package net.sf.mp.demo.conference.domain.conference;

import java.sql.*;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import javax.persistence.metamodel.SetAttribute;

import net.sf.mp.demo.conference.domain.conference.Conference;
import net.sf.mp.demo.conference.domain.conference.ConferenceMember;
import net.sf.mp.demo.conference.domain.conference.Sponsor;
import net.sf.mp.demo.conference.domain.admin.Country;

@StaticMetamodel(Address.class)
public class Address_ {

    public static volatile SingularAttribute<Address, Long> id;

    public static volatile SingularAttribute<Address, String> street1;
    public static volatile SingularAttribute<Address, String> street2;

    public static volatile SingularAttribute<Address, Country> countryId;

    public static volatile SetAttribute<Address, Conference> conferenceAddressViaAddressId;
    public static volatile SetAttribute<Address, ConferenceMember> conferenceMemberAddressViaAddressId;
    public static volatile SetAttribute<Address, Sponsor> sponsorAddressViaAddressId;


}
