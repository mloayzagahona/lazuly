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
import net.sf.mp.demo.conference.dao.face.conference.ConferenceFeedbackDao;
import net.sf.mp.demo.conference.domain.conference.ConferenceFeedback;
import net.sf.mp.demo.conference.domain.conference.Conference;
import net.sf.mp.demo.conference.domain.conference.ConferenceMember;

/**
 *
 * <p>Title: ConferenceFeedbackJPAImpl</p>
 *
 * <p>Description: Interface of a Data access object dealing with ConferenceFeedbackJPAImpl
 * persistence. It offers a set of methods which allow for saving,
 * deleting and searching ConferenceFeedbackJPAImpl objects</p>
 *
 */

public class ConferenceFeedbackJPAImpl extends JpaDaoSupport implements ConferenceFeedbackDao {

	public ConferenceFeedbackJPAImpl () {}

    /**
     * Inserts a ConferenceFeedback entity 
     * @param ConferenceFeedback conferenceFeedback
     */
    public void insertConferenceFeedback(ConferenceFeedback conferenceFeedback) {
       convertTransientReferenceToNull (conferenceFeedback);
       getJpaTemplate().persist(conferenceFeedback);
    }

    protected void insertConferenceFeedback(EntityManager emForRecursiveDao, ConferenceFeedback conferenceFeedback) {
       emForRecursiveDao.persist(conferenceFeedback);
    } 
    /**
     * Inserts a list of ConferenceFeedback entity 
     * @param List<ConferenceFeedback> conferenceFeedbacks
     */
    public void insertConferenceFeedbacks(List<ConferenceFeedback> conferenceFeedbacks) {
    	//TODO
    }
    /**
     * Updates a ConferenceFeedback entity 
     * @param ConferenceFeedback conferenceFeedback
     */
    public ConferenceFeedback updateConferenceFeedback(ConferenceFeedback conferenceFeedback) {
       convertTransientReferenceToNull (conferenceFeedback);
       return getJpaTemplate().merge(conferenceFeedback);
    }

	/**
     * Updates a ConferenceFeedback entity with only the attributes set into ConferenceFeedback.
	 * The primary keys are to be set for this method to operate.
	 * This is a performance friendly feature, which remove the udibiquous full load and full update when an
	 * update is issued
     * Remark: The primary keys cannot be update by this methods, nor are the attributes that must be set to null.
     * @param ConferenceFeedback conferenceFeedback
    */ 
    @Transactional
    public int updateNotNullOnlyConferenceFeedback(ConferenceFeedback conferenceFeedback) {
        Query jpaQuery = getEntityManager().createQuery(getUpdateNotNullOnlyConferenceFeedbackQueryChunk(conferenceFeedback));
        if (conferenceFeedback.getId() != null) {
           jpaQuery.setParameter ("id", conferenceFeedback.getId());
        }   
        if (conferenceFeedback.getComment() != null) {
           jpaQuery.setParameter ("comment", conferenceFeedback.getComment());
        }   
        if (conferenceFeedback.getFeedbackDate() != null) {
           jpaQuery.setParameter ("feedbackDate", conferenceFeedback.getFeedbackDate());
        }   
        if (conferenceFeedback.getConferenceId() != null) {
           jpaQuery.setParameter ("conferenceId", conferenceFeedback.getConferenceId());
        }   
        if (conferenceFeedback.getConferenceMemberId() != null) {
           jpaQuery.setParameter ("conferenceMemberId", conferenceFeedback.getConferenceMemberId());
        }   
		return jpaQuery.executeUpdate();
    }

    protected String getUpdateNotNullOnlyConferenceFeedbackQueryChunkPrototype (ConferenceFeedback conferenceFeedback) {
        boolean isWhereSet = false;
        StringBuffer query = new StringBuffer();
        query.append (" update ConferenceFeedback conferenceFeedback ");
        if (conferenceFeedback.getComment() != null) {
           query.append (getQueryCommaSet (isWhereSet));
           isWhereSet = true; 
           query.append(" conferenceFeedback.comment = :comment");
        }
        if (conferenceFeedback.getFeedbackDate() != null) {
           query.append (getQueryCommaSet (isWhereSet));
           isWhereSet = true; 
           query.append(" conferenceFeedback.feedbackDate = :feedbackDate");
        }
        if (conferenceFeedback.getConferenceId() != null) {
           query.append (getQueryCommaSet (isWhereSet));
           isWhereSet = true; 
           query.append(" conferenceFeedback.conferenceId = :conferenceId");
        }
        if (conferenceFeedback.getConferenceMemberId() != null) {
           query.append (getQueryCommaSet (isWhereSet));
           isWhereSet = true; 
           query.append(" conferenceFeedback.conferenceMemberId = :conferenceMemberId");
        }
        return query.toString();
    }
    
