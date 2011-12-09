package net.sf.mp.demo.conference.dao.impl.jpa.admin;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Date;
import java.sql.Timestamp;

import javax.persistence.Query;
import javax.persistence.EntityManager;

import org.springframework.orm.jpa.EntityManagerFactoryUtils;
import org.springframework.orm.jpa.support.JpaDaoSupport;
import org.springframework.transaction.annotation.Transactional;

import net.sf.minuteProject.architecture.bsla.bean.criteria.PaginationCriteria;
import net.sf.mp.demo.conference.dao.face.admin.CountryDao;
import net.sf.mp.demo.conference.domain.admin.Country;

/**
 *
 * <p>Title: CountryJPAImpl</p>
 *
 * <p>Description: Interface of a Data access object dealing with CountryJPAImpl
 * persistence. It offers a set of methods which allow for saving,
 * deleting and searching CountryJPAImpl objects</p>
 *
 */

public class CountryJPAImpl extends JpaDaoSupport implements CountryDao {

	public CountryJPAImpl () {}

    /**
     * Inserts a Country entity 
     * @param Country country
     */
    public void insertCountry(Country country) {
       convertTransientReferenceToNull (country);
       getJpaTemplate().persist(country);
    }

    protected void insertCountry(EntityManager emForRecursiveDao, Country country) {
       emForRecursiveDao.persist(country);
    } 
    /**
     * Inserts a list of Country entity 
     * @param List<Country> countrys
     */
    public void insertCountrys(List<Country> countrys) {
    	//TODO
    }
    /**
     * Updates a Country entity 
     * @param Country country
     */
    public Country updateCountry(Country country) {
       convertTransientReferenceToNull (country);
       return getJpaTemplate().merge(country);
    }

	/**
     * Updates a Country entity with only the attributes set into Country.
	 * The primary keys are to be set for this method to operate.
	 * This is a performance friendly feature, which remove the udibiquous full load and full update when an
	 * update is issued
     * Remark: The primary keys cannot be update by this methods, nor are the attributes that must be set to null.
     * @param Country country
    */ 
    @Transactional
    public int updateNotNullOnlyCountry(Country country) {
        Query jpaQuery = getEntityManager().createQuery(getUpdateNotNullOnlyCountryQueryChunk(country));
        if (country.getId() != null) {
           jpaQuery.setParameter ("id", country.getId());
        }   
        if (country.getName() != null) {
           jpaQuery.setParameter ("name", country.getName());
        }   
        if (country.getIsoName() != null) {
           jpaQuery.setParameter ("isoName", country.getIsoName());
        }   
		return jpaQuery.executeUpdate();
    }

    protected String getUpdateNotNullOnlyCountryQueryChunkPrototype (Country country) {
        boolean isWhereSet = false;
        StringBuffer query = new StringBuffer();
        query.append (" update Country country ");
        if (country.getName() != null) {
           query.append (getQueryCommaSet (isWhereSet));
           isWhereSet = true; 
           query.append(" country.name = :name");
        }
        if (country.getIsoName() != null) {
           query.append (getQueryCommaSet (isWhereSet));
           isWhereSet = true; 
           query.append(" country.isoName = :isoName");
        }
        return query.toString();
    }
    
    protected String getUpdateNotNullOnlyCountryQueryChunk (Country country) {
        boolean isWhereSet = false;
        StringBuffer query = new StringBuffer(getUpdateNotNullOnlyCountryQueryChunkPrototype(country));
        if (country.getId() != null) {
           query.append (getQueryWHERE_AND (isWhereSet));
           isWhereSet = true;
			     query.append(" country.id = :id");
        }
        return query.toString();
    }
    
                
	protected Country assignBlankToNull (Country country) {
	    if (country==null)
			return null;
        if (country.getName()!=null && country.getName().equals(""))
           country.setName((String)null);
        if (country.getIsoName()!=null && country.getIsoName().equals(""))
           country.setIsoName((String)null);
		return country;
	}
	
	protected boolean isAllNull (Country country) {
	    if (country==null)
			return true;
        if (country.getId()!=null) 
            return false;
        if (country.getName()!=null) 
            return false;
        if (country.getIsoName()!=null) 
            return false;
		return true;
	}
		
