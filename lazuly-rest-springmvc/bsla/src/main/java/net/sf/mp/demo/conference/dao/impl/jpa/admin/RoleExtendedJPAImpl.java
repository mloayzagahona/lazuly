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
import net.sf.mp.demo.conference.dao.face.admin.RoleExtDao;
import net.sf.mp.demo.conference.domain.admin.Role;
import net.sf.mp.demo.conference.dao.impl.jpa.admin.RoleJPAImpl;

/**
 *
 * <p>Title: RoleExtendedJPAImpl</p>
 *
 * <p>Description: Interface of a Data access object dealing with RoleExtendedJPAImpl
 * persistence. It offers a set of methods which allow for saving,
 * deleting and searching RoleExtendedJPAImpl objects</p>
 *
 */

public class RoleExtendedJPAImpl extends RoleJPAImpl implements RoleExtDao {

    private Logger log = Logger.getLogger(this.getClass());
    
    private SimpleCache simpleCache = new SimpleCache();
    private EntityManager emForRecursiveDao; // dao that needs other dao in a recursive manner not support by spring configuration

    public RoleExtendedJPAImpl () {}

    /**
     * generic to move after in superclass
     */
    public RoleExtendedJPAImpl (EntityManager emForRecursiveDao) {
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
     * Inserts a Role entity with cascade of its children
     * @param Role role
     */
    public void insertRoleWithCascade(Role role) {
    	RoleExtendedJPAImpl roleextendedjpaimpl = new RoleExtendedJPAImpl(getEntityManager());
    	roleextendedjpaimpl.insertRoleWithCascade(roleextendedjpaimpl.getEntityManagerForRecursiveDao(), role);
    }
     
    public void insertRoleWithCascade(EntityManager emForRecursiveDao, Role role) {
       insertRole(emForRecursiveDao, role);
    }
        
    /**
     * Inserts a list of Role entity with cascade of its children
     * @param List<Role> roles
     */
    public void insertRolesWithCascade(List<Role> roles) {
       for (Role role : roles) {
          insertRoleWithCascade(role);
       }
    } 
        
    /**
     * lookup Role entity Role, criteria and max result number
     */
    public List<Role> lookupRole(Role role, Criteria criteria, Integer numberOfResult, EntityManager em) {
		boolean isWhereSet = false;
        StringBuffer query = new StringBuffer();
        query.append ("SELECT role FROM Role role ");
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
    
    public List<Role> lookupRole(Role role, Criteria criteria, Integer numberOfResult) {
		return lookupRole(role, criteria, numberOfResult, getEntityManager());
    }

    public Integer updateNotNullOnlyRole (Role role, Criteria criteria) {
        String queryWhat = getUpdateNotNullOnlyRoleQueryChunkPrototype (role);
        StringBuffer query = new StringBuffer (queryWhat);
        boolean isWhereSet = false;
        for (Criterion criterion : criteria.getClauseCriterions()) {
            query.append (getQueryWHERE_AND (isWhereSet));
            isWhereSet = true;   
            query.append(criterion.getExpression());			
        }  
        Query jpaQuery = getEntityManager().createQuery(query.toString());
        isWhereSet = false;
        if (role.getId() != null) {
           jpaQuery.setParameter ("id", role.getId());
        }   
        if (role.getName() != null) {
           jpaQuery.setParameter ("name", role.getName());
        }   
		return jpaQuery.executeUpdate();           
    }
	
	public Role affectRole (Role role) {
	    return referRole (role, false);		    
	}
		
	/**
	 * Assign the first role retrieved corresponding to the role criteria.
	 * Blank criteria are mapped to null.
	 * If no criteria is found, null is returned.
	 * If there is no role corresponding in the database. Then role is inserted and returned with its primary key(s). 
	 */
	public Role assignRole (Role role) {
	    return referRole (role, true);	
	}
	
	public Role referRole (Role role, boolean isAssign) {
		role = assignBlankToNull (role);
		if (isAllNull(role))
			return null;
		else {
			List<Role> list = searchPrototypeRole(role);
			if (list.isEmpty()) {
			    if (isAssign)
			       insertRole(role);
			    else
				   return null;
			}
			else if (list.size()==1)
				role.copy(list.get(0));
			else 
				//TODO log error
				role.copy(list.get(0));
		}
		return role;		    
	}

   public Role assignRoleUseCache (Role role) {
      return referRoleUseCache (role, true);
   }
      		
   public Role affectRoleUseCache (Role role) {
      return referRoleUseCache (role, false);
   }
      		
   public Role referRoleUseCache (Role role, boolean isAssign) {
	  String key = getCacheKey(null, role, null, "assignRole");
      Role roleCache = (Role)simpleCache.get(key);
      if (roleCache==null) {
         roleCache = referRole (role, isAssign);
         if (key!=null)
         	simpleCache.put(key, roleCache);
      }
      role.copy(roleCache);
      return roleCache;
   }	

	private String getCacheKey (Role roleWhat, Role positiveRole, Role negativeRole, String queryKey) {
	    StringBuffer sb = new StringBuffer();
	    sb.append(queryKey);
	    if (roleWhat!=null)
	       sb.append(roleWhat.toStringWithParents());
	    if (positiveRole!=null)
	       sb.append(positiveRole.toStringWithParents());
	    if (negativeRole!=null)
	       sb.append(negativeRole.toStringWithParents());
	    return sb.toString();
	}
	
    public Role partialLoadWithParentFirstRole(Role roleWhat, Role positiveRole, Role negativeRole){
		List <Role> list = partialLoadWithParentRole(roleWhat, positiveRole, negativeRole);
		return (!list.isEmpty())?(Role)list.get(0):null;
    }
    
    public Role partialLoadWithParentFirstRoleUseCache(Role roleWhat, Role positiveRole, Role negativeRole, Boolean useCache){
		List <Role> list = partialLoadWithParentRoleUseCache(roleWhat, positiveRole, negativeRole, useCache);
		return (!list.isEmpty())?(Role)list.get(0):null;
    }
	
	public Role partialLoadWithParentFirstRoleUseCacheOnResult(Role roleWhat, Role positiveRole, Role negativeRole, Boolean useCache){
		List <Role> list = partialLoadWithParentRoleUseCacheOnResult(roleWhat, positiveRole, negativeRole, useCache);
		return (!list.isEmpty())?(Role)list.get(0):null;
    }
	//
	protected List<Role> partialLoadWithParentRole(Role roleWhat, Role positiveRole, Role negativeRole, Integer nbOfResult, Boolean useCache) {
		 return partialLoadWithParentRole(roleWhat, positiveRole, negativeRole, new QuerySelectInit(), nbOfResult, useCache);
	}	  

	protected List partialLoadWithParentRoleQueryResult (Role roleWhat, Role positiveRole, Role negativeRole, Integer nbOfResult, Boolean useCache) {
		 return partialLoadWithParentRoleQueryResult (roleWhat, positiveRole, negativeRole, new QuerySelectInit(), nbOfResult, useCache);
	}	
    
    public List<Role> getDistinctRole(Role roleWhat, Role positiveRole, Role negativeRole) {
		 return partialLoadWithParentRole(roleWhat, positiveRole, negativeRole, new QuerySelectDistinctInit(), null, false);
	}
	
	public List<Role> partialLoadWithParentRole(Role roleWhat, Role positiveRole, Role negativeRole) {
		 return partialLoadWithParentRole(roleWhat, positiveRole, negativeRole, new QuerySelectInit(), null, false);
	}	
  
	public List<Role> partialLoadWithParentRoleUseCacheOnResult(Role roleWhat, Role positiveRole, Role negativeRole, Boolean useCache) {
		String key = getCacheKey(roleWhat, positiveRole, negativeRole, "partialLoadWithParentRole");
		List<Role> list = (List<Role>)simpleCache.get(key);
		if (list==null || list.isEmpty()) {
			list = partialLoadWithParentRole(roleWhat, positiveRole, negativeRole);
			if (!list.isEmpty())
			   simpleCache.put(key, list);
		}
		return list;	
	}	

	public List<Role> partialLoadWithParentRoleUseCache(Role roleWhat, Role positiveRole, Role negativeRole, Boolean useCache) {
		String key = getCacheKey(roleWhat, positiveRole, negativeRole, "partialLoadWithParentRole");
		List<Role> list = (List<Role>)simpleCache.get(key);
		if (list==null) {
			list = partialLoadWithParentRole(roleWhat, positiveRole, negativeRole);
			simpleCache.put(key, list);
		}
		return list;	
	}	
	
	private List<Role> handleLoadWithParentRole(Map beanPath, List list, Role roleWhat) {
	    return handleLoadWithParentRole(beanPath, list, roleWhat, true);  
	}
	
	private List<Role> handleLoadWithParentRole(Map beanPath, List list, Role roleWhat, boolean isHql) {
		if (beanPath.size()==1) {
			return handlePartialLoadWithParentRoleWithOneElementInRow(list, beanPath, roleWhat, isHql);
		}
		return handlePartialLoadWithParentRole(list, beanPath, roleWhat, isHql);	
	}
	
    	// to set in super class	
	protected void populateRole (Role role, Object value, String beanPath) throws IllegalAccessException, InvocationTargetException, NoSuchMethodException {
	   BeanUtils.populateBeanObject(role, beanPath, value);
	}
	
	protected void populateRoleFromSQL (Role role, Object value, String beanPath) throws IllegalAccessException, InvocationTargetException, NoSuchMethodException {
	   BeanUtils.populateBeanObject(role, beanPath, value);
	}
    	// to set in super class BEWARE: genericity is only one level!!!!! first level is a copy second level is a reference!!! change to role.clone() instead
	private Role cloneRole (Role role) throws IllegalAccessException, InvocationTargetException, InstantiationException, NoSuchMethodException {
		//return (Role) BeanUtils.cloneBeanObject(role);
	   if (role==null) return new Role();
	   return role.clone();
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
	
    public List<Role> countDistinct (Role whatMask, Role whereEqCriteria) {
       return partialLoadWithParentRole(whatMask, whereEqCriteria, null, new QuerySelectCountInit("role"), null, false);
    }   
    	
    public Long count(Role whereEqCriteria) {
        List<Role> list = partialLoadWithParentRole(null, whereEqCriteria, null, new QueryCountInit("role"), null, false);    
    	if (!list.isEmpty())
    		return list.get(0).getCount__();
    	return 0L;
    }
		
		  		
   public Role getFirstRoleWhereConditionsAre (Role role) {
      List<Role> list = partialLoadWithParentRole(getDefaultRoleWhat(), role, null, 1, false);
      if (list.isEmpty()) {
         return null;
      }
      else if (list.size()==1)
         return list.get(0);
      else 
      //TODO log error
         return list.get(0);	
	}

   private List getFirstResultWhereConditionsAre (Role role) {
      return  partialLoadWithParentRoleQueryResult(getDefaultRoleWhat(), role, null, 1, false);	
   }
   
   protected Role getDefaultRoleWhat() {
      Role role = new Role();
      role.setId(Integer.valueOf("-1"));
      return role;
   }
   
	public Role getFirstRole (Role role) {
		if (isAllNull(role))
			return null;
		else {
			List<Role> list = searchPrototype (role, 1);
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
    * checks if the Role entity exists
    */           
    public boolean existsRole (Role role) {
       if (getFirstRole(role)!=null)
          return true;
       return false;  
    }
        
    public boolean existsRoleWhereConditionsAre (Role role) {
       if (getFirstResultWhereConditionsAre (role).isEmpty())
          return false;
       return true;  
    }


	
	private int countPartialField (Role role) {
	   int cpt = 0;
       if (role.getId() != null) {
          cpt++;
       }
       if (role.getName() != null) {
          cpt++;
       }
       return cpt;
	}   
  	
	public List<Role> partialLoadWithParentRole(Role roleWhat, Role positiveRole, Role negativeRole, QueryWhatInit queryWhatInit, Integer nbOfResult, Boolean useCache) {
		Map beanPath = new Hashtable();
		List list = partialLoadWithParentRoleJPAQueryResult (roleWhat, positiveRole, negativeRole, queryWhatInit, beanPath, nbOfResult, useCache);
		if (beanPath.size()==1) {
			return handlePartialLoadWithParentRoleWithOneElementInRow(list, beanPath, roleWhat, true);
		}
		return handlePartialLoadWithParentRole(list, beanPath, roleWhat, true);
	}	

	private List partialLoadWithParentRoleQueryResult(Role roleWhat, Role positiveRole, Role negativeRole, QueryWhatInit queryWhatInit, Integer nbOfResult, Boolean useCache) {
		return partialLoadWithParentRoleJPAQueryResult (roleWhat, positiveRole, negativeRole, queryWhatInit, new Hashtable(), nbOfResult, useCache);
  }	
  
	private List partialLoadWithParentRoleJPAQueryResult(Role roleWhat, Role positiveRole, Role negativeRole, QueryWhatInit queryWhatInit, Map beanPath, Integer nbOfResult, Boolean useCache) {
		Query hquery = getPartialLoadWithParentRoleJPAQuery (roleWhat, positiveRole, negativeRole, beanPath, queryWhatInit, nbOfResult);
		return hquery.getResultList();
  }	
   /**
    * @returns an JPA Hsql query based on entity Role and its parents and the maximum number of result
    */
	protected Query getPartialLoadWithParentRoleJPAQuery (Role roleWhat, Role positiveRole, Role negativeRole, Map beanPath, QueryWhatInit queryWhatInit, Integer nbOfResult) {
	   Query query = getEntityManager().createQuery(getPartialLoadWithParentRoleHsqlQuery (roleWhat, positiveRole, negativeRole, beanPath, queryWhatInit));
	   if (nbOfResult!=null)
	      query.setMaxResults(nbOfResult);
	   return query;
  }
  
	private List<Role> handlePartialLoadWithParentRole(List<Object[]> list, Map<Integer, String> beanPath, Role roleWhat, boolean isJql) {
		try {
			return convertPartialLoadWithParentRole(list, beanPath, roleWhat);
		} catch (Exception ex) {
			log.error("Error conversion list from handlePartialLoadWithParentRole, message:"+ex.getMessage());
			return new ArrayList<Role>();
		}
    }

	private List<Role> handlePartialLoadWithParentRoleWithOneElementInRow(List<Object> list, Map<Integer, String> beanPath, Role roleWhat, boolean isJql) {
		try {
			return convertPartialLoadWithParentRoleWithOneElementInRow(list, beanPath, roleWhat);
		} catch (Exception ex) {
			log.error("Error conversion list from handlePartialLoadWithParentRoleWithOneElementInRow, message:"+ex.getMessage());
			return new ArrayList<Role>();
		}
    }
    	
	 private List<Role> convertPartialLoadWithParentRole(List<Object[]> list, Map<Integer, String> beanPath, Role roleWhat) throws IllegalAccessException, InvocationTargetException, InstantiationException, NoSuchMethodException {
		 List<Role> resultList = new ArrayList<Role>();
		 for (Object[] row : list) {		
		    Role role = cloneRole (roleWhat);
		    Iterator<Entry<Integer, String>> iter = beanPath.entrySet().iterator();	
		    while (iter.hasNext()) {
		       Entry entry = iter.next();
		       populateRole (role, row[(Integer)entry.getKey()], (String)entry.getValue());
		    }
		    resultList.add(role);
		 }
		 return resultList;		
	 }	
    
	 private List<Role> convertPartialLoadWithParentRoleWithOneElementInRow(List<Object> list, Map<Integer, String> beanPath, Role roleWhat) throws IllegalAccessException, InvocationTargetException, InstantiationException, NoSuchMethodException {
		 List<Role> resultList = new ArrayList<Role>();
		 for (Object row : list) {		
		    Role role = cloneRole (roleWhat);
		    Iterator<Entry<Integer, String>> iter = beanPath.entrySet().iterator();	
		    while (iter.hasNext()) {
		       Entry entry = iter.next();
		       populateRole (role, row, (String)entry.getValue());
		    }
		    resultList.add(role);
		 }		 
		 return resultList;		
	 }
   
	public List partialLoadWithParentForBean(Object bean, Role roleWhat, Role positiveRole, Role negativeRole) {
		Map beanPath = new Hashtable();
		Query hquery = getPartialLoadWithParentRoleJPAQuery (roleWhat, positiveRole, negativeRole, beanPath, new QuerySelectInit(), null);
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
	public String getPartialLoadWithParentRoleHsqlQuery (Role role, Role positiveRole, Role negativeRole, Map beanPath, QueryWhatInit queryWhatInit) {
		Hashtable aliasWhatHt = new Hashtable();
		String what = getPartialLoadWithParentRoleQuery (role, null, aliasWhatHt, null, null, beanPath, "", queryWhatInit);
		Hashtable aliasWhereHt = new Hashtable();
		String where = getPartialLoadWithParentWhereRoleQuery (positiveRole, null, aliasWhatHt, aliasWhereHt, null, null);
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
	public List<Role> partialLoadRole(Role role, Role positiveRole, Role negativeRole) {
	    Query hquery = getEntityManager().createQuery(getPartialLoadRoleQuery (role, positiveRole, negativeRole));
		int countPartialField = countPartialField(role);
		if (countPartialField==0) 
		   return new ArrayList<Role>();
		List list = hquery.getResultList();
		Iterator iter = list.iterator();
		List<Role> returnList = new ArrayList<Role>();
		while(iter.hasNext()) {
	       int index = 0;
	       Object[] row;
	       if (countPartialField==1) {
	    	  row = new Object[1];
	    	  row[0] = iter.next();
	       } else 
		      row = (Object[]) iter.next();
		   Role roleResult = new Role();
           if (role.getId() != null) {
			  roleResult.setId((Integer) row[index]);
			  index++;
           }
           if (role.getName() != null) {
			  roleResult.setName((String) row[index]);
			  index++;
           }
           returnList.add(roleResult);
        }
	    return returnList;
	}

	public static String getPartialLoadWithParentWhereRoleQuery (
	   Role role, Boolean isWhereSet, Hashtable aliasHt, Hashtable aliasWhereHt, String childAlias, String childFKAlias) {
	   if (role==null)
	      return "";
	   String alias = null;
	   if (aliasWhereHt == null) {
	      aliasWhereHt = new Hashtable();
	   } 
	   if (isLookedUp(role)){
	      alias = getNextAlias (aliasWhereHt, role);
		  aliasWhereHt.put (getAliasKey(alias), getAliasConnection(alias, childAlias, childFKAlias));
	   }
	   if (isWhereSet == null)
          isWhereSet = false;
       StringBuffer query = new StringBuffer();
       if (role.getId() != null) {
           query.append (getQueryBLANK_AND (isWhereSet));
		   isWhereSet = true;
           query.append(" "+alias+".id = "+ role.getId() + " ");
       }
       if (role.getName() != null) {
           query.append (getQueryBLANK_AND (isWhereSet));
		   isWhereSet = true;
           query.append(" "+alias+".name = '"+ role.getName()+"' ");
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
	
    public static String getPartialLoadWithParentRoleQuery (
	   Role role, Boolean isWhereSet, Hashtable aliasHt, String childAlias, String childFKAlias, Map beanPath, String rootPath, QueryWhatInit queryWhatInit) {
	   if (role==null)
	      return "";
	   String alias = null;
	   if (aliasHt == null) {
	      aliasHt = new Hashtable();
	   } 
	   if (isLookedUp(role)){
	      alias = getNextAlias (aliasHt, role);
		  aliasHt.put (getAliasKey(alias), getAliasConnection(alias, childAlias, childFKAlias));
	   }
	   if (isWhereSet == null)
          isWhereSet = false;
       StringBuffer query = new StringBuffer();
       if (role.getId() != null) {
          query.append (queryWhatInit.getWhatInit (isWhereSet));
          isWhereSet = true; 
          beanPath.put(beanPath.size(), rootPath+"id");
          query.append(" "+alias+".id ");
       }
       if (role.getName() != null) {
          query.append (queryWhatInit.getWhatInit (isWhereSet));
          isWhereSet = true; 
          beanPath.put(beanPath.size(), rootPath+"name");
          query.append(" "+alias+".name ");
       }
//       query.append(getRoleSearchEqualQuery (positiveRole, negativeRole));
	   return query.toString(); 
    }
	
	protected static String getAliasConnection(String existingAlias, String childAlias, String childFKAlias) {
		if (childAlias==null)
		   return "";
		return childAlias+"."+childFKAlias+" = "+existingAlias+"."+"id";
	}
	
	protected static String getAliasKey (String alias) {
	  //TODO this is a temporary solution use a dedicated object in BslaHiberateDaoSupport
		return "Role|"+alias;
	}
	
	protected static String getAliasKeyAlias (String aliasKey) {
	  //TODO this is a temporary solution use a dedicated object in BslaHiberateDaoSupport
		return StringUtils.substringAfter(aliasKey, "|");
	}
	
	protected static String getAliasKeyDomain (String aliasKey) {
	  //TODO this is a temporary solution use a dedicated object in BslaHiberateDaoSupport
	  return StringUtils.substringBefore(aliasKey, "|");
	}
	
	protected static String getNextAlias (Hashtable aliasHt, Role role) {
		int cptSameAlias = 0;
		Enumeration<String> keys = aliasHt.keys();
		while (keys.hasMoreElements()) {
			String key = keys.nextElement();
			if (key.startsWith("role"))
				cptSameAlias++;
		}
		if (cptSameAlias==0)
			return "role";
		else
			return "role_"+cptSameAlias;
	}
	
	
	protected static boolean isLookedUp (Role role) {
	   if (role==null)
		  return false;
       if (role.getId() != null) {
	      return true;
       }
       if (role.getName() != null) {
	      return true;
       }
       return false;   
	}
	
    public String getPartialLoadRoleQuery(
	   Role role, 
	   Role positiveRole, 
	   Role negativeRole) {
       boolean isWhereSet = false;
       StringBuffer query = new StringBuffer();
       if (role.getId() != null) {
          query.append (getQuerySelectComma (isWhereSet));
          isWhereSet = true; 
          query.append(" id ");
       }
       if (role.getName() != null) {
          query.append (getQuerySelectComma (isWhereSet));
          isWhereSet = true; 
          query.append(" name ");
       }
       query.append(getRoleSearchEqualQuery (positiveRole, negativeRole));
	   return query.toString(); 
    }
	
	public List<Role> searchPrototypeWithCacheRole(Role role) {
		SimpleCache simpleCache = new SimpleCache();
		List<Role> list = (List<Role>)simpleCache.get(role.toString());
		if (list==null) {
			list = searchPrototypeRole(role);
			simpleCache.put(role.toString(), list);
		}
		return list;
	}

    public List<Role> loadGraph(Role graphMaskWhat, List<Role> whereMask) {
        return loadGraphOneLevel(graphMaskWhat, whereMask);
    }
 
    public List<Role> loadGraphOneLevel(Role graphMaskWhat, List<Role> whereMask) {
        //first get roots element from where list mask
        // this brings the level 0 of the graph (root level)
        graphMaskWhat.setId(graphMaskWhat.integerMask__);
        List<Role> roles = searchPrototypeRole (whereMask);
        // for each sub level perform the search with a subquery then reassemble
        // 1. get all sublevel queries
        // 2. perform queries on the correct dao
        // 3. reassemble
        return getLoadGraphOneLevel (graphMaskWhat, roles);
    }

	private List<Role> copy(List<Role> inputs) {
		List<Role> l = new ArrayList<Role>();
		for (Role input : inputs) {
			Role copy = new Role();
			copy.copy(input);
			l.add(copy);
		}
		return l;
	}
	   
	private List<Role> getLoadGraphOneLevel (Role graphMaskWhat, List<Role> parents) {
	    return loadGraphFromParentKey (graphMaskWhat, parents);
	} 
	
	public List<Role> loadGraphFromParentKey (Role graphMaskWhat, List<Role> parents) {
		//foreach children:
		//check if not empty take first
		parents = copy (parents); //working with detached entities to avoid unnecessary sql calls
		if (parents==null || parents.isEmpty())
		   return parents;
		List<String> ids = getPk (parents);
		return parents;
	}
	
	private List<String> getPk(List<Role> input) {
		List<String> s = new ArrayList<String>();
		for (Role t : input) {
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
