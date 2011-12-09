package net.sf.mp.demo.conference.dao.face.statistics;

import net.sf.mp.demo.conference.domain.statistics.StatMbByRole;
import java.util.List;
import net.sf.minuteProject.architecture.bsla.bean.criteria.PaginationCriteria;
import net.sf.minuteProject.architecture.bsla.dao.face.DataAccessObject;


/**
 *
 * <p>Title: StatMbByRoleDao</p>
 *
 * <p>Description: Interface of a Data access object dealing with StatMbByRoleDao
 * persistence. It offers a set of methods which allow for saving,
 * deleting and searching statMbByRole objects</p>
 *
 */
public interface StatMbByRoleDao extends DataAccessObject {

    /**
     * Inserts a StatMbByRole entity 
     * @param StatMbByRole statMbByRole
     */
    public void insertStatMbByRole(StatMbByRole statMbByRole) ;
 
    /**
     * Inserts a list of StatMbByRole entity 
     * @param List<StatMbByRole> statMbByRoles
     */
    public void insertStatMbByRoles(List<StatMbByRole> statMbByRoles) ;
        
    /**
     * Updates a StatMbByRole entity 
     * @param StatMbByRole statMbByRole
     */
    public StatMbByRole updateStatMbByRole(StatMbByRole statMbByRole) ;

	 /**
     * Updates a StatMbByRole entity with only the attributes set into StatMbByRole.
	 * The primary keys are to be set for this method to operate.
	 * This is a performance friendly feature, which remove the udibiquous full load and full update when an
	 * update is to be done
   * Remark: The primary keys cannot be update by this methods, nor are the attributes that must be set to null.
   * @param StatMbByRole statMbByRole
   */
    public int updateNotNullOnlyStatMbByRole(StatMbByRole statMbByRole) ;
	 
	public int updateNotNullOnlyPrototypeStatMbByRole(StatMbByRole statMbByRole, StatMbByRole prototypeCriteria);
	
     /**
     * Saves a StatMbByRole entity 
     * @param StatMbByRole statMbByRole
     */
    public void saveStatMbByRole(StatMbByRole statMbByRole);
    
    /**
     * Deletes a StatMbByRole entity 
     * @param StatMbByRole statMbByRole
     */
    public void deleteStatMbByRole(StatMbByRole statMbByRole) ;
 
    /**
     * Loads the StatMbByRole entity which is related to an instance of
     * StatMbByRole
     * @param Long id
     * @return StatMbByRole The StatMbByRole entity
     
    public StatMbByRole loadStatMbByRole(Long id);
*/
    /**
     * Loads the StatMbByRole entity which is related to an instance of
     * StatMbByRole
     * @param java.lang.String Id
     * @return StatMbByRole The StatMbByRole entity
     */
    public StatMbByRole loadStatMbByRole(java.lang.String id);    

    /**
     * Loads a list of StatMbByRole entity 
     * @param List<java.lang.String> ids
     * @return List<StatMbByRole> The StatMbByRole entity
     */
    public List<StatMbByRole> loadStatMbByRoleListByStatMbByRole (List<StatMbByRole> statMbByRoles);
    
    /**
     * Loads a list of StatMbByRole entity 
     * @param List<java.lang.String> ids
     * @return List<StatMbByRole> The StatMbByRole entity
     */
    public List<StatMbByRole> loadStatMbByRoleListById(List<java.lang.String> ids);
    
    /**
     * Loads the StatMbByRole entity which is related to an instance of
     * StatMbByRole and its dependent one to many objects
     * @param Long id
     * @return StatMbByRole The StatMbByRole entity
     */
    public StatMbByRole loadFullFirstLevelStatMbByRole(java.lang.String id);
    
    /**
     * Loads the StatMbByRole entity which is related to an instance of
     * StatMbByRole
     * @param StatMbByRole statMbByRole
     * @return StatMbByRole The StatMbByRole entity
     */
    public StatMbByRole loadFullFirstLevelStatMbByRole(StatMbByRole statMbByRole);    
    
    
    /**
     * Loads the StatMbByRole entity which is related to an instance of
     * StatMbByRole and its dependent objects one to many
     * @param Long id
     * @return StatMbByRole The StatMbByRole entity
     */
    public StatMbByRole loadFullStatMbByRole(Long id) ;

    /**
     * Searches a list of StatMbByRole entity based on a StatMbByRole containing StatMbByRole matching criteria
     * @param StatMbByRole statMbByRole
     * @return List<StatMbByRole>
     */
    public List<StatMbByRole> searchPrototypeStatMbByRole(StatMbByRole statMbByRole) ;
    
    /**
     * Searches a list of StatMbByRole entity based on a list of StatMbByRole containing StatMbByRole matching criteria
     * @param List<StatMbByRole> statMbByRoles
     * @return List<StatMbByRole>
     */
    public List<StatMbByRole> searchPrototypeStatMbByRole(List<StatMbByRole> statMbByRoles) ;    
    	
	/**
     * Searches a list of StatMbByRole entity 
     * @param StatMbByRole statMbByRole
     * @return List
     */
    public List<StatMbByRole> searchPrototypeStatMbByRole(StatMbByRole statMbByRolePositive, StatMbByRole statMbByRoleNegative) ;
        
    /**
     * Load a paginated list of StatMbByRole entity dependent of pagination criteria
     * @param PaginationCriteria paginationCriteria
     * @return List
     */
    public List<StatMbByRole> loadPaginatedStatMbByRole (StatMbByRole statMbByRole, PaginationCriteria paginationCriteria) ;
            
}
