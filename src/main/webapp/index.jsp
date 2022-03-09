<%@ page import="dao.DAO"%>
<%@ page import="java.util.List"%>
<%@ page import="bean.ProductBean"%>
<%@ page import="com.google.gson.*"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Product Index</title>
</head>
<body>
	<%@include file="/header.jsp"%>
	<table>
		<tbody>
			<tr>
				<th><a href="insert.jsp"><button>新增</button></a></th>
				<th><button id="showAll">顯示全部</button></th>
			</tr>
		</tbody>
	</table>
	<table border="2">
		<thead>
			<tr>
				<th><p id="totalNum"></p></th>
				<th><button class="type" id="sort">產品種類</button>
					<form class="columnSearch">
						<input id="type" class="columnSearchInput" type="text" placeholder="">
						<button class="" type="submit">查詢</button>
					</form></th>
				<th><button class="supplier" id="sort">產品供應商</button>
					<form class="columnSearch">
						<input id="supplier" class="columnSearchInput" type="text" placeholder="">
						<button class="" type="submit">查詢</button>
					</form></th>
				<th><button class="id" id="sort">產品編號</button>
					<form class="columnSearch">
						<input id="id" class="columnSearchInput" type="text" placeholder="">
						<button class="" type="submit">查詢</button>
					</form></th>
				<th><button class="name" id="sort">產品名稱</button>
					<form class="columnSearch">
						<input id="name" class="columnSearchInput" type="text"
							placeholder="">
						<button class="" type="submit">查詢</button>
					</form></th>
				<th><button class="stock" id="sort">產品進貨量</button>
					<form class="columnSearch">
						<input id="stock" class="columnSearchInput" type="text"
							placeholder="">
						<button class="" type="submit">查詢</button>
					</form></th>
				<th><button class="cost" id="sort">產品買價</button>
					<form class="columnSearch">
						<input id="cost" class="columnSearchInput" type="text"
							placeholder="">
						<button class="" type="submit">查詢</button>
					</form></th>
				<th><button class="price" id="sort">產品售價</button>
					<form class="columnSearch">
						<input id="price" class="columnSearchInput" type="text"
							placeholder="">
						<button class="" type="submit">查詢</button>
					</form></th>
				<th>產品圖片</th>
			</tr>

		</thead>
		<tbody id="resultTable"></tbody>
	</table>
	<script src="https://unpkg.com/axios/dist/axios.min.js"></script>
	<script src="js/product.js"></script>
</body>
</html>