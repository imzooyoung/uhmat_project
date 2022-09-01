<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>    
<!DOCTYPE html>
<html>
<head> 
<meta charset="UTF-8">
<title>Insert title here</title>
<link href="css/main.css" rel="stylesheet" type="text/css">
<!-- <link href="css/header.css" rel="stylesheet" type="text/css"> -->
<!-- <link href="css/footer.css" rel="stylesheet" type="text/css"> -->
</head>
<body>
	<!-- 헤더 들어가는곳 -->
		<jsp:include page="inc/header.jsp" flush="false" />
	<!-- 헤더 들어가는곳 -->
	
	<!-- 사이드바 들어가는곳 -->
<%-- 		<jsp:include page="inc/sidebar.jsp" flush="false" /> --%>
	<!-- 사이드바 들어가는곳 -->
<!-- 	<hr>		 -->
			
	<!-- 메인페이지 영역 시작 -->
	<div class="mainContainer">	
	<!-- 검색창 시작 -->
	<div class="mainImageContainer">
		<img class="mainImage" src="image/mainSample3.jpg">
	</div>
	<!-- 검색창 끝 -->
	
	<!-- 본문들어가는 곳 시작 -->
		<!-- 최고의 리뷰 순위 시작 -->
		<div class="mainViewContainer">	
			<div class="rankContainer">
				<div class = "imgContainer" ><h2>최다 좋아요 리뷰</h2>
					
				</div>
			</div>
		<!-- 최고의 리뷰 순위 끝 -->
		
		<!-- 최신 리뷰 시작 -->
			<div class="rankReview">
				<div class = "imgContainer"><h2>어맛 최신 리뷰</h2>
					
				</div>
			</div>
		<!-- 최신 리뷰 끝 -->
		
		<!-- 어맛 추천 리뷰 시작 -->
			<div class="rankReview">
				<div class = "imgContainer"><h2>어맛 추천 리뷰</h2>
					
				</div>
			</div>
		<!-- 어맛 추천 리뷰 끝 -->
		
		<!-- 리뷰어 창 시작 -->
			<div class="reviewer">
				<div class = "imgContainer"><h2>어맛 리뷰어</h2>
					<div class = "mainView" >
						<ul>
							<li><img src="image/sample1.jpg" width="100%" /></li>
							<li><img src="image/sample2.jpg" width="100%" /></li>
							<li><img src="image/sample3.jpg" width="100%" /></li>
							<li><img src="image/sample4.jpg" width="100%" /></li>
							<li><img src="image/sample5.jpg" width="100%" /></li>
						</ul>
					</div>
				</div>
			</div>
		</div>	
		<!-- 리뷰어 창 끝 -->
		
		
		
	</div>
<!-- 메인페이지 영역 끝 -->		
<!-- <hr> -->
		
	<!-- 푸터 들어가는곳 시작 -->
		<jsp:include page="inc/footer.jsp" flush="false" />
	<!-- 푸터 들어가는곳 끝 -->		

</body>
</html>   