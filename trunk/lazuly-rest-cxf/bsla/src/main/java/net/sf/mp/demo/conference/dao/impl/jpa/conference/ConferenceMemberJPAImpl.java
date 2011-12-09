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
import net.sf.mp.demo.conference.dao.face.conference.ConferenceMemberDao;
import net.sf.mp.demo.conference.domain.conference.ConferenceMember;
import net.sf.mp.demo.conference.domain.conference.Conference;
import net.sf.mp.demo.conference.domain.conference.Address;

/**
 *
 * <p>Title: ConferenceMemberJPAImpl</p>
 *
 * <p>Description: Interface of a Data access object dealing with ConferenceMemberJPAImpl
 * persistence. It offers a set of methods which allow for saving,
 * deleting and searching ConferenceMemberJPAImpl objects</p>
 *
 */

public class ConferenceMemberJPAImpl extends JpaDaoSupport implements ConferenceMemberDao {

	public ConferenceMemberJPAImpl () {}

    /**
     * Inserts a ConferenceMember entity 
     * @param ConferenceMember conferenceMember
     */
    public void insertConferenceMember(ConferenceMember conferenceMember) {
       convertTransientReferenceToNull (conferenceMember);
       getJpaTemplate().persist(conferenceMember);
    }

    protected void insertConferenceMember(EntityManager emForRecursiveDao, ConferenceMember conferenceMember) {
       emForRecursiveDao.persist(conferenceMember);
    } 
    /**
     * Inserts a list of ConferenceMember entity 
     * @param List<ConferenceMember> conferenceMembers
     */
    public void insertConferenceMembers(List<ConferenceMember> conferenceMembers) {
    	//TODO
    }
    /**
     * Updates a ConferenceMember entity 
     * @param ConferenceMember conferenceMember
     */
    public ConferenceMember updateConferenceMember(ConferenceMember conferenceMember) {
       convertTransientReferenceToNull (conferenceMember);
       return getJpaTemplate().merge(conferenceMember);
    }

	/**
     * Updates a ConferenceMember entity with only the attributes set into ConferenceMember.
	 * The primary keys are to be set for this method to operate.
	 * This is a performance friendly feature, which remove the udibiquous full load and full update when an
	 * update is issued
     * Remark: The primary keys cannot be update by this methods, nor are the attributes that must be set to null.
     * @param ConferenceMember conferenceMember
    */ 
    @Transactional
    public int updateNotNullOnlyConferenceMember(ConferenceMember conferenceMember) {
        Query jpaQuery = getEntityManager().createQuery(getUpdateNotNullOnlyConferenceMemberQueryChunk(conferenceMember));
        if (conferenceMember.getId() != null) {
           jpaQuery.setParameter ("id", conferenceMember.getId());
        }   
        if (conferenceMember.getConferenceId() != null) {
           jpaQuery.setParameter ("conferenceId", conferenceMember.getConferenceId());
        }   
        if (conferenceMember.getFirstName() != null) {
           jpaQuery.setParameter ("firstName", conferenceMember.getFirstName());
        }   
        if (conferenceMember.getLastName() != null) {
           jpaQuery.setParameter ("lastName", conferenceMember.getLastName());
        }   
        if (conferenceMember.getEmail() != null) {
           jpaQuery.setParameter ("email", conferenceMember.getEmail());
        }   
        if (conferenceMember.getAddressId() != null) {
           jpaQuery.setParameter ("addressId", conferenceMember.getAddressId());
        }   
        if (conferenceMember.getStatus() != null) {
           jpaQuery.setParameter ("status", conferenceMember.getStatus());
        }   
		return jpaQuery.executeUpdate();
    }

