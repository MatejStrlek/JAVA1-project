/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package hr.algebra.dal;

import hr.algebra.dal.sql.DataSourceSingleton;
import java.io.InputStream;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author matej.galic
 */
public class RepositoryFactory {

    private static final String PATH = "/config/repository.properties";
    private static final String CLASS_NAME = "CLASS_NAME";

    private static final Properties properties = new Properties();

    static {
        try (InputStream is = DataSourceSingleton.class.getResourceAsStream(PATH)) {
            properties.load(is);
            
            repository = (Repository)
                    Class.forName(properties.getProperty(CLASS_NAME))
                    .getDeclaredConstructor()
                    .newInstance();

        } catch (Exception ex) {
            Logger.getLogger(DataSourceSingleton.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private static Repository repository;

    private RepositoryFactory() {
    }

    public static Repository getRepository() {
        return repository;
    }

}
