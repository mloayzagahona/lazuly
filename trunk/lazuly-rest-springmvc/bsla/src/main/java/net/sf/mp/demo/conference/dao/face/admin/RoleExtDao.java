package net.sf.mp.demo.conference.dao.face.admin;

import net.sf.mp.demo.conference.domain.admin.Role;
import java.util.List;
import net.sf.minuteProject.architecture.filter.data.Criteria;
import net.sf.minuteProject.architecture.bsla.dao.face.DataAccessObject;

/**
 *
 * <p>Title: RoleExtDao</p>
 *
 * <p>Description: Interface of a Data access object dealing with RoleExtDao
 * persistence. It offers extended DAO functionalities</p>
 *
 */
public interface RoleExtDao extends DataAccessObject {
     
     /**
     * Inserts a Role entity with cascade of its children
     * @param Role role
     */
    public void insertRoleWithCascade(Role role) ;
    
    /**
     * Inserts a list of Role entity with cascade of its children
     * @param List<Role> roles
     */
    public void insertRolesWithCascade(List<Role> roles) ;        
   
    /**
     * lookup Role entity Role, criteria and max result number
     */
    public List<Role> lookupRole(Role role, Criteria criteria, Integer numberOfResult);
	
	public Integer updateNotNullOnlyRole (Role role, Criteria criteria);

	/**
	 * Affect the first role retrieved corresponding to the role criteria.
	 * Blank criteria are mapped to null.
	 * If no criteria is found, null is returned.
	 */
    public Role affectRole (Role role);
    
    public Role affectRoleUseCache (Role role);
    	
	/**
	 * Assign the first role retrieved corresponding to the role criteria.
	 * Blank criteria are mapped to null.
	 * If no criteria is found, null is returned.
	 * If there is no role corresponding in the database. Then role is inserted and returned with its primary key(s). 
	 */
    public Role assignRole (Role role);
    
    public Role assignRoleUseCache (Role role);
         
    /**
    * return the first Role entity found
    */           
    public Role getFirstRole (Role role);
    
    /**
    * checks if the Role entity exists
    */           
    public boolean existsRole (Role role);    
    
    public boolean existsRoleWhereConditionsAre (Role role);

    /**
    * partial load enables to specify the fields you want to load explicitly
    */            
    public List<Role> partialLoadRole(Role role, Role positiveRole, Role negativeRole);

    /**
    * partial load with parent entities
    * variation (list, first, distinct decorator)
    * variation2 (with cache)
    */
    public List<Role> partialLoadWithParentRole(Role role, Role positiveRole, Role negativeRole);

    public List<Role> partialLoadWithParentRoleUseCache(Role role, Role positiveRole, Role negativeRole, Boolean useCache);

    public List<Role> partialLoadWithParentRoleUseCacheOnResult(Role role, Role positiveRole, Role negativeRole, Boolean useCache);

    /**
    * variation first
    */
    public Role partialLoadWithParentFirstRole(Role roleWhat, Role positiveRole, Role negativeRole);
    
    public Role partialLoadWithParentFirstRoleUseCache(Role roleWhat, Role positiveRole, Role negativeRole, Boolean useCache);

    public Role partialLoadWithParentFirstRoleUseCacheOnResult(Role roleWhat, Role positiveRole, Role negativeRole, Boolean useCache);

    /**
    * variation distinct
    */
    public List<Role> getDistinctRole(Role roleWhat, Role positiveRole, Role negativeRole);

    //
    public List partialLoadWithParentForBean(Object bean, Role role, Role positiveRole, Role negativeRole);

    /**
    * search on prototype with cache
    */
    public List<Role> searchPrototypeWithCacheRole (Role role);
      
    
    /**
     * Searches a list of distinct Role entity based on a Role mask and a list of Role containing Role matching criteria
     * @param Role role
     * @param List<Role> roles
     * @return List<Role>
     */
    public List<Role> searchDistinctPrototypeRole(Role roleMask, List<Role> roles) ;    

	public List<Role> countDistinct (Role whatMask, Role whereEqCriteria);
	
	public Long count (Role whereEqCriteria);
	
	public List<Role> loadGraph(Role graphMaskWhat, List<Role> whereMask);  
	
	public List<Role> loadGraphFromParentKey (Role graphMaskWhat, List<Role> parents); 
	
    /**
     * generic to move after in superclass
     */
    public List<Object[]> getSQLQueryResult(String query);     

	
}

