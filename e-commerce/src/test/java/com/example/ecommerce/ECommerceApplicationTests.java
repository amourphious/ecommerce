package com.example.ecommerce;

import com.example.ecommerce.entity.*;
import com.example.ecommerce.enums.FromStatus;
import com.example.ecommerce.enums.ToStatus;
import com.example.ecommerce.repository.*;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

@SpringBootTest
class ECommerceApplicationTests {

    //	creating object
    Role r1 = new Role("CUSTOMER_ROLE");
    Role r2 = new Role("SELLER_ROLE");
    Role r3 = new Role("ADMIN_ROLE");
//	end of object creation

    @Autowired
    UserRepository userRepository;

    @Autowired
    CustomerRepository customerRepository;

    @Autowired
    SellerRepository sellerRepository;

    @Autowired
    AddressRepository addressRepository;

    @Autowired
    CategoriesRepository categoriesRepository;

    @Autowired
    ProductRepository productRepository;

    @Autowired
    ProductVariationRepository productVariationRepository;

    @Autowired
    InvoiceRepository invoiceRepository;

    @Autowired
    ProductReviewRepository productReviewRepository;

    PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    @Test
    void contextLoads() {

    }

    @Test
    public void addCustomer() {
        Customer customer = new Customer();
        customer.setUsername("monks_mojo");
        customer.setEmail("makkarudit@gmail.com");
        customer.setFirstName("udit");
        customer.setLastName("makkar");
        customer.setPassword(passwordEncoder.encode("pass"));
        customer.setDeleted(false);
        customer.setActive(true);
        customer.setRolesList(Arrays.asList(r1, r2, r3)); // many-to-many don,t require additional table;
        customer.setContactNumber("8826225077");
        customerRepository.save(customer);
    }

    @Test
    public void addSeller() {
        Seller seller = new Seller();
        seller.setUsername("krishna");
        seller.setEmail("krishna@gmail.com");
        seller.setFirstName("krishna");
        seller.setMiddleName("govinda");
        seller.setLastName("vasudeva");
        seller.setPassword(passwordEncoder.encode("pass"));
        seller.setDeleted(false);
        seller.setActive(true);
        seller.setRolesList(Arrays.asList(r1, r3)); // many-to-many don,t require added from role side
        seller.setGstNumber("frx-99087-y");
        seller.setCompanyName("crossing infinity");
        seller.setContactNumber("4444444444");
        sellerRepository.save(seller);
    }

//	@Test
//	public void addCustomer(){
//		User user=userRepository.findByEmail("makkarudit@gmail.com");
//		Customer customer= new Customer();
//		customer.setContactNumber("8826225077");
////		user.createCustomer(customer);
//		userRepository.save(user);
//	}

//	@Test
//	public void addSeller(){
//		User user=userRepository.findByEmail("makkarudit@gmail.com");
//		user.createSeller(seller);
//		userRepository.save(user);
//	}


//	@Test
//	public void addCustomer1(){
//		User user=userRepository.findByEmail("krishna@gmail.com");
//		Customer customer= new Customer();
//		customer.setContactNumber("999999999");
//		user.createCustomer(customer);
//		userRepository.save(user);
//	}

//	@Test
//	public void addSeller1(){
//		User user=userRepository.findByEmail("krishna@gmail.com");
//		Seller seller= new Seller();
//		seller.setGstNumber("god-999-y");
//		seller.setComapnyName("go-loka");
//		seller.setContactNumber("9999999999");
//		user.createSeller(seller);
//		userRepository.save(user);
//	}

    @Test
    public void addAddress() {
        User user = userRepository.findByEmail("makkarudit@gmail.com");
        Address address1 = new Address("block-19", "tilak nagar", "delhi", "india", 1100018, "home");
        user.addAddress(address1);
        Address address2 = new Address("23/11", "neo zone", "crater-delhi", "zone-11", 119898, "work");
        user.addAddress(address2);
        userRepository.save(user);
    }

    @Test
    public void addAddress1() {
        User user = userRepository.findByEmail("krishna@gmail.com");
        Address address1 = new Address("brij-streets", "vrindawan", "mathura", "india", 1100, "home");
        user.addAddress(address1);
        Address address2 = new Address("nirvana", "mandalas", "dwarka dhama", "universe", 119898, "work");
        user.addAddress(address2);
        userRepository.save(user);
    }

    @Test
    public void addCategory1() {
        Categories parentCategory = new Categories();
        parentCategory.setCategoryName("books");
        categoriesRepository.save(parentCategory);
    }

    @Test
    public void addCategory2() {
        Categories parentCategory = categoriesRepository.findByCategoryName("books");
        Categories childCategory = new Categories();
        childCategory.setCategoryName("neo-classic books");
                childCategory.setParentCategory(parentCategory);
        categoriesRepository.save(childCategory);
    }

    @Test
    public void addCategory3() {
        Categories parentCategory = categoriesRepository.findByCategoryName("books");
        Categories childCategory = new Categories();
        childCategory.setCategoryName("classic books");
        childCategory.setParentCategory(parentCategory);
        categoriesRepository.save(childCategory);
    }

    @Test
    public void addCategory4() {
        Categories parentCategory = new Categories();
        parentCategory.setCategoryName("grooming products");
        categoriesRepository.save(parentCategory);
    }

    @Test
    public void addCategory5() {
        Categories parentCategory = categoriesRepository.findByCategoryName("grooming products");
        Categories childCategory = new Categories();
        childCategory.setCategoryName("hair brush");
        childCategory.setParentCategory(parentCategory);
        categoriesRepository.save(childCategory);
    }

    @Test
    public void addCategory6() {
        Categories parentCategory = categoriesRepository.findByCategoryName("grooming products");
        Categories childCategory = new Categories();
        childCategory.setCategoryName("shaving kits");
        childCategory.setParentCategory(parentCategory);
        categoriesRepository.save(childCategory);
    }


