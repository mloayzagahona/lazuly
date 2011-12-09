package net.sf.mp.demo.conference.dao.impl.jpa.statistics;

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
import net.sf.mp.demo.conference.dao.face.statistics.StatMbPerCtryConfDao;
import net.sf.mp.demo.conference.domain.statistics.StatMbPerCtryConf;

/**
 *
 * <p>Title: StatMbPerCtryConfJPAImpl</p>
 *
 * <p>Description: Interface of a Data access object dealing with StatMbPerCtryConfJPAImpl
 * persistence. It offers a set of methods which allow for saving,
 * deleting and searching StatMbPerCtryConfJPAImpl objects</p>
 *
 */

public class StatMbPerCtryConfJPAImpl extends JpaDaoSupport implements StatMbPerCtryConfDao {

	public StatMbPerCtryConfJPAImpl () {}

    /**
     * Inserts a StatMbPerCtryConf entity 
     * @param StatMbPerCtryConf statMbPerCtryConf
     */
    public void insertStatMbPerCtryConf(StatMbPerCtryConf statMbPerCtryConf) {
       convertTransientReferenceToNull (statMbPerCtryConf);
       getJpaTemplate().persist(statMbPerCtryConf);
    }

    protected void insertStatMbPerCtryConf(EntityManager emForRecursiveDao, StatMbPerCtryConf statMbPerCtryConf) {
       emForRecursiveDao.persist(statMbPerCtryConf);
    } 
    /**
     * Inserts a list of StatMbPerCtryConf entity 
     * @param List<StatMbPerCtryConf> statMbPerCtryConfs
     */
    public void insertStatMbPerCtryConfs(List<StatMbPerCtryConf> statMbPerCtryConfs) {
    	//TODO
    }
    /**
     * Updates a StatMbPerCtryConf entity 
     * @param StatMbPerCtryConf statMbPerCtryConf
     */
    public StatMbPerCtryConf updateStatMbPerCtryConf(StatMbPerCtryConf statMbPerCtryConf) {
       convertTransientReferenceToNull (statMbPerCtryConf);
       return getJpaTemplate().merge(statMbPerCtryConf);
    }

	/**
     * Updates a StatMbPerCtryConf entity with only the attributes set into StatMbPerCtryConf.
	 * The primary keys are to be set for this method to operate.
	 * This is a performance friendly feature, which remove the udibiquous full load and full update when an
	 * update is issued
     * Remark: The primary keys cannot be update by this methods, nor are the attributes that must be set to null.
     * @param StatMbPerCtryConf statMbPerCtryConf
    */ 
    @Transactional
    public int updateNotNullOnlyStatMbPerCtryConf(StatMbPerCtryConf statMbPerCtryConf) {
        Query jpaQuery = getEntityManager().createQuery(getUpdateNotNullOnlyStatMbPerCtryConfQueryChunk(statMbPerCtryConf));
        if (statMbPerCtryConf.getId() != null) {
           jpaQuery.setParameter ("id", statMbPerCtryConf.getId());
        }   
        if (statMbPerCtryConf.getCountry() != null) {
           jpaQuery.setParameter ("country", statMbPerCtryConf.getCountry());
        }   
        if (statMbPerCtryConf.getConferenceName() != null) {
           jpaQuery.setParameter ("conferenceName", statMbPerCtryConf.getConferenceName());
        }   
        if (statMbPerCtryConf.getNumber() != null) {
           jpaQuery.setParameter ("number", statMbPerCtryConf.getNumber());
        }   
		return jpaQuery.executeUpdate();
    }

    protected String getUpdateNotNullOnlyStatMbPerCtryConfQueryChunkPrototype (StatMbPerCtryConf statMbPerCtryConf) {
        boolean isWhereSet = false;
        StringBuffer query = new StringBuffer();
        query.append (" update StatMbPerCtryConf statMbPerCtryConf ");
        if (statMbPerCtryConf.getCountry() != null) {
           query.append (getQueryCommaSet (isWhereSet));
           isWhereSet = true; 
           query.append(" statMbPerCtryConf.country = :country");
        }
        if (statMbPerCtryConf.getConferenceName() != null) {
           query.append (getQueryCommaSet (isWhereSet));
           isWhereSet = true; 
           query.append(" statMbPerCtryConf.conferenceName = :conferenceName");
        }
        if (statMbPerCtryConf.getNumber() != null) {
           query.append (getQueryCommaSet (isWhereSet));
           isWhereSet = true; 
           query.append(" statMbPerCtryConf.number = :number");
        }
        return query.toString();
    }
    
