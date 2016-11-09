<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/jsp/public/head.jsp"%>
<div id="content">
	<div class="left">
		<table align="left" border="0" cellspacing="0" cellpadding="0" class="menu_table">
			<tr>
				<td style="padding-top:10px;width:20px;white-space: normal;" valign="top">
					<c:forEach items="${masterMenus }" var="masterMenu">
						<div class="master_menu">
							<a href="javascript:;" onclick="showSlaveMenu('topSlaveMenu_${masterMenu.id}')">${masterMenu.menuname }</a>
						</div>
					</c:forEach>
				</td>
				<td style="background-color: #EEE;width: 100%;">
					<div class="slave-nav">
						<c:forEach items="${topSlaveMenus}" var="topSlaveMenu">
							<ul id="topSlaveMenu_${topSlaveMenu.id }" class="mtree transit">
								<%@ include file="/jsp/public/menu.jsp"%>
							</ul>
						</c:forEach>
					</div>
				</td>
			</tr>
		</table>
		<ul class="mtree transit" style="display:block;">
			<li><a href="#">Africa</a>
				<ul>
					<li><a href="#">Algeria</a></li>
					<li><a href="#">Marocco</a></li>
					<li><a href="#">Libya</a></li>
					<li><a href="#">Somalia</a></li>
					<li><a href="#">Kenya</a></li>
					<li><a href="#">Mauritania</a></li>
					<li><a href="#">South Africa</a></li>
				</ul></li>
			<li><a href="#">America</a>
				<ul>
					<li><a href="#">North-America</a>
						<ul>
							<li><a href="#">Canada</a></li>
							<li><a href="#">USA</a>
								<ul>
									<li><a href="#">New York</a></li>
									<li><a href="#">California</a>
										<ul>
											<li><a href="#">Los Angeles</a></li>
											<li><a href="#">San Diego</a></li>
											<li><a href="#">Sacramento</a></li>
											<li><a href="#">San Francisco</a></li>
											<li><a href="#">Bakersville</a></li>
										</ul></li>
									<li><a href="#">Lousiana</a></li>
									<li><a href="#">Texas</a></li>
									<li><a href="#">Nevada</a></li>
									<li><a href="#">Montana</a></li>
									<li><a href="#">Virginia</a></li>
								</ul></li>
						</ul></li>
					<li><a href="#">Middle-America</a>
						<ul>
							<li><a href="#">Mexico</a></li>
							<li><a href="#">Honduras</a></li>
							<li><a href="#">Guatemala</a></li>
						</ul></li>
					<li><a href="#">South-America</a>
						<ul>
							<li><a href="#">Brazil</a></li>
							<li><a href="#">Argentina</a></li>
							<li><a href="#">Uruguay</a></li>
							<li><a href="#">Chile</a></li>
						</ul></li>
				</ul></li>
			<li><a href="#">Asia</a>
				<ul>
					<li><a href="#">China</a></li>
					<li><a href="#">India</a></li>
					<li><a href="#">Malaysia</a></li>
					<li><a href="#">Thailand</a></li>
					<li><a href="#">Vietnam</a></li>
					<li><a href="#">Singapore</a></li>
					<li><a href="#">Indonesia</a></li>
					<li><a href="#">Mongolia</a></li>
				</ul></li>
			<li><a href="#">Europe</a>
				<ul>
					<li><a href="#">North</a>
						<ul>
							<li><a href="#">Norway</a></li>
							<li><a href="#">Sweden</a></li>
							<li><a href="#">Finland</a></li>
						</ul></li>
					<li><a href="#">East</a>
						<ul>
							<li><a href="#">Romania</a></li>
							<li><a href="#">Bulgaria</a></li>
							<li><a href="#">Poland</a></li>
						</ul></li>
					<li><a href="#">South</a>
						<ul>
							<li><a href="#">Italy</a></li>
							<li><a href="#">Greece</a></li>
							<li><a href="#">Spain</a></li>
						</ul></li>
					<li><a href="#">West</a>
						<ul>
							<li><a href="#">France</a></li>
							<li><a href="#">England</a></li>
							<li><a href="#">Portugal</a></li>
						</ul></li>
				</ul></li>
			<li><a href="#">Oceania</a>
				<ul>
					<li><a href="#">Australia</a></li>
					<li><a href="#">New Zealand</a></li>
				</ul></li>
			<li><a href="#">Arctica</a></li>
			<li><a href="#">Antarctica</a></li>
		</ul>
		
		<ul class="mtree transit" style="display:none;">
			<li>
				<a href="#">Africa</a>
				<ul>
					<li><a href="#">Algeria</a></li>
					<li><a href="#">Marocco</a></li>
					<li><a href="#">Libya</a></li>
					<li><a href="#">Somalia</a></li>
					<li><a href="#">Kenya</a></li>
					<li><a href="#">Mauritania</a></li>
					<li><a href="#">South Africa</a></li>
				</ul>
			</li>
		</ul>
		

	</div>
	<div class="right">
		<iframe class="right-iframe" frameborder="0" src=""></iframe>
	</div>
</div>


<script src='/menu/js/jquery.velocity.min.js'></script> 
<script src="/menu/js/mtree.js"></script> 
<script>
$(function(){
	var mtree = $('ul.mtree');
	mtree.wrap('<div class=mtree-demo></div>');
	var skins = ['bubba','skinny','transit','jet','nix'];
	mtree.addClass(skins[0]);
	$('body').prepend('<div class="mtree-skin-selector" style="display:none;"><ul class="button-group radius"></ul></div>');
	var s = $('.mtree-skin-selector');
	$.each(skins, function(index, val) {
	  s.find('ul').append('<li><button class="small skin">' + val + '</button></li>');
	});
	s.find('ul').append('<li><button class="small csl active">Close Same Level</button></li>');
	s.find('button.skin').each(function(index){
	  $(this).on('click.mtree-skin-selector', function(){
	    s.find('button.skin.active').removeClass('active');
	    $(this).addClass('active');
	    mtree.removeClass(skins.join(' ')).addClass(skins[index]);
	  });
	})
	s.find('button:first').addClass('active');
	s.find('.csl').on('click.mtree-close-same-level', function(){
	  $(this).toggleClass('active'); 
	});
});

//菜单切换
$(function(){
	$('#content').find('.left').find('.mtree-demo').attr('style','display:none');
});

function showSlaveMenu(slaveMenuId){
	$('#content').find('.left').find('.mtree-demo').attr('style','display:none');
	$('#' + slaveMenuId).parent().attr('style','display:block;');
}
</script>
<!-- content结束 -->
<%@ include file="/jsp/public/footer.jsp"%>
