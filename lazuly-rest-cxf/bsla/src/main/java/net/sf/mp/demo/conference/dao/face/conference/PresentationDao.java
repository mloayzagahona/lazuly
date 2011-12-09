package net.sf.mp.demo.conference.dao.face.conference;

import net.sf.mp.demo.conference.domain.conference.Presentation;
import java.util.List;
import net.sf.minuteProject.architecture.bsla.bean.criteria.PaginationCriteria;
import net.sf.minuteProject.architecture.bsla.dao.face.DataAccessObject;


/**
 *
 * <p>Title: PresentationDao</p>
 *
 * <p>Description: Interface of a Data access object dealing with PresentationDao
 * persistence. It offers a set of methods which allow for saving,
 * deleting and searching presentation objects</p>
 *
 */
public interface PresentationDao extends DataAccessObject {

    /**
     * Inserts a Presentation entity 
     * @param Presentation presentation
     */
    public void insertPresentation(Presentation presentation) ;
 
    /**
     * Inserts a list of Presentation entity 
     * @param List<Presentation> presentations
     */
    public void insertPresentations(List<Presentation> presentations) ;
        
    /**
     * Updates a Presentation entity 
     * @param Presentation presentation
     */
    public Presentation updatePresentation(Presentation presentation) ;

	 /**
     * Updates a Presentation entity with only the attributes set into Presentation.
	 * The primary keys are to be set for this method to operate.
	 * This is a performance friendly feature, which remove the udibiquous full load and full update when an
	 * update is to be done
   * Remark: The primary keys cannot be update by this methods, nor are the attributes that must be set to null.
   * @param Presentation presentation
   */
    public int updateNotNullOnlyPresentation(Presentation presentation) ;
	 
	public int updateNotNullOnlyPrototypePresentation(Presentation presentation, Presentation prototypeCriteria);
	
     /**
     * Saves a Presentation entity 
     * @param Presentation presentation
     */
    public void savePresentation(Presentation presentation);
    
    /**
     * Deletes a Presentation entity 
     * @param Presentation presentation
     */
    public void deletePresentation(Presentation presentation) ;
 
    /**
     * Loads the Presentation entity which is related to an instance of
     * Presentation
     * @param Long id
     * @return Presentation The Presentation entity
     
    public Presentation loadPresentation(Long id);
*/
    /**
     * Loads the Presentation entity which is related to an instance of
     * Presentation
     * @param java.lang.Long Id
     * @return Presentation The Presentation entity
     */
    public Presentation loadPresentation(java.lang.Long id);    

    /**
     * Loads a list of Presentation entity 
     * @param List<java.lang.Long> ids
     * @return List<Presentation> The Presentation entity
     */
    public List<Presentation> loadPresentationListByPresentation (List<Presentation> presentations);
    
    /**
     * Loads a list of Presentation entity 
     * @param List<java.lang.Long> ids
     * @return List<Presentation> The Presentation entity
     */
    public List<Presentation> loadPresentationListById(List<java.lang.Long> ids);
    
    /**
     * Loads the Presentation entity which is related to an instance of
     * Presentation and its dependent one to many objects
     * @param Long id
     * @return Presentation The Presentation entity
     */
    public Presentation loadFullFirstLevelPresentation(java.lang.Long id);
    
    /**
     * Loads the Presentation entity which is related to an instance of
     * Presentation
     * @param Presentation presentation
     * @return Presentation The Presentation entity
     */
    public Presentation loadFullFirstLevelPresentation(Presentation presentation);    
    
    
    /**
     * Loads the Presentation entity which is related to an instance of
     * Presentation and its dependent objects one to many
     * @param Long id
     * @return Presentation The Presentation entity
     */
    public Presentation loadFullPresentation(Long id) ;

    /**
     * Searches a list of Presentation entity based on a Presentation containing Presentation matching criteria
     * @param Presentation presentation
     * @return List<Presentation>
     */
    public List<Presentation> searchPrototypePresentation(Presentation presentation) ;
    
    /**
     * Searches a list of Presentation entity based on a list of Presentation containing Presentation matching criteria
     * @param List<Presentation> presentations
     * @return List<Presentation>
     */
    public List<Presentation> searchPrototypePresentation(List<Presentation> presentations) ;    
    	
	/**
     * Searches a list of Presentation entity 
     * @param Presentation presentation
     * @return List
     */
    public List<Presentation> searchPrototypePresentation(Presentation presentationPositive, Presentation presentationNegative) ;
        
    /**
     * Load a paginated list of Presentation entity dependent of pagination criteria
     * @param PaginationCriteria paginationCriteria
     * @return List
     */
    public List<Presentation> loadPaginatedPresentation (Presentation presentation, PaginationCriteria paginationCriteria) ;
            
}
