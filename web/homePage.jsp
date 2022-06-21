<%-- 
    Document   : homePage
    Created on : Jun 1, 2022, 8:11:33 PM
    Author     : Admin
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <title>W3.CSS Template</title>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <link rel="stylesheet" href="https://www.w3schools.com/w3css/4/w3.css">
        <link rel="stylesheet" href="https://fonts.googleapis.com/css?family=Montserrat">
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
        <style>
            body, h1,h2,h3,h4,h5,h6 {font-family: "Montserrat", sans-serif}
            .w3-row-padding img {margin-bottom: 12px}
            /* Set the width of the sidebar to 120px */
            .w3-sidebar {width: 120px;background: #222;}
            /* Add a left margin to the "page content" that matches the width of the sidebar (120px) */
            #main {margin-left: 120px}
            /* Remove margins from "page content" on small screens */
            @media only screen and (max-width: 600px) {#main {margin-left: 0}}
        </style>
    </head>
    <body>

        <!-- Icon Bar (Sidebar - hidden on small screens) -->
        <nav class="w3-sidebar w3-bar-block w3-small w3-hide-small w3-center">
            <!-- Avatar image in top left corner -->
            <img src="https://www.w3schools.com/w3images/avatar_smoke.jpg" style="width:100%">

            <a href="#" class="w3-bar-item w3-button w3-padding-large w3-black">
                <i class="fa fa-file w3-xxlarge"></i>
                <p>Hóa đơn</p>
            </a>

            <a href="#about" class="w3-bar-item w3-button w3-padding-large w3-hover-black">
                <i class="fa fa-user w3-xxlarge"></i>
                <p>Thông tin khách hàng</p>
            </a>


            <a href="#photos" class="w3-bar-item w3-button w3-padding-large w3-hover-black">
                <i class="fa fa-dollar w3-xxlarge"></i>
                <p>Thống kê lợi nhuận</p>
            </a>
            <a href="#contact" class="w3-bar-item w3-button w3-padding-large w3-hover-black">
                <i class="fa fa-cart-plus w3-xxlarge"></i>
                <p>Thông tin món đồ</p>
            </a>
        </nav>

        <!-- Navbar on small screens (Hidden on medium and large screens) -->
        <div class="w3-top w3-hide-large w3-hide-medium" id="myNavbar">
            
        </div>
        
        
    </body>
</html>
