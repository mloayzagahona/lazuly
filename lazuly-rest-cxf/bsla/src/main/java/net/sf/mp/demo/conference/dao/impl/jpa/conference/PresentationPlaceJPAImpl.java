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
import net.sf.mp.demo.conference.dao.face.conference.PresentationPlaceDao;
import net.sf.mp.demo.conference.domain.conference.PresentationPlace;

/**
 *
 * <p>Title: PresentationPlaceJPAImpl</p>
 *
 * <p>Description: Interface of a Data access object dealing with PresentationPlaceJPAImpl
 * persistence. It offers a set of methods which allow for saving,
 * deleting and searching PresentationPlaceJPAImpl objects</p>
 *
 */

public class PresentationPlaceJPAImpl extends JpaDaoSupport implements PresentationPlaceDao {

	public PresentationPlaceJPAImpl () {}

    /**
     * Inserts a PresentationPlace entity 
     * @param PresentationPlace presentationPlace
     */
    public void insertPresentationPlace(PresentationPlace presentationPlace) {
       convertTransientReferenceToNull (presentationPlace);
       getJpaTemplate().persist(presentationPlace);
    }

    protected void insertPresentationPlace(EntityManager emForRecursiveDao, PresentationPlace presentationPlace) {
       emForRecursiveDao.persist(presentationPlace);
    } 
    /**
     * Inserts a list of PresentationPlace entity 
     * @param List<PresentationPlace> presentationPlaces
     */
    public void insertPresentationPlaces(List<PresentationPlace> presentationPlaces) {
    	//TODO
    }
    /**
     * Updates a PresentationPlace entity 
     * @param PresentationPlace presentationPlace
     */
    public PresentationPlace updatePresentationPlace(PresentationPlace presentationPlace) {
       convertTransientReferenceToNull (presentationPlace);
       return getJpaTemplate().merge(presentationPlace);
    }

	/**
     * Updates a PresentationPlace entity with only the attributes set into PresentationPlace.
	 * The primary keys are to be set for this method to operate.
	 * This is a performance friendly feature, which remove the udibiquous full load and full update when an
	 * update is issued
     * Remark: The primary keys cannot be update by this methods, nor are the attributes that must be set to null.
     * @param PresentationPlace presentationPlace
    */ 
    @Transactional
    public int updateNotNullOnlyPresentationPlace(PresentationPlace presentationPlace) {
        Query jpaQuery = getEntityManager().createQuery(getUpdateNotNullOnlyPresentationPlaceQueryChunk(presentationPlace));
        if (presentationPlace.getId() != null) {
           jpaQuery.setParameter ("id", presentationPlace.getId());
        }   
        if (presentationPlace.getLocation() != null) {
           jpaQuery.setParameter ("location", presentationPlace.getLocation());
        }   
        if (presentationPlace.getNumberOfSeats() != null) {
           jpaQuery.setParameter ("numberOfSeats", presentationPlace.getNumberOfSeats());
        }   
		return jpaQuery.executeUpdate();
    }

    protected String getUpdateNotNullOnlyPresentationPlaceQueryChunkPrototype (PresentationPlace presentationPlace) {
        boolean isWhereSet = false;
        StringBuffer query = new StringBuffer();
        query.append (" update PresentationPlace presentationPlace ");
        if (presentationPlace.getLocation() != null) {
           query.append (getQueryCommaSet (isWhereSet));
           isWhereSet = true; 
           query.append(" presentationPlace.location = :location");
        }
        if (presentationPlace.getNumberOfSeats() != null) {
           query.append (getQueryCommaSet (isWhereSet));
           isWhereSet = true; 
           query.append(" presentationPlace.numberOfSeats = :numberOfSeats");
        }
        return query.toString();
    }
    