    protected String getUpdateNotNullOnlyConferenceMemberQueryChunkPrototype (ConferenceMember conferenceMember) {
        boolean isWhereSet = false;
        StringBuffer query = new StringBuffer();
        query.append (" update ConferenceMember conferenceMember ");
        if (conferenceMember.getConferenceId() != null) {
           query.append (getQueryCommaSet (isWhereSet));
           isWhereSet = true; 
           query.append(" conferenceMember.conferenceId = :conferenceId");
        }
        if (conferenceMember.getFirstName() != null) {
           query.append (getQueryCommaSet (isWhereSet));
           isWhereSet = true; 
           query.append(" conferenceMember.firstName = :firstName");
        }
        if (conferenceMember.getLastName() != null) {
           query.append (getQueryCommaSet (isWhereSet));
           isWhereSet = true; 
           query.append(" conferenceMember.lastName = :lastName");
        }
        if (conferenceMember.getEmail() != null) {
           query.append (getQueryCommaSet (isWhereSet));
           isWhereSet = true; 
           query.append(" conferenceMember.email = :email");
        }
        if (conferenceMember.getAddressId() != null) {
           query.append (getQueryCommaSet (isWhereSet));
           isWhereSet = true; 
           query.append(" conferenceMember.addressId = :addressId");
        }
        if (conferenceMember.getStatus() != null) {
           query.append (getQueryCommaSet (isWhereSet));
           isWhereSet = true; 
           query.append(" conferenceMember.status = :status");
        }
        return query.toString();
    }
    
    protected String getUpdateNotNullOnlyConferenceMemberQueryChunk (ConferenceMember conferenceMember) {
        boolean isWhereSet = false;
        StringBuffer query = new StringBuffer(getUpdateNotNullOnlyConferenceMemberQueryChunkPrototype(conferenceMember));
        if (conferenceMember.getId() != null) {
           query.append (getQueryWHERE_AND (isWhereSet));
           isWhereSet = true;
			     query.append(" conferenceMember.id = :id");
        }
        return query.toString();
    }
    
                
	protected ConferenceMember assignBlankToNull (ConferenceMember conferenceMember) {
	    if (conferenceMember==null)
			return null;
        if (conferenceMember.getFirstName()!=null && conferenceMember.getFirstName().equals(""))
           conferenceMember.setFirstName((String)null);
        if (conferenceMember.getLastName()!=null && conferenceMember.getLastName().equals(""))
           conferenceMember.setLastName((String)null);
        if (conferenceMember.getEmail()!=null && conferenceMember.getEmail().equals(""))
           conferenceMember.setEmail((String)null);
        if (conferenceMember.getStatus()!=null && conferenceMember.getStatus().equals(""))
           conferenceMember.setStatus((String)null);
		return conferenceMember;
	}
	
	protected boolean isAllNull (ConferenceMember conferenceMember) {
	    if (conferenceMember==null)
			return true;
        if (conferenceMember.getId()!=null) 
            return false;
        if (conferenceMember.getConferenceId()!=null) 
            return false;
        if (conferenceMember.getFirstName()!=null) 
            return false;
        if (conferenceMember.getLastName()!=null) 
            return false;
        if (conferenceMember.getEmail()!=null) 
            return false;
        if (conferenceMember.getAddressId()!=null) 
            return false;
        if (conferenceMember.getStatus()!=null) 
            return false;
		return true;
	}
		
