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
import net.sf.mp.demo.conference.dao.face.statistics.StatMbByRoleDao;
import net.sf.mp.demo.conference.domain.statistics.StatMbByRole;

/**
 *
 * <p>Title: StatMbByRoleJPAImpl</p>
 *
 * <p>Description: Interface of a Data access object dealing with StatMbByRoleJPAImpl
 * persistence. It offers a set of methods which allow for saving,
 * deleting and searching StatMbByRoleJPAImpl objects</p>
 *
 */

public class StatMbByRoleJPAImpl extends JpaDaoSupport implements StatMbByRoleDao {

	public StatMbByRoleJPAImpl () {}

    /**
     * Inserts a StatMbByRole entity 
     * @param StatMbByRole statMbByRole
     */
    public void insertStatMbByRole(StatMbByRole statMbByRole) {
       convertTransientReferenceToNull (statMbByRole);
       getJpaTemplate().persist(statMbByRole);
    }

    protected void insertStatMbByRole(EntityManager emForRecursiveDao, StatMbByRole statMbByRole) {
       emForRecursiveDao.persist(statMbByRole);
    } 
    /**
     * Inserts a list of StatMbByRole entity 
     * @param List<StatMbByRole> statMbByRoles
     */
    public void insertStatMbByRoles(List<StatMbByRole> statMbByRoles) {
    	//TODO
    }
    /**
     * Updates a StatMbByRole entity 
     * @param StatMbByRole statMbByRole
     */
    public StatMbByRole updateStatMbByRole(StatMbByRole statMbByRole) {
       convertTransientReferenceToNull (statMbByRole);
       return getJpaTemplate().merge(statMbByRole);
    }

	/**
     * Updates a StatMbByRole entity with only the attributes set into StatMbByRole.
	 * The primary keys are to be set for this method to operate.
	 * This is a performance friendly feature, which remove the udibiquous full load and full update when an
	 * update is issued
     * Remark: The primary keys cannot be update by this methods, nor are the attributes that must be set to null.
     * @param StatMbByRole statMbByRole
    */ 
    @Transactional
    public int updateNotNullOnlyStatMbByRole(StatMbByRole statMbByRole) {
        Query jpaQuery = getEntityManager().createQuery(getUpdateNotNullOnlyStatMbByRoleQueryChunk(statMbByRole));
        if (statMbByRole.getId() != null) {
           jpaQuery.setParameter ("id", statMbByRole.getId());
        }   
        if (statMbByRole.getStatMbPerCtryConfId() != null) {
           jpaQuery.setParameter ("statMbPerCtryConfId", statMbByRole.getStatMbPerCtryConfId());
        }   
        if (statMbByRole.getRoleName() != null) {
           jpaQuery.setParameter ("roleName", statMbByRole.getRoleName());
        }   
        if (statMbByRole.getNumber() != null) {
           jpaQuery.setParameter ("number", statMbByRole.getNumber());
        }   
		return jpaQuery.executeUpdate();
    }

    protected String getUpdateNotNullOnlyStatMbByRoleQueryChunkPrototype (StatMbByRole statMbByRole) {
        boolean isWhereSet = false;
        StringBuffer query = new StringBuffer();
        query.append (" update StatMbByRole statMbByRole ");
        if (statMbByRole.getStatMbPerCtryConfId() != null) {
           query.append (getQueryCommaSet (isWhereSet));
           isWhereSet = true; 
           query.append(" statMbByRole.statMbPerCtryConfId = :statMbPerCtryConfId");
        }
        if (statMbByRole.getRoleName() != null) {
           query.append (getQueryCommaSet (isWhereSet));
           isWhereSet = true; 
           query.append(" statMbByRole.roleName = :roleName");
        }
        if (statMbByRole.getNumber() != null) {
           query.append (getQueryCommaSet (isWhereSet));
           isWhereSet = true; 
           query.append(" statMbByRole.number = :number");
        }
        return query.toString();
    }
    
