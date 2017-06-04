(function (window) {
	'use strict';

	// enter key 이벤트 적용
	$("#todoText").keyup(function(e) {
	    if (e.keyCode == 13) insertTodo();
	});
	//초기 시작값은 항상 all
	loadTodoList("");
})(window);


// gubun : "" ,"completed", "active"
function loadTodoList(gubun){
	var ajaxUrl = "http://localhost:8080/api/todos/" + gubun;

	// class 변경
	if (gubun == "") {
		$("#all").attr("class","selected");
		$("#active").removeClass("selected");
		$("#completed").removeClass("selected");
		$("#gubun").val("");
	} else if(gubun == "active"){
		$("#active").attr("class","selected");
		$("#all").removeClass("selected");
		$("#completed").removeClass("selected");
		$("#gubun").val("active");
	} else {
		$("#completed").attr("class","selected");
		$("#active").removeClass("selected");
		$("#all").removeClass("selected");
		$("#gubun").val("completed");
	}
	//select list
	$.ajax({
		type : "GET",
		url : ajaxUrl,
		dataType : "json",
		success : function(todoList) {
			// clear list
			$(".todo-list").html("");
			for(var i = 0; i < todoList.length ; i++){
					var html = "";
					todoList[i].completed == 0 ? html += "<li>" : html += "<li class='completed'>";
					html += "	<div class='view'>";
					todoList[i].completed == 0 ? html += "<input class='toggle' type='checkbox' id='"+ todoList[i].id +"' onclick='completedTodo(this)'>"
																			: html += "<input class='toggle' type='checkbox' id='"+ todoList[i].id +"' onclick='completedTodo(this)' checked>";
					html += "		<label>" + todoList[i].todo + "</label>";
					html += "		<button class='destroy' onclick=deleteTodo('"+ todoList[i].id +"')></button>";
					html += "	</div>";
					html += "</li>";
					$(".todo-list").append(html);
					selectCnt();
			}
			$("#lessCnt").text(todoList[0].cnt);
		}, error:function(xhr, status, error){
				alert("xhr : " + xhr.responseText + " status : " + status + " error : " + error);
    }
	});
}

function selectCnt(){
	var cntUrl = "http://localhost:8080/api/todos/left";
	//select cnt
	$.ajax({
		type : "GET",
		url : cntUrl,
		dataType : "json",
		success : function(data) {
			$("#lessCnt").text(data[0].cnt);
		}, error:function(xhr, status, error){
				alert("xhr : " + xhr.responseText + " status : " + status + " error : " + error);
    }
	});
}

//insert todo
function insertTodo(){
	var ajaxUrl = "http://localhost:8080/api/todos";
	var param = {"todo" : $("#todoText").val(), "completed" : 0, "date" : $.now()};

	if($("#todoText").val() == "") {
		alert("내용을 입력해주세요.");
		return false;
	}

	$.ajax({
		type : "POST",
		contentType: "application/json",
		url : ajaxUrl,
		data : JSON.stringify(param) ,
		dataType : "json",
		success : function(data) {
			loadTodoList("");
			$("#todoText").val("");
		}, error:function(xhr, status, error){
				alert("xhr : " + xhr.responseText + " status : " + status + " error : " + error);
    }
	});
}

//complete todo
function completedTodo(obj){
	var ajaxUrl = "http://localhost:8080/api/todos/" + obj.id;
	var param = $(obj).is(":checked") ? {"id" : obj.id, "completed" : 1} : {"id" : obj.id, "completed" : 0};
	$.ajax({
		type : "PUT",
		contentType: "application/json",
		url : ajaxUrl,
		data : JSON.stringify(param),
		dataType : "json",
		success : function(data) {
			loadTodoList($("#gubun").val());
		}, error:function(xhr, status, error){
				alert("xhr : " + xhr.responseText + " status : " + status + " error : " + error);
    }
	});
}

//delete todo
function deleteTodo(id){
	var ajaxUrl = "http://localhost:8080/api/todos/" + id;
	$.ajax({
		type : "DELETE",
		url : ajaxUrl,
		dataType : "json",
		success : function(data) {
			loadTodoList($("#gubun").val());
		}, error:function(xhr, status, error){
				alert("xhr : " + xhr.responseText + " status : " + status + " error : " + error);
    }
	});
}

function clearCompleted(){
	var ajaxUrl = "http://localhost:8080/api/todos/clear";
	$.ajax({
		type : "DELETE",
		url : ajaxUrl,
		dataType : "json",
		success : function(data) {
			loadTodoList("");
		}, error:function(xhr, status, error){
				alert("xhr : " + xhr.responseText + " status : " + status + " error : " + error);
    }
	});
}
