const queryString = window.location.search
const urlParams = new URLSearchParams(queryString)
const targetID = urlParams.get('id')
const BASE_URL = "http://localhost:8080/Project_2/AllDataJsonServlet"
const typeList = ["MEAT", "VEGE", "BEVE"]
const supplierList = ["悠活農村", "菜鮮生", "新鮮屋", "天天蔬菜箱", "GO蔬菜箱"]
const columnList = ["產品種類", "產品供應商", "產品編號", "產品名稱", "產品存量", "產品買價", "產品售價", "產品圖片"]

let rawData = []
let oldProductName = ""

axios.get(BASE_URL)
	.then(response => {
		showData(...getTargetProduct(response.data))
		addEventListeners(response.data)
	})
	.catch(error => { console.log(error) })


function getTargetProduct(rawData) {
	return rawData.filter(product => product.id == targetID)
}

function showData(data) {
	let contents = "<tr>"
	contents += "<td width='155px'><input class='產品種類' type='text' name='type' value='" + data.type + "'></td>"
	contents += "<td width='155px'><input class='產品供應商' type='text' name='supplier' value='" + data.supplier + "'></td>"
	contents += "<td id='id'><input class='產品編號' id='idValue' disabled type='text' name='id' value='" + data.id + "'></td>"
	contents += "<td id='name'><input class='產品名稱' id='nameValue' type='text' name='name' value='" + data.name + "'></td>"
	contents += "<td><input class='產品存量' type='text' name='stock' value='" + data.stock + "'></td>"
	contents += "<td><input class='產品買價' type='text' name='cost' value='" + data.cost + "'></td>"
	contents += "<td><input class='產品售價' type='text' name='price' value='" + data.price + "'></td>"
	contents += "<td id='imageTD'><input class='產品圖片' type='file' name='image' /></td>"
	contents += "<td><button id='button'>修改</button></td>"
	contents += "<td><button>取消</button></td>"
	resultTable.innerHTML = contents
	oldProductName = data.name
}

function addEventListeners(data) {
	const inputs = document.querySelectorAll("input")

	document.querySelector("#id").addEventListener("click", () => {
		alert("產品編號不能更改")
	})

	document.querySelector("#nameValue").addEventListener("change", (event) => {
		for (let i = 0; i < data.length; i++) {
			if (data[i].name.toLowerCase() == event.target.value.trim().toLowerCase()) {
				alert("已有同名稱產品")
				event.target.value = oldProductName
				i = data.length
			}
		}
	})

	for (let i = 0; i < inputs.length - 2; i++) {
		if (i === 0) {
			inputs[i].addEventListener("mousedown", event => {
				let typeContent = "<select id='type' class='input' name='type'>"
				for (let j = 0; j < typeList.length; j++) {
					typeContent += "<option value='" + typeList[j] + "'>" + typeList[j] + "</option>"
				}
				typeContent += "</select>"
				event.target.parentElement.innerHTML = typeContent
			})
		}
		if (i === 1) {
			inputs[i].addEventListener("mousedown", event => {
				let supplierContent = "<select id='supplier' class='input' name='supplier'>"
				for (let k = 0; k < supplierList.length; k++) {
					supplierContent += "<option value='" + supplierList[k] + "'>" + supplierList[k] + "</option>"
				}
				supplierContent += "</select>"
				event.target.parentElement.innerHTML = supplierContent
				alert(event.target.parentElement.innerHTML)
			})
		}
	}



	document.querySelector("#editForm").addEventListener("submit", (event) => {
		submitResult.innerHTML = ""
		event.preventDefault()
		let switcher = "on"


		for (let i = 0; i < inputs.length; i++) {
			if (inputs[i].value.trim() == "" && i < 7) {
				switcher = "off"
				submitResult.innerHTML += "請輸入" + inputs[i].classList[0] + "<br>"
			}

			if (i === 4) {
				if (inputs[i].value.match(/\./)) {
					switcher = "off"
					submitResult.innerHTML += inputs[i].classList[0] + "只可輸入整數" + "<br>"
				}
			}

			if (i > 4 && i < 6) {
				if (inputs[i].value.match(/[\`\~\!\@\#\$\%\^\&\*\(\)\_\+\-\=\{\}\[\]\;\:\'\"\<\>\?\,\\]/) ||
					inputs[i].value.match(/[\u4E00-\u9FFF]/) ||
					inputs[i].value.match(/[a-zA-Z]/)) {
					switcher = "off"
					submitResult.innerHTML += inputs[i].classList[0] + "只可輸入數字" + "<br>"
				}
				if (inputs[i].value.match("..")) {
					inputs[i].value = inputs[i].value.replace("..", ".")
				}
			}
		}


		if (switcher === "on") {
			document.querySelector("#idValue").disabled = false
			event.currentTarget.submit()
		}

	})
}