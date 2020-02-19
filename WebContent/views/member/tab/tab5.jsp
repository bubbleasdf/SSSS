<%-- 공용 자랑 게시판 입니다. --%>


<%@page import="com.kh.petner.member.model.vo.Member"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
        <% 
        	Member m = (Member)session.getAttribute("member");
        
        	System.out.println("Member : "+m);
        
        %>
        

<!DOCTYPE html>
<html>

<head>

  <meta charset="utf-8">

  <title>Shop Homepage - Start Bootstrap Template</title>

  <!-- Bootstrap core CSS -->
  <link href="/PETNER/resources/css/member/tab/bootstrap.min.css" rel="stylesheet">
<link rel="stylesheet" href="/PETNER/resources/css/member/tab/jquery-te-1.4.0.css">

  <!-- Bootstrap core JavaScript -->
  <script src="/PETNER/resources/css/member/tab/jquery.min.js"></script>
  <script src="/PETNER/resources/css/member/tab/bootstrap.bundle.min.js"></script>
  <script src="/PETNER/resources/js/member/tab/jquery-te-1.4.0.min.js"></script>
  
	<link rel="stylesheet" href="/PETNER/resources/css/bootstrap.css" type="text/css">

</head>

<!-- 각 menu를 눌렀을 때 ListPage가 바뀌게 해주는 스크립트 -->

 <script>
 //시작하면 우측 리스트 화면 띄우기
 	$(function(){
 		listFunction('1');
 	});
 
 			// 좌측 메뉴 버튼
          function listFunction(menu){
        	  console.log('listFunction 실행 : '+menu);

             $.ajax({
                  url:"/PETNER/selectList.bo",
                  type:"get",
                  data: { type:menu},
                  
                  // 리스트 만들어 주기.
                  success:function(data){
                    console.log("성공 : "+data);
                   
                    //페이지 첫화면 load
                    $('#pageArea').load('tab/BoardTab/mpBoardListForm.jsp');
                    
                 	//리스트 초기화
                    $('#listDiv').html("");
                    
                 	
                    $.each(data, function(index, item){
                      var $1div1 = $('<div class="col-lg-4 col-md-6 mb-4">');
                      var $2div2 = $('<div class="card h-100">');
                      
                      console.log("이미지 경로 테스트 : "+ item.boardfile);
                      
                      
                      
                      //img와 h4의 a태그에 모달 링크를 넣어줘야 한다. data-target="#modal-login" data-toggle="modal"
                      //빈 modal을 띄우고 ajax로 처리해야 할지도?
                      
                    		  //<a class="btn btn-default" data-target="#modal" data-toggle="modal">자세히 알아보기</a>
                    		  
                    		  
                    		  //data-target으로 modal 불러오는거 실패
//                      var $3a = $('<a>').attr('class','btn btn-default').data('target','#mpBoardModal').data('toggle','modal');										// a태그에 detail부분을
                      var $3a = $('<a>').attr({class:'btn btn-default', href:'javascript:openModal('+item.bno+');'});										// a태그에 detail부분을
                    	  									
                      var $4img = $('<img class="card-img-top">').attr('src','/PETNER/resources/img/member/mpBoard/'+item.boardfile); //getPetPic() 
                      $3a.append($4img);
						

                      var $3div3 = $('<div class="card-body">');

                      var $4h4 = $('<h4 class="card-title">');
                      var $5a = $('<a>').attr('class','btn btn-default').data('target','#mpBoardModal').data('toggle','modal').text(item.btitle);     //  글제목 detail부분을 넣자.
                      $4h4.append($5a);
                      
                      var $4h5 = $('<h5>');
                      var $4p = $('<p class="card-text">').text("작성자 : "+ item.bwriter +"의 글입니다.");               // 
                      $4h5.append($4p);
                      $3div3.append($4h4);

                      $3div3.append($4h5);



                      var $3div4 = $('<div clas="card-footer">');
                      
                      
                      //hidden으로 글번호 값을 저장한다.
                      var $4input = $('<input type="hidden" id="hiddenBno" value='+item.bno+'>');
                      
                      
                      
                      var text="";
                      for(i=0; i<5; i++){
                    	  if(item.starCount>0){
                    		  text+="&#9733;";
                    	  }else{
                    		  text+="&#9734;";
                    	  } // if else
                      }//for
                      
                      var $4small = $('<small class="text-muted">').html(text);  // 동적으로 수정해줄 필요가 있다.
                      $3div4.append($4small);
                      
                      
                      //이 div에 글번호 input hidden을 붙인다.
                      $3div4.append($4input);

                      
                      $2div2.append($3a);
                      $2div2.append($3div3);
                      $2div2.append($3div4);

                      $1div1.append($2div2);
                      
                      console.log("$1div1 : " + $1div1);
                      
                      $('#listDiv').append($1div1);
                      
                    }); //each 
                  }
                  , error:function(data){
                    console.log('에러에러에러에러 : '+data);
                    alert("code:"+request.status+"\n"+"message:"+request.responseText+"\n"+"error:"+error);
                  }
              });  //ajax
          }  //listFunction
          
          //글쓰기
          function insertBoard(){
        	  
        	  $('#listArea').html("");	//현재 목록화면 가리기
        	  $('#listDiv').html("");	//현재 리스트화면 가리기
        	  
        	  
        	  
        	  $('#pageArea').load("tab/BoardTab/mpBoardInsertForm.jsp");
        	  
          } // insertBoard
          
          
          
          // 검색창 아직 안만듬
          function searchBoard(){
        	  
        	  $('#searchForm').submit();
        	  
          }//searchBoard
          
        </script>


