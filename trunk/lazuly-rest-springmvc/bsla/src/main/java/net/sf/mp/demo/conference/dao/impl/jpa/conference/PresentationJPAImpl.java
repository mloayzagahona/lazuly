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
import net.sf.mp.demo.conference.dao.face.conference.PresentationDao;
import net.sf.mp.demo.conference.domain.conference.Presentation;
import net.sf.mp.demo.conference.domain.conference.PresentationPlace;

/**
 *
 * <p>Title: PresentationJPAImpl</p>
 *
 * <p>Description: Interface of a Data access object dealing with PresentationJPAImpl
 * persistence. It offers a set of methods which allow for saving,
 * deleting and searching PresentationJPAImpl objects</p>
 *
 */

public class PresentationJPAImpl extends JpaDaoSupport implements PresentationDao {

	public PresentationJPAImpl () {}

    /**
     * Inserts a Presentation entity 
     * @param Presentation presentation
     */
    public void insertPresentation(Presentation presentation) {
       convertTransientReferenceToNull (presentation);
       getJpaTemplate().persist(presentation);
    }

    protected void insertPresentation(EntityManager emForRecursiveDao, Presentation presentation) {
       emForRecursiveDao.persist(presentation);
    } 
    /**
     * Inserts a list of Presentation entity 
     * @param List<Presentation> presentations
     */
    public void insertPresentations(List<Presentation> presentations) {
    	//TODO
    }
    /**
     * Updates a Presentation entity 
     * @param Presentation presentation
     */
    public Presentation updatePresentation(Presentation presentation) {
       convertTransientReferenceToNull (presentation);
       return getJpaTemplate().merge(presentation);
    }

	/**
     * Updates a Presentation entity with only the attributes set into Presentation.
	 * The primary keys are to be set for this method to operate.
	 * This is a performance friendly feature, which remove the udibiquous full load and full update when an
	 * update is issued
     * Remark: The primary keys cannot be update by this methods, nor are the attributes that must be set to null.
     * @param Presentation presentation
    */ 
    @Transactional
    public int updateNotNullOnlyPresentation(Presentation presentation) {
        Query jpaQuery = getEntityManager().createQuery(getUpdateNotNullOnlyPresentationQueryChunk(presentation));
        if (presentation.getId() != null) {
           jpaQuery.setParameter ("id", presentation.getId());
        }   
        if (presentation.getStartTime() != null) {
           jpaQuery.setParameter ("startTime", presentation.getStartTime());
        }   
        if (presentation.getEndTime() != null) {
           jpaQuery.setParameter ("endTime", presentation.getEndTime());
        }   
        if (presentation.getAbstractName() != null) {
           jpaQuery.setParameter ("abstractName", presentation.getAbstractName());
        }   
        if (presentation.getTitle() != null) {
           jpaQuery.setParameter ("title", presentation.getTitle());
        }   
        if (presentation.getStatus() != null) {
           jpaQuery.setParameter ("status", presentation.getStatus());
        }   
        if (presentation.getPresentationPlaceId() != null) {
           jpaQuery.setParameter ("presentationPlaceId", presentation.getPresentationPlaceId());
        }   
        if (presentation.getProposalTime() != null) {
           jpaQuery.setParameter ("proposalTime", presentation.getProposalTime());
        }   
		return jpaQuery.executeUpdate();
    }

    protected String getUpdateNotNullOnlyPresentationQueryChunkPrototype (Presentation presentation) {
        boolean isWhereSet = false;
        StringBuffer query = new StringBuffer();
        query.append (" update Presentation presentation ");
        if (presentation.getStartTime() != null) {
           query.append (getQueryCommaSet (isWhereSet));
           isWhereSet = true; 
           query.append(" presentation.startTime = :startTime");
        }
        if (presentation.getEndTime() != null) {
           query.append (getQueryCommaSet (isWhereSet));
           isWhereSet = true; 
           query.append(" presentation.endTime = :endTime");
        }
        if (presentation.getAbstractName() != null) {
           query.append (getQueryCommaSet (isWhereSet));
           isWhereSet = true; 
           query.append(" presentation.abstractName = :abstractName");
        }
        if (presentation.getTitle() != null) {
           query.append (getQueryCommaSet (isWhereSet));
           isWhereSet = true; 
           query.append(" presentation.title = :title");
        }
        if (presentation.getStatus() != null) {
           query.append (getQueryCommaSet (isWhereSet));
           isWhereSet = true; 
           query.append(" presentation.status = :status");
        }
        if (presentation.getPresentationPlaceId() != null) {
           query.append (getQueryCommaSet (isWhereSet));
           isWhereSet = true; 
           query.append(" presentation.presentationPlaceId = :presentationPlaceId");
        }
        if (presentation.getProposalTime() != null) {
           query.append (getQueryCommaSet (isWhereSet));
           isWhereSet = true; 
           query.append(" presentation.proposalTime = :proposalTime");
        }
        return query.toString();
    }
    
