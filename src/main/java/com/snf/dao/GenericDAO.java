package com.snf.dao;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import com.snf.builder.JPQLBuilder;

@SuppressWarnings("unchecked")
public abstract class GenericDAO<T , ID> implements Serializable {

	private static final long serialVersionUID = 3836419209780168990L;
	
	@Inject
	@PersistenceContext
	private EntityManager manager;
	
    public T getById(ID id) {
        return (T) manager.find(getTypeClass(), id);
    }
 
    public T save(T entity) {
    	try{
    		manager.getTransaction().begin();
            entity = manager.merge(entity);
            manager.getTransaction().commit();
    	}catch(Exception e){
    		manager.getTransaction().rollback();
    		throw e;    		
    	}
    	return entity;
    	
    }
	
	public boolean delete(T entity) {
		boolean sucesso = false;
    	try{
    		if (!getManager().contains(entity)) {
    			entity = getManager().merge(entity);
    		}
    		getManager().remove(entity);
    		sucesso = true;
    	}catch(Exception e){
    		e.printStackTrace();
    		manager.getTransaction().rollback();
    		throw e;
    	}
    	return sucesso;
    }
 
    public void delete(Long id) {
    	try{
    		manager.getTransaction().begin();
    		manager.remove(manager.find(getTypeClass(), id));
            manager.getTransaction().commit();
    	}catch(Exception e){
    		e.printStackTrace();
    		manager.getTransaction().rollback();
    		throw e;
    	}
    }
    
    public void colocarParametros(Query query, JPQLBuilder builder) {
    	for (Map.Entry<String, Object> parametros : builder.getParametros().entrySet()) {
			query.setParameter(parametros.getKey(), parametros.getValue());
		}
    }
 
    public List<T> getAll() {
        return manager.createQuery("FROM " + getTypeClass().getName()).getResultList();
    }
 
    private Class<?> getTypeClass() {
        return (Class<?>) ((ParameterizedType) this.getClass()
                .getGenericSuperclass()).getActualTypeArguments()[0];
    }

	public EntityManager getManager() {
		return manager;
	}
    
}
