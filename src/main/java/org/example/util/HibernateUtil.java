package org.example.util;

import lombok.experimental.UtilityClass;
import org.example.converter.BirthdayConverter;
import org.example.entity.User;
import org.hibernate.SessionFactory;
import org.hibernate.boot.model.naming.CamelCaseToUnderscoresNamingStrategy;
import org.hibernate.cfg.Configuration;

@UtilityClass
public class HibernateUtil {
    public static SessionFactory buildSessionFactory() {
        /**
         * Класс configuration представляет собой класс, настраивающий взаимодействие пользователя с БД.
         * Он указывает, как преобразуем классы в таблицы, какие типы используем, какие процедуры, функции итд
         * Короче, проще заглянуть внутрь, чем описать словами
         */
        Configuration configuration = new Configuration();
        /*
        Здесь 2 способа приведения поля класса в вид для колонки в СУБД.
        1 способ ниже, 2 способ в классе User, аннотация @Column
         */
        configuration.setPhysicalNamingStrategy(new CamelCaseToUnderscoresNamingStrategy()); // Это 1 способ
        configuration.addAnnotatedClass(User.class); // добавляем класс помеченный аннотацией Entity в конфиг
        configuration.addAttributeConverter(new BirthdayConverter(), true); // собственный конвертер (см. соответствующий класс)
        configuration.configure();
        return configuration.buildSessionFactory();
    }
}