    protected String getUpdateNotNullOnlyPresentationQueryChunk (Presentation presentation) {
        boolean isWhereSet = false;
        StringBuffer query = new StringBuffer(getUpdateNotNullOnlyPresentationQueryChunkPrototype(presentation));
        if (presentation.getId() != null) {
           query.append (getQueryWHERE_AND (isWhereSet));
           isWhereSet = true;
			     query.append(" presentation.id = :id");
        }
        return query.toString();
    }
    
                
	protected Presentation assignBlankToNull (Presentation presentation) {
	    if (presentation==null)
			return null;
        if (presentation.getAbstractName()!=null && presentation.getAbstractName().equals(""))
           presentation.setAbstractName((String)null);
        if (presentation.getTitle()!=null && presentation.getTitle().equals(""))
           presentation.setTitle((String)null);
        if (presentation.getStatus()!=null && presentation.getStatus().equals(""))
           presentation.setStatus((String)null);
		return presentation;
	}
	
	protected boolean isAllNull (Presentation presentation) {
	    if (presentation==null)
			return true;
        if (presentation.getId()!=null) 
            return false;
        if (presentation.getStartTime()!=null) 
            return false;
        if (presentation.getEndTime()!=null) 
            return false;
        if (presentation.getAbstractName()!=null) 
            return false;
        if (presentation.getTitle()!=null) 
            return false;
        if (presentation.getStatus()!=null) 
            return false;
        if (presentation.getPresentationPlaceId()!=null) 
            return false;
        if (presentation.getProposalTime()!=null) 
            return false;
		return true;
	}
		
	@Transactional
    public int updateNotNullOnlyPrototypePresentation(Presentation presentation, Presentation prototypeCriteria) {
        boolean isWhereSet = false;
        StringBuffer query = new StringBuffer();
        query.append (" update Presentation presentation ");
        if (presentation.getId() != null) {
           query.append (getQueryCommaSet (isWhereSet));
           isWhereSet = true; 
           query.append(" presentation.id = "+ presentation.getId() + " ");
        }
        if (presentation.getStartTime() != null) {
           query.append (getQueryCommaSet (isWhereSet));
           isWhereSet = true; 
           query.append(" presentation.startTime = '"+ presentation.getStartTime()+"' ");
        }
        if (presentation.getEndTime() != null) {
           query.append (getQueryCommaSet (isWhereSet));
           isWhereSet = true; 
           query.append(" presentation.endTime = '"+ presentation.getEndTime()+"' ");
        }
        if (presentation.getAbstractName() != null) {
           query.append (getQueryCommaSet (isWhereSet));
           isWhereSet = true; 
           query.append(" presentation.abstractName = '"+ presentation.getAbstractName()+"' ");
        }
        if (presentation.getTitle() != null) {
           query.append (getQueryCommaSet (isWhereSet));
           isWhereSet = true; 
           query.append(" presentation.title = '"+ presentation.getTitle()+"' ");
        }
        if (presentation.getStatus() != null) {
           query.append (getQueryCommaSet (isWhereSet));
           isWhereSet = true; 
           query.append(" presentation.status = '"+ presentation.getStatus()+"' ");
        }
        if (presentation.getPresentationPlaceId() != null) {
           query.append (getQueryCommaSet (isWhereSet));
           isWhereSet = true; 
           query.append(" presentation.presentationPlaceId = "+ presentation.getPresentationPlaceId() + " ");
        }
        if (presentation.getProposalTime() != null) {
           query.append (getQueryCommaSet (isWhereSet));
           isWhereSet = true; 
           query.append(" presentation.proposalTime = '"+ presentation.getProposalTime()+"' ");
        }
		isWhereSet = false; 
        if (prototypeCriteria.getId() != null) {
           query.append (getQueryWHERE_AND (isWhereSet));
           isWhereSet = true;
           query.append(" presentation.id = "+ prototypeCriteria.getId() + " ");
        }
        if (prototypeCriteria.getStartTime() != null) {
           query.append (getQueryWHERE_AND (isWhereSet));
           isWhereSet = true;
           query.append(" presentation.startTime = '"+ prototypeCriteria.getStartTime()+"' ");
        }
        if (prototypeCriteria.getEndTime() != null) {
           query.append (getQueryWHERE_AND (isWhereSet));
           isWhereSet = true;
           query.append(" presentation.endTime = '"+ prototypeCriteria.getEndTime()+"' ");
        }
        if (prototypeCriteria.getAbstractName() != null) {
           query.append (getQueryWHERE_AND (isWhereSet));
           isWhereSet = true;
           query.append(" presentation.abstractName = '"+ prototypeCriteria.getAbstractName()+"' ");
        }
        if (prototypeCriteria.getTitle() != null) {
           query.append (getQueryWHERE_AND (isWhereSet));
           isWhereSet = true;
           query.append(" presentation.title = '"+ prototypeCriteria.getTitle()+"' ");
        }
        if (prototypeCriteria.getStatus() != null) {
           query.append (getQueryWHERE_AND (isWhereSet));
           isWhereSet = true;
           query.append(" presentation.status = '"+ prototypeCriteria.getStatus()+"' ");
        }
        if (prototypeCriteria.getPresentationPlaceId() != null) {
           query.append (getQueryWHERE_AND (isWhereSet));
           isWhereSet = true;
           query.append(" presentation.presentationPlaceId = "+ prototypeCriteria.getPresentationPlaceId() + " ");
        }
        if (prototypeCriteria.getProposalTime() != null) {
           query.append (getQueryWHERE_AND (isWhereSet));
           isWhereSet = true;
           query.append(" presentation.proposalTime = '"+ prototypeCriteria.getProposalTime()+"' ");
        }
        Query jpaQuery = getEntityManager().createQuery(query.toString());
		return jpaQuery.executeUpdate();
    }
     