    protected String getUpdateNotNullOnlyStatMbPerCtryConfQueryChunk (StatMbPerCtryConf statMbPerCtryConf) {
        boolean isWhereSet = false;
        StringBuffer query = new StringBuffer(getUpdateNotNullOnlyStatMbPerCtryConfQueryChunkPrototype(statMbPerCtryConf));
        if (statMbPerCtryConf.getId() != null) {
           query.append (getQueryWHERE_AND (isWhereSet));
           isWhereSet = true;
			     query.append(" statMbPerCtryConf.id = :id");
        }
        return query.toString();
    }
    
                
	protected StatMbPerCtryConf assignBlankToNull (StatMbPerCtryConf statMbPerCtryConf) {
	    if (statMbPerCtryConf==null)
			return null;
        if (statMbPerCtryConf.getId()!=null && statMbPerCtryConf.getId().equals(""))
           statMbPerCtryConf.setId((String)null);
        if (statMbPerCtryConf.getCountry()!=null && statMbPerCtryConf.getCountry().equals(""))
           statMbPerCtryConf.setCountry((String)null);
        if (statMbPerCtryConf.getConferenceName()!=null && statMbPerCtryConf.getConferenceName().equals(""))
           statMbPerCtryConf.setConferenceName((String)null);
		return statMbPerCtryConf;
	}
	
	protected boolean isAllNull (StatMbPerCtryConf statMbPerCtryConf) {
	    if (statMbPerCtryConf==null)
			return true;
        if (statMbPerCtryConf.getId()!=null) 
            return false;
        if (statMbPerCtryConf.getCountry()!=null) 
            return false;
        if (statMbPerCtryConf.getConferenceName()!=null) 
            return false;
        if (statMbPerCtryConf.getNumber()!=null) 
            return false;
		return true;
	}
		
	@Transactional
    public int updateNotNullOnlyPrototypeStatMbPerCtryConf(StatMbPerCtryConf statMbPerCtryConf, StatMbPerCtryConf prototypeCriteria) {
        boolean isWhereSet = false;
        StringBuffer query = new StringBuffer();
        query.append (" update StatMbPerCtryConf statMbPerCtryConf ");
        if (statMbPerCtryConf.getId() != null) {
           query.append (getQueryCommaSet (isWhereSet));
           isWhereSet = true; 
           query.append(" statMbPerCtryConf.id = '"+ statMbPerCtryConf.getId()+"' ");
        }
        if (statMbPerCtryConf.getCountry() != null) {
           query.append (getQueryCommaSet (isWhereSet));
           isWhereSet = true; 
           query.append(" statMbPerCtryConf.country = '"+ statMbPerCtryConf.getCountry()+"' ");
        }
        if (statMbPerCtryConf.getConferenceName() != null) {
           query.append (getQueryCommaSet (isWhereSet));
           isWhereSet = true; 
           query.append(" statMbPerCtryConf.conferenceName = '"+ statMbPerCtryConf.getConferenceName()+"' ");
        }
        if (statMbPerCtryConf.getNumber() != null) {
           query.append (getQueryCommaSet (isWhereSet));
           isWhereSet = true; 
           query.append(" statMbPerCtryConf.number = "+ statMbPerCtryConf.getNumber() + " ");
        }
		isWhereSet = false; 
        if (prototypeCriteria.getId() != null) {
           query.append (getQueryWHERE_AND (isWhereSet));
           isWhereSet = true;
           query.append(" statMbPerCtryConf.id = '"+ prototypeCriteria.getId()+"' ");
        }
        if (prototypeCriteria.getCountry() != null) {
           query.append (getQueryWHERE_AND (isWhereSet));
           isWhereSet = true;
           query.append(" statMbPerCtryConf.country = '"+ prototypeCriteria.getCountry()+"' ");
        }
        if (prototypeCriteria.getConferenceName() != null) {
           query.append (getQueryWHERE_AND (isWhereSet));
           isWhereSet = true;
           query.append(" statMbPerCtryConf.conferenceName = '"+ prototypeCriteria.getConferenceName()+"' ");
        }
        if (prototypeCriteria.getNumber() != null) {
           query.append (getQueryWHERE_AND (isWhereSet));
           isWhereSet = true;
           query.append(" statMbPerCtryConf.number = "+ prototypeCriteria.getNumber() + " ");
        }
        Query jpaQuery = getEntityManager().createQuery(query.toString());
		return jpaQuery.executeUpdate();
    }
     
