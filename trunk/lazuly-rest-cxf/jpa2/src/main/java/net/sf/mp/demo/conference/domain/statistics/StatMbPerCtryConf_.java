package net.sf.mp.demo.conference.domain.statistics;

import java.sql.*;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import javax.persistence.metamodel.SetAttribute;


@StaticMetamodel(StatMbPerCtryConf.class)
public class StatMbPerCtryConf_ {

    public static volatile SingularAttribute<StatMbPerCtryConf, String> id;

    public static volatile SingularAttribute<StatMbPerCtryConf, String> country;
    public static volatile SingularAttribute<StatMbPerCtryConf, String> conferenceName;
    public static volatile SingularAttribute<StatMbPerCtryConf, Long> number;




}
