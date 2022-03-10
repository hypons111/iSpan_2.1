const form = document.querySelector("#insert")
const id = document.querySelector("#id")
const name = document.querySelector("#name")
const supplier = document.querySelector("#supplier")
const type = document.querySelector("#type")
const submit = document.querySelector("#submit")
const inputs = document.querySelectorAll(".input")
const submitResult = document.querySelector("#submitResult")
const BASE_URL = "http://localhost:8080/iSpanSecond/AllDataJsonServlet"
let rawData = []

axios
	.get(BASE_URL)
	.then((response) => {
		rawData = response.data
	})
	.catch((err) => console.log(err));


form.addEventListener("change", () => {
	if (type.value !== "" && supplier.value !== "") {
		id.value = idGenerator()
	}
})


name.addEventListener("change", () => {
	for (let i = 0; i < rawData.length; i++) {
		if (rawData[i].name.toLowerCase() === name.value.trim().toLowerCase()) {
			alert("已有同名稱產品")
			name.value = ""
			i = rawData.length
		}
	}
})

form.addEventListener("submit", (event) => {
	let switcher = "on"
	submitResult.innerText = ""
	
	inputs.forEach(input => {
		if (input.value.trim() == "") {
			switcher = "off"
			submitResult.innerHTML += "請輸入" + input.previousElementSibling.innerText + "<br>"
			event.preventDefault()
		}
	})

	for (let i = 4; i < inputs.length - 1; i++) {
		if (inputs[i].value.trim().length == 0) {
			switcher = "off"
			event.preventDefault()
			submitResult.innerHTML += inputs[i].previousElementSibling.innerText + "不可空白" + "<br>"
		}
		if (i === 4) {
			if(inputs[i].value.match(/\./)) {
				switcher = "off"
				event.preventDefault()
				submitResult.innerHTML += inputs[i].previousElementSibling.innerText + "只可輸入整數" + "<br>"
			}
		}
		if (inputs[i].value.match(/[\`\~\!\@\#\$\%\^\&\*\(\)\_\+\-\=\{\}\[\]\;\:\'\"\<\>\?\,\\]/) ||
			inputs[i].value.match(/[\u4E00-\u9FFF]/) ||
			inputs[i].value.match(/[a-zA-Z]/)) {
			switcher = "off"
			event.preventDefault()
			submitResult.innerHTML += inputs[i].previousElementSibling.innerText + "只可輸入數字" + "<br>"
		}
		if(inputs[i].value.match("..")) {
			inputs[i].value = inputs[i].value.replace("..", ".")
		}
	}
	if (switcher === "on") {
		event.currentTarget.submit()
	}
})

function idGenerator() {
	str = ""
	let d = new Date();
	const arr = [
		d.getFullYear().toString().substring(2, 4),
		d.getMonth() + 1,
		d.getDate(),
		d.getHours(),
		d.getMinutes(),
		d.getSeconds()]
	for (let i = 0; i < 6; i++) {
		if (arr[i].toString().length < 2) {
			str += "0" + arr[i].toString()
		} else {
			str += arr[i].toString()
		}
	}
	return str
}