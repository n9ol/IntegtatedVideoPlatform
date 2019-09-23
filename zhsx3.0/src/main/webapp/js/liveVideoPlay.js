var flashvars={
    f : ctx + '/webVideoResourcesPath/urlLive?videoId=' + receive.id,
    a:'',
    s:1,
    c:0,
    st:1,
    fc:1
};
var params={bgcolor:'#FFF',allowFullScreen:true,allowScriptAccess:'always',wmode:'transparent'};
CKobject.embedSWF(ctx +'/common/ckplayer/ckplayer/ckplayer.swf', receive.div,'ckplayer_a1','100%', '100%',flashvars,params);