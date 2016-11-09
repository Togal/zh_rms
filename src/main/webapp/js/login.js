$(function(){
	//用户登录
	$('#form-sigin').submit(function(){
		$(this).find('input').parent().find('.hint').attr('style','display:none');
		var ySubmit = true;
		$.each($(this).find('input'),function(i,item){
			if(!ySubmit){
				return;
			}
			if(null == $(item).val() || '' == $(item).val()){
				$(item).parent().find('.hint-'+ $(item).attr('name') +'').attr('style','display:block;');
				$(item).focus();
				ySubmit = false;
			}
		});
		return ySubmit;
	});
})
