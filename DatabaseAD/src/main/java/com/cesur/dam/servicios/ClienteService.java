package com.cesur.dam.servicios;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import com.cesur.dam.Entidades.Cliente;
import com.cesur.dam.repositorios.ClienteRepository;

@Service
public class ClienteService {
    private final ClienteRepository clienteRepository;

    @Autowired
    public ClienteService(ClienteRepository clienteRepository) {
        this.clienteRepository = clienteRepository;
    }

    public List<Cliente> obtenerTodos() {
        List<Cliente> clientes = clienteRepository.findAll();

        // Convertir la lista de clientes a formato XML
        String xmlClientes = convertirListaClientesAXML(clientes);

        // Guardar la lista de clientes como un archivo XML formateado
        guardarClientesComoXml(xmlClientes);

        return clientes;
    }

    private String convertirListaClientesAXML(List<Cliente> clientes) {
        StringBuilder xmlBuilder = new StringBuilder();
        xmlBuilder.append("<ArrayList>");

        for (Cliente cliente : clientes) {
            xmlBuilder.append("\n  <item>");
            xmlBuilder.append("\n    <idCliente>").append(cliente.getIdCliente()).append("</idCliente>");
            xmlBuilder.append("\n    <nombre>").append(cliente.getNombre()).append("</nombre>");
            xmlBuilder.append("\n    <apellidos>").append(cliente.getApellidos()).append("</apellidos>");
            xmlBuilder.append("\n    <telefono>").append(cliente.getTelefono()).append("</telefono>");
            xmlBuilder.append("\n    <email>").append(cliente.getEmail()).append("</email>");
            xmlBuilder.append("\n  </item>");
        }

        xmlBuilder.append("\n</ArrayList>");

        return xmlBuilder.toString();
    }

    private void guardarClientesComoXml(String xmlClientes) {
        String directorioDeTrabajo = "DatabaseAD\\Files";
        String nombreArchivo = "clientes.xml";

        try {
            // Construir la ruta completa para guardar el archivo XML en la carpeta "Files"
            File archivoXml = new File(directorioDeTrabajo, nombreArchivo);

            // Guardar el archivo XML formateado
            try (PrintWriter writer = new PrintWriter(new FileWriter(archivoXml, false), true)) {
                writer.println("<?xml version=\"1.0\" encoding=\"UTF-8\"?>");
                writer.println(xmlClientes);
            }
        } catch (IOException e) {
            throw new RuntimeException("Error al intentar guardar la lista de clientes como XML", e);
        }
    }
}
