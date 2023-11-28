package com.cesur.dam.repositorios;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;

@Repository
public class ConexionRepository {

    @Value("${spring.datasource.url}")
    private String databaseUrl;

    @Value("${spring.datasource.username}")
    private String databaseUsername;

    @Value("${spring.datasource.password}")
    private String databasePassword;

    public String confirmarConexion() {
        try {
            System.out.println("Conectando a la base de datos...");

            try (Connection connection = DriverManager.getConnection(databaseUrl, databaseUsername, databasePassword)) {
                System.out.println("Conexión exitosa a la base de datos.");
                return "Conexión exitosa";
            } catch (SQLException e) {
                System.err.println("Error al conectar a la base de datos.");
                e.printStackTrace();
                return "Error al conectar a la base de datos.";
            }
        } catch (Exception e) {
            System.err.println("Error general.");
            e.printStackTrace();
            return "Error general.";
        }
    }
}
