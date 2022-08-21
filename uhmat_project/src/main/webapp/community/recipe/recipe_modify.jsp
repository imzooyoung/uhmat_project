<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style type="text/css">
	#modifyForm {
		width: 500px;
		height: 450px;
		border: 1px solid red;
		margin: auto;
	}
	
	h1 {
		text-align: center;
	}
	
	table {
		margin: auto;
		width: 450px;
	}
	
	.td_left {
		width: 150px;
		background: orange;
		text-align: center;
	}
	
	.td_right {
		width: 300px;
		background: skyblue;
	}
	
	#commandCell {
		text-align: center;
	}
</style>
</head>
<body>
	<!-- 게시판 글 수정 -->
	<section>
		<h1>recipe_modifyForm</h1>
		<form action="RecipeModifyPro.co" name="recipeForm" method="post" enctype="multipart/form-data">
			<!-- 게시물 수정에 필요한 글번호와 페이징 처리에 필요한 페이지번호도 함께 전달 -->
			<input type="hidden" name="idx" value="${recipe.idx }">
			<input type="hidden" name="pageNum" value="${param.pageNum }">
			<!-- 업로드에 사용된 파일이름을 불러옵니다. -->
			<input type="hidden" name="fileName" value="${param.fileName }">
			<table>
				<tr>
					<td>닉네임</td>
					<td><input type="text" id="nickname" name="nickname" value="${recipe.nickname }" required="required" readonly="readonly"></td>
				</tr>
				<tr>
					<td>제목</td>
					<td><input type="text" id="subject" name="subject" value="${recipe.subject }" required="required"></td>
				</tr>
				<tr>
					<td>내용</td>
					<td><textarea rows="15" cols="40" id="content" name="content" required="required"> ${recipe.content }</textarea></td>
				</tr>
				<tr>
<!-- 					파일 첨부 형식은 input 태그의 type="file" 속성 사용 -->
					<td><input type="file" name="file1" multiple="multiple" required="required" /></td>
				</tr>
				<tr>
<!-- 					파일 첨부 형식은 input 태그의 type="file" 속성 사용 -->
					<td><input type="file" name="file2" multiple="multiple" ></td>
				</tr>
				<tr>
<!-- 					파일 첨부 형식은 input 태그의 type="file" 속성 사용 -->
					<td><input type="file" name="file3" multiple="multiple" ></td>
				</tr>
				<tr>
<!-- 					파일 첨부 형식은 input 태그의 type="file" 속성 사용 -->
					<td><input type="file" name="file4" multiple="multiple" ></td>
				</tr>
				<tr>
<!-- 					파일 첨부 형식은 input 태그의 type="file" 속성 사용 -->
					<td><input type="file" name="file5" multiple="multiple" ></td>
				</tr>
			</table>
			<section>
				<input type="submit" value="수정">&nbsp;&nbsp;
				<input type="reset" value="다시쓰기">&nbsp;&nbsp;
				<input type="button" value="취소" onclick="history.back()">			
			</section>
		</form>
	</section>
</body>
</html>