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
import net.sf.mp.demo.conference.dao.face.conference.SponsorDao;
import net.sf.mp.demo.conference.domain.conference.Sponsor;
import net.sf.mp.demo.conference.domain.conference.Address;
import net.sf.mp.demo.conference.domain.conference.Conference;

/**
 *
 * <p>Title: SponsorJPAImpl</p>
 *
 * <p>Description: Interface of a Data access object dealing with SponsorJPAImpl
 * persistence. It offers a set of methods which allow for saving,
 * deleting and searching SponsorJPAImpl objects</p>
 *
 */

public class SponsorJPAImpl extends JpaDaoSupport implements SponsorDao {

	public SponsorJPAImpl () {}

    /**
     * Inserts a Sponsor entity 
     * @param Sponsor sponsor
     */
    public void insertSponsor(Sponsor sponsor) {
       convertTransientReferenceToNull (sponsor);
       getJpaTemplate().persist(sponsor);
    }

    protected void insertSponsor(EntityManager emForRecursiveDao, Sponsor sponsor) {
       emForRecursiveDao.persist(sponsor);
    } 
    /**
     * Inserts a list of Sponsor entity 
     * @param List<Sponsor> sponsors
     */
    public void insertSponsors(List<Sponsor> sponsors) {
    	//TODO
    }
    /**
     * Updates a Sponsor entity 
     * @param Sponsor sponsor
     */
    public Sponsor updateSponsor(Sponsor sponsor) {
       convertTransientReferenceToNull (sponsor);
       return getJpaTemplate().merge(sponsor);
    }

	/**
     * Updates a Sponsor entity with only the attributes set into Sponsor.
	 * The primary keys are to be set for this method to operate.
	 * This is a performance friendly feature, which remove the udibiquous full load and full update when an
	 * update is issued
     * Remark: The primary keys cannot be update by this methods, nor are the attributes that must be set to null.
     * @param Sponsor sponsor
    */ 
    @Transactional
    public int updateNotNullOnlySponsor(Sponsor sponsor) {
        Query jpaQuery = getEntityManager().createQuery(getUpdateNotNullOnlySponsorQueryChunk(sponsor));
        if (sponsor.getId() != null) {
           jpaQuery.setParameter ("id", sponsor.getId());
        }   
        if (sponsor.getName() != null) {
           jpaQuery.setParameter ("name", sponsor.getName());
        }   
        if (sponsor.getPrivilegeType() != null) {
           jpaQuery.setParameter ("privilegeType", sponsor.getPrivilegeType());
        }   
        if (sponsor.getStatus() != null) {
           jpaQuery.setParameter ("status", sponsor.getStatus());
        }   
        if (sponsor.getConferenceId() != null) {
           jpaQuery.setParameter ("conferenceId", sponsor.getConferenceId());
        }   
        if (sponsor.getAddressId() != null) {
           jpaQuery.setParameter ("addressId", sponsor.getAddressId());
        }   
		return jpaQuery.executeUpdate();
    }

    protected String getUpdateNotNullOnlySponsorQueryChunkPrototype (Sponsor sponsor) {
        boolean isWhereSet = false;
        StringBuffer query = new StringBuffer();
        query.append (" update Sponsor sponsor ");
        if (sponsor.getName() != null) {
           query.append (getQueryCommaSet (isWhereSet));
           isWhereSet = true; 
           query.append(" sponsor.name = :name");
        }
        if (sponsor.getPrivilegeType() != null) {
           query.append (getQueryCommaSet (isWhereSet));
           isWhereSet = true; 
           query.append(" sponsor.privilegeType = :privilegeType");
        }
        if (sponsor.getStatus() != null) {
           query.append (getQueryCommaSet (isWhereSet));
           isWhereSet = true; 
           query.append(" sponsor.status = :status");
        }
        if (sponsor.getConferenceId() != null) {
           query.append (getQueryCommaSet (isWhereSet));
           isWhereSet = true; 
           query.append(" sponsor.conferenceId = :conferenceId");
        }
        if (sponsor.getAddressId() != null) {
           query.append (getQueryCommaSet (isWhereSet));
           isWhereSet = true; 
           query.append(" sponsor.addressId = :addressId");
        }
        return query.toString();
    }
    
