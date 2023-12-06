package com.cesur.dam.servicios;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class FileService {

    public void guardarArchivo(MultipartFile archivo) {
//logica de archivos
// Obtiene la ruta absoluta del directorio de trabajo (la raíz del proyecto)
        String directorioDeTrabajo = "DatabaseAD\\Files";

        // Construye la ruta completa para guardar el archivo en la raíz del proyecto
        Path rutaArchivo = Paths.get(directorioDeTrabajo, archivo.getOriginalFilename());

        // Guarda el archivo en la ruta especificada
        try {
            Files.write(rutaArchivo, archivo.getBytes());
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
    public void borrarArchivo(String nombreArchivo) {
        String directorioDeTrabajo = "DatabaseAD\\Files";
        Path rutaArchivo = Paths.get(directorioDeTrabajo, nombreArchivo);
    
        if (!Files.exists(rutaArchivo)) {
            throw new RuntimeException("El archivo " + nombreArchivo + " no existe.");
        } else {
            try {
                Files.deleteIfExists(rutaArchivo);
            } catch (IOException e) {
                throw new RuntimeException("Error al intentar borrar el archivo " + nombreArchivo, e);
            }
        }
    }
    
  public List<String> listarArchivos() {
        String directorioDeTrabajo = "DatabaseAD";
        File directorio = new File(directorioDeTrabajo);

        if (!directorio.exists() || !directorio.isDirectory()) {
            throw new RuntimeException("El directorio no existe o no es válido: " + directorioDeTrabajo);
        }

        // Lista los archivos en el directorio
        String[] archivos = directorio.list();

        if (archivos == null || archivos.length == 0) {
            // No hay archivos, devolver una lista vacía en lugar de lanzar una excepción
            return new ArrayList<>();
        }

        return Arrays.asList(archivos);
    }

}
