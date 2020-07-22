<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html><html><head><meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" type="text/css" href="common.css">
</head><body>
<table><caption>게시글 상세 보기</caption>
	<tr><th>번호</th><td>${board.num}</td></tr>
	<tr><th>제목</th><td>${board.subject}</td></tr>
	<tr><th>작성자</th><td>${board.writer}</td></tr>
	<!-- pre 줄바꿈, 간격 등 입력한 형태를 보존하여 출력 -->
	<tr><th>내용</th><td><pre>${board.content}</pre></td></tr>
	<tr><th>이메일</th><td>${board.email}</td></tr>
	<tr><th>IP</th><td>${board.ip}</td></tr>
	<tr><th>조회수</th><td>${board.readcount}</td></tr>
	<tr><th>작성일</th><td>${board.reg_date}</td></tr>
</table>
<button onclick="location.href='updateForm.action?num=${board.num}&pageNum=${pageNum}'">수정</button>
<button onclick="location.href='deleteForm.action?num=${board.num}&pageNum=${pageNum}'">삭제</button>
<button onclick="location.href='insertForm.action?num=${board.num}&pageNum=${pageNum}'">답변</button>
<button onclick="location.href='list.action?pageNum=${pageNum}'">목록</button>
</body>
</html>

