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
import net.sf.mp.demo.conference.dao.face.conference.EvaluationExtDao;
import net.sf.mp.demo.conference.domain.conference.Evaluation;
import net.sf.mp.demo.conference.dao.impl.jpa.conference.EvaluationJPAImpl;

import net.sf.mp.demo.conference.dao.impl.jpa.conference.ConferenceMemberExtendedJPAImpl;
import net.sf.mp.demo.conference.dao.impl.jpa.conference.PresentationExtendedJPAImpl;
/**
 *
 * <p>Title: EvaluationExtendedJPAImpl</p>
 *
 * <p>Description: Interface of a Data access object dealing with EvaluationExtendedJPAImpl
 * persistence. It offers a set of methods which allow for saving,
 * deleting and searching EvaluationExtendedJPAImpl objects</p>
 *
 */

public class EvaluationExtendedJPAImpl extends EvaluationJPAImpl implements EvaluationExtDao {

    private Logger log = Logger.getLogger(this.getClass());
    
    private SimpleCache simpleCache = new SimpleCache();
    private EntityManager emForRecursiveDao; // dao that needs other dao in a recursive manner not support by spring configuration

    public EvaluationExtendedJPAImpl () {}

    /**
     * generic to move after in superclass
     */
    public EvaluationExtendedJPAImpl (EntityManager emForRecursiveDao) {
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
     * Inserts a Evaluation entity with cascade of its children
     * @param Evaluation evaluation
     */
    public void insertEvaluationWithCascade(Evaluation evaluation) {
    	EvaluationExtendedJPAImpl evaluationextendedjpaimpl = new EvaluationExtendedJPAImpl(getEntityManager());
    	evaluationextendedjpaimpl.insertEvaluationWithCascade(evaluationextendedjpaimpl.getEntityManagerForRecursiveDao(), evaluation);
    }
     
    public void insertEvaluationWithCascade(EntityManager emForRecursiveDao, Evaluation evaluation) {
       insertEvaluation(emForRecursiveDao, evaluation);
    }
        
    /**
     * Inserts a list of Evaluation entity with cascade of its children
     * @param List<Evaluation> evaluations
     */
    public void insertEvaluationsWithCascade(List<Evaluation> evaluations) {
       for (Evaluation evaluation : evaluations) {
          insertEvaluationWithCascade(evaluation);
       }
    } 
        
    /**
     * lookup Evaluation entity Evaluation, criteria and max result number
     */
    public List<Evaluation> lookupEvaluation(Evaluation evaluation, Criteria criteria, Integer numberOfResult, EntityManager em) {
		boolean isWhereSet = false;
        StringBuffer query = new StringBuffer();
        query.append ("SELECT evaluation FROM Evaluation evaluation ");
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
    
    public List<Evaluation> lookupEvaluation(Evaluation evaluation, Criteria criteria, Integer numberOfResult) {
		return lookupEvaluation(evaluation, criteria, numberOfResult, getEntityManager());
    }

    public Integer updateNotNullOnlyEvaluation (Evaluation evaluation, Criteria criteria) {
        String queryWhat = getUpdateNotNullOnlyEvaluationQueryChunkPrototype (evaluation);
        StringBuffer query = new StringBuffer (queryWhat);
        boolean isWhereSet = false;
        for (Criterion criterion : criteria.getClauseCriterions()) {
            query.append (getQueryWHERE_AND (isWhereSet));
            isWhereSet = true;   
            query.append(criterion.getExpression());			
        }  
        Query jpaQuery = getEntityManager().createQuery(query.toString());
        isWhereSet = false;
        if (evaluation.getId() != null) {
           jpaQuery.setParameter ("id", evaluation.getId());
        }   
        if (evaluation.getConferenceMemberId() != null) {
           jpaQuery.setParameter ("conferenceMemberId", evaluation.getConferenceMemberId());
        }   
        if (evaluation.getPresentationId() != null) {
           jpaQuery.setParameter ("presentationId", evaluation.getPresentationId());
        }   
        if (evaluation.getStar() != null) {
           jpaQuery.setParameter ("star", evaluation.getStar());
        }   
        if (evaluation.getComment() != null) {
           jpaQuery.setParameter ("comment", evaluation.getComment());
        }   
        if (evaluation.getEvaluationDate() != null) {
           jpaQuery.setParameter ("evaluationDate", evaluation.getEvaluationDate());
        }   
		return jpaQuery.executeUpdate();           
    }
	
	public Evaluation affectEvaluation (Evaluation evaluation) {
	    return referEvaluation (evaluation, false);		    
	}
		
	/**
	 * Assign the first evaluation retrieved corresponding to the evaluation criteria.
	 * Blank criteria are mapped to null.
	 * If no criteria is found, null is returned.
	 * If there is no evaluation corresponding in the database. Then evaluation is inserted and returned with its primary key(s). 
	 */
	public Evaluation assignEvaluation (Evaluation evaluation) {
	    return referEvaluation (evaluation, true);	
	}
	
	public Evaluation referEvaluation (Evaluation evaluation, boolean isAssign) {
		evaluation = assignBlankToNull (evaluation);
		if (isAllNull(evaluation))
			return null;
		else {
			List<Evaluation> list = searchPrototypeEvaluation(evaluation);
			if (list.isEmpty()) {
			    if (isAssign)
			       insertEvaluation(evaluation);
			    else
				   return null;
			}
			else if (list.size()==1)
				evaluation.copy(list.get(0));
			else 
				//TODO log error
				evaluation.copy(list.get(0));
		}
		return evaluation;		    
	}

   public Evaluation assignEvaluationUseCache (Evaluation evaluation) {
      return referEvaluationUseCache (evaluation, true);
   }
      		
   public Evaluation affectEvaluationUseCache (Evaluation evaluation) {
      return referEvaluationUseCache (evaluation, false);
   }
      		
   public Evaluation referEvaluationUseCache (Evaluation evaluation, boolean isAssign) {
	  String key = getCacheKey(null, evaluation, null, "assignEvaluation");
      Evaluation evaluationCache = (Evaluation)simpleCache.get(key);
      if (evaluationCache==null) {
         evaluationCache = referEvaluation (evaluation, isAssign);
         if (key!=null)
         	simpleCache.put(key, evaluationCache);
      }
      evaluation.copy(evaluationCache);
      return evaluationCache;
   }	

	private String getCacheKey (Evaluation evaluationWhat, Evaluation positiveEvaluation, Evaluation negativeEvaluation, String queryKey) {
	    StringBuffer sb = new StringBuffer();
	    sb.append(queryKey);
	    if (evaluationWhat!=null)
	       sb.append(evaluationWhat.toStringWithParents());
	    if (positiveEvaluation!=null)
	       sb.append(positiveEvaluation.toStringWithParents());
	    if (negativeEvaluation!=null)
	       sb.append(negativeEvaluation.toStringWithParents());
	    return sb.toString();
	}
	
    public Evaluation partialLoadWithParentFirstEvaluation(Evaluation evaluationWhat, Evaluation positiveEvaluation, Evaluation negativeEvaluation){
		List <Evaluation> list = partialLoadWithParentEvaluation(evaluationWhat, positiveEvaluation, negativeEvaluation);
		return (!list.isEmpty())?(Evaluation)list.get(0):null;
    }
    
    public Evaluation partialLoadWithParentFirstEvaluationUseCache(Evaluation evaluationWhat, Evaluation positiveEvaluation, Evaluation negativeEvaluation, Boolean useCache){
		List <Evaluation> list = partialLoadWithParentEvaluationUseCache(evaluationWhat, positiveEvaluation, negativeEvaluation, useCache);
		return (!list.isEmpty())?(Evaluation)list.get(0):null;
    }
	
	public Evaluation partialLoadWithParentFirstEvaluationUseCacheOnResult(Evaluation evaluationWhat, Evaluation positiveEvaluation, Evaluation negativeEvaluation, Boolean useCache){
		List <Evaluation> list = partialLoadWithParentEvaluationUseCacheOnResult(evaluationWhat, positiveEvaluation, negativeEvaluation, useCache);
		return (!list.isEmpty())?(Evaluation)list.get(0):null;
    }
	//
	protected List<Evaluation> partialLoadWithParentEvaluation(Evaluation evaluationWhat, Evaluation positiveEvaluation, Evaluation negativeEvaluation, Integer nbOfResult, Boolean useCache) {
		 return partialLoadWithParentEvaluation(evaluationWhat, positiveEvaluation, negativeEvaluation, new QuerySelectInit(), nbOfResult, useCache);
	}	  

	protected List partialLoadWithParentEvaluationQueryResult (Evaluation evaluationWhat, Evaluation positiveEvaluation, Evaluation negativeEvaluation, Integer nbOfResult, Boolean useCache) {
		 return partialLoadWithParentEvaluationQueryResult (evaluationWhat, positiveEvaluation, negativeEvaluation, new QuerySelectInit(), nbOfResult, useCache);
	}	
    
    public List<Evaluation> getDistinctEvaluation(Evaluation evaluationWhat, Evaluation positiveEvaluation, Evaluation negativeEvaluation) {
		 return partialLoadWithParentEvaluation(evaluationWhat, positiveEvaluation, negativeEvaluation, new QuerySelectDistinctInit(), null, false);
	}
	
	public List<Evaluation> partialLoadWithParentEvaluation(Evaluation evaluationWhat, Evaluation positiveEvaluation, Evaluation negativeEvaluation) {
		 return partialLoadWithParentEvaluation(evaluationWhat, positiveEvaluation, negativeEvaluation, new QuerySelectInit(), null, false);
	}	
  
	public List<Evaluation> partialLoadWithParentEvaluationUseCacheOnResult(Evaluation evaluationWhat, Evaluation positiveEvaluation, Evaluation negativeEvaluation, Boolean useCache) {
		String key = getCacheKey(evaluationWhat, positiveEvaluation, negativeEvaluation, "partialLoadWithParentEvaluation");
		List<Evaluation> list = (List<Evaluation>)simpleCache.get(key);
		if (list==null || list.isEmpty()) {
			list = partialLoadWithParentEvaluation(evaluationWhat, positiveEvaluation, negativeEvaluation);
			if (!list.isEmpty())
			   simpleCache.put(key, list);
		}
		return list;	
	}	

	public List<Evaluation> partialLoadWithParentEvaluationUseCache(Evaluation evaluationWhat, Evaluation positiveEvaluation, Evaluation negativeEvaluation, Boolean useCache) {
		String key = getCacheKey(evaluationWhat, positiveEvaluation, negativeEvaluation, "partialLoadWithParentEvaluation");
		List<Evaluation> list = (List<Evaluation>)simpleCache.get(key);
		if (list==null) {
			list = partialLoadWithParentEvaluation(evaluationWhat, positiveEvaluation, negativeEvaluation);
			simpleCache.put(key, list);
		}
		return list;	
	}	
	
	private List<Evaluation> handleLoadWithParentEvaluation(Map beanPath, List list, Evaluation evaluationWhat) {
	    return handleLoadWithParentEvaluation(beanPath, list, evaluationWhat, true);  
	}
	
	private List<Evaluation> handleLoadWithParentEvaluation(Map beanPath, List list, Evaluation evaluationWhat, boolean isHql) {
		if (beanPath.size()==1) {
			return handlePartialLoadWithParentEvaluationWithOneElementInRow(list, beanPath, evaluationWhat, isHql);
		}
		return handlePartialLoadWithParentEvaluation(list, beanPath, evaluationWhat, isHql);	
	}
	
    	// to set in super class	
	protected void populateEvaluation (Evaluation evaluation, Object value, String beanPath) throws IllegalAccessException, InvocationTargetException, NoSuchMethodException {
	   BeanUtils.populateBeanObject(evaluation, beanPath, value);
	}
	
	protected void populateEvaluationFromSQL (Evaluation evaluation, Object value, String beanPath) throws IllegalAccessException, InvocationTargetException, NoSuchMethodException {
	   BeanUtils.populateBeanObject(evaluation, beanPath, value);
	}
    	// to set in super class BEWARE: genericity is only one level!!!!! first level is a copy second level is a reference!!! change to evaluation.clone() instead
	private Evaluation cloneEvaluation (Evaluation evaluation) throws IllegalAccessException, InvocationTargetException, InstantiationException, NoSuchMethodException {
		//return (Evaluation) BeanUtils.cloneBeanObject(evaluation);
	   if (evaluation==null) return new Evaluation();
	   return evaluation.clone();
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
	
    public List<Evaluation> countDistinct (Evaluation whatMask, Evaluation whereEqCriteria) {
       return partialLoadWithParentEvaluation(whatMask, whereEqCriteria, null, new QuerySelectCountInit("evaluation"), null, false);
    }   
    	
    public Long count(Evaluation whereEqCriteria) {
        List<Evaluation> list = partialLoadWithParentEvaluation(null, whereEqCriteria, null, new QueryCountInit("evaluation"), null, false);    
    	if (!list.isEmpty())
    		return list.get(0).getCount__();
    	return 0L;
    }
		
		  		
   public Evaluation getFirstEvaluationWhereConditionsAre (Evaluation evaluation) {
      List<Evaluation> list = partialLoadWithParentEvaluation(getDefaultEvaluationWhat(), evaluation, null, 1, false);
      if (list.isEmpty()) {
         return null;
      }
      else if (list.size()==1)
         return list.get(0);
      else 
      //TODO log error
         return list.get(0);	
	}

   private List getFirstResultWhereConditionsAre (Evaluation evaluation) {
      return  partialLoadWithParentEvaluationQueryResult(getDefaultEvaluationWhat(), evaluation, null, 1, false);	
   }
   
   protected Evaluation getDefaultEvaluationWhat() {
      Evaluation evaluation = new Evaluation();
      evaluation.setId(Long.valueOf("-1"));
      return evaluation;
   }
   
	public Evaluation getFirstEvaluation (Evaluation evaluation) {
		if (isAllNull(evaluation))
			return null;
		else {
			List<Evaluation> list = searchPrototype (evaluation, 1);
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
    * checks if the Evaluation entity exists
    */           
    public boolean existsEvaluation (Evaluation evaluation) {
       if (getFirstEvaluation(evaluation)!=null)
          return true;
       return false;  
    }
        
    public boolean existsEvaluationWhereConditionsAre (Evaluation evaluation) {
       if (getFirstResultWhereConditionsAre (evaluation).isEmpty())
          return false;
       return true;  
    }


	
	private int countPartialField (Evaluation evaluation) {
	   int cpt = 0;
       if (evaluation.getId() != null) {
          cpt++;
       }
       if (evaluation.getConferenceMemberId() != null) {
          cpt++;
       }
       if (evaluation.getPresentationId() != null) {
          cpt++;
       }
       if (evaluation.getStar() != null) {
          cpt++;
       }
       if (evaluation.getComment() != null) {
          cpt++;
       }
       if (evaluation.getEvaluationDate() != null) {
          cpt++;
       }
       return cpt;
	}   
  	
	public List<Evaluation> partialLoadWithParentEvaluation(Evaluation evaluationWhat, Evaluation positiveEvaluation, Evaluation negativeEvaluation, QueryWhatInit queryWhatInit, Integer nbOfResult, Boolean useCache) {
		Map beanPath = new Hashtable();
		List list = partialLoadWithParentEvaluationJPAQueryResult (evaluationWhat, positiveEvaluation, negativeEvaluation, queryWhatInit, beanPath, nbOfResult, useCache);
		if (beanPath.size()==1) {
			return handlePartialLoadWithParentEvaluationWithOneElementInRow(list, beanPath, evaluationWhat, true);
		}
		return handlePartialLoadWithParentEvaluation(list, beanPath, evaluationWhat, true);
	}	

	private List partialLoadWithParentEvaluationQueryResult(Evaluation evaluationWhat, Evaluation positiveEvaluation, Evaluation negativeEvaluation, QueryWhatInit queryWhatInit, Integer nbOfResult, Boolean useCache) {
		return partialLoadWithParentEvaluationJPAQueryResult (evaluationWhat, positiveEvaluation, negativeEvaluation, queryWhatInit, new Hashtable(), nbOfResult, useCache);
  }	
  
	private List partialLoadWithParentEvaluationJPAQueryResult(Evaluation evaluationWhat, Evaluation positiveEvaluation, Evaluation negativeEvaluation, QueryWhatInit queryWhatInit, Map beanPath, Integer nbOfResult, Boolean useCache) {
		Query hquery = getPartialLoadWithParentEvaluationJPAQuery (evaluationWhat, positiveEvaluation, negativeEvaluation, beanPath, queryWhatInit, nbOfResult);
		return hquery.getResultList();
  }	
   /**
    * @returns an JPA Hsql query based on entity Evaluation and its parents and the maximum number of result
    */
	protected Query getPartialLoadWithParentEvaluationJPAQuery (Evaluation evaluationWhat, Evaluation positiveEvaluation, Evaluation negativeEvaluation, Map beanPath, QueryWhatInit queryWhatInit, Integer nbOfResult) {
	   Query query = getEntityManager().createQuery(getPartialLoadWithParentEvaluationHsqlQuery (evaluationWhat, positiveEvaluation, negativeEvaluation, beanPath, queryWhatInit));
	   if (nbOfResult!=null)
	      query.setMaxResults(nbOfResult);
	   return query;
  }
  
	private List<Evaluation> handlePartialLoadWithParentEvaluation(List<Object[]> list, Map<Integer, String> beanPath, Evaluation evaluationWhat, boolean isJql) {
		try {
			return convertPartialLoadWithParentEvaluation(list, beanPath, evaluationWhat);
		} catch (Exception ex) {
			log.error("Error conversion list from handlePartialLoadWithParentEvaluation, message:"+ex.getMessage());
			return new ArrayList<Evaluation>();
		}
    }

	private List<Evaluation> handlePartialLoadWithParentEvaluationWithOneElementInRow(List<Object> list, Map<Integer, String> beanPath, Evaluation evaluationWhat, boolean isJql) {
		try {
			return convertPartialLoadWithParentEvaluationWithOneElementInRow(list, beanPath, evaluationWhat);
		} catch (Exception ex) {
			log.error("Error conversion list from handlePartialLoadWithParentEvaluationWithOneElementInRow, message:"+ex.getMessage());
			return new ArrayList<Evaluation>();
		}
    }
    	
	 private List<Evaluation> convertPartialLoadWithParentEvaluation(List<Object[]> list, Map<Integer, String> beanPath, Evaluation evaluationWhat) throws IllegalAccessException, InvocationTargetException, InstantiationException, NoSuchMethodException {
		 List<Evaluation> resultList = new ArrayList<Evaluation>();
		 for (Object[] row : list) {		
		    Evaluation evaluation = cloneEvaluation (evaluationWhat);
		    Iterator<Entry<Integer, String>> iter = beanPath.entrySet().iterator();	
		    while (iter.hasNext()) {
		       Entry entry = iter.next();
		       populateEvaluation (evaluation, row[(Integer)entry.getKey()], (String)entry.getValue());
		    }
		    resultList.add(evaluation);
		 }
		 return resultList;		
	 }	
    
	 private List<Evaluation> convertPartialLoadWithParentEvaluationWithOneElementInRow(List<Object> list, Map<Integer, String> beanPath, Evaluation evaluationWhat) throws IllegalAccessException, InvocationTargetException, InstantiationException, NoSuchMethodException {
		 List<Evaluation> resultList = new ArrayList<Evaluation>();
		 for (Object row : list) {		
		    Evaluation evaluation = cloneEvaluation (evaluationWhat);
		    Iterator<Entry<Integer, String>> iter = beanPath.entrySet().iterator();	
		    while (iter.hasNext()) {
		       Entry entry = iter.next();
		       populateEvaluation (evaluation, row, (String)entry.getValue());
		    }
		    resultList.add(evaluation);
		 }		 
		 return resultList;		
	 }
   
	public List partialLoadWithParentForBean(Object bean, Evaluation evaluationWhat, Evaluation positiveEvaluation, Evaluation negativeEvaluation) {
		Map beanPath = new Hashtable();
		Query hquery = getPartialLoadWithParentEvaluationJPAQuery (evaluationWhat, positiveEvaluation, negativeEvaluation, beanPath, new QuerySelectInit(), null);
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
	public String getPartialLoadWithParentEvaluationHsqlQuery (Evaluation evaluation, Evaluation positiveEvaluation, Evaluation negativeEvaluation, Map beanPath, QueryWhatInit queryWhatInit) {
		Hashtable aliasWhatHt = new Hashtable();
		String what = getPartialLoadWithParentEvaluationQuery (evaluation, null, aliasWhatHt, null, null, beanPath, "", queryWhatInit);
		Hashtable aliasWhereHt = new Hashtable();
		String where = getPartialLoadWithParentWhereEvaluationQuery (positiveEvaluation, null, aliasWhatHt, aliasWhereHt, null, null);
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
	public List<Evaluation> partialLoadEvaluation(Evaluation evaluation, Evaluation positiveEvaluation, Evaluation negativeEvaluation) {
	    Query hquery = getEntityManager().createQuery(getPartialLoadEvaluationQuery (evaluation, positiveEvaluation, negativeEvaluation));
		int countPartialField = countPartialField(evaluation);
		if (countPartialField==0) 
		   return new ArrayList<Evaluation>();
		List list = hquery.getResultList();
		Iterator iter = list.iterator();
		List<Evaluation> returnList = new ArrayList<Evaluation>();
		while(iter.hasNext()) {
	       int index = 0;
	       Object[] row;
	       if (countPartialField==1) {
	    	  row = new Object[1];
	    	  row[0] = iter.next();
	       } else 
		      row = (Object[]) iter.next();
		   Evaluation evaluationResult = new Evaluation();
           if (evaluation.getId() != null) {
			  evaluationResult.setId((Long) row[index]);
			  index++;
           }
           if (evaluation.getConferenceMemberId() != null) {
			  evaluationResult.setConferenceMemberId_((Long) row[index]);
			  index++;
           }
           if (evaluation.getPresentationId() != null) {
			  evaluationResult.setPresentationId_((Long) row[index]);
			  index++;
           }
           if (evaluation.getStar() != null) {
			  evaluationResult.setStar((Integer) row[index]);
			  index++;
           }
           if (evaluation.getComment() != null) {
			  evaluationResult.setComment((String) row[index]);
			  index++;
           }
           if (evaluation.getEvaluationDate() != null) {
			  evaluationResult.setEvaluationDate((Timestamp) row[index]);
			  index++;
           }
           returnList.add(evaluationResult);
        }
	    return returnList;
	}

	public static String getPartialLoadWithParentWhereEvaluationQuery (
	   Evaluation evaluation, Boolean isWhereSet, Hashtable aliasHt, Hashtable aliasWhereHt, String childAlias, String childFKAlias) {
	   if (evaluation==null)
	      return "";
	   String alias = null;
	   if (aliasWhereHt == null) {
	      aliasWhereHt = new Hashtable();
	   } 
	   if (isLookedUp(evaluation)){
	      alias = getNextAlias (aliasWhereHt, evaluation);
		  aliasWhereHt.put (getAliasKey(alias), getAliasConnection(alias, childAlias, childFKAlias));
	   }
	   if (isWhereSet == null)
          isWhereSet = false;
       StringBuffer query = new StringBuffer();
       if (evaluation.getId() != null) {
           query.append (getQueryBLANK_AND (isWhereSet));
		   isWhereSet = true;
           query.append(" "+alias+".id = "+ evaluation.getId() + " ");
       }
       if (evaluation.getConferenceMemberId() != null) {
           query.append (getQueryBLANK_AND (isWhereSet));
		   isWhereSet = true;
           query.append(" "+alias+".conferenceMemberId = "+ evaluation.getConferenceMemberId() + " ");
       }
       if (evaluation.getPresentationId() != null) {
           query.append (getQueryBLANK_AND (isWhereSet));
		   isWhereSet = true;
           query.append(" "+alias+".presentationId = "+ evaluation.getPresentationId() + " ");
       }
       if (evaluation.getStar() != null) {
           query.append (getQueryBLANK_AND (isWhereSet));
		   isWhereSet = true;
           query.append(" "+alias+".star = "+ evaluation.getStar() + " ");
       }
       if (evaluation.getComment() != null) {
           query.append (getQueryBLANK_AND (isWhereSet));
		   isWhereSet = true;
           query.append(" "+alias+".comment = '"+ evaluation.getComment()+"' ");
       }
       if (evaluation.getEvaluationDate() != null) {
           query.append (getQueryBLANK_AND (isWhereSet));
		   isWhereSet = true;
           query.append(" "+alias+".evaluationDate = '"+ evaluation.getEvaluationDate()+"' ");
       }
       if (evaluation.getConferenceMemberId()!=null) {
	      String chunck = net.sf.mp.demo.conference.dao.impl.jpa.conference.ConferenceMemberExtendedJPAImpl.getPartialLoadWithParentWhereConferenceMemberQuery(
		      evaluation.getConferenceMemberId(), 
			  isWhereSet, aliasHt, aliasWhereHt, alias, "conferenceMemberId");
		  if (chunck!=null && !chunck.equals("")) {
		     query.append(chunck);
		     isWhereSet=true;
		  }  	  
	   }
       if (evaluation.getPresentationId()!=null) {
	      String chunck = net.sf.mp.demo.conference.dao.impl.jpa.conference.PresentationExtendedJPAImpl.getPartialLoadWithParentWherePresentationQuery(
		      evaluation.getPresentationId(), 
			  isWhereSet, aliasHt, aliasWhereHt, alias, "presentationId");
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
	
    public static String getPartialLoadWithParentEvaluationQuery (
	   Evaluation evaluation, Boolean isWhereSet, Hashtable aliasHt, String childAlias, String childFKAlias, Map beanPath, String rootPath, QueryWhatInit queryWhatInit) {
	   if (evaluation==null)
	      return "";
	   String alias = null;
	   if (aliasHt == null) {
	      aliasHt = new Hashtable();
	   } 
	   if (isLookedUp(evaluation)){
	      alias = getNextAlias (aliasHt, evaluation);
		  aliasHt.put (getAliasKey(alias), getAliasConnection(alias, childAlias, childFKAlias));
	   }
	   if (isWhereSet == null)
          isWhereSet = false;
       StringBuffer query = new StringBuffer();
       if (evaluation.getId() != null) {
          query.append (queryWhatInit.getWhatInit (isWhereSet));
          isWhereSet = true; 
          beanPath.put(beanPath.size(), rootPath+"id");
          query.append(" "+alias+".id ");
       }
       if (evaluation.getConferenceMemberId() != null) {
          query.append (queryWhatInit.getWhatInit (isWhereSet));
          isWhereSet = true; 
          beanPath.put(beanPath.size(), rootPath+"conferenceMemberId");
          query.append(" "+alias+".conferenceMemberId ");
       }
       if (evaluation.getPresentationId() != null) {
          query.append (queryWhatInit.getWhatInit (isWhereSet));
          isWhereSet = true; 
          beanPath.put(beanPath.size(), rootPath+"presentationId");
          query.append(" "+alias+".presentationId ");
       }
       if (evaluation.getStar() != null) {
          query.append (queryWhatInit.getWhatInit (isWhereSet));
          isWhereSet = true; 
          beanPath.put(beanPath.size(), rootPath+"star");
          query.append(" "+alias+".star ");
       }
       if (evaluation.getComment() != null) {
          query.append (queryWhatInit.getWhatInit (isWhereSet));
          isWhereSet = true; 
          beanPath.put(beanPath.size(), rootPath+"comment");
          query.append(" "+alias+".comment ");
       }
       if (evaluation.getEvaluationDate() != null) {
          query.append (queryWhatInit.getWhatInit (isWhereSet));
          isWhereSet = true; 
          beanPath.put(beanPath.size(), rootPath+"evaluationDate");
          query.append(" "+alias+".evaluationDate ");
       }
       if (evaluation.getConferenceMemberId()!=null) {
	      String chunck = net.sf.mp.demo.conference.dao.impl.jpa.conference.ConferenceMemberExtendedJPAImpl.getPartialLoadWithParentConferenceMemberQuery(
		      evaluation.getConferenceMemberId(), 
			  isWhereSet, aliasHt, alias, "conferenceMemberId", beanPath, rootPath+"conferenceMemberId.", queryWhatInit);
		  if (chunck!=null && !chunck.equals("")) {
		     query.append(chunck);
		     isWhereSet=true;
		  } 
	   }  
       if (evaluation.getPresentationId()!=null) {
	      String chunck = net.sf.mp.demo.conference.dao.impl.jpa.conference.PresentationExtendedJPAImpl.getPartialLoadWithParentPresentationQuery(
		      evaluation.getPresentationId(), 
			  isWhereSet, aliasHt, alias, "presentationId", beanPath, rootPath+"presentationId.", queryWhatInit);
		  if (chunck!=null && !chunck.equals("")) {
		     query.append(chunck);
		     isWhereSet=true;
		  } 
	   }  
//       query.append(getEvaluationSearchEqualQuery (positiveEvaluation, negativeEvaluation));
	   return query.toString(); 
    }
	
	protected static String getAliasConnection(String existingAlias, String childAlias, String childFKAlias) {
		if (childAlias==null)
		   return "";
		return childAlias+"."+childFKAlias+" = "+existingAlias+"."+"id";
	}
	
	protected static String getAliasKey (String alias) {
	  //TODO this is a temporary solution use a dedicated object in BslaHiberateDaoSupport
		return "Evaluation|"+alias;
	}
	
	protected static String getAliasKeyAlias (String aliasKey) {
	  //TODO this is a temporary solution use a dedicated object in BslaHiberateDaoSupport
		return StringUtils.substringAfter(aliasKey, "|");
	}
	
	protected static String getAliasKeyDomain (String aliasKey) {
	  //TODO this is a temporary solution use a dedicated object in BslaHiberateDaoSupport
	  return StringUtils.substringBefore(aliasKey, "|");
	}
	
	protected static String getNextAlias (Hashtable aliasHt, Evaluation evaluation) {
		int cptSameAlias = 0;
		Enumeration<String> keys = aliasHt.keys();
		while (keys.hasMoreElements()) {
			String key = keys.nextElement();
			if (key.startsWith("evaluation"))
				cptSameAlias++;
		}
		if (cptSameAlias==0)
			return "evaluation";
		else
			return "evaluation_"+cptSameAlias;
	}
	
	
	protected static boolean isLookedUp (Evaluation evaluation) {
	   if (evaluation==null)
		  return false;
       if (evaluation.getId() != null) {
	      return true;
       }
       if (evaluation.getConferenceMemberId() != null) {
	      return true;
       }
       if (evaluation.getPresentationId() != null) {
	      return true;
       }
       if (evaluation.getStar() != null) {
	      return true;
       }
       if (evaluation.getComment() != null) {
	      return true;
       }
       if (evaluation.getEvaluationDate() != null) {
	      return true;
       }
       if (evaluation.getConferenceMemberId()!=null) {
	      return true;
	   }  
       if (evaluation.getPresentationId()!=null) {
	      return true;
	   }  
       return false;   
	}
	
    public String getPartialLoadEvaluationQuery(
	   Evaluation evaluation, 
	   Evaluation positiveEvaluation, 
	   Evaluation negativeEvaluation) {
       boolean isWhereSet = false;
       StringBuffer query = new StringBuffer();
       if (evaluation.getId() != null) {
          query.append (getQuerySelectComma (isWhereSet));
          isWhereSet = true; 
          query.append(" id ");
       }
       if (evaluation.getConferenceMemberId() != null) {
          query.append (getQuerySelectComma (isWhereSet));
          isWhereSet = true; 
          query.append(" conferenceMemberId ");
       }
       if (evaluation.getPresentationId() != null) {
          query.append (getQuerySelectComma (isWhereSet));
          isWhereSet = true; 
          query.append(" presentationId ");
       }
       if (evaluation.getStar() != null) {
          query.append (getQuerySelectComma (isWhereSet));
          isWhereSet = true; 
          query.append(" star ");
       }
       if (evaluation.getComment() != null) {
          query.append (getQuerySelectComma (isWhereSet));
          isWhereSet = true; 
          query.append(" comment ");
       }
       if (evaluation.getEvaluationDate() != null) {
          query.append (getQuerySelectComma (isWhereSet));
          isWhereSet = true; 
          query.append(" evaluationDate ");
       }
       query.append(getEvaluationSearchEqualQuery (positiveEvaluation, negativeEvaluation));
	   return query.toString(); 
    }
	
	public List<Evaluation> searchPrototypeWithCacheEvaluation(Evaluation evaluation) {
		SimpleCache simpleCache = new SimpleCache();
		List<Evaluation> list = (List<Evaluation>)simpleCache.get(evaluation.toString());
		if (list==null) {
			list = searchPrototypeEvaluation(evaluation);
			simpleCache.put(evaluation.toString(), list);
		}
		return list;
	}

    public List<Evaluation> loadGraph(Evaluation graphMaskWhat, List<Evaluation> whereMask) {
        return loadGraphOneLevel(graphMaskWhat, whereMask);
    }
 
    public List<Evaluation> loadGraphOneLevel(Evaluation graphMaskWhat, List<Evaluation> whereMask) {
        //first get roots element from where list mask
        // this brings the level 0 of the graph (root level)
        graphMaskWhat.setId(graphMaskWhat.longMask__);
        List<Evaluation> evaluations = searchPrototypeEvaluation (whereMask);
        // for each sub level perform the search with a subquery then reassemble
        // 1. get all sublevel queries
        // 2. perform queries on the correct dao
        // 3. reassemble
        return getLoadGraphOneLevel (graphMaskWhat, evaluations);
    }

	private List<Evaluation> copy(List<Evaluation> inputs) {
		List<Evaluation> l = new ArrayList<Evaluation>();
		for (Evaluation input : inputs) {
			Evaluation copy = new Evaluation();
			copy.copy(input);
			l.add(copy);
		}
		return l;
	}
	   
	private List<Evaluation> getLoadGraphOneLevel (Evaluation graphMaskWhat, List<Evaluation> parents) {
	    return loadGraphFromParentKey (graphMaskWhat, parents);
	} 
	
	public List<Evaluation> loadGraphFromParentKey (Evaluation graphMaskWhat, List<Evaluation> parents) {
		//foreach children:
		//check if not empty take first
		parents = copy (parents); //working with detached entities to avoid unnecessary sql calls
		if (parents==null || parents.isEmpty())
		   return parents;
		List<String> ids = getPk (parents);
		return parents;
	}
	
	private List<String> getPk(List<Evaluation> input) {
		List<String> s = new ArrayList<String>();
		for (Evaluation t : input) {
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
