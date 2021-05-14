<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html>
<html>
<head>
<meta name="viewport" content="width=device-width, initial-scale=1" />
<link rel="stylesheet"
	href="https://stackpath.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css"
	integrity="sha384-HSMxcRTRxnN+Bdg0JdbxYKrThecOKuH5zCYotlSAcp1+c8xmyTe9GYg1l9a69psu"
	crossorigin="anonymous">

<!-- Optional theme -->
<link rel="stylesheet"
	href="https://stackpath.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap-theme.min.css"
	integrity="sha384-6pzBo3FDv/PJ8r2KRkGHifhEocL+1X2rVCTTkUfGk7/0pbek5mMa1upzvWbrUbOZ"
	crossorigin="anonymous">
<link rel="stylesheet" href="/index.css">
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
	<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.3/umd/popper.min.js"></script>
	<link rel="stylesheet" href="https://ajax.aspnetcdn.com/ajax/jquery.ui/1.10.4/themes/redmond/jquery-ui.css">
	<script src="https://ajax.googleapis.com/ajax/libs/jqueryui/1.12.1/jquery-ui.min.js"> </script>
	<title>GadgetBadget</title>

    <style >
        #name-warning-message{
            padding-left: 340px;
            font-size: 18px;
            font-weight: bold;
        }
        #description-warning-message{
            padding-left: 340px;
            font-size: 18px;
            font-weight: bold;
        }
        #category-warning-message{
            padding-left: 340px;
            font-size: 18px;
            font-weight: bold;
        }
    </style>
</head>
<body>
	<jsp:include page="home.jsp"></jsp:include>
	<div class="container">
		<div class="list">
			<div class="card">
				<h2>New Research</h2>
				<hr />
				<form:form action="newResearch" method="post"
					modelAttribute="research" id="addResearch">
                    <input type="hidden" id="error" name="error" value="">
					<form:label path="name" id="name">Name:</form:label>
					<form:input path="name" id="name"/>
                    <div id="span-container">
                        <span id="name-warning-message" class="text-danger"> </span>
                    </div>
					<br />
	
					<form:label path="description">Description:</form:label>
					<form:textarea path="description" cols="25" rows="5" id="description" />
                    <div id="span-container">
                        <span id="description-warning-message" class="text-danger"> </span>
                    </div>
					<br />
	
					<form:label path="category">Category:</form:label>
					<form:input path="category"  id="category"/>
                    <div id="span-container">
                        <span id="category-warning-message" class="text-danger"> </span>
                    </div>
					<br />

					<form:button>Save</form:button>
				</form:form>
			</div>
		</div>
	</div>

    <script>
        $(document).ready(function(){
            $('#name-warning-message').hide();
            $('#description-warning-message').hide();
            $('#category-warning-message').hide();

            var name_error=false;
            var description_error=false;
            var cateogory_error=false;

            $('#name').focusout(function(){
                validate_name();
            });

            $('#description').focusout(function(){
                validate_description();
            });

            $('#category').focusout(function(){
                validate_category();
            });

            function validate_name(){
                var name=$('#name').val();
                var name_regex=/^[a-zA-Z ]{3,15}$/;
                if(name.length==''){
                    $('#name-warning-message').show();
                    $('#name-warning-message').html("Please Enter the Name !");
                    first_name_error=true;
                }
                else if(name.length<3){
                    $('#name-warning-message').show();
                    $('#name-warning-message').html("Name is Not Valid !");
                    first_name_error=true;
                }
                else if(!name_regex.test(name)){
                    $('#name-warning-message').show();
                    $('#name-warning-message').html("Name Must Not Contain Any Digits Or Symbols !");
                    first_name_error=true;
                }
                else{
                    $('#name-warning-message').hide();
                }
            }

            function validate_description(){
                var description=$('#description').val();

                if(description.length==''){
                    $('#description-warning-message').show();
                    $('#description-warning-message').html("Please Enter the Description !");
                    last_name_error=true;
                }
                else if(last_name.length<3){
                    $('#description-warning-message').show();
                    $('#description-warning-message').html("Description is Not Valid !");
                    last_name_error=true;
                }
                else{
                    $('#description-warning-message').hide();
                }
            }
            function validate_category(){
                var category=$('#category').val();

                if(category.length==''){
                    $('#category-warning-message').show();
                    $('#category-warning-message').html("Please Enter the Category !");
                    last_name_error=true;
                }
                else if(category.length<3){
                    $('#category-warning-message').show();
                    $('#category-warning-message').html("Category is Not Valid !");
                    last_name_error=true;
                }
                else{
                    $('#category-warning-message').hide();
                }
            }

            $('#addResearch').submit(function(){
                document.getElementById("error").value = "";
                name_error=false;
                description_error=false;
                cateogory_error=false;

                validate_name();
                validate_description();
                validate_category();

                if((name_error==false)&&(description_error==false)&&(cateogory_error==false)){
                    document.getElementById("error").value = "1";
                    return true;
                }
                else{
                    document.getElementById("error").value = "0";
                    return false;
                }
            });
        });
    </script>
</body>
</html>