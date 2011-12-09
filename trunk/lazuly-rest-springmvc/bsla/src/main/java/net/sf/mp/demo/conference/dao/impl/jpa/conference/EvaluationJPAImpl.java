package net.sf.mp.demo.conference.dao.impl.jpa.conference;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Date;
import java.sql.Timestamp;

import javax.persistence.Query;
import javax.persistence.EntityManager;

import org.springframework.orm.jpa.EntityManagerFactoryUtils;
import org.springframework.orm.jpa.support.JpaDaoSupport;
import org.springframework.transaction.annotation.Transactional;

import net.sf.minuteProject.architecture.bsla.bean.criteria.PaginationCriteria;
import net.sf.mp.demo.conference.dao.face.conference.EvaluationDao;
import net.sf.mp.demo.conference.domain.conference.Evaluation;
import net.sf.mp.demo.conference.domain.conference.ConferenceMember;
import net.sf.mp.demo.conference.domain.conference.Presentation;

/**
 *
 * <p>Title: EvaluationJPAImpl</p>
 *
 * <p>Description: Interface of a Data access object dealing with EvaluationJPAImpl
 * persistence. It offers a set of methods which allow for saving,
 * deleting and searching EvaluationJPAImpl objects</p>
 *
 */

public class EvaluationJPAImpl extends JpaDaoSupport implements EvaluationDao {

	public EvaluationJPAImpl () {}

    /**
     * Inserts a Evaluation entity 
     * @param Evaluation evaluation
     */
    public void insertEvaluation(Evaluation evaluation) {
       convertTransientReferenceToNull (evaluation);
       getJpaTemplate().persist(evaluation);
    }

    protected void insertEvaluation(EntityManager emForRecursiveDao, Evaluation evaluation) {
       emForRecursiveDao.persist(evaluation);
    } 
    /**
     * Inserts a list of Evaluation entity 
     * @param List<Evaluation> evaluations
     */
    public void insertEvaluations(List<Evaluation> evaluations) {
    	//TODO
    }
    /**
     * Updates a Evaluation entity 
     * @param Evaluation evaluation
     */
    public Evaluation updateEvaluation(Evaluation evaluation) {
       convertTransientReferenceToNull (evaluation);
       return getJpaTemplate().merge(evaluation);
    }

	/**
     * Updates a Evaluation entity with only the attributes set into Evaluation.
	 * The primary keys are to be set for this method to operate.
	 * This is a performance friendly feature, which remove the udibiquous full load and full update when an
	 * update is issued
     * Remark: The primary keys cannot be update by this methods, nor are the attributes that must be set to null.
     * @param Evaluation evaluation
    */ 
    @Transactional
    public int updateNotNullOnlyEvaluation(Evaluation evaluation) {
        Query jpaQuery = getEntityManager().createQuery(getUpdateNotNullOnlyEvaluationQueryChunk(evaluation));
        if (evaluation.getId() != null) {
           jpaQuery.setParameter ("id", evaluation.getId());
        }   
        if (evaluation.getConferenceMemberId() != null) {
           jpaQuery.setParameter ("conferenceMemberId", evaluation.getConferenceMemberId());
        }   
        if (evaluation.getPresentationId() != null) {
           jpaQuery.setParameter ("presentationId", evaluation.getPresentationId());
        }   
        if (evaluation.getStar() != null) {
           jpaQuery.setParameter ("star", evaluation.getStar());
        }   
        if (evaluation.getComment() != null) {
           jpaQuery.setParameter ("comment", evaluation.getComment());
        }   
        if (evaluation.getEvaluationDate() != null) {
           jpaQuery.setParameter ("evaluationDate", evaluation.getEvaluationDate());
        }   
		return jpaQuery.executeUpdate();
    }

