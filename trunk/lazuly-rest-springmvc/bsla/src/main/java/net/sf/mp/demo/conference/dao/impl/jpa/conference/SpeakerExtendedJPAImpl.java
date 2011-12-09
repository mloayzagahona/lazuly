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
import net.sf.mp.demo.conference.dao.face.conference.SpeakerExtDao;
import net.sf.mp.demo.conference.domain.conference.Speaker;
import net.sf.mp.demo.conference.dao.impl.jpa.conference.SpeakerJPAImpl;

import net.sf.mp.demo.conference.dao.impl.jpa.conference.ConferenceMemberExtendedJPAImpl;
import net.sf.mp.demo.conference.dao.impl.jpa.conference.SponsorExtendedJPAImpl;
/**
 *
 * <p>Title: SpeakerExtendedJPAImpl</p>
 *
 * <p>Description: Interface of a Data access object dealing with SpeakerExtendedJPAImpl
 * persistence. It offers a set of methods which allow for saving,
 * deleting and searching SpeakerExtendedJPAImpl objects</p>
 *
 */

public class SpeakerExtendedJPAImpl extends SpeakerJPAImpl implements SpeakerExtDao {

    private Logger log = Logger.getLogger(this.getClass());
    
    private SimpleCache simpleCache = new SimpleCache();
    private EntityManager emForRecursiveDao; // dao that needs other dao in a recursive manner not support by spring configuration

    public SpeakerExtendedJPAImpl () {}

