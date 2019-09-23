//观看直播视频
function watchZVideo(id,type,isGoClass){
	if(isGoClass === "1"){
		switch (type) {
		case 'A':
			window.open(ctx+"/online/qualityPlay?id="+id,"_blank");
			break;
		case 'G':
			window.open(ctx+"/online/inManyPlay?id="+id,"_blank");
			break;
		case 'Z':
			window.open(ctx+"/online/livePlay?id="+id,"_blank");
			break;
		}
	}else if(isGoClass === "2"){
		layer.msg("直播尚未开始!");
	}
}