    protected String getUpdateNotNullOnlyEvaluationQueryChunkPrototype (Evaluation evaluation) {
        boolean isWhereSet = false;
        StringBuffer query = new StringBuffer();
        query.append (" update Evaluation evaluation ");
        if (evaluation.getConferenceMemberId() != null) {
           query.append (getQueryCommaSet (isWhereSet));
           isWhereSet = true; 
           query.append(" evaluation.conferenceMemberId = :conferenceMemberId");
        }
        if (evaluation.getPresentationId() != null) {
           query.append (getQueryCommaSet (isWhereSet));
           isWhereSet = true; 
           query.append(" evaluation.presentationId = :presentationId");
        }
        if (evaluation.getStar() != null) {
           query.append (getQueryCommaSet (isWhereSet));
           isWhereSet = true; 
           query.append(" evaluation.star = :star");
        }
        if (evaluation.getComment() != null) {
           query.append (getQueryCommaSet (isWhereSet));
           isWhereSet = true; 
           query.append(" evaluation.comment = :comment");
        }
        if (evaluation.getEvaluationDate() != null) {
           query.append (getQueryCommaSet (isWhereSet));
           isWhereSet = true; 
           query.append(" evaluation.evaluationDate = :evaluationDate");
        }
        return query.toString();
    }
    
    protected String getUpdateNotNullOnlyEvaluationQueryChunk (Evaluation evaluation) {
        boolean isWhereSet = false;
        StringBuffer query = new StringBuffer(getUpdateNotNullOnlyEvaluationQueryChunkPrototype(evaluation));
        if (evaluation.getId() != null) {
           query.append (getQueryWHERE_AND (isWhereSet));
           isWhereSet = true;
			     query.append(" evaluation.id = :id");
        }
        return query.toString();
    }
    
                
	protected Evaluation assignBlankToNull (Evaluation evaluation) {
	    if (evaluation==null)
			return null;
        if (evaluation.getComment()!=null && evaluation.getComment().equals(""))
           evaluation.setComment((String)null);
		return evaluation;
	}
	
	protected boolean isAllNull (Evaluation evaluation) {
	    if (evaluation==null)
			return true;
        if (evaluation.getId()!=null) 
            return false;
        if (evaluation.getConferenceMemberId()!=null) 
            return false;
        if (evaluation.getPresentationId()!=null) 
            return false;
        if (evaluation.getStar()!=null) 
            return false;
        if (evaluation.getComment()!=null) 
            return false;
        if (evaluation.getEvaluationDate()!=null) 
            return false;
		return true;
	}
		
	@Transactional
    public int updateNotNullOnlyPrototypeEvaluation(Evaluation evaluation, Evaluation prototypeCriteria) {
        boolean isWhereSet = false;
        StringBuffer query = new StringBuffer();
        query.append (" update Evaluation evaluation ");
        if (evaluation.getId() != null) {
           query.append (getQueryCommaSet (isWhereSet));
           isWhereSet = true; 
           query.append(" evaluation.id = "+ evaluation.getId() + " ");
        }
        if (evaluation.getConferenceMemberId() != null) {
           query.append (getQueryCommaSet (isWhereSet));
           isWhereSet = true; 
           query.append(" evaluation.conferenceMemberId = "+ evaluation.getConferenceMemberId() + " ");
        }
        if (evaluation.getPresentationId() != null) {
           query.append (getQueryCommaSet (isWhereSet));
           isWhereSet = true; 
           query.append(" evaluation.presentationId = "+ evaluation.getPresentationId() + " ");
        }
        if (evaluation.getStar() != null) {
           query.append (getQueryCommaSet (isWhereSet));
           isWhereSet = true; 
           query.append(" evaluation.star = "+ evaluation.getStar() + " ");
        }
        if (evaluation.getComment() != null) {
           query.append (getQueryCommaSet (isWhereSet));
           isWhereSet = true; 
           query.append(" evaluation.comment = '"+ evaluation.getComment()+"' ");
        }
        if (evaluation.getEvaluationDate() != null) {
           query.append (getQueryCommaSet (isWhereSet));
           isWhereSet = true; 
           query.append(" evaluation.evaluationDate = '"+ evaluation.getEvaluationDate()+"' ");
        }
		isWhereSet = false; 
        if (prototypeCriteria.getId() != null) {
           query.append (getQueryWHERE_AND (isWhereSet));
           isWhereSet = true;
           query.append(" evaluation.id = "+ prototypeCriteria.getId() + " ");
        }
        if (prototypeCriteria.getConferenceMemberId() != null) {
           query.append (getQueryWHERE_AND (isWhereSet));
           isWhereSet = true;
           query.append(" evaluation.conferenceMemberId = "+ prototypeCriteria.getConferenceMemberId() + " ");
        }
        if (prototypeCriteria.getPresentationId() != null) {
           query.append (getQueryWHERE_AND (isWhereSet));
           isWhereSet = true;
           query.append(" evaluation.presentationId = "+ prototypeCriteria.getPresentationId() + " ");
        }
        if (prototypeCriteria.getStar() != null) {
           query.append (getQueryWHERE_AND (isWhereSet));
           isWhereSet = true;
           query.append(" evaluation.star = "+ prototypeCriteria.getStar() + " ");
        }
        if (prototypeCriteria.getComment() != null) {
           query.append (getQueryWHERE_AND (isWhereSet));
           isWhereSet = true;
           query.append(" evaluation.comment = '"+ prototypeCriteria.getComment()+"' ");
        }
        if (prototypeCriteria.getEvaluationDate() != null) {
           query.append (getQueryWHERE_AND (isWhereSet));
           isWhereSet = true;
           query.append(" evaluation.evaluationDate = '"+ prototypeCriteria.getEvaluationDate()+"' ");
        }
        Query jpaQuery = getEntityManager().createQuery(query.toString());
		return jpaQuery.executeUpdate();
    }
     