    /**
     * generic to move after in superclass
     */
    public SpeakerExtendedJPAImpl (EntityManager emForRecursiveDao) {
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
     * Inserts a Speaker entity with cascade of its children
     * @param Speaker speaker
     */
    public void insertSpeakerWithCascade(Speaker speaker) {
    	SpeakerExtendedJPAImpl speakerextendedjpaimpl = new SpeakerExtendedJPAImpl(getEntityManager());
    	speakerextendedjpaimpl.insertSpeakerWithCascade(speakerextendedjpaimpl.getEntityManagerForRecursiveDao(), speaker);
    }
     
    public void insertSpeakerWithCascade(EntityManager emForRecursiveDao, Speaker speaker) {
       insertSpeaker(emForRecursiveDao, speaker);
    }
        
    /**
     * Inserts a list of Speaker entity with cascade of its children
     * @param List<Speaker> speakers
     */
    public void insertSpeakersWithCascade(List<Speaker> speakers) {
       for (Speaker speaker : speakers) {
          insertSpeakerWithCascade(speaker);
       }
    } 
        
    /**
     * lookup Speaker entity Speaker, criteria and max result number
     */
    public List<Speaker> lookupSpeaker(Speaker speaker, Criteria criteria, Integer numberOfResult, EntityManager em) {
		boolean isWhereSet = false;
        StringBuffer query = new StringBuffer();
        query.append ("SELECT speaker FROM Speaker speaker ");
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
    
    public List<Speaker> lookupSpeaker(Speaker speaker, Criteria criteria, Integer numberOfResult) {
		return lookupSpeaker(speaker, criteria, numberOfResult, getEntityManager());
    }

    public Integer updateNotNullOnlySpeaker (Speaker speaker, Criteria criteria) {
        String queryWhat = getUpdateNotNullOnlySpeakerQueryChunkPrototype (speaker);
        StringBuffer query = new StringBuffer (queryWhat);
        boolean isWhereSet = false;
        for (Criterion criterion : criteria.getClauseCriterions()) {
            query.append (getQueryWHERE_AND (isWhereSet));
            isWhereSet = true;   
            query.append(criterion.getExpression());			
        }  
        Query jpaQuery = getEntityManager().createQuery(query.toString());
        isWhereSet = false;
        if (speaker.getId() != null) {
           jpaQuery.setParameter ("id", speaker.getId());
        }   
        if (speaker.getConferenceMemberId() != null) {
           jpaQuery.setParameter ("conferenceMemberId", speaker.getConferenceMemberId());
        }   
        if (speaker.getBio() != null) {
           jpaQuery.setParameter ("bio", speaker.getBio());
        }   
        if (speaker.getPhoto() != null) {
           jpaQuery.setParameter ("photo", speaker.getPhoto());
        }   
        if (speaker.getWebSiteUrl() != null) {
           jpaQuery.setParameter ("webSiteUrl", speaker.getWebSiteUrl());
        }   
        if (speaker.getSponsorId() != null) {
           jpaQuery.setParameter ("sponsorId", speaker.getSponsorId());
        }   
		return jpaQuery.executeUpdate();           
    }
	
	public Speaker affectSpeaker (Speaker speaker) {
	    return referSpeaker (speaker, false);		    
	}
		
	/**
	 * Assign the first speaker retrieved corresponding to the speaker criteria.
	 * Blank criteria are mapped to null.
	 * If no criteria is found, null is returned.
	 * If there is no speaker corresponding in the database. Then speaker is inserted and returned with its primary key(s). 
	 */
	public Speaker assignSpeaker (Speaker speaker) {
	    return referSpeaker (speaker, true);	
	}
	
	public Speaker referSpeaker (Speaker speaker, boolean isAssign) {
		speaker = assignBlankToNull (speaker);
		if (isAllNull(speaker))
			return null;
		else {
			List<Speaker> list = searchPrototypeSpeaker(speaker);
			if (list.isEmpty()) {
			    if (isAssign)
			       insertSpeaker(speaker);
			    else
				   return null;
			}
			else if (list.size()==1)
				speaker.copy(list.get(0));
			else 
				//TODO log error
				speaker.copy(list.get(0));
		}
		return speaker;		    
	}

   public Speaker assignSpeakerUseCache (Speaker speaker) {
      return referSpeakerUseCache (speaker, true);
   }
      		
   public Speaker affectSpeakerUseCache (Speaker speaker) {
      return referSpeakerUseCache (speaker, false);
   }
      		
   public Speaker referSpeakerUseCache (Speaker speaker, boolean isAssign) {
	  String key = getCacheKey(null, speaker, null, "assignSpeaker");
      Speaker speakerCache = (Speaker)simpleCache.get(key);
      if (speakerCache==null) {
         speakerCache = referSpeaker (speaker, isAssign);
         if (key!=null)
         	simpleCache.put(key, speakerCache);
      }
      speaker.copy(speakerCache);
      return speakerCache;
   }	

	private String getCacheKey (Speaker speakerWhat, Speaker positiveSpeaker, Speaker negativeSpeaker, String queryKey) {
	    StringBuffer sb = new StringBuffer();
	    sb.append(queryKey);
	    if (speakerWhat!=null)
	       sb.append(speakerWhat.toStringWithParents());
	    if (positiveSpeaker!=null)
	       sb.append(positiveSpeaker.toStringWithParents());
	    if (negativeSpeaker!=null)
	       sb.append(negativeSpeaker.toStringWithParents());
	    return sb.toString();
	}
	
    public Speaker partialLoadWithParentFirstSpeaker(Speaker speakerWhat, Speaker positiveSpeaker, Speaker negativeSpeaker){
		List <Speaker> list = partialLoadWithParentSpeaker(speakerWhat, positiveSpeaker, negativeSpeaker);
		return (!list.isEmpty())?(Speaker)list.get(0):null;
    }
    
    public Speaker partialLoadWithParentFirstSpeakerUseCache(Speaker speakerWhat, Speaker positiveSpeaker, Speaker negativeSpeaker, Boolean useCache){
		List <Speaker> list = partialLoadWithParentSpeakerUseCache(speakerWhat, positiveSpeaker, negativeSpeaker, useCache);
		return (!list.isEmpty())?(Speaker)list.get(0):null;
    }
	
	public Speaker partialLoadWithParentFirstSpeakerUseCacheOnResult(Speaker speakerWhat, Speaker positiveSpeaker, Speaker negativeSpeaker, Boolean useCache){
		List <Speaker> list = partialLoadWithParentSpeakerUseCacheOnResult(speakerWhat, positiveSpeaker, negativeSpeaker, useCache);
		return (!list.isEmpty())?(Speaker)list.get(0):null;
    }
	//
	protected List<Speaker> partialLoadWithParentSpeaker(Speaker speakerWhat, Speaker positiveSpeaker, Speaker negativeSpeaker, Integer nbOfResult, Boolean useCache) {
		 return partialLoadWithParentSpeaker(speakerWhat, positiveSpeaker, negativeSpeaker, new QuerySelectInit(), nbOfResult, useCache);
	}	  

	protected List partialLoadWithParentSpeakerQueryResult (Speaker speakerWhat, Speaker positiveSpeaker, Speaker negativeSpeaker, Integer nbOfResult, Boolean useCache) {
		 return partialLoadWithParentSpeakerQueryResult (speakerWhat, positiveSpeaker, negativeSpeaker, new QuerySelectInit(), nbOfResult, useCache);
	}	
    
    public List<Speaker> getDistinctSpeaker(Speaker speakerWhat, Speaker positiveSpeaker, Speaker negativeSpeaker) {
		 return partialLoadWithParentSpeaker(speakerWhat, positiveSpeaker, negativeSpeaker, new QuerySelectDistinctInit(), null, false);
	}
	
	public List<Speaker> partialLoadWithParentSpeaker(Speaker speakerWhat, Speaker positiveSpeaker, Speaker negativeSpeaker) {
		 return partialLoadWithParentSpeaker(speakerWhat, positiveSpeaker, negativeSpeaker, new QuerySelectInit(), null, false);
	}	
  
	public List<Speaker> partialLoadWithParentSpeakerUseCacheOnResult(Speaker speakerWhat, Speaker positiveSpeaker, Speaker negativeSpeaker, Boolean useCache) {
		String key = getCacheKey(speakerWhat, positiveSpeaker, negativeSpeaker, "partialLoadWithParentSpeaker");
		List<Speaker> list = (List<Speaker>)simpleCache.get(key);
		if (list==null || list.isEmpty()) {
			list = partialLoadWithParentSpeaker(speakerWhat, positiveSpeaker, negativeSpeaker);
			if (!list.isEmpty())
			   simpleCache.put(key, list);
		}
		return list;	
	}	

	public List<Speaker> partialLoadWithParentSpeakerUseCache(Speaker speakerWhat, Speaker positiveSpeaker, Speaker negativeSpeaker, Boolean useCache) {
		String key = getCacheKey(speakerWhat, positiveSpeaker, negativeSpeaker, "partialLoadWithParentSpeaker");
		List<Speaker> list = (List<Speaker>)simpleCache.get(key);
		if (list==null) {
			list = partialLoadWithParentSpeaker(speakerWhat, positiveSpeaker, negativeSpeaker);
			simpleCache.put(key, list);
		}
		return list;	
	}	
	
	private List<Speaker> handleLoadWithParentSpeaker(Map beanPath, List list, Speaker speakerWhat) {
	    return handleLoadWithParentSpeaker(beanPath, list, speakerWhat, true);  
	}
	
	private List<Speaker> handleLoadWithParentSpeaker(Map beanPath, List list, Speaker speakerWhat, boolean isHql) {
		if (beanPath.size()==1) {
			return handlePartialLoadWithParentSpeakerWithOneElementInRow(list, beanPath, speakerWhat, isHql);
		}
		return handlePartialLoadWithParentSpeaker(list, beanPath, speakerWhat, isHql);	
	}
	
    	// to set in super class	
	protected void populateSpeaker (Speaker speaker, Object value, String beanPath) throws IllegalAccessException, InvocationTargetException, NoSuchMethodException {
	   BeanUtils.populateBeanObject(speaker, beanPath, value);
	}
	
	protected void populateSpeakerFromSQL (Speaker speaker, Object value, String beanPath) throws IllegalAccessException, InvocationTargetException, NoSuchMethodException {
	   BeanUtils.populateBeanObject(speaker, beanPath, value);
	}
    	// to set in super class BEWARE: genericity is only one level!!!!! first level is a copy second level is a reference!!! change to speaker.clone() instead
	private Speaker cloneSpeaker (Speaker speaker) throws IllegalAccessException, InvocationTargetException, InstantiationException, NoSuchMethodException {
		//return (Speaker) BeanUtils.cloneBeanObject(speaker);
	   if (speaker==null) return new Speaker();
	   return speaker.clone();
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
	
    public List<Speaker> countDistinct (Speaker whatMask, Speaker whereEqCriteria) {
       return partialLoadWithParentSpeaker(whatMask, whereEqCriteria, null, new QuerySelectCountInit("speaker"), null, false);
    }   
    	
    public Long count(Speaker whereEqCriteria) {
        List<Speaker> list = partialLoadWithParentSpeaker(null, whereEqCriteria, null, new QueryCountInit("speaker"), null, false);    
    	if (!list.isEmpty())
    		return list.get(0).getCount__();
    	return 0L;
    }
		
		  		
   public Speaker getFirstSpeakerWhereConditionsAre (Speaker speaker) {
      List<Speaker> list = partialLoadWithParentSpeaker(getDefaultSpeakerWhat(), speaker, null, 1, false);
      if (list.isEmpty()) {
         return null;
      }
      else if (list.size()==1)
         return list.get(0);
      else 
      //TODO log error
         return list.get(0);	
	}

   private List getFirstResultWhereConditionsAre (Speaker speaker) {
      return  partialLoadWithParentSpeakerQueryResult(getDefaultSpeakerWhat(), speaker, null, 1, false);	
   }
   
   protected Speaker getDefaultSpeakerWhat() {
      Speaker speaker = new Speaker();
      speaker.setId(Long.valueOf("-1"));
      return speaker;
   }
   
	public Speaker getFirstSpeaker (Speaker speaker) {
		if (isAllNull(speaker))
			return null;
		else {
			List<Speaker> list = searchPrototype (speaker, 1);
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
    * checks if the Speaker entity exists
    */           
    public boolean existsSpeaker (Speaker speaker) {
       if (getFirstSpeaker(speaker)!=null)
          return true;
       return false;  
    }
        
    public boolean existsSpeakerWhereConditionsAre (Speaker speaker) {
       if (getFirstResultWhereConditionsAre (speaker).isEmpty())
          return false;
       return true;  
    }


	
	private int countPartialField (Speaker speaker) {
	   int cpt = 0;
       if (speaker.getId() != null) {
          cpt++;
       }
       if (speaker.getConferenceMemberId() != null) {
          cpt++;
       }
       if (speaker.getBio() != null) {
          cpt++;
       }
       if (speaker.getPhoto() != null) {
          cpt++;
       }
       if (speaker.getWebSiteUrl() != null) {
          cpt++;
       }
       if (speaker.getSponsorId() != null) {
          cpt++;
       }
       return cpt;
	}   
  	
	public List<Speaker> partialLoadWithParentSpeaker(Speaker speakerWhat, Speaker positiveSpeaker, Speaker negativeSpeaker, QueryWhatInit queryWhatInit, Integer nbOfResult, Boolean useCache) {
		Map beanPath = new Hashtable();
		List list = partialLoadWithParentSpeakerJPAQueryResult (speakerWhat, positiveSpeaker, negativeSpeaker, queryWhatInit, beanPath, nbOfResult, useCache);
		if (beanPath.size()==1) {
			return handlePartialLoadWithParentSpeakerWithOneElementInRow(list, beanPath, speakerWhat, true);
		}
		return handlePartialLoadWithParentSpeaker(list, beanPath, speakerWhat, true);
	}	

	private List partialLoadWithParentSpeakerQueryResult(Speaker speakerWhat, Speaker positiveSpeaker, Speaker negativeSpeaker, QueryWhatInit queryWhatInit, Integer nbOfResult, Boolean useCache) {
		return partialLoadWithParentSpeakerJPAQueryResult (speakerWhat, positiveSpeaker, negativeSpeaker, queryWhatInit, new Hashtable(), nbOfResult, useCache);
  }	
  
	private List partialLoadWithParentSpeakerJPAQueryResult(Speaker speakerWhat, Speaker positiveSpeaker, Speaker negativeSpeaker, QueryWhatInit queryWhatInit, Map beanPath, Integer nbOfResult, Boolean useCache) {
		Query hquery = getPartialLoadWithParentSpeakerJPAQuery (speakerWhat, positiveSpeaker, negativeSpeaker, beanPath, queryWhatInit, nbOfResult);
		return hquery.getResultList();
  }	
   /**
    * @returns an JPA Hsql query based on entity Speaker and its parents and the maximum number of result
    */
	protected Query getPartialLoadWithParentSpeakerJPAQuery (Speaker speakerWhat, Speaker positiveSpeaker, Speaker negativeSpeaker, Map beanPath, QueryWhatInit queryWhatInit, Integer nbOfResult) {
	   Query query = getEntityManager().createQuery(getPartialLoadWithParentSpeakerHsqlQuery (speakerWhat, positiveSpeaker, negativeSpeaker, beanPath, queryWhatInit));
	   if (nbOfResult!=null)
	      query.setMaxResults(nbOfResult);
	   return query;
  }
  
	private List<Speaker> handlePartialLoadWithParentSpeaker(List<Object[]> list, Map<Integer, String> beanPath, Speaker speakerWhat, boolean isJql) {
		try {
			return convertPartialLoadWithParentSpeaker(list, beanPath, speakerWhat);
		} catch (Exception ex) {
			log.error("Error conversion list from handlePartialLoadWithParentSpeaker, message:"+ex.getMessage());
			return new ArrayList<Speaker>();
		}
    }

	private List<Speaker> handlePartialLoadWithParentSpeakerWithOneElementInRow(List<Object> list, Map<Integer, String> beanPath, Speaker speakerWhat, boolean isJql) {
		try {
			return convertPartialLoadWithParentSpeakerWithOneElementInRow(list, beanPath, speakerWhat);
		} catch (Exception ex) {
			log.error("Error conversion list from handlePartialLoadWithParentSpeakerWithOneElementInRow, message:"+ex.getMessage());
			return new ArrayList<Speaker>();
		}
    }
    	
	 private List<Speaker> convertPartialLoadWithParentSpeaker(List<Object[]> list, Map<Integer, String> beanPath, Speaker speakerWhat) throws IllegalAccessException, InvocationTargetException, InstantiationException, NoSuchMethodException {
		 List<Speaker> resultList = new ArrayList<Speaker>();
		 for (Object[] row : list) {		
		    Speaker speaker = cloneSpeaker (speakerWhat);
		    Iterator<Entry<Integer, String>> iter = beanPath.entrySet().iterator();	
		    while (iter.hasNext()) {
		       Entry entry = iter.next();
		       populateSpeaker (speaker, row[(Integer)entry.getKey()], (String)entry.getValue());
		    }
		    resultList.add(speaker);
		 }
		 return resultList;		
	 }	
    
	 private List<Speaker> convertPartialLoadWithParentSpeakerWithOneElementInRow(List<Object> list, Map<Integer, String> beanPath, Speaker speakerWhat) throws IllegalAccessException, InvocationTargetException, InstantiationException, NoSuchMethodException {
		 List<Speaker> resultList = new ArrayList<Speaker>();
		 for (Object row : list) {		
		    Speaker speaker = cloneSpeaker (speakerWhat);
		    Iterator<Entry<Integer, String>> iter = beanPath.entrySet().iterator();	
		    while (iter.hasNext()) {
		       Entry entry = iter.next();
		       populateSpeaker (speaker, row, (String)entry.getValue());
		    }
		    resultList.add(speaker);
		 }		 
		 return resultList;		
	 }
   
	public List partialLoadWithParentForBean(Object bean, Speaker speakerWhat, Speaker positiveSpeaker, Speaker negativeSpeaker) {
		Map beanPath = new Hashtable();
		Query hquery = getPartialLoadWithParentSpeakerJPAQuery (speakerWhat, positiveSpeaker, negativeSpeaker, beanPath, new QuerySelectInit(), null);
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
	public String getPartialLoadWithParentSpeakerHsqlQuery (Speaker speaker, Speaker positiveSpeaker, Speaker negativeSpeaker, Map beanPath, QueryWhatInit queryWhatInit) {
		Hashtable aliasWhatHt = new Hashtable();
		String what = getPartialLoadWithParentSpeakerQuery (speaker, null, aliasWhatHt, null, null, beanPath, "", queryWhatInit);
		Hashtable aliasWhereHt = new Hashtable();
		String where = getPartialLoadWithParentWhereSpeakerQuery (positiveSpeaker, null, aliasWhatHt, aliasWhereHt, null, null);
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
	public List<Speaker> partialLoadSpeaker(Speaker speaker, Speaker positiveSpeaker, Speaker negativeSpeaker) {
	    Query hquery = getEntityManager().createQuery(getPartialLoadSpeakerQuery (speaker, positiveSpeaker, negativeSpeaker));
		int countPartialField = countPartialField(speaker);
		if (countPartialField==0) 
		   return new ArrayList<Speaker>();
		List list = hquery.getResultList();
		Iterator iter = list.iterator();
		List<Speaker> returnList = new ArrayList<Speaker>();
		while(iter.hasNext()) {
	       int index = 0;
	       Object[] row;
	       if (countPartialField==1) {
	    	  row = new Object[1];
	    	  row[0] = iter.next();
	       } else 
		      row = (Object[]) iter.next();
		   Speaker speakerResult = new Speaker();
           if (speaker.getId() != null) {
			  speakerResult.setId((Long) row[index]);
			  index++;
           }
           if (speaker.getConferenceMemberId() != null) {
			  speakerResult.setConferenceMemberId_((Long) row[index]);
			  index++;
           }
           if (speaker.getBio() != null) {
			  speakerResult.setBio((String) row[index]);
			  index++;
           }
           if (speaker.getPhoto() != null) {
			  speakerResult.setPhoto((String) row[index]);
			  index++;
           }
           if (speaker.getWebSiteUrl() != null) {
			  speakerResult.setWebSiteUrl((String) row[index]);
			  index++;
           }
           if (speaker.getSponsorId() != null) {
			  speakerResult.setSponsorId_((Long) row[index]);
			  index++;
           }
           returnList.add(speakerResult);
        }
	    return returnList;
	}

	public static String getPartialLoadWithParentWhereSpeakerQuery (
	   Speaker speaker, Boolean isWhereSet, Hashtable aliasHt, Hashtable aliasWhereHt, String childAlias, String childFKAlias) {
	   if (speaker==null)
	      return "";
	   String alias = null;
	   if (aliasWhereHt == null) {
	      aliasWhereHt = new Hashtable();
	   } 
	   if (isLookedUp(speaker)){
	      alias = getNextAlias (aliasWhereHt, speaker);
		  aliasWhereHt.put (getAliasKey(alias), getAliasConnection(alias, childAlias, childFKAlias));
	   }
	   if (isWhereSet == null)
          isWhereSet = false;
       StringBuffer query = new StringBuffer();
       if (speaker.getId() != null) {
           query.append (getQueryBLANK_AND (isWhereSet));
		   isWhereSet = true;
           query.append(" "+alias+".id = "+ speaker.getId() + " ");
       }
       if (speaker.getConferenceMemberId() != null) {
           query.append (getQueryBLANK_AND (isWhereSet));
		   isWhereSet = true;
           query.append(" "+alias+".conferenceMemberId = "+ speaker.getConferenceMemberId() + " ");
       }
       if (speaker.getBio() != null) {
           query.append (getQueryBLANK_AND (isWhereSet));
		   isWhereSet = true;
           query.append(" "+alias+".bio = '"+ speaker.getBio()+"' ");
       }
       if (speaker.getPhoto() != null) {
           query.append (getQueryBLANK_AND (isWhereSet));
		   isWhereSet = true;
           query.append(" "+alias+".photo = '"+ speaker.getPhoto()+"' ");
       }
       if (speaker.getWebSiteUrl() != null) {
           query.append (getQueryBLANK_AND (isWhereSet));
		   isWhereSet = true;
           query.append(" "+alias+".webSiteUrl = '"+ speaker.getWebSiteUrl()+"' ");
       }
       if (speaker.getSponsorId() != null) {
           query.append (getQueryBLANK_AND (isWhereSet));
		   isWhereSet = true;
           query.append(" "+alias+".sponsorId = "+ speaker.getSponsorId() + " ");
       }
       if (speaker.getConferenceMemberId()!=null) {
	      String chunck = net.sf.mp.demo.conference.dao.impl.jpa.conference.ConferenceMemberExtendedJPAImpl.getPartialLoadWithParentWhereConferenceMemberQuery(
		      speaker.getConferenceMemberId(), 
			  isWhereSet, aliasHt, aliasWhereHt, alias, "conferenceMemberId");
		  if (chunck!=null && !chunck.equals("")) {
		     query.append(chunck);
		     isWhereSet=true;
		  }  	  
	   }
       if (speaker.getSponsorId()!=null) {
	      String chunck = net.sf.mp.demo.conference.dao.impl.jpa.conference.SponsorExtendedJPAImpl.getPartialLoadWithParentWhereSponsorQuery(
		      speaker.getSponsorId(), 
			  isWhereSet, aliasHt, aliasWhereHt, alias, "sponsorId");
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
	
    public static String getPartialLoadWithParentSpeakerQuery (
	   Speaker speaker, Boolean isWhereSet, Hashtable aliasHt, String childAlias, String childFKAlias, Map beanPath, String rootPath, QueryWhatInit queryWhatInit) {
	   if (speaker==null)
	      return "";
	   String alias = null;
	   if (aliasHt == null) {
	      aliasHt = new Hashtable();
	   } 
	   if (isLookedUp(speaker)){
	      alias = getNextAlias (aliasHt, speaker);
		  aliasHt.put (getAliasKey(alias), getAliasConnection(alias, childAlias, childFKAlias));
	   }
	   if (isWhereSet == null)
          isWhereSet = false;
       StringBuffer query = new StringBuffer();
       if (speaker.getId() != null) {
          query.append (queryWhatInit.getWhatInit (isWhereSet));
          isWhereSet = true; 
          beanPath.put(beanPath.size(), rootPath+"id");
          query.append(" "+alias+".id ");
       }
       if (speaker.getConferenceMemberId() != null) {
          query.append (queryWhatInit.getWhatInit (isWhereSet));
          isWhereSet = true; 
          beanPath.put(beanPath.size(), rootPath+"conferenceMemberId");
          query.append(" "+alias+".conferenceMemberId ");
       }
       if (speaker.getBio() != null) {
          query.append (queryWhatInit.getWhatInit (isWhereSet));
          isWhereSet = true; 
          beanPath.put(beanPath.size(), rootPath+"bio");
          query.append(" "+alias+".bio ");
       }
       if (speaker.getPhoto() != null) {
          query.append (queryWhatInit.getWhatInit (isWhereSet));
          isWhereSet = true; 
          beanPath.put(beanPath.size(), rootPath+"photo");
          query.append(" "+alias+".photo ");
       }
       if (speaker.getWebSiteUrl() != null) {
          query.append (queryWhatInit.getWhatInit (isWhereSet));
          isWhereSet = true; 
          beanPath.put(beanPath.size(), rootPath+"webSiteUrl");
          query.append(" "+alias+".webSiteUrl ");
       }
       if (speaker.getSponsorId() != null) {
          query.append (queryWhatInit.getWhatInit (isWhereSet));
          isWhereSet = true; 
          beanPath.put(beanPath.size(), rootPath+"sponsorId");
          query.append(" "+alias+".sponsorId ");
       }
       if (speaker.getConferenceMemberId()!=null) {
	      String chunck = net.sf.mp.demo.conference.dao.impl.jpa.conference.ConferenceMemberExtendedJPAImpl.getPartialLoadWithParentConferenceMemberQuery(
		      speaker.getConferenceMemberId(), 
			  isWhereSet, aliasHt, alias, "conferenceMemberId", beanPath, rootPath+"conferenceMemberId.", queryWhatInit);
		  if (chunck!=null && !chunck.equals("")) {
		     query.append(chunck);
		     isWhereSet=true;
		  } 
	   }  
       if (speaker.getSponsorId()!=null) {
	      String chunck = net.sf.mp.demo.conference.dao.impl.jpa.conference.SponsorExtendedJPAImpl.getPartialLoadWithParentSponsorQuery(
		      speaker.getSponsorId(), 
			  isWhereSet, aliasHt, alias, "sponsorId", beanPath, rootPath+"sponsorId.", queryWhatInit);
		  if (chunck!=null && !chunck.equals("")) {
		     query.append(chunck);
		     isWhereSet=true;
		  } 
	   }  
//       query.append(getSpeakerSearchEqualQuery (positiveSpeaker, negativeSpeaker));
	   return query.toString(); 
    }
	
	protected static String getAliasConnection(String existingAlias, String childAlias, String childFKAlias) {
		if (childAlias==null)
		   return "";
		return childAlias+"."+childFKAlias+" = "+existingAlias+"."+"id";
	}
	
	protected static String getAliasKey (String alias) {
	  //TODO this is a temporary solution use a dedicated object in BslaHiberateDaoSupport
		return "Speaker|"+alias;
	}
	
	protected static String getAliasKeyAlias (String aliasKey) {
	  //TODO this is a temporary solution use a dedicated object in BslaHiberateDaoSupport
		return StringUtils.substringAfter(aliasKey, "|");
	}
	
	protected static String getAliasKeyDomain (String aliasKey) {
	  //TODO this is a temporary solution use a dedicated object in BslaHiberateDaoSupport
	  return StringUtils.substringBefore(aliasKey, "|");
	}
	
	protected static String getNextAlias (Hashtable aliasHt, Speaker speaker) {
		int cptSameAlias = 0;
		Enumeration<String> keys = aliasHt.keys();
		while (keys.hasMoreElements()) {
			String key = keys.nextElement();
			if (key.startsWith("speaker"))
				cptSameAlias++;
		}
		if (cptSameAlias==0)
			return "speaker";
		else
			return "speaker_"+cptSameAlias;
	}
	
	
	protected static boolean isLookedUp (Speaker speaker) {
	   if (speaker==null)
		  return false;
       if (speaker.getId() != null) {
	      return true;
       }
       if (speaker.getConferenceMemberId() != null) {
	      return true;
       }
       if (speaker.getBio() != null) {
	      return true;
       }
       if (speaker.getPhoto() != null) {
	      return true;
       }
       if (speaker.getWebSiteUrl() != null) {
	      return true;
       }
       if (speaker.getSponsorId() != null) {
	      return true;
       }
       if (speaker.getConferenceMemberId()!=null) {
	      return true;
	   }  
       if (speaker.getSponsorId()!=null) {
	      return true;
	   }  
       return false;   
	}
	
    public String getPartialLoadSpeakerQuery(
	   Speaker speaker, 
	   Speaker positiveSpeaker, 
	   Speaker negativeSpeaker) {
       boolean isWhereSet = false;
       StringBuffer query = new StringBuffer();
       if (speaker.getId() != null) {
          query.append (getQuerySelectComma (isWhereSet));
          isWhereSet = true; 
          query.append(" id ");
       }
       if (speaker.getConferenceMemberId() != null) {
          query.append (getQuerySelectComma (isWhereSet));
          isWhereSet = true; 
          query.append(" conferenceMemberId ");
       }
       if (speaker.getBio() != null) {
          query.append (getQuerySelectComma (isWhereSet));
          isWhereSet = true; 
          query.append(" bio ");
       }
       if (speaker.getPhoto() != null) {
          query.append (getQuerySelectComma (isWhereSet));
          isWhereSet = true; 
          query.append(" photo ");
       }
       if (speaker.getWebSiteUrl() != null) {
          query.append (getQuerySelectComma (isWhereSet));
          isWhereSet = true; 
          query.append(" webSiteUrl ");
       }
       if (speaker.getSponsorId() != null) {
          query.append (getQuerySelectComma (isWhereSet));
          isWhereSet = true; 
          query.append(" sponsorId ");
       }
       query.append(getSpeakerSearchEqualQuery (positiveSpeaker, negativeSpeaker));
	   return query.toString(); 
    }
	
	public List<Speaker> searchPrototypeWithCacheSpeaker(Speaker speaker) {
		SimpleCache simpleCache = new SimpleCache();
		List<Speaker> list = (List<Speaker>)simpleCache.get(speaker.toString());
		if (list==null) {
			list = searchPrototypeSpeaker(speaker);
			simpleCache.put(speaker.toString(), list);
		}
		return list;
	}

    public List<Speaker> loadGraph(Speaker graphMaskWhat, List<Speaker> whereMask) {
        return loadGraphOneLevel(graphMaskWhat, whereMask);
    }
 
    public List<Speaker> loadGraphOneLevel(Speaker graphMaskWhat, List<Speaker> whereMask) {
        //first get roots element from where list mask
        // this brings the level 0 of the graph (root level)
        graphMaskWhat.setId(graphMaskWhat.longMask__);
        List<Speaker> speakers = searchPrototypeSpeaker (whereMask);
        // for each sub level perform the search with a subquery then reassemble
        // 1. get all sublevel queries
        // 2. perform queries on the correct dao
        // 3. reassemble
        return getLoadGraphOneLevel (graphMaskWhat, speakers);
    }

	private List<Speaker> copy(List<Speaker> inputs) {
		List<Speaker> l = new ArrayList<Speaker>();
		for (Speaker input : inputs) {
			Speaker copy = new Speaker();
			copy.copy(input);
			l.add(copy);
		}
		return l;
	}
	   
	private List<Speaker> getLoadGraphOneLevel (Speaker graphMaskWhat, List<Speaker> parents) {
	    return loadGraphFromParentKey (graphMaskWhat, parents);
	} 
	
	public List<Speaker> loadGraphFromParentKey (Speaker graphMaskWhat, List<Speaker> parents) {
		//foreach children:
		//check if not empty take first
		parents = copy (parents); //working with detached entities to avoid unnecessary sql calls
		if (parents==null || parents.isEmpty())
		   return parents;
		List<String> ids = getPk (parents);
		return parents;
	}
	
	private List<String> getPk(List<Speaker> input) {
		List<String> s = new ArrayList<String>();
		for (Speaker t : input) {
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
