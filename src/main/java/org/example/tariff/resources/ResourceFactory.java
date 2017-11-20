
package org.example.tariff.resources;



import org.springframework.data.domain.Page;


public  class ResourceFactory<T,U> {
   private AbstractResourceMapper<T,U> mapper;
   public ResourceFactory(AbstractResourceMapper<T,U> mapper){
       this.mapper=mapper;
   }
   public Page<U> createResource(Page<T> content){

       return content.map(c->mapper.populateDTO(c));
   }
    
}
