<%-- 
    Document   : bill
    Created on : Jun 6, 2022, 10:37:54 AM
    Author     : Admin
--%>

<%@page import="dao.BillDAO"%>
<%@page import="dao.ItemDAO"%>
<%@page import="dao.CustomerDAO"%>
<%@page import="dto.General"%>
<%@page import="dto.CustomerDTO"%>
<%@page import="dto.ItemDTO"%>
<%@page import="java.util.ArrayList"%>
<%@page import="dto.BillDTO"%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>

    <head>
        <meta charset="utf-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width,initial-scale=1">
        <title>Thông tin khách hàng</title>
        <!-- Favicon icon -->
        <link rel="icon" type="image/png" sizes="16x16" href="images/favicon.png">
        <!-- Custom Stylesheet -->
        <link href="./plugins/tables/css/datatable/dataTables.bootstrap4.min.css" rel="stylesheet">

        <link rel="stylesheet" href="https://www.w3schools.com/w3css/4/w3.css">
        <link rel="stylesheet" href="https://fonts.googleapis.com/css?family=Montserrat">
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
        <link href="css/style.css" rel="stylesheet"> 
    </head>

    <body>

        <!--*******************
            Preloader start
        ********************-->
        <div id="preloader">
            <div class="loader">
                <svg class="circular" viewBox="25 25 50 50">
                <circle class="path" cx="50" cy="50" r="20" fill="none" stroke-width="3" stroke-miterlimit="10" />
                </svg>
            </div>
        </div>
        <!--*******************
            Preloader end
        ********************-->


        <!--**********************************
            Main wrapper start
        ***********************************-->
        <div id="main-wrapper">

            <!--**********************************
                Nav header start
            ***********************************-->
            <!--            <div class="nav-header">
                            <div class="brand-logo">
                                <a href="index.html">
                                    <b class="logo-abbr"><img src="images/logo.png" alt=""> </b>
                                    <span class="logo-compact"><img src="./images/logo-compact.png" alt=""></span>
                                    <span class="brand-title">
                                        <img src="images/logo-text.png" alt="">
                                    </span>
                                </a>
                            </div>
                        </div>-->
            <!--**********************************
                Nav header end
            ***********************************-->

            <!--**********************************
                Header start
            ***********************************-->

            <!--**********************************
                Header end ti-comment-alt
            ***********************************-->

            <!--**********************************
                Sidebar start
            ***********************************-->
            <ul class="metismenu" id="menu">
                <nav class="w3-sidebar w3-bar-block w3-small w3-hide-small w3-center">
                    <!-- Avatar image in top left corner -->
                    <img src="https://www.w3schools.com/w3images/avatar_smoke.jpg" style="width:100%">

                    <a href="Bill" class="w3-bar-item w3-button w3-padding-large w3-black">
                        <i class="fa fa-file w3-xxlarge"></i>
                        <p>Hóa đơn</p>
                    </a>

                    <a href="customerlist.jsp" class="w3-bar-item w3-button w3-padding-large w3-hover-black">
                        <i class="fa fa-user w3-xxlarge"></i>
                        <p>Thông tin khách hàng</p>
                    </a>


                    <a href="#photos" class="w3-bar-item w3-button w3-padding-large w3-hover-black">
                        <i class="fa fa-dollar w3-xxlarge"></i>
                        <p>Thống kê lợi nhuận</p>
                    </a>
                    <a href="itemlist.jsp" class="w3-bar-item w3-button w3-padding-large w3-hover-black">
                        <i class="fa fa-cart-plus w3-xxlarge"></i>
                        <p>Thông tin món đồ</p>
                    </a>
                </nav>
            </ul>
            <!--**********************************
                Sidebar end
            ***********************************-->

            <!--**********************************
                Content body start
            ***********************************-->
            <div class="content-body">

                <!--            <div class="row page-titles mx-0">
                                <div class="col p-md-0">
                                    <ol class="breadcrumb">
                                        <li class="breadcrumb-item"><a href="javascript:void(0)">Dashboard</a></li>
                                        <li class="breadcrumb-item active"><a href="javascript:void(0)">Home</a></li>
                                    </ol>
                                </div>
                            </div>-->
                <!-- row -->

                <div class="container-fluid">
                    <div class="row">
                        <div class="col-12">
                            <div class="card">
                                <div class="card-body">
                                    <div id ="header_title_table">
                                        <h4 class="card-title">Thông tin hóa đơn</h4>
                                        <a herf="customer-update.html"><input id="create_customer" type="submit" name="action" value="Thêm hóa đơn"/></a>
                                    </div>
                                    <div class="table-responsive">

                                        <%
                                            ArrayList<BillDTO> bill = (ArrayList<BillDTO>) session.getAttribute("bill");
                                            String error = (String) request.getAttribute("message");
                                            BillDAO daoBill = new BillDAO();
                                            ItemDAO daoItem = new ItemDAO();
                                            ArrayList<ItemDTO> itemdto = daoItem.getAllItem();
                                            if (bill != null) {

                                        %>
                                        <table class="table table-striped table-bordered zero-configuration">
                                            <thead>
                                                <tr>
                                                    <th>Họ Tên</th>
                                                    <th>Tên đồ vật</th>
                                                    <th>Ngày gửi</th>
                                                    <th>Số ngày gửi</th>
                                                    <th>Function</th>
                                                </tr>
                                            </thead>
                                            <tbody>
                                                <%for (BillDTO b : bill) {%>
                                            
                                                <tr>
                                                    <td style="text-align: center;padding: 10px 5px;"><%= daoBill.getCustomerByItemID(b).getFullname()%></td>
                                                    <td style="text-align: center;padding: 10px 5px;"><%= daoItem.viewItem(b.getItemID()).getItemName()%></td>
                                                    <td style="text-align: center;padding: 10px 5px;"><%= b.getBillBeginDate()%></td>
                                                    <td style="text-align: center;padding: 10px 5px;"><%= b.getNumberDays()%></td>
                                                    <td style="text-align: center;padding: 10px 5px;"><button><a style="text-decoration: none;padding: 10px;" href="DetailController?action=detail&id=<%= b.getItemID()%>">Chi tiết</a></button></td>
                                                </tr>
                                            
                                            <%}%>
                                            </tbody>
                                        </table>

                                        <% }
                                        %>

                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
                <!-- #/ container -->
            </div>
            <!--**********************************
                Content body end
            ***********************************-->


            <!--**********************************
                Footer start
            ***********************************-->
            <div class="footer">
                <div class="copyright">
                    <!--                <p>Copyright &copy; Designed & Developed by <a href="https://themeforest.net/user/quixlab">Quixlab</a> 2018</p>-->
                </div>
            </div>
            <!--**********************************
                Footer end
            ***********************************-->
        </div>
        <!--**********************************
            Main wrapper end
        ***********************************-->

        <!--**********************************
            Scripts
        ***********************************-->
        <script src="plugins/common/common.min.js"></script>
        <script src="js/custom.min.js"></script>
        <script src="js/settings.js"></script>
        <script src="js/gleek.js"></script>
        <script src="js/styleSwitcher.js"></script>

        <script src="./plugins/tables/js/jquery.dataTables.min.js"></script>
        <script src="./plugins/tables/js/datatable/dataTables.bootstrap4.min.js"></script>
        <script src="./plugins/tables/js/datatable-init/datatable-basic.min.js"></script>

    </body>
</html>