    protected String getUpdateNotNullOnlyConferenceFeedbackQueryChunk (ConferenceFeedback conferenceFeedback) {
        boolean isWhereSet = false;
        StringBuffer query = new StringBuffer(getUpdateNotNullOnlyConferenceFeedbackQueryChunkPrototype(conferenceFeedback));
        if (conferenceFeedback.getId() != null) {
           query.append (getQueryWHERE_AND (isWhereSet));
           isWhereSet = true;
			     query.append(" conferenceFeedback.id = :id");
        }
        return query.toString();
    }
    
                
	protected ConferenceFeedback assignBlankToNull (ConferenceFeedback conferenceFeedback) {
	    if (conferenceFeedback==null)
			return null;
        if (conferenceFeedback.getComment()!=null && conferenceFeedback.getComment().equals(""))
           conferenceFeedback.setComment((String)null);
		return conferenceFeedback;
	}
	
	protected boolean isAllNull (ConferenceFeedback conferenceFeedback) {
	    if (conferenceFeedback==null)
			return true;
        if (conferenceFeedback.getId()!=null) 
            return false;
        if (conferenceFeedback.getComment()!=null) 
            return false;
        if (conferenceFeedback.getFeedbackDate()!=null) 
            return false;
        if (conferenceFeedback.getConferenceId()!=null) 
            return false;
        if (conferenceFeedback.getConferenceMemberId()!=null) 
            return false;
		return true;
	}
		
	@Transactional
    public int updateNotNullOnlyPrototypeConferenceFeedback(ConferenceFeedback conferenceFeedback, ConferenceFeedback prototypeCriteria) {
        boolean isWhereSet = false;
        StringBuffer query = new StringBuffer();
        query.append (" update ConferenceFeedback conferenceFeedback ");
        if (conferenceFeedback.getId() != null) {
           query.append (getQueryCommaSet (isWhereSet));
           isWhereSet = true; 
           query.append(" conferenceFeedback.id = "+ conferenceFeedback.getId() + " ");
        }
        if (conferenceFeedback.getComment() != null) {
           query.append (getQueryCommaSet (isWhereSet));
           isWhereSet = true; 
           query.append(" conferenceFeedback.comment = '"+ conferenceFeedback.getComment()+"' ");
        }
        if (conferenceFeedback.getFeedbackDate() != null) {
           query.append (getQueryCommaSet (isWhereSet));
           isWhereSet = true; 
           query.append(" conferenceFeedback.feedbackDate = '"+ conferenceFeedback.getFeedbackDate()+"' ");
        }
        if (conferenceFeedback.getConferenceId() != null) {
           query.append (getQueryCommaSet (isWhereSet));
           isWhereSet = true; 
           query.append(" conferenceFeedback.conferenceId = "+ conferenceFeedback.getConferenceId() + " ");
        }
        if (conferenceFeedback.getConferenceMemberId() != null) {
           query.append (getQueryCommaSet (isWhereSet));
           isWhereSet = true; 
           query.append(" conferenceFeedback.conferenceMemberId = "+ conferenceFeedback.getConferenceMemberId() + " ");
        }
		isWhereSet = false; 
        if (prototypeCriteria.getId() != null) {
           query.append (getQueryWHERE_AND (isWhereSet));
           isWhereSet = true;
           query.append(" conferenceFeedback.id = "+ prototypeCriteria.getId() + " ");
        }
        if (prototypeCriteria.getComment() != null) {
           query.append (getQueryWHERE_AND (isWhereSet));
           isWhereSet = true;
           query.append(" conferenceFeedback.comment = '"+ prototypeCriteria.getComment()+"' ");
        }
        if (prototypeCriteria.getFeedbackDate() != null) {
           query.append (getQueryWHERE_AND (isWhereSet));
           isWhereSet = true;
           query.append(" conferenceFeedback.feedbackDate = '"+ prototypeCriteria.getFeedbackDate()+"' ");
        }
        if (prototypeCriteria.getConferenceId() != null) {
           query.append (getQueryWHERE_AND (isWhereSet));
           isWhereSet = true;
           query.append(" conferenceFeedback.conferenceId = "+ prototypeCriteria.getConferenceId() + " ");
        }
        if (prototypeCriteria.getConferenceMemberId() != null) {
           query.append (getQueryWHERE_AND (isWhereSet));
           isWhereSet = true;
           query.append(" conferenceFeedback.conferenceMemberId = "+ prototypeCriteria.getConferenceMemberId() + " ");
        }
        Query jpaQuery = getEntityManager().createQuery(query.toString());
		return jpaQuery.executeUpdate();
    }
     