    protected String getUpdateNotNullOnlyStatMbByRoleQueryChunk (StatMbByRole statMbByRole) {
        boolean isWhereSet = false;
        StringBuffer query = new StringBuffer(getUpdateNotNullOnlyStatMbByRoleQueryChunkPrototype(statMbByRole));
        if (statMbByRole.getId() != null) {
           query.append (getQueryWHERE_AND (isWhereSet));
           isWhereSet = true;
			     query.append(" statMbByRole.id = :id");
        }
        return query.toString();
    }
    
                
	protected StatMbByRole assignBlankToNull (StatMbByRole statMbByRole) {
	    if (statMbByRole==null)
			return null;
        if (statMbByRole.getId()!=null && statMbByRole.getId().equals(""))
           statMbByRole.setId((String)null);
        if (statMbByRole.getStatMbPerCtryConfId()!=null && statMbByRole.getStatMbPerCtryConfId().equals(""))
           statMbByRole.setStatMbPerCtryConfId((String)null);
        if (statMbByRole.getRoleName()!=null && statMbByRole.getRoleName().equals(""))
           statMbByRole.setRoleName((String)null);
		return statMbByRole;
	}
	
	protected boolean isAllNull (StatMbByRole statMbByRole) {
	    if (statMbByRole==null)
			return true;
        if (statMbByRole.getId()!=null) 
            return false;
        if (statMbByRole.getStatMbPerCtryConfId()!=null) 
            return false;
        if (statMbByRole.getRoleName()!=null) 
            return false;
        if (statMbByRole.getNumber()!=null) 
            return false;
		return true;
	}
		
	@Transactional
    public int updateNotNullOnlyPrototypeStatMbByRole(StatMbByRole statMbByRole, StatMbByRole prototypeCriteria) {
        boolean isWhereSet = false;
        StringBuffer query = new StringBuffer();
        query.append (" update StatMbByRole statMbByRole ");
        if (statMbByRole.getId() != null) {
           query.append (getQueryCommaSet (isWhereSet));
           isWhereSet = true; 
           query.append(" statMbByRole.id = '"+ statMbByRole.getId()+"' ");
        }
        if (statMbByRole.getStatMbPerCtryConfId() != null) {
           query.append (getQueryCommaSet (isWhereSet));
           isWhereSet = true; 
           query.append(" statMbByRole.statMbPerCtryConfId = '"+ statMbByRole.getStatMbPerCtryConfId()+"' ");
        }
        if (statMbByRole.getRoleName() != null) {
           query.append (getQueryCommaSet (isWhereSet));
           isWhereSet = true; 
           query.append(" statMbByRole.roleName = '"+ statMbByRole.getRoleName()+"' ");
        }
        if (statMbByRole.getNumber() != null) {
           query.append (getQueryCommaSet (isWhereSet));
           isWhereSet = true; 
           query.append(" statMbByRole.number = "+ statMbByRole.getNumber() + " ");
        }
		isWhereSet = false; 
        if (prototypeCriteria.getId() != null) {
           query.append (getQueryWHERE_AND (isWhereSet));
           isWhereSet = true;
           query.append(" statMbByRole.id = '"+ prototypeCriteria.getId()+"' ");
        }
        if (prototypeCriteria.getStatMbPerCtryConfId() != null) {
           query.append (getQueryWHERE_AND (isWhereSet));
           isWhereSet = true;
           query.append(" statMbByRole.statMbPerCtryConfId = '"+ prototypeCriteria.getStatMbPerCtryConfId()+"' ");
        }
        if (prototypeCriteria.getRoleName() != null) {
           query.append (getQueryWHERE_AND (isWhereSet));
           isWhereSet = true;
           query.append(" statMbByRole.roleName = '"+ prototypeCriteria.getRoleName()+"' ");
        }
        if (prototypeCriteria.getNumber() != null) {
           query.append (getQueryWHERE_AND (isWhereSet));
           isWhereSet = true;
           query.append(" statMbByRole.number = "+ prototypeCriteria.getNumber() + " ");
        }
        Query jpaQuery = getEntityManager().createQuery(query.toString());
		return jpaQuery.executeUpdate();
    }
     
     /**
     * Saves a StatMbByRole entity 
     * @param StatMbByRole statMbByRole
     */
    public void saveStatMbByRole(StatMbByRole statMbByRole) {
       //getJpaTemplate().persist(statMbByRole);
       convertTransientReferenceToNull (statMbByRole);
       if (getJpaTemplate().contains(statMbByRole)) {
          getJpaTemplate().merge(statMbByRole);
       } else {
          getJpaTemplate().persist(statMbByRole);
       }
       getJpaTemplate().flush(); 
    }
       
    /**
     * Deletes a StatMbByRole entity 
     * @param StatMbByRole statMbByRole
     */
    public void deleteStatMbByRole(StatMbByRole statMbByRole) {
      getJpaTemplate().remove(statMbByRole);
    }
    
