# Sprint-Group-B-ECommerce

## Title : Online Shopping Platform

### Entity:

1) Product (int prodId, String prodName, String category, double price, double rating)

1) Seller (int sellerId, String sellerName, String userName, String password, double rating, List<Product> product)

1) Customer (int custId, String custName, String userName, String password, String address, List<Product> wishlist, List<Orders> custOrders) ; Methods (void addToWishlist(Product p), addToCustOrders(Orders o))

1) Orders (int orderId, Customer customer, Seller seller, Product product, LocalDate deliveryDate)

1) Feedback (int feedbackId, Orders order, double rating, String feedback, LocalDate dateCreated)

1) Feedback Response (int feedbackId, double feedbackRating, String feedback, LocalDate dateCreated)


### Modules / Controllers:

1) Product
1) Seller
1) Customer
1) Login
1) Orders
1) Feedback

### Service Implementation :

#### 1) Product
1. Product saveProduct(Product product) throws AlreadyExistsException, Exception;

2. List<Product> getAllProducts() throws AlreadyExistsException, Exception;

3. Optional<Product> getProductById(int id) throws NotFoundException, Exception;

4. void updateProduct(Product p) throws NotFoundException, Exception;

5. void deleteProduct(int id) throws NotFoundException, Exception;

6. List<Product> getProductsByCategory(String category) throws NotFoundException

#### 2) Seller
1. Seller saveSeller(Seller seller) throws AlreadyExistsException, UniqueValueException;

2. List<Seller> getAllSellers() throws NotFoundException;

3. Optional<Seller> getSellerById(int sellerId) throws NotFoundException;

4. Seller deleteSeller(int sellerId) throws NotFoundException;

5. Seller updateSeller(Seller seller) throws NotFoundException;

6. String loginSeller(Seller seller) throws NotFoundException, MismatchException;

7. List<Seller> filterAboveRating(double rating) throws NotFoundException;

#### 3) Customer

1. Customer addCustomer(Customer c) throws AlreadyExistsException, UniqueValueException;

2. List<Customer> getCustomers();

3. void deleteCustomerById(int custId) throws NotFoundException;

4. void updateCustomer(int custId, Customer c) throws NotFoundException;

5. Optional<Customer> getCustomerById(int custId) throws NotFoundException;

6. String loginCustomer(Customer customer) throws NotFoundException, MismatchException;

7. String placeOrder(int custId, Orders o) throws AlreadyExistsException;

8. String addWishlist(int custId, Product p);

#### 4) Orders 

1. Orders saveOrder(Orders orders) throws AlreadyExistsException;

2. List<Orders> getAllOrders();

3. Orders getOrderById(int id) throws NotFoundException;

4. void update(Orders orders) throws NotFoundException;

5. void delete(int id) throws NotFoundException;

6. List<Orders> getOrdersBySeller(int id) throws NotFoundException;

7. List<Orders> getOrdersByCustomer(int id) throws NotFoundException

#### 5) Feedback 

1. addFeedback(Feedback feedback) throws NotFoundException;

2. List<Feedback> getAllFeedbacks() throws NotFoundException;

3. Feedback getFeedbackById(int id) throws NotFoundException;

4. Feedback updateFeedbackById(int id, Feedback feedback) throws NotFoundException;

5. String deleteFeedbackById(int id) throws NotFoundException;

6. void updateProductRating(Feedback feedback);

7. void updateSellerRating(int id);

8. Feedback getFeedbackByOrderId(int id) throws NotFoundException;

9. List<FeedbackResponse> getFeedbackByCustomerId(int id) throws NotFoundException;

10. List<FeedbackResponse> getFeedbackByProductId(int id) throws NotFoundException;


### Repository:

#### 1) Product
1) Seller
2) Customer
3) Orders
4) Feedback


### Routing 

- Save/Create
  - /save/Object

- Get
  - /object
  - /Object/id

- Update
  - /update/Object

- Delete
  - /delete/Object/id