	@Transactional
    public int updateNotNullOnlyPrototypeConferenceMember(ConferenceMember conferenceMember, ConferenceMember prototypeCriteria) {
        boolean isWhereSet = false;
        StringBuffer query = new StringBuffer();
        query.append (" update ConferenceMember conferenceMember ");
        if (conferenceMember.getId() != null) {
           query.append (getQueryCommaSet (isWhereSet));
           isWhereSet = true; 
           query.append(" conferenceMember.id = "+ conferenceMember.getId() + " ");
        }
        if (conferenceMember.getConferenceId() != null) {
           query.append (getQueryCommaSet (isWhereSet));
           isWhereSet = true; 
           query.append(" conferenceMember.conferenceId = "+ conferenceMember.getConferenceId() + " ");
        }
        if (conferenceMember.getFirstName() != null) {
           query.append (getQueryCommaSet (isWhereSet));
           isWhereSet = true; 
           query.append(" conferenceMember.firstName = '"+ conferenceMember.getFirstName()+"' ");
        }
        if (conferenceMember.getLastName() != null) {
           query.append (getQueryCommaSet (isWhereSet));
           isWhereSet = true; 
           query.append(" conferenceMember.lastName = '"+ conferenceMember.getLastName()+"' ");
        }
        if (conferenceMember.getEmail() != null) {
           query.append (getQueryCommaSet (isWhereSet));
           isWhereSet = true; 
           query.append(" conferenceMember.email = '"+ conferenceMember.getEmail()+"' ");
        }
        if (conferenceMember.getAddressId() != null) {
           query.append (getQueryCommaSet (isWhereSet));
           isWhereSet = true; 
           query.append(" conferenceMember.addressId = "+ conferenceMember.getAddressId() + " ");
        }
        if (conferenceMember.getStatus() != null) {
           query.append (getQueryCommaSet (isWhereSet));
           isWhereSet = true; 
           query.append(" conferenceMember.status = '"+ conferenceMember.getStatus()+"' ");
        }
		isWhereSet = false; 
        if (prototypeCriteria.getId() != null) {
           query.append (getQueryWHERE_AND (isWhereSet));
           isWhereSet = true;
           query.append(" conferenceMember.id = "+ prototypeCriteria.getId() + " ");
        }
        if (prototypeCriteria.getConferenceId() != null) {
           query.append (getQueryWHERE_AND (isWhereSet));
           isWhereSet = true;
           query.append(" conferenceMember.conferenceId = "+ prototypeCriteria.getConferenceId() + " ");
        }
        if (prototypeCriteria.getFirstName() != null) {
           query.append (getQueryWHERE_AND (isWhereSet));
           isWhereSet = true;
           query.append(" conferenceMember.firstName = '"+ prototypeCriteria.getFirstName()+"' ");
        }
        if (prototypeCriteria.getLastName() != null) {
           query.append (getQueryWHERE_AND (isWhereSet));
           isWhereSet = true;
           query.append(" conferenceMember.lastName = '"+ prototypeCriteria.getLastName()+"' ");
        }
        if (prototypeCriteria.getEmail() != null) {
           query.append (getQueryWHERE_AND (isWhereSet));
           isWhereSet = true;
           query.append(" conferenceMember.email = '"+ prototypeCriteria.getEmail()+"' ");
        }
        if (prototypeCriteria.getAddressId() != null) {
           query.append (getQueryWHERE_AND (isWhereSet));
           isWhereSet = true;
           query.append(" conferenceMember.addressId = "+ prototypeCriteria.getAddressId() + " ");
        }
        if (prototypeCriteria.getStatus() != null) {
           query.append (getQueryWHERE_AND (isWhereSet));
           isWhereSet = true;
           query.append(" conferenceMember.status = '"+ prototypeCriteria.getStatus()+"' ");
        }
        Query jpaQuery = getEntityManager().createQuery(query.toString());
		return jpaQuery.executeUpdate();
    }
     
     /**
     * Saves a ConferenceMember entity 
     * @param ConferenceMember conferenceMember
     */
    public void saveConferenceMember(ConferenceMember conferenceMember) {
       //getJpaTemplate().persist(conferenceMember);
       convertTransientReferenceToNull (conferenceMember);
       if (getJpaTemplate().contains(conferenceMember)) {
          getJpaTemplate().merge(conferenceMember);
       } else {
          getJpaTemplate().persist(conferenceMember);
       }
       getJpaTemplate().flush(); 
    }
       
    /**
     * Deletes a ConferenceMember entity 
     * @param ConferenceMember conferenceMember
     */
    public void deleteConferenceMember(ConferenceMember conferenceMember) {
      getJpaTemplate().remove(conferenceMember);
    }
    
    /**
     * Loads the ConferenceMember entity which is related to an instance of
     * ConferenceMember
     * @param Long id
     * @return ConferenceMember The ConferenceMember entity
     
    public ConferenceMember loadConferenceMember(Long id) {
    	return (ConferenceMember)getJpaTemplate().get(ConferenceMember.class, id);
    }
*/
  