    protected String getUpdateNotNullOnlySponsorQueryChunk (Sponsor sponsor) {
        boolean isWhereSet = false;
        StringBuffer query = new StringBuffer(getUpdateNotNullOnlySponsorQueryChunkPrototype(sponsor));
        if (sponsor.getId() != null) {
           query.append (getQueryWHERE_AND (isWhereSet));
           isWhereSet = true;
			     query.append(" sponsor.id = :id");
        }
        return query.toString();
    }
    
                
	protected Sponsor assignBlankToNull (Sponsor sponsor) {
	    if (sponsor==null)
			return null;
        if (sponsor.getName()!=null && sponsor.getName().equals(""))
           sponsor.setName((String)null);
        if (sponsor.getPrivilegeType()!=null && sponsor.getPrivilegeType().equals(""))
           sponsor.setPrivilegeType((String)null);
        if (sponsor.getStatus()!=null && sponsor.getStatus().equals(""))
           sponsor.setStatus((String)null);
		return sponsor;
	}
	
	protected boolean isAllNull (Sponsor sponsor) {
	    if (sponsor==null)
			return true;
        if (sponsor.getId()!=null) 
            return false;
        if (sponsor.getName()!=null) 
            return false;
        if (sponsor.getPrivilegeType()!=null) 
            return false;
        if (sponsor.getStatus()!=null) 
            return false;
        if (sponsor.getConferenceId()!=null) 
            return false;
        if (sponsor.getAddressId()!=null) 
            return false;
		return true;
	}
		
	@Transactional
    public int updateNotNullOnlyPrototypeSponsor(Sponsor sponsor, Sponsor prototypeCriteria) {
        boolean isWhereSet = false;
        StringBuffer query = new StringBuffer();
        query.append (" update Sponsor sponsor ");
        if (sponsor.getId() != null) {
           query.append (getQueryCommaSet (isWhereSet));
           isWhereSet = true; 
           query.append(" sponsor.id = "+ sponsor.getId() + " ");
        }
        if (sponsor.getName() != null) {
           query.append (getQueryCommaSet (isWhereSet));
           isWhereSet = true; 
           query.append(" sponsor.name = '"+ sponsor.getName()+"' ");
        }
        if (sponsor.getPrivilegeType() != null) {
           query.append (getQueryCommaSet (isWhereSet));
           isWhereSet = true; 
           query.append(" sponsor.privilegeType = '"+ sponsor.getPrivilegeType()+"' ");
        }
        if (sponsor.getStatus() != null) {
           query.append (getQueryCommaSet (isWhereSet));
           isWhereSet = true; 
           query.append(" sponsor.status = '"+ sponsor.getStatus()+"' ");
        }
        if (sponsor.getConferenceId() != null) {
           query.append (getQueryCommaSet (isWhereSet));
           isWhereSet = true; 
           query.append(" sponsor.conferenceId = "+ sponsor.getConferenceId() + " ");
        }
        if (sponsor.getAddressId() != null) {
           query.append (getQueryCommaSet (isWhereSet));
           isWhereSet = true; 
           query.append(" sponsor.addressId = "+ sponsor.getAddressId() + " ");
        }
		isWhereSet = false; 
        if (prototypeCriteria.getId() != null) {
           query.append (getQueryWHERE_AND (isWhereSet));
           isWhereSet = true;
           query.append(" sponsor.id = "+ prototypeCriteria.getId() + " ");
        }
        if (prototypeCriteria.getName() != null) {
           query.append (getQueryWHERE_AND (isWhereSet));
           isWhereSet = true;
           query.append(" sponsor.name = '"+ prototypeCriteria.getName()+"' ");
        }
        if (prototypeCriteria.getPrivilegeType() != null) {
           query.append (getQueryWHERE_AND (isWhereSet));
           isWhereSet = true;
           query.append(" sponsor.privilegeType = '"+ prototypeCriteria.getPrivilegeType()+"' ");
        }
        if (prototypeCriteria.getStatus() != null) {
           query.append (getQueryWHERE_AND (isWhereSet));
           isWhereSet = true;
           query.append(" sponsor.status = '"+ prototypeCriteria.getStatus()+"' ");
        }
        if (prototypeCriteria.getConferenceId() != null) {
           query.append (getQueryWHERE_AND (isWhereSet));
           isWhereSet = true;
           query.append(" sponsor.conferenceId = "+ prototypeCriteria.getConferenceId() + " ");
        }
        if (prototypeCriteria.getAddressId() != null) {
           query.append (getQueryWHERE_AND (isWhereSet));
           isWhereSet = true;
           query.append(" sponsor.addressId = "+ prototypeCriteria.getAddressId() + " ");
        }
        Query jpaQuery = getEntityManager().createQuery(query.toString());
		return jpaQuery.executeUpdate();
    }
     
