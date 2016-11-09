function checkForm(form){
	var err = '';
	$(':input,select').each(function(){
		if(('' == $(this).val() || '-1' == $(this).val() || null == $(this).val()) && '*' == $(this).prev().prev().html()){
			if($(this).attr('type') == 'file'){
				
			}
			err += ('[' + $(this).prev().html() + ']不能为空\r\n');
		}
	});
	if(err == ''){
		form.submit.disabled = true;
		form.submit.value = '提交中'
	}else{
		alert('错误提示:\r\n' + err);
		form.submit.disabled = false;
		return false;
	}
}

function checkDelForm(){
	if(checkCheckBoxChs('btSelectItem') == false){
		alert('至少选择一项！');
		return false;
	}else{
		return confirm('您确认要操作选中的记录？（如有子级数据则会级联操作）');
	}
}

function checkCheckBoxChs(objName){
	var obj = document.getElementsByName(objName);
	var objLen = obj.length;
	var objYN = false;
	var i;
	for(i = 0; i< objLen; i++){
		if(obj[i].checked == true){
			objYN = true;
			break;
		}
	}
	return objYN;
}

function createHiddenInputs(table){
	$('#hiddenInputs').empty();
	$.each(getIdSelections(table),function(i,item){
		$('#hiddenInputs').append('<input type="hidden" name="ids" value="'+ item +'">')
	});
}

function getIdSelections(table) {
    return $.map($(table).bootstrapTable('getSelections'), function(row) {
        return row.id
    });
}

function frameLoad(url){
	if(url == '')
		return;
	$('.right-iframe').attr('src',url);
}

//当前用户权限
var currentUserHasPrivileges = new Array();
$.each($(parent.document).find('.currentUserHasPrivilege'),function(i,item){
	var array = $(item).text().split('@');
	if(array[0] == '' || array[1] == '')
		return;
	currentUserHasPrivileges.push(array[1]);
});

/*按钮级别权限控制*/
function accessControl(){
	if($('#currentUser').val() == 'zowbman'){
		$('.access-control').removeClass('access-control');
		return;
	}
	$.each($('.access-control'),function(i,item){
		$.each(currentUserHasPrivileges,function(j,item2){
			var selectDomMethod = item2.substring(0,1);
			item2 = item2.substring(item2.indexOf(selectDomMethod) + 1,item2.length);
			if(selectDomMethod == '.'){
				if($(item).attr('class').indexOf(item2) > -1){
					$(item).removeClass('access-control');
					return;
				}
			}else{
				if($(item).attr('id') != undefined && $(item).attr('id').indexOf(item2) > -1){
					$(item).removeClass('access-control');
					return;
				}
			}
		});
	});
}