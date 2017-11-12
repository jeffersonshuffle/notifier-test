/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.example.tariff.resources;



import org.springframework.data.domain.Page;

/**
 *
 * @author root
 */
public  class ResourceFactory<T,U> {
   private AbstractResourceMapper<T,U> mapper;
   public ResourceFactory(AbstractResourceMapper<T,U> mapper){
       this.mapper=mapper;
   }
   public Page<U> createResource(Page<T> content){

       return content.map(c->mapper.populateDTO(c));
   }
    
}