    /**
     * Loads the ConferenceMember entity which is related to an instance of
     * ConferenceMember
     * @param java.lang.Long Id
     * @return ConferenceMember The ConferenceMember entity
     */
    public ConferenceMember loadConferenceMember(java.lang.Long id) {
    	return (ConferenceMember)getJpaTemplate().find(ConferenceMember.class, id);
    }
    
    /**
     * Loads a list of ConferenceMember entity 
     * @param List<java.lang.Long> ids
     * @return List<ConferenceMember> The ConferenceMember entity
     */
    public List<ConferenceMember> loadConferenceMemberListByConferenceMember (List<ConferenceMember> conferenceMembers) {
       return null;
    }
    
    /**
     * Loads a list of ConferenceMember entity 
     * @param List<java.lang.Long> ids
     * @return List<ConferenceMember> The ConferenceMember entity
     */
    public List<ConferenceMember> loadConferenceMemberListById(List<java.lang.Long> ids){
       return null;
    }
    
    /**
     * Loads the ConferenceMember entity which is related to an instance of
     * ConferenceMember and its dependent one to many objects
     * @param Long id
     * @return ConferenceMember The ConferenceMember entity
     */
    public ConferenceMember loadFullFirstLevelConferenceMember(java.lang.Long id) {
        List list = getJpaTemplate().find(
                     "SELECT conferenceMember FROM ConferenceMember conferenceMember "
                     + " LEFT JOIN conferenceMember.conferenceFeedbackConferenceMemberIds "   
                     + " LEFT JOIN conferenceMember.evaluationConferenceMemberIds "   
                     + " LEFT JOIN conferenceMember.memberRoleConferenceMemberIds "   
                     + " WHERE conferenceMember.id = "+id
               );
         if (list!=null && !list.isEmpty())
            return (ConferenceMember)list.get(0);
         return null;
    	//return null;//(ConferenceMember) getJpaTemplate().queryForObject("loadFullFirstLevelConferenceMember", id);
    }

    /**
     * Loads the ConferenceMember entity which is related to an instance of
     * ConferenceMember
     * @param ConferenceMember conferenceMember
     * @return ConferenceMember The ConferenceMember entity
     */
    public ConferenceMember loadFullFirstLevelConferenceMember(ConferenceMember conferenceMember) {
        boolean isWhereSet = false;
        StringBuffer query = new StringBuffer();
        query.append ("SELECT conferenceMember FROM ConferenceMember conferenceMember ");
        if (conferenceMember.getId() != null) {
           query.append (getQueryWHERE_AND (isWhereSet));
           isWhereSet = true;
           query.append(" conferenceMember.id = "+ conferenceMember.getId() + " ");
         }
	        	List list = getJpaTemplate().find(query.toString());
        if (list!=null && !list.isEmpty())
           return (ConferenceMember)list.get(0);    
        return null;
    }  
     
    /**
     * Loads the ConferenceMember entity which is related to an instance of
     * ConferenceMember and its dependent objects one to many
     * @param Long id
     * @return ConferenceMember The ConferenceMember entity
     */
    public ConferenceMember loadFullConferenceMember(Long id) {
    	return null;//(ConferenceMember)getJpaTemplate().queryForObject("loadFullConferenceMember", id);
    }

    /**
     * Searches a list of ConferenceMember entity 
     * @param ConferenceMember conferenceMember
     * @return List
     */
    public List<ConferenceMember> searchPrototypeConferenceMember(ConferenceMember conferenceMember) {      
       return searchPrototype (conferenceMember, null);            
    }  
    
    protected List<ConferenceMember> searchPrototype (ConferenceMember conferenceMember, Integer maxResults) { 
       Query hquery = getEntityManager().createQuery(getConferenceMemberSearchEqualQuery (conferenceMember));
       if (maxResults!=null)
          hquery.setMaxResults(maxResults);
       return hquery.getResultList();         
    }        
    
