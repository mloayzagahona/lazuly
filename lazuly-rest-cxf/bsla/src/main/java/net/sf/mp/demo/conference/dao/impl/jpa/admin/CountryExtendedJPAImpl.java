package net.sf.mp.demo.conference.dao.impl.jpa.admin;

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
import net.sf.mp.demo.conference.dao.face.admin.CountryExtDao;
import net.sf.mp.demo.conference.domain.admin.Country;
import net.sf.mp.demo.conference.dao.impl.jpa.admin.CountryJPAImpl;

import net.sf.mp.demo.conference.domain.conference.Address;
import net.sf.mp.demo.conference.dao.impl.jpa.conference.AddressExtendedJPAImpl;
/**
 *
 * <p>Title: CountryExtendedJPAImpl</p>
 *
 * <p>Description: Interface of a Data access object dealing with CountryExtendedJPAImpl
 * persistence. It offers a set of methods which allow for saving,
 * deleting and searching CountryExtendedJPAImpl objects</p>
 *
 */

public class CountryExtendedJPAImpl extends CountryJPAImpl implements CountryExtDao {

    private Logger log = Logger.getLogger(this.getClass());
    
    private SimpleCache simpleCache = new SimpleCache();
    private AddressExtendedJPAImpl addressextendedjpaimpl;
    private EntityManager emForRecursiveDao; // dao that needs other dao in a recursive manner not support by spring configuration

    public CountryExtendedJPAImpl () {}