    protected String getUpdateNotNullOnlyPresentationPlaceQueryChunk (PresentationPlace presentationPlace) {
        boolean isWhereSet = false;
        StringBuffer query = new StringBuffer(getUpdateNotNullOnlyPresentationPlaceQueryChunkPrototype(presentationPlace));
        if (presentationPlace.getId() != null) {
           query.append (getQueryWHERE_AND (isWhereSet));
           isWhereSet = true;
			     query.append(" presentationPlace.id = :id");
        }
        return query.toString();
    }
    
                
	protected PresentationPlace assignBlankToNull (PresentationPlace presentationPlace) {
	    if (presentationPlace==null)
			return null;
        if (presentationPlace.getLocation()!=null && presentationPlace.getLocation().equals(""))
           presentationPlace.setLocation((String)null);
		return presentationPlace;
	}
	
	protected boolean isAllNull (PresentationPlace presentationPlace) {
	    if (presentationPlace==null)
			return true;
        if (presentationPlace.getId()!=null) 
            return false;
        if (presentationPlace.getLocation()!=null) 
            return false;
        if (presentationPlace.getNumberOfSeats()!=null) 
            return false;
		return true;
	}
		
	@Transactional
    public int updateNotNullOnlyPrototypePresentationPlace(PresentationPlace presentationPlace, PresentationPlace prototypeCriteria) {
        boolean isWhereSet = false;
        StringBuffer query = new StringBuffer();
        query.append (" update PresentationPlace presentationPlace ");
        if (presentationPlace.getId() != null) {
           query.append (getQueryCommaSet (isWhereSet));
           isWhereSet = true; 
           query.append(" presentationPlace.id = "+ presentationPlace.getId() + " ");
        }
        if (presentationPlace.getLocation() != null) {
           query.append (getQueryCommaSet (isWhereSet));
           isWhereSet = true; 
           query.append(" presentationPlace.location = '"+ presentationPlace.getLocation()+"' ");
        }
        if (presentationPlace.getNumberOfSeats() != null) {
           query.append (getQueryCommaSet (isWhereSet));
           isWhereSet = true; 
           query.append(" presentationPlace.numberOfSeats = "+ presentationPlace.getNumberOfSeats() + " ");
        }
		isWhereSet = false; 
        if (prototypeCriteria.getId() != null) {
           query.append (getQueryWHERE_AND (isWhereSet));
           isWhereSet = true;
           query.append(" presentationPlace.id = "+ prototypeCriteria.getId() + " ");
        }
        if (prototypeCriteria.getLocation() != null) {
           query.append (getQueryWHERE_AND (isWhereSet));
           isWhereSet = true;
           query.append(" presentationPlace.location = '"+ prototypeCriteria.getLocation()+"' ");
        }
        if (prototypeCriteria.getNumberOfSeats() != null) {
           query.append (getQueryWHERE_AND (isWhereSet));
           isWhereSet = true;
           query.append(" presentationPlace.numberOfSeats = "+ prototypeCriteria.getNumberOfSeats() + " ");
        }
        Query jpaQuery = getEntityManager().createQuery(query.toString());
		return jpaQuery.executeUpdate();
    }
     
     /**
     * Saves a PresentationPlace entity 
     * @param PresentationPlace presentationPlace
     */
    public void savePresentationPlace(PresentationPlace presentationPlace) {
       //getJpaTemplate().persist(presentationPlace);
       convertTransientReferenceToNull (presentationPlace);
       if (getJpaTemplate().contains(presentationPlace)) {
          getJpaTemplate().merge(presentationPlace);
       } else {
          getJpaTemplate().persist(presentationPlace);
       }
       getJpaTemplate().flush(); 
    }
       
    /**
     * Deletes a PresentationPlace entity 
     * @param PresentationPlace presentationPlace
     */
    public void deletePresentationPlace(PresentationPlace presentationPlace) {
      getJpaTemplate().remove(presentationPlace);
    }
    
    /**
     * Loads the PresentationPlace entity which is related to an instance of
     * PresentationPlace
     * @param Long id
     * @return PresentationPlace The PresentationPlace entity
     
    public PresentationPlace loadPresentationPlace(Long id) {
    	return (PresentationPlace)getJpaTemplate().get(PresentationPlace.class, id);
    }
*/
  
