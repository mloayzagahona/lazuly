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


import net.sf.mp.demo.conference.dao.face.conference.EvaluationDao;
import net.sf.mp.demo.conference.dao.face.conference.EvaluationExtDao;
import net.sf.mp.demo.conference.domain.conference.Evaluation;

/**
 *
 * <p>Title: EvaluationResource</p>
 *
 * <p>Description: remote interface for EvaluationResource service </p>
 *
 */
@RequestMapping ("/rest/xml/evaluations")
@Controller 
public class EvaluationResource  {

	@Autowired
	@Qualifier("evaluationDao")
	EvaluationDao evaluationDao;
	
	@Autowired
	@Qualifier("evaluationExtDao")
	EvaluationExtDao evaluationExtDao;

//MP-MANAGED-UPDATABLE-BEGINNING-DISABLE @FIND_ALL-evaluation@
    @RequestMapping(method = RequestMethod.GET)
    @Transactional
	@ResponseBody
    public List<Evaluation> findAll () {
		List<Evaluation> r = new ArrayList<Evaluation>();
        List<Evaluation> l = evaluationDao.searchPrototypeEvaluation(new Evaluation());
		for (Evaluation evaluation : l) {
			r.add(evaluation.flat());
		}
		return r;
    }
//MP-MANAGED-UPDATABLE-ENDING

//MP-MANAGED-UPDATABLE-BEGINNING-DISABLE @FIND_BY_ID-evaluation@
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	@ResponseBody
	@Transactional
    public Evaluation findById (@PathVariable ("id") java.lang.Long id) {
        Evaluation _evaluation = new Evaluation ();
		_evaluation.setId(id);
		_evaluation = evaluationExtDao.getFirstEvaluation(_evaluation);
		if (_evaluation!=null) return _evaluation.flat();
		return new Evaluation ();
    }
//MP-MANAGED-UPDATABLE-ENDING

	@RequestMapping(method = RequestMethod.POST)
	@Transactional(propagation=Propagation.REQUIRED, rollbackFor=Exception.class)
	@ResponseBody
	public Evaluation create (
        @RequestParam("id") Long id,
        @RequestParam("conferenceMemberId") Long conferenceMemberId,
        @RequestParam("presentationId") Long presentationId,
        @RequestParam("star") Integer star,
        @RequestParam("comment") String comment,
        @RequestParam("evaluationDate") Timestamp evaluationDate,
        HttpServletResponse servletResponse
        ) throws IOException {
        Evaluation _evaluation = new Evaluation (
           id,
           conferenceMemberId,
           presentationId,
           star,
           comment,
           evaluationDate);
        return save(_evaluation);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    @Transactional(propagation=Propagation.REQUIRED, rollbackFor=Exception.class)
	@ResponseBody
    public String delete (@PathVariable ("id") Long id) {
        Evaluation evaluation = new Evaluation ();
        evaluation.setId(id);
        evaluationDao.deleteEvaluation(evaluation);
		return "OK";
    }
	
	@RequestMapping(method = RequestMethod.PUT)
	@ResponseBody
	@Transactional(propagation=Propagation.REQUIRED, rollbackFor=Exception.class)
    public Evaluation save (
        @RequestParam("id") Long id,
        @RequestParam("conferenceMemberId") Long conferenceMemberId,
        @RequestParam("presentationId") Long presentationId,
        @RequestParam("star") Integer star,
        @RequestParam("comment") String comment,
        @RequestParam("evaluationDate") Timestamp evaluationDate,
        HttpServletResponse servletResponse
        ) throws IOException {
        Evaluation _evaluation = new Evaluation (
           id,
           conferenceMemberId,
           presentationId,
           star,
           comment,
           evaluationDate);
        return save(_evaluation);
    }	


	public Evaluation save (Evaluation evaluation) {
		evaluationDao.saveEvaluation(evaluation);
		return evaluation;
	}



}