     /**
     * Saves a Sponsor entity 
     * @param Sponsor sponsor
     */
    public void saveSponsor(Sponsor sponsor) {
       //getJpaTemplate().persist(sponsor);
       convertTransientReferenceToNull (sponsor);
       if (getJpaTemplate().contains(sponsor)) {
          getJpaTemplate().merge(sponsor);
       } else {
          getJpaTemplate().persist(sponsor);
       }
       getJpaTemplate().flush(); 
    }
       
    /**
     * Deletes a Sponsor entity 
     * @param Sponsor sponsor
     */
    public void deleteSponsor(Sponsor sponsor) {
      getJpaTemplate().remove(sponsor);
    }
    
    /**
     * Loads the Sponsor entity which is related to an instance of
     * Sponsor
     * @param Long id
     * @return Sponsor The Sponsor entity
     
    public Sponsor loadSponsor(Long id) {
    	return (Sponsor)getJpaTemplate().get(Sponsor.class, id);
    }
*/
  
    /**
     * Loads the Sponsor entity which is related to an instance of
     * Sponsor
     * @param java.lang.Long Id
     * @return Sponsor The Sponsor entity
     */
    public Sponsor loadSponsor(java.lang.Long id) {
    	return (Sponsor)getJpaTemplate().find(Sponsor.class, id);
    }
    
    /**
     * Loads a list of Sponsor entity 
     * @param List<java.lang.Long> ids
     * @return List<Sponsor> The Sponsor entity
     */
    public List<Sponsor> loadSponsorListBySponsor (List<Sponsor> sponsors) {
       return null;
    }
    
    /**
     * Loads a list of Sponsor entity 
     * @param List<java.lang.Long> ids
     * @return List<Sponsor> The Sponsor entity
     */
    public List<Sponsor> loadSponsorListById(List<java.lang.Long> ids){
       return null;
    }
    
    /**
     * Loads the Sponsor entity which is related to an instance of
     * Sponsor and its dependent one to many objects
     * @param Long id
     * @return Sponsor The Sponsor entity
     */
    public Sponsor loadFullFirstLevelSponsor(java.lang.Long id) {
        List list = getJpaTemplate().find(
                     "SELECT sponsor FROM Sponsor sponsor "
                     + " LEFT JOIN sponsor.speakerSponsorIds "   
                     + " WHERE sponsor.id = "+id
               );
         if (list!=null && !list.isEmpty())
            return (Sponsor)list.get(0);
         return null;
    	//return null;//(Sponsor) getJpaTemplate().queryForObject("loadFullFirstLevelSponsor", id);
    }

