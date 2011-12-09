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


import net.sf.mp.demo.conference.dao.face.conference.ConferenceDao;
import net.sf.mp.demo.conference.dao.face.conference.ConferenceExtDao;
import net.sf.mp.demo.conference.domain.conference.Conference;

/**
 *
 * <p>Title: ConferenceResource</p>
 *
 * <p>Description: remote interface for ConferenceResource service </p>
 *
 */
@RequestMapping ("/rest/xml/conferences")
@Controller 
public class ConferenceResource  {

	@Autowired
	@Qualifier("conferenceDao")
	ConferenceDao conferenceDao;
	
	@Autowired
	@Qualifier("conferenceExtDao")
	ConferenceExtDao conferenceExtDao;

//MP-MANAGED-UPDATABLE-BEGINNING-DISABLE @FIND_ALL-conference@
    @RequestMapping(method = RequestMethod.GET)
    @Transactional
	@ResponseBody
    public List<Conference> findAll () {
		List<Conference> r = new ArrayList<Conference>();
        List<Conference> l = conferenceDao.searchPrototypeConference(new Conference());
		for (Conference conference : l) {
			r.add(conference.flat());
		}
		return r;
    }
//MP-MANAGED-UPDATABLE-ENDING

//MP-MANAGED-UPDATABLE-BEGINNING-DISABLE @FIND_BY_ID-conference@
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	@ResponseBody
	@Transactional
    public Conference findById (@PathVariable ("id") java.lang.Long id) {
        Conference _conference = new Conference ();
		_conference.setId(id);
		_conference = conferenceExtDao.getFirstConference(_conference);
		if (_conference!=null) return _conference.flat();
		return new Conference ();
    }
//MP-MANAGED-UPDATABLE-ENDING

	@RequestMapping(method = RequestMethod.POST)
	@Transactional(propagation=Propagation.REQUIRED, rollbackFor=Exception.class)
	@ResponseBody
	public Conference create (
        @RequestParam("id") Long id,
        @RequestParam("name") String name,
        @RequestParam("startDate") Date startDate,
        @RequestParam("endDate") Date endDate,
        @RequestParam("addressId") Long addressId,
        HttpServletResponse servletResponse
        ) throws IOException {
        Conference _conference = new Conference (
           id,
           name,
           startDate,
           endDate,
           addressId);
        return save(_conference);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    @Transactional(propagation=Propagation.REQUIRED, rollbackFor=Exception.class)
	@ResponseBody
    public String delete (@PathVariable ("id") Long id) {
        Conference conference = new Conference ();
        conference.setId(id);
        conferenceDao.deleteConference(conference);
		return "OK";
    }
	
	@RequestMapping(method = RequestMethod.PUT)
	@ResponseBody
	@Transactional(propagation=Propagation.REQUIRED, rollbackFor=Exception.class)
    public Conference save (
        @RequestParam("id") Long id,
        @RequestParam("name") String name,
        @RequestParam("startDate") Date startDate,
        @RequestParam("endDate") Date endDate,
        @RequestParam("addressId") Long addressId,
        HttpServletResponse servletResponse
        ) throws IOException {
        Conference _conference = new Conference (
           id,
           name,
           startDate,
           endDate,
           addressId);
        return save(_conference);
    }	


	public Conference save (Conference conference) {
		conferenceDao.saveConference(conference);
		return conference;
	}



}