     /**
     * Saves a Presentation entity 
     * @param Presentation presentation
     */
    public void savePresentation(Presentation presentation) {
       //getJpaTemplate().persist(presentation);
       convertTransientReferenceToNull (presentation);
       if (getJpaTemplate().contains(presentation)) {
          getJpaTemplate().merge(presentation);
       } else {
          getJpaTemplate().persist(presentation);
       }
       getJpaTemplate().flush(); 
    }
       
    /**
     * Deletes a Presentation entity 
     * @param Presentation presentation
     */
    public void deletePresentation(Presentation presentation) {
      getJpaTemplate().remove(presentation);
    }
    
    /**
     * Loads the Presentation entity which is related to an instance of
     * Presentation
     * @param Long id
     * @return Presentation The Presentation entity
     
    public Presentation loadPresentation(Long id) {
    	return (Presentation)getJpaTemplate().get(Presentation.class, id);
    }
*/
  
    /**
     * Loads the Presentation entity which is related to an instance of
     * Presentation
     * @param java.lang.Long Id
     * @return Presentation The Presentation entity
     */
    public Presentation loadPresentation(java.lang.Long id) {
    	return (Presentation)getJpaTemplate().find(Presentation.class, id);
    }
    
    /**
     * Loads a list of Presentation entity 
     * @param List<java.lang.Long> ids
     * @return List<Presentation> The Presentation entity
     */
    public List<Presentation> loadPresentationListByPresentation (List<Presentation> presentations) {
       return null;
    }
    
    /**
     * Loads a list of Presentation entity 
     * @param List<java.lang.Long> ids
     * @return List<Presentation> The Presentation entity
     */
    public List<Presentation> loadPresentationListById(List<java.lang.Long> ids){
       return null;
    }
    
    /**
     * Loads the Presentation entity which is related to an instance of
     * Presentation and its dependent one to many objects
     * @param Long id
     * @return Presentation The Presentation entity
     */
    public Presentation loadFullFirstLevelPresentation(java.lang.Long id) {
        List list = getJpaTemplate().find(
                     "SELECT presentation FROM Presentation presentation "
                     + " LEFT JOIN presentation.evaluationPresentationIds "   
                     + " LEFT JOIN presentation.speakerPresentationPresentationIds "   
                     + " WHERE presentation.id = "+id
               );
         if (list!=null && !list.isEmpty())
            return (Presentation)list.get(0);
         return null;
    	//return null;//(Presentation) getJpaTemplate().queryForObject("loadFullFirstLevelPresentation", id);
    }

