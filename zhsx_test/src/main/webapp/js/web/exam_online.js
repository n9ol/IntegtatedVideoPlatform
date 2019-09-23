var HH = 0;//时
var mm = 0;//分
var ss = 0;//秒
var timeState = true;//时间状态 默认为true 开启时间
var questions= QuestionJosn;
var itemList=["A","B","C","D","E","F"]
var activeQuestion=0; //当前操作的考题编号
var questioned = 0; //
var checkQues = []; //已做答的题的集合
var intDiff = parseInt(60*times); //倒计时总秒数量
function timer(intDiff) {
  window.setInterval(function () {
    var day = 0,
      hour = 0,
      minute = 0,
      second = 0; //时间默认值
    if (intDiff > 0) {
      hour = Math.floor(intDiff / (60 * 60)) ;
      minute = Math.floor(intDiff / 60) - (day * 24 * 60) - (hour * 60);
      second = Math.floor(intDiff) - (day * 24 * 60 * 60) - (hour * 60 * 60) - (minute * 60);
    }
    if (hour <= 9) hour = '0' + hour;
    if (minute <= 9) minute = '0' + minute;
    if (second <= 9) second = '0' + second;
   $(".time").html("<font size='2'>剩余时间:</font>"+hour+"-")
    $(".time").append( minute + '-');
    $(".time").append(second );
   
    intDiff--;
  }, 1000);
}
$(function () {
	$("#testTop").attr("class","has-sub active");
	$("#testTopZ").attr("class","active2");
	timer(intDiff);
});
//展示考卷信息
if( questions == '' ){
	 $(".question_title").empty();
	 $(".question_title").html("当前考卷未添加考题！！");
	 $("#submitQuestions").hide();
	 $("#nextQuestion").hide();
	
}else{
	
	function showQuestion(id){
		console.log("id="+id);
		console.log("点答题卡");console.log(checkQues);
		$(".questioned").text(id);
		questioned = id/questions.length
		if(activeQuestion!=undefined){
			$("#ques"+activeQuestion).removeClass("question_id").addClass("active_question_id");
		}
		
		
		var question = questions[id-1];
		
		activeQuestion = id;
		$("#isshoucang").val(question.his);
		$(".question").find(".question_info").remove();
		$(".question_title").html("<strong>第 "+ id+ " 题 、</strong>"+question.questionTitle+"&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp<strong>分值：</strong>("+question.score+"分)<div class='text-left' style='position:relative;z-index:20;right:-778px;cursor: pointer; top:-108px;font-size: 15px;line-height: 30px;width:160px;'><div id='unHeart' style='color:#999999;' onclick='Dshou();' ><input type='hidden' name='queId'>  <span class='glyphicon glyphicon-heart-empty'></span> <span>收藏本题</span> </div><div id='heart' style='color:#C40000;display: none;' onclick='Qshou();'> <span class='glyphicon glyphicon-heart'></span> <span>已收藏</span> </div></div>");//问题标题;
		$("input[name='queId']").val(question.questionId);
		// $("input[name='collection']").val(question.his);
		//$("input[name='id']").val(id);
		//  his = $("input[name='collection']").val();
		var his =  $("#isshoucang").val();
		if(his == 'Y'){
			$("#unHeart").hide();
			$("#heart").show();
		}
		if(his==""){
			$("#unHeart").show();
			$("#heart").hide();
			
		}
		var qtype="";
		
		var items = question.questionItems.split(";");
		var item="";
		var cang=""
			if("1"==question.qtype){
				qtype="单选题";
				var i=0;
				$.each(question.options, function(k, v) {  
					item ="<li class='question_info' onclick='clickTrim(this,"+JSON.stringify(question)+" )' id='item"
					+i+"'><input type='radio' name='item'  id='"+id+itemList[i]+"'  value='"+k+"'>&nbsp;"+itemList[i]+"."+v+"</li>";
					$(".question").append(item);
					i++;
				}); 
			}else if("2"==question.qtype){
				qtype="多选题";
				var i=0;
				$.each(question.options, function(k, v) {  
					
					item ="<li class='question_info' onclick='clickTrim(this,"+JSON.stringify(question)+" )' id='item"
					+i+"'><input type='checkbox' name='item' id='"+id+itemList[i]+"' value='"+k+"'>&nbsp;"+itemList[i]+"."+v+"</li>";
					$(".question").append(item);
					i++;
				}); 
				
			}else if("0"==question.qtype){
				qtype="判断题";
				var i=0;
				$.each(question.options, function(k, v) {  
					
					item ="<li class='question_info' onclick='clickTrim(this,"+JSON.stringify(question)+" )' id='item"
					+i+"'><input type='radio' name='item'  id='"+id+itemList[i]+"'  value='"+k+"'>&nbsp;"+v+"</li>";
					$(".question").append(item);
					i++;
				});  
				
			}
		$("#qtype").html(qtype);
		
		
		$(".question").attr("id","question"+id);
		$("#ques"+id).removeClass("active_question_id").addClass("question_id");
		console.log("点答题卡循环钱");
		console.log(checkQues);
		console.log(checkQues.length+"已选题长度");
		for(var i=0;i<checkQues.length;i++){
			if(checkQues[i].id==id&&(checkQues[i].allOption).length>0){
				
				
				var getOption = checkQues[i].allOption.split(";");
				
				for(var j=0;j<getOption.length;j++){
					
					
					$("#"+checkQues[i].num+getOption[j]).prop("checked","checked");
					
					$("#"+checkQues[i].num+getOption[j]).parent(".question_info").addClass("clickTrim");
				}
				$("#"+checkQues[i].item).find("input").prop("checked","checked");
				
				$("#"+checkQues[i].item).addClass("clickTrim");
				$("#ques"+activeQuestion).removeClass("question_id").addClass("clickQue");
			}
		}
		progress();
	}
}
/*答题卡*/
function answerCard(){
    $(".question_sum").text(questions.length);
    for(var i=1;i<=questions.length;i++){
        var questionId ="<li id='ques"+i+"'onclick='saveQuestionState("+i+")' class='questionId'>"+i+"</li>";
        $("#answerCard ul").append(questionId);
    }
}

