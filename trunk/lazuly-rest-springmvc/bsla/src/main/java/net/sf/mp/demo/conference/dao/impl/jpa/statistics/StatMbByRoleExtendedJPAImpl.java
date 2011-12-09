package net.sf.mp.demo.conference.dao.impl.jpa.statistics;

import java.lang.reflect.InvocationTargetException;
import java.sql.Clob;
import java.sql.Blob;
import java.sql.Timestamp;
import java.util.Date;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.Iterator;
import java.util.List;
import java.util.Hashtable;
import java.util.Map;
import java.util.Map.Entry;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;

import javax.persistence.Query;
import javax.persistence.EntityManager;

import net.sf.minuteProject.architecture.query.QueryWhatInit;
import net.sf.minuteProject.architecture.query.impl.QueryCountInit;
import net.sf.minuteProject.architecture.query.impl.QuerySelectCountInit;
import net.sf.minuteProject.architecture.query.impl.QuerySelectInit;
import net.sf.minuteProject.architecture.query.impl.QuerySelectDistinctInit;
import net.sf.minuteProject.architecture.cache.SimpleCache;
import net.sf.minuteProject.architecture.filter.data.ClauseCriterion;
import net.sf.minuteProject.architecture.filter.data.Criteria;
import net.sf.minuteProject.architecture.filter.data.Criterion;
import net.sf.minuteProject.architecture.filter.data.InCriterion;
import net.sf.minuteProject.architecture.filter.data.OrderCriteria;
import net.sf.minuteProject.architecture.utils.BeanUtils;
import net.sf.mp.demo.conference.dao.face.statistics.StatMbByRoleExtDao;
import net.sf.mp.demo.conference.domain.statistics.StatMbByRole;
import net.sf.mp.demo.conference.dao.impl.jpa.statistics.StatMbByRoleJPAImpl;

/**
 *
 * <p>Title: StatMbByRoleExtendedJPAImpl</p>
 *
 * <p>Description: Interface of a Data access object dealing with StatMbByRoleExtendedJPAImpl
 * persistence. It offers a set of methods which allow for saving,
 * deleting and searching StatMbByRoleExtendedJPAImpl objects</p>
 *
 */

public class StatMbByRoleExtendedJPAImpl extends StatMbByRoleJPAImpl implements StatMbByRoleExtDao {

    private Logger log = Logger.getLogger(this.getClass());
    
    private SimpleCache simpleCache = new SimpleCache();
    private EntityManager emForRecursiveDao; // dao that needs other dao in a recursive manner not support by spring configuration

    public StatMbByRoleExtendedJPAImpl () {}

    /**
     * generic to move after in superclass
     */
    public StatMbByRoleExtendedJPAImpl (EntityManager emForRecursiveDao) {
       this.emForRecursiveDao = emForRecursiveDao;
    }
            
    /**
     * generic to move after in superclass
     */
    public List<Object[]> getSQLQueryResult(String query) {
		   Query queryJ = getEntityManager().createNativeQuery(query);
		   return queryJ.getResultList();		        
    }


    /**
     * Inserts a StatMbByRole entity with cascade of its children
     * @param StatMbByRole statMbByRole
     */
    public void insertStatMbByRoleWithCascade(StatMbByRole statMbByRole) {
    	StatMbByRoleExtendedJPAImpl statmbbyroleextendedjpaimpl = new StatMbByRoleExtendedJPAImpl(getEntityManager());
    	statmbbyroleextendedjpaimpl.insertStatMbByRoleWithCascade(statmbbyroleextendedjpaimpl.getEntityManagerForRecursiveDao(), statMbByRole);
    }
     
    public void insertStatMbByRoleWithCascade(EntityManager emForRecursiveDao, StatMbByRole statMbByRole) {
       insertStatMbByRole(emForRecursiveDao, statMbByRole);
    }
        
    /**
     * Inserts a list of StatMbByRole entity with cascade of its children
     * @param List<StatMbByRole> statMbByRoles
     */
    public void insertStatMbByRolesWithCascade(List<StatMbByRole> statMbByRoles) {
       for (StatMbByRole statMbByRole : statMbByRoles) {
          insertStatMbByRoleWithCascade(statMbByRole);
       }
    } 
        
    /**
     * lookup StatMbByRole entity StatMbByRole, criteria and max result number
     */
    public List<StatMbByRole> lookupStatMbByRole(StatMbByRole statMbByRole, Criteria criteria, Integer numberOfResult, EntityManager em) {
		boolean isWhereSet = false;
        StringBuffer query = new StringBuffer();
        query.append ("SELECT statMbByRole FROM StatMbByRole statMbByRole ");
        for (Criterion criterion : criteria.getClauseCriterions()) {
            query.append (getQueryWHERE_AND (isWhereSet));
            isWhereSet = true;   
            query.append(criterion.getExpression());			
        }
        OrderCriteria orderCriteria = criteria.getOrderCriteria();
        if (criteria.getOrderCriteria()!=null)
        	query.append(orderCriteria.getExpression());		
        Query hquery = em.createQuery(query.toString());
        if (numberOfResult!=null)
            hquery.setMaxResults(numberOfResult);
        return hquery.getResultList();    
    }
    