    public List<ConferenceMember> searchPrototypeConferenceMember (List<ConferenceMember> conferenceMembers) {
       return searchPrototype (conferenceMembers, null);  
    }

    protected List<ConferenceMember> searchPrototype (List<ConferenceMember> conferenceMembers, Integer maxResults) {    
       //TODO convert setMaxResults in JPA if (maxResults!=null)
       //   getJpaTemplate().setMaxResults(maxResults);
       return getJpaTemplate().find(getConferenceMemberSearchEqualQuery (null, conferenceMembers));            
    }    

    public List<ConferenceMember> searchDistinctPrototypeConferenceMember (ConferenceMember conferenceMemberMask, List<ConferenceMember> conferenceMembers) {
        return getJpaTemplate().find(getConferenceMemberSearchEqualQuery (conferenceMemberMask, conferenceMembers));    
    }
         
	/**
     * Searches a list of ConferenceMember entity 
     * @param ConferenceMember conferenceMemberPositive
     * @param ConferenceMember conferenceMemberNegative
     * @return List
     */
    public List<ConferenceMember> searchPrototypeConferenceMember(ConferenceMember conferenceMemberPositive, ConferenceMember conferenceMemberNegative) {
	    return getJpaTemplate().find(getConferenceMemberSearchEqualQuery (conferenceMemberPositive, conferenceMemberNegative));  
    }

    /**
    * return a jql query search on a ConferenceMember prototype
    */
    protected String getConferenceMemberSearchEqualQuery (ConferenceMember conferenceMember) {
        StringBuffer query = new StringBuffer();
        StringBuffer queryWhere = new StringBuffer();
        query.append ("SELECT conferenceMember FROM ConferenceMember conferenceMember ");
        queryWhere.append (getConferenceMemberSearchEqualWhereQueryChunk(conferenceMember, false));   
	    return getHQuery(query.toString(), queryWhere.toString());
    }


    /**
    * return a jql search for a list of ConferenceMember prototype
    */
    protected String getConferenceMemberSearchEqualQuery (ConferenceMember conferenceMemberMask, List<ConferenceMember> conferenceMembers) {
        boolean isOrSet = false;
        StringBuffer query = new StringBuffer();
        if (conferenceMemberMask !=null)
           query.append (getConferenceMemberMaskWhat (conferenceMemberMask));
        query.append (" FROM ConferenceMember conferenceMember ");
        StringBuffer queryWhere = new StringBuffer();
        for (ConferenceMember conferenceMember : conferenceMembers) {
           if (!isAllNull(conferenceMember)) {        
	           queryWhere.append (getQueryOR (isOrSet));
	           isOrSet = true;        
	           queryWhere.append (" ("+getConferenceMemberSearchEqualWhereQueryChunk(conferenceMember, false)+") ");
           }
        }
	    return getHQuery(query.toString(), queryWhere.toString());
    }	
    
    protected String getConferenceMemberMaskWhat (ConferenceMember conferenceMemberMask) {
        boolean isCommaSet = false;
        StringBuffer query = new StringBuffer("SELECT DISTINCT ");
        if (conferenceMemberMask.getId() != null) {
           query.append (getQueryComma (isCommaSet));
           isCommaSet = true;
           query.append(" id ");
        }
        if (conferenceMemberMask.getConferenceId() != null) {
           query.append (getQueryComma (isCommaSet));
           isCommaSet = true;
           query.append(" conferenceId ");
        }
        if (conferenceMemberMask.getFirstName() != null) {
           query.append (getQueryComma (isCommaSet));
           isCommaSet = true;
           query.append(" firstName ");
        }
        if (conferenceMemberMask.getLastName() != null) {
           query.append (getQueryComma (isCommaSet));
           isCommaSet = true;
           query.append(" lastName ");
        }
        if (conferenceMemberMask.getEmail() != null) {
           query.append (getQueryComma (isCommaSet));
           isCommaSet = true;
           query.append(" email ");
        }
        if (conferenceMemberMask.getAddressId() != null) {
           query.append (getQueryComma (isCommaSet));
           isCommaSet = true;
           query.append(" addressId ");
        }
        if (conferenceMemberMask.getStatus() != null) {
           query.append (getQueryComma (isCommaSet));
           isCommaSet = true;
           query.append(" status ");
        }
        if (!isCommaSet)
           return "";
	    return query.toString();        
    }
    
