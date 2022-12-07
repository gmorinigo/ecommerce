package com.grupo1.ecommerce;

import com.grupo1.ecommerce.models.CategProducto;
import com.grupo1.ecommerce.models.Categoria;
import com.grupo1.ecommerce.models.Producto;
import com.grupo1.ecommerce.repository.CategProductoRepository;
import com.grupo1.ecommerce.repository.CategoriaRepository;
import com.grupo1.ecommerce.repository.ProductoRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.List;

@SpringBootApplication
public class EcommerceApplication {

	public static void main(String[] args) {
		SpringApplication.run(EcommerceApplication.class, args);
	}

	@Bean
	public CommandLineRunner initData(ProductoRepository productoRepository, CategoriaRepository categoriaRepository,
									  CategProductoRepository categProductoRepository) {
		return (args) -> {
			Producto zapatilla = new Producto("Zapatillas Nike", 30);
			Producto pelota = new Producto("Pelota", 20);
			Producto heladera = new Producto("Heladera", 10);
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
		};
	}
}
