package net.sf.mp.demo.conference.dao.face.conference;

import net.sf.mp.demo.conference.domain.conference.PresentationPlace;
import java.util.List;
import net.sf.minuteProject.architecture.bsla.bean.criteria.PaginationCriteria;
import net.sf.minuteProject.architecture.bsla.dao.face.DataAccessObject;


/**
 *
 * <p>Title: PresentationPlaceDao</p>
 *
 * <p>Description: Interface of a Data access object dealing with PresentationPlaceDao
 * persistence. It offers a set of methods which allow for saving,
 * deleting and searching presentationPlace objects</p>
 *
 */
public interface PresentationPlaceDao extends DataAccessObject {

    /**
     * Inserts a PresentationPlace entity 
     * @param PresentationPlace presentationPlace
     */
    public void insertPresentationPlace(PresentationPlace presentationPlace) ;
 
    /**
     * Inserts a list of PresentationPlace entity 
     * @param List<PresentationPlace> presentationPlaces
     */
    public void insertPresentationPlaces(List<PresentationPlace> presentationPlaces) ;
        
    /**
     * Updates a PresentationPlace entity 
     * @param PresentationPlace presentationPlace
     */
    public PresentationPlace updatePresentationPlace(PresentationPlace presentationPlace) ;

	 /**
     * Updates a PresentationPlace entity with only the attributes set into PresentationPlace.
	 * The primary keys are to be set for this method to operate.
	 * This is a performance friendly feature, which remove the udibiquous full load and full update when an
	 * update is to be done
   * Remark: The primary keys cannot be update by this methods, nor are the attributes that must be set to null.
   * @param PresentationPlace presentationPlace
   */
    public int updateNotNullOnlyPresentationPlace(PresentationPlace presentationPlace) ;
	 
	public int updateNotNullOnlyPrototypePresentationPlace(PresentationPlace presentationPlace, PresentationPlace prototypeCriteria);
	
     /**
     * Saves a PresentationPlace entity 
     * @param PresentationPlace presentationPlace
     */
    public void savePresentationPlace(PresentationPlace presentationPlace);
    
    /**
     * Deletes a PresentationPlace entity 
     * @param PresentationPlace presentationPlace
     */
    public void deletePresentationPlace(PresentationPlace presentationPlace) ;
 
    /**
     * Loads the PresentationPlace entity which is related to an instance of
     * PresentationPlace
     * @param Long id
     * @return PresentationPlace The PresentationPlace entity
     
    public PresentationPlace loadPresentationPlace(Long id);
*/
    /**
     * Loads the PresentationPlace entity which is related to an instance of
     * PresentationPlace
     * @param java.lang.Long Id
     * @return PresentationPlace The PresentationPlace entity
     */
    public PresentationPlace loadPresentationPlace(java.lang.Long id);    

    /**
     * Loads a list of PresentationPlace entity 
     * @param List<java.lang.Long> ids
     * @return List<PresentationPlace> The PresentationPlace entity
     */
    public List<PresentationPlace> loadPresentationPlaceListByPresentationPlace (List<PresentationPlace> presentationPlaces);
    
    /**
     * Loads a list of PresentationPlace entity 
     * @param List<java.lang.Long> ids
     * @return List<PresentationPlace> The PresentationPlace entity
     */
    public List<PresentationPlace> loadPresentationPlaceListById(List<java.lang.Long> ids);
    
    /**
     * Loads the PresentationPlace entity which is related to an instance of
     * PresentationPlace and its dependent one to many objects
     * @param Long id
     * @return PresentationPlace The PresentationPlace entity
     */
    public PresentationPlace loadFullFirstLevelPresentationPlace(java.lang.Long id);
    
    /**
     * Loads the PresentationPlace entity which is related to an instance of
     * PresentationPlace
     * @param PresentationPlace presentationPlace
     * @return PresentationPlace The PresentationPlace entity
     */
    public PresentationPlace loadFullFirstLevelPresentationPlace(PresentationPlace presentationPlace);    
    
    
    /**
     * Loads the PresentationPlace entity which is related to an instance of
     * PresentationPlace and its dependent objects one to many
     * @param Long id
     * @return PresentationPlace The PresentationPlace entity
     */
    public PresentationPlace loadFullPresentationPlace(Long id) ;

    /**
     * Searches a list of PresentationPlace entity based on a PresentationPlace containing PresentationPlace matching criteria
     * @param PresentationPlace presentationPlace
     * @return List<PresentationPlace>
     */
    public List<PresentationPlace> searchPrototypePresentationPlace(PresentationPlace presentationPlace) ;
    
    /**
     * Searches a list of PresentationPlace entity based on a list of PresentationPlace containing PresentationPlace matching criteria
     * @param List<PresentationPlace> presentationPlaces
     * @return List<PresentationPlace>
     */
    public List<PresentationPlace> searchPrototypePresentationPlace(List<PresentationPlace> presentationPlaces) ;    
    	
	/**
     * Searches a list of PresentationPlace entity 
     * @param PresentationPlace presentationPlace
     * @return List
     */
    public List<PresentationPlace> searchPrototypePresentationPlace(PresentationPlace presentationPlacePositive, PresentationPlace presentationPlaceNegative) ;
        
    /**
     * Load a paginated list of PresentationPlace entity dependent of pagination criteria
     * @param PaginationCriteria paginationCriteria
     * @return List
     */
    public List<PresentationPlace> loadPaginatedPresentationPlace (PresentationPlace presentationPlace, PaginationCriteria paginationCriteria) ;
            
}
