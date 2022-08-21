package action;

import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.Timestamp;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

import svc.RecipeDetailService;
import svc.RecipeModifyProService;
import vo.ActionForward;
import vo.RecipeDTO;

public class RecipeModifyProAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		System.out.println("레시피글 수정 작업 요청 액션 - RecipeModifyProAction");
		
		ActionForward forward = null;
		RecipeDTO recipe = new RecipeDTO();
		
		int idx = Integer.parseInt(request.getParameter("idx"));
		String filePath =  request.getServletContext().getRealPath("/reciep_upload");
		
		RecipeDetailService service = new RecipeDetailService();
		recipe = service.getRecipe(idx);
		String[] fileName = {
				recipe.getReal_File1(), 
				recipe.getReal_File2(),
				recipe.getReal_File3(),
				recipe.getReal_File4(),
				recipe.getReal_File5()
		};
		
		System.out.println(fileName);
		
		for(int i = 0; i < 5; i++) {
			Path path = Paths.get(filePath + "/" + fileName[i]);
					Files.deleteIfExists(path);
					System.out.println("fileDelete is success");
			
		};
		
		// -----------------------------------------------------------------
		
		// 파일 업로드 처리를 위해 MultipartRequest 객체 활용(cos.jar 라이브러리 필요)
		// 1. 업로드 파일 위치(이클립스 프로젝트 상의 경로) 저장
		String uploadPath = "recipe_upload"; // 가상의 폴더명
		
		// 2. 업로드 파일 크기를 제한하기 위한 정수 형태의 값 지정(10MB 제한)
		int fileSize = 1024 * 1024 * 10; // byte(1) -> KB(1024Byte) -> MB(1024KB) -> 10MB 단위 변환
				
		// 3. 현재 프로젝트(서블릿)를 처리하는 객체인 서블릿 컨텍스트 객체 얻어오기
		ServletContext context = request.getServletContext();
				
		// 4. 업로드 파일이 저장되는 실제 경로를 얻어오기
		// => ServletContext 객체의 getRealPath() 메서드 호출
		String realPath = context.getRealPath(uploadPath); // 가상의 업로드 폴더명을 파라미터로 전달
//		System.out.println(realPath);
		// D:\workspace_uhmat\.metadata\.plugins\org.eclipse.wst.server.core\tmp0\wtpwebapps\ uhmat_project\recipe_upload
		
		// 5. MultipartRequest 객체 생성
		// => 생성자 파라미터로 파일 업로드에 필요한 각종 파라미터를 전달
		MultipartRequest multi = new MultipartRequest(
			request, // 1) 실제 요청 정보가 포함된 request 객체
			realPath, // 2) 실제 업로드 폴더 경로
			fileSize, // 3) 업로드 파일 크기
			"UTF-8", // 4) 파일명에 대한 인코딩 방식(한글 처리 등이 필요하므로 UTF-8 지정)
			new DefaultFileRenamePolicy() // 5) 중복 파일명에 대한 처리를 담당하는 객체(파일명 뒤에 숫자 1 부터 차례대로 부여)
		);
		
		recipe.setIdx(Integer.parseInt(multi.getParameter("idx")));
		recipe.setNickname(multi.getParameter("nickname"));
		recipe.setSubject(multi.getParameter("subject"));
		recipe.setContent(multi.getParameter("content"));
		recipe.setOriginal_File1(multi.getOriginalFileName("file1"));
		recipe.setReal_File1(multi.getFilesystemName("file1"));
		recipe.setOriginal_File2(multi.getOriginalFileName("file2"));
		recipe.setReal_File2(multi.getFilesystemName("file2"));
		recipe.setOriginal_File3(multi.getOriginalFileName("file3"));
		recipe.setReal_File3(multi.getFilesystemName("file3"));
		recipe.setOriginal_File4(multi.getOriginalFileName("file4"));
		recipe.setReal_File4(multi.getFilesystemName("file4"));
		recipe.setOriginal_File5(multi.getOriginalFileName("file5"));
		recipe.setReal_File5(multi.getFilesystemName("file5"));	
		
		System.out.println("레시피프로액션 - " + recipe);
		
		// ====================================================================
		
		RecipeModifyProService service2 = new RecipeModifyProService();
		boolean isModifySuccess = service2.modifyRecipe(recipe);
		
		if(!isModifySuccess) {
			response.setContentType("text/html; charset=UTF-8");
			PrintWriter out = response.getWriter();
			out.println("<script>");
			out.println("alert('글 수정 실패!')");
			out.println("history.back()");
			out.println("</script>");
		} else {
			forward = new ActionForward();
			forward.setPath("RecipeDetail.co?idx=" + Integer.parseInt(multi.getParameter("idx")) + "&pageNum=" + multi.getParameter("pageNum"));
			forward.setRedirect(true);
		}
		
		return forward;
	}

}
