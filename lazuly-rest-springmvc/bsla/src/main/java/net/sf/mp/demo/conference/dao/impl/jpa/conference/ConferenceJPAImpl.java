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
import net.sf.mp.demo.conference.dao.face.conference.ConferenceDao;
import net.sf.mp.demo.conference.domain.conference.Conference;
import net.sf.mp.demo.conference.domain.conference.Address;

/**
 *
 * <p>Title: ConferenceJPAImpl</p>
 *
 * <p>Description: Interface of a Data access object dealing with ConferenceJPAImpl
 * persistence. It offers a set of methods which allow for saving,
 * deleting and searching ConferenceJPAImpl objects</p>
 *
 */

public class ConferenceJPAImpl extends JpaDaoSupport implements ConferenceDao {

	public ConferenceJPAImpl () {}

    /**
     * Inserts a Conference entity 
     * @param Conference conference
     */
    public void insertConference(Conference conference) {
       convertTransientReferenceToNull (conference);
       getJpaTemplate().persist(conference);
    }

    protected void insertConference(EntityManager emForRecursiveDao, Conference conference) {
       emForRecursiveDao.persist(conference);
    } 
    /**
     * Inserts a list of Conference entity 
     * @param List<Conference> conferences
     */
    public void insertConferences(List<Conference> conferences) {
    	//TODO
    }
    /**
     * Updates a Conference entity 
     * @param Conference conference
     */
    public Conference updateConference(Conference conference) {
       convertTransientReferenceToNull (conference);
       return getJpaTemplate().merge(conference);
    }

	/**
     * Updates a Conference entity with only the attributes set into Conference.
	 * The primary keys are to be set for this method to operate.
	 * This is a performance friendly feature, which remove the udibiquous full load and full update when an
	 * update is issued
     * Remark: The primary keys cannot be update by this methods, nor are the attributes that must be set to null.
     * @param Conference conference
    */ 
    @Transactional
    public int updateNotNullOnlyConference(Conference conference) {
        Query jpaQuery = getEntityManager().createQuery(getUpdateNotNullOnlyConferenceQueryChunk(conference));
        if (conference.getId() != null) {
           jpaQuery.setParameter ("id", conference.getId());
        }   
        if (conference.getName() != null) {
           jpaQuery.setParameter ("name", conference.getName());
        }   
        if (conference.getStartDate() != null) {
           jpaQuery.setParameter ("startDate", conference.getStartDate());
        }   
        if (conference.getEndDate() != null) {
           jpaQuery.setParameter ("endDate", conference.getEndDate());
        }   
        if (conference.getAddressId() != null) {
           jpaQuery.setParameter ("addressId", conference.getAddressId());
        }   
		return jpaQuery.executeUpdate();
    }

    protected String getUpdateNotNullOnlyConferenceQueryChunkPrototype (Conference conference) {
        boolean isWhereSet = false;
        StringBuffer query = new StringBuffer();
        query.append (" update Conference conference ");
        if (conference.getName() != null) {
           query.append (getQueryCommaSet (isWhereSet));
           isWhereSet = true; 
           query.append(" conference.name = :name");
        }
        if (conference.getStartDate() != null) {
           query.append (getQueryCommaSet (isWhereSet));
           isWhereSet = true; 
           query.append(" conference.startDate = :startDate");
        }
        if (conference.getEndDate() != null) {
           query.append (getQueryCommaSet (isWhereSet));
           isWhereSet = true; 
           query.append(" conference.endDate = :endDate");
        }
        if (conference.getAddressId() != null) {
           query.append (getQueryCommaSet (isWhereSet));
           isWhereSet = true; 
           query.append(" conference.addressId = :addressId");
        }
        return query.toString();
    }
    
    protected String getUpdateNotNullOnlyConferenceQueryChunk (Conference conference) {
        boolean isWhereSet = false;
        StringBuffer query = new StringBuffer(getUpdateNotNullOnlyConferenceQueryChunkPrototype(conference));
        if (conference.getId() != null) {
           query.append (getQueryWHERE_AND (isWhereSet));
           isWhereSet = true;
			     query.append(" conference.id = :id");
        }
        return query.toString();
    }
    
                
	protected Conference assignBlankToNull (Conference conference) {
	    if (conference==null)
			return null;
        if (conference.getName()!=null && conference.getName().equals(""))
           conference.setName((String)null);
		return conference;
	}
	