     /**
     * Saves a StatMbPerCtryConf entity 
     * @param StatMbPerCtryConf statMbPerCtryConf
     */
    public void saveStatMbPerCtryConf(StatMbPerCtryConf statMbPerCtryConf) {
       //getJpaTemplate().persist(statMbPerCtryConf);
       convertTransientReferenceToNull (statMbPerCtryConf);
       if (getJpaTemplate().contains(statMbPerCtryConf)) {
          getJpaTemplate().merge(statMbPerCtryConf);
       } else {
          getJpaTemplate().persist(statMbPerCtryConf);
       }
       getJpaTemplate().flush(); 
    }
       
    /**
     * Deletes a StatMbPerCtryConf entity 
     * @param StatMbPerCtryConf statMbPerCtryConf
     */
    public void deleteStatMbPerCtryConf(StatMbPerCtryConf statMbPerCtryConf) {
      getJpaTemplate().remove(statMbPerCtryConf);
    }
    
    /**
     * Loads the StatMbPerCtryConf entity which is related to an instance of
     * StatMbPerCtryConf
     * @param Long id
     * @return StatMbPerCtryConf The StatMbPerCtryConf entity
     
    public StatMbPerCtryConf loadStatMbPerCtryConf(Long id) {
    	return (StatMbPerCtryConf)getJpaTemplate().get(StatMbPerCtryConf.class, id);
    }
*/
  
    /**
     * Loads the StatMbPerCtryConf entity which is related to an instance of
     * StatMbPerCtryConf
     * @param java.lang.String Id
     * @return StatMbPerCtryConf The StatMbPerCtryConf entity
     */
    public StatMbPerCtryConf loadStatMbPerCtryConf(java.lang.String id) {
    	return (StatMbPerCtryConf)getJpaTemplate().find(StatMbPerCtryConf.class, id);
    }
    
    /**
     * Loads a list of StatMbPerCtryConf entity 
     * @param List<java.lang.String> ids
     * @return List<StatMbPerCtryConf> The StatMbPerCtryConf entity
     */
    public List<StatMbPerCtryConf> loadStatMbPerCtryConfListByStatMbPerCtryConf (List<StatMbPerCtryConf> statMbPerCtryConfs) {
       return null;
    }
    
    /**
     * Loads a list of StatMbPerCtryConf entity 
     * @param List<java.lang.String> ids
     * @return List<StatMbPerCtryConf> The StatMbPerCtryConf entity
     */
    public List<StatMbPerCtryConf> loadStatMbPerCtryConfListById(List<java.lang.String> ids){
       return null;
    }
    
    /**
     * Loads the StatMbPerCtryConf entity which is related to an instance of
     * StatMbPerCtryConf and its dependent one to many objects
     * @param Long id
     * @return StatMbPerCtryConf The StatMbPerCtryConf entity
     */
    public StatMbPerCtryConf loadFullFirstLevelStatMbPerCtryConf(java.lang.String id) {
        List list = getJpaTemplate().find(
                     "SELECT statMbPerCtryConf FROM StatMbPerCtryConf statMbPerCtryConf "
                     + " WHERE statMbPerCtryConf.id = "+id
               );
         if (list!=null && !list.isEmpty())
            return (StatMbPerCtryConf)list.get(0);
         return null;
    	//return null;//(StatMbPerCtryConf) getJpaTemplate().queryForObject("loadFullFirstLevelStatMbPerCtryConf", id);
    }