	@Transactional
    public int updateNotNullOnlyPrototypeCountry(Country country, Country prototypeCriteria) {
        boolean isWhereSet = false;
        StringBuffer query = new StringBuffer();
        query.append (" update Country country ");
        if (country.getId() != null) {
           query.append (getQueryCommaSet (isWhereSet));
           isWhereSet = true; 
           query.append(" country.id = "+ country.getId() + " ");
        }
        if (country.getName() != null) {
           query.append (getQueryCommaSet (isWhereSet));
           isWhereSet = true; 
           query.append(" country.name = '"+ country.getName()+"' ");
        }
        if (country.getIsoName() != null) {
           query.append (getQueryCommaSet (isWhereSet));
           isWhereSet = true; 
           query.append(" country.isoName = '"+ country.getIsoName()+"' ");
        }
		isWhereSet = false; 
        if (prototypeCriteria.getId() != null) {
           query.append (getQueryWHERE_AND (isWhereSet));
           isWhereSet = true;
           query.append(" country.id = "+ prototypeCriteria.getId() + " ");
        }
        if (prototypeCriteria.getName() != null) {
           query.append (getQueryWHERE_AND (isWhereSet));
           isWhereSet = true;
           query.append(" country.name = '"+ prototypeCriteria.getName()+"' ");
        }
        if (prototypeCriteria.getIsoName() != null) {
           query.append (getQueryWHERE_AND (isWhereSet));
           isWhereSet = true;
           query.append(" country.isoName = '"+ prototypeCriteria.getIsoName()+"' ");
        }
        Query jpaQuery = getEntityManager().createQuery(query.toString());
		return jpaQuery.executeUpdate();
    }
     
     /**
     * Saves a Country entity 
     * @param Country country
     */
    public void saveCountry(Country country) {
       //getJpaTemplate().persist(country);
       convertTransientReferenceToNull (country);
       if (getJpaTemplate().contains(country)) {
          getJpaTemplate().merge(country);
       } else {
          getJpaTemplate().persist(country);
       }
       getJpaTemplate().flush(); 
    }
       
    /**
     * Deletes a Country entity 
     * @param Country country
     */
    public void deleteCountry(Country country) {
      getJpaTemplate().remove(country);
    }
    
    /**
     * Loads the Country entity which is related to an instance of
     * Country
     * @param Long id
     * @return Country The Country entity
     
    public Country loadCountry(Long id) {
    	return (Country)getJpaTemplate().get(Country.class, id);
    }
*/
  
    /**
     * Loads the Country entity which is related to an instance of
     * Country
     * @param java.lang.Integer Id
     * @return Country The Country entity
     */
    public Country loadCountry(java.lang.Integer id) {
    	return (Country)getJpaTemplate().find(Country.class, id);
    }
    
    /**
     * Loads a list of Country entity 
     * @param List<java.lang.Integer> ids
     * @return List<Country> The Country entity
     */
    public List<Country> loadCountryListByCountry (List<Country> countrys) {
       return null;
    }
    
    /**
     * Loads a list of Country entity 
     * @param List<java.lang.Integer> ids
     * @return List<Country> The Country entity
     */
    public List<Country> loadCountryListById(List<java.lang.Integer> ids){
       return null;
    }
    
    /**
     * Loads the Country entity which is related to an instance of
     * Country and its dependent one to many objects
     * @param Long id
     * @return Country The Country entity
     */
    public Country loadFullFirstLevelCountry(java.lang.Integer id) {
        List list = getJpaTemplate().find(
                     "SELECT country FROM Country country "
                     + " LEFT JOIN country.addressCountryIds "   
                     + " WHERE country.id = "+id
               );
         if (list!=null && !list.isEmpty())
            return (Country)list.get(0);
         return null;
    	//return null;//(Country) getJpaTemplate().queryForObject("loadFullFirstLevelCountry", id);
    }

    /**
     * Loads the Country entity which is related to an instance of
     * Country
     * @param Country country
     * @return Country The Country entity
     */
    public Country loadFullFirstLevelCountry(Country country) {
        boolean isWhereSet = false;
        StringBuffer query = new StringBuffer();
        query.append ("SELECT country FROM Country country ");
        query.append (" LEFT JOIN country.addressCountryIds ");
        if (country.getId() != null) {
           query.append (getQueryWHERE_AND (isWhereSet));
           isWhereSet = true;
           query.append(" country.id = "+ country.getId() + " ");
         }
	        	List list = getJpaTemplate().find(query.toString());
        if (list!=null && !list.isEmpty())
           return (Country)list.get(0);    
        return null;
    }  
     
    /**
     * Loads the Country entity which is related to an instance of
     * Country and its dependent objects one to many
     * @param Long id
     * @return Country The Country entity
     */
    public Country loadFullCountry(Long id) {
    	return null;//(Country)getJpaTemplate().queryForObject("loadFullCountry", id);
    }