    /**
     * Loads the PresentationPlace entity which is related to an instance of
     * PresentationPlace
     * @param java.lang.Long Id
     * @return PresentationPlace The PresentationPlace entity
     */
    public PresentationPlace loadPresentationPlace(java.lang.Long id) {
    	return (PresentationPlace)getJpaTemplate().find(PresentationPlace.class, id);
    }
    
    /**
     * Loads a list of PresentationPlace entity 
     * @param List<java.lang.Long> ids
     * @return List<PresentationPlace> The PresentationPlace entity
     */
    public List<PresentationPlace> loadPresentationPlaceListByPresentationPlace (List<PresentationPlace> presentationPlaces) {
       return null;
    }
    
    /**
     * Loads a list of PresentationPlace entity 
     * @param List<java.lang.Long> ids
     * @return List<PresentationPlace> The PresentationPlace entity
     */
    public List<PresentationPlace> loadPresentationPlaceListById(List<java.lang.Long> ids){
       return null;
    }
    
    /**
     * Loads the PresentationPlace entity which is related to an instance of
     * PresentationPlace and its dependent one to many objects
     * @param Long id
     * @return PresentationPlace The PresentationPlace entity
     */
    public PresentationPlace loadFullFirstLevelPresentationPlace(java.lang.Long id) {
        List list = getJpaTemplate().find(
                     "SELECT presentationPlace FROM PresentationPlace presentationPlace "
                     + " LEFT JOIN presentationPlace.presentationPresentationPlaceIds "   
                     + " WHERE presentationPlace.id = "+id
               );
         if (list!=null && !list.isEmpty())
            return (PresentationPlace)list.get(0);
         return null;
    	//return null;//(PresentationPlace) getJpaTemplate().queryForObject("loadFullFirstLevelPresentationPlace", id);
    }

    /**
     * Loads the PresentationPlace entity which is related to an instance of
     * PresentationPlace
     * @param PresentationPlace presentationPlace
     * @return PresentationPlace The PresentationPlace entity
     */
    public PresentationPlace loadFullFirstLevelPresentationPlace(PresentationPlace presentationPlace) {
        boolean isWhereSet = false;
        StringBuffer query = new StringBuffer();
        query.append ("SELECT presentationPlace FROM PresentationPlace presentationPlace ");
        query.append (" LEFT JOIN presentationPlace.presentationPresentationPlaceIds ");
        if (presentationPlace.getId() != null) {
           query.append (getQueryWHERE_AND (isWhereSet));
           isWhereSet = true;
           query.append(" presentationPlace.id = "+ presentationPlace.getId() + " ");
         }
	        	List list = getJpaTemplate().find(query.toString());
        if (list!=null && !list.isEmpty())
           return (PresentationPlace)list.get(0);    
        return null;
    }  
     
    /**
     * Loads the PresentationPlace entity which is related to an instance of
     * PresentationPlace and its dependent objects one to many
     * @param Long id
     * @return PresentationPlace The PresentationPlace entity
     */
    public PresentationPlace loadFullPresentationPlace(Long id) {
    	return null;//(PresentationPlace)getJpaTemplate().queryForObject("loadFullPresentationPlace", id);
    }

    /**
     * Searches a list of PresentationPlace entity 
     * @param PresentationPlace presentationPlace
     * @return List
     */
    public List<PresentationPlace> searchPrototypePresentationPlace(PresentationPlace presentationPlace) {      
       return searchPrototype (presentationPlace, null);            
    }  
    
    protected List<PresentationPlace> searchPrototype (PresentationPlace presentationPlace, Integer maxResults) { 
       Query hquery = getEntityManager().createQuery(getPresentationPlaceSearchEqualQuery (presentationPlace));
       if (maxResults!=null)
          hquery.setMaxResults(maxResults);
       return hquery.getResultList();         
    }        
    
    public List<PresentationPlace> searchPrototypePresentationPlace (List<PresentationPlace> presentationPlaces) {
       return searchPrototype (presentationPlaces, null);  
    }

