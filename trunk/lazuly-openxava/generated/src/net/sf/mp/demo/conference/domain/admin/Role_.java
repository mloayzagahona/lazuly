package net.sf.mp.demo.conference.domain.admin;

import java.sql.*;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import javax.persistence.metamodel.SetAttribute;

import net.sf.mp.demo.conference.domain.conference.ConferenceMember;

@StaticMetamodel(Role.class)
public class Role_ {

    public static volatile SingularAttribute<Role, Integer> id;

    public static volatile SingularAttribute<Role, String> name;



    public static volatile SetAttribute<Role, ConferenceMember> conferenceMembers;

}