     /**
     * Saves a Evaluation entity 
     * @param Evaluation evaluation
     */
    public void saveEvaluation(Evaluation evaluation) {
       //getJpaTemplate().persist(evaluation);
       convertTransientReferenceToNull (evaluation);
       if (getJpaTemplate().contains(evaluation)) {
          getJpaTemplate().merge(evaluation);
       } else {
          getJpaTemplate().persist(evaluation);
       }
       getJpaTemplate().flush(); 
    }
       
    /**
     * Deletes a Evaluation entity 
     * @param Evaluation evaluation
     */
    public void deleteEvaluation(Evaluation evaluation) {
      getJpaTemplate().remove(evaluation);
    }
    
    /**
     * Loads the Evaluation entity which is related to an instance of
     * Evaluation
     * @param Long id
     * @return Evaluation The Evaluation entity
     
    public Evaluation loadEvaluation(Long id) {
    	return (Evaluation)getJpaTemplate().get(Evaluation.class, id);
    }
*/
  
    /**
     * Loads the Evaluation entity which is related to an instance of
     * Evaluation
     * @param java.lang.Long Id
     * @return Evaluation The Evaluation entity
     */
    public Evaluation loadEvaluation(java.lang.Long id) {
    	return (Evaluation)getJpaTemplate().find(Evaluation.class, id);
    }
    
    /**
     * Loads a list of Evaluation entity 
     * @param List<java.lang.Long> ids
     * @return List<Evaluation> The Evaluation entity
     */
    public List<Evaluation> loadEvaluationListByEvaluation (List<Evaluation> evaluations) {
       return null;
    }
    
    /**
     * Loads a list of Evaluation entity 
     * @param List<java.lang.Long> ids
     * @return List<Evaluation> The Evaluation entity
     */
    public List<Evaluation> loadEvaluationListById(List<java.lang.Long> ids){
       return null;
    }
    
    /**
     * Loads the Evaluation entity which is related to an instance of
     * Evaluation and its dependent one to many objects
     * @param Long id
     * @return Evaluation The Evaluation entity
     */
    public Evaluation loadFullFirstLevelEvaluation(java.lang.Long id) {
        List list = getJpaTemplate().find(
                     "SELECT evaluation FROM Evaluation evaluation "
                     + " WHERE evaluation.id = "+id
               );
         if (list!=null && !list.isEmpty())
            return (Evaluation)list.get(0);
         return null;
    	//return null;//(Evaluation) getJpaTemplate().queryForObject("loadFullFirstLevelEvaluation", id);
    }

    /**
     * Loads the Evaluation entity which is related to an instance of
     * Evaluation
     * @param Evaluation evaluation
     * @return Evaluation The Evaluation entity
     */
    public Evaluation loadFullFirstLevelEvaluation(Evaluation evaluation) {
        boolean isWhereSet = false;
        StringBuffer query = new StringBuffer();
        query.append ("SELECT evaluation FROM Evaluation evaluation ");
        if (evaluation.getId() != null) {
           query.append (getQueryWHERE_AND (isWhereSet));
           isWhereSet = true;
           query.append(" evaluation.id = "+ evaluation.getId() + " ");
         }
	        	List list = getJpaTemplate().find(query.toString());
        if (list!=null && !list.isEmpty())
           return (Evaluation)list.get(0);    
        return null;
    }  
     
