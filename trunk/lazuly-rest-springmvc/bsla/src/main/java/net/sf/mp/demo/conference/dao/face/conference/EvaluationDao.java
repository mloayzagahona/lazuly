package net.sf.mp.demo.conference.dao.face.conference;

import net.sf.mp.demo.conference.domain.conference.Evaluation;
import java.util.List;
import net.sf.minuteProject.architecture.bsla.bean.criteria.PaginationCriteria;
import net.sf.minuteProject.architecture.bsla.dao.face.DataAccessObject;


/**
 *
 * <p>Title: EvaluationDao</p>
 *
 * <p>Description: Interface of a Data access object dealing with EvaluationDao
 * persistence. It offers a set of methods which allow for saving,
 * deleting and searching evaluation objects</p>
 *
 */
public interface EvaluationDao extends DataAccessObject {

    /**
     * Inserts a Evaluation entity 
     * @param Evaluation evaluation
     */
    public void insertEvaluation(Evaluation evaluation) ;
 
    /**
     * Inserts a list of Evaluation entity 
     * @param List<Evaluation> evaluations
     */
    public void insertEvaluations(List<Evaluation> evaluations) ;
        
    /**
     * Updates a Evaluation entity 
     * @param Evaluation evaluation
     */
    public Evaluation updateEvaluation(Evaluation evaluation) ;

	 /**
     * Updates a Evaluation entity with only the attributes set into Evaluation.
	 * The primary keys are to be set for this method to operate.
	 * This is a performance friendly feature, which remove the udibiquous full load and full update when an
	 * update is to be done
   * Remark: The primary keys cannot be update by this methods, nor are the attributes that must be set to null.
   * @param Evaluation evaluation
   */
    public int updateNotNullOnlyEvaluation(Evaluation evaluation) ;
	 
	public int updateNotNullOnlyPrototypeEvaluation(Evaluation evaluation, Evaluation prototypeCriteria);
	
     /**
     * Saves a Evaluation entity 
     * @param Evaluation evaluation
     */
    public void saveEvaluation(Evaluation evaluation);
    
    /**
     * Deletes a Evaluation entity 
     * @param Evaluation evaluation
     */
    public void deleteEvaluation(Evaluation evaluation) ;
 
    /**
     * Loads the Evaluation entity which is related to an instance of
     * Evaluation
     * @param Long id
     * @return Evaluation The Evaluation entity
     
    public Evaluation loadEvaluation(Long id);
*/
    /**
     * Loads the Evaluation entity which is related to an instance of
     * Evaluation
     * @param java.lang.Long Id
     * @return Evaluation The Evaluation entity
     */
    public Evaluation loadEvaluation(java.lang.Long id);    

    /**
     * Loads a list of Evaluation entity 
     * @param List<java.lang.Long> ids
     * @return List<Evaluation> The Evaluation entity
     */
    public List<Evaluation> loadEvaluationListByEvaluation (List<Evaluation> evaluations);
    
    /**
     * Loads a list of Evaluation entity 
     * @param List<java.lang.Long> ids
     * @return List<Evaluation> The Evaluation entity
     */
    public List<Evaluation> loadEvaluationListById(List<java.lang.Long> ids);
    
    /**
     * Loads the Evaluation entity which is related to an instance of
     * Evaluation and its dependent one to many objects
     * @param Long id
     * @return Evaluation The Evaluation entity
     */
    public Evaluation loadFullFirstLevelEvaluation(java.lang.Long id);
    
    /**
     * Loads the Evaluation entity which is related to an instance of
     * Evaluation
     * @param Evaluation evaluation
     * @return Evaluation The Evaluation entity
     */
    public Evaluation loadFullFirstLevelEvaluation(Evaluation evaluation);    
    
    
    /**
     * Loads the Evaluation entity which is related to an instance of
     * Evaluation and its dependent objects one to many
     * @param Long id
     * @return Evaluation The Evaluation entity
     */
    public Evaluation loadFullEvaluation(Long id) ;

    /**
     * Searches a list of Evaluation entity based on a Evaluation containing Evaluation matching criteria
     * @param Evaluation evaluation
     * @return List<Evaluation>
     */
    public List<Evaluation> searchPrototypeEvaluation(Evaluation evaluation) ;
    
    /**
     * Searches a list of Evaluation entity based on a list of Evaluation containing Evaluation matching criteria
     * @param List<Evaluation> evaluations
     * @return List<Evaluation>
     */
    public List<Evaluation> searchPrototypeEvaluation(List<Evaluation> evaluations) ;    
    	
	/**
     * Searches a list of Evaluation entity 
     * @param Evaluation evaluation
     * @return List
     */
    public List<Evaluation> searchPrototypeEvaluation(Evaluation evaluationPositive, Evaluation evaluationNegative) ;
        
    /**
     * Load a paginated list of Evaluation entity dependent of pagination criteria
     * @param PaginationCriteria paginationCriteria
     * @return List
     */
    public List<Evaluation> loadPaginatedEvaluation (Evaluation evaluation, PaginationCriteria paginationCriteria) ;
            
}
