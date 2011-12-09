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
import net.sf.mp.demo.conference.dao.face.conference.SponsorExtDao;
import net.sf.mp.demo.conference.domain.conference.Sponsor;
import net.sf.mp.demo.conference.dao.impl.jpa.conference.SponsorJPAImpl;

import net.sf.mp.demo.conference.domain.conference.Speaker;
import net.sf.mp.demo.conference.dao.impl.jpa.conference.SpeakerExtendedJPAImpl;
import net.sf.mp.demo.conference.dao.impl.jpa.conference.AddressExtendedJPAImpl;
import net.sf.mp.demo.conference.dao.impl.jpa.conference.ConferenceExtendedJPAImpl;
/**
 *
 * <p>Title: SponsorExtendedJPAImpl</p>
 *
 * <p>Description: Interface of a Data access object dealing with SponsorExtendedJPAImpl
 * persistence. It offers a set of methods which allow for saving,
 * deleting and searching SponsorExtendedJPAImpl objects</p>
 *
 */

public class SponsorExtendedJPAImpl extends SponsorJPAImpl implements SponsorExtDao {

    private Logger log = Logger.getLogger(this.getClass());
    
    private SimpleCache simpleCache = new SimpleCache();
    private SpeakerExtendedJPAImpl speakerextendedjpaimpl;
    private EntityManager emForRecursiveDao; // dao that needs other dao in a recursive manner not support by spring configuration

    public SponsorExtendedJPAImpl () {}

