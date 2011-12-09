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


import net.sf.mp.demo.conference.dao.face.conference.ConferenceFeedbackDao;
import net.sf.mp.demo.conference.dao.face.conference.ConferenceFeedbackExtDao;
import net.sf.mp.demo.conference.domain.conference.ConferenceFeedback;

/**
 *
 * <p>Title: ConferenceFeedbackResource</p>
 *
 * <p>Description: remote interface for ConferenceFeedbackResource service </p>
 *
 */
@RequestMapping ("/rest/xml/conferencefeedbacks")
@Controller 
public class ConferenceFeedbackResource  {

	@Autowired
	@Qualifier("conferenceFeedbackDao")
	ConferenceFeedbackDao conferenceFeedbackDao;
	
	@Autowired
	@Qualifier("conferenceFeedbackExtDao")
	ConferenceFeedbackExtDao conferenceFeedbackExtDao;

//MP-MANAGED-UPDATABLE-BEGINNING-DISABLE @FIND_ALL-conference_feedback@
    @RequestMapping(method = RequestMethod.GET)
    @Transactional
	@ResponseBody
    public List<ConferenceFeedback> findAll () {
		List<ConferenceFeedback> r = new ArrayList<ConferenceFeedback>();
        List<ConferenceFeedback> l = conferenceFeedbackDao.searchPrototypeConferenceFeedback(new ConferenceFeedback());
		for (ConferenceFeedback conferenceFeedback : l) {
			r.add(conferenceFeedback.flat());
		}
		return r;
    }
//MP-MANAGED-UPDATABLE-ENDING

//MP-MANAGED-UPDATABLE-BEGINNING-DISABLE @FIND_BY_ID-conference_feedback@
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	@ResponseBody
	@Transactional
    public ConferenceFeedback findById (@PathVariable ("id") java.lang.Integer id) {
        ConferenceFeedback _conferenceFeedback = new ConferenceFeedback ();
		_conferenceFeedback.setId(id);
		_conferenceFeedback = conferenceFeedbackExtDao.getFirstConferenceFeedback(_conferenceFeedback);
		if (_conferenceFeedback!=null) return _conferenceFeedback.flat();
		return new ConferenceFeedback ();
    }
//MP-MANAGED-UPDATABLE-ENDING

	@RequestMapping(method = RequestMethod.POST)
	@Transactional(propagation=Propagation.REQUIRED, rollbackFor=Exception.class)
	@ResponseBody
	public ConferenceFeedback create (
        @RequestParam("id") Integer id,
        @RequestParam("comment") String comment,
        @RequestParam("feedbackDate") Timestamp feedbackDate,
        @RequestParam("conferenceId") Long conferenceId,
        @RequestParam("conferenceMemberId") Long conferenceMemberId,
        HttpServletResponse servletResponse
        ) throws IOException {
        ConferenceFeedback _conferenceFeedback = new ConferenceFeedback (
           id,
           comment,
           feedbackDate,
           conferenceId,
           conferenceMemberId);
        return save(_conferenceFeedback);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    @Transactional(propagation=Propagation.REQUIRED, rollbackFor=Exception.class)
	@ResponseBody
    public String delete (@PathVariable ("id") Integer id) {
        ConferenceFeedback conferenceFeedback = new ConferenceFeedback ();
        conferenceFeedback.setId(id);
        conferenceFeedbackDao.deleteConferenceFeedback(conferenceFeedback);
		return "OK";
    }
	
	@RequestMapping(method = RequestMethod.PUT)
	@ResponseBody
	@Transactional(propagation=Propagation.REQUIRED, rollbackFor=Exception.class)
    public ConferenceFeedback save (
        @RequestParam("id") Integer id,
        @RequestParam("comment") String comment,
        @RequestParam("feedbackDate") Timestamp feedbackDate,
        @RequestParam("conferenceId") Long conferenceId,
        @RequestParam("conferenceMemberId") Long conferenceMemberId,
        HttpServletResponse servletResponse
        ) throws IOException {
        ConferenceFeedback _conferenceFeedback = new ConferenceFeedback (
           id,
           comment,
           feedbackDate,
           conferenceId,
           conferenceMemberId);
        return save(_conferenceFeedback);
    }	


	public ConferenceFeedback save (ConferenceFeedback conferenceFeedback) {
		conferenceFeedbackDao.saveConferenceFeedback(conferenceFeedback);
		return conferenceFeedback;
	}



}