    protected String getConferenceMemberSearchEqualWhereQueryChunk (ConferenceMember conferenceMember, boolean isAndSet) {
        StringBuffer query = new StringBuffer();
        if (conferenceMember.getId() != null) {
           query.append (getQueryAND (isAndSet));
           isAndSet = true;
           query.append(" conferenceMember.id = "+ conferenceMember.getId() + " ");
        }
        if (conferenceMember.getConferenceId() != null) {
           query.append (getQueryAND (isAndSet));
           isAndSet = true;
           query.append(" conferenceMember.conferenceId = "+ conferenceMember.getConferenceId() + " ");
        }
        if (conferenceMember.getFirstName() != null) {
           query.append (getQueryAND (isAndSet));
           isAndSet = true;
           query.append(" conferenceMember.firstName = '"+ conferenceMember.getFirstName()+"' ");
        }
        if (conferenceMember.getLastName() != null) {
           query.append (getQueryAND (isAndSet));
           isAndSet = true;
           query.append(" conferenceMember.lastName = '"+ conferenceMember.getLastName()+"' ");
        }
        if (conferenceMember.getEmail() != null) {
           query.append (getQueryAND (isAndSet));
           isAndSet = true;
           query.append(" conferenceMember.email = '"+ conferenceMember.getEmail()+"' ");
        }
        if (conferenceMember.getAddressId() != null) {
           query.append (getQueryAND (isAndSet));
           isAndSet = true;
           query.append(" conferenceMember.addressId = "+ conferenceMember.getAddressId() + " ");
        }
        if (conferenceMember.getStatus() != null) {
           query.append (getQueryAND (isAndSet));
           isAndSet = true;
           query.append(" conferenceMember.status = '"+ conferenceMember.getStatus()+"' ");
        }
	    return query.toString();
    }   
     