	protected boolean isAllNull (Conference conference) {
	    if (conference==null)
			return true;
        if (conference.getId()!=null) 
            return false;
        if (conference.getName()!=null) 
            return false;
        if (conference.getStartDate()!=null) 
            return false;
        if (conference.getEndDate()!=null) 
            return false;
        if (conference.getAddressId()!=null) 
            return false;
		return true;
	}
		
	@Transactional
    public int updateNotNullOnlyPrototypeConference(Conference conference, Conference prototypeCriteria) {
        boolean isWhereSet = false;
        StringBuffer query = new StringBuffer();
        query.append (" update Conference conference ");
        if (conference.getId() != null) {
           query.append (getQueryCommaSet (isWhereSet));
           isWhereSet = true; 
           query.append(" conference.id = "+ conference.getId() + " ");
        }
        if (conference.getName() != null) {
           query.append (getQueryCommaSet (isWhereSet));
           isWhereSet = true; 
           query.append(" conference.name = '"+ conference.getName()+"' ");
        }
        if (conference.getStartDate() != null) {
           query.append (getQueryCommaSet (isWhereSet));
           isWhereSet = true; 
           query.append(" conference.startDate = '"+ conference.getStartDate()+"' ");
        }
        if (conference.getEndDate() != null) {
           query.append (getQueryCommaSet (isWhereSet));
           isWhereSet = true; 
           query.append(" conference.endDate = '"+ conference.getEndDate()+"' ");
        }
        if (conference.getAddressId() != null) {
           query.append (getQueryCommaSet (isWhereSet));
           isWhereSet = true; 
           query.append(" conference.addressId = "+ conference.getAddressId() + " ");
        }
		isWhereSet = false; 
        if (prototypeCriteria.getId() != null) {
           query.append (getQueryWHERE_AND (isWhereSet));
           isWhereSet = true;
           query.append(" conference.id = "+ prototypeCriteria.getId() + " ");
        }
        if (prototypeCriteria.getName() != null) {
           query.append (getQueryWHERE_AND (isWhereSet));
           isWhereSet = true;
           query.append(" conference.name = '"+ prototypeCriteria.getName()+"' ");
        }
        if (prototypeCriteria.getStartDate() != null) {
           query.append (getQueryWHERE_AND (isWhereSet));
           isWhereSet = true;
           query.append(" conference.startDate = '"+ prototypeCriteria.getStartDate()+"' ");
        }
        if (prototypeCriteria.getEndDate() != null) {
           query.append (getQueryWHERE_AND (isWhereSet));
           isWhereSet = true;
           query.append(" conference.endDate = '"+ prototypeCriteria.getEndDate()+"' ");
        }
        if (prototypeCriteria.getAddressId() != null) {
           query.append (getQueryWHERE_AND (isWhereSet));
           isWhereSet = true;
           query.append(" conference.addressId = "+ prototypeCriteria.getAddressId() + " ");
        }
        Query jpaQuery = getEntityManager().createQuery(query.toString());
		return jpaQuery.executeUpdate();
    }
     
     /**
     * Saves a Conference entity 
     * @param Conference conference
     */
    public void saveConference(Conference conference) {
       //getJpaTemplate().persist(conference);
       convertTransientReferenceToNull (conference);
       if (getJpaTemplate().contains(conference)) {
          getJpaTemplate().merge(conference);
       } else {
          getJpaTemplate().persist(conference);
       }
       getJpaTemplate().flush(); 
    }
       
    /**
     * Deletes a Conference entity 
     * @param Conference conference
     */
    public void deleteConference(Conference conference) {
      getJpaTemplate().remove(conference);
    }
    
