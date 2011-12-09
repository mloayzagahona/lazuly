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
import net.sf.mp.demo.conference.dao.face.conference.PresentationExtDao;
import net.sf.mp.demo.conference.domain.conference.Presentation;
import net.sf.mp.demo.conference.dao.impl.jpa.conference.PresentationJPAImpl;

import net.sf.mp.demo.conference.domain.conference.Evaluation;
import net.sf.mp.demo.conference.dao.impl.jpa.conference.EvaluationExtendedJPAImpl;
import net.sf.mp.demo.conference.dao.impl.jpa.conference.PresentationPlaceExtendedJPAImpl;
/**
 *
 * <p>Title: PresentationExtendedJPAImpl</p>
 *
 * <p>Description: Interface of a Data access object dealing with PresentationExtendedJPAImpl
 * persistence. It offers a set of methods which allow for saving,
 * deleting and searching PresentationExtendedJPAImpl objects</p>
 *
 */

public class PresentationExtendedJPAImpl extends PresentationJPAImpl implements PresentationExtDao {

    private Logger log = Logger.getLogger(this.getClass());
    
    private SimpleCache simpleCache = new SimpleCache();
    private EvaluationExtendedJPAImpl evaluationextendedjpaimpl;
    private EntityManager emForRecursiveDao; // dao that needs other dao in a recursive manner not support by spring configuration

    public PresentationExtendedJPAImpl () {}

