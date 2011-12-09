package net.sf.mp.demo.conference.domain.conference;

import java.sql.*;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import javax.persistence.metamodel.SetAttribute;

import net.sf.mp.demo.conference.domain.conference.ConferenceFeedback;
import net.sf.mp.demo.conference.domain.conference.Evaluation;
import net.sf.mp.demo.conference.domain.conference.Speaker;
import net.sf.mp.demo.conference.domain.conference.Conference;
import net.sf.mp.demo.conference.domain.conference.Address;
import net.sf.mp.demo.conference.domain.admin.Role;

@StaticMetamodel(ConferenceMember.class)
public class ConferenceMember_ {

    public static volatile SingularAttribute<ConferenceMember, Long> id;

    public static volatile SingularAttribute<ConferenceMember, String> firstName;
    public static volatile SingularAttribute<ConferenceMember, String> lastName;
    public static volatile SingularAttribute<ConferenceMember, String> email;
    public static volatile SingularAttribute<ConferenceMember, String> status;

    public static volatile SingularAttribute<ConferenceMember, Conference> conferenceId;
    public static volatile SingularAttribute<ConferenceMember, Address> addressId;

    public static volatile SetAttribute<ConferenceMember, ConferenceFeedback> conferenceFeedbackConferenceMemberViaConferenceMemberId;
    public static volatile SetAttribute<ConferenceMember, Evaluation> evaluationConferenceMemberViaConferenceMemberId;
    public static volatile SetAttribute<ConferenceMember, Speaker> speakerConferenceMemberViaConferenceMemberId;

    public static volatile SetAttribute<ConferenceMember, Role> roleMemberRoleViaId;

}
