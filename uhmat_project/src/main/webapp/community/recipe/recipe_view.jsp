<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link href="https://fonts.googleapis.com/icon?family=Material+Icons" rel="stylesheet">
<title>Insert title here</title>
</head>
<body>
	<h1>recipe_view.jsp</h1>
	
	<section>
		<table>
			<tr>
				<td>${recipe.nickname }</td> 
			</tr>
			<tr>
				<td>${recipe.datetime } | ${recipe.readcount }</td> 
			</tr>
	
		</table>
	</section>
	
	<section>
		${recipe.subject }
	</section>
	<!-- 사진 -->
	<section>
		<table>
			<tr>
				<td width="200"><img src="./upload/recipe_upload/${recipe.real_File1 }"></td>
				<td width="200"><img src="./upload/recipe_upload/${recipe.real_File2 }"></td>
				<td width="200"><img src="./upload/recipe_upload/${recipe.real_File3 }"></td>
				<td width="200"><img src="./upload/recipe_upload/${recipe.real_File4 }"></td>
				<td width="200"><img src="./upload/recipe_upload/${recipe.real_File5 }"></td>
			</tr>
		</table>
	</section>
	<section>
		${recipe.content }
	</section>
	<hr>
	
	<section>
		<input type="button" value="댓글" onclick="location.href='RecipeReplyForm.co?idx=${param.idx}&pageNum=${param.pageNum}'">
		<input type="button" value="수정" onclick="location.href='RecipeModifyForm.co?idx=${recipe.idx}&pageNum=${param.pageNum}'">
		<input type="button" value="삭제" onclick="location.href='RecipeDeleteForm.co?idx=${recipe.idx}&pageNum=${param.pageNum}'">
		<input type="button" value="목록" onclick="location.href='RecipeList.co?pageNum=${param.pageNum}'">
	</section>
	
	<section> 
		<table border="1">
		<c:forEach items="${recipeReplyList }" var="recipeReplyList">
			<tr>
				<td>
					<c:forEach begin="1" end="${recipeReplyList.re_lev }">
						<i class="material-icons" style="font-size:20px;color:red">subdirectory_arrow_right</i>
					</c:forEach>
					${recipeReplyList.nickname }
				</td>
				<td width="500"> ${recipeReplyList.content } </td>
				<td>${recipeReplyList.date } </td>
				<td><input type="button" value="대댓글" onclick="location.href='RecipeRereplyForm.co?idx=${param.idx}&pageNum=${param.pageNum}&reply_idx=${recipeReplyList.idx} '"></td>
				<td><input type="button" value="댓글삭제" onclick="location.href='RecipeReplyDeleteForm.co?idx=${recipe.idx}&pageNum=${param.pageNum}&reply_idx=${recipeReplyList.idx}&nickname=${recipeReplyList.nickname}'"> </td>
				<td><input type="button" value="댓글수정" onclick="location.href='RecipeReplyModifyForm.co?idx=${recipe.idx}&pageNum=${param.pageNum}&reply_idx=${recipeReplyList.idx}&nickname=${recipeReplyList.nickname}'"></td>
			</tr>
		</c:forEach>
		</table>
	</section>
	
</body>
</html>