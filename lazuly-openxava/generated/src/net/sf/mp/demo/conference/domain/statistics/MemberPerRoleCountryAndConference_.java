package net.sf.mp.demo.conference.domain.statistics;

import java.sql.*;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import javax.persistence.metamodel.SetAttribute;

import net.sf.mp.demo.conference.domain.statistics.MemberPerCountryAndConference;

@StaticMetamodel(MemberPerRoleCountryAndConference.class)
public class MemberPerRoleCountryAndConference_ {

    public static volatile SingularAttribute<MemberPerRoleCountryAndConference, String> id;

    public static volatile SingularAttribute<MemberPerRoleCountryAndConference, String> roleName;
    public static volatile SingularAttribute<MemberPerRoleCountryAndConference, Long> number;

    public static volatile SingularAttribute<MemberPerRoleCountryAndConference, MemberPerCountryAndConference> statMbPerCtryConfId;



}