    /**
     * Loads the Conference entity which is related to an instance of
     * Conference
     * @param Long id
     * @return Conference The Conference entity
     
    public Conference loadConference(Long id) {
    	return (Conference)getJpaTemplate().get(Conference.class, id);
    }
*/
  
    /**
     * Loads the Conference entity which is related to an instance of
     * Conference
     * @param java.lang.Long Id
     * @return Conference The Conference entity
     */
    public Conference loadConference(java.lang.Long id) {
    	return (Conference)getJpaTemplate().find(Conference.class, id);
    }
    
    /**
     * Loads a list of Conference entity 
     * @param List<java.lang.Long> ids
     * @return List<Conference> The Conference entity
     */
    public List<Conference> loadConferenceListByConference (List<Conference> conferences) {
       return null;
    }
    
    /**
     * Loads a list of Conference entity 
     * @param List<java.lang.Long> ids
     * @return List<Conference> The Conference entity
     */
    public List<Conference> loadConferenceListById(List<java.lang.Long> ids){
       return null;
    }
    
    /**
     * Loads the Conference entity which is related to an instance of
     * Conference and its dependent one to many objects
     * @param Long id
     * @return Conference The Conference entity
     */
    public Conference loadFullFirstLevelConference(java.lang.Long id) {
        List list = getJpaTemplate().find(
                     "SELECT conference FROM Conference conference "
                     + " LEFT JOIN conference.conferenceFeedbackConferenceIds "   
                     + " LEFT JOIN conference.conferenceMemberConferenceIds "   
                     + " LEFT JOIN conference.sponsorConferenceIds "   
                     + " WHERE conference.id = "+id
               );
         if (list!=null && !list.isEmpty())
            return (Conference)list.get(0);
         return null;
    	//return null;//(Conference) getJpaTemplate().queryForObject("loadFullFirstLevelConference", id);
    }

    /**
     * Loads the Conference entity which is related to an instance of
     * Conference
     * @param Conference conference
     * @return Conference The Conference entity
     */
    public Conference loadFullFirstLevelConference(Conference conference) {
        boolean isWhereSet = false;
        StringBuffer query = new StringBuffer();
        query.append ("SELECT conference FROM Conference conference ");
        query.append (" LEFT JOIN conference.conferenceFeedbackConferenceIds ");
        query.append (" LEFT JOIN conference.conferenceMemberConferenceIds ");
        query.append (" LEFT JOIN conference.sponsorConferenceIds ");
        if (conference.getId() != null) {
           query.append (getQueryWHERE_AND (isWhereSet));
           isWhereSet = true;
           query.append(" conference.id = "+ conference.getId() + " ");
         }
	        	List list = getJpaTemplate().find(query.toString());
        if (list!=null && !list.isEmpty())
           return (Conference)list.get(0);    
        return null;
    }  
     
    /**
     * Loads the Conference entity which is related to an instance of
     * Conference and its dependent objects one to many
     * @param Long id
     * @return Conference The Conference entity
     */
    public Conference loadFullConference(Long id) {
    	return null;//(Conference)getJpaTemplate().queryForObject("loadFullConference", id);
    }

    /**
     * Searches a list of Conference entity 
     * @param Conference conference
     * @return List
     */
    public List<Conference> searchPrototypeConference(Conference conference) {      
       return searchPrototype (conference, null);            
    }  
    
    protected List<Conference> searchPrototype (Conference conference, Integer maxResults) { 
       Query hquery = getEntityManager().createQuery(getConferenceSearchEqualQuery (conference));
       if (maxResults!=null)
          hquery.setMaxResults(maxResults);
       return hquery.getResultList();         
    }        
    
    public List<Conference> searchPrototypeConference (List<Conference> conferences) {
       return searchPrototype (conferences, null);  
    }

    protected List<Conference> searchPrototype (List<Conference> conferences, Integer maxResults) {    
       //TODO convert setMaxResults in JPA if (maxResults!=null)
       //   getJpaTemplate().setMaxResults(maxResults);
       return getJpaTemplate().find(getConferenceSearchEqualQuery (null, conferences));            
    }    

