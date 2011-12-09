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


import net.sf.mp.demo.conference.dao.face.conference.PresentationPlaceDao;
import net.sf.mp.demo.conference.dao.face.conference.PresentationPlaceExtDao;
import net.sf.mp.demo.conference.domain.conference.PresentationPlace;

/**
 *
 * <p>Title: PresentationPlaceResource</p>
 *
 * <p>Description: remote interface for PresentationPlaceResource service </p>
 *
 */
@RequestMapping ("/rest/xml/presentationplaces")
@Controller 
public class PresentationPlaceResource  {

	@Autowired
	@Qualifier("presentationPlaceDao")
	PresentationPlaceDao presentationPlaceDao;
	
	@Autowired
	@Qualifier("presentationPlaceExtDao")
	PresentationPlaceExtDao presentationPlaceExtDao;

//MP-MANAGED-UPDATABLE-BEGINNING-DISABLE @FIND_ALL-presentation_place@
    @RequestMapping(method = RequestMethod.GET)
    @Transactional
	@ResponseBody
    public List<PresentationPlace> findAll () {
		List<PresentationPlace> r = new ArrayList<PresentationPlace>();
        List<PresentationPlace> l = presentationPlaceDao.searchPrototypePresentationPlace(new PresentationPlace());
		for (PresentationPlace presentationPlace : l) {
			r.add(presentationPlace.flat());
		}
		return r;
    }
//MP-MANAGED-UPDATABLE-ENDING

//MP-MANAGED-UPDATABLE-BEGINNING-DISABLE @FIND_BY_ID-presentation_place@
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	@ResponseBody
	@Transactional
    public PresentationPlace findById (@PathVariable ("id") java.lang.Long id) {
        PresentationPlace _presentationPlace = new PresentationPlace ();
		_presentationPlace.setId(id);
		_presentationPlace = presentationPlaceExtDao.getFirstPresentationPlace(_presentationPlace);
		if (_presentationPlace!=null) return _presentationPlace.flat();
		return new PresentationPlace ();
    }
//MP-MANAGED-UPDATABLE-ENDING

	@RequestMapping(method = RequestMethod.POST)
	@Transactional(propagation=Propagation.REQUIRED, rollbackFor=Exception.class)
	@ResponseBody
	public PresentationPlace create (
        @RequestParam("id") Long id,
        @RequestParam("location") String location,
        @RequestParam("numberOfSeats") Integer numberOfSeats,
        HttpServletResponse servletResponse
        ) throws IOException {
        PresentationPlace _presentationPlace = new PresentationPlace (
           id,
           location,
           numberOfSeats);
        return save(_presentationPlace);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    @Transactional(propagation=Propagation.REQUIRED, rollbackFor=Exception.class)
	@ResponseBody
    public String delete (@PathVariable ("id") Long id) {
        PresentationPlace presentationPlace = new PresentationPlace ();
        presentationPlace.setId(id);
        presentationPlaceDao.deletePresentationPlace(presentationPlace);
		return "OK";
    }
	
	@RequestMapping(method = RequestMethod.PUT)
	@ResponseBody
	@Transactional(propagation=Propagation.REQUIRED, rollbackFor=Exception.class)
    public PresentationPlace save (
        @RequestParam("id") Long id,
        @RequestParam("location") String location,
        @RequestParam("numberOfSeats") Integer numberOfSeats,
        HttpServletResponse servletResponse
        ) throws IOException {
        PresentationPlace _presentationPlace = new PresentationPlace (
           id,
           location,
           numberOfSeats);
        return save(_presentationPlace);
    }	


	public PresentationPlace save (PresentationPlace presentationPlace) {
		presentationPlaceDao.savePresentationPlace(presentationPlace);
		return presentationPlace;
	}



}