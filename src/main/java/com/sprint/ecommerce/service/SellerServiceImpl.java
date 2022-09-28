package com.sprint.ecommerce.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sprint.ecommerce.entity.Seller;
import com.sprint.ecommerce.exception.AlreadyExistsException;
import com.sprint.ecommerce.exception.NotFoundException;
import com.sprint.ecommerce.repository.SellerRepository;

@Service
public class SellerServiceImpl implements SellerService {

	@Autowired
	private SellerRepository sellerRepo;

	@Override
	public Seller saveSeller(Seller seller) throws AlreadyExistsException {
		if (sellerRepo.existsById(seller.getSellerId())) {
			throw new AlreadyExistsException();
		}
		Seller s1 = sellerRepo.save(seller);
		return s1;
	}

	@Override
	public List<Seller> getAllSellers() {
		List<Seller> list = sellerRepo.findAll();
		return list;
	}

	@Override
	public Optional<Seller> getSellerById(int sellerId) throws NotFoundException {
		if (!sellerRepo.existsById(sellerId)) {
			throw new NotFoundException();
		}
		Optional<Seller> seller = sellerRepo.findById(sellerId);
		return seller;
	}

	@Override
	public Seller deleteSeller(int sellerId) throws NotFoundException {
		if (!sellerRepo.existsById(sellerId)) {
			throw new NotFoundException();
		}
		sellerRepo.deleteById(sellerId);
		return null;
	}

	@Override
	public Seller updateSeller(Seller seller) throws NotFoundException {
		if (!sellerRepo.existsById(seller.getSellerId())) {
			throw new NotFoundException();
		}
		Seller s1 = sellerRepo.save(seller);
		return s1;
	}

	@Override
	public String loginSeller(Seller seller) throws NotFoundException {
		if (sellerRepo.existsById(seller.getSellerId())) {
			Seller s1 = sellerRepo.findById(seller.getSellerId()).get();
			if (seller.getUserName().equals(s1.getUserName()) && seller.getPassword().equals(s1.getPassword())) {
				return "Seller logged in";
			} else {
				return "Invalid Credentials";
			}
		} else {
			throw new NotFoundException();
		}
	}

}