<!-- 모달 창 가운데로 모으는 스타일 -->
<style>

	.modal {
        text-align: center;
	}
 
	.modal-content {
	        display: inline-block;
	        text-align: left;
	        vertical-align: middle;
	}
	@media screen and (min-width: 768px) { 
	        .modal:before {
	                display: inline-block;
	                vertical-align: middle;
	                content: " ";
	                height: 100%;
	        }
	}

</style>


<body>

  <!-- Page Content -->
  <!-- menu -->

  <div class="container">
      <div style="padding-top:20px;"></div>
    <div class="row" style="width:1300px;">
      <div class="col-lg-3">
        <h1 class="my-4"><%=m.getUserName() %></h1>
        <div class="list-group">
          <a href="#" class="list-group-item" onclick="listFunction('1')">전체</a>
          <a href="#" class="list-group-item" onclick="listFunction('3')">인기</a>
          <a href="#" class="list-group-item" onclick="listFunction('2')">내거</a>
          <br>
        <div style="padding-left: 10px;, padding-right: 10px;">
	          <button class="btn btn-info" onclick="insertBoard();">글쓰기</button> 
	          <br>
        </div>
        
        
        
        <div style="padding-left: 10px;, padding-right: 10px;">
        	<form action="/PETNER/mpBoard.se" method="get" id="searchForm">
        	<select name="sel_category">
        		<option>제목</option>
        		<option>작성자</option>
        	</select>
        	<input type="text" name="searchId">
	          <button class="btn btn-info" onclick="searchBoard();">검색</button>
	          </form> 
        </div>
        
        </div>
      </div>  <!-- /.col-lg-3 -->


      <!-- right display -->


      <!-- 협찬,추천,이벤트 배너 -->
      <div class="col-lg-9" >
      
	      <div id="pageArea">
	      
	      
	      </div> <!-- pageArea -->
	      
	      <!-- 글 ListPage -->
	      <!-- 글 만큼 반복문 -->
	      <div class="row" id="listDiv">                                          
	
	       
	      </div> <!-- /.row #listDiv-->
      

      </div>  <!-- /.col-lg-9 #pageArea-->
