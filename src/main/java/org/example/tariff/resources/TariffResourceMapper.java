/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.example.tariff.resources;


import java.util.List;
import java.util.stream.Collectors;
import org.example.tariff.entities.Tariff;
import org.example.tariff.model.TariffDTO;
import org.example.tariff.utils.BeanCopyUtil;

/**
 *
 * @author root
 */
public class TariffResourceMapper extends AbstractResourceMapper<Tariff,TariffDTO>{

  
    @Override
    public List<TariffDTO> populateDTO(List<Tariff> content){
            return  content.stream()
                            .map(t->BeanCopyUtil.toTariffDTO(t))
                            .collect(Collectors.toList());
	}

    @Override
    public TariffDTO populateDTO(Tariff content) {
        return BeanCopyUtil.toTariffDTO(content);
    }
}
