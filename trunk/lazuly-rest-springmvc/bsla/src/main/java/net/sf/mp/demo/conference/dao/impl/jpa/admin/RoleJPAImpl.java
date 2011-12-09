package net.sf.mp.demo.conference.dao.impl.jpa.admin;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Date;
import java.sql.Timestamp;

import javax.persistence.Query;
import javax.persistence.EntityManager;

import org.springframework.orm.jpa.EntityManagerFactoryUtils;
import org.springframework.orm.jpa.support.JpaDaoSupport;
import org.springframework.transaction.annotation.Transactional;

import net.sf.minuteProject.architecture.bsla.bean.criteria.PaginationCriteria;
import net.sf.mp.demo.conference.dao.face.admin.RoleDao;
import net.sf.mp.demo.conference.domain.admin.Role;

/**
 *
 * <p>Title: RoleJPAImpl</p>
 *
 * <p>Description: Interface of a Data access object dealing with RoleJPAImpl
 * persistence. It offers a set of methods which allow for saving,
 * deleting and searching RoleJPAImpl objects</p>
 *
 */

public class RoleJPAImpl extends JpaDaoSupport implements RoleDao {

	public RoleJPAImpl () {}

    /**
     * Inserts a Role entity 
     * @param Role role
     */
    public void insertRole(Role role) {
       convertTransientReferenceToNull (role);
       getJpaTemplate().persist(role);
    }

    protected void insertRole(EntityManager emForRecursiveDao, Role role) {
       emForRecursiveDao.persist(role);
    } 
    /**
     * Inserts a list of Role entity 
     * @param List<Role> roles
     */
    public void insertRoles(List<Role> roles) {
    	//TODO
    }
    /**
     * Updates a Role entity 
     * @param Role role
     */
    public Role updateRole(Role role) {
       convertTransientReferenceToNull (role);
       return getJpaTemplate().merge(role);
    }

	/**
     * Updates a Role entity with only the attributes set into Role.
	 * The primary keys are to be set for this method to operate.
	 * This is a performance friendly feature, which remove the udibiquous full load and full update when an
	 * update is issued
     * Remark: The primary keys cannot be update by this methods, nor are the attributes that must be set to null.
     * @param Role role
    */ 
    @Transactional
    public int updateNotNullOnlyRole(Role role) {
        Query jpaQuery = getEntityManager().createQuery(getUpdateNotNullOnlyRoleQueryChunk(role));
        if (role.getId() != null) {
           jpaQuery.setParameter ("id", role.getId());
        }   
        if (role.getName() != null) {
           jpaQuery.setParameter ("name", role.getName());
        }   
		return jpaQuery.executeUpdate();
    }

    protected String getUpdateNotNullOnlyRoleQueryChunkPrototype (Role role) {
        boolean isWhereSet = false;
        StringBuffer query = new StringBuffer();
        query.append (" update Role role ");
        if (role.getName() != null) {
           query.append (getQueryCommaSet (isWhereSet));
           isWhereSet = true; 
           query.append(" role.name = :name");
        }
        return query.toString();
    }
    
    protected String getUpdateNotNullOnlyRoleQueryChunk (Role role) {
        boolean isWhereSet = false;
        StringBuffer query = new StringBuffer(getUpdateNotNullOnlyRoleQueryChunkPrototype(role));
        if (role.getId() != null) {
           query.append (getQueryWHERE_AND (isWhereSet));
           isWhereSet = true;
			     query.append(" role.id = :id");
        }
        return query.toString();
    }
    
                
	protected Role assignBlankToNull (Role role) {
	    if (role==null)
			return null;
        if (role.getName()!=null && role.getName().equals(""))
           role.setName((String)null);
		return role;
	}
	
	protected boolean isAllNull (Role role) {
	    if (role==null)
			return true;
        if (role.getId()!=null) 
            return false;
        if (role.getName()!=null) 
            return false;
		return true;
	}
		
