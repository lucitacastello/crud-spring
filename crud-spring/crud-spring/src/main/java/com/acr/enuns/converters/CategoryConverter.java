package com.acr.enuns.converters;

import com.acr.enuns.Category;
import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

import java.util.stream.Stream;
//O spring vai fazer a conversão para o Enumerator
//para converter ENUMS

@Converter(autoApply = true) //JPA aplicar sempre que necessário
public class CategoryConverter implements AttributeConverter<Category,String> {
    @Override
    public String convertToDatabaseColumn(Category category) {
        //converter o valor para o DB
        if(category == null){
            return null;
        }
        return category.getValue();
    }

    @Override
    public Category convertToEntityAttribute(String value) {
        if(value == null){
            return null;
        }

        //filtro do java
        return Stream.of(Category.values())
                .filter(c -> c.getValue().equals(value))
                .findFirst()
                .orElseThrow(IllegalArgumentException::new);
    }


}