    /**
     * Loads the StatMbPerCtryConf entity which is related to an instance of
     * StatMbPerCtryConf
     * @param StatMbPerCtryConf statMbPerCtryConf
     * @return StatMbPerCtryConf The StatMbPerCtryConf entity
     */
    public StatMbPerCtryConf loadFullFirstLevelStatMbPerCtryConf(StatMbPerCtryConf statMbPerCtryConf) {
        boolean isWhereSet = false;
        StringBuffer query = new StringBuffer();
        query.append ("SELECT statMbPerCtryConf FROM StatMbPerCtryConf statMbPerCtryConf ");
        if (statMbPerCtryConf.getId() != null) {
           query.append (getQueryWHERE_AND (isWhereSet));
           isWhereSet = true;
           query.append(" statMbPerCtryConf.id = '"+ statMbPerCtryConf.getId()+"' ");
         }
	        	List list = getJpaTemplate().find(query.toString());
        if (list!=null && !list.isEmpty())
           return (StatMbPerCtryConf)list.get(0);    
        return null;
    }  
     
    /**
     * Loads the StatMbPerCtryConf entity which is related to an instance of
     * StatMbPerCtryConf and its dependent objects one to many
     * @param Long id
     * @return StatMbPerCtryConf The StatMbPerCtryConf entity
     */
    public StatMbPerCtryConf loadFullStatMbPerCtryConf(Long id) {
    	return null;//(StatMbPerCtryConf)getJpaTemplate().queryForObject("loadFullStatMbPerCtryConf", id);
    }

    /**
     * Searches a list of StatMbPerCtryConf entity 
     * @param StatMbPerCtryConf statMbPerCtryConf
     * @return List
     */
    public List<StatMbPerCtryConf> searchPrototypeStatMbPerCtryConf(StatMbPerCtryConf statMbPerCtryConf) {      
       return searchPrototype (statMbPerCtryConf, null);            
    }  
    
    protected List<StatMbPerCtryConf> searchPrototype (StatMbPerCtryConf statMbPerCtryConf, Integer maxResults) { 
       Query hquery = getEntityManager().createQuery(getStatMbPerCtryConfSearchEqualQuery (statMbPerCtryConf));
       if (maxResults!=null)
          hquery.setMaxResults(maxResults);
       return hquery.getResultList();         
    }        
    
    public List<StatMbPerCtryConf> searchPrototypeStatMbPerCtryConf (List<StatMbPerCtryConf> statMbPerCtryConfs) {
       return searchPrototype (statMbPerCtryConfs, null);  
    }

    protected List<StatMbPerCtryConf> searchPrototype (List<StatMbPerCtryConf> statMbPerCtryConfs, Integer maxResults) {    
       //TODO convert setMaxResults in JPA if (maxResults!=null)
       //   getJpaTemplate().setMaxResults(maxResults);
       return getJpaTemplate().find(getStatMbPerCtryConfSearchEqualQuery (null, statMbPerCtryConfs));            
    }    

    public List<StatMbPerCtryConf> searchDistinctPrototypeStatMbPerCtryConf (StatMbPerCtryConf statMbPerCtryConfMask, List<StatMbPerCtryConf> statMbPerCtryConfs) {
        return getJpaTemplate().find(getStatMbPerCtryConfSearchEqualQuery (statMbPerCtryConfMask, statMbPerCtryConfs));    
    }
         
	/**
     * Searches a list of StatMbPerCtryConf entity 
     * @param StatMbPerCtryConf statMbPerCtryConfPositive
     * @param StatMbPerCtryConf statMbPerCtryConfNegative
     * @return List
     */
    public List<StatMbPerCtryConf> searchPrototypeStatMbPerCtryConf(StatMbPerCtryConf statMbPerCtryConfPositive, StatMbPerCtryConf statMbPerCtryConfNegative) {
	    return getJpaTemplate().find(getStatMbPerCtryConfSearchEqualQuery (statMbPerCtryConfPositive, statMbPerCtryConfNegative));  
    }

    /**
    * return a jql query search on a StatMbPerCtryConf prototype
    */
    protected String getStatMbPerCtryConfSearchEqualQuery (StatMbPerCtryConf statMbPerCtryConf) {
        StringBuffer query = new StringBuffer();
        StringBuffer queryWhere = new StringBuffer();
        query.append ("SELECT statMbPerCtryConf FROM StatMbPerCtryConf statMbPerCtryConf ");
        queryWhere.append (getStatMbPerCtryConfSearchEqualWhereQueryChunk(statMbPerCtryConf, false));   
	    return getHQuery(query.toString(), queryWhere.toString());
    }