    /**
     * Loads the StatMbByRole entity which is related to an instance of
     * StatMbByRole
     * @param Long id
     * @return StatMbByRole The StatMbByRole entity
     
    public StatMbByRole loadStatMbByRole(Long id) {
    	return (StatMbByRole)getJpaTemplate().get(StatMbByRole.class, id);
    }
*/
  
    /**
     * Loads the StatMbByRole entity which is related to an instance of
     * StatMbByRole
     * @param java.lang.String Id
     * @return StatMbByRole The StatMbByRole entity
     */
    public StatMbByRole loadStatMbByRole(java.lang.String id) {
    	return (StatMbByRole)getJpaTemplate().find(StatMbByRole.class, id);
    }
    
    /**
     * Loads a list of StatMbByRole entity 
     * @param List<java.lang.String> ids
     * @return List<StatMbByRole> The StatMbByRole entity
     */
    public List<StatMbByRole> loadStatMbByRoleListByStatMbByRole (List<StatMbByRole> statMbByRoles) {
       return null;
    }
    
    /**
     * Loads a list of StatMbByRole entity 
     * @param List<java.lang.String> ids
     * @return List<StatMbByRole> The StatMbByRole entity
     */
    public List<StatMbByRole> loadStatMbByRoleListById(List<java.lang.String> ids){
       return null;
    }
    
    /**
     * Loads the StatMbByRole entity which is related to an instance of
     * StatMbByRole and its dependent one to many objects
     * @param Long id
     * @return StatMbByRole The StatMbByRole entity
     */
    public StatMbByRole loadFullFirstLevelStatMbByRole(java.lang.String id) {
        List list = getJpaTemplate().find(
                     "SELECT statMbByRole FROM StatMbByRole statMbByRole "
                     + " WHERE statMbByRole.id = "+id
               );
         if (list!=null && !list.isEmpty())
            return (StatMbByRole)list.get(0);
         return null;
    	//return null;//(StatMbByRole) getJpaTemplate().queryForObject("loadFullFirstLevelStatMbByRole", id);
    }

    /**
     * Loads the StatMbByRole entity which is related to an instance of
     * StatMbByRole
     * @param StatMbByRole statMbByRole
     * @return StatMbByRole The StatMbByRole entity
     */
    public StatMbByRole loadFullFirstLevelStatMbByRole(StatMbByRole statMbByRole) {
        boolean isWhereSet = false;
        StringBuffer query = new StringBuffer();
        query.append ("SELECT statMbByRole FROM StatMbByRole statMbByRole ");
        if (statMbByRole.getId() != null) {
           query.append (getQueryWHERE_AND (isWhereSet));
           isWhereSet = true;
           query.append(" statMbByRole.id = '"+ statMbByRole.getId()+"' ");
         }
	        	List list = getJpaTemplate().find(query.toString());
        if (list!=null && !list.isEmpty())
           return (StatMbByRole)list.get(0);    
        return null;
    }  
     
    /**
     * Loads the StatMbByRole entity which is related to an instance of
     * StatMbByRole and its dependent objects one to many
     * @param Long id
     * @return StatMbByRole The StatMbByRole entity
     */
    public StatMbByRole loadFullStatMbByRole(Long id) {
    	return null;//(StatMbByRole)getJpaTemplate().queryForObject("loadFullStatMbByRole", id);
    }

    /**
     * Searches a list of StatMbByRole entity 
     * @param StatMbByRole statMbByRole
     * @return List
     */
    public List<StatMbByRole> searchPrototypeStatMbByRole(StatMbByRole statMbByRole) {      
       return searchPrototype (statMbByRole, null);            
    }  
    
    protected List<StatMbByRole> searchPrototype (StatMbByRole statMbByRole, Integer maxResults) { 
       Query hquery = getEntityManager().createQuery(getStatMbByRoleSearchEqualQuery (statMbByRole));
       if (maxResults!=null)
          hquery.setMaxResults(maxResults);
       return hquery.getResultList();         
    }        
    
    public List<StatMbByRole> searchPrototypeStatMbByRole (List<StatMbByRole> statMbByRoles) {
       return searchPrototype (statMbByRoles, null);  
    }

    protected List<StatMbByRole> searchPrototype (List<StatMbByRole> statMbByRoles, Integer maxResults) {    
       //TODO convert setMaxResults in JPA if (maxResults!=null)
       //   getJpaTemplate().setMaxResults(maxResults);
       return getJpaTemplate().find(getStatMbByRoleSearchEqualQuery (null, statMbByRoles));            
    }    