    /**
     * Loads the Evaluation entity which is related to an instance of
     * Evaluation and its dependent objects one to many
     * @param Long id
     * @return Evaluation The Evaluation entity
     */
    public Evaluation loadFullEvaluation(Long id) {
    	return null;//(Evaluation)getJpaTemplate().queryForObject("loadFullEvaluation", id);
    }

    /**
     * Searches a list of Evaluation entity 
     * @param Evaluation evaluation
     * @return List
     */
    public List<Evaluation> searchPrototypeEvaluation(Evaluation evaluation) {      
       return searchPrototype (evaluation, null);            
    }  
    
    protected List<Evaluation> searchPrototype (Evaluation evaluation, Integer maxResults) { 
       Query hquery = getEntityManager().createQuery(getEvaluationSearchEqualQuery (evaluation));
       if (maxResults!=null)
          hquery.setMaxResults(maxResults);
       return hquery.getResultList();         
    }        
    
    public List<Evaluation> searchPrototypeEvaluation (List<Evaluation> evaluations) {
       return searchPrototype (evaluations, null);  
    }

    protected List<Evaluation> searchPrototype (List<Evaluation> evaluations, Integer maxResults) {    
       //TODO convert setMaxResults in JPA if (maxResults!=null)
       //   getJpaTemplate().setMaxResults(maxResults);
       return getJpaTemplate().find(getEvaluationSearchEqualQuery (null, evaluations));            
    }    

    public List<Evaluation> searchDistinctPrototypeEvaluation (Evaluation evaluationMask, List<Evaluation> evaluations) {
        return getJpaTemplate().find(getEvaluationSearchEqualQuery (evaluationMask, evaluations));    
    }
         
	/**
     * Searches a list of Evaluation entity 
     * @param Evaluation evaluationPositive
     * @param Evaluation evaluationNegative
     * @return List
     */
    public List<Evaluation> searchPrototypeEvaluation(Evaluation evaluationPositive, Evaluation evaluationNegative) {
	    return getJpaTemplate().find(getEvaluationSearchEqualQuery (evaluationPositive, evaluationNegative));  
    }

    /**
    * return a jql query search on a Evaluation prototype
    */
    protected String getEvaluationSearchEqualQuery (Evaluation evaluation) {
        StringBuffer query = new StringBuffer();
        StringBuffer queryWhere = new StringBuffer();
        query.append ("SELECT evaluation FROM Evaluation evaluation ");
        queryWhere.append (getEvaluationSearchEqualWhereQueryChunk(evaluation, false));   
	    return getHQuery(query.toString(), queryWhere.toString());
    }


    /**
    * return a jql search for a list of Evaluation prototype
    */
    protected String getEvaluationSearchEqualQuery (Evaluation evaluationMask, List<Evaluation> evaluations) {
        boolean isOrSet = false;
        StringBuffer query = new StringBuffer();
        if (evaluationMask !=null)
           query.append (getEvaluationMaskWhat (evaluationMask));
        query.append (" FROM Evaluation evaluation ");
        StringBuffer queryWhere = new StringBuffer();
        for (Evaluation evaluation : evaluations) {
           if (!isAllNull(evaluation)) {        
	           queryWhere.append (getQueryOR (isOrSet));
	           isOrSet = true;        
	           queryWhere.append (" ("+getEvaluationSearchEqualWhereQueryChunk(evaluation, false)+") ");
           }
        }
	    return getHQuery(query.toString(), queryWhere.toString());
    }	
    
    protected String getEvaluationMaskWhat (Evaluation evaluationMask) {
        boolean isCommaSet = false;
        StringBuffer query = new StringBuffer("SELECT DISTINCT ");
        if (evaluationMask.getId() != null) {
           query.append (getQueryComma (isCommaSet));
           isCommaSet = true;
           query.append(" id ");
        }
        if (evaluationMask.getConferenceMemberId() != null) {
           query.append (getQueryComma (isCommaSet));
           isCommaSet = true;
           query.append(" conferenceMemberId ");
        }
        if (evaluationMask.getPresentationId() != null) {
           query.append (getQueryComma (isCommaSet));
           isCommaSet = true;
           query.append(" presentationId ");
        }
        if (evaluationMask.getStar() != null) {
           query.append (getQueryComma (isCommaSet));
           isCommaSet = true;
           query.append(" star ");
        }
        if (evaluationMask.getComment() != null) {
           query.append (getQueryComma (isCommaSet));
           isCommaSet = true;
           query.append(" comment ");
        }
        if (evaluationMask.getEvaluationDate() != null) {
           query.append (getQueryComma (isCommaSet));
           isCommaSet = true;
           query.append(" evaluationDate ");
        }
        if (!isCommaSet)
           return "";
	    return query.toString();        
    }
    
