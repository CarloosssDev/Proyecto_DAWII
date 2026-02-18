package cibertec.pe.service;

import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cibertec.pe.dtos.DetallePedidoRequest;
import cibertec.pe.dtos.DetallePedidoResponse;
import cibertec.pe.dtos.PedidoRequest;
import cibertec.pe.dtos.PedidoResponse;
import cibertec.pe.enums.EstadoPedido;
import cibertec.pe.enums.MetodoPago;
import cibertec.pe.feignclient.ClienteFeignClient;
import cibertec.pe.feignclient.ProductoFeignClient;
import cibertec.pe.feignclient.RepartidorFeignClient;
import cibertec.pe.mapper.DetallePedidoMapper;
import cibertec.pe.mapper.PedidoMapper;
import cibertec.pe.model.DetallePedido;
import cibertec.pe.model.Pedido;
import cibertec.pe.repository.IPedidoRepository;

@Service
public class PedidoImplement implements IPedidoService {

    @Autowired
    private IPedidoRepository repository;

    @Autowired
    private ClienteFeignClient clienteClient;

    @Autowired
    private ProductoFeignClient productoClient;

    @Autowired
    private RepartidorFeignClient repartidorClient;

    @Autowired
    private PedidoMapper pedidoMapper;

    @Autowired
    private DetallePedidoMapper detalleMapper;

    @Override
    @Transactional
    public PedidoResponse registrarPedido(PedidoRequest request) {

        var datosCliente = clienteClient.buscarPorTelefono(request.getTelefonoCliente());
        var datosRepartidor = repartidorClient.obtenerPorId(request.getIdRepartidor());

        Pedido nuevoPedido = pedidoMapper.toEntity(request);

        List<DetallePedido> detallesEntity = new ArrayList<>();
        List<DetallePedidoResponse> detallesResponse = new ArrayList<>();
        double montoTotal = 0;

        for (DetallePedidoRequest item : request.getDetalles()) {
            var infoProducto = productoClient.obtenerPorId(item.getIdProducto());

            DetallePedido detEntity = detalleMapper.toEntity(item, infoProducto.getPrecio());
            detallesEntity.add(detEntity);

            montoTotal += detEntity.getSubTotal();

            detallesResponse.add(detalleMapper.toResponse(detEntity, infoProducto.getNombre()));
        }

        nuevoPedido.setMontoTotal(montoTotal);
        nuevoPedido.setDetalles(detallesEntity);

        Pedido pedidoGuardado = repository.save(nuevoPedido);

        return pedidoMapper.toResponse(
                pedidoGuardado,
                datosCliente.getNombre(),
                datosCliente.getDireccion(),
                datosRepartidor.getNombre(),
                detallesResponse);
    }

    @Override
    public List<PedidoResponse> listarPedidos() {
        List<Pedido> pedidos = repository.findAll();
        List<PedidoResponse> responseList = new ArrayList<>();

        for (Pedido pedido : pedidos) {
            var datosCliente = clienteClient.buscarPorTelefono(pedido.getTelefonoCliente());
            var datosRepartidor = repartidorClient.obtenerPorId(pedido.getIdRepartidor());

            List<DetallePedidoResponse> detallesResponse = new ArrayList<>();
            for (DetallePedido det : pedido.getDetalles()) {
                var infoProducto = productoClient.obtenerPorId(det.getIdProducto());
                detallesResponse.add(detalleMapper.toResponse(det, infoProducto.getNombre()));
            }

            responseList.add(pedidoMapper.toResponse(
                    pedido,
                    datosCliente.getNombre(),
                    datosCliente.getDireccion(),
                    datosRepartidor.getNombre(),
                    detallesResponse));
        }
        return responseList;
    }

    @Override
    public Pedido buscarPorId(Long id) {
        return repository.findById(id).orElseThrow(() -> new RuntimeException("Pedido no encontrado"));
    }

    @Override
    @Transactional
    public Pedido actualizarPedido(Long id, PedidoRequest pedido) {
        Pedido existente = buscarPorId(id);
        existente.setIdRepartidor(pedido.getIdRepartidor());
        return repository.save(existente);
    }

    @Override
    @Transactional
    public Pedido cambiarEstado(Long id, EstadoPedido nuevoEstado) {
        Pedido pedido = buscarPorId(id);
        pedido.setEstado(nuevoEstado);
        return repository.save(pedido);
    }

    @Override
    @Transactional
    public Pedido registrarPagoFinal(Long id, MetodoPago pago) {
        Pedido pedido = buscarPorId(id);
        pedido.setMetodoPago(pago);
        return repository.save(pedido);
    }

    @Override
    public List<Pedido> listarPorEstado(EstadoPedido estado) {
        return repository.findByEstado(estado);
    }

    @Override
    public List<Pedido> listarPorRepartidor(Long idRepartidor) {
        return repository.findByIdRepartidor(idRepartidor);
    }

    @Override
    public List<Pedido> listarPorTelefono(String telefono) {
        return repository.findByTelefonoCliente(telefono);
    }
}