`
		
    </div> <!-- /.row -->

  </div>
  <!-- /.container -->




<script>
	//모달창 동적으로 만들어서 띄우자
	
	function openModal(bno){
		console.log("openModal 실행 : "+bno)
		  $.ajax({
			  url: "/PETNER/bSelect.bo"
			  , type:"get"
			  , data:{ bno:bno}
		  
		  
			  , success:function(data){
				  console.log("받아온 data : "+data);
				  console.log("받아온 data : "+data.btype);
				  
				  //값을 만들어서 모달을 만들어 띄우자
				  
				  //이미지 띄우기 전에 초기화
				  var $img = $('<img>').attr({
					  id:"img-banner-area",width:"100%", src:"/PETNER/resources/img/member/mpBoard/"+data.boardfile
				  }).css("max-height","590px");
				  
				  //append하기 전에 초기화
				  $('div.img-banner-area').html("");
				  $('div.img-banner-area').append($img);
				  
				  
				  var $input1=$('<input type="text" name="userName" value='+data.bwriter+' class="form-control" style="margin-bottom:8px">')
				  .prop('readonly','true');
				  var $select1=$('<select name="Type" id="selType" required class="form-control">');
				  
				  var $option1=$('<option value="1" >전체 공유</option>');
				  var $option2=$('<option value="2" >나만 공유</option>');
				  
				  
				  
				  if(data.btype== 1){
					$option1.prop('selected','true');	
				  }else if(data.btype==2){
					  $option2.prop('selected','true');  
				  }else if(data.btype==3){
					  
				  }else{
					  alert("타입을 알 수 없는 글 입니다. ")
				  }
				  
				  
				  
				  $select1.html("");
				  $select1.append($option1);
				  $select1.append($option2);
				  
				  $select1.prop('disabled','true');
				  
				  
				  $('div.col-md-8').html("");
				  $('div.col-md-8').append($input1);
				  $('div.col-md-8').append($select1);
				  
				  $('div.modal-header').html("<h3 align='center'>"+data.btitle+"</h3>");
				  
				  
				  //제목 지정
				  $('input[name=bTitle]').val(data.btitle).prop("readonly","true");
				  
					//jqte를 띄우는 부분
				  	$(".textArea").jqte({indent: false,
						link: false, linktypes: ["Web URL", "E-mail", "Picture"],
						source: false,sub: false, remove: false});
					$('.jqte_editor').attr("contenteditable","false").css("cssText","height: 300px; resize: none;");
			  		$('.jqte_editor').jqteVal(data.bcontent);
				  
				  
				  //modal 세팅완료
				  
				  
				  
				  
				  
				  // modal 띄움
				  $('#mpBoardModal').modal();
				  
				  
				  
			  }//success
			  
			  , error:function(request,status,error){
				  console.log("에라에러에러 : ");
				  alert("code:"+request.status+"\n"+"message:"+request.responseText+"\n"+"error:"+error);
			  } //errorfunction
		  });
	}
	
</script>


<form action="/PETNER/mpUpdate.bo" id="bUpdateForm" method="POST" 
			enctype="multipart/form-data">
    <div class="row">
        <div class="modal" id="mpBoardModal" tabindex="-1"  > <!-- tabindex : ?????-->
            <!-- <div class="modal-dialog">  -->
                <div class="modal-content" style="width:975px; height:670px">
                    <div class="modal-header" style="align:center;">      <!--모달의 제목 부분-->

                        <button class="close" data-dismiss="modal">&times;</button>  <!-- 모달창을 닫을 수 있는 버튼-->
                    </div>
                                
                    
	<div class="row" style="width:100%; height:80%; background-color: rgba( 200, 200, 200, 0.2);">
			<div class="col-md-6" style="width:100%; height:100%;">
				<div class="img-banner-area" style="max-width:100%; max-height:600px;">
					<!-- 요기에 이미지를 넣는다. -->
				</div>
			</div> <!-- col-md-6 -->

		<div class="col-md-6" style="width:100%; height:100%;">
			<div class="form-group">
			 <div class="row">
			 	<div class="col-md-3">
				 	<label class="form-control">작성자</label>
				 	<label class="form-control">카테고리</label>
			 	</div>
			 	<div class="col-md-8">			 	
				 	
				 	<!--  요기에 온갖 input값 등을 넣는다. -->
					
			 	</div>
			 	
			 </div>
				
				
				
			</div>	<!-- form-group -->
		
			<div class="form-group">
				<input type="text" name="bTitle" class="form-control"  required> <!-- 제목부분 -->
				<div class='textArea' name="jqteArea"></div> <!-- jquery text editor -->
			</div>
			
			<div class="form-group text-center">
				<button class="btn btn-primary" onclick="changeBtn()" id="chBtn">수정</button>
				<button class="btn btn-warning" onclick="delDetail()" id="delBtn" style="display:none">삭제</button>
			</div>
		</div> <!-- col-md-6 -->
		</div> <!-- row -->
		
		
		
                </div>
  <!--  </div> -->          
        </div>
    </div>
    </form>
    
    
    
    
<script>
	function LoadImg(value){
		 if(value.files && value.files[0])
		 {
			var reader=new FileReader();
			
			reader.onload=function(e){
				$('.img-banner-area').css('background','white');
				$('#img-banner-area').attr('src',e.target.result).css('display','block');
				$('h1.text-center').hide();
			}
			reader.readAsDataURL(value.files[0]);
		 }//if
	}

//수정 버튼을 누르면 readonly가 풀리고 각종 내용을 수정할수 있게 된다.
	function changeBtn(){
	
		var user1 = $('input[name=userName]').val();
		var user2 = <%=m.getUserEmail()%>
		if(!(user1.equals(user2)) || user1.equals("admin@a.com")){//작성자 및 관리자인지 확인하고
			alert('작성자(혹은 관리자)만 수정이 가능합니다. ');
		}else{
			//버튼 이름 바꿔주고
			$('#chBtn').val('수정 완료');
			
			//submit으로 바꿔줌
			$('#chBtn').attr({onclick:'', type:'submit'});
			
			//삭제 버튼 보여줌
			$('#delBtn').css('display','block');
		
			//readonly등을 수정가능하게 바꿔줌.
			//카테고리 수정
			$('#selType').prop('disabled','false');
			
			//타이틀 수정
			$('input[name=bTitle]').prop("readonly","false");
	
			//내용 수정
			$('.jqte_editor').attr("contenteditable","true").css("cssText","height: 300px; resize: none;");
	
			//이미지 수정 버튼 등장
			var $imgBtn=$('<input type="file" name="n_file"accept=".gif, .jpg, .png" style="display: none;" onchange="LoadImg(this)">')
			$('div.img-banner-area').append($imgBtn);		
		}//if else
	

	}//changeBtn

	function delDetail(){
		var user1 = $('input[name=userName]').val();
		var user2 = <%=m.getUserEmail()%>
		if(!(user1.equals(user2)) || user1.equals("admin@a.com")){//작성자 및 관리자인지 확인하고
			alert('작성자(혹은 관리자)만 삭제가 가능합니다. ');
		}else{
			//버튼 이름 바꿔주고
			$('#chBtn').val('수정 완료');
			
			//submit으로 바꿔줌
			$('#chBtn').attr({onclick:'', type:'submit'});
		
			//readonly등을 수정가능하게 바꿔줌.
			//카테고리 수정
			$('#selType').prop('disabled','false');
			
			//타이틀 수정
			$('input[name=bTitle]').prop("readonly","false");
	
			//내용 수정
			$('.jqte_editor').attr("contenteditable","true").css("cssText","height: 300px; resize: none;");
	
			//이미지 수정 버튼 등장
			var $imgBtn=$('<input type="file" name="n_file"accept=".gif, .jpg, .png" style="display: none;" onchange="LoadImg(this)">')
			$('div.img-banner-area').append($imgBtn);		
		}//if else
	}//delDetail
</script>




</body>

</html>
