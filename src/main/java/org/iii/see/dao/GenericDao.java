package org.iii.see.dao;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Order;
import javax.persistence.criteria.Path;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.apache.commons.lang.StringUtils;
import org.iii.see.dao.wherecon.Criteria;
import org.iii.see.dao.wherecon.Criterion;
import org.iii.see.dao.wherecon.OrderBy;
import org.springframework.stereotype.Repository;

@Repository
public class GenericDao<ENTITY, PK> {

	@PersistenceContext
	private EntityManager em;
	
	public ENTITY create(ENTITY entity) {
		em.persist(entity);
		return entity;
	}
	
	public ENTITY update(ENTITY entity) {
		em.merge(entity);
		return entity;
	}
	
	public void delete(ENTITY entity, PK pk) {
		Object originalObject = em.find(entity.getClass(), pk);
		em.remove(originalObject);
	}
	
	@SuppressWarnings("unchecked")
	public ENTITY query(ENTITY entity, PK pk) {
		Object resultEntity = em.find(entity.getClass(), pk);
		return (ENTITY)resultEntity;
	}

	public EntityManager getEntityManager() {
		return em;
	}
	
	@SuppressWarnings("rawtypes")
	public List queryByNamedQuery(String queryName, Map<String, Object> params) {
		Query query = em.createNamedQuery(queryName);

		Iterator iterator = params.entrySet().iterator();
		while (iterator.hasNext()) { 
			Map.Entry entry = (Map.Entry)iterator.next();
			query.setParameter((String)entry.getKey(), entry.getValue());
		}
		
		return query.getResultList();
	}

	@SuppressWarnings("rawtypes")
	public List queryByNamedQuery(String queryName, Map<String, Object> params, int recordStart, int recordLength) {
		Query query = em.createNamedQuery(queryName);
		
		Iterator iterator = params.entrySet().iterator();
		while (iterator.hasNext()) { 
			Map.Entry entry = (Map.Entry)iterator.next();
			query.setParameter((String)entry.getKey(), entry.getValue());
		}
				
		query.setFirstResult(recordStart).setMaxResults(recordLength);		

		return query.getResultList();
	}
		
	@SuppressWarnings("rawtypes")
	public int executeByNamedQuery(String queryName, Map<String, Object> params) {
		Query query = em.createNamedQuery(queryName);

		Iterator iterator = params.entrySet().iterator();
		while (iterator.hasNext()) { 
			Map.Entry entry = (Map.Entry)iterator.next();
			query.setParameter((String)entry.getKey(), entry.getValue());
		}
		
		int result = query.executeUpdate();
		em.flush();

		return result;
	}
	
	@SuppressWarnings("unchecked")
	public List<ENTITY> queryAll(ENTITY entity) {		
		CriteriaBuilder criteriaBuilder = em.getCriteriaBuilder();

		CriteriaQuery criteriaQuery = criteriaBuilder.createQuery(entity.getClass());
		Root<ENTITY> rootEntity = (Root<ENTITY>) criteriaQuery.from(entity.getClass());
		
		criteriaQuery.select(rootEntity);
		
		TypedQuery<ENTITY> query = em.createQuery(criteriaQuery);
		return query.getResultList();
	}
	
	public List<ENTITY> query(ENTITY entity, Criteria criteria) {

		TypedQuery<ENTITY> query = buildQuery(entity, criteria);
		
		return query.getResultList();
	}

	public List<ENTITY> query(ENTITY entity, Criteria criteria, int recordStart, int recordLength) {

		TypedQuery<ENTITY> query = buildQuery(entity, criteria);
		query.setFirstResult(recordStart).setMaxResults(recordLength);
		
		return query.getResultList();
	}
	
	@SuppressWarnings("unchecked")
	private TypedQuery<ENTITY> buildQuery(ENTITY entity, Criteria criteria) {
		CriteriaBuilder criteriaBuilder = em.getCriteriaBuilder();

		CriteriaQuery criteriaQuery = criteriaBuilder.createQuery(entity.getClass());
		Root<ENTITY> rootEntity = (Root<ENTITY>) criteriaQuery.from(entity.getClass());
		
		criteriaQuery.select(rootEntity);
		
		List<Criterion> criterionList = criteria.getCriterionList();
		List<OrderBy> orderByList = criteria.getOrderByList();
		
		List<Predicate> predicates = buildPredicates(criteriaBuilder, rootEntity, criterionList) ;
				
		if (predicates.size() > 1) {
			criteriaQuery.where(criteriaBuilder.and((Predicate[])predicates.toArray()));
		} else if (predicates.size() == 1) { 
			criteriaQuery.where(predicates.get(0));
		}
		
		List<Order> orderList = new ArrayList<Order>();
		for (OrderBy orderBy : orderByList) {
			Path<Object> column = rootEntity.get(orderBy.getProperty().getProperty());
			if (orderBy.isAscending()) {
				orderList.add(criteriaBuilder.asc(column));				
			} else {
				orderList.add(criteriaBuilder.desc(column));
			}			
		}
		
		criteriaQuery.orderBy(orderList);
		
		TypedQuery<ENTITY> query = em.createQuery(criteriaQuery);		
		return query;
	}
	
