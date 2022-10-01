package com.sprint.ecommerce.service;

import java.util.List;
import java.util.Optional;

import com.sprint.ecommerce.entity.Product;
import com.sprint.ecommerce.entity.Seller;
import com.sprint.ecommerce.exception.AlreadyExistsException;
import com.sprint.ecommerce.exception.MismatchException;
import com.sprint.ecommerce.exception.NotFoundException;
import com.sprint.ecommerce.exception.UniqueValueException;

public interface SellerService {

	Seller saveSeller(Seller seller) throws AlreadyExistsException, UniqueValueException;

	List<Seller> getAllSellers() throws NotFoundException;

	Optional<Seller> getSellerById(int sellerId) throws NotFoundException;

	Seller deleteSeller(int sellerId) throws NotFoundException;

	Seller updateSeller(Seller seller) throws NotFoundException;

	String loginSeller(Seller seller) throws NotFoundException, MismatchException;

	List<Seller> filterAboveRating(double rating) throws NotFoundException;

	String addToProductList(int sellerId, Product p) throws NotFoundException, AlreadyExistsException, Exception;

	String removeFromProductList(int sellerId, int prodId) throws NotFoundException;
}
