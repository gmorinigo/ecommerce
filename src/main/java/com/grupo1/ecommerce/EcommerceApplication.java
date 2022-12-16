package com.grupo1.ecommerce;

import com.grupo1.ecommerce.models.*;
import com.grupo1.ecommerce.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.List;

@SpringBootApplication
public class EcommerceApplication {

	public static void main(String[] args) {
		SpringApplication.run(EcommerceApplication.class, args);
	}


	@Autowired
	PasswordEncoder passwordEncoder;
	@Bean
	public CommandLineRunner initData(ProductoRepository productoRepository, CategoriaRepository categoriaRepository,
									  CategProductoRepository categProductoRepository, CarritoRepository carritoRepository,
									  CarritoProductoRepository carritoProductoRepository, ClientRepository clientRepository) {
		return (args) -> {
			Producto zapatilla = new Producto("Zapatillas Nike", 30, 0, 25000.00);
			Producto pelota = new Producto("Pelota", 20, 0, 15000.00);
			Producto heladera = new Producto("Heladera", 10, 0, 150000.00);
			productoRepository.saveAll(List.of(zapatilla,pelota,heladera));

			Categoria depote = new Categoria("Deportes");
			Categoria electro = new Categoria("Electro");
			Categoria hogar = new Categoria("Hogar");
			Categoria indumentaria = new Categoria("Indumentaria");

			categoriaRepository.saveAll(List.of(depote,electro,hogar,indumentaria));

			CategProducto zapatillaDeporte = new CategProducto(depote,zapatilla);
			CategProducto zapatillaIndumentaria = new CategProducto(indumentaria,zapatilla);
			CategProducto pelotaDeporte = new CategProducto(depote,pelota);
			CategProducto heladeraElectro = new CategProducto(electro,heladera);
			CategProducto heladeraHogar = new CategProducto(hogar,heladera);

			categProductoRepository.saveAll(List.of(zapatillaDeporte,zapatillaIndumentaria,pelotaDeporte,heladeraElectro,heladeraHogar));

			Carrito carrito1 = new Carrito(0);
			Carrito carrito2 = new Carrito(0);

			Client client1 = new Client("Melba", "Lorenzo", "melba@emil.com", passwordEncoder.encode("ABCD1234"));
			Client client2 = new Client("aa", "ss", "a@a.com", passwordEncoder.encode("123"));

			carrito1.setOwnerCarrito(client1);
			carrito2.setOwnerCarrito(client2);
			client1.setCarrito(carrito1);
			client2.setCarrito(carrito2);
			clientRepository.save(client1);
			clientRepository.save(client2);
			carritoRepository.save(carrito1);
			carritoRepository.save(carrito2);

			CarritoProducto carrito1Zapatilla = new CarritoProducto(1,zapatilla,client2.getCarrito());
			CarritoProducto carrito1Pelota = new CarritoProducto(1,pelota,client2.getCarrito());
			carritoProductoRepository.saveAll(List.of(carrito1Zapatilla, carrito1Pelota));


		};
	}
}
