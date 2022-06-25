<%-- 
    Document   : item_update
    Created on : Jun 23, 2022, 12:30:30 AM
    Author     : Admin
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        
        <script>
            var loadFile = function (event) {
                var reader = new FileReader();
                reader.onload = function () {
                    var output = document.getElementById('output');
                    output.src = reader.result;
                };
                reader.readAsDataURL(event.target.files[0]);
            };// code display image upload
        </script>
        <style>
            #imageOut{
                margin: auto;
                position: absolute;
                float: left;
                width: 150px;
                height: 80px;
                
            }
            #output{
                width: 150px;
                height: 80px;            }
        </style>
    </head>
    <body>
        <%
            String itemID = request.getParameter("itemID");
            String itemName = request.getParameter("itemName");
            String itemPic = request.getParameter("itemPic");
            String customerID = request.getParameter("customerID");
            String storeID = request.getParameter("storeID");
            String itemSendingDate = request.getParameter("itemSendingDate");
            String itemGettingDate = request.getParameter("itemGettingDate");
            String statusID = request.getParameter("statusID");
            String isKeep = request.getParameter("isKeep");
        %>
        <div>
            <div class="container-login100" style="background-image: url('images/bg-01.jpg') "</div>

            <form action="UpdateItemController" method="post" enctype="multipart/form-data">
                <h1 style="background-color: #ff6699;align-items: flex-start; font-size: 40px;margin-top:10px;border-radius: 10px;text-align: center;padding: 20px" >Thông Tin Vật Phẩm </h1></br>

                <div width="700" height="500" border="2" cellpaddixng="5" style="border-radius: 10px;background-color: #ff9999;max-width: 50rem;padding:20px;margin: 10px"></br>


                    <p style="font-size: 20px;border-radius: 10px;font-family: inherit">Mã Món Đồ:</p>
                    <input type="text" name="itemID" placeholder="Nhập Mã Món Đồ" value="<%= itemID%>" style="width: 700px;height: 30px;border-radius: 10px;padding: 20px" disabled/></br>



                    <p style="font-size: 20px;border-radius: 10px;font-family: inherit">Tên Món Đồ:</p>
                    <input type="text" name="itemName" placeholder="Nhập Tên Món Đồ" value="<%= itemName%>" style="width: 700px;height: 30px;border-radius: 10px;padding: 20px"/></br>
                    <p style="font-size: 20px;border-radius: 10px;font-family: inherit">Hình ảnh:</p>
                    <input type="file" id="image_input" name="itemPic" accept="image/*" onchange="loadFile(event)" value="<%= itemPic%>"/></br>
                    <div id="imageOut"><img id="output"/></div></br>
                    
                    <p style="font-size: 20px;border-radius: 10px;font-family: inherit;margin-top: 100px">Mã Khách Hàng:</p></br>
                    <input type="text" name="customeID" placeholder="Mã Khách Hàng" value="<%= customerID%>" style="width: 700px;height: 30px;border-radius: 10px;padding: 20px" disabled></br>
                    <p style="font-size: 20px;border-radius: 10px;font-family: inherit">Mã Cửa Hàng:</p>
                    <input type="text" name="storeID" placeholder="Nhập Mã Cửa Hàng" value="<%= storeID%>" style="width: 700px;height: 30px;border-radius: 10px;padding: 20px" disabled></br>


                    <p style="font-size: 20px;border-radius: 10px;font-family: inherit">Ngày Cầm:</p>
                    <input type="text" name="itemSendingDate" value="<%= itemSendingDate%>" style="width: 700px;height: 30px;border-radius: 10px;padding: 20px"/></br>
                    <p style="font-size: 20px;border-radius: 10px;font-family: inherit">Ngày Lấy:</p>
                    <input type="text" name="itemGettingDate" value="<%= itemGettingDate%>" style="width: 700px;height: 30px;border-radius: 10px;padding: 20px"/></br>
                    <p style="font-size: 20px;border-radius: 10px;font-family: inherit">Tình trạng:</p>
                    <input type="text" name="isStatus" value="<%= statusID%>" style="width: 700px;height: 30px;border-radius: 10px;padding: 20px"/></br>
                    <p style="font-size: 20px;border-radius: 10px;font-family: inherit">Giữ:</p>
                    <input type="text" name="isKeep" value="<%= isKeep%>" style="width: 700px;height: 30px;border-radius: 10px;padding: 20px"/></br>

                    <input type="submit" name="action" value="update" style="color:green;width: 700px;background-color: beige;border-radius: 10px ;text-align: center;background-color: aqua;margin-top: 20px;padding: 20px;font-size: 20px"/>

                </div>
            </form>
        </div>
    </body>
</html>
