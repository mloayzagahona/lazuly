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


import net.sf.mp.demo.conference.dao.face.conference.SpeakerDao;
import net.sf.mp.demo.conference.dao.face.conference.SpeakerExtDao;
import net.sf.mp.demo.conference.domain.conference.Speaker;

/**
 *
 * <p>Title: SpeakerResource</p>
 *
 * <p>Description: remote interface for SpeakerResource service </p>
 *
 */
@RequestMapping ("/rest/xml/speakers")
@Controller 
public class SpeakerResource  {

	@Autowired
	@Qualifier("speakerDao")
	SpeakerDao speakerDao;
	
	@Autowired
	@Qualifier("speakerExtDao")
	SpeakerExtDao speakerExtDao;

//MP-MANAGED-UPDATABLE-BEGINNING-DISABLE @FIND_ALL-speaker@
    @RequestMapping(method = RequestMethod.GET)
    @Transactional
	@ResponseBody
    public List<Speaker> findAll () {
		List<Speaker> r = new ArrayList<Speaker>();
        List<Speaker> l = speakerDao.searchPrototypeSpeaker(new Speaker());
		for (Speaker speaker : l) {
			r.add(speaker.flat());
		}
		return r;
    }
//MP-MANAGED-UPDATABLE-ENDING

//MP-MANAGED-UPDATABLE-BEGINNING-DISABLE @FIND_BY_ID-speaker@
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	@ResponseBody
	@Transactional
    public Speaker findById (@PathVariable ("id") java.lang.Long id) {
        Speaker _speaker = new Speaker ();
		_speaker.setId(id);
		_speaker = speakerExtDao.getFirstSpeaker(_speaker);
		if (_speaker!=null) return _speaker.flat();
		return new Speaker ();
    }
//MP-MANAGED-UPDATABLE-ENDING

	@RequestMapping(method = RequestMethod.POST)
	@Transactional(propagation=Propagation.REQUIRED, rollbackFor=Exception.class)
	@ResponseBody
	public Speaker create (
        @RequestParam("id") Long id,
        @RequestParam("conferenceMemberId") Long conferenceMemberId,
        @RequestParam("bio") String bio,
        @RequestParam("photo") String photo,
        @RequestParam("webSiteUrl") String webSiteUrl,
        @RequestParam("sponsorId") Long sponsorId,
        HttpServletResponse servletResponse
        ) throws IOException {
        Speaker _speaker = new Speaker (
           id,
           conferenceMemberId,
           bio,
           photo,
           webSiteUrl,
           sponsorId);
        return save(_speaker);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    @Transactional(propagation=Propagation.REQUIRED, rollbackFor=Exception.class)
	@ResponseBody
    public String delete (@PathVariable ("id") Long id) {
        Speaker speaker = new Speaker ();
        speaker.setId(id);
        speakerDao.deleteSpeaker(speaker);
		return "OK";
    }
	
	@RequestMapping(method = RequestMethod.PUT)
	@ResponseBody
	@Transactional(propagation=Propagation.REQUIRED, rollbackFor=Exception.class)
    public Speaker save (
        @RequestParam("id") Long id,
        @RequestParam("conferenceMemberId") Long conferenceMemberId,
        @RequestParam("bio") String bio,
        @RequestParam("photo") String photo,
        @RequestParam("webSiteUrl") String webSiteUrl,
        @RequestParam("sponsorId") Long sponsorId,
        HttpServletResponse servletResponse
        ) throws IOException {
        Speaker _speaker = new Speaker (
           id,
           conferenceMemberId,
           bio,
           photo,
           webSiteUrl,
           sponsorId);
        return save(_speaker);
    }	


	public Speaker save (Speaker speaker) {
		speakerDao.saveSpeaker(speaker);
		return speaker;
	}



}