    public List<StatMbByRole> searchDistinctPrototypeStatMbByRole (StatMbByRole statMbByRoleMask, List<StatMbByRole> statMbByRoles) {
        return getJpaTemplate().find(getStatMbByRoleSearchEqualQuery (statMbByRoleMask, statMbByRoles));    
    }
         
	/**
     * Searches a list of StatMbByRole entity 
     * @param StatMbByRole statMbByRolePositive
     * @param StatMbByRole statMbByRoleNegative
     * @return List
     */
    public List<StatMbByRole> searchPrototypeStatMbByRole(StatMbByRole statMbByRolePositive, StatMbByRole statMbByRoleNegative) {
	    return getJpaTemplate().find(getStatMbByRoleSearchEqualQuery (statMbByRolePositive, statMbByRoleNegative));  
    }

    /**
    * return a jql query search on a StatMbByRole prototype
    */
    protected String getStatMbByRoleSearchEqualQuery (StatMbByRole statMbByRole) {
        StringBuffer query = new StringBuffer();
        StringBuffer queryWhere = new StringBuffer();
        query.append ("SELECT statMbByRole FROM StatMbByRole statMbByRole ");
        queryWhere.append (getStatMbByRoleSearchEqualWhereQueryChunk(statMbByRole, false));   
	    return getHQuery(query.toString(), queryWhere.toString());
    }


    /**
    * return a jql search for a list of StatMbByRole prototype
    */
    protected String getStatMbByRoleSearchEqualQuery (StatMbByRole statMbByRoleMask, List<StatMbByRole> statMbByRoles) {
        boolean isOrSet = false;
        StringBuffer query = new StringBuffer();
        if (statMbByRoleMask !=null)
           query.append (getStatMbByRoleMaskWhat (statMbByRoleMask));
        query.append (" FROM StatMbByRole statMbByRole ");
        StringBuffer queryWhere = new StringBuffer();
        for (StatMbByRole statMbByRole : statMbByRoles) {
           if (!isAllNull(statMbByRole)) {        
	           queryWhere.append (getQueryOR (isOrSet));
	           isOrSet = true;        
	           queryWhere.append (" ("+getStatMbByRoleSearchEqualWhereQueryChunk(statMbByRole, false)+") ");
           }
        }
	    return getHQuery(query.toString(), queryWhere.toString());
    }	
    
    protected String getStatMbByRoleMaskWhat (StatMbByRole statMbByRoleMask) {
        boolean isCommaSet = false;
        StringBuffer query = new StringBuffer("SELECT DISTINCT ");
        if (statMbByRoleMask.getId() != null) {
           query.append (getQueryComma (isCommaSet));
           isCommaSet = true;
           query.append(" id ");
        }
        if (statMbByRoleMask.getStatMbPerCtryConfId() != null) {
           query.append (getQueryComma (isCommaSet));
           isCommaSet = true;
           query.append(" statMbPerCtryConfId ");
        }
        if (statMbByRoleMask.getRoleName() != null) {
           query.append (getQueryComma (isCommaSet));
           isCommaSet = true;
           query.append(" roleName ");
        }
        if (statMbByRoleMask.getNumber() != null) {
           query.append (getQueryComma (isCommaSet));
           isCommaSet = true;
           query.append(" number ");
        }
        if (!isCommaSet)
           return "";
	    return query.toString();        
    }
    
    protected String getStatMbByRoleSearchEqualWhereQueryChunk (StatMbByRole statMbByRole, boolean isAndSet) {
        StringBuffer query = new StringBuffer();
        if (statMbByRole.getId() != null) {
           query.append (getQueryAND (isAndSet));
           isAndSet = true;
           query.append(" statMbByRole.id = '"+ statMbByRole.getId()+"' ");
        }
        if (statMbByRole.getStatMbPerCtryConfId() != null) {
           query.append (getQueryAND (isAndSet));
           isAndSet = true;
           query.append(" statMbByRole.statMbPerCtryConfId = '"+ statMbByRole.getStatMbPerCtryConfId()+"' ");
        }
        if (statMbByRole.getRoleName() != null) {
           query.append (getQueryAND (isAndSet));
           isAndSet = true;
           query.append(" statMbByRole.roleName = '"+ statMbByRole.getRoleName()+"' ");
        }
        if (statMbByRole.getNumber() != null) {
           query.append (getQueryAND (isAndSet));
           isAndSet = true;
           query.append(" statMbByRole.number = "+ statMbByRole.getNumber() + " ");
        }
	    return query.toString();
    }   
     