    /**
     * Searches a list of Country entity 
     * @param Country country
     * @return List
     */
    public List<Country> searchPrototypeCountry(Country country) {      
       return searchPrototype (country, null);            
    }  
    
    protected List<Country> searchPrototype (Country country, Integer maxResults) { 
       Query hquery = getEntityManager().createQuery(getCountrySearchEqualQuery (country));
       if (maxResults!=null)
          hquery.setMaxResults(maxResults);
       return hquery.getResultList();         
    }        
    
    public List<Country> searchPrototypeCountry (List<Country> countrys) {
       return searchPrototype (countrys, null);  
    }

    protected List<Country> searchPrototype (List<Country> countrys, Integer maxResults) {    
       //TODO convert setMaxResults in JPA if (maxResults!=null)
       //   getJpaTemplate().setMaxResults(maxResults);
       return getJpaTemplate().find(getCountrySearchEqualQuery (null, countrys));            
    }    

    public List<Country> searchDistinctPrototypeCountry (Country countryMask, List<Country> countrys) {
        return getJpaTemplate().find(getCountrySearchEqualQuery (countryMask, countrys));    
    }
         
	/**
     * Searches a list of Country entity 
     * @param Country countryPositive
     * @param Country countryNegative
     * @return List
     */
    public List<Country> searchPrototypeCountry(Country countryPositive, Country countryNegative) {
	    return getJpaTemplate().find(getCountrySearchEqualQuery (countryPositive, countryNegative));  
    }

    /**
    * return a jql query search on a Country prototype
    */
    protected String getCountrySearchEqualQuery (Country country) {
        StringBuffer query = new StringBuffer();
        StringBuffer queryWhere = new StringBuffer();
        query.append ("SELECT country FROM Country country ");
        queryWhere.append (getCountrySearchEqualWhereQueryChunk(country, false));   
	    return getHQuery(query.toString(), queryWhere.toString());
    }


    /**
    * return a jql search for a list of Country prototype
    */
    protected String getCountrySearchEqualQuery (Country countryMask, List<Country> countrys) {
        boolean isOrSet = false;
        StringBuffer query = new StringBuffer();
        if (countryMask !=null)
           query.append (getCountryMaskWhat (countryMask));
        query.append (" FROM Country country ");
        StringBuffer queryWhere = new StringBuffer();
        for (Country country : countrys) {
           if (!isAllNull(country)) {        
	           queryWhere.append (getQueryOR (isOrSet));
	           isOrSet = true;        
	           queryWhere.append (" ("+getCountrySearchEqualWhereQueryChunk(country, false)+") ");
           }
        }
	    return getHQuery(query.toString(), queryWhere.toString());
    }	
    
    protected String getCountryMaskWhat (Country countryMask) {
        boolean isCommaSet = false;
        StringBuffer query = new StringBuffer("SELECT DISTINCT ");
        if (countryMask.getId() != null) {
           query.append (getQueryComma (isCommaSet));
           isCommaSet = true;
           query.append(" id ");
        }
        if (countryMask.getName() != null) {
           query.append (getQueryComma (isCommaSet));
           isCommaSet = true;
           query.append(" name ");
        }
        if (countryMask.getIsoName() != null) {
           query.append (getQueryComma (isCommaSet));
           isCommaSet = true;
           query.append(" isoName ");
        }
        if (!isCommaSet)
           return "";
	    return query.toString();        
    }
    
    protected String getCountrySearchEqualWhereQueryChunk (Country country, boolean isAndSet) {
        StringBuffer query = new StringBuffer();
        if (country.getId() != null) {
           query.append (getQueryAND (isAndSet));
           isAndSet = true;
           query.append(" country.id = "+ country.getId() + " ");
        }
        if (country.getName() != null) {
           query.append (getQueryAND (isAndSet));
           isAndSet = true;
           query.append(" country.name = '"+ country.getName()+"' ");
        }
        if (country.getIsoName() != null) {
           query.append (getQueryAND (isAndSet));
           isAndSet = true;
           query.append(" country.isoName = '"+ country.getIsoName()+"' ");
        }
	    return query.toString();
    }   
     