    /**
     * Loads the Presentation entity which is related to an instance of
     * Presentation
     * @param Presentation presentation
     * @return Presentation The Presentation entity
     */
    public Presentation loadFullFirstLevelPresentation(Presentation presentation) {
        boolean isWhereSet = false;
        StringBuffer query = new StringBuffer();
        query.append ("SELECT presentation FROM Presentation presentation ");
        if (presentation.getId() != null) {
           query.append (getQueryWHERE_AND (isWhereSet));
           isWhereSet = true;
           query.append(" presentation.id = "+ presentation.getId() + " ");
         }
	        	List list = getJpaTemplate().find(query.toString());
        if (list!=null && !list.isEmpty())
           return (Presentation)list.get(0);    
        return null;
    }  
     
    /**
     * Loads the Presentation entity which is related to an instance of
     * Presentation and its dependent objects one to many
     * @param Long id
     * @return Presentation The Presentation entity
     */
    public Presentation loadFullPresentation(Long id) {
    	return null;//(Presentation)getJpaTemplate().queryForObject("loadFullPresentation", id);
    }

    /**
     * Searches a list of Presentation entity 
     * @param Presentation presentation
     * @return List
     */
    public List<Presentation> searchPrototypePresentation(Presentation presentation) {      
       return searchPrototype (presentation, null);            
    }  
    
    protected List<Presentation> searchPrototype (Presentation presentation, Integer maxResults) { 
       Query hquery = getEntityManager().createQuery(getPresentationSearchEqualQuery (presentation));
       if (maxResults!=null)
          hquery.setMaxResults(maxResults);
       return hquery.getResultList();         
    }        
    
    public List<Presentation> searchPrototypePresentation (List<Presentation> presentations) {
       return searchPrototype (presentations, null);  
    }

    protected List<Presentation> searchPrototype (List<Presentation> presentations, Integer maxResults) {    
       //TODO convert setMaxResults in JPA if (maxResults!=null)
       //   getJpaTemplate().setMaxResults(maxResults);
       return getJpaTemplate().find(getPresentationSearchEqualQuery (null, presentations));            
    }    

    public List<Presentation> searchDistinctPrototypePresentation (Presentation presentationMask, List<Presentation> presentations) {
        return getJpaTemplate().find(getPresentationSearchEqualQuery (presentationMask, presentations));    
    }
         
	/**
     * Searches a list of Presentation entity 
     * @param Presentation presentationPositive
     * @param Presentation presentationNegative
     * @return List
     */
    public List<Presentation> searchPrototypePresentation(Presentation presentationPositive, Presentation presentationNegative) {
	    return getJpaTemplate().find(getPresentationSearchEqualQuery (presentationPositive, presentationNegative));  
    }

    /**
    * return a jql query search on a Presentation prototype
    */
    protected String getPresentationSearchEqualQuery (Presentation presentation) {
        StringBuffer query = new StringBuffer();
        StringBuffer queryWhere = new StringBuffer();
        query.append ("SELECT presentation FROM Presentation presentation ");
        queryWhere.append (getPresentationSearchEqualWhereQueryChunk(presentation, false));   
	    return getHQuery(query.toString(), queryWhere.toString());
    }


    /**
    * return a jql search for a list of Presentation prototype
    */
    protected String getPresentationSearchEqualQuery (Presentation presentationMask, List<Presentation> presentations) {
        boolean isOrSet = false;
        StringBuffer query = new StringBuffer();
        if (presentationMask !=null)
           query.append (getPresentationMaskWhat (presentationMask));
        query.append (" FROM Presentation presentation ");
        StringBuffer queryWhere = new StringBuffer();
        for (Presentation presentation : presentations) {
           if (!isAllNull(presentation)) {        
	           queryWhere.append (getQueryOR (isOrSet));
	           isOrSet = true;        
	           queryWhere.append (" ("+getPresentationSearchEqualWhereQueryChunk(presentation, false)+") ");
           }
        }
	    return getHQuery(query.toString(), queryWhere.toString());
    }	
    
