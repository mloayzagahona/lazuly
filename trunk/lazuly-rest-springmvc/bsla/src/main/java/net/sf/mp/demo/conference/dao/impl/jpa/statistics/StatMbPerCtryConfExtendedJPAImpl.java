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
import net.sf.mp.demo.conference.dao.face.statistics.StatMbPerCtryConfExtDao;
import net.sf.mp.demo.conference.domain.statistics.StatMbPerCtryConf;
import net.sf.mp.demo.conference.dao.impl.jpa.statistics.StatMbPerCtryConfJPAImpl;

/**
 *
 * <p>Title: StatMbPerCtryConfExtendedJPAImpl</p>
 *
 * <p>Description: Interface of a Data access object dealing with StatMbPerCtryConfExtendedJPAImpl
 * persistence. It offers a set of methods which allow for saving,
 * deleting and searching StatMbPerCtryConfExtendedJPAImpl objects</p>
 *
 */

public class StatMbPerCtryConfExtendedJPAImpl extends StatMbPerCtryConfJPAImpl implements StatMbPerCtryConfExtDao {

    private Logger log = Logger.getLogger(this.getClass());
    
    private SimpleCache simpleCache = new SimpleCache();
    private EntityManager emForRecursiveDao; // dao that needs other dao in a recursive manner not support by spring configuration

    public StatMbPerCtryConfExtendedJPAImpl () {}