    protected String getEvaluationSearchEqualWhereQueryChunk (Evaluation evaluation, boolean isAndSet) {
        StringBuffer query = new StringBuffer();
        if (evaluation.getId() != null) {
           query.append (getQueryAND (isAndSet));
           isAndSet = true;
           query.append(" evaluation.id = "+ evaluation.getId() + " ");
        }
        if (evaluation.getConferenceMemberId() != null) {
           query.append (getQueryAND (isAndSet));
           isAndSet = true;
           query.append(" evaluation.conferenceMemberId = "+ evaluation.getConferenceMemberId() + " ");
        }
        if (evaluation.getPresentationId() != null) {
           query.append (getQueryAND (isAndSet));
           isAndSet = true;
           query.append(" evaluation.presentationId = "+ evaluation.getPresentationId() + " ");
        }
        if (evaluation.getStar() != null) {
           query.append (getQueryAND (isAndSet));
           isAndSet = true;
           query.append(" evaluation.star = "+ evaluation.getStar() + " ");
        }
        if (evaluation.getComment() != null) {
           query.append (getQueryAND (isAndSet));
           isAndSet = true;
           query.append(" evaluation.comment = '"+ evaluation.getComment()+"' ");
        }
        if (evaluation.getEvaluationDate() != null) {
           query.append (getQueryAND (isAndSet));
           isAndSet = true;
           query.append(" evaluation.evaluationDate = '"+ evaluation.getEvaluationDate()+"' ");
        }
	    return query.toString();
    }   
     
    /**
    * return a jql search on a Evaluation prototype with positive and negative beans
    */
    protected String getEvaluationSearchEqualQuery (Evaluation evaluationPositive, Evaluation evaluationNegative) {
        boolean isWhereSet = false;
        StringBuffer query = new StringBuffer();
        query.append (" SELECT evaluation FROM Evaluation evaluation ");
        if (evaluationPositive!=null && evaluationPositive.getId() != null) {
           query.append (getQueryWHERE_AND (isWhereSet));
           isWhereSet = true;
           query.append(" evaluation.id = "+ evaluationPositive.getId() + " ");
        } else if (evaluationNegative.getId() != null) {
           query.append (getQueryWHERE_AND (isWhereSet));
           isWhereSet = true;   
           query.append(" evaluation.id is null ");
        }
        if (evaluationPositive!=null && evaluationPositive.getConferenceMemberId() != null) {
           query.append (getQueryWHERE_AND (isWhereSet));
           isWhereSet = true;
           query.append(" evaluation.conferenceMemberId = "+ evaluationPositive.getConferenceMemberId() + " ");
        } else if (evaluationNegative.getConferenceMemberId() != null) {
           query.append (getQueryWHERE_AND (isWhereSet));
           isWhereSet = true;   
           query.append(" evaluation.conferenceMemberId is null ");
        }
        if (evaluationPositive!=null && evaluationPositive.getPresentationId() != null) {
           query.append (getQueryWHERE_AND (isWhereSet));
           isWhereSet = true;
           query.append(" evaluation.presentationId = "+ evaluationPositive.getPresentationId() + " ");
        } else if (evaluationNegative.getPresentationId() != null) {
           query.append (getQueryWHERE_AND (isWhereSet));
           isWhereSet = true;   
           query.append(" evaluation.presentationId is null ");
        }
        if (evaluationPositive!=null && evaluationPositive.getStar() != null) {
           query.append (getQueryWHERE_AND (isWhereSet));
           isWhereSet = true;
           query.append(" evaluation.star = "+ evaluationPositive.getStar() + " ");
        } else if (evaluationNegative.getStar() != null) {
           query.append (getQueryWHERE_AND (isWhereSet));
           isWhereSet = true;   
           query.append(" evaluation.star is null ");
        }
        if (evaluationPositive!=null && evaluationPositive.getComment() != null) {
           query.append (getQueryWHERE_AND (isWhereSet));
           isWhereSet = true;
           query.append(" evaluation.comment = '"+ evaluationPositive.getComment()+"' ");
        } else if (evaluationNegative.getComment() != null) {
           query.append (getQueryWHERE_AND (isWhereSet));
           isWhereSet = true;   
           query.append(" evaluation.comment is null ");
        }
        if (evaluationPositive!=null && evaluationPositive.getEvaluationDate() != null) {
           query.append (getQueryWHERE_AND (isWhereSet));
           isWhereSet = true;
           query.append(" evaluation.evaluationDate = '"+ evaluationPositive.getEvaluationDate()+"' ");
        } else if (evaluationNegative.getEvaluationDate() != null) {
           query.append (getQueryWHERE_AND (isWhereSet));
           isWhereSet = true;   
           query.append(" evaluation.evaluationDate is null ");
        }
	    return query.toString();
    }
    
