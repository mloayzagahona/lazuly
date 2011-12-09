package net.sf.mp.demo.conference.dao.impl.jpa.conference;

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
import net.sf.mp.demo.conference.dao.face.conference.ConferenceFeedbackExtDao;
import net.sf.mp.demo.conference.domain.conference.ConferenceFeedback;
import net.sf.mp.demo.conference.dao.impl.jpa.conference.ConferenceFeedbackJPAImpl;

import net.sf.mp.demo.conference.dao.impl.jpa.conference.ConferenceExtendedJPAImpl;
import net.sf.mp.demo.conference.dao.impl.jpa.conference.ConferenceMemberExtendedJPAImpl;
/**
 *
 * <p>Title: ConferenceFeedbackExtendedJPAImpl</p>
 *
 * <p>Description: Interface of a Data access object dealing with ConferenceFeedbackExtendedJPAImpl
 * persistence. It offers a set of methods which allow for saving,
 * deleting and searching ConferenceFeedbackExtendedJPAImpl objects</p>
 *
 */

public class ConferenceFeedbackExtendedJPAImpl extends ConferenceFeedbackJPAImpl implements ConferenceFeedbackExtDao {

    private Logger log = Logger.getLogger(this.getClass());
    
    private SimpleCache simpleCache = new SimpleCache();
    private EntityManager emForRecursiveDao; // dao that needs other dao in a recursive manner not support by spring configuration

    public ConferenceFeedbackExtendedJPAImpl () {}

