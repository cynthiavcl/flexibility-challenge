package ar.com.plug.examen.domain.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import ar.com.plug.examen.domain.model.Product;

public interface ProductRepository  extends JpaRepository<Product, Long>{

}
