# Online-shop cart
 <p>This project  is analogue of online-shop cart.</p>
 
# Download
<p>Download cart.zip. Install instruction is inside.</p>

[cart.zip](https://github.com/ARK21/cart/files/1629672/cart.zip)

# SQL 
  <p>For information storage uses PostgreSQL 9.5. Data stores in 3 schemes:</p>
  <li> 1. <strong> books</strong> </li>
  <p>Here stores list of enable book. Their IDs, authors, titles, price per one copy.</p>
  
![booksscheme](https://user-images.githubusercontent.com/18110699/34907887-568e8bcc-f8b8-11e7-8ca7-2c779e4642e1.jpg)

 <li> 2. <strong> customer</strong> </li>
 <p>Here stores list of Customers. Their order_ids, full names, phone numbers, total prices. After order confirm, you get irder_id and  your data stores here.</p>
 
![customerscheme](https://user-images.githubusercontent.com/18110699/34907844-859a6b3a-f8b7-11e7-92e1-86c0cdea8d7e.jpg)

<li> 3. <strong> orders</strong> </li>
 <p>Here stores list of orders. You can see your order by order_id, books which you ordered, their titles, authors, quantity, prices per 1 copy. For primery key use 'uid' column. </p>

![ordersscheme](https://user-images.githubusercontent.com/18110699/34907861-dbe91cac-f8b7-11e7-8fe0-bb2c7da27bae.jpg)

# Interface
<h2>Index.jsp</h2>

![main page](https://user-images.githubusercontent.com/18110699/34905793-b250a510-f892-11e7-8034-945dd9cdfef0.jpg)
<p>Main page of project. Here you can see enable books list, titles, authors, prices. This list takes from postgresql database. </p>
<hr>
 <strong>It's page has two option:</strong>
 <li> 1. You can add 1 instance of Item in cart, to press: "Order now". </li>
 <li> 2. You can see what items your cart contain, to press: "Show my cart". </li>
 <h2>cart.jsp</h2> 
<p>If your cart is empty. your will see this:</p>

![empty cart](https://user-images.githubusercontent.com/18110699/34907069-77320e38-f8ab-11e7-908f-86da8d8825eb.jpg)
<p>If you press "Order now", chosen book will be add and you will redirect to your cart:</p>

![cart](https://user-images.githubusercontent.com/18110699/34907139-8a242bf6-f8ac-11e7-8649-5f130e399d0e.jpg)
<p>On this page you can items, quantity, total price. Also you can delete or add one copy of chosen books, by clicking the corresponding buttons. To choose another book, back to the main page press: "Continue shopping". </p> 

<p>When you will be ready to confirm order, fill "Full name" and "Phone number" fields and press "Confirm" </p>

![confirm order](https://user-images.githubusercontent.com/18110699/34907367-079e6af8-f8b0-11e7-96cc-3baa3ad10946.jpg)
<h2>orderSuccessed.jsp</h2>
<p>Your will redirect to the successful confirmation page. Here you can see order list and order id. It's id of your order in database.</p>

![confirm succeed](https://user-images.githubusercontent.com/18110699/34907451-301823a6-f8b1-11e7-9f05-49a36de4d017.jpg)
<hr>
<p>Now you can see order data in database. </p>
<h3>Customer data and total price. </h3>

![sqlcustomer](https://user-images.githubusercontent.com/18110699/34907550-0964dd74-f8b3-11e7-875d-e31769569d50.jpg)

<h3>Order items. </h3>

![sqlorders](https://user-images.githubusercontent.com/18110699/34907555-1653d94a-f8b3-11e7-8dbd-4e97efa91373.jpg)


