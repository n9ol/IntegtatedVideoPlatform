var flashvars={
	f:ctx+'/webVideoResourcesPath/url?videoId='+receive.id,
	a:'',
	s:'1',
	c:0,
	b:1
	};
var params={bgcolor:'#FFF',allowFullScreen:true,allowScriptAccess:'always',wmode:'transparent'};
CKobject.embedSWF(receive.swf,receive.div,'ckplayer_a1','100%','100%',flashvars,params);