

<%--
  Created by IntelliJ IDEA.
  User: Test
  Date: 07.05.2024
  Time: 13:32
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>flowers</title>
    <link href="https://fonts.googleapis.com/css?family=Josefin+Sans:100,200,300,medium,500,600,700,100italic,200italic,300italic,italic,500italic,600italic,700italic&display=swap" rel="stylesheet" />
    <link rel="preconnect" href="https://fonts.googleapis.com">
    <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-QWTKZyjpPEjISv5WaRU9OFeRpok6YctnYmDr5pNlyT2bRjXh0JMhjY6hW+ALEwIH" crossorigin="anonymous">
    <link rel="stylesheet" href="../style/style.css">

    <link href="https://fonts.googleapis.com/css?family=Josefin+Sans:100,200,300,medium,500,600,700,100italic,200italic,300italic,italic,500italic,600italic,700italic&display=swap" rel="stylesheet" />
    <link rel="preconnect" href="https://fonts.googleapis.com">
    <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>

    <link rel="stylesheet" href="../style/style.css">
    <style>
        .pricing-header {
            max-width: 700px;
        }
        .w-100{
            alignment: center;
            max-width: 350px;
        }
        .table-responsive{
            margin: 0 auto;
            max-width: 960px;
        }
    </style>
</head>
<body>
<script type="module">
    let request = new XMLHttpRequest();
    request.open("GET", "flowers.json");

    request.responseType = "json";
    request.send();

    request.onload = function() {
        let response = request.response;
        fillTable(response);
    }

    function fillTable(response) {
        let tbody = document.querySelector("tbody");
        response.forEach((object, index) => {
            let newflower = document.createElement("tr");
            newflower.innerHTML = `
<th scope="row" class="text-start">${object["flower_name"]}</th>
<td class="text-body-secondary">${object["sort"]}</td>
<td class="text-body-secondary">${object["color"]}</td>
<td class="text-body-secondary">${object["live"]}</td>
<td class="text-body-secondary">${object["red_book"]}</td>
<td>
<button onclick="deleteRow(this)">Delete</button>
<button onclick="editRow(this)">Update</button>
</td>`;
            tbody.appendChild(newflower);
        });
    }

    function deleteRow(btn) {
        let row = btn.parentNode.parentNode;
        row.parentNode.removeChild(row);
    }

    function editRow(btn) {
        let row = btn.parentNode.parentNode;
        let cells = row.getElementsByTagName('td');
        let flowerData = {
            sort: cells[0].textContent,
            color: cells[1].textContent,
            live: cells[2].textContent,
            red_book: cells[3].textContent
        };

        let formHtml = `
<form id="editForm">
<label>Sort:</label>
<input type="text" name="sort" value="${flowerData.sort}" /><br/>
<label>Color:</label>
<input type="text" name="color" value="${flowerData.color}" /><br/>
<label>Live:</label>
<input type="text" name="live" value="${flowerData.live}" /><br/>
<label>Red Book:</label>
<input type="text" name="red_book" value="${flowerData.red_book}" /><br/>
<button type="button" onclick="saveChanges(this, ${row.rowIndex})">Save Changes</button>
</form>
`;

        showModal(formHtml);
    }

    function showModal(content) {
        let modal = document.createElement('div');
        modal.innerHTML = content;
        document.body.appendChild(modal);
    }

    function saveChanges(btn, rowIndex) {
        let form = document.getElementById('editForm');
        let newValues = {
            sort: form.sort.value,
            color: form.color.value,
            live: form.live.value,
            red_book: form.red_book.value
        };

        let table = document.querySelector('table');
        let row = table.rows[rowIndex];
        let cells = row.getElementsByTagName('td');
        cells[0].textContent = newValues.sort;
        cells[1].textContent = newValues.color;
        cells[2].textContent = newValues.live;
        cells[3].textContent = newValues.red_book;

        document.body.removeChild(btn.closest('div'));
    }
</script>

<div class="container py-3">
    <header>


        <div class="pricing-header p-3 pb-md-4 mx-auto text-center">
            <h1 class="block-text__title">FLOWERS</h1>

        </div>
    </header>
    <main>
        <div class="pricing-header p-3 pb-md-4 mx-auto text-center">
            <p>
                <button type="button" class="w-100 btn btn-lg btn-primary" onclick="location.href='${pageContext.request.contextPath}/flowers_table'">START</button>
            </p>
        </div>

        <div class="table-responsive">
            <table class="table text-center">
                <thead>
                <tr>
                    <th style="width: 34%;">Name</th>
                    <th style="width: 34%;">sort</th>
                    <th style="width: 22%;">color</th>
                    <th style="width: 22%;">Live</th>
                    <th style="width: 22%;">red_book</th>
                    <th style="width: 22%;">Actions</th>
                </tr>
                </thead>
                <tbody>
                <tr>
                    <th scope="row" class="text-start">Роза</th>
                    <td class="text-body-secondary">красивая</td>
                    <td class="text-body-secondary">розовый</td>
                    <td class="text-body-secondary">Англия</td>
                    <td class="text-body-secondary">да</td>
                </tr>
                </tbody>
            </table>
        </div>
    </main>

    <footer >
        <div>


            <ul id="flowersList"></ul>
            <img width="300px" src="../img/111.svg" alt="Цветок 5">
            <img width="500px" src="../img/1.svg" alt="Цветок 1">
            <img width="300px" src="../img/2.svg" alt="Цветок 4">
            <div class="copyright">
                <p>© Copyright Tupota 2024</p>
            </div>
        </div>
    </footer>
</div>

<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-YvpcrYf0tY3lHB60NNkmXc5s9fDVZLESaAA55NDzOxhy9GkcIdslK1eN7N6jIeHz" crossorigin="anonymous"></script>
</body>
</html>