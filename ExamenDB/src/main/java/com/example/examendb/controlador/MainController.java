package com.example.examendb.controlador;

import com.example.examendb.modelo.Cliente;
import com.example.examendb.repositorio.ClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/cliente")
public class MainController {
    @Autowired
    private ClienteRepository repository;

    /**
     * imprime a un cliente por su id en cosnola
     * @param id
     * @return cliente
     */

    @GetMapping("/id/{id}")
    public Cliente getClienteById(@PathVariable Long id){
        return repository.getClienteById(id);
    }

    /**
     * clientes activos con mas ventas por encima de...
     * @param total
     * @return ventas
     */


    @GetMapping("/ventas")
    public List<Cliente> obtenerClientesActivosConVentasMayorQue(@RequestParam("cantidad") Double total) {
        return repository.encontrarClientesActivosConVentasMayorQue(total);
    }

    /**
     * Subir nuevo cliente
     * @param cliente
     * @return nuevo cliente
     */

    @PostMapping("/cliente/post")
    public ResponseEntity <Cliente> nuevo(@RequestBody Cliente cliente){
        return new ResponseEntity<>(repository.save(cliente), HttpStatus.OK);
    }

    /**
     * çresumen estadistico
     * @return estadisticas
     */
    @GetMapping("/total/media")
    public String obtenerTotalVentasYPromedio() {
        Double totalVentas = repository.calcularTotalVentas();
        Double promedioVentas = repository.calcularPromedioVentas();
        Long clientesInactivos = repository.contarClientesInactivos();

        return "Total de ventas: " + totalVentas + ", Promedio de ventas: " + promedioVentas +
                ", Cantidad de clientes inactivos: " + clientesInactivos;
    }
}
