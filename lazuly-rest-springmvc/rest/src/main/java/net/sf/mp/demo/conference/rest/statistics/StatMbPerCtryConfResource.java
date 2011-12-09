package net.sf.mp.demo.conference.rest.statistics;


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


import net.sf.mp.demo.conference.dao.face.statistics.StatMbPerCtryConfDao;
import net.sf.mp.demo.conference.dao.face.statistics.StatMbPerCtryConfExtDao;
import net.sf.mp.demo.conference.domain.statistics.StatMbPerCtryConf;

/**
 *
 * <p>Title: StatMbPerCtryConfResource</p>
 *
 * <p>Description: remote interface for StatMbPerCtryConfResource service </p>
 *
 */
@RequestMapping ("/rest/xml/statmbperctryconfs")
@Controller 
public class StatMbPerCtryConfResource  {

	@Autowired
	@Qualifier("statMbPerCtryConfDao")
	StatMbPerCtryConfDao statMbPerCtryConfDao;
	
	@Autowired
	@Qualifier("statMbPerCtryConfExtDao")
	StatMbPerCtryConfExtDao statMbPerCtryConfExtDao;

//MP-MANAGED-UPDATABLE-BEGINNING-DISABLE @FIND_ALL-stat_mb_per_ctry_conf@
    @RequestMapping(method = RequestMethod.GET)
    @Transactional
	@ResponseBody
    public List<StatMbPerCtryConf> findAll () {
		List<StatMbPerCtryConf> r = new ArrayList<StatMbPerCtryConf>();
        List<StatMbPerCtryConf> l = statMbPerCtryConfDao.searchPrototypeStatMbPerCtryConf(new StatMbPerCtryConf());
		for (StatMbPerCtryConf statMbPerCtryConf : l) {
			r.add(statMbPerCtryConf.flat());
		}
		return r;
    }
//MP-MANAGED-UPDATABLE-ENDING

//MP-MANAGED-UPDATABLE-BEGINNING-DISABLE @FIND_BY_ID-stat_mb_per_ctry_conf@
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	@ResponseBody
	@Transactional
    public StatMbPerCtryConf findById (@PathVariable ("id") java.lang.String id) {
        StatMbPerCtryConf _statMbPerCtryConf = new StatMbPerCtryConf ();
		_statMbPerCtryConf.setId(id);
		_statMbPerCtryConf = statMbPerCtryConfExtDao.getFirstStatMbPerCtryConf(_statMbPerCtryConf);
		if (_statMbPerCtryConf!=null) return _statMbPerCtryConf.flat();
		return new StatMbPerCtryConf ();
    }
//MP-MANAGED-UPDATABLE-ENDING

	@RequestMapping(method = RequestMethod.POST)
	@Transactional(propagation=Propagation.REQUIRED, rollbackFor=Exception.class)
	@ResponseBody
	public StatMbPerCtryConf create (
        @RequestParam("id") String id,
        @RequestParam("country") String country,
        @RequestParam("conferenceName") String conferenceName,
        @RequestParam("number") Long number,
        HttpServletResponse servletResponse
        ) throws IOException {
        StatMbPerCtryConf _statMbPerCtryConf = new StatMbPerCtryConf (
           id,
           country,
           conferenceName,
           number);
        return save(_statMbPerCtryConf);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    @Transactional(propagation=Propagation.REQUIRED, rollbackFor=Exception.class)
	@ResponseBody
    public String delete (@PathVariable ("id") String id) {
        StatMbPerCtryConf statMbPerCtryConf = new StatMbPerCtryConf ();
        statMbPerCtryConf.setId(id);
        statMbPerCtryConfDao.deleteStatMbPerCtryConf(statMbPerCtryConf);
		return "OK";
    }
	
	@RequestMapping(method = RequestMethod.PUT)
	@ResponseBody
	@Transactional(propagation=Propagation.REQUIRED, rollbackFor=Exception.class)
    public StatMbPerCtryConf save (
        @RequestParam("id") String id,
        @RequestParam("country") String country,
        @RequestParam("conferenceName") String conferenceName,
        @RequestParam("number") Long number,
        HttpServletResponse servletResponse
        ) throws IOException {
        StatMbPerCtryConf _statMbPerCtryConf = new StatMbPerCtryConf (
           id,
           country,
           conferenceName,
           number);
        return save(_statMbPerCtryConf);
    }	


	public StatMbPerCtryConf save (StatMbPerCtryConf statMbPerCtryConf) {
		statMbPerCtryConfDao.saveStatMbPerCtryConf(statMbPerCtryConf);
		return statMbPerCtryConf;
	}



}