(function(_) {
	var _report = _report || {};
	
	/**
	 * get url parameters
	 */
	var getQueryString = function() {
		var url = location.href, params = {}, query = url.split('?')[1] || '';

		params.isEmpty = true;
		if (query == '') {
			return params;
		}
		params.isEmpty = false;
		var paramStrings = query.split('&');
		for (var i = 0, len = paramStrings.length; i < len; i++) {
			var kv = paramStrings[i].split('=');
			params[kv[0]] = decodeURIComponent(kv[1]);
		}
		return params;

	}
	
	/**
	 * 
	 */
	$.extend(_report, {
		utils : {
			queryString : getQueryString(),
			parse:function(s){
				return parseInt(s.substr(1,s.length -2));
			}
		},
		// store or remove  using cookies
		cache : {
			put : function(k, v) {
				$.cookie(k, v);
			},
			get : function(k) {
				return $.cookie(k);
			},
			remove : function(k) {
				$.cookie(k, {
					expires : -1
				});
			}
		},
		ui : {
			alert : function(msg, callback) {
				var cb = callback || function() {
				};
				bootbox.dialog({
					message : "<span class='bigger-110'>" + msg + "</span>",
					buttons : {
						"success" : {
							"label" : "确定",
							"className" : "btn-sm btn-success",
							"callback" : cb
						}
					}
				});

			}
		}
	});

	_.report = _report;
})(window);