     /**
     * Saves a ConferenceFeedback entity 
     * @param ConferenceFeedback conferenceFeedback
     */
    public void saveConferenceFeedback(ConferenceFeedback conferenceFeedback) {
       //getJpaTemplate().persist(conferenceFeedback);
       convertTransientReferenceToNull (conferenceFeedback);
       if (getJpaTemplate().contains(conferenceFeedback)) {
          getJpaTemplate().merge(conferenceFeedback);
       } else {
          getJpaTemplate().persist(conferenceFeedback);
       }
       getJpaTemplate().flush(); 
    }
       
    /**
     * Deletes a ConferenceFeedback entity 
     * @param ConferenceFeedback conferenceFeedback
     */
    public void deleteConferenceFeedback(ConferenceFeedback conferenceFeedback) {
      getJpaTemplate().remove(conferenceFeedback);
    }
    
    /**
     * Loads the ConferenceFeedback entity which is related to an instance of
     * ConferenceFeedback
     * @param Long id
     * @return ConferenceFeedback The ConferenceFeedback entity
     
    public ConferenceFeedback loadConferenceFeedback(Long id) {
    	return (ConferenceFeedback)getJpaTemplate().get(ConferenceFeedback.class, id);
    }
*/
  
    /**
     * Loads the ConferenceFeedback entity which is related to an instance of
     * ConferenceFeedback
     * @param java.lang.Integer Id
     * @return ConferenceFeedback The ConferenceFeedback entity
     */
    public ConferenceFeedback loadConferenceFeedback(java.lang.Integer id) {
    	return (ConferenceFeedback)getJpaTemplate().find(ConferenceFeedback.class, id);
    }
    
    /**
     * Loads a list of ConferenceFeedback entity 
     * @param List<java.lang.Integer> ids
     * @return List<ConferenceFeedback> The ConferenceFeedback entity
     */
    public List<ConferenceFeedback> loadConferenceFeedbackListByConferenceFeedback (List<ConferenceFeedback> conferenceFeedbacks) {
       return null;
    }
    
    /**
     * Loads a list of ConferenceFeedback entity 
     * @param List<java.lang.Integer> ids
     * @return List<ConferenceFeedback> The ConferenceFeedback entity
     */
    public List<ConferenceFeedback> loadConferenceFeedbackListById(List<java.lang.Integer> ids){
       return null;
    }
    
    /**
     * Loads the ConferenceFeedback entity which is related to an instance of
     * ConferenceFeedback and its dependent one to many objects
     * @param Long id
     * @return ConferenceFeedback The ConferenceFeedback entity
     */
    public ConferenceFeedback loadFullFirstLevelConferenceFeedback(java.lang.Integer id) {
        List list = getJpaTemplate().find(
                     "SELECT conferenceFeedback FROM ConferenceFeedback conferenceFeedback "
                     + " WHERE conferenceFeedback.id = "+id
               );
         if (list!=null && !list.isEmpty())
            return (ConferenceFeedback)list.get(0);
         return null;
    	//return null;//(ConferenceFeedback) getJpaTemplate().queryForObject("loadFullFirstLevelConferenceFeedback", id);
    }

    /**
     * Loads the ConferenceFeedback entity which is related to an instance of
     * ConferenceFeedback
     * @param ConferenceFeedback conferenceFeedback
     * @return ConferenceFeedback The ConferenceFeedback entity
     */
    public ConferenceFeedback loadFullFirstLevelConferenceFeedback(ConferenceFeedback conferenceFeedback) {
        boolean isWhereSet = false;
        StringBuffer query = new StringBuffer();
        query.append ("SELECT conferenceFeedback FROM ConferenceFeedback conferenceFeedback ");
        if (conferenceFeedback.getId() != null) {
           query.append (getQueryWHERE_AND (isWhereSet));
           isWhereSet = true;
           query.append(" conferenceFeedback.id = "+ conferenceFeedback.getId() + " ");
         }
	        	List list = getJpaTemplate().find(query.toString());
        if (list!=null && !list.isEmpty())
           return (ConferenceFeedback)list.get(0);    
        return null;
    }  
     