    /**
     * Load a paginated list of Evaluation entity dependent of pagination criteria
     * @param PaginationCriteria paginationCriteria
     * @return List
     */
    public List <Evaluation> loadPaginatedEvaluation (Evaluation evaluation, PaginationCriteria paginationCriteria) {
	    List<Long> page = loadPaginatedEvaluationIdentitiesFromStartPositionId(evaluation, paginationCriteria);
    	int start = paginationCriteria.getNumberOfRowsReturned()*(paginationCriteria.getPageNumber());
    	int max = page.size();
    	if (start<max) {
    	   List<Long> returnPage = page.subList(start, max);	
           StringBuffer query = new StringBuffer();
           query.append (" SELECT evaluation FROM Evaluation evaluation ");      
	       query.append(" where evaluation.id in (");
	       for (Iterator iter = returnPage.iterator(); iter.hasNext();) {
			  Long id = (Long) iter.next();
			  query.append(id.toString());
		      if (iter.hasNext())
			     query.append(",");
		   }
	       query.append(") ");
	       return getJpaTemplate().find(query.toString()); 
    	} 
        return new ArrayList<Evaluation>();
    }      

    protected List<Long> loadPaginatedEvaluationIdentitiesFromStartPositionId (Evaluation evaluation, PaginationCriteria paginationCriteria) {
       boolean isWhereSet = false;
       StringBuffer query = new StringBuffer();
       query.append ("select evaluation.id ");
       query.append (getEvaluationSearchEqualQuery (evaluation));
       if (paginationCriteria.getOrderList()!=null) {
    	   query.append(" order by "+paginationCriteria.getOrderList());
       }
       int maxResult = paginationCriteria.getNumberOfRowsReturned()*(1+paginationCriteria.getPageNumber());
       List<Long> set = getEntityManager().createNamedQuery(query.toString()).setMaxResults(maxResult).getResultList();
       return set;
    }

    //TODO put in upper class
    
	protected String getQueryWHERE_AND(boolean isWhereSet) {
		if (isWhereSet)
			return " AND ";
		return " WHERE ";
	}

	protected String getQueryCommaSet(boolean isWhereSet) {
		if (isWhereSet)
			return " , ";
		return " SET ";
	}

	protected static String getQuerySelectComma(boolean isSelectSet) {
		if (isSelectSet)
			return " , ";
		return " select ";
	}

	protected static String getQueryBLANK_AND(boolean isBlankSet) {
		if (isBlankSet)
			return " AND ";
		return " ";
	}

	protected static String getQueryBLANK_COMMA(boolean isBlankSet) {
		if (isBlankSet)
			return " , ";
		return " ";
	}

	protected String getQueryComma(boolean isCommaSet) {
		return (isCommaSet) ? " , " : "";
	}

	protected String getQueryOR(boolean isOrSet) {
		return (isOrSet) ? " OR " : "";
	}

	protected String getQueryAND(boolean isAndSet) {
		return (isAndSet) ? " AND " : "";
	}

	protected String getHQuery (String what, String where) {
        String whereWord = getWhere (where);
	    return what+whereWord+where;
    }
    
    protected String getWhere (String where) {
        return (where==null || where.trim().equals("") || where.trim()==null)?"":" WHERE ";
    }   
    protected EntityManager getEntityManager () {
        return EntityManagerFactoryUtils.getTransactionalEntityManager(getJpaTemplate().getEntityManagerFactory());    
    }
    
    protected void convertTransientReferenceToNull (Evaluation evaluation) {
	   evaluation.setConferenceMemberId ((ConferenceMember)null);
	   evaluation.setPresentationId ((Presentation)null);
    }
}