    /**
     * generic to move after in superclass
     */
    public StatMbPerCtryConfExtendedJPAImpl (EntityManager emForRecursiveDao) {
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
     * Inserts a StatMbPerCtryConf entity with cascade of its children
     * @param StatMbPerCtryConf statMbPerCtryConf
     */
    public void insertStatMbPerCtryConfWithCascade(StatMbPerCtryConf statMbPerCtryConf) {
    	StatMbPerCtryConfExtendedJPAImpl statmbperctryconfextendedjpaimpl = new StatMbPerCtryConfExtendedJPAImpl(getEntityManager());
    	statmbperctryconfextendedjpaimpl.insertStatMbPerCtryConfWithCascade(statmbperctryconfextendedjpaimpl.getEntityManagerForRecursiveDao(), statMbPerCtryConf);
    }
     
    public void insertStatMbPerCtryConfWithCascade(EntityManager emForRecursiveDao, StatMbPerCtryConf statMbPerCtryConf) {
       insertStatMbPerCtryConf(emForRecursiveDao, statMbPerCtryConf);
    }
        
    /**
     * Inserts a list of StatMbPerCtryConf entity with cascade of its children
     * @param List<StatMbPerCtryConf> statMbPerCtryConfs
     */
    public void insertStatMbPerCtryConfsWithCascade(List<StatMbPerCtryConf> statMbPerCtryConfs) {
       for (StatMbPerCtryConf statMbPerCtryConf : statMbPerCtryConfs) {
          insertStatMbPerCtryConfWithCascade(statMbPerCtryConf);
       }
    } 
        
    /**
     * lookup StatMbPerCtryConf entity StatMbPerCtryConf, criteria and max result number
     */
    public List<StatMbPerCtryConf> lookupStatMbPerCtryConf(StatMbPerCtryConf statMbPerCtryConf, Criteria criteria, Integer numberOfResult, EntityManager em) {
		boolean isWhereSet = false;
        StringBuffer query = new StringBuffer();
        query.append ("SELECT statMbPerCtryConf FROM StatMbPerCtryConf statMbPerCtryConf ");
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
    
    public List<StatMbPerCtryConf> lookupStatMbPerCtryConf(StatMbPerCtryConf statMbPerCtryConf, Criteria criteria, Integer numberOfResult) {
		return lookupStatMbPerCtryConf(statMbPerCtryConf, criteria, numberOfResult, getEntityManager());
    }

    public Integer updateNotNullOnlyStatMbPerCtryConf (StatMbPerCtryConf statMbPerCtryConf, Criteria criteria) {
        String queryWhat = getUpdateNotNullOnlyStatMbPerCtryConfQueryChunkPrototype (statMbPerCtryConf);
        StringBuffer query = new StringBuffer (queryWhat);
        boolean isWhereSet = false;
        for (Criterion criterion : criteria.getClauseCriterions()) {
            query.append (getQueryWHERE_AND (isWhereSet));
            isWhereSet = true;   
            query.append(criterion.getExpression());			
        }  
        Query jpaQuery = getEntityManager().createQuery(query.toString());
        isWhereSet = false;
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
	
	public StatMbPerCtryConf affectStatMbPerCtryConf (StatMbPerCtryConf statMbPerCtryConf) {
	    return referStatMbPerCtryConf (statMbPerCtryConf, false);		    
	}
		
	/**
	 * Assign the first statMbPerCtryConf retrieved corresponding to the statMbPerCtryConf criteria.
	 * Blank criteria are mapped to null.
	 * If no criteria is found, null is returned.
	 * If there is no statMbPerCtryConf corresponding in the database. Then statMbPerCtryConf is inserted and returned with its primary key(s). 
	 */
	public StatMbPerCtryConf assignStatMbPerCtryConf (StatMbPerCtryConf statMbPerCtryConf) {
	    return referStatMbPerCtryConf (statMbPerCtryConf, true);	
	}
	
	public StatMbPerCtryConf referStatMbPerCtryConf (StatMbPerCtryConf statMbPerCtryConf, boolean isAssign) {
		statMbPerCtryConf = assignBlankToNull (statMbPerCtryConf);
		if (isAllNull(statMbPerCtryConf))
			return null;
		else {
			List<StatMbPerCtryConf> list = searchPrototypeStatMbPerCtryConf(statMbPerCtryConf);
			if (list.isEmpty()) {
			    if (isAssign)
			       insertStatMbPerCtryConf(statMbPerCtryConf);
			    else
				   return null;
			}
			else if (list.size()==1)
				statMbPerCtryConf.copy(list.get(0));
			else 
				//TODO log error
				statMbPerCtryConf.copy(list.get(0));
		}
		return statMbPerCtryConf;		    
	}

   public StatMbPerCtryConf assignStatMbPerCtryConfUseCache (StatMbPerCtryConf statMbPerCtryConf) {
      return referStatMbPerCtryConfUseCache (statMbPerCtryConf, true);
   }
      		
   public StatMbPerCtryConf affectStatMbPerCtryConfUseCache (StatMbPerCtryConf statMbPerCtryConf) {
      return referStatMbPerCtryConfUseCache (statMbPerCtryConf, false);
   }
      		
   public StatMbPerCtryConf referStatMbPerCtryConfUseCache (StatMbPerCtryConf statMbPerCtryConf, boolean isAssign) {
	  String key = getCacheKey(null, statMbPerCtryConf, null, "assignStatMbPerCtryConf");
      StatMbPerCtryConf statMbPerCtryConfCache = (StatMbPerCtryConf)simpleCache.get(key);
      if (statMbPerCtryConfCache==null) {
         statMbPerCtryConfCache = referStatMbPerCtryConf (statMbPerCtryConf, isAssign);
         if (key!=null)
         	simpleCache.put(key, statMbPerCtryConfCache);
      }
      statMbPerCtryConf.copy(statMbPerCtryConfCache);
      return statMbPerCtryConfCache;
   }	

	private String getCacheKey (StatMbPerCtryConf statMbPerCtryConfWhat, StatMbPerCtryConf positiveStatMbPerCtryConf, StatMbPerCtryConf negativeStatMbPerCtryConf, String queryKey) {
	    StringBuffer sb = new StringBuffer();
	    sb.append(queryKey);
	    if (statMbPerCtryConfWhat!=null)
	       sb.append(statMbPerCtryConfWhat.toStringWithParents());
	    if (positiveStatMbPerCtryConf!=null)
	       sb.append(positiveStatMbPerCtryConf.toStringWithParents());
	    if (negativeStatMbPerCtryConf!=null)
	       sb.append(negativeStatMbPerCtryConf.toStringWithParents());
	    return sb.toString();
	}
	
    public StatMbPerCtryConf partialLoadWithParentFirstStatMbPerCtryConf(StatMbPerCtryConf statMbPerCtryConfWhat, StatMbPerCtryConf positiveStatMbPerCtryConf, StatMbPerCtryConf negativeStatMbPerCtryConf){
		List <StatMbPerCtryConf> list = partialLoadWithParentStatMbPerCtryConf(statMbPerCtryConfWhat, positiveStatMbPerCtryConf, negativeStatMbPerCtryConf);
		return (!list.isEmpty())?(StatMbPerCtryConf)list.get(0):null;
    }
    
    public StatMbPerCtryConf partialLoadWithParentFirstStatMbPerCtryConfUseCache(StatMbPerCtryConf statMbPerCtryConfWhat, StatMbPerCtryConf positiveStatMbPerCtryConf, StatMbPerCtryConf negativeStatMbPerCtryConf, Boolean useCache){
		List <StatMbPerCtryConf> list = partialLoadWithParentStatMbPerCtryConfUseCache(statMbPerCtryConfWhat, positiveStatMbPerCtryConf, negativeStatMbPerCtryConf, useCache);
		return (!list.isEmpty())?(StatMbPerCtryConf)list.get(0):null;
    }
	
	public StatMbPerCtryConf partialLoadWithParentFirstStatMbPerCtryConfUseCacheOnResult(StatMbPerCtryConf statMbPerCtryConfWhat, StatMbPerCtryConf positiveStatMbPerCtryConf, StatMbPerCtryConf negativeStatMbPerCtryConf, Boolean useCache){
		List <StatMbPerCtryConf> list = partialLoadWithParentStatMbPerCtryConfUseCacheOnResult(statMbPerCtryConfWhat, positiveStatMbPerCtryConf, negativeStatMbPerCtryConf, useCache);
		return (!list.isEmpty())?(StatMbPerCtryConf)list.get(0):null;
    }
	//
	protected List<StatMbPerCtryConf> partialLoadWithParentStatMbPerCtryConf(StatMbPerCtryConf statMbPerCtryConfWhat, StatMbPerCtryConf positiveStatMbPerCtryConf, StatMbPerCtryConf negativeStatMbPerCtryConf, Integer nbOfResult, Boolean useCache) {
		 return partialLoadWithParentStatMbPerCtryConf(statMbPerCtryConfWhat, positiveStatMbPerCtryConf, negativeStatMbPerCtryConf, new QuerySelectInit(), nbOfResult, useCache);
	}	  

	protected List partialLoadWithParentStatMbPerCtryConfQueryResult (StatMbPerCtryConf statMbPerCtryConfWhat, StatMbPerCtryConf positiveStatMbPerCtryConf, StatMbPerCtryConf negativeStatMbPerCtryConf, Integer nbOfResult, Boolean useCache) {
		 return partialLoadWithParentStatMbPerCtryConfQueryResult (statMbPerCtryConfWhat, positiveStatMbPerCtryConf, negativeStatMbPerCtryConf, new QuerySelectInit(), nbOfResult, useCache);
	}	
    
    public List<StatMbPerCtryConf> getDistinctStatMbPerCtryConf(StatMbPerCtryConf statMbPerCtryConfWhat, StatMbPerCtryConf positiveStatMbPerCtryConf, StatMbPerCtryConf negativeStatMbPerCtryConf) {
		 return partialLoadWithParentStatMbPerCtryConf(statMbPerCtryConfWhat, positiveStatMbPerCtryConf, negativeStatMbPerCtryConf, new QuerySelectDistinctInit(), null, false);
	}
	
	public List<StatMbPerCtryConf> partialLoadWithParentStatMbPerCtryConf(StatMbPerCtryConf statMbPerCtryConfWhat, StatMbPerCtryConf positiveStatMbPerCtryConf, StatMbPerCtryConf negativeStatMbPerCtryConf) {
		 return partialLoadWithParentStatMbPerCtryConf(statMbPerCtryConfWhat, positiveStatMbPerCtryConf, negativeStatMbPerCtryConf, new QuerySelectInit(), null, false);
	}	
  
	public List<StatMbPerCtryConf> partialLoadWithParentStatMbPerCtryConfUseCacheOnResult(StatMbPerCtryConf statMbPerCtryConfWhat, StatMbPerCtryConf positiveStatMbPerCtryConf, StatMbPerCtryConf negativeStatMbPerCtryConf, Boolean useCache) {
		String key = getCacheKey(statMbPerCtryConfWhat, positiveStatMbPerCtryConf, negativeStatMbPerCtryConf, "partialLoadWithParentStatMbPerCtryConf");
		List<StatMbPerCtryConf> list = (List<StatMbPerCtryConf>)simpleCache.get(key);
		if (list==null || list.isEmpty()) {
			list = partialLoadWithParentStatMbPerCtryConf(statMbPerCtryConfWhat, positiveStatMbPerCtryConf, negativeStatMbPerCtryConf);
			if (!list.isEmpty())
			   simpleCache.put(key, list);
		}
		return list;	
	}	

	public List<StatMbPerCtryConf> partialLoadWithParentStatMbPerCtryConfUseCache(StatMbPerCtryConf statMbPerCtryConfWhat, StatMbPerCtryConf positiveStatMbPerCtryConf, StatMbPerCtryConf negativeStatMbPerCtryConf, Boolean useCache) {
		String key = getCacheKey(statMbPerCtryConfWhat, positiveStatMbPerCtryConf, negativeStatMbPerCtryConf, "partialLoadWithParentStatMbPerCtryConf");
		List<StatMbPerCtryConf> list = (List<StatMbPerCtryConf>)simpleCache.get(key);
		if (list==null) {
			list = partialLoadWithParentStatMbPerCtryConf(statMbPerCtryConfWhat, positiveStatMbPerCtryConf, negativeStatMbPerCtryConf);
			simpleCache.put(key, list);
		}
		return list;	
	}	
	
	private List<StatMbPerCtryConf> handleLoadWithParentStatMbPerCtryConf(Map beanPath, List list, StatMbPerCtryConf statMbPerCtryConfWhat) {
	    return handleLoadWithParentStatMbPerCtryConf(beanPath, list, statMbPerCtryConfWhat, true);  
	}
	
	private List<StatMbPerCtryConf> handleLoadWithParentStatMbPerCtryConf(Map beanPath, List list, StatMbPerCtryConf statMbPerCtryConfWhat, boolean isHql) {
		if (beanPath.size()==1) {
			return handlePartialLoadWithParentStatMbPerCtryConfWithOneElementInRow(list, beanPath, statMbPerCtryConfWhat, isHql);
		}
		return handlePartialLoadWithParentStatMbPerCtryConf(list, beanPath, statMbPerCtryConfWhat, isHql);	
	}
	
    	// to set in super class	
	protected void populateStatMbPerCtryConf (StatMbPerCtryConf statMbPerCtryConf, Object value, String beanPath) throws IllegalAccessException, InvocationTargetException, NoSuchMethodException {
	   BeanUtils.populateBeanObject(statMbPerCtryConf, beanPath, value);
	}
	
	protected void populateStatMbPerCtryConfFromSQL (StatMbPerCtryConf statMbPerCtryConf, Object value, String beanPath) throws IllegalAccessException, InvocationTargetException, NoSuchMethodException {
	   BeanUtils.populateBeanObject(statMbPerCtryConf, beanPath, value);
	}
    	// to set in super class BEWARE: genericity is only one level!!!!! first level is a copy second level is a reference!!! change to statMbPerCtryConf.clone() instead
	private StatMbPerCtryConf cloneStatMbPerCtryConf (StatMbPerCtryConf statMbPerCtryConf) throws IllegalAccessException, InvocationTargetException, InstantiationException, NoSuchMethodException {
		//return (StatMbPerCtryConf) BeanUtils.cloneBeanObject(statMbPerCtryConf);
	   if (statMbPerCtryConf==null) return new StatMbPerCtryConf();
	   return statMbPerCtryConf.clone();
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
	
    public List<StatMbPerCtryConf> countDistinct (StatMbPerCtryConf whatMask, StatMbPerCtryConf whereEqCriteria) {
       return partialLoadWithParentStatMbPerCtryConf(whatMask, whereEqCriteria, null, new QuerySelectCountInit("statMbPerCtryConf"), null, false);
    }   
    	
    public Long count(StatMbPerCtryConf whereEqCriteria) {
        List<StatMbPerCtryConf> list = partialLoadWithParentStatMbPerCtryConf(null, whereEqCriteria, null, new QueryCountInit("statMbPerCtryConf"), null, false);    
    	if (!list.isEmpty())
    		return list.get(0).getCount__();
    	return 0L;
    }
		
		  		
   public StatMbPerCtryConf getFirstStatMbPerCtryConfWhereConditionsAre (StatMbPerCtryConf statMbPerCtryConf) {
      List<StatMbPerCtryConf> list = partialLoadWithParentStatMbPerCtryConf(getDefaultStatMbPerCtryConfWhat(), statMbPerCtryConf, null, 1, false);
      if (list.isEmpty()) {
         return null;
      }
      else if (list.size()==1)
         return list.get(0);
      else 
      //TODO log error
         return list.get(0);	
	}

   private List getFirstResultWhereConditionsAre (StatMbPerCtryConf statMbPerCtryConf) {
      return  partialLoadWithParentStatMbPerCtryConfQueryResult(getDefaultStatMbPerCtryConfWhat(), statMbPerCtryConf, null, 1, false);	
   }
   
   protected StatMbPerCtryConf getDefaultStatMbPerCtryConfWhat() {
      StatMbPerCtryConf statMbPerCtryConf = new StatMbPerCtryConf();
      statMbPerCtryConf.setId("");
      return statMbPerCtryConf;
   }
   
	public StatMbPerCtryConf getFirstStatMbPerCtryConf (StatMbPerCtryConf statMbPerCtryConf) {
		if (isAllNull(statMbPerCtryConf))
			return null;
		else {
			List<StatMbPerCtryConf> list = searchPrototype (statMbPerCtryConf, 1);
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
    * checks if the StatMbPerCtryConf entity exists
    */           
    public boolean existsStatMbPerCtryConf (StatMbPerCtryConf statMbPerCtryConf) {
       if (getFirstStatMbPerCtryConf(statMbPerCtryConf)!=null)
          return true;
       return false;  
    }
        
    public boolean existsStatMbPerCtryConfWhereConditionsAre (StatMbPerCtryConf statMbPerCtryConf) {
       if (getFirstResultWhereConditionsAre (statMbPerCtryConf).isEmpty())
          return false;
       return true;  
    }


	
	private int countPartialField (StatMbPerCtryConf statMbPerCtryConf) {
	   int cpt = 0;
       if (statMbPerCtryConf.getId() != null) {
          cpt++;
       }
       if (statMbPerCtryConf.getCountry() != null) {
          cpt++;
       }
       if (statMbPerCtryConf.getConferenceName() != null) {
          cpt++;
       }
       if (statMbPerCtryConf.getNumber() != null) {
          cpt++;
       }
       return cpt;
	}   
  	
	public List<StatMbPerCtryConf> partialLoadWithParentStatMbPerCtryConf(StatMbPerCtryConf statMbPerCtryConfWhat, StatMbPerCtryConf positiveStatMbPerCtryConf, StatMbPerCtryConf negativeStatMbPerCtryConf, QueryWhatInit queryWhatInit, Integer nbOfResult, Boolean useCache) {
		Map beanPath = new Hashtable();
		List list = partialLoadWithParentStatMbPerCtryConfJPAQueryResult (statMbPerCtryConfWhat, positiveStatMbPerCtryConf, negativeStatMbPerCtryConf, queryWhatInit, beanPath, nbOfResult, useCache);
		if (beanPath.size()==1) {
			return handlePartialLoadWithParentStatMbPerCtryConfWithOneElementInRow(list, beanPath, statMbPerCtryConfWhat, true);
		}
		return handlePartialLoadWithParentStatMbPerCtryConf(list, beanPath, statMbPerCtryConfWhat, true);
	}	

	private List partialLoadWithParentStatMbPerCtryConfQueryResult(StatMbPerCtryConf statMbPerCtryConfWhat, StatMbPerCtryConf positiveStatMbPerCtryConf, StatMbPerCtryConf negativeStatMbPerCtryConf, QueryWhatInit queryWhatInit, Integer nbOfResult, Boolean useCache) {
		return partialLoadWithParentStatMbPerCtryConfJPAQueryResult (statMbPerCtryConfWhat, positiveStatMbPerCtryConf, negativeStatMbPerCtryConf, queryWhatInit, new Hashtable(), nbOfResult, useCache);
  }	
  
	private List partialLoadWithParentStatMbPerCtryConfJPAQueryResult(StatMbPerCtryConf statMbPerCtryConfWhat, StatMbPerCtryConf positiveStatMbPerCtryConf, StatMbPerCtryConf negativeStatMbPerCtryConf, QueryWhatInit queryWhatInit, Map beanPath, Integer nbOfResult, Boolean useCache) {
		Query hquery = getPartialLoadWithParentStatMbPerCtryConfJPAQuery (statMbPerCtryConfWhat, positiveStatMbPerCtryConf, negativeStatMbPerCtryConf, beanPath, queryWhatInit, nbOfResult);
		return hquery.getResultList();
  }	
   /**
    * @returns an JPA Hsql query based on entity StatMbPerCtryConf and its parents and the maximum number of result
    */
	protected Query getPartialLoadWithParentStatMbPerCtryConfJPAQuery (StatMbPerCtryConf statMbPerCtryConfWhat, StatMbPerCtryConf positiveStatMbPerCtryConf, StatMbPerCtryConf negativeStatMbPerCtryConf, Map beanPath, QueryWhatInit queryWhatInit, Integer nbOfResult) {
	   Query query = getEntityManager().createQuery(getPartialLoadWithParentStatMbPerCtryConfHsqlQuery (statMbPerCtryConfWhat, positiveStatMbPerCtryConf, negativeStatMbPerCtryConf, beanPath, queryWhatInit));
	   if (nbOfResult!=null)
	      query.setMaxResults(nbOfResult);
	   return query;
  }
  
	private List<StatMbPerCtryConf> handlePartialLoadWithParentStatMbPerCtryConf(List<Object[]> list, Map<Integer, String> beanPath, StatMbPerCtryConf statMbPerCtryConfWhat, boolean isJql) {
		try {
			return convertPartialLoadWithParentStatMbPerCtryConf(list, beanPath, statMbPerCtryConfWhat);
		} catch (Exception ex) {
			log.error("Error conversion list from handlePartialLoadWithParentStatMbPerCtryConf, message:"+ex.getMessage());
			return new ArrayList<StatMbPerCtryConf>();
		}
    }

	private List<StatMbPerCtryConf> handlePartialLoadWithParentStatMbPerCtryConfWithOneElementInRow(List<Object> list, Map<Integer, String> beanPath, StatMbPerCtryConf statMbPerCtryConfWhat, boolean isJql) {
		try {
			return convertPartialLoadWithParentStatMbPerCtryConfWithOneElementInRow(list, beanPath, statMbPerCtryConfWhat);
		} catch (Exception ex) {
			log.error("Error conversion list from handlePartialLoadWithParentStatMbPerCtryConfWithOneElementInRow, message:"+ex.getMessage());
			return new ArrayList<StatMbPerCtryConf>();
		}
    }
    	
	 private List<StatMbPerCtryConf> convertPartialLoadWithParentStatMbPerCtryConf(List<Object[]> list, Map<Integer, String> beanPath, StatMbPerCtryConf statMbPerCtryConfWhat) throws IllegalAccessException, InvocationTargetException, InstantiationException, NoSuchMethodException {
		 List<StatMbPerCtryConf> resultList = new ArrayList<StatMbPerCtryConf>();
		 for (Object[] row : list) {		
		    StatMbPerCtryConf statMbPerCtryConf = cloneStatMbPerCtryConf (statMbPerCtryConfWhat);
		    Iterator<Entry<Integer, String>> iter = beanPath.entrySet().iterator();	
		    while (iter.hasNext()) {
		       Entry entry = iter.next();
		       populateStatMbPerCtryConf (statMbPerCtryConf, row[(Integer)entry.getKey()], (String)entry.getValue());
		    }
		    resultList.add(statMbPerCtryConf);
		 }
		 return resultList;		
	 }	
    
	 private List<StatMbPerCtryConf> convertPartialLoadWithParentStatMbPerCtryConfWithOneElementInRow(List<Object> list, Map<Integer, String> beanPath, StatMbPerCtryConf statMbPerCtryConfWhat) throws IllegalAccessException, InvocationTargetException, InstantiationException, NoSuchMethodException {
		 List<StatMbPerCtryConf> resultList = new ArrayList<StatMbPerCtryConf>();
		 for (Object row : list) {		
		    StatMbPerCtryConf statMbPerCtryConf = cloneStatMbPerCtryConf (statMbPerCtryConfWhat);
		    Iterator<Entry<Integer, String>> iter = beanPath.entrySet().iterator();	
		    while (iter.hasNext()) {
		       Entry entry = iter.next();
		       populateStatMbPerCtryConf (statMbPerCtryConf, row, (String)entry.getValue());
		    }
		    resultList.add(statMbPerCtryConf);
		 }		 
		 return resultList;		
	 }
   
	public List partialLoadWithParentForBean(Object bean, StatMbPerCtryConf statMbPerCtryConfWhat, StatMbPerCtryConf positiveStatMbPerCtryConf, StatMbPerCtryConf negativeStatMbPerCtryConf) {
		Map beanPath = new Hashtable();
		Query hquery = getPartialLoadWithParentStatMbPerCtryConfJPAQuery (statMbPerCtryConfWhat, positiveStatMbPerCtryConf, negativeStatMbPerCtryConf, beanPath, new QuerySelectInit(), null);
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
	public String getPartialLoadWithParentStatMbPerCtryConfHsqlQuery (StatMbPerCtryConf statMbPerCtryConf, StatMbPerCtryConf positiveStatMbPerCtryConf, StatMbPerCtryConf negativeStatMbPerCtryConf, Map beanPath, QueryWhatInit queryWhatInit) {
		Hashtable aliasWhatHt = new Hashtable();
		String what = getPartialLoadWithParentStatMbPerCtryConfQuery (statMbPerCtryConf, null, aliasWhatHt, null, null, beanPath, "", queryWhatInit);
		Hashtable aliasWhereHt = new Hashtable();
		String where = getPartialLoadWithParentWhereStatMbPerCtryConfQuery (positiveStatMbPerCtryConf, null, aliasWhatHt, aliasWhereHt, null, null);
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
	public List<StatMbPerCtryConf> partialLoadStatMbPerCtryConf(StatMbPerCtryConf statMbPerCtryConf, StatMbPerCtryConf positiveStatMbPerCtryConf, StatMbPerCtryConf negativeStatMbPerCtryConf) {
	    Query hquery = getEntityManager().createQuery(getPartialLoadStatMbPerCtryConfQuery (statMbPerCtryConf, positiveStatMbPerCtryConf, negativeStatMbPerCtryConf));
		int countPartialField = countPartialField(statMbPerCtryConf);
		if (countPartialField==0) 
		   return new ArrayList<StatMbPerCtryConf>();
		List list = hquery.getResultList();
		Iterator iter = list.iterator();
		List<StatMbPerCtryConf> returnList = new ArrayList<StatMbPerCtryConf>();
		while(iter.hasNext()) {
	       int index = 0;
	       Object[] row;
	       if (countPartialField==1) {
	    	  row = new Object[1];
	    	  row[0] = iter.next();
	       } else 
		      row = (Object[]) iter.next();
		   StatMbPerCtryConf statMbPerCtryConfResult = new StatMbPerCtryConf();
           if (statMbPerCtryConf.getId() != null) {
			  statMbPerCtryConfResult.setId((String) row[index]);
			  index++;
           }
           if (statMbPerCtryConf.getCountry() != null) {
			  statMbPerCtryConfResult.setCountry((String) row[index]);
			  index++;
           }
           if (statMbPerCtryConf.getConferenceName() != null) {
			  statMbPerCtryConfResult.setConferenceName((String) row[index]);
			  index++;
           }
           if (statMbPerCtryConf.getNumber() != null) {
			  statMbPerCtryConfResult.setNumber((Long) row[index]);
			  index++;
           }
           returnList.add(statMbPerCtryConfResult);
        }
	    return returnList;
	}

	public static String getPartialLoadWithParentWhereStatMbPerCtryConfQuery (
	   StatMbPerCtryConf statMbPerCtryConf, Boolean isWhereSet, Hashtable aliasHt, Hashtable aliasWhereHt, String childAlias, String childFKAlias) {
	   if (statMbPerCtryConf==null)
	      return "";
	   String alias = null;
	   if (aliasWhereHt == null) {
	      aliasWhereHt = new Hashtable();
	   } 
	   if (isLookedUp(statMbPerCtryConf)){
	      alias = getNextAlias (aliasWhereHt, statMbPerCtryConf);
		  aliasWhereHt.put (getAliasKey(alias), getAliasConnection(alias, childAlias, childFKAlias));
	   }
	   if (isWhereSet == null)
          isWhereSet = false;
       StringBuffer query = new StringBuffer();
       if (statMbPerCtryConf.getId() != null) {
           query.append (getQueryBLANK_AND (isWhereSet));
		   isWhereSet = true;
           query.append(" "+alias+".id = '"+ statMbPerCtryConf.getId()+"' ");
       }
       if (statMbPerCtryConf.getCountry() != null) {
           query.append (getQueryBLANK_AND (isWhereSet));
		   isWhereSet = true;
           query.append(" "+alias+".country = '"+ statMbPerCtryConf.getCountry()+"' ");
       }
       if (statMbPerCtryConf.getConferenceName() != null) {
           query.append (getQueryBLANK_AND (isWhereSet));
		   isWhereSet = true;
           query.append(" "+alias+".conferenceName = '"+ statMbPerCtryConf.getConferenceName()+"' ");
       }
       if (statMbPerCtryConf.getNumber() != null) {
           query.append (getQueryBLANK_AND (isWhereSet));
		   isWhereSet = true;
           query.append(" "+alias+".number = "+ statMbPerCtryConf.getNumber() + " ");
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
	
    public static String getPartialLoadWithParentStatMbPerCtryConfQuery (
	   StatMbPerCtryConf statMbPerCtryConf, Boolean isWhereSet, Hashtable aliasHt, String childAlias, String childFKAlias, Map beanPath, String rootPath, QueryWhatInit queryWhatInit) {
	   if (statMbPerCtryConf==null)
	      return "";
	   String alias = null;
	   if (aliasHt == null) {
	      aliasHt = new Hashtable();
	   } 
	   if (isLookedUp(statMbPerCtryConf)){
	      alias = getNextAlias (aliasHt, statMbPerCtryConf);
		  aliasHt.put (getAliasKey(alias), getAliasConnection(alias, childAlias, childFKAlias));
	   }
	   if (isWhereSet == null)
          isWhereSet = false;
       StringBuffer query = new StringBuffer();
       if (statMbPerCtryConf.getId() != null) {
          query.append (queryWhatInit.getWhatInit (isWhereSet));
          isWhereSet = true; 
          beanPath.put(beanPath.size(), rootPath+"id");
          query.append(" "+alias+".id ");
       }
       if (statMbPerCtryConf.getCountry() != null) {
          query.append (queryWhatInit.getWhatInit (isWhereSet));
          isWhereSet = true; 
          beanPath.put(beanPath.size(), rootPath+"country");
          query.append(" "+alias+".country ");
       }
       if (statMbPerCtryConf.getConferenceName() != null) {
          query.append (queryWhatInit.getWhatInit (isWhereSet));
          isWhereSet = true; 
          beanPath.put(beanPath.size(), rootPath+"conferenceName");
          query.append(" "+alias+".conferenceName ");
       }
       if (statMbPerCtryConf.getNumber() != null) {
          query.append (queryWhatInit.getWhatInit (isWhereSet));
          isWhereSet = true; 
          beanPath.put(beanPath.size(), rootPath+"number");
          query.append(" "+alias+".number ");
       }
//       query.append(getStatMbPerCtryConfSearchEqualQuery (positiveStatMbPerCtryConf, negativeStatMbPerCtryConf));
	   return query.toString(); 
    }
	
	protected static String getAliasConnection(String existingAlias, String childAlias, String childFKAlias) {
		if (childAlias==null)
		   return "";
		return childAlias+"."+childFKAlias+" = "+existingAlias+"."+"id";
	}
	
	protected static String getAliasKey (String alias) {
	  //TODO this is a temporary solution use a dedicated object in BslaHiberateDaoSupport
		return "StatMbPerCtryConf|"+alias;
	}
	
	protected static String getAliasKeyAlias (String aliasKey) {
	  //TODO this is a temporary solution use a dedicated object in BslaHiberateDaoSupport
		return StringUtils.substringAfter(aliasKey, "|");
	}
	
	protected static String getAliasKeyDomain (String aliasKey) {
	  //TODO this is a temporary solution use a dedicated object in BslaHiberateDaoSupport
	  return StringUtils.substringBefore(aliasKey, "|");
	}
	
	protected static String getNextAlias (Hashtable aliasHt, StatMbPerCtryConf statMbPerCtryConf) {
		int cptSameAlias = 0;
		Enumeration<String> keys = aliasHt.keys();
		while (keys.hasMoreElements()) {
			String key = keys.nextElement();
			if (key.startsWith("statMbPerCtryConf"))
				cptSameAlias++;
		}
		if (cptSameAlias==0)
			return "statMbPerCtryConf";
		else
			return "statMbPerCtryConf_"+cptSameAlias;
	}
	
	
	protected static boolean isLookedUp (StatMbPerCtryConf statMbPerCtryConf) {
	   if (statMbPerCtryConf==null)
		  return false;
       if (statMbPerCtryConf.getId() != null) {
	      return true;
       }
       if (statMbPerCtryConf.getCountry() != null) {
	      return true;
       }
       if (statMbPerCtryConf.getConferenceName() != null) {
	      return true;
       }
       if (statMbPerCtryConf.getNumber() != null) {
	      return true;
       }
       return false;   
	}
	
    public String getPartialLoadStatMbPerCtryConfQuery(
	   StatMbPerCtryConf statMbPerCtryConf, 
	   StatMbPerCtryConf positiveStatMbPerCtryConf, 
	   StatMbPerCtryConf negativeStatMbPerCtryConf) {
       boolean isWhereSet = false;
       StringBuffer query = new StringBuffer();
       if (statMbPerCtryConf.getId() != null) {
          query.append (getQuerySelectComma (isWhereSet));
          isWhereSet = true; 
          query.append(" id ");
       }
       if (statMbPerCtryConf.getCountry() != null) {
          query.append (getQuerySelectComma (isWhereSet));
          isWhereSet = true; 
          query.append(" country ");
       }
       if (statMbPerCtryConf.getConferenceName() != null) {
          query.append (getQuerySelectComma (isWhereSet));
          isWhereSet = true; 
          query.append(" conferenceName ");
       }
       if (statMbPerCtryConf.getNumber() != null) {
          query.append (getQuerySelectComma (isWhereSet));
          isWhereSet = true; 
          query.append(" number ");
       }
       query.append(getStatMbPerCtryConfSearchEqualQuery (positiveStatMbPerCtryConf, negativeStatMbPerCtryConf));
	   return query.toString(); 
    }
	
	public List<StatMbPerCtryConf> searchPrototypeWithCacheStatMbPerCtryConf(StatMbPerCtryConf statMbPerCtryConf) {
		SimpleCache simpleCache = new SimpleCache();
		List<StatMbPerCtryConf> list = (List<StatMbPerCtryConf>)simpleCache.get(statMbPerCtryConf.toString());
		if (list==null) {
			list = searchPrototypeStatMbPerCtryConf(statMbPerCtryConf);
			simpleCache.put(statMbPerCtryConf.toString(), list);
		}
		return list;
	}

    public List<StatMbPerCtryConf> loadGraph(StatMbPerCtryConf graphMaskWhat, List<StatMbPerCtryConf> whereMask) {
        return loadGraphOneLevel(graphMaskWhat, whereMask);
    }
 
    public List<StatMbPerCtryConf> loadGraphOneLevel(StatMbPerCtryConf graphMaskWhat, List<StatMbPerCtryConf> whereMask) {
        //first get roots element from where list mask
        // this brings the level 0 of the graph (root level)
        graphMaskWhat.setId(graphMaskWhat.stringMask__);
        List<StatMbPerCtryConf> statMbPerCtryConfs = searchPrototypeStatMbPerCtryConf (whereMask);
        // for each sub level perform the search with a subquery then reassemble
        // 1. get all sublevel queries
        // 2. perform queries on the correct dao
        // 3. reassemble
        return getLoadGraphOneLevel (graphMaskWhat, statMbPerCtryConfs);
    }

	private List<StatMbPerCtryConf> copy(List<StatMbPerCtryConf> inputs) {
		List<StatMbPerCtryConf> l = new ArrayList<StatMbPerCtryConf>();
		for (StatMbPerCtryConf input : inputs) {
			StatMbPerCtryConf copy = new StatMbPerCtryConf();
			copy.copy(input);
			l.add(copy);
		}
		return l;
	}
	   
	private List<StatMbPerCtryConf> getLoadGraphOneLevel (StatMbPerCtryConf graphMaskWhat, List<StatMbPerCtryConf> parents) {
	    return loadGraphFromParentKey (graphMaskWhat, parents);
	} 
	
	public List<StatMbPerCtryConf> loadGraphFromParentKey (StatMbPerCtryConf graphMaskWhat, List<StatMbPerCtryConf> parents) {
		//foreach children:
		//check if not empty take first
		parents = copy (parents); //working with detached entities to avoid unnecessary sql calls
		if (parents==null || parents.isEmpty())
		   return parents;
		List<String> ids = getPk (parents);
		return parents;
	}
	
	private List<String> getPk(List<StatMbPerCtryConf> input) {
		List<String> s = new ArrayList<String>();
		for (StatMbPerCtryConf t : input) {
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
