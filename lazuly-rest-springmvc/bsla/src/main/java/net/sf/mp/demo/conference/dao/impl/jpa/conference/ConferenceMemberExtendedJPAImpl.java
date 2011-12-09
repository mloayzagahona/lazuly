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
import net.sf.mp.demo.conference.dao.face.conference.ConferenceMemberExtDao;
import net.sf.mp.demo.conference.domain.conference.ConferenceMember;
import net.sf.mp.demo.conference.dao.impl.jpa.conference.ConferenceMemberJPAImpl;

import net.sf.mp.demo.conference.domain.conference.ConferenceFeedback;
import net.sf.mp.demo.conference.dao.impl.jpa.conference.ConferenceFeedbackExtendedJPAImpl;
import net.sf.mp.demo.conference.domain.conference.Evaluation;
import net.sf.mp.demo.conference.dao.impl.jpa.conference.EvaluationExtendedJPAImpl;
import net.sf.mp.demo.conference.domain.conference.Speaker;
import net.sf.mp.demo.conference.dao.impl.jpa.conference.SpeakerExtendedJPAImpl;
import net.sf.mp.demo.conference.dao.impl.jpa.conference.ConferenceExtendedJPAImpl;
import net.sf.mp.demo.conference.dao.impl.jpa.conference.AddressExtendedJPAImpl;
/**
 *
 * <p>Title: ConferenceMemberExtendedJPAImpl</p>
 *
 * <p>Description: Interface of a Data access object dealing with ConferenceMemberExtendedJPAImpl
 * persistence. It offers a set of methods which allow for saving,
 * deleting and searching ConferenceMemberExtendedJPAImpl objects</p>
 *
 */

public class ConferenceMemberExtendedJPAImpl extends ConferenceMemberJPAImpl implements ConferenceMemberExtDao {

    private Logger log = Logger.getLogger(this.getClass());
    
    private SimpleCache simpleCache = new SimpleCache();
    private ConferenceFeedbackExtendedJPAImpl conferencefeedbackextendedjpaimpl;
    private EvaluationExtendedJPAImpl evaluationextendedjpaimpl;
    private SpeakerExtendedJPAImpl speakerextendedjpaimpl;
    private EntityManager emForRecursiveDao; // dao that needs other dao in a recursive manner not support by spring configuration

    public ConferenceMemberExtendedJPAImpl () {}