	private List<Predicate> buildPredicates(CriteriaBuilder criteriaBuilder, Root<ENTITY> rootEntity, List<Criterion> criterionList) {
		
		List<Predicate> predicates = new ArrayList<Predicate>();
		
		for (Criterion criterion : criterionList) {
			Predicate predicate = null;
			Path<Object> column = null;
			
			String columnName = criterion.getProperty().getProperty();
			if (StringUtils.indexOf(columnName, "id.") >= 0) {							
				column = rootEntity.get("id").get(StringUtils.replace(columnName, "id.", ""));
			} else {
				column = rootEntity.get(criterion.getProperty().getProperty());
			}
			
			
			switch(criterion.getOperation()) {
			case Criteria.EQ :
				predicate = criteriaBuilder.equal(column, criteriaBuilder.literal(criterion.getValue1()));		
				break;
			case Criteria.NE :
				predicate = criteriaBuilder.notEqual(column, criteriaBuilder.literal(criterion.getValue1()));		
				break;
			case Criteria.LIKE :
				predicate = criteriaBuilder.like(rootEntity.<String>get(criterion.getProperty().getProperty()), (String)criterion.getValue1());
				break;
			case Criteria.GE :
				if (criterion.getValue1() instanceof String) {
					predicate = criteriaBuilder.greaterThanOrEqualTo(rootEntity.<String>get(criterion.getProperty().getProperty()), (String)criterion.getValue1());												
				} else if (criterion.getValue1() instanceof Timestamp) {
					predicate = criteriaBuilder.greaterThanOrEqualTo(rootEntity.<Timestamp>get(criterion.getProperty().getProperty()), (Timestamp)criterion.getValue1());							
				}
				break;
			case Criteria.GT :
				if (criterion.getValue1() instanceof String) {
					predicate = criteriaBuilder.greaterThan(rootEntity.<String>get(criterion.getProperty().getProperty()), (String)criterion.getValue1());		
				} else if (criterion.getValue1() instanceof Timestamp) {
					predicate = criteriaBuilder.greaterThan(rootEntity.<Timestamp>get(criterion.getProperty().getProperty()), (Timestamp)criterion.getValue1());		
				}
			case Criteria.LE :
				if (criterion.getValue1() instanceof String) {
					predicate = criteriaBuilder.lessThanOrEqualTo(rootEntity.<String>get(criterion.getProperty().getProperty()), (String)criterion.getValue1());		
				} else if (criterion.getValue1() instanceof Timestamp) {
					predicate = criteriaBuilder.lessThanOrEqualTo(rootEntity.<Timestamp>get(criterion.getProperty().getProperty()), (Timestamp)criterion.getValue1());		
				}
				break;
			case Criteria.LT :
				if (criterion.getValue1() instanceof String) {
					predicate = criteriaBuilder.lessThan(rootEntity.<String>get(criterion.getProperty().getProperty()), (String)criterion.getValue1());		
				} else if (criterion.getValue1() instanceof Timestamp) {
					predicate = criteriaBuilder.lessThan(rootEntity.<Timestamp>get(criterion.getProperty().getProperty()), (Timestamp)criterion.getValue1());		
				}
			}
			
			if (predicate != null) {
				predicates.add(predicate);
			}			
		}	
		
		return predicates;
	}
	
	public Long count(ENTITY entity, Criteria criteria) {

		CriteriaBuilder criteriaBuilder = em.getCriteriaBuilder();

		CriteriaQuery<Long> criteriaQuery = criteriaBuilder.createQuery(Long.class);
		Root<ENTITY> rootEntity = (Root<ENTITY>) criteriaQuery.from(entity.getClass());
		
		criteriaQuery.select(criteriaBuilder.count(rootEntity));
		
		List<Criterion> criterionList = criteria.getCriterionList();
		List<Predicate> predicates = buildPredicates(criteriaBuilder, rootEntity, criterionList) ;
				
		if (predicates.size() > 1) {
			criteriaQuery.where(criteriaBuilder.and((Predicate[])predicates.toArray()));
		} else if (predicates.size() == 1) { 
			criteriaQuery.where(predicates.get(0));
		}
						
		return em.createQuery(criteriaQuery).getSingleResult();
	}

}
