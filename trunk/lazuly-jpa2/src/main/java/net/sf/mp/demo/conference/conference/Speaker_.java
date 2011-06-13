package net.sf.mp.demo.conference.conference;

import java.sql.*;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import javax.persistence.metamodel.SetAttribute;

import net.sf.mp.demo.conference.conference.ConferenceMember;
import net.sf.mp.demo.conference.conference.Sponsor;
import net.sf.mp.demo.conference.conference.Presentation;

@StaticMetamodel(Speaker.class)
public class Speaker_ {

    public static volatile SingularAttribute<Speaker, Long> id;

    public static volatile SingularAttribute<Speaker, String> bio;
    public static volatile SingularAttribute<Speaker, String> photo;
    public static volatile SingularAttribute<Speaker, String> webSiteUrl;

    public static volatile SingularAttribute<Speaker, ConferenceMember> conferenceMemberId;
    public static volatile SingularAttribute<Speaker, Sponsor> sponsorId;


    public static volatile SetAttribute<Speaker, Presentation> presentations;

}
