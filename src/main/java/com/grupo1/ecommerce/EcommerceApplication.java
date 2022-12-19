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
			Producto remera1 = new Producto("remera1", 30, 0, 12500.00);
			Producto remera2 = new Producto("remera2", 20, 30, 10500.00);
			Producto remera3 = new Producto("remera3", 10, 0, 15000.00);
			Producto remera4 = new Producto("remera4", 30, 15, 15000.00);
			Producto zapatilla1 = new Producto("zapatilla1", 20, 0, 20000.00);
			Producto zapatilla2 = new Producto("zapatilla2", 10, 20, 24000.00);
			Producto zapatilla3 = new Producto("zapatilla3", 30, 0, 25000.00);
			Producto campera1 = new Producto("campera1", 20, 0, 28000.00);
			Producto campera2 = new Producto("campera2", 10, 0, 30000.00);
			Producto campera3 = new Producto("campera3", 30, 12, 32000.00);
			Producto campera4 = new Producto("campera4", 20, 5, 29000.00);
			Producto gorra1 = new Producto("gorra1", 10, 10, 1800.00);
			Producto gorra2 = new Producto("gorra2", 30, 5, 2500.00);
			Producto gorra3 = new Producto("gorra3", 20, 15, 1500.00);
			Producto gorra4 = new Producto("gorra4", 10, 20, 1500.00);
			productoRepository.saveAll(List.of(remera1,remera2,remera3,remera4,zapatilla1,zapatilla2,zapatilla3,
					campera1,campera2,campera3,campera4,gorra1,gorra2,gorra3,gorra4));

			Categoria remeras = new Categoria("Remeras");
			Categoria zapatillas = new Categoria("Zapatillas");
			Categoria camperas = new Categoria("Camperas");
			Categoria gorras = new Categoria("Gorras");

			categoriaRepository.saveAll(List.of(remeras,zapatillas,camperas,gorras));

			CategProducto remeraCatProd1 = new CategProducto(remeras,remera1);
			CategProducto remeraCatProd2 = new CategProducto(remeras,remera2);
			CategProducto remeraCatProd3 = new CategProducto(remeras,remera3);
			CategProducto remeraCatProd4 = new CategProducto(remeras,remera4);

			CategProducto zapatillaCatProd1 = new CategProducto(zapatillas,zapatilla1);
			CategProducto zapatillaCatProd2 = new CategProducto(zapatillas,zapatilla2);
			CategProducto zapatillaCatProd3 = new CategProducto(zapatillas,zapatilla3);
			CategProducto zapatillaCatProd4 = new CategProducto(zapatillas,remera4);

			CategProducto camperaCatProd1 = new CategProducto(camperas,campera1);
			CategProducto camperaCatProd2 = new CategProducto(camperas,campera2);
			CategProducto camperaCatProd3 = new CategProducto(camperas,campera3);
			CategProducto camperaCatProd4 = new CategProducto(camperas,campera4);

			CategProducto gorraCatProd1 = new CategProducto(gorras,gorra1);
			CategProducto gorraCatProd2 = new CategProducto(gorras,gorra2);
			CategProducto gorraCatProd3 = new CategProducto(gorras,gorra3);
			CategProducto gorraCatProd4 = new CategProducto(gorras,gorra4);

			categProductoRepository.saveAll(List.of(remeraCatProd1,remeraCatProd2,remeraCatProd3,remeraCatProd4,
					zapatillaCatProd1,zapatillaCatProd2,zapatillaCatProd3,zapatillaCatProd4,
					camperaCatProd1,camperaCatProd2,camperaCatProd3,camperaCatProd4,
					gorraCatProd1,gorraCatProd2,gorraCatProd3,gorraCatProd4));


			Client client1 = new Client("Melba", "Lorenzo", "melba.testjava@gmail.com", passwordEncoder.encode("ABCD1234"));
			Client client2 = new Client("aa", "ss", "a@a.com", passwordEncoder.encode("123"));

			Carrito carrito1 = new Carrito(0, client1);
			Carrito carrito2 = new Carrito(0, client2);

			client1.setCarrito(carrito1);
			client2.setCarrito(carrito2);

			clientRepository.save(client1);
			clientRepository.save(client2);
/*
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
			carritoRepository.save(carrito1);

			CarritoProducto carrito1Zapatilla = new CarritoProducto(1,zapatilla,carrito1);
			CarritoProducto carrito1Pelota = new CarritoProducto(1,pelota,carrito1);

			carritoProductoRepository.saveAll(List.of(carrito1Zapatilla, carrito1Pelota));

			Client client1 = new Client("Melba", "Lorenzo", "melba@emil.com", passwordEncoder.encode("ABCD1234"));
			Client client2 = new Client("aa", "ss", "a@a.com", passwordEncoder.encode("123"));
			clientRepository.save(client1);
			clientRepository.save(client2);
*/
		};
	}
}
