<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

	<%@include file="/header.jsp"%>
	<form id="insert" method="post" action="InsertServlet"
		enctype="multipart/form-data">

		<p>
			<label>產品種類</label>
			<select id="type" class="input" name="type">
				<option value=""></option>
				<option value="MEAT">肉類</option>
				<option value="VEGE">蔬菜</option>
				<option value="BEVERAGE">飲品</option>
			</select>
		</p>

		<p>
			<label>產品供應商</label> <select id="supplier" class="input"
				name="supplier">
				<option value=""></option>
				<option id="01" value="悠活農村">悠活農村</option>
				<option id="02" value="菜鮮生">菜鮮生</option>
				<option id="03" value="新鮮屋">新鮮屋</option>
				<option id="04" value="天天蔬菜箱">天天蔬菜箱</option>
				<option id="05" value="GO蔬菜箱">GO蔬菜箱</option>
			</select>
		</p>

		<p>
			<label>產品編號(自動產生)</label><input class="input" id="id" type="text"
				name="id">
		</p>

		<p>
			<label>產品名稱</label><input id="name" class="input" type="text"
				name="name">
		</p>

		<p>
			<label>產品進貨量</label><input id="stock" class="input" type="text"
				name="stock">
		</p>

		<p>
			<label>產品買價</label><input id="cost" class="input" type="text"
				name="cost">
		</p>

		<p>
			<label>產品售價</label> <input id="price" class="input" type="text" name="price">
		</p>

		<p>
			<label>產品圖片</label> <input id="image" class="input" type="file" name="file" />
		</p>

		<p>
			<button id="submit" type="submit">新增</button>
			<button type="reset">重設</button>
		</p>

		<p id="submitResult"></p>
	</form>
	<script src="https://unpkg.com/axios/dist/axios.min.js"></script>
	<script src="js/productInsert.js"></script>

</body>
</html>

