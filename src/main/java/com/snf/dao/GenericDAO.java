package com.snf.dao;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.util.List;

import javax.inject.Inject;
import javax.persistence.EntityManager;

@SuppressWarnings("unchecked")
public abstract class GenericDAO<T , ID> implements Serializable {

	private static final long serialVersionUID = 3836419209780168990L;
	
	@Inject
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
 
    public List<T> getAll() {
        return manager.createQuery(("FROM " + getTypeClass().getName())).getResultList();
    }
 
    private Class<?> getTypeClass() {
        Class<?> clazz = (Class<?>) ((ParameterizedType) this.getClass()
                .getGenericSuperclass()).getActualTypeArguments()[0];
        return clazz;
    }

	public EntityManager getManager() {
		return manager;
	}
    
}