    public List<Conference> searchDistinctPrototypeConference (Conference conferenceMask, List<Conference> conferences) {
        return getJpaTemplate().find(getConferenceSearchEqualQuery (conferenceMask, conferences));    
    }
         
	/**
     * Searches a list of Conference entity 
     * @param Conference conferencePositive
     * @param Conference conferenceNegative
     * @return List
     */
    public List<Conference> searchPrototypeConference(Conference conferencePositive, Conference conferenceNegative) {
	    return getJpaTemplate().find(getConferenceSearchEqualQuery (conferencePositive, conferenceNegative));  
    }

    /**
    * return a jql query search on a Conference prototype
    */
    protected String getConferenceSearchEqualQuery (Conference conference) {
        StringBuffer query = new StringBuffer();
        StringBuffer queryWhere = new StringBuffer();
        query.append ("SELECT conference FROM Conference conference ");
        queryWhere.append (getConferenceSearchEqualWhereQueryChunk(conference, false));   
	    return getHQuery(query.toString(), queryWhere.toString());
    }


    /**
    * return a jql search for a list of Conference prototype
    */
    protected String getConferenceSearchEqualQuery (Conference conferenceMask, List<Conference> conferences) {
        boolean isOrSet = false;
        StringBuffer query = new StringBuffer();
        if (conferenceMask !=null)
           query.append (getConferenceMaskWhat (conferenceMask));
        query.append (" FROM Conference conference ");
        StringBuffer queryWhere = new StringBuffer();
        for (Conference conference : conferences) {
           if (!isAllNull(conference)) {        
	           queryWhere.append (getQueryOR (isOrSet));
	           isOrSet = true;        
	           queryWhere.append (" ("+getConferenceSearchEqualWhereQueryChunk(conference, false)+") ");
           }
        }
	    return getHQuery(query.toString(), queryWhere.toString());
    }	
    
    protected String getConferenceMaskWhat (Conference conferenceMask) {
        boolean isCommaSet = false;
        StringBuffer query = new StringBuffer("SELECT DISTINCT ");
        if (conferenceMask.getId() != null) {
           query.append (getQueryComma (isCommaSet));
           isCommaSet = true;
           query.append(" id ");
        }
        if (conferenceMask.getName() != null) {
           query.append (getQueryComma (isCommaSet));
           isCommaSet = true;
           query.append(" name ");
        }
        if (conferenceMask.getStartDate() != null) {
           query.append (getQueryComma (isCommaSet));
           isCommaSet = true;
           query.append(" startDate ");
        }
        if (conferenceMask.getEndDate() != null) {
           query.append (getQueryComma (isCommaSet));
           isCommaSet = true;
           query.append(" endDate ");
        }
        if (conferenceMask.getAddressId() != null) {
           query.append (getQueryComma (isCommaSet));
           isCommaSet = true;
           query.append(" addressId ");
        }
        if (!isCommaSet)
           return "";
	    return query.toString();        
    }
    
    protected String getConferenceSearchEqualWhereQueryChunk (Conference conference, boolean isAndSet) {
        StringBuffer query = new StringBuffer();
        if (conference.getId() != null) {
           query.append (getQueryAND (isAndSet));
           isAndSet = true;
           query.append(" conference.id = "+ conference.getId() + " ");
        }
        if (conference.getName() != null) {
           query.append (getQueryAND (isAndSet));
           isAndSet = true;
           query.append(" conference.name = '"+ conference.getName()+"' ");
        }
        if (conference.getStartDate() != null) {
           query.append (getQueryAND (isAndSet));
           isAndSet = true;
           query.append(" conference.startDate = '"+ conference.getStartDate()+"' ");
        }
        if (conference.getEndDate() != null) {
           query.append (getQueryAND (isAndSet));
           isAndSet = true;
           query.append(" conference.endDate = '"+ conference.getEndDate()+"' ");
        }
        if (conference.getAddressId() != null) {
           query.append (getQueryAND (isAndSet));
           isAndSet = true;
           query.append(" conference.addressId = "+ conference.getAddressId() + " ");
        }
	    return query.toString();
    }   
     
