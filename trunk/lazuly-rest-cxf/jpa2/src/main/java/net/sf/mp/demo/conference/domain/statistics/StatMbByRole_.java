package net.sf.mp.demo.conference.domain.statistics;

import java.sql.*;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import javax.persistence.metamodel.SetAttribute;


@StaticMetamodel(StatMbByRole.class)
public class StatMbByRole_ {

    public static volatile SingularAttribute<StatMbByRole, String> id;

    public static volatile SingularAttribute<StatMbByRole, String> statMbPerCtryConfId;
    public static volatile SingularAttribute<StatMbByRole, String> roleName;
    public static volatile SingularAttribute<StatMbByRole, Long> number;




}
