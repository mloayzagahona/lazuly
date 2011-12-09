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


import net.sf.mp.demo.conference.dao.face.statistics.StatMbByRoleDao;
import net.sf.mp.demo.conference.dao.face.statistics.StatMbByRoleExtDao;
import net.sf.mp.demo.conference.domain.statistics.StatMbByRole;

/**
 *
 * <p>Title: StatMbByRoleResource</p>
 *
 * <p>Description: remote interface for StatMbByRoleResource service </p>
 *
 */
@RequestMapping ("/rest/xml/statmbbyroles")
@Controller 
public class StatMbByRoleResource  {

	@Autowired
	@Qualifier("statMbByRoleDao")
	StatMbByRoleDao statMbByRoleDao;
	
	@Autowired
	@Qualifier("statMbByRoleExtDao")
	StatMbByRoleExtDao statMbByRoleExtDao;

//MP-MANAGED-UPDATABLE-BEGINNING-DISABLE @FIND_ALL-stat_mb_by_role@
    @RequestMapping(method = RequestMethod.GET)
    @Transactional
	@ResponseBody
    public List<StatMbByRole> findAll () {
		List<StatMbByRole> r = new ArrayList<StatMbByRole>();
        List<StatMbByRole> l = statMbByRoleDao.searchPrototypeStatMbByRole(new StatMbByRole());
		for (StatMbByRole statMbByRole : l) {
			r.add(statMbByRole.flat());
		}
		return r;
    }
//MP-MANAGED-UPDATABLE-ENDING

//MP-MANAGED-UPDATABLE-BEGINNING-DISABLE @FIND_BY_ID-stat_mb_by_role@
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	@ResponseBody
	@Transactional
    public StatMbByRole findById (@PathVariable ("id") java.lang.String id) {
        StatMbByRole _statMbByRole = new StatMbByRole ();
		_statMbByRole.setId(id);
		_statMbByRole = statMbByRoleExtDao.getFirstStatMbByRole(_statMbByRole);
		if (_statMbByRole!=null) return _statMbByRole.flat();
		return new StatMbByRole ();
    }
//MP-MANAGED-UPDATABLE-ENDING

	@RequestMapping(method = RequestMethod.POST)
	@Transactional(propagation=Propagation.REQUIRED, rollbackFor=Exception.class)
	@ResponseBody
	public StatMbByRole create (
        @RequestParam("id") String id,
        @RequestParam("statMbPerCtryConfId") String statMbPerCtryConfId,
        @RequestParam("roleName") String roleName,
        @RequestParam("number") Long number,
        HttpServletResponse servletResponse
        ) throws IOException {
        StatMbByRole _statMbByRole = new StatMbByRole (
           id,
           statMbPerCtryConfId,
           roleName,
           number);
        return save(_statMbByRole);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    @Transactional(propagation=Propagation.REQUIRED, rollbackFor=Exception.class)
	@ResponseBody
    public String delete (@PathVariable ("id") String id) {
        StatMbByRole statMbByRole = new StatMbByRole ();
        statMbByRole.setId(id);
        statMbByRoleDao.deleteStatMbByRole(statMbByRole);
		return "OK";
    }
	
	@RequestMapping(method = RequestMethod.PUT)
	@ResponseBody
	@Transactional(propagation=Propagation.REQUIRED, rollbackFor=Exception.class)
    public StatMbByRole save (
        @RequestParam("id") String id,
        @RequestParam("statMbPerCtryConfId") String statMbPerCtryConfId,
        @RequestParam("roleName") String roleName,
        @RequestParam("number") Long number,
        HttpServletResponse servletResponse
        ) throws IOException {
        StatMbByRole _statMbByRole = new StatMbByRole (
           id,
           statMbPerCtryConfId,
           roleName,
           number);
        return save(_statMbByRole);
    }	


	public StatMbByRole save (StatMbByRole statMbByRole) {
		statMbByRoleDao.saveStatMbByRole(statMbByRole);
		return statMbByRole;
	}



}