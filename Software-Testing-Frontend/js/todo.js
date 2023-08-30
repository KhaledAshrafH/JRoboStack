$(document).ready(function () {
    $.ajax({
        type: "GET",
        url: "http://localhost:8080/list",
        success: function (data) {
            var todos = JSON.parse(JSON.stringify(data));
            var table = document.getElementById("todo-table");
            for (var i = 0; i < todos.length; i++) {
                createNewRow(table, todos[i]);
            }
        },
        error: function (xhr, status, error) {
            console.log("Error retrieving todos: " + error);
        }
    });
});

function createNewRow(table, todo) {
    var row = table.insertRow(-1);
    var idCell = row.insertCell(0);
    var titleCell = row.insertCell(1);
    var descCell = row.insertCell(2);
    var completedCell = row.insertCell(3);
    var deleteCell = row.insertCell(4);
    row.id = "row-" + todo.id;
    idCell.innerHTML = todo.id;
    titleCell.innerHTML = todo.title;
    descCell.innerHTML = todo.description;
    var checkbox = document.createElement("input");
    checkbox.id = "checkbox-" + todo.id;
    checkbox.type = "checkbox";
    checkbox.checked = todo.completed;
    checkbox.classList.add("form-check-input");
    checkbox.onclick = (function (id) {
        return function () {
            updateTodo(id, this.checked);
        };
    })(todo.id);
    completedCell.appendChild(checkbox);
    deleteCell.innerHTML = "<button class= 'btn btn-danger' onclick='deleteTodo(" + todo.id + ")'>Delete</button>";
}

function deleteTodo(id) {
    $.ajax({
        type: "DELETE",
        url: "http://localhost:8080/delete?id=" + id,
        success: function () {
            console.log("Todo deleted successfully");
            var row = document.getElementById("row-" + id);
            row.parentNode.removeChild(row);
        }
    });
}

function updateTodo(id, completed) {
    $.ajax({
        type: "PUT",
        url: "http://localhost:8080/update?id=" + id + "&completed=" + completed,
        success: function () {
            console.log("Todo updated successfully");
        },
        error: function (xhr, status, error) {
            console.log("Error updating todo: " + error);

            alert("Error updating todo: " + error);
            var checkbox = document.getElementById("checkbox-" + id);
            checkbox.checked = !completed;
        }
    });
}

function addTodoFormHandler(event) {
    event.preventDefault();
    var todoInput = document.getElementById("todo");
    var descInput = document.getElementById("desc");
    var todo = todoInput.value;
    var desc = descInput.value;

    $.ajax({
        type: "POST",
        url: "http://localhost:8080/create",
        data: JSON.stringify({ title: todo, description: desc }),
        contentType: "application/json",
        success: function (data) {
            console.log("Todo added successfully");
            var todoList = document.getElementById("todo-table");
            createNewRow(todoList, data);
        },
        error: function (xhr, status, error) {
            console.log("Error adding todo: " + error);
        }
    });

    todoInput.value = "";
    descInput.value = "";
}

function clearRowsIn(table) {
    while (table.rows.length > 0) {
        table.deleteRow(0);
    }
}

function listAllTodoFormHandler(event) {
    $.ajax({
        type: "GET",
        url: "http://localhost:8080/list",
        success: function (data) {
            var todos = JSON.parse(JSON.stringify(data));
            var table = document.getElementById("todo-table");
            clearRowsIn(table);
            for (var i = 0; i < todos.length; i++) {
                createNewRow(table, todos[i]);
            }
        }
    });
}

function listCompletedTodoFormHandler(event) {
    $.ajax({
        type: "GET",
        url: "http://localhost:8080/listCompleted",
        success: function (data) {
            var todos = JSON.parse(JSON.stringify(data));
            var table = document.getElementById("todo-table");
            clearRowsIn(table);
            for (var i = 0; i < todos.length; i++) {
                createNewRow(table, todos[i]);
            }
        }
    });
}