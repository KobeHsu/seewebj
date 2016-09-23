package org.iii.see.dao;

import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.springframework.stereotype.Repository;

@Repository
public class BaseDao<ENTITY, PK> {

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
	
	public List<ENTITY> queryAll(ENTITY entity) {		
		CriteriaBuilder criteriaBuilder = em.getCriteriaBuilder();
		CriteriaQuery criteriaQuery = criteriaBuilder.createQuery(entity.getClass());
		Root<ENTITY> rootEntity = (Root<ENTITY>) criteriaQuery.from(entity.getClass());
		criteriaQuery.select(rootEntity);
		TypedQuery<ENTITY> query = em.createQuery(criteriaQuery);
		return query.getResultList();
	}
}