    /**
     * Loads the Sponsor entity which is related to an instance of
     * Sponsor
     * @param Sponsor sponsor
     * @return Sponsor The Sponsor entity
     */
    public Sponsor loadFullFirstLevelSponsor(Sponsor sponsor) {
        boolean isWhereSet = false;
        StringBuffer query = new StringBuffer();
        query.append ("SELECT sponsor FROM Sponsor sponsor ");
        query.append (" LEFT JOIN sponsor.speakerSponsorIds ");
        if (sponsor.getId() != null) {
           query.append (getQueryWHERE_AND (isWhereSet));
           isWhereSet = true;
           query.append(" sponsor.id = "+ sponsor.getId() + " ");
         }
	        	List list = getJpaTemplate().find(query.toString());
        if (list!=null && !list.isEmpty())
           return (Sponsor)list.get(0);    
        return null;
    }  
     
    /**
     * Loads the Sponsor entity which is related to an instance of
     * Sponsor and its dependent objects one to many
     * @param Long id
     * @return Sponsor The Sponsor entity
     */
    public Sponsor loadFullSponsor(Long id) {
    	return null;//(Sponsor)getJpaTemplate().queryForObject("loadFullSponsor", id);
    }

    /**
     * Searches a list of Sponsor entity 
     * @param Sponsor sponsor
     * @return List
     */
    public List<Sponsor> searchPrototypeSponsor(Sponsor sponsor) {      
       return searchPrototype (sponsor, null);            
    }  
    
    protected List<Sponsor> searchPrototype (Sponsor sponsor, Integer maxResults) { 
       Query hquery = getEntityManager().createQuery(getSponsorSearchEqualQuery (sponsor));
       if (maxResults!=null)
          hquery.setMaxResults(maxResults);
       return hquery.getResultList();         
    }        
    
    public List<Sponsor> searchPrototypeSponsor (List<Sponsor> sponsors) {
       return searchPrototype (sponsors, null);  
    }

    protected List<Sponsor> searchPrototype (List<Sponsor> sponsors, Integer maxResults) {    
       //TODO convert setMaxResults in JPA if (maxResults!=null)
       //   getJpaTemplate().setMaxResults(maxResults);
       return getJpaTemplate().find(getSponsorSearchEqualQuery (null, sponsors));            
    }    

    public List<Sponsor> searchDistinctPrototypeSponsor (Sponsor sponsorMask, List<Sponsor> sponsors) {
        return getJpaTemplate().find(getSponsorSearchEqualQuery (sponsorMask, sponsors));    
    }
         
	/**
     * Searches a list of Sponsor entity 
     * @param Sponsor sponsorPositive
     * @param Sponsor sponsorNegative
     * @return List
     */
    public List<Sponsor> searchPrototypeSponsor(Sponsor sponsorPositive, Sponsor sponsorNegative) {
	    return getJpaTemplate().find(getSponsorSearchEqualQuery (sponsorPositive, sponsorNegative));  
    }

    /**
    * return a jql query search on a Sponsor prototype
    */
    protected String getSponsorSearchEqualQuery (Sponsor sponsor) {
        StringBuffer query = new StringBuffer();
        StringBuffer queryWhere = new StringBuffer();
        query.append ("SELECT sponsor FROM Sponsor sponsor ");
        queryWhere.append (getSponsorSearchEqualWhereQueryChunk(sponsor, false));   
	    return getHQuery(query.toString(), queryWhere.toString());
    }


    /**
    * return a jql search for a list of Sponsor prototype
    */
    protected String getSponsorSearchEqualQuery (Sponsor sponsorMask, List<Sponsor> sponsors) {
        boolean isOrSet = false;
        StringBuffer query = new StringBuffer();
        if (sponsorMask !=null)
           query.append (getSponsorMaskWhat (sponsorMask));
        query.append (" FROM Sponsor sponsor ");
        StringBuffer queryWhere = new StringBuffer();
        for (Sponsor sponsor : sponsors) {
           if (!isAllNull(sponsor)) {        
	           queryWhere.append (getQueryOR (isOrSet));
	           isOrSet = true;        
	           queryWhere.append (" ("+getSponsorSearchEqualWhereQueryChunk(sponsor, false)+") ");
           }
        }
	    return getHQuery(query.toString(), queryWhere.toString());
    }	
    