    @Test
    public void addProduct() {
        Categories categories = categoriesRepository.findByCategoryName("hair brush");
        Product product = new Product();
        product.setName("Gliding Brush");
        product.setBrand("Nivea");
        product.setDescription("roller pin hair brush");
        product.setActive(true);
        product.setCancellable(true);
        product.setReturnable(false);
        categories.insertProduct(product);
        categoriesRepository.save(categories);
    }

    @Test
    public void addProduct1() {
        Categories categories = categoriesRepository.findByCategoryName("shaving kits");
        Product product = new Product();
        product.setName("Heavy Duty Beard Oil");
        product.setBrand("Beardo");
        product.setDescription("beard growth oil");
        product.setActive(true);
        product.setCancellable(true);
        product.setReturnable(false);
        categories.insertProduct(product);
        categoriesRepository.save(categories);
    }

    @Test
    public void addSellerProducts() {
        Seller seller = sellerRepository.findById(270l).get();
        Product product = productRepository.findByName("Gliding Brush");
        Product product1 = productRepository.findByName("Heavy Duty Beard Oil");
        Set<Product> productSet = new HashSet<>();
        productSet.add(product);
        productSet.add(product1);
        seller.setProductList(productSet);
        sellerRepository.save(seller);
    }

    @Test
    public void addReview() {
        Product product = productRepository.findByName("Gliding Brush");
        ProductReview productReview = new ProductReview("durable and good build quality", 4.5, product);
        Customer customer = customerRepository.findById(266l).get();
        customer.insertProductReview(productReview);
        customerRepository.save(customer);
    }

    @Test
    public void addReview1() {
        Product product = productRepository.findByName("Heavy Duty Beard Oil");
        ProductReview productReview = new ProductReview("expensive but shows good result", 4d, product);
        Customer customer = customerRepository.findById(266l).get();
        customer.insertProductReview(productReview);
        customerRepository.save(customer);
    }

    @Test
    public void addProductVariation() {
        Product product = productRepository.findByName("Gliding Brush");
        ProductVariation productVariation = new ProductVariation();
        productVariation.setMetadata("{" + "\"description\": \"BLACK COLOR BRUSH\"," + "}");
        productVariation.setQuantity(5);
        productVariation.setPrice(230.50);
        productVariation.setImageName("black brush image");
        product.insertProductVariation(productVariation); // adding variation to product one to many
        productRepository.save(product);
    }

    @Test
    public void addProductVariation1() {
        Product product = productRepository.findByName("Gliding Brush");
        ProductVariation productVariation = new ProductVariation();
        productVariation.setMetadata("{" + "\"description\": \"BLUE COLOR BRUSH\"," + "}");
        productVariation.setQuantity(3);
        productVariation.setPrice(230.50);
        productVariation.setImageName("blue brush image");
        product.insertProductVariation(productVariation); // adding variation to product one to many
        productRepository.save(product);
    }

    @Test
    public void testCreateInvoice() {
        Invoice invoice = new Invoice();
        Customer customer = customerRepository.findById(266l).get();
		Address address=addressRepository.fetchAddress("home",customer.getUserId());
		invoice.setInvoiceAddress(new InvoiceAddress(address));
		invoice.setAmountPaid(2000d);
		invoice.setPaymentMode("credit card");
		Date currentDate=new Date();
		invoice.setDate(currentDate);
		customer.createInvoice(invoice);
		customerRepository.save(customer);
    }

    @Test
    public void testAddProductsToInvoice(){
        Invoice invoice=invoiceRepository.findById(286l).get();
        ProductVariation productVariation=productVariationRepository.findById(284l).get();
        OrderProduct orderProduct=new OrderProduct();
        orderProduct.setPrice(500d);
        orderProduct.setQuantity(1);
        orderProduct.setProductVariation(productVariation);
        invoice.insertOrderProduct(orderProduct);

        OrderStatus orderStatus=new OrderStatus();
        orderStatus.setTransitionComment("order received");
        orderStatus.setFromStatus(FromStatus.ORDER_PLACED);
        orderStatus.setToStatus(ToStatus.ORDER_CONFIRMED);
        orderProduct.setStataus(orderStatus);
        invoiceRepository.save(invoice);
    }

    @Test
    public void testAddCart(){
        Customer customer=customerRepository.findById(266l).get();
        ProductVariation productVariation=productVariationRepository.findById(284l).get();
        ProductVariation productVariation1=productVariationRepository.findById(285l).get();

        Cart cart=new Cart();
        Set<ProductVariation> productVariationSet=new HashSet<>();

        productVariationSet.add(productVariation);
        productVariationSet.add(productVariation1);
        cart.setProductVariationSet(productVariationSet);

        customer.createCart(cart);
        customerRepository.save(customer);
    }

    @Test
    public void printEmail(){
        List<Seller> sellerList=sellerRepository.fetchAllSeller();
        sellerList.forEach(e-> System.out.println(e.getEmail()));
    }

    @Test
    public void getCustomer(){
        Customer customer=customerRepository.findByActivationToken("f3ba76cd-0464-427d-9ffe-3de9330dda57");
        System.out.println(customer.getEmail());
        System.out.println(customer.getFirstName());
    }

    @Test
    public void getUser(){
        User user=userRepository.findByResetToken("30b893e7-7169-455a-b214-f4d62b8527d2");
        System.out.println(user.getFirstName());
    }

    @Transactional
    @Test
    @Rollback(false)
    public  void deleteUser(){
        Seller seller=sellerRepository.findByUsername("hari-bol");
        if(seller!=null){
            sellerRepository.delete(seller);
        }
    }
}
