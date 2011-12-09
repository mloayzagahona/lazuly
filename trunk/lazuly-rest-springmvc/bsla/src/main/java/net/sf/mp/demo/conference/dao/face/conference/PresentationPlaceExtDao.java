package net.sf.mp.demo.conference.dao.face.conference;

import net.sf.mp.demo.conference.domain.conference.PresentationPlace;
import java.util.List;
import net.sf.minuteProject.architecture.filter.data.Criteria;
import net.sf.minuteProject.architecture.bsla.dao.face.DataAccessObject;

/**
 *
 * <p>Title: PresentationPlaceExtDao</p>
 *
 * <p>Description: Interface of a Data access object dealing with PresentationPlaceExtDao
 * persistence. It offers extended DAO functionalities</p>
 *
 */
public interface PresentationPlaceExtDao extends DataAccessObject {
     
     /**
     * Inserts a PresentationPlace entity with cascade of its children
     * @param PresentationPlace presentationPlace
     */
    public void insertPresentationPlaceWithCascade(PresentationPlace presentationPlace) ;
    
    /**
     * Inserts a list of PresentationPlace entity with cascade of its children
     * @param List<PresentationPlace> presentationPlaces
     */
    public void insertPresentationPlacesWithCascade(List<PresentationPlace> presentationPlaces) ;        
   
    /**
     * lookup PresentationPlace entity PresentationPlace, criteria and max result number
     */
    public List<PresentationPlace> lookupPresentationPlace(PresentationPlace presentationPlace, Criteria criteria, Integer numberOfResult);
	
	public Integer updateNotNullOnlyPresentationPlace (PresentationPlace presentationPlace, Criteria criteria);

	/**
	 * Affect the first presentationPlace retrieved corresponding to the presentationPlace criteria.
	 * Blank criteria are mapped to null.
	 * If no criteria is found, null is returned.
	 */
    public PresentationPlace affectPresentationPlace (PresentationPlace presentationPlace);
    
    public PresentationPlace affectPresentationPlaceUseCache (PresentationPlace presentationPlace);
    	
	/**
	 * Assign the first presentationPlace retrieved corresponding to the presentationPlace criteria.
	 * Blank criteria are mapped to null.
	 * If no criteria is found, null is returned.
	 * If there is no presentationPlace corresponding in the database. Then presentationPlace is inserted and returned with its primary key(s). 
	 */
    public PresentationPlace assignPresentationPlace (PresentationPlace presentationPlace);
    
    public PresentationPlace assignPresentationPlaceUseCache (PresentationPlace presentationPlace);
         
    /**
    * return the first PresentationPlace entity found
    */           
    public PresentationPlace getFirstPresentationPlace (PresentationPlace presentationPlace);
    
    /**
    * checks if the PresentationPlace entity exists
    */           
    public boolean existsPresentationPlace (PresentationPlace presentationPlace);    
    
    public boolean existsPresentationPlaceWhereConditionsAre (PresentationPlace presentationPlace);

    /**
    * partial load enables to specify the fields you want to load explicitly
    */            
    public List<PresentationPlace> partialLoadPresentationPlace(PresentationPlace presentationPlace, PresentationPlace positivePresentationPlace, PresentationPlace negativePresentationPlace);

    /**
    * partial load with parent entities
    * variation (list, first, distinct decorator)
    * variation2 (with cache)
    */
    public List<PresentationPlace> partialLoadWithParentPresentationPlace(PresentationPlace presentationPlace, PresentationPlace positivePresentationPlace, PresentationPlace negativePresentationPlace);

    public List<PresentationPlace> partialLoadWithParentPresentationPlaceUseCache(PresentationPlace presentationPlace, PresentationPlace positivePresentationPlace, PresentationPlace negativePresentationPlace, Boolean useCache);

    public List<PresentationPlace> partialLoadWithParentPresentationPlaceUseCacheOnResult(PresentationPlace presentationPlace, PresentationPlace positivePresentationPlace, PresentationPlace negativePresentationPlace, Boolean useCache);

    /**
    * variation first
    */
    public PresentationPlace partialLoadWithParentFirstPresentationPlace(PresentationPlace presentationPlaceWhat, PresentationPlace positivePresentationPlace, PresentationPlace negativePresentationPlace);
    
    public PresentationPlace partialLoadWithParentFirstPresentationPlaceUseCache(PresentationPlace presentationPlaceWhat, PresentationPlace positivePresentationPlace, PresentationPlace negativePresentationPlace, Boolean useCache);

    public PresentationPlace partialLoadWithParentFirstPresentationPlaceUseCacheOnResult(PresentationPlace presentationPlaceWhat, PresentationPlace positivePresentationPlace, PresentationPlace negativePresentationPlace, Boolean useCache);

    /**
    * variation distinct
    */
    public List<PresentationPlace> getDistinctPresentationPlace(PresentationPlace presentationPlaceWhat, PresentationPlace positivePresentationPlace, PresentationPlace negativePresentationPlace);

    //
    public List partialLoadWithParentForBean(Object bean, PresentationPlace presentationPlace, PresentationPlace positivePresentationPlace, PresentationPlace negativePresentationPlace);

    /**
    * search on prototype with cache
    */
    public List<PresentationPlace> searchPrototypeWithCachePresentationPlace (PresentationPlace presentationPlace);
      
    
    /**
     * Searches a list of distinct PresentationPlace entity based on a PresentationPlace mask and a list of PresentationPlace containing PresentationPlace matching criteria
     * @param PresentationPlace presentationPlace
     * @param List<PresentationPlace> presentationPlaces
     * @return List<PresentationPlace>
     */
    public List<PresentationPlace> searchDistinctPrototypePresentationPlace(PresentationPlace presentationPlaceMask, List<PresentationPlace> presentationPlaces) ;    

	public List<PresentationPlace> countDistinct (PresentationPlace whatMask, PresentationPlace whereEqCriteria);
	
	public Long count (PresentationPlace whereEqCriteria);
	
	public List<PresentationPlace> loadGraph(PresentationPlace graphMaskWhat, List<PresentationPlace> whereMask);  
	
	public List<PresentationPlace> loadGraphFromParentKey (PresentationPlace graphMaskWhat, List<PresentationPlace> parents); 
	
    /**
     * generic to move after in superclass
     */
    public List<Object[]> getSQLQueryResult(String query);     

	
}