    /**
     * generic to move after in superclass
     */
    public SponsorExtendedJPAImpl (EntityManager emForRecursiveDao) {
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
     * Inserts a Sponsor entity with cascade of its children
     * @param Sponsor sponsor
     */
    public void insertSponsorWithCascade(Sponsor sponsor) {
    	SponsorExtendedJPAImpl sponsorextendedjpaimpl = new SponsorExtendedJPAImpl(getEntityManager());
    	sponsorextendedjpaimpl.insertSponsorWithCascade(sponsorextendedjpaimpl.getEntityManagerForRecursiveDao(), sponsor);
    }
     
    public void insertSponsorWithCascade(EntityManager emForRecursiveDao, Sponsor sponsor) {
       insertSponsor(emForRecursiveDao, sponsor);
       if (!sponsor.getSpeakerSponsorViaSponsorId().isEmpty()) {
          SpeakerExtendedJPAImpl speakerextendedjpaimpl = new SpeakerExtendedJPAImpl (emForRecursiveDao);
          for (Speaker _speakerSponsorViaSponsorId : sponsor.getSpeakerSponsorViaSponsorId()) {
             speakerextendedjpaimpl.insertSpeakerWithCascade(emForRecursiveDao, _speakerSponsorViaSponsorId);
          }
       } 
    }
        
    /**
     * Inserts a list of Sponsor entity with cascade of its children
     * @param List<Sponsor> sponsors
     */
    public void insertSponsorsWithCascade(List<Sponsor> sponsors) {
       for (Sponsor sponsor : sponsors) {
          insertSponsorWithCascade(sponsor);
       }
    } 
        
    /**
     * lookup Sponsor entity Sponsor, criteria and max result number
     */
    public List<Sponsor> lookupSponsor(Sponsor sponsor, Criteria criteria, Integer numberOfResult, EntityManager em) {
		boolean isWhereSet = false;
        StringBuffer query = new StringBuffer();
        query.append ("SELECT sponsor FROM Sponsor sponsor ");
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
    
    public List<Sponsor> lookupSponsor(Sponsor sponsor, Criteria criteria, Integer numberOfResult) {
		return lookupSponsor(sponsor, criteria, numberOfResult, getEntityManager());
    }

    public Integer updateNotNullOnlySponsor (Sponsor sponsor, Criteria criteria) {
        String queryWhat = getUpdateNotNullOnlySponsorQueryChunkPrototype (sponsor);
        StringBuffer query = new StringBuffer (queryWhat);
        boolean isWhereSet = false;
        for (Criterion criterion : criteria.getClauseCriterions()) {
            query.append (getQueryWHERE_AND (isWhereSet));
            isWhereSet = true;   
            query.append(criterion.getExpression());			
        }  
        Query jpaQuery = getEntityManager().createQuery(query.toString());
        isWhereSet = false;
        if (sponsor.getId() != null) {
           jpaQuery.setParameter ("id", sponsor.getId());
        }   
        if (sponsor.getName() != null) {
           jpaQuery.setParameter ("name", sponsor.getName());
        }   
        if (sponsor.getPrivilegeType() != null) {
           jpaQuery.setParameter ("privilegeType", sponsor.getPrivilegeType());
        }   
        if (sponsor.getStatus() != null) {
           jpaQuery.setParameter ("status", sponsor.getStatus());
        }   
        if (sponsor.getConferenceId() != null) {
           jpaQuery.setParameter ("conferenceId", sponsor.getConferenceId());
        }   
        if (sponsor.getAddressId() != null) {
           jpaQuery.setParameter ("addressId", sponsor.getAddressId());
        }   
		return jpaQuery.executeUpdate();           
    }
	
	public Sponsor affectSponsor (Sponsor sponsor) {
	    return referSponsor (sponsor, false);		    
	}
		
	/**
	 * Assign the first sponsor retrieved corresponding to the sponsor criteria.
	 * Blank criteria are mapped to null.
	 * If no criteria is found, null is returned.
	 * If there is no sponsor corresponding in the database. Then sponsor is inserted and returned with its primary key(s). 
	 */
	public Sponsor assignSponsor (Sponsor sponsor) {
	    return referSponsor (sponsor, true);	
	}
	
	public Sponsor referSponsor (Sponsor sponsor, boolean isAssign) {
		sponsor = assignBlankToNull (sponsor);
		if (isAllNull(sponsor))
			return null;
		else {
			List<Sponsor> list = searchPrototypeSponsor(sponsor);
			if (list.isEmpty()) {
			    if (isAssign)
			       insertSponsor(sponsor);
			    else
				   return null;
			}
			else if (list.size()==1)
				sponsor.copy(list.get(0));
			else 
				//TODO log error
				sponsor.copy(list.get(0));
		}
		return sponsor;		    
	}

   public Sponsor assignSponsorUseCache (Sponsor sponsor) {
      return referSponsorUseCache (sponsor, true);
   }
      		
   public Sponsor affectSponsorUseCache (Sponsor sponsor) {
      return referSponsorUseCache (sponsor, false);
   }
      		
   public Sponsor referSponsorUseCache (Sponsor sponsor, boolean isAssign) {
	  String key = getCacheKey(null, sponsor, null, "assignSponsor");
      Sponsor sponsorCache = (Sponsor)simpleCache.get(key);
      if (sponsorCache==null) {
         sponsorCache = referSponsor (sponsor, isAssign);
         if (key!=null)
         	simpleCache.put(key, sponsorCache);
      }
      sponsor.copy(sponsorCache);
      return sponsorCache;
   }	

	private String getCacheKey (Sponsor sponsorWhat, Sponsor positiveSponsor, Sponsor negativeSponsor, String queryKey) {
	    StringBuffer sb = new StringBuffer();
	    sb.append(queryKey);
	    if (sponsorWhat!=null)
	       sb.append(sponsorWhat.toStringWithParents());
	    if (positiveSponsor!=null)
	       sb.append(positiveSponsor.toStringWithParents());
	    if (negativeSponsor!=null)
	       sb.append(negativeSponsor.toStringWithParents());
	    return sb.toString();
	}
	
    public Sponsor partialLoadWithParentFirstSponsor(Sponsor sponsorWhat, Sponsor positiveSponsor, Sponsor negativeSponsor){
		List <Sponsor> list = partialLoadWithParentSponsor(sponsorWhat, positiveSponsor, negativeSponsor);
		return (!list.isEmpty())?(Sponsor)list.get(0):null;
    }
    
    public Sponsor partialLoadWithParentFirstSponsorUseCache(Sponsor sponsorWhat, Sponsor positiveSponsor, Sponsor negativeSponsor, Boolean useCache){
		List <Sponsor> list = partialLoadWithParentSponsorUseCache(sponsorWhat, positiveSponsor, negativeSponsor, useCache);
		return (!list.isEmpty())?(Sponsor)list.get(0):null;
    }
	
	public Sponsor partialLoadWithParentFirstSponsorUseCacheOnResult(Sponsor sponsorWhat, Sponsor positiveSponsor, Sponsor negativeSponsor, Boolean useCache){
		List <Sponsor> list = partialLoadWithParentSponsorUseCacheOnResult(sponsorWhat, positiveSponsor, negativeSponsor, useCache);
		return (!list.isEmpty())?(Sponsor)list.get(0):null;
    }
	//
	protected List<Sponsor> partialLoadWithParentSponsor(Sponsor sponsorWhat, Sponsor positiveSponsor, Sponsor negativeSponsor, Integer nbOfResult, Boolean useCache) {
		 return partialLoadWithParentSponsor(sponsorWhat, positiveSponsor, negativeSponsor, new QuerySelectInit(), nbOfResult, useCache);
	}	  

	protected List partialLoadWithParentSponsorQueryResult (Sponsor sponsorWhat, Sponsor positiveSponsor, Sponsor negativeSponsor, Integer nbOfResult, Boolean useCache) {
		 return partialLoadWithParentSponsorQueryResult (sponsorWhat, positiveSponsor, negativeSponsor, new QuerySelectInit(), nbOfResult, useCache);
	}	
    
    public List<Sponsor> getDistinctSponsor(Sponsor sponsorWhat, Sponsor positiveSponsor, Sponsor negativeSponsor) {
		 return partialLoadWithParentSponsor(sponsorWhat, positiveSponsor, negativeSponsor, new QuerySelectDistinctInit(), null, false);
	}
	
	public List<Sponsor> partialLoadWithParentSponsor(Sponsor sponsorWhat, Sponsor positiveSponsor, Sponsor negativeSponsor) {
		 return partialLoadWithParentSponsor(sponsorWhat, positiveSponsor, negativeSponsor, new QuerySelectInit(), null, false);
	}	
  
	public List<Sponsor> partialLoadWithParentSponsorUseCacheOnResult(Sponsor sponsorWhat, Sponsor positiveSponsor, Sponsor negativeSponsor, Boolean useCache) {
		String key = getCacheKey(sponsorWhat, positiveSponsor, negativeSponsor, "partialLoadWithParentSponsor");
		List<Sponsor> list = (List<Sponsor>)simpleCache.get(key);
		if (list==null || list.isEmpty()) {
			list = partialLoadWithParentSponsor(sponsorWhat, positiveSponsor, negativeSponsor);
			if (!list.isEmpty())
			   simpleCache.put(key, list);
		}
		return list;	
	}	

	public List<Sponsor> partialLoadWithParentSponsorUseCache(Sponsor sponsorWhat, Sponsor positiveSponsor, Sponsor negativeSponsor, Boolean useCache) {
		String key = getCacheKey(sponsorWhat, positiveSponsor, negativeSponsor, "partialLoadWithParentSponsor");
		List<Sponsor> list = (List<Sponsor>)simpleCache.get(key);
		if (list==null) {
			list = partialLoadWithParentSponsor(sponsorWhat, positiveSponsor, negativeSponsor);
			simpleCache.put(key, list);
		}
		return list;	
	}	
	
	private List<Sponsor> handleLoadWithParentSponsor(Map beanPath, List list, Sponsor sponsorWhat) {
	    return handleLoadWithParentSponsor(beanPath, list, sponsorWhat, true);  
	}
	
	private List<Sponsor> handleLoadWithParentSponsor(Map beanPath, List list, Sponsor sponsorWhat, boolean isHql) {
		if (beanPath.size()==1) {
			return handlePartialLoadWithParentSponsorWithOneElementInRow(list, beanPath, sponsorWhat, isHql);
		}
		return handlePartialLoadWithParentSponsor(list, beanPath, sponsorWhat, isHql);	
	}
	
    	// to set in super class	
	protected void populateSponsor (Sponsor sponsor, Object value, String beanPath) throws IllegalAccessException, InvocationTargetException, NoSuchMethodException {
	   BeanUtils.populateBeanObject(sponsor, beanPath, value);
	}
	
	protected void populateSponsorFromSQL (Sponsor sponsor, Object value, String beanPath) throws IllegalAccessException, InvocationTargetException, NoSuchMethodException {
	   BeanUtils.populateBeanObject(sponsor, beanPath, value);
	}
    	// to set in super class BEWARE: genericity is only one level!!!!! first level is a copy second level is a reference!!! change to sponsor.clone() instead
	private Sponsor cloneSponsor (Sponsor sponsor) throws IllegalAccessException, InvocationTargetException, InstantiationException, NoSuchMethodException {
		//return (Sponsor) BeanUtils.cloneBeanObject(sponsor);
	   if (sponsor==null) return new Sponsor();
	   return sponsor.clone();
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
	
    public List<Sponsor> countDistinct (Sponsor whatMask, Sponsor whereEqCriteria) {
       return partialLoadWithParentSponsor(whatMask, whereEqCriteria, null, new QuerySelectCountInit("sponsor"), null, false);
    }   
    	
    public Long count(Sponsor whereEqCriteria) {
        List<Sponsor> list = partialLoadWithParentSponsor(null, whereEqCriteria, null, new QueryCountInit("sponsor"), null, false);    
    	if (!list.isEmpty())
    		return list.get(0).getCount__();
    	return 0L;
    }
		
		  		
   public Sponsor getFirstSponsorWhereConditionsAre (Sponsor sponsor) {
      List<Sponsor> list = partialLoadWithParentSponsor(getDefaultSponsorWhat(), sponsor, null, 1, false);
      if (list.isEmpty()) {
         return null;
      }
      else if (list.size()==1)
         return list.get(0);
      else 
      //TODO log error
         return list.get(0);	
	}

   private List getFirstResultWhereConditionsAre (Sponsor sponsor) {
      return  partialLoadWithParentSponsorQueryResult(getDefaultSponsorWhat(), sponsor, null, 1, false);	
   }
   
   protected Sponsor getDefaultSponsorWhat() {
      Sponsor sponsor = new Sponsor();
      sponsor.setId(Long.valueOf("-1"));
      return sponsor;
   }
   
	public Sponsor getFirstSponsor (Sponsor sponsor) {
		if (isAllNull(sponsor))
			return null;
		else {
			List<Sponsor> list = searchPrototype (sponsor, 1);
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
    * checks if the Sponsor entity exists
    */           
    public boolean existsSponsor (Sponsor sponsor) {
       if (getFirstSponsor(sponsor)!=null)
          return true;
       return false;  
    }
        
    public boolean existsSponsorWhereConditionsAre (Sponsor sponsor) {
       if (getFirstResultWhereConditionsAre (sponsor).isEmpty())
          return false;
       return true;  
    }


	
	private int countPartialField (Sponsor sponsor) {
	   int cpt = 0;
       if (sponsor.getId() != null) {
          cpt++;
       }
       if (sponsor.getName() != null) {
          cpt++;
       }
       if (sponsor.getPrivilegeType() != null) {
          cpt++;
       }
       if (sponsor.getStatus() != null) {
          cpt++;
       }
       if (sponsor.getConferenceId() != null) {
          cpt++;
       }
       if (sponsor.getAddressId() != null) {
          cpt++;
       }
       return cpt;
	}   
  	
	public List<Sponsor> partialLoadWithParentSponsor(Sponsor sponsorWhat, Sponsor positiveSponsor, Sponsor negativeSponsor, QueryWhatInit queryWhatInit, Integer nbOfResult, Boolean useCache) {
		Map beanPath = new Hashtable();
		List list = partialLoadWithParentSponsorJPAQueryResult (sponsorWhat, positiveSponsor, negativeSponsor, queryWhatInit, beanPath, nbOfResult, useCache);
		if (beanPath.size()==1) {
			return handlePartialLoadWithParentSponsorWithOneElementInRow(list, beanPath, sponsorWhat, true);
		}
		return handlePartialLoadWithParentSponsor(list, beanPath, sponsorWhat, true);
	}	

	private List partialLoadWithParentSponsorQueryResult(Sponsor sponsorWhat, Sponsor positiveSponsor, Sponsor negativeSponsor, QueryWhatInit queryWhatInit, Integer nbOfResult, Boolean useCache) {
		return partialLoadWithParentSponsorJPAQueryResult (sponsorWhat, positiveSponsor, negativeSponsor, queryWhatInit, new Hashtable(), nbOfResult, useCache);
  }	
  
	private List partialLoadWithParentSponsorJPAQueryResult(Sponsor sponsorWhat, Sponsor positiveSponsor, Sponsor negativeSponsor, QueryWhatInit queryWhatInit, Map beanPath, Integer nbOfResult, Boolean useCache) {
		Query hquery = getPartialLoadWithParentSponsorJPAQuery (sponsorWhat, positiveSponsor, negativeSponsor, beanPath, queryWhatInit, nbOfResult);
		return hquery.getResultList();
  }	
   /**
    * @returns an JPA Hsql query based on entity Sponsor and its parents and the maximum number of result
    */
	protected Query getPartialLoadWithParentSponsorJPAQuery (Sponsor sponsorWhat, Sponsor positiveSponsor, Sponsor negativeSponsor, Map beanPath, QueryWhatInit queryWhatInit, Integer nbOfResult) {
	   Query query = getEntityManager().createQuery(getPartialLoadWithParentSponsorHsqlQuery (sponsorWhat, positiveSponsor, negativeSponsor, beanPath, queryWhatInit));
	   if (nbOfResult!=null)
	      query.setMaxResults(nbOfResult);
	   return query;
  }
  
	private List<Sponsor> handlePartialLoadWithParentSponsor(List<Object[]> list, Map<Integer, String> beanPath, Sponsor sponsorWhat, boolean isJql) {
		try {
			return convertPartialLoadWithParentSponsor(list, beanPath, sponsorWhat);
		} catch (Exception ex) {
			log.error("Error conversion list from handlePartialLoadWithParentSponsor, message:"+ex.getMessage());
			return new ArrayList<Sponsor>();
		}
    }

	private List<Sponsor> handlePartialLoadWithParentSponsorWithOneElementInRow(List<Object> list, Map<Integer, String> beanPath, Sponsor sponsorWhat, boolean isJql) {
		try {
			return convertPartialLoadWithParentSponsorWithOneElementInRow(list, beanPath, sponsorWhat);
		} catch (Exception ex) {
			log.error("Error conversion list from handlePartialLoadWithParentSponsorWithOneElementInRow, message:"+ex.getMessage());
			return new ArrayList<Sponsor>();
		}
    }
    	
	 private List<Sponsor> convertPartialLoadWithParentSponsor(List<Object[]> list, Map<Integer, String> beanPath, Sponsor sponsorWhat) throws IllegalAccessException, InvocationTargetException, InstantiationException, NoSuchMethodException {
		 List<Sponsor> resultList = new ArrayList<Sponsor>();
		 for (Object[] row : list) {		
		    Sponsor sponsor = cloneSponsor (sponsorWhat);
		    Iterator<Entry<Integer, String>> iter = beanPath.entrySet().iterator();	
		    while (iter.hasNext()) {
		       Entry entry = iter.next();
		       populateSponsor (sponsor, row[(Integer)entry.getKey()], (String)entry.getValue());
		    }
		    resultList.add(sponsor);
		 }
		 return resultList;		
	 }	
    
	 private List<Sponsor> convertPartialLoadWithParentSponsorWithOneElementInRow(List<Object> list, Map<Integer, String> beanPath, Sponsor sponsorWhat) throws IllegalAccessException, InvocationTargetException, InstantiationException, NoSuchMethodException {
		 List<Sponsor> resultList = new ArrayList<Sponsor>();
		 for (Object row : list) {		
		    Sponsor sponsor = cloneSponsor (sponsorWhat);
		    Iterator<Entry<Integer, String>> iter = beanPath.entrySet().iterator();	
		    while (iter.hasNext()) {
		       Entry entry = iter.next();
		       populateSponsor (sponsor, row, (String)entry.getValue());
		    }
		    resultList.add(sponsor);
		 }		 
		 return resultList;		
	 }
   
	public List partialLoadWithParentForBean(Object bean, Sponsor sponsorWhat, Sponsor positiveSponsor, Sponsor negativeSponsor) {
		Map beanPath = new Hashtable();
		Query hquery = getPartialLoadWithParentSponsorJPAQuery (sponsorWhat, positiveSponsor, negativeSponsor, beanPath, new QuerySelectInit(), null);
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
	public String getPartialLoadWithParentSponsorHsqlQuery (Sponsor sponsor, Sponsor positiveSponsor, Sponsor negativeSponsor, Map beanPath, QueryWhatInit queryWhatInit) {
		Hashtable aliasWhatHt = new Hashtable();
		String what = getPartialLoadWithParentSponsorQuery (sponsor, null, aliasWhatHt, null, null, beanPath, "", queryWhatInit);
		Hashtable aliasWhereHt = new Hashtable();
		String where = getPartialLoadWithParentWhereSponsorQuery (positiveSponsor, null, aliasWhatHt, aliasWhereHt, null, null);
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
	public List<Sponsor> partialLoadSponsor(Sponsor sponsor, Sponsor positiveSponsor, Sponsor negativeSponsor) {
	    Query hquery = getEntityManager().createQuery(getPartialLoadSponsorQuery (sponsor, positiveSponsor, negativeSponsor));
		int countPartialField = countPartialField(sponsor);
		if (countPartialField==0) 
		   return new ArrayList<Sponsor>();
		List list = hquery.getResultList();
		Iterator iter = list.iterator();
		List<Sponsor> returnList = new ArrayList<Sponsor>();
		while(iter.hasNext()) {
	       int index = 0;
	       Object[] row;
	       if (countPartialField==1) {
	    	  row = new Object[1];
	    	  row[0] = iter.next();
	       } else 
		      row = (Object[]) iter.next();
		   Sponsor sponsorResult = new Sponsor();
           if (sponsor.getId() != null) {
			  sponsorResult.setId((Long) row[index]);
			  index++;
           }
           if (sponsor.getName() != null) {
			  sponsorResult.setName((String) row[index]);
			  index++;
           }
           if (sponsor.getPrivilegeType() != null) {
			  sponsorResult.setPrivilegeType((String) row[index]);
			  index++;
           }
           if (sponsor.getStatus() != null) {
			  sponsorResult.setStatus((String) row[index]);
			  index++;
           }
           if (sponsor.getConferenceId() != null) {
			  sponsorResult.setConferenceId_((Long) row[index]);
			  index++;
           }
           if (sponsor.getAddressId() != null) {
			  sponsorResult.setAddressId_((Long) row[index]);
			  index++;
           }
           returnList.add(sponsorResult);
        }
	    return returnList;
	}

	public static String getPartialLoadWithParentWhereSponsorQuery (
	   Sponsor sponsor, Boolean isWhereSet, Hashtable aliasHt, Hashtable aliasWhereHt, String childAlias, String childFKAlias) {
	   if (sponsor==null)
	      return "";
	   String alias = null;
	   if (aliasWhereHt == null) {
	      aliasWhereHt = new Hashtable();
	   } 
	   if (isLookedUp(sponsor)){
	      alias = getNextAlias (aliasWhereHt, sponsor);
		  aliasWhereHt.put (getAliasKey(alias), getAliasConnection(alias, childAlias, childFKAlias));
	   }
	   if (isWhereSet == null)
          isWhereSet = false;
       StringBuffer query = new StringBuffer();
       if (sponsor.getId() != null) {
           query.append (getQueryBLANK_AND (isWhereSet));
		   isWhereSet = true;
           query.append(" "+alias+".id = "+ sponsor.getId() + " ");
       }
       if (sponsor.getName() != null) {
           query.append (getQueryBLANK_AND (isWhereSet));
		   isWhereSet = true;
           query.append(" "+alias+".name = '"+ sponsor.getName()+"' ");
       }
       if (sponsor.getPrivilegeType() != null) {
           query.append (getQueryBLANK_AND (isWhereSet));
		   isWhereSet = true;
           query.append(" "+alias+".privilegeType = '"+ sponsor.getPrivilegeType()+"' ");
       }
       if (sponsor.getStatus() != null) {
           query.append (getQueryBLANK_AND (isWhereSet));
		   isWhereSet = true;
           query.append(" "+alias+".status = '"+ sponsor.getStatus()+"' ");
       }
       if (sponsor.getConferenceId() != null) {
           query.append (getQueryBLANK_AND (isWhereSet));
		   isWhereSet = true;
           query.append(" "+alias+".conferenceId = "+ sponsor.getConferenceId() + " ");
       }
       if (sponsor.getAddressId() != null) {
           query.append (getQueryBLANK_AND (isWhereSet));
		   isWhereSet = true;
           query.append(" "+alias+".addressId = "+ sponsor.getAddressId() + " ");
       }
       if (sponsor.getAddressId()!=null) {
	      String chunck = net.sf.mp.demo.conference.dao.impl.jpa.conference.AddressExtendedJPAImpl.getPartialLoadWithParentWhereAddressQuery(
		      sponsor.getAddressId(), 
			  isWhereSet, aliasHt, aliasWhereHt, alias, "addressId");
		  if (chunck!=null && !chunck.equals("")) {
		     query.append(chunck);
		     isWhereSet=true;
		  }  	  
	   }
       if (sponsor.getConferenceId()!=null) {
	      String chunck = net.sf.mp.demo.conference.dao.impl.jpa.conference.ConferenceExtendedJPAImpl.getPartialLoadWithParentWhereConferenceQuery(
		      sponsor.getConferenceId(), 
			  isWhereSet, aliasHt, aliasWhereHt, alias, "conferenceId");
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
	
    public static String getPartialLoadWithParentSponsorQuery (
	   Sponsor sponsor, Boolean isWhereSet, Hashtable aliasHt, String childAlias, String childFKAlias, Map beanPath, String rootPath, QueryWhatInit queryWhatInit) {
	   if (sponsor==null)
	      return "";
	   String alias = null;
	   if (aliasHt == null) {
	      aliasHt = new Hashtable();
	   } 
	   if (isLookedUp(sponsor)){
	      alias = getNextAlias (aliasHt, sponsor);
		  aliasHt.put (getAliasKey(alias), getAliasConnection(alias, childAlias, childFKAlias));
	   }
	   if (isWhereSet == null)
          isWhereSet = false;
       StringBuffer query = new StringBuffer();
       if (sponsor.getId() != null) {
          query.append (queryWhatInit.getWhatInit (isWhereSet));
          isWhereSet = true; 
          beanPath.put(beanPath.size(), rootPath+"id");
          query.append(" "+alias+".id ");
       }
       if (sponsor.getName() != null) {
          query.append (queryWhatInit.getWhatInit (isWhereSet));
          isWhereSet = true; 
          beanPath.put(beanPath.size(), rootPath+"name");
          query.append(" "+alias+".name ");
       }
       if (sponsor.getPrivilegeType() != null) {
          query.append (queryWhatInit.getWhatInit (isWhereSet));
          isWhereSet = true; 
          beanPath.put(beanPath.size(), rootPath+"privilegeType");
          query.append(" "+alias+".privilegeType ");
       }
       if (sponsor.getStatus() != null) {
          query.append (queryWhatInit.getWhatInit (isWhereSet));
          isWhereSet = true; 
          beanPath.put(beanPath.size(), rootPath+"status");
          query.append(" "+alias+".status ");
       }
       if (sponsor.getConferenceId() != null) {
          query.append (queryWhatInit.getWhatInit (isWhereSet));
          isWhereSet = true; 
          beanPath.put(beanPath.size(), rootPath+"conferenceId");
          query.append(" "+alias+".conferenceId ");
       }
       if (sponsor.getAddressId() != null) {
          query.append (queryWhatInit.getWhatInit (isWhereSet));
          isWhereSet = true; 
          beanPath.put(beanPath.size(), rootPath+"addressId");
          query.append(" "+alias+".addressId ");
       }
       if (sponsor.getAddressId()!=null) {
	      String chunck = net.sf.mp.demo.conference.dao.impl.jpa.conference.AddressExtendedJPAImpl.getPartialLoadWithParentAddressQuery(
		      sponsor.getAddressId(), 
			  isWhereSet, aliasHt, alias, "addressId", beanPath, rootPath+"addressId.", queryWhatInit);
		  if (chunck!=null && !chunck.equals("")) {
		     query.append(chunck);
		     isWhereSet=true;
		  } 
	   }  
       if (sponsor.getConferenceId()!=null) {
	      String chunck = net.sf.mp.demo.conference.dao.impl.jpa.conference.ConferenceExtendedJPAImpl.getPartialLoadWithParentConferenceQuery(
		      sponsor.getConferenceId(), 
			  isWhereSet, aliasHt, alias, "conferenceId", beanPath, rootPath+"conferenceId.", queryWhatInit);
		  if (chunck!=null && !chunck.equals("")) {
		     query.append(chunck);
		     isWhereSet=true;
		  } 
	   }  
//       query.append(getSponsorSearchEqualQuery (positiveSponsor, negativeSponsor));
	   return query.toString(); 
    }
	
	protected static String getAliasConnection(String existingAlias, String childAlias, String childFKAlias) {
		if (childAlias==null)
		   return "";
		return childAlias+"."+childFKAlias+" = "+existingAlias+"."+"id";
	}
	
	protected static String getAliasKey (String alias) {
	  //TODO this is a temporary solution use a dedicated object in BslaHiberateDaoSupport
		return "Sponsor|"+alias;
	}
	
	protected static String getAliasKeyAlias (String aliasKey) {
	  //TODO this is a temporary solution use a dedicated object in BslaHiberateDaoSupport
		return StringUtils.substringAfter(aliasKey, "|");
	}
	
	protected static String getAliasKeyDomain (String aliasKey) {
	  //TODO this is a temporary solution use a dedicated object in BslaHiberateDaoSupport
	  return StringUtils.substringBefore(aliasKey, "|");
	}
	
	protected static String getNextAlias (Hashtable aliasHt, Sponsor sponsor) {
		int cptSameAlias = 0;
		Enumeration<String> keys = aliasHt.keys();
		while (keys.hasMoreElements()) {
			String key = keys.nextElement();
			if (key.startsWith("sponsor"))
				cptSameAlias++;
		}
		if (cptSameAlias==0)
			return "sponsor";
		else
			return "sponsor_"+cptSameAlias;
	}
	
	
	protected static boolean isLookedUp (Sponsor sponsor) {
	   if (sponsor==null)
		  return false;
       if (sponsor.getId() != null) {
	      return true;
       }
       if (sponsor.getName() != null) {
	      return true;
       }
       if (sponsor.getPrivilegeType() != null) {
	      return true;
       }
       if (sponsor.getStatus() != null) {
	      return true;
       }
       if (sponsor.getConferenceId() != null) {
	      return true;
       }
       if (sponsor.getAddressId() != null) {
	      return true;
       }
       if (sponsor.getAddressId()!=null) {
	      return true;
	   }  
       if (sponsor.getConferenceId()!=null) {
	      return true;
	   }  
       return false;   
	}
	
    public String getPartialLoadSponsorQuery(
	   Sponsor sponsor, 
	   Sponsor positiveSponsor, 
	   Sponsor negativeSponsor) {
       boolean isWhereSet = false;
       StringBuffer query = new StringBuffer();
       if (sponsor.getId() != null) {
          query.append (getQuerySelectComma (isWhereSet));
          isWhereSet = true; 
          query.append(" id ");
       }
       if (sponsor.getName() != null) {
          query.append (getQuerySelectComma (isWhereSet));
          isWhereSet = true; 
          query.append(" name ");
       }
       if (sponsor.getPrivilegeType() != null) {
          query.append (getQuerySelectComma (isWhereSet));
          isWhereSet = true; 
          query.append(" privilegeType ");
       }
       if (sponsor.getStatus() != null) {
          query.append (getQuerySelectComma (isWhereSet));
          isWhereSet = true; 
          query.append(" status ");
       }
       if (sponsor.getConferenceId() != null) {
          query.append (getQuerySelectComma (isWhereSet));
          isWhereSet = true; 
          query.append(" conferenceId ");
       }
       if (sponsor.getAddressId() != null) {
          query.append (getQuerySelectComma (isWhereSet));
          isWhereSet = true; 
          query.append(" addressId ");
       }
       query.append(getSponsorSearchEqualQuery (positiveSponsor, negativeSponsor));
	   return query.toString(); 
    }
	
	public List<Sponsor> searchPrototypeWithCacheSponsor(Sponsor sponsor) {
		SimpleCache simpleCache = new SimpleCache();
		List<Sponsor> list = (List<Sponsor>)simpleCache.get(sponsor.toString());
		if (list==null) {
			list = searchPrototypeSponsor(sponsor);
			simpleCache.put(sponsor.toString(), list);
		}
		return list;
	}

    public List<Sponsor> loadGraph(Sponsor graphMaskWhat, List<Sponsor> whereMask) {
        return loadGraphOneLevel(graphMaskWhat, whereMask);
    }
 
    public List<Sponsor> loadGraphOneLevel(Sponsor graphMaskWhat, List<Sponsor> whereMask) {
        //first get roots element from where list mask
        // this brings the level 0 of the graph (root level)
        graphMaskWhat.setId(graphMaskWhat.longMask__);
        List<Sponsor> sponsors = searchPrototypeSponsor (whereMask);
        // for each sub level perform the search with a subquery then reassemble
        // 1. get all sublevel queries
        // 2. perform queries on the correct dao
        // 3. reassemble
        return getLoadGraphOneLevel (graphMaskWhat, sponsors);
    }

	private List<Sponsor> copy(List<Sponsor> inputs) {
		List<Sponsor> l = new ArrayList<Sponsor>();
		for (Sponsor input : inputs) {
			Sponsor copy = new Sponsor();
			copy.copy(input);
			l.add(copy);
		}
		return l;
	}
	   
	private List<Sponsor> getLoadGraphOneLevel (Sponsor graphMaskWhat, List<Sponsor> parents) {
	    return loadGraphFromParentKey (graphMaskWhat, parents);
	} 
	
	public List<Sponsor> loadGraphFromParentKey (Sponsor graphMaskWhat, List<Sponsor> parents) {
		//foreach children:
		//check if not empty take first
		parents = copy (parents); //working with detached entities to avoid unnecessary sql calls
		if (parents==null || parents.isEmpty())
		   return parents;
		List<String> ids = getPk (parents);
		if (graphMaskWhat.getSpeakerSponsorViaSponsorId()!=null && !graphMaskWhat.getSpeakerSponsorViaSponsorId().isEmpty()) {
			for (Speaker childWhat : graphMaskWhat.getSpeakerSponsorViaSponsorId()) {
				childWhat.setSponsorId_(graphMaskWhat.longMask__); // add to the what mask, usefull for reconciliation
				SpeakerExtendedJPAImpl speakerextendedjpaimpl = new SpeakerExtendedJPAImpl ();
				List<Speaker> children = speakerextendedjpaimpl.lookupSpeaker(childWhat, getFkCriteria(" id ", ids), null, getEntityManager());
				reassembleSpeaker (children, parents);				
				break;
			}
		}
		return parents;
	}
	
	private void reassembleSpeaker (List<Speaker> children, List<Sponsor> parents) {
		for (Speaker child : children) {
			for (Sponsor parent : parents) {
				if (parent.getId()!=null && parent.getId().toString().equals(child.getSponsorId()+"")) {
					parent.addSpeakerSponsorViaSponsorId(child); //SpeakerSponsorViaSponsorId
					child.setSponsorId(parent);
					break;
				}
			}
		}
	}
	
	private List<String> getPk(List<Sponsor> input) {
		List<String> s = new ArrayList<String>();
		for (Sponsor t : input) {
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
	
    public void setSpeakerExtendedJPAImpl (SpeakerExtendedJPAImpl speakerextendedjpaimpl) {
       this.speakerextendedjpaimpl = speakerextendedjpaimpl;
    }
    
    public SpeakerExtendedJPAImpl getSpeakerExtendedJPAImpl () {
       return speakerextendedjpaimpl;
    }
    
    
}