    /**
    * return a jql search on a Conference prototype with positive and negative beans
    */
    protected String getConferenceSearchEqualQuery (Conference conferencePositive, Conference conferenceNegative) {
        boolean isWhereSet = false;
        StringBuffer query = new StringBuffer();
        query.append (" SELECT conference FROM Conference conference ");
        if (conferencePositive!=null && conferencePositive.getId() != null) {
           query.append (getQueryWHERE_AND (isWhereSet));
           isWhereSet = true;
           query.append(" conference.id = "+ conferencePositive.getId() + " ");
        } else if (conferenceNegative.getId() != null) {
           query.append (getQueryWHERE_AND (isWhereSet));
           isWhereSet = true;   
           query.append(" conference.id is null ");
        }
        if (conferencePositive!=null && conferencePositive.getName() != null) {
           query.append (getQueryWHERE_AND (isWhereSet));
           isWhereSet = true;
           query.append(" conference.name = '"+ conferencePositive.getName()+"' ");
        } else if (conferenceNegative.getName() != null) {
           query.append (getQueryWHERE_AND (isWhereSet));
           isWhereSet = true;   
           query.append(" conference.name is null ");
        }
        if (conferencePositive!=null && conferencePositive.getStartDate() != null) {
           query.append (getQueryWHERE_AND (isWhereSet));
           isWhereSet = true;
           query.append(" conference.startDate = '"+ conferencePositive.getStartDate()+"' ");
        } else if (conferenceNegative.getStartDate() != null) {
           query.append (getQueryWHERE_AND (isWhereSet));
           isWhereSet = true;   
           query.append(" conference.startDate is null ");
        }
        if (conferencePositive!=null && conferencePositive.getEndDate() != null) {
           query.append (getQueryWHERE_AND (isWhereSet));
           isWhereSet = true;
           query.append(" conference.endDate = '"+ conferencePositive.getEndDate()+"' ");
        } else if (conferenceNegative.getEndDate() != null) {
           query.append (getQueryWHERE_AND (isWhereSet));
           isWhereSet = true;   
           query.append(" conference.endDate is null ");
        }
        if (conferencePositive!=null && conferencePositive.getAddressId() != null) {
           query.append (getQueryWHERE_AND (isWhereSet));
           isWhereSet = true;
           query.append(" conference.addressId = "+ conferencePositive.getAddressId() + " ");
        } else if (conferenceNegative.getAddressId() != null) {
           query.append (getQueryWHERE_AND (isWhereSet));
           isWhereSet = true;   
           query.append(" conference.addressId is null ");
        }
	    return query.toString();
    }
    
    /**
     * Load a paginated list of Conference entity dependent of pagination criteria
     * @param PaginationCriteria paginationCriteria
     * @return List
     */
    public List <Conference> loadPaginatedConference (Conference conference, PaginationCriteria paginationCriteria) {
	    List<Long> page = loadPaginatedConferenceIdentitiesFromStartPositionId(conference, paginationCriteria);
    	int start = paginationCriteria.getNumberOfRowsReturned()*(paginationCriteria.getPageNumber());
    	int max = page.size();
    	if (start<max) {
    	   List<Long> returnPage = page.subList(start, max);	
           StringBuffer query = new StringBuffer();
           query.append (" SELECT conference FROM Conference conference ");      
	       query.append(" where conference.id in (");
	       for (Iterator iter = returnPage.iterator(); iter.hasNext();) {
			  Long id = (Long) iter.next();
			  query.append(id.toString());
		      if (iter.hasNext())
			     query.append(",");
		   }
	       query.append(") ");
	       return getJpaTemplate().find(query.toString()); 
    	} 
        return new ArrayList<Conference>();
    }      

    protected List<Long> loadPaginatedConferenceIdentitiesFromStartPositionId (Conference conference, PaginationCriteria paginationCriteria) {
       boolean isWhereSet = false;
       StringBuffer query = new StringBuffer();
       query.append ("select conference.id ");
       query.append (getConferenceSearchEqualQuery (conference));
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
    
    protected void convertTransientReferenceToNull (Conference conference) {
	   conference.setAddressId ((Address)null);
    }
}
