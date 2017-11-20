
package org.example.tariff.exceptions;

import java.util.HashMap;
import java.util.Map;
import java.util.stream.IntStream;

import org.springframework.util.StringUtils;




public class EntityUpdateException extends RuntimeException {
    
    
    public EntityUpdateException(EntityNotFoundException ex){
        super(ex);
    }
      public EntityUpdateException(Throwable ex){
        super(ex);
    }
    /**
     * 
     
     * @param clazz
     * @param searchParamsMap must be odd key/value pair
     */
    public EntityUpdateException(Class clazz, String[] searchParamsMap) {
        super(EntityUpdateException.generateMessage(clazz.getSimpleName(),
                toMap( searchParamsMap)));
    }

    private static String generateMessage(String entity, Map<String, String> searchParams) {
        return StringUtils.capitalize(entity) +
                " was not found for parameters " +
                searchParams;
    }

    private static Map<String, String> toMap(
             String[] entries) {
        if (entries.length % 2 == 1)
            throw new IllegalArgumentException("Invalid entries");
        return IntStream.range(0, entries.length / 2).map(i -> i * 2)
                .collect(HashMap::new,
                        (m, i) -> m.put(entries[i], entries[i + 1]),
                        Map::putAll);
    }

}