    /**
     * Loads the ConferenceFeedback entity which is related to an instance of
     * ConferenceFeedback and its dependent objects one to many
     * @param Long id
     * @return ConferenceFeedback The ConferenceFeedback entity
     */
    public ConferenceFeedback loadFullConferenceFeedback(Long id) {
    	return null;//(ConferenceFeedback)getJpaTemplate().queryForObject("loadFullConferenceFeedback", id);
    }

    /**
     * Searches a list of ConferenceFeedback entity 
     * @param ConferenceFeedback conferenceFeedback
     * @return List
     */
    public List<ConferenceFeedback> searchPrototypeConferenceFeedback(ConferenceFeedback conferenceFeedback) {      
       return searchPrototype (conferenceFeedback, null);            
    }  
    
    protected List<ConferenceFeedback> searchPrototype (ConferenceFeedback conferenceFeedback, Integer maxResults) { 
       Query hquery = getEntityManager().createQuery(getConferenceFeedbackSearchEqualQuery (conferenceFeedback));
       if (maxResults!=null)
          hquery.setMaxResults(maxResults);
       return hquery.getResultList();         
    }        
    
    public List<ConferenceFeedback> searchPrototypeConferenceFeedback (List<ConferenceFeedback> conferenceFeedbacks) {
       return searchPrototype (conferenceFeedbacks, null);  
    }

    protected List<ConferenceFeedback> searchPrototype (List<ConferenceFeedback> conferenceFeedbacks, Integer maxResults) {    
       //TODO convert setMaxResults in JPA if (maxResults!=null)
       //   getJpaTemplate().setMaxResults(maxResults);
       return getJpaTemplate().find(getConferenceFeedbackSearchEqualQuery (null, conferenceFeedbacks));            
    }    

    public List<ConferenceFeedback> searchDistinctPrototypeConferenceFeedback (ConferenceFeedback conferenceFeedbackMask, List<ConferenceFeedback> conferenceFeedbacks) {
        return getJpaTemplate().find(getConferenceFeedbackSearchEqualQuery (conferenceFeedbackMask, conferenceFeedbacks));    
    }
         
	/**
     * Searches a list of ConferenceFeedback entity 
     * @param ConferenceFeedback conferenceFeedbackPositive
     * @param ConferenceFeedback conferenceFeedbackNegative
     * @return List
     */
    public List<ConferenceFeedback> searchPrototypeConferenceFeedback(ConferenceFeedback conferenceFeedbackPositive, ConferenceFeedback conferenceFeedbackNegative) {
	    return getJpaTemplate().find(getConferenceFeedbackSearchEqualQuery (conferenceFeedbackPositive, conferenceFeedbackNegative));  
    }

    /**
    * return a jql query search on a ConferenceFeedback prototype
    */
    protected String getConferenceFeedbackSearchEqualQuery (ConferenceFeedback conferenceFeedback) {
        StringBuffer query = new StringBuffer();
        StringBuffer queryWhere = new StringBuffer();
        query.append ("SELECT conferenceFeedback FROM ConferenceFeedback conferenceFeedback ");
        queryWhere.append (getConferenceFeedbackSearchEqualWhereQueryChunk(conferenceFeedback, false));   
	    return getHQuery(query.toString(), queryWhere.toString());
    }


    /**
    * return a jql search for a list of ConferenceFeedback prototype
    */
    protected String getConferenceFeedbackSearchEqualQuery (ConferenceFeedback conferenceFeedbackMask, List<ConferenceFeedback> conferenceFeedbacks) {
        boolean isOrSet = false;
        StringBuffer query = new StringBuffer();
        if (conferenceFeedbackMask !=null)
           query.append (getConferenceFeedbackMaskWhat (conferenceFeedbackMask));
        query.append (" FROM ConferenceFeedback conferenceFeedback ");
        StringBuffer queryWhere = new StringBuffer();
        for (ConferenceFeedback conferenceFeedback : conferenceFeedbacks) {
           if (!isAllNull(conferenceFeedback)) {        
	           queryWhere.append (getQueryOR (isOrSet));
	           isOrSet = true;        
	           queryWhere.append (" ("+getConferenceFeedbackSearchEqualWhereQueryChunk(conferenceFeedback, false)+") ");
           }
        }
	    return getHQuery(query.toString(), queryWhere.toString());
    }	
    
