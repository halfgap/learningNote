/**
 * 
 */
$(function(){
	var w = document.body.parentElement.clientWidth;
	$("#kaishi").css("width",w+"px");
	onresize = function(){
		change();
	}
	function change(){
	var w = document.body.parentElement.clientWidth;
	var h = document.body.parentElement.clientHeight;
	$("html").css("background-size",w+"px "+h+"px");
	$("#kaishi").css("width",w+"px");
	}
	
	
	
	$("#kaishi1").hover(function(){
		$("#kaishi1").css("width","400px");
		$("#kaishi1").text("点击展开");
	},
		function(){
			$("#kaishi1").css("width","100px");
			$("#kaishi1").text("菜单");
	}
	);
	$("#kaishi2").click(function(){
			$("#egg").text("你发现了一个小彩蛋~");
			$("#egg").animate({"right":"0px"}).hide(2000);
			$("body").css("background","url('imgs/bg1.jpg') fixed center center no-repeat");
	});
	
	
	$("#back").click(function(){
		window.location.href="index.html";
	});
	
});

setInterval(function(){
	 
	var d = new Date();
	kaishi2.innerText = d.toLocaleTimeString();
},1000);

