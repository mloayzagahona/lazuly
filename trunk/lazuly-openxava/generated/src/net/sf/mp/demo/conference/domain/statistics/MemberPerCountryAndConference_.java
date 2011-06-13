package net.sf.mp.demo.conference.domain.statistics;

import java.sql.*;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import javax.persistence.metamodel.SetAttribute;

import net.sf.mp.demo.conference.domain.statistics.MemberPerRoleCountryAndConference;

@StaticMetamodel(MemberPerCountryAndConference.class)
public class MemberPerCountryAndConference_ {

    public static volatile SingularAttribute<MemberPerCountryAndConference, String> id;

    public static volatile SingularAttribute<MemberPerCountryAndConference, String> country;
    public static volatile SingularAttribute<MemberPerCountryAndConference, String> conferenceName;
    public static volatile SingularAttribute<MemberPerCountryAndConference, Long> number;


    public static volatile SetAttribute<MemberPerCountryAndConference, MemberPerRoleCountryAndConference> memberPerRoleCountryAndConferences;


}