    protected String getConferenceFeedbackMaskWhat (ConferenceFeedback conferenceFeedbackMask) {
        boolean isCommaSet = false;
        StringBuffer query = new StringBuffer("SELECT DISTINCT ");
        if (conferenceFeedbackMask.getId() != null) {
           query.append (getQueryComma (isCommaSet));
           isCommaSet = true;
           query.append(" id ");
        }
        if (conferenceFeedbackMask.getComment() != null) {
           query.append (getQueryComma (isCommaSet));
           isCommaSet = true;
           query.append(" comment ");
        }
        if (conferenceFeedbackMask.getFeedbackDate() != null) {
           query.append (getQueryComma (isCommaSet));
           isCommaSet = true;
           query.append(" feedbackDate ");
        }
        if (conferenceFeedbackMask.getConferenceId() != null) {
           query.append (getQueryComma (isCommaSet));
           isCommaSet = true;
           query.append(" conferenceId ");
        }
        if (conferenceFeedbackMask.getConferenceMemberId() != null) {
           query.append (getQueryComma (isCommaSet));
           isCommaSet = true;
           query.append(" conferenceMemberId ");
        }
        if (!isCommaSet)
           return "";
	    return query.toString();        
    }
    
    protected String getConferenceFeedbackSearchEqualWhereQueryChunk (ConferenceFeedback conferenceFeedback, boolean isAndSet) {
        StringBuffer query = new StringBuffer();
        if (conferenceFeedback.getId() != null) {
           query.append (getQueryAND (isAndSet));
           isAndSet = true;
           query.append(" conferenceFeedback.id = "+ conferenceFeedback.getId() + " ");
        }
        if (conferenceFeedback.getComment() != null) {
           query.append (getQueryAND (isAndSet));
           isAndSet = true;
           query.append(" conferenceFeedback.comment = '"+ conferenceFeedback.getComment()+"' ");
        }
        if (conferenceFeedback.getFeedbackDate() != null) {
           query.append (getQueryAND (isAndSet));
           isAndSet = true;
           query.append(" conferenceFeedback.feedbackDate = '"+ conferenceFeedback.getFeedbackDate()+"' ");
        }
        if (conferenceFeedback.getConferenceId() != null) {
           query.append (getQueryAND (isAndSet));
           isAndSet = true;
           query.append(" conferenceFeedback.conferenceId = "+ conferenceFeedback.getConferenceId() + " ");
        }
        if (conferenceFeedback.getConferenceMemberId() != null) {
           query.append (getQueryAND (isAndSet));
           isAndSet = true;
           query.append(" conferenceFeedback.conferenceMemberId = "+ conferenceFeedback.getConferenceMemberId() + " ");
        }
	    return query.toString();
    }   
     