    protected String getPresentationMaskWhat (Presentation presentationMask) {
        boolean isCommaSet = false;
        StringBuffer query = new StringBuffer("SELECT DISTINCT ");
        if (presentationMask.getId() != null) {
           query.append (getQueryComma (isCommaSet));
           isCommaSet = true;
           query.append(" id ");
        }
        if (presentationMask.getStartTime() != null) {
           query.append (getQueryComma (isCommaSet));
           isCommaSet = true;
           query.append(" startTime ");
        }
        if (presentationMask.getEndTime() != null) {
           query.append (getQueryComma (isCommaSet));
           isCommaSet = true;
           query.append(" endTime ");
        }
        if (presentationMask.getAbstractName() != null) {
           query.append (getQueryComma (isCommaSet));
           isCommaSet = true;
           query.append(" abstractName ");
        }
        if (presentationMask.getTitle() != null) {
           query.append (getQueryComma (isCommaSet));
           isCommaSet = true;
           query.append(" title ");
        }
        if (presentationMask.getStatus() != null) {
           query.append (getQueryComma (isCommaSet));
           isCommaSet = true;
           query.append(" status ");
        }
        if (presentationMask.getPresentationPlaceId() != null) {
           query.append (getQueryComma (isCommaSet));
           isCommaSet = true;
           query.append(" presentationPlaceId ");
        }
        if (presentationMask.getProposalTime() != null) {
           query.append (getQueryComma (isCommaSet));
           isCommaSet = true;
           query.append(" proposalTime ");
        }
        if (!isCommaSet)
           return "";
	    return query.toString();        
    }
    
    protected String getPresentationSearchEqualWhereQueryChunk (Presentation presentation, boolean isAndSet) {
        StringBuffer query = new StringBuffer();
        if (presentation.getId() != null) {
           query.append (getQueryAND (isAndSet));
           isAndSet = true;
           query.append(" presentation.id = "+ presentation.getId() + " ");
        }
        if (presentation.getStartTime() != null) {
           query.append (getQueryAND (isAndSet));
           isAndSet = true;
           query.append(" presentation.startTime = '"+ presentation.getStartTime()+"' ");
        }
        if (presentation.getEndTime() != null) {
           query.append (getQueryAND (isAndSet));
           isAndSet = true;
           query.append(" presentation.endTime = '"+ presentation.getEndTime()+"' ");
        }
        if (presentation.getAbstractName() != null) {
           query.append (getQueryAND (isAndSet));
           isAndSet = true;
           query.append(" presentation.abstractName = '"+ presentation.getAbstractName()+"' ");
        }
        if (presentation.getTitle() != null) {
           query.append (getQueryAND (isAndSet));
           isAndSet = true;
           query.append(" presentation.title = '"+ presentation.getTitle()+"' ");
        }
        if (presentation.getStatus() != null) {
           query.append (getQueryAND (isAndSet));
           isAndSet = true;
           query.append(" presentation.status = '"+ presentation.getStatus()+"' ");
        }
        if (presentation.getPresentationPlaceId() != null) {
           query.append (getQueryAND (isAndSet));
           isAndSet = true;
           query.append(" presentation.presentationPlaceId = "+ presentation.getPresentationPlaceId() + " ");
        }
        if (presentation.getProposalTime() != null) {
           query.append (getQueryAND (isAndSet));
           isAndSet = true;
           query.append(" presentation.proposalTime = '"+ presentation.getProposalTime()+"' ");
        }
	    return query.toString();
    }   
     