	@Transactional
    public int updateNotNullOnlyPrototypeRole(Role role, Role prototypeCriteria) {
        boolean isWhereSet = false;
        StringBuffer query = new StringBuffer();
        query.append (" update Role role ");
        if (role.getId() != null) {
           query.append (getQueryCommaSet (isWhereSet));
           isWhereSet = true; 
           query.append(" role.id = "+ role.getId() + " ");
        }
        if (role.getName() != null) {
           query.append (getQueryCommaSet (isWhereSet));
           isWhereSet = true; 
           query.append(" role.name = '"+ role.getName()+"' ");
        }
		isWhereSet = false; 
        if (prototypeCriteria.getId() != null) {
           query.append (getQueryWHERE_AND (isWhereSet));
           isWhereSet = true;
           query.append(" role.id = "+ prototypeCriteria.getId() + " ");
        }
        if (prototypeCriteria.getName() != null) {
           query.append (getQueryWHERE_AND (isWhereSet));
           isWhereSet = true;
           query.append(" role.name = '"+ prototypeCriteria.getName()+"' ");
        }
        Query jpaQuery = getEntityManager().createQuery(query.toString());
		return jpaQuery.executeUpdate();
    }
     
     /**
     * Saves a Role entity 
     * @param Role role
     */
    public void saveRole(Role role) {
       //getJpaTemplate().persist(role);
       convertTransientReferenceToNull (role);
       if (getJpaTemplate().contains(role)) {
          getJpaTemplate().merge(role);
       } else {
          getJpaTemplate().persist(role);
       }
       getJpaTemplate().flush(); 
    }
       
    /**
     * Deletes a Role entity 
     * @param Role role
     */
    public void deleteRole(Role role) {
      getJpaTemplate().remove(role);
    }
    
    /**
     * Loads the Role entity which is related to an instance of
     * Role
     * @param Long id
     * @return Role The Role entity
     
    public Role loadRole(Long id) {
    	return (Role)getJpaTemplate().get(Role.class, id);
    }
*/
  
    /**
     * Loads the Role entity which is related to an instance of
     * Role
     * @param java.lang.Integer Id
     * @return Role The Role entity
     */
    public Role loadRole(java.lang.Integer id) {
    	return (Role)getJpaTemplate().find(Role.class, id);
    }
    
    /**
     * Loads a list of Role entity 
     * @param List<java.lang.Integer> ids
     * @return List<Role> The Role entity
     */
    public List<Role> loadRoleListByRole (List<Role> roles) {
       return null;
    }
    
    /**
     * Loads a list of Role entity 
     * @param List<java.lang.Integer> ids
     * @return List<Role> The Role entity
     */
    public List<Role> loadRoleListById(List<java.lang.Integer> ids){
       return null;
    }
    
    /**
     * Loads the Role entity which is related to an instance of
     * Role and its dependent one to many objects
     * @param Long id
     * @return Role The Role entity
     */
    public Role loadFullFirstLevelRole(java.lang.Integer id) {
        List list = getJpaTemplate().find(
                     "SELECT role FROM Role role "
                     + " LEFT JOIN role.memberRoleRoleIds "   
                     + " WHERE role.id = "+id
               );
         if (list!=null && !list.isEmpty())
            return (Role)list.get(0);
         return null;
    	//return null;//(Role) getJpaTemplate().queryForObject("loadFullFirstLevelRole", id);
    }

    /**
     * Loads the Role entity which is related to an instance of
     * Role
     * @param Role role
     * @return Role The Role entity
     */
    public Role loadFullFirstLevelRole(Role role) {
        boolean isWhereSet = false;
        StringBuffer query = new StringBuffer();
        query.append ("SELECT role FROM Role role ");
        if (role.getId() != null) {
           query.append (getQueryWHERE_AND (isWhereSet));
           isWhereSet = true;
           query.append(" role.id = "+ role.getId() + " ");
         }
	        	List list = getJpaTemplate().find(query.toString());
        if (list!=null && !list.isEmpty())
           return (Role)list.get(0);    
        return null;
    }  
     
    /**
     * Loads the Role entity which is related to an instance of
     * Role and its dependent objects one to many
     * @param Long id
     * @return Role The Role entity
     */
    public Role loadFullRole(Long id) {
    	return null;//(Role)getJpaTemplate().queryForObject("loadFullRole", id);
    }