    /**
     * generic to move after in superclass
     */
    public ConferenceFeedbackExtendedJPAImpl (EntityManager emForRecursiveDao) {
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
     * Inserts a ConferenceFeedback entity with cascade of its children
     * @param ConferenceFeedback conferenceFeedback
     */
    public void insertConferenceFeedbackWithCascade(ConferenceFeedback conferenceFeedback) {
    	ConferenceFeedbackExtendedJPAImpl conferencefeedbackextendedjpaimpl = new ConferenceFeedbackExtendedJPAImpl(getEntityManager());
    	conferencefeedbackextendedjpaimpl.insertConferenceFeedbackWithCascade(conferencefeedbackextendedjpaimpl.getEntityManagerForRecursiveDao(), conferenceFeedback);
    }
     
    public void insertConferenceFeedbackWithCascade(EntityManager emForRecursiveDao, ConferenceFeedback conferenceFeedback) {
       insertConferenceFeedback(emForRecursiveDao, conferenceFeedback);
    }
        
    /**
     * Inserts a list of ConferenceFeedback entity with cascade of its children
     * @param List<ConferenceFeedback> conferenceFeedbacks
     */
    public void insertConferenceFeedbacksWithCascade(List<ConferenceFeedback> conferenceFeedbacks) {
       for (ConferenceFeedback conferenceFeedback : conferenceFeedbacks) {
          insertConferenceFeedbackWithCascade(conferenceFeedback);
       }
    } 
        
    /**
     * lookup ConferenceFeedback entity ConferenceFeedback, criteria and max result number
     */
    public List<ConferenceFeedback> lookupConferenceFeedback(ConferenceFeedback conferenceFeedback, Criteria criteria, Integer numberOfResult, EntityManager em) {
		boolean isWhereSet = false;
        StringBuffer query = new StringBuffer();
        query.append ("SELECT conferenceFeedback FROM ConferenceFeedback conferenceFeedback ");
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
    
    public List<ConferenceFeedback> lookupConferenceFeedback(ConferenceFeedback conferenceFeedback, Criteria criteria, Integer numberOfResult) {
		return lookupConferenceFeedback(conferenceFeedback, criteria, numberOfResult, getEntityManager());
    }

    public Integer updateNotNullOnlyConferenceFeedback (ConferenceFeedback conferenceFeedback, Criteria criteria) {
        String queryWhat = getUpdateNotNullOnlyConferenceFeedbackQueryChunkPrototype (conferenceFeedback);
        StringBuffer query = new StringBuffer (queryWhat);
        boolean isWhereSet = false;
        for (Criterion criterion : criteria.getClauseCriterions()) {
            query.append (getQueryWHERE_AND (isWhereSet));
            isWhereSet = true;   
            query.append(criterion.getExpression());			
        }  
        Query jpaQuery = getEntityManager().createQuery(query.toString());
        isWhereSet = false;
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
	
	public ConferenceFeedback affectConferenceFeedback (ConferenceFeedback conferenceFeedback) {
	    return referConferenceFeedback (conferenceFeedback, false);		    
	}
		
	/**
	 * Assign the first conferenceFeedback retrieved corresponding to the conferenceFeedback criteria.
	 * Blank criteria are mapped to null.
	 * If no criteria is found, null is returned.
	 * If there is no conferenceFeedback corresponding in the database. Then conferenceFeedback is inserted and returned with its primary key(s). 
	 */
	public ConferenceFeedback assignConferenceFeedback (ConferenceFeedback conferenceFeedback) {
	    return referConferenceFeedback (conferenceFeedback, true);	
	}
	
	public ConferenceFeedback referConferenceFeedback (ConferenceFeedback conferenceFeedback, boolean isAssign) {
		conferenceFeedback = assignBlankToNull (conferenceFeedback);
		if (isAllNull(conferenceFeedback))
			return null;
		else {
			List<ConferenceFeedback> list = searchPrototypeConferenceFeedback(conferenceFeedback);
			if (list.isEmpty()) {
			    if (isAssign)
			       insertConferenceFeedback(conferenceFeedback);
			    else
				   return null;
			}
			else if (list.size()==1)
				conferenceFeedback.copy(list.get(0));
			else 
				//TODO log error
				conferenceFeedback.copy(list.get(0));
		}
		return conferenceFeedback;		    
	}

   public ConferenceFeedback assignConferenceFeedbackUseCache (ConferenceFeedback conferenceFeedback) {
      return referConferenceFeedbackUseCache (conferenceFeedback, true);
   }
      		
   public ConferenceFeedback affectConferenceFeedbackUseCache (ConferenceFeedback conferenceFeedback) {
      return referConferenceFeedbackUseCache (conferenceFeedback, false);
   }
      		
   public ConferenceFeedback referConferenceFeedbackUseCache (ConferenceFeedback conferenceFeedback, boolean isAssign) {
	  String key = getCacheKey(null, conferenceFeedback, null, "assignConferenceFeedback");
      ConferenceFeedback conferenceFeedbackCache = (ConferenceFeedback)simpleCache.get(key);
      if (conferenceFeedbackCache==null) {
         conferenceFeedbackCache = referConferenceFeedback (conferenceFeedback, isAssign);
         if (key!=null)
         	simpleCache.put(key, conferenceFeedbackCache);
      }
      conferenceFeedback.copy(conferenceFeedbackCache);
      return conferenceFeedbackCache;
   }	

	private String getCacheKey (ConferenceFeedback conferenceFeedbackWhat, ConferenceFeedback positiveConferenceFeedback, ConferenceFeedback negativeConferenceFeedback, String queryKey) {
	    StringBuffer sb = new StringBuffer();
	    sb.append(queryKey);
	    if (conferenceFeedbackWhat!=null)
	       sb.append(conferenceFeedbackWhat.toStringWithParents());
	    if (positiveConferenceFeedback!=null)
	       sb.append(positiveConferenceFeedback.toStringWithParents());
	    if (negativeConferenceFeedback!=null)
	       sb.append(negativeConferenceFeedback.toStringWithParents());
	    return sb.toString();
	}
	
    public ConferenceFeedback partialLoadWithParentFirstConferenceFeedback(ConferenceFeedback conferenceFeedbackWhat, ConferenceFeedback positiveConferenceFeedback, ConferenceFeedback negativeConferenceFeedback){
		List <ConferenceFeedback> list = partialLoadWithParentConferenceFeedback(conferenceFeedbackWhat, positiveConferenceFeedback, negativeConferenceFeedback);
		return (!list.isEmpty())?(ConferenceFeedback)list.get(0):null;
    }
    
    public ConferenceFeedback partialLoadWithParentFirstConferenceFeedbackUseCache(ConferenceFeedback conferenceFeedbackWhat, ConferenceFeedback positiveConferenceFeedback, ConferenceFeedback negativeConferenceFeedback, Boolean useCache){
		List <ConferenceFeedback> list = partialLoadWithParentConferenceFeedbackUseCache(conferenceFeedbackWhat, positiveConferenceFeedback, negativeConferenceFeedback, useCache);
		return (!list.isEmpty())?(ConferenceFeedback)list.get(0):null;
    }
	
	public ConferenceFeedback partialLoadWithParentFirstConferenceFeedbackUseCacheOnResult(ConferenceFeedback conferenceFeedbackWhat, ConferenceFeedback positiveConferenceFeedback, ConferenceFeedback negativeConferenceFeedback, Boolean useCache){
		List <ConferenceFeedback> list = partialLoadWithParentConferenceFeedbackUseCacheOnResult(conferenceFeedbackWhat, positiveConferenceFeedback, negativeConferenceFeedback, useCache);
		return (!list.isEmpty())?(ConferenceFeedback)list.get(0):null;
    }
	//
	protected List<ConferenceFeedback> partialLoadWithParentConferenceFeedback(ConferenceFeedback conferenceFeedbackWhat, ConferenceFeedback positiveConferenceFeedback, ConferenceFeedback negativeConferenceFeedback, Integer nbOfResult, Boolean useCache) {
		 return partialLoadWithParentConferenceFeedback(conferenceFeedbackWhat, positiveConferenceFeedback, negativeConferenceFeedback, new QuerySelectInit(), nbOfResult, useCache);
	}	  

	protected List partialLoadWithParentConferenceFeedbackQueryResult (ConferenceFeedback conferenceFeedbackWhat, ConferenceFeedback positiveConferenceFeedback, ConferenceFeedback negativeConferenceFeedback, Integer nbOfResult, Boolean useCache) {
		 return partialLoadWithParentConferenceFeedbackQueryResult (conferenceFeedbackWhat, positiveConferenceFeedback, negativeConferenceFeedback, new QuerySelectInit(), nbOfResult, useCache);
	}	
    
    public List<ConferenceFeedback> getDistinctConferenceFeedback(ConferenceFeedback conferenceFeedbackWhat, ConferenceFeedback positiveConferenceFeedback, ConferenceFeedback negativeConferenceFeedback) {
		 return partialLoadWithParentConferenceFeedback(conferenceFeedbackWhat, positiveConferenceFeedback, negativeConferenceFeedback, new QuerySelectDistinctInit(), null, false);
	}
	
	public List<ConferenceFeedback> partialLoadWithParentConferenceFeedback(ConferenceFeedback conferenceFeedbackWhat, ConferenceFeedback positiveConferenceFeedback, ConferenceFeedback negativeConferenceFeedback) {
		 return partialLoadWithParentConferenceFeedback(conferenceFeedbackWhat, positiveConferenceFeedback, negativeConferenceFeedback, new QuerySelectInit(), null, false);
	}	
  
	public List<ConferenceFeedback> partialLoadWithParentConferenceFeedbackUseCacheOnResult(ConferenceFeedback conferenceFeedbackWhat, ConferenceFeedback positiveConferenceFeedback, ConferenceFeedback negativeConferenceFeedback, Boolean useCache) {
		String key = getCacheKey(conferenceFeedbackWhat, positiveConferenceFeedback, negativeConferenceFeedback, "partialLoadWithParentConferenceFeedback");
		List<ConferenceFeedback> list = (List<ConferenceFeedback>)simpleCache.get(key);
		if (list==null || list.isEmpty()) {
			list = partialLoadWithParentConferenceFeedback(conferenceFeedbackWhat, positiveConferenceFeedback, negativeConferenceFeedback);
			if (!list.isEmpty())
			   simpleCache.put(key, list);
		}
		return list;	
	}	

	public List<ConferenceFeedback> partialLoadWithParentConferenceFeedbackUseCache(ConferenceFeedback conferenceFeedbackWhat, ConferenceFeedback positiveConferenceFeedback, ConferenceFeedback negativeConferenceFeedback, Boolean useCache) {
		String key = getCacheKey(conferenceFeedbackWhat, positiveConferenceFeedback, negativeConferenceFeedback, "partialLoadWithParentConferenceFeedback");
		List<ConferenceFeedback> list = (List<ConferenceFeedback>)simpleCache.get(key);
		if (list==null) {
			list = partialLoadWithParentConferenceFeedback(conferenceFeedbackWhat, positiveConferenceFeedback, negativeConferenceFeedback);
			simpleCache.put(key, list);
		}
		return list;	
	}	
	
	private List<ConferenceFeedback> handleLoadWithParentConferenceFeedback(Map beanPath, List list, ConferenceFeedback conferenceFeedbackWhat) {
	    return handleLoadWithParentConferenceFeedback(beanPath, list, conferenceFeedbackWhat, true);  
	}
	
	private List<ConferenceFeedback> handleLoadWithParentConferenceFeedback(Map beanPath, List list, ConferenceFeedback conferenceFeedbackWhat, boolean isHql) {
		if (beanPath.size()==1) {
			return handlePartialLoadWithParentConferenceFeedbackWithOneElementInRow(list, beanPath, conferenceFeedbackWhat, isHql);
		}
		return handlePartialLoadWithParentConferenceFeedback(list, beanPath, conferenceFeedbackWhat, isHql);	
	}
	
    	// to set in super class	
	protected void populateConferenceFeedback (ConferenceFeedback conferenceFeedback, Object value, String beanPath) throws IllegalAccessException, InvocationTargetException, NoSuchMethodException {
	   BeanUtils.populateBeanObject(conferenceFeedback, beanPath, value);
	}
	
	protected void populateConferenceFeedbackFromSQL (ConferenceFeedback conferenceFeedback, Object value, String beanPath) throws IllegalAccessException, InvocationTargetException, NoSuchMethodException {
	   BeanUtils.populateBeanObject(conferenceFeedback, beanPath, value);
	}
    	// to set in super class BEWARE: genericity is only one level!!!!! first level is a copy second level is a reference!!! change to conferenceFeedback.clone() instead
	private ConferenceFeedback cloneConferenceFeedback (ConferenceFeedback conferenceFeedback) throws IllegalAccessException, InvocationTargetException, InstantiationException, NoSuchMethodException {
		//return (ConferenceFeedback) BeanUtils.cloneBeanObject(conferenceFeedback);
	   if (conferenceFeedback==null) return new ConferenceFeedback();
	   return conferenceFeedback.clone();
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
	
    public List<ConferenceFeedback> countDistinct (ConferenceFeedback whatMask, ConferenceFeedback whereEqCriteria) {
       return partialLoadWithParentConferenceFeedback(whatMask, whereEqCriteria, null, new QuerySelectCountInit("conferenceFeedback"), null, false);
    }   
    	
    public Long count(ConferenceFeedback whereEqCriteria) {
        List<ConferenceFeedback> list = partialLoadWithParentConferenceFeedback(null, whereEqCriteria, null, new QueryCountInit("conferenceFeedback"), null, false);    
    	if (!list.isEmpty())
    		return list.get(0).getCount__();
    	return 0L;
    }
		
		  		
   public ConferenceFeedback getFirstConferenceFeedbackWhereConditionsAre (ConferenceFeedback conferenceFeedback) {
      List<ConferenceFeedback> list = partialLoadWithParentConferenceFeedback(getDefaultConferenceFeedbackWhat(), conferenceFeedback, null, 1, false);
      if (list.isEmpty()) {
         return null;
      }
      else if (list.size()==1)
         return list.get(0);
      else 
      //TODO log error
         return list.get(0);	
	}

   private List getFirstResultWhereConditionsAre (ConferenceFeedback conferenceFeedback) {
      return  partialLoadWithParentConferenceFeedbackQueryResult(getDefaultConferenceFeedbackWhat(), conferenceFeedback, null, 1, false);	
   }
   
   protected ConferenceFeedback getDefaultConferenceFeedbackWhat() {
      ConferenceFeedback conferenceFeedback = new ConferenceFeedback();
      conferenceFeedback.setId(Integer.valueOf("-1"));
      return conferenceFeedback;
   }
   
	public ConferenceFeedback getFirstConferenceFeedback (ConferenceFeedback conferenceFeedback) {
		if (isAllNull(conferenceFeedback))
			return null;
		else {
			List<ConferenceFeedback> list = searchPrototype (conferenceFeedback, 1);
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
    * checks if the ConferenceFeedback entity exists
    */           
    public boolean existsConferenceFeedback (ConferenceFeedback conferenceFeedback) {
       if (getFirstConferenceFeedback(conferenceFeedback)!=null)
          return true;
       return false;  
    }
        
    public boolean existsConferenceFeedbackWhereConditionsAre (ConferenceFeedback conferenceFeedback) {
       if (getFirstResultWhereConditionsAre (conferenceFeedback).isEmpty())
          return false;
       return true;  
    }


	
	private int countPartialField (ConferenceFeedback conferenceFeedback) {
	   int cpt = 0;
       if (conferenceFeedback.getId() != null) {
          cpt++;
       }
       if (conferenceFeedback.getComment() != null) {
          cpt++;
       }
       if (conferenceFeedback.getFeedbackDate() != null) {
          cpt++;
       }
       if (conferenceFeedback.getConferenceId() != null) {
          cpt++;
       }
       if (conferenceFeedback.getConferenceMemberId() != null) {
          cpt++;
       }
       return cpt;
	}   
  	
	public List<ConferenceFeedback> partialLoadWithParentConferenceFeedback(ConferenceFeedback conferenceFeedbackWhat, ConferenceFeedback positiveConferenceFeedback, ConferenceFeedback negativeConferenceFeedback, QueryWhatInit queryWhatInit, Integer nbOfResult, Boolean useCache) {
		Map beanPath = new Hashtable();
		List list = partialLoadWithParentConferenceFeedbackJPAQueryResult (conferenceFeedbackWhat, positiveConferenceFeedback, negativeConferenceFeedback, queryWhatInit, beanPath, nbOfResult, useCache);
		if (beanPath.size()==1) {
			return handlePartialLoadWithParentConferenceFeedbackWithOneElementInRow(list, beanPath, conferenceFeedbackWhat, true);
		}
		return handlePartialLoadWithParentConferenceFeedback(list, beanPath, conferenceFeedbackWhat, true);
	}	

	private List partialLoadWithParentConferenceFeedbackQueryResult(ConferenceFeedback conferenceFeedbackWhat, ConferenceFeedback positiveConferenceFeedback, ConferenceFeedback negativeConferenceFeedback, QueryWhatInit queryWhatInit, Integer nbOfResult, Boolean useCache) {
		return partialLoadWithParentConferenceFeedbackJPAQueryResult (conferenceFeedbackWhat, positiveConferenceFeedback, negativeConferenceFeedback, queryWhatInit, new Hashtable(), nbOfResult, useCache);
  }	
  
	private List partialLoadWithParentConferenceFeedbackJPAQueryResult(ConferenceFeedback conferenceFeedbackWhat, ConferenceFeedback positiveConferenceFeedback, ConferenceFeedback negativeConferenceFeedback, QueryWhatInit queryWhatInit, Map beanPath, Integer nbOfResult, Boolean useCache) {
		Query hquery = getPartialLoadWithParentConferenceFeedbackJPAQuery (conferenceFeedbackWhat, positiveConferenceFeedback, negativeConferenceFeedback, beanPath, queryWhatInit, nbOfResult);
		return hquery.getResultList();
  }	
   /**
    * @returns an JPA Hsql query based on entity ConferenceFeedback and its parents and the maximum number of result
    */
	protected Query getPartialLoadWithParentConferenceFeedbackJPAQuery (ConferenceFeedback conferenceFeedbackWhat, ConferenceFeedback positiveConferenceFeedback, ConferenceFeedback negativeConferenceFeedback, Map beanPath, QueryWhatInit queryWhatInit, Integer nbOfResult) {
	   Query query = getEntityManager().createQuery(getPartialLoadWithParentConferenceFeedbackHsqlQuery (conferenceFeedbackWhat, positiveConferenceFeedback, negativeConferenceFeedback, beanPath, queryWhatInit));
	   if (nbOfResult!=null)
	      query.setMaxResults(nbOfResult);
	   return query;
  }
  
	private List<ConferenceFeedback> handlePartialLoadWithParentConferenceFeedback(List<Object[]> list, Map<Integer, String> beanPath, ConferenceFeedback conferenceFeedbackWhat, boolean isJql) {
		try {
			return convertPartialLoadWithParentConferenceFeedback(list, beanPath, conferenceFeedbackWhat);
		} catch (Exception ex) {
			log.error("Error conversion list from handlePartialLoadWithParentConferenceFeedback, message:"+ex.getMessage());
			return new ArrayList<ConferenceFeedback>();
		}
    }

	private List<ConferenceFeedback> handlePartialLoadWithParentConferenceFeedbackWithOneElementInRow(List<Object> list, Map<Integer, String> beanPath, ConferenceFeedback conferenceFeedbackWhat, boolean isJql) {
		try {
			return convertPartialLoadWithParentConferenceFeedbackWithOneElementInRow(list, beanPath, conferenceFeedbackWhat);
		} catch (Exception ex) {
			log.error("Error conversion list from handlePartialLoadWithParentConferenceFeedbackWithOneElementInRow, message:"+ex.getMessage());
			return new ArrayList<ConferenceFeedback>();
		}
    }
    	
	 private List<ConferenceFeedback> convertPartialLoadWithParentConferenceFeedback(List<Object[]> list, Map<Integer, String> beanPath, ConferenceFeedback conferenceFeedbackWhat) throws IllegalAccessException, InvocationTargetException, InstantiationException, NoSuchMethodException {
		 List<ConferenceFeedback> resultList = new ArrayList<ConferenceFeedback>();
		 for (Object[] row : list) {		
		    ConferenceFeedback conferenceFeedback = cloneConferenceFeedback (conferenceFeedbackWhat);
		    Iterator<Entry<Integer, String>> iter = beanPath.entrySet().iterator();	
		    while (iter.hasNext()) {
		       Entry entry = iter.next();
		       populateConferenceFeedback (conferenceFeedback, row[(Integer)entry.getKey()], (String)entry.getValue());
		    }
		    resultList.add(conferenceFeedback);
		 }
		 return resultList;		
	 }	
    
	 private List<ConferenceFeedback> convertPartialLoadWithParentConferenceFeedbackWithOneElementInRow(List<Object> list, Map<Integer, String> beanPath, ConferenceFeedback conferenceFeedbackWhat) throws IllegalAccessException, InvocationTargetException, InstantiationException, NoSuchMethodException {
		 List<ConferenceFeedback> resultList = new ArrayList<ConferenceFeedback>();
		 for (Object row : list) {		
		    ConferenceFeedback conferenceFeedback = cloneConferenceFeedback (conferenceFeedbackWhat);
		    Iterator<Entry<Integer, String>> iter = beanPath.entrySet().iterator();	
		    while (iter.hasNext()) {
		       Entry entry = iter.next();
		       populateConferenceFeedback (conferenceFeedback, row, (String)entry.getValue());
		    }
		    resultList.add(conferenceFeedback);
		 }		 
		 return resultList;		
	 }
   
	public List partialLoadWithParentForBean(Object bean, ConferenceFeedback conferenceFeedbackWhat, ConferenceFeedback positiveConferenceFeedback, ConferenceFeedback negativeConferenceFeedback) {
		Map beanPath = new Hashtable();
		Query hquery = getPartialLoadWithParentConferenceFeedbackJPAQuery (conferenceFeedbackWhat, positiveConferenceFeedback, negativeConferenceFeedback, beanPath, new QuerySelectInit(), null);
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
	public String getPartialLoadWithParentConferenceFeedbackHsqlQuery (ConferenceFeedback conferenceFeedback, ConferenceFeedback positiveConferenceFeedback, ConferenceFeedback negativeConferenceFeedback, Map beanPath, QueryWhatInit queryWhatInit) {
		Hashtable aliasWhatHt = new Hashtable();
		String what = getPartialLoadWithParentConferenceFeedbackQuery (conferenceFeedback, null, aliasWhatHt, null, null, beanPath, "", queryWhatInit);
		Hashtable aliasWhereHt = new Hashtable();
		String where = getPartialLoadWithParentWhereConferenceFeedbackQuery (positiveConferenceFeedback, null, aliasWhatHt, aliasWhereHt, null, null);
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
	public List<ConferenceFeedback> partialLoadConferenceFeedback(ConferenceFeedback conferenceFeedback, ConferenceFeedback positiveConferenceFeedback, ConferenceFeedback negativeConferenceFeedback) {
	    Query hquery = getEntityManager().createQuery(getPartialLoadConferenceFeedbackQuery (conferenceFeedback, positiveConferenceFeedback, negativeConferenceFeedback));
		int countPartialField = countPartialField(conferenceFeedback);
		if (countPartialField==0) 
		   return new ArrayList<ConferenceFeedback>();
		List list = hquery.getResultList();
		Iterator iter = list.iterator();
		List<ConferenceFeedback> returnList = new ArrayList<ConferenceFeedback>();
		while(iter.hasNext()) {
	       int index = 0;
	       Object[] row;
	       if (countPartialField==1) {
	    	  row = new Object[1];
	    	  row[0] = iter.next();
	       } else 
		      row = (Object[]) iter.next();
		   ConferenceFeedback conferenceFeedbackResult = new ConferenceFeedback();
           if (conferenceFeedback.getId() != null) {
			  conferenceFeedbackResult.setId((Integer) row[index]);
			  index++;
           }
           if (conferenceFeedback.getComment() != null) {
			  conferenceFeedbackResult.setComment((String) row[index]);
			  index++;
           }
           if (conferenceFeedback.getFeedbackDate() != null) {
			  conferenceFeedbackResult.setFeedbackDate((Timestamp) row[index]);
			  index++;
           }
           if (conferenceFeedback.getConferenceId() != null) {
			  conferenceFeedbackResult.setConferenceId_((Long) row[index]);
			  index++;
           }
           if (conferenceFeedback.getConferenceMemberId() != null) {
			  conferenceFeedbackResult.setConferenceMemberId_((Long) row[index]);
			  index++;
           }
           returnList.add(conferenceFeedbackResult);
        }
	    return returnList;
	}

	public static String getPartialLoadWithParentWhereConferenceFeedbackQuery (
	   ConferenceFeedback conferenceFeedback, Boolean isWhereSet, Hashtable aliasHt, Hashtable aliasWhereHt, String childAlias, String childFKAlias) {
	   if (conferenceFeedback==null)
	      return "";
	   String alias = null;
	   if (aliasWhereHt == null) {
	      aliasWhereHt = new Hashtable();
	   } 
	   if (isLookedUp(conferenceFeedback)){
	      alias = getNextAlias (aliasWhereHt, conferenceFeedback);
		  aliasWhereHt.put (getAliasKey(alias), getAliasConnection(alias, childAlias, childFKAlias));
	   }
	   if (isWhereSet == null)
          isWhereSet = false;
       StringBuffer query = new StringBuffer();
       if (conferenceFeedback.getId() != null) {
           query.append (getQueryBLANK_AND (isWhereSet));
		   isWhereSet = true;
           query.append(" "+alias+".id = "+ conferenceFeedback.getId() + " ");
       }
       if (conferenceFeedback.getComment() != null) {
           query.append (getQueryBLANK_AND (isWhereSet));
		   isWhereSet = true;
           query.append(" "+alias+".comment = '"+ conferenceFeedback.getComment()+"' ");
       }
       if (conferenceFeedback.getFeedbackDate() != null) {
           query.append (getQueryBLANK_AND (isWhereSet));
		   isWhereSet = true;
           query.append(" "+alias+".feedbackDate = '"+ conferenceFeedback.getFeedbackDate()+"' ");
       }
       if (conferenceFeedback.getConferenceId() != null) {
           query.append (getQueryBLANK_AND (isWhereSet));
		   isWhereSet = true;
           query.append(" "+alias+".conferenceId = "+ conferenceFeedback.getConferenceId() + " ");
       }
       if (conferenceFeedback.getConferenceMemberId() != null) {
           query.append (getQueryBLANK_AND (isWhereSet));
		   isWhereSet = true;
           query.append(" "+alias+".conferenceMemberId = "+ conferenceFeedback.getConferenceMemberId() + " ");
       }
       if (conferenceFeedback.getConferenceId()!=null) {
	      String chunck = net.sf.mp.demo.conference.dao.impl.jpa.conference.ConferenceExtendedJPAImpl.getPartialLoadWithParentWhereConferenceQuery(
		      conferenceFeedback.getConferenceId(), 
			  isWhereSet, aliasHt, aliasWhereHt, alias, "conferenceId");
		  if (chunck!=null && !chunck.equals("")) {
		     query.append(chunck);
		     isWhereSet=true;
		  }  	  
	   }
       if (conferenceFeedback.getConferenceMemberId()!=null) {
	      String chunck = net.sf.mp.demo.conference.dao.impl.jpa.conference.ConferenceMemberExtendedJPAImpl.getPartialLoadWithParentWhereConferenceMemberQuery(
		      conferenceFeedback.getConferenceMemberId(), 
			  isWhereSet, aliasHt, aliasWhereHt, alias, "conferenceMemberId");
		  if (chunck!=null && !chunck.equals("")) {
		     query.append(chunck);
		     isWhereSet=true;
		  }  	  
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
	
    public static String getPartialLoadWithParentConferenceFeedbackQuery (
	   ConferenceFeedback conferenceFeedback, Boolean isWhereSet, Hashtable aliasHt, String childAlias, String childFKAlias, Map beanPath, String rootPath, QueryWhatInit queryWhatInit) {
	   if (conferenceFeedback==null)
	      return "";
	   String alias = null;
	   if (aliasHt == null) {
	      aliasHt = new Hashtable();
	   } 
	   if (isLookedUp(conferenceFeedback)){
	      alias = getNextAlias (aliasHt, conferenceFeedback);
		  aliasHt.put (getAliasKey(alias), getAliasConnection(alias, childAlias, childFKAlias));
	   }
	   if (isWhereSet == null)
          isWhereSet = false;
       StringBuffer query = new StringBuffer();
       if (conferenceFeedback.getId() != null) {
          query.append (queryWhatInit.getWhatInit (isWhereSet));
          isWhereSet = true; 
          beanPath.put(beanPath.size(), rootPath+"id");
          query.append(" "+alias+".id ");
       }
       if (conferenceFeedback.getComment() != null) {
          query.append (queryWhatInit.getWhatInit (isWhereSet));
          isWhereSet = true; 
          beanPath.put(beanPath.size(), rootPath+"comment");
          query.append(" "+alias+".comment ");
       }
       if (conferenceFeedback.getFeedbackDate() != null) {
          query.append (queryWhatInit.getWhatInit (isWhereSet));
          isWhereSet = true; 
          beanPath.put(beanPath.size(), rootPath+"feedbackDate");
          query.append(" "+alias+".feedbackDate ");
       }
       if (conferenceFeedback.getConferenceId() != null) {
          query.append (queryWhatInit.getWhatInit (isWhereSet));
          isWhereSet = true; 
          beanPath.put(beanPath.size(), rootPath+"conferenceId");
          query.append(" "+alias+".conferenceId ");
       }
       if (conferenceFeedback.getConferenceMemberId() != null) {
          query.append (queryWhatInit.getWhatInit (isWhereSet));
          isWhereSet = true; 
          beanPath.put(beanPath.size(), rootPath+"conferenceMemberId");
          query.append(" "+alias+".conferenceMemberId ");
       }
       if (conferenceFeedback.getConferenceId()!=null) {
	      String chunck = net.sf.mp.demo.conference.dao.impl.jpa.conference.ConferenceExtendedJPAImpl.getPartialLoadWithParentConferenceQuery(
		      conferenceFeedback.getConferenceId(), 
			  isWhereSet, aliasHt, alias, "conferenceId", beanPath, rootPath+"conferenceId.", queryWhatInit);
		  if (chunck!=null && !chunck.equals("")) {
		     query.append(chunck);
		     isWhereSet=true;
		  } 
	   }  
       if (conferenceFeedback.getConferenceMemberId()!=null) {
	      String chunck = net.sf.mp.demo.conference.dao.impl.jpa.conference.ConferenceMemberExtendedJPAImpl.getPartialLoadWithParentConferenceMemberQuery(
		      conferenceFeedback.getConferenceMemberId(), 
			  isWhereSet, aliasHt, alias, "conferenceMemberId", beanPath, rootPath+"conferenceMemberId.", queryWhatInit);
		  if (chunck!=null && !chunck.equals("")) {
		     query.append(chunck);
		     isWhereSet=true;
		  } 
	   }  
//       query.append(getConferenceFeedbackSearchEqualQuery (positiveConferenceFeedback, negativeConferenceFeedback));
	   return query.toString(); 
    }
	
	protected static String getAliasConnection(String existingAlias, String childAlias, String childFKAlias) {
		if (childAlias==null)
		   return "";
		return childAlias+"."+childFKAlias+" = "+existingAlias+"."+"id";
	}
	
	protected static String getAliasKey (String alias) {
	  //TODO this is a temporary solution use a dedicated object in BslaHiberateDaoSupport
		return "ConferenceFeedback|"+alias;
	}
	
	protected static String getAliasKeyAlias (String aliasKey) {
	  //TODO this is a temporary solution use a dedicated object in BslaHiberateDaoSupport
		return StringUtils.substringAfter(aliasKey, "|");
	}
	
	protected static String getAliasKeyDomain (String aliasKey) {
	  //TODO this is a temporary solution use a dedicated object in BslaHiberateDaoSupport
	  return StringUtils.substringBefore(aliasKey, "|");
	}
	
	protected static String getNextAlias (Hashtable aliasHt, ConferenceFeedback conferenceFeedback) {
		int cptSameAlias = 0;
		Enumeration<String> keys = aliasHt.keys();
		while (keys.hasMoreElements()) {
			String key = keys.nextElement();
			if (key.startsWith("conferenceFeedback"))
				cptSameAlias++;
		}
		if (cptSameAlias==0)
			return "conferenceFeedback";
		else
			return "conferenceFeedback_"+cptSameAlias;
	}
	
	
	protected static boolean isLookedUp (ConferenceFeedback conferenceFeedback) {
	   if (conferenceFeedback==null)
		  return false;
       if (conferenceFeedback.getId() != null) {
	      return true;
       }
       if (conferenceFeedback.getComment() != null) {
	      return true;
       }
       if (conferenceFeedback.getFeedbackDate() != null) {
	      return true;
       }
       if (conferenceFeedback.getConferenceId() != null) {
	      return true;
       }
       if (conferenceFeedback.getConferenceMemberId() != null) {
	      return true;
       }
       if (conferenceFeedback.getConferenceId()!=null) {
	      return true;
	   }  
       if (conferenceFeedback.getConferenceMemberId()!=null) {
	      return true;
	   }  
       return false;   
	}
	
    public String getPartialLoadConferenceFeedbackQuery(
	   ConferenceFeedback conferenceFeedback, 
	   ConferenceFeedback positiveConferenceFeedback, 
	   ConferenceFeedback negativeConferenceFeedback) {
       boolean isWhereSet = false;
       StringBuffer query = new StringBuffer();
       if (conferenceFeedback.getId() != null) {
          query.append (getQuerySelectComma (isWhereSet));
          isWhereSet = true; 
          query.append(" id ");
       }
       if (conferenceFeedback.getComment() != null) {
          query.append (getQuerySelectComma (isWhereSet));
          isWhereSet = true; 
          query.append(" comment ");
       }
       if (conferenceFeedback.getFeedbackDate() != null) {
          query.append (getQuerySelectComma (isWhereSet));
          isWhereSet = true; 
          query.append(" feedbackDate ");
       }
       if (conferenceFeedback.getConferenceId() != null) {
          query.append (getQuerySelectComma (isWhereSet));
          isWhereSet = true; 
          query.append(" conferenceId ");
       }
       if (conferenceFeedback.getConferenceMemberId() != null) {
          query.append (getQuerySelectComma (isWhereSet));
          isWhereSet = true; 
          query.append(" conferenceMemberId ");
       }
       query.append(getConferenceFeedbackSearchEqualQuery (positiveConferenceFeedback, negativeConferenceFeedback));
	   return query.toString(); 
    }
	
	public List<ConferenceFeedback> searchPrototypeWithCacheConferenceFeedback(ConferenceFeedback conferenceFeedback) {
		SimpleCache simpleCache = new SimpleCache();
		List<ConferenceFeedback> list = (List<ConferenceFeedback>)simpleCache.get(conferenceFeedback.toString());
		if (list==null) {
			list = searchPrototypeConferenceFeedback(conferenceFeedback);
			simpleCache.put(conferenceFeedback.toString(), list);
		}
		return list;
	}

    public List<ConferenceFeedback> loadGraph(ConferenceFeedback graphMaskWhat, List<ConferenceFeedback> whereMask) {
        return loadGraphOneLevel(graphMaskWhat, whereMask);
    }
 
    public List<ConferenceFeedback> loadGraphOneLevel(ConferenceFeedback graphMaskWhat, List<ConferenceFeedback> whereMask) {
        //first get roots element from where list mask
        // this brings the level 0 of the graph (root level)
        graphMaskWhat.setId(graphMaskWhat.integerMask__);
        List<ConferenceFeedback> conferenceFeedbacks = searchPrototypeConferenceFeedback (whereMask);
        // for each sub level perform the search with a subquery then reassemble
        // 1. get all sublevel queries
        // 2. perform queries on the correct dao
        // 3. reassemble
        return getLoadGraphOneLevel (graphMaskWhat, conferenceFeedbacks);
    }

	private List<ConferenceFeedback> copy(List<ConferenceFeedback> inputs) {
		List<ConferenceFeedback> l = new ArrayList<ConferenceFeedback>();
		for (ConferenceFeedback input : inputs) {
			ConferenceFeedback copy = new ConferenceFeedback();
			copy.copy(input);
			l.add(copy);
		}
		return l;
	}
	   
	private List<ConferenceFeedback> getLoadGraphOneLevel (ConferenceFeedback graphMaskWhat, List<ConferenceFeedback> parents) {
	    return loadGraphFromParentKey (graphMaskWhat, parents);
	} 
	
	public List<ConferenceFeedback> loadGraphFromParentKey (ConferenceFeedback graphMaskWhat, List<ConferenceFeedback> parents) {
		//foreach children:
		//check if not empty take first
		parents = copy (parents); //working with detached entities to avoid unnecessary sql calls
		if (parents==null || parents.isEmpty())
		   return parents;
		List<String> ids = getPk (parents);
		return parents;
	}
	
	private List<String> getPk(List<ConferenceFeedback> input) {
		List<String> s = new ArrayList<String>();
		for (ConferenceFeedback t : input) {
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
