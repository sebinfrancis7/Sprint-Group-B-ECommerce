package com.sprint.ecommerce.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.sprint.ecommerce.entity.Feedback;
import com.sprint.ecommerce.entity.FeedbackResponse;
import com.sprint.ecommerce.entity.Product;

@Repository
public interface FeedbackRepository extends JpaRepository<Feedback, Integer> {

	@Query("select coalesce(sum(f.rating),0) from Feedback f join Orders o on f.order = o.orderId where o.product =:prod")
	public double findTotalRatingOfProduct(@Param("prod") Product prod);

	@Query("select count(*) from Feedback f join Orders o on f.order = o.orderId where o.product =:prod")
	public int findCountOfProductFeedback(@Param("prod") Product prod);
//	select count(*) from feedback f join orders o on f.order_id = o.order_id where o.product = 1;

	@Query("select f from Feedback f where f.order.orderId = :id")
	public Feedback getFeedbackFromOrderID(@Param("id") int id);

	@Query("select new com.sprint.ecommerce.entity.FeedbackResponse(f.feedbackId,f.rating, f.feedback, f.dateCreated) from Feedback f where f.order.customer.custId = :id order by f.order.orderId desc")
	public List<FeedbackResponse> getFeedbackFromCustomerID(@Param("id") int id);

	@Query("select new com.sprint.ecommerce.entity.FeedbackResponse(f.feedbackId,f.rating, f.feedback, f.dateCreated) from Feedback f where f.order.product.prodId = :id order by f.rating desc")
	public List<FeedbackResponse> getFeedbackFromProductID(@Param("id") int id);
}