    /**
    * return a jql search for a list of StatMbPerCtryConf prototype
    */
    protected String getStatMbPerCtryConfSearchEqualQuery (StatMbPerCtryConf statMbPerCtryConfMask, List<StatMbPerCtryConf> statMbPerCtryConfs) {
        boolean isOrSet = false;
        StringBuffer query = new StringBuffer();
        if (statMbPerCtryConfMask !=null)
           query.append (getStatMbPerCtryConfMaskWhat (statMbPerCtryConfMask));
        query.append (" FROM StatMbPerCtryConf statMbPerCtryConf ");
        StringBuffer queryWhere = new StringBuffer();
        for (StatMbPerCtryConf statMbPerCtryConf : statMbPerCtryConfs) {
           if (!isAllNull(statMbPerCtryConf)) {        
	           queryWhere.append (getQueryOR (isOrSet));
	           isOrSet = true;        
	           queryWhere.append (" ("+getStatMbPerCtryConfSearchEqualWhereQueryChunk(statMbPerCtryConf, false)+") ");
           }
        }
	    return getHQuery(query.toString(), queryWhere.toString());
    }	
    
    protected String getStatMbPerCtryConfMaskWhat (StatMbPerCtryConf statMbPerCtryConfMask) {
        boolean isCommaSet = false;
        StringBuffer query = new StringBuffer("SELECT DISTINCT ");
        if (statMbPerCtryConfMask.getId() != null) {
           query.append (getQueryComma (isCommaSet));
           isCommaSet = true;
           query.append(" id ");
        }
        if (statMbPerCtryConfMask.getCountry() != null) {
           query.append (getQueryComma (isCommaSet));
           isCommaSet = true;
           query.append(" country ");
        }
        if (statMbPerCtryConfMask.getConferenceName() != null) {
           query.append (getQueryComma (isCommaSet));
           isCommaSet = true;
           query.append(" conferenceName ");
        }
        if (statMbPerCtryConfMask.getNumber() != null) {
           query.append (getQueryComma (isCommaSet));
           isCommaSet = true;
           query.append(" number ");
        }
        if (!isCommaSet)
           return "";
	    return query.toString();        
    }
    
    protected String getStatMbPerCtryConfSearchEqualWhereQueryChunk (StatMbPerCtryConf statMbPerCtryConf, boolean isAndSet) {
        StringBuffer query = new StringBuffer();
        if (statMbPerCtryConf.getId() != null) {
           query.append (getQueryAND (isAndSet));
           isAndSet = true;
           query.append(" statMbPerCtryConf.id = '"+ statMbPerCtryConf.getId()+"' ");
        }
        if (statMbPerCtryConf.getCountry() != null) {
           query.append (getQueryAND (isAndSet));
           isAndSet = true;
           query.append(" statMbPerCtryConf.country = '"+ statMbPerCtryConf.getCountry()+"' ");
        }
        if (statMbPerCtryConf.getConferenceName() != null) {
           query.append (getQueryAND (isAndSet));
           isAndSet = true;
           query.append(" statMbPerCtryConf.conferenceName = '"+ statMbPerCtryConf.getConferenceName()+"' ");
        }
        if (statMbPerCtryConf.getNumber() != null) {
           query.append (getQueryAND (isAndSet));
           isAndSet = true;
           query.append(" statMbPerCtryConf.number = "+ statMbPerCtryConf.getNumber() + " ");
        }
	    return query.toString();
    }   
     