    /**
    * return a jql search on a ConferenceFeedback prototype with positive and negative beans
    */
    protected String getConferenceFeedbackSearchEqualQuery (ConferenceFeedback conferenceFeedbackPositive, ConferenceFeedback conferenceFeedbackNegative) {
        boolean isWhereSet = false;
        StringBuffer query = new StringBuffer();
        query.append (" SELECT conferenceFeedback FROM ConferenceFeedback conferenceFeedback ");
        if (conferenceFeedbackPositive!=null && conferenceFeedbackPositive.getId() != null) {
           query.append (getQueryWHERE_AND (isWhereSet));
           isWhereSet = true;
           query.append(" conferenceFeedback.id = "+ conferenceFeedbackPositive.getId() + " ");
        } else if (conferenceFeedbackNegative.getId() != null) {
           query.append (getQueryWHERE_AND (isWhereSet));
           isWhereSet = true;   
           query.append(" conferenceFeedback.id is null ");
        }
        if (conferenceFeedbackPositive!=null && conferenceFeedbackPositive.getComment() != null) {
           query.append (getQueryWHERE_AND (isWhereSet));
           isWhereSet = true;
           query.append(" conferenceFeedback.comment = '"+ conferenceFeedbackPositive.getComment()+"' ");
        } else if (conferenceFeedbackNegative.getComment() != null) {
           query.append (getQueryWHERE_AND (isWhereSet));
           isWhereSet = true;   
           query.append(" conferenceFeedback.comment is null ");
        }
        if (conferenceFeedbackPositive!=null && conferenceFeedbackPositive.getFeedbackDate() != null) {
           query.append (getQueryWHERE_AND (isWhereSet));
           isWhereSet = true;
           query.append(" conferenceFeedback.feedbackDate = '"+ conferenceFeedbackPositive.getFeedbackDate()+"' ");
        } else if (conferenceFeedbackNegative.getFeedbackDate() != null) {
           query.append (getQueryWHERE_AND (isWhereSet));
           isWhereSet = true;   
           query.append(" conferenceFeedback.feedbackDate is null ");
        }
        if (conferenceFeedbackPositive!=null && conferenceFeedbackPositive.getConferenceId() != null) {
           query.append (getQueryWHERE_AND (isWhereSet));
           isWhereSet = true;
           query.append(" conferenceFeedback.conferenceId = "+ conferenceFeedbackPositive.getConferenceId() + " ");
        } else if (conferenceFeedbackNegative.getConferenceId() != null) {
           query.append (getQueryWHERE_AND (isWhereSet));
           isWhereSet = true;   
           query.append(" conferenceFeedback.conferenceId is null ");
        }
        if (conferenceFeedbackPositive!=null && conferenceFeedbackPositive.getConferenceMemberId() != null) {
           query.append (getQueryWHERE_AND (isWhereSet));
           isWhereSet = true;
           query.append(" conferenceFeedback.conferenceMemberId = "+ conferenceFeedbackPositive.getConferenceMemberId() + " ");
        } else if (conferenceFeedbackNegative.getConferenceMemberId() != null) {
           query.append (getQueryWHERE_AND (isWhereSet));
           isWhereSet = true;   
           query.append(" conferenceFeedback.conferenceMemberId is null ");
        }
	    return query.toString();
    }
    
    /**
     * Load a paginated list of ConferenceFeedback entity dependent of pagination criteria
     * @param PaginationCriteria paginationCriteria
     * @return List
     */
    public List <ConferenceFeedback> loadPaginatedConferenceFeedback (ConferenceFeedback conferenceFeedback, PaginationCriteria paginationCriteria) {
	    List<Integer> page = loadPaginatedConferenceFeedbackIdentitiesFromStartPositionId(conferenceFeedback, paginationCriteria);
    	int start = paginationCriteria.getNumberOfRowsReturned()*(paginationCriteria.getPageNumber());
    	int max = page.size();
    	if (start<max) {
    	   List<Integer> returnPage = page.subList(start, max);	
           StringBuffer query = new StringBuffer();
           query.append (" SELECT conferenceFeedback FROM ConferenceFeedback conferenceFeedback ");      
	       query.append(" where conferenceFeedback.id in (");
	       for (Iterator iter = returnPage.iterator(); iter.hasNext();) {
			  Integer id = (Integer) iter.next();
			  query.append(id.toString());
		      if (iter.hasNext())
			     query.append(",");
		   }
	       query.append(") ");
	       return getJpaTemplate().find(query.toString()); 
    	} 
        return new ArrayList<ConferenceFeedback>();
    }      

    protected List<Integer> loadPaginatedConferenceFeedbackIdentitiesFromStartPositionId (ConferenceFeedback conferenceFeedback, PaginationCriteria paginationCriteria) {
       boolean isWhereSet = false;
       StringBuffer query = new StringBuffer();
       query.append ("select conferenceFeedback.id ");
       query.append (getConferenceFeedbackSearchEqualQuery (conferenceFeedback));
       if (paginationCriteria.getOrderList()!=null) {
    	   query.append(" order by "+paginationCriteria.getOrderList());
       }
       int maxResult = paginationCriteria.getNumberOfRowsReturned()*(1+paginationCriteria.getPageNumber());
       List<Integer> set = getEntityManager().createNamedQuery(query.toString()).setMaxResults(maxResult).getResultList();
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
    
    protected void convertTransientReferenceToNull (ConferenceFeedback conferenceFeedback) {
	   conferenceFeedback.setConferenceId ((Conference)null);
	   conferenceFeedback.setConferenceMemberId ((ConferenceMember)null);
    }
}
