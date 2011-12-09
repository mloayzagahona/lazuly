package net.sf.mp.demo.conference.dao.face.admin;

import net.sf.mp.demo.conference.domain.admin.Role;
import java.util.List;
import net.sf.minuteProject.architecture.bsla.bean.criteria.PaginationCriteria;
import net.sf.minuteProject.architecture.bsla.dao.face.DataAccessObject;


/**
 *
 * <p>Title: RoleDao</p>
 *
 * <p>Description: Interface of a Data access object dealing with RoleDao
 * persistence. It offers a set of methods which allow for saving,
 * deleting and searching role objects</p>
 *
 */
public interface RoleDao extends DataAccessObject {

    /**
     * Inserts a Role entity 
     * @param Role role
     */
    public void insertRole(Role role) ;
 
    /**
     * Inserts a list of Role entity 
     * @param List<Role> roles
     */
    public void insertRoles(List<Role> roles) ;
        
    /**
     * Updates a Role entity 
     * @param Role role
     */
    public Role updateRole(Role role) ;

	 /**
     * Updates a Role entity with only the attributes set into Role.
	 * The primary keys are to be set for this method to operate.
	 * This is a performance friendly feature, which remove the udibiquous full load and full update when an
	 * update is to be done
   * Remark: The primary keys cannot be update by this methods, nor are the attributes that must be set to null.
   * @param Role role
   */
    public int updateNotNullOnlyRole(Role role) ;
	 
	public int updateNotNullOnlyPrototypeRole(Role role, Role prototypeCriteria);
	
     /**
     * Saves a Role entity 
     * @param Role role
     */
    public void saveRole(Role role);
    
    /**
     * Deletes a Role entity 
     * @param Role role
     */
    public void deleteRole(Role role) ;
 
    /**
     * Loads the Role entity which is related to an instance of
     * Role
     * @param Long id
     * @return Role The Role entity
     
    public Role loadRole(Long id);
*/
    /**
     * Loads the Role entity which is related to an instance of
     * Role
     * @param java.lang.Integer Id
     * @return Role The Role entity
     */
    public Role loadRole(java.lang.Integer id);    

    /**
     * Loads a list of Role entity 
     * @param List<java.lang.Integer> ids
     * @return List<Role> The Role entity
     */
    public List<Role> loadRoleListByRole (List<Role> roles);
    
    /**
     * Loads a list of Role entity 
     * @param List<java.lang.Integer> ids
     * @return List<Role> The Role entity
     */
    public List<Role> loadRoleListById(List<java.lang.Integer> ids);
    
    /**
     * Loads the Role entity which is related to an instance of
     * Role and its dependent one to many objects
     * @param Long id
     * @return Role The Role entity
     */
    public Role loadFullFirstLevelRole(java.lang.Integer id);
    
    /**
     * Loads the Role entity which is related to an instance of
     * Role
     * @param Role role
     * @return Role The Role entity
     */
    public Role loadFullFirstLevelRole(Role role);    
    
    
    /**
     * Loads the Role entity which is related to an instance of
     * Role and its dependent objects one to many
     * @param Long id
     * @return Role The Role entity
     */
    public Role loadFullRole(Long id) ;

    /**
     * Searches a list of Role entity based on a Role containing Role matching criteria
     * @param Role role
     * @return List<Role>
     */
    public List<Role> searchPrototypeRole(Role role) ;
    
    /**
     * Searches a list of Role entity based on a list of Role containing Role matching criteria
     * @param List<Role> roles
     * @return List<Role>
     */
    public List<Role> searchPrototypeRole(List<Role> roles) ;    
    	
	/**
     * Searches a list of Role entity 
     * @param Role role
     * @return List
     */
    public List<Role> searchPrototypeRole(Role rolePositive, Role roleNegative) ;
        
    /**
     * Load a paginated list of Role entity dependent of pagination criteria
     * @param PaginationCriteria paginationCriteria
     * @return List
     */
    public List<Role> loadPaginatedRole (Role role, PaginationCriteria paginationCriteria) ;
            
}