    /**
     * generic to move after in superclass
     */
    public PresentationExtendedJPAImpl (EntityManager emForRecursiveDao) {
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
     * Inserts a Presentation entity with cascade of its children
     * @param Presentation presentation
     */
    public void insertPresentationWithCascade(Presentation presentation) {
    	PresentationExtendedJPAImpl presentationextendedjpaimpl = new PresentationExtendedJPAImpl(getEntityManager());
    	presentationextendedjpaimpl.insertPresentationWithCascade(presentationextendedjpaimpl.getEntityManagerForRecursiveDao(), presentation);
    }
     
    public void insertPresentationWithCascade(EntityManager emForRecursiveDao, Presentation presentation) {
       insertPresentation(emForRecursiveDao, presentation);
       if (!presentation.getEvaluationPresentationViaPresentationId().isEmpty()) {
          EvaluationExtendedJPAImpl evaluationextendedjpaimpl = new EvaluationExtendedJPAImpl (emForRecursiveDao);
          for (Evaluation _evaluationPresentationViaPresentationId : presentation.getEvaluationPresentationViaPresentationId()) {
             evaluationextendedjpaimpl.insertEvaluationWithCascade(emForRecursiveDao, _evaluationPresentationViaPresentationId);
          }
       } 
    }
        
    /**
     * Inserts a list of Presentation entity with cascade of its children
     * @param List<Presentation> presentations
     */
    public void insertPresentationsWithCascade(List<Presentation> presentations) {
       for (Presentation presentation : presentations) {
          insertPresentationWithCascade(presentation);
       }
    } 
        
    /**
     * lookup Presentation entity Presentation, criteria and max result number
     */
    public List<Presentation> lookupPresentation(Presentation presentation, Criteria criteria, Integer numberOfResult, EntityManager em) {
		boolean isWhereSet = false;
        StringBuffer query = new StringBuffer();
        query.append ("SELECT presentation FROM Presentation presentation ");
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
    
    public List<Presentation> lookupPresentation(Presentation presentation, Criteria criteria, Integer numberOfResult) {
		return lookupPresentation(presentation, criteria, numberOfResult, getEntityManager());
    }

    public Integer updateNotNullOnlyPresentation (Presentation presentation, Criteria criteria) {
        String queryWhat = getUpdateNotNullOnlyPresentationQueryChunkPrototype (presentation);
        StringBuffer query = new StringBuffer (queryWhat);
        boolean isWhereSet = false;
        for (Criterion criterion : criteria.getClauseCriterions()) {
            query.append (getQueryWHERE_AND (isWhereSet));
            isWhereSet = true;   
            query.append(criterion.getExpression());			
        }  
        Query jpaQuery = getEntityManager().createQuery(query.toString());
        isWhereSet = false;
        if (presentation.getId() != null) {
           jpaQuery.setParameter ("id", presentation.getId());
        }   
        if (presentation.getStartTime() != null) {
           jpaQuery.setParameter ("startTime", presentation.getStartTime());
        }   
        if (presentation.getEndTime() != null) {
           jpaQuery.setParameter ("endTime", presentation.getEndTime());
        }   
        if (presentation.getAbstractName() != null) {
           jpaQuery.setParameter ("abstractName", presentation.getAbstractName());
        }   
        if (presentation.getTitle() != null) {
           jpaQuery.setParameter ("title", presentation.getTitle());
        }   
        if (presentation.getStatus() != null) {
           jpaQuery.setParameter ("status", presentation.getStatus());
        }   
        if (presentation.getPresentationPlaceId() != null) {
           jpaQuery.setParameter ("presentationPlaceId", presentation.getPresentationPlaceId());
        }   
        if (presentation.getProposalTime() != null) {
           jpaQuery.setParameter ("proposalTime", presentation.getProposalTime());
        }   
		return jpaQuery.executeUpdate();           
    }
	
	public Presentation affectPresentation (Presentation presentation) {
	    return referPresentation (presentation, false);		    
	}
		
	/**
	 * Assign the first presentation retrieved corresponding to the presentation criteria.
	 * Blank criteria are mapped to null.
	 * If no criteria is found, null is returned.
	 * If there is no presentation corresponding in the database. Then presentation is inserted and returned with its primary key(s). 
	 */
	public Presentation assignPresentation (Presentation presentation) {
	    return referPresentation (presentation, true);	
	}
	
	public Presentation referPresentation (Presentation presentation, boolean isAssign) {
		presentation = assignBlankToNull (presentation);
		if (isAllNull(presentation))
			return null;
		else {
			List<Presentation> list = searchPrototypePresentation(presentation);
			if (list.isEmpty()) {
			    if (isAssign)
			       insertPresentation(presentation);
			    else
				   return null;
			}
			else if (list.size()==1)
				presentation.copy(list.get(0));
			else 
				//TODO log error
				presentation.copy(list.get(0));
		}
		return presentation;		    
	}

   public Presentation assignPresentationUseCache (Presentation presentation) {
      return referPresentationUseCache (presentation, true);
   }
      		
   public Presentation affectPresentationUseCache (Presentation presentation) {
      return referPresentationUseCache (presentation, false);
   }
      		
   public Presentation referPresentationUseCache (Presentation presentation, boolean isAssign) {
	  String key = getCacheKey(null, presentation, null, "assignPresentation");
      Presentation presentationCache = (Presentation)simpleCache.get(key);
      if (presentationCache==null) {
         presentationCache = referPresentation (presentation, isAssign);
         if (key!=null)
         	simpleCache.put(key, presentationCache);
      }
      presentation.copy(presentationCache);
      return presentationCache;
   }	

	private String getCacheKey (Presentation presentationWhat, Presentation positivePresentation, Presentation negativePresentation, String queryKey) {
	    StringBuffer sb = new StringBuffer();
	    sb.append(queryKey);
	    if (presentationWhat!=null)
	       sb.append(presentationWhat.toStringWithParents());
	    if (positivePresentation!=null)
	       sb.append(positivePresentation.toStringWithParents());
	    if (negativePresentation!=null)
	       sb.append(negativePresentation.toStringWithParents());
	    return sb.toString();
	}
	
    public Presentation partialLoadWithParentFirstPresentation(Presentation presentationWhat, Presentation positivePresentation, Presentation negativePresentation){
		List <Presentation> list = partialLoadWithParentPresentation(presentationWhat, positivePresentation, negativePresentation);
		return (!list.isEmpty())?(Presentation)list.get(0):null;
    }
    
    public Presentation partialLoadWithParentFirstPresentationUseCache(Presentation presentationWhat, Presentation positivePresentation, Presentation negativePresentation, Boolean useCache){
		List <Presentation> list = partialLoadWithParentPresentationUseCache(presentationWhat, positivePresentation, negativePresentation, useCache);
		return (!list.isEmpty())?(Presentation)list.get(0):null;
    }
	
	public Presentation partialLoadWithParentFirstPresentationUseCacheOnResult(Presentation presentationWhat, Presentation positivePresentation, Presentation negativePresentation, Boolean useCache){
		List <Presentation> list = partialLoadWithParentPresentationUseCacheOnResult(presentationWhat, positivePresentation, negativePresentation, useCache);
		return (!list.isEmpty())?(Presentation)list.get(0):null;
    }
	//
	protected List<Presentation> partialLoadWithParentPresentation(Presentation presentationWhat, Presentation positivePresentation, Presentation negativePresentation, Integer nbOfResult, Boolean useCache) {
		 return partialLoadWithParentPresentation(presentationWhat, positivePresentation, negativePresentation, new QuerySelectInit(), nbOfResult, useCache);
	}	  

	protected List partialLoadWithParentPresentationQueryResult (Presentation presentationWhat, Presentation positivePresentation, Presentation negativePresentation, Integer nbOfResult, Boolean useCache) {
		 return partialLoadWithParentPresentationQueryResult (presentationWhat, positivePresentation, negativePresentation, new QuerySelectInit(), nbOfResult, useCache);
	}	
    
    public List<Presentation> getDistinctPresentation(Presentation presentationWhat, Presentation positivePresentation, Presentation negativePresentation) {
		 return partialLoadWithParentPresentation(presentationWhat, positivePresentation, negativePresentation, new QuerySelectDistinctInit(), null, false);
	}
	
	public List<Presentation> partialLoadWithParentPresentation(Presentation presentationWhat, Presentation positivePresentation, Presentation negativePresentation) {
		 return partialLoadWithParentPresentation(presentationWhat, positivePresentation, negativePresentation, new QuerySelectInit(), null, false);
	}	
  
	public List<Presentation> partialLoadWithParentPresentationUseCacheOnResult(Presentation presentationWhat, Presentation positivePresentation, Presentation negativePresentation, Boolean useCache) {
		String key = getCacheKey(presentationWhat, positivePresentation, negativePresentation, "partialLoadWithParentPresentation");
		List<Presentation> list = (List<Presentation>)simpleCache.get(key);
		if (list==null || list.isEmpty()) {
			list = partialLoadWithParentPresentation(presentationWhat, positivePresentation, negativePresentation);
			if (!list.isEmpty())
			   simpleCache.put(key, list);
		}
		return list;	
	}	

	public List<Presentation> partialLoadWithParentPresentationUseCache(Presentation presentationWhat, Presentation positivePresentation, Presentation negativePresentation, Boolean useCache) {
		String key = getCacheKey(presentationWhat, positivePresentation, negativePresentation, "partialLoadWithParentPresentation");
		List<Presentation> list = (List<Presentation>)simpleCache.get(key);
		if (list==null) {
			list = partialLoadWithParentPresentation(presentationWhat, positivePresentation, negativePresentation);
			simpleCache.put(key, list);
		}
		return list;	
	}	
	
	private List<Presentation> handleLoadWithParentPresentation(Map beanPath, List list, Presentation presentationWhat) {
	    return handleLoadWithParentPresentation(beanPath, list, presentationWhat, true);  
	}
	
	private List<Presentation> handleLoadWithParentPresentation(Map beanPath, List list, Presentation presentationWhat, boolean isHql) {
		if (beanPath.size()==1) {
			return handlePartialLoadWithParentPresentationWithOneElementInRow(list, beanPath, presentationWhat, isHql);
		}
		return handlePartialLoadWithParentPresentation(list, beanPath, presentationWhat, isHql);	
	}
	
    	// to set in super class	
	protected void populatePresentation (Presentation presentation, Object value, String beanPath) throws IllegalAccessException, InvocationTargetException, NoSuchMethodException {
	   BeanUtils.populateBeanObject(presentation, beanPath, value);
	}
	
	protected void populatePresentationFromSQL (Presentation presentation, Object value, String beanPath) throws IllegalAccessException, InvocationTargetException, NoSuchMethodException {
	   BeanUtils.populateBeanObject(presentation, beanPath, value);
	}
    	// to set in super class BEWARE: genericity is only one level!!!!! first level is a copy second level is a reference!!! change to presentation.clone() instead
	private Presentation clonePresentation (Presentation presentation) throws IllegalAccessException, InvocationTargetException, InstantiationException, NoSuchMethodException {
		//return (Presentation) BeanUtils.cloneBeanObject(presentation);
	   if (presentation==null) return new Presentation();
	   return presentation.clone();
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
	
    public List<Presentation> countDistinct (Presentation whatMask, Presentation whereEqCriteria) {
       return partialLoadWithParentPresentation(whatMask, whereEqCriteria, null, new QuerySelectCountInit("presentation"), null, false);
    }   
    	
    public Long count(Presentation whereEqCriteria) {
        List<Presentation> list = partialLoadWithParentPresentation(null, whereEqCriteria, null, new QueryCountInit("presentation"), null, false);    
    	if (!list.isEmpty())
    		return list.get(0).getCount__();
    	return 0L;
    }
		
		  		
   public Presentation getFirstPresentationWhereConditionsAre (Presentation presentation) {
      List<Presentation> list = partialLoadWithParentPresentation(getDefaultPresentationWhat(), presentation, null, 1, false);
      if (list.isEmpty()) {
         return null;
      }
      else if (list.size()==1)
         return list.get(0);
      else 
      //TODO log error
         return list.get(0);	
	}

   private List getFirstResultWhereConditionsAre (Presentation presentation) {
      return  partialLoadWithParentPresentationQueryResult(getDefaultPresentationWhat(), presentation, null, 1, false);	
   }
   
   protected Presentation getDefaultPresentationWhat() {
      Presentation presentation = new Presentation();
      presentation.setId(Long.valueOf("-1"));
      return presentation;
   }
   
	public Presentation getFirstPresentation (Presentation presentation) {
		if (isAllNull(presentation))
			return null;
		else {
			List<Presentation> list = searchPrototype (presentation, 1);
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
    * checks if the Presentation entity exists
    */           
    public boolean existsPresentation (Presentation presentation) {
       if (getFirstPresentation(presentation)!=null)
          return true;
       return false;  
    }
        
    public boolean existsPresentationWhereConditionsAre (Presentation presentation) {
       if (getFirstResultWhereConditionsAre (presentation).isEmpty())
          return false;
       return true;  
    }


	
	private int countPartialField (Presentation presentation) {
	   int cpt = 0;
       if (presentation.getId() != null) {
          cpt++;
       }
       if (presentation.getStartTime() != null) {
          cpt++;
       }
       if (presentation.getEndTime() != null) {
          cpt++;
       }
       if (presentation.getAbstractName() != null) {
          cpt++;
       }
       if (presentation.getTitle() != null) {
          cpt++;
       }
       if (presentation.getStatus() != null) {
          cpt++;
       }
       if (presentation.getPresentationPlaceId() != null) {
          cpt++;
       }
       if (presentation.getProposalTime() != null) {
          cpt++;
       }
       return cpt;
	}   
  	
	public List<Presentation> partialLoadWithParentPresentation(Presentation presentationWhat, Presentation positivePresentation, Presentation negativePresentation, QueryWhatInit queryWhatInit, Integer nbOfResult, Boolean useCache) {
		Map beanPath = new Hashtable();
		List list = partialLoadWithParentPresentationJPAQueryResult (presentationWhat, positivePresentation, negativePresentation, queryWhatInit, beanPath, nbOfResult, useCache);
		if (beanPath.size()==1) {
			return handlePartialLoadWithParentPresentationWithOneElementInRow(list, beanPath, presentationWhat, true);
		}
		return handlePartialLoadWithParentPresentation(list, beanPath, presentationWhat, true);
	}	

	private List partialLoadWithParentPresentationQueryResult(Presentation presentationWhat, Presentation positivePresentation, Presentation negativePresentation, QueryWhatInit queryWhatInit, Integer nbOfResult, Boolean useCache) {
		return partialLoadWithParentPresentationJPAQueryResult (presentationWhat, positivePresentation, negativePresentation, queryWhatInit, new Hashtable(), nbOfResult, useCache);
  }	
  
	private List partialLoadWithParentPresentationJPAQueryResult(Presentation presentationWhat, Presentation positivePresentation, Presentation negativePresentation, QueryWhatInit queryWhatInit, Map beanPath, Integer nbOfResult, Boolean useCache) {
		Query hquery = getPartialLoadWithParentPresentationJPAQuery (presentationWhat, positivePresentation, negativePresentation, beanPath, queryWhatInit, nbOfResult);
		return hquery.getResultList();
  }	
   /**
    * @returns an JPA Hsql query based on entity Presentation and its parents and the maximum number of result
    */
	protected Query getPartialLoadWithParentPresentationJPAQuery (Presentation presentationWhat, Presentation positivePresentation, Presentation negativePresentation, Map beanPath, QueryWhatInit queryWhatInit, Integer nbOfResult) {
	   Query query = getEntityManager().createQuery(getPartialLoadWithParentPresentationHsqlQuery (presentationWhat, positivePresentation, negativePresentation, beanPath, queryWhatInit));
	   if (nbOfResult!=null)
	      query.setMaxResults(nbOfResult);
	   return query;
  }
  
	private List<Presentation> handlePartialLoadWithParentPresentation(List<Object[]> list, Map<Integer, String> beanPath, Presentation presentationWhat, boolean isJql) {
		try {
			return convertPartialLoadWithParentPresentation(list, beanPath, presentationWhat);
		} catch (Exception ex) {
			log.error("Error conversion list from handlePartialLoadWithParentPresentation, message:"+ex.getMessage());
			return new ArrayList<Presentation>();
		}
    }

	private List<Presentation> handlePartialLoadWithParentPresentationWithOneElementInRow(List<Object> list, Map<Integer, String> beanPath, Presentation presentationWhat, boolean isJql) {
		try {
			return convertPartialLoadWithParentPresentationWithOneElementInRow(list, beanPath, presentationWhat);
		} catch (Exception ex) {
			log.error("Error conversion list from handlePartialLoadWithParentPresentationWithOneElementInRow, message:"+ex.getMessage());
			return new ArrayList<Presentation>();
		}
    }
    	
	 private List<Presentation> convertPartialLoadWithParentPresentation(List<Object[]> list, Map<Integer, String> beanPath, Presentation presentationWhat) throws IllegalAccessException, InvocationTargetException, InstantiationException, NoSuchMethodException {
		 List<Presentation> resultList = new ArrayList<Presentation>();
		 for (Object[] row : list) {		
		    Presentation presentation = clonePresentation (presentationWhat);
		    Iterator<Entry<Integer, String>> iter = beanPath.entrySet().iterator();	
		    while (iter.hasNext()) {
		       Entry entry = iter.next();
		       populatePresentation (presentation, row[(Integer)entry.getKey()], (String)entry.getValue());
		    }
		    resultList.add(presentation);
		 }
		 return resultList;		
	 }	
    
	 private List<Presentation> convertPartialLoadWithParentPresentationWithOneElementInRow(List<Object> list, Map<Integer, String> beanPath, Presentation presentationWhat) throws IllegalAccessException, InvocationTargetException, InstantiationException, NoSuchMethodException {
		 List<Presentation> resultList = new ArrayList<Presentation>();
		 for (Object row : list) {		
		    Presentation presentation = clonePresentation (presentationWhat);
		    Iterator<Entry<Integer, String>> iter = beanPath.entrySet().iterator();	
		    while (iter.hasNext()) {
		       Entry entry = iter.next();
		       populatePresentation (presentation, row, (String)entry.getValue());
		    }
		    resultList.add(presentation);
		 }		 
		 return resultList;		
	 }
   
	public List partialLoadWithParentForBean(Object bean, Presentation presentationWhat, Presentation positivePresentation, Presentation negativePresentation) {
		Map beanPath = new Hashtable();
		Query hquery = getPartialLoadWithParentPresentationJPAQuery (presentationWhat, positivePresentation, negativePresentation, beanPath, new QuerySelectInit(), null);
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
	public String getPartialLoadWithParentPresentationHsqlQuery (Presentation presentation, Presentation positivePresentation, Presentation negativePresentation, Map beanPath, QueryWhatInit queryWhatInit) {
		Hashtable aliasWhatHt = new Hashtable();
		String what = getPartialLoadWithParentPresentationQuery (presentation, null, aliasWhatHt, null, null, beanPath, "", queryWhatInit);
		Hashtable aliasWhereHt = new Hashtable();
		String where = getPartialLoadWithParentWherePresentationQuery (positivePresentation, null, aliasWhatHt, aliasWhereHt, null, null);
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
	public List<Presentation> partialLoadPresentation(Presentation presentation, Presentation positivePresentation, Presentation negativePresentation) {
	    Query hquery = getEntityManager().createQuery(getPartialLoadPresentationQuery (presentation, positivePresentation, negativePresentation));
		int countPartialField = countPartialField(presentation);
		if (countPartialField==0) 
		   return new ArrayList<Presentation>();
		List list = hquery.getResultList();
		Iterator iter = list.iterator();
		List<Presentation> returnList = new ArrayList<Presentation>();
		while(iter.hasNext()) {
	       int index = 0;
	       Object[] row;
	       if (countPartialField==1) {
	    	  row = new Object[1];
	    	  row[0] = iter.next();
	       } else 
		      row = (Object[]) iter.next();
		   Presentation presentationResult = new Presentation();
           if (presentation.getId() != null) {
			  presentationResult.setId((Long) row[index]);
			  index++;
           }
           if (presentation.getStartTime() != null) {
			  presentationResult.setStartTime((Timestamp) row[index]);
			  index++;
           }
           if (presentation.getEndTime() != null) {
			  presentationResult.setEndTime((Timestamp) row[index]);
			  index++;
           }
           if (presentation.getAbstractName() != null) {
			  presentationResult.setAbstractName((String) row[index]);
			  index++;
           }
           if (presentation.getTitle() != null) {
			  presentationResult.setTitle((String) row[index]);
			  index++;
           }
           if (presentation.getStatus() != null) {
			  presentationResult.setStatus((String) row[index]);
			  index++;
           }
           if (presentation.getPresentationPlaceId() != null) {
			  presentationResult.setPresentationPlaceId_((Long) row[index]);
			  index++;
           }
           if (presentation.getProposalTime() != null) {
			  presentationResult.setProposalTime((Timestamp) row[index]);
			  index++;
           }
           returnList.add(presentationResult);
        }
	    return returnList;
	}

	public static String getPartialLoadWithParentWherePresentationQuery (
	   Presentation presentation, Boolean isWhereSet, Hashtable aliasHt, Hashtable aliasWhereHt, String childAlias, String childFKAlias) {
	   if (presentation==null)
	      return "";
	   String alias = null;
	   if (aliasWhereHt == null) {
	      aliasWhereHt = new Hashtable();
	   } 
	   if (isLookedUp(presentation)){
	      alias = getNextAlias (aliasWhereHt, presentation);
		  aliasWhereHt.put (getAliasKey(alias), getAliasConnection(alias, childAlias, childFKAlias));
	   }
	   if (isWhereSet == null)
          isWhereSet = false;
       StringBuffer query = new StringBuffer();
       if (presentation.getId() != null) {
           query.append (getQueryBLANK_AND (isWhereSet));
		   isWhereSet = true;
           query.append(" "+alias+".id = "+ presentation.getId() + " ");
       }
       if (presentation.getStartTime() != null) {
           query.append (getQueryBLANK_AND (isWhereSet));
		   isWhereSet = true;
           query.append(" "+alias+".startTime = '"+ presentation.getStartTime()+"' ");
       }
       if (presentation.getEndTime() != null) {
           query.append (getQueryBLANK_AND (isWhereSet));
		   isWhereSet = true;
           query.append(" "+alias+".endTime = '"+ presentation.getEndTime()+"' ");
       }
       if (presentation.getAbstractName() != null) {
           query.append (getQueryBLANK_AND (isWhereSet));
		   isWhereSet = true;
           query.append(" "+alias+".abstractName = '"+ presentation.getAbstractName()+"' ");
       }
       if (presentation.getTitle() != null) {
           query.append (getQueryBLANK_AND (isWhereSet));
		   isWhereSet = true;
           query.append(" "+alias+".title = '"+ presentation.getTitle()+"' ");
       }
       if (presentation.getStatus() != null) {
           query.append (getQueryBLANK_AND (isWhereSet));
		   isWhereSet = true;
           query.append(" "+alias+".status = '"+ presentation.getStatus()+"' ");
       }
       if (presentation.getPresentationPlaceId() != null) {
           query.append (getQueryBLANK_AND (isWhereSet));
		   isWhereSet = true;
           query.append(" "+alias+".presentationPlaceId = "+ presentation.getPresentationPlaceId() + " ");
       }
       if (presentation.getProposalTime() != null) {
           query.append (getQueryBLANK_AND (isWhereSet));
		   isWhereSet = true;
           query.append(" "+alias+".proposalTime = '"+ presentation.getProposalTime()+"' ");
       }
       if (presentation.getPresentationPlaceId()!=null) {
	      String chunck = net.sf.mp.demo.conference.dao.impl.jpa.conference.PresentationPlaceExtendedJPAImpl.getPartialLoadWithParentWherePresentationPlaceQuery(
		      presentation.getPresentationPlaceId(), 
			  isWhereSet, aliasHt, aliasWhereHt, alias, "presentationPlaceId");
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
	
    public static String getPartialLoadWithParentPresentationQuery (
	   Presentation presentation, Boolean isWhereSet, Hashtable aliasHt, String childAlias, String childFKAlias, Map beanPath, String rootPath, QueryWhatInit queryWhatInit) {
	   if (presentation==null)
	      return "";
	   String alias = null;
	   if (aliasHt == null) {
	      aliasHt = new Hashtable();
	   } 
	   if (isLookedUp(presentation)){
	      alias = getNextAlias (aliasHt, presentation);
		  aliasHt.put (getAliasKey(alias), getAliasConnection(alias, childAlias, childFKAlias));
	   }
	   if (isWhereSet == null)
          isWhereSet = false;
       StringBuffer query = new StringBuffer();
       if (presentation.getId() != null) {
          query.append (queryWhatInit.getWhatInit (isWhereSet));
          isWhereSet = true; 
          beanPath.put(beanPath.size(), rootPath+"id");
          query.append(" "+alias+".id ");
       }
       if (presentation.getStartTime() != null) {
          query.append (queryWhatInit.getWhatInit (isWhereSet));
          isWhereSet = true; 
          beanPath.put(beanPath.size(), rootPath+"startTime");
          query.append(" "+alias+".startTime ");
       }
       if (presentation.getEndTime() != null) {
          query.append (queryWhatInit.getWhatInit (isWhereSet));
          isWhereSet = true; 
          beanPath.put(beanPath.size(), rootPath+"endTime");
          query.append(" "+alias+".endTime ");
       }
       if (presentation.getAbstractName() != null) {
          query.append (queryWhatInit.getWhatInit (isWhereSet));
          isWhereSet = true; 
          beanPath.put(beanPath.size(), rootPath+"abstractName");
          query.append(" "+alias+".abstractName ");
       }
       if (presentation.getTitle() != null) {
          query.append (queryWhatInit.getWhatInit (isWhereSet));
          isWhereSet = true; 
          beanPath.put(beanPath.size(), rootPath+"title");
          query.append(" "+alias+".title ");
       }
       if (presentation.getStatus() != null) {
          query.append (queryWhatInit.getWhatInit (isWhereSet));
          isWhereSet = true; 
          beanPath.put(beanPath.size(), rootPath+"status");
          query.append(" "+alias+".status ");
       }
       if (presentation.getPresentationPlaceId() != null) {
          query.append (queryWhatInit.getWhatInit (isWhereSet));
          isWhereSet = true; 
          beanPath.put(beanPath.size(), rootPath+"presentationPlaceId");
          query.append(" "+alias+".presentationPlaceId ");
       }
       if (presentation.getProposalTime() != null) {
          query.append (queryWhatInit.getWhatInit (isWhereSet));
          isWhereSet = true; 
          beanPath.put(beanPath.size(), rootPath+"proposalTime");
          query.append(" "+alias+".proposalTime ");
       }
       if (presentation.getPresentationPlaceId()!=null) {
	      String chunck = net.sf.mp.demo.conference.dao.impl.jpa.conference.PresentationPlaceExtendedJPAImpl.getPartialLoadWithParentPresentationPlaceQuery(
		      presentation.getPresentationPlaceId(), 
			  isWhereSet, aliasHt, alias, "presentationPlaceId", beanPath, rootPath+"presentationPlaceId.", queryWhatInit);
		  if (chunck!=null && !chunck.equals("")) {
		     query.append(chunck);
		     isWhereSet=true;
		  } 
	   }  
//       query.append(getPresentationSearchEqualQuery (positivePresentation, negativePresentation));
	   return query.toString(); 
    }
	
	protected static String getAliasConnection(String existingAlias, String childAlias, String childFKAlias) {
		if (childAlias==null)
		   return "";
		return childAlias+"."+childFKAlias+" = "+existingAlias+"."+"id";
	}
	
	protected static String getAliasKey (String alias) {
	  //TODO this is a temporary solution use a dedicated object in BslaHiberateDaoSupport
		return "Presentation|"+alias;
	}
	
	protected static String getAliasKeyAlias (String aliasKey) {
	  //TODO this is a temporary solution use a dedicated object in BslaHiberateDaoSupport
		return StringUtils.substringAfter(aliasKey, "|");
	}
	
	protected static String getAliasKeyDomain (String aliasKey) {
	  //TODO this is a temporary solution use a dedicated object in BslaHiberateDaoSupport
	  return StringUtils.substringBefore(aliasKey, "|");
	}
	
	protected static String getNextAlias (Hashtable aliasHt, Presentation presentation) {
		int cptSameAlias = 0;
		Enumeration<String> keys = aliasHt.keys();
		while (keys.hasMoreElements()) {
			String key = keys.nextElement();
			if (key.startsWith("presentation"))
				cptSameAlias++;
		}
		if (cptSameAlias==0)
			return "presentation";
		else
			return "presentation_"+cptSameAlias;
	}
	
	
	protected static boolean isLookedUp (Presentation presentation) {
	   if (presentation==null)
		  return false;
       if (presentation.getId() != null) {
	      return true;
       }
       if (presentation.getStartTime() != null) {
	      return true;
       }
       if (presentation.getEndTime() != null) {
	      return true;
       }
       if (presentation.getAbstractName() != null) {
	      return true;
       }
       if (presentation.getTitle() != null) {
	      return true;
       }
       if (presentation.getStatus() != null) {
	      return true;
       }
       if (presentation.getPresentationPlaceId() != null) {
	      return true;
       }
       if (presentation.getProposalTime() != null) {
	      return true;
       }
       if (presentation.getPresentationPlaceId()!=null) {
	      return true;
	   }  
       return false;   
	}
	
    public String getPartialLoadPresentationQuery(
	   Presentation presentation, 
	   Presentation positivePresentation, 
	   Presentation negativePresentation) {
       boolean isWhereSet = false;
       StringBuffer query = new StringBuffer();
       if (presentation.getId() != null) {
          query.append (getQuerySelectComma (isWhereSet));
          isWhereSet = true; 
          query.append(" id ");
       }
       if (presentation.getStartTime() != null) {
          query.append (getQuerySelectComma (isWhereSet));
          isWhereSet = true; 
          query.append(" startTime ");
       }
       if (presentation.getEndTime() != null) {
          query.append (getQuerySelectComma (isWhereSet));
          isWhereSet = true; 
          query.append(" endTime ");
       }
       if (presentation.getAbstractName() != null) {
          query.append (getQuerySelectComma (isWhereSet));
          isWhereSet = true; 
          query.append(" abstractName ");
       }
       if (presentation.getTitle() != null) {
          query.append (getQuerySelectComma (isWhereSet));
          isWhereSet = true; 
          query.append(" title ");
       }
       if (presentation.getStatus() != null) {
          query.append (getQuerySelectComma (isWhereSet));
          isWhereSet = true; 
          query.append(" status ");
       }
       if (presentation.getPresentationPlaceId() != null) {
          query.append (getQuerySelectComma (isWhereSet));
          isWhereSet = true; 
          query.append(" presentationPlaceId ");
       }
       if (presentation.getProposalTime() != null) {
          query.append (getQuerySelectComma (isWhereSet));
          isWhereSet = true; 
          query.append(" proposalTime ");
       }
       query.append(getPresentationSearchEqualQuery (positivePresentation, negativePresentation));
	   return query.toString(); 
    }
	
	public List<Presentation> searchPrototypeWithCachePresentation(Presentation presentation) {
		SimpleCache simpleCache = new SimpleCache();
		List<Presentation> list = (List<Presentation>)simpleCache.get(presentation.toString());
		if (list==null) {
			list = searchPrototypePresentation(presentation);
			simpleCache.put(presentation.toString(), list);
		}
		return list;
	}

    public List<Presentation> loadGraph(Presentation graphMaskWhat, List<Presentation> whereMask) {
        return loadGraphOneLevel(graphMaskWhat, whereMask);
    }
 
    public List<Presentation> loadGraphOneLevel(Presentation graphMaskWhat, List<Presentation> whereMask) {
        //first get roots element from where list mask
        // this brings the level 0 of the graph (root level)
        graphMaskWhat.setId(graphMaskWhat.longMask__);
        List<Presentation> presentations = searchPrototypePresentation (whereMask);
        // for each sub level perform the search with a subquery then reassemble
        // 1. get all sublevel queries
        // 2. perform queries on the correct dao
        // 3. reassemble
        return getLoadGraphOneLevel (graphMaskWhat, presentations);
    }

	private List<Presentation> copy(List<Presentation> inputs) {
		List<Presentation> l = new ArrayList<Presentation>();
		for (Presentation input : inputs) {
			Presentation copy = new Presentation();
			copy.copy(input);
			l.add(copy);
		}
		return l;
	}
	   
	private List<Presentation> getLoadGraphOneLevel (Presentation graphMaskWhat, List<Presentation> parents) {
	    return loadGraphFromParentKey (graphMaskWhat, parents);
	} 
	
	public List<Presentation> loadGraphFromParentKey (Presentation graphMaskWhat, List<Presentation> parents) {
		//foreach children:
		//check if not empty take first
		parents = copy (parents); //working with detached entities to avoid unnecessary sql calls
		if (parents==null || parents.isEmpty())
		   return parents;
		List<String> ids = getPk (parents);
		if (graphMaskWhat.getEvaluationPresentationViaPresentationId()!=null && !graphMaskWhat.getEvaluationPresentationViaPresentationId().isEmpty()) {
			for (Evaluation childWhat : graphMaskWhat.getEvaluationPresentationViaPresentationId()) {
				childWhat.setPresentationId_(graphMaskWhat.longMask__); // add to the what mask, usefull for reconciliation
				EvaluationExtendedJPAImpl evaluationextendedjpaimpl = new EvaluationExtendedJPAImpl ();
				List<Evaluation> children = evaluationextendedjpaimpl.lookupEvaluation(childWhat, getFkCriteria(" id ", ids), null, getEntityManager());
				reassembleEvaluation (children, parents);				
				break;
			}
		}
		return parents;
	}
	
	private void reassembleEvaluation (List<Evaluation> children, List<Presentation> parents) {
		for (Evaluation child : children) {
			for (Presentation parent : parents) {
				if (parent.getId()!=null && parent.getId().toString().equals(child.getPresentationId()+"")) {
					parent.addEvaluationPresentationViaPresentationId(child); //EvaluationPresentationViaPresentationId
					child.setPresentationId(parent);
					break;
				}
			}
		}
	}
	
	private List<String> getPk(List<Presentation> input) {
		List<String> s = new ArrayList<String>();
		for (Presentation t : input) {
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
	
    public void setEvaluationExtendedJPAImpl (EvaluationExtendedJPAImpl evaluationextendedjpaimpl) {
       this.evaluationextendedjpaimpl = evaluationextendedjpaimpl;
    }
    
    public EvaluationExtendedJPAImpl getEvaluationExtendedJPAImpl () {
       return evaluationextendedjpaimpl;
    }
    
    
}