    /**
     * generic to move after in superclass
     */
    public ConferenceMemberExtendedJPAImpl (EntityManager emForRecursiveDao) {
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
     * Inserts a ConferenceMember entity with cascade of its children
     * @param ConferenceMember conferenceMember
     */
    public void insertConferenceMemberWithCascade(ConferenceMember conferenceMember) {
    	ConferenceMemberExtendedJPAImpl conferencememberextendedjpaimpl = new ConferenceMemberExtendedJPAImpl(getEntityManager());
    	conferencememberextendedjpaimpl.insertConferenceMemberWithCascade(conferencememberextendedjpaimpl.getEntityManagerForRecursiveDao(), conferenceMember);
    }
     
    public void insertConferenceMemberWithCascade(EntityManager emForRecursiveDao, ConferenceMember conferenceMember) {
       insertConferenceMember(emForRecursiveDao, conferenceMember);
       if (!conferenceMember.getConferenceFeedbackConferenceMemberViaConferenceMemberId().isEmpty()) {
          ConferenceFeedbackExtendedJPAImpl conferencefeedbackextendedjpaimpl = new ConferenceFeedbackExtendedJPAImpl (emForRecursiveDao);
          for (ConferenceFeedback _conferenceFeedbackConferenceMemberViaConferenceMemberId : conferenceMember.getConferenceFeedbackConferenceMemberViaConferenceMemberId()) {
             conferencefeedbackextendedjpaimpl.insertConferenceFeedbackWithCascade(emForRecursiveDao, _conferenceFeedbackConferenceMemberViaConferenceMemberId);
          }
       } 
       if (!conferenceMember.getEvaluationConferenceMemberViaConferenceMemberId().isEmpty()) {
          EvaluationExtendedJPAImpl evaluationextendedjpaimpl = new EvaluationExtendedJPAImpl (emForRecursiveDao);
          for (Evaluation _evaluationConferenceMemberViaConferenceMemberId : conferenceMember.getEvaluationConferenceMemberViaConferenceMemberId()) {
             evaluationextendedjpaimpl.insertEvaluationWithCascade(emForRecursiveDao, _evaluationConferenceMemberViaConferenceMemberId);
          }
       } 
       if (!conferenceMember.getSpeakerConferenceMemberViaConferenceMemberId().isEmpty()) {
          SpeakerExtendedJPAImpl speakerextendedjpaimpl = new SpeakerExtendedJPAImpl (emForRecursiveDao);
          for (Speaker _speakerConferenceMemberViaConferenceMemberId : conferenceMember.getSpeakerConferenceMemberViaConferenceMemberId()) {
             speakerextendedjpaimpl.insertSpeakerWithCascade(emForRecursiveDao, _speakerConferenceMemberViaConferenceMemberId);
          }
       } 
    }
        
    /**
     * Inserts a list of ConferenceMember entity with cascade of its children
     * @param List<ConferenceMember> conferenceMembers
     */
    public void insertConferenceMembersWithCascade(List<ConferenceMember> conferenceMembers) {
       for (ConferenceMember conferenceMember : conferenceMembers) {
          insertConferenceMemberWithCascade(conferenceMember);
       }
    } 
        
    /**
     * lookup ConferenceMember entity ConferenceMember, criteria and max result number
     */
    public List<ConferenceMember> lookupConferenceMember(ConferenceMember conferenceMember, Criteria criteria, Integer numberOfResult, EntityManager em) {
		boolean isWhereSet = false;
        StringBuffer query = new StringBuffer();
        query.append ("SELECT conferenceMember FROM ConferenceMember conferenceMember ");
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
    
    public List<ConferenceMember> lookupConferenceMember(ConferenceMember conferenceMember, Criteria criteria, Integer numberOfResult) {
		return lookupConferenceMember(conferenceMember, criteria, numberOfResult, getEntityManager());
    }

    public Integer updateNotNullOnlyConferenceMember (ConferenceMember conferenceMember, Criteria criteria) {
        String queryWhat = getUpdateNotNullOnlyConferenceMemberQueryChunkPrototype (conferenceMember);
        StringBuffer query = new StringBuffer (queryWhat);
        boolean isWhereSet = false;
        for (Criterion criterion : criteria.getClauseCriterions()) {
            query.append (getQueryWHERE_AND (isWhereSet));
            isWhereSet = true;   
            query.append(criterion.getExpression());			
        }  
        Query jpaQuery = getEntityManager().createQuery(query.toString());
        isWhereSet = false;
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
	
	public ConferenceMember affectConferenceMember (ConferenceMember conferenceMember) {
	    return referConferenceMember (conferenceMember, false);		    
	}
		
	/**
	 * Assign the first conferenceMember retrieved corresponding to the conferenceMember criteria.
	 * Blank criteria are mapped to null.
	 * If no criteria is found, null is returned.
	 * If there is no conferenceMember corresponding in the database. Then conferenceMember is inserted and returned with its primary key(s). 
	 */
	public ConferenceMember assignConferenceMember (ConferenceMember conferenceMember) {
	    return referConferenceMember (conferenceMember, true);	
	}
	
	public ConferenceMember referConferenceMember (ConferenceMember conferenceMember, boolean isAssign) {
		conferenceMember = assignBlankToNull (conferenceMember);
		if (isAllNull(conferenceMember))
			return null;
		else {
			List<ConferenceMember> list = searchPrototypeConferenceMember(conferenceMember);
			if (list.isEmpty()) {
			    if (isAssign)
			       insertConferenceMember(conferenceMember);
			    else
				   return null;
			}
			else if (list.size()==1)
				conferenceMember.copy(list.get(0));
			else 
				//TODO log error
				conferenceMember.copy(list.get(0));
		}
		return conferenceMember;		    
	}

   public ConferenceMember assignConferenceMemberUseCache (ConferenceMember conferenceMember) {
      return referConferenceMemberUseCache (conferenceMember, true);
   }
      		
   public ConferenceMember affectConferenceMemberUseCache (ConferenceMember conferenceMember) {
      return referConferenceMemberUseCache (conferenceMember, false);
   }
      		
   public ConferenceMember referConferenceMemberUseCache (ConferenceMember conferenceMember, boolean isAssign) {
	  String key = getCacheKey(null, conferenceMember, null, "assignConferenceMember");
      ConferenceMember conferenceMemberCache = (ConferenceMember)simpleCache.get(key);
      if (conferenceMemberCache==null) {
         conferenceMemberCache = referConferenceMember (conferenceMember, isAssign);
         if (key!=null)
         	simpleCache.put(key, conferenceMemberCache);
      }
      conferenceMember.copy(conferenceMemberCache);
      return conferenceMemberCache;
   }	

	private String getCacheKey (ConferenceMember conferenceMemberWhat, ConferenceMember positiveConferenceMember, ConferenceMember negativeConferenceMember, String queryKey) {
	    StringBuffer sb = new StringBuffer();
	    sb.append(queryKey);
	    if (conferenceMemberWhat!=null)
	       sb.append(conferenceMemberWhat.toStringWithParents());
	    if (positiveConferenceMember!=null)
	       sb.append(positiveConferenceMember.toStringWithParents());
	    if (negativeConferenceMember!=null)
	       sb.append(negativeConferenceMember.toStringWithParents());
	    return sb.toString();
	}
	
    public ConferenceMember partialLoadWithParentFirstConferenceMember(ConferenceMember conferenceMemberWhat, ConferenceMember positiveConferenceMember, ConferenceMember negativeConferenceMember){
		List <ConferenceMember> list = partialLoadWithParentConferenceMember(conferenceMemberWhat, positiveConferenceMember, negativeConferenceMember);
		return (!list.isEmpty())?(ConferenceMember)list.get(0):null;
    }
    
    public ConferenceMember partialLoadWithParentFirstConferenceMemberUseCache(ConferenceMember conferenceMemberWhat, ConferenceMember positiveConferenceMember, ConferenceMember negativeConferenceMember, Boolean useCache){
		List <ConferenceMember> list = partialLoadWithParentConferenceMemberUseCache(conferenceMemberWhat, positiveConferenceMember, negativeConferenceMember, useCache);
		return (!list.isEmpty())?(ConferenceMember)list.get(0):null;
    }
	
	public ConferenceMember partialLoadWithParentFirstConferenceMemberUseCacheOnResult(ConferenceMember conferenceMemberWhat, ConferenceMember positiveConferenceMember, ConferenceMember negativeConferenceMember, Boolean useCache){
		List <ConferenceMember> list = partialLoadWithParentConferenceMemberUseCacheOnResult(conferenceMemberWhat, positiveConferenceMember, negativeConferenceMember, useCache);
		return (!list.isEmpty())?(ConferenceMember)list.get(0):null;
    }
	//
	protected List<ConferenceMember> partialLoadWithParentConferenceMember(ConferenceMember conferenceMemberWhat, ConferenceMember positiveConferenceMember, ConferenceMember negativeConferenceMember, Integer nbOfResult, Boolean useCache) {
		 return partialLoadWithParentConferenceMember(conferenceMemberWhat, positiveConferenceMember, negativeConferenceMember, new QuerySelectInit(), nbOfResult, useCache);
	}	  

	protected List partialLoadWithParentConferenceMemberQueryResult (ConferenceMember conferenceMemberWhat, ConferenceMember positiveConferenceMember, ConferenceMember negativeConferenceMember, Integer nbOfResult, Boolean useCache) {
		 return partialLoadWithParentConferenceMemberQueryResult (conferenceMemberWhat, positiveConferenceMember, negativeConferenceMember, new QuerySelectInit(), nbOfResult, useCache);
	}	
    
    public List<ConferenceMember> getDistinctConferenceMember(ConferenceMember conferenceMemberWhat, ConferenceMember positiveConferenceMember, ConferenceMember negativeConferenceMember) {
		 return partialLoadWithParentConferenceMember(conferenceMemberWhat, positiveConferenceMember, negativeConferenceMember, new QuerySelectDistinctInit(), null, false);
	}
	
	public List<ConferenceMember> partialLoadWithParentConferenceMember(ConferenceMember conferenceMemberWhat, ConferenceMember positiveConferenceMember, ConferenceMember negativeConferenceMember) {
		 return partialLoadWithParentConferenceMember(conferenceMemberWhat, positiveConferenceMember, negativeConferenceMember, new QuerySelectInit(), null, false);
	}	
  
	public List<ConferenceMember> partialLoadWithParentConferenceMemberUseCacheOnResult(ConferenceMember conferenceMemberWhat, ConferenceMember positiveConferenceMember, ConferenceMember negativeConferenceMember, Boolean useCache) {
		String key = getCacheKey(conferenceMemberWhat, positiveConferenceMember, negativeConferenceMember, "partialLoadWithParentConferenceMember");
		List<ConferenceMember> list = (List<ConferenceMember>)simpleCache.get(key);
		if (list==null || list.isEmpty()) {
			list = partialLoadWithParentConferenceMember(conferenceMemberWhat, positiveConferenceMember, negativeConferenceMember);
			if (!list.isEmpty())
			   simpleCache.put(key, list);
		}
		return list;	
	}	

	public List<ConferenceMember> partialLoadWithParentConferenceMemberUseCache(ConferenceMember conferenceMemberWhat, ConferenceMember positiveConferenceMember, ConferenceMember negativeConferenceMember, Boolean useCache) {
		String key = getCacheKey(conferenceMemberWhat, positiveConferenceMember, negativeConferenceMember, "partialLoadWithParentConferenceMember");
		List<ConferenceMember> list = (List<ConferenceMember>)simpleCache.get(key);
		if (list==null) {
			list = partialLoadWithParentConferenceMember(conferenceMemberWhat, positiveConferenceMember, negativeConferenceMember);
			simpleCache.put(key, list);
		}
		return list;	
	}	
	
	private List<ConferenceMember> handleLoadWithParentConferenceMember(Map beanPath, List list, ConferenceMember conferenceMemberWhat) {
	    return handleLoadWithParentConferenceMember(beanPath, list, conferenceMemberWhat, true);  
	}
	
	private List<ConferenceMember> handleLoadWithParentConferenceMember(Map beanPath, List list, ConferenceMember conferenceMemberWhat, boolean isHql) {
		if (beanPath.size()==1) {
			return handlePartialLoadWithParentConferenceMemberWithOneElementInRow(list, beanPath, conferenceMemberWhat, isHql);
		}
		return handlePartialLoadWithParentConferenceMember(list, beanPath, conferenceMemberWhat, isHql);	
	}
	
    	// to set in super class	
	protected void populateConferenceMember (ConferenceMember conferenceMember, Object value, String beanPath) throws IllegalAccessException, InvocationTargetException, NoSuchMethodException {
	   BeanUtils.populateBeanObject(conferenceMember, beanPath, value);
	}
	
	protected void populateConferenceMemberFromSQL (ConferenceMember conferenceMember, Object value, String beanPath) throws IllegalAccessException, InvocationTargetException, NoSuchMethodException {
	   BeanUtils.populateBeanObject(conferenceMember, beanPath, value);
	}
    	// to set in super class BEWARE: genericity is only one level!!!!! first level is a copy second level is a reference!!! change to conferenceMember.clone() instead
	private ConferenceMember cloneConferenceMember (ConferenceMember conferenceMember) throws IllegalAccessException, InvocationTargetException, InstantiationException, NoSuchMethodException {
		//return (ConferenceMember) BeanUtils.cloneBeanObject(conferenceMember);
	   if (conferenceMember==null) return new ConferenceMember();
	   return conferenceMember.clone();
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
	
    public List<ConferenceMember> countDistinct (ConferenceMember whatMask, ConferenceMember whereEqCriteria) {
       return partialLoadWithParentConferenceMember(whatMask, whereEqCriteria, null, new QuerySelectCountInit("conferenceMember"), null, false);
    }   
    	
    public Long count(ConferenceMember whereEqCriteria) {
        List<ConferenceMember> list = partialLoadWithParentConferenceMember(null, whereEqCriteria, null, new QueryCountInit("conferenceMember"), null, false);    
    	if (!list.isEmpty())
    		return list.get(0).getCount__();
    	return 0L;
    }
		
		  		
   public ConferenceMember getFirstConferenceMemberWhereConditionsAre (ConferenceMember conferenceMember) {
      List<ConferenceMember> list = partialLoadWithParentConferenceMember(getDefaultConferenceMemberWhat(), conferenceMember, null, 1, false);
      if (list.isEmpty()) {
         return null;
      }
      else if (list.size()==1)
         return list.get(0);
      else 
      //TODO log error
         return list.get(0);	
	}

   private List getFirstResultWhereConditionsAre (ConferenceMember conferenceMember) {
      return  partialLoadWithParentConferenceMemberQueryResult(getDefaultConferenceMemberWhat(), conferenceMember, null, 1, false);	
   }
   
   protected ConferenceMember getDefaultConferenceMemberWhat() {
      ConferenceMember conferenceMember = new ConferenceMember();
      conferenceMember.setId(Long.valueOf("-1"));
      return conferenceMember;
   }
   
	public ConferenceMember getFirstConferenceMember (ConferenceMember conferenceMember) {
		if (isAllNull(conferenceMember))
			return null;
		else {
			List<ConferenceMember> list = searchPrototype (conferenceMember, 1);
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
    * checks if the ConferenceMember entity exists
    */           
    public boolean existsConferenceMember (ConferenceMember conferenceMember) {
       if (getFirstConferenceMember(conferenceMember)!=null)
          return true;
       return false;  
    }
        
    public boolean existsConferenceMemberWhereConditionsAre (ConferenceMember conferenceMember) {
       if (getFirstResultWhereConditionsAre (conferenceMember).isEmpty())
          return false;
       return true;  
    }


	
	private int countPartialField (ConferenceMember conferenceMember) {
	   int cpt = 0;
       if (conferenceMember.getId() != null) {
          cpt++;
       }
       if (conferenceMember.getConferenceId() != null) {
          cpt++;
       }
       if (conferenceMember.getFirstName() != null) {
          cpt++;
       }
       if (conferenceMember.getLastName() != null) {
          cpt++;
       }
       if (conferenceMember.getEmail() != null) {
          cpt++;
       }
       if (conferenceMember.getAddressId() != null) {
          cpt++;
       }
       if (conferenceMember.getStatus() != null) {
          cpt++;
       }
       return cpt;
	}   
  	
	public List<ConferenceMember> partialLoadWithParentConferenceMember(ConferenceMember conferenceMemberWhat, ConferenceMember positiveConferenceMember, ConferenceMember negativeConferenceMember, QueryWhatInit queryWhatInit, Integer nbOfResult, Boolean useCache) {
		Map beanPath = new Hashtable();
		List list = partialLoadWithParentConferenceMemberJPAQueryResult (conferenceMemberWhat, positiveConferenceMember, negativeConferenceMember, queryWhatInit, beanPath, nbOfResult, useCache);
		if (beanPath.size()==1) {
			return handlePartialLoadWithParentConferenceMemberWithOneElementInRow(list, beanPath, conferenceMemberWhat, true);
		}
		return handlePartialLoadWithParentConferenceMember(list, beanPath, conferenceMemberWhat, true);
	}	

	private List partialLoadWithParentConferenceMemberQueryResult(ConferenceMember conferenceMemberWhat, ConferenceMember positiveConferenceMember, ConferenceMember negativeConferenceMember, QueryWhatInit queryWhatInit, Integer nbOfResult, Boolean useCache) {
		return partialLoadWithParentConferenceMemberJPAQueryResult (conferenceMemberWhat, positiveConferenceMember, negativeConferenceMember, queryWhatInit, new Hashtable(), nbOfResult, useCache);
  }	
  
	private List partialLoadWithParentConferenceMemberJPAQueryResult(ConferenceMember conferenceMemberWhat, ConferenceMember positiveConferenceMember, ConferenceMember negativeConferenceMember, QueryWhatInit queryWhatInit, Map beanPath, Integer nbOfResult, Boolean useCache) {
		Query hquery = getPartialLoadWithParentConferenceMemberJPAQuery (conferenceMemberWhat, positiveConferenceMember, negativeConferenceMember, beanPath, queryWhatInit, nbOfResult);
		return hquery.getResultList();
  }	
   /**
    * @returns an JPA Hsql query based on entity ConferenceMember and its parents and the maximum number of result
    */
	protected Query getPartialLoadWithParentConferenceMemberJPAQuery (ConferenceMember conferenceMemberWhat, ConferenceMember positiveConferenceMember, ConferenceMember negativeConferenceMember, Map beanPath, QueryWhatInit queryWhatInit, Integer nbOfResult) {
	   Query query = getEntityManager().createQuery(getPartialLoadWithParentConferenceMemberHsqlQuery (conferenceMemberWhat, positiveConferenceMember, negativeConferenceMember, beanPath, queryWhatInit));
	   if (nbOfResult!=null)
	      query.setMaxResults(nbOfResult);
	   return query;
  }
  
	private List<ConferenceMember> handlePartialLoadWithParentConferenceMember(List<Object[]> list, Map<Integer, String> beanPath, ConferenceMember conferenceMemberWhat, boolean isJql) {
		try {
			return convertPartialLoadWithParentConferenceMember(list, beanPath, conferenceMemberWhat);
		} catch (Exception ex) {
			log.error("Error conversion list from handlePartialLoadWithParentConferenceMember, message:"+ex.getMessage());
			return new ArrayList<ConferenceMember>();
		}
    }

	private List<ConferenceMember> handlePartialLoadWithParentConferenceMemberWithOneElementInRow(List<Object> list, Map<Integer, String> beanPath, ConferenceMember conferenceMemberWhat, boolean isJql) {
		try {
			return convertPartialLoadWithParentConferenceMemberWithOneElementInRow(list, beanPath, conferenceMemberWhat);
		} catch (Exception ex) {
			log.error("Error conversion list from handlePartialLoadWithParentConferenceMemberWithOneElementInRow, message:"+ex.getMessage());
			return new ArrayList<ConferenceMember>();
		}
    }
    	
	 private List<ConferenceMember> convertPartialLoadWithParentConferenceMember(List<Object[]> list, Map<Integer, String> beanPath, ConferenceMember conferenceMemberWhat) throws IllegalAccessException, InvocationTargetException, InstantiationException, NoSuchMethodException {
		 List<ConferenceMember> resultList = new ArrayList<ConferenceMember>();
		 for (Object[] row : list) {		
		    ConferenceMember conferenceMember = cloneConferenceMember (conferenceMemberWhat);
		    Iterator<Entry<Integer, String>> iter = beanPath.entrySet().iterator();	
		    while (iter.hasNext()) {
		       Entry entry = iter.next();
		       populateConferenceMember (conferenceMember, row[(Integer)entry.getKey()], (String)entry.getValue());
		    }
		    resultList.add(conferenceMember);
		 }
		 return resultList;		
	 }	
    
	 private List<ConferenceMember> convertPartialLoadWithParentConferenceMemberWithOneElementInRow(List<Object> list, Map<Integer, String> beanPath, ConferenceMember conferenceMemberWhat) throws IllegalAccessException, InvocationTargetException, InstantiationException, NoSuchMethodException {
		 List<ConferenceMember> resultList = new ArrayList<ConferenceMember>();
		 for (Object row : list) {		
		    ConferenceMember conferenceMember = cloneConferenceMember (conferenceMemberWhat);
		    Iterator<Entry<Integer, String>> iter = beanPath.entrySet().iterator();	
		    while (iter.hasNext()) {
		       Entry entry = iter.next();
		       populateConferenceMember (conferenceMember, row, (String)entry.getValue());
		    }
		    resultList.add(conferenceMember);
		 }		 
		 return resultList;		
	 }
   
	public List partialLoadWithParentForBean(Object bean, ConferenceMember conferenceMemberWhat, ConferenceMember positiveConferenceMember, ConferenceMember negativeConferenceMember) {
		Map beanPath = new Hashtable();
		Query hquery = getPartialLoadWithParentConferenceMemberJPAQuery (conferenceMemberWhat, positiveConferenceMember, negativeConferenceMember, beanPath, new QuerySelectInit(), null);
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
	public String getPartialLoadWithParentConferenceMemberHsqlQuery (ConferenceMember conferenceMember, ConferenceMember positiveConferenceMember, ConferenceMember negativeConferenceMember, Map beanPath, QueryWhatInit queryWhatInit) {
		Hashtable aliasWhatHt = new Hashtable();
		String what = getPartialLoadWithParentConferenceMemberQuery (conferenceMember, null, aliasWhatHt, null, null, beanPath, "", queryWhatInit);
		Hashtable aliasWhereHt = new Hashtable();
		String where = getPartialLoadWithParentWhereConferenceMemberQuery (positiveConferenceMember, null, aliasWhatHt, aliasWhereHt, null, null);
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
	public List<ConferenceMember> partialLoadConferenceMember(ConferenceMember conferenceMember, ConferenceMember positiveConferenceMember, ConferenceMember negativeConferenceMember) {
	    Query hquery = getEntityManager().createQuery(getPartialLoadConferenceMemberQuery (conferenceMember, positiveConferenceMember, negativeConferenceMember));
		int countPartialField = countPartialField(conferenceMember);
		if (countPartialField==0) 
		   return new ArrayList<ConferenceMember>();
		List list = hquery.getResultList();
		Iterator iter = list.iterator();
		List<ConferenceMember> returnList = new ArrayList<ConferenceMember>();
		while(iter.hasNext()) {
	       int index = 0;
	       Object[] row;
	       if (countPartialField==1) {
	    	  row = new Object[1];
	    	  row[0] = iter.next();
	       } else 
		      row = (Object[]) iter.next();
		   ConferenceMember conferenceMemberResult = new ConferenceMember();
           if (conferenceMember.getId() != null) {
			  conferenceMemberResult.setId((Long) row[index]);
			  index++;
           }
           if (conferenceMember.getConferenceId() != null) {
			  conferenceMemberResult.setConferenceId_((Long) row[index]);
			  index++;
           }
           if (conferenceMember.getFirstName() != null) {
			  conferenceMemberResult.setFirstName((String) row[index]);
			  index++;
           }
           if (conferenceMember.getLastName() != null) {
			  conferenceMemberResult.setLastName((String) row[index]);
			  index++;
           }
           if (conferenceMember.getEmail() != null) {
			  conferenceMemberResult.setEmail((String) row[index]);
			  index++;
           }
           if (conferenceMember.getAddressId() != null) {
			  conferenceMemberResult.setAddressId_((Long) row[index]);
			  index++;
           }
           if (conferenceMember.getStatus() != null) {
			  conferenceMemberResult.setStatus((String) row[index]);
			  index++;
           }
           returnList.add(conferenceMemberResult);
        }
	    return returnList;
	}

	public static String getPartialLoadWithParentWhereConferenceMemberQuery (
	   ConferenceMember conferenceMember, Boolean isWhereSet, Hashtable aliasHt, Hashtable aliasWhereHt, String childAlias, String childFKAlias) {
	   if (conferenceMember==null)
	      return "";
	   String alias = null;
	   if (aliasWhereHt == null) {
	      aliasWhereHt = new Hashtable();
	   } 
	   if (isLookedUp(conferenceMember)){
	      alias = getNextAlias (aliasWhereHt, conferenceMember);
		  aliasWhereHt.put (getAliasKey(alias), getAliasConnection(alias, childAlias, childFKAlias));
	   }
	   if (isWhereSet == null)
          isWhereSet = false;
       StringBuffer query = new StringBuffer();
       if (conferenceMember.getId() != null) {
           query.append (getQueryBLANK_AND (isWhereSet));
		   isWhereSet = true;
           query.append(" "+alias+".id = "+ conferenceMember.getId() + " ");
       }
       if (conferenceMember.getConferenceId() != null) {
           query.append (getQueryBLANK_AND (isWhereSet));
		   isWhereSet = true;
           query.append(" "+alias+".conferenceId = "+ conferenceMember.getConferenceId() + " ");
       }
       if (conferenceMember.getFirstName() != null) {
           query.append (getQueryBLANK_AND (isWhereSet));
		   isWhereSet = true;
           query.append(" "+alias+".firstName = '"+ conferenceMember.getFirstName()+"' ");
       }
       if (conferenceMember.getLastName() != null) {
           query.append (getQueryBLANK_AND (isWhereSet));
		   isWhereSet = true;
           query.append(" "+alias+".lastName = '"+ conferenceMember.getLastName()+"' ");
       }
       if (conferenceMember.getEmail() != null) {
           query.append (getQueryBLANK_AND (isWhereSet));
		   isWhereSet = true;
           query.append(" "+alias+".email = '"+ conferenceMember.getEmail()+"' ");
       }
       if (conferenceMember.getAddressId() != null) {
           query.append (getQueryBLANK_AND (isWhereSet));
		   isWhereSet = true;
           query.append(" "+alias+".addressId = "+ conferenceMember.getAddressId() + " ");
       }
       if (conferenceMember.getStatus() != null) {
           query.append (getQueryBLANK_AND (isWhereSet));
		   isWhereSet = true;
           query.append(" "+alias+".status = '"+ conferenceMember.getStatus()+"' ");
       }
       if (conferenceMember.getConferenceId()!=null) {
	      String chunck = net.sf.mp.demo.conference.dao.impl.jpa.conference.ConferenceExtendedJPAImpl.getPartialLoadWithParentWhereConferenceQuery(
		      conferenceMember.getConferenceId(), 
			  isWhereSet, aliasHt, aliasWhereHt, alias, "conferenceId");
		  if (chunck!=null && !chunck.equals("")) {
		     query.append(chunck);
		     isWhereSet=true;
		  }  	  
	   }
       if (conferenceMember.getAddressId()!=null) {
	      String chunck = net.sf.mp.demo.conference.dao.impl.jpa.conference.AddressExtendedJPAImpl.getPartialLoadWithParentWhereAddressQuery(
		      conferenceMember.getAddressId(), 
			  isWhereSet, aliasHt, aliasWhereHt, alias, "addressId");
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
	
    public static String getPartialLoadWithParentConferenceMemberQuery (
	   ConferenceMember conferenceMember, Boolean isWhereSet, Hashtable aliasHt, String childAlias, String childFKAlias, Map beanPath, String rootPath, QueryWhatInit queryWhatInit) {
	   if (conferenceMember==null)
	      return "";
	   String alias = null;
	   if (aliasHt == null) {
	      aliasHt = new Hashtable();
	   } 
	   if (isLookedUp(conferenceMember)){
	      alias = getNextAlias (aliasHt, conferenceMember);
		  aliasHt.put (getAliasKey(alias), getAliasConnection(alias, childAlias, childFKAlias));
	   }
	   if (isWhereSet == null)
          isWhereSet = false;
       StringBuffer query = new StringBuffer();
       if (conferenceMember.getId() != null) {
          query.append (queryWhatInit.getWhatInit (isWhereSet));
          isWhereSet = true; 
          beanPath.put(beanPath.size(), rootPath+"id");
          query.append(" "+alias+".id ");
       }
       if (conferenceMember.getConferenceId() != null) {
          query.append (queryWhatInit.getWhatInit (isWhereSet));
          isWhereSet = true; 
          beanPath.put(beanPath.size(), rootPath+"conferenceId");
          query.append(" "+alias+".conferenceId ");
       }
       if (conferenceMember.getFirstName() != null) {
          query.append (queryWhatInit.getWhatInit (isWhereSet));
          isWhereSet = true; 
          beanPath.put(beanPath.size(), rootPath+"firstName");
          query.append(" "+alias+".firstName ");
       }
       if (conferenceMember.getLastName() != null) {
          query.append (queryWhatInit.getWhatInit (isWhereSet));
          isWhereSet = true; 
          beanPath.put(beanPath.size(), rootPath+"lastName");
          query.append(" "+alias+".lastName ");
       }
       if (conferenceMember.getEmail() != null) {
          query.append (queryWhatInit.getWhatInit (isWhereSet));
          isWhereSet = true; 
          beanPath.put(beanPath.size(), rootPath+"email");
          query.append(" "+alias+".email ");
       }
       if (conferenceMember.getAddressId() != null) {
          query.append (queryWhatInit.getWhatInit (isWhereSet));
          isWhereSet = true; 
          beanPath.put(beanPath.size(), rootPath+"addressId");
          query.append(" "+alias+".addressId ");
       }
       if (conferenceMember.getStatus() != null) {
          query.append (queryWhatInit.getWhatInit (isWhereSet));
          isWhereSet = true; 
          beanPath.put(beanPath.size(), rootPath+"status");
          query.append(" "+alias+".status ");
       }
       if (conferenceMember.getConferenceId()!=null) {
	      String chunck = net.sf.mp.demo.conference.dao.impl.jpa.conference.ConferenceExtendedJPAImpl.getPartialLoadWithParentConferenceQuery(
		      conferenceMember.getConferenceId(), 
			  isWhereSet, aliasHt, alias, "conferenceId", beanPath, rootPath+"conferenceId.", queryWhatInit);
		  if (chunck!=null && !chunck.equals("")) {
		     query.append(chunck);
		     isWhereSet=true;
		  } 
	   }  
       if (conferenceMember.getAddressId()!=null) {
	      String chunck = net.sf.mp.demo.conference.dao.impl.jpa.conference.AddressExtendedJPAImpl.getPartialLoadWithParentAddressQuery(
		      conferenceMember.getAddressId(), 
			  isWhereSet, aliasHt, alias, "addressId", beanPath, rootPath+"addressId.", queryWhatInit);
		  if (chunck!=null && !chunck.equals("")) {
		     query.append(chunck);
		     isWhereSet=true;
		  } 
	   }  
//       query.append(getConferenceMemberSearchEqualQuery (positiveConferenceMember, negativeConferenceMember));
	   return query.toString(); 
    }
	
	protected static String getAliasConnection(String existingAlias, String childAlias, String childFKAlias) {
		if (childAlias==null)
		   return "";
		return childAlias+"."+childFKAlias+" = "+existingAlias+"."+"id";
	}
	
	protected static String getAliasKey (String alias) {
	  //TODO this is a temporary solution use a dedicated object in BslaHiberateDaoSupport
		return "ConferenceMember|"+alias;
	}
	
	protected static String getAliasKeyAlias (String aliasKey) {
	  //TODO this is a temporary solution use a dedicated object in BslaHiberateDaoSupport
		return StringUtils.substringAfter(aliasKey, "|");
	}
	
	protected static String getAliasKeyDomain (String aliasKey) {
	  //TODO this is a temporary solution use a dedicated object in BslaHiberateDaoSupport
	  return StringUtils.substringBefore(aliasKey, "|");
	}
	
	protected static String getNextAlias (Hashtable aliasHt, ConferenceMember conferenceMember) {
		int cptSameAlias = 0;
		Enumeration<String> keys = aliasHt.keys();
		while (keys.hasMoreElements()) {
			String key = keys.nextElement();
			if (key.startsWith("conferenceMember"))
				cptSameAlias++;
		}
		if (cptSameAlias==0)
			return "conferenceMember";
		else
			return "conferenceMember_"+cptSameAlias;
	}
	
	
	protected static boolean isLookedUp (ConferenceMember conferenceMember) {
	   if (conferenceMember==null)
		  return false;
       if (conferenceMember.getId() != null) {
	      return true;
       }
       if (conferenceMember.getConferenceId() != null) {
	      return true;
       }
       if (conferenceMember.getFirstName() != null) {
	      return true;
       }
       if (conferenceMember.getLastName() != null) {
	      return true;
       }
       if (conferenceMember.getEmail() != null) {
	      return true;
       }
       if (conferenceMember.getAddressId() != null) {
	      return true;
       }
       if (conferenceMember.getStatus() != null) {
	      return true;
       }
       if (conferenceMember.getConferenceId()!=null) {
	      return true;
	   }  
       if (conferenceMember.getAddressId()!=null) {
	      return true;
	   }  
       return false;   
	}
	
    public String getPartialLoadConferenceMemberQuery(
	   ConferenceMember conferenceMember, 
	   ConferenceMember positiveConferenceMember, 
	   ConferenceMember negativeConferenceMember) {
       boolean isWhereSet = false;
       StringBuffer query = new StringBuffer();
       if (conferenceMember.getId() != null) {
          query.append (getQuerySelectComma (isWhereSet));
          isWhereSet = true; 
          query.append(" id ");
       }
       if (conferenceMember.getConferenceId() != null) {
          query.append (getQuerySelectComma (isWhereSet));
          isWhereSet = true; 
          query.append(" conferenceId ");
       }
       if (conferenceMember.getFirstName() != null) {
          query.append (getQuerySelectComma (isWhereSet));
          isWhereSet = true; 
          query.append(" firstName ");
       }
       if (conferenceMember.getLastName() != null) {
          query.append (getQuerySelectComma (isWhereSet));
          isWhereSet = true; 
          query.append(" lastName ");
       }
       if (conferenceMember.getEmail() != null) {
          query.append (getQuerySelectComma (isWhereSet));
          isWhereSet = true; 
          query.append(" email ");
       }
       if (conferenceMember.getAddressId() != null) {
          query.append (getQuerySelectComma (isWhereSet));
          isWhereSet = true; 
          query.append(" addressId ");
       }
       if (conferenceMember.getStatus() != null) {
          query.append (getQuerySelectComma (isWhereSet));
          isWhereSet = true; 
          query.append(" status ");
       }
       query.append(getConferenceMemberSearchEqualQuery (positiveConferenceMember, negativeConferenceMember));
	   return query.toString(); 
    }
	
	public List<ConferenceMember> searchPrototypeWithCacheConferenceMember(ConferenceMember conferenceMember) {
		SimpleCache simpleCache = new SimpleCache();
		List<ConferenceMember> list = (List<ConferenceMember>)simpleCache.get(conferenceMember.toString());
		if (list==null) {
			list = searchPrototypeConferenceMember(conferenceMember);
			simpleCache.put(conferenceMember.toString(), list);
		}
		return list;
	}

    public List<ConferenceMember> loadGraph(ConferenceMember graphMaskWhat, List<ConferenceMember> whereMask) {
        return loadGraphOneLevel(graphMaskWhat, whereMask);
    }
 
    public List<ConferenceMember> loadGraphOneLevel(ConferenceMember graphMaskWhat, List<ConferenceMember> whereMask) {
        //first get roots element from where list mask
        // this brings the level 0 of the graph (root level)
        graphMaskWhat.setId(graphMaskWhat.longMask__);
        List<ConferenceMember> conferenceMembers = searchPrototypeConferenceMember (whereMask);
        // for each sub level perform the search with a subquery then reassemble
        // 1. get all sublevel queries
        // 2. perform queries on the correct dao
        // 3. reassemble
        return getLoadGraphOneLevel (graphMaskWhat, conferenceMembers);
    }

	private List<ConferenceMember> copy(List<ConferenceMember> inputs) {
		List<ConferenceMember> l = new ArrayList<ConferenceMember>();
		for (ConferenceMember input : inputs) {
			ConferenceMember copy = new ConferenceMember();
			copy.copy(input);
			l.add(copy);
		}
		return l;
	}
	   
	private List<ConferenceMember> getLoadGraphOneLevel (ConferenceMember graphMaskWhat, List<ConferenceMember> parents) {
	    return loadGraphFromParentKey (graphMaskWhat, parents);
	} 
	
	public List<ConferenceMember> loadGraphFromParentKey (ConferenceMember graphMaskWhat, List<ConferenceMember> parents) {
		//foreach children:
		//check if not empty take first
		parents = copy (parents); //working with detached entities to avoid unnecessary sql calls
		if (parents==null || parents.isEmpty())
		   return parents;
		List<String> ids = getPk (parents);
		if (graphMaskWhat.getConferenceFeedbackConferenceMemberViaConferenceMemberId()!=null && !graphMaskWhat.getConferenceFeedbackConferenceMemberViaConferenceMemberId().isEmpty()) {
			for (ConferenceFeedback childWhat : graphMaskWhat.getConferenceFeedbackConferenceMemberViaConferenceMemberId()) {
				childWhat.setConferenceMemberId_(graphMaskWhat.longMask__); // add to the what mask, usefull for reconciliation
				ConferenceFeedbackExtendedJPAImpl conferencefeedbackextendedjpaimpl = new ConferenceFeedbackExtendedJPAImpl ();
				List<ConferenceFeedback> children = conferencefeedbackextendedjpaimpl.lookupConferenceFeedback(childWhat, getFkCriteria(" id ", ids), null, getEntityManager());
				reassembleConferenceFeedback (children, parents);				
				break;
			}
		}
		if (graphMaskWhat.getEvaluationConferenceMemberViaConferenceMemberId()!=null && !graphMaskWhat.getEvaluationConferenceMemberViaConferenceMemberId().isEmpty()) {
			for (Evaluation childWhat : graphMaskWhat.getEvaluationConferenceMemberViaConferenceMemberId()) {
				childWhat.setConferenceMemberId_(graphMaskWhat.longMask__); // add to the what mask, usefull for reconciliation
				EvaluationExtendedJPAImpl evaluationextendedjpaimpl = new EvaluationExtendedJPAImpl ();
				List<Evaluation> children = evaluationextendedjpaimpl.lookupEvaluation(childWhat, getFkCriteria(" id ", ids), null, getEntityManager());
				reassembleEvaluation (children, parents);				
				break;
			}
		}
		if (graphMaskWhat.getSpeakerConferenceMemberViaConferenceMemberId()!=null && !graphMaskWhat.getSpeakerConferenceMemberViaConferenceMemberId().isEmpty()) {
			for (Speaker childWhat : graphMaskWhat.getSpeakerConferenceMemberViaConferenceMemberId()) {
				childWhat.setConferenceMemberId_(graphMaskWhat.longMask__); // add to the what mask, usefull for reconciliation
				SpeakerExtendedJPAImpl speakerextendedjpaimpl = new SpeakerExtendedJPAImpl ();
				List<Speaker> children = speakerextendedjpaimpl.lookupSpeaker(childWhat, getFkCriteria(" id ", ids), null, getEntityManager());
				reassembleSpeaker (children, parents);				
				break;
			}
		}
		return parents;
	}
	
	private void reassembleConferenceFeedback (List<ConferenceFeedback> children, List<ConferenceMember> parents) {
		for (ConferenceFeedback child : children) {
			for (ConferenceMember parent : parents) {
				if (parent.getId()!=null && parent.getId().toString().equals(child.getConferenceMemberId()+"")) {
					parent.addConferenceFeedbackConferenceMemberViaConferenceMemberId(child); //ConferenceFeedbackConferenceMemberViaConferenceMemberId
					child.setConferenceMemberId(parent);
					break;
				}
			}
		}
	}
	
	private void reassembleEvaluation (List<Evaluation> children, List<ConferenceMember> parents) {
		for (Evaluation child : children) {
			for (ConferenceMember parent : parents) {
				if (parent.getId()!=null && parent.getId().toString().equals(child.getConferenceMemberId()+"")) {
					parent.addEvaluationConferenceMemberViaConferenceMemberId(child); //EvaluationConferenceMemberViaConferenceMemberId
					child.setConferenceMemberId(parent);
					break;
				}
			}
		}
	}
	
	private void reassembleSpeaker (List<Speaker> children, List<ConferenceMember> parents) {
		for (Speaker child : children) {
			for (ConferenceMember parent : parents) {
				if (parent.getId()!=null && parent.getId().toString().equals(child.getConferenceMemberId()+"")) {
					parent.addSpeakerConferenceMemberViaConferenceMemberId(child); //SpeakerConferenceMemberViaConferenceMemberId
					child.setConferenceMemberId(parent);
					break;
				}
			}
		}
	}
	
	private List<String> getPk(List<ConferenceMember> input) {
		List<String> s = new ArrayList<String>();
		for (ConferenceMember t : input) {
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
	
    public void setConferenceFeedbackExtendedJPAImpl (ConferenceFeedbackExtendedJPAImpl conferencefeedbackextendedjpaimpl) {
       this.conferencefeedbackextendedjpaimpl = conferencefeedbackextendedjpaimpl;
    }
    
    public ConferenceFeedbackExtendedJPAImpl getConferenceFeedbackExtendedJPAImpl () {
       return conferencefeedbackextendedjpaimpl;
    }
    
    public void setEvaluationExtendedJPAImpl (EvaluationExtendedJPAImpl evaluationextendedjpaimpl) {
       this.evaluationextendedjpaimpl = evaluationextendedjpaimpl;
    }
    
    public EvaluationExtendedJPAImpl getEvaluationExtendedJPAImpl () {
       return evaluationextendedjpaimpl;
    }
    
    public void setSpeakerExtendedJPAImpl (SpeakerExtendedJPAImpl speakerextendedjpaimpl) {
       this.speakerextendedjpaimpl = speakerextendedjpaimpl;
    }
    
    public SpeakerExtendedJPAImpl getSpeakerExtendedJPAImpl () {
       return speakerextendedjpaimpl;
    }
    
    
}