    protected List<PresentationPlace> searchPrototype (List<PresentationPlace> presentationPlaces, Integer maxResults) {    
       //TODO convert setMaxResults in JPA if (maxResults!=null)
       //   getJpaTemplate().setMaxResults(maxResults);
       return getJpaTemplate().find(getPresentationPlaceSearchEqualQuery (null, presentationPlaces));            
    }    

    public List<PresentationPlace> searchDistinctPrototypePresentationPlace (PresentationPlace presentationPlaceMask, List<PresentationPlace> presentationPlaces) {
        return getJpaTemplate().find(getPresentationPlaceSearchEqualQuery (presentationPlaceMask, presentationPlaces));    
    }
         
	/**
     * Searches a list of PresentationPlace entity 
     * @param PresentationPlace presentationPlacePositive
     * @param PresentationPlace presentationPlaceNegative
     * @return List
     */
    public List<PresentationPlace> searchPrototypePresentationPlace(PresentationPlace presentationPlacePositive, PresentationPlace presentationPlaceNegative) {
	    return getJpaTemplate().find(getPresentationPlaceSearchEqualQuery (presentationPlacePositive, presentationPlaceNegative));  
    }

    /**
    * return a jql query search on a PresentationPlace prototype
    */
    protected String getPresentationPlaceSearchEqualQuery (PresentationPlace presentationPlace) {
        StringBuffer query = new StringBuffer();
        StringBuffer queryWhere = new StringBuffer();
        query.append ("SELECT presentationPlace FROM PresentationPlace presentationPlace ");
        queryWhere.append (getPresentationPlaceSearchEqualWhereQueryChunk(presentationPlace, false));   
	    return getHQuery(query.toString(), queryWhere.toString());
    }


    /**
    * return a jql search for a list of PresentationPlace prototype
    */
    protected String getPresentationPlaceSearchEqualQuery (PresentationPlace presentationPlaceMask, List<PresentationPlace> presentationPlaces) {
        boolean isOrSet = false;
        StringBuffer query = new StringBuffer();
        if (presentationPlaceMask !=null)
           query.append (getPresentationPlaceMaskWhat (presentationPlaceMask));
        query.append (" FROM PresentationPlace presentationPlace ");
        StringBuffer queryWhere = new StringBuffer();
        for (PresentationPlace presentationPlace : presentationPlaces) {
           if (!isAllNull(presentationPlace)) {        
	           queryWhere.append (getQueryOR (isOrSet));
	           isOrSet = true;        
	           queryWhere.append (" ("+getPresentationPlaceSearchEqualWhereQueryChunk(presentationPlace, false)+") ");
           }
        }
	    return getHQuery(query.toString(), queryWhere.toString());
    }	
    
    protected String getPresentationPlaceMaskWhat (PresentationPlace presentationPlaceMask) {
        boolean isCommaSet = false;
        StringBuffer query = new StringBuffer("SELECT DISTINCT ");
        if (presentationPlaceMask.getId() != null) {
           query.append (getQueryComma (isCommaSet));
           isCommaSet = true;
           query.append(" id ");
        }
        if (presentationPlaceMask.getLocation() != null) {
           query.append (getQueryComma (isCommaSet));
           isCommaSet = true;
           query.append(" location ");
        }
        if (presentationPlaceMask.getNumberOfSeats() != null) {
           query.append (getQueryComma (isCommaSet));
           isCommaSet = true;
           query.append(" numberOfSeats ");
        }
        if (!isCommaSet)
           return "";
	    return query.toString();        
    }
    
    protected String getPresentationPlaceSearchEqualWhereQueryChunk (PresentationPlace presentationPlace, boolean isAndSet) {
        StringBuffer query = new StringBuffer();
        if (presentationPlace.getId() != null) {
           query.append (getQueryAND (isAndSet));
           isAndSet = true;
           query.append(" presentationPlace.id = "+ presentationPlace.getId() + " ");
        }
        if (presentationPlace.getLocation() != null) {
           query.append (getQueryAND (isAndSet));
           isAndSet = true;
           query.append(" presentationPlace.location = '"+ presentationPlace.getLocation()+"' ");
        }
        if (presentationPlace.getNumberOfSeats() != null) {
           query.append (getQueryAND (isAndSet));
           isAndSet = true;
           query.append(" presentationPlace.numberOfSeats = "+ presentationPlace.getNumberOfSeats() + " ");
        }
	    return query.toString();
    }   
     
