package net.sf.mp.demo.conference.rest.conference;


import java.util.Date;
import java.util.List;
import java.util.ArrayList;
import java.io.*;
import java.sql.*;

import javax.servlet.http.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.FormParam;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.PUT;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Request;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;
import javax.xml.bind.JAXBElement;

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
@Path ("/rest/xml/evaluations")
@Produces ({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
@Consumes ({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
@Service
@Transactional
public class EvaluationResource  {
 

	@Autowired
	@Qualifier("evaluationDao")
	EvaluationDao evaluationDao;
	
	@Autowired
	@Qualifier("evaluationExtDao")
	EvaluationExtDao evaluationExtDao;

//MP-MANAGED-UPDATABLE-BEGINNING-DISABLE @FIND_ALL-evaluation@
    @GET
    @Produces ({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})	
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
    @GET
    @Path("{id}")
    @Produces ({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})		
    public Evaluation findById (@PathParam ("id") java.lang.Long id) {
        Evaluation _evaluation = new Evaluation ();
		_evaluation.setId(id);
		_evaluation = evaluationExtDao.getFirstEvaluation(_evaluation);
		if (_evaluation!=null) return _evaluation.flat();
		return new Evaluation ();
    }
//MP-MANAGED-UPDATABLE-ENDING

    @DELETE
    @Path("{id}")
    public void delete (@PathParam ("id") Long id) {
        Evaluation evaluation = new Evaluation ();
        evaluation.setId(id);
        evaluationDao.deleteEvaluation(evaluation);
    }

    @POST
    @Produces ({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})	
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    public Evaluation create (
        @FormParam("id") Long id,
        @FormParam("conferenceMemberId") Long conferenceMemberId,
        @FormParam("presentationId") Long presentationId,
        @FormParam("star") Integer star,
        @FormParam("comment") String comment,
        @FormParam("evaluationDate") Timestamp evaluationDate,
        @Context HttpServletResponse servletResponse
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

    @PUT
    @Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public Evaluation save(JAXBElement<Evaluation> jaxbEvaluation) {
        Evaluation evaluation = jaxbEvaluation.getValue();
        if (evaluation.getId()!=null)
            return evaluationDao.updateEvaluation(evaluation);
        return save(evaluation);
    }

	public Evaluation save (Evaluation evaluation) {
		evaluationDao.saveEvaluation(evaluation);
		return evaluation;
	}


}