    /**
     * generic to move after in superclass
     */
    public CountryExtendedJPAImpl (EntityManager emForRecursiveDao) {
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
     * Inserts a Country entity with cascade of its children
     * @param Country country
     */
    public void insertCountryWithCascade(Country country) {
    	CountryExtendedJPAImpl countryextendedjpaimpl = new CountryExtendedJPAImpl(getEntityManager());
    	countryextendedjpaimpl.insertCountryWithCascade(countryextendedjpaimpl.getEntityManagerForRecursiveDao(), country);
    }
     
    public void insertCountryWithCascade(EntityManager emForRecursiveDao, Country country) {
       insertCountry(emForRecursiveDao, country);
       if (!country.getAddressCountryViaCountryId().isEmpty()) {
          AddressExtendedJPAImpl addressextendedjpaimpl = new AddressExtendedJPAImpl (emForRecursiveDao);
          for (Address _addressCountryViaCountryId : country.getAddressCountryViaCountryId()) {
             addressextendedjpaimpl.insertAddressWithCascade(emForRecursiveDao, _addressCountryViaCountryId);
          }
       } 
    }
        
    /**
     * Inserts a list of Country entity with cascade of its children
     * @param List<Country> countrys
     */
    public void insertCountrysWithCascade(List<Country> countrys) {
       for (Country country : countrys) {
          insertCountryWithCascade(country);
       }
    } 
        
    /**
     * lookup Country entity Country, criteria and max result number
     */
    public List<Country> lookupCountry(Country country, Criteria criteria, Integer numberOfResult, EntityManager em) {
		boolean isWhereSet = false;
        StringBuffer query = new StringBuffer();
        query.append ("SELECT country FROM Country country ");
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
    
    public List<Country> lookupCountry(Country country, Criteria criteria, Integer numberOfResult) {
		return lookupCountry(country, criteria, numberOfResult, getEntityManager());
    }

    public Integer updateNotNullOnlyCountry (Country country, Criteria criteria) {
        String queryWhat = getUpdateNotNullOnlyCountryQueryChunkPrototype (country);
        StringBuffer query = new StringBuffer (queryWhat);
        boolean isWhereSet = false;
        for (Criterion criterion : criteria.getClauseCriterions()) {
            query.append (getQueryWHERE_AND (isWhereSet));
            isWhereSet = true;   
            query.append(criterion.getExpression());			
        }  
        Query jpaQuery = getEntityManager().createQuery(query.toString());
        isWhereSet = false;
        if (country.getId() != null) {
           jpaQuery.setParameter ("id", country.getId());
        }   
        if (country.getName() != null) {
           jpaQuery.setParameter ("name", country.getName());
        }   
        if (country.getIsoName() != null) {
           jpaQuery.setParameter ("isoName", country.getIsoName());
        }   
		return jpaQuery.executeUpdate();           
    }
	
	public Country affectCountry (Country country) {
	    return referCountry (country, false);		    
	}
		
	/**
	 * Assign the first country retrieved corresponding to the country criteria.
	 * Blank criteria are mapped to null.
	 * If no criteria is found, null is returned.
	 * If there is no country corresponding in the database. Then country is inserted and returned with its primary key(s). 
	 */
	public Country assignCountry (Country country) {
	    return referCountry (country, true);	
	}
	
	public Country referCountry (Country country, boolean isAssign) {
		country = assignBlankToNull (country);
		if (isAllNull(country))
			return null;
		else {
			List<Country> list = searchPrototypeCountry(country);
			if (list.isEmpty()) {
			    if (isAssign)
			       insertCountry(country);
			    else
				   return null;
			}
			else if (list.size()==1)
				country.copy(list.get(0));
			else 
				//TODO log error
				country.copy(list.get(0));
		}
		return country;		    
	}

   public Country assignCountryUseCache (Country country) {
      return referCountryUseCache (country, true);
   }
      		
   public Country affectCountryUseCache (Country country) {
      return referCountryUseCache (country, false);
   }
      		
   public Country referCountryUseCache (Country country, boolean isAssign) {
	  String key = getCacheKey(null, country, null, "assignCountry");
      Country countryCache = (Country)simpleCache.get(key);
      if (countryCache==null) {
         countryCache = referCountry (country, isAssign);
         if (key!=null)
         	simpleCache.put(key, countryCache);
      }
      country.copy(countryCache);
      return countryCache;
   }	

	private String getCacheKey (Country countryWhat, Country positiveCountry, Country negativeCountry, String queryKey) {
	    StringBuffer sb = new StringBuffer();
	    sb.append(queryKey);
	    if (countryWhat!=null)
	       sb.append(countryWhat.toStringWithParents());
	    if (positiveCountry!=null)
	       sb.append(positiveCountry.toStringWithParents());
	    if (negativeCountry!=null)
	       sb.append(negativeCountry.toStringWithParents());
	    return sb.toString();
	}
	
    public Country partialLoadWithParentFirstCountry(Country countryWhat, Country positiveCountry, Country negativeCountry){
		List <Country> list = partialLoadWithParentCountry(countryWhat, positiveCountry, negativeCountry);
		return (!list.isEmpty())?(Country)list.get(0):null;
    }
    
    public Country partialLoadWithParentFirstCountryUseCache(Country countryWhat, Country positiveCountry, Country negativeCountry, Boolean useCache){
		List <Country> list = partialLoadWithParentCountryUseCache(countryWhat, positiveCountry, negativeCountry, useCache);
		return (!list.isEmpty())?(Country)list.get(0):null;
    }
	
	public Country partialLoadWithParentFirstCountryUseCacheOnResult(Country countryWhat, Country positiveCountry, Country negativeCountry, Boolean useCache){
		List <Country> list = partialLoadWithParentCountryUseCacheOnResult(countryWhat, positiveCountry, negativeCountry, useCache);
		return (!list.isEmpty())?(Country)list.get(0):null;
    }
	//
	protected List<Country> partialLoadWithParentCountry(Country countryWhat, Country positiveCountry, Country negativeCountry, Integer nbOfResult, Boolean useCache) {
		 return partialLoadWithParentCountry(countryWhat, positiveCountry, negativeCountry, new QuerySelectInit(), nbOfResult, useCache);
	}	  

	protected List partialLoadWithParentCountryQueryResult (Country countryWhat, Country positiveCountry, Country negativeCountry, Integer nbOfResult, Boolean useCache) {
		 return partialLoadWithParentCountryQueryResult (countryWhat, positiveCountry, negativeCountry, new QuerySelectInit(), nbOfResult, useCache);
	}	
    
    public List<Country> getDistinctCountry(Country countryWhat, Country positiveCountry, Country negativeCountry) {
		 return partialLoadWithParentCountry(countryWhat, positiveCountry, negativeCountry, new QuerySelectDistinctInit(), null, false);
	}
	
	public List<Country> partialLoadWithParentCountry(Country countryWhat, Country positiveCountry, Country negativeCountry) {
		 return partialLoadWithParentCountry(countryWhat, positiveCountry, negativeCountry, new QuerySelectInit(), null, false);
	}	
  
	public List<Country> partialLoadWithParentCountryUseCacheOnResult(Country countryWhat, Country positiveCountry, Country negativeCountry, Boolean useCache) {
		String key = getCacheKey(countryWhat, positiveCountry, negativeCountry, "partialLoadWithParentCountry");
		List<Country> list = (List<Country>)simpleCache.get(key);
		if (list==null || list.isEmpty()) {
			list = partialLoadWithParentCountry(countryWhat, positiveCountry, negativeCountry);
			if (!list.isEmpty())
			   simpleCache.put(key, list);
		}
		return list;	
	}	

	public List<Country> partialLoadWithParentCountryUseCache(Country countryWhat, Country positiveCountry, Country negativeCountry, Boolean useCache) {
		String key = getCacheKey(countryWhat, positiveCountry, negativeCountry, "partialLoadWithParentCountry");
		List<Country> list = (List<Country>)simpleCache.get(key);
		if (list==null) {
			list = partialLoadWithParentCountry(countryWhat, positiveCountry, negativeCountry);
			simpleCache.put(key, list);
		}
		return list;	
	}	
	
	private List<Country> handleLoadWithParentCountry(Map beanPath, List list, Country countryWhat) {
	    return handleLoadWithParentCountry(beanPath, list, countryWhat, true);  
	}
	
	private List<Country> handleLoadWithParentCountry(Map beanPath, List list, Country countryWhat, boolean isHql) {
		if (beanPath.size()==1) {
			return handlePartialLoadWithParentCountryWithOneElementInRow(list, beanPath, countryWhat, isHql);
		}
		return handlePartialLoadWithParentCountry(list, beanPath, countryWhat, isHql);	
	}
	
    	// to set in super class	
	protected void populateCountry (Country country, Object value, String beanPath) throws IllegalAccessException, InvocationTargetException, NoSuchMethodException {
	   BeanUtils.populateBeanObject(country, beanPath, value);
	}
	
	protected void populateCountryFromSQL (Country country, Object value, String beanPath) throws IllegalAccessException, InvocationTargetException, NoSuchMethodException {
	   BeanUtils.populateBeanObject(country, beanPath, value);
	}
    	// to set in super class BEWARE: genericity is only one level!!!!! first level is a copy second level is a reference!!! change to country.clone() instead
	private Country cloneCountry (Country country) throws IllegalAccessException, InvocationTargetException, InstantiationException, NoSuchMethodException {
		//return (Country) BeanUtils.cloneBeanObject(country);
	   if (country==null) return new Country();
	   return country.clone();
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
	
    public List<Country> countDistinct (Country whatMask, Country whereEqCriteria) {
       return partialLoadWithParentCountry(whatMask, whereEqCriteria, null, new QuerySelectCountInit("country"), null, false);
    }   
    	
    public Long count(Country whereEqCriteria) {
        List<Country> list = partialLoadWithParentCountry(null, whereEqCriteria, null, new QueryCountInit("country"), null, false);    
    	if (!list.isEmpty())
    		return list.get(0).getCount__();
    	return 0L;
    }
		
		  		
   public Country getFirstCountryWhereConditionsAre (Country country) {
      List<Country> list = partialLoadWithParentCountry(getDefaultCountryWhat(), country, null, 1, false);
      if (list.isEmpty()) {
         return null;
      }
      else if (list.size()==1)
         return list.get(0);
      else 
      //TODO log error
         return list.get(0);	
	}

   private List getFirstResultWhereConditionsAre (Country country) {
      return  partialLoadWithParentCountryQueryResult(getDefaultCountryWhat(), country, null, 1, false);	
   }
   
   protected Country getDefaultCountryWhat() {
      Country country = new Country();
      country.setId(Integer.valueOf("-1"));
      return country;
   }
   
	public Country getFirstCountry (Country country) {
		if (isAllNull(country))
			return null;
		else {
			List<Country> list = searchPrototype (country, 1);
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
    * checks if the Country entity exists
    */           
    public boolean existsCountry (Country country) {
       if (getFirstCountry(country)!=null)
          return true;
       return false;  
    }
        
    public boolean existsCountryWhereConditionsAre (Country country) {
       if (getFirstResultWhereConditionsAre (country).isEmpty())
          return false;
       return true;  
    }


	
	private int countPartialField (Country country) {
	   int cpt = 0;
       if (country.getId() != null) {
          cpt++;
       }
       if (country.getName() != null) {
          cpt++;
       }
       if (country.getIsoName() != null) {
          cpt++;
       }
       return cpt;
	}   
  	
	public List<Country> partialLoadWithParentCountry(Country countryWhat, Country positiveCountry, Country negativeCountry, QueryWhatInit queryWhatInit, Integer nbOfResult, Boolean useCache) {
		Map beanPath = new Hashtable();
		List list = partialLoadWithParentCountryJPAQueryResult (countryWhat, positiveCountry, negativeCountry, queryWhatInit, beanPath, nbOfResult, useCache);
		if (beanPath.size()==1) {
			return handlePartialLoadWithParentCountryWithOneElementInRow(list, beanPath, countryWhat, true);
		}
		return handlePartialLoadWithParentCountry(list, beanPath, countryWhat, true);
	}	

	private List partialLoadWithParentCountryQueryResult(Country countryWhat, Country positiveCountry, Country negativeCountry, QueryWhatInit queryWhatInit, Integer nbOfResult, Boolean useCache) {
		return partialLoadWithParentCountryJPAQueryResult (countryWhat, positiveCountry, negativeCountry, queryWhatInit, new Hashtable(), nbOfResult, useCache);
  }	
  
	private List partialLoadWithParentCountryJPAQueryResult(Country countryWhat, Country positiveCountry, Country negativeCountry, QueryWhatInit queryWhatInit, Map beanPath, Integer nbOfResult, Boolean useCache) {
		Query hquery = getPartialLoadWithParentCountryJPAQuery (countryWhat, positiveCountry, negativeCountry, beanPath, queryWhatInit, nbOfResult);
		return hquery.getResultList();
  }	
   /**
    * @returns an JPA Hsql query based on entity Country and its parents and the maximum number of result
    */
	protected Query getPartialLoadWithParentCountryJPAQuery (Country countryWhat, Country positiveCountry, Country negativeCountry, Map beanPath, QueryWhatInit queryWhatInit, Integer nbOfResult) {
	   Query query = getEntityManager().createQuery(getPartialLoadWithParentCountryHsqlQuery (countryWhat, positiveCountry, negativeCountry, beanPath, queryWhatInit));
	   if (nbOfResult!=null)
	      query.setMaxResults(nbOfResult);
	   return query;
  }
  
	private List<Country> handlePartialLoadWithParentCountry(List<Object[]> list, Map<Integer, String> beanPath, Country countryWhat, boolean isJql) {
		try {
			return convertPartialLoadWithParentCountry(list, beanPath, countryWhat);
		} catch (Exception ex) {
			log.error("Error conversion list from handlePartialLoadWithParentCountry, message:"+ex.getMessage());
			return new ArrayList<Country>();
		}
    }

	private List<Country> handlePartialLoadWithParentCountryWithOneElementInRow(List<Object> list, Map<Integer, String> beanPath, Country countryWhat, boolean isJql) {
		try {
			return convertPartialLoadWithParentCountryWithOneElementInRow(list, beanPath, countryWhat);
		} catch (Exception ex) {
			log.error("Error conversion list from handlePartialLoadWithParentCountryWithOneElementInRow, message:"+ex.getMessage());
			return new ArrayList<Country>();
		}
    }
    	
	 private List<Country> convertPartialLoadWithParentCountry(List<Object[]> list, Map<Integer, String> beanPath, Country countryWhat) throws IllegalAccessException, InvocationTargetException, InstantiationException, NoSuchMethodException {
		 List<Country> resultList = new ArrayList<Country>();
		 for (Object[] row : list) {		
		    Country country = cloneCountry (countryWhat);
		    Iterator<Entry<Integer, String>> iter = beanPath.entrySet().iterator();	
		    while (iter.hasNext()) {
		       Entry entry = iter.next();
		       populateCountry (country, row[(Integer)entry.getKey()], (String)entry.getValue());
		    }
		    resultList.add(country);
		 }
		 return resultList;		
	 }	
    
	 private List<Country> convertPartialLoadWithParentCountryWithOneElementInRow(List<Object> list, Map<Integer, String> beanPath, Country countryWhat) throws IllegalAccessException, InvocationTargetException, InstantiationException, NoSuchMethodException {
		 List<Country> resultList = new ArrayList<Country>();
		 for (Object row : list) {		
		    Country country = cloneCountry (countryWhat);
		    Iterator<Entry<Integer, String>> iter = beanPath.entrySet().iterator();	
		    while (iter.hasNext()) {
		       Entry entry = iter.next();
		       populateCountry (country, row, (String)entry.getValue());
		    }
		    resultList.add(country);
		 }		 
		 return resultList;		
	 }
   
	public List partialLoadWithParentForBean(Object bean, Country countryWhat, Country positiveCountry, Country negativeCountry) {
		Map beanPath = new Hashtable();
		Query hquery = getPartialLoadWithParentCountryJPAQuery (countryWhat, positiveCountry, negativeCountry, beanPath, new QuerySelectInit(), null);
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
	public String getPartialLoadWithParentCountryHsqlQuery (Country country, Country positiveCountry, Country negativeCountry, Map beanPath, QueryWhatInit queryWhatInit) {
		Hashtable aliasWhatHt = new Hashtable();
		String what = getPartialLoadWithParentCountryQuery (country, null, aliasWhatHt, null, null, beanPath, "", queryWhatInit);
		Hashtable aliasWhereHt = new Hashtable();
		String where = getPartialLoadWithParentWhereCountryQuery (positiveCountry, null, aliasWhatHt, aliasWhereHt, null, null);
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
	public List<Country> partialLoadCountry(Country country, Country positiveCountry, Country negativeCountry) {
	    Query hquery = getEntityManager().createQuery(getPartialLoadCountryQuery (country, positiveCountry, negativeCountry));
		int countPartialField = countPartialField(country);
		if (countPartialField==0) 
		   return new ArrayList<Country>();
		List list = hquery.getResultList();
		Iterator iter = list.iterator();
		List<Country> returnList = new ArrayList<Country>();
		while(iter.hasNext()) {
	       int index = 0;
	       Object[] row;
	       if (countPartialField==1) {
	    	  row = new Object[1];
	    	  row[0] = iter.next();
	       } else 
		      row = (Object[]) iter.next();
		   Country countryResult = new Country();
           if (country.getId() != null) {
			  countryResult.setId((Integer) row[index]);
			  index++;
           }
           if (country.getName() != null) {
			  countryResult.setName((String) row[index]);
			  index++;
           }
           if (country.getIsoName() != null) {
			  countryResult.setIsoName((String) row[index]);
			  index++;
           }
           returnList.add(countryResult);
        }
	    return returnList;
	}

	public static String getPartialLoadWithParentWhereCountryQuery (
	   Country country, Boolean isWhereSet, Hashtable aliasHt, Hashtable aliasWhereHt, String childAlias, String childFKAlias) {
	   if (country==null)
	      return "";
	   String alias = null;
	   if (aliasWhereHt == null) {
	      aliasWhereHt = new Hashtable();
	   } 
	   if (isLookedUp(country)){
	      alias = getNextAlias (aliasWhereHt, country);
		  aliasWhereHt.put (getAliasKey(alias), getAliasConnection(alias, childAlias, childFKAlias));
	   }
	   if (isWhereSet == null)
          isWhereSet = false;
       StringBuffer query = new StringBuffer();
       if (country.getId() != null) {
           query.append (getQueryBLANK_AND (isWhereSet));
		   isWhereSet = true;
           query.append(" "+alias+".id = "+ country.getId() + " ");
       }
       if (country.getName() != null) {
           query.append (getQueryBLANK_AND (isWhereSet));
		   isWhereSet = true;
           query.append(" "+alias+".name = '"+ country.getName()+"' ");
       }
       if (country.getIsoName() != null) {
           query.append (getQueryBLANK_AND (isWhereSet));
		   isWhereSet = true;
           query.append(" "+alias+".isoName = '"+ country.getIsoName()+"' ");
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
	
    public static String getPartialLoadWithParentCountryQuery (
	   Country country, Boolean isWhereSet, Hashtable aliasHt, String childAlias, String childFKAlias, Map beanPath, String rootPath, QueryWhatInit queryWhatInit) {
	   if (country==null)
	      return "";
	   String alias = null;
	   if (aliasHt == null) {
	      aliasHt = new Hashtable();
	   } 
	   if (isLookedUp(country)){
	      alias = getNextAlias (aliasHt, country);
		  aliasHt.put (getAliasKey(alias), getAliasConnection(alias, childAlias, childFKAlias));
	   }
	   if (isWhereSet == null)
          isWhereSet = false;
       StringBuffer query = new StringBuffer();
       if (country.getId() != null) {
          query.append (queryWhatInit.getWhatInit (isWhereSet));
          isWhereSet = true; 
          beanPath.put(beanPath.size(), rootPath+"id");
          query.append(" "+alias+".id ");
       }
       if (country.getName() != null) {
          query.append (queryWhatInit.getWhatInit (isWhereSet));
          isWhereSet = true; 
          beanPath.put(beanPath.size(), rootPath+"name");
          query.append(" "+alias+".name ");
       }
       if (country.getIsoName() != null) {
          query.append (queryWhatInit.getWhatInit (isWhereSet));
          isWhereSet = true; 
          beanPath.put(beanPath.size(), rootPath+"isoName");
          query.append(" "+alias+".isoName ");
       }
//       query.append(getCountrySearchEqualQuery (positiveCountry, negativeCountry));
	   return query.toString(); 
    }
	
	protected static String getAliasConnection(String existingAlias, String childAlias, String childFKAlias) {
		if (childAlias==null)
		   return "";
		return childAlias+"."+childFKAlias+" = "+existingAlias+"."+"id";
	}
	
	protected static String getAliasKey (String alias) {
	  //TODO this is a temporary solution use a dedicated object in BslaHiberateDaoSupport
		return "Country|"+alias;
	}
	
	protected static String getAliasKeyAlias (String aliasKey) {
	  //TODO this is a temporary solution use a dedicated object in BslaHiberateDaoSupport
		return StringUtils.substringAfter(aliasKey, "|");
	}
	
	protected static String getAliasKeyDomain (String aliasKey) {
	  //TODO this is a temporary solution use a dedicated object in BslaHiberateDaoSupport
	  return StringUtils.substringBefore(aliasKey, "|");
	}
	
	protected static String getNextAlias (Hashtable aliasHt, Country country) {
		int cptSameAlias = 0;
		Enumeration<String> keys = aliasHt.keys();
		while (keys.hasMoreElements()) {
			String key = keys.nextElement();
			if (key.startsWith("country"))
				cptSameAlias++;
		}
		if (cptSameAlias==0)
			return "country";
		else
			return "country_"+cptSameAlias;
	}
	
	
	protected static boolean isLookedUp (Country country) {
	   if (country==null)
		  return false;
       if (country.getId() != null) {
	      return true;
       }
       if (country.getName() != null) {
	      return true;
       }
       if (country.getIsoName() != null) {
	      return true;
       }
       return false;   
	}
	
    public String getPartialLoadCountryQuery(
	   Country country, 
	   Country positiveCountry, 
	   Country negativeCountry) {
       boolean isWhereSet = false;
       StringBuffer query = new StringBuffer();
       if (country.getId() != null) {
          query.append (getQuerySelectComma (isWhereSet));
          isWhereSet = true; 
          query.append(" id ");
       }
       if (country.getName() != null) {
          query.append (getQuerySelectComma (isWhereSet));
          isWhereSet = true; 
          query.append(" name ");
       }
       if (country.getIsoName() != null) {
          query.append (getQuerySelectComma (isWhereSet));
          isWhereSet = true; 
          query.append(" isoName ");
       }
       query.append(getCountrySearchEqualQuery (positiveCountry, negativeCountry));
	   return query.toString(); 
    }
	
	public List<Country> searchPrototypeWithCacheCountry(Country country) {
		SimpleCache simpleCache = new SimpleCache();
		List<Country> list = (List<Country>)simpleCache.get(country.toString());
		if (list==null) {
			list = searchPrototypeCountry(country);
			simpleCache.put(country.toString(), list);
		}
		return list;
	}

    public List<Country> loadGraph(Country graphMaskWhat, List<Country> whereMask) {
        return loadGraphOneLevel(graphMaskWhat, whereMask);
    }
 
    public List<Country> loadGraphOneLevel(Country graphMaskWhat, List<Country> whereMask) {
        //first get roots element from where list mask
        // this brings the level 0 of the graph (root level)
        graphMaskWhat.setId(graphMaskWhat.integerMask__);
        List<Country> countrys = searchPrototypeCountry (whereMask);
        // for each sub level perform the search with a subquery then reassemble
        // 1. get all sublevel queries
        // 2. perform queries on the correct dao
        // 3. reassemble
        return getLoadGraphOneLevel (graphMaskWhat, countrys);
    }

	private List<Country> copy(List<Country> inputs) {
		List<Country> l = new ArrayList<Country>();
		for (Country input : inputs) {
			Country copy = new Country();
			copy.copy(input);
			l.add(copy);
		}
		return l;
	}
	   
	private List<Country> getLoadGraphOneLevel (Country graphMaskWhat, List<Country> parents) {
	    return loadGraphFromParentKey (graphMaskWhat, parents);
	} 
	
	public List<Country> loadGraphFromParentKey (Country graphMaskWhat, List<Country> parents) {
		//foreach children:
		//check if not empty take first
		parents = copy (parents); //working with detached entities to avoid unnecessary sql calls
		if (parents==null || parents.isEmpty())
		   return parents;
		List<String> ids = getPk (parents);
		if (graphMaskWhat.getAddressCountryViaCountryId()!=null && !graphMaskWhat.getAddressCountryViaCountryId().isEmpty()) {
			for (Address childWhat : graphMaskWhat.getAddressCountryViaCountryId()) {
				childWhat.setCountryId_(graphMaskWhat.integerMask__); // add to the what mask, usefull for reconciliation
				AddressExtendedJPAImpl addressextendedjpaimpl = new AddressExtendedJPAImpl ();
				List<Address> children = addressextendedjpaimpl.lookupAddress(childWhat, getFkCriteria(" id ", ids), null, getEntityManager());
				reassembleAddress (children, parents);				
				break;
			}
		}
		return parents;
	}
	
	private void reassembleAddress (List<Address> children, List<Country> parents) {
		for (Address child : children) {
			for (Country parent : parents) {
				if (parent.getId()!=null && parent.getId().toString().equals(child.getCountryId()+"")) {
					parent.addAddressCountryViaCountryId(child); //AddressCountryViaCountryId
					child.setCountryId(parent);
					break;
				}
			}
		}
	}
	
	private List<String> getPk(List<Country> input) {
		List<String> s = new ArrayList<String>();
		for (Country t : input) {
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
	
    public void setAddressExtendedJPAImpl (AddressExtendedJPAImpl addressextendedjpaimpl) {
       this.addressextendedjpaimpl = addressextendedjpaimpl;
    }
    
    public AddressExtendedJPAImpl getAddressExtendedJPAImpl () {
       return addressextendedjpaimpl;
    }
    
    
}
