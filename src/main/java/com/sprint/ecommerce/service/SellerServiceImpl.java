package com.sprint.ecommerce.service;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sprint.ecommerce.entity.Product;
import com.sprint.ecommerce.entity.Seller;
import com.sprint.ecommerce.exception.AlreadyExistsException;
import com.sprint.ecommerce.exception.MismatchException;
import com.sprint.ecommerce.exception.NotFoundException;
import com.sprint.ecommerce.exception.UniqueValueException;
import com.sprint.ecommerce.helpers.PasswordHash;
import com.sprint.ecommerce.repository.SellerRepository;

@Service
public class SellerServiceImpl implements SellerService {

	@Autowired
	private SellerRepository sellerRepo;

	@Override
	public Seller saveSeller(Seller seller) throws AlreadyExistsException, UniqueValueException {
		if (sellerRepo.existsById(seller.getSellerId())) {
			throw new AlreadyExistsException("Seller Already Exists");
		}
		List<String> userNameList = sellerRepo.uniqueUserName();
		if (userNameList.contains(seller.getUserName())) {
			throw new UniqueValueException("Seller Username Already Exists. Enter a unique value.");
		}
		PasswordHash p1 = new PasswordHash();
		seller.setPassword(p1.encrypt(seller.getPassword()));
		Seller s1 = sellerRepo.save(seller);
		return s1;
	}

	@Override
	public List<Seller> getAllSellers() throws NotFoundException {
		List<Seller> list = sellerRepo.findAll();
		if (list.isEmpty()) {
			throw new NotFoundException("No Sellers Found. Add sellers.");
		}
		return list;
	}

	@Override
	public Optional<Seller> getSellerById(int sellerId) throws NotFoundException {
		if (!sellerRepo.existsById(sellerId)) {
			throw new NotFoundException("No Seller Found");
		}
		Optional<Seller> seller = sellerRepo.findById(sellerId);
		return seller;
	}

	@Override
	public Seller deleteSeller(int sellerId) throws NotFoundException {
		if (!sellerRepo.existsById(sellerId)) {
			throw new NotFoundException("Seller Id does not exist");
		}
		sellerRepo.deleteById(sellerId);
		return null;
	}

	@Override
	public Seller updateSeller(Seller seller) throws NotFoundException {
		if (!sellerRepo.existsById(seller.getSellerId())) {
			throw new NotFoundException("No Seller Found");
		}
		Seller s1 = sellerRepo.save(seller);
		return s1;
	}

	@Override
	public String loginSeller(Seller seller) throws NotFoundException, MismatchException {
		PasswordHash p1 = new PasswordHash();
		if (sellerRepo.existsById(seller.getSellerId())) {
			Seller s1 = sellerRepo.findById(seller.getSellerId()).get();
			if (seller.getUserName().equals(s1.getUserName())
					&& seller.getPassword().equals(p1.decrypt(s1.getPassword()))) {
				return "Seller logged in";
			} else {
				throw new MismatchException("Invalid Credentials. Please Recheck Your Username And Password.");
			}
		} else {
			throw new NotFoundException("Seller Id Not Found");
		}
	}

	@Override
	public List<Seller> filterAboveRating(double rating) throws NotFoundException {
		List<Seller> list = sellerRepo.findAboveRating(rating);
		if (list.size() < 1) {
			throw new NotFoundException("No sellers found above " + rating + " rating.");
		}
		return list;
	}

}
