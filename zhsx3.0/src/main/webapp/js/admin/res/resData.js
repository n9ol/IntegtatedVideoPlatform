$("#resData").find("div.yiru").mouseover(function(){
	var className = $(this).attr("class");
	if(className === 'clear zongTiao1 yiru' || className === 'clear zongTiao1 yiru border_color'){
		$(this).addClass("border_color");
		$(this).find("span.EOGexf1").css("display","none");
	}else if(className != 'clear zongTiao yiru mouseover pcicon'){
		$(this).addClass("mouseover");
		$(this).find("span.EOGexf1").css("display","block");
		$(this).find("span.EOGexf1").find("img").attr("src", ctx+"/img/select_fill_1.png");
	}
});

$("#resData").find("div.yiru").mouseout(function() {
	var className = $(this).attr("class");
	if(className === 'clear zongTiao1 yiru border_color' || className === 'clear zongTiao1 yiru'){
		var checkboxs = $(this).find("span.span_input").find('input').is(":checked");
		if (!(checkboxs === true)) {
			$(this).removeClass('border_color')
		}
	}else if(className === "clear zongTiao yiru mouseover"){
		$(this).removeClass("mouseover");
		$(this).find("span.EOGexf1").find("img").attr("src", "data:image/gif;base64,R0lGODlhAQABAIAAAAAAAP///ywAAAAAAQABAAACAUwAOw==");
	}
});

$("#resData").find("div.yiru").click(function() {
	var className = $(this).attr("class");
	if(className === 'clear zongTiao yiru mouseover'){
		$(this).addClass("pcicon");
		$(this).find("span.EOGexf1").find("img").attr("src", ctx+"/img/select_fill.png");
		$(this).find("span.span_input").find("input").prop("checked",true);
		var isok = true;
		$(".span_input input").each(function(){
			if(!(this.checked)){
				isok = false;
			}
		});
		if(isok){
			$(".name input").prop("checked", true);
		}
	}else if(className === 'clear zongTiao yiru mouseover pcicon'){
		$(this).removeClass("pcicon");
		$(this).find("span.EOGexf1").find("img").attr("src", ctx+"/img/select_fill_1.png");
		$(this).find("span.span_input").find("input").prop("checked",false);
		$(".name input").prop("checked", false);
	}
});	