    /**
    * return a jql search on a Country prototype with positive and negative beans
    */
    protected String getCountrySearchEqualQuery (Country countryPositive, Country countryNegative) {
        boolean isWhereSet = false;
        StringBuffer query = new StringBuffer();
        query.append (" SELECT country FROM Country country ");
        if (countryPositive!=null && countryPositive.getId() != null) {
           query.append (getQueryWHERE_AND (isWhereSet));
           isWhereSet = true;
           query.append(" country.id = "+ countryPositive.getId() + " ");
        } else if (countryNegative.getId() != null) {
           query.append (getQueryWHERE_AND (isWhereSet));
           isWhereSet = true;   
           query.append(" country.id is null ");
        }
        if (countryPositive!=null && countryPositive.getName() != null) {
           query.append (getQueryWHERE_AND (isWhereSet));
           isWhereSet = true;
           query.append(" country.name = '"+ countryPositive.getName()+"' ");
        } else if (countryNegative.getName() != null) {
           query.append (getQueryWHERE_AND (isWhereSet));
           isWhereSet = true;   
           query.append(" country.name is null ");
        }
        if (countryPositive!=null && countryPositive.getIsoName() != null) {
           query.append (getQueryWHERE_AND (isWhereSet));
           isWhereSet = true;
           query.append(" country.isoName = '"+ countryPositive.getIsoName()+"' ");
        } else if (countryNegative.getIsoName() != null) {
           query.append (getQueryWHERE_AND (isWhereSet));
           isWhereSet = true;   
           query.append(" country.isoName is null ");
        }
	    return query.toString();
    }
    
    /**
     * Load a paginated list of Country entity dependent of pagination criteria
     * @param PaginationCriteria paginationCriteria
     * @return List
     */
    public List <Country> loadPaginatedCountry (Country country, PaginationCriteria paginationCriteria) {
	    List<Integer> page = loadPaginatedCountryIdentitiesFromStartPositionId(country, paginationCriteria);
    	int start = paginationCriteria.getNumberOfRowsReturned()*(paginationCriteria.getPageNumber());
    	int max = page.size();
    	if (start<max) {
    	   List<Integer> returnPage = page.subList(start, max);	
           StringBuffer query = new StringBuffer();
           query.append (" SELECT country FROM Country country ");      
	       query.append(" where country.id in (");
	       for (Iterator iter = returnPage.iterator(); iter.hasNext();) {
			  Integer id = (Integer) iter.next();
			  query.append(id.toString());
		      if (iter.hasNext())
			     query.append(",");
		   }
	       query.append(") ");
	       return getJpaTemplate().find(query.toString()); 
    	} 
        return new ArrayList<Country>();
    }      

    protected List<Integer> loadPaginatedCountryIdentitiesFromStartPositionId (Country country, PaginationCriteria paginationCriteria) {
       boolean isWhereSet = false;
       StringBuffer query = new StringBuffer();
       query.append ("select country.id ");
       query.append (getCountrySearchEqualQuery (country));
       if (paginationCriteria.getOrderList()!=null) {
    	   query.append(" order by "+paginationCriteria.getOrderList());
       }
       int maxResult = paginationCriteria.getNumberOfRowsReturned()*(1+paginationCriteria.getPageNumber());
       List<Integer> set = getEntityManager().createNamedQuery(query.toString()).setMaxResults(maxResult).getResultList();
       return set;
    }

    //TODO put in upper class
    
	protected String getQueryWHERE_AND(boolean isWhereSet) {
		if (isWhereSet)
			return " AND ";
		return " WHERE ";
	}

	protected String getQueryCommaSet(boolean isWhereSet) {
		if (isWhereSet)
			return " , ";
		return " SET ";
	}

	protected static String getQuerySelectComma(boolean isSelectSet) {
		if (isSelectSet)
			return " , ";
		return " select ";
	}

	protected static String getQueryBLANK_AND(boolean isBlankSet) {
		if (isBlankSet)
			return " AND ";
		return " ";
	}

	protected static String getQueryBLANK_COMMA(boolean isBlankSet) {
		if (isBlankSet)
			return " , ";
		return " ";
	}

	protected String getQueryComma(boolean isCommaSet) {
		return (isCommaSet) ? " , " : "";
	}

	protected String getQueryOR(boolean isOrSet) {
		return (isOrSet) ? " OR " : "";
	}

	protected String getQueryAND(boolean isAndSet) {
		return (isAndSet) ? " AND " : "";
	}

	protected String getHQuery (String what, String where) {
        String whereWord = getWhere (where);
	    return what+whereWord+where;
    }
    
    protected String getWhere (String where) {
        return (where==null || where.trim().equals("") || where.trim()==null)?"":" WHERE ";
    }   
    protected EntityManager getEntityManager () {
        return EntityManagerFactoryUtils.getTransactionalEntityManager(getJpaTemplate().getEntityManagerFactory());    
    }
    
    protected void convertTransientReferenceToNull (Country country) {
    }
}
