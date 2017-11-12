
package org.example.tariff.resources;


import java.util.List;


/**
 *
 * @author root
 */

public abstract class AbstractResourceMapper<T,U>  {
    public abstract List<U> populateDTO(List<T> content);
    public abstract U populateDTO(T content);
}