/*选中考题*/
var Question;

function clickTrim(source,question){
var isHave=0;//checkQues是否已经有当前操作的题；0无
	
	console.log("选中id="+source.id);
    var id = source.id;
    //我的
    var xx= $("#"+id).find("input").is(':checked');
   
    if(xx==true){
    	 $("#"+id).find("input").prop("checked",false);
    	 $("#"+id).removeClass("clickTrim");
         $("#ques"+activeQuestion).addClass("question_id").addClass("clickQue");
//    	 progress();
    }else{
      $("#"+id).find("input").prop("checked","checked");
      $("#"+id).addClass("clickTrim");
      $("#ques"+activeQuestion).removeClass("question_id").addClass("clickQue");
    }
//    $("#"+id).find("input").prop("checked","checked");
//    $("#"+id).addClass("clickTrim");
//    $("#ques"+activeQuestion).removeClass("question_id").addClass("clickQue");
    
    var ques =0;
    
    console.log("循环之前");console.log(checkQues);

    for(var i=0;i<checkQues.length;i++){	
    	
    	console.log("checkQues[i].id="+checkQues[i].id);
    	console.log("activeQuestion="+activeQuestion);
    	
    	   if( checkQues[i].id==activeQuestion){
    		   isHave=1;//有
    		   
    		   console.log("checkQues[i].allOption="+checkQues[i].allOption);
    		   if(checkQues[i].allOption==undefined)
    			checkQues[i].allOption="";
    		   console.log("console.log(source);");
    		   console.log($(source));
    		   console.log( $("#"+id));
    		   console.log(source);
    		   var theOption=$(source).find("input[name=item]:checked").val();//$(source).find("input[name=item]:checked").val();
    		   
    		   
    		   console.log("theOption"); console.log(theOption);
    		   var index =checkQues[i].allOption.indexOf(theOption); 
    		   console.log("index"); console.log(index);
    		   if(index>=0){
    			   if("2"==question.qtype){
    				   checkQues[i].allOption=(checkQues[i].allOption).replace(theOption+';','');
    	    		     
    			   }else{
    				   checkQues[i].allOption="";
    				  
    			   } 
    			   
    			   $("#"+id).removeClass("clickTrim");
    			   $("#"+checkQues[i].num+theOption).prop("checked",false);
    			   
    			  //答题卡样式
    			   $("#ques"+activeQuestion).removeClass("clickQue").addClass("question_id");
    			   
    			   
    		   }else{
    			ques = checkQues[i].id;
    			checkQues[i].item = id;//获取当前考题的选项ID
    			checkQues[i].answer =$(source).find("input[name=item]:checked").val();//获取当前考题的选项值（选择或不选择）//$(source).find("input[name=item]:checked").val();
    			if("2"==question.qtype){
    				checkQues[i].allOption+=theOption+';';//获取当前考题的选项值(多选扩展)
    				 console.log("----1添加处二位热无热无");console.log(theOption);
				}else{
					checkQues[i].allOption=theOption;
				}
    			
    		   }
           }
    }
    console.log("循环之后");console.log(isHave);
    if(checkQues.length==0|| isHave==0){
        var check ={};
        console.log("循环之后check.allOption"+check.allOption);
		if(check.allOption==undefined)
			check.allOption="";
		 var theOption=$("#"+id).find("input[name=item]:checked").val();
		 var index =check.allOption.indexOf(theOption); 
		   if(index>=0){
			   if("2"==question.qtype){
				   check.allOption=check.allOption.replace(theOption+';','');
				   console.log("添加处二位热无热无4444");console.log(theOption);
				   
			   }else{
				   check.allOption="";
				   
			   }
			  
			   $("#"+id).removeClass("clickTrim");
			     $("#"+check.num+theOption).prop("checked",false);
			     
			     //答题卡样式
			     $("#ques"+activeQuestion).removeClass("clickQue").addClass("question_id");
			   
		   }else{
			   check.tId=question.tId;
			   check.qtype=question.qtype;
			   check.questionId=question.questionId;
			   check.score=question.score;
			   check.sortOrder=question.sortOrder;
			    check.id=activeQuestion;//获取当前考题的编号
				check.item = id;//获取当前考题的选项ID
				//check.num=question.questionNum;//考题号
				check.answer =$("#"+id).find("input[name=item]:checked").val();//获取当前考题的选项值
			   
				if("2"==question.qtype){
					 check.allOption+=theOption+';';//获取当前考题的选项值(多选扩展)
				}else{
					check.allOption=theOption;
				}
			   
		   }
		
		   console.log('----------------------');
		   console.log(check);
        checkQues.push(check);
        console.log('----------------------');
    }
    $(".question_info").each(function(){
        var otherId =$(this).attr("id");
        if(otherId!=id && "2"!=question.qtype){
        //  $("#"+otherId).find("input").prop("checked",false);
        	
          $("#"+otherId).removeClass("clickTrim");
        }
    })
    Question = activeQuestion;
  // progress();
    
}

