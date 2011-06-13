package net.sf.mp.demo.conference.admin;

import java.sql.*;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import javax.persistence.metamodel.SetAttribute;

import net.sf.mp.demo.conference.conference.Address;

@StaticMetamodel(Country.class)
public class Country_ {

    public static volatile SingularAttribute<Country, Integer> id;

    public static volatile SingularAttribute<Country, String> name;
    public static volatile SingularAttribute<Country, String> isoName;


    public static volatile SetAttribute<Country, Address> addresses;


}