    /**
    * return a jql search on a Presentation prototype with positive and negative beans
    */
    protected String getPresentationSearchEqualQuery (Presentation presentationPositive, Presentation presentationNegative) {
        boolean isWhereSet = false;
        StringBuffer query = new StringBuffer();
        query.append (" SELECT presentation FROM Presentation presentation ");
        if (presentationPositive!=null && presentationPositive.getId() != null) {
           query.append (getQueryWHERE_AND (isWhereSet));
           isWhereSet = true;
           query.append(" presentation.id = "+ presentationPositive.getId() + " ");
        } else if (presentationNegative.getId() != null) {
           query.append (getQueryWHERE_AND (isWhereSet));
           isWhereSet = true;   
           query.append(" presentation.id is null ");
        }
        if (presentationPositive!=null && presentationPositive.getStartTime() != null) {
           query.append (getQueryWHERE_AND (isWhereSet));
           isWhereSet = true;
           query.append(" presentation.startTime = '"+ presentationPositive.getStartTime()+"' ");
        } else if (presentationNegative.getStartTime() != null) {
           query.append (getQueryWHERE_AND (isWhereSet));
           isWhereSet = true;   
           query.append(" presentation.startTime is null ");
        }
        if (presentationPositive!=null && presentationPositive.getEndTime() != null) {
           query.append (getQueryWHERE_AND (isWhereSet));
           isWhereSet = true;
           query.append(" presentation.endTime = '"+ presentationPositive.getEndTime()+"' ");
        } else if (presentationNegative.getEndTime() != null) {
           query.append (getQueryWHERE_AND (isWhereSet));
           isWhereSet = true;   
           query.append(" presentation.endTime is null ");
        }
        if (presentationPositive!=null && presentationPositive.getAbstractName() != null) {
           query.append (getQueryWHERE_AND (isWhereSet));
           isWhereSet = true;
           query.append(" presentation.abstractName = '"+ presentationPositive.getAbstractName()+"' ");
        } else if (presentationNegative.getAbstractName() != null) {
           query.append (getQueryWHERE_AND (isWhereSet));
           isWhereSet = true;   
           query.append(" presentation.abstractName is null ");
        }
        if (presentationPositive!=null && presentationPositive.getTitle() != null) {
           query.append (getQueryWHERE_AND (isWhereSet));
           isWhereSet = true;
           query.append(" presentation.title = '"+ presentationPositive.getTitle()+"' ");
        } else if (presentationNegative.getTitle() != null) {
           query.append (getQueryWHERE_AND (isWhereSet));
           isWhereSet = true;   
           query.append(" presentation.title is null ");
        }
        if (presentationPositive!=null && presentationPositive.getStatus() != null) {
           query.append (getQueryWHERE_AND (isWhereSet));
           isWhereSet = true;
           query.append(" presentation.status = '"+ presentationPositive.getStatus()+"' ");
        } else if (presentationNegative.getStatus() != null) {
           query.append (getQueryWHERE_AND (isWhereSet));
           isWhereSet = true;   
           query.append(" presentation.status is null ");
        }
        if (presentationPositive!=null && presentationPositive.getPresentationPlaceId() != null) {
           query.append (getQueryWHERE_AND (isWhereSet));
           isWhereSet = true;
           query.append(" presentation.presentationPlaceId = "+ presentationPositive.getPresentationPlaceId() + " ");
        } else if (presentationNegative.getPresentationPlaceId() != null) {
           query.append (getQueryWHERE_AND (isWhereSet));
           isWhereSet = true;   
           query.append(" presentation.presentationPlaceId is null ");
        }
        if (presentationPositive!=null && presentationPositive.getProposalTime() != null) {
           query.append (getQueryWHERE_AND (isWhereSet));
           isWhereSet = true;
           query.append(" presentation.proposalTime = '"+ presentationPositive.getProposalTime()+"' ");
        } else if (presentationNegative.getProposalTime() != null) {
           query.append (getQueryWHERE_AND (isWhereSet));
           isWhereSet = true;   
           query.append(" presentation.proposalTime is null ");
        }
	    return query.toString();
    }
    
    /**
     * Load a paginated list of Presentation entity dependent of pagination criteria
     * @param PaginationCriteria paginationCriteria
     * @return List
     */
    public List <Presentation> loadPaginatedPresentation (Presentation presentation, PaginationCriteria paginationCriteria) {
	    List<Long> page = loadPaginatedPresentationIdentitiesFromStartPositionId(presentation, paginationCriteria);
    	int start = paginationCriteria.getNumberOfRowsReturned()*(paginationCriteria.getPageNumber());
    	int max = page.size();
    	if (start<max) {
    	   List<Long> returnPage = page.subList(start, max);	
           StringBuffer query = new StringBuffer();
           query.append (" SELECT presentation FROM Presentation presentation ");      
	       query.append(" where presentation.id in (");
	       for (Iterator iter = returnPage.iterator(); iter.hasNext();) {
			  Long id = (Long) iter.next();
			  query.append(id.toString());
		      if (iter.hasNext())
			     query.append(",");
		   }
	       query.append(") ");
	       return getJpaTemplate().find(query.toString()); 
    	} 
        return new ArrayList<Presentation>();
    }      

    protected List<Long> loadPaginatedPresentationIdentitiesFromStartPositionId (Presentation presentation, PaginationCriteria paginationCriteria) {
       boolean isWhereSet = false;
       StringBuffer query = new StringBuffer();
       query.append ("select presentation.id ");
       query.append (getPresentationSearchEqualQuery (presentation));
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
    
    protected void convertTransientReferenceToNull (Presentation presentation) {
	   presentation.setPresentationPlaceId ((PresentationPlace)null);
    }
}