    protected String getSponsorMaskWhat (Sponsor sponsorMask) {
        boolean isCommaSet = false;
        StringBuffer query = new StringBuffer("SELECT DISTINCT ");
        if (sponsorMask.getId() != null) {
           query.append (getQueryComma (isCommaSet));
           isCommaSet = true;
           query.append(" id ");
        }
        if (sponsorMask.getName() != null) {
           query.append (getQueryComma (isCommaSet));
           isCommaSet = true;
           query.append(" name ");
        }
        if (sponsorMask.getPrivilegeType() != null) {
           query.append (getQueryComma (isCommaSet));
           isCommaSet = true;
           query.append(" privilegeType ");
        }
        if (sponsorMask.getStatus() != null) {
           query.append (getQueryComma (isCommaSet));
           isCommaSet = true;
           query.append(" status ");
        }
        if (sponsorMask.getConferenceId() != null) {
           query.append (getQueryComma (isCommaSet));
           isCommaSet = true;
           query.append(" conferenceId ");
        }
        if (sponsorMask.getAddressId() != null) {
           query.append (getQueryComma (isCommaSet));
           isCommaSet = true;
           query.append(" addressId ");
        }
        if (!isCommaSet)
           return "";
	    return query.toString();        
    }
    
    protected String getSponsorSearchEqualWhereQueryChunk (Sponsor sponsor, boolean isAndSet) {
        StringBuffer query = new StringBuffer();
        if (sponsor.getId() != null) {
           query.append (getQueryAND (isAndSet));
           isAndSet = true;
           query.append(" sponsor.id = "+ sponsor.getId() + " ");
        }
        if (sponsor.getName() != null) {
           query.append (getQueryAND (isAndSet));
           isAndSet = true;
           query.append(" sponsor.name = '"+ sponsor.getName()+"' ");
        }
        if (sponsor.getPrivilegeType() != null) {
           query.append (getQueryAND (isAndSet));
           isAndSet = true;
           query.append(" sponsor.privilegeType = '"+ sponsor.getPrivilegeType()+"' ");
        }
        if (sponsor.getStatus() != null) {
           query.append (getQueryAND (isAndSet));
           isAndSet = true;
           query.append(" sponsor.status = '"+ sponsor.getStatus()+"' ");
        }
        if (sponsor.getConferenceId() != null) {
           query.append (getQueryAND (isAndSet));
           isAndSet = true;
           query.append(" sponsor.conferenceId = "+ sponsor.getConferenceId() + " ");
        }
        if (sponsor.getAddressId() != null) {
           query.append (getQueryAND (isAndSet));
           isAndSet = true;
           query.append(" sponsor.addressId = "+ sponsor.getAddressId() + " ");
        }
	    return query.toString();
    }   
     