    /**
     * Searches a list of Role entity 
     * @param Role role
     * @return List
     */
    public List<Role> searchPrototypeRole(Role role) {      
       return searchPrototype (role, null);            
    }  
    
    protected List<Role> searchPrototype (Role role, Integer maxResults) { 
       Query hquery = getEntityManager().createQuery(getRoleSearchEqualQuery (role));
       if (maxResults!=null)
          hquery.setMaxResults(maxResults);
       return hquery.getResultList();         
    }        
    
    public List<Role> searchPrototypeRole (List<Role> roles) {
       return searchPrototype (roles, null);  
    }

    protected List<Role> searchPrototype (List<Role> roles, Integer maxResults) {    
       //TODO convert setMaxResults in JPA if (maxResults!=null)
       //   getJpaTemplate().setMaxResults(maxResults);
       return getJpaTemplate().find(getRoleSearchEqualQuery (null, roles));            
    }    

    public List<Role> searchDistinctPrototypeRole (Role roleMask, List<Role> roles) {
        return getJpaTemplate().find(getRoleSearchEqualQuery (roleMask, roles));    
    }
         
	/**
     * Searches a list of Role entity 
     * @param Role rolePositive
     * @param Role roleNegative
     * @return List
     */
    public List<Role> searchPrototypeRole(Role rolePositive, Role roleNegative) {
	    return getJpaTemplate().find(getRoleSearchEqualQuery (rolePositive, roleNegative));  
    }

    /**
    * return a jql query search on a Role prototype
    */
    protected String getRoleSearchEqualQuery (Role role) {
        StringBuffer query = new StringBuffer();
        StringBuffer queryWhere = new StringBuffer();
        query.append ("SELECT role FROM Role role ");
        queryWhere.append (getRoleSearchEqualWhereQueryChunk(role, false));   
	    return getHQuery(query.toString(), queryWhere.toString());
    }


    /**
    * return a jql search for a list of Role prototype
    */
    protected String getRoleSearchEqualQuery (Role roleMask, List<Role> roles) {
        boolean isOrSet = false;
        StringBuffer query = new StringBuffer();
        if (roleMask !=null)
           query.append (getRoleMaskWhat (roleMask));
        query.append (" FROM Role role ");
        StringBuffer queryWhere = new StringBuffer();
        for (Role role : roles) {
           if (!isAllNull(role)) {        
	           queryWhere.append (getQueryOR (isOrSet));
	           isOrSet = true;        
	           queryWhere.append (" ("+getRoleSearchEqualWhereQueryChunk(role, false)+") ");
           }
        }
	    return getHQuery(query.toString(), queryWhere.toString());
    }	
    
    protected String getRoleMaskWhat (Role roleMask) {
        boolean isCommaSet = false;
        StringBuffer query = new StringBuffer("SELECT DISTINCT ");
        if (roleMask.getId() != null) {
           query.append (getQueryComma (isCommaSet));
           isCommaSet = true;
           query.append(" id ");
        }
        if (roleMask.getName() != null) {
           query.append (getQueryComma (isCommaSet));
           isCommaSet = true;
           query.append(" name ");
        }
        if (!isCommaSet)
           return "";
	    return query.toString();        
    }
    
    protected String getRoleSearchEqualWhereQueryChunk (Role role, boolean isAndSet) {
        StringBuffer query = new StringBuffer();
        if (role.getId() != null) {
           query.append (getQueryAND (isAndSet));
           isAndSet = true;
           query.append(" role.id = "+ role.getId() + " ");
        }
        if (role.getName() != null) {
           query.append (getQueryAND (isAndSet));
           isAndSet = true;
           query.append(" role.name = '"+ role.getName()+"' ");
        }
	    return query.toString();
    }   
     
