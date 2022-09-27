package com.sprint.ecommerce.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.sprint.ecommerce.entity.Feedback;
import com.sprint.ecommerce.entity.Product;

@Repository
public interface FeedbackRepository extends JpaRepository<Feedback, Integer> {

	@Query("select sum(f.rating) from Feedback f join Orders o on f.order = o.orderId where o.product =:prod")
	public double findTotalRatingOfProduct(@Param("prod") Product prod);

	@Query("select count(*) from Feedback f join Orders o on f.order = o.orderId where o.product =:prod")
	public int findCountOfProductFeedback(@Param("prod") Product prod);
//	select count(*) from feedback f join orders o on f.order_id = o.order_id where o.product = 1;
}
