<%@page import="com.kh.petner.member.model.vo.Member"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
    <% Member m = (Member)session.getAttribute("member");  %>
    
<!DOCTYPE html>
<html>
	<head>
		<!-- Mobile Specific Meta -->
		<meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
		<!-- Author Meta -->
		<meta name="author" content="colorlib">
		<!-- Favicon-->
		<link rel="shortcut icon" href="/PETNER/resources/img/common/small_logo.jpg">
		<!-- meta character set -->
		<meta charset="UTF-8">
		<!-- Site Title -->
		
		<title>My Page</title>
		<script src="http://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
		<link rel="stylesheet" href="/PETNER/resources/css/member/main_hyon.css" type="text/css">
		
		<link href="https://fonts.googleapis.com/css?family=Poppins:100,200,400,300,500,600,700" rel="stylesheet">
		<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/lightgallery/1.6.11/css/lightgallery.min.css">
	
	
		<link rel="stylesheet" href="/PETNER/resources/css/font-awesome.min.css">
		<link rel="stylesheet" href="/PETNER/resources/css/bootstrap.css" type="text/css">
		<link rel="stylesheet" href="/PETNER/resources/css/main.css" type="text/css">
		
		<script src="/PETNER/resources/js/vendor/jquery-3.4.1.min.js" type="text/javascript"></script>
		<script src="/PETNER/resources/js/vendor/bootstrap.min.js" type="text/javascript"></script>
		
		
	</head>
	
	
	<body>
	
    <!--######## End Header Area ########-->
	<div style="padding-top:80px;"></div>
    <!--tap-->
    <script>

     $('document').ready(function () {
    	 
    	 var $row = $('<div class="row>"');
    	 var $p = $('<p>').text('textValue');
    	 
    	 
    	 $row.append($p);
    	 
    	 
    	 console.log($row);
    	 
    	 
    	$('#tab_container').load("tab/tab1.jsp");

    	// $('.tab_Chat').load("tab/chat.jsp");

    	
    	//tab을 눌렀을 때
        $("ul.tabs li").click(function () {
        	$("#tab_container").hide();

            $("ul.tabs li").removeClass("active").css("color", "#333");
            $(this).addClass("active").css("color", "green");

            console.log($(this).attr('rel'));
            console.log($('ul.tabs li.active').attr('rel'));

            $("#tab_container").load("tab/"+$('ul.tabs li.active').attr('rel')+".jsp");

            $("#tab_container").fadeIn();

        });
    });


        //펫 정보 수정 페이지로 이동
        function updatePet(petName){
        	location.href="<%=request.getContextPath()%>/pSel.pe?petName="+petName;
        }

        //펫 대표 설정
        function topPet(petName){
        	location.href="/PETNER/pUpdate.pe?petName="+petName;
        }

        //펫 삭제
        function deletePet(petName){

        	location.href="/PETNER/pDelete.pe?petName="+petName;
        }
        
        //채팅창 띄우기
        function tab_Chat(){
        	$(this).hide();
        	
        }

    </script>
    <!--tap end-->


    <!-- Start Align Area -->
    <section class="whole-wrap">
        <div class="container" style="margin:50px">


 <!--start tab area-->
    
    	<div class="container">
	        <ul class="tabs">
	            <li rel="tab1">회원 정보 관리</li>
	            <!-- 
	            <li rel="tab2">호텔 예약 조회</li>
	            <li rel="tab3">1:1 문의 내역</li>
	            <li rel="tab5">
	            펫트너 월드
	            <a class="btn btn-default" href="tab/chat.jsp" onclick="tab_Chat(); window.open(this.href,'','width=370, height=720'); return false;">Open</a>
	            </li> 
	            -->
	        </ul>
    	</div> <!-- container.tabs -->

	    <div class="container">
	    <br>
	    <br>
	        <div id="tab_container" style="width:100%;">
		        	<!-- 요기에 불러옴 -->
	
	        </div> <!-- .tab_container -->
	    </div><!-- container -->
	


</div> <!-- container -->
</section>


<script>
	function deleteMember(){
		 if (confirm("정말 탈퇴하시겠습니까??") == true){    //확인
			 $('#delForm').submit();
		 }else{   //취소
		     return false;
		 }
		 
	}//deleteMember
</script>

</body>

</html>