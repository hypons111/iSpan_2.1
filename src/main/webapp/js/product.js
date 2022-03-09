const BASE_URL = "http://localhost:8080/Project_2/AllDataJsonServlet";
const resultTable = document.querySelector("#resultTable")
const id = document.querySelector('#id')
const columnSearchs = document.querySelectorAll('.columnSearch')
const columnSearchInputs = document.querySelectorAll('.columnSearchInput')
const sorts = document.querySelectorAll('#sort')
let currentData = []
let rawData = ""
let sortStates = "ASC"



function ultimateSearch() {
	let tempData = rawData
	for (let k = 0; k < columnSearchInputs.length; k++) {
		if (columnSearchInputs[k].value !== "") {
			if (k < 4) {
				tempData = tempData.filter(product => product[columnSearchInputs[k].id].toLowerCase().includes(columnSearchInputs[k].value))
			} else {
				if (columnSearchInputs[k].value.includes("<")) {
					tempData = tempData.filter(product => Number(product[columnSearchInputs[k].id]) < Number(columnSearchInputs[k].value.slice(1)))
				} else if (columnSearchInputs[k].value.includes(">")) {
					tempData = tempData.filter(product => Number(product[columnSearchInputs[k].id]) > Number(columnSearchInputs[k].value.slice(1)))
				} else {
					tempData = tempData.filter(product => Number(product[columnSearchInputs[k].id]) == Number(columnSearchInputs[k].value))
				}
			}
		}
	}
	return tempData
}

axios
	.get(BASE_URL)
	.then(response => {
		return new Promise(resolve => {
			resolve(response.data)
			rawData = response.data
		})
	})
	.then(rawData => { showData(rawData) })
	.catch(error => console.log(error));


//show all data button listener
document.querySelector('#showAll').addEventListener('click', () => {
	showData(rawData)
	columnSearchInputs.forEach(columnSearchInput => {
		columnSearchInput.value = ""
	})

})

//sorts listener
sorts.forEach(sort => {
	sort.addEventListener('click', (event) => {
		event.preventDefault()
		let attribute = event.target.classList[0]
		if (sortStates === "ASC") {
			currentData.sort((a, b) => {
				if (a[attribute] < b[attribute]) { return -1 }
				if (a[attribute] > b[attribute]) { return 1 }
				return 0
			})
			sortStates = "DESC"
		} else {
			currentData.sort((a, b) => {
				if (a[attribute] < b[attribute]) { return 1 }
				if (a[attribute] > b[attribute]) { return -1 }
				return 0
			})
			sortStates = "ASC"
		}
		showData(currentData)
	})
})

//column searchs listener
for (let i = 0; i < columnSearchs.length; i++) {
	columnSearchs[i].addEventListener('submit', (event) => {
		event.preventDefault()
		showData(ultimateSearch())
	})
}

// show data
function showData(data) {
	currentData = []
	currentData.push(...data)
	document.querySelector("#totalNum").innerText = data.length + "筆"
	contents = ""
	for (let i = 0; i < data.length; i++) {
		contents += "<tr><td>" + (i + 1) + "</td>"
		contents += "<td>" + data[i].type + "</td>"
		contents += "<td>" + data[i].supplier + "</td>"
		contents += "<td>" + data[i].id + "</td>"
		contents += "<td>" + data[i].name + "</td>"
		contents += "<td>" + data[i].stock + "</td>"
		contents += "<td>" + data[i].cost + "</td>"
		contents += "<td>" + data[i].price + "</td>"
		contents += "<td><img src='image/product/" + data[i].image + "' width='50px'></td>"
		contents += "<td><a href=edit.jsp?id=" + data[i].id + "><button>修改</button></a></td>"
		contents += "<td><a href=DeleteServlet?id=" + data[i].id + "><button>刪除</button></a></td></tr>"
	}
	resultTable.innerHTML = contents
}

