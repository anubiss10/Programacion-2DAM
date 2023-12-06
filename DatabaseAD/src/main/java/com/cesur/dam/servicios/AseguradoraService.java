package com.cesur.dam.servicios;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import com.cesur.dam.Entidades.Aseguradora;
import com.cesur.dam.repositorios.AseguradoraRepository;

@Service
public class AseguradoraService {
    private final AseguradoraRepository aseguradoraRepository;

    @Autowired
    public AseguradoraService(AseguradoraRepository aseguradoraRepository) {
        this.aseguradoraRepository = aseguradoraRepository;
    }

    public List<Aseguradora> obtenerTodos() {
        List<Aseguradora> aseguradoras = aseguradoraRepository.findAll();

        // Convertir la lista de aseguradoras a formato XML
        String xmlAseguradoras = convertirListaAseguradorasAXML(aseguradoras);

        // Guardar la lista de aseguradoras como un archivo XML formateado
        guardarAseguradorasComoXml(xmlAseguradoras);

        return aseguradoras;
    }

    private String convertirListaAseguradorasAXML(List<Aseguradora> aseguradoras) {
        StringBuilder xmlBuilder = new StringBuilder();
        xmlBuilder.append("<ArrayList>");

        for (Aseguradora aseguradora : aseguradoras) {
            xmlBuilder.append("\n  <item>");
            xmlBuilder.append("\n    <idAseguradora>").append(aseguradora.getIdAseguradora()).append("</idAseguradora>");
            xmlBuilder.append("\n    <nombre>").append(aseguradora.getNombre()).append("</nombre>");
            xmlBuilder.append("\n  </item>");
        }

        xmlBuilder.append("\n</ArrayList>");

        return xmlBuilder.toString();
    }

    private void guardarAseguradorasComoXml(String xmlAseguradoras) {
        String directorioDeTrabajo = "DatabaseAD\\Files";
        String nombreArchivo = "aseguradoras.xml";

        try {
            // Construir la ruta completa para guardar el archivo XML en la carpeta "Files"
            File archivoXml = new File(directorioDeTrabajo, nombreArchivo);

            // Guardar el archivo XML formateado
            try (PrintWriter writer = new PrintWriter(new FileWriter(archivoXml, false), true)) {
                writer.println("<?xml version=\"1.0\" encoding=\"UTF-8\"?>");
                writer.println(xmlAseguradoras);
            }
        } catch (IOException e) {
            throw new RuntimeException("Error al intentar guardar la lista de aseguradoras como XML", e);
        }
    }
}