    public List<StatMbByRole> lookupStatMbByRole(StatMbByRole statMbByRole, Criteria criteria, Integer numberOfResult) {
		return lookupStatMbByRole(statMbByRole, criteria, numberOfResult, getEntityManager());
    }

    public Integer updateNotNullOnlyStatMbByRole (StatMbByRole statMbByRole, Criteria criteria) {
        String queryWhat = getUpdateNotNullOnlyStatMbByRoleQueryChunkPrototype (statMbByRole);
        StringBuffer query = new StringBuffer (queryWhat);
        boolean isWhereSet = false;
        for (Criterion criterion : criteria.getClauseCriterions()) {
            query.append (getQueryWHERE_AND (isWhereSet));
            isWhereSet = true;   
            query.append(criterion.getExpression());			
        }  
        Query jpaQuery = getEntityManager().createQuery(query.toString());
        isWhereSet = false;
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
	
	public StatMbByRole affectStatMbByRole (StatMbByRole statMbByRole) {
	    return referStatMbByRole (statMbByRole, false);		    
	}
		
	/**
	 * Assign the first statMbByRole retrieved corresponding to the statMbByRole criteria.
	 * Blank criteria are mapped to null.
	 * If no criteria is found, null is returned.
	 * If there is no statMbByRole corresponding in the database. Then statMbByRole is inserted and returned with its primary key(s). 
	 */
	public StatMbByRole assignStatMbByRole (StatMbByRole statMbByRole) {
	    return referStatMbByRole (statMbByRole, true);	
	}
	
	public StatMbByRole referStatMbByRole (StatMbByRole statMbByRole, boolean isAssign) {
		statMbByRole = assignBlankToNull (statMbByRole);
		if (isAllNull(statMbByRole))
			return null;
		else {
			List<StatMbByRole> list = searchPrototypeStatMbByRole(statMbByRole);
			if (list.isEmpty()) {
			    if (isAssign)
			       insertStatMbByRole(statMbByRole);
			    else
				   return null;
			}
			else if (list.size()==1)
				statMbByRole.copy(list.get(0));
			else 
				//TODO log error
				statMbByRole.copy(list.get(0));
		}
		return statMbByRole;		    
	}

   public StatMbByRole assignStatMbByRoleUseCache (StatMbByRole statMbByRole) {
      return referStatMbByRoleUseCache (statMbByRole, true);
   }
      		
   public StatMbByRole affectStatMbByRoleUseCache (StatMbByRole statMbByRole) {
      return referStatMbByRoleUseCache (statMbByRole, false);
   }
      		
   public StatMbByRole referStatMbByRoleUseCache (StatMbByRole statMbByRole, boolean isAssign) {
	  String key = getCacheKey(null, statMbByRole, null, "assignStatMbByRole");
      StatMbByRole statMbByRoleCache = (StatMbByRole)simpleCache.get(key);
      if (statMbByRoleCache==null) {
         statMbByRoleCache = referStatMbByRole (statMbByRole, isAssign);
         if (key!=null)
         	simpleCache.put(key, statMbByRoleCache);
      }
      statMbByRole.copy(statMbByRoleCache);
      return statMbByRoleCache;
   }	

	private String getCacheKey (StatMbByRole statMbByRoleWhat, StatMbByRole positiveStatMbByRole, StatMbByRole negativeStatMbByRole, String queryKey) {
	    StringBuffer sb = new StringBuffer();
	    sb.append(queryKey);
	    if (statMbByRoleWhat!=null)
	       sb.append(statMbByRoleWhat.toStringWithParents());
	    if (positiveStatMbByRole!=null)
	       sb.append(positiveStatMbByRole.toStringWithParents());
	    if (negativeStatMbByRole!=null)
	       sb.append(negativeStatMbByRole.toStringWithParents());
	    return sb.toString();
	}
	
    public StatMbByRole partialLoadWithParentFirstStatMbByRole(StatMbByRole statMbByRoleWhat, StatMbByRole positiveStatMbByRole, StatMbByRole negativeStatMbByRole){
		List <StatMbByRole> list = partialLoadWithParentStatMbByRole(statMbByRoleWhat, positiveStatMbByRole, negativeStatMbByRole);
		return (!list.isEmpty())?(StatMbByRole)list.get(0):null;
    }
    
    public StatMbByRole partialLoadWithParentFirstStatMbByRoleUseCache(StatMbByRole statMbByRoleWhat, StatMbByRole positiveStatMbByRole, StatMbByRole negativeStatMbByRole, Boolean useCache){
		List <StatMbByRole> list = partialLoadWithParentStatMbByRoleUseCache(statMbByRoleWhat, positiveStatMbByRole, negativeStatMbByRole, useCache);
		return (!list.isEmpty())?(StatMbByRole)list.get(0):null;
    }
	
	public StatMbByRole partialLoadWithParentFirstStatMbByRoleUseCacheOnResult(StatMbByRole statMbByRoleWhat, StatMbByRole positiveStatMbByRole, StatMbByRole negativeStatMbByRole, Boolean useCache){
		List <StatMbByRole> list = partialLoadWithParentStatMbByRoleUseCacheOnResult(statMbByRoleWhat, positiveStatMbByRole, negativeStatMbByRole, useCache);
		return (!list.isEmpty())?(StatMbByRole)list.get(0):null;
    }
	//
	protected List<StatMbByRole> partialLoadWithParentStatMbByRole(StatMbByRole statMbByRoleWhat, StatMbByRole positiveStatMbByRole, StatMbByRole negativeStatMbByRole, Integer nbOfResult, Boolean useCache) {
		 return partialLoadWithParentStatMbByRole(statMbByRoleWhat, positiveStatMbByRole, negativeStatMbByRole, new QuerySelectInit(), nbOfResult, useCache);
	}	  

	protected List partialLoadWithParentStatMbByRoleQueryResult (StatMbByRole statMbByRoleWhat, StatMbByRole positiveStatMbByRole, StatMbByRole negativeStatMbByRole, Integer nbOfResult, Boolean useCache) {
		 return partialLoadWithParentStatMbByRoleQueryResult (statMbByRoleWhat, positiveStatMbByRole, negativeStatMbByRole, new QuerySelectInit(), nbOfResult, useCache);
	}	
    
    public List<StatMbByRole> getDistinctStatMbByRole(StatMbByRole statMbByRoleWhat, StatMbByRole positiveStatMbByRole, StatMbByRole negativeStatMbByRole) {
		 return partialLoadWithParentStatMbByRole(statMbByRoleWhat, positiveStatMbByRole, negativeStatMbByRole, new QuerySelectDistinctInit(), null, false);
	}
	
	public List<StatMbByRole> partialLoadWithParentStatMbByRole(StatMbByRole statMbByRoleWhat, StatMbByRole positiveStatMbByRole, StatMbByRole negativeStatMbByRole) {
		 return partialLoadWithParentStatMbByRole(statMbByRoleWhat, positiveStatMbByRole, negativeStatMbByRole, new QuerySelectInit(), null, false);
	}	
  
	public List<StatMbByRole> partialLoadWithParentStatMbByRoleUseCacheOnResult(StatMbByRole statMbByRoleWhat, StatMbByRole positiveStatMbByRole, StatMbByRole negativeStatMbByRole, Boolean useCache) {
		String key = getCacheKey(statMbByRoleWhat, positiveStatMbByRole, negativeStatMbByRole, "partialLoadWithParentStatMbByRole");
		List<StatMbByRole> list = (List<StatMbByRole>)simpleCache.get(key);
		if (list==null || list.isEmpty()) {
			list = partialLoadWithParentStatMbByRole(statMbByRoleWhat, positiveStatMbByRole, negativeStatMbByRole);
			if (!list.isEmpty())
			   simpleCache.put(key, list);
		}
		return list;	
	}	

	public List<StatMbByRole> partialLoadWithParentStatMbByRoleUseCache(StatMbByRole statMbByRoleWhat, StatMbByRole positiveStatMbByRole, StatMbByRole negativeStatMbByRole, Boolean useCache) {
		String key = getCacheKey(statMbByRoleWhat, positiveStatMbByRole, negativeStatMbByRole, "partialLoadWithParentStatMbByRole");
		List<StatMbByRole> list = (List<StatMbByRole>)simpleCache.get(key);
		if (list==null) {
			list = partialLoadWithParentStatMbByRole(statMbByRoleWhat, positiveStatMbByRole, negativeStatMbByRole);
			simpleCache.put(key, list);
		}
		return list;	
	}	
	
	private List<StatMbByRole> handleLoadWithParentStatMbByRole(Map beanPath, List list, StatMbByRole statMbByRoleWhat) {
	    return handleLoadWithParentStatMbByRole(beanPath, list, statMbByRoleWhat, true);  
	}
	
	private List<StatMbByRole> handleLoadWithParentStatMbByRole(Map beanPath, List list, StatMbByRole statMbByRoleWhat, boolean isHql) {
		if (beanPath.size()==1) {
			return handlePartialLoadWithParentStatMbByRoleWithOneElementInRow(list, beanPath, statMbByRoleWhat, isHql);
		}
		return handlePartialLoadWithParentStatMbByRole(list, beanPath, statMbByRoleWhat, isHql);	
	}
	
    	// to set in super class	
	protected void populateStatMbByRole (StatMbByRole statMbByRole, Object value, String beanPath) throws IllegalAccessException, InvocationTargetException, NoSuchMethodException {
	   BeanUtils.populateBeanObject(statMbByRole, beanPath, value);
	}
	
	protected void populateStatMbByRoleFromSQL (StatMbByRole statMbByRole, Object value, String beanPath) throws IllegalAccessException, InvocationTargetException, NoSuchMethodException {
	   BeanUtils.populateBeanObject(statMbByRole, beanPath, value);
	}
    	// to set in super class BEWARE: genericity is only one level!!!!! first level is a copy second level is a reference!!! change to statMbByRole.clone() instead
	private StatMbByRole cloneStatMbByRole (StatMbByRole statMbByRole) throws IllegalAccessException, InvocationTargetException, InstantiationException, NoSuchMethodException {
		//return (StatMbByRole) BeanUtils.cloneBeanObject(statMbByRole);
	   if (statMbByRole==null) return new StatMbByRole();
	   return statMbByRole.clone();
	}
    
    // to set in super class
	private Object getBeanObjectInstance (Object bean) throws SecurityException, NoSuchMethodException, IllegalArgumentException, InstantiationException, IllegalAccessException, InvocationTargetException {
       return BeanUtils.getBeanObjectInstance(bean);	
	}
	// to set in super class
	protected void populateObject (Object bean, Object value, String beanPath) throws IllegalAccessException, InvocationTargetException {
       BeanUtils.populateObject(bean, value, beanPath);
	}
	
	protected void populateObjectFromSQL (Object bean, Object value, String beanPath) throws IllegalAccessException, InvocationTargetException {
       BeanUtils.populateObject(bean, value, beanPath);
	}
	
    public List<StatMbByRole> countDistinct (StatMbByRole whatMask, StatMbByRole whereEqCriteria) {
       return partialLoadWithParentStatMbByRole(whatMask, whereEqCriteria, null, new QuerySelectCountInit("statMbByRole"), null, false);
    }   
    	
    public Long count(StatMbByRole whereEqCriteria) {
        List<StatMbByRole> list = partialLoadWithParentStatMbByRole(null, whereEqCriteria, null, new QueryCountInit("statMbByRole"), null, false);    
    	if (!list.isEmpty())
    		return list.get(0).getCount__();
    	return 0L;
    }
		
		  		
   public StatMbByRole getFirstStatMbByRoleWhereConditionsAre (StatMbByRole statMbByRole) {
      List<StatMbByRole> list = partialLoadWithParentStatMbByRole(getDefaultStatMbByRoleWhat(), statMbByRole, null, 1, false);
      if (list.isEmpty()) {
         return null;
      }
      else if (list.size()==1)
         return list.get(0);
      else 
      //TODO log error
         return list.get(0);	
	}

   private List getFirstResultWhereConditionsAre (StatMbByRole statMbByRole) {
      return  partialLoadWithParentStatMbByRoleQueryResult(getDefaultStatMbByRoleWhat(), statMbByRole, null, 1, false);	
   }
   
   protected StatMbByRole getDefaultStatMbByRoleWhat() {
      StatMbByRole statMbByRole = new StatMbByRole();
      statMbByRole.setId("");
      return statMbByRole;
   }
   
	public StatMbByRole getFirstStatMbByRole (StatMbByRole statMbByRole) {
		if (isAllNull(statMbByRole))
			return null;
		else {
			List<StatMbByRole> list = searchPrototype (statMbByRole, 1);
			if (list.isEmpty()) {
				return null;
			}
			else if (list.size()==1)
				return list.get(0);
			else 
				//TODO log error
				return list.get(0);	
		}
	}
	
    /**
    * checks if the StatMbByRole entity exists
    */           
    public boolean existsStatMbByRole (StatMbByRole statMbByRole) {
       if (getFirstStatMbByRole(statMbByRole)!=null)
          return true;
       return false;  
    }
        
    public boolean existsStatMbByRoleWhereConditionsAre (StatMbByRole statMbByRole) {
       if (getFirstResultWhereConditionsAre (statMbByRole).isEmpty())
          return false;
       return true;  
    }


	
	private int countPartialField (StatMbByRole statMbByRole) {
	   int cpt = 0;
       if (statMbByRole.getId() != null) {
          cpt++;
       }
       if (statMbByRole.getStatMbPerCtryConfId() != null) {
          cpt++;
       }
       if (statMbByRole.getRoleName() != null) {
          cpt++;
       }
       if (statMbByRole.getNumber() != null) {
          cpt++;
       }
       return cpt;
	}   
  	
	public List<StatMbByRole> partialLoadWithParentStatMbByRole(StatMbByRole statMbByRoleWhat, StatMbByRole positiveStatMbByRole, StatMbByRole negativeStatMbByRole, QueryWhatInit queryWhatInit, Integer nbOfResult, Boolean useCache) {
		Map beanPath = new Hashtable();
		List list = partialLoadWithParentStatMbByRoleJPAQueryResult (statMbByRoleWhat, positiveStatMbByRole, negativeStatMbByRole, queryWhatInit, beanPath, nbOfResult, useCache);
		if (beanPath.size()==1) {
			return handlePartialLoadWithParentStatMbByRoleWithOneElementInRow(list, beanPath, statMbByRoleWhat, true);
		}
		return handlePartialLoadWithParentStatMbByRole(list, beanPath, statMbByRoleWhat, true);
	}	

	private List partialLoadWithParentStatMbByRoleQueryResult(StatMbByRole statMbByRoleWhat, StatMbByRole positiveStatMbByRole, StatMbByRole negativeStatMbByRole, QueryWhatInit queryWhatInit, Integer nbOfResult, Boolean useCache) {
		return partialLoadWithParentStatMbByRoleJPAQueryResult (statMbByRoleWhat, positiveStatMbByRole, negativeStatMbByRole, queryWhatInit, new Hashtable(), nbOfResult, useCache);
  }	
  
	private List partialLoadWithParentStatMbByRoleJPAQueryResult(StatMbByRole statMbByRoleWhat, StatMbByRole positiveStatMbByRole, StatMbByRole negativeStatMbByRole, QueryWhatInit queryWhatInit, Map beanPath, Integer nbOfResult, Boolean useCache) {
		Query hquery = getPartialLoadWithParentStatMbByRoleJPAQuery (statMbByRoleWhat, positiveStatMbByRole, negativeStatMbByRole, beanPath, queryWhatInit, nbOfResult);
		return hquery.getResultList();
  }	
   /**
    * @returns an JPA Hsql query based on entity StatMbByRole and its parents and the maximum number of result
    */
	protected Query getPartialLoadWithParentStatMbByRoleJPAQuery (StatMbByRole statMbByRoleWhat, StatMbByRole positiveStatMbByRole, StatMbByRole negativeStatMbByRole, Map beanPath, QueryWhatInit queryWhatInit, Integer nbOfResult) {
	   Query query = getEntityManager().createQuery(getPartialLoadWithParentStatMbByRoleHsqlQuery (statMbByRoleWhat, positiveStatMbByRole, negativeStatMbByRole, beanPath, queryWhatInit));
	   if (nbOfResult!=null)
	      query.setMaxResults(nbOfResult);
	   return query;
  }
  
	private List<StatMbByRole> handlePartialLoadWithParentStatMbByRole(List<Object[]> list, Map<Integer, String> beanPath, StatMbByRole statMbByRoleWhat, boolean isJql) {
		try {
			return convertPartialLoadWithParentStatMbByRole(list, beanPath, statMbByRoleWhat);
		} catch (Exception ex) {
			log.error("Error conversion list from handlePartialLoadWithParentStatMbByRole, message:"+ex.getMessage());
			return new ArrayList<StatMbByRole>();
		}
    }

	private List<StatMbByRole> handlePartialLoadWithParentStatMbByRoleWithOneElementInRow(List<Object> list, Map<Integer, String> beanPath, StatMbByRole statMbByRoleWhat, boolean isJql) {
		try {
			return convertPartialLoadWithParentStatMbByRoleWithOneElementInRow(list, beanPath, statMbByRoleWhat);
		} catch (Exception ex) {
			log.error("Error conversion list from handlePartialLoadWithParentStatMbByRoleWithOneElementInRow, message:"+ex.getMessage());
			return new ArrayList<StatMbByRole>();
		}
    }
    	
	 private List<StatMbByRole> convertPartialLoadWithParentStatMbByRole(List<Object[]> list, Map<Integer, String> beanPath, StatMbByRole statMbByRoleWhat) throws IllegalAccessException, InvocationTargetException, InstantiationException, NoSuchMethodException {
		 List<StatMbByRole> resultList = new ArrayList<StatMbByRole>();
		 for (Object[] row : list) {		
		    StatMbByRole statMbByRole = cloneStatMbByRole (statMbByRoleWhat);
		    Iterator<Entry<Integer, String>> iter = beanPath.entrySet().iterator();	
		    while (iter.hasNext()) {
		       Entry entry = iter.next();
		       populateStatMbByRole (statMbByRole, row[(Integer)entry.getKey()], (String)entry.getValue());
		    }
		    resultList.add(statMbByRole);
		 }
		 return resultList;		
	 }	
    
	 private List<StatMbByRole> convertPartialLoadWithParentStatMbByRoleWithOneElementInRow(List<Object> list, Map<Integer, String> beanPath, StatMbByRole statMbByRoleWhat) throws IllegalAccessException, InvocationTargetException, InstantiationException, NoSuchMethodException {
		 List<StatMbByRole> resultList = new ArrayList<StatMbByRole>();
		 for (Object row : list) {		
		    StatMbByRole statMbByRole = cloneStatMbByRole (statMbByRoleWhat);
		    Iterator<Entry<Integer, String>> iter = beanPath.entrySet().iterator();	
		    while (iter.hasNext()) {
		       Entry entry = iter.next();
		       populateStatMbByRole (statMbByRole, row, (String)entry.getValue());
		    }
		    resultList.add(statMbByRole);
		 }		 
		 return resultList;		
	 }
   
	public List partialLoadWithParentForBean(Object bean, StatMbByRole statMbByRoleWhat, StatMbByRole positiveStatMbByRole, StatMbByRole negativeStatMbByRole) {
		Map beanPath = new Hashtable();
		Query hquery = getPartialLoadWithParentStatMbByRoleJPAQuery (statMbByRoleWhat, positiveStatMbByRole, negativeStatMbByRole, beanPath, new QuerySelectInit(), null);
        List<Object[]> list = hquery.getResultList();
		return handlePartialLoadWithParentForBean(list, beanPath, bean);
    }	
//	 to set in super class
	private List handlePartialLoadWithParentForBean(List<Object[]> list, Map<Integer, String> beanPath, Object bean) {
		try {
			return convertPartialLoadWithParentForBean(list, beanPath, bean);
		} catch (Exception ex) {
			//TODO log ex
			return new ArrayList();
		}
    }
	// to set in super class
	private List convertPartialLoadWithParentForBean(List<Object[]> list, Map<Integer, String> beanPath, Object bean) throws IllegalAccessException, InvocationTargetException, InstantiationException, NoSuchMethodException {
		List resultList = new ArrayList();
		for (Object[] row : list) {		
		   Object result = getBeanObjectInstance(bean);
		   Iterator<Entry<Integer, String>> iter = beanPath.entrySet().iterator();	
		   while (iter.hasNext()) {
			  Entry entry = iter.next();
			  populateObject (result, row[(Integer)entry.getKey()], getFieldFromBeanPath((String)entry.getValue()));
			}
			resultList.add(result);
		}
		return resultList;		
    }	
	

	
	// to set in super class
	private String getFieldFromBeanPath(String beanPath) {
	   String result = StringUtils.substringAfterLast(beanPath, ".");
	   if (result.equals(""))
		 return beanPath;
	   return result;
	}

   /**
	  * 
    * partial on entity and its parents load enables to specify the fields you want to load explicitly
    */
	public String getPartialLoadWithParentStatMbByRoleHsqlQuery (StatMbByRole statMbByRole, StatMbByRole positiveStatMbByRole, StatMbByRole negativeStatMbByRole, Map beanPath, QueryWhatInit queryWhatInit) {
		Hashtable aliasWhatHt = new Hashtable();
		String what = getPartialLoadWithParentStatMbByRoleQuery (statMbByRole, null, aliasWhatHt, null, null, beanPath, "", queryWhatInit);
		Hashtable aliasWhereHt = new Hashtable();
		String where = getPartialLoadWithParentWhereStatMbByRoleQuery (positiveStatMbByRole, null, aliasWhatHt, aliasWhereHt, null, null);
		String whereHow = reconciliateWherePath(aliasWhatHt, aliasWhereHt);
		String how = reconciliateHowPath(aliasWhatHt, aliasWhereHt);
		String whereConcat = "";
		if (where!=null && !where.equals("") && whereHow!=null && !whereHow.equals(""))
			whereConcat=" AND ";
		return what+" FROM "+how +" WHERE "+whereHow+ whereConcat +where;
	}
    
	/**
    * partial on a single entity load enables to specify the fields you want to load explicitly
    */         
	public List<StatMbByRole> partialLoadStatMbByRole(StatMbByRole statMbByRole, StatMbByRole positiveStatMbByRole, StatMbByRole negativeStatMbByRole) {
	    Query hquery = getEntityManager().createQuery(getPartialLoadStatMbByRoleQuery (statMbByRole, positiveStatMbByRole, negativeStatMbByRole));
		int countPartialField = countPartialField(statMbByRole);
		if (countPartialField==0) 
		   return new ArrayList<StatMbByRole>();
		List list = hquery.getResultList();
		Iterator iter = list.iterator();
		List<StatMbByRole> returnList = new ArrayList<StatMbByRole>();
		while(iter.hasNext()) {
	       int index = 0;
	       Object[] row;
	       if (countPartialField==1) {
	    	  row = new Object[1];
	    	  row[0] = iter.next();
	       } else 
		      row = (Object[]) iter.next();
		   StatMbByRole statMbByRoleResult = new StatMbByRole();
           if (statMbByRole.getId() != null) {
			  statMbByRoleResult.setId((String) row[index]);
			  index++;
           }
           if (statMbByRole.getStatMbPerCtryConfId() != null) {
			  statMbByRoleResult.setStatMbPerCtryConfId((String) row[index]);
			  index++;
           }
           if (statMbByRole.getRoleName() != null) {
			  statMbByRoleResult.setRoleName((String) row[index]);
			  index++;
           }
           if (statMbByRole.getNumber() != null) {
			  statMbByRoleResult.setNumber((Long) row[index]);
			  index++;
           }
           returnList.add(statMbByRoleResult);
        }
	    return returnList;
	}

	public static String getPartialLoadWithParentWhereStatMbByRoleQuery (
	   StatMbByRole statMbByRole, Boolean isWhereSet, Hashtable aliasHt, Hashtable aliasWhereHt, String childAlias, String childFKAlias) {
	   if (statMbByRole==null)
	      return "";
	   String alias = null;
	   if (aliasWhereHt == null) {
	      aliasWhereHt = new Hashtable();
	   } 
	   if (isLookedUp(statMbByRole)){
	      alias = getNextAlias (aliasWhereHt, statMbByRole);
		  aliasWhereHt.put (getAliasKey(alias), getAliasConnection(alias, childAlias, childFKAlias));
	   }
	   if (isWhereSet == null)
          isWhereSet = false;
       StringBuffer query = new StringBuffer();
       if (statMbByRole.getId() != null) {
           query.append (getQueryBLANK_AND (isWhereSet));
		   isWhereSet = true;
           query.append(" "+alias+".id = '"+ statMbByRole.getId()+"' ");
       }
       if (statMbByRole.getStatMbPerCtryConfId() != null) {
           query.append (getQueryBLANK_AND (isWhereSet));
		   isWhereSet = true;
           query.append(" "+alias+".statMbPerCtryConfId = '"+ statMbByRole.getStatMbPerCtryConfId()+"' ");
       }
       if (statMbByRole.getRoleName() != null) {
           query.append (getQueryBLANK_AND (isWhereSet));
		   isWhereSet = true;
           query.append(" "+alias+".roleName = '"+ statMbByRole.getRoleName()+"' ");
       }
       if (statMbByRole.getNumber() != null) {
           query.append (getQueryBLANK_AND (isWhereSet));
		   isWhereSet = true;
           query.append(" "+alias+".number = "+ statMbByRole.getNumber() + " ");
       }
	   return query.toString(); 
    }
	
	public static String reconciliateWherePath(Hashtable aliasWhatHt, Hashtable aliasWhereHt) {
	   StringBuffer sb = new StringBuffer();
	   boolean isBlankSet = false;
	   aliasWhatHt.putAll(aliasWhereHt);
	   Enumeration<String> elements = aliasWhatHt.elements();
	   while (elements.hasMoreElements()) {
		  String element = elements.nextElement();
		  if (!element.equals("")) {
		     sb.append (getQueryBLANK_AND (isBlankSet));
		     isBlankSet=true;
		     sb.append(element);
		  }
	   }
	   return sb.toString();
	}
	
	public static String reconciliateHowPath(Hashtable aliasWhatHt, Hashtable aliasWhereHt) {
	   StringBuffer sb = new StringBuffer();
	   boolean isBlankSet = false;
	   aliasWhatHt.putAll(aliasWhereHt);
	   Enumeration<String> keys = aliasWhatHt.keys();
	   while (keys.hasMoreElements()) {
		  String key = keys.nextElement();
		  sb.append (getQueryBLANK_COMMA (isBlankSet));
		  isBlankSet = true;
		  sb.append(getAliasKeyDomain(key)+" "+getAliasKeyAlias(key));
	   }
	   return sb.toString();
	}
	
	protected static String getRootDomainName (String domainName) {
		return StringUtils.substringBefore(domainName, "_");
	}
	
    public static String getPartialLoadWithParentStatMbByRoleQuery (
	   StatMbByRole statMbByRole, Boolean isWhereSet, Hashtable aliasHt, String childAlias, String childFKAlias, Map beanPath, String rootPath, QueryWhatInit queryWhatInit) {
	   if (statMbByRole==null)
	      return "";
	   String alias = null;
	   if (aliasHt == null) {
	      aliasHt = new Hashtable();
	   } 
	   if (isLookedUp(statMbByRole)){
	      alias = getNextAlias (aliasHt, statMbByRole);
		  aliasHt.put (getAliasKey(alias), getAliasConnection(alias, childAlias, childFKAlias));
	   }
	   if (isWhereSet == null)
          isWhereSet = false;
       StringBuffer query = new StringBuffer();
       if (statMbByRole.getId() != null) {
          query.append (queryWhatInit.getWhatInit (isWhereSet));
          isWhereSet = true; 
          beanPath.put(beanPath.size(), rootPath+"id");
          query.append(" "+alias+".id ");
       }
       if (statMbByRole.getStatMbPerCtryConfId() != null) {
          query.append (queryWhatInit.getWhatInit (isWhereSet));
          isWhereSet = true; 
          beanPath.put(beanPath.size(), rootPath+"statMbPerCtryConfId");
          query.append(" "+alias+".statMbPerCtryConfId ");
       }
       if (statMbByRole.getRoleName() != null) {
          query.append (queryWhatInit.getWhatInit (isWhereSet));
          isWhereSet = true; 
          beanPath.put(beanPath.size(), rootPath+"roleName");
          query.append(" "+alias+".roleName ");
       }
       if (statMbByRole.getNumber() != null) {
          query.append (queryWhatInit.getWhatInit (isWhereSet));
          isWhereSet = true; 
          beanPath.put(beanPath.size(), rootPath+"number");
          query.append(" "+alias+".number ");
       }
//       query.append(getStatMbByRoleSearchEqualQuery (positiveStatMbByRole, negativeStatMbByRole));
	   return query.toString(); 
    }
	
	protected static String getAliasConnection(String existingAlias, String childAlias, String childFKAlias) {
		if (childAlias==null)
		   return "";
		return childAlias+"."+childFKAlias+" = "+existingAlias+"."+"id";
	}
	
	protected static String getAliasKey (String alias) {
	  //TODO this is a temporary solution use a dedicated object in BslaHiberateDaoSupport
		return "StatMbByRole|"+alias;
	}
	
	protected static String getAliasKeyAlias (String aliasKey) {
	  //TODO this is a temporary solution use a dedicated object in BslaHiberateDaoSupport
		return StringUtils.substringAfter(aliasKey, "|");
	}
	
	protected static String getAliasKeyDomain (String aliasKey) {
	  //TODO this is a temporary solution use a dedicated object in BslaHiberateDaoSupport
	  return StringUtils.substringBefore(aliasKey, "|");
	}
	
	protected static String getNextAlias (Hashtable aliasHt, StatMbByRole statMbByRole) {
		int cptSameAlias = 0;
		Enumeration<String> keys = aliasHt.keys();
		while (keys.hasMoreElements()) {
			String key = keys.nextElement();
			if (key.startsWith("statMbByRole"))
				cptSameAlias++;
		}
		if (cptSameAlias==0)
			return "statMbByRole";
		else
			return "statMbByRole_"+cptSameAlias;
	}
	
	
	protected static boolean isLookedUp (StatMbByRole statMbByRole) {
	   if (statMbByRole==null)
		  return false;
       if (statMbByRole.getId() != null) {
	      return true;
       }
       if (statMbByRole.getStatMbPerCtryConfId() != null) {
	      return true;
       }
       if (statMbByRole.getRoleName() != null) {
	      return true;
       }
       if (statMbByRole.getNumber() != null) {
	      return true;
       }
       return false;   
	}
	
    public String getPartialLoadStatMbByRoleQuery(
	   StatMbByRole statMbByRole, 
	   StatMbByRole positiveStatMbByRole, 
	   StatMbByRole negativeStatMbByRole) {
       boolean isWhereSet = false;
       StringBuffer query = new StringBuffer();
       if (statMbByRole.getId() != null) {
          query.append (getQuerySelectComma (isWhereSet));
          isWhereSet = true; 
          query.append(" id ");
       }
       if (statMbByRole.getStatMbPerCtryConfId() != null) {
          query.append (getQuerySelectComma (isWhereSet));
          isWhereSet = true; 
          query.append(" statMbPerCtryConfId ");
       }
       if (statMbByRole.getRoleName() != null) {
          query.append (getQuerySelectComma (isWhereSet));
          isWhereSet = true; 
          query.append(" roleName ");
       }
       if (statMbByRole.getNumber() != null) {
          query.append (getQuerySelectComma (isWhereSet));
          isWhereSet = true; 
          query.append(" number ");
       }
       query.append(getStatMbByRoleSearchEqualQuery (positiveStatMbByRole, negativeStatMbByRole));
	   return query.toString(); 
    }
	
	public List<StatMbByRole> searchPrototypeWithCacheStatMbByRole(StatMbByRole statMbByRole) {
		SimpleCache simpleCache = new SimpleCache();
		List<StatMbByRole> list = (List<StatMbByRole>)simpleCache.get(statMbByRole.toString());
		if (list==null) {
			list = searchPrototypeStatMbByRole(statMbByRole);
			simpleCache.put(statMbByRole.toString(), list);
		}
		return list;
	}

    public List<StatMbByRole> loadGraph(StatMbByRole graphMaskWhat, List<StatMbByRole> whereMask) {
        return loadGraphOneLevel(graphMaskWhat, whereMask);
    }
 
    public List<StatMbByRole> loadGraphOneLevel(StatMbByRole graphMaskWhat, List<StatMbByRole> whereMask) {
        //first get roots element from where list mask
        // this brings the level 0 of the graph (root level)
        graphMaskWhat.setId(graphMaskWhat.stringMask__);
        List<StatMbByRole> statMbByRoles = searchPrototypeStatMbByRole (whereMask);
        // for each sub level perform the search with a subquery then reassemble
        // 1. get all sublevel queries
        // 2. perform queries on the correct dao
        // 3. reassemble
        return getLoadGraphOneLevel (graphMaskWhat, statMbByRoles);
    }

	private List<StatMbByRole> copy(List<StatMbByRole> inputs) {
		List<StatMbByRole> l = new ArrayList<StatMbByRole>();
		for (StatMbByRole input : inputs) {
			StatMbByRole copy = new StatMbByRole();
			copy.copy(input);
			l.add(copy);
		}
		return l;
	}
	   
	private List<StatMbByRole> getLoadGraphOneLevel (StatMbByRole graphMaskWhat, List<StatMbByRole> parents) {
	    return loadGraphFromParentKey (graphMaskWhat, parents);
	} 
	
	public List<StatMbByRole> loadGraphFromParentKey (StatMbByRole graphMaskWhat, List<StatMbByRole> parents) {
		//foreach children:
		//check if not empty take first
		parents = copy (parents); //working with detached entities to avoid unnecessary sql calls
		if (parents==null || parents.isEmpty())
		   return parents;
		List<String> ids = getPk (parents);
		return parents;
	}
	
	private List<String> getPk(List<StatMbByRole> input) {
		List<String> s = new ArrayList<String>();
		for (StatMbByRole t : input) {
			s.add(t.getId()+"");
		}
		return s;
	}

	private Criteria getFkCriteria(String field, List<String> ids) {
		Criteria criteria = new Criteria();
		criteria.addCriterion(getInPk(field, ids));
		return criteria;
	}

	private ClauseCriterion getInPk(String field, List<String> ids) {
		InCriterion inCriterion = new InCriterion(field, ids, true);
		return inCriterion;
	}	    
    
    
    public EntityManager getEntityManagerForRecursiveDao() {
		return emForRecursiveDao;
	}

	public void setEntityManagerForRecursiveDao(EntityManager emForRecursiveDao) {
		this.emForRecursiveDao = emForRecursiveDao;
	}
	
    
}