    /**
    * return a jql search on a StatMbPerCtryConf prototype with positive and negative beans
    */
    protected String getStatMbPerCtryConfSearchEqualQuery (StatMbPerCtryConf statMbPerCtryConfPositive, StatMbPerCtryConf statMbPerCtryConfNegative) {
        boolean isWhereSet = false;
        StringBuffer query = new StringBuffer();
        query.append (" SELECT statMbPerCtryConf FROM StatMbPerCtryConf statMbPerCtryConf ");
        if (statMbPerCtryConfPositive!=null && statMbPerCtryConfPositive.getId() != null) {
           query.append (getQueryWHERE_AND (isWhereSet));
           isWhereSet = true;
           query.append(" statMbPerCtryConf.id = '"+ statMbPerCtryConfPositive.getId()+"' ");
        } else if (statMbPerCtryConfNegative.getId() != null) {
           query.append (getQueryWHERE_AND (isWhereSet));
           isWhereSet = true;   
           query.append(" statMbPerCtryConf.id is null ");
        }
        if (statMbPerCtryConfPositive!=null && statMbPerCtryConfPositive.getCountry() != null) {
           query.append (getQueryWHERE_AND (isWhereSet));
           isWhereSet = true;
           query.append(" statMbPerCtryConf.country = '"+ statMbPerCtryConfPositive.getCountry()+"' ");
        } else if (statMbPerCtryConfNegative.getCountry() != null) {
           query.append (getQueryWHERE_AND (isWhereSet));
           isWhereSet = true;   
           query.append(" statMbPerCtryConf.country is null ");
        }
        if (statMbPerCtryConfPositive!=null && statMbPerCtryConfPositive.getConferenceName() != null) {
           query.append (getQueryWHERE_AND (isWhereSet));
           isWhereSet = true;
           query.append(" statMbPerCtryConf.conferenceName = '"+ statMbPerCtryConfPositive.getConferenceName()+"' ");
        } else if (statMbPerCtryConfNegative.getConferenceName() != null) {
           query.append (getQueryWHERE_AND (isWhereSet));
           isWhereSet = true;   
           query.append(" statMbPerCtryConf.conferenceName is null ");
        }
        if (statMbPerCtryConfPositive!=null && statMbPerCtryConfPositive.getNumber() != null) {
           query.append (getQueryWHERE_AND (isWhereSet));
           isWhereSet = true;
           query.append(" statMbPerCtryConf.number = "+ statMbPerCtryConfPositive.getNumber() + " ");
        } else if (statMbPerCtryConfNegative.getNumber() != null) {
           query.append (getQueryWHERE_AND (isWhereSet));
           isWhereSet = true;   
           query.append(" statMbPerCtryConf.number is null ");
        }
	    return query.toString();
    }
    
    /**
     * Load a paginated list of StatMbPerCtryConf entity dependent of pagination criteria
     * @param PaginationCriteria paginationCriteria
     * @return List
     */
    public List <StatMbPerCtryConf> loadPaginatedStatMbPerCtryConf (StatMbPerCtryConf statMbPerCtryConf, PaginationCriteria paginationCriteria) {
	    List<String> page = loadPaginatedStatMbPerCtryConfIdentitiesFromStartPositionId(statMbPerCtryConf, paginationCriteria);
    	int start = paginationCriteria.getNumberOfRowsReturned()*(paginationCriteria.getPageNumber());
    	int max = page.size();
    	if (start<max) {
    	   List<String> returnPage = page.subList(start, max);	
           StringBuffer query = new StringBuffer();
           query.append (" SELECT statMbPerCtryConf FROM StatMbPerCtryConf statMbPerCtryConf ");      
	       query.append(" where statMbPerCtryConf.id in (");
	       for (Iterator iter = returnPage.iterator(); iter.hasNext();) {
			  String id = (String) iter.next();
			  query.append("'"+id.toString()+"'");
		      if (iter.hasNext())
			     query.append(",");
		   }
	       query.append(") ");
	       return getJpaTemplate().find(query.toString()); 
    	} 
        return new ArrayList<StatMbPerCtryConf>();
    }      

    protected List<String> loadPaginatedStatMbPerCtryConfIdentitiesFromStartPositionId (StatMbPerCtryConf statMbPerCtryConf, PaginationCriteria paginationCriteria) {
       boolean isWhereSet = false;
       StringBuffer query = new StringBuffer();
       query.append ("select statMbPerCtryConf.id ");
       query.append (getStatMbPerCtryConfSearchEqualQuery (statMbPerCtryConf));
       if (paginationCriteria.getOrderList()!=null) {
    	   query.append(" order by "+paginationCriteria.getOrderList());
       }
       int maxResult = paginationCriteria.getNumberOfRowsReturned()*(1+paginationCriteria.getPageNumber());
       List<String> set = getEntityManager().createNamedQuery(query.toString()).setMaxResults(maxResult).getResultList();
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
    
    protected void convertTransientReferenceToNull (StatMbPerCtryConf statMbPerCtryConf) {
    }
}
