function view_View(){var k=window,j=document,s=k.external,t,p,o='',w={},F=[],C=[],n=[],z,B;if(!k.__gwt_stylesLoaded){k.__gwt_stylesLoaded={};}if(!k.__gwt_scriptsLoaded){k.__gwt_scriptsLoaded={};}function u(){try{return s&&(s.gwtOnLoad&&k.location.search.indexOf('gwt.hybrid')== -1);}catch(a){return false;}}
function v(){if(t&&p){t(z,'view.View',o);}}
function r(){var i,h;j.write('<script id="__gwt_marker_view.View"><\/script>');h=j.getElementById('__gwt_marker_view.View');if(h){i=h.previousSibling;}function d(b){var a=b.lastIndexOf('/');return a>=0?b.substring(0,a+1):'';}
;if(i&&i.src){o=d(i.src);}if(o==''){var c=j.getElementsByTagName('base');if(c.length>0){o=c[c.length-1].href;}else{var g=j.location;var e=g.href;o=d(e.substr(0,e.length-g.hash.length));}}else if(o.match(/^\w+:\/\//)){}else{var f=j.createElement('img');f.src=o+'clear.cache.gif';o=d(f.src);}if(h){h.parentNode.removeChild(h);}}
function A(){var f=document.getElementsByTagName('meta');for(var d=0,g=f.length;d<g;++d){var e=f[d],h=e.getAttribute('name'),b;if(h){if(h=='gwt:property'){b=e.getAttribute('content');if(b){var i,c=b.indexOf('=');if(c>=0){h=b.substring(0,c);i=b.substring(c+1);}else{h=b;i='';}w[h]=i;}}else if(h=='gwt:onPropertyErrorFn'){b=e.getAttribute('content');if(b){try{B=eval(b);}catch(a){alert('Bad handler "'+b+'" for "gwt:onPropertyErrorFn"');}}}else if(h=='gwt:onLoadErrorFn'){b=e.getAttribute('content');if(b){try{z=eval(b);}catch(a){alert('Bad handler "'+b+'" for "gwt:onLoadErrorFn"');}}}}}}
function m(a,b){return b in F[a];}
function l(a){var b=w[a];return b==null?null:b;}
function E(d,e){var a=n;for(var b=0,c=d.length-1;b<c;++b){a=a[d[b]]||(a[d[b]]=[]);}a[d[c]]=e;}
function q(d){var e=C[d](),b=F[d];if(e in b){return e;}var a=[];for(var c in b){a[b[c]]=c;}if(B){B(d,a,e);}throw null;}
C['gwtx.logging.console']=function(){try{if('console' in k){var b=k.console;if(b.log&&(b.debug&&(b.info&&(b.warn&&b.error)))){return 'firebug';}else if(b.log){return 'safari';}else{return 'null';}}else if('opera' in k){if(opera.postError){return 'opera';}else{return 'null';}}else{return 'null';}}catch(a){k.alert('Unexpected exception in logging console creator, using null logger: '+a);return 'null';}};F['gwtx.logging.console']={'firebug':0,'null':1,'opera':2,'safari':3};C['user.agent']=function(){var d=navigator.userAgent.toLowerCase();var b=function(a){return parseInt(a[1])*1000+parseInt(a[2]);};if(d.indexOf('opera')!= -1){return 'opera';}else if(d.indexOf('webkit')!= -1){return 'safari';}else if(d.indexOf('msie')!= -1){var c=/msie ([0-9]+)\.([0-9]+)/.exec(d);if(c&&c.length==3){if(b(c)>=6000){return 'ie6';}}}else if(d.indexOf('gecko')!= -1){var c=/rv:([0-9]+)\.([0-9]+)/.exec(d);if(c&&c.length==3){if(b(c)>=1008)return 'gecko1_8';}return 'gecko';}return 'unknown';};F['user.agent']={'gecko':0,'gecko1_8':1,'ie6':2,'opera':3,'safari':4};view_View.onScriptLoad=function(a){view_View=null;t=a;v();};r();A();var y;function x(){if(!p){p=true;v();if(j.removeEventListener){j.removeEventListener('DOMContentLoaded',x,false);}if(y){clearInterval(y);}}}
if(j.addEventListener){j.addEventListener('DOMContentLoaded',x,false);}var y=setInterval(function(){if(/loaded|complete/.test(j.readyState)){x();}},50);var D;try{E(['firebug','gecko1_8'],'9E7315A0D96C43B1DA6CCF6F20122C7F');E(['opera','gecko1_8'],'9E7315A0D96C43B1DA6CCF6F20122C7F');E(['null','gecko1_8'],'9E7315A0D96C43B1DA6CCF6F20122C7F');E(['safari','gecko1_8'],'9E7315A0D96C43B1DA6CCF6F20122C7F');E(['opera','ie6'],'A1E722CEC8EBFA3134698DB383E62F78');E(['firebug','ie6'],'A1E722CEC8EBFA3134698DB383E62F78');E(['null','ie6'],'A1E722CEC8EBFA3134698DB383E62F78');E(['safari','ie6'],'A1E722CEC8EBFA3134698DB383E62F78');E(['null','gecko'],'AA6BCB05DF1118FB7E7586592979294C');E(['safari','gecko'],'AA6BCB05DF1118FB7E7586592979294C');E(['firebug','gecko'],'AA6BCB05DF1118FB7E7586592979294C');E(['opera','gecko'],'AA6BCB05DF1118FB7E7586592979294C');E(['safari','safari'],'B0B1FAB91D7568DCC163B4A5FD7BA46E');E(['firebug','safari'],'B0B1FAB91D7568DCC163B4A5FD7BA46E');E(['null','safari'],'B0B1FAB91D7568DCC163B4A5FD7BA46E');E(['opera','safari'],'B0B1FAB91D7568DCC163B4A5FD7BA46E');E(['opera','opera'],'EFB49A1A857E25543D0F551453CAFB2C');E(['null','opera'],'EFB49A1A857E25543D0F551453CAFB2C');E(['safari','opera'],'EFB49A1A857E25543D0F551453CAFB2C');E(['firebug','opera'],'EFB49A1A857E25543D0F551453CAFB2C');D=n[q('gwtx.logging.console')][q('user.agent')];}catch(a){return;}D+='.cache.js';if(!__gwt_stylesLoaded['View.css']){__gwt_stylesLoaded['View.css']=true;document.write('<link rel="stylesheet" href="'+o+'View.css">');}j.write('<script src="'+o+D+'"><\/script>');}
view_View.__gwt_initHandlers=function(i,e,j){var d=window,g=d.onresize,f=d.onbeforeunload,h=d.onunload;d.onresize=function(a){try{i();}finally{g&&g(a);}};d.onbeforeunload=function(a){var c,b;try{c=e();}finally{b=f&&f(a);}if(c!=null){return c;}if(b!=null){return b;}};d.onunload=function(a){try{j();}finally{h&&h(a);}};};view_View();