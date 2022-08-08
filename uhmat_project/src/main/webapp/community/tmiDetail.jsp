<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %> 
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<section>
		<table border="1">
			<tr><th width="70">제 목</th><td colspan="3" >${tmiBoard.subject }</td></tr>
			<tr>
				<th width="70">작성자</th><td>${tmiBoard.nickname }</td>
				<th width="70">작성일</th><td>${tmiBoard.datetime }</td>
			<tr>
				<th width="70">조회수</th>
				<td>${tmiBoard.readcount }</td>
			</tr>
			
		</table>
	</section>
	<section>
		${tmiBoard.content }
	</section>
	
	<hr>
	
	<section>
		<input type="text" placeholder="댓글" name="reply">
		<input type="button" value="댓글 전송">
	</section>
	
	<section>
		<input type="button" value="수정" onclick="location.href='TmiModifyForm.co?idx=${tmiBoard.idx}&pageNum=${param.pageNum}'">
		<input type="button" value="삭제" onclick="location.href='TmiDeleteForm.co?board_num=${tmiBoard.idx}&pageNum=${param.pageNum}'">
		<input type="button" value="목록" onclick="location.href='TmiList.co?pageNum=${param.pageNum}'">
	
	</section>
</body>
</html>