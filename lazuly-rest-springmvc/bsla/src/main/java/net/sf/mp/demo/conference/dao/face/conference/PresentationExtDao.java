package net.sf.mp.demo.conference.dao.face.conference;

import net.sf.mp.demo.conference.domain.conference.Presentation;
import java.util.List;
import net.sf.minuteProject.architecture.filter.data.Criteria;
import net.sf.minuteProject.architecture.bsla.dao.face.DataAccessObject;

/**
 *
 * <p>Title: PresentationExtDao</p>
 *
 * <p>Description: Interface of a Data access object dealing with PresentationExtDao
 * persistence. It offers extended DAO functionalities</p>
 *
 */
public interface PresentationExtDao extends DataAccessObject {
     
     /**
     * Inserts a Presentation entity with cascade of its children
     * @param Presentation presentation
     */
    public void insertPresentationWithCascade(Presentation presentation) ;
    
    /**
     * Inserts a list of Presentation entity with cascade of its children
     * @param List<Presentation> presentations
     */
    public void insertPresentationsWithCascade(List<Presentation> presentations) ;        
   
    /**
     * lookup Presentation entity Presentation, criteria and max result number
     */
    public List<Presentation> lookupPresentation(Presentation presentation, Criteria criteria, Integer numberOfResult);
	
	public Integer updateNotNullOnlyPresentation (Presentation presentation, Criteria criteria);

	/**
	 * Affect the first presentation retrieved corresponding to the presentation criteria.
	 * Blank criteria are mapped to null.
	 * If no criteria is found, null is returned.
	 */
    public Presentation affectPresentation (Presentation presentation);
    
    public Presentation affectPresentationUseCache (Presentation presentation);
    	
	/**
	 * Assign the first presentation retrieved corresponding to the presentation criteria.
	 * Blank criteria are mapped to null.
	 * If no criteria is found, null is returned.
	 * If there is no presentation corresponding in the database. Then presentation is inserted and returned with its primary key(s). 
	 */
    public Presentation assignPresentation (Presentation presentation);
    
    public Presentation assignPresentationUseCache (Presentation presentation);
         
    /**
    * return the first Presentation entity found
    */           
    public Presentation getFirstPresentation (Presentation presentation);
    
    /**
    * checks if the Presentation entity exists
    */           
    public boolean existsPresentation (Presentation presentation);    
    
    public boolean existsPresentationWhereConditionsAre (Presentation presentation);

    /**
    * partial load enables to specify the fields you want to load explicitly
    */            
    public List<Presentation> partialLoadPresentation(Presentation presentation, Presentation positivePresentation, Presentation negativePresentation);

    /**
    * partial load with parent entities
    * variation (list, first, distinct decorator)
    * variation2 (with cache)
    */
    public List<Presentation> partialLoadWithParentPresentation(Presentation presentation, Presentation positivePresentation, Presentation negativePresentation);

    public List<Presentation> partialLoadWithParentPresentationUseCache(Presentation presentation, Presentation positivePresentation, Presentation negativePresentation, Boolean useCache);

    public List<Presentation> partialLoadWithParentPresentationUseCacheOnResult(Presentation presentation, Presentation positivePresentation, Presentation negativePresentation, Boolean useCache);

    /**
    * variation first
    */
    public Presentation partialLoadWithParentFirstPresentation(Presentation presentationWhat, Presentation positivePresentation, Presentation negativePresentation);
    
    public Presentation partialLoadWithParentFirstPresentationUseCache(Presentation presentationWhat, Presentation positivePresentation, Presentation negativePresentation, Boolean useCache);

    public Presentation partialLoadWithParentFirstPresentationUseCacheOnResult(Presentation presentationWhat, Presentation positivePresentation, Presentation negativePresentation, Boolean useCache);

    /**
    * variation distinct
    */
    public List<Presentation> getDistinctPresentation(Presentation presentationWhat, Presentation positivePresentation, Presentation negativePresentation);

    //
    public List partialLoadWithParentForBean(Object bean, Presentation presentation, Presentation positivePresentation, Presentation negativePresentation);

    /**
    * search on prototype with cache
    */
    public List<Presentation> searchPrototypeWithCachePresentation (Presentation presentation);
      
    
    /**
     * Searches a list of distinct Presentation entity based on a Presentation mask and a list of Presentation containing Presentation matching criteria
     * @param Presentation presentation
     * @param List<Presentation> presentations
     * @return List<Presentation>
     */
    public List<Presentation> searchDistinctPrototypePresentation(Presentation presentationMask, List<Presentation> presentations) ;    

	public List<Presentation> countDistinct (Presentation whatMask, Presentation whereEqCriteria);
	
	public Long count (Presentation whereEqCriteria);
	
	public List<Presentation> loadGraph(Presentation graphMaskWhat, List<Presentation> whereMask);  
	
	public List<Presentation> loadGraphFromParentKey (Presentation graphMaskWhat, List<Presentation> parents); 
	
    /**
     * generic to move after in superclass
     */
    public List<Object[]> getSQLQueryResult(String query);     

	
}