    /**
    * return a jql search on a ConferenceMember prototype with positive and negative beans
    */
    protected String getConferenceMemberSearchEqualQuery (ConferenceMember conferenceMemberPositive, ConferenceMember conferenceMemberNegative) {
        boolean isWhereSet = false;
        StringBuffer query = new StringBuffer();
        query.append (" SELECT conferenceMember FROM ConferenceMember conferenceMember ");
        if (conferenceMemberPositive!=null && conferenceMemberPositive.getId() != null) {
           query.append (getQueryWHERE_AND (isWhereSet));
           isWhereSet = true;
           query.append(" conferenceMember.id = "+ conferenceMemberPositive.getId() + " ");
        } else if (conferenceMemberNegative.getId() != null) {
           query.append (getQueryWHERE_AND (isWhereSet));
           isWhereSet = true;   
           query.append(" conferenceMember.id is null ");
        }
        if (conferenceMemberPositive!=null && conferenceMemberPositive.getConferenceId() != null) {
           query.append (getQueryWHERE_AND (isWhereSet));
           isWhereSet = true;
           query.append(" conferenceMember.conferenceId = "+ conferenceMemberPositive.getConferenceId() + " ");
        } else if (conferenceMemberNegative.getConferenceId() != null) {
           query.append (getQueryWHERE_AND (isWhereSet));
           isWhereSet = true;   
           query.append(" conferenceMember.conferenceId is null ");
        }
        if (conferenceMemberPositive!=null && conferenceMemberPositive.getFirstName() != null) {
           query.append (getQueryWHERE_AND (isWhereSet));
           isWhereSet = true;
           query.append(" conferenceMember.firstName = '"+ conferenceMemberPositive.getFirstName()+"' ");
        } else if (conferenceMemberNegative.getFirstName() != null) {
           query.append (getQueryWHERE_AND (isWhereSet));
           isWhereSet = true;   
           query.append(" conferenceMember.firstName is null ");
        }
        if (conferenceMemberPositive!=null && conferenceMemberPositive.getLastName() != null) {
           query.append (getQueryWHERE_AND (isWhereSet));
           isWhereSet = true;
           query.append(" conferenceMember.lastName = '"+ conferenceMemberPositive.getLastName()+"' ");
        } else if (conferenceMemberNegative.getLastName() != null) {
           query.append (getQueryWHERE_AND (isWhereSet));
           isWhereSet = true;   
           query.append(" conferenceMember.lastName is null ");
        }
        if (conferenceMemberPositive!=null && conferenceMemberPositive.getEmail() != null) {
           query.append (getQueryWHERE_AND (isWhereSet));
           isWhereSet = true;
           query.append(" conferenceMember.email = '"+ conferenceMemberPositive.getEmail()+"' ");
        } else if (conferenceMemberNegative.getEmail() != null) {
           query.append (getQueryWHERE_AND (isWhereSet));
           isWhereSet = true;   
           query.append(" conferenceMember.email is null ");
        }
        if (conferenceMemberPositive!=null && conferenceMemberPositive.getAddressId() != null) {
           query.append (getQueryWHERE_AND (isWhereSet));
           isWhereSet = true;
           query.append(" conferenceMember.addressId = "+ conferenceMemberPositive.getAddressId() + " ");
        } else if (conferenceMemberNegative.getAddressId() != null) {
           query.append (getQueryWHERE_AND (isWhereSet));
           isWhereSet = true;   
           query.append(" conferenceMember.addressId is null ");
        }
        if (conferenceMemberPositive!=null && conferenceMemberPositive.getStatus() != null) {
           query.append (getQueryWHERE_AND (isWhereSet));
           isWhereSet = true;
           query.append(" conferenceMember.status = '"+ conferenceMemberPositive.getStatus()+"' ");
        } else if (conferenceMemberNegative.getStatus() != null) {
           query.append (getQueryWHERE_AND (isWhereSet));
           isWhereSet = true;   
           query.append(" conferenceMember.status is null ");
        }
	    return query.toString();
    }
    
    /**
     * Load a paginated list of ConferenceMember entity dependent of pagination criteria
     * @param PaginationCriteria paginationCriteria
     * @return List
     */
    public List <ConferenceMember> loadPaginatedConferenceMember (ConferenceMember conferenceMember, PaginationCriteria paginationCriteria) {
	    List<Long> page = loadPaginatedConferenceMemberIdentitiesFromStartPositionId(conferenceMember, paginationCriteria);
    	int start = paginationCriteria.getNumberOfRowsReturned()*(paginationCriteria.getPageNumber());
    	int max = page.size();
    	if (start<max) {
    	   List<Long> returnPage = page.subList(start, max);	
           StringBuffer query = new StringBuffer();
           query.append (" SELECT conferenceMember FROM ConferenceMember conferenceMember ");      
	       query.append(" where conferenceMember.id in (");
	       for (Iterator iter = returnPage.iterator(); iter.hasNext();) {
			  Long id = (Long) iter.next();
			  query.append(id.toString());
		      if (iter.hasNext())
			     query.append(",");
		   }
	       query.append(") ");
	       return getJpaTemplate().find(query.toString()); 
    	} 
        return new ArrayList<ConferenceMember>();
    }      

    protected List<Long> loadPaginatedConferenceMemberIdentitiesFromStartPositionId (ConferenceMember conferenceMember, PaginationCriteria paginationCriteria) {
       boolean isWhereSet = false;
       StringBuffer query = new StringBuffer();
       query.append ("select conferenceMember.id ");
       query.append (getConferenceMemberSearchEqualQuery (conferenceMember));
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
    
    protected void convertTransientReferenceToNull (ConferenceMember conferenceMember) {
	   conferenceMember.setConferenceId ((Conference)null);
	   conferenceMember.setAddressId ((Address)null);
    }
}
