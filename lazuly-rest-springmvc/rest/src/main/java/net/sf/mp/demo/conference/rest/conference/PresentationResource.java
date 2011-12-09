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


import net.sf.mp.demo.conference.dao.face.conference.PresentationDao;
import net.sf.mp.demo.conference.dao.face.conference.PresentationExtDao;
import net.sf.mp.demo.conference.domain.conference.Presentation;

/**
 *
 * <p>Title: PresentationResource</p>
 *
 * <p>Description: remote interface for PresentationResource service </p>
 *
 */
@RequestMapping ("/rest/xml/presentations")
@Controller 
public class PresentationResource  {

	@Autowired
	@Qualifier("presentationDao")
	PresentationDao presentationDao;
	
	@Autowired
	@Qualifier("presentationExtDao")
	PresentationExtDao presentationExtDao;

//MP-MANAGED-UPDATABLE-BEGINNING-DISABLE @FIND_ALL-presentation@
    @RequestMapping(method = RequestMethod.GET)
    @Transactional
	@ResponseBody
    public List<Presentation> findAll () {
		List<Presentation> r = new ArrayList<Presentation>();
        List<Presentation> l = presentationDao.searchPrototypePresentation(new Presentation());
		for (Presentation presentation : l) {
			r.add(presentation.flat());
		}
		return r;
    }
//MP-MANAGED-UPDATABLE-ENDING

//MP-MANAGED-UPDATABLE-BEGINNING-DISABLE @FIND_BY_ID-presentation@
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	@ResponseBody
	@Transactional
    public Presentation findById (@PathVariable ("id") java.lang.Long id) {
        Presentation _presentation = new Presentation ();
		_presentation.setId(id);
		_presentation = presentationExtDao.getFirstPresentation(_presentation);
		if (_presentation!=null) return _presentation.flat();
		return new Presentation ();
    }
//MP-MANAGED-UPDATABLE-ENDING

	@RequestMapping(method = RequestMethod.POST)
	@Transactional(propagation=Propagation.REQUIRED, rollbackFor=Exception.class)
	@ResponseBody
	public Presentation create (
        @RequestParam("id") Long id,
        @RequestParam("startTime") Timestamp startTime,
        @RequestParam("endTime") Timestamp endTime,
        @RequestParam("abstractName") String abstractName,
        @RequestParam("title") String title,
        @RequestParam("status") String status,
        @RequestParam("presentationPlaceId") Long presentationPlaceId,
        @RequestParam("proposalTime") Timestamp proposalTime,
        HttpServletResponse servletResponse
        ) throws IOException {
        Presentation _presentation = new Presentation (
           id,
           startTime,
           endTime,
           abstractName,
           title,
           status,
           presentationPlaceId,
           proposalTime);
        return save(_presentation);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    @Transactional(propagation=Propagation.REQUIRED, rollbackFor=Exception.class)
	@ResponseBody
    public String delete (@PathVariable ("id") Long id) {
        Presentation presentation = new Presentation ();
        presentation.setId(id);
        presentationDao.deletePresentation(presentation);
		return "OK";
    }
	
	@RequestMapping(method = RequestMethod.PUT)
	@ResponseBody
	@Transactional(propagation=Propagation.REQUIRED, rollbackFor=Exception.class)
    public Presentation save (
        @RequestParam("id") Long id,
        @RequestParam("startTime") Timestamp startTime,
        @RequestParam("endTime") Timestamp endTime,
        @RequestParam("abstractName") String abstractName,
        @RequestParam("title") String title,
        @RequestParam("status") String status,
        @RequestParam("presentationPlaceId") Long presentationPlaceId,
        @RequestParam("proposalTime") Timestamp proposalTime,
        HttpServletResponse servletResponse
        ) throws IOException {
        Presentation _presentation = new Presentation (
           id,
           startTime,
           endTime,
           abstractName,
           title,
           status,
           presentationPlaceId,
           proposalTime);
        return save(_presentation);
    }	


	public Presentation save (Presentation presentation) {
		presentationDao.savePresentation(presentation);
		return presentation;
	}



}