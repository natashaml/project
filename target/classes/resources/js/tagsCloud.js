var rnumber = Math.floor(Math.random() * 99);
var widget_so = new SWFObject("http://freeviral.ru/tagcloud.swf?r=" + rnumber,
		"tagcloudflash", "100%", "40%", "9");
widget_so.addParam("wmode", "transparent");
widget_so.addParam("allowScriptAccess", "always");
widget_so.addVariable("tcolor", "0x1E90FF");
widget_so.addVariable("tspeed", "100");
widget_so.addVariable("distr", "true");
widget_so.addVariable("mode", "tags");
widget_so.addVariable("hicolor", "0x00cc00");
widget_so.addVariable("tagcloud", $('#con').attr('name'));
widget_so.write("con");