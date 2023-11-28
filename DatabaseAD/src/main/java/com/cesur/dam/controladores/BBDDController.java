package com.cesur.dam.controladores;

import java.util.List;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.cesur.dam.Entidades.Cliente;
import com.cesur.dam.servicios.ClienteService;
import com.cesur.dam.servicios.ConexionService;
import com.cesur.dam.servicios.FileService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;

@Controller
public class BBDDController {
	
    @Autowired
    protected ConexionService conexionService;

    @Autowired
    private final FileService fileService;

    public BBDDController(FileService fileService) {
        this.fileService = fileService;
    }

    @ResponseBody
    @GetMapping("/conexion")
    public String devolvertests() {
        return conexionService.devolverTests();
    }
	@ResponseBody
    @PostMapping("/subirArchivo") //PostMapping indica que el metodo responde a solicitudes HTTP Post
    public String subirArchivo(@RequestParam("archivo") MultipartFile archivo) { //RequestParam parametro para recibir el archivo que se esta subiendo
		//MultipartFile interfaz de Spring que proporciona metodos para interactuar con archivos que se stan subiendo
        fileService.guardarArchivo(archivo);
        return "Archivo subido correctamente";
    }
    @ResponseBody
@PostMapping("/borrarArchivo")
public void borrarArchivos(@RequestParam("archivo") String archivo) {
    fileService.borrarArchivo(archivo);
}
@RestController
@RequestMapping("/clientes")
public class ClienteController {
    private final ClienteService clienteService;

    @Autowired
    public ClienteController(ClienteService clienteService) {
        this.clienteService = clienteService;
    }

    @GetMapping
    public List<Cliente> obtenerTodosClientes() {
        return clienteService.obtenerTodos();
    }
}
@RestController
@RequestMapping("/xml")
public class XmlController {
    private final ClienteService clienteService;

    @Autowired
    public XmlController(ClienteService clienteService) {
        this.clienteService = clienteService;
    }

    @GetMapping("/clientes")
    public ResponseEntity<String> obtenerClientesEnXml() throws JsonProcessingException {
        List<Cliente> clientes = clienteService.obtenerTodos();
        XmlMapper xmlMapper = new XmlMapper();
        String xml = xmlMapper.writeValueAsString(clientes);

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_XML);

        return new ResponseEntity<>(xml, headers, HttpStatus.OK);
    }
}


}


