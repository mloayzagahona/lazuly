package net.sf.mp.demo.conference.dao.face.conference;

import net.sf.mp.demo.conference.domain.conference.ConferenceFeedback;
import java.util.List;
import net.sf.minuteProject.architecture.bsla.bean.criteria.PaginationCriteria;
import net.sf.minuteProject.architecture.bsla.dao.face.DataAccessObject;


/**
 *
 * <p>Title: ConferenceFeedbackDao</p>
 *
 * <p>Description: Interface of a Data access object dealing with ConferenceFeedbackDao
 * persistence. It offers a set of methods which allow for saving,
 * deleting and searching conferenceFeedback objects</p>
 *
 */
public interface ConferenceFeedbackDao extends DataAccessObject {

    /**
     * Inserts a ConferenceFeedback entity 
     * @param ConferenceFeedback conferenceFeedback
     */
    public void insertConferenceFeedback(ConferenceFeedback conferenceFeedback) ;
 
    /**
     * Inserts a list of ConferenceFeedback entity 
     * @param List<ConferenceFeedback> conferenceFeedbacks
     */
    public void insertConferenceFeedbacks(List<ConferenceFeedback> conferenceFeedbacks) ;
        
    /**
     * Updates a ConferenceFeedback entity 
     * @param ConferenceFeedback conferenceFeedback
     */
    public ConferenceFeedback updateConferenceFeedback(ConferenceFeedback conferenceFeedback) ;

	 /**
     * Updates a ConferenceFeedback entity with only the attributes set into ConferenceFeedback.
	 * The primary keys are to be set for this method to operate.
	 * This is a performance friendly feature, which remove the udibiquous full load and full update when an
	 * update is to be done
   * Remark: The primary keys cannot be update by this methods, nor are the attributes that must be set to null.
   * @param ConferenceFeedback conferenceFeedback
   */
    public int updateNotNullOnlyConferenceFeedback(ConferenceFeedback conferenceFeedback) ;
	 
	public int updateNotNullOnlyPrototypeConferenceFeedback(ConferenceFeedback conferenceFeedback, ConferenceFeedback prototypeCriteria);
	
     /**
     * Saves a ConferenceFeedback entity 
     * @param ConferenceFeedback conferenceFeedback
     */
    public void saveConferenceFeedback(ConferenceFeedback conferenceFeedback);
    
    /**
     * Deletes a ConferenceFeedback entity 
     * @param ConferenceFeedback conferenceFeedback
     */
    public void deleteConferenceFeedback(ConferenceFeedback conferenceFeedback) ;
 
    /**
     * Loads the ConferenceFeedback entity which is related to an instance of
     * ConferenceFeedback
     * @param Long id
     * @return ConferenceFeedback The ConferenceFeedback entity
     
    public ConferenceFeedback loadConferenceFeedback(Long id);
*/
    /**
     * Loads the ConferenceFeedback entity which is related to an instance of
     * ConferenceFeedback
     * @param java.lang.Integer Id
     * @return ConferenceFeedback The ConferenceFeedback entity
     */
    public ConferenceFeedback loadConferenceFeedback(java.lang.Integer id);    

    /**
     * Loads a list of ConferenceFeedback entity 
     * @param List<java.lang.Integer> ids
     * @return List<ConferenceFeedback> The ConferenceFeedback entity
     */
    public List<ConferenceFeedback> loadConferenceFeedbackListByConferenceFeedback (List<ConferenceFeedback> conferenceFeedbacks);
    
    /**
     * Loads a list of ConferenceFeedback entity 
     * @param List<java.lang.Integer> ids
     * @return List<ConferenceFeedback> The ConferenceFeedback entity
     */
    public List<ConferenceFeedback> loadConferenceFeedbackListById(List<java.lang.Integer> ids);
    
    /**
     * Loads the ConferenceFeedback entity which is related to an instance of
     * ConferenceFeedback and its dependent one to many objects
     * @param Long id
     * @return ConferenceFeedback The ConferenceFeedback entity
     */
    public ConferenceFeedback loadFullFirstLevelConferenceFeedback(java.lang.Integer id);
    
    /**
     * Loads the ConferenceFeedback entity which is related to an instance of
     * ConferenceFeedback
     * @param ConferenceFeedback conferenceFeedback
     * @return ConferenceFeedback The ConferenceFeedback entity
     */
    public ConferenceFeedback loadFullFirstLevelConferenceFeedback(ConferenceFeedback conferenceFeedback);    
    
    
    /**
     * Loads the ConferenceFeedback entity which is related to an instance of
     * ConferenceFeedback and its dependent objects one to many
     * @param Long id
     * @return ConferenceFeedback The ConferenceFeedback entity
     */
    public ConferenceFeedback loadFullConferenceFeedback(Long id) ;

    /**
     * Searches a list of ConferenceFeedback entity based on a ConferenceFeedback containing ConferenceFeedback matching criteria
     * @param ConferenceFeedback conferenceFeedback
     * @return List<ConferenceFeedback>
     */
    public List<ConferenceFeedback> searchPrototypeConferenceFeedback(ConferenceFeedback conferenceFeedback) ;
    
    /**
     * Searches a list of ConferenceFeedback entity based on a list of ConferenceFeedback containing ConferenceFeedback matching criteria
     * @param List<ConferenceFeedback> conferenceFeedbacks
     * @return List<ConferenceFeedback>
     */
    public List<ConferenceFeedback> searchPrototypeConferenceFeedback(List<ConferenceFeedback> conferenceFeedbacks) ;    
    	
	/**
     * Searches a list of ConferenceFeedback entity 
     * @param ConferenceFeedback conferenceFeedback
     * @return List
     */
    public List<ConferenceFeedback> searchPrototypeConferenceFeedback(ConferenceFeedback conferenceFeedbackPositive, ConferenceFeedback conferenceFeedbackNegative) ;
        
    /**
     * Load a paginated list of ConferenceFeedback entity dependent of pagination criteria
     * @param PaginationCriteria paginationCriteria
     * @return List
     */
    public List<ConferenceFeedback> loadPaginatedConferenceFeedback (ConferenceFeedback conferenceFeedback, PaginationCriteria paginationCriteria) ;
            
}
