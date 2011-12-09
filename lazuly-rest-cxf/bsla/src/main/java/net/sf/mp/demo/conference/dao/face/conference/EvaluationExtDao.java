package net.sf.mp.demo.conference.dao.face.conference;

import net.sf.mp.demo.conference.domain.conference.Evaluation;
import java.util.List;
import net.sf.minuteProject.architecture.filter.data.Criteria;
import net.sf.minuteProject.architecture.bsla.dao.face.DataAccessObject;

/**
 *
 * <p>Title: EvaluationExtDao</p>
 *
 * <p>Description: Interface of a Data access object dealing with EvaluationExtDao
 * persistence. It offers extended DAO functionalities</p>
 *
 */
public interface EvaluationExtDao extends DataAccessObject {
     
     /**
     * Inserts a Evaluation entity with cascade of its children
     * @param Evaluation evaluation
     */
    public void insertEvaluationWithCascade(Evaluation evaluation) ;
    
    /**
     * Inserts a list of Evaluation entity with cascade of its children
     * @param List<Evaluation> evaluations
     */
    public void insertEvaluationsWithCascade(List<Evaluation> evaluations) ;        
   
    /**
     * lookup Evaluation entity Evaluation, criteria and max result number
     */
    public List<Evaluation> lookupEvaluation(Evaluation evaluation, Criteria criteria, Integer numberOfResult);
	
	public Integer updateNotNullOnlyEvaluation (Evaluation evaluation, Criteria criteria);

	/**
	 * Affect the first evaluation retrieved corresponding to the evaluation criteria.
	 * Blank criteria are mapped to null.
	 * If no criteria is found, null is returned.
	 */
    public Evaluation affectEvaluation (Evaluation evaluation);
    
    public Evaluation affectEvaluationUseCache (Evaluation evaluation);
    	
	/**
	 * Assign the first evaluation retrieved corresponding to the evaluation criteria.
	 * Blank criteria are mapped to null.
	 * If no criteria is found, null is returned.
	 * If there is no evaluation corresponding in the database. Then evaluation is inserted and returned with its primary key(s). 
	 */
    public Evaluation assignEvaluation (Evaluation evaluation);
    
    public Evaluation assignEvaluationUseCache (Evaluation evaluation);
         
    /**
    * return the first Evaluation entity found
    */           
    public Evaluation getFirstEvaluation (Evaluation evaluation);
    
    /**
    * checks if the Evaluation entity exists
    */           
    public boolean existsEvaluation (Evaluation evaluation);    
    
    public boolean existsEvaluationWhereConditionsAre (Evaluation evaluation);

    /**
    * partial load enables to specify the fields you want to load explicitly
    */            
    public List<Evaluation> partialLoadEvaluation(Evaluation evaluation, Evaluation positiveEvaluation, Evaluation negativeEvaluation);

    /**
    * partial load with parent entities
    * variation (list, first, distinct decorator)
    * variation2 (with cache)
    */
    public List<Evaluation> partialLoadWithParentEvaluation(Evaluation evaluation, Evaluation positiveEvaluation, Evaluation negativeEvaluation);

    public List<Evaluation> partialLoadWithParentEvaluationUseCache(Evaluation evaluation, Evaluation positiveEvaluation, Evaluation negativeEvaluation, Boolean useCache);

    public List<Evaluation> partialLoadWithParentEvaluationUseCacheOnResult(Evaluation evaluation, Evaluation positiveEvaluation, Evaluation negativeEvaluation, Boolean useCache);

    /**
    * variation first
    */
    public Evaluation partialLoadWithParentFirstEvaluation(Evaluation evaluationWhat, Evaluation positiveEvaluation, Evaluation negativeEvaluation);
    
    public Evaluation partialLoadWithParentFirstEvaluationUseCache(Evaluation evaluationWhat, Evaluation positiveEvaluation, Evaluation negativeEvaluation, Boolean useCache);

    public Evaluation partialLoadWithParentFirstEvaluationUseCacheOnResult(Evaluation evaluationWhat, Evaluation positiveEvaluation, Evaluation negativeEvaluation, Boolean useCache);

    /**
    * variation distinct
    */
    public List<Evaluation> getDistinctEvaluation(Evaluation evaluationWhat, Evaluation positiveEvaluation, Evaluation negativeEvaluation);

    //
    public List partialLoadWithParentForBean(Object bean, Evaluation evaluation, Evaluation positiveEvaluation, Evaluation negativeEvaluation);

    /**
    * search on prototype with cache
    */
    public List<Evaluation> searchPrototypeWithCacheEvaluation (Evaluation evaluation);
      
    
    /**
     * Searches a list of distinct Evaluation entity based on a Evaluation mask and a list of Evaluation containing Evaluation matching criteria
     * @param Evaluation evaluation
     * @param List<Evaluation> evaluations
     * @return List<Evaluation>
     */
    public List<Evaluation> searchDistinctPrototypeEvaluation(Evaluation evaluationMask, List<Evaluation> evaluations) ;    

	public List<Evaluation> countDistinct (Evaluation whatMask, Evaluation whereEqCriteria);
	
	public Long count (Evaluation whereEqCriteria);
	
	public List<Evaluation> loadGraph(Evaluation graphMaskWhat, List<Evaluation> whereMask);  
	
	public List<Evaluation> loadGraphFromParentKey (Evaluation graphMaskWhat, List<Evaluation> parents); 
	
    /**
     * generic to move after in superclass
     */
    public List<Object[]> getSQLQueryResult(String query);     

	
}