    /**
    * return a jql search on a Sponsor prototype with positive and negative beans
    */
    protected String getSponsorSearchEqualQuery (Sponsor sponsorPositive, Sponsor sponsorNegative) {
        boolean isWhereSet = false;
        StringBuffer query = new StringBuffer();
        query.append (" SELECT sponsor FROM Sponsor sponsor ");
        if (sponsorPositive!=null && sponsorPositive.getId() != null) {
           query.append (getQueryWHERE_AND (isWhereSet));
           isWhereSet = true;
           query.append(" sponsor.id = "+ sponsorPositive.getId() + " ");
        } else if (sponsorNegative.getId() != null) {
           query.append (getQueryWHERE_AND (isWhereSet));
           isWhereSet = true;   
           query.append(" sponsor.id is null ");
        }
        if (sponsorPositive!=null && sponsorPositive.getName() != null) {
           query.append (getQueryWHERE_AND (isWhereSet));
           isWhereSet = true;
           query.append(" sponsor.name = '"+ sponsorPositive.getName()+"' ");
        } else if (sponsorNegative.getName() != null) {
           query.append (getQueryWHERE_AND (isWhereSet));
           isWhereSet = true;   
           query.append(" sponsor.name is null ");
        }
        if (sponsorPositive!=null && sponsorPositive.getPrivilegeType() != null) {
           query.append (getQueryWHERE_AND (isWhereSet));
           isWhereSet = true;
           query.append(" sponsor.privilegeType = '"+ sponsorPositive.getPrivilegeType()+"' ");
        } else if (sponsorNegative.getPrivilegeType() != null) {
           query.append (getQueryWHERE_AND (isWhereSet));
           isWhereSet = true;   
           query.append(" sponsor.privilegeType is null ");
        }
        if (sponsorPositive!=null && sponsorPositive.getStatus() != null) {
           query.append (getQueryWHERE_AND (isWhereSet));
           isWhereSet = true;
           query.append(" sponsor.status = '"+ sponsorPositive.getStatus()+"' ");
        } else if (sponsorNegative.getStatus() != null) {
           query.append (getQueryWHERE_AND (isWhereSet));
           isWhereSet = true;   
           query.append(" sponsor.status is null ");
        }
        if (sponsorPositive!=null && sponsorPositive.getConferenceId() != null) {
           query.append (getQueryWHERE_AND (isWhereSet));
           isWhereSet = true;
           query.append(" sponsor.conferenceId = "+ sponsorPositive.getConferenceId() + " ");
        } else if (sponsorNegative.getConferenceId() != null) {
           query.append (getQueryWHERE_AND (isWhereSet));
           isWhereSet = true;   
           query.append(" sponsor.conferenceId is null ");
        }
        if (sponsorPositive!=null && sponsorPositive.getAddressId() != null) {
           query.append (getQueryWHERE_AND (isWhereSet));
           isWhereSet = true;
           query.append(" sponsor.addressId = "+ sponsorPositive.getAddressId() + " ");
        } else if (sponsorNegative.getAddressId() != null) {
           query.append (getQueryWHERE_AND (isWhereSet));
           isWhereSet = true;   
           query.append(" sponsor.addressId is null ");
        }
	    return query.toString();
    }
    
    /**
     * Load a paginated list of Sponsor entity dependent of pagination criteria
     * @param PaginationCriteria paginationCriteria
     * @return List
     */
    public List <Sponsor> loadPaginatedSponsor (Sponsor sponsor, PaginationCriteria paginationCriteria) {
	    List<Long> page = loadPaginatedSponsorIdentitiesFromStartPositionId(sponsor, paginationCriteria);
    	int start = paginationCriteria.getNumberOfRowsReturned()*(paginationCriteria.getPageNumber());
    	int max = page.size();
    	if (start<max) {
    	   List<Long> returnPage = page.subList(start, max);	
           StringBuffer query = new StringBuffer();
           query.append (" SELECT sponsor FROM Sponsor sponsor ");      
	       query.append(" where sponsor.id in (");
	       for (Iterator iter = returnPage.iterator(); iter.hasNext();) {
			  Long id = (Long) iter.next();
			  query.append(id.toString());
		      if (iter.hasNext())
			     query.append(",");
		   }
	       query.append(") ");
	       return getJpaTemplate().find(query.toString()); 
    	} 
        return new ArrayList<Sponsor>();
    }      

    protected List<Long> loadPaginatedSponsorIdentitiesFromStartPositionId (Sponsor sponsor, PaginationCriteria paginationCriteria) {
       boolean isWhereSet = false;
       StringBuffer query = new StringBuffer();
       query.append ("select sponsor.id ");
       query.append (getSponsorSearchEqualQuery (sponsor));
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
    
    protected void convertTransientReferenceToNull (Sponsor sponsor) {
	   sponsor.setAddressId ((Address)null);
	   sponsor.setConferenceId ((Conference)null);
    }
}
