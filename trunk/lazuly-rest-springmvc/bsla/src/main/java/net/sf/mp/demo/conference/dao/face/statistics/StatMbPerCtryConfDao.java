package net.sf.mp.demo.conference.dao.face.statistics;

import net.sf.mp.demo.conference.domain.statistics.StatMbPerCtryConf;
import java.util.List;
import net.sf.minuteProject.architecture.bsla.bean.criteria.PaginationCriteria;
import net.sf.minuteProject.architecture.bsla.dao.face.DataAccessObject;


/**
 *
 * <p>Title: StatMbPerCtryConfDao</p>
 *
 * <p>Description: Interface of a Data access object dealing with StatMbPerCtryConfDao
 * persistence. It offers a set of methods which allow for saving,
 * deleting and searching statMbPerCtryConf objects</p>
 *
 */
public interface StatMbPerCtryConfDao extends DataAccessObject {

    /**
     * Inserts a StatMbPerCtryConf entity 
     * @param StatMbPerCtryConf statMbPerCtryConf
     */
    public void insertStatMbPerCtryConf(StatMbPerCtryConf statMbPerCtryConf) ;
 
    /**
     * Inserts a list of StatMbPerCtryConf entity 
     * @param List<StatMbPerCtryConf> statMbPerCtryConfs
     */
    public void insertStatMbPerCtryConfs(List<StatMbPerCtryConf> statMbPerCtryConfs) ;
        
    /**
     * Updates a StatMbPerCtryConf entity 
     * @param StatMbPerCtryConf statMbPerCtryConf
     */
    public StatMbPerCtryConf updateStatMbPerCtryConf(StatMbPerCtryConf statMbPerCtryConf) ;

	 /**
     * Updates a StatMbPerCtryConf entity with only the attributes set into StatMbPerCtryConf.
	 * The primary keys are to be set for this method to operate.
	 * This is a performance friendly feature, which remove the udibiquous full load and full update when an
	 * update is to be done
   * Remark: The primary keys cannot be update by this methods, nor are the attributes that must be set to null.
   * @param StatMbPerCtryConf statMbPerCtryConf
   */
    public int updateNotNullOnlyStatMbPerCtryConf(StatMbPerCtryConf statMbPerCtryConf) ;
	 
	public int updateNotNullOnlyPrototypeStatMbPerCtryConf(StatMbPerCtryConf statMbPerCtryConf, StatMbPerCtryConf prototypeCriteria);
	
     /**
     * Saves a StatMbPerCtryConf entity 
     * @param StatMbPerCtryConf statMbPerCtryConf
     */
    public void saveStatMbPerCtryConf(StatMbPerCtryConf statMbPerCtryConf);
    
    /**
     * Deletes a StatMbPerCtryConf entity 
     * @param StatMbPerCtryConf statMbPerCtryConf
     */
    public void deleteStatMbPerCtryConf(StatMbPerCtryConf statMbPerCtryConf) ;
 
    /**
     * Loads the StatMbPerCtryConf entity which is related to an instance of
     * StatMbPerCtryConf
     * @param Long id
     * @return StatMbPerCtryConf The StatMbPerCtryConf entity
     
    public StatMbPerCtryConf loadStatMbPerCtryConf(Long id);
*/
    /**
     * Loads the StatMbPerCtryConf entity which is related to an instance of
     * StatMbPerCtryConf
     * @param java.lang.String Id
     * @return StatMbPerCtryConf The StatMbPerCtryConf entity
     */
    public StatMbPerCtryConf loadStatMbPerCtryConf(java.lang.String id);    

    /**
     * Loads a list of StatMbPerCtryConf entity 
     * @param List<java.lang.String> ids
     * @return List<StatMbPerCtryConf> The StatMbPerCtryConf entity
     */
    public List<StatMbPerCtryConf> loadStatMbPerCtryConfListByStatMbPerCtryConf (List<StatMbPerCtryConf> statMbPerCtryConfs);
    
    /**
     * Loads a list of StatMbPerCtryConf entity 
     * @param List<java.lang.String> ids
     * @return List<StatMbPerCtryConf> The StatMbPerCtryConf entity
     */
    public List<StatMbPerCtryConf> loadStatMbPerCtryConfListById(List<java.lang.String> ids);
    
    /**
     * Loads the StatMbPerCtryConf entity which is related to an instance of
     * StatMbPerCtryConf and its dependent one to many objects
     * @param Long id
     * @return StatMbPerCtryConf The StatMbPerCtryConf entity
     */
    public StatMbPerCtryConf loadFullFirstLevelStatMbPerCtryConf(java.lang.String id);
    
    /**
     * Loads the StatMbPerCtryConf entity which is related to an instance of
     * StatMbPerCtryConf
     * @param StatMbPerCtryConf statMbPerCtryConf
     * @return StatMbPerCtryConf The StatMbPerCtryConf entity
     */
    public StatMbPerCtryConf loadFullFirstLevelStatMbPerCtryConf(StatMbPerCtryConf statMbPerCtryConf);    
    
    
    /**
     * Loads the StatMbPerCtryConf entity which is related to an instance of
     * StatMbPerCtryConf and its dependent objects one to many
     * @param Long id
     * @return StatMbPerCtryConf The StatMbPerCtryConf entity
     */
    public StatMbPerCtryConf loadFullStatMbPerCtryConf(Long id) ;

    /**
     * Searches a list of StatMbPerCtryConf entity based on a StatMbPerCtryConf containing StatMbPerCtryConf matching criteria
     * @param StatMbPerCtryConf statMbPerCtryConf
     * @return List<StatMbPerCtryConf>
     */
    public List<StatMbPerCtryConf> searchPrototypeStatMbPerCtryConf(StatMbPerCtryConf statMbPerCtryConf) ;
    
    /**
     * Searches a list of StatMbPerCtryConf entity based on a list of StatMbPerCtryConf containing StatMbPerCtryConf matching criteria
     * @param List<StatMbPerCtryConf> statMbPerCtryConfs
     * @return List<StatMbPerCtryConf>
     */
    public List<StatMbPerCtryConf> searchPrototypeStatMbPerCtryConf(List<StatMbPerCtryConf> statMbPerCtryConfs) ;    
    	
	/**
     * Searches a list of StatMbPerCtryConf entity 
     * @param StatMbPerCtryConf statMbPerCtryConf
     * @return List
     */
    public List<StatMbPerCtryConf> searchPrototypeStatMbPerCtryConf(StatMbPerCtryConf statMbPerCtryConfPositive, StatMbPerCtryConf statMbPerCtryConfNegative) ;
        
    /**
     * Load a paginated list of StatMbPerCtryConf entity dependent of pagination criteria
     * @param PaginationCriteria paginationCriteria
     * @return List
     */
    public List<StatMbPerCtryConf> loadPaginatedStatMbPerCtryConf (StatMbPerCtryConf statMbPerCtryConf, PaginationCriteria paginationCriteria) ;
            
}