    /**
    * return a jql search on a PresentationPlace prototype with positive and negative beans
    */
    protected String getPresentationPlaceSearchEqualQuery (PresentationPlace presentationPlacePositive, PresentationPlace presentationPlaceNegative) {
        boolean isWhereSet = false;
        StringBuffer query = new StringBuffer();
        query.append (" SELECT presentationPlace FROM PresentationPlace presentationPlace ");
        if (presentationPlacePositive!=null && presentationPlacePositive.getId() != null) {
           query.append (getQueryWHERE_AND (isWhereSet));
           isWhereSet = true;
           query.append(" presentationPlace.id = "+ presentationPlacePositive.getId() + " ");
        } else if (presentationPlaceNegative.getId() != null) {
           query.append (getQueryWHERE_AND (isWhereSet));
           isWhereSet = true;   
           query.append(" presentationPlace.id is null ");
        }
        if (presentationPlacePositive!=null && presentationPlacePositive.getLocation() != null) {
           query.append (getQueryWHERE_AND (isWhereSet));
           isWhereSet = true;
           query.append(" presentationPlace.location = '"+ presentationPlacePositive.getLocation()+"' ");
        } else if (presentationPlaceNegative.getLocation() != null) {
           query.append (getQueryWHERE_AND (isWhereSet));
           isWhereSet = true;   
           query.append(" presentationPlace.location is null ");
        }
        if (presentationPlacePositive!=null && presentationPlacePositive.getNumberOfSeats() != null) {
           query.append (getQueryWHERE_AND (isWhereSet));
           isWhereSet = true;
           query.append(" presentationPlace.numberOfSeats = "+ presentationPlacePositive.getNumberOfSeats() + " ");
        } else if (presentationPlaceNegative.getNumberOfSeats() != null) {
           query.append (getQueryWHERE_AND (isWhereSet));
           isWhereSet = true;   
           query.append(" presentationPlace.numberOfSeats is null ");
        }
	    return query.toString();
    }
    
    /**
     * Load a paginated list of PresentationPlace entity dependent of pagination criteria
     * @param PaginationCriteria paginationCriteria
     * @return List
     */
    public List <PresentationPlace> loadPaginatedPresentationPlace (PresentationPlace presentationPlace, PaginationCriteria paginationCriteria) {
	    List<Long> page = loadPaginatedPresentationPlaceIdentitiesFromStartPositionId(presentationPlace, paginationCriteria);
    	int start = paginationCriteria.getNumberOfRowsReturned()*(paginationCriteria.getPageNumber());
    	int max = page.size();
    	if (start<max) {
    	   List<Long> returnPage = page.subList(start, max);	
           StringBuffer query = new StringBuffer();
           query.append (" SELECT presentationPlace FROM PresentationPlace presentationPlace ");      
	       query.append(" where presentationPlace.id in (");
	       for (Iterator iter = returnPage.iterator(); iter.hasNext();) {
			  Long id = (Long) iter.next();
			  query.append(id.toString());
		      if (iter.hasNext())
			     query.append(",");
		   }
	       query.append(") ");
	       return getJpaTemplate().find(query.toString()); 
    	} 
        return new ArrayList<PresentationPlace>();
    }      

    protected List<Long> loadPaginatedPresentationPlaceIdentitiesFromStartPositionId (PresentationPlace presentationPlace, PaginationCriteria paginationCriteria) {
       boolean isWhereSet = false;
       StringBuffer query = new StringBuffer();
       query.append ("select presentationPlace.id ");
       query.append (getPresentationPlaceSearchEqualQuery (presentationPlace));
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
    
    protected void convertTransientReferenceToNull (PresentationPlace presentationPlace) {
    }
}
