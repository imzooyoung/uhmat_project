<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %> 
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>ServiceCenter</title>
<script src='https://kit.fontawesome.com/a076d05399.js' crossorigin='anonymous'></script> 
<link rel="preconnect" href="https://fonts.googleapis.com"> 
<link rel="preconnect" href="https://fonts.gstatic.com" crossorigin> 
<link href="https://fonts.googleapis.com/css2?family=Luckiest+Guy&family=Poor+Story&display=swap" rel="stylesheet"> 
<link href="css/serviceCenterMain.css" rel="stylesheet">

</head>
<body style="width:100%;">
		<!-- 헤더 들어가는곳 -->
		<jsp:include page="../inc/header.jsp"></jsp:include>
		<!-- 헤더 들어가는곳 -->
		
		<h1 style="text-align: center; font-size: 50px">고객센터</h1>
		
		<!-- 왼쪽 사이드바 -->
		<div style="padding:30px; background:#fafafa;" id="sidebar">
					<div class="sub_left_menu noto400 font_15">
						<a href="NoticeList.sc">공지사항</a><br>
						<a href="FAQList.sc">FAQ</a>
					</div>
		</div>

	<div class="clear"></div>

	<article id="front" style="margin-left: 23%;">
		<section>
			<section id="search">
				<div>
					<span>FAQ</span> 검색하기
				</div>

				<form name="qna_board_search_frm" method="post" action="FAQList.sc">
					<input type="text" name="keyword" value="" placeholder="검색어를 입력하세요">
					<input type="submit" name="search_type" value="검색">
				</form>
			</section>

		<!-- 최근 게시글 -->
			<section>
				<div id="FAQ">
					<h3 class="brown">최근 신고 글</h3>
					<table class="faqList">
						<c:forEach var="faq" items="${faq5 }">
							<tr>
								<td><a href="FAQDetail.sc?idx=${faq.idx}&pageNum=1&keyword=">${faq.subject }</a></td>
							</tr>
						</c:forEach>
					</table>
				</div>
			</section>
			<section>
				<div id="notice">
					<h3 class="brown">최근 공지사항</h3>
					<c:forEach var="notice" items="${notice5 }">
						<table class="noticeList">
							<tr>
								<td>
								<a href="NoticeDetail.sc?idx=${notice.idx}&pageNum=1&keyword=">${notice.subject }</a></td>
							</tr>
						</table>
					</c:forEach>
				</div>
			</section>
		</section>
	</article>
	<div class="clear"></div>  
	
	<!-- 푸터 들어가는곳 -->
	<jsp:include page="../inc/footer.jsp"></jsp:include>
	<!-- 푸터 들어가는곳 -->

	
</body>
</html>