    /**
    * return a jql search on a StatMbByRole prototype with positive and negative beans
    */
    protected String getStatMbByRoleSearchEqualQuery (StatMbByRole statMbByRolePositive, StatMbByRole statMbByRoleNegative) {
        boolean isWhereSet = false;
        StringBuffer query = new StringBuffer();
        query.append (" SELECT statMbByRole FROM StatMbByRole statMbByRole ");
        if (statMbByRolePositive!=null && statMbByRolePositive.getId() != null) {
           query.append (getQueryWHERE_AND (isWhereSet));
           isWhereSet = true;
           query.append(" statMbByRole.id = '"+ statMbByRolePositive.getId()+"' ");
        } else if (statMbByRoleNegative.getId() != null) {
           query.append (getQueryWHERE_AND (isWhereSet));
           isWhereSet = true;   
           query.append(" statMbByRole.id is null ");
        }
        if (statMbByRolePositive!=null && statMbByRolePositive.getStatMbPerCtryConfId() != null) {
           query.append (getQueryWHERE_AND (isWhereSet));
           isWhereSet = true;
           query.append(" statMbByRole.statMbPerCtryConfId = '"+ statMbByRolePositive.getStatMbPerCtryConfId()+"' ");
        } else if (statMbByRoleNegative.getStatMbPerCtryConfId() != null) {
           query.append (getQueryWHERE_AND (isWhereSet));
           isWhereSet = true;   
           query.append(" statMbByRole.statMbPerCtryConfId is null ");
        }
        if (statMbByRolePositive!=null && statMbByRolePositive.getRoleName() != null) {
           query.append (getQueryWHERE_AND (isWhereSet));
           isWhereSet = true;
           query.append(" statMbByRole.roleName = '"+ statMbByRolePositive.getRoleName()+"' ");
        } else if (statMbByRoleNegative.getRoleName() != null) {
           query.append (getQueryWHERE_AND (isWhereSet));
           isWhereSet = true;   
           query.append(" statMbByRole.roleName is null ");
        }
        if (statMbByRolePositive!=null && statMbByRolePositive.getNumber() != null) {
           query.append (getQueryWHERE_AND (isWhereSet));
           isWhereSet = true;
           query.append(" statMbByRole.number = "+ statMbByRolePositive.getNumber() + " ");
        } else if (statMbByRoleNegative.getNumber() != null) {
           query.append (getQueryWHERE_AND (isWhereSet));
           isWhereSet = true;   
           query.append(" statMbByRole.number is null ");
        }
	    return query.toString();
    }
    
    /**
     * Load a paginated list of StatMbByRole entity dependent of pagination criteria
     * @param PaginationCriteria paginationCriteria
     * @return List
     */
    public List <StatMbByRole> loadPaginatedStatMbByRole (StatMbByRole statMbByRole, PaginationCriteria paginationCriteria) {
	    List<String> page = loadPaginatedStatMbByRoleIdentitiesFromStartPositionId(statMbByRole, paginationCriteria);
    	int start = paginationCriteria.getNumberOfRowsReturned()*(paginationCriteria.getPageNumber());
    	int max = page.size();
    	if (start<max) {
    	   List<String> returnPage = page.subList(start, max);	
           StringBuffer query = new StringBuffer();
           query.append (" SELECT statMbByRole FROM StatMbByRole statMbByRole ");      
	       query.append(" where statMbByRole.id in (");
	       for (Iterator iter = returnPage.iterator(); iter.hasNext();) {
			  String id = (String) iter.next();
			  query.append("'"+id.toString()+"'");
		      if (iter.hasNext())
			     query.append(",");
		   }
	       query.append(") ");
	       return getJpaTemplate().find(query.toString()); 
    	} 
        return new ArrayList<StatMbByRole>();
    }      

    protected List<String> loadPaginatedStatMbByRoleIdentitiesFromStartPositionId (StatMbByRole statMbByRole, PaginationCriteria paginationCriteria) {
       boolean isWhereSet = false;
       StringBuffer query = new StringBuffer();
       query.append ("select statMbByRole.id ");
       query.append (getStatMbByRoleSearchEqualQuery (statMbByRole));
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
    
    protected void convertTransientReferenceToNull (StatMbByRole statMbByRole) {
    }
}
