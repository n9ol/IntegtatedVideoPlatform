﻿(function(f) {
	f.fn.erweima = function(a) {
		if (null != a) {
			if (null != a.text) {
				var c = a.text,
				b, e, g, d;
				b = "";
				g = c.length;
				for (e = 0; e < g; e++) d = c.charCodeAt(e),
				1 <= d && 127 >= d ? b += c.charAt(e) : (2047 < d ? (b += String.fromCharCode(224 | d >> 12 & 15), b += String.fromCharCode(128 | d >> 6 & 63)) : b += String.fromCharCode(192 | d >> 6 & 31), b += String.fromCharCode(128 | d >> 0 & 63));
				a.text = b
			}
			null != a.radius && (c = .01 * parseInt(a.radius), a.radius = c);
			null != a.mSize && (c = .01 * parseInt(a.mSize), a.mSize = c);
			null != a.mPosX && (c = .01 * parseInt(a.mPosX), a.mPosX = c);
			null != a.mPosY && (c = .01 * parseInt(a.mPosY), a.mPosY = c)
		}
		a = f.extend({
			render: "canvas",
			ecLevel: "H",
			minVersion: 6,
			fill: "#666",
			background: "#fff",
			text: "http://www.jq22.com",
			size: 300,
			radius: .5,
			quiet: 4,
			mode: 2,
			mSize: .1,
			mPosX: .5,
			mPosY: .5,
			label: "\u626b\u7801\u5173\u6ce8\u6211",
			fontname: "Microsoft YaHei",
			fontcolor: "orange"
		},
		a);
		f(this).empty().qrcode(a)
	}
})(jQuery);