package com.example.examendb.repositorio;

import com.example.examendb.modelo.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ClienteRepository extends JpaRepository<Cliente, Long> {

    /**
     * Obtenemos al cliente por su id
     * @param id del cliente
     * @return cliente
     */

    public Cliente getClienteById(Long id);

    /**
     * busqueda del cliente activo con mas ventas de x...
     * @param total
     * @return cliente
     */
    @Query("SELECT c FROM Cliente c WHERE c.estado = 'Activo' AND c.total > :cantidad")
    List<Cliente> encontrarClientesActivosConVentasMayorQue(@Param("cantidad") Double total);

    /**
     * busquedas para el ultimo apartado
     * @return info
     */

    @Query("SELECT SUM(c.total) FROM Cliente c")
    Double calcularTotalVentas();

    @Query("SELECT AVG(c.total) FROM Cliente c")
    Double calcularPromedioVentas();

    @Query("SELECT COUNT(c) FROM Cliente c WHERE c.estado = 'inactivo'")
    Long contarClientesInactivos();
}