    /**
    * return a jql search on a Role prototype with positive and negative beans
    */
    protected String getRoleSearchEqualQuery (Role rolePositive, Role roleNegative) {
        boolean isWhereSet = false;
        StringBuffer query = new StringBuffer();
        query.append (" SELECT role FROM Role role ");
        if (rolePositive!=null && rolePositive.getId() != null) {
           query.append (getQueryWHERE_AND (isWhereSet));
           isWhereSet = true;
           query.append(" role.id = "+ rolePositive.getId() + " ");
        } else if (roleNegative.getId() != null) {
           query.append (getQueryWHERE_AND (isWhereSet));
           isWhereSet = true;   
           query.append(" role.id is null ");
        }
        if (rolePositive!=null && rolePositive.getName() != null) {
           query.append (getQueryWHERE_AND (isWhereSet));
           isWhereSet = true;
           query.append(" role.name = '"+ rolePositive.getName()+"' ");
        } else if (roleNegative.getName() != null) {
           query.append (getQueryWHERE_AND (isWhereSet));
           isWhereSet = true;   
           query.append(" role.name is null ");
        }
	    return query.toString();
    }
    
    /**
     * Load a paginated list of Role entity dependent of pagination criteria
     * @param PaginationCriteria paginationCriteria
     * @return List
     */
    public List <Role> loadPaginatedRole (Role role, PaginationCriteria paginationCriteria) {
	    List<Integer> page = loadPaginatedRoleIdentitiesFromStartPositionId(role, paginationCriteria);
    	int start = paginationCriteria.getNumberOfRowsReturned()*(paginationCriteria.getPageNumber());
    	int max = page.size();
    	if (start<max) {
    	   List<Integer> returnPage = page.subList(start, max);	
           StringBuffer query = new StringBuffer();
           query.append (" SELECT role FROM Role role ");      
	       query.append(" where role.id in (");
	       for (Iterator iter = returnPage.iterator(); iter.hasNext();) {
			  Integer id = (Integer) iter.next();
			  query.append(id.toString());
		      if (iter.hasNext())
			     query.append(",");
		   }
	       query.append(") ");
	       return getJpaTemplate().find(query.toString()); 
    	} 
        return new ArrayList<Role>();
    }      

    protected List<Integer> loadPaginatedRoleIdentitiesFromStartPositionId (Role role, PaginationCriteria paginationCriteria) {
       boolean isWhereSet = false;
       StringBuffer query = new StringBuffer();
       query.append ("select role.id ");
       query.append (getRoleSearchEqualQuery (role));
       if (paginationCriteria.getOrderList()!=null) {
    	   query.append(" order by "+paginationCriteria.getOrderList());
       }
       int maxResult = paginationCriteria.getNumberOfRowsReturned()*(1+paginationCriteria.getPageNumber());
       List<Integer> set = getEntityManager().createNamedQuery(query.toString()).setMaxResults(maxResult).getResultList();
       return set;
    }

    //TODO put in upper class
    
	protected String getQueryWHERE_AND(boolean isWhereSet) {
		if (isWhereSet)
			return " AND ";
		return " WHERE ";
	}

	protected String getQueryCommaSet(boolean isWhereSet) {
		if (isWhereSet)
			return " , ";
		return " SET ";
	}

	protected static String getQuerySelectComma(boolean isSelectSet) {
		if (isSelectSet)
			return " , ";
		return " select ";
	}

	protected static String getQueryBLANK_AND(boolean isBlankSet) {
		if (isBlankSet)
			return " AND ";
		return " ";
	}

	protected static String getQueryBLANK_COMMA(boolean isBlankSet) {
		if (isBlankSet)
			return " , ";
		return " ";
	}

	protected String getQueryComma(boolean isCommaSet) {
		return (isCommaSet) ? " , " : "";
	}

	protected String getQueryOR(boolean isOrSet) {
		return (isOrSet) ? " OR " : "";
	}

	protected String getQueryAND(boolean isAndSet) {
		return (isAndSet) ? " AND " : "";
	}

	protected String getHQuery (String what, String where) {
        String whereWord = getWhere (where);
	    return what+whereWord+where;
    }
    
    protected String getWhere (String where) {
        return (where==null || where.trim().equals("") || where.trim()==null)?"":" WHERE ";
    }   
    protected EntityManager getEntityManager () {
        return EntityManagerFactoryUtils.getTransactionalEntityManager(getJpaTemplate().getEntityManagerFactory());    
    }
    
    protected void convertTransientReferenceToNull (Role role) {
    }
}
