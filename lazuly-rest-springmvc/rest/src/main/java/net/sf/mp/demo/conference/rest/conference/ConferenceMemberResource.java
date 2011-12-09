package net.sf.mp.demo.conference.rest.conference;


import java.util.Date;
import java.util.List;
import java.util.ArrayList;
import java.io.*;
import java.sql.*;

import java.io.IOException;
import javax.servlet.http.*;
import java.util.List;
import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RequestParam;


import net.sf.mp.demo.conference.dao.face.conference.ConferenceMemberDao;
import net.sf.mp.demo.conference.dao.face.conference.ConferenceMemberExtDao;
import net.sf.mp.demo.conference.domain.conference.ConferenceMember;

/**
 *
 * <p>Title: ConferenceMemberResource</p>
 *
 * <p>Description: remote interface for ConferenceMemberResource service </p>
 *
 */
@RequestMapping ("/rest/xml/conferencemembers")
@Controller 
public class ConferenceMemberResource  {

	@Autowired
	@Qualifier("conferenceMemberDao")
	ConferenceMemberDao conferenceMemberDao;
	
	@Autowired
	@Qualifier("conferenceMemberExtDao")
	ConferenceMemberExtDao conferenceMemberExtDao;

//MP-MANAGED-UPDATABLE-BEGINNING-DISABLE @FIND_ALL-conference_member@
    @RequestMapping(method = RequestMethod.GET)
    @Transactional
	@ResponseBody
    public List<ConferenceMember> findAll () {
		List<ConferenceMember> r = new ArrayList<ConferenceMember>();
        List<ConferenceMember> l = conferenceMemberDao.searchPrototypeConferenceMember(new ConferenceMember());
		for (ConferenceMember conferenceMember : l) {
			r.add(conferenceMember.flat());
		}
		return r;
    }
//MP-MANAGED-UPDATABLE-ENDING

//MP-MANAGED-UPDATABLE-BEGINNING-DISABLE @FIND_BY_ID-conference_member@
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	@ResponseBody
	@Transactional
    public ConferenceMember findById (@PathVariable ("id") java.lang.Long id) {
        ConferenceMember _conferenceMember = new ConferenceMember ();
		_conferenceMember.setId(id);
		_conferenceMember = conferenceMemberExtDao.getFirstConferenceMember(_conferenceMember);
		if (_conferenceMember!=null) return _conferenceMember.flat();
		return new ConferenceMember ();
    }
//MP-MANAGED-UPDATABLE-ENDING

	@RequestMapping(method = RequestMethod.POST)
	@Transactional(propagation=Propagation.REQUIRED, rollbackFor=Exception.class)
	@ResponseBody
	public ConferenceMember create (
        @RequestParam("id") Long id,
        @RequestParam("conferenceId") Long conferenceId,
        @RequestParam("firstName") String firstName,
        @RequestParam("lastName") String lastName,
        @RequestParam("email") String email,
        @RequestParam("addressId") Long addressId,
        @RequestParam("status") String status,
        HttpServletResponse servletResponse
        ) throws IOException {
        ConferenceMember _conferenceMember = new ConferenceMember (
           id,
           conferenceId,
           firstName,
           lastName,
           email,
           addressId,
           status);
        return save(_conferenceMember);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    @Transactional(propagation=Propagation.REQUIRED, rollbackFor=Exception.class)
	@ResponseBody
    public String delete (@PathVariable ("id") Long id) {
        ConferenceMember conferenceMember = new ConferenceMember ();
        conferenceMember.setId(id);
        conferenceMemberDao.deleteConferenceMember(conferenceMember);
		return "OK";
    }
	
	@RequestMapping(method = RequestMethod.PUT)
	@ResponseBody
	@Transactional(propagation=Propagation.REQUIRED, rollbackFor=Exception.class)
    public ConferenceMember save (
        @RequestParam("id") Long id,
        @RequestParam("conferenceId") Long conferenceId,
        @RequestParam("firstName") String firstName,
        @RequestParam("lastName") String lastName,
        @RequestParam("email") String email,
        @RequestParam("addressId") Long addressId,
        @RequestParam("status") String status,
        HttpServletResponse servletResponse
        ) throws IOException {
        ConferenceMember _conferenceMember = new ConferenceMember (
           id,
           conferenceId,
           firstName,
           lastName,
           email,
           addressId,
           status);
        return save(_conferenceMember);
    }	


	public ConferenceMember save (ConferenceMember conferenceMember) {
		conferenceMemberDao.saveConferenceMember(conferenceMember);
		return conferenceMember;
	}



}