/*设置进度条*/
function progress(){
	//
	var finish=1;
	for(var i=0; i<checkQues.length;i++){
		console.log(checkQues[i]);
		if(checkQues[i].allOption.length>0){
			finish++;
		}
	}
	console.log("做了几题"+finish);
	var prog = finish/questions.length;
    //var prog = ($(".active_question_id").length+1)/questions.length;
    var pro = $(".progress").parent().width() * prog;
    $(".progres").text((prog*100).toString().substr(0,5)+"%")
    $(".progress").animate({
        width: pro,
        opacity: 0.5
    }, 1000);
}

/*保存考题状态 已做答的状态*/
function saveQuestionState(clickId){
	$("input[name='idm']").val(clickId);
    showQuestion(clickId)
}

$(function(){
    $(".middle-top-left").width($(".middle-top").width()-$(".middle-top-right").width())
    $(".progress-left").width($(".middle-top-left").width()-200);
    progress();
    answerCard();
    showQuestion(1);
    $("input[name='idm']").val(1);
    /*alert(QuestionJosn.length);*/
    /*实现进度条信息加载的动画*/
    var str = '';
    /*开启或者停止时间*/
    $(".time-stop").click(function () {
        timeState = false;
        $(this).hide();
        $(".time-start").show();
    });
    $(".time-start").click(function () {
        timeState = true;
        $(this).hide();
        $(".time-stop").show();
    });

  

    /*答题卡的切换*/
    $("#openCard").click(function(){
        $("#closeCard").show();
        $("#answerCard").slideDown();
        $(this).hide();
    })
    $("#closeCard").click(function(){
        $("#openCard").show();
        
        $("#answerCard").slideUp();
        $(this).hide();
    })

   
     //提交试卷
    function tijiao(){
          var cont=questions.length;
          var testId = $("input[name='testId']").val();
			$.ajax({
				type:'post',
	   			url:ctx+"/testwebstutestquestionanswer/addTest",
	   	        data: {answer:JSON.stringify(checkQues),questionCon:cont,testId:testId},
	   			success:function(data){
	   				var success= data.success;
	   				if(success=="true"){
	   					$("#tan").show();
	   					$('.daan').html("<li>总共<span>"+data.questionCon+"</span>题</li><li>回答对了<span>"+data.rightNum+"</span>题</li><li>回答错<span>"+data.errorNum+"</span>题</li><li>未答提数<span>"+data.notDoNum+"</span>题</li><li>您的得分是<span>"+data.score+"</span>分</li><input type='hidden' name='testId' value='"+data.testId+"'/>");
	   				}else{
	   					alert('考试异常！');
	   				} 
	   			}
	   		});
    }
    
    
    function endTest(){
    	alert("考试时间到!");
    	tijiao();
    }
    var t = parseInt(60*times)+2;
   
    var tt = setTimeout(endTest,t*1000)
    $("#submitQuestions").click(function(){
    	tijiao();
    	clearTimeout(tt);
    });
    
    //进入下一题
    $("#nextQuestion").click(function(){
    		
    	
    	   if(activeQuestion<questions.length) {
   			showQuestion(activeQuestion+1);
   			} else if (activeQuestion==questions.length){ 
   			alert('已经是最后一题！');
   				
   			}else{
   				showQuestion(activeQuestion);
   			}
    })
  
   
});
function Dshou(){
    $("#unHeart").hide();
    $("#heart").show();
    var queId = $("input[name='queId']").val();
   // $("input[name='collection']").val("Y")
  shoucang(queId);
}
function Qshou(){
    $("#heart").hide();
    $("#unHeart").show();
    var queId = $("input[name='queId']").val();
    //$("input[name='collection']").val("")
  quxiaoshoucang(queId);
};
//收藏
function shoucang(queId){
	var idm = $("input[name='idm']").val();
	$.ajax({
		   type: "POST",
		   url: ctx+"/sysHistory/insterSysHistory",
		   data: {pubType:"Q",pubFlag:"C",pubId:queId},
		   success: function(msg){
			   questions[idm-1].his="Y";
			   layer.msg(msg);
		   }
		});

}
//取消收藏
function quxiaoshoucang(queId){
	var idm = $("input[name='idm']").val();
		$.ajax({
		   type: "POST",
		   url: ctx+"/sysHistory/delSysHistory",
		   data: {pubType:"Q",pubFlag:"C",pubId:queId},
		   success: function(msg){
			   questions[idm-1].his="";
			   layer.msg(